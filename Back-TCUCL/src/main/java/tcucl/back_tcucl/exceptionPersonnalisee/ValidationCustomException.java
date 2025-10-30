package tcucl.back_tcucl.exceptionPersonnalisee;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationCustomException extends ValidationException {
    private final Set<ConstraintViolation<?>> violations;

    public ValidationCustomException(Set<? extends ConstraintViolation<?>> violations) {
        super(violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(" \n")));
        this.violations = new HashSet<>(violations); // copie défensive
    }

    public Set<ConstraintViolation<?>> getViolations() {
        return violations;
    }
}

