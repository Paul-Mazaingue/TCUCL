import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss'
})
export class ResetPasswordComponent implements OnInit {
  token = '';
  newPassword = '';
  confirmPassword = '';
  showNewPassword = false;
  showConfirmPassword = false;
  isLoading = false;
  isSuccess = false;
  errorMessage = '';
  tokenValid = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.token = params['token'] || '';
      if (this.token) {
        this.tokenValid = true;
      } else {
        this.errorMessage = 'Lien de réinitialisation invalide.';
      }
    });
  }

  toggleNewPasswordVisibility(): void {
    this.showNewPassword = !this.showNewPassword;
  }

  toggleConfirmPasswordVisibility(): void {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  onSubmit(): void {
    this.errorMessage = '';

    if (!this.newPassword || !this.confirmPassword) {
      this.errorMessage = 'Veuillez entrer votre nouveau mot de passe.';
      return;
    }

    if (this.newPassword !== this.confirmPassword) {
      this.errorMessage = 'Les mots de passe ne correspondent pas.';
      return;
    }

    if (this.newPassword.length < 10) {
      this.errorMessage = 'Le mot de passe doit contenir au moins 10 caractères.';
      return;
    }

    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^a-zA-Z\d]).{10,}$/;
    if (!regex.test(this.newPassword)) {
      this.errorMessage = 'Le mot de passe doit contenir une majuscule, une minuscule, un chiffre et un caractère spécial.';
      return;
    }

    this.isLoading = true;
    this.authService.confirmPasswordReset(this.token, this.newPassword).subscribe({
      next: () => {
        this.isLoading = false;
        this.isSuccess = true;
      },
      error: (error) => {
        this.isLoading = false;
        if (error.status === 429) {
          this.errorMessage = error.error || 'Trop de demandes. Réessayez plus tard.';
        } else if (error.status === 400) {
          this.errorMessage = error.error || 'Lien de réinitialisation invalide ou expiré.';
        } else {
          this.errorMessage = 'Une erreur est survenue. Veuillez réessayer.';
        }
      }
    });
  }

  backToLogin(): void {
    this.router.navigate(['/login']);
  }
}
