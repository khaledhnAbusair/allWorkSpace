<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Departments</title>
</head>
<body>
	<h1>Departments</h1>

	<form action='<c:url  value="/dispatch/departments"/>' method="post">
		<table>

			<tr>
				<td>Departments No:</td>
				<td><input type="text" name="deptNo"></td>
			</tr>
			<tr>

				<td>Department Name :</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"></td>
			</tr>
		</table>




	</form>


	<table border="1px"
		style="text-align: center; background-color: powderblue;">
		<thead>
			<tr style="color: red;">
				<th style="font-size: 160%;">No.</th>
				<th style="font-size: 160%;">Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${departments}" var="dept">
				<tr>
					<td style="font-size: 160%;">${dept.deptNo}</td>
					<td style="font-size: 160%;">${dept.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>