package ru.example.tild.controlleradvice;

import io.jsonwebtoken.JwtException;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.example.tild.model.error.ValidationErrorResponse;
import ru.example.tild.model.error.Violation;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ValidationHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

//    @ExceptionHandler(Exception.class)
//    //@ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<String> onDataIntegrityViolationException(
//            Exception e
//    ){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> onAuthException(
            AuthException e
    ){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> onJwtException( JwtException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

}
