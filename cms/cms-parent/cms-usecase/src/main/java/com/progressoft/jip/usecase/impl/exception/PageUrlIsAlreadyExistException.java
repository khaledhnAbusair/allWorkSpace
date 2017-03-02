package com.progressoft.jip.usecase.impl.exception;

public class PageUrlIsAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PageUrlIsAlreadyExistException() {
		super();
	}

	public PageUrlIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PageUrlIsAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageUrlIsAlreadyExistException(String message) {
		super(message);
	}

	public PageUrlIsAlreadyExistException(Throwable cause) {
		super(cause);
	}

	
}
