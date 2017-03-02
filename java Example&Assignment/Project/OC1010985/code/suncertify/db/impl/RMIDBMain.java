/**
 * 
 */
package suncertify.db.impl;

import java.rmi.RemoteException;

import suncertify.db.DBInternalException;
import suncertify.db.DBMain;
import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.server.rmi.RemoteDBMain;

/**
 * An implementation for <code>DBMain</code> which passes the calls to a stub of
 * type {@link RemoteDBMain}.
 * <p>
 * 
 * All <code>java.rmi.RemoteException</code> thrown by <code>RemoteDBMain</code>
 * methods are wrapped with {@link DBInternalException} exception, such
 * exception is thrown by RMI runtime environment to indicate a failure in RMI
 * environment or a runtime exception thrown by remote engine stubs.
 * 
 * @author Mohammad S. Abdellatif
 * @see RemoteDBMain
 */
class RMIDBMain implements DBMain {

	private RemoteDBMain remoteDbMain;

	/**
	 * 
	 * @param remoteDBMain
	 */
	RMIDBMain(RemoteDBMain remoteDBMain) {
		this.remoteDbMain = remoteDBMain;
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public int create(String[] data) throws DuplicateKeyException {
		try {
			return remoteDbMain.create(data);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public void delete(int recNo) throws RecordNotFoundException {
		try {
			remoteDbMain.delete(recNo);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public int[] find(String[] criteria) throws RecordNotFoundException {
		try {
			return remoteDbMain.find(criteria);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public boolean isLocked(int recNo) throws RecordNotFoundException {
		try {
			return remoteDbMain.isLocked(recNo);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public void lock(int recNo) throws RecordNotFoundException {
		try {
			remoteDbMain.lock(recNo);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		try {
			return remoteDbMain.read(recNo);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public void unlock(int recNo) throws RecordNotFoundException {
		try {
			remoteDbMain.unlock(recNo);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/**
	 * Delegates call to wrapped stub.
	 */
	@Override
	public void update(int recNo, String[] data) throws RecordNotFoundException {
		try {
			remoteDbMain.update(recNo, data);
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}
}
