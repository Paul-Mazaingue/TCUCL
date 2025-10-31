import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-outil-suivi',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './outil-suivi-page.component.html',
  styleUrls: ['./outil-suivi-page.component.scss']
})
export class OutilSuiviPageComponent {
  constructor(private router: Router) {}

  // =============================================================
  // Navigation
  // =============================================================
  goHome() {
    this.router.navigate(['/dashboard']);
  }

  // =============================================================
  // Données - Graphique 1 (années, objectif, réalisé)
  // =============================================================
  // TODO BACKEND: Remplacer les tableaux years/objectif/realise par des données API.
  // - Endpoint attendu: GET /emissions/usager?etablissement={id}
  // - Réponse: { years: number[], objectif: number[], realise: (number|null)[] }
  // - Gérer des trous (réalisé manquant) côté front (déjà fait)

  // === Graphique 1: années + objectifs/réalisés (gère données manquantes) ===
  // TODO BACKEND: remplacer par données serveur, avec potentiels trous (années sans info)
  years = [2018, 2019, 2020, 2021, 2022, 2023, 2025, 2027, 2030, 2032, 2035];
  objectif = [800, 780, 740, NaN, 680, 640, 620, NaN, 560, 540, 500];
  realise  = [820, 700, NaN, 720, 710, 590, NaN, 605, 540, NaN, 480];
  diff = [] as string[]; // remplacé par texte sécurisé '-' si données manquantes

  // TODO BACKEND: Remplacer la logique d'établissement par un chargement dynamique depuis le serveur
  etablissements = ['Campus Lyon', 'Campus Paris', 'Campus Lille'];
  selectedEtablissement = this.etablissements[0];

  // =============================================================
  // Survol & Tooltip (graphique 1)
  // =============================================================
  hoverBar: number|null = null;
  // Tooltip HTML overlay (au-dessus de tout, y compris le titre)
  tooltipPageX = 0;
  tooltipPageY = 0;
  onEnterBar(index: number) { this.hoverBar = index; }
  onLeaveBar() { this.hoverBar = null; }
  onMoveBar(event: MouseEvent, index: number) {
    this.hoverBar = index;
    this.tooltipPageX = event.pageX;
    this.tooltipPageY = event.pageY;
  }

  // =============================================================
  // Mise en page commune (graphiques 1 & global)
  // =============================================================
  barWidth = 30;
  barSpacing = 60;
  margin = 60;
  // TODO BACKEND: svgWidth doit s'adapter au nombre d'années retourné par le serveur
  get svgWidth(): number { return this.margin * 2 + (this.years.length - 1) * this.barSpacing; }
  getX(index: number): number { return this.margin + index * this.barSpacing; }

  // =============================================================
  // Échelle Y sécurisée (graphique 1)
  // =============================================================
  // Ignore les valeurs NaN et manquantes pour calculer le max et l'échelle.
  getHeight(value: number): number {
    const numbers: number[] = [...this.objectif, ...this.realise].filter(v => this.isValidNumber(v)) as number[];
    const maximum = numbers.length ? Math.max(...numbers) : 1;
    return value > 0 ? (value / maximum) * 200 : 0;
  }
  getY(value: number): number { return 250 - this.getHeight(value); }

  // Utilitaires simples
  getMin(a: number, b: number): number { return a < b ? a : b; }
  max(a: number, b: number): number { return a > b ? a : b; }
  isValidNumber(n: number | undefined | null): boolean { return typeof n === 'number' && isFinite(n); }

  // =============================================================
  // Helpers - Graphique 1 (gestion des trous, labels, couleurs)
  // =============================================================
  getColor(diff: number): string { if (diff > 0) return '#e74c3c'; if (diff < 0) return '#27ae60'; return '#999'; }
  getHoverCenterX(): number { const i = this.hoverBar ?? 0; return this.getX(i) + this.barWidth / 2; }
  getHoverTopY(): number { const i = this.hoverBar ?? 0; const top = this.yearTop(i); return this.getY(top ?? 0); }
  getTooltipX(): number {
    const cx = this.getHoverCenterX();
    const nearLeft = cx < this.margin + this.barSpacing;
    const nearRight = cx > this.svgWidth - this.margin - this.barSpacing;
    const offset = nearLeft ? this.barSpacing : (nearRight ? -this.barSpacing : 0);
    return cx + offset;
  }
  getTooltipYear(): number { const i = this.hoverBar ?? 0; return this.years[i]; }
  getTooltipRealise(): string | number { const i = this.hoverBar ?? 0; const v = this.getYearRea(i); return v !== null ? v : '-'; }
  getTooltipObjectif(): string | number { const i = this.hoverBar ?? 0; const v = this.getYearObj(i); return v !== null ? v : '-'; }

