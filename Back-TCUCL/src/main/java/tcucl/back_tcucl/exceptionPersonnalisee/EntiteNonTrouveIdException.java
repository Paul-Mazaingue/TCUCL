package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_ENTITE_NON_TROUVE_ID;

public class EntiteNonTrouveIdException extends NonTrouveGeneralCustomException {
    public EntiteNonTrouveIdException(Long entiteId) {
        super(ERREUR_ENTITE_NON_TROUVE_ID + entiteId);
    }
}
