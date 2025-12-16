import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ScenerioGestionComponent } from "../sub-sections/scenerio-gestion/scenerio-gestion.component";
import { RecommendationsComponent } from "../sub-sections/recommendations/recommendations.component";
import { categories, Scenario } from "../../../../models/scenario.model";
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-pilotage-scenario',
  standalone: true,
  imports: [CommonModule, FormsModule, ScenerioGestionComponent, RecommendationsComponent, MatMenuModule],
  templateUrl: './pilotage-scenario-page.component.html',
  styleUrls: ['./pilotage-scenario-page.component.scss']
})
export class PilotageScenarioPageComponent {
  constructor(private router: Router) {}

  @Output() scenarioCountChange = new EventEmitter<number>();

  newScenario: Partial<Scenario> = {
    name: 'Scenario de reference',
    description: '',
    year: new Date().getFullYear(),
    goal: 0,
  };
  
  scenarios: Scenario[] = [];
  count = 0;
  showForm = false;
  selectedScenarioId: number | null = null;
  editingScenarioId: number | null = null; // Track which scenario is being edited
  
  // Store edited values temporarily
  editedScenario: Partial<Scenario> = {
    name: '',
    description: '',
    year: 0,
    goal: 0,
  };

  updateCount(newCount: number): void {
    this.count = newCount;
  }

  validateYear(value: number) {
    if (value < 2019) {
      this.newScenario.year = 2019;
    }
  }
  
  resetForm(): void {
    this.newScenario = {
      name: 'Scenario de reference',
      description: '',
      year: new Date().getFullYear(),
      goal: 0,
    };
  }

  addScenario(): void {
    if (!this.newScenario.name) return;
  
    if (Number(this.newScenario.year ?? 0) < 2019) {
      this.newScenario.year = 2019;
    }
  
    const newId = this.scenarios.length > 0
      ? Math.max(...this.scenarios.map(s => s.id)) + 1
      : 1;
  
    const newScenario: Scenario = {
      id: newId,
      name: this.newScenario.name,
      description: this.newScenario.description || '',
      year: this.newScenario.year || 2019,
      goal: this.newScenario.goal || 0,
    };
  
    this.scenarios.push(newScenario);
    this.scenarioCountChange.emit(this.scenarios.length);
    this.resetForm();
    this.showForm = false;
    this.count = this.scenarios.length;
  }
  

  toggleForm(): void {
    this.showForm = !this.showForm;
    if (this.showForm) {
      this.resetForm();
    }
  }

  cancelForm(): void {
    this.resetForm();
    this.showForm = false;
  }

  onSelectScenario(id: number): void {
    // Don't select if editing
    if (this.editingScenarioId !== id) {
      this.selectedScenarioId = id;
    }
  }

  onEditScenario(scenario: Scenario): void {
    // Store original values and enter edit mode
    this.editingScenarioId = scenario.id;
    this.editedScenario = {
      name: scenario.name,
      description: scenario.description,
      year: scenario.year,
      goal: scenario.goal
    };
  }
  saveEdit(): void {
    if (!this.editingScenarioId || !this.editedScenario.name) return;
  
    if (Number(this.editedScenario.year ?? 0) < 2019) {
      this.editedScenario.year = 2019;
    }
  
    const index = this.scenarios.findIndex(s => s.id === this.editingScenarioId);
    if (index !== -1) {
      this.scenarios[index] = {
        ...this.scenarios[index],
        ...this.editedScenario
      };
    }
  
    this.cancelEdit();
  }
  
  cancelEdit(): void {
    this.editingScenarioId = null;
    this.editedScenario = {
      name: '',
      description: '',
      year: 0,
      goal: 0,
    };
  }

  onDeleteScenario(id: number): void {
    this.scenarios = this.scenarios.filter(scenario => scenario.id !== id);
    this.count = this.scenarios.length;
    this.scenarioCountChange.emit(this.scenarios.length);
    
    // If we were editing this scenario, cancel edit mode
    if (this.editingScenarioId === id) {
      this.cancelEdit();
    }
  }

  onDuplicateScenario(scenario: Scenario): void {
    const newId = this.scenarios.length > 0 
      ? Math.max(...this.scenarios.map(s => s.id)) + 1 
      : 1;
    
    const duplicatedScenario: Scenario = {
      ...scenario,
      id: newId,
      name: scenario.name + ' (copie)'
    };
    
    this.scenarios.push(duplicatedScenario);
    this.count = this.scenarios.length;
    this.scenarioCountChange.emit(this.scenarios.length);
  }

  goHome(): void {
    this.router.navigate(['/dashboard']);
  }
}