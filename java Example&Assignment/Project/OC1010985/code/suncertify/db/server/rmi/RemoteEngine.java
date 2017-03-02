/**
 * 
 */
package suncertify.db.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import suncertify.db.Engine;
import suncertify.db.Schema;

/**
 * A mirror for {@link Engine} to support engine operations over RMI.
 * 
 * @author Mohammad S. Abdellatif
 * @see Engine
 */
public interface RemoteEngine extends Remote {

	/**
	 * Performs {@link Engine#getDatabaseMagicCookie()} remotely.
	 * 
	 * @return wrapped database magic cookie.
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public int getDatabaseMagicCookie() throws RemoteException;

	/**
	 * Performs {@link Engine#getDBSchema()} remotely.
	 * 
	 * @return database file record structure definition.
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public Schema getDBSchema() throws RemoteException;

	/**
	 * Performs {@link Engine#newSession()} remotely.
	 * 
	 * @return new unique session.
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public RemoteSession newSession() throws RemoteException;

	/**
	 * Performs {@link Engine#shutdown()} remotely.
	 * 
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public void shutdown() throws RemoteException;

	/**
	 * Performs {@link Engine#isDown()} remotely.
	 * 
	 * @return <code>true</code> if remote engine is down, <code>false</code>
	 *         otherwise.
	 * @throws RemoteException
	 *             if runtime exception is thrown by remote JVM or a failure
	 *             occurred in RMI runtime.
	 */
	public boolean isDown() throws RemoteException;
}
