package suncertify.pref;

/**
 * Defines the values for keys and default values passed to
 * <code>java.util.pref.Preferences</code> getter methods.
 * <p>
 * 
 * Each <code>PrefKey</code> defined has two attributes:
 * <ul>
 * <li>
 * Key represented by {@link #key}: the key passed to
 * <code>java.util.pref.Preferences</code> getter methods, the same value is
 * returned by {@link #getKey()}.</li>
 * <li>
 * Default Value represented by {@link #defaultValue}: the default value to be
 * used in case there is no value related for the key, the same value is
 * returned by {@link #getDefaultValue()}.</li>
 * </ul>
 * 
 * <p>
 * The following naming convention is used to separate the definition for each
 * user/system preferences type used in application:
 * <ul>
 * <li>
 * <code>SYSTEM_</code>: prefix used to define system related preferences</li>
 * <li>
 * <code>ADMIN_</code>: prefix used to define database administrator related
 * preferences.</li>
 * <li>
 * <code>USER_</code>: prefix used to define normal user related preferences.</li>
 * </ul>
 * 
 * @author Mohammad S. Abdellatif
 * @see PrefNode
 * @see PrefBean
 */
public enum PrefKey implements PrefNode<Object> {

	/**
	 * Defines the node for the last database file path selected by a database
	 * administrator for which a network service was successfully started.
	 */
	ADMIN_DB_FILE_PATH("admin.db.file.path"),
	/**
	 * Defines the node for the last service name used successfully by a
	 * database administrator to start a network service.
	 */
	ADMIN_SERVICE_NAME("admin.db.service.name"),
	/**
	 * Defines the node for the last service port used successfully by a
	 * database administrator to start a network service.
	 */
	ADMIN_SERVICE_PORT("admin.db.service.port"),
	/**
	 * Defines the node for the last host name successfully used by a user to
	 * connect to a database network service.
	 */
	USER_SERVICE_HOST("client.db.service.host"),
	/**
	 * Defines the node for the last service name successfully used by a user to
	 * connect to a database network service.
	 */
	USER_SERVICE_NAME("client.db.service.name"),
	/**
	 * Defines the node for the last service port successfully used by a client
	 * to connect to a database network service.
	 */
	USER_SERVICE_PORT("client.db.service.port"),
	/**
	 * Defines the node for the last database file path successfully used by a
	 * user locally.
	 */
	USER_LOCAL_DB_PATH("client.db.file.path"),
	/**
	 * Defines the node for the columns chosen by the user to be displayed.
	 */
	USER_VISIBLE_COLUMNS(
			"client.view.contractors.list.columns",
			"NAME,LOCATION,OWNER");
	/**
	 * Defines the key for preferences entry.
	 */
	public final String key;
	/**
	 * Defines the default value preferences.
	 */
	public final Object defaultValue;

	/**
	 * Construct a definition for preferences entry with key <code>key<code> and
	 * <code>null</code> default value.
	 * 
	 * @param key
	 *            the key for preferences entry.
	 */
	private PrefKey(String key) {
		this(key, null);
	}

	/**
	 * Construct a definition for preferences entry with key <code>key<code> and
	 * <code>defaultValue</code> default value.
	 * 
	 * @param key
	 *            the key for preferences entry.
	 * @param defaultValue
	 *            the default value for this key.
	 */
	private PrefKey(String key, Object defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	/**
	 * Returns key for this preferences node which is the same as {@link #key}.
	 * 
	 * @return the key for this preferences node.
	 */
	@Override
	public String getKey() {
		return key;
	}

	/**
	 * Returns the default value for this preferences node which is the same as
	 * {@link #defaultValue}.
	 * 
	 * @return the default value for this node.
	 */
	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}
}
