package suncertify.pref;

import java.util.Properties;
import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;

/**
 * A <code>java.util.prefs.Preferences</code> implementation used by
 * {@link PreferencesFactoryImpl}.
 * <p>
 * 
 * This implementation read/store preferences from/to an instance of
 * <code>java.util.Properties</code>. The keys passed to <code>get</code> and
 * <code>put</code> methods are prefixed with a string passed to class instance.
 * <br>
 * No preferences nesting is supported by this class, each node does not has a
 * child node and it has only a parent passed through its constructor.
 * 
 * @author Mohammad S. Abdellatif
 * @see PreferencesFactoryImpl
 */
class PreferencesImpl extends AbstractPreferences {

	private final Properties preferences;
	private final String keyPrefix;
	private final PreferencesFactoryImpl factory;

	/**
	 * Construct new preferences node.
	 * 
	 * @param factory
	 *            preferences factory for which this node is related.
	 * @param parent
	 *            the parent for this node.
	 * @param name
	 *            the name for this node.
	 * @param keyPrefix
	 *            the prefix to use to read/store preferences from/to passed
	 *            properties <code>preferences</code>.
	 * @param preferences
	 *            properties map which holds preferences values.
	 */
	public PreferencesImpl(PreferencesFactoryImpl factory,
			AbstractPreferences parent, String name, String keyPrefix,
			Properties preferences) {
		super(parent, name);
		this.preferences = preferences;
		this.keyPrefix = keyPrefix;
		this.factory = factory;
	}

	/**
	 * Sets preference <code>key</code> value to <code>value</code>.
	 * 
	 * @param key
	 *            preference key.
	 * @param value
	 *            preference value.
	 */
	@Override
	protected void putSpi(String key, String value) {
		preferences.setProperty(keyPrefix + key, value);
	}

	/**
	 * Gets the value of preference <code>key</code>.
	 * 
	 * @param key
	 *            preference to get its value.
	 * @return preference value.
	 */
	@Override
	protected String getSpi(String key) {
		return preferences.getProperty(keyPrefix + key);
	}

	/**
	 * Remove preference <code>key</code>.
	 * 
	 * @param key
	 *            preference to remove.
	 */
	@Override
	protected void removeSpi(String key) {
		preferences.remove(keyPrefix + key);
	}

	/**
	 * Remove this preference node, which do nothing.
	 */
	@Override
	protected void removeNodeSpi() throws BackingStoreException {
		// ignore
	}

	/**
	 * Returns the preferences keys related to this node.
	 * 
	 * @return preferences keys for this node.
	 */
	@Override
	protected String[] keysSpi() throws BackingStoreException {
		return preferences.stringPropertyNames().toArray(
				new String[preferences.size()]);
	}

	/**
	 * Returns the names for children preferences nodes under this node.
	 * 
	 * @return allays zero length array because it has no children.
	 */
	@Override
	protected String[] childrenNamesSpi() throws BackingStoreException {
		return new String[0];
	}

	/**
	 * Returns the child node with name <code>name</code>.
	 * 
	 * @return always <code>null</code>.
	 */
	@Override
	protected AbstractPreferences childSpi(String name) {
		return null;
	}

	/**
	 * Synchronize this node with preferences persistence storage.
	 */
	@Override
	protected void syncSpi() throws BackingStoreException {
		// the same as flush
		flush();
	}

	/**
	 * Flush an changes made on this code to persistence storage.
	 */
	@Override
	protected void flushSpi() throws BackingStoreException {
		factory.persist(preferences);
	}
}
