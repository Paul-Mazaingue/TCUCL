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
      title: 'Optimisation du parc véhicules',
      description: 'Électrification du parc - Gain : 320 tCO₂e'
    },
    {
      title: 'Rénovation immobilière',
      description: 'Efficacité énergétique bâtiments - Gain : 280 tCO₂e'
    },
    {
      title: 'Mobilité quotidienne durable',
      description: 'Télétravail & transports alternatifs - Gain : 180 tCO₂e'
    },
    {
      title: 'Modernisation équipements',
      description: 'Haute efficacité énergétique - Gain : 150 tCO₂e'
    },
    {
      title: 'Rationalisation voyages',
      description: 'Réduction mobilité internationale - Gain : 220 tCO₂e'
    },
    {
      title: 'Écart trajectoire cible',
      description: 'Reste 2194 tCO₂e pour objectif -45%'
    }
  ];
  
}
