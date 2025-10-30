package tcucl.back_tcucl.exceptionPersonnalisee;

import static tcucl.back_tcucl.Constante.ERREUR_ENTITE_EXISTE_DEJA;

public class EntiteDejaExistantAvecNomTypeException extends RuntimeException {
    public EntiteDejaExistantAvecNomTypeException(String entiteNom, String entiteType) {
        super(ERREUR_ENTITE_EXISTE_DEJA + "  " + entiteNom + "  " + entiteType);
    }
}
