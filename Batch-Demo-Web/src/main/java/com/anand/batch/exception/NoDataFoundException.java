package com.anand.batch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NO_CONTENT, reason="No data found")
public class NoDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5332259768742716491L;
	
	public NoDataFoundException() {
		super();
	}
	public NoDataFoundException(String message) {
		super(message);
	}
	
}
