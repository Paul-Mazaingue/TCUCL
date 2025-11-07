// scenario.model.ts
export interface EmissionPost {
    name: string;
    color: string;
    value: number; // Adjustment level (%)
    currentEmission: number; // tCO₂e
    reduction: number; // tCO₂e
  }
  
  export interface Scenario {
    id: number;
    name: string;
    description: string;
    totalEmission: string; // e.g., "4875 tCO₂e"
    ecartCible: string; // e.g., "+2194"
    reductionEstimee: string; // e.g., "-9.6%"
    posts: EmissionPost[];
  }
  