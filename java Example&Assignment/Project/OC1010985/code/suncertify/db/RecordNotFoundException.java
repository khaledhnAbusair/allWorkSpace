package suncertify.db;

/**
 * An indicator if a record to be read, updated, or deleted is not found in file
 * or already deleted.
 * 
 * @see DBMain
 * @author Mohammad S. Abdellatif
 */
public class RecordNotFoundException extends Exception {

	/**
	 * Constructs an instance which represents that a record to be read, updated
	 * or deleted is not found or deleted.
	 */
	public RecordNotFoundException() {
	}

	/**
	 * Constructs an instance with a description if the record to be read,
	 * updated, or deleted is not found or deleted.
	 * 
	 * @param message
	 *            description why record is not found.
	 */
	public RecordNotFoundException(String message) {
		super(message);
	}
}
