import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ScenarioCardComponent } from '../scenario-card/scenario-card.component';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

interface Scenario {
  id: number;
  name: string;
  description: string;
  totalEmission: string; 
  ecartCible?: string; 
  reductionEstimee: string; 
}

@Component({
  selector: 'app-scenerio-gestion',
  standalone: true,
  imports: [CommonModule, FormsModule, ScenarioCardComponent],
  templateUrl: './scenerio-gestion.component.html',
  styleUrls: ['./scenerio-gestion.component.scss'],
})
export class ScenerioGestionComponent {

  isComparing = false;
@Output() scenarioCountChange = new EventEmitter<number>();

constructor(private router: Router) {}

ngOnInit() {
  // Watch route changes
  this.router.events.pipe(
    filter(event => event instanceof NavigationEnd)
  ).subscribe((event: any) => {
    this.isComparing = event.urlAfterRedirects.includes('comparaison');
  });

  if (this.scenarios.length > 0) {
    this.selectedScenario = this.scenarios[0];
  }
  this.scenarioCountChange.emit(this.scenarios.length);
  this.scenarioCountChange.emit(this.scenarios.length);
}

toggleCompareMode() {
  if (this.isComparing) {
    // Go back to main page
    this.router.navigate(['/']);
  } else {
    // Go to comparison page
    this.router.navigate(['/comparaison']);
  }
}

compareScenarios() {
  this.router.navigate(['/comparaison']);
}
  selectedScenario: Scenario | null = null;
  selectScenario(scenario: Scenario) {
    this.selectedScenario = scenario;
  }  

  scenarios: Scenario[] = [
    {
      id: 1,
      name: 'Scénario de référence',
      description: 'Basé sur les données actuelles(100% par poste)',
      totalEmission: '4875 tCO₂e',
      ecartCible: '+2194',
      reductionEstimee: '-0.0%',
    },
    {
      id: 2,
      name: 'Scénario réduction énergétique',
      description: 'Focus sur l’énergie et le numérique',
      totalEmission: '4408 tCO₂e',
      ecartCible: '+1727',
      reductionEstimee: '-9.6%',
    },
    {
      id: 3,
      name: 'Scénario mobilité durable',
      description: 'Réduction des déplacements et mobilité',
      totalEmission: '4243 tCO₂e',
      ecartCible: '+1562',
      reductionEstimee: '-13.0%',
    },
  ];

  newScenario: Partial<Scenario> = {};

  addScenario() {
    if (this.newScenario.name) {
      const newId =
        this.scenarios.length > 0
          ? Math.max(...this.scenarios.map((s) => s.id)) + 1
          : 1;

      let preset = {
        totalEmission: '',
        reduction: ' ',
      };

      switch (this.newScenario.name) {
        case 'reference':
          preset = { totalEmission: '4875 tCO₂e', reduction: '10.0 ' };
          break;
        case 'energie':
          preset = { totalEmission: '4408 tCO₂e', reduction: '-9.6 ' };
          break;
        case 'mobilite':
          preset = { totalEmission: '4243 tCO₂e', reduction: '-13.0' };
          break;
      }

      this.scenarios.push({
        id: newId,
        name: this.newScenario.name,
        description: this.newScenario.description || '',
        totalEmission: preset.totalEmission,
        reductionEstimee: preset.reduction,
      } as Scenario);

      this.newScenario = {};
      this.scenarioCountChange.emit(this.scenarios.length);
    }
  }

  duplicateScenario(scenario: Scenario) {
    const newId =
      this.scenarios.length > 0
        ? Math.max(...this.scenarios.map((s) => s.id)) + 1
        : 1;

    this.scenarios.push({
      id: newId,
      name: `${scenario.name} (Copie)`,
      description: scenario.description,
      totalEmission: scenario.totalEmission,
      reductionEstimee: scenario.reductionEstimee,
    });
    this.scenarioCountChange.emit(this.scenarios.length);
  }

  deleteScenario(id: number) {
    this.scenarios = this.scenarios.filter((s) => s.id !== id);
    this.scenarioCountChange.emit(this.scenarios.length);
  }
}
