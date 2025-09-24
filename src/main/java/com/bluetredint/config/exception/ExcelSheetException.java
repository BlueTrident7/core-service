package com.bluetredint.config.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelSheetException extends ApplicationException {

	private static final long serialVersionUID = -2118846901713997155L;

	public ExcelSheetException(String message) {
		super(HttpStatus.BAD_REQUEST.value(), message);
	}

}
