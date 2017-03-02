package exceptions;

public class NoTermCategoryFoundException extends CategorySuperException {
	private static final long serialVersionUID = 1L;

	public NoTermCategoryFoundException() {
		super();
	}

	public NoTermCategoryFoundException(String message) {
		super(message);
	}

	public NoTermCategoryFoundException(Throwable cause) {
		super(cause);
	}

	public NoTermCategoryFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTermCategoryFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
