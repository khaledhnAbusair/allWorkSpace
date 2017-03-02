<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%
		String name = request.getParameter("name");
		String child = request.getParameter("child");
	%>
	<c:if test="${not empty error}">
		<div class="col-xs-12">
			<c:out value="${error}">${error}</c:out>
			&nbsp;
		</div>
	</c:if>
	<jsp:useBean id="termCategoryJpaRepositorey" scope="session"
		class="impl.TermCategoryJpaRepositorey"></jsp:useBean>
	<h1 class="page-header">Manage Categories</h1>
	<div class="row">
		<div class="col-xs-12">
			<button class="btn btn-primary" data-toggle="modal"
				data-target="#newCategoryModal">
				<i class="glyphicon glyphicon-plus"></i>Add
			</button>
			&nbsp;
		</div>
		<div class="col-xs-8">
			<form method="post" action="./manageCategories">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Category</th>
							<th>Allows Children</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${loadCategories}">
							<tr>
								<td id="catName"><c:out value="${item.catName}" /></td>
								<td id="allowChild"><c:out value="${item.allowChildren}" /></td>
								<td><a class="btn btn-info"
									href="./editCategory?name=${item.catName}&child=${item.allowChildren}"
									role="button"> <span class="glyphicon glyphicon-pencil"></span>Edit
								</a></td>
								<td><a class="btn btn-danger"
									href="./deleteCategory?name=${item.catName}&child=${item.allowChildren}"
									role="button"> <span class="glyphicon glyphicon-trash"></span>Delete
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>
<!-- /container -->
<div class="modal fade" id="newCategoryModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<form action="./manageCategories" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add Category</h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="row form-group">
							<div class="col-xs-12">Name</div>
							<div class="col-xs-12">
								<input type="text" name="name" id="name" class="form-control"
									placeholder="Name" required autofocus>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-xs-12">Allows Children</div>
							<div class="col-xs-12">
								<select name="category" class="form-control">
									<option value="1">Yes</option>
									<option value="0">No</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>

