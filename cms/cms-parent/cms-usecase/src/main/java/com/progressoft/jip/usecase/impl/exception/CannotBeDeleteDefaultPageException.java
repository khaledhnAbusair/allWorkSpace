package com.progressoft.jip.usecase.impl.exception;

public class CannotBeDeleteDefaultPageException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CannotBeDeleteDefaultPageException() {
		super();
	}

	public CannotBeDeleteDefaultPageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CannotBeDeleteDefaultPageException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotBeDeleteDefaultPageException(String message) {
		super(message);
	}

	public CannotBeDeleteDefaultPageException(Throwable cause) {
		super(cause);
	}

}
