package com.insurancesystem.exception;

import org.springframework.http.HttpStatus;
/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
public class LoginException {
	
	private String message;
    private HttpStatus httpStatus;

    public LoginException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
