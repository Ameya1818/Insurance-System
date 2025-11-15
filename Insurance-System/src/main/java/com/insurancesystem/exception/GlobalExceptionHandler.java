package com.insurancesystem.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles all exceptions globally.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle Role Not Found
	@ExceptionHandler(value = { RoleNotFoundException.class })
	public ResponseEntity<Object> roleHandler(RoleNotFoundException ex) {
		RoleException roleException = new RoleException(ex.getMessage());
		return new ResponseEntity<>(roleException, HttpStatus.NOT_FOUND);
	}

	// Handle User ID Not Found
	@ExceptionHandler(value = { UserIdNotFoundException.class })
	public ResponseEntity<Object> userIdHandler(UserIdNotFoundException ex) {
		UserIdException userIdException = new UserIdException(ex.getMessage());
		return new ResponseEntity<>(userIdException, HttpStatus.NOT_FOUND);
	}

	// Handle validation errors (@Valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	// Handle duplicate email
	@ExceptionHandler(value = { DuplicateEmailException.class })
	public ResponseEntity<Object> handleDuplicateEmail(DuplicateEmailException ex) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), ex.getCause(), HttpStatus.CONFLICT);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	// Handle profile/resource not found
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ProfileException> handleProfileNotFound(ResourceNotFoundException ex) {
		ProfileException profileException = new ProfileException(ex.getMessage());
		return new ResponseEntity<>(profileException, HttpStatus.NOT_FOUND);
	}

	// Handle login failures
	@ExceptionHandler(value = { InvalidCredentialsException.class })
	public ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException ex) {
		LoginException response = new LoginException(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

}
