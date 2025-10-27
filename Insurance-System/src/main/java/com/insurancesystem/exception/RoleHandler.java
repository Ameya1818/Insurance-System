package com.insurancesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoleHandler {
	
	
	@ExceptionHandler(value= {RoleNotFoundException.class})
	public ResponseEntity<Object> roleHandler(RoleNotFoundException roleNotFoundException){
		RoleException roleException= new RoleException(roleNotFoundException.getMessage());
		return new ResponseEntity<>(roleException,HttpStatus.NOT_FOUND);
		
	}
	
	

}
