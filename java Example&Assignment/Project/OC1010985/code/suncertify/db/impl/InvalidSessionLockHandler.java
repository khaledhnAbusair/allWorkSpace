package suncertify.db.impl;

import java.io.IOException;

import suncertify.db.Data.RecordsLockHandler;
import suncertify.db.RecordNotFoundException;

/**
 * A records lock handler which always throws <code>IllegalStateException</code>
 * from its methods to notify the caller that his session is invalid.
 * 
 * @author Mohammad S. Abdellatif
 */
class InvalidSessionLockHandler implements RecordsLockHandler {

	/**
	 * The default error message to be used when throwing exceptions.
	 */
	static final String INVALID_CLIENT_SESSION_MSG = "invalid client session";
	private final String errorMessage;

	/**
	 * Construct a new lock handler.
	 */
	public InvalidSessionLockHandler() {
		this(INVALID_CLIENT_SESSION_MSG);
	}

	/**
	 * Construct a new lock handler to use <code>errorMessage</code> as a
	 * description for exceptions thrown.
	 * 
	 * @param errorMessage
	 *            the description for thrown exceptions
	 */
	public InvalidSessionLockHandler(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	@Override
	public void lock(int recNo) {
		throw new IllegalStateException(errorMessage);
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	@Override
	public boolean isLocked(int recNo) {
		throw new IllegalStateException(errorMessage);
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	@Override
	public boolean isLockedByCaller(int recNo) throws RecordNotFoundException,
			IOException {
		throw new IllegalStateException(errorMessage);
	}

	/**
	 * Always throw <code>IllegalStateException</code> to indicate an invalid
	 * caller session.
	 */
	@Override
	public void unlock(int recNo) {
		throw new IllegalStateException(errorMessage);
	}
}
