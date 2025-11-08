import { Component, EventEmitter, Output, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ScenarioCardComponent } from '../scenario-card/scenario-card.component';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { ScenarioService } from "../../scenario.service";
import { Scenario } from "../../../../../models/scenario.model";
import { PostsComponent } from '../posts/posts.component';

@Component({
  selector: 'app-scenerio-gestion',
  standalone: true,
  imports: [CommonModule, FormsModule, ScenarioCardComponent, PostsComponent],
  providers: [ScenarioService],
  templateUrl: './scenerio-gestion.component.html',
  styleUrls: ['./scenerio-gestion.component.scss'],
})
export class ScenerioGestionComponent implements OnInit {
  scenarios: Scenario[] = [];
  selectedScenario: Scenario | null = null;
  isComparing = false;
  newScenario: Partial<Scenario> = {};

  @Output() scenarioCountChange = new EventEmitter<number>();

  constructor(
    private router: Router,
    private scenarioService: ScenarioService
  ) {}

  ngOnInit() {
    // watch router events to toggle compare mode
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        this.isComparing = event.urlAfterRedirects.includes('comparaison');
      });

    // ✅ fetch scenarios from service
    this.loadScenarios();
  }

  private loadScenarios() {
    this.scenarioService.getScenarios().subscribe(scenarios => {
      this.scenarios = scenarios;
      if (this.scenarios.length > 0) {
        this.selectedScenario = this.scenarios[0];
      }
      this.scenarioCountChange.emit(this.scenarios.length);
    });
  }

  toggleCompareMode() {
    this.isComparing ? this.router.navigate(['/']) : this.router.navigate(['/comparaison']);
  }

  compareScenarios() {
    this.router.navigate(['/comparaison']);
  }

  selectScenario(scenario: Scenario) {
    this.selectedScenario = scenario;
  }

  addScenario() {
    if (!this.newScenario.name) return;

    const newId =
      this.scenarios.length > 0
        ? Math.max(...this.scenarios.map(s => s.id)) + 1
        : 1;

    let preset = { totalEmission: '', reduction: '' };

    switch (this.newScenario.name.toLowerCase()) {
      case 'reference':
        preset = { totalEmission: '4875 tCO₂e', reduction: '-0.0%' };
        break;
      case 'energie':
        preset = { totalEmission: '4408 tCO₂e', reduction: '-9.6%' };
        break;
      case 'mobilite':
        preset = { totalEmission: '4243 tCO₂e', reduction: '-13.0%' };
        break;
    }

    const newScenario: Scenario = {
      id: newId,
      name: this.newScenario.name,
      description: this.newScenario.description || '',
      totalEmission: preset.totalEmission,
      reductionEstimee: preset.reduction,
      ecartCible: '',
      posts: []
    };

    this.scenarios.push(newScenario);
    this.scenarioCountChange.emit(this.scenarios.length);
    this.newScenario = {};
  }

  duplicateScenario(scenario: Scenario) {
    const newId =
      this.scenarios.length > 0
        ? Math.max(...this.scenarios.map(s => s.id)) + 1
        : 1;

    const duplicate: Scenario = {
      ...scenario,
      id: newId,
      name: `${scenario.name} (Copie)`
    };

    this.scenarios.push(duplicate);
    this.scenarioCountChange.emit(this.scenarios.length);
  }

  deleteScenario(id: number) {
    this.scenarios = this.scenarios.filter(s => s.id !== id);
    this.scenarioCountChange.emit(this.scenarios.length);
  }
}
