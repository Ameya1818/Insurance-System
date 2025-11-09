package com.insurancesystem.exception;
/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Custom exception for profile not found
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
