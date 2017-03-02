package suncertify.model;

/**
 * An indicator for a failure in internal process for a {@link ContractorModel}.
 * <p>
 * When thrown, it means there is a problem while persisting or retrieving
 * contractors records from persistence storage wrapped by an instance of
 * {@link ContractorModel}.
 * 
 * @author Mohammad S. Abdellatif
 * @see ContractorModel
 */
public class ModelException extends Exception {

	/**
	 * Construct an instance with failure description <code>message</code>.
	 * 
	 * @param message
	 *            failure description.
	 */
	public ModelException(String message) {
		super(message);
	}

	/**
	 * Construct an instance caused by <code>cause</code> with failure
	 * description <code>message</code>.
	 * 
	 * @param message
	 *            failure description.
	 * @param cause
	 *            cause of failure.
	 */
	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct an instance caused by <code>cause</code>.
	 * 
	 * @param cause
	 *            cause of failure.
	 */
	public ModelException(Throwable cause) {
		super(cause);
	}
}
