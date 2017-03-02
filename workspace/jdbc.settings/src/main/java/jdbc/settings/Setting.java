package jdbc.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Setting {

	private static Setting setting;
	private Properties properties = new Properties();
	private static final Object monitor = new Object();

	private Setting() {
		try (InputStream inputStream = this.getClass().getResourceAsStream("/jdbc.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new IllegalStateException("failed while loading configurations");
		}
	}

	public static Setting getSetting() {
		if (setting == null) {
			synchronized (monitor) {
				if (setting == null) {
					setting = new Setting();
				}
			}
		}
		return setting;
	}

	public String getDBUrl() {

		return getProperty("database.url");
	}

	public String getDBUsername() {

		return getProperty("database.username");
	}

	public String getDBPassword() {

		return getProperty("database.password");
	}

	public String getDBDriver() {

		return getProperty("database.driver.classname");
	}

	public int getDBPoolSize() {
		return getIntProperty("database.pool.size");
	}

	public int getDBMaxActive() {

		return getIntProperty("database.pool.maxActive");
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int getIntProperty(String key) {
		String value = getProperty(key);
		return Integer.valueOf(value);
	}

}
