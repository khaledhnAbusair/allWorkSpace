package suncertify.db;

import java.io.File;

import suncertify.db.server.NetworkService;

/**
 * Defines a service for creating DB engines for local database files,
 * references for remote DB engines, and engines network services.
 * <p>
 * 
 * Creating multiple or single instance(s) for a database engine is an
 * implementation specific behavior as long as the consistency of data is
 * maintained correctly.
 * 
 * @author Mohammad S. Abdellatif
 * @see EngineServiceLoader
 * @see Engine
 */
public interface EngineService {

	/**
	 * Creates an engine for database file <code>dbFile</code>.
	 * <p>
	 * 
	 * Returns a new engine for database file <code>dbFile</code> or cached one
	 * if already created. It is up to provider implementation to control the
	 * number of instances created for same database file as long as the
	 * consistency of data is maintained correctly.
	 * 
	 * @param dbFile
	 *            database file reference.
	 * @return engine for database file.
	 * @throws EngineServiceException
	 *             if creation for engine is failed.
	 */
	public Engine getEngine(File dbFile) throws EngineServiceException;

	/**
	 * Creates a reference for a remote engine with unique name
	 * <code>serviceName</code> listening on port <code>port</code> hosted on
	 * <code>host</code>.
	 * 
	 * @param host
	 *            host name which remote engine service is working on.
	 * @param port
	 *            remote engine service listing port.
	 * @param serviceName
	 *            unique service name for remote engine service.
	 * @return reference for a remote engine service.
	 * @throws EngineServiceException
	 *             if getting for a reference is failed.
	 */
	public Engine getEngine(String host, int port, String serviceName)
			throws EngineServiceException;

	/**
	 * Creates an engine network service with name <code>serviceName</code> for
	 * database file <code>dbFile</code> listening on port <code>port</code>.
	 * 
	 * @param dbFile
	 *            database file for which to start network service.
	 * @param serviceName
	 *            network service name to be exported with.
	 * @param port
	 *            listening port for network service.
	 * @return a database network service.
	 * @throws EngineServiceException
	 *             if creating for network service is failed.
	 */
	public NetworkService createNetworkService(File dbFile, String serviceName,
			int port) throws EngineServiceException;
}
