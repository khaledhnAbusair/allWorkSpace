<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty error}">
	<div class="col-xs-12">
		<c:out value="${error}">${error}</c:out>
		&nbsp;
	</div>
</c:if>
<div class="container">
	<c:if test="${not empty error}">
		<div class="col-xs-12">
			<c:out value="${error}">${error}</c:out>
			&nbsp;
		</div>
	</c:if>
	<h1 class="page-header">Terms</h1>
	<div class="row">
		<div class="col-xs-4">
			<div id="term-tree"></div>
		</div>
		<div class="col-sm-8">
			<form class="form-horizontal" method="post" action="./userManagment">
				<div class="col-xs-12">
					<div>
						<input class="form-control" id="parentId" name="parentId" value="${termId}"
							type="hidden" />
					</div>
					<div class="row form-group">
						<div class="col-xs-12">Name</div>
						<div class="col-xs-12">
							<input type="text" name="termName" id="name" value="${name}"
								class="form-control" placeholder="Name" required autofocus>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-12">Category</div>
						<div class="col-xs-12">
							<select id="category" name="termCategory" class="form-control">
								<c:forEach var="item" items="${loadCategories}">
									<option ${item.catName eq catName ? 'selected' : '' }
										value="${item.catName}">${item.catName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-12">Label</div>
						<div class="col-xs-12">
							<input type="text" name="termLabel" value="${label}" id="label"
								class="form-control" placeholder="Label" required autofocus>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-12">Purpose</div>
						<div class="col-xs-12">
							<textarea class="form-control" rows="6" id="purpose" name="termPurpose">${purpose}</textarea>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-12">
							<div class="pull-right">

								<button class="btn btn-primary" type="submit" name="action"
									value="save">
									<i class="glyphicon glyphicon-floppy-disk"></i>Save
								</button>
								&nbsp;
								<button class="btn btn-warning" name="action" value="reset">
									<i class="glyphicon glyphicon-refresh"></i>Reset
								</button>
								&nbsp;
								<button class="btn btn-danger" name="action" value="delete">
									<i class="glyphicon glyphicon-trash"></i>Delete
								</button>
								&nbsp;
							</div>
							<div class="pull-left">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#newTermModal">
									<i class="glyphicon glyphicon-plus"></i>Add child
								</button>
								&nbsp;
							</div>
						</div>
					</div>
				</div>
			</form>


		</div>
	</div>
</div>

<div class="modal fade" id="newTermModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<form action="./userManagment" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add child term</h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="row form-group">
							<div class="col-xs-12">Name</div>
							<div class="col-xs-12">
								<input type="text" id="name" name="childName"
									class="form-control" placeholder="Name" required autofocus>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-xs-12">Category</div>
							<div class="col-xs-12">
								<select name="childCategory" class="form-control">
									<c:forEach var="item" items="${loadCategories}">
										<option value="${item.catName}">${item.catName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-xs-12">Label</div>
							<div class="col-xs-12">
								<input type="text" name="childLabel" id="label"
									class="form-control" placeholder="Label" required autofocus>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-xs-12">Purpose</div>
							<div class="col-xs-12">
								<textarea class="form-control" rows="6" name="childPurpose"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" name="action" value="addChild"
						class="btn btn-primary">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>



