/**
 * 
 */
package suncertify.db.impl;

import java.rmi.RemoteException;

import suncertify.db.DBInternalException;
import suncertify.db.DBMain;
import suncertify.db.Engine;
import suncertify.db.Session;
import suncertify.db.server.rmi.RemoteSession;

/**
 * A <code>Session</code> implementation which wraps a stub of type
 * {@link RemoteSession}.
 * <p>
 * Instanes of this class are created by {@link RMIEngine}.<br>
 * All <code>java.rmi.RemoteException</code> thrown by wrapped stub are rethrown
 * as {@link DBInternalException}.
 * 
 * @author Mohammad S. Adelltif
 * @see RemoteSession
 */
class RMISession implements Session {

	private final RemoteSession session;
	private final RMIEngine engineProxy;
	private RMIDBMain delegate;

	/**
	 * Construct new instance constructed by engine <code>engineProxy</code> and
	 * wraps stub <code>session</code>.
	 * 
	 * @param engineProxy
	 *            engine which this session is related to.
	 * @param remoteSession
	 *            stub to wrap and delegate calls to.
	 */
	RMISession(RMIEngine engineProxy, RemoteSession remoteSession) {
		this.session = remoteSession;
		this.engineProxy = engineProxy;
	}

	/*
     *
     */
	@Override
	public Engine getEngine() {
		return engineProxy;
	}

	/*
     *
     */
	@Override
	public void discard() {
		try {
			session.discard();
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/*
     *
     */
	@Override
	public boolean isValid() {
		try {
			return session.isValid();
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/*
     *
     */
	@Override
	public DBMain getDataAccess() {
		if (!isValid()) {
			throw new IllegalStateException("Session is invalid");
		}
		try {
			if (delegate == null) {
				delegate = new RMIDBMain(session.getDataAccess());
			}
			return delegate;
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/*
     *
     */
	@Override
	public String getId() {
		try {
			return session.getId();
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}
}
