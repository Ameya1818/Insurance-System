package com.insurancesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Handles exceptions globally for the project
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { DuplicateEmailException.class })
	public ResponseEntity<Object> handleException(DuplicateEmailException ex) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), ex.getCause(), HttpStatus.CONFLICT);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	// Handle profile not found
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ProfileException> handleProfileNotFound(ResourceNotFoundException ex) {
		ProfileException profileException = new ProfileException(ex.getMessage()); // error message
		return new ResponseEntity<>(profileException, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(value = {InvalidCredentialsException.class})
	public ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException ex) {
	    LoginException response = new LoginException(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}



}
