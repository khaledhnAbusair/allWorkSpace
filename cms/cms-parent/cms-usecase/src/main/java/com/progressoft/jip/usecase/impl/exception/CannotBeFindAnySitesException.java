package com.progressoft.jip.usecase.impl.exception;

public class CannotBeFindAnySitesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CannotBeFindAnySitesException() {
		super();
	}

	public CannotBeFindAnySitesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CannotBeFindAnySitesException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotBeFindAnySitesException(String message) {
		super(message);
	}

	public CannotBeFindAnySitesException(Throwable cause) {
		super(cause);
	}

}
