package com.bluetrident.config.exception;
//package com.lifetrenz.lths.core.common.app.exception;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
///**
// * 
// * @author Prasanna.M
// *
// */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//		String errorMessage = "Constraint violation occurred. ";
//
//		// Extract more details about the exception and customize the error message
//		if (ex.getCause() instanceof ConstraintViolationException) {
//			ConstraintViolationException constraintEx = (ConstraintViolationException) ex.getCause();
//			errorMessage += "Details: " + constraintEx.getMessage();
//		}
//
//		return new ResponseEntity<>(new ConstraintViolationException(errorMessage), HttpStatus.BAD_REQUEST);
//	}
//
//}
