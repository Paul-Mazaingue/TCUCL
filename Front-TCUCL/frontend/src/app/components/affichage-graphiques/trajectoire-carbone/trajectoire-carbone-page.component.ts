import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
 
// ===== Interfaces =====
interface PosteEmission {
  id: string;
  nom: string;
  emissionsReference: number;
  // Cible "de base" propre au poste (sera recalée pour matcher l'objectif global)
  reductionBasePct: number;
}
interface TableRow {
  label: string;
  values: number[];          // valeurs par année (kgCO2e)
  dropBetweenPct: number;    // baisse entre 1re et dernière année de la période (%, par poste)
  annualRequiredPct: number; // CAGR (%, par poste)
}
 
// ===== Palette Okabe–Ito (colorblind-safe) =====
const POSTES_COLORS: Record<string, string> = {
  'energie': '#E69F00',                // orange
  'autres-deplacements': '#56B4E9',   // sky blue
  'numerique': '#009E73',             // bluish green
  'batiments': '#F0E442',             // yellow
  'mobilite-dom-tram': '#0072B2',     // blue
  'dechets': '#D55E00',               // vermillion
  'achats': '#CC79A7',                // reddish purple
  'autres-immobilisations': '#000000',// black
  'emissions-fugitives': '#999999'    // neutral gray
};
 
