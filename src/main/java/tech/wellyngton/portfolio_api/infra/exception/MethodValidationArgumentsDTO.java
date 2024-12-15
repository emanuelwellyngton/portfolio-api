package tech.wellyngton.portfolio_api.infra.exception;

import org.springframework.validation.FieldError;

public record MethodValidationArgumentsDTO(
        String field,
        String message
) {
    MethodValidationArgumentsDTO(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
