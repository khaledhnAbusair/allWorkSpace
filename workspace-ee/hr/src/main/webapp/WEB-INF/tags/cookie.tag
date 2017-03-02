<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="cookieName" type="java.lang.String" required="true"%>

<%
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
%>

<%=cookie.getValue()%>
<%
	break;
			}
		}
	}
%>