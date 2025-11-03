import { Component, HostListener } from '@angular/core';
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
  
  // Couleurs pour les postes dans le menu déroulant
  private POSTES_COLORS: Record<string, string> = {
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
  
  // Retourne la couleur d'un poste basée sur son nom
  getPosteColor(posteName: string): string {
    // Normalisation du nom pour correspondre aux clés du mapping
    const normalized = posteName.toLowerCase()
      .replace(/é|è|ê/g, 'e')
      .replace(/à|â/g, 'a')
      .replace(/ç/g, 'c')
      .replace(/[^a-z0-9]/g, '-')
      .replace(/-+/g, '-')
      .replace(/^-|-$/g, '');
    
    // Mapping des noms affichés vers les clés
    const mapping: Record<string, string> = {
      'emissions-fugitives': 'emissions-fugitives',
      'energie': 'energie',
      'deplacements-france': 'mobilite-dom-tram',
      'deplacements-internationaux': 'autres-deplacements',
      'batiments-mobilier-et-parkings': 'batiments',
      'numerique': 'numerique',
      'autres-immobilisations': 'autres-immobilisations',
      'achats-et-restauration': 'achats',
      'dechets': 'dechets'
    };
    
    const key = mapping[normalized] || normalized;
    return this.POSTES_COLORS[key] || '#ccc';
  }
  // TODO BACKEND: remplacer ces données d'exemple par des valeurs par année
  postesObjectifParAn: Record<number, number[]> = {};
  postesRealiseParAn: Record<number, number[]> = {};

  selectedYearForPostes: number = this.years[0];

  // Mise à l'échelle verticale
  postesBarWidth = 14; // largeur d'une barre individuelle
  postesGroupGap = 10; // espace entre les 2 barres d'un même poste
  postesItemSpacing = 90; // espace entre postes
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
  postesLeftLabelWidth = 180;
  postesTopMargin = 24;
  postesBottomMargin = 24;
  postesRowHeight = 46;
  postesBarHeight = 12;
  postesBarsGap = 6;
  postesRightMargin = 90;
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
  // Graphique 4 (Comparaison Réalisés multiples années par poste) - Layout & helpers
  // =============================================================
  // TODO BACKEND: Réutiliser les mêmes postes et valeurs réalisées pour différentes années (déjà prévu via postesRealiseParAn)
  compareYears: number[] = [this.years[0], this.years[this.years.length - 1]]; // Par défaut première et dernière
  // Mode de calcul des pourcentages: vs moyenne des années sélectionnées ou vs première année
  comp4PercentMode: 'vs_mean' | 'vs_first' = 'vs_mean';
  // Sélection multi-postes (afficher 1, 2, tous, etc.)
  comp4PostesMenuOpen = false;
  selectedPosteIndexes: number[] = []; // vide => tous, sinon liste des index sélectionnés
  toggleComp4PostesMenu() { this.comp4PostesMenuOpen = !this.comp4PostesMenuOpen; }
  closeComp4PostesMenu() { this.comp4PostesMenuOpen = false; }
  isPosteSelected(index: number): boolean {
    return this.selectedPosteIndexes.length === 0 || this.selectedPosteIndexes.includes(index);
  }
  togglePoste(index: number) {
    const pos = this.selectedPosteIndexes.indexOf(index);
    if (pos >= 0) {
      this.selectedPosteIndexes.splice(pos, 1);
    } else {
      this.selectedPosteIndexes.push(index);
      this.selectedPosteIndexes.sort((a, b) => a - b);
    }
  }
  selectAllPostes() {
    this.selectedPosteIndexes = [];
  }
  get comp4VisiblePosteIndexes(): number[] {
    if (this.selectedPosteIndexes.length === 0) return this.postes.map((_, i) => i);
    return this.selectedPosteIndexes;
  }
  get comp4VisiblePostesCount(): number {
    return this.comp4VisiblePosteIndexes.length;
  }
  compareMenuOpen = false;
  toggleCompareMenu() { this.compareMenuOpen = !this.compareMenuOpen; }
  closeCompareMenu() { this.compareMenuOpen = false; }
  // Fermer les menus si clic en dehors (écoute sur tout le document)
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
    // Ne pas fermer si on clique dans les menus ou sur les boutons
    if (target.closest('.year-select-dropdown') || target.closest('.year-select-btn') ||
        target.closest('.dropdown') || target.closest('.icon-btn') || target.closest('.card-actions')) {
      return;
    }
    // Fermer les menus de sélection
    if (this.compareMenuOpen) this.closeCompareMenu();
    if (this.comp4PostesMenuOpen) this.closeComp4PostesMenu();
    // Fermer les menus de téléchargement
    this.closeMenus();
  }
  toggleCompareYear(year: number) {
    const idx = this.compareYears.indexOf(year);
    if (idx >= 0) {
      this.compareYears.splice(idx, 1);
    } else {
      this.compareYears.push(year);
      this.compareYears.sort((a, b) => a - b);
    }
  }
  isYearSelected(year: number): boolean { return this.compareYears.includes(year); }
  // Palette de couleurs pour différencier les années
  private colorPalette = ['#329dd5', '#f39c12', '#8e44ad', '#16a085', '#e74c3c', '#3498db', '#f1c40f', '#9b59b6', '#1abc9c', '#e67e22'];
  getYearColor(year: number): string {
    const idx = this.compareYears.indexOf(year);
    return idx >= 0 ? this.colorPalette[idx % this.colorPalette.length] : '#999';
  }

  // Graphique 4: barres groupées empilées verticalement
  comp4LeftLabelWidth = 200;
  comp4TopMargin = 30;
  comp4BottomMargin = 30;
  comp4BarHeight = 16; // Hauteur d'une barre individuelle
  comp4YearGap = 8; // Espace entre les années dans un même poste
  comp4PosteGap = 12; // Espace entre les postes
  comp4RightMargin = 80;
  
  get comp4ChartWidth(): number { return Math.min(1000, this.comp4LeftLabelWidth + 700 + this.comp4RightMargin); }
  get comp4UsableWidth(): number { return this.comp4ChartWidth - this.comp4LeftLabelWidth - this.comp4RightMargin; }
  get comp4BarStartX(): number { return this.comp4LeftLabelWidth; }
  
  // Hauteur d'un poste = (nombre années * (hauteur barre + gap)) - gap final
  get comp4PosteHeight(): number {
    if (this.compareYears.length === 0) return 20;
    return this.compareYears.length * (this.comp4BarHeight + this.comp4YearGap) - this.comp4YearGap;
  }
  
  get comp4ChartHeight(): number {
    const visibleCount = this.comp4VisiblePosteIndexes.length;
    return this.comp4TopMargin + this.comp4BottomMargin + 
           (visibleCount * (this.comp4PosteHeight + this.comp4PosteGap)) - this.comp4PosteGap;
  }
  
  // Y de départ d'un poste (utilise l'index visible, pas l'index original)
  comp4PosteY(visibleIndex: number): number {
    return this.comp4TopMargin + visibleIndex * (this.comp4PosteHeight + this.comp4PosteGap);
  }
  
  // Y d'une barre spécifique dans un poste
  comp4BarY(visibleIndex: number, yearIndex: number): number {
    return this.comp4PosteY(visibleIndex) + yearIndex * (this.comp4BarHeight + this.comp4YearGap);
  }
  
  // Largeur max sur toutes les années sélectionnées
  getComp4Max(): number {
    const all: number[] = [];
    this.compareYears.forEach(y => {
      const vals = this.postesRealiseParAn[y] ?? [];
      vals.forEach(v => { if (typeof v === 'number' && isFinite(v) && v > 0) all.push(v); });
    });
    return all.length ? Math.max(...all) : 1;
  }
  
  comp4BarWidth(value: number): number {
    const maxV = this.getComp4Max();
    if (!maxV || value <= 0) return 0;
    const w = (value / maxV) * this.comp4UsableWidth;
    return Math.min(w, this.comp4UsableWidth);
  }
  
  comp4ValueX(value: number): number {
    const end = this.comp4BarStartX + this.comp4BarWidth(value) + 8;
    return Math.min(end, this.comp4ChartWidth - 10);
  }
  
  // Moyenne des valeurs réalisées sur les années sélectionnées (poste donné)
  private comp4Mean(posteIndex: number): number | null {
    const values: number[] = this.compareYears
      .map(y => this.postesRealiseParAn[y]?.[posteIndex])
      .filter(v => typeof v === 'number' && isFinite(v as number)) as number[];
    if (!values.length) return null;
    return values.reduce((a, b) => a + b, 0) / values.length;
  }
  // Pourcentage selon le mode sélectionné
  comp4DiffPct(year: number, posteIndex: number): string {
    const current = this.postesRealiseParAn[year]?.[posteIndex];
    if (!isFinite(current as number)) return '-';
    let ref: number | null = null;
    if (this.comp4PercentMode === 'vs_first') {
      if (this.compareYears.length < 2 || this.compareYears[0] === year) return '-';
      ref = this.postesRealiseParAn[this.compareYears[0]]?.[posteIndex] as number;
    } else {
      // vs_mean: moyenne des années sélectionnées (incluant l'année courante)
      ref = this.comp4Mean(posteIndex);
    }
    if (!isFinite(ref as number) || !ref || ref === 0) return '-';
    const pct = Math.round((((current as number) - (ref as number)) / (ref as number)) * 100);
    if (pct > 0) return `+${pct}%`; if (pct < 0) return `${pct}%`; return '0%';
  }
  
  comp4DiffColor(year: number, posteIndex: number): string {
    const val = this.comp4DiffPct(year, posteIndex);
    if (val === '-' || val === '0%') return '#999';
    return val.startsWith('+') ? '#e74c3c' : '#27ae60';
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
  globalTotals = [12.0, 11.5, 10.9, 11.8, 10.3, 9.7, 9.2, 9.8, 8.4, 7.9, 7.2];
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
  toggleMenu(key: 'g1' | 'g2' | 'g3' | 'g4') {
    this.cardMenuOpen[key] = !this.cardMenuOpen[key];
    // Fermer les menus de sélection (années et postes) quand on ouvre le menu télécharger
    if (this.cardMenuOpen[key]) {
      if (this.compareMenuOpen) this.closeCompareMenu();
      if (this.comp4PostesMenuOpen) this.closeComp4PostesMenu();
    }
  }
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
      csv += `Poste;${this.compareYears.map(y => `Realise_${y}`).join(';')};Diff_vs_premiere%\n`;
      this.postes.forEach((p, i) => {
        const vals = this.compareYears.map(y => (this.postesRealiseParAn[y]?.[i] ?? ''));
        const d = this.compareYears.length > 1 ? this.comp4DiffPct(this.compareYears[this.compareYears.length - 1], i) : '-';
        csv += `${p};${vals.join(';')};${d}\n`;
      });
    }
    this.downloadBlob(new Blob([csv], { type: 'text/csv;charset=utf-8;' }), `export_${key}.csv`);
    this.closeMenus();
  }
}
