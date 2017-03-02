package suncertify.model;

/**
 * Thrown to indicate a contractor record not found in persistence storage or it
 * was deleted.
 * <p>
 * 
 * @author Mohammad S. Abdellatif
 */
public class ContractorNotFoundException extends ModelException {

	/**
	 * Construct an instance with error description <code>message</code>
	 * 
	 * @param message
	 *            cause of failure.
	 */
	public ContractorNotFoundException(String message) {
		super(message);
	}

	/**
	 * Construct an instance caused by <code>cause</code>.
	 * 
	 * @param cause
	 *            cause of failure.
	 */
	public ContractorNotFoundException(Throwable cause) {
		super(cause);
	}
}
