package ws_work.api.infra.errorsHandlers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());

    }

    @ExceptionHandler(MissingIdException.class)
    public ResponseEntity handleMissingIdException(MissingIdException ex) {
        return ResponseEntity.badRequest().body(new DataErrorValidation("Id", "não deve ser nulo"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String message = String.format("O valor '%s' não é válido para o parâmetro '%s'. Tipo esperado: '%s'.",
                exception.getValue(), exception.getName(), exception.getRequiredType().getSimpleName());
        return ResponseEntity.badRequest().body(new DataErrorValidation(exception.getName(), message));
    }

    public static class MissingIdException extends RuntimeException {
        public MissingIdException() {
        }
    }


    private record DataErrorValidation(String campo, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
