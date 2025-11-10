package com.insurancesystem.exception;

/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
public class ProfileException {
	
	private final String message;

	public ProfileException(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

}
