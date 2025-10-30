package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_EMAIL_OU_MDP_INVALIDE;

public class MauvaisIdentifiantsException extends RuntimeException {

    public MauvaisIdentifiantsException() {
        super(ERREUR_EMAIL_OU_MDP_INVALIDE);
    }
}
