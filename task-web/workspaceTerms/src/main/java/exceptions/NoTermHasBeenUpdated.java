package exceptions;

public class NoTermHasBeenUpdated extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoTermHasBeenUpdated() {
		super();
	}

	public NoTermHasBeenUpdated(String message) {
		super(message);
	}

	public NoTermHasBeenUpdated(Throwable cause) {
		super(cause);
	}

	public NoTermHasBeenUpdated(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTermHasBeenUpdated(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
