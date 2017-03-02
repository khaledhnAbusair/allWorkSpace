package exceptions;

public class NoParentRootIsExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoParentRootIsExistException() {
		super();
	}

	public NoParentRootIsExistException(String message) {
		super(message);
	}

	public NoParentRootIsExistException(Throwable cause) {
		super(cause);
	}

	public NoParentRootIsExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoParentRootIsExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
