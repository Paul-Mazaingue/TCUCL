import { Component, EventEmitter, Output, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScenarioCardComponent } from '../scenario-card/scenario-card.component';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { ScenarioService } from "../../scenario.service";
import { categories } from "../../../../../models/scenario.model";
import { PostsComponent } from '../posts/posts.component';
import { Input } from '@angular/core';

@Component({
  selector: 'app-scenerio-gestion',
  standalone: true,
  imports: [CommonModule, ScenarioCardComponent, PostsComponent],
  providers: [ScenarioService],
  templateUrl: './scenerio-gestion.component.html',
  styleUrls: ['./scenerio-gestion.component.scss'],
})
export class ScenerioGestionComponent implements OnInit {
  @Input() categories: categories[] = [];
  selectedScenario: categories | null = null;
  @Input() scenarioId!: number; 
  isComparing = false;
  @Output() scenarioCountChange = new EventEmitter<number>();

  // Add these properties to store the emission data
  totalEmissions: number = 0;
  totalReduction: number = 0;
  reductionRate: number = 0;

  constructor(
    private router: Router,
    private scenarioService: ScenarioService
  ) {}

  ngOnInit() {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        this.isComparing = event.urlAfterRedirects.includes('comparaison');
      });

    this.loadScenarios();
  }

  private loadScenarios() {
    this.scenarioService.getScenarios().subscribe(categories => {
      this.categories = categories;
  
      if (this.scenarioId) {
        this.selectedScenario = this.categories.find(s => s.id === this.scenarioId) || null;
        // Optionally, initialize totals from the selected scenario
        if (this.selectedScenario) {
          this.calculateInitialTotals(this.selectedScenario);
        }
      }
  
      this.scenarioCountChange.emit(this.categories.length);
    });
  }

  // Add these methods to handle the emission events
  onTotalEmissionsChange(total: number) {
    this.totalEmissions = total;
    // Update the selected scenario with new total if needed
    if (this.selectedScenario) {
      this.selectedScenario.totalEmission = total;
    }
    console.log('Total Emissions updated:', total);
  }

  onTotalReductionChange(reduction: number) {
    this.totalReduction = reduction;
    if (this.selectedScenario) {
      this.selectedScenario.reductionEstimee = reduction;
    }
    console.log('Total Reduction updated:', reduction);
  }

  onReductionRateChange(rate: number) {
    this.reductionRate = rate;
    if (this.selectedScenario) {
      this.selectedScenario.ecartCible = rate;
    }
    console.log('Reduction Rate updated:', rate);
  }

  // Optional: Calculate initial totals from the scenario data
  private calculateInitialTotals(scenario: categories) {
    if (scenario.posts && scenario.posts.length > 0) {
      this.totalEmissions = scenario.posts.reduce((sum, post) => sum + post.currentEmission, 0);
      this.totalReduction = scenario.posts.reduce((sum, post) => sum + (post.reduction || 0), 0);
      // You might need to calculate initial reduction rate based on base emissions
    }
  }
  
  toggleCompareMode() {
    this.isComparing ? this.router.navigate(['/']) : this.router.navigate(['/comparaison']);
  }

  ngOnChanges() {
    if (this.categories.length > 0 && this.scenarioId) {
      this.selectedScenario = this.categories.find(s => s.id === this.scenarioId) || null;
      if (this.selectedScenario) {
        this.calculateInitialTotals(this.selectedScenario);
      }
    }
  }
  
  compareScenarios() {
    this.router.navigate(['/comparaison']);
  }

  selectScenario(category: categories) {
    this.selectedScenario = category;
    // Reset totals when selecting a new scenario
    this.calculateInitialTotals(category);
  }
}