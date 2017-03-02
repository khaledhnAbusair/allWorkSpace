package suncertify.model;

/**
 * Thrown to indicate a contractor to be persisted has the same keys for a
 * contractor already exist in same persistence storage.
 * 
 * @author Mohammad S. Abdellatif
 */
public class DuplicateContractorException extends ModelException {

	/**
	 * Construct new exception caused by <code>cause</code>.
	 * 
	 * @param cause
	 *            cause of failure.
	 */
	public DuplicateContractorException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct new exception with description <code>message</code>.
	 * 
	 * @param message
	 *            description of failure.
	 */
	public DuplicateContractorException(String message) {
		super(message);
	}
}
