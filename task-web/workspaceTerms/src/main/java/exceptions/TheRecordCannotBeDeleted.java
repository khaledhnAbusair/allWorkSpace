package exceptions;

public class TheRecordCannotBeDeleted extends CategorySuperException {
	private static final long serialVersionUID = 1L;

	public TheRecordCannotBeDeleted() {
		super();
	}

	public TheRecordCannotBeDeleted(String message) {
		super(message);
	}

	public TheRecordCannotBeDeleted(Throwable cause) {
		super(cause);
	}

	public TheRecordCannotBeDeleted(String message, Throwable cause) {
		super(message, cause);
	}

	public TheRecordCannotBeDeleted(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
