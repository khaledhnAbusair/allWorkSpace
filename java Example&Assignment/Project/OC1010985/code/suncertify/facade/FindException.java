package suncertify.facade;

/**
 * Thrown to indicate a failure while finding contractors in a persistence
 * storage.
 * 
 * @author Mohammad S. Abdellatif
 */
public class FindException extends Exception {

	/**
	 * Construct a new exception with description.
	 * 
	 * @param message
	 *            description of failure.
	 */
	public FindException(String message) {
		super(message);
	}

	/**
	 * Construct a new exception with cause and description.
	 * 
	 * @param message
	 *            description of failure.
	 * @param cause
	 *            cause of failure.
	 */
	public FindException(String message, Throwable cause) {
		super(message, cause);
	}
}
