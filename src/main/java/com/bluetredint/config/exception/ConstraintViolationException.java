package com.bluetredint.config.exception;
import org.springframework.http.HttpStatus;

/**
 * 
 * @author Prasanna.M
 *
 */
public class ConstraintViolationException extends ApplicationException {

	private static final long serialVersionUID = -7264851570856163536L;

	public ConstraintViolationException(String message) {
		super(HttpStatus.CONFLICT.value(), message);
	}

}
