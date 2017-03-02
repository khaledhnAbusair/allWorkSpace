package suncertify.facade;

/**
 * Thrown to indicate a failure while booking a contractor.
 * 
 * @author Mohammad S. Abdellatif
 * @see BookingSystemFacade
 */
public class BookingException extends Exception {

	/**
	 * Construct an exception with description and cause.
	 * 
	 * @param message
	 *            description of failure.
	 * @param cause
	 *            cause of failure.
	 */
	public BookingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct an exception with description.
	 * 
	 * @param message
	 *            description of failure.
	 */
	public BookingException(String message) {
		super(message);
	}
}
