<%@page import="hr.domain.DepartmentsInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Departments</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>No.</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<%
				Iterable<DepartmentsInfo> list = (Iterable<DepartmentsInfo>) request.getAttribute("departmentsList");
				for (DepartmentsInfo info : list) {
			%>
			<tr>
				<td><%=info.getDeptNumber()%></td>
				<td><%=info.getDeptName()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<% 
	if(request.getAttribute("errorMessage") !=null){
	%>
	<p> Failed :</p><%= request.getAttribute("errorMessage") %>
<%

	}
%>	
	
	<form action="/departments" method="post">
		<table>
			<tr>
				<td>No.:</td>
				<td><input type="text" name="deptNo" required /></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>