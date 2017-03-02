/**
 * 
 */
package suncertify.db;

/**
 * Define an interface for database file records create, read, lock, update and
 * delete operations.
 * <p>
 * The implementation must assure that updates and deletes operations can only
 * occur on locked record.
 * 
 * <p>
 * For update operation(s), the caller must lock the record by calling
 * {@link #lock(int)} then unlock it after finishing the update operation(s) by
 * calling {@link #unlock(int)}, but for delete, the caller only has to call
 * {@link #lock(int)} before calling {@link #delete(int)}, no need to unlock the
 * record after delete because it does not exist any more.<br>
 * Updating for primary keys field is prohibited,
 * {@link #update(int, java.lang.String[])} only modifies non primary keys
 * fields, to update a primary key, the record must be deleted then reinserted
 * again using new unique record number.
 * 
 * <p>
 * An instance of this interface can be obtained from
 * {@link Session#getDataAccess()}. {@link Data} is a suitable implementation of
 * this interface which can be used by many database file engine providers.
 * 
 * @author Mohammad S. Abdellatif
 * @see Session
 * @see Data
 * 
 */
public interface DBMain {

	/**
	 * Reads a record from the file. Returns an array where each element is a
	 * record value.
	 * <p>
	 * 
	 * @param recNo
	 *            record number to be read.
	 * @return the record fields values.
	 * @throws RecordNotFoundException
	 *             when record with record number <code>recNo</code> is not
	 *             found in file or is marked as deleted.
	 */
	public String[] read(int recNo) throws RecordNotFoundException;

	/**
	 * Modifies non primary key field(s) of a record.
	 * <p>
	 * The new value for field <code>n</code> appears in <code>data[n]</code>.
	 * Record will be updated if and only if the record was locked by the
	 * client. If field <code>data[n]</code> contents length is larger than the
	 * expected field size it will be trimmed, if it is smaller it will be
	 * padded with spaces at the rear, <code>null</code> field value will be
	 * treated the same as empty string.<br>
	 * Updating for record primary key field(s) is prohibited, an
	 * {@link IllegalStateException} will be thrown if primary key field(s)
	 * passed through <code>data</code> are differ from current value(s) in
	 * database file.<br>
	 * An <code>IllegalArgumentException</code> will be thrown to indicate on of
	 * the following:
	 * <ul>
	 * <li>Number of fields passed does not match the exact number of fields in
	 * database</li>
	 * <li>One of the fields value is larger than expected size.</li>
	 * <li>One of the primary keys fields or non-null field passed value is
	 * <code>null</code> or empty.</li>
	 * </ul>
	 * 
	 * @param recNo
	 *            record number to be updated.
	 * @param data
	 *            the new values of record fields.
	 * @throws RecordNotFoundException
	 *             when record with record number <code>recNo</code> is not
	 *             found in file, marked as deleted or is not locked.
	 */
	public void update(int recNo, String[] data) throws RecordNotFoundException;

	/**
	 * Deletes a record, making the record number and associated disk storage
	 * available for reuse or just mark the record status as delete.
	 * <p>
	 * The record will be deleted if and only if the record was locked by the
	 * client. After a successful call to this method there is no need to unlock
	 * the record by calling {@link #unlock(int)} because it does not exit any
	 * more.
	 * 
	 * @param recNo
	 *            record number to be deleted.
	 * @throws RecordNotFoundException
	 *             when record with record number <code>recNo</code> is not
	 *             found in file or is marked as deleted or record is not locked
	 *             by the client.
	 */
	public void delete(int recNo) throws RecordNotFoundException;

	/**
	 * Returns an array of record numbers that match the specified criteria,
	 * criteria matching is case insensitive.
	 * <p>
	 * Field <code>n</code> in the database file is described by
	 * <code>criteria[n]</code>. A <code>null</code> or empty value in
	 * <code>criteria[n]</code> matches any field value. A non-null value in
	 * <code>criteria[n]</code> matches any field value that begins with
	 * <code>criteria[n]</code>. (For example, "Fred" matches "Fred" or
	 * "freddy".)<br>
	 * It throws <code>IllegalArgumentException</code> is fields count in passed
	 * criteria does not match fields number in database.
	 * 
	 * @param criteria
	 *            criteria which records will be matched with.
	 * @return an array of record numbers that match criteria specified in
	 *         <code>criteria</code> array.
	 * @throws RecordNotFoundException
	 *             when no records match criteria.
	 */
	public int[] find(String[] criteria) throws RecordNotFoundException;

	/**
	 * Creates a new record in the database (possibly reusing a deleted entry).
	 * Inserts the given dbMain, and returns the record number of the new
	 * record.
	 * <p>
	 * if field <code>data[n]</code> is smaller than expected size it will be
	 * padded with spaces.<code>null</code> field value will be treated the same
	 * as empty string. <br>
	 * It throws <code>IellgalArgumentException</code> to indicate one of the
	 * following:
	 * <ul>
	 * <li>Empty or <code>null<code> values in primary key(s).</li>
	 * <li>Number of fields passed does not match the exact number of fields in
	 * database</li>
	 * <li>One of the fields value is larger than expected size.</li>
	 * </ul>
	 * 
	 * @param data
	 *            the new record fields to be created.
	 * @return the record number of the new record.
	 * @throws DuplicateKeyException
	 *             when new record key field(s) have duplicated values in file.
	 */
	public int create(String[] data) throws DuplicateKeyException;

	/**
	 * Locks a record so that it can only be updated or deleted by this client.
	 * <p>
	 * If the specified record is already locked, the current thread gives up
	 * the CPU and consumes no CPU cycles until the record is unlocked.
	 * 
	 * @param recNo
	 *            record number to be locked.
	 * @throws RecordNotFoundException
	 *             when record to be locked is not found in database file.
	 */
	public void lock(int recNo) throws RecordNotFoundException;

	/**
	 * Releases the lock on a record.
	 * <p>
	 * Throws <code>IllegalStateException</code> is record is not locked by
	 * caller.
	 * 
	 * @param recNo
	 *            record number for the record to be unlocked.
	 * @throws RecordNotFoundException
	 *             when record to be unlocked is not found in database file.
	 */
	public void unlock(int recNo) throws RecordNotFoundException;

	/**
	 * Determines if a record is currently locked.
	 * <p>
	 * Returns <code>true</code> if the record is locked, <code>false</code>
	 * otherwise
	 * 
	 * @param recNo
	 *            record number for the record which its lock to be checked.
	 * @return true if record is locked, false otherwise.
	 * @throws RecordNotFoundException
	 *             when record which its lock status to be determine is not
	 *             found.
	 */
	public boolean isLocked(int recNo) throws RecordNotFoundException;
}
