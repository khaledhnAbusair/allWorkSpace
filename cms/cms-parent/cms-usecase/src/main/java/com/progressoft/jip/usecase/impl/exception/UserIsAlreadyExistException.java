package com.progressoft.jip.usecase.impl.exception;

public class UserIsAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserIsAlreadyExistException() {
		super();
	}

	public UserIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserIsAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserIsAlreadyExistException(String message) {
		super(message);
	}

	public UserIsAlreadyExistException(Throwable cause) {
		super(cause);
	}

}
