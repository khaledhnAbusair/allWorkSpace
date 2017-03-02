package suncertify.model.db;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import suncertify.db.DBMain;
import suncertify.db.DuplicateKeyException;
import suncertify.db.Engine;
import suncertify.db.RecordNotFoundException;
import suncertify.db.Schema;
import suncertify.db.Session;
import suncertify.model.Contractor;
import suncertify.model.ContractorCriteria;
import suncertify.model.ContractorModel;
import suncertify.model.ContractorNotFoundException;
import suncertify.model.DuplicateContractorException;
import suncertify.model.ModelException;

/**
 * An implementation for {@link ContractorModel} which converts string array
 * records data retrieved from {@link DBMain} as {@link Contractor} instances
 * and visa versa.
 * <p>
 * 
 * It needs a {@link Session} from which to have an access to a database file
 * data through interface {@link DBMain}, an instance of it is retrieved by
 * calling {@link Session#getDataAccess() }
 * 
 * It depends on {@link Schema} to convert string array records to a contractor
 * beans.
 * 
 * @author Mohammad S. Abdellatif
 * @see ContractorModel
 * @see Contractor
 * @see Engine
 */
public class DBFileContractorModel implements ContractorModel {

	private static final int CONTRACTORS_DB_FILE_MAGIC_COOKIE = 514;
	private static final String SPECIALTIES_SEPERATOR = ", ";
	private Session session;
	private DBMain data;
	private Schema schema;

	/**
	 * Construct a new model using unique data access provided by
	 * <code>session</code>.
	 * 
	 * @param session
	 *            unique access to database file.
	 */
	public DBFileContractorModel(Session session) {
		Engine engine = session.getEngine();

		validateDatabaseType(engine);
		this.session = session;
		this.schema = engine.getDBSchema();

		data = session.getDataAccess();
	}

	/*
     *
     */
	@Override
	public void discard() {
		session.discard();
	}

	/*
     *
     * 
     */
	@Override
	public Set<Contractor> find(ContractorCriteria criteria)
			throws ModelException {
		Set<Contractor> contractors = new TreeSet<Contractor>();
		int[] records;
		try {
			Integer size = criteria.getSize();
			String[] specialties = criteria.getSpecialties();
			String[] fieldCriteria = new String[6];
			Integer owner = criteria.getOwner();
			BigDecimal rate = criteria.getRate();

			fieldCriteria[schema.getFieldIndex("name")] = criteria.getName();
			fieldCriteria[schema.getFieldIndex("location")] =
					criteria.getLocation();
			fieldCriteria[schema.getFieldIndex("owner")] =
					owner == null ? "" : owner.toString();
			fieldCriteria[schema.getFieldIndex("rate")] =
					rate == null ? "" : Contractor.RATE_FORMAT.format(rate);
			fieldCriteria[schema.getFieldIndex("size")] =
					(size == null ? "" : size) + "";
			fieldCriteria[schema.getFieldIndex("specialties")] =
					getSpecialtiesArrayAsString(specialties);

			records = data.find(fieldCriteria);
		} catch (RecordNotFoundException ex) {
			// no records matching criteria found
			return contractors;
		}
		for (int i = 0; i < records.length; i++) {
			int recNo = records[i];
			try {
				String[] record = data.read(recNo);
				DBFileContractor contractor =
						getDataRecordAsContractor(recNo, record);

				contractors.add(contractor);
			} catch (RecordNotFoundException e) {
				// ignore
			}
		}
		return contractors;

	}

	/*
     * 
     */
	@Override
	public void update(Contractor contractor) throws ModelException {
		boolean wasLocked = false;
		boolean wasUpdated = false;
		int recNo;

		recNo = getContractorRecNo(contractor);

		try {
			String[] record = getContractorAsDataRecord(contractor);

			data.lock(recNo);
			wasLocked = true;
			data.update(recNo, record);
			wasUpdated = true;
		} catch (RecordNotFoundException ex) {
			throw new ContractorNotFoundException(ex);
		} finally {
			if (wasLocked) {
				// unlock if only locked
				try {
					data.unlock(recNo);
				} catch (RecordNotFoundException ex) {
					if (wasUpdated) {
						throw new ModelException(
								"unable to unlock updated record");
					} else {
						// log the unlock exception to through update
						// operation exceptoin
						Logger.getLogger(DBFileContractorModel.class.getName())
								.log(Level.SEVERE, null, ex);
					}
				}
			}
		}
	}

	/*
     *
     */
	@Override
	public void create(Contractor contractor) throws ModelException {
		try {
			String[] record = getContractorAsDataRecord(contractor);
			data.create(record);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateContractorException(ex);
		}
	}

	/*
     *
     */
	@Override
	public void delete(Contractor contractor) throws ModelException {
		try {
			int recNo = getContractorRecNo(contractor);

			data.lock(recNo);
			data.delete(recNo);
			// no need to unlock the record since the engine will
			// automatically unlock it
		} catch (RecordNotFoundException ex) {
			throw new ContractorNotFoundException(ex);
		}
	}

	/*
     * 
     */
	@Override
	public void refresh(Contractor contractor) throws ModelException,
			ContractorNotFoundException {
		int recNo = getContractorRecNo(contractor);
		try {
			fillContractorEntity(contractor, data.read(recNo));
		} catch (RecordNotFoundException ex) {
			throw new ContractorNotFoundException(ex);
		}
	}

	/*
     * 
     */
	@Override
	public void lock(Contractor contractor) throws ModelException,
			ContractorNotFoundException {
		try {
			int recNo = getContractorRecNo(contractor);
			data.lock(recNo);
		} catch (RecordNotFoundException ex) {
			throw new ContractorNotFoundException(ex);
		}
	}

	/*
     *
     */
	@Override
	public void unlock(Contractor contractor) throws ModelException,
			ContractorNotFoundException {
		try {
			int recNo = getContractorRecNo(contractor);
			data.unlock(recNo);
		} catch (RecordNotFoundException ex) {
			throw new ContractorNotFoundException(ex);
		}
	}

	/**
	 * Checks if passed engine is an engine for a contractors database file, if
	 * not, throw an IllegalArgumentException
	 * 
	 * @param engine
	 *            engine to check.
	 * @throws IllegalArgumentException
	 *             if engine passed is not for a contractors database file.
	 */
	private void validateDatabaseType(Engine engine)
			throws IllegalArgumentException {
		if (engine.getDatabaseMagicCookie() != CONTRACTORS_DB_FILE_MAGIC_COOKIE) {
			throw new IllegalArgumentException("Not a contractors database");
		}
	}

	/**
	 * Returns the contractor record no in database file.
	 * 
	 * @param contractor
	 *            contractor for which to find its number
	 * @return contractor record number.
	 * @throws ContractorNotFoundException
	 *             if contractor was not found in database file.
	 */
	private int getContractorRecNo(Contractor contractor)
			throws ContractorNotFoundException {
		if (contractor instanceof DBFileContractor
				&& ((DBFileContractor) contractor).sessionId.equals(session
						.getId())) {
			// instance of DBFileContractor and read by this model instance.
			return ((DBFileContractor) contractor).recNo;
		} else {
			try {
				String[] criteria = new String[schema.getFieldsCount()];
				String name =
						String.format("%-" + schema.getFieldSize("name") + "s",
								contractor.getName());
				String location =
						String.format("%-" + schema.getFieldSize("location")
								+ "s", contractor.getLocation());
				int[] find;

				criteria[schema.getFieldIndex("name")] = name;
				criteria[schema.getFieldIndex("location")] = location;

				find = data.find(criteria);
				if (find.length == 0) {
					// only the unique record found
					return find[0];
				}
			} catch (RecordNotFoundException ex) {
				throw new ContractorNotFoundException(ex.getMessage());
			}
		}
		throw new ContractorNotFoundException("contractor not found");
	}

	/**
	 * Convert <code>contractor</code> as a string record.
	 * 
	 * @param contractor
	 *            contractor to be converted.
	 * @return contractor information as string record.
	 */
	private String[] getContractorAsDataRecord(Contractor contractor) {
		String[] record = new String[6];
		String[] specialties = contractor.getSpecialties();
		String specialtiesString = getSpecialtiesArrayAsString(specialties);
		Integer owner = contractor.getOwner();
		Integer size = contractor.getSize();
		String name = contractor.getName();
		String location = contractor.getLocation();
		BigDecimal rate = contractor.getRate();

		name = name == null ? "" : name;
		location = location == null ? "" : location;

		record[schema.getFieldIndex("name")] = name;
		record[schema.getFieldIndex("location")] = location;
		record[schema.getFieldIndex("specialties")] = specialtiesString;
		record[schema.getFieldIndex("size")] = (size == null ? "" : size) + "";
		record[schema.getFieldIndex("rate")] =
				(rate == null ? "" : Contractor.RATE_FORMAT.format(rate));
		record[schema.getFieldIndex("owner")] =
				(owner == null ? "" : owner) + "";

		return record;
	}

	/**
	 * Converts record <code>recNo</code> fields to a contractor instance.
	 * 
	 * @param recNo
	 *            record number.
	 * @param record
	 *            contractor data fields.
	 * @return contractor fields as bean instance.
	 */
	private DBFileContractor getDataRecordAsContractor(int recNo,
			String[] record) {
		DBFileContractor contractor =
				new DBFileContractor(recNo, session.getId());
		fillContractorEntity(contractor, record);
		return contractor;
	}

	/**
	 * Fill bean <code>contractor</code> with fields in <code>record</code>.
	 * 
	 * @param contractor
	 *            bean to fill its property with.
	 * @param record
	 *            record in database file.
	 */
	private void fillContractorEntity(Contractor contractor, String[] record) {
		String size;
		String owner;

		contractor.setName(getFieldValue(record[schema.getFieldIndex("name")]));
		contractor.setLocation(getFieldValue(record[schema
				.getFieldIndex("location")]));
		contractor.setSpecialties(record[schema.getFieldIndex("specialties")]
				.split(SPECIALTIES_SEPERATOR));
		size = record[schema.getFieldIndex("size")];
		contractor.setSize(size == null ? 0 : Integer.parseInt(size.trim()));

		try {
			double doubleValue =
					Contractor.RATE_FORMAT
							.parse(getFieldValue(record[schema
									.getFieldIndex("rate")])).doubleValue();
			contractor.setRate(new BigDecimal(doubleValue));
		} catch (ParseException e) {
			// this shall not happens
			throw new IllegalStateException(e);
		}

		owner = getFieldValue(record[schema.getFieldIndex("owner")]);
		contractor.setOwner(owner == null ? null : new Integer(owner));
	}

	/**
	 * Returns field value in a database record as a property value.
	 * 
	 * @param value
	 *            field value in a database record.
	 * @return If <code>"".equals(value)</code> return <code>null</code>,
	 *         otherwise, return <code>value.trim()</code>.
	 */
	private static String getFieldValue(String value) {
		value = value.trim();
		return "".equals(value) ? null : value.trim();
	}

	/**
	 * Returns specialties as a single comma separated value.
	 * 
	 * @param specialties
	 *            array of specialties.
	 * @return specialties as single comma separated string.
	 */
	private static String getSpecialtiesArrayAsString(String[] specialties) {
		if (specialties == null || specialties.length == 0) {
			return "";
		} else {
			StringBuilder specialtiesString = new StringBuilder();
			int length = specialties.length;

			for (int i = 0; i < length; i++) {
				String value = specialties[i];
				if (value != null && !"".equals(value.trim())) {
					if (specialtiesString.length() > 0) {
						specialtiesString.append(SPECIALTIES_SEPERATOR);
					}
					specialtiesString.append(value);
				}
			}
			return specialtiesString.toString();
		}
	}
}
