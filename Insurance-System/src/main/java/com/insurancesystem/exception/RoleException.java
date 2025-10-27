package com.insurancesystem.exception;

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
