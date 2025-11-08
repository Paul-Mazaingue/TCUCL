import { NgFor } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-recommendations',
  imports: [NgFor],
  templateUrl: './recommendations.component.html',
  styleUrl: './recommendations.component.scss'
})
export class RecommendationsComponent {
 recommendations: any = [
    {
      title: 'Réduire "Énergie et eau" de 20%',
      description: 'Gain potentiel : 240 tCO₂e (4.9% du total)'
    },
    {
      title: 'Réduire "Achats et restauration" de 20%',
      description: 'Gain potentiel : 190 tCO₂e (3.9% du total)'
    },
    {
      title: 'Réduire "Mobilité domicile-travail" de 20%',
      description: 'Gain potentiel : 170 tCO₂e (3.5% du total)'
    },
    {
      title: 'Écart avec la trajectoire cible',
      description: 'Il reste 2194 tCO₂e à réduire pour atteindre l’objectif de -45%'
    }
  ];
  
}
