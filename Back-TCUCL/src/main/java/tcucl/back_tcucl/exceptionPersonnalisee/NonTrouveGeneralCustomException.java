package tcucl.back_tcucl.exceptionPersonnalisee;

import jakarta.persistence.EntityNotFoundException;

public class NonTrouveGeneralCustomException extends EntityNotFoundException {

    public NonTrouveGeneralCustomException(String message) {
        super(message);
    }
}
