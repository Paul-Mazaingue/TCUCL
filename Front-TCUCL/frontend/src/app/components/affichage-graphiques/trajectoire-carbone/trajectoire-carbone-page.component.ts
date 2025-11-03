import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

interface PosteEmission {
  id: string;
  nom: string;
  emissionsReference: number;
  reductionBasePct: number; // réglage poste par poste (modifiable en mode avancé)
}
interface TableRow {
  label: string;
  values: number[];
  dropBetweenPct: number;
  annualRequiredPct: number;
}

interface StoredSettings {
  reduction: number;
  lockGlobal: boolean;
  startYear: number;
  endYear: number;
  postes: Record<string, number>; // id -> reductionBasePct
}

// 🎨 Palette Okabe–Ito (daltonien-friendly)
const POSTES_COLORS: Record<string, string> = {
  'energie': '#E69F00',
  'autres-deplacements': '#56B4E9',
  'numerique': '#009E73',
  'batiments': '#F0E442',
  'mobilite-dom-tram': '#0072B2',
  'dechets': '#D55E00',
  'achats': '#CC79A7',
  'autres-immobilisations': '#000000',
  'emissions-fugitives': '#999999'
};

const POSTES_ADEME_DEFAULTS: PosteEmission[] = [
  { id: 'energie',               nom: 'Énergie',                               emissionsReference: 1200, reductionBasePct: 30 },
  { id: 'mobilite-dom-tram',     nom: 'Déplacements domicile-travail',         emissionsReference: 850,  reductionBasePct: 40 },
  { id: 'autres-deplacements',   nom: 'Déplacements professionnels',           emissionsReference: 650,  reductionBasePct: 35 },
  { id: 'batiments',             nom: 'Bâtiments et mobilier',                 emissionsReference: 500,  reductionBasePct: 25 },
  { id: 'numerique',             nom: 'Numérique',                             emissionsReference: 280,  reductionBasePct: 45 },
  { id: 'achats',                nom: 'Achats et restauration',                emissionsReference: 950,  reductionBasePct: 35 },
  { id: 'dechets',               nom: 'Déchets',                               emissionsReference: 180,  reductionBasePct: 50 },
  { id: 'autres-immobilisations',nom: 'Autres immobilisations',                emissionsReference: 220,  reductionBasePct: 20 },
  { id: 'emissions-fugitives',   nom: 'Émissions fugitives',                   emissionsReference: 45,   reductionBasePct: 20 },
];

const STORAGE_KEY = 'trajectoire_carbone_settings_v1';

@Component({
  selector: 'app-trajectoire-carbone',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './trajectoire-carbone-page.component.html',
  styleUrls: ['./trajectoire-carbone-page.component.scss']
})
export class TrajectoireCarbonePageComponent implements OnInit, AfterViewInit, OnDestroy {

  // Bornes
  readonly REF_YEAR = 2019;
  readonly MAX_YEAR = 2050;

  // Contrôles principaux
  allowedYears: number[] = [];
  startYear = 2019;
  endYear   = 2030;
  reduction = 32;             // objectif global %
  showAdvanced = false;       // affichage panneau avancé
  lockGlobal = false;         // 🔒 verrouiller la cible globale ?

  // Données
  postes = POSTES_ADEME_DEFAULTS.map(p => ({ ...p }));
  private defaultPostes = POSTES_ADEME_DEFAULTS.map(p => ({ ...p })); // pour réinitialiser
  years: number[] = [];
  tableRows: TableRow[] = [];

  // Chart.js
  private chart: any = null;
  private ChartCtor: any = null;

  constructor(private router: Router) {}

  // ===== Lifecycle =====
  ngOnInit(): void {
    this.allowedYears = Array.from({ length: this.MAX_YEAR - this.REF_YEAR + 1 }, (_, i) => this.REF_YEAR + i);
    this.loadSettings();          // ⚙️ charge les réglages
    this.rebuildYears();
    this.generateTableRows();     // prépare les données avant le graphique
  }

  async ngAfterViewInit(): Promise<void> {
    await this.initChartIfPossible();
    this.ensureChart();
    this.updateChartData(true);
    this.chart?.update(); // affichage initial garanti
  }

  ngOnDestroy(): void { if (this.chart?.destroy) this.chart.destroy(); }

  // ===== Navigation =====
  goHome(): void { this.router.navigate(['/dashboard']); }

  // ===== Cadran global =====
  get totalFirstYear(): number {
    return this.tableRows.reduce((s, r) => s + (r.values[0] ?? 0), 0);
  }
  get totalLastYear(): number {
    const lastIdx = Math.max(0, this.years.length - 1);
    return this.tableRows.reduce((s, r) => s + (r.values[lastIdx] ?? 0), 0);
  }
  get overallDropBetweenPct(): number {
    const first = this.totalFirstYear;
    const last  = this.totalLastYear;
    if (first <= 0) return 0;
    return Math.round(((last - first) / first) * 100);
  }

