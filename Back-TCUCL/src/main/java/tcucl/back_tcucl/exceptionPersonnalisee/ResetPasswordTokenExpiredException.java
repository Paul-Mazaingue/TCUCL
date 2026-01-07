package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_RESET_TOKEN_EXPIRE;

public class ResetPasswordTokenExpiredException extends RuntimeException {

    public ResetPasswordTokenExpiredException() {
        super(ERREUR_RESET_TOKEN_EXPIRE);
    }
}
