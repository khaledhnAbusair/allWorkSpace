package suncertify.db.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import suncertify.db.DBMain;
import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;

/**
 * The skeleton which wraps instance of {@link DBMain} and passes remote calls
 * to it.
 * 
 * @author Mohammad S. Abdellatif
 * 
 */
class RemoteDBMainSkeleton implements RemoteDBMain, RemoteServiceObject {

	/**
	 * Wrapped DBMain.
	 */
	private DBMain dbMain;

	/**
	 * Construct a new skeleton to wrap <code>dbMain</code> to delegates remote
	 * calls to it.
	 * 
	 * @param dbMain
	 *            DBMain to wrap.
	 */
	RemoteDBMainSkeleton(DBMain dbMain) {
		this.dbMain = dbMain;
	}

	/*
     *
     */
	@Override
	public int create(String[] data) throws DuplicateKeyException {
		return dbMain.create(data);
	}

	/*
     * 
     */
	@Override
	public void delete(int recNo) throws RecordNotFoundException {
		dbMain.delete(recNo);
	}

	/*
     * 
     */
	@Override
	public int[] find(String[] criteria) throws RecordNotFoundException {
		return dbMain.find(criteria);
	}

	/*
     * 
     */
	@Override
	public boolean isLocked(int recNo) throws RecordNotFoundException {
		return dbMain.isLocked(recNo);
	}

	/*
     * 
     */
	@Override
	public void lock(int recNo) throws RecordNotFoundException {
		dbMain.lock(recNo);
	}

	/*
     * 
     */
	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		return dbMain.read(recNo);
	}

	/*
     * 
     */
	@Override
	public void unlock(int recNo) throws RecordNotFoundException {
		dbMain.unlock(recNo);
	}

	/*
     * 
     */
	@Override
	public void update(int recNo, String[] data) throws RecordNotFoundException {
		dbMain.update(recNo, data);
	}

	/*
     * 
     * 
     */
	@Override
	public void export(int port) throws RemoteException {
		UnicastRemoteObject.exportObject(this, port);
	}

	/*
     * 
     */
	@Override
	public void unexport() throws RemoteException {
		UnicastRemoteObject.unexportObject(this, true);
	}
}
