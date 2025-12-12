import { Component, Input, OnChanges, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChartData, ChartOptions } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { EmissionPost ,categories} from '../../../../../models/scenario.model';

@Component({
  selector: 'app-posts',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseChartDirective],
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnChanges {
  @Input() posts: EmissionPost[] = [];
  @Input() scenarioName: string = ''; // Add this input
  @Output() totalEmissionsChange = new EventEmitter<number>();
  @Output() totalReductionChange = new EventEmitter<number>();
  @Output() reductionRateChange = new EventEmitter<number>();

  activePost: EmissionPost | null = null;

  private baseEmissions: Record<string, number> = {};

  // Totals
  totalBaseEmissions: number = 0;
  totalCurrentEmissions: number = 0;
  totalReduction: number = 0;
  reductionRate: number = 0;

  barChartData!: ChartData<'bar'>;
  radarChartData!: ChartData<'radar'>;

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

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['posts'] && this.posts?.length > 0) {
      this.baseEmissions = this.posts.reduce((acc, p) => {
        acc[p.name] = p.currentEmission;
        return acc;
      }, {} as Record<string, number>);

      this.activePost = this.posts[0];

      const first = this.activePost;
      const base = this.baseEmissions[first.name];
      const newEmission = (base * first.value) / 100;
      first.currentEmission = +newEmission.toFixed(1);
      first.reduction = +(newEmission - base).toFixed(1);

      this.calculateTotals();
      this.updateCharts();
    }
  }

  applyPost(post: EmissionPost): void {
    this.activePost = post;

    const base = this.baseEmissions[post.name];
    const newEmission = (base * post.value) / 100;

    post.currentEmission = +newEmission.toFixed(1);
    post.reduction = +(newEmission - base).toFixed(1);

    this.posts = [...this.posts];
    this.calculateTotals();
    this.updateCharts();
  }

  calculateTotals(): void {
    this.totalBaseEmissions = Object.values(this.baseEmissions).reduce((sum, val) => sum + val, 0);
    this.totalCurrentEmissions = this.posts.reduce((sum, post) => sum + post.currentEmission, 0);
    this.totalReduction = this.posts.reduce((sum, post) => sum + (post.reduction || 0), 0);
    
    // Calculate reduction rate (percentage)
    this.reductionRate = this.totalBaseEmissions > 0 
      ? ((this.totalBaseEmissions - this.totalCurrentEmissions) / this.totalBaseEmissions) * 100 
      : 0;
    
    // Emit the totals to parent component
    this.totalEmissionsChange.emit(this.totalCurrentEmissions);
    this.totalReductionChange.emit(this.totalReduction);
    this.reductionRateChange.emit(this.reductionRate);
  }

  updateCharts(): void {
    this.barChartData = {
      labels: this.posts.map(p => p.name),
      datasets: [
        {
          label: 'Émissions (tCO₂e)',
          data: this.posts.map(p => p.currentEmission),
          backgroundColor: this.posts.map(p => p.color),
          borderWidth: 1
        }
      ]
    };

    this.radarChartData = {
      labels: this.posts.map(p => p.name),
      datasets: [
        {
          label: 'Ajustement (%)',
          data: this.posts.map(p => p.value),
          backgroundColor: 'rgba(76, 29, 149, 0.3)',
          borderColor: '#4c1d95',
          borderWidth: 2,
          pointBackgroundColor: '#4c1d95'
        }
      ]
    };
  }

  getAbsValue(value: number): number {
    return Math.abs(value);
  }

  newPost: Partial<EmissionPost> = {
    name: '',
    currentEmission: 0,
    value: 100,
    color: this.getRandomColor()
  };

  getRandomColor(): string {
    const colors = [
      '#6366f1', '#8b5cf6', '#ec4899', '#f43f5e', '#ef4444', 
      '#f97316', '#f59e0b', '#10b981', '#14b8a6', '#0ea5e9'
    ];
    return colors[Math.floor(Math.random() * colors.length)];
  }

  // Method to update a post's value (adjustment percentage)
  updatePostValue(post: EmissionPost, newValue: number): void {
    const base = this.baseEmissions[post.name];
    const newEmission = (base * newValue) / 100;
    
    post.value = newValue;
    post.currentEmission = +newEmission.toFixed(1);
    post.reduction = +(newEmission - base).toFixed(1);
    
    this.posts = [...this.posts];
    this.calculateTotals();
    this.updateCharts();
  }

  // Method to update a post's base emission
  updatePostBaseEmission(post: EmissionPost, newBase: number): void {
    this.baseEmissions[post.name] = newBase;
    
    // Recalculate current emission based on the adjustment percentage
    const newEmission = (newBase * post.value) / 100;
    post.currentEmission = +newEmission.toFixed(1);
    post.reduction = +(newEmission - newBase).toFixed(1);
    
    this.posts = [...this.posts];
    this.calculateTotals();
    this.updateCharts();
  }
}