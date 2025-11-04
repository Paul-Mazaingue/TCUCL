import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';


export interface Scenario {
  id: number;
  name: string;
  description: string;
  totalEmission: string;
  ecartCible?: string;
  reductionEstimee: string;
}

@Component({
  selector: 'app-scenario-card',
  standalone: true,
  imports: [CommonModule, FormsModule,MatMenuModule],
  templateUrl: './scenario-card.component.html',
  styleUrls: ['./scenario-card.component.scss']
})
export class ScenarioCardComponent {
  @Input() scenario!: Scenario;
  @Input() isSelected = false;
  @Output() delete = new EventEmitter<number>();
  @Output() duplicate = new EventEmitter<Scenario>();
  @Output() modify = new EventEmitter<Scenario>();
  @Output() select = new EventEmitter<any>();

  isEditing = false;
  editedScenario: Partial<Scenario> = {};

  onSelect() {
    this.select.emit(this.scenario);
  }

  startEdit() {
    this.isEditing = true;
    this.editedScenario = { ...this.scenario };
  }

  saveEdit() {
    this.modify.emit(this.editedScenario as Scenario);
    this.isEditing = false;
  }

  cancelEdit() {
    this.isEditing = false;
  }

  onDuplicate() {
    this.duplicate.emit(this.scenario);
  }

  onDelete() {
    this.delete.emit(this.scenario.id);
  }
}
