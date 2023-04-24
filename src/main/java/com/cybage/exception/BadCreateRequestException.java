package com.cybage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadCreateRequestException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	//create exception
	public BadCreateRequestException(String message) {
		super(message);
	}
}
