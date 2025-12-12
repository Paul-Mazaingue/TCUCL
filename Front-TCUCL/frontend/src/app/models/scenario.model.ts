export interface EmissionPost {
    name: string;
    color: string;
    value: number; 
    currentEmission: number;
    reduction: number;
  }
  export interface categories{
    name: string;
    id: number;
    description: string;
    totalEmission: number; 
    ecartCible: number;
    reductionEstimee: number;
    posts: EmissionPost[];
  }

  export interface Scenario {
    id: number;
    name: string;
    year?: string | number; 
    goal?: number; 
    description: string;
  }
  