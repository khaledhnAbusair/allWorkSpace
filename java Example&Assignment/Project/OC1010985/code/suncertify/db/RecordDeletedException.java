package suncertify.db;

/**
 * A {@link RecordNotFoundException} exception to indicate a record is not found
 * because it is deleted.
 * 
 * @author Mohammad S. Abdellatif
 */
public class RecordDeletedException extends RecordNotFoundException {

	/**
	 * Construct a new exception.
	 */
	public RecordDeletedException() {
	}
}
