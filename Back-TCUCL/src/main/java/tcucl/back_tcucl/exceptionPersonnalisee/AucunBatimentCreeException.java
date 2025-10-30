package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_AUCUN_BATIMENT_CREE;

public class AucunBatimentCreeException extends RuntimeException{

    public AucunBatimentCreeException() {
        super(ERREUR_AUCUN_BATIMENT_CREE);
    }
}
