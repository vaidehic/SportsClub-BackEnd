package com.cybage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	 
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	
	@ExceptionHandler(ResourceNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(ResourceNotFoundException resource, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(resource.getMessage());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }
	@ExceptionHandler(EnrollmentServiceException.class)
	  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(EnrollmentServiceException resource, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(resource.getMessage());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }
}
