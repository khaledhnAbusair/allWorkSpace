<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- start: PAGE CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- start: FORM VALIDATION 2 PANEL -->
		<div class="panel panel-white">
			<div class="panel-heading">
				<div class="panel-tools">
					<div class="dropdown">
						<a data-toggle="dropdown"
							class="btn btn-xs dropdown-toggle btn-transparent-grey"> <i
							class="fa fa-cog"></i>
						</a>
						<ul class="dropdown-menu dropdown-light pull-right" role="menu">
							<li><a class="panel-collapse collapses" href="#"><i
									class="fa fa-angle-up"></i> <span>Collapse</span> </a></li>
							<li><a class="panel-refresh" href="#"> <i
									class="fa fa-refresh"></i> <span>Refresh</span>
							</a></li>
							<li><a class="panel-config" href="#panel-config"
								data-toggle="modal"> <i class="fa fa-wrench"></i> <span>Configurations</span>
							</a></li>
							<li><a class="panel-expand" href="#"> <i
									class="fa fa-expand"></i> <span>Full screen</span>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<h2>
					<i class="fa fa-pencil-square"></i> Edit User
				</h2>
				<hr>
				<form action="<c:url value='/dispatch/editUser'/>" method="post">

					<c:if test="${failureError ne null }">
						<div style="color: red;">Something went wrong:
							${failureError}</div>
					</c:if>
					<c:if test="${errors ne null}">
						<div style="color: red;">
							<c:forEach items="${errors.allErrors}" var="error">
		${error.defaultMessage}<br />
							</c:forEach>
						</div>
					</c:if>
					<div class="row">
						<div class="col-md-12">
							<div class="errorHandler alert alert-danger no-display">
								<i class="fa fa-times-sign"></i> You have some form errors.
								Please check below.
							</div>
							<div class="successHandler alert alert-success no-display">
								<i class="fa fa-ok"></i> Your form validation is successful!
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label"> Full Name <span
									class="symbol required"></span>
								</label> <input type="text" placeholder="Edit your FullName"
									class="form-control" id="fullName" name="fullName"
									value="${user.fullName}">
							</div>
							<div class="form-group">
								<label class="control-label"> UserName <span
									class="symbol required"></span>
								</label> <input type="text" class="form-control" id="userName"
									value="${user.userName}" name="userName" readonly>
							</div>
							<div class="form-group">
								<label class="control-label"> password <span
									class="symbol required"></span>
								</label> <input type="text" class="form-control" id="password"
									value="${user.password}" name="password" readonly>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-md-3">
							<button type="submit" name="reset"
								class="btn btn-info pull-right">Reset</button>
						</div>
						<div class="col-md-3">
							<button name="save" class="btn btn-yellow btn-block"
								type="submit">
								Save <i class="fa fa-arrow-circle-right"></i>
							</button>
						</div>
						<div class="col-md-1">
							<button name="disable" type="submit"
								class="btn btn-danger pull-left">Disable</button>
						</div>
						<div class="col-md-1">
							<button name="enable" type="submit"
								class="btn btn-info pull-left">Enable</button>
						</div>
						<div class="col-md-3">
							<a href='<c:url value="./userManagement"/>'
								class="btn btn-danger pull-left"> Cancel <i
								class="glyphicon glyphicon-ban-circle"></i>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- end: FORM VALIDATION 2 PANEL -->
	</div>
</div>
<!-- end: PAGE CONTENT-->