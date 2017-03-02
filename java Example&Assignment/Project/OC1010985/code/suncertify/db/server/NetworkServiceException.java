package suncertify.db.server;

/**
 * Thrown to indicate a failure in network service.
 * 
 * @author Mohammad S. Abdellatif
 * @see NetworkService
 */
public class NetworkServiceException extends Exception {

	/**
	 * Construct an instance with failure description <code>message</code> and
	 * cause of failure <code>cause</code>.
	 * 
	 * @param message
	 *            failure description.
	 * @param cause
	 *            cause of failure.
	 */
	public NetworkServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct an instance with failure description <code>message</code>.
	 * 
	 * @param message
	 *            failure description.
	 */
	public NetworkServiceException(String message) {
		super(message);
	}
}
