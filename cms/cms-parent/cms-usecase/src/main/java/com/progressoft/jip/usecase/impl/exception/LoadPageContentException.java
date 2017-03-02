package com.progressoft.jip.usecase.impl.exception;

public class LoadPageContentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LoadPageContentException() {
		super();
	}

	public LoadPageContentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoadPageContentException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoadPageContentException(String message) {
		super(message);
	}

	public LoadPageContentException(Throwable cause) {
		super(cause);
	}

}
