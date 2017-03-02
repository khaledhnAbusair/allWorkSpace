package com.progressoft.jip.deliverycontrollers.exception;

public class UnpublishedPageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnpublishedPageException() {
		super();
	}

	public UnpublishedPageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnpublishedPageException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnpublishedPageException(String message) {
		super(message);
	}

	public UnpublishedPageException(Throwable cause) {
		super(cause);
	}

}
