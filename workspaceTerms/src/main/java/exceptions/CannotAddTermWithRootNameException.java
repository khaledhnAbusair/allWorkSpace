package exceptions;

public class CannotAddTermWithRootNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CannotAddTermWithRootNameException() {
		super();
	}

	public CannotAddTermWithRootNameException(String message) {
		super(message);
	}

	public CannotAddTermWithRootNameException(Throwable cause) {
		super(cause);
	}

	public CannotAddTermWithRootNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotAddTermWithRootNameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