  // Helpers valeurs/états par année
  getYearObj(i: number): number | null { const v = this.objectif[i]; return this.isValidNumber(v) ? (v as number) : null; }
  getYearRea(i: number): number | null { const v = this.realise[i]; return this.isValidNumber(v) ? (v as number) : null; }
  hasBoth(i: number): boolean { return this.getYearObj(i) !== null && this.getYearRea(i) !== null; }
  greater(i: number): boolean { const o = this.getYearObj(i); const r = this.getYearRea(i); return o !== null && r !== null && r > o; }
  less(i: number): boolean { const o = this.getYearObj(i); const r = this.getYearRea(i); return o !== null && r !== null && r < o; }

  // Base de la barre grise (objectif si réalisé absent; min(obj,rea) sinon)
  baseValue(i: number): number {
    const o = this.getYearObj(i); const r = this.getYearRea(i);
    if (o === null && r === null) return 0;
    if (o !== null && r === null) return o;
    if (o === null && r !== null) return r; // fallback extrême
    return r! < o! ? r! : o!;
  }
  // Position du haut de la pile pour placer le label nombre
  yearTop(i: number): number | null {
    const o = this.getYearObj(i); const r = this.getYearRea(i);
    if (o === null && r === null) return null;
    if (o !== null && r === null) return o;
    if (o === null && r !== null) return r;
    return this.max(o!, r!);
  }
  // Valeur à afficher (réalisé si dispo, sinon objectif)
  yearLabelValue(i: number): number | null { const r = this.getYearRea(i); if (r !== null) return r; const o = this.getYearObj(i); return o !== null ? o : null; }
  // Pourcentage signé
  getYearDiffText(i: number): string {
    const o = this.getYearObj(i); const r = this.getYearRea(i);
    if (o === null || r === null || o === 0) return '-';
    const pct = Math.round(((r - o) / o) * 100);
    if (pct > 0) return `+${pct}%`; if (pct < 0) return `${pct}%`; return '0%';
  }

  // =============================================================
  // Graphique 2 (Objectif vs Réalisé par poste) - Données & Layout
  // =============================================================
  // TODO BACKEND: Alimenter postesObjectifParAn et postesRealiseParAn via API
  // - Endpoint: GET /emissions/postes?annee={year}&etablissement={id}
  // - Réponse: { postes: string[], objectif: number[], realise: number[] }

  // ==========================
  // Graphique 2: comparaison par poste (objectif vs réalisé) pour une année
  // ==========================
  // TODO BACKEND: charger dynamiquement la liste des postes et les valeurs par année depuis l'API
  postes = ['Emissions fugitives','Energie','Déplacements France','Déplacements internationaux','Bâtiments, mobilier et parkings','Numérique','Autres immobilisations','Achats et restauration','Déchets'];
  // TODO BACKEND: remplacer ces données d'exemple par des valeurs par année
  postesObjectifParAn: Record<number, number[]> = {};
  postesRealiseParAn: Record<number, number[]> = {};

  selectedYearForPostes: number = this.years[0];

  // Mise à l'échelle verticale (ancienne version des barres verticales)
  postesBarWidth = 14; // largeur d'une barre individuelle (objectif ou réalisé)
  postesGroupGap = 10; // espace entre les 2 barres d'un même poste (rapproché)
  postesItemSpacing = 90; // espace entre postes (ajusté pour tenir sans scroll)
  postesMargin = 60;
  get postesSvgWidth(): number { return this.postesMargin * 2 + (this.postes.length - 1) * this.postesItemSpacing; }
  getPostX(index: number): number { return this.postesMargin + index * this.postesItemSpacing; }
  getPostesMaxForYear(year: number): number {
    const o = this.postesObjectifParAn[year] ?? []; const r = this.postesRealiseParAn[year] ?? [];
    const merged = [...o, ...r].filter(v => typeof v === 'number' && isFinite(v));
    return merged.length ? Math.max(...merged) : 0;
  }
  getPostHeight(value: number, year: number): number { const maxV = this.getPostesMaxForYear(year) || 1; return value > 0 ? (value / maxV) * 180 : 0; }
  getPostY(value: number, year: number): number { return 280 - this.getPostHeight(value, year); }