// ===== Données & cibles de base par poste =====
const POSTES_ADEME: PosteEmission[] = [
  { id: 'emissions-fugitives', nom: 'Émissions fugitives', emissionsReference: 45,  reductionBasePct: 20 },
  { id: 'energie',             nom: 'Énergie',              emissionsReference: 1200, reductionBasePct: 30 },
  { id: 'mobilite-dom-tram',   nom: 'Déplacements domicile - travail', emissionsReference: 850, reductionBasePct: 40 },
  { id: 'autres-deplacements', nom: 'Déplacements professionnels, stages et autres', emissionsReference: 650, reductionBasePct: 35 },
  { id: 'batiments',           nom: 'Bâtiments, mobilier et parkings', emissionsReference: 500, reductionBasePct: 25 },
  { id: 'numerique',           nom: 'Numérique',            emissionsReference: 280, reductionBasePct: 45 },
  { id: 'autres-immobilisations', nom: 'Autres immobilisations', emissionsReference: 220, reductionBasePct: 20 },
  { id: 'achats',              nom: 'Achats et restauration', emissionsReference: 950, reductionBasePct: 35 },
  { id: 'dechets',             nom: 'Déchets',              emissionsReference: 180, reductionBasePct: 50 },
];
 
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
 
  // Contrôles
  allowedYears: number[] = [];
  startYear = 2019;
  endYear = 2030;
  reduction = 32; // objectif global (%, curseur)
 
  // Données
  postes = POSTES_ADEME.map(p => ({ ...p }));
  years: number[] = [];
  tableRows: TableRow[] = [];
 
  // Chart.js
  private chart: any = null;
  private ChartCtor: any = null;
 
  constructor(private router: Router) {}
 
  // ===== Lifecycle =====
  ngOnInit(): void {
    this.allowedYears = Array.from({ length: this.MAX_YEAR - this.REF_YEAR + 1 }, (_, i) => this.REF_YEAR + i);
    this.updateAll();
  }
  ngAfterViewInit(): void { this.initChartIfPossible(); }
  ngOnDestroy(): void { if (this.chart?.destroy) this.chart.destroy(); }
 
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
 
  // ===== Calcul des réductions effectives par poste =====
  /** Retourne, pour chaque poste, la réduction % effective après recalage sur l'objectif global. */
  private getEffectiveReductionsByPoste(): Map<string, number> {
    // moyenne pondérée des cibles "de base"
    const totalRef = this.postes.reduce((s, p) => s + p.emissionsReference, 0);
    const weightedBase =
      this.postes.reduce((s, p) => s + p.emissionsReference * (p.reductionBasePct / 100), 0) / totalRef; // en fraction
 
    // facteur d'ajustement pour matcher "reduction" (curseur)
    const target = this.reduction / 100; // fraction
    const factor = weightedBase > 0 ? (target / weightedBase) : 1;
 
    // applique le facteur, borne 0..100 %
    const map = new Map<string, number>();
    this.postes.forEach(p => {
      const eff = Math.max(0, Math.min(100, p.reductionBasePct * factor));
      map.set(p.id, eff);
    });
    return map;
  }
 
  // ===== Génération des séries & tableau =====
  private rebuildYears(): void {
    if (this.endYear < this.startYear) [this.startYear, this.endYear] = [this.endYear, this.startYear];
    this.years = Array.from({ length: this.endYear - this.startYear + 1 }, (_, i) => this.startYear + i);
  }
 
  private computeDrops(values: number[]): { dropBetweenPct: number; annualRequiredPct: number } {
    const first = values[0] ?? 0;
    const last  = values[values.length - 1] ?? 0;
    const span  = Math.max(1, this.years.length - 1);
    const dropBetweenPct = first > 0 ? Math.round(((last - first) / first) * 100) : 0;
    const annualRequiredPct = (first > 0 && last >= 0)
      ? Math.round(((Math.pow((last || 0.000001) / first, 1 / span) - 1) * 100) * 100) / 100
      : 0;
    return { dropBetweenPct, annualRequiredPct };
  }
 
  private generateTableRows(): void {
    const effReductions = this.getEffectiveReductionsByPoste(); // % par poste (après recalage)
 
    this.tableRows = this.postes.map(p => {
      const targetPct = effReductions.get(p.id) ?? 0; // %
      const startVal  = p.emissionsReference;
      const endVal    = Math.round(p.emissionsReference * (1 - targetPct / 100));
 
      // interpolation linéaire du start -> end sur la période visible
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
    if (t) this.reduction = Math.max(0, Math.min(100, Number(t.value)));
    this.generateTableRows();
    this.updateChartData(true);
  }
  onStartYearSelect(val: number | string): void {
    this.startYear = Number(val);
    this.updateAll();
  }
  onEndYearSelect(val: number | string): void {
    this.endYear = Number(val);
    this.updateAll();
  }
 
  private updateAll(): void {
    this.rebuildYears();
    this.generateTableRows();
    this.ensureChart();
    this.updateChartData(true);
  }
 
  // ===== Chart.js lifecycle (robuste) =====
  private async initChartIfPossible() {
    try {
      const ChartModule = await import('chart.js/auto');
      this.ChartCtor = ChartModule.default || (ChartModule as any);
      this.ensureChart();
      this.updateChartData(true);
    } catch { /* ignore */ }
  }
 
  private ensureChart(): void {
    if (!this.ChartCtor) return;
    if (this.chart) return;
    const canvas = document.getElementById('trajectory-chart') as HTMLCanvasElement | null;
    if (!canvas) return;
    const ctx = canvas.getContext('2d'); if (!ctx) return;
 
    const datasets = this.postes.map(p => {
      const base = POSTES_COLORS[p.id] || '#777';
      return {
        label: p.nom,
        data: this.years.map((_, idx) => 0), // sera rempli tout de suite après
        backgroundColor: this.hexToRgba(base, 0.85),
        borderColor: base,
        borderWidth: 1.5,
        stack: 'emissions'
      };
    });
 
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
 
  /** Met à jour datasets et (optionnellement) labels sans détruire le chart */
  private updateChartData(relabel = false): void {
    if (!this.chart) return;
    if (relabel) this.chart.data.labels = this.years.map(String);
 
    // recalculer les réductions effectives pour cohérence graphique
    const effReductions = this.getEffectiveReductionsByPoste();
 
    this.chart.data.datasets.forEach((ds: any, di: number) => {
      const p = this.postes[di];
      const base = POSTES_COLORS[p.id] || '#777';
 
      const targetPct = effReductions.get(p.id) ?? 0; // %
      const startVal  = p.emissionsReference;
      const endVal    = Math.round(p.emissionsReference * (1 - targetPct / 100));
      const n = Math.max(1, this.years.length - 1);
 
      ds.data = this.years.map((_, i) => {
        const t = n > 0 ? i / n : 0;
        return Math.round(startVal + (endVal - startVal) * t);
      });
      ds.backgroundColor = this.hexToRgba(base, 0.85);
      ds.borderColor = base;
    });
 
    this.chart.update('none');
  }
 
  // ===== Export CSV =====
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
 
  // ===== Export PDF =====
  exportPDF(): void {
    const doc = new jsPDF({ orientation: 'landscape', unit: 'pt', format: 'a4' });
    doc.setFontSize(14);
    doc.text(`Trajectoire carbone ${this.startYear}-${this.endYear} — Objectif global ${this.reduction}%`, 40, 40);
 
    const head = [['Poste', ...this.years.map(String), `Baisse ${this.startYear}-${this.endYear}`, 'Baisse annuelle']];
    const body = this.tableRows.map(r => [
      r.label,
      ...r.values.map(v => v.toString()),
      `${r.dropBetweenPct}%`,
      `${r.annualRequiredPct.toFixed(2)}%`
    ]);
 
    autoTable(doc, { head, body, startY: 60, theme: 'grid', styles: { fontSize: 8 } });
    doc.save(`trajectoire_${this.startYear}-${this.endYear}.pdf`);
  }
}