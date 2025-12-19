import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trajectoire } from '../models/trajectoire.model';
import { ApiEndpoints } from './api-endpoints';
import { AuthService } from './auth.service';

export interface TrajectoireResponse {
  real: Trajectoire | null;
  mock: Trajectoire | null;
  warnings?: string[];
}

@Injectable({ providedIn: 'root' })
export class TrajectoireService {
  constructor(private http: HttpClient, private auth: AuthService) {}

  private headers(): HttpHeaders {
    const token = this.auth.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      ...(token ? { Authorization: `Bearer ${token}` } : {})
    });
  }

  get(entiteId: number): Observable<TrajectoireResponse> {
    return this.http.get<TrajectoireResponse>(ApiEndpoints.Trajectoire.getByEntite(entiteId), { headers: this.headers() });
  }

  upsert(entiteId: number, payload: Trajectoire): Observable<Trajectoire> {
    return this.http.put<Trajectoire>(ApiEndpoints.Trajectoire.upsert(entiteId), payload, { headers: this.headers() });
  }

  propagateGlobal(): Observable<void> {
    return this.http.post<void>(ApiEndpoints.Trajectoire.propagateGlobal(), {}, { headers: this.headers() });
  }

  getPostesDefaults(entiteId: number): Observable<Array<{ id: string; nom: string; emissionsReference: number; reductionBasePct: number }>> {
    return this.http.get<Array<{ id: string; nom: string; emissionsReference: number; reductionBasePct: number }>>(
      ApiEndpoints.Trajectoire.getPostesDefaults(entiteId),
      { headers: this.headers() }
    );
  }
}
