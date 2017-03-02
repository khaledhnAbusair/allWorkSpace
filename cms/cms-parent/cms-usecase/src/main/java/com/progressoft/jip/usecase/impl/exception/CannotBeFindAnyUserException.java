package com.progressoft.jip.usecase.impl.exception;

public class CannotBeFindAnyUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CannotBeFindAnyUserException() {
		super();
	}

	public CannotBeFindAnyUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CannotBeFindAnyUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotBeFindAnyUserException(String message) {
		super(message);
	}

	public CannotBeFindAnyUserException(Throwable cause) {
		super(cause);
	}

}
