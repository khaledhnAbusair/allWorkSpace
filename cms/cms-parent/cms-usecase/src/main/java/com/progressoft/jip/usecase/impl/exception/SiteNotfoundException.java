package com.progressoft.jip.usecase.impl.exception;

public class SiteNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SiteNotfoundException() {
		super();
	}

	public SiteNotfoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SiteNotfoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiteNotfoundException(String message) {
		super(message);
	}

	public SiteNotfoundException(Throwable cause) {
		super(cause);
	}

}
