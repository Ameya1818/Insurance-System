package com.insurancesystem.exception;
/*
**
* @author Nikita Mahajan
* @since 2025-10-27
* @description This class handles Null Pointer Exception.
*/
public class UserIdException {
	
	private final String message;

	public UserIdException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
