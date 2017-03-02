<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<c:if test="${not empty error}">
		<div class="col-xs-12">
			<c:out value="${error}">${error}</c:out>
			&nbsp;
		</div>
	</c:if>
	<h1 class="page-header">Search</h1>
	<div class="row">
		<div class="col-xs-12">
			<form class="form-horizontal" action="./searchTerm" method="post">
				<div class="col-xs-12">
					<div class="row form-group">
						<div class="col-xs-2">Name Starts with</div>
						<div class="col-xs-3">
							<input type="text" id="name" name="name" class="form-control"
								placeholder="Name" autofocus>
						</div>

						<div class="col-xs-2">In Category</div>
						<div class="col-xs-3">
							<select name="category" class="form-control">
								<option value="">please select category Name ...</option>
								<c:forEach var="item" items="${loadCategories}">
									<option value="${item.catName}">${item.catName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-2">Label starts with</div>
						<div class="col-xs-3">
							<input type="text" id="label" name="label" class="form-control"
								placeholder="Label" autofocus>
						</div>
						<div class="col-xs-2">Purpose contains</div>
						<div class="col-xs-3">
							<input type="text" id="label" name="purpose" class="form-control"
								placeholder="Label" autofocus>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-12">
							<div class="pull-right">
								<button type="submit" class="btn btn-primary">
									<i class="glyphicon glyphicon-search"></i>Search
								</button>
								&nbsp;
								<button class="btn btn-warning">
									<i class="glyphicon glyphicon-refresh"></i>Reset
								</button>
								&nbsp;
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="col-xs-12">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Term name</th>
						<th>category</th>
						<th>Label</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${searchQuery}">
						<tr>
							<td>${item.termName}</td>
							<td>${item.termCategory.catName}</td>
							<td>${item.termLabel}</td>
							<td><a href="./userManagment?id=${item.termId}"
								class="btn btn-primary"> <span
									class="glyphicon glyphicon-list-alt"></span>View
							</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>

