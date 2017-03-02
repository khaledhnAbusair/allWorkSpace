package suncertify.db.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import suncertify.db.Engine;
import suncertify.db.EngineService;
import suncertify.db.EngineServiceException;
import suncertify.db.server.NetworkService;
import suncertify.db.server.rmi.RMINetworkService;
import suncertify.db.server.rmi.RemoteEngine;

/**
 * An {@link EngineService} for creating engines for local database files,
 * locating remote database engines exported to RMI registries using interface
 * {@link RemoteEngine}, and uses {@link RMINetworkService} as an engine network
 * service.
 * <p>
 * 
 * Call for {@link #getEngine(java.io.File)} will return the same engine
 * instance for passed database file. If {@link Engine#shutdown()} is called, a
 * new instance will be created if the same database file is passed again.
 * <p>
 * 
 * Local database files can only be created for supported files defined in enum
 * {@link DBFileType}.
 * 
 * @author Mohammad S. Abdellatif
 * @see RMINetworkService
 * @see RemoteEngine
 */
public class EngineServiceImpl implements EngineService {

	/**
	 * Defines the database files supported by {@link EngineServiceImpl}.
	 * 
	 * @author Mohammad S. Abdellatif
	 */
	public static enum DBFileType {

		/**
		 * Defines <code>Contractors</code> database file type.
		 */
		CONTRACTOR_DB_FILE(
				514,
				"US-ASCII",
				new short[] { (short) 0, (short) 1 },
				new short[] { 5 });
		/**
		 * The value for magic cookie which identify the database type.
		 */
		final int magicCookieValue;
		/**
		 * Character set to use when read/write data from/to file.
		 */
		final String charset;
		/**
		 * ordered primary keys indexes.
		 */
		final short[] primaryKeysIndexs;
		/**
		 * ordered indexes for fields which can accept <code>null</code>, empty,
		 * values.
		 */
		final short[] nullableFields;

		/**
		 * Construct a new type with magic cookie <code>magicCookieValue</code>
		 * and primary keys indexes <code>primaryKeys</code>.
		 * 
		 * @param magicCookieValue
		 *            database file magic cookie.
		 * @param charset
		 *            the character set to use.
		 * @param primaryKeys
		 *            indexes for primary keys fields.
		 * @param nullableFields
		 *            indexes for nullable fields.
		 */
		private DBFileType(int magicCookieValue, String charset,
				short[] primaryKeys, short[] nullableFields) {
			this.magicCookieValue = magicCookieValue;
			this.charset = charset;
			this.primaryKeysIndexs = primaryKeys;
			this.nullableFields = nullableFields;
		}

		/**
		 * Returns an instance according to passed cookie or <code>null</code>
		 * if no match found.
		 * 
		 * @param magicCookie
		 *            magic cookie for database file.
		 * @return <code>null</code> if no match found.
		 */
		static DBFileType getByMagicCookie(int magicCookie) {
			DBFileType[] values = DBFileType.values();
			for (DBFileType value : values) {
				if (value.magicCookieValue == magicCookie) {
					return value;
				}
			}
			return null;
		}
	}

	/**
	 * Returns the cached <code>Engine</code> instance for database file
	 * <code>dbFile</code> or creates and caches a new one. If the holder for
	 * returned instance calls <code>shutdown()</code> on it the cached instance
	 * will be deleted and a new one will be created if the same
	 * <code>dbFile</code> is passed again to this method.
	 */
	@Override
	public Engine getEngine(File dbFile) throws EngineServiceException {
		return LocalEngine.getEngine(dbFile);
	}

	/**
	 * Locates a remote database engine exported to RMI registry.
	 * <p>
	 * 
	 * The lookup result for a stub of type {@link RemoteEngine} on machine
	 * <code>host</code> on port <code>port</code> with name
	 * <code>serviceName</code> will be wrapped with an <code>Engine</code>
	 * instance which will navigate all calls to stub.
	 */
	@Override
	public Engine getEngine(String host, int port, String serviceName)
			throws EngineServiceException {
		try {
			return RMIEngine.getEngine(host, port, serviceName);
		} catch (RemoteException ex) {
			throw new EngineServiceException(ex.getMessage(), ex);
		} catch (NotBoundException ex) {
			throw new EngineServiceException(ex.getMessage(), ex);
		} catch (MalformedURLException ex) {
			throw new EngineServiceException(ex.getMessage(), ex);
		}
	}

	/**
	 * Always a new instance of {@link RMINetworkService} will be returned.
	 */
	@Override
	public NetworkService createNetworkService(File dbFile, String serviceName,
			int port) throws EngineServiceException {
		RMINetworkService service =
				new RMINetworkService(dbFile, serviceName, port);
		return service;
	}
}
