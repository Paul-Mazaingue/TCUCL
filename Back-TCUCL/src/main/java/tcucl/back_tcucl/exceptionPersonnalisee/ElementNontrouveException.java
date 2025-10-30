package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_NON_TROUVE_ID;

public class ElementNontrouveException extends NonTrouveGeneralCustomException {

    public ElementNontrouveException(String element,Long elementId) {
        super(element + ERREUR_NON_TROUVE_ID + elementId);
    }
}
