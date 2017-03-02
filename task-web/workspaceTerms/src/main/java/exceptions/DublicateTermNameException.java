package exceptions;

public class DublicateTermNameException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DublicateTermNameException() {
		super();
	}

	public DublicateTermNameException(String message) {
		super(message);
	}

	public DublicateTermNameException(Throwable cause) {
		super(cause);
	}

	public DublicateTermNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public DublicateTermNameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
