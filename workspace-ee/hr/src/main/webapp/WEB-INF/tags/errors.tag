<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="name" type="java.lang.String"%>

<%
	String attributeName = name == null ? "errorMessage" : name;

	if (request.getAttribute(attributeName) != null) {
%>
<p>
	Failed :<%=request.getAttribute(attributeName)%></p>
<%
	}
%>