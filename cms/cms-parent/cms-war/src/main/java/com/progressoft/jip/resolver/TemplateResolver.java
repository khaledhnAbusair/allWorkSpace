package com.progressoft.jip.resolver;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class TemplateResolver implements ViewResolver {

	private static final String POST = "POST";
	private static final String PAGE_CONTENT = "pageContent";
	private static final String LOGIN = "Login";
	private static final String JSP = ".jsp";
	private ViewResolver viewResolver;

	public TemplateResolver(ViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		View baseView = viewResolver.resolveViewName("/base", locale);
		View loginView = viewResolver.resolveViewName("/Login", locale);

		return new View() {
			@Override
			public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				String method = request.getMethod();
				if (!method.equals(POST)) {
					if (viewName.equals(LOGIN)) {
						loginView.render(model, request, response);
					} else {
						request.setAttribute(PAGE_CONTENT, viewName + JSP);
						baseView.render(model, request, response);
					}
				} else {
					viewResolver.resolveViewName(viewName, locale).render(model, request, response);
				}
			}

			@Override
			public String getContentType() {
				return baseView.getContentType();
			}
		};
	}

}
