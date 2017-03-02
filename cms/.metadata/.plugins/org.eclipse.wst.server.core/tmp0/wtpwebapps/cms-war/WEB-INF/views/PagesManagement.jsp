<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- start: PAGE CONTENT -->
<div class="row">
	<div>
		<div class="col-md-12">
			<!-- start: EXPORT DATA TABLE PANEL  -->
			<div class="panel panel-white">
				<div class="panel-heading">
					<h4 class="panel-title">
						<span class="text-bold">Data</span> Table Page
					</h4>
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
						<a class="btn btn-xs btn-link panel-close" href="#"> <i
							class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<form action="<c:url value='/dispatch/pageManagement'/>"
					method="post">
					<c:if test="${failureError ne null }">
						<div style="color: red;">Something went wrong:
							${failureError}</div>
					</c:if>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 space20">
								<a href='<c:url value="./addPage"/>'
									class="btn btn-orange add-row pull-right"> Add Page <i
									class="fa fa-plus"></i>
								</a>
								<div class="input-group col-md-6 ">

									<select class="form-control" id="site" name="site">
										<option value="0">All</option>
										<c:forEach items="${parentSite}" var="site">
											<option value="${site.siteId}">${site.siteName}-/
												${site.siteUrl}</option>
										</c:forEach>
									</select> <span class="input-group-btn">
										<button name="filter" class="btn btn-default">
											<i class="glyphicon glyphicon-filter"></i>Filter
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>
				</form>

				<div class="table-responsive">
					<table class="table table-striped table-hover" id="sample-table-2">
						<thead>
							<tr>
								<th>Page URI</th>
								<th>Page Title</th>
								<th>Site</th>
								<th>Is Landing</th>
								<th>Is Publish</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pages}" var="page">
								<tr>
									<td>${page.pageUrl}</td>
									<td>${page.pageTitle}</td>
									<td>${page.siteId.siteName}</td>
									<td>${page.isLandingPage}</td>
									<td>${page.isPublish}</td>
									<td><a
										href='<c:url value="./editPage?pageUrl=${page.pageUrl}"/>'
										class="btn btn-info edit-row"> Edit </a></td>

									<td><form method="post">
											<input name="page" type="hidden" value="${page.pageUrl}" />
											<button class="btn btn-danger" name="delete" type="submit">Delete</button>
										</form></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- end: EXPORT DATA TABLE PANEL -->
	</div>
</div>
<!-- end: PAGE CONTENT-->