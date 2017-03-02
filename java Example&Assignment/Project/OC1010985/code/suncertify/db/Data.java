package suncertify.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

/**
 * A suitable concrete implementation for {@link DBMain} which takes a records
 * read/write and locking strategy and a shared lock to be used when validating
 * records primary keys.
 * 
 * <p>
 * This class can be used by an database file engine implementations instead of
 * implementing <code>DBMain</code> from scratch.
 * 
 * <p>
 * It uses an implementation of {@link RecordsHandler} to read/write records
 * from/to database file stream and uses an implementation of
 * {@link RecordsLockHandler} to lock/unlock records. A shared instance of
 * <code>java.util.concurrent.locks.Lock</code> is passed to the instances to
 * synchronize the process of validating records primary keys through create and
 * update operations performed by calling threads.
 * 
 * <p>
 * The synchronization of reading/writing from/to database file stream must be
 * handled by <code>RecordsHandler</code> passed to this class instance, this
 * will give the implementer the chance of handling this behavior. Even
 * synchronizing and behavior of records locking must be the responsibility of
 * <code>RecordsLockHandler</code> implementer for the same reason.
 * 
 * <p>
 * <code>RecordsHandler</code> only works with records in a binary stream, this
 * class will handle the process of converting records binaries as string data
 * fields according to {@link Schema} instance passed through its constructor.
 * 
 * <p>
 * Update/delete operations throw <code>java.lang.IllegalStateException</code>
 * if record to be updated/deleted is not locked by calling client.
 * 
 * @author Mohammad S. Abdellatif
 * @see DBMain
 * @see Schema
 * @see Engine
 */
public class Data implements DBMain {

	private final String charset;
	private final Schema schema;
	private final RecordsHandler recordsHandler;
	private final RecordsLockHandler lockHandler;
	private final Lock keyValidationLock;

	/**
	 * Construct a new database file CRUD operations handler.
	 * <p>
	 * 
	 * @param schema
	 *            the structure of database file records.
	 * @param recordsHandler
	 *            the strategy for database file records read/write.
	 * @param lockHandler
	 *            the strategy for database file records locking.
	 * @param keyValidationLock
	 *            shared lock used this instance when validating created/updated
	 *            records primary keys.
	 * @param charset
	 *            character set to use when read/write file data to database.
	 */
	public Data(Schema schema, RecordsHandler recordsHandler,
			RecordsLockHandler lockHandler, Lock keyValidationLock,
			String charset) {
		this.schema = schema;
		this.recordsHandler = recordsHandler;
		this.lockHandler = lockHandler;
		this.keyValidationLock = keyValidationLock;
		this.charset = charset;
	}

