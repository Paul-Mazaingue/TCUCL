import {
  Component,
  Input,
  OnChanges,
  SimpleChanges,
  Output,
  EventEmitter
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChartData, ChartOptions } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { EmissionPost } from '../../../../../models/scenario.model';

@Component({
  selector: 'app-posts',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseChartDirective],
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnChanges {

  /* ===================== INPUTS / OUTPUTS ===================== */

  @Input() posts: EmissionPost[] = [];
  @Input() scenarioName = '';

  @Output() totalEmissionsChange = new EventEmitter<number>();
  @Output() totalReductionChange = new EventEmitter<number>();
  @Output() reductionRateChange = new EventEmitter<number>();

  /* ===================== STATE ===================== */

  activePost: EmissionPost | null = null;

  /** Immutable snapshot of base emissions */
  private baseEmissions: Record<string, number> = {};

  totalBaseEmissions = 0;
  totalCurrentEmissions = 0;
  totalReduction = 0;
  reductionRate = 0;

  barChartData!: ChartData<'bar'>;
  radarChartData!: ChartData<'radar'>;

  /* ===================== CHART OPTIONS ===================== */

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
      legend: { display: false }
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

  /* ===================== LIFECYCLE ===================== */

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['posts'] && this.posts?.length) {
      this.captureBaseEmissions();
      this.resetPostsToBase();

      this.activePost = this.posts[0];

      this.calculateTotals();
      this.updateCharts();
    }
  }

  /* ===================== BASE HANDLING ===================== */

  private captureBaseEmissions(): void {
    this.baseEmissions = this.posts.reduce((acc, post) => {
      acc[post.name] = post.currentEmission;
      return acc;
    }, {} as Record<string, number>);
  }

  private resetPostsToBase(): void {
    this.posts = this.posts.map(post => ({
      ...post,
      value: 100,
      currentEmission: this.baseEmissions[post.name],
      reduction: 0
    }));
  }

  /* ===================== POST CALCULATIONS ===================== */

  private recalculatePost(post: EmissionPost): void {
    const base = this.baseEmissions[post.name];
    if (base == null) return;

    const newEmission = (base * post.value) / 100;

    post.currentEmission = +newEmission.toFixed(1);
    post.reduction = +(base - newEmission).toFixed(1);
  }

  applyPost(post: EmissionPost): void {
    this.activePost = post;

    this.recalculatePost(post);

    this.posts = [...this.posts];
    this.calculateTotals();
    this.updateCharts();
  }

  updatePostValue(post: EmissionPost, newValue: number): void {
    post.value = newValue;

    this.recalculatePost(post);

    this.posts = [...this.posts];
    this.calculateTotals();
    this.updateCharts();
  }

  /* ===================== TOTALS ===================== */

  calculateTotals(): void {
    this.totalBaseEmissions = Object.values(this.baseEmissions)
      .reduce((sum, v) => sum + v, 0);

    this.totalCurrentEmissions = this.posts
      .reduce((sum, p) => sum + p.currentEmission, 0);

    this.totalReduction = this.posts
      .reduce((sum, p) => sum + (p.reduction || 0), 0);

    this.reductionRate = this.totalBaseEmissions
      ? ((this.totalBaseEmissions - this.totalCurrentEmissions) / this.totalBaseEmissions) * 100
      : 0;

    this.totalEmissionsChange.emit(this.totalCurrentEmissions);
    this.totalReductionChange.emit(this.totalReduction);
    this.reductionRateChange.emit(this.reductionRate);
  }

  /* ===================== CHARTS ===================== */

  updateCharts(): void {
    this.barChartData = {
      labels: this.posts.map(p => p.name),
      datasets: [{
        label: 'Émissions (tCO₂e)',
        data: this.posts.map(p => p.currentEmission),
        backgroundColor: this.posts.map(p => p.color),
        borderWidth: 1
      }]
    };

    this.radarChartData = {
      labels: this.posts.map(p => p.name),
      datasets: [{
        label: 'Ajustement (%)',
        data: this.posts.map(p => p.value),
        backgroundColor: 'rgba(76, 29, 149, 0.3)',
        borderColor: '#4c1d95',
        borderWidth: 2,
        pointBackgroundColor: '#4c1d95'
      }]
    };
  }

  /* ===================== UTILS ===================== */

  getAbsValue(value: number): number {
    return Math.abs(value);
  }
}
