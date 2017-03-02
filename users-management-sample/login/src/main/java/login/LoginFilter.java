/**
 * 
 */
package login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author PSLPT 147
 *
 */
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

	private static final String USER_INFO_ATTR = "userInfo";
	private String[] urlsToExcludes;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludes = filterConfig.getInitParameter("urls.exclude");
		urlsToExcludes = excludes.split(",");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		boolean excluded = isURLExcluded(httpReq);
		HttpSession session = httpReq.getSession(false);

		if (!excluded && (session == null || session.getAttribute(USER_INFO_ATTR) == null)) {
			HttpServletResponse httpResp = (HttpServletResponse) response;
			httpResp.sendRedirect(httpReq.getContextPath() + "/login");
			return;
		}
		SecurityContext context = new SecurityContext();
		if (session != null && session.getAttribute(USER_INFO_ATTR) != null) {
			context.setUserInfo((UserInfo) session.getAttribute(USER_INFO_ATTR));
		}
		SecurityContext.setSecurityContext(context);
		try {
			// proceed
			chain.doFilter(request, response);
			//
		} finally {
			SecurityContext.clear();
		}

	}

	private boolean isURLExcluded(HttpServletRequest httpReq) {
		String url = httpReq.getServletPath() + (httpReq.getPathInfo() == null ? "" : httpReq.getPathInfo());
		for (String exclude : urlsToExcludes) {
			if (url.matches(exclude)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {

	}

}
