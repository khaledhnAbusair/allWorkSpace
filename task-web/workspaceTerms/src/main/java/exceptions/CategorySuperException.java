package exceptions;

public class CategorySuperException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CategorySuperException() {
		super();
	}

	public CategorySuperException(String message) {
		super(message);
	}

	public CategorySuperException(Throwable cause) {
		super(cause);
	}

	public CategorySuperException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategorySuperException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
