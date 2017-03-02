package com.progressoft.jip.usecase.impl.exception;

public class ConfirmPasswordDoesNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfirmPasswordDoesNotMatchException() {
		super();
	}

	public ConfirmPasswordDoesNotMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConfirmPasswordDoesNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfirmPasswordDoesNotMatchException(String message) {
		super(message);
	}

	public ConfirmPasswordDoesNotMatchException(Throwable cause) {
		super(cause);
	}

}