  // ===== Graphique 2 (barres horizontales) =====
  // Mise en page: labels à gauche, barres horizontales duo (objectif bleu / réalisé orange)
  postesLeftLabelWidth = 180; // aligné
  postesTopMargin = 24;
  postesBottomMargin = 24;
  postesRowHeight = 46; // aligné
  postesBarHeight = 12;
  postesBarsGap = 6; // aligné
  postesRightMargin = 90; // aligné
  get postesHChartWidth(): number { return Math.min(920, this.postesLeftLabelWidth + 660 + this.postesRightMargin); }
  get postesHChartHeight(): number { return this.postesTopMargin + this.postesBottomMargin + this.postes.length * this.postesRowHeight; }
  get postesUsableWidth(): number { return this.postesHChartWidth - this.postesLeftLabelWidth - this.postesRightMargin; }
  getPostBarX(): number { return this.postesLeftLabelWidth; }
  getPostBarWidth(value: number, year: number): number {
    const maxV = this.getPostesMaxForYear(year) || 1;
    const raw = value > 0 ? (value / maxV) * this.postesUsableWidth : 0;
    return Math.max(0, Math.min(this.postesUsableWidth, raw));
  }
  getPostGroupY(index: number): number { return this.postesTopMargin + index * this.postesRowHeight + this.postesRowHeight / 2; }
  getPostObjBarY(index: number): number { return this.getPostGroupY(index) - (this.postesBarsGap / 2) - this.postesBarHeight; }
  getPostReaBarY(index: number): number { return this.getPostGroupY(index) + (this.postesBarsGap / 2); }
  getPostDiffColor(obj: number, rea: number): string { if (!obj || !isFinite(obj) || !isFinite(rea)) return '#999'; return rea - obj > 0 ? '#e74c3c' : (rea - obj < 0 ? '#27ae60' : '#999'); }
  getPostDiffPct(obj: number, rea: number): string {
    if (!obj || !isFinite(obj) || !isFinite(rea)) return '-';
    const pct = Math.round(((rea - obj) / obj) * 100);
    if (pct > 0) return `+${pct}%`;
    if (pct < 0) return `${pct}%`;
    return '0%';
  }
  getBarEndX(value: number, year: number): number { return this.getPostBarX() + this.getPostBarWidth(value, year); }
  getValueLabelX(value: number, year: number): number { const end = this.getBarEndX(value, year) + 6; return Math.min(end, this.postesHChartWidth - 8); }

