/**
 * 
 */
package suncertify.db.server.rmi;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import suncertify.db.DBInternalException;
import suncertify.db.Engine;
import suncertify.db.Schema;

/**
 * A skeleton which wraps an {@link Engine} instance and passes all remote calls
 * to it.
 * 
 * @author Mohammad S. Abdellatif
 * @see Engine
 */
class RemoteEngineSkeleton implements RemoteEngine, RemoteServiceObject {

	private Engine engine;
	private List<RemoteSessionSkeleton> sessions =
			new ArrayList<RemoteSessionSkeleton>();
	private int port;

	/**
	 * Construct a new skeleton.
	 * 
	 * @param engine
	 *            the engine to which pass remote calls.
	 */
	RemoteEngineSkeleton(Engine engine) {
		this.engine = engine;
	}

	/*
     * 
     */
	@Override
	public int getDatabaseMagicCookie() throws RemoteException {
		return engine.getDatabaseMagicCookie();
	}

	/*
     *
     */
	@Override
	public Schema getDBSchema() {
		return engine.getDBSchema();
	}

	/*
     *
     */
	@Override
	public RemoteSession newSession() {
		try {
			RemoteSessionSkeleton session =
					new RemoteSessionSkeleton(engine.newSession());
			session.export(port);
			sessions.add(session);
			return session;
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/*
     *
     */
	@Override
	public void shutdown() {
		try {
			engine.shutdown();
		} finally {
			// in a thread, if there is too many clients it wont take much time
			new Thread() {

				@Override
				public void run() {
					try {
						unexport();
					} catch (RemoteException ex) {
					}
				}
			}.start();
		}
	}

	/*
	 * 
	 */
	@Override
	public boolean isDown() throws RemoteException {
		return engine.isDown();
	}

	/*
     * 
     */
	@Override
	public void export(int port) throws RemoteException {
		this.port = port;
		UnicastRemoteObject.exportObject(this, port);
	}

	/*
     * 
     */
	@Override
	public void unexport() throws NoSuchObjectException {
		UnicastRemoteObject.unexportObject(this, true);
		for (RemoteSessionSkeleton remoteSessionSkelton : sessions) {
			try {
				if (remoteSessionSkelton != null) {
					// if already unexported
					remoteSessionSkelton.unexport();
				}
			} catch (NoSuchObjectException e) {
				// ignore, unexported by remote client by calling discard
			} catch (RemoteException ex) {
				Logger.getLogger(RemoteEngineSkeleton.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}
}
