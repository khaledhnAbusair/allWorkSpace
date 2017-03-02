<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="container">
	<%
		String name = request.getParameter("name");
		String child = request.getParameter("child");
	%>
	<c:if test="${not empty error}">
		<div class="col-xs-12">
			<c:out value="${error}">${error}</c:out>
			&nbsp;
		</div>
	</c:if>
	<h1 class="page-header">Edit Category</h1>
	<div class="row">

		<form method="post" action="./editCategory">
			<div class="col-xs-6 form-horizontal">
				<div class="row form-group">
					<div class="col-xs-12">Allows Children</div>
					<div class="col-xs-12">
						<Select name="categories" class="form-control">
							<option value="<%=name%>"><%=name%></option>
						</select>
					</div>
				</div>


				<div class="row form-group">
					<div class="col-xs-12">Allows Children</div>
					<div class="col-xs-12">
						<select name="category" class="form-control">
							<option value="1">true</option>
							<option value="0">false</option>

						</select>
					</div>

				</div>
				<div class="pull-right">
					<button type="button" class="btn btn-default">Cancel</button>
					<button type="submit" class="btn btn-primary">Save</button>
				</div>
			</div>

		</form>
	</div>
</div>
<!-- /container -->
