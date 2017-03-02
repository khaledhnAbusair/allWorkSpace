package jdbc.settings.enhancment;

public interface SettingDB {

	default String getDBUrl(String key) {
		return getProperty(key);
	}

	default String getDBUsername(String key) {
		return getProperty(key);
	}

	default String getDBPassword(String key) {
		return getProperty(key);
	}

	default String getDBDriver(String key) {
		return getProperty(key);
	}

	default int getDBPoolSize(String key) {
		return getIntProperty(key);
	}

	default int getDBMaxActive(String key) {
		return getIntProperty(key);
	}

	int getDBMaxActives(String key);

	String getProperty(String key);

	default int getIntProperty(String key) {
		String value = getProperty(key);
		return Integer.valueOf(value);
	}

}