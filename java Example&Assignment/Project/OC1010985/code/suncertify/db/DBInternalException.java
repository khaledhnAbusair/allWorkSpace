package suncertify.db;

/**
 * Signal an internal failure occurred in a database engine.
 * 
 * <p>
 * This is an indicator for a serious problem occurred in an engine or one of
 * its components.
 * 
 * @author Mohammad S. Abdellatif
 */
public class DBInternalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construct an internal failure caused by <code>cause</code>.
	 * 
	 * @param cause
	 *            cause of failure.
	 */
	public DBInternalException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct an internal failure with description <code>message</code>.
	 * 
	 * @param message
	 *            description of failure.
	 */
	public DBInternalException(String message) {
		super(message);
	}
}
