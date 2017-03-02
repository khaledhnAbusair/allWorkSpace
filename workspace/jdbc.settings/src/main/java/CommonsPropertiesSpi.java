import java.util.Arrays;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import jdbc.settings.enhancment.SettingDB;

public class CommonsPropertiesSpi implements SettingDB {
	private Configuration configuration;

	public CommonsPropertiesSpi() {
		try {
			Configuration proparties = new PropertiesConfiguration("/jdbc-congif.properties");
			XMLConfiguration xmlConfiguration = new XMLConfiguration("/jdbc-settings.xml");
			configuration = new CompositeConfiguration(Arrays.asList(proparties, xmlConfiguration));

		} catch (ConfigurationException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String getProperty(String key) {
		return configuration.getString(key);
	}

	@Override
	public int getDBMaxActives(String key) {
		return configuration.getInt(key);
	}

}
