package tech.wellyngton.portfolio_api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExpetionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmailNotConfirmedExeption.class)
    public ResponseEntity emailNotConfirmed() {
        return ResponseEntity.unprocessableEntity().body("email_not_confirmed");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentInvalid(MethodArgumentNotValidException ex) {
        var fields = ex.getFieldErrors().stream().map(MethodValidationArgumentsDTO::new).toList();
        return ResponseEntity.badRequest().body(fields);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity duplicatedEntity() {
        return ResponseEntity.badRequest().body("duplicated_entity");
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity emailAlreadyRegistered() {
        return ResponseEntity.badRequest().body("email_already_registered");
    }

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity invalidCode() {
        return ResponseEntity.status(405).body("invalid_code");
    }

    @ExceptionHandler(ExpiredCodeException.class)
    public ResponseEntity expiredCode() {
        return ResponseEntity.status(405).body("code_is_expired");
    }

}
