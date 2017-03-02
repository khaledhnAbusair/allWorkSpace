/**
 *
 */
package suncertify.db.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import suncertify.db.CachingRecordsHandler;
import suncertify.db.DBInternalException;
import suncertify.db.DBMain;
import suncertify.db.Data;
import suncertify.db.Data.RecordsLockHandler;
import suncertify.db.Engine;
import suncertify.db.EngineServiceException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.Schema;
import suncertify.db.Session;
import suncertify.db.impl.EngineServiceImpl.DBFileType;

/**
 * A {@link Engine} implementation which manages the access for a local database
 * file.
 * <p>
 * 
 * Factory method {@link #getEngine(java.io.File)} will construct and caches an
 * instance for passed database file. If {@link #shutdown()} is called on
 * returned instances it will be removed automatically from cache and created
 * again if same database file is passed again to factory method. <br>
 * Database files supported by this engine are defined by {@link DBFileType}. <br>
 * This engine uses {@link DBMain} implementation {@link Data} as unique session
 * data access which required implementing interfaces {@link RecordsLockHandler}
 * and {@link RecordsLockHandler}.
 * 
 * @author Mohammad S. Abdellatif
 */
class LocalEngine implements Engine {
	/*
	 * No need to implement equals() method because the default behavior is
	 * enough to check engines equality, a database file engine will be created
	 * only once for each database file so, if there is more than one reference
	 * for a database file engine, the will refer to the same singleton
	 * instance.
	 */

	/**
	 * Caches for constructed local engines.
	 */
	private static final Map<File, LocalEngine> engines =
			new HashMap<File, LocalEngine>();
	/**
	 * A lock for managing creation for local engine instances.
	 */
	private static final Lock engineCreationLock = new ReentrantLock();
	/**
	 * Used to generate a unique ID for each client accepted by this engine
	 */
	private int totalClients;
	/**
	 * Database file which this engine instance is managing.
	 */
	private File dbFile;
	/**
	 * database file read/write stream.
	 */
	private RandomAccessFile raf;
	/**
	 * Holds client sessions with session ID as key and the session as value.
	 */
	private Map<String, LocalSession> clientSessions =
			new HashMap<String, LocalSession>();
	/**
	 * Holds locked records with record No. as key and the locking session as
	 * value.
	 */
	private Map<Integer, LocalSession> lockedRecords =
			new HashMap<Integer, LocalSession>();
	/**
	 * A lock for having a single access to database engine.
	 */
	private final ReentrantLock engineLock = new ReentrantLock(true);
	/**
	 * Condition for clients who just want to do locking
	 */
	private final Condition lockingCodition = engineLock.newCondition();
	/**
	 * A shared lock used by Data instances to validate primary keys.
	 */
	private final Lock dataManipulationLock = new ReentrantLock(true);
	private int recordZeroOffset;
	private int recordSize;
	private Schema parsedSchema;
	private FileLock fileLock;
	private Data.RecordsHandler recordsHandler;
	private boolean down = false;
	private int magicCookie;
	private String charset;

	/*
     * 
     */
	@Override
	public int getDatabaseMagicCookie() {
		return magicCookie;
	}

	/*
     * 
     */
	@Override
	public Schema getDBSchema() {
		return parsedSchema;
	}

	/**
	 * Returned session unique ID is
	 */
	@Override
	public Session newSession() {
		engineLock.lock();
		try {
			String acceptedClientId =
					"client-connection-" + magicCookie + "-" + (++totalClients)
							+ "-"
							+ new Long(System.currentTimeMillis()).hashCode();
			RecordsHandlerDelegate recordHandler =
					new RecordsHandlerDelegate(recordsHandler);
			RecordsLockHandlerDelegate lockHandler =
					new RecordsLockHandlerDelegate(new RecordsLockHandlerImpl(
							this, acceptedClientId));
			Data sessionDataAccess =
					new Data(parsedSchema, recordHandler, lockHandler,
							dataManipulationLock, charset);
			LocalSession session =
					new LocalSession(acceptedClientId, this, sessionDataAccess,
							recordHandler, lockHandler);

			clientSessions.put(acceptedClientId, session);
			return session;
		} finally {
			engineLock.unlock();
		}
	}

	/*
     * 
     */
	@Override
	public void shutdown() {
		engineLock.lock();
		try {
			Collection<LocalSession> sessions;
			engines.remove(dbFile);

			sessions = new ArrayList<LocalSession>(clientSessions.values());

			for (LocalSession session : sessions) {
				session.recordsHandlerDelegate
						.setHandler(new InvalidSessionRecordsHandler(
								"invalid session, engine is down"));
				session.recordsLockHandlerDelegate
						.setHandler(new InvalidSessionLockHandler(
								"invalid session, engine is down"));
				session.discard();
			}

			clientSessions = null;
			lockedRecords = null;

			raf.close();

		} catch (IOException ex) {
			Logger.getLogger(LocalEngine.class.getName()).log(Level.SEVERE,
					"unable to cloed database file stream", ex);
		} finally {
			down = true;
			engineLock.unlock();
		}
	}

