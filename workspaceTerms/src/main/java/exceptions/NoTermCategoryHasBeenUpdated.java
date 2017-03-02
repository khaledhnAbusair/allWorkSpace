package exceptions;

public class NoTermCategoryHasBeenUpdated extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoTermCategoryHasBeenUpdated() {
		super();
	}

	public NoTermCategoryHasBeenUpdated(String message) {
		super(message);
	}

	public NoTermCategoryHasBeenUpdated(Throwable cause) {
		super(cause);
	}

	public NoTermCategoryHasBeenUpdated(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTermCategoryHasBeenUpdated(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
