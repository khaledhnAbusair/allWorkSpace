package exceptions;

public class TermCategoryCannotBeenDeletedBecauseHasTermBelong extends CategorySuperException {
	private static final long serialVersionUID = 1L;

	public TermCategoryCannotBeenDeletedBecauseHasTermBelong() {
		super();
	}

	public TermCategoryCannotBeenDeletedBecauseHasTermBelong(String message) {
		super(message);
	}

	public TermCategoryCannotBeenDeletedBecauseHasTermBelong(Throwable cause) {
		super(cause);
	}

	public TermCategoryCannotBeenDeletedBecauseHasTermBelong(String message, Throwable cause) {
		super(message, cause);
	}

	public TermCategoryCannotBeenDeletedBecauseHasTermBelong(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
