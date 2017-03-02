package com.progressoft.jip.usecase.impl.exception;

public class LoadSiteContentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoadSiteContentException() {
		super();
	}

	public LoadSiteContentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoadSiteContentException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoadSiteContentException(String message) {
		super(message);
	}

	public LoadSiteContentException(Throwable cause) {
		super(cause);
	}

	
}
