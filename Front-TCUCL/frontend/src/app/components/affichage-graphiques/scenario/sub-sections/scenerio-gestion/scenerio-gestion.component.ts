import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ScenarioCardComponent } from '../scenario-card/scenario-card.component';

interface Scenario {
  id: number;
  name: string;
  description: string;
  totalEmission: string; // e.g. '4875 tCO₂e'
  ecartCible?: string; // e.g. '+2194 tCO₂e à réduire'
  reductionEstimee: string; // e.g. '-0.0%'
}

@Component({
  selector: 'app-scenerio-gestion',
  standalone: true,
  imports: [CommonModule, FormsModule, ScenarioCardComponent],
  templateUrl: './scenerio-gestion.component.html',
  styleUrls: ['./scenerio-gestion.component.scss'],
})
export class ScenerioGestionComponent {
  selectedScenario: Scenario = {
    totalEmission: 'N/A',
    ecartCible: 'N/A',
    reductionEstimee: 'N/A',
    id: 0,
    name: '',
    description: ''
  };
  
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
  }

  deleteScenario(id: number) {
    this.scenarios = this.scenarios.filter((s) => s.id !== id);
  }
}
