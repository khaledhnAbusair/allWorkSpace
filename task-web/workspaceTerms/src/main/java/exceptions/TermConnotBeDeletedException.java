package exceptions;

public class TermConnotBeDeletedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TermConnotBeDeletedException() {
		super();
	}

	public TermConnotBeDeletedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TermConnotBeDeletedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TermConnotBeDeletedException(String message) {
		super(message);
	}

	public TermConnotBeDeletedException(Throwable cause) {
		super(cause);
	}

}
