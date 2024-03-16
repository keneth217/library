package com.samis.security.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(WrongEmail.class)
    public ResponseEntity<String> handleWrongEmail(WrongEmail ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }
}
