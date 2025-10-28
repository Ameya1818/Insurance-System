package com.insurancesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { DuplicateEmailException.class })
    public ResponseEntity<Object> handleException(DuplicateEmailException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), ex.getCause(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
