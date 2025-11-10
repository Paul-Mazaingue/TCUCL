import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
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
  @Input() posts: EmissionPost[] = [];

  activePost: EmissionPost | null = null;

  private baseEmissions: Record<string, number> = {};

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
    this.updateCharts();
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
    color: '#6366f1'
  };
  
  addPost(): void {
    if (!this.newPost.name || this.newPost.currentEmission == null) return;
  
    const post: EmissionPost = {
      name: this.newPost.name,
      currentEmission: this.newPost.currentEmission,
      reduction: 0,
      value: this.newPost.value ?? 100,
      color: this.newPost.color ?? '#6366f1'
    };
  
    this.posts = [...this.posts, post];
  
    this.baseEmissions[post.name] = post.currentEmission;
  
    this.newPost = { name: '', currentEmission: 0, value: 100 };
  
    this.updateCharts();
  }
}
