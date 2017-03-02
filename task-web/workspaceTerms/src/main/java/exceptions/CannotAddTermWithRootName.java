package exceptions;

public class CannotAddTermWithRootName extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CannotAddTermWithRootName() {
		super();
	}

	public CannotAddTermWithRootName(String message) {
		super(message);
	}

	public CannotAddTermWithRootName(Throwable cause) {
		super(cause);
	}

	public CannotAddTermWithRootName(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotAddTermWithRootName(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
