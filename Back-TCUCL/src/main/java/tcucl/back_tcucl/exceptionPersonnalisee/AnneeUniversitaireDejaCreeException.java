package tcucl.back_tcucl.exceptionPersonnalisee;

public class AnneeUniversitaireDejaCreeException extends RuntimeException {
    public AnneeUniversitaireDejaCreeException(Integer anneeUniversitaire) {
        super("L'année universitaire " + anneeUniversitaire + " est déjà créée.");
    }
}
