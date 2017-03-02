<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees</title>
</head>
<body>
	<c:if test="${failureError ne null }">
		<div style="color: red;">Something went wrong: ${failureError}</div>
	</c:if>
	<c:if test="${successMessage ne null }">
		<div style="color: green;">${successMessage}</div>
	</c:if>
	<c:if test="${errors ne null}">
		<div style="color: red;">
			<c:forEach items="${errors.allErrors}" var="error">
		${error.defaultMessage}<br />
			</c:forEach>
		</div>
	</c:if>
	<form action="<c:url value='/dispatch/employees' />" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName" /></td>
			</tr>
			<tr>
				<td>Position</td>
				<td><input type="text" name="position1" /></td>
			</tr>
			<tr>
				<td>Picture</td>
				<td><input type="file" name="picture" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" /></td>
			</tr>
		</table>
	</form>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Position</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
					<td>${employee.position}</td>
					<td><c:if test="${employee.picture ne null }">
							<img width="150" height="auto"
								src="<c:url value="/dispatch/employees/image/${employee.id}" />" />
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>