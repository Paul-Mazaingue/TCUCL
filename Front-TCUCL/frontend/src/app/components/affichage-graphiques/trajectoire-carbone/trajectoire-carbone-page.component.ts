import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trajectoire-carbone',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './trajectoire-carbone-page.component.html',
  styleUrls: ['./trajectoire-carbone-page.component.scss']
})
export class TrajectoireCarbonePageComponent {
  constructor(private router: Router) {}
  // Fake data (tonnes CO2e par année)
  years = [2019, 2020, 2021, 2022, 2023, 2024];
  values = [1800, 1650, 1500, 1400, 1300, 1250];

  // SVG helpers
  width = 640;
  height = 300;
  pad = 40;

  get maxY() { return Math.max(...this.values) * 1.1; }
  x(i: number) { return this.pad + i * ((this.width - 2 * this.pad) / (this.values.length - 1)); }
  y(v: number) { return this.height - this.pad - (v / this.maxY) * (this.height - 2 * this.pad); }
  get polyline(): string {
    return this.values.map((v, i) => `${this.x(i)},${this.y(v)}`).join(' ');
  }

  goHome() { this.router.navigate(['/dashboard']); }
}
