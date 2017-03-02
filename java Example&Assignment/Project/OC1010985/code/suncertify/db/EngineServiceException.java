package suncertify.db;

/**
 * Thrown to indicate a failure in {@link EngineService} operations.
 * 
 * @author Mohammad S. Abdellatif
 */
public class EngineServiceException extends Exception {

	/**
	 * Construct a new instance with error description <code>message</code>
	 * caused by <code>cause</code>.
	 * 
	 * @param message
	 *            error description.
	 * @param cause
	 *            cause of failure.
	 */
	public EngineServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct a new instance with error description <code>message</code>.
	 * 
	 * @param message
	 */
	public EngineServiceException(String message) {
		super(message);
	}
}
