package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_PASSWORD_POLICY;

public class PasswordPolicyException extends RuntimeException {

    public PasswordPolicyException() {
        super(ERREUR_PASSWORD_POLICY);
    }
}
