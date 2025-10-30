package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_UTILISATEUR_NON_TROUVE_ID;

public class UtilisateurNonTrouveIdException extends NonTrouveGeneralCustomException {

    public UtilisateurNonTrouveIdException(Long utilisateurId) {
        super(ERREUR_UTILISATEUR_NON_TROUVE_ID + utilisateurId);
    }
}
