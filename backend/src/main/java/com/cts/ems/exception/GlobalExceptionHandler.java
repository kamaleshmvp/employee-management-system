package com.cts.ems.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
		
		return buildErrorResponse("Validation error: One or more fields contain invalid data. Please review and correct them", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
		
		return buildErrorResponse(resourceNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<Object> handleUserExistException(UserExistException userExistException) {
		
		return buildErrorResponse(userExistException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
		
		return buildErrorResponse(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
		
		Map<String, Object> response = new HashMap<>();
		response.put("Message", message);
		response.put("Status", status.value());
		return new ResponseEntity<>(response, status);
	}
}
