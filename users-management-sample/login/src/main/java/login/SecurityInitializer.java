/**
 * 
 */
package login;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author PSLPT 147
 *
 */
public class SecurityInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		LoginServlet servlet = new LoginServlet(new MemoryAuthenticationManager());
		ServletRegistration.Dynamic dynamic = ctx.addServlet("LoginServlet", servlet);
		dynamic.addMapping("/login", "/login.jsp");

		LoginFilter loginFilter = new LoginFilter();
		FilterRegistration.Dynamic filter = ctx.addFilter("LoginFilter", loginFilter);
		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		filter.setInitParameter("urls.exclude", "/login,/static/[\\w\\W]*");
	}

}
