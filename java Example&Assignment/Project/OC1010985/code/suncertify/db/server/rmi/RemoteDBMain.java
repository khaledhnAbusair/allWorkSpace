/**
 * 
 */
package suncertify.db.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import suncertify.db.DBMain;
import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;

/**
 * A Mirror for interface {@link DBMain} to support its operation over RMI.
 * 
 * @author Mohammad S. Abdellatif
 */
public interface RemoteDBMain extends Remote {

	/**
	 * Performs {@link DBMain#read(int)} operation remotely.
	 * 
	 * @param recNo
	 *            record number to read.
	 * @return read record number.
	 * @throws RemoteException
	 *             on failure occurred in RMI environment or runtime exception
	 *             is thrown in remote application.
	 * @throws RecordNotFoundException
	 *             if record is not found or deleted.
	 */
	public String[] read(int recNo) throws RemoteException,
			RecordNotFoundException;

	/**
	 * Performs {@link DBMain#update(int, java.lang.String[])} operation
	 * remotely.
	 * 
	 * @param recNo
	 *            record number to update.
	 * @param data
	 *            new record data fields.
	 * @throws RemoteException
	 *             on failure occurred in RMI environment or runtime exception
	 *             is thrown in remote application
	 * @throws RecordNotFoundException
	 *             if record is not found or deleted.
	 */
	public void update(int recNo, String[] data) throws RemoteException,
			RecordNotFoundException;

	/**
	 * Performs {@link DBMain#delete(int)} operation remotely.
	 * 
	 * @param recNo
	 * @throws RemoteException
	 *             on failure occurred in RMI environment or runtime exception
	 *             is thrown in remote application
	 * @throws RecordNotFoundException
	 *             if record is not found or deleted.
	 */
	public void delete(int recNo) throws RemoteException,
			RecordNotFoundException;

	/**
	 * Performs {@link DBMain#find(java.lang.String[])} operation remotely.
	 * 
	 * @param criteria
	 *            criteria to match records with.
	 * @return matched record numbers.
	 * @throws RemoteException
	 *             on failure occurred in RMI environment or runtime exception
	 *             is thrown in remote application
	 * @throws RecordNotFoundException
	 *             if no records found.
	 */
	public int[] find(String[] criteria) throws RemoteException,
			RecordNotFoundException;

	/**
	 * Performs {@link DBMain#create(java.lang.String[])} operation remotely.
	 * 
	 * @param data
	 *            new record fields.
	 * @return assigned record no.
	 * @throws RemoteException
	 *             on failure occurred in RMI environment or runtime exception
	 *             is thrown in remote application
	 * @throws DuplicateKeyException
	 *             is record primary key is duplicated with an already existing
	 *             record.
	 */
	public int create(String[] data) throws RemoteException,
			DuplicateKeyException;

	/**
	 * Performs {@link DBMain#lock(int)} operation remotely.
	 * 
	 * @param recNo
	 *            record no to lock
	 * @throws RemoteException
	 *             on failure occurred in RMI environment or runtime exception
	 *             is thrown in remote application
	 * @throws RecordNotFoundException
	 *             if record is not found or deleted.
	 */
	public void lock(int recNo) throws RemoteException, RecordNotFoundException;

	/**
	 * Performs {@link DBMain#unlock(int)} operation remotely.
	 * 
	 * @param recNo
	 *            record number to unlock.
	 * @throws RemoteException
	 *             on failure occurred in RMI environment or runtime exception
	 *             is thrown in remote application
	 * @throws RecordNotFoundException
	 *             if record is not found or deleted.
	 */
	public void unlock(int recNo) throws RemoteException,
			RecordNotFoundException;

	/**
	 * Performs {@link DBMain#isLocked(int)} operation remotely.
	 * 
	 * @param recNo
	 *            record number to check if locked or not.
	 * @return <code>true</code> if locked, otherwise, <code>false</code>.
	 * @throws RemoteExceptionon
	 *             failure occurred in RMI environment or runtime exception is
	 *             thrown in remote application
	 * @throws RecordNotFoundException
	 *             if record is not found or deleted.
	 */
	public boolean isLocked(int recNo) throws RemoteException,
			RecordNotFoundException;
}
