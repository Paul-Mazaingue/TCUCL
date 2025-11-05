import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trajectoire } from '../models/trajectoire.model';
import { ApiEndpoints } from './api-endpoints';
import { AuthService } from './auth.service';

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

  get(entiteId: number): Observable<Trajectoire> {
    return this.http.get<Trajectoire>(ApiEndpoints.Trajectoire.getByEntite(entiteId), { headers: this.headers() });
  }

  upsert(entiteId: number, payload: Trajectoire): Observable<Trajectoire> {
    return this.http.put<Trajectoire>(ApiEndpoints.Trajectoire.upsert(entiteId), payload, { headers: this.headers() });
  }
}
