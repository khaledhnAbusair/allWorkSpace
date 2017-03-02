package com.progressoft.jip.usecase.impl.exception;

public class UserAccountIsDisabledException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserAccountIsDisabledException() {
		super();
	}

	public UserAccountIsDisabledException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAccountIsDisabledException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAccountIsDisabledException(String message) {
		super(message);
	}

	public UserAccountIsDisabledException(Throwable cause) {
		super(cause);
	}

}
