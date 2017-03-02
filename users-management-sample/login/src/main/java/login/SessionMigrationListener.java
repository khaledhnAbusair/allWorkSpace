/**
 * 
 */
package login;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * @author PSLPT 147
 *
 */
public class SessionMigrationListener implements HttpSessionActivationListener {

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {

	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {

	}

}
