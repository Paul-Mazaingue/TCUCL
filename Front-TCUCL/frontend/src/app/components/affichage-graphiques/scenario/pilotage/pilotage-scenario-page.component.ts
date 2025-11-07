import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ScenerioGestionComponent } from "../sub-sections/scenerio-gestion/scenerio-gestion.component";
import { RecommendationsComponent } from "../sub-sections/recommendations/recommendations.component";
import { PostsComponent } from "../sub-sections/posts/posts.component";

@Component({
  selector: 'app-pilotage-scenario',
  standalone: true,
  imports: [CommonModule, ScenerioGestionComponent, RecommendationsComponent, PostsComponent],
  templateUrl: './pilotage-scenario-page.component.html',
  styleUrls: ['./pilotage-scenario-page.component.scss']
})
export class PilotageScenarioPageComponent {
  constructor(private router: Router) {}

  count=0;

  updateCount(newCount: number) {
    this.count = newCount;
  }  

  goHome() { this.router.navigate(['/dashboard']); }
}
