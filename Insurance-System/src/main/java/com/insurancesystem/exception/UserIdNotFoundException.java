package com.insurancesystem.exception;
/**
 * @author Nikita Mahajan
 * @since 2025-10-27
 * @description This class handles Custom Exception.
 */
public class UserIdNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 2L;

	public UserIdNotFoundException(String message) {
		super(message);
	}

}
