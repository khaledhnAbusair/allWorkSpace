/**
 * 
 */
package com.progressoft.jip.cachecontrol;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author PSLPT 147
 *
 */
@WebFilter(urlPatterns = { "*.css", "*.js" })
public class CacheControlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResp = (HttpServletResponse) response;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpResp) {
			@Override
			public void setHeader(String name, String value) {
				if ("Cache-Control".equalsIgnoreCase(name)) {
					return;
				}
				super.setHeader(name, value);
			}
		};

		httpResp.setHeader("Cache-Control", "public, max-age=" + (60 * 60));

		chain.doFilter(request, wrapper);
	}

	@Override
	public void destroy() {

	}

}
