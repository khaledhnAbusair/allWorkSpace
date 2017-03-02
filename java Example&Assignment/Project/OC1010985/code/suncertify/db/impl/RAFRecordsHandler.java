package suncertify.db.impl;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.Lock;

import suncertify.db.Data;
import suncertify.db.Data.RecordsHandler;
import suncertify.db.RecordDeletedException;
import suncertify.db.RecordNotFoundException;

/**
 * A {@link RecordsHandler} implementation which uses "Random Access File" to
 * read database file record stream.
 * <p>
 * Used by {@link LocalEngine} for session unique data access of type
 * {@link Data}. Access records through this class is thread safe.
 * 
 * @author Mohammad S. Abdellatif
 * @see LocalEngine
 */
class RAFRecordsHandler implements RecordsHandler {

	/**
	 * Size of record deleted flag bytes.
	 */
	private static final short DELETE_FLAG_SIZE = 2;
	/**
	 * Flag for deleted record.
	 */
	private static final short DELETED_RECORD_FLAG = (short) 0X8000;
	/**
	 * Flag for valid readable record.
	 */
	private static final short VALID_RECORD_FLAG = (short) 0x0000;
	/**
	 * Database file stream.
	 */
	private final RandomAccessFile raf;
	/**
	 * A lock for controlling the access of database file stream.
	 */
	private final Lock readWriteLock;
	private final int recordZeroOffset;
	private final int recordSize;

	/**
	 * Construct a new records handler.
	 * 
	 * @param raf
	 *            database file stream.
	 * @param readWriteLock
	 *            read write lock.
	 * @param recordZeroOffset
	 *            record number zero off set
	 * @param recordSize
	 *            record size.
	 */
	RAFRecordsHandler(RandomAccessFile raf, Lock readWriteLock,
			int recordZeroOffset, int recordSize) {
		this.raf = raf;
		this.readWriteLock = readWriteLock;
		this.recordSize = recordSize;
		this.recordZeroOffset = recordZeroOffset;
	}

	/*
     * 
     */
	@Override
	public byte[] read(int recNo) throws RecordNotFoundException, IOException {
		readWriteLock.lock();
		try {
			long recOffset = calculateRecordOffset(recNo);
			byte[] record;
			short deleteFlag;

			if (recOffset >= raf.length()) {
				throw new RecordNotFoundException();
			}
			record = new byte[recordSize];
			raf.seek(recOffset);

			deleteFlag = raf.readShort();
			if (deleteFlag == DELETED_RECORD_FLAG) {
				throw new RecordDeletedException();
			}

			raf.read(record);
			return record;
		} finally {
			readWriteLock.unlock();
		}
	}

	/*
     * 
     */
	@Override
	public int create(byte[] record) throws IOException {
		if (record.length != recordSize) {
			throw new IllegalStateException("invalid record length");
		}
		readWriteLock.lock();
		try {
			long newRecOffset = raf.length();
			// new length = current length + record size + deleted flag size
			raf.setLength(newRecOffset + recordSize + DELETE_FLAG_SIZE);
			raf.seek(newRecOffset);

			raf.writeShort(VALID_RECORD_FLAG);
			raf.write(record);
			return (int) (newRecOffset / (recordSize + DELETE_FLAG_SIZE));
		} finally {
			readWriteLock.unlock();
		}
	}

	/*
     * 
     */
	@Override
	public void update(int recNo, byte[] record)
			throws RecordNotFoundException, IOException {
		readWriteLock.lock();
		try {
			long recordOffset = calculateRecordOffset(recNo);
			int length = record.length;

			if (recordOffset >= raf.length()) {
				throw new RecordNotFoundException();
			}
			raf.seek(recordOffset);
			if (raf.readShort() == DELETED_RECORD_FLAG) {
				throw new RecordDeletedException();
			}

			if (length != recordSize) {
				throw new IllegalArgumentException(
						"record size to persist is invalid:" + length);
			}
			raf.write(record);
		} finally {
			readWriteLock.unlock();
		}
	}

	/*
     * 
     */
	@Override
	public void delete(int recNo) throws RecordNotFoundException, IOException {
		readWriteLock.lock();
		try {
			long recordOffset = calculateRecordOffset(recNo);
			short deleteFlag;

			if (recordOffset >= raf.length()) {
				throw new RecordNotFoundException();
			}
			raf.seek(recordOffset);

			deleteFlag = raf.readShort();
			if (deleteFlag == DELETED_RECORD_FLAG) {
				throw new RecordDeletedException();
			}
			raf.seek(recordOffset);// seek again to modify delete flag
			raf.writeShort(DELETED_RECORD_FLAG);
		} finally {
			readWriteLock.unlock();
		}
	}

	/**
	 * Calculate record <code>recNo</code> offset.
	 * 
	 * @param recNo
	 *            record number for which to calculate its offset.
	 * @return the offset of record.
	 */
	private long calculateRecordOffset(int recNo) {
		return (recNo * (recordSize + DELETE_FLAG_SIZE)) + recordZeroOffset;
	}
}