	/*
     * 
     */
	@Override
	public boolean isDown() {
		return down;
	}

	/**
	 * Factory method for instances of this class which returns a cached engine
	 * if it was already created for same database file.
	 * 
	 * @param dbFile
	 *            database file for an engine will be created.
	 * @return a new or cached local engine for database file.
	 * @throws EngineFactoryException
	 *             if passed <code>dbFile</code> does not exist, not a file,
	 *             reading for file is failed, or is not a supported database
	 *             file.
	 */
	static Engine getEngine(File dbFile) throws EngineServiceException {
		if (dbFile == null) {
			throw new NullPointerException();
		}

		if (!dbFile.exists()) {
			throw new EngineServiceException("DB file does not exist");
		} else if (!dbFile.isFile()) {
			throw new EngineServiceException("not a file");
		}

		engineCreationLock.lock();
		try {
			LocalEngine engine = engines.get(dbFile);
			if (engine == null) {
				engine = new LocalEngine();
				engine.prepareEngine(dbFile);
				engine.dbFile = dbFile;
				engines.put(dbFile, engine);
			}
			return engine;
		} catch (IOException ex) {
			throw new EngineServiceException(ex.getMessage(), ex);
		} finally {
			engineCreationLock.unlock();
		}
	}

	/**
	 * Prepare the new engine instance to accept client calls.
	 * 
	 * @param dbFile
	 *            database file to manage.
	 * @throws IOException
	 *             if reading for database file is failed.
	 * @throws EngineServiceException
	 *             if database file is not a supported database file.
	 */
	private void prepareEngine(File dbFile) throws IOException,
			EngineServiceException {
		FileChannel fc;
		DBFileType dbFileType;
		boolean prepared = false;

		raf = new RandomAccessFile(dbFile, "rw");
		try {
			fc = raf.getChannel();
			fileLock = fc.tryLock();
			if (fileLock == null) {
				throw new IOException("can not have lock to database file");
			}

			magicCookie = raf.readInt();
			dbFileType = DBFileType.getByMagicCookie(magicCookie);
			if (dbFileType == null) {
				throw new EngineServiceException("unsupported database file");
			}

			charset = dbFileType.charset;

			readSchema(dbFileType.primaryKeysIndexs, dbFileType.nullableFields);
			recordsHandler =
					new CachingRecordsHandler(new RAFRecordsHandler(raf,
							engineLock, recordZeroOffset, recordSize));

			prepared = true;
		} finally {
			if (!prepared) {
				try {
					// no need to keep it open
					raf.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * Discard client with id <code>clientId</code> by releasing all resources
	 * locked by him.
	 * <p>
	 * Any call to discarded session methods or session related data access
	 * instance will throw an <code>IllegalStateException</code> to indicate the
	 * session is invalid.
	 * 
	 * @param clientId
	 *            client session id to be discarded.
	 */
	void discardSession(String clientId) {
		engineLock.lock();
		try {
			LocalSession discardedSession = clientSessions.remove(clientId);

			if (discardedSession == null) {
				throw new IllegalArgumentException("client not found");
			}
			if (lockedRecords.containsValue(discardedSession)) {
				// if client exist and has records locked by him
				Iterator<Integer> lockedRecNos =
						lockedRecords.keySet().iterator();

				while (lockedRecNos.hasNext()) {
					Integer recNo = lockedRecNos.next();
					LocalSession lockingSession = lockedRecords.get(recNo);
					if (discardedSession.equals(lockingSession)) {
						lockedRecords.remove(recNo);
					}
				}
				lockingCodition.signalAll();
			}
			discardedSession.recordsHandlerDelegate
					.setHandler(new InvalidSessionRecordsHandler());
			discardedSession.recordsLockHandlerDelegate
					.setHandler(new InvalidSessionLockHandler());
		} finally {
			engineLock.unlock();
		}
	}

	/**
	 * Checks if client session with id <code>clientId</code> is valid or not.
	 * 
	 * @param clientId
	 *            client session id.
	 * @return Returns <code>true</code> if client session with id
	 *         <code>clientId</code> is still valid, <code>false</code>
	 *         otherwise.
	 */
	boolean isValidSession(String clientId) {
		return clientSessions.containsKey(clientId);
	}

	/**
	 * Lock Record <code>recNo</code> for client <code>clientId</code>.
	 * 
	 * @param recNo
	 *            record number to be locked.
	 * @param clientId
	 *            caller client id.
	 * @throws RecordNotFoundException
	 *             if record does not exist in database or deleted.
	 * @throws IOException
	 *             if reading for record data is failed.
	 */
	void lock(int recNo, String clientId) throws RecordNotFoundException,
			IOException {
		engineLock.lock();
		LocalSession session = clientSessions.get(clientId);

		if (session == null) {
			throw new IllegalArgumentException("unknow client: " + clientId);
		}

		try {
			recordsHandler.read(recNo);

			while (lockedRecords.containsKey(recNo)) {
				LocalSession lockingClient = lockedRecords.get(recNo);
				if (lockingClient.getId().equals(clientId)) {
					// it is locked by the same client who asked for lock
					return;
				}
				lockingCodition.await();
			}

			lockedRecords.put(recNo, session);
		} catch (InterruptedException ex) {
			throw new DBInternalException(ex);
		} finally {
			engineLock.unlock();
		}
	}

	/**
	 * Unlock record <code>recNo</code> already locked by client
	 * <code>clientId</code>. It throws an <code>IllegalStateException</code> if
	 * the record is locked by another client.
	 * 
	 * @param recNo
	 *            record number to unlock.
	 * @param clientId
	 *            caller client id.
	 * @throws RecordNotFoundException
	 *             if record does not exist or deleted.
	 * @throws IOException
	 *             if reading for record data is failed.
	 */
	void unlock(int recNo, String clientId) throws RecordNotFoundException,
			IOException {
		engineLock.lock();
		try {
			boolean isLocked;

			// to check if record exist
			recordsHandler.read(recNo);

			isLocked = lockedRecords.containsKey(recNo);
			if (isLocked) {
				LocalSession lockedClient = lockedRecords.get(recNo);
				if (lockedClient.getId().equals(clientId)) {
					lockedRecords.remove(recNo);
					lockingCodition.signal();
					return;
				} else {
					throw new IllegalStateException(
							"not locked by calling client");
				}
			}
		} finally {
			engineLock.unlock();
		}
	}

	/**
	 * Checks if record <code>recNo</code> is locked.
	 * 
	 * @param recNo
	 *            record number to check its lock status.
	 * @return <code>true</code> if record is locked, <code>false</code>
	 *         otherwise.
	 * @throws RecordNotFoundException
	 *             if record does not exist or delete.
	 * @throws IOException
	 *             if reading for record is failed.
	 */
	boolean isLocked(int recNo) throws RecordNotFoundException, IOException {
		recordsHandler.read(recNo);
		return lockedRecords.containsKey(recNo);
	}

	/**
	 * Checks if record <code>recNo</code> is locked by client
	 * <code>clientId</code>.
	 * 
	 * @param recNo
	 *            record number to check if it is locked or not.
	 * @param clientId
	 *            client id to check if record is locked by it or not.
	 * @return <code>true</code> if locked by client <code>clientId</code>,
	 *         <code>false</code> otherwise.
	 * @throws RecordNotFoundException
	 *             if record does not exist or deleted.
	 * @throws IOException
	 *             if reading for record is failed.
	 */
	boolean isLockedByClient(int recNo, String clientId)
			throws RecordNotFoundException, IOException {
		boolean recNoLocked = isLocked(recNo);
		if (recNoLocked) {
			return lockedRecords.get(recNo).getId().equals(clientId);
		}
		return false;
	}

	/**
	 * Reads the schema part in database file and construct the schema instance
	 * using primary key indexes <code>primayKeyIndexes</code>.
	 * 
	 * @param primayKeyIndexes
	 *            holds the indexes for primary keys fields.
	 * @param nullableFieldsIndexes
	 *            holds the indexes for nullable fields.
	 * @throws IOException
	 *             if reading for schema part is failed.
	 */
	private void readSchema(short[] primayKeyIndexes,
			short[] nullableFieldsIndexes) throws IOException {
		short fieldsCount;
		String[] fieldName;
		short[] fieldSize;
		boolean[] primaryKeyIndicator;
		boolean[] acceptNull;

		recordZeroOffset = raf.readInt();

		fieldsCount = raf.readShort();
		fieldName = new String[fieldsCount];
		fieldSize = new short[fieldsCount];
		primaryKeyIndicator = new boolean[fieldsCount];
		acceptNull = new boolean[fieldsCount];

		for (short i = 0; i < fieldsCount; i++) {
			short nameLength = raf.readShort();
			byte[] nameBytes = new byte[nameLength];
			short size;

			raf.read(nameBytes);
			fieldName[i] = new String(nameBytes);

			size = raf.readShort();
			fieldSize[i] = size;

			recordSize += size;
			primaryKeyIndicator[i] =
					Arrays.binarySearch(primayKeyIndexes, i) > -1 ? true
							: false;
			acceptNull[i] =
					Arrays.binarySearch(nullableFieldsIndexes, i) > -1 ? true
							: false;
		}
		parsedSchema =
				new Schema(fieldName, fieldSize, primaryKeyIndicator,
						acceptNull);
	}
}
