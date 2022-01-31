package com.category.test.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @apiNote Exception Controller
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentException(Exception ex) {
		System.err.println("IllegalArgumentException");
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
	public ResponseEntity<?> EmptyResultDataAccessException(Exception ex) {
		System.err.println("EmptyResultDataAccessException");
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> NullPointerException(Exception ex) {
		System.err.println("NullPointerException");
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
