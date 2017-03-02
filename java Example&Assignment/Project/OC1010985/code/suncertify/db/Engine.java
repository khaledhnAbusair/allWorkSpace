/**
 * 
 */
package suncertify.db;

/**
 * Defines an engine for managing the access to a single database file data
 * through sessions.
 * 
 * <p>
 * An instance of this interface can be an engine managing the access to a local
 * database file exist in same caller JVM file system or a proxy for a remote
 * engine exist in another remote JVM which manages the access to a database
 * file exists in that JVM.
 * 
 * <p>
 * Method {@link #getDBSchema() } returns a description for the record structure
 * for the database file managed by this engine.
 * 
 * <p>
 * {@link Session} is the unique access point to manipulate a database file data
 * which can be created by calling {@link #newSession() }
 * 
 * <p>
 * Calling {@link #shutdown() } will result in stopping the engine from accepting
 * any new clients, discarding all sessions already attached to it, and free any
 * resources maintained by this engine. Shutting down an engine, by local or
 * remote engine clients, is not controlled by this API, the caller for this API
 * shall manages this.
 * 
 * @author Mohammad S. Abdellatif
 * @see Session
 * @see EngineService
 */
public interface Engine {

	/**
	 * Returns the magic cookie which is the unique identify for a database file
	 * type.
	 * <p>
	 * A database file which stores the contractors information has its unique
	 * cookie differ form a database file which stores an inventory data.
	 * 
	 * @return wrapped file magic cookie.
	 */
	public int getDatabaseMagicCookie();

	/**
	 * Gets the descriptor for database file record structure.
	 * <p>
	 * An <code>IllegalStateException</code> will be thrown if the engine is
	 * down.
	 * 
	 * @return database file record structure definition.
	 */
	public Schema getDBSchema();

	/**
	 * Creates a new unique session which represents a unique access to database
	 * file managed by this engine instance.
	 * 
	 * <p>
	 * An <code>IllegalStateException</code> will be thrown if the engine was
	 * shutdown.
	 * 
	 * @return new unique access.
	 */
	public Session newSession();

	/**
	 * Shutdown engine and automatically discard any sessions attached to it and
	 * release the lock over database file.
	 * 
	 * <p>
	 * Shutting down an engine by local or remote caller must be controlled by
	 * API caller.
	 */
	public void shutdown();

	/**
	 * Returns <code>true</code> if this engine instance is down, otherwise
	 * <code>false</code>.
	 * 
	 * @return returns <code>true</code> if engine is down, otherwise
	 *         <code>false</code>.
	 */
	public boolean isDown();
}
