package com.dailycode.tutorial.error;

import com.dailycode.tutorial.entity.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DepartmentExceptions.class)
	public ResponseEntity<ErrorMessage> departmentException(DepartmentExceptions exception) {

		Integer exceptionStatus = exception.getStatusCode();

		if (exceptionStatus == 404) {
			ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
			return ResponseEntity.status(HttpStatus.CREATED).body(message);
		} else if (exceptionStatus == 409) {
			ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
		} 
		else {
			ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
		}
	}

}
