<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- start: PAGE CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- start: EXPORT DATA TABLE PANEL  -->
		<div class="panel panel-white">
			<div class="panel-heading">
				<h4 class="panel-title">
					Export <span class="text-bold">Data</span> Table
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
									class="fa fa-expand"></i> <span>Fullscreen</span>
							</a></li>
						</ul>
					</div>
					<a class="btn btn-xs btn-link panel-close" href="#"> <i
						class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12 space20">
						<a href='<c:url value="./addUser"/>'
							class="btn btn-orange add-row pull-right"> Add User <i
							class="fa fa-plus"></i>
						</a>
						<div class="input-group col-md-6 "></div>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table table-striped table-hover" id="sample-table-2">
						<thead>
							<tr>
								<th>FullName</th>
								<th>UserName</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td>${user.userName}</td>
									<td>${user.fullName}</td>
									<td>${user.isDisable}</td>
									<td><a
										href='<c:url value="./editUser?userName=${user.userName}"/>'
										class="btn btn-info edit-row"> Manage </a></td>
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