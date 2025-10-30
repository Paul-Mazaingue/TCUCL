import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-outil-suivi',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './outil-suivi-page.component.html',
  styleUrls: ['./outil-suivi-page.component.scss']
})
export class OutilSuiviPageComponent {
  constructor(private router: Router) {}

  // Données fictives
  labels = ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin'];
  data = [120, 80, 150, 60, 200, 90];
  get max() {
    return Math.max(...this.data, 1);
  }

  goHome() {
    this.router.navigate(['/dashboard']);
  }
}
