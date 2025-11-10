package com.insurancesystem.exception;
/**
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description This class handles Null Pointer Exception.
 */
public class RoleException {
	private final String message;
	
	public RoleException(String message) {
		super();
		this.message=message;
		
	}
	public String getMessage() {
		return message;
	}

}
