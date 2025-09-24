package com.bluetredint.config.exception;
import org.springframework.http.HttpStatus;

public class NoLongerValidException extends ApplicationException {

	private static final long serialVersionUID = -3007880127519385021L;

	public NoLongerValidException(String message) {
		super(HttpStatus.GONE.value(), message);
	}

}
