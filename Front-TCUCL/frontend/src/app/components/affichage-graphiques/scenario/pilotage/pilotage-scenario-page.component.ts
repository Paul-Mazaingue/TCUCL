import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ScenerioGestionComponent } from "../sub-sections/scenerio-gestion/scenerio-gestion.component";
import { RecommendationsComponent } from "../sub-sections/recommendations/recommendations.component";

type Slice = { label: string; value: number; color: string };

@Component({
  selector: 'app-pilotage-scenario',
  standalone: true,
  imports: [CommonModule, ScenerioGestionComponent, RecommendationsComponent],
  templateUrl: './pilotage-scenario-page.component.html',
  styleUrls: ['./pilotage-scenario-page.component.scss']
})
export class PilotageScenarioPageComponent {
  constructor(private router: Router) {}
  count=3;

  slices: Slice[] = [
    { label: 'Sobriété', value: 35, color: '#329dd5' },
    { label: 'Efficacité', value: 25, color: '#66bb6a' },
    { label: 'Électrification', value: 20, color: '#ffa726' },
    { label: 'Compensation', value: 20, color: '#ab47bc' }
  ];

  get total(): number {
    return this.slices.reduce((s, x) => s + x.value, 0);
  }

  get gradient(): string {
    let acc = 0;
    const parts: string[] = [];
    for (const s of this.slices) {
      const start = (acc / this.total) * 360;
      acc += s.value;
      const end = (acc / this.total) * 360;
      parts.push(`${s.color} ${start}deg ${end}deg`);
    }
    return `conic-gradient(${parts.join(', ')})`;
  }

  goHome() { this.router.navigate(['/dashboard']); }
}
