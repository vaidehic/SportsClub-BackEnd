package com.cybage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception class for EnrollmentService
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnrollmentServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EnrollmentServiceException(String message) {
		super(message);
	}
}
