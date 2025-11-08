import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiEndpoints } from './api-endpoints';
import { AuthService } from './auth.service';

export interface OutilSuiviData {
  years: number[];
  objectif: number[]; // contient éventuellement NaN en mode mock
  realise: number[];  // contient éventuellement NaN en mode mock
  postes: string[];
  postesObjectifParAn: Record<number, number[]>;
  postesRealiseParAn: Record<number, number[]>;
  indicateursParAn: Record<number, Record<string, number | null>>;
  globalTotals: number[];
}

export interface EntiteNomId {
  id: number;
  nom: string;
}

@Injectable({ providedIn: 'root' })
export class OutilSuiviService {
  constructor(private http: HttpClient, private auth: AuthService) {}

  private headers(): HttpHeaders {
    const token = this.auth.getToken();
    return new HttpHeaders({ 'Content-Type': 'application/json', ...(token ? { Authorization: `Bearer ${token}` } : {}) });
  }

  loadAll(entiteId: number, etablissementLabel?: string): Observable<OutilSuiviData> {
    return this.http.get<OutilSuiviData>(ApiEndpoints.OutilSuivi.getByEntite(entiteId), { headers: this.headers() });
  }

  getAllEntites(): Observable<EntiteNomId[]> {
    return this.http.get<EntiteNomId[]>(ApiEndpoints.Utilisateur.getAllEntiteNomId(), { headers: this.headers() });
  }
}
