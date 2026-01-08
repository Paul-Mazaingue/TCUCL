package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_RESET_TOKEN_INVALIDE;

public class ResetPasswordTokenInvalidException extends RuntimeException {

    public ResetPasswordTokenInvalidException() {
        super(ERREUR_RESET_TOKEN_INVALIDE);
    }
}
