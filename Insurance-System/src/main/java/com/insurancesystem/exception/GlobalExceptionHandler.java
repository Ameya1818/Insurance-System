package com.insurancesystem.exception;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description This class handles Exceptions(Globally).
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description This class handle the Exception globally.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(value= {RoleNotFoundException.class})
	public ResponseEntity<Object> roleHandler(RoleNotFoundException roleNotFoundException){
		RoleException roleException= new RoleException(roleNotFoundException.getMessage());
		return new ResponseEntity<>(roleException,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value= {UserIdNotFoundException.class})
	public ResponseEntity<Object> userIdHandler(UserIdNotFoundException userNotfoundException){
		UserIdException userIdException= new UserIdException(userNotfoundException.getMessage());
		return new ResponseEntity<>(userIdException,HttpStatus.NOT_FOUND);
		
	}
	//We use this to handle validation errors in a clean, user-friendly way when using @Valid in Spring Boot.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error ->
	        errors.put(error.getField(), error.getDefaultMessage())
	    );

	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
=======

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


>>>>>>> aeb0ebb9f66718fd665201b34385c680e03dece0

}
