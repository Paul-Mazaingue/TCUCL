import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChartOptions, ChartData } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-posts',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseChartDirective],
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent {
  // === Base emission data ===
  emissionCategories = [
    { name: 'Énergie et eau', color: '#2ecc71', value: 35, currentEmission: 420, reduction: 780 },
    { name: 'Émissions fugitives', color: '#27ae60', value: 15, currentEmission: 7, reduction: 38 },
    { name: 'Mobilité domicile-travail', color: '#f39c12', value: 100, currentEmission: 850, reduction: 0 },
    { name: 'Déplacements professionnels', color: '#e67e22', value: 100, currentEmission: 650, reduction: 0 },
    { name: 'Bâtiments et mobilier', color: '#f39c12', value: 100, currentEmission: 500, reduction: 0 },
    { name: 'Numérique', color: '#9b59b6', value: 100, currentEmission: 280, reduction: 0 },
    { name: 'Autres immobilisations', color: '#8e44ad', value: 100, currentEmission: 220, reduction: 0 },
    { name: 'Achats et restauration', color: '#3498db', value: 100, currentEmission: 950, reduction: 0 },
    { name: 'Déchets', color: '#16a085', value: 100, currentEmission: 180, reduction: 0 }
  ];

  // store initial emissions
  private baseEmissions = this.emissionCategories.map(e => e.currentEmission);

  categories = this.emissionCategories.map(e => e.name);

  // === BAR CHART ===
  barChartData: ChartData<'bar'> = {
    labels: this.categories,
    datasets: [
      {
        label: 'Émissions (tCO₂e)',
        data: this.emissionCategories.map(e => e.currentEmission),
        backgroundColor: '#a8e063',
        borderColor: '#7ac142',
        borderWidth: 1
      }
    ]
  };

  barChartOptions: ChartOptions<'bar'> = {
    indexAxis: 'y',
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: { display: false },
      tooltip: { enabled: true }
    },
    scales: {
      x: {
        grid: { color: '#e5e7eb' },
        ticks: { color: '#555' }
      },
      y: {
        grid: { display: false },
        ticks: { color: '#333' }
      }
    }
  };

  // === RADAR CHART ===
  radarChartData: ChartData<'radar'> = {
    labels: this.categories,
    datasets: [
      {
        label: 'Ajustement (%)',
        data: this.emissionCategories.map(e => e.value),
        backgroundColor: 'rgba(76, 29, 149, 0.3)', // purple background
        borderColor: '#4c1d95',
        borderWidth: 2,
        pointBackgroundColor: '#4c1d95'
      }
    ]
  };

  radarChartOptions: ChartOptions<'radar'> = {
    responsive: true,
    plugins: {
      legend: { display: false },
      tooltip: {
        enabled: true,
        callbacks: {
          label: (context) => {
            const label = context.label || '';
            const value = context.formattedValue || '';
            return `${label}: ${value}%`;
          }
        }
      }
    },
    scales: {
      r: {
        suggestedMin: 0,
        suggestedMax: 150,
        angleLines: { color: '#e5e7eb' },
        grid: { color: '#e5e7eb' },
        pointLabels: { color: '#555', font: { size: 10 } },
        ticks: {
          stepSize: 50,
          color: '#777',
          backdropColor: 'transparent'
        }
      }
    }
  };

  // === UPDATE LOGIC ===
  updateEmission(item: any) {
    const index = this.emissionCategories.indexOf(item);
    const baseValue = this.baseEmissions[index];

    // compute new emission
    item.currentEmission = Math.round(baseValue * (item.value / 100));

    // difference vs base value (reduction if < 0, increase if > 0)
    item.reduction = Math.round(item.currentEmission - baseValue);

    this.updateCharts();
  }

  updateCharts() {
    this.barChartData.datasets[0].data = this.emissionCategories.map(e => e.currentEmission);
    this.radarChartData.datasets[0].data = this.emissionCategories.map(e => e.value);

    // reassign to trigger Angular change detection
    this.barChartData = { ...this.barChartData };
    this.radarChartData = { ...this.radarChartData };
  }

  getAbsValue(value: number): number {
    return Math.abs(value);
  }
}
