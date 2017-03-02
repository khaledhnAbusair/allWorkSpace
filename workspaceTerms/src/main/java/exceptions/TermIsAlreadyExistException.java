package exceptions;

public class TermIsAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TermIsAlreadyExistException() {
		super();
	}

	public TermIsAlreadyExistException(String message) {
		super(message);
	}

	public TermIsAlreadyExistException(Throwable cause) {
		super(cause);
	}

	public TermIsAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public TermIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
