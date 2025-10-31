import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-scenario-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './scenario-card.component.html',
  styleUrls: ['./scenario-card.component.scss']
})
export class ScenarioCardComponent {
  @Input() scenario: any;
  @Output() delete = new EventEmitter<number>();
  @Output() duplicate = new EventEmitter<any>();

  onDuplicate() {
    this.duplicate.emit(this.scenario);
  }

  onDelete() {
    this.delete.emit(this.scenario.id);
  }
}
