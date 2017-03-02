/**
 * 
 */
package samples.security;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * @author PSLPT 147
 *
 */
public interface UserInfo extends Serializable {

	public String getUsername();

	public String getFullName();

	public default Map<String, Object> getAdditionalInfo() {
		return Collections.emptyMap();
	}

	public default <T> T getAdditionalInfo(String key) {
		return getAdditionalInfo(key);
	}

}
