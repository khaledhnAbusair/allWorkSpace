<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>User management</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/static/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/static/main.css"/>" rel="stylesheet">
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Users Management</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><i class="glyphicon glyphicon-user"></i>Admin<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="./ChangePassword.html"><i
									class="glyphicon glyphicon-pencil"></i>Change Password</a></li>
							<li><a href="#"><i class="glyphicon glyphicon-log-out"></i>Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<c:choose>
				<c:when test="${sideNav eq null }">
					<div class="col-sm-3 col-md-2 sidebar">
						<ul class="nav nav-sidebar">
							<li><a href="./profile">User profile</a></li>
							<li><a href="./users">Users Management</a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<jsp:include page="${sideNav}"></jsp:include>
				</c:otherwise>
			</c:choose>
			<div class="col-sm-9 col-sm-offset-2">
				<c:if test="${pageContent ne null}">
					<jsp:include page="${pageContent}"></jsp:include>
				</c:if>
			</div>
		</div>
	</div>
	<!-- /container -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src='<c:url value="/static/bootstrap/js/bootstrap.min.js"/>'></script>
</body>
</html>
