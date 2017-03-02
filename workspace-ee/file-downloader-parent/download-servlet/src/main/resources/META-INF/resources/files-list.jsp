<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Files List</title>
</head>
<body>

	<form action='<c:url value="/downloader" />' method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>File To Upload</td>
				<td><input type="file" name="uploaded" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" /></td>
			</tr>
		</table>
	</form>

	<table border='1'>
		<thead>
			<tr class="active">
				<th>File</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${paths}" var="path">
				<tr>
					<td class="active">${path.fileName}</td>
					<td><a target="_blank"
						href='<c:url value="/downloader/${path.fileName} "/>'>
							Download </a></td>
				</tr>
			</c:forEach>
		</tbody>


	</table>
</body>
</html>