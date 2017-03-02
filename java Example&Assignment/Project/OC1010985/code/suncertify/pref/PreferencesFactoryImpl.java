package suncertify.pref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

/**
 * An implementation for <code>java.util.prefs.PreferencesFactory</code> used to
 * load and store user and system preferences from properties file in current
 * working directory.
 * <p>
 * 
 * This implementation for preferences API can only be used in a single user
 * environment, system root preferences and user preferences are constructed
 * only once.
 * <p>
 * The expected properties file name is <code>suncertify.properties</code>,
 * prefix <code>user.</code> is used to distinguish user from system properties
 * in this file, there is no need to include this prefix when passing key to
 * getter methods for <code>Preferences</code> instances created by
 * {@link #userRoot()} because it is included when getting and persisting
 * values.
 * 
 * @author Mohammad S. Abdellatif
 */
public class PreferencesFactoryImpl implements PreferencesFactory {

	/**
	 * Holds the name for properties file name
	 * <code>suncertify.properties</code>.
	 */
	public static final String PREFERENCES_FILE_NAME = "suncertify.properties";
	/**
	 * Holds the description for preferences file.
	 */
	public static final String PREFERENCES_FILE_DESC =
			"User and system preferences";
	/**
	 * Defines the prefix for system preferences keys.
	 */
	public static final String SYSTEM_PREFERENCES_PREFIX = "";
	/**
	 * Defines the prefix for user preferences keys.
	 */
	private static final String USER_PREFERENCES_PREFIX = "user";
	/**
	 * System preferences node.
	 */
	private PreferencesImpl system;
	/**
	 * User preferences node.
	 */
	private PreferencesImpl user;
	/**
	 * suncertify.properties file exist in current working directory.
	 */
	private Properties store;

	/**
	 * Construct the singleton system root preferences with <code>null</code>
	 * parent node.
	 * 
	 * @return singleton system root preferences.
	 */
	@Override
	public Preferences systemRoot() {
		if (system == null) {
			Properties wholeStore = getStore();
			Properties nodePref = new Properties();
			Set<String> keys = wholeStore.stringPropertyNames();

			for (String key : keys) {
				if (!key.startsWith(USER_PREFERENCES_PREFIX)) {
					nodePref.setProperty(key, wholeStore.getProperty(key));
				}
			}
			system =
					new PreferencesImpl(this, null, "system",
							SYSTEM_PREFERENCES_PREFIX, nodePref);

		}
		return system;
	}

	/**
	 * Construct/Return the singleton user preferences node with system node as
	 * parent node.
	 * 
	 * @return singleton user preferences node.
	 */
	@Override
	public Preferences userRoot() {
		if (user == null) {
			Properties wholeStore = getStore();
			Properties nodePref = new Properties();
			Set<String> keys = wholeStore.stringPropertyNames();

			for (String key : keys) {
				if (key.startsWith(USER_PREFERENCES_PREFIX)) {
					nodePref.setProperty(key, wholeStore.getProperty(key));
				}
			}
			user =
					new PreferencesImpl(this, system,
							SYSTEM_PREFERENCES_PREFIX, USER_PREFERENCES_PREFIX
									+ ".", nodePref);
		}
		return user;
	}

	/**
	 * Persist preferences stored in <code>nodePref</code> to properties file
	 * <code>suncertify.properties</code>.
	 * 
	 * @throws BackingStoreException
	 *             when persisting preferences to properties file is failed.
	 */
	void persist(Properties nodePref) throws BackingStoreException {
		File file = getPreferencesFile();
		FileOutputStream output = null;
		boolean completed = false;
		try {
			file.createNewFile();
			output = new FileOutputStream(file);
			store.putAll(nodePref);
			store.store(output, PREFERENCES_FILE_DESC);
			output.flush();
			completed = true;
		} catch (IOException ex) {
			throw new BackingStoreException(ex);
		} finally {
			try {
				output.close();
			} catch (IOException ex) {
				if (completed) {
					// if persisting is completed and closing of stream is
					// failed notify the caller for closing the stream exception
					throw new BackingStoreException(ex);
				}// else ignore to throw the original exception
			}
		}
	}

	/**
	 * Load system and user preferences from properties file
	 * <code>suncertify.properties</code> that exist in current working
	 * directory, if not exist, return a <code>Properties</code> without any
	 * entry.
	 * 
	 * @return system and user preferences loaded from
	 *         <code>suncertify.properties</code> which may exist in current
	 *         working directory.
	 */
	private Properties getStore() {
		if (store == null) {
			File prefFile = getPreferencesFile();

			store = new Properties();
			if (prefFile.exists() && prefFile.isFile()) {
				FileInputStream input = null;
				try {
					input = new FileInputStream(prefFile);
					store.load(input);
				} catch (IOException ex) {
					throw new IllegalStateException(
							"unable to read preferences file", ex);
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (IOException ex) {
							// ignore
						}
					}
				}
			}

		}
		return store;
	}

	/**
	 * Returns a reference for preferences file with name
	 * <code>suncertify.properties</code> which exist under current working
	 * directory.
	 * 
	 * @return a reference to <code>suncertify.properties</code> user current
	 *         working directory.
	 */
	private File getPreferencesFile() {
		String curDir = System.getProperty("user.dir");
		File prefFile =
				new File(curDir + File.separator + PREFERENCES_FILE_NAME);
		return prefFile;
	}
}
