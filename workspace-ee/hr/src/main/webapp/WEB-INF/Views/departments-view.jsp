<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ps" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="utils" uri="http://www.progressoft.com/taglib"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Departments" name="title" />
</jsp:include>

<core:if test="${cookie['post-counts']ne null} ">
	<div class="alert alert-warning alert-dismissible" role="alert">
		you added : ${cookie['post-counts'].value} Departments</div>
</core:if>


<p class="lead">Departments Information</p>



<table border='1' class="table table-hover">
	<thead>
		<tr>
			<th class="text-center">No.</th>
			<th class="text-center">Name</th>
		</tr>
	</thead>
	<tbody>

		<core:forEach var="department" items="${requestScope.departmentsList}">

			<tr>

				<td class="text-center"><core:out
						value=" ${department.deptNumber}"></core:out></td>
				<td class="text-center"><core:out
						value="${department.deptName}"></core:out></td>
			</tr>

		</core:forEach>
	</tbody>
</table>
<ps:errors />

<form action="/departments" method="post" class="form-horizontal">
	<table>
		<tr>
			<td>No.:</td>
			<td><input class="form-control" type="text" name="deptNumber"
				required
				value="${param['deptNumber'] eq null ?'':param['deptNumber']} " /></td>
		</tr>
		<tr>
			<td>Name:</td>
			<td><input class="form-control" type="text" name="deptName"
				required value="${param['deptName'] eq null ?'':param['deptName']} " /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				class="btn btn-success btn-lg" /></td>
		</tr>
	</table>
</form>

<%@include file="/WEB-INF/includes/footer.jsp"%>
