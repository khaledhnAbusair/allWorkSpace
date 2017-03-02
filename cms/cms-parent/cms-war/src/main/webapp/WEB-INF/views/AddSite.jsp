<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<i class="fa fa-pencil-square"></i> Add Site
				</h2>
				<hr>
				<form action="<c:url value='/dispatch/addSite'/>" method="post">
					<c:if test="${failureError ne null }">
						<div style="color: red;">Something went wrong:
							${failureError}</div>
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
								<label class="control-label"> Site Name <span
									class="symbol required"></span>
								</label> <input type="text" placeholder="Insert your Site Name"
									class="form-control" id="siteName" name="siteName">
							</div>
							<div class="form-group">
								<label class="control-label"> Site URI <span
									class="symbol required"></span>
								</label> <input type="text" placeholder="Insert your Site URI"
									class="form-control" id="siteUrl" name="siteUrl">
							</div>
							<div class="form-group">


								<label class="control-label"> Parent Site <span
									class="symbol required"></span>
								</label> <select class="form-control" id="site" name="site">
									<option value="">Select Parent Site</option>
									<c:forEach items="${siteParent}" var="site">
										<option value="${site.siteId}">${site.siteName}</option>
									</c:forEach>
								</select>

							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<button class="btn btn-yellow btn-block" type="submit">
								Save <i class="fa fa-arrow-circle-right"></i>
							</button>
						</div>
						<div class="col-md-4">
							<a class="btn btn-danger" href="./sites.html"> Cancel <i
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