import {Injectable, signal} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError, map } from 'rxjs/operators';
import {ApiEndpoints} from './api-endpoints';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAuthenticated = signal(false);
  // Aligner le type sur le UtilisateurDto renvoyé par le backend
  userInfo = signal<{
    id: number;
    nom: string;
    prenom: string;
    email: string;
    estAdmin: boolean;
    superAdmin: boolean;
    entiteNom: string;
    entiteId: number;
  } | null>(null);

  constructor(private router: Router, private http: HttpClient) {}

  login(email: string, password: string): Observable<boolean> {
    const loginData = { email: email, mdp: password };

    return this.http
      .post<{ jeton: string; user: {
        id: number;
        nom: string;
        prenom: string;
        email: string;
        estAdmin: boolean;
        superAdmin: boolean;
        entiteNom: string;
        entiteId: number;
      } }>(
        ApiEndpoints.auth.connexion(),
        loginData
      )
      .pipe(
        tap(response => {
          localStorage.setItem('auth_token', response.jeton);
          this.userInfo.set(response.user);
          this.isAuthenticated.set(true);
        }),
        map(() => true),
        catchError(error => {
          console.error('Erreur login', error);
          this.isAuthenticated.set(false);
          return of(false);
        })
      );
  }

  logout(): void {
    this.isAuthenticated.set(false);
    this.userInfo.set(null);
    localStorage.removeItem('auth_token');
    this.router.navigate(['/login']);
  }

  requestPasswordReset(email: string): Observable<boolean> {
    return this.http
      .post(ApiEndpoints.auth.forgotPassword(), { email }, { responseType: 'text' })
      .pipe(
        map(() => true)
      );
  }

  confirmPasswordReset(token: string, newPassword: string): Observable<boolean> {
    return this.http
      .post(ApiEndpoints.auth.resetPassword(), { token, newPassword }, { responseType: 'text' })
      .pipe(
        map(() => true)
      );
  }


  getUserInfo(): any {
    return this.userInfo;
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }
}
