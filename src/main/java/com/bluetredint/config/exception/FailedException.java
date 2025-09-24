/**
 * 
 */
package com.bluetredint.config.exception;
import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jess.B
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FailedException extends ApplicationException {

	private static final long serialVersionUID = -3341555776124795132L;

	public FailedException(String message) {
		super(HttpStatus.CONFLICT.value(), message);
	}
}
