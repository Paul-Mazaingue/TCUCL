package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_RESET_PASSWORD_RATE_LIMIT;

public class ResetPasswordRateLimitException extends RuntimeException {

    public ResetPasswordRateLimitException() {
        super(ERREUR_RESET_PASSWORD_RATE_LIMIT);
    }
}
