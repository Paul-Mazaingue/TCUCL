import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.scss'
})
export class ForgotPasswordComponent {
  email = '';
  isSubmitted = false;
  isLoading = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.authService.requestPasswordReset(this.email).subscribe({
      next: () => {
        this.isLoading = false;
        this.isSubmitted = true;
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error?.error || 'Une erreur est survenue. Veuillez réessayer dans quelques instants.';
      }
    });
  }

  backToLogin(): void {
    this.router.navigate(['/login']);
  }
}
