import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartData, ChartOptions } from 'chart.js';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BaseChartDirective } from 'ng2-charts';

interface EmissionPost {
  name: string;
  emissions: { [year: string]: number }; // Allow any year
  selected: boolean;
}

@Component({
  selector: 'app-comparaison',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseChartDirective],
  templateUrl: './comparaison.component.html',
  styleUrls: ['./comparaison.component.scss']
})
export class ComparaisonComponent {
  constructor(private router: Router) {}

 // === Available years ===
availableYears: string[] = ['2019','2020','2021','2022','2023','2024','2030'];
year1: string = '2019';
year2: string = '2030';

// === Emission posts with dynamic values for all years ===
emissionPosts: EmissionPost[] = [
  {
    name: 'Énergie',
    emissions: { '2019': 113, '2020': 110, '2021': 108, '2022': 105, '2023': 100, '2024': 80, '2030': 54 },
    selected: true
  },
  {
    name: 'Déplacements domicile - travail',
    emissions: { '2019': 180, '2020': 175, '2021': 170, '2022': 165, '2023': 160, '2024': 155, '2030': 153 },
    selected: true
  },
  {
    name: 'Déplacements professionnels, stages et césures',
    emissions: { '2019': 287, '2020': 280, '2021': 275, '2022': 270, '2023': 265, '2024': 262, '2030': 261 },
    selected: true
  },
  {
    name: 'Bâtiments, mobilier et parkings',
    emissions: { '2019': 109, '2020': 107, '2021': 105, '2022': 102, '2023': 100, '2024': 98, '2030': 97 },
    selected: true
  },
  {
    name: 'Numérique',
    emissions: { '2019': 48, '2020': 47, '2021': 46, '2022': 45, '2023': 44, '2024': 44, '2030': 43 },
    selected: true
  },
  {
    name: 'Autres immobilisations',
    emissions: { '2019': 12, '2020': 12, '2021': 12, '2022': 12, '2023': 12, '2024': 11, '2030': 11 },
    selected: true
  },
  {
    name: 'Achats et restauration',
    emissions: { '2019': 51, '2020': 51, '2021': 51, '2022': 51, '2023': 51, '2024': 51, '2030': 51 },
    selected: true
  },
  {
    name: 'Déchets',
    emissions: { '2019': 47, '2020': 47, '2021': 47, '2022': 47, '2023': 47, '2024': 47, '2030': 47 },
    selected: true
  }
];


  // === Selected posts ===
  get selectedPosts() {
    return this.emissionPosts.filter(p => p.selected);
  }

  // === Calculated posts for table ===
  get calculatedPosts() {
    return this.selectedPosts.map(post => {
      const value1 = post.emissions[this.year1];
      const value2 = post.emissions[this.year2];
      const reduction = ((value2 - value1) / value1) * 100;
      return {
        name: post.name,
        value1,
        value2,
        reduction: reduction.toFixed(1)
      };
    });
  }

  // === Global totals ===
  get totalYear1() {
    return this.selectedPosts.reduce((sum, p) => sum + p.emissions[this.year1], 0);
  }

  get totalYear2() {
    return this.selectedPosts.reduce((sum, p) => sum + p.emissions[this.year2], 0);
  }

  get totalReduction() {
    return (((this.totalYear2 - this.totalYear1) / this.totalYear1) * 100).toFixed(1);
  }

  // === Bar chart ===
  get barChartData(): ChartData<'bar'> {
    return {
      labels: this.selectedPosts.map(e => e.name),
      datasets: [
        {
          label: this.year1,
          data: this.selectedPosts.map(e => e.emissions[this.year1]),
          backgroundColor: '#60a5fa'
        },
        {
          label: this.year2,
          data: this.selectedPosts.map(e => e.emissions[this.year2]),
          backgroundColor: '#10b981'
        }
      ]
    };
  }

  barChartOptions: ChartOptions<'bar'> = {
    indexAxis: 'y',
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: { position: 'bottom' },
      tooltip: {
        callbacks: {
          label: (ctx) => `${ctx.dataset.label}: ${ctx.formattedValue} tCO₂e`
        }
      }
    },
    scales: {
      x: { beginAtZero: true, grid: { color: '#e5e7eb' } },
      y: { ticks: { color: '#374151' } }
    }
  };

  // === Trigger update ===
  updateCharts() {
    this.emissionPosts = [...this.emissionPosts];
  }

  // === Navigate back ===
  goToPilotageScenario() {
    this.router.navigate(['/pilotage-scenario']);
  }
}
