<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page errorPage="error.jsp"%>
<div class="container">

	<c:if test="${not empty error}">
		<div class="col-xs-12">
			<c:out value="${error}">${error}</c:out>
			&nbsp;
		</div>
	</c:if>

	<h1 class="page-header">Delete Category</h1>
	<div class="row">
		<form method="post" class="form-horizontal" action="./deleteCategory"
			id="deleteForm">
			<div class="col-xs-6 form-horizontal">
				<div class="row form-group">
					<div class="col-xs-12">Category Name</div>
					<div class="col-xs-12">
						<input type="text" name="catName" class="form-control"
							value="${sessionScope.name}" disabled /> <input type="hidden"
							name="name" class="form-control" value="${sessionScope.name}" />
					</div>
				</div>

				<div class="row form-group">
					<div class="col-xs-12">Allows Children</div>
					<div class="col-xs-12">
						<input type="text" name="allowChild" class="form-control"
							value="${sessionScope.child}" disabled /> <input type="hidden"
							name="child" class="form-control" value="${sessionScope.child}" />
					</div>

				</div>
				<div class="pull-right">

					<button type="submit" class="btn btn-danger" id="delete">
						<span class="glyphicon glyphicon-trash"></span>Are you sure you
						want to delete category
					</button>
					<a href="./manageCategories" type="button" id="back"
						class="btn btn-default">Back</a>
				</div>
			</div>

		</form>
	</div>
</div>
