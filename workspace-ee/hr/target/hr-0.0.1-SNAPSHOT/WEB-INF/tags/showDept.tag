<%@ tag language="java" pageEncoding="UTF-8"
	import="hr.domain.DepartmentsInfo"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%
	Iterable<DepartmentsInfo> list = (Iterable<DepartmentsInfo>) request.getAttribute(name);
	for (DepartmentsInfo info : list) {
%>
<tr>
	<td><%=info.getDeptNumber()%></td>
	<td><%=info.getDeptName()%></td>
</tr>
<%
	}
%>