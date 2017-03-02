package suncertify.pref;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * A bean for setting, getting, and storing administrator preferences.
 * 
 * @author Mohammad S. Abdellatif
 */
public class AdminPref extends PrefBean {

	/**
	 * Construct a new administrator preferences bean.
	 */
	public AdminPref() {
		super(Preferences.userRoot());
	}

	/**
	 * Sets database file pointer used by administrator to start network service
	 * for.
	 * 
	 * @param file
	 *            database file pointer.
	 */
	public void setDBFileLocation(File file) {
		try {
			String canonicalPath = file.getCanonicalPath();

			preferences.put(PrefKey.ADMIN_DB_FILE_PATH.key, canonicalPath);
		} catch (IOException ex) {
			Logger.getLogger(UserPref.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	/**
	 * Gets last database file pointer used by administrator to start a network
	 * service successfully.
	 * 
	 * @return last database file pointer.
	 */
	public File getDBFileLocation() {
		String value =
				preferences.get(PrefKey.ADMIN_DB_FILE_PATH.key,
						(String) PrefKey.ADMIN_DB_FILE_PATH.defaultValue);
		return value == null ? null : new File(value);
	}

	/**
	 * Sets the service name used by an administrator to start a database
	 * network service with.
	 * 
	 * @param serviceName
	 *            database network service name used by administrator.
	 */
	public void setServiceName(String serviceName) {
		preferences.put(PrefKey.ADMIN_SERVICE_NAME.key, serviceName);
	}

	/**
	 * Returns the service name used by an administrator to start a database
	 * network service.
	 * 
	 * @return service name for database network service used by administrator.
	 */
	public String getServiceName() {
		return get(PrefKey.ADMIN_SERVICE_NAME);
	}

	/**
	 * Sets the service port used by an administrator to start a database
	 * network service with.
	 * 
	 * @param port
	 *            database network service port used by administrator.
	 */
	public void setPort(Integer port) {
		preferences.putInt(PrefKey.ADMIN_SERVICE_PORT.key, port);
	}

	/**
	 * Returns the service port used by administrator to start a database
	 * network service.
	 * 
	 * @return service port for database network service used by administrator.
	 */
	public Integer getPort() {
		String value =
				preferences.get(PrefKey.ADMIN_SERVICE_PORT.key,
						(String) PrefKey.ADMIN_SERVICE_PORT.defaultValue);
		return value == null ? null : new Integer(value);
	}
}
