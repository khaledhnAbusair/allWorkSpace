package exceptions;

public class CategoryReferencedException extends CategorySuperException {
	private static final long serialVersionUID = 1L;

	public CategoryReferencedException() {
		super();
	}

	public CategoryReferencedException(String message) {
		super(message);
	}

	public CategoryReferencedException(Throwable cause) {
		super(cause);
	}

	public CategoryReferencedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryReferencedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
