package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_AUCUN_SALARIE_ENREGISTRE;

public class AucunSalarieEnregistre extends RuntimeException {
    public AucunSalarieEnregistre() {
        super(ERREUR_AUCUN_SALARIE_ENREGISTRE);
    }
}
