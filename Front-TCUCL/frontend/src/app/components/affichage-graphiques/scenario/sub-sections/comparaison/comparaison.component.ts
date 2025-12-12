// import { Component, Inject, OnInit } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { ChartData, ChartOptions } from 'chart.js';
// import { Router } from '@angular/router';
// import { FormsModule } from '@angular/forms';
// import { BaseChartDirective } from 'ng2-charts';
// import { ScenarioService } from  "../../scenario.service";
// import { categories, EmissionPost } from "../../../../../models/scenario.model"

// interface CalculatedScenario {
//   id: number;
//   name: string;
//   description: string;
//   totalEmissions: number;
//   reductionPercentage: number;
//   posts: EmissionPost[];
//   originalTotalEmission: string;
//   ecartCible: string;
//   reductionEstimee: string;
// }

// interface TimeSeriesPost {
//   name: string;
//   emissions: { [year: string]: number };
//   selected: boolean;
// }

// type ComparisonMode = 'scenarios' | 'posts';

// @Component({
//   selector: 'app-comparison',
//   standalone: true,
//   imports: [CommonModule, FormsModule, BaseChartDirective],
//   templateUrl: './comparaison.component.html',
//   styleUrls: ['./comparaison.component.scss'],
// })
// export class ComparisonComponent implements OnInit {
//   constructor(
//     private router: Router,
//     @Inject(ScenarioService) private scenarioService: ScenarioService
//   ) {}

//   // === Comparison Mode ===
//   comparisonMode: ComparisonMode = 'scenarios';

//   // === Available years for posts comparison ===
//   availableYears: string[] = ['2019', '2020', '2021', '2022', '2023', '2024', '2030'];
//   year1: string = '2019';
//   year2: string = '2030';

//   // === Emission posts with dynamic values for all years ===
//   emissionPosts: TimeSeriesPost[] = [
//     {
//       name: 'Énergie',
//       emissions: { '2019': 113, '2020': 110, '2021': 108, '2022': 105, '2023': 100, '2024': 80, '2030': 54 },
//       selected: true
//     },
//     {
//       name: 'Déplacements domicile - travail',
//       emissions: { '2019': 180, '2020': 175, '2021': 170, '2022': 165, '2023': 160, '2024': 155, '2030': 153 },
//       selected: true
//     },
//     {
//       name: 'Déplacements professionnels, stages et césures',
//       emissions: { '2019': 287, '2020': 280, '2021': 275, '2022': 270, '2023': 265, '2024': 262, '2030': 261 },
//       selected: true
//     },
//     {
//       name: 'Bâtiments, mobilier et parkings',
//       emissions: { '2019': 109, '2020': 107, '2021': 105, '2022': 102, '2023': 100, '2024': 98, '2030': 97 },
//       selected: true
//     },
//     {
//       name: 'Numérique',
//       emissions: { '2019': 48, '2020': 47, '2021': 46, '2022': 45, '2023': 44, '2024': 44, '2030': 43 },
//       selected: true
//     },
//     {
//       name: 'Autres immobilisations',
//       emissions: { '2019': 12, '2020': 12, '2021': 12, '2022': 12, '2023': 12, '2024': 11, '2030': 11 },
//       selected: true
//     },
//     {
//       name: 'Achats et restauration',
//       emissions: { '2019': 51, '2020': 51, '2021': 51, '2022': 51, '2023': 51, '2024': 51, '2030': 51 },
//       selected: true
//     },
//     {
//       name: 'Déchets',
//       emissions: { '2019': 47, '2020': 47, '2021': 47, '2022': 47, '2023': 47, '2024': 47, '2030': 47 },
//       selected: true
//     }
//   ];

//   // === Scenarios data ===
//   category: categories[] = [];
  
//   // === Selected scenarios for comparison ===
//   selectedScenarioIds: number[] = [1, 2];

//   // === Global emissions tracking ===
//   globalEmissions = {
//     baseline: 4875, // Reference scenario emissions
//     target: 2681,   // Target emissions (4875 - 2194)
//     currentSelected: 0
//   };

//   // === Chart data and options ===
//   barChartData!: ChartData<'bar'>;
//   scenarioBarChartData!: ChartData<'bar'>;
//   radarChartData: any;
//   emissionTrendData: any;
//   postsComparisonChartData: any;

//   barChartOptions: ChartOptions<'bar'> = {
//     indexAxis: 'y',
//     responsive: true,
//     maintainAspectRatio: false,
//     plugins: {
//       legend: { position: 'bottom' },
//       tooltip: {
//         callbacks: {
//           label: (ctx) => `${ctx.dataset.label}: ${ctx.formattedValue} tCO₂e`
//         }
//       }
//     },
//     scales: {
//       x: { 
//         beginAtZero: true, 
//         grid: { color: '#e5e7eb' },
//         title: {
//           display: true,
//           text: 'Émissions (tCO₂e)'
//         }
//       },
//       y: { 
//         ticks: { color: '#374151' },
//         title: {
//           display: true,
//           text: 'Postes d\'émission'
//         }
//       }
//     }
//   };

//   scenarioBarChartOptions: ChartOptions<'bar'> = {
//     responsive: true,
//     plugins: {
//       legend: {
//         position: 'top',
//       },
//       tooltip: {
//         callbacks: {
//           label: (context: any) => `${context.dataset.label}: ${context.formattedValue} tCO₂e`
//         }
//       }
//     },
//     scales: {
//       y: {
//         beginAtZero: true,
//         title: {
//           display: true,
//           text: 'Émissions (tCO₂e)'
//         }
//       },
//       x: {
//         title: {
//           display: true,
//           text: 'Scénarios'
//         }
//       }
//     }
//   };

//   ngOnInit() {
//     this.loadScenarios();
//   }

//   // === DATA LOADING ===
//   loadScenarios() {
//     this.scenarioService.getScenarios().subscribe(scenarios => {
//       this.category=  category;
//       this.initializeCharts();
//     });
//   }

//   // === MODE MANAGEMENT ===
//   setComparisonMode(mode: ComparisonMode) {
//     this.comparisonMode = mode;
//     this.updateCharts();
//   }

//   // === POSTS COMPARISON METHODS ===
//   get selectedPosts() {
//     return this.emissionPosts.filter(p => p.selected);
//   }

//   get calculatedPosts() {
//     return this.selectedPosts.map(post => {
//       const value1 = post.emissions[this.year1];
//       const value2 = post.emissions[this.year2];
//       const reduction = ((value2 - value1) / value1) * 100;
//       return {
//         name: post.name,
//         value1,
//         value2,
//         reduction: reduction.toFixed(1)
//       };
//     });
//   }

//   get totalYear1() {
//     return this.selectedPosts.reduce((sum, p) => sum + p.emissions[this.year1], 0);
//   }

//   get totalYear2() {
//     return this.selectedPosts.reduce((sum, p) => sum + p.emissions[this.year2], 0);
//   }

//   get totalReduction() {
//     return (((this.totalYear2 - this.totalYear1) / this.totalYear1) * 100).toFixed(1);
//   }

//   // === SCENARIOS COMPARISON METHODS ===
//   get selectedScenarios(): Scenario[] {
//     return this.scenarios.filter(scenario => 
//       this.selectedScenarioIds.includes(scenario.id)
//     );
//   }

//   calculateTotalEmissions(scenario: Scenario): number {
//     return scenario.posts.reduce((sum, post) => sum + post.currentEmission, 0);
//   }

//   calculateReductionPercentage(scenario: Scenario): number {
//     const totalEmissions = this.calculateTotalEmissions(scenario);
//     return ((totalEmissions - this.globalEmissions.baseline) / this.globalEmissions.baseline) * 100;
//   }

//   get calculatedScenarios(): CalculatedScenario[] {
//     return this.selectedScenarios.map(scenario => ({
//       id: scenario.id,
//       name: scenario.name,
//       description: scenario.description,
//       totalEmissions: this.calculateTotalEmissions(scenario),
//       reductionPercentage: this.calculateReductionPercentage(scenario),
//       posts: scenario.posts,
//       originalTotalEmission: scenario.totalEmission,
//       ecartCible: scenario.ecartCible,
//       reductionEstimee: scenario.reductionEstimee
//     }));
//   }

//   // === SCENARIOS SELECTION METHODS ===
//   toggleScenario(scenarioId: number) {
//     const index = this.selectedScenarioIds.indexOf(scenarioId);
//     if (index > -1) {
//       this.selectedScenarioIds.splice(index, 1);
//     } else {
//       this.selectedScenarioIds.push(scenarioId);
//     }
//     this.updateCharts();
//   }

//   selectAllScenarios() {
//     this.selectedScenarioIds = this.scenarios.map(s => s.id);
//     this.updateCharts();
//   }

//   clearAllSelections() {
//     this.selectedScenarioIds = [];
//     this.updateCharts();
//   }

//   // === CHART MANAGEMENT ===
//   initializeCharts() {
//     this.updateCharts();
//   }

//   updateCharts() {
//     if (this.comparisonMode === 'posts') {
//       this.updatePostsCharts();
//     } else {
//       this.updateScenariosCharts();
//     }
//   }

//   updatePostsCharts() {
//     // Main posts comparison chart
//     this.barChartData = {
//       labels: this.selectedPosts.map(e => e.name),
//       datasets: [
//         {
//           label: this.year1,
//           data: this.selectedPosts.map(e => e.emissions[this.year1]),
//           backgroundColor: '#60a5fa'
//         },
//         {
//           label: this.year2,
//           data: this.selectedPosts.map(e => e.emissions[this.year2]),
//           backgroundColor: '#10b981'
//         }
//       ]
//     };

//     // Posts trend chart
//     this.updatePostsTrendChart();
//   }

//   updatePostsTrendChart() {
//     const years = this.availableYears;
//     this.postsComparisonChartData = {
//       labels: years,
//       datasets: this.selectedPosts.map(post => ({
//         label: post.name,
//         data: years.map(year => post.emissions[year]),
//         borderColor: this.getPostColor(post.name),
//         backgroundColor: this.getPostColor(post.name, 0.1),
//         tension: 0.4,
//         fill: true
//       }))
//     };
//   }

//   updateScenariosCharts() {
//     this.updateGlobalEmissions();
//     this.updateScenarioBarChart();
//     this.updateRadarChart();
//     this.updateEmissionTrendChart();
//   }

//   updateGlobalEmissions() {
//     const selectedTotals = this.calculatedScenarios.map(s => s.totalEmissions);
//     this.globalEmissions.currentSelected = selectedTotals.length > 0 ? 
//       Math.min(...selectedTotals) : 0;
//   }

//   updateScenarioBarChart() {
//     this.scenarioBarChartData = {
//       labels: this.calculatedScenarios.map(s => s.name),
//       datasets: [
//         {
//           label: 'Émissions totales',
//           data: this.calculatedScenarios.map(s => s.totalEmissions),
//           backgroundColor: this.calculatedScenarios.map(s => this.getScenarioColor(s.id, 0.8)),
//           borderColor: this.calculatedScenarios.map(s => this.getScenarioColor(s.id, 1)),
//           borderWidth: 1
//         },
//         {
//           label: 'Cible de réduction',
//           data: this.calculatedScenarios.map(() => this.globalEmissions.target),
//           backgroundColor: '#10b981',
//           borderColor: '#047857',
//           borderWidth: 1
//         }
//       ]
//     };
//   }

//   updateRadarChart() {
//     const allPostNames = this.getPostNames();
    
//     this.radarChartData = {
//       labels: allPostNames,
//       datasets: this.calculatedScenarios.map(scenario => ({
//         label: scenario.name,
//         data: allPostNames.map(postName => {
//           const scenarioPost = scenario.posts.find(p => p.name === postName);
//           return scenarioPost ? scenarioPost.currentEmission : 0;
//         }),
//         backgroundColor: this.getScenarioColor(scenario.id, 0.2),
//         borderColor: this.getScenarioColor(scenario.id, 1),
//         borderWidth: 2,
//         pointBackgroundColor: this.getScenarioColor(scenario.id, 1)
//       }))
//     };
//   }

