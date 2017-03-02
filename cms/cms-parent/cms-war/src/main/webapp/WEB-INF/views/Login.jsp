<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>CMS -Login Page</title>
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
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/bootstrap/css/bootstrap.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/font-awesome/css/font-awesome.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/animate.css/animate.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/iCheck/skins/all.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/css/styles.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/css/styles-responsive.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/iCheck/skins/all.css"/>'>

<!--[if IE 7]>
		<link rel="stylesheet" href="assets/plugins/font-awesome/css/font-awesome-ie7.min.css">
		<![endif]-->
<!-- end: MAIN CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->
</head>
<!-- end: HEAD -->
<!-- start: BODY -->
<body class="login">
	<div class="row">
		<div
			class="main-login col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="logo">
				<img src="<c:url value='/static/assets/images/logo.png'/>" alt="" />
			</div>
			<!-- start: LOGIN BOX -->
			<div class="box-login">
				<h3>Sign in to your account</h3>
				<p>Please enter your name and password to log in.</p>
				<form class="form-login" action="<c:url value='/dispatch/login'/>"
					method="post">
					
					<c:if test="${error ne null}">
						<div class="alert alert-danger" role="alert">${error}</div>
					</c:if>

					<div class="errorHandler alert alert-danger no-display">
						<i class="fa fa-remove-sign"></i> You have some form errors.
						Please check below.
					</div>
					<fieldset>
						<div class="form-group">
							<span class="input-icon"> <input type="text"
								class="form-control" name="userName" placeholder="UserName">
								<i class="fa fa-user"></i>
							</span>
						</div>
						<div class="form-group form-actions">
							<span class="input-icon"> <input type="password"
								class="form-control password" name="password"
								placeholder="Password"> <i class="fa fa-lock"></i> <a
								class="forgot" href="#"> I forgot my password </a>
							</span>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-green pull-right">
								Login <i class="fa fa-arrow-circle-right"></i>
							</button>
						</div>
						<div class="new-account">
							Don't have an account yet? <a href="#" class="register">
								Create an account </a>
						</div>
					</fieldset>
				</form>
				<!-- start: COPYRIGHT -->
				<div class="copyright">2017 &copy; KHALED ABU-SAIR.</div>
				<!-- end: COPYRIGHT -->
			</div>
			<!-- end: LOGIN BOX -->
			<!-- start: FORGOT BOX -->
			<div class="box-forgot">
				<h3>Forget Password?</h3>
				<p>Enter your User Name below to reset your password.</p>
				<form class="form-forgot" action="<c:url value='/dispatch/login'/>"
					method="post">
					<div class="errorHandler alert alert-danger no-display">
						<i class="fa fa-remove-sign"></i> You have some form errors.
						Please check below.
					</div>
					<fieldset>
						<div class="form-group">
							<!-- 							<span class="input-icon"> <input type="text" -->
							<!-- 								class="form-control" name="userName" -->
							<!-- 								placeholder="Enter your userName"> <i -->
							<!-- 								class="fa fa-envelope"></i> -->
							<!-- 							</span> -->

							<input type="text" class="form-control" name="userName"
								placeholder="Enter your userName">


							<button type="submit" name="forgetPassword"
								class="btn btn-green pull-right">
								Submit <i class="fa fa-arrow-circle-right"></i>
							</button>
						</div>
					</fieldset>
				</form>
				<!-- start: COPYRIGHT -->
				<div class="copyright">2017 &copy; KHALED ABU-SAIR.</div>
				<!-- end: COPYRIGHT -->
			</div>
			<!-- end: FORGOT BOX -->
			<!-- start: REGISTER BOX -->
			<div class="box-register">
				<h3>Sign Up</h3>
				<p>Enter your personal details below:</p>
				<form class="form-register"
					action="<c:url value='/dispatch/addUser'/>" method="POST">
					<div class="errorHandler alert alert-danger no-display">
						<i class="fa fa-remove-sign"></i> You have some form errors.
						Please check below.
					</div>
					<fieldset>
						<div class="form-group">
							<input type="text" class="form-control" name="userName"
								placeholder="userName">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="fullName"
								placeholder="fullName">
						</div>
						<p>Enter your account details below:</p>
						<div class="form-group">
							<span class="input-icon"> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Password"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<div class="form-actions">
							Already have an account? <a href="#" class="go-back"> Log-in
							</a>
							<button type="submit" name="createUser"
								class="btn btn-green pull-right">
								Submit <i class="fa fa-arrow-circle-right"></i>
							</button>
						</div>
					</fieldset>
				</form>
				<!-- start: COPYRIGHT -->
				<div class="copyright">2017 &copy; KHALED ABU-SAIR.</div>
				<!-- end: COPYRIGHT -->
			</div>
			<!-- end: REGISTER BOX -->
		</div>
	</div>
	<!-- start: MAIN JAVASCRIPTS -->
	<!--[if lt IE 9]>
		<script src="assets/plugins/respond.min.js"></script>
		<script src="assets/plugins/excanvas.min.js"></script>
		<script type="text/javascript" src="assets/plugins/jQuery/jquery-1.11.1.min.js"></script>
		<![endif]-->
	<!--[if gte IE 9]><!-->
	<script
		src='<c:url value="/static/assets/plugins/jQuery/jquery-2.1.1.min.js"/>'></script>
	<!--<![endif]-->
	<script
		src='<c:url value="/static/assets/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootstrap/js/bootstrap.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/iCheck/jquery.icheck.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/jquery.transit/jquery.transit.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/TouchSwipe/jquery.touchSwipe.min.js"/>'></script>
	<script src='<c:url value="/static/assets/js/main.js"/>'></script>
	<!-- end: MAIN JAVASCRIPTS -->
	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<script
		src='<c:url value="/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js"/>'></script>
	<script src='<c:url value="/static/assets/js/login.js"/>'></script>
	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<script>
		jQuery(document).ready(function() {
			Main.init();
			Login.init();
		});
	</script>
</body>
<!-- end: BODY -->

</html>