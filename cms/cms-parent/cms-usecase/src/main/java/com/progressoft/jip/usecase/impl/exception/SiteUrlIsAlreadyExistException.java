package com.progressoft.jip.usecase.impl.exception;

public class SiteUrlIsAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SiteUrlIsAlreadyExistException() {
		super();
	}

	public SiteUrlIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SiteUrlIsAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiteUrlIsAlreadyExistException(String message) {
		super(message);
	}

	public SiteUrlIsAlreadyExistException(Throwable cause) {
		super(cause);
	}

}
