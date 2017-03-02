package suncertify.db.impl;

import java.io.IOException;

import suncertify.db.Data.RecordsHandler;
import suncertify.db.RecordNotFoundException;

/**
 * A record handler which passes the calls to a wrapped {@link RecordsHandler}
 * instance.
 * <p>
 * It is mainly used by {@link LocalEngine} to change the behavior of records
 * handling at runtime when a session is discarded.
 * 
 * @author Mohammad S. Abdellatif
 * @see LocalEngine
 */
class RecordsHandlerDelegate implements RecordsHandler {

	private RecordsHandler handler;

	/**
	 * Construct a new delegate which pass all calls to <code>handler</code>.
	 * 
	 * @param handler
	 *            handler to pass call to.
	 */
	public RecordsHandlerDelegate(RecordsHandler handler) {
		this.handler = handler;
	}

	/**
	 * Sets the handler to which pass calls.
	 * 
	 * @param handler
	 *            handler to pass call to.
	 */
	public void setHandler(RecordsHandler handler) {
		this.handler = handler;
	}

	/*
     * 
     */
	@Override
	public byte[] read(int recNo) throws RecordNotFoundException, IOException {
		return handler.read(recNo);
	}

	/*
     * 
     */
	@Override
	public int create(byte[] record) throws IOException {
		return handler.create(record);
	}

	/*
     * 
     */
	@Override
	public void update(int recNo, byte[] record)
			throws RecordNotFoundException, IOException {
		handler.update(recNo, record);
	}

	/*
     * 
     */
	@Override
	public void delete(int recNo) throws RecordNotFoundException, IOException {
		handler.delete(recNo);
	}
}
