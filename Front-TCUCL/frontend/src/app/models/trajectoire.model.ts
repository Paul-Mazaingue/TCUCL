export interface TrajectoirePosteReglage {
  id: string;
  reductionBasePct: number;
}

export interface Trajectoire {
  entiteId: number;
  referenceYear: number;
  targetYear: number;
  targetPercentage: number; // 0-100
  lockGlobal?: boolean;
  postesReglages?: TrajectoirePosteReglage[];
}