  // ===== Utilitaires =====
  private clampPct(x: number): number { return Math.max(0, Math.min(100, x)); }

  /** Moyenne pondérée ACTUELLE des réglages poste par poste (sans rescaling) en % */
  get weightedCurrentPct(): number {
    const totalRef = this.postes.reduce((s, p) => s + p.emissionsReference, 0);
    if (totalRef <= 0) return 0;
    const frac = this.postes.reduce((s, p) => s + (p.emissionsReference * (this.clampPct(p.reductionBasePct) / 100)), 0) / totalRef;
    return Math.round(frac * 10000) / 100; // 2 décimales
  }
  /** Écart vs. l’objectif global (en points de %) */
  get weightedGapPct(): number {
    return Math.round((this.weightedCurrentPct - this.reduction) * 100) / 100;
  }

  // ===== Persistance =====
  saveSettings(): void {
    const payload: StoredSettings = {
      reduction: this.reduction,
      lockGlobal: this.lockGlobal,
      startYear: this.startYear,
      endYear: this.endYear,
      postes: this.postes.reduce<Record<string, number>>((acc, p) => {
        acc[p.id] = this.clampPct(p.reductionBasePct);
        return acc;
      }, {})
    };
    try { localStorage.setItem(STORAGE_KEY, JSON.stringify(payload)); } catch {}
  }

  // Public wrapper to ensure template can call a clearly public method in all build modes
  saveSettingsPublic(): void {
    this.saveSettings();
  }

  private loadSettings(): void {
    try {
      const raw = localStorage.getItem(STORAGE_KEY);
      if (!raw) return;
      const s = JSON.parse(raw) as Partial<StoredSettings>;
      if (typeof s.reduction === 'number') this.reduction = this.clampPct(s.reduction);
      if (typeof s.lockGlobal === 'boolean') this.lockGlobal = s.lockGlobal;
      if (typeof s.startYear === 'number') this.startYear = Math.max(this.REF_YEAR, Math.min(this.MAX_YEAR, s.startYear));
      if (typeof s.endYear === 'number')   this.endYear   = Math.max(this.REF_YEAR, Math.min(this.MAX_YEAR, s.endYear));
      if (s.postes && typeof s.postes === 'object') {
        this.postes.forEach(p => {
          const saved = s.postes![p.id];
          if (typeof saved === 'number') p.reductionBasePct = this.clampPct(saved);
        });
      }
    } catch { /* ignore */ }
  }

  // ===== Réductions effectives par poste =====
  private getEffectiveReductionsByPoste(): Map<string, number> {
    const map = new Map<string, number>();

    if (this.lockGlobal) {
      this.postes.forEach(p => map.set(p.id, this.clampPct(p.reductionBasePct)));
      return map;
    }

    // lockGlobal = false → recaler tous les postes vers la cible globale
    const totalRef = this.postes.reduce((s, p) => s + p.emissionsReference, 0);
    const weightedBase =
      this.postes.reduce((s, p) => s + p.emissionsReference * (this.clampPct(p.reductionBasePct) / 100), 0) / totalRef; // fraction

    const target = this.reduction / 100;
    const factor = weightedBase > 0 ? (target / weightedBase) : 1;

    this.postes.forEach(p => {
      map.set(p.id, this.clampPct(p.reductionBasePct * factor));
    });
    return map;
  }

  /** Rééquilibre les autres postes pour conserver l’objectif global (lockGlobal = true) */
  private rebalanceOthersToKeepGlobal(changedId: string): void {
    const totalRef = this.postes.reduce((s, p) => s + p.emissionsReference, 0);
    const w = (p: PosteEmission) => p.emissionsReference / totalRef;

    const T = this.reduction / 100; // cible globale (fraction)
    const changed = this.postes.find(p => p.id === changedId);
    if (!changed) return;

    changed.reductionBasePct = this.clampPct(changed.reductionBasePct);

    const wk = w(changed);
    const rk = changed.reductionBasePct / 100;

    const others = this.postes.filter(p => p.id !== changedId);
    const D = others.reduce((s, p) => s + w(p) * (this.clampPct(p.reductionBasePct) / 100), 0);

    if (D <= 1e-9) return;

    // facteur pour les autres : sum_others(w_i * (f*r_i)) + w_k*r_k = T
    const f = (T - wk * rk) / D;

    others.forEach(p => {
      p.reductionBasePct = this.clampPct(p.reductionBasePct * f);
    });

    // correction fine si petit écart résiduel
    const currentWeighted =
      this.postes.reduce((s, p) => s + w(p) * (this.clampPct(p.reductionBasePct) / 100), 0);
    const err = T - currentWeighted;

    if (Math.abs(err) > 1e-4) {
      const adjustable = others.filter(p => p.reductionBasePct > 0 && p.reductionBasePct < 100);
      const denom = adjustable.reduce((s, p) => s + w(p), 0);
      if (denom > 0) {
        adjustable.forEach(p => {
          const deltaPctPoints = (err * w(p) / denom) * 100;
          p.reductionBasePct = this.clampPct(p.reductionBasePct + deltaPctPoints);
        });
      }
    }
  }

  // ===== Génération séries & tableau =====
  private rebuildYears(): void {
    if (this.endYear < this.startYear) [this.startYear, this.endYear] = [this.endYear, this.startYear];
    this.startYear = Math.max(this.REF_YEAR, Math.min(this.MAX_YEAR, this.startYear));
    this.endYear   = Math.max(this.REF_YEAR, Math.min(this.MAX_YEAR, this.endYear));
    this.years = Array.from({ length: this.endYear - this.startYear + 1 }, (_, i) => this.startYear + i);
  }

  private computeDrops(values: number[]): { dropBetweenPct: number; annualRequiredPct: number } {
    const first = values[0] ?? 0;
    const last  = values[values.length - 1] ?? 0;
    const span  = Math.max(1, this.years.length - 1);
    const dropBetweenPct = first > 0 ? Math.round(((last - first) / first) * 100) : 0;
    const annualRequiredPct =
      (first > 0 && last >= 0)
        ? Math.round(((Math.pow((last || 0.000001) / first, 1 / span) - 1) * 100) * 100) / 100
        : 0;
    return { dropBetweenPct, annualRequiredPct };
  }

  private generateTableRows(): void {
    const effReductions = this.getEffectiveReductionsByPoste(); // % par poste

    this.tableRows = this.postes.map(p => {
      const targetPct = effReductions.get(p.id) ?? 0;
      const startVal  = p.emissionsReference;
      const endVal    = Math.round(p.emissionsReference * (1 - targetPct / 100));

      // Trajectoire linéaire start -> end sur la période visible
      const n = Math.max(1, this.years.length - 1);
      const values = this.years.map((_, idx) => {
        const t = n > 0 ? idx / n : 0;
        return Math.round(startVal + (endVal - startVal) * t);
      });

      const { dropBetweenPct, annualRequiredPct } = this.computeDrops(values);
      return { label: p.nom, values, dropBetweenPct, annualRequiredPct };
    });
  }

  // ===== Interactions =====
  onReductionInput(evt: Event): void {
    const t = evt.target as HTMLInputElement | null;
    if (t) this.reduction = this.clampPct(Number(t.value));
    this.generateTableRows();
    this.updateChartData(true);
    this.saveSettings();
  }

  onStartYearSelect(val: number | string): void {
    this.startYear = Number(val);
    this.rebuildYears();
    this.generateTableRows();
    this.updateChartData(true);
    this.saveSettings();
  }

  onEndYearSelect(val: number | string): void {
    this.endYear = Number(val);
    this.rebuildYears();
    this.generateTableRows();
    this.updateChartData(true);
    this.saveSettings();
  }

  toggleAdvancedMode(): void {
    this.showAdvanced = !this.showAdvanced;
  }

  /** Quand un slider de poste bouge. Si lockGlobal, on rééquilibre les autres postes. */
  onPosteReductionChange(changedId?: string): void {
    if (this.lockGlobal && changedId) {
      this.rebalanceOthersToKeepGlobal(changedId);
    } else if (changedId) {
      const p = this.postes.find(x => x.id === changedId);
      if (p) p.reductionBasePct = this.clampPct(p.reductionBasePct);
    }
    this.generateTableRows();
    this.updateChartData(true);
    this.saveSettings();
  }

  /** 🔄 Réinitialise tous les postes à leurs valeurs par défaut */
  resetPostes(): void {
    this.postes = this.defaultPostes.map(p => ({ ...p }));
    this.generateTableRows();
    this.updateChartData(true);
    this.saveSettings();
  }

  // ===== Chart.js (robuste) =====
  private async initChartIfPossible() {
    const ChartModule = await import('chart.js/auto');
    this.ChartCtor = ChartModule.default || (ChartModule as any);
  }

  private ensureChart(): void {
    if (!this.ChartCtor || this.chart) return;

    const canvas = document.getElementById('trajectory-chart') as HTMLCanvasElement | null;
    if (!canvas) return;
    const ctx = canvas.getContext('2d'); if (!ctx) return;

    const datasets = this.postes.map(p => ({
      label: p.nom,
      data: [],
      backgroundColor: this.hexToRgba(POSTES_COLORS[p.id] || '#777', 0.85),
      borderColor: POSTES_COLORS[p.id] || '#777',
      borderWidth: 1.5,
      stack: 'emissions'
    }));

    this.chart = new this.ChartCtor(ctx, {
      type: 'bar',
      data: { labels: this.years.map(String), datasets },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: { x: { stacked: true }, y: { stacked: true, beginAtZero: true } },
        plugins: { legend: { display: true, position: 'top' } }
      }
    });
  }

  private hexToRgba(hex: string, alpha = 0.85) {
    const m = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex)!;
    const r = parseInt(m[1], 16), g = parseInt(m[2], 16), b = parseInt(m[3], 16);
    return `rgba(${r}, ${g}, ${b}, ${alpha})`;
  }

  /** Met à jour datasets (et labels si relabel) sans détruire le chart. */
  private updateChartData(relabel = false): void {
    if (!this.chart) return;

    if (relabel) this.chart.data.labels = this.years.map(String);

    const effReductions = this.getEffectiveReductionsByPoste();

    this.chart.data.datasets.forEach((ds: any, di: number) => {
      const p = this.postes[di];
      const targetPct = effReductions.get(p.id) ?? 0;
      const startVal  = p.emissionsReference;
      const endVal    = Math.round(p.emissionsReference * (1 - targetPct / 100));
      const n = Math.max(1, this.years.length - 1);

      ds.data = this.years.map((_, i) => {
        const t = n > 0 ? i / n : 0;
        return Math.round(startVal + (endVal - startVal) * t);
      });
    });

    this.chart.update('none');
  }

  // ===== Export CSV / PDF =====
  exportCSV(): void {
    const header = ['Poste', ...this.years.map(String), `Baisse ${this.startYear}-${this.endYear}`, 'Baisse annuelle'];
    const rows = this.tableRows.map(r => [
      r.label,
      ...r.values.map(v => v.toString()),
      `${r.dropBetweenPct}%`,
      `${r.annualRequiredPct.toFixed(2)}%`
    ]);
    const csv = [header, ...rows].map(r => r.join(',')).join('\n');
    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `trajectoire_${this.startYear}-${this.endYear}.csv`;
    a.click();
    URL.revokeObjectURL(url);
  }

  /** PDF avec tableau + graphique (canvas converti en image) */
  exportPDF(): void {
    const doc = new jsPDF({ orientation: 'landscape', unit: 'pt', format: 'a4' });
    const margin = 40;
    const pageWidth  = doc.internal.pageSize.getWidth();
    const pageHeight = doc.internal.pageSize.getHeight();

    // Titre
    doc.setFontSize(14);
    doc.text(
      `Trajectoire carbone ${this.startYear}-${this.endYear} — Objectif global ${this.reduction}%`,
      margin,
      margin
    );

    // Tableau
    const head = [[
      'Poste',
      ...this.years.map(String),
      `Baisse ${this.startYear}-${this.endYear}`,
      'Baisse annuelle'
    ]];

    const body = this.tableRows.map(r => [
      r.label,
      ...r.values.map(v => v.toString()),
      `${r.dropBetweenPct}%`,
      `${r.annualRequiredPct.toFixed(2)}%`
    ]);

    autoTable(doc, {
      head,
      body,
      startY: margin + 20,
      theme: 'grid',
      styles: { fontSize: 8, cellPadding: 3 },
      headStyles: { fillColor: [225, 239, 254], textColor: [15, 23, 42] },
      margin: { left: margin, right: margin }
    });

    // Graphique (canvas -> image)
    const canvas = document.getElementById('trajectory-chart') as HTMLCanvasElement | null;
    if (canvas) {
      try {
        const imgData = canvas.toDataURL('image/png', 1.0);

        const imgProps = (doc as any).getImageProperties
          ? (doc as any).getImageProperties(imgData)
          : { width: canvas.width, height: canvas.height };

        const maxWidth = pageWidth - margin * 2;
        const ratio = imgProps.height / imgProps.width;
        const imgWidth  = maxWidth;
        const imgHeight = imgWidth * ratio;

        const tableY = (doc as any).lastAutoTable?.finalY ?? (margin + 20);
        let y = tableY + 20;

        if (y + imgHeight + margin > pageHeight) {
          doc.addPage();
          y = margin;
        }

        doc.addImage(imgData, 'PNG', margin, y, imgWidth, imgHeight);
      } catch {
        // si problème de canvas (onglet masqué / CORS), on exporte sans le graphe
      }
    }

    doc.save(`trajectoire_${this.startYear}-${this.endYear}.pdf`);
  }
}
