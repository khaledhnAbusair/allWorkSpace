/**
 * 
 */
package suncertify.db;

/**
 * Defines a unique access to a database file engine.
 * <p>
 * 
 * An instance can be obtained by calling {@link Engine#newSession()}. Each
 * session created by a database engine has its unique ID and access to the
 * database file data through interface {@link DBMain} returned by calling
 * {@link #getDataAccess()}. {@link #discard()} discards this session from use,
 * discarding a session free all resources locked by this session.
 * <p>
 * 
 * 
 * @see Engine
 * @see DBMain
 * @author Mohammad S. Abdellatif
 * 
 */
public interface Session {

	/**
	 * The unique ID for this session cross same engine.
	 * 
	 * @return session unique ID.
	 */
	public String getId();

	/**
	 * Get the engine which this session belongs to.
	 * 
	 * @return session engine.
	 */
	public Engine getEngine();

	/**
	 * Get this session access to database file records.
	 * <p>
	 * 
	 * Multiple calls to this method will return the same instance and it will
	 * throw an {@link IllegalStateException} in case {@link #discard()} was
	 * called.
	 * 
	 * @return session access to database file records.
	 */
	public DBMain getDataAccess();

	/**
	 * Discard this session for use and release all resources locked by it.
	 * <p>
	 * Any call to instance method returned by {@link #getDataAccess()} will
	 * throw an {@link IllegalStateException}.
	 */
	public void discard();

	/**
	 * Returns if this session is valid or not.
	 * <p>
	 * A session is invalid for mostly after calling {@link #discard()}.
	 * 
	 * @return <code>true</code> if a session is valid for use, otherwise,
	 *         <code>false</code>.
	 */
	public boolean isValid();
}
