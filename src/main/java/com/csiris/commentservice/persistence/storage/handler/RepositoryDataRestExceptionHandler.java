package com.csiris.commentservice.persistence.storage.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RepositoryDataRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	private ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<String>("oopps", HttpStatus.BAD_REQUEST);
	}
}