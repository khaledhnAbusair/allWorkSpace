package exceptions;

public class TermCategoryIsAlreadyExistException extends CategorySuperException {
	private static final long serialVersionUID = 1L;

	public TermCategoryIsAlreadyExistException() {
		super();
	}

	public TermCategoryIsAlreadyExistException(String message) {
		super(message);
	}

	public TermCategoryIsAlreadyExistException(Throwable cause) {
		super(cause);
	}

	public TermCategoryIsAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public TermCategoryIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
