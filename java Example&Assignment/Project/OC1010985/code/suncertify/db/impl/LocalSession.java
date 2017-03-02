/**
 * 
 */
package suncertify.db.impl;

import suncertify.db.DBMain;
import suncertify.db.Engine;
import suncertify.db.Session;

/**
 * A {@link Session} implementation for a local session for a local database
 * file engine.
 * <p>
 * Instances are created by {@link LocalEngine}.
 * 
 * @author Mohammad S. Abdellatif
 * @see LocalEngine
 * @see RecordsHandlerDelegate
 * @see RecordsLockHandlerDelegate
 */
class LocalSession implements Session {
	/*
	 * This class also contains components related to a session even if it they
	 * are not used in it. No need to implement equals or hashCode because
	 * Object implementations are suitable for this class.
	 */

	private String clientId;
	private LocalEngine engine;
	private DBMain data;
	final RecordsHandlerDelegate recordsHandlerDelegate;
	final RecordsLockHandlerDelegate recordsLockHandlerDelegate;

	/**
	 * Construct a new session with id <code>clientId</code>.
	 * 
	 * @param clientId
	 *            unique client id.
	 * @param engine
	 *            local engine this session is related to.
	 * @param data
	 *            the unique data access for this session.
	 * @param recordsHandlerDelegate
	 *            records handler delegate.
	 * @param lockHandlerDelegate
	 *            lock handler delegates.
	 */
	LocalSession(String clientId, LocalEngine engine, DBMain data,
			RecordsHandlerDelegate recordsHandlerDelegate,
			RecordsLockHandlerDelegate lockHandlerDelegate) {
		this.clientId = clientId;
		this.engine = engine;
		this.data = data;
		this.recordsHandlerDelegate = recordsHandlerDelegate;
		this.recordsLockHandlerDelegate = lockHandlerDelegate;
	}

	/*
     * 
     */
	@Override
	public Engine getEngine() {
		return engine;
	}

	/*
     *
     */
	@Override
	public void discard() {
		engine.discardSession(clientId);
	}

	/*
     * 
     */
	@Override
	public boolean isValid() {
		return engine.isValidSession(clientId);
	}

	/*
     *
     */
	@Override
	public DBMain getDataAccess() {
		if (!isValid()) {
			throw new IllegalStateException("Session is invalid");
		}
		return data;
	}

	/*
     *
     */
	@Override
	public String getId() {
		return clientId;
	}

}
