package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_AUCUN_ETUDIANT_ENREGISTRE;

public class AucunEtudiantEnregistre extends RuntimeException {
    public AucunEtudiantEnregistre() {
        super(ERREUR_AUCUN_ETUDIANT_ENREGISTRE);
    }
}
