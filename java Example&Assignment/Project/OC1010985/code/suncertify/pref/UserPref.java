package suncertify.pref;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import suncertify.ui.client.ContractorsTabularViewPanel.Column;

/**
 * A bean for getting, setting, and storing preferences for a normal user.
 * 
 * @author Mohammad S. Abdellatif
 */
public class UserPref extends PrefBean {

	/**
	 * Construct a new user preferences bean.
	 */
	public UserPref() {
		super(Preferences.userRoot());
	}

	/**
	 * Gets the local database file used successfully by the user to connect to
	 * it.
	 * 
	 * @return last database file used by the user to connect to or
	 *         <code>null</code> if not specified.
	 */
	public File getDBFile() {
		String value = get(PrefKey.USER_LOCAL_DB_PATH);
		return value == null ? null : new File(value);
	}

	/**
	 * Sets the local database file successfully used by the user to connect to.
	 * 
	 * @param file
	 *            local database file.
	 */
	public void setDBFile(File file) {
		try {
			String canonicalPath = file.getCanonicalPath();

			preferences.put(PrefKey.USER_LOCAL_DB_PATH.key, canonicalPath);
		} catch (IOException ex) {
			Logger.getLogger(UserPref.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	/**
	 * Returns the host name used successfully by the user to connect to a
	 * database network service.
	 * 
	 * @return host name used by user to connect to a database network service.
	 */
	public String getHost() {
		return preferences.get(PrefKey.USER_SERVICE_HOST.key,
				(String) PrefKey.USER_SERVICE_HOST.defaultValue);
	}

	/**
	 * Sets the host name used successfully by the user to connect to a database
	 * network service.
	 * 
	 * @param host
	 *            host name successfully used by user to connect to a database
	 *            network service.
	 */
	public void setHost(String host) {
		preferences.put(PrefKey.USER_SERVICE_HOST.key, host);
	}

	/**
	 * Returns the service name successfully used by the user to connect to a
	 * database service.
	 * 
	 * @return service name successfully used by the user to connect to a
	 *         database service.
	 */
	public String getServiceName() {
		return preferences.get(PrefKey.USER_SERVICE_NAME.key,
				(String) PrefKey.USER_SERVICE_NAME.defaultValue);
	}

	/**
	 * Sets the service name successfully used by user to connect to a database
	 * network service.
	 * 
	 * @param serviceName
	 *            service name used to connect to a database network service.
	 */
	public void setServiceName(String serviceName) {
		preferences.put(PrefKey.USER_SERVICE_NAME.key, serviceName);
	}

	/**
	 * Gets the service port successfully used by user to connect to a database
	 * network service.
	 * 
	 * @return service port successfully used by user to connect to a database
	 *         network service.
	 */
	public Integer getPort() {
		String value =
				preferences.get(PrefKey.USER_SERVICE_PORT.key,
						(String) PrefKey.USER_SERVICE_PORT.defaultValue);
		return value == null ? null : new Integer(value);
	}

	/**
	 * Sets the service port successfully used by user to connect to a database
	 * network service.
	 * 
	 * @param port
	 *            service port successfully used by user to connect to a
	 *            database network service.
	 */
	public void setPort(Integer port) {
		preferences.put(PrefKey.USER_SERVICE_PORT.key,
				port == null ? "" : port.toString());
	}

	/**
	 * Sets the visible columns chosen by the user to display contractors
	 * records in.
	 * 
	 * @param columns
	 *            visible columns chosen by the user.
	 */
	public void setVisibleColumns(Column[] columns) {
		StringBuilder names = new StringBuilder();
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];

			names.append(column.name()).append(",");
		}
		if (names.length() > 0) {
			names.deleteCharAt(names.length() - 1);
		}
		preferences.put(PrefKey.USER_VISIBLE_COLUMNS.key, names.toString());
	}

	/**
	 * Gets the visible columns last chosen by the user to display contractors
	 * information in.
	 * 
	 * @return visible columns chosen by the user.
	 */
	public Column[] getVisibleColumns() {
		String[] names = get(PrefKey.USER_VISIBLE_COLUMNS).split(",");
		Column[] columns = new Column[names.length];
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			columns[i] = Column.valueOf(name);
		}
		return columns;
	}
}
