package com.bluetrident.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bluetrident.config.exception.ApplicationException;

@ControllerAdvice

public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ApplicationResponse<?>> handleException(ApplicationException e, WebRequest wr) {
		return new ResponseEntity<ApplicationResponse<?>>(
				new ApplicationResponse<>(ExceptionConstants.ERROR, String.valueOf(e.getCode()), e.getMessage()),
				HttpStatus.valueOf(e.getCode()));
	}
}
