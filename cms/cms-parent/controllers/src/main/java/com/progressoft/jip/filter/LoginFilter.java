package com.progressoft.jip.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	private static final String DISPATCH_LOGIN = "/dispatch/login";
	private static final String URLS_EXCLUDE = "urls.exclude";
	private static final String USER_NAME = "userName";
	private String[] urlsToExcludes;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String exclude = filterConfig.getInitParameter(URLS_EXCLUDE);
		urlsToExcludes = exclude.split(",");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		boolean urlExcluded = isURLExcluded(servletRequest);
		HttpSession session = servletRequest.getSession();

		if (!urlExcluded && (Objects.isNull(session) || Objects.isNull(session.getAttribute(USER_NAME)))) {
			HttpServletResponse httpResp = (HttpServletResponse) response;
			httpResp.sendRedirect(servletRequest.getContextPath() + DISPATCH_LOGIN);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// Nothing
	}

	private boolean isURLExcluded(HttpServletRequest httpReq) {
		String url = httpReq.getServletPath() + (Objects.isNull(httpReq.getPathInfo()) ? "" : httpReq.getPathInfo());
		for (String exclude : urlsToExcludes) {
			if (url.matches(exclude)) {
				return true;
			}
		}
		return false;
	}
}
