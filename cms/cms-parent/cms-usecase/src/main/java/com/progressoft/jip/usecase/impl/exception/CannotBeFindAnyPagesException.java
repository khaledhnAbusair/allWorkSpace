package com.progressoft.jip.usecase.impl.exception;

public class CannotBeFindAnyPagesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CannotBeFindAnyPagesException() {
		super();
	}

	public CannotBeFindAnyPagesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CannotBeFindAnyPagesException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotBeFindAnyPagesException(String message) {
		super(message);
	}

	public CannotBeFindAnyPagesException(Throwable cause) {
		super(cause);
	}

	
}
