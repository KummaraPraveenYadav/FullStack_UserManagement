package com.example.simple.globalexecptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> notFound(UserNotFoundException userNotFoundException){
		System.out.println("error");
		ErrorMessage errorMessage = new ErrorMessage(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST.name(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
}
