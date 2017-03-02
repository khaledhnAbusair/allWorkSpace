package com.progressoft.jip.parsing.exception;

public class TerminateConnectionParsingException extends RuntimeException {

	public TerminateConnectionParsingException() {

	}

	public TerminateConnectionParsingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TerminateConnectionParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public TerminateConnectionParsingException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
