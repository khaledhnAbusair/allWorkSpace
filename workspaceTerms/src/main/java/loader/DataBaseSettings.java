package loader;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class DataBaseSettings {

	private static DataBaseSettings dataBaseSettings;

	private Properties properties;

	private DataBaseSettings() {
		try {
			properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("configurations/database.properties"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static DataBaseSettings getInstance() {
		if (Objects.isNull(dataBaseSettings)) {
			synchronized (DataBaseSettings.class) {
				if (Objects.isNull(dataBaseSettings)) {
					dataBaseSettings = new DataBaseSettings();
				}
			}
		}
		return dataBaseSettings;
	}

	public String username() {
		return properties.getProperty("username");
	}

	public String password() {
		return properties.getProperty("password");
	}

	public String url() {
		return properties.getProperty("url");
	}

	public String driverClassName() {
		return properties.getProperty("driverClassName");
	}

}
