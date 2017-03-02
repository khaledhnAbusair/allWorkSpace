package suncertify.db.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import suncertify.db.DBInternalException;
import suncertify.db.Engine;
import suncertify.db.Schema;
import suncertify.db.Session;
import suncertify.db.server.rmi.RemoteEngine;

/**
 * An {@link Engine} implementation which passes the calls to a stub of type
 * {@link RemoteEngine}.
 * <p>
 * Factory method {@link #getEngine(java.lang.String, int, java.lang.String)}
 * locates the stub for remote engine.
 * 
 * All <code>java.rmi.RemoteException</code> thrown by wrapped stub are rethrown
 * as {@link DBInternalException}.
 * 
 * @author Mohammad S. Abdellatif
 * @see RemoteEngine
 */
class RMIEngine implements Engine {

	private RemoteEngine remoteEngine;
	private String engineURI;
	private boolean down = false;
	private int magicCookie;

	/**
	 * Returns a {@link RMIEngine} instance which wraps a stub of type
	 * {@link RemoteEngine}.
	 * 
	 * @param host
	 *            the machine in which remote engine is running.
	 * @param port
	 *            the port for RMI runtime on machine.
	 * @param serviceName
	 *            the name to lookup with for a stub.
	 * @return an engine which wraps looked up remote engine stub.
	 * @throws RemoteException
	 *             thrown by RMI runtime.
	 * @throws NotBoundException
	 *             if no engine service is exported with this name.
	 * @throws MalformedURLException
	 *             if lookup URL is not valid.
	 */
	static Engine getEngine(String host, int port, String serviceName)
			throws RemoteException, NotBoundException, MalformedURLException {
		String engineURI = "//" + host + ":" + port + "/" + serviceName;
		RemoteEngine engineStubs = (RemoteEngine) Naming.lookup(engineURI);
		return new RMIEngine(engineStubs, engineURI);
	}

	/**
	 * Construct a new instance which wraps stub <code>remoteEngine</code>.
	 * 
	 * @param remoteEngine
	 *            remote engine stub to be wrapped.
	 */
	RMIEngine(RemoteEngine remoteEngine, String engineURI) {
		this.remoteEngine = remoteEngine;
		this.engineURI = engineURI;
		magicCookie = getDatabaseMagicCookie();
	}

	/*
     * 
     */
	@Override
	public int getDatabaseMagicCookie() {
		try {
			return remoteEngine.getDatabaseMagicCookie();
		} catch (RemoteException ex) {
			throw new DBInternalException(ex);
		}
	}

	/*
     * 
     */
	@Override
	public Schema getDBSchema() {
		try {
			return remoteEngine.getDBSchema();
		} catch (RemoteException e) {
			throw new DBInternalException(e);
		}
	}

	/*
     *
     */
	@Override
	public Session newSession() {
		try {
			return new RMISession(this, remoteEngine.newSession());
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
			remoteEngine.shutdown();
			down = true;
		} catch (RemoteException ex) {
			throw new DBInternalException(ex);
		}
	}

	/*
     * 
     */
	@Override
	public boolean isDown() {
		try {
			if (!down) {
				// check, maybe it is down by other clients
				return remoteEngine.isDown();
			}
			return down;
		} catch (RemoteException ex) {
			throw new DBInternalException(ex);
		}
	}

	/**
	 * Check equality with other RMI engine.
	 * 
	 * @return If passed object in an instance of the same class, looked up
	 *         through the same URI and have the same database cookie, returned
	 *         value is <code>true</code>, otherwise, <code>false</code>.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RMIEngine) {
			RMIEngine other = (RMIEngine) obj;
			if (other.engineURI.equals(engineURI)
					&& other.magicCookie == magicCookie) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns hash code for this remote engine.
	 * 
	 * @return this instance hash code.
	 */
	@Override
	public int hashCode() {
		return engineURI.hashCode() + magicCookie;
	}
}
