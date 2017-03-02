package suncertify.db.impl;

import java.io.IOException;

import suncertify.db.Data.RecordsLockHandler;
import suncertify.db.RecordNotFoundException;

/**
 * A {@link RecordsLockHandler} handler which passes the locking operations to
 * {@link LocalEngine} locking methods.
 * <p>
 * 
 * @author Mohammad S. Abdellatif
 * @see LocalEngine
 */
class RecordsLockHandlerImpl implements RecordsLockHandler {

	private final LocalEngine engine;
	private final String clientId;

	/**
	 * Construct a new locking handler for client accepted by local engine
	 * <code>engine</code> with id <code>clientId</code>.
	 * 
	 * @param engine
	 *            local engine for which this handler belongs to.
	 * @param clientId
	 *            client id this handler is related to.
	 */
	RecordsLockHandlerImpl(LocalEngine engine, String clientId) {
		this.engine = engine;
		this.clientId = clientId;
	}

	/*
     *
     */
	@Override
	public void lock(int recNo) throws RecordNotFoundException, IOException {
		engine.lock(recNo, clientId);
	}

	/*
     *
     */
	@Override
	public boolean isLocked(int recNo) throws RecordNotFoundException,
			IOException {
		return engine.isLocked(recNo);
	}

	/*
     * 
     */
	@Override
	public boolean isLockedByCaller(int recNo) throws RecordNotFoundException,
			IOException {
		return engine.isLockedByClient(recNo, clientId);
	}

	/*
     * 
     */
	@Override
	public void unlock(int recNo) throws RecordNotFoundException, IOException {
		engine.unlock(recNo, clientId);
	}
}
