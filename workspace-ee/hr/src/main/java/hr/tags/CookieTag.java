package hr.tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public class CookieTag extends SimpleTagSupport {

	private String name;

	@Override
	public void doTag() throws JspException, IOException {
		PageContext context = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		Cookie[] cookies = request.getCookies();
		if (!Objects.isNull(cookies)) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					JspWriter jspWriter = context.getOut();
					jspWriter.println(cookie.getValue());
					jspWriter.flush();
					break;
				}

			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
