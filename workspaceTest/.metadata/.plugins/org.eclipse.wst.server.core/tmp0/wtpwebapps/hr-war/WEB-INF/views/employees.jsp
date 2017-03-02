<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
Iterable<Employee>

.alert {
	padding: 20px;
	background-color: #f44336;
	color: white;
}

.btnSubmit {
	margin-left: 15px;
	color: white;
	font-weight: bold;
	float: right;
	font-size: 10px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
</head>
<body>

	<c:if test="${ SuccessMessage ne null}">
		<div class="alert">
			<span class="closebtn"
				onclick="this.parentElement.style.display='none';">&times;</span> <strong>Success
				Message :)</strong>
			<h4>${SuccessMessage}</h4>
		</div>

	</c:if>
	<c:if test="${errors ne null }">
		<div class="alert">
			<span class="closebtn"
				onclick="this.parentElement.style.display='none';">&times;</span> <strong>Danger!</strong>
			<c:forEach items="${errors.allErrors}" var="error">
				<h4>${error.defaultMessage}</h4>
			</c:forEach>
		</div>
	</c:if>
	<form action="<c:url value='/dispatch/employees'/>" method="post"
		enctype="multipart/form-data">
		<table>

			<tr>
				<td>ID</td>
				<td><input type="text" name="id" readonly
					placeholder="Cannot insert id "></td>
			</tr>

			<tr>
				<td>FirstName</td>
				<td><input type="text" name="firstName"></td>
			</tr>
			<tr>
				<td>LastName</td>
				<td><input type="text" name="lastName"></td>
			</tr>
			<tr>
				<td>Position</td>
				<td><input type="text" name="position"></td>
			</tr>
			<tr>
				<td>Picture</td>
				<td><input type="file" name="picture"></td>
			</tr>
			<tr>
				<td colspan="2"><input id="btnSubmit" type="submit"></td>
			</tr>
		</table>
	</form>

	<table>
		<thead>
			<tr>
				<td>ID</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Position</td>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td>${employee.id }</td>
					<td>${employee.firstName }</td>
					<td>${employee.lastName }</td>
					<td>${employee.position }</td>
					<td><c:if test="${ employee.picature ne null}"></c:if> <img
						width="150" height="auto"
						src="<c:url value="/dispatch/employees/image/${employee.id}"/>" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>