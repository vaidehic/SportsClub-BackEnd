package com.cybage.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

//custom exception class for EnrollmentService
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SportsServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SportsServiceException(String message) {
		super(message);
	}
}
