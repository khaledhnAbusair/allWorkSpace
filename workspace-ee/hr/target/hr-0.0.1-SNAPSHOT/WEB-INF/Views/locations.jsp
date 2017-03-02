
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param value="Locations" name="title" />
</jsp:include>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-8">
			<table class="table table-hover">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Latitude</th>
					<th>Longitude</th>
					<th>Description</th>
				</tr>
				<core:forEach items="${locationsList}" var="location">
					<tr>
						<td><core:out value="${location.id}"></core:out></td>
						<td><core:out value="${location.name}"></core:out></td>
						<td><core:out value="${location.latitude}"></core:out></td>
						<td><core:out value="${location.longitude}"></core:out></td>
						<td><core:out value="${location.description}"></core:out></td>
					</tr>
				</core:forEach>
			</table>
		</div>
	</div>

	<div class="col-xs-8">

		<form action="<core:url value='/locations'/>" method="post">
			<div class="form-group">
				<label for="exampleInputEmail1">Name</label> <input type="text"
					name="name" class="form-control" id="exampleInputEmail1">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Latitude</label> <input
					type="text" name="latitude" class="form-control">
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Longitude</label> <input type="text"
					name="longitude" class="form-control">
			</div>
			<div class="form-group">
				<label for="exampleInputFile">Description</label>
				<textarea name="description" rows="5" cols="10" class="form-control"></textarea>
			</div>
			<button type="submit" class="btn btn-success btn-lg">Submit</button>
		</form>

	</div>
</div>
<%@include file="/WEB-INF/includes/footer.jsp"%>
