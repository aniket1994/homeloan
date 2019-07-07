package com.bank.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.app.dto.ErrorResponse;

@ControllerAdvice
public class CustomerExceptionController {
	
	 @ExceptionHandler(value = CustomerException.class)
	 public ResponseEntity<Object> exception(CustomerException exception) {
		 
		 
	      return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	   }

}