	/*
     * 
     */
	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		validateRecNo(recNo);
		try {
			byte[] record = recordsHandler.read(recNo);
			return getBytesAsDataFields(record);
		} catch (IOException ex) {
			throw new DBInternalException(ex.getMessage());
		}
	}

	/*
     * 
     */
	@Override
	public void update(int recNo, String[] data) throws RecordNotFoundException {
		validateRecNo(recNo);
		try {
			if (!lockHandler.isLockedByCaller(recNo)) {
				throw new IllegalStateException(
						"Record is not locked by calling client for update");
			} else {
				String[] currentData;

				validateFields(data);
				// read record data from database
				currentData = getBytesAsDataFields(recordsHandler.read(recNo));

				for (short i = 0; i < schema.getFieldsCount(); i++) {
					if (schema.isPrimaryKeyField(i)) {
						if (!currentData[i].trim().equalsIgnoreCase(
								data[i].trim())) {
							throw new IllegalStateException(
									"Update for record primary key(s) is prohibited"
											+ " delete then recreate");
						}
						// keep the key the same as in database
						data[i] = currentData[i];
					}

				}
				recordsHandler.update(recNo, getDataFieldsAsBytes(data));
			}
		} catch (IOException ex) {
			throw new DBInternalException(ex);
		}
	}

	/*
     *
     */
	@Override
	public void delete(int recNo) throws RecordNotFoundException {
		try {
			if (!lockHandler.isLockedByCaller(recNo)) {
				throw new IllegalStateException(
						"Record is not locked for delete");
			}
			validateRecNo(recNo);
			keyValidationLock.lock();
			try {
				recordsHandler.delete(recNo);
				// unlock it in engine since it is unusable.
				lockHandler.unlock(recNo);
			} finally {
				keyValidationLock.unlock();
			}
		} catch (IOException ex) {
			throw new DBInternalException(ex.getMessage());
		}
	}

	/*
     * 
     */
	@Override
	public int[] find(String[] criteria) throws RecordNotFoundException {
		ArrayList<Integer> recNos = new ArrayList<Integer>();
		int[] recNosArray;
		int criteriaLength;

		if (criteria == null) {
			throw new IllegalArgumentException(
					"passed critieria can not be null");
		}
		if ((criteriaLength = criteria.length) != schema.getFieldsCount()) {
			throw new IllegalArgumentException(
					"critiera fields number is invalid, expected: "
							+ schema.getFieldsCount() + " got "
							+ criteriaLength);
		}

		try {
			int nextRecNo = 0;
			while (true) {
				try {
					if (isRecordMatchCriteria(nextRecNo, criteria)) {
						recNos.add(nextRecNo);
					}
				} catch (IOException ex) {
					throw new DBInternalException(ex);
				} catch (RecordDeletedException ex) {
					// continue to next record
				}
				nextRecNo++;// next record
			}
		} catch (RecordNotFoundException ex) {
			// all record are read
		}
		if (recNos.isEmpty()) {
			throw new RecordNotFoundException("No matching was found");
		}
		recNosArray = new int[recNos.size()];
		for (int i = 0; i < recNos.size(); i++) {
			recNosArray[i] = recNos.get(i);
		}
		return recNosArray;
	}

	/*
     *
     */
	@Override
	public int create(String[] data) throws DuplicateKeyException {
		validateFields(data);
		keyValidationLock.lock();
		try {
			if (isValidPrimayKey(data)) {
				return recordsHandler.create(getDataFieldsAsBytes(data));
			}
			throw new DuplicateKeyException();
		} catch (IOException ex) {
			throw new DBInternalException(ex);
		} finally {
			keyValidationLock.unlock();
		}
	}

	/*
     * 
     */
	@Override
	public void lock(int recNo) throws RecordNotFoundException {
		validateRecNo(recNo);
		try {
			lockHandler.lock(recNo);
		} catch (IOException ex) {
			throw new DBInternalException(ex.getMessage());
		}
	}

	/*
     * 
     */
	@Override
	public void unlock(int recNo) throws RecordNotFoundException {
		validateRecNo(recNo);
		try {
			lockHandler.unlock(recNo);
		} catch (IOException ex) {
			throw new DBInternalException(ex.getMessage());
		}
	}

	/*
     * 
     */
	@Override
	public boolean isLocked(int recNo) throws RecordNotFoundException {
		validateRecNo(recNo);
		try {
			return lockHandler.isLocked(recNo);
		} catch (IOException ex) {
			throw new DBInternalException(ex.getMessage());
		}
	}

	/**
	 * Defines the strategy for database file records bytes read/write
	 * operations to be used by an instance of {@link Data}.
	 * 
	 * <p>
	 * The synchronizing for read/write operations for calling threads must be
	 * handled by the implementer.
	 */
	public static interface RecordsHandler {

		/**
		 * Read bytes for record with no <code>recNo</code>.
		 * 
		 * @param recNo
		 *            the number of record to be read.
		 * @return bytes of read record.
		 * @throws RecordNotFoundException
		 *             if record does not exist in database file or deleted.
		 * @throws IOException
		 *             when writing to database file stream is failed.
		 */
		public byte[] read(int recNo) throws RecordNotFoundException,
				IOException;

		/**
		 * Write new record bytes <code>record</code> to database file stream
		 * then return the record number assigned to it.
		 * 
		 * <p>
		 * Returned record number can be the result of appending newly created
		 * record at the rare of file or reusing a deleted record position.
		 * 
		 * @param record
		 *            new record bytes to be added to database file stream.
		 * @return record number assigned to newly created record.
		 * @throws IOException
		 *             if writing for record bytes is failed.
		 */
		public int create(byte[] record) throws IOException;

		/**
		 * Update record bytes for record with number <code>recNo</code> with
		 * bytes <code>record</code>.
		 * 
		 * @param recNo
		 *            record number to be updated.
		 * @param record
		 *            new record bytes.
		 * @throws RecordNotFoundException
		 *             if record does not exist in database or deleted.
		 * @throws IOException
		 *             when writing for record bytes is failed.
		 */
		public void update(int recNo, byte[] record)
				throws RecordNotFoundException, IOException;

		/**
		 * Mark record with number <code>recNo</code> as deleted.
		 * 
		 * <p>
		 * After the record is deleted the implementer has the choice of reusing
		 * its space as a free space when adding new records.
		 * 
		 * @param recNo
		 *            record number to be marked as deleted.
		 * @throws RecordNotFoundException
		 *             if record does not exist in database file or deleted.
		 * @throws IOException
		 *             if marking for records bytes as deleted is failed.
		 */
		public void delete(int recNo) throws RecordNotFoundException,
				IOException;
	}

	/**
	 * Defines the strategy for records locking behavior to be used by an
	 * instance of {@link Data}.
	 * 
	 * @see Data
	 */
	public static interface RecordsLockHandler {

		/**
		 * Lock record with number <code>recNo</code>.
		 * <p>
		 * 
		 * @param recNo
		 *            record number to be locked.
		 * @throws RecordNotFoundException
		 *             if record is not found in database file or deleted.
		 * @throws IOException
		 *             if reading for record stream is failed.
		 */
		public void lock(int recNo) throws RecordNotFoundException, IOException;

		/**
		 * Checks if record <code>recNo</code> generally locked.
		 * 
		 * @param recNo
		 *            record number to check if it is locked or not.
		 * @return <code>true</code> if record with number <code>recNo</code> is
		 *         locked, otherwise <code>false</code>.
		 * @throws RecordNotFoundException
		 *             if record does not exist in database file or deleted.
		 * @throws IOException
		 *             if reading for records bytes is failed.
		 */
		public boolean isLocked(int recNo) throws RecordNotFoundException,
				IOException;

		/**
		 * Checks if record is locked by calling client.
		 * 
		 * @param recNo
		 *            record number to check if is locked by caller or not.
		 * @return <code>true</code> if record with number <code>recNo</code> is
		 *         locked by calling thread, otherwise <code>false</code>.
		 * @throws RecordNotFoundException
		 *             if record does not exist in database file or deleted.
		 * @throws IOException
		 *             if reading from records bytes is failed.
		 */
		public boolean isLockedByCaller(int recNo)
				throws RecordNotFoundException, IOException;

		/**
		 * Unlock record with number <code>recNo</code> and notify other waiting
		 * threads a record has been unlocked.
		 * <p>
		 * 
		 * @param recNo
		 *            record number to be unlocked.
		 * @throws RecordNotFoundException
		 *             if record does not exist or deleted.
		 * @throws IOException
		 *             if reading for records bytes is failed.
		 */
		public void unlock(int recNo) throws RecordNotFoundException,
				IOException;
	}

	/**
	 * Validate <code>recNo</code> if it is<code> >= 0</code>.
	 * 
	 * @param recNo
	 *            record number to be validated.
	 * @throws IllegalArgumentException
	 *             if <code>recNo</code> is negative.
	 */
	private void validateRecNo(int recNo) throws IllegalArgumentException {
		if (recNo < 0) {
			throw new IllegalArgumentException("negative recNo is invalid "
					+ recNo);
		}
	}

	/**
	 * Return bytes <code>record</code> as string fields according to schema
	 * structure passed through the constructor. The length for each field must
	 * be according to schema too.
	 * 
	 * @param record
	 *            records bytes to be converted as string fields.
	 * @return parsed string fields.
	 * @throws UnsupportedEncodingException
	 *             if encoding for record bytes is failed.
	 */
	private String[] getBytesAsDataFields(byte[] record)
			throws UnsupportedEncodingException {
		short fieldsCount = schema.getFieldsCount();
		String[] data = new String[fieldsCount];
		ByteArrayInputStream input = new ByteArrayInputStream(record);

		for (short index = 0; index < fieldsCount; index++) {
			short fieldSize = schema.getFieldSize(index);
			byte[] fieldBytes = new byte[fieldSize];

			input.read(fieldBytes, 0, fieldSize);
			data[index] = new String(fieldBytes, charset);
		}
		return data;
	}

	/**
	 * Returns string fields <code>data</code> as bytes. The total length of
	 * bytes must be equals to total expected record length to be stored.
	 * 
	 * @param data
	 *            record as string fields.
	 * @return record as bytes.
	 */
	private byte[] getDataFieldsAsBytes(String[] data) throws IOException {
		fixRecordData(data);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		for (short index = 0; index < data.length; index++) {
			output.write(data[index].getBytes(charset));
		}
		return output.toByteArray();
	}

	/**
	 * Fix each string field <code>data</code> by padding it with spaces until
	 * it reaches its maximum size.
	 * 
	 * @param data
	 *            data to fix.
	 * @throws UnsupportedEncodingException
	 */
	private void fixRecordData(String[] data)
			throws UnsupportedEncodingException {
		int dataLength = data.length;
		for (short i = 0; i < dataLength; i++) {
			data[i] = fixFieldValue(i, data[i]);
		}
	}

	/**
	 * Fix field <code>i</code> value by padding it with spaces until reaches
	 * its maximum size.
	 * 
	 * @param i
	 *            location of field in schema.
	 * @param field
	 *            field value.
	 * @return new field value.
	 * @throws UnsupportedEncodingException
	 *             if encoding for field is failed.
	 */
	private String fixFieldValue(short i, String field)
			throws UnsupportedEncodingException {
		/*
		 * by scenes field passed must not be greater than expected because it
		 * is validated
		 */
		int valueLength;
		short fieldSize;

		field = new String((field == null ? "" : field).getBytes(), charset);
		valueLength = field.length();
		fieldSize = schema.getFieldSize(i);

		if (valueLength < fieldSize) {
			// append spaces
			field = String.format("%-" + fieldSize + "s", field);
		}
		return field;
	}

	/**
	 * Validate if passed record fields <code>data</code> is not
	 * <code>null</code> or its length is not equals to total expected fields
	 * count.
	 * 
	 * @param data
	 *            records fields.
	 * @throws NullPointerException
	 *             thrown if passed <code>data</code> is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if passed record fields does not match the expected length or
	 *             <code>null</code>F.
	 */
	private void validateFields(String[] data) throws NullPointerException,
			IllegalArgumentException {
		int dataCount;
		short expectedCount;

		if (data == null) {
			throw new NullPointerException("record data can not be null");
		}

		dataCount = data.length;
		expectedCount = schema.getFieldsCount();

		if (dataCount != expectedCount) {
			throw new IllegalArgumentException("expected record fields count: "
					+ expectedCount + " not " + dataCount);
		}

		for (short i = 0; i < dataCount; i++) {
			String value = data[i];
			short fieldSize;
			int valueLength;

			value = value == null ? "" : value.trim();
			// of course primary keys are not nullable fields
			if ("".equals(value) && !schema.isNullableField(i)) {
				throw new IllegalArgumentException("field '"
						+ schema.getFieldName(i) + "' can not be null");
			}

			fieldSize = schema.getFieldSize(i);
			valueLength = value.length();

			if (valueLength > fieldSize) {
				throw new IllegalArgumentException(
						"value too large for field '" + schema.getFieldName(i)
								+ "', expected size: " + fieldSize + " got "
								+ valueLength);
			}
		}
	}

	/**
	 * Check if record <code>recNo</code> fields matches passed
	 * <code>criteria</code>.
	 * 
	 * @param recNo
	 *            record number to check if match.
	 * @param criteria
	 *            criteria to match with.
	 * @return <code>true</code> if data record matches criteria, otherwise,
	 *         <code>false</code>.
	 * @throws IOException
	 *             if reading records data from file stream is failed.
	 * @throws RecordNotFoundException
	 *             if record does not exist in database file or deleted.
	 */
	private boolean isRecordMatchCriteria(int recNo, String[] criteria)
			throws IOException, RecordNotFoundException {
		byte[] record = recordsHandler.read(recNo);
		String[] data = getBytesAsDataFields(record);
		return isCriteriaMatched(data, criteria);
	}

	/**
	 * Checks if record <code>data</code> matches <code>criteria</code>.
	 * 
	 * @param data
	 *            record to check if match.
	 * @param criteria
	 *            criteria to match with.
	 * @return <code>true</code> if data record matches criteria, otherwise,
	 *         <code>false</code>.
	 */
	private boolean isCriteriaMatched(String[] data, String[] criteria) {
		for (int i = 0; i < data.length; i++) {
			String cf = criteria[i];// criteria field
			String df = data[i].toLowerCase();// data field

			if (cf == null || df.startsWith(cf.toLowerCase())) {
				// record field and cf matched, check other fields
				continue;
			}
			return false;// at least one field mismatched, all record mismatched
		}
		return true;
	}

	/**
	 * Checks if record <code>data</code> has a valid primary key which does not
	 * exist in database file.
	 * 
	 * @param data
	 *            record to validate its key.
	 * @return <code>true</code> if keys fields are valid, otherwise,
	 *         <code>false</code>.
	 * @throws IOException
	 *             if reading for database records is failed.
	 */
	private boolean isValidPrimayKey(String[] data) throws IOException {
		String[] criteria = new String[schema.getFieldsCount()];

		for (short i = 0; i < schema.getFieldsCount(); i++) {
			criteria[i] =
					schema.isPrimaryKeyField(i) ? fixFieldValue(i, data[i])
							: null;
		}

		try {
			int recNo = 0;
			while (true) {
				try {
					if (isRecordMatchCriteria(recNo, criteria)) {
						return false;// not a valid primary key
					}
				} catch (RecordDeletedException ex) {
				}
				recNo++;
			}
		} catch (RecordNotFoundException ex) {
		}
		return true;// valid key
	}
}
