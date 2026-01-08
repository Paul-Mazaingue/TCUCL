import { Component, EventEmitter, Output, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScenarioCardComponent } from '../scenario-card/scenario-card.component';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { ScenarioService } from '../../scenario.service';
import { categories } from '../../../../../models/scenario.model';
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

  // Original emission data properties
  totalEmissions: number = 0;
  totalReduction: number = 0;
  reductionRate: number = 0;

  // NEW: Recommendation properties (added without affecting original code)
  recommendations: string[] = [];
  emissionLevel: 'LOW' | 'MEDIUM' | 'HIGH' = 'LOW';
  reductionPerformance: 'EXCELLENT' | 'GOOD' | 'POOR' = 'GOOD';

  constructor(
    private router: Router,
    private scenarioService: ScenarioService
  ) {}

  ngOnInit() {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        this.isComparing = event.urlAfterRedirects.includes('comparaison');
      });

    this.loadScenarios();
  }

  private loadScenarios() {
    this.scenarioService.getScenarios().subscribe((categories) => {
      this.categories = categories;

      if (this.scenarioId) {
        this.selectedScenario =
          this.categories.find((s) => s.id === this.scenarioId) || null;
        if (this.selectedScenario) {
          // NEW: Generate recommendations when scenario is loaded
          this.generateRecommendations();
        }
      }

      this.scenarioCountChange.emit(this.categories.length);
    });
  }

  // Original methods remain unchanged
  onTotalEmissionsChange(total: number) {
    this.totalEmissions = total;
    if (this.selectedScenario) {
      this.selectedScenario.totalEmission = total;
    }
    console.log('Total Emissions updated:', total);

    // NEW: Added recommendation generation (doesn't affect original functionality)
    this.generateRecommendations();
  }

  onTotalReductionChange(reduction: number) {
    this.totalReduction = reduction;
    if (this.selectedScenario) {
      this.selectedScenario.reductionEstimee = reduction;
    }
    console.log('Total Reduction updated:', reduction);

    // NEW: Added recommendation generation (doesn't affect original functionality)
    this.generateRecommendations();
  }

  onReductionRateChange(rate: number) {
    this.reductionRate = rate;
    if (this.selectedScenario) {
      this.selectedScenario.ecartCible = rate;
    }
    console.log('Reduction Rate updated:', rate);

    // NEW: Added recommendation generation (doesn't affect original functionality)
    this.generateRecommendations();
  }

  ngOnChanges() {
    if (this.categories.length > 0 && this.scenarioId) {
      this.selectedScenario =
        this.categories.find((s) => s.id === this.scenarioId) || null;
      // NEW: Generate recommendations when scenario changes
      if (this.selectedScenario) {
        this.generateRecommendations();
      }
    }
  }

  compareScenarios() {
    this.router.navigate(['/comparaison']);
  }

  selectScenario(category: categories) {
    this.selectedScenario = category;
    // NEW: Generate recommendations when scenario is selected
    this.generateRecommendations();
  }

  // ========== NEW RECOMMENDATION METHODS (additions only) ==========

  private generateRecommendations() {
    this.recommendations = [];
    this.analyzeEmissionLevel();
    this.analyzeReductionPerformance();

    // Generate specific recommendations
    if (this.emissionLevel === 'HIGH' && this.reductionPerformance === 'POOR') {
      this.recommendations.push(
        '🚨 High emissions with poor reduction rate detected',
        '• Consider implementing stricter emission controls',
        '• Review current reduction strategies for effectiveness',
        '• Invest in high-impact reduction technologies',
        '• Set more aggressive short-term reduction targets'
      );
    } else if (
      this.emissionLevel === 'HIGH' &&
      this.reductionPerformance === 'GOOD'
    ) {
      this.recommendations.push(
        '⚠️ High emissions with moderate reduction progress',
        '• Maintain current reduction initiatives',
        '• Explore additional reduction opportunities',
        '• Consider carbon offset programs for immediate impact',
        '• Benchmark against industry best practices'
      );
    } else if (
      this.emissionLevel === 'LOW' &&
      this.reductionPerformance === 'EXCELLENT'
    ) {
      this.recommendations.push(
        '✅ Excellent performance with low emissions',
        '• Continue current successful strategies',
        '• Document and share best practices',
        '• Consider becoming a net-zero or carbon-negative entity',
        '• Explore innovative carbon capture technologies'
      );
    }

    // Add MEDIUM emission level cases
    if (this.emissionLevel === 'MEDIUM') {
      this.recommendations.push(
        '📊 Medium emission levels detected',
        '• Monitor emission trends closely',
        '• Implement incremental improvements',
        '• Set realistic reduction targets',
        '• Consider cost-effective reduction measures'
      );
    }

    // Category-specific recommendations
    if (this.selectedScenario) {
      this.addCategorySpecificRecommendations();
    }
  }

  private analyzeEmissionLevel() {
    // Define thresholds (adjust based on your business context)
    const highThreshold = 10000; // Example: tons of CO2
    const mediumThreshold = 5000;

    if (this.totalEmissions > highThreshold) {
      this.emissionLevel = 'HIGH';
    } else if (this.totalEmissions > mediumThreshold) {
      this.emissionLevel = 'MEDIUM';
    } else {
      this.emissionLevel = 'LOW';
    }
  }

  private analyzeReductionPerformance() {
    // Analyze reduction rate effectiveness
    if (this.reductionRate >= 20) {
      this.reductionPerformance = 'EXCELLENT';
    } else if (this.reductionRate >= 10) {
      this.reductionPerformance = 'GOOD';
    } else {
      this.reductionPerformance = 'POOR';
    }
  }

  private addCategorySpecificRecommendations() {
    if (!this.selectedScenario) return;

    const categoryName = this.selectedScenario.name?.toLowerCase() || '';

    if (categoryName.includes('energy') || categoryName.includes('power')) {
      this.recommendations.push(
        '🔋 Energy Sector Recommendations:',
        '• Transition to renewable energy sources',
        '• Implement energy efficiency measures',
        '• Consider on-site solar generation',
        '• Explore battery storage solutions'
      );
    } else if (
      categoryName.includes('transport') ||
      categoryName.includes('vehicle')
    ) {
      this.recommendations.push(
        '🚗 Transportation Recommendations:',
        '• Electrify vehicle fleet',
        '• Optimize logistics and routing',
        '• Promote public transport and carpooling',
        '• Implement telecommuting policies'
      );
    } else if (
      categoryName.includes('manufacturing') ||
      categoryName.includes('production')
    ) {
      this.recommendations.push(
        '🏭 Manufacturing Recommendations:',
        '• Implement circular economy principles',
        '• Optimize production processes',
        '• Use recycled materials',
        '• Upgrade to energy-efficient machinery'
      );
    }
  }

 // NOUVEAU : Méthode publique pour obtenir le statut de progression (peut être utilisée dans le template)
 getProgressStatus(): { status: string; color: string; icon: string } {
  if (this.reductionRate >= 20) {
    return { status: 'En avance sur l\'objectif', color: 'success', icon: '✅' };
  } else if (this.reductionRate >= 10) {
    return { status: 'Dans les temps', color: 'warning', icon: '⚠️' };
  } else {
    return { status: 'Nécessite une attention', color: 'danger', icon: '🚨' };
  }
}

// NOUVEAU : Méthode publique pour calculer l'efficacité de la réduction
calculateEffectiveness(): number {
  if (this.totalEmissions === 0) return 0;
  const effectiveness = (this.totalReduction / this.totalEmissions) * 100;
  return Math.round(effectiveness * 10) / 10; // Arrondir à 1 décimale
}

// NOUVEAU : Méthodes utilitaires optionnelles (peuvent être ajoutées si nécessaire)
exportRecommendations() {
  if (!this.selectedScenario) return;

  const content = `
Scénario : ${this.selectedScenario.name || 'Inconnu'}
Date : ${new Date().toLocaleDateString()}
Émissions totales : ${this.totalEmissions}
Réduction totale : ${this.totalReduction}
Taux de réduction : ${this.reductionRate} %
Statut : ${this.getProgressStatus().status}

RECOMMANDATIONS :
${this.recommendations.join('\n')}
  `;

  // Créer un fichier téléchargeable
  const blob = new Blob([content], { type: 'text/plain' });
  const url = window.URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url;
  a.download = `recommandations-${
    this.selectedScenario.name || 'scenario'
  }.txt`;
  a.click();
}
}
