package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_NON_TROUVE_ID;

public class OngletNonTrouveIdException extends NonTrouveGeneralCustomException {

    public OngletNonTrouveIdException(String onglet, Long ongletId) {
        super(onglet + ERREUR_NON_TROUVE_ID + ongletId);
    }

}
