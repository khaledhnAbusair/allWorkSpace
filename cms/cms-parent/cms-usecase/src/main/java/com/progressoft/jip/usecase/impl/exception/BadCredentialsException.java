package com.progressoft.jip.usecase.impl.exception;

public class BadCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadCredentialsException() {
		super();
	}

	public BadCredentialsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadCredentialsException(String message) {
		super(message);
	}

	public BadCredentialsException(Throwable cause) {
		super(cause);
	}

	
	
}
