package suncertify.db.impl;

import java.io.IOException;

import suncertify.db.Data.RecordsLockHandler;
import suncertify.db.RecordNotFoundException;

/**
 * A record lock handler which passes the calls to a wrapped
 * {@link RecordsLockHandler} instance.
 * <p>
 * It is mainly used by {@link LocalEngine} to change the behavior of records
 * locking at runtime when a session is discarded.
 * 
 * @author Mohammad S. Abdellatif
 */
class RecordsLockHandlerDelegate implements RecordsLockHandler {

	private RecordsLockHandler handler;

	/**
	 * Construct a new handler which passes calls to <code>handler</code>.
	 * 
	 * @param handler
	 *            handler to pass calls to.
	 */
	public RecordsLockHandlerDelegate(RecordsLockHandler handler) {
		this.handler = handler;
	}

	/**
	 * Set the handler to be wrapped and passes called to it.
	 * 
	 * @param handler
	 *            to be wrapped handler.
	 */
	public void setHandler(RecordsLockHandler handler) {
		this.handler = handler;
	}

	/*
     *
     */
	@Override
	public void lock(int recNo) throws RecordNotFoundException, IOException {
		handler.lock(recNo);
	}

	/*
     *
     */
	@Override
	public boolean isLocked(int recNo) throws RecordNotFoundException,
			IOException {
		return handler.isLocked(recNo);
	}

	/*
     * 
     */
	@Override
	public boolean isLockedByCaller(int recNo) throws RecordNotFoundException,
			IOException {
		return handler.isLockedByCaller(recNo);
	}

	/*
     *
     */
	@Override
	public void unlock(int recNo) throws RecordNotFoundException, IOException {
		handler.unlock(recNo);
	}
}
