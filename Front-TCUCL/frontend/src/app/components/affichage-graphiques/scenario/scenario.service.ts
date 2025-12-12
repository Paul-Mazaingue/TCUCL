import { Injectable } from '@angular/core';
import { categories, EmissionPost } from '../../../models/scenario.model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ScenarioService {
  private categories : categories[] = [
    {
      id: 1,
      name: "L'evolution de référence",
      description: 'Basé sur les données actuelles (100% par poste)',
      totalEmission: 0,
      ecartCible: 0,
      reductionEstimee: 0,
      posts: [
        {
          name: 'Énergie et eau',
          color: '#2ecc71',
          value: 35,
          currentEmission: 420,
          reduction: 780,
        },
        {
          name: 'Émissions fugitives',
          color: '#27ae60',
          value: 15,
          currentEmission: 7,
          reduction: 38,
        },
        {
          name: 'Mobilité domicile-travail',
          color: '#f39c12',
          value: 100,
          currentEmission: 850,
          reduction: 0,
        },
        {
          name: 'Déplacements professionnels',
          color: '#e67e22',
          value: 100,
          currentEmission: 650,
          reduction: 0,
        },
        {
          name: 'Bâtiments et mobilier',
          color: '#f39c12',
          value: 100,
          currentEmission: 500,
          reduction: 0,
        },
        {
          name: 'Numérique',
          color: '#9b59b6',
          value: 100,
          currentEmission: 280,
          reduction: 0,
        },
        {
          name: 'Autres immobilisations',
          color: '#8e44ad',
          value: 100,
          currentEmission: 220,
          reduction: 0,
        },
        {
          name: 'Achats et restauration',
          color: '#3498db',
          value: 100,
          currentEmission: 950,
          reduction: 0,
        },
        {
          name: 'Déchets',
          color: '#16a085',
          value: 100,
          currentEmission: 180,
          reduction: 0,
        },
      ],
    },
    {
      id: 2,
      name: "L'evolution du parc Immoblier",
      description:
        'Réduction ciblée sur l’énergie et le numérique pour les bâtiments',
      totalEmission: 0,
      ecartCible: 0,
      reductionEstimee: 0,
      posts: [
        {
          name: 'Surface de bâtiment supplémentaire',
          color: '#2ecc71',
          value: 48,
          currentEmission: 102,
          reduction: 36,
        },
        {
          name: 'Surface de parking couverte supplémentaire',
          color: '#27ae60',
          value: 36,
          currentEmission: 45,
          reduction: 12,
        },
        {
          name: 'Ambition sur la performance carbone',
          color: '#f39c12',
          value: 73,
          currentEmission: 87,
          reduction: 26,
        },
        {
          name: 'Réduction carbone visée',
          color: '#e67e22',
          value: 130,
          currentEmission: 120,
          reduction: 74,
        },
        {
          name: 'Ambition sur la performance énergétique',
          color: '#9b59b6',
          value: 41,
          currentEmission: 112,
          reduction: 62,
        },
        {
          name: 'Consommation associée',
          color: '#8e44ad',
          value: 57,
          currentEmission: 188,
          reduction: 43,
        },
        {
          name: "Part d'énergie bas carbone",
          color: '#3498db',
          value: 100,
          currentEmission: 92,
          reduction: 0,
        },
        {
          name: "Taux d'équipement",
          color: '#16a085',
          value: 49,
          currentEmission: 81,
          reduction: 23,
        },
        {
          name: "Durée d'amortissement / durée de vie",
          color: '#34495e',
          value: 7,
          currentEmission: 4,
          reduction: 2,
        },
        {
          name: "Taux d'équipements par usage",
          color: '#95a5a6',
          value: 98,
          currentEmission: 29,
          reduction: 5,
        },
        {
          name: "Baisse de la quantité d'achat par usage",
          color: '#c0392b',
          value: 6,
          currentEmission: 20,
          reduction: 9,
        },
      ],
    },
    {
      id: 3,
      name: "L'evolution de la mobilité quotidienne",
      description:
        'Diminution des émissions via la mobilité durable et les déplacements réduits',
      totalEmission: 0,
      ecartCible: 0,
      reductionEstimee:0,
      posts: [
        {
          name: 'Part des étudiants sur site',
          color: '#2ecc71',
          value: 100,
          currentEmission: 500,
          reduction: 50,
        },
        {
          name: 'Distance domicile-campus',
          color: '#27ae60',
          value: 5.5,
          currentEmission: 100,
          reduction: 5,
        },
        {
          name: 'Parts modales (étudiants)',
          color: '#f39c12',
          value: 82,
          currentEmission: 200,
          reduction: 10,
        },
        {
          name: 'Part des nouveaux salariés sur site',
          color: '#e67e22',
          value: 100,
          currentEmission: 300,
          reduction: 30,
        },
        {
          name: 'Distance domicile-travail',
          color: '#f39c12',
          value: 11.7,
          currentEmission: 400,
          reduction: 20,
        },
        {
          name: 'Nombre de jours de présence sur site',
          color: '#9b59b6',
          value: 210,
          currentEmission: 150,
          reduction: 15,
        },
        {
          name: 'Parts modales (salariés)',
          color: '#8e44ad',
          value: 87,
          currentEmission: 600,
          reduction: 50,
        },
        {
          name: 'Part covoiturage',
          color: '#3498db',
          value: 0,
          currentEmission: 0,
          reduction: 0,
        },
      ],
    },
    {
      id: 4,
      name: "L'evolution de la mobilité internationale",
      description:
        'Adaptation des déplacements internationaux, limitation des voyages professionnels vers l’étranger',
      totalEmission: 0,
      ecartCible: 0,
      reductionEstimee: 0,
      posts: [
        {
          name: "Part d'étudiants partant en mobilité chaque année",
          color: '#2ecc71',
          value: 100,
          currentEmission: 500,
          reduction: 50,
        },
        {
          name: 'Proportion de ces étudiants partant en Europe',
          color: '#27ae60',
          value: 60,
          currentEmission: 200,
          reduction: 30,
        },
        {
          name: 'Part modale train, bus / avion',
          color: '#f39c12',
          value: 40,
          currentEmission: 150,
          reduction: 20,
        },
        {
          name: 'Distance moyenne train, bus',
          color: '#e67e22',
          value: 800,
          currentEmission: 300,
          reduction: 50,
        },
        {
          name: "Part d'étudiants partant en mobilité chaque année",
          color: '#f39c12',
          value: 100,
          currentEmission: 500,
          reduction: 50,
        },
        {
          name: 'Proportion de ces étudiants partant en Europe',
          color: '#9b59b6',
          value: 60,
          currentEmission: 200,
          reduction: 30,
        },
        {
          name: 'Part modale train, bus / avion',
          color: '#8e44ad',
          value: 40,
          currentEmission: 150,
          reduction: 20,
        },
        {
          name: 'Part de salariés partant en mobilité chaque année',
          color: '#3498db',
          value: 90,
          currentEmission: 450,
          reduction: 45,
        },
        {
          name: 'Proportion de ces salariés partant en Europe',
          color: '#16a085',
          value: 50,
          currentEmission: 250,
          reduction: 25,
        },
        {
          name: 'Part modale train, bus / avion',
          color: '#1abc9c',
          value: 30,
          currentEmission: 120,
          reduction: 15,
        },
      ],
    },
    {
      id: 5,
      name:" L'evolution des effectifs",
      description:
        'Hypothèse de réajustement des effectifs et réduction corrélée des activités administratives et logistiques',
      totalEmission: 0,
      ecartCible: 0,
      reductionEstimee: 0,
      posts: [
        {
          name: 'Effectif étudiant',
          color: '#2ecc71',
          value: 120,
          currentEmission: 800,
          reduction: 150,
        },
        {
          name: 'Effectif salariés',
          color: '#27ae60',
          value: 450,
          currentEmission: 600,
          reduction: 100,
        },
      ],
    },
  ];

  private baseEmissions: number[][] = [];

  constructor() {
    // Store base emissions for each scenario
    this.categories.forEach(scenario => {
      this.baseEmissions[scenario.id] = scenario.posts.map(post => post.currentEmission);
    });
  }

  getScenarios(): Observable<categories[]> {
    return of(this.categories);
  }

  getScenarioById(id: number): Observable<categories | undefined> {
    return of(this.categories.find(scenario => scenario.id === id));
  }

  updatePost(scenarioId: number, post: EmissionPost): void {
    this.getScenarioById(scenarioId).subscribe(scenario => {
      if (!scenario) return;

      const index = scenario.posts.indexOf(post);
      const baseValue = this.baseEmissions[scenarioId][index];

      post.currentEmission = Math.round(baseValue * post.value / 100);
      post.reduction = baseValue - post.currentEmission;
    });
  }
}
