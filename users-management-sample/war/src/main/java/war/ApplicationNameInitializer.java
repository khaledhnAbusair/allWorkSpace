/**
 * 
 */
package war;

import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author PSLPT 147
 *
 */
@WebListener
public class ApplicationNameInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		String parameter = servletContext.getInitParameter("application.name");
		if (Objects.isNull(parameter)) {
			throw new IllegalArgumentException("expected context parameter: application.name");
		}
		servletContext.setAttribute("applicationName", parameter);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
