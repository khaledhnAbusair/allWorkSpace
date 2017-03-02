<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Content Management System</title>
<!-- start: META -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- end: META -->
<!-- start: MAIN CSS -->
<link
	href='http://fonts.googleapis.com/css?family=Raleway:400,300,500,600,700,200,100,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/bootstrap/css/bootstrap.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/font-awesome/css/font-awesome.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/iCheck/skins/all.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/perfect-scrollbar/src/perfect-scrollbar.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/css/styles.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/css/styles-responsive.css"/>'>
</head>

<body>
	<div class="panel panel-default">
		<c:if test="${error ne null}">
			<div class="alert alert-danger" role="alert" style="width: 50">
				${error }</div>
		</c:if>
		<div class="panel-heading">
			<h3 class="panel panel-info">Page Content</h3>
		</div>
		<div class="list-group">
			<ol>
				<c:forEach items="${pages}" var="page">
					<li class="list-group-item"><a
						href="<c:url value="/dispatch${page.siteId.siteUrl}${page.pageUrl}" />">
							${page.pageTitle} </a></li>
				</c:forEach>
			</ol>
		</div>

		<div class="panel-body">
			<p class="panel-body">${pageContent}</p>
		</div>
	</div>
</body>
</html>