package exceptions;

public class RecordCannotBeDeleted extends CategorySuperException {
	private static final long serialVersionUID = 1L;

	public RecordCannotBeDeleted() {
		super();
	}

	public RecordCannotBeDeleted(String message) {
		super(message);
	}

	public RecordCannotBeDeleted(Throwable cause) {
		super(cause);
	}

	public RecordCannotBeDeleted(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordCannotBeDeleted(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
