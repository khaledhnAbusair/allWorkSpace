/**
 * 
 */
package suncertify.db.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import suncertify.db.Session;

/**
 * A mirror for {@link Session} to handle session operations over RMI.
 * 
 * @author Mohammad S. Abdellatif
 * @see Session
 */
public interface RemoteSession extends Remote {

	/**
	 * Performs {@link Session#getId()} remotely.
	 * 
	 * @return session unique ID.
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public String getId() throws RemoteException;

	/**
	 * Performs {@link Session#getDataAccess()} remotely.
	 * 
	 * @return unique access to database file data.
	 * @throws bRemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public RemoteDBMain getDataAccess() throws RemoteException;

	/**
	 * Performs {@link Session#discard()} remotely.
	 * 
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public void discard() throws RemoteException;

	/**
	 * Performs {@link Session#isValid()} remotely.
	 * 
	 * @return <code>true</code> if this session is valid, <code>false</code>
	 *         otherwise.
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public boolean isValid() throws RemoteException;
}
