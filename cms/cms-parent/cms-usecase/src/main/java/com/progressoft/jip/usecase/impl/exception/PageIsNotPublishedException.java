package com.progressoft.jip.usecase.impl.exception;

public class PageIsNotPublishedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PageIsNotPublishedException() {
		super();
	}

	public PageIsNotPublishedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PageIsNotPublishedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageIsNotPublishedException(String message) {
		super(message);
	}

	public PageIsNotPublishedException(Throwable cause) {
		super(cause);
	}

}
