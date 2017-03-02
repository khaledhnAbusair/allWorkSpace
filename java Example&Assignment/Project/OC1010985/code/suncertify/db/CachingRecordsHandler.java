package suncertify.db;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import suncertify.db.Data.RecordsHandler;

/**
 * A records handler which adds the behavior for caching records stream to a
 * wrapped records handler.
 * 
 * @author Mohammad S. Abdellatif
 */
public class CachingRecordsHandler implements Data.RecordsHandler {

	protected Data.RecordsHandler handler;
	private Map<Integer, byte[]> cache = new HashMap<Integer, byte[]>();
	private ReadWriteLock lock = new ReentrantReadWriteLock(true);
	private Lock readLock = lock.readLock();
	private Lock writeLock = lock.writeLock();

	/**
	 * Construct records caching handler to wrap <code>handler</code>.
	 * 
	 * @param handler
	 *            handler to add caching behavior to.
	 */
	public CachingRecordsHandler(RecordsHandler handler) {
		this.handler = handler;
	}

	/**
	 * Returns the cached bytes, if nothing found in cache read record from
	 * wrapped record handler then caches it.
	 */
	@Override
	public byte[] read(int recNo) throws RecordNotFoundException, IOException {
		readLock.lock();
		try {
			if (cache.containsKey(recNo)) {
				return cache.get(recNo);
			} else {
				byte[] record = handler.read(recNo);
				cache.put(recNo, record);
				return record;
			}
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * Navigate create call to wrapped records handler then caches the record if
	 * creation is success.
	 */
	@Override
	public int create(byte[] record) throws IOException {
		int recordNo = handler.create(record);
		cache.put(recordNo, record);
		return recordNo;
	}

	/**
	 * Navigate update call to wrapped records handler then caches the updated
	 * record data if update is success.
	 */
	@Override
	public void update(int recNo, byte[] record)
			throws RecordNotFoundException, IOException {
		writeLock.lock();
		try {
			handler.update(recNo, record);
			cache.put(recNo, record);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * Navigate delete to wrapped records handler then remove the record data
	 * from cache if exist.
	 */
	@Override
	public void delete(int recNo) throws RecordNotFoundException, IOException {
		writeLock.lock();
		try {
			handler.delete(recNo);
			cache.remove(recNo);
		} finally {
			writeLock.unlock();
		}
	}
}
