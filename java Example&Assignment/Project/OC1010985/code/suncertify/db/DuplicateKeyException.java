package suncertify.db;

/**
 * Thrown to indicate a duplicate key constraint has been broken while inserting
 * a record in database file.
 * 
 * @author Mohammad S. Abdellatif
 */
public class DuplicateKeyException extends Exception {

	/**
	 * Construct a duplicate key exception;
	 */
	public DuplicateKeyException() {
	}

	/**
	 * Construct a duplicate key exception with description <code>message</code>
	 * .
	 * 
	 * @param message
	 *            error description.
	 */
	public DuplicateKeyException(String message) {
		super(message);
	}
}
