package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_MAUVAIS_ANCIEN_MDP;

public class MauvaisAncienMdpException extends RuntimeException {

    public MauvaisAncienMdpException() {
        super(ERREUR_MAUVAIS_ANCIEN_MDP);

    }
}