  // =============================================================
  // Graphique 3 (Réalisé A vs B par poste) - Layout & helpers
  // =============================================================
  // TODO BACKEND: Réutiliser les mêmes postes et valeurs réalisées pour différentes années (déjà prévu via postesRealiseParAn)
  compareYearA: number = this.years[0];
  compareYearB: number = this.years[this.years.length - 1];
  compLeftLabelWidth = 180; compTopMargin = 24; compBottomMargin = 24; compRowHeight = 46; compBarHeight = 12; compBarsGap = 6; compRightMargin = 90;
  get compChartWidth(): number { return Math.min(920, this.compLeftLabelWidth + 660 + this.compRightMargin); }
  get compChartHeight(): number { return this.compTopMargin + this.compBottomMargin + this.postes.length * this.compRowHeight; }
  get compUsableWidth(): number { return this.compChartWidth - this.compLeftLabelWidth - this.compRightMargin; }
  compBarX(): number { return this.compLeftLabelWidth; }
  compGroupY(i: number): number { return this.compTopMargin + i * this.compRowHeight + this.compRowHeight / 2; }
  compBarAY(i: number): number { return this.compGroupY(i) - (this.compBarsGap / 2) - this.compBarHeight; }
  compBarBY(i: number): number { return this.compGroupY(i) + (this.compBarsGap / 2); }
  getCompMax(a: number, b: number): number {
    const ra = this.postesRealiseParAn[a] ?? []; const rb = this.postesRealiseParAn[b] ?? [];
    const merged = [...ra, ...rb].filter(v => typeof v === 'number' && isFinite(v));
    return merged.length ? Math.max(...merged) : 1;
  }
  compWidthFor(value: number, a: number, b: number): number { const maxV = this.getCompMax(a, b) || 1; const w = value > 0 ? (value / maxV) * this.compUsableWidth : 0; return Math.max(0, Math.min(this.compUsableWidth, w)); }
  compValueX(value: number, a: number, b: number): number { return Math.min(this.compBarX() + this.compWidthFor(value, a, b) + 6, this.compChartWidth - 8); }
  compDiffPctText(yearA: number, yearB: number, index: number): string {
    const ra = this.postesRealiseParAn[yearA]?.[index]; const rb = this.postesRealiseParAn[yearB]?.[index];
    if (!isFinite(ra as number) || !isFinite(rb as number) || !ra) return '-';
    const pct = Math.round((((rb as number) - (ra as number)) / (ra as number)) * 100);
    if (pct > 0) return `+${pct}%`; if (pct < 0) return `${pct}%`; return '0%';
  }
  compDiffColor(yearA: number, yearB: number, index: number): string {
    const ra = this.postesRealiseParAn[yearA]?.[index]; const rb = this.postesRealiseParAn[yearB]?.[index];
    if (!isFinite(ra as number) || !isFinite(rb as number) || !ra) return '#999';
    const diff = (rb as number) - (ra as number);
    return diff > 0 ? '#e74c3c' : (diff < 0 ? '#27ae60' : '#999');
  }

  // =============================================================
  // Graphique global (tonnes CO2) - Données, échelle & tooltip
  // =============================================================
  // TODO BACKEND: Remplacer globalTotals par une série API de totaux annuels en tCO2
  // - Endpoint: GET /emissions/globales?etablissement={id}
  // - Réponse: { years: number[], totals: number[] }
  // - La ligne de référence se place sur la première année retournée

  // Réduction hauteur premier graphique (visuel plus compact en haut à gauche)
  firstChartHeight = 300;
  // ===== Graphique global (tonnes CO2) =====
  // TODO BACKEND: remplacer par les totaux globaux (tonnes) par année
  globalTotals = [12.0, 11.5, 10.9, 14, 10.3, 9.7, 9.2, 9.8, 17, 7.9, 15];
  get globalRef(): number { return this.globalTotals[0] ?? 0; }
  isValidGlobal(n: number): boolean { return typeof n === 'number' && isFinite(n) && n >= 0; }
  getGlobalNumbers(): number[] { return this.globalTotals.filter(v => this.isValidGlobal(v)); }
  get globalMax(): number { const list = this.getGlobalNumbers(); return list.length ? Math.max(...list, this.globalRef) : this.globalRef || 1; }
  getGlobalHeight(value: number): number { return value > 0 ? (value / this.globalMax) * 200 : 0; }
  getGlobalY(value: number): number { return 250 - this.getGlobalHeight(value); }
  getGlobalDiffPct(index: number): string {
    const v = this.globalTotals[index]; const ref = this.globalRef;
    if (!this.isValidGlobal(v) || !this.isValidGlobal(ref) || ref === 0) return '-';
    const pct = Math.round(((v - ref) / ref) * 100);
    if (pct > 0) return `+${pct}%`; if (pct < 0) return `${pct}%`; return '0%';
  }
  getGlobalDiffColor(index: number): string {
    const v = this.globalTotals[index]; const ref = this.globalRef;
    if (!this.isValidGlobal(v) || !this.isValidGlobal(ref) || ref === 0) return '#999';
    return v - ref > 0 ? '#e74c3c' : (v - ref < 0 ? '#27ae60' : '#999');
  }

  // Survol/tooltip pour graphique global (tCO2)
  hoverGlobalIndex: number|null = null;
  onEnterGlobal(index: number) { this.hoverGlobalIndex = index; }
  onLeaveGlobal() { this.hoverGlobalIndex = null; }
  onMoveGlobal(event: MouseEvent, index: number) { this.hoverGlobalIndex = index; this.tooltipPageX = event.pageX; this.tooltipPageY = event.pageY; }
  getTooltipYearGlobal(): number { const i = this.hoverGlobalIndex ?? 0; return this.years[i]; }
  getTooltipGlobalTotal(): string | number { const i = this.hoverGlobalIndex ?? 0; const v = this.globalTotals[i]; return this.isValidGlobal(v) ? v : '-'; }
  getTooltipGlobalDiff(): string {  const i = this.hoverGlobalIndex ?? 0; return this.getGlobalDiffPct(i); }

  // =============================================================
  // Initialisation (mock de scénarios pour graphiques 2/3)
  // =============================================================
  // TODO BACKEND: Remplacer par un chargement initial (resolver ou ngOnInit avec service HTTP)
  ngOnInit(): void {
    // Graphique 2 scénarios (démo): profils contrastés par année
    const scenarios: Record<number, { obj: number[]; rea: number[] }> = {
      2018: { obj: [5, 140, 220, 260, 70, 30, 10, 8, 40], rea: [6, 180, 260, 320, 95, 42, 12, 12, 55] },
      2020: { obj: [8, 90, 120, 110, 160, 35, 15, 9, 120], rea: [7, 85, 100, 95, 200, 50, 18, 11, 140] },
      2022: { obj: [0, 80, 150, 180, 80, 60, 0, 6, 70],  rea: [0, 70, 140, 210, 85, 120, 0, 9, 90] },
      2025: { obj: [3, 60, 90, 120, 60, 30, 10, 50, 40],  rea: [4, 55, 85, 130, 65, 38, 12, 90, 42] },
      2030: { obj: [2, 70, 130, 0, 70, 25, 8, 7, 5],     rea: [3, 65, 110, 0, 60, 22, 6, 10, 3] },
    };

    for (const y of this.years) {
      const s = scenarios[y];
      const obj = s?.obj ?? [12.0, 95.0, 181.2, 234.4, 89.0, 41.0, 22.0, 5.5, 76.9];
      const rea = s?.rea ?? [ 9.5, 113.2, 178.5, 315.2,108.7, 47.6, 18.0,11.8, 50.5];
      this.postesObjectifParAn[y] = obj;
      this.postesRealiseParAn[y]  = rea;
    }

    // Diff sécurisée (texte) pour Graphique 1
    this.diff = this.years.map((_, i) => this.getYearDiffText(i));
  }

  // =============================================================
  // Actions de carte: menu download (PDF/CSV)
  // =============================================================
  cardMenuOpen: { [key: string]: boolean } = { g1: false, g2: false, g3: false, g4: false };
  toggleMenu(key: 'g1' | 'g2' | 'g3' | 'g4') { this.cardMenuOpen[key] = !this.cardMenuOpen[key]; }
  closeMenus() { this.cardMenuOpen = { g1: false, g2: false, g3: false, g4: false }; }

  private downloadBlob(content: Blob, filename: string) {
    const url = URL.createObjectURL(content);
    const a = document.createElement('a');
    a.href = url; a.download = filename; a.click();
    URL.revokeObjectURL(url);
  }
  private loadJsPdf(): Promise<any> {
    return new Promise((resolve, reject) => {
      const w = window as any;
      if (w.jspdf?.jsPDF) return resolve(w.jspdf.jsPDF);
      const script = document.createElement('script');
      script.src = 'https://cdn.jsdelivr.net/npm/jspdf@2.5.1/dist/jspdf.umd.min.js';
      script.async = true;
      script.onload = () => resolve((window as any).jspdf.jsPDF);
      script.onerror = reject;
      document.body.appendChild(script);
    });
  }
  private loadHtml2Canvas(): Promise<any> {
    return new Promise((resolve, reject) => {
      const w = window as any;
      if (w.html2canvas) return resolve(w.html2canvas);
      const script = document.createElement('script');
      script.src = 'https://cdn.jsdelivr.net/npm/html2canvas@1.4.1/dist/html2canvas.min.js';
      script.async = true;
      script.onload = () => resolve((window as any).html2canvas);
      script.onerror = reject;
      document.body.appendChild(script);
    });
  }
  private async elementToPngDataUrl(el: Element): Promise<string> {
    const html2canvas = await this.loadHtml2Canvas();
    const node = (el as HTMLElement).closest('.card') || (el as HTMLElement);
    const canvas: HTMLCanvasElement = await html2canvas(node, {
      backgroundColor: '#ffffff',
      scale: 2,
      useCORS: true,
      allowTaint: true,
      logging: false,
      windowWidth: document.documentElement.scrollWidth,
      ignoreElements: (element: Element) => {
        const cls = (element as HTMLElement).classList;
        return !!(cls && (cls.contains('card-actions') || cls.contains('icon-btn') || cls.contains('dropdown')));
      }
    });
    return canvas.toDataURL('image/png');
  }
  async downloadPdfFor(key: 'g1' | 'g2' | 'g3' | 'g4', svgRef: Element | null) {
    if (!svgRef) return;
    try {
      const dataUrl = await this.elementToPngDataUrl(svgRef);
      const jsPDFCtor = await this.loadJsPdf();
      const doc = new jsPDFCtor({ orientation: 'landscape', unit: 'pt', format: 'a4' });
      const pageW = doc.internal.pageSize.getWidth();
      const pageH = doc.internal.pageSize.getHeight();
      const margin = 24;
      const maxW = pageW - margin * 2;
      const maxH = pageH - margin * 2;
      const img = new Image();
      const dims: { w: number; h: number } = await new Promise((res) => {
        img.onload = () => res({ w: img.width, h: img.height });
        img.src = dataUrl;
      });
      const ratio = Math.min(maxW / dims.w, maxH / dims.h);
      const drawW = Math.max(1, Math.floor(dims.w * ratio));
      const drawH = Math.max(1, Math.floor(dims.h * ratio));
      const x = (pageW - drawW) / 2;
      const y = (pageH - drawH) / 2;
      doc.addImage(dataUrl, 'PNG', x, y, drawW, drawH);
      doc.save(`export_${key}.pdf`);
    } catch (e) {
      console.error('Export PDF échoué', e);
    } finally {
      this.closeMenus();
    }
  }
  downloadCsvFor(key: 'g1' | 'g2' | 'g3' | 'g4') {
    let csv = '';
    if (key === 'g1') {
      csv += 'Annee;Objectif;Realise;Diff%\n';
      this.years.forEach((y, i) => {
        const o = this.getYearObj(i);
        const r = this.getYearRea(i);
        const d = this.getYearDiffText(i);
        csv += `${y};${o ?? ''};${r ?? ''};${d}\n`;
      });
    } else if (key === 'g2') {
      csv += 'Annee;Total_tCO2;Diff_vs_ref%\n';
      this.years.forEach((y, i) => {
        const t = this.globalTotals[i] ?? '';
        const d = this.getGlobalDiffPct(i);
        csv += `${y};${t};${d}\n`;
      });
    } else if (key === 'g3') {
      csv += `Poste;Objectif_${this.selectedYearForPostes};Realise_${this.selectedYearForPostes};Diff%\n`;
      const o = this.postesObjectifParAn[this.selectedYearForPostes] ?? [];
      const r = this.postesRealiseParAn[this.selectedYearForPostes] ?? [];
      this.postes.forEach((p, i) => {
        const oi = o[i] ?? '';
        const ri = r[i] ?? '';
        const d = this.getPostDiffPct(typeof oi === 'number' ? oi : NaN, typeof ri === 'number' ? ri : NaN);
        csv += `${p};${oi};${ri};${d}\n`;
      });
    } else if (key === 'g4') {
      csv += `Poste;Realise_${this.compareYearA};Realise_${this.compareYearB};Diff_vs_A%\n`;
      const a = this.postesRealiseParAn[this.compareYearA] ?? [];
      const b = this.postesRealiseParAn[this.compareYearB] ?? [];
      this.postes.forEach((p, i) => {
        const ai = a[i] ?? '';
        const bi = b[i] ?? '';
        const d = this.compDiffPctText(this.compareYearA, this.compareYearB, i);
        csv += `${p};${ai};${bi};${d}\n`;
      });
    }
    this.downloadBlob(new Blob([csv], { type: 'text/csv;charset=utf-8;' }), `export_${key}.csv`);
    this.closeMenus();
  }
}
