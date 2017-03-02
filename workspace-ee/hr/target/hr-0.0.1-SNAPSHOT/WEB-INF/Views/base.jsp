<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${param['title']}</title>
<link href="<core:url value="/static/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet">
<core:if test="${additionalHead ne null }">
	<jsp:include page="${additionalHead}"></jsp:include>
</core:if>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<body>
	<div class="container-fluid">
		<jsp:include page="${content}"></jsp:include>
	</div>

	<script src='<c:url value="/static/bootstrap/js/bootstrap.min.js"/>'></script>
	<core:if test="${additionalfooter ne null}">
		<jsp:include page="${additionalfooter}"></jsp:include>
	</core:if>
</body>
</html>

