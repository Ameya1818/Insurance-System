package com.insurancesystem.exception;
/**
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description This class handles Custom Exception.
 */
public class RoleNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(String message) {
		super(message);
	}
	
}
