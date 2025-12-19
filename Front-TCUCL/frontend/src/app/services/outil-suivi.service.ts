import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiEndpoints } from './api-endpoints';
import { AuthService } from './auth.service';

export interface OutilSuiviData {
  years: number[];
  objectif: (number | null)[];
  realise: (number | null)[];
  postes: string[];
  postesObjectifParAn: Record<number, (number | null)[]>;
  postesRealiseParAn: Record<number, (number | null)[]>;
  indicateursParAn: Record<number, Record<string, number | null>>;
  globalTotals: (number | null)[];
}

export interface OutilSuiviResponse {
  real: OutilSuiviData | null;
  mock: OutilSuiviData | null;
  warnings?: string[];
  issuesByYear?: Record<number, string[]>;
}

@Injectable({ providedIn: 'root' })
export class OutilSuiviService {
  constructor(private http: HttpClient, private auth: AuthService) {}

  private headers(): HttpHeaders {
    const token = this.auth.getToken();
    return new HttpHeaders({ 'Content-Type': 'application/json', ...(token ? { Authorization: `Bearer ${token}` } : {}) });
  }

  loadAll(entiteId: number, etablissementLabel?: string): Observable<OutilSuiviResponse> {
    return this.http.get<OutilSuiviResponse>(ApiEndpoints.OutilSuivi.getByEntite(entiteId), { headers: this.headers() });
  }
}