//   updateEmissionTrendChart() {
//     const scenarios = this.calculatedScenarios.sort((a, b) => a.totalEmissions - b.totalEmissions);
    
//     this.emissionTrendData = {
//       labels: scenarios.map(s => s.name),
//       datasets: [
//         {
//           label: 'Émissions totales',
//           data: scenarios.map(s => s.totalEmissions),
//           backgroundColor: scenarios.map(s => this.getScenarioColor(s.id, 0.8)),
//           borderColor: scenarios.map(s => this.getScenarioColor(s.id, 1)),
//           borderWidth: 1
//         },
//         {
//           label: 'Scénario de référence',
//           data: scenarios.map(() => this.globalEmissions.baseline),
//           borderColor: '#ef4444',
//           borderWidth: 2,
//           borderDash: [5, 5],
//           fill: false,
//           pointRadius: 0
//         },
//         {
//           label: 'Cible de réduction',
//           data: scenarios.map(() => this.globalEmissions.target),
//           borderColor: '#10b981',
//           borderWidth: 2,
//           borderDash: [5, 5],
//           fill: false,
//           pointRadius: 0
//         }
//       ]
//     };
//   }

//   // === HELPER METHODS ===
//   getScenarioColor(scenarioId: number, opacity: number): string {
//     const colors = [
//       'rgba(59, 130, 246, OPACITY)',   // Blue
//       'rgba(16, 185, 129, OPACITY)',   // Green
//       'rgba(245, 158, 11, OPACITY)',   // Yellow
//       'rgba(139, 92, 246, OPACITY)',   // Purple
//       'rgba(239, 68, 68, OPACITY)'     // Red
//     ];
//     return colors[(scenarioId - 1) % colors.length].replace('OPACITY', opacity.toString());
//   }

//   getPostColor(postName: string, opacity: number = 1): string {
//     const colorMap: { [key: string]: string } = {
//       'Énergie': 'rgba(59, 130, 246, OPACITY)',
//       'Déplacements domicile - travail': 'rgba(16, 185, 129, OPACITY)',
//       'Déplacements professionnels, stages et césures': 'rgba(245, 158, 11, OPACITY)',
//       'Bâtiments, mobilier et parkings': 'rgba(139, 92, 246, OPACITY)',
//       'Numérique': 'rgba(239, 68, 68, OPACITY)',
//       'Autres immobilisations': 'rgba(99, 102, 241, OPACITY)',
//       'Achats et restauration': 'rgba(14, 165, 233, OPACITY)',
//       'Déchets': 'rgba(20, 184, 166, OPACITY)'
//     };
//     return (colorMap[postName] || 'rgba(156, 163, 175, OPACITY)').replace('OPACITY', opacity.toString());
//   }

//   get globalReductionPercentage(): number {
//     if (this.globalEmissions.currentSelected === 0) return 0;
//     return ((this.globalEmissions.baseline - this.globalEmissions.currentSelected) / this.globalEmissions.baseline) * 100;
//   }

//   get bestScenario(): CalculatedScenario | null {
//     if (this.calculatedScenarios.length === 0) return null;
//     return this.calculatedScenarios.reduce((best, current) => 
//       current.totalEmissions < best.totalEmissions ? current : best
//     );
//   }

//   getPostNames(): string[] {
//     const allPosts = this.categories.flatMap(s => s.posts.map(p => p.name));
//     return [...new Set(allPosts)];
//   }

//   getPostEmission(scenario: CalculatedScenario, postName: string): number {
//     const post = scenario.posts.find(p => p.name === postName);
//     return post ? post.currentEmission : 0;
//   }

//   // === NAVIGATION ===
//   goToPilotageScenario() {
//     this.router.navigate(['/pilotage-scenario']);
//   }

//   // === UTILITY METHODS ===
//   formatNumber(value: number): string {
//     return value.toLocaleString('fr-FR');
//   }

//   getReductionClass(reduction: string): string {
//     const value = parseFloat(reduction);
//     return value < 0 ? 'positive' : value > 0 ? 'negative' : 'neutral';
//   }
// }