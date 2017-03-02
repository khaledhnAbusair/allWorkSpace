package suncertify.model.db;

import suncertify.db.DBMain;
import suncertify.model.Contractor;

/**
 * A bean for encapsulating a contractor info retrieved from {@link DBMain}.
 * Additionally its hold the record no for the contractor in DB file and the
 * session ID by which this instance is created.
 * 
 * @author Mohammad S. Abdellatif
 * @see DBFileContractorModel
 */
class DBFileContractor extends Contractor {

	/**
	 * Record number for contractor in DB file.
	 */
	final int recNo;
	/**
	 * Session ID for the client who read this entity.
	 */
	final String sessionId;

	/**
	 * Construct a new contractor entity.
	 * 
	 * @param recNo
	 *            record number for contractor in DB file.
	 * @param sessionId
	 *            client who read this entity.
	 */
	DBFileContractor(int recNo, String sessionId) {
		this.recNo = recNo;
		this.sessionId = sessionId;
	}

	/**
	 * Override to trim extra spaces from elements before setting the property.
	 * 
	 * @param specialties
	 *            specialties to set.
	 */
	@Override
	public void setSpecialties(String[] specialties) {
		if (specialties != null) {
			for (int i = 0; i < specialties.length; i++) {
				specialties[i] =
						specialties[i] == null ? "" : specialties[i].trim();
			}
		}
		super.setSpecialties(specialties);
	}
}
