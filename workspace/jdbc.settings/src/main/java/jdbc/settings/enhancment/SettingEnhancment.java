package jdbc.settings.enhancment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SettingEnhancment implements SettingDB {

	private static final String URL = "jdbc:mysql://localhost:3306/employees";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final Object monitor = new Object();
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final int POOL_SIZE = 10;
	private static final int MAX_ACTIVE = 6;

	private Properties properties = new Properties();
	private static SettingDB setting;
	private SettingDB settingDB;

	private SettingEnhancment() {
		try (InputStream inputStream = this.getClass().getResourceAsStream("/jdbc.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new IllegalStateException("failed while loading configurations");
		}
	}

	public static SettingDB getSetting() {
		if (setting == null) {
			synchronized (monitor) {
				if (setting == null) {
					setting = new SettingEnhancment();
				}
			}
		}
		return setting;
	}

	@Override
	public String getDBUrl(String key) {
		return SettingDB.super.getDBUrl(URL);
		// return settingDB.getDBUrl(URL);
	}

	@Override
	public String getDBPassword(String key) {
		return SettingDB.super.getDBPassword(PASSWORD);
	}

	@Override
	public String getDBUsername(String key) {
		return SettingDB.super.getDBUsername(USERNAME);
	}

	@Override
	public String getDBDriver(String key) {
		return SettingDB.super.getDBDriver(DRIVER);
	}

	@Override
	public int getDBMaxActive(String key) {
		return SettingDB.super.getDBMaxActive(String.valueOf(MAX_ACTIVE));
	}

	@Override
	public int getDBPoolSize(String key) {
		return SettingDB.super.getDBPoolSize(String.valueOf(POOL_SIZE));
	}

	@Override
	public String getProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDBMaxActives(String key) {
		// TODO Auto-generated method stub
		return 0;
	}
}
