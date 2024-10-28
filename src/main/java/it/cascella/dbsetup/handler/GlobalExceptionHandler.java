package it.cascella.dbsetup.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Questa classe gestisce le eccezioni globali, la Annotazione @RestControllerAdvice
// indica che questa classe è un componente che può gestire le eccezioni in tutti i controller
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Gestione personalizzata delle eccezioni di validazione
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // L'annotazione @ExceptionHandler indica che questo metodo
    //gestirà le eccezioni di tipo MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    // Puoi aggiungere altri metodi per gestire altre eccezioni qui
}
