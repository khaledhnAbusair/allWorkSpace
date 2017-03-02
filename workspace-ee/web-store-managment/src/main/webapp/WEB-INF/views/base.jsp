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
<meta name="description" content="">
<meta name="author" content="">

<title>${applicationScope.applicationName}</title>

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
				<a class="navbar-brand" href="./userManagment">Term Store
					Management</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="./userManagment">Terms<span
							class="sr-only">(current)</span></a></li>
					<li><a href="./manageCategories">Categories</a></li>
					<li><a href="./searchTerm">Search</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div>
		<c:if test="${body ne null}">
			<jsp:include page="${body}"></jsp:include>
		</c:if>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="./static/bootstrap/js/bootstrap.min.js"></script>
	<script src="./static/bootstrap/js/bootstrap-treeview.min.js"></script>
	<script type="text/javascript">
		var tree;
		var xmlhttp = new XMLHttpRequest();
		function getTermTree() {

			if (this.readyState == 4 && this.status == 200) {
				window.tree = JSON.parse("[" + this.responseText + "]");
				console.log(window.tree);
				$('#term-tree').treeview({
					data : window.tree,
					onNodeSelected : function(event, data) {
						xmlhttp.open("GET", data.href, true);
						xmlhttp.onreadystatechange = getTermTree;
						xmlhttp.send(null);
						findTermById(getId(data.href), data);
					}
				});
			}
		}
		function findTermById(id) {
			var xmlhttpTerm = new XMLHttpRequest();

			xmlhttpTerm.open("GET", "./treeStructure?id=" + id, true);
			xmlhttpTerm.onreadystatechange = function() {
				var term = JSON.parse("[" + this.responseText + "]")[0];
				document.getElementById('parentId').value = id;
				document.getElementById('name').value = term.name;
				document.getElementById('label').value = term.label;
				document.getElementById('purpose').value = term.purpose;
				document.getElementById('category').value = term.category;

			}
			xmlhttpTerm.send(null);
		}

		function getId(href) {
			parameters = href.split('?')[1];
			parameterId = parameters.split('&')[1];
			return parameterId.split('=')[1];
		}

		$(document).ready(function() {
			xmlhttp.open("GET", "./treeStructure?depth=0", true);
			xmlhttp.onreadystatechange = getTermTree;
			xmlhttp.send();
		});
	</script>

</body>
</html>