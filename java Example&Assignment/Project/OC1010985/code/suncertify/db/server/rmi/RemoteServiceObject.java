package suncertify.db.server.rmi;

import java.rmi.RemoteException;

/**
 * Define the implementer as a part of RMI network service.
 * <p>
 * It is used by <code>RemoteEngineSkelton</code> to export/unexport the
 * part/whole tree of network service skeletons to RMI registry.
 * 
 * @author Mohammad S. Abdellatif
 */
interface RemoteServiceObject {
	/*
	 * used as a composite
	 */

	/**
	 * Export this object to RMI runtime.
	 * 
	 * @param port
	 *            port to export remote object to, if <code>0</code> get any
	 *            free port.
	 * @throws RemoteException
	 *             if exporting is failed.
	 */
	void export(int port) throws RemoteException;

	/**
	 * Unexport this object from RMI runtime.
	 * 
	 * @throws RemoteException
	 *             if unexporting is failed.
	 */
	void unexport() throws RemoteException;
}
