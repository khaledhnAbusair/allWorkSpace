package suncertify.db.impl;

import suncertify.db.Data.RecordsHandler;

/**
 * A records handler which always throws <code>IllegalStateException</code> from
 * its methods to notify the caller that his session is invalid.
 * 
 * @author Mohammad S. Abdellatif
 */
class InvalidSessionRecordsHandler implements RecordsHandler {

	/**
	 * The default error message to be used when throwing exceptions.
	 */
	static final String INVALID_CLIENT_SESSION_MSG = "invalid client session";
	private final String errorMessage;

	/**
	 * Construct a new records handler.
	 */
	public InvalidSessionRecordsHandler() {
		this(INVALID_CLIENT_SESSION_MSG);
	}

	/**
	 * Construct a new records handler with <code>errorMessage</code> as a
	 * description for exceptions thrown.
	 * 
	 * @param errorMessage
	 *            the description for thrown exception.
	 */
	public InvalidSessionRecordsHandler(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	public byte[] read(int recNo) {
		throw new IllegalStateException(errorMessage);
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	public int create(byte[] record) {
		throw new IllegalStateException(errorMessage);
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	public void update(int recNo, byte[] record) {
		throw new IllegalStateException(errorMessage);
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	public void delete(int recNo) {
		throw new IllegalStateException(errorMessage);
	}
}
