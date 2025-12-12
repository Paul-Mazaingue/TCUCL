import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { categories } from '../../../../../models/scenario.model';


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
  @Input() category!: categories;
  @Input() isSelected = false;
  @Output() select = new EventEmitter<any>();


  formatNumber(value: number): string {
    return value.toLocaleString('fr-FR', {
      minimumFractionDigits: 1,
      maximumFractionDigits: 1
    });
  }
  
  onSelect() {
    this.select.emit(this.category);
  }
}
