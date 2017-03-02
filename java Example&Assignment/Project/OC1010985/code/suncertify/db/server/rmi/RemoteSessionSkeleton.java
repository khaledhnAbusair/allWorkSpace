/**
 * 
 */
package suncertify.db.server.rmi;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.logging.Level;
import java.util.logging.Logger;

import suncertify.db.DBInternalException;
import suncertify.db.Session;

/**
 * A skeleton which wraps {@link Session} instance and passes all remote calls
 * to it.
 * <p>
 * This skeleton is constructed by {@link RemoteEngineSkeleton}. The
 * <code>java.rmi.server.Unreferenced</code> is implemented to capture if remote
 * client connection is dropped to discard wrapped session.
 * 
 * @author Mohammad S. Abdellatif
 * 
 */
class RemoteSessionSkeleton implements RemoteSession, Unreferenced,
		RemoteServiceObject {

	private Session session;
	private int port;
	/**
	 * To return same remote instance for the same client.
	 */
	private RemoteDBMainSkeleton remoteDBMainSkelton;

	/**
	 * Construct a new skelton.
	 * 
	 * @param session
	 *            session to pass remote calls to.
	 */
	RemoteSessionSkeleton(Session session) {
		this.session = session;
	}

	/*
     *
     */
	@Override
	public void discard() {
		session.discard();
		try {
			// unexport from environment after discarding
			unexport();
		} catch (RemoteException e) {
			Logger.getLogger(RemoteSessionSkeleton.class.getName()).log(
					Level.SEVERE, null, e);
		}
	}

	/*
     *
     */
	@Override
	public boolean isValid() {
		return session.isValid();
	}

	/*
     *
     */
	@Override
	public RemoteDBMain getDataAccess() {
		if (remoteDBMainSkelton == null) {
			try {
				remoteDBMainSkelton =
						new RemoteDBMainSkeleton(session.getDataAccess());
				remoteDBMainSkelton.export(port);
			} catch (RemoteException e) {
				throw new DBInternalException(e);
			}
		}
		return remoteDBMainSkelton;
	}

	/*
     *
     */
	@Override
	public String getId() {
		return session.getId();
	}

	/**
	 * Called by RMI environment to notify this instance, the skelton, its stub
	 * is not referenced by any client to discard the client session
	 * automatically from engine if the session is still valid for use.
	 * <p>
	 * The period between checking for disconnection and the call to this method
	 * is an RMI environment specific behavior.
	 */
	@Override
	public void unreferenced() {
		if (session.isValid()) {
			session.discard();
		}
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
	public void unexport() throws RemoteException {
		try {
			UnicastRemoteObject.unexportObject(this, true);

			if (remoteDBMainSkelton != null) {
				remoteDBMainSkelton.unexport();
			}
		} catch (NoSuchObjectException e) {
			// ignore, unexported by remote client by calling discard
		}
	}
}
