<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Content Management System</title>
<!-- start: META -->
<meta charset="utf-8" />
<!--[if IE]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]-->
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
	href='<c:url value="/static/assets/plugins/animate.css/animate.min.css"/>'>
<!-- end: MAIN CSS -->
<!-- start: CSS REQUIRED FOR SUBVIEW CONTENTS -->
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/owl-carousel/owl-carousel/owl.carousel.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/owl-carousel/owl-carousel/owl.theme.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/owl-carousel/owl-carousel/owl.transitions.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/summernote/dist/summernote.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/fullcalendar/fullcalendar/fullcalendar.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/toastr/toastr.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/bootstrap-select/bootstrap-select.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/DataTables/media/css/DT_bootstrap.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.min.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>'>
<!-- end: CSS REQUIRED FOR THIS SUBVIEW CONTENTS-->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->
<!-- start: CORE CSS -->
<link rel="stylesheet"
	href='<c:url value="/static/assets/css/styles.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/css/styles-responsive.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/static/assets/css/plugins.css"/>'>
<link rel="stylesheet" href=/static/assets/css/themes/theme-style8.css
	type="text/css" id="skin_color">
<link rel="stylesheet" href="assets/css/print.css" type="text/css"
	media="print" />
<!-- end: CORE CSS -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- end: HEAD -->
<!-- start: BODY -->
<body>

	<div class="main-wrapper">
		<!-- start: TOPBAR -->
		<header class="topbar navbar navbar-inverse navbar-fixed-top inner">
			<!-- start: TOPBAR CONTAINER -->
			<div class="container">
				<div class="navbar-header">
					<a class="sb-toggle-left hidden-md hidden-lg" href="#main-navbar">
						<i class="fa fa-bars"></i>
					</a>
					<!-- start: LOGO -->
					<a class="navbar-brand" href='<c:url value="./userManagement"/>'>
						Content Management System </a>
					<!-- end: LOGO -->
				</div>
				<form method="POST">
					<div class="topbar-tools">
						<!-- start: TOP NAVIGATION MENU -->
						<ul class="nav navbar-right">
							<!-- start: USER DROPDOWN -->
							<li class="dropdown current-user"><a data-toggle="dropdown"
								data-hover="dropdown" class="dropdown-toggle"
								data-close-others="true" href="#"> <span
									class="username hidden-xs">Welcome ${userName}</span> <i
									class="fa fa-caret-down "></i>
							</a>
								<ul class="dropdown-menu dropdown-dark">
									<li><a href='<c:url value="./changePassword"/>'>
											Change Password </a></li>
									<li><button type="submit" name="logout">Log Out</button></li>
								</ul></li>
						</ul>
						<!-- end: TOP NAVIGATION MENU -->
					</div>
				</form>
			</div>
			<!-- end: TOPBAR CONTAINER -->
		</header>
		<!-- end: TOPBAR -->
		<!-- start: PAGESLIDE LEFT -->
		<a class="closedbar inner hidden-sm hidden-xs" href="#"></a>
		<nav id="pageslide-left" class="pageslide inner">
			<div class="navbar-content">
				<!-- start: SIDEBAR -->
				<div class="main-navigation left-wrapper transition-left">
					<div class="navigation-toggler hidden-sm hidden-xs">
						<a href="#main-navbar" class="sb-toggle-left"> </a>
					</div>
					<div class="user-profile border-top padding-horizontal-10 block">
						<div class="inline-block">
							<img src="<c:url value='/static/assets/images/logo.png'/>" alt="" />

						</div>
						<div class="inline-block">
							<h5 class="no-margin">Welcome</h5>
							<h4 class="no-margin"></h4>
						</div>
					</div>
					<!-- start: MAIN NAVIGATION MENU -->
					<ul class="main-navigation-menu">
						<li><a href='<c:url value="./userManagement"/>'><i
								class="fa fa-desktop"></i> <span class="title"> Users
									Management </span><i class="icon-arrow"></i> </a></li>
						<li><a href='<c:url value="./siteManagement"/>'><i
								class="fa fa-cogs"></i> <span class="title"> Sites
									Management </span><i class="icon-arrow"></i> </a></li>
						<li><a href='<c:url value="./pageManagement"/>'><i
								class="fa fa-th-large"></i> <span class="title"> Page
									Management </span><i class="icon-arrow"></i> </a></li>
					</ul>
					<!-- end: MAIN NAVIGATION MENU -->
				</div>
				<!-- end: SIDEBAR -->
			</div>
		</nav>
		<!-- end: PAGESLIDE LEFT -->
		<!-- start: MAIN CONTAINER -->
		<div class="main-container inner">
			<!-- start: PAGE -->
			<c:if test="${pageContent ne null}">
				<jsp:include page='${pageContent}'></jsp:include>
			</c:if>

			<!-- end: PAGE -->
		</div>
		<!-- end: MAIN CONTAINER -->
		<!-- start: FOOTER -->
		<footer class="inner">
			<div class="footer-inner">
				<div class="pull-left">2017 &copy; KHALED ABU-SAIR.</div>
				<div class="pull-right">
					<span class="go-top"><i class="fa fa-chevron-up"></i></span>
				</div>
			</div>
		</footer>
		<!-- end: FOOTER -->
		<!-- start: SUBVIEW SAMPLE CONTENTS -->

		<div id="readNote">
			<div class="barTopSubview">
				<a href="#newNote" class="new-note button-sv"><i
					class="fa fa-plus"></i> Add new note</a>
			</div>
			<div class="noteWrap col-md-8 col-md-offset-2">
				<div class="panel panel-note">
					<div class="e-slider owl-carousel owl-theme">
						<div class="item">
							<div class="panel-heading">
								<h3>This is a Note</h3>
							</div>
							<div class="panel-body">
								<div class="note-short-content">Lorem ipsum dolor sit
									amet, consectetur adipisici elit, sed eiusmod tempor incidunt
									ut labore et dolore magna aliqua. Ut enim ad minim veniam...</div>
								<div class="note-content">
									Lorem ipsum dolor sit amet, consectetur adipisici elit, sed
									eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut
									enim ad minim veniam, quis nostrud exercitation ullamco laboris
									nisi ut aliquid ex ea commodi consequat. Quis aute iure
									reprehenderit in <strong>voluptate velit</strong> esse cillum
									dolore eu fugiat nulla pariatur. <br> Excepteur sint
									obcaecat cupiditat non proident, sunt in culpa qui officia
									deserunt mollit anim id est laborum. <br> Nemo enim ipsam
									voluptatem, quia voluptas sit, aspernatur aut odit aut fugit,
									sed quia consequuntur magni dolores eos, qui ratione voluptatem
									sequi nesciunt, neque porro quisquam est, qui dolorem ipsum,
									quia dolor sit, amet, consectetur, adipisci v'elit, sed quia
									non numquam eius modi tempora incidunt, ut labore et dolore
									magnam aliquam quaerat voluptatem. <br> Ut enim ad minima
									veniam, quis nostrum exercitationem ullam corporis suscipit
									laboriosam, nisi ut <strong>aliquid ex ea commodi
										consequatur?</strong> <br> Quis autem vel eum iure reprehenderit,
									qui in ea voluptate velit esse, quam nihil molestiae
									consequatur, vel illum, qui dolorem eum fugiat, quo voluptas
									nulla pariatur? <br> At vero eos et accusamus et iusto
									odio dignissimos ducimus, qui blanditiis praesentium voluptatum
									deleniti atque corrupti, quos dolores et quas molestias
									excepturi sint, obcaecati cupiditate non provident, similique
									sunt in culpa, qui officia deserunt mollitia animi, id est
									laborum et dolorum fuga. Et harum quidem rerum facilis est et
									expedita distinctio. <br> Nam libero tempore, cum soluta
									nobis est eligendi optio, cumque nihil impedit, quo minus id,
									quod maxime placeat, facere possimus, omnis voluptas assumenda
									est, omnis dolor repellendus. Temporibus autem quibusdam et aut
									officiis debitis aut rerum necessitatibus saepe eveniet, ut et
									voluptates repudiandae sint et molestiae non recusandae. <br>
									Itaque earum rerum hic tenetur a sapiente delectus, ut aut
									reiciendis voluptatibus maiores alias consequatur aut
									perferendis doloribus asperiores repellat.
								</div>
								<div class="note-options pull-right">
									<a href="#readNote" class="read-note"><i
										class="fa fa-chevron-circle-right"></i> Read</a><a href="#"
										class="delete-note"><i class="fa fa-times"></i> Delete</a>
								</div>
							</div>
							<div class="panel-footer">
								<div class="avatar-note">
									<img src="assets/images/avatar-2-small.jpg" alt="">
								</div>
								<span class="author-note">Nicole Bell</span>
								<time class="timestamp" title="2014-02-18T00:00:00-05:00">
									2014-02-18T00:00:00-05:00 </time>
							</div>
						</div>
						<div class="item">
							<div class="panel-heading">
								<h3>This is the second Note</h3>
							</div>
							<div class="panel-body">
								<div class="note-short-content">Excepteur sint obcaecat
									cupiditat non proident, sunt in culpa qui officia deserunt
									mollit anim id est laborum. Nemo enim ipsam voluptatem, quia
									voluptas sit...</div>
								<div class="note-content">
									Excepteur sint obcaecat cupiditat non proident, sunt in culpa
									qui officia deserunt mollit anim id est laborum. <br> Nemo
									enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit
									aut fugit, sed quia consequuntur magni dolores eos, qui ratione
									voluptatem sequi nesciunt, neque porro quisquam est, qui
									dolorem ipsum, quia dolor sit, amet, consectetur, adipisci
									v'elit, sed quia non numquam eius modi tempora incidunt, ut
									labore et dolore magnam aliquam quaerat voluptatem. <br>
									Ut enim ad minima veniam, quis nostrum exercitationem ullam
									corporis suscipit laboriosam, nisi ut <strong>aliquid
										ex ea commodi consequatur?</strong> <br> Quis autem vel eum iure
									reprehenderit, qui in ea voluptate velit esse, quam nihil
									molestiae consequatur, vel illum, qui dolorem eum fugiat, quo
									voluptas nulla pariatur? <br> Nam libero tempore, cum
									soluta nobis est eligendi optio, cumque nihil impedit, quo
									minus id, quod maxime placeat, facere possimus, omnis voluptas
									assumenda est, omnis dolor repellendus. Temporibus autem
									quibusdam et aut officiis debitis aut rerum necessitatibus
									saepe eveniet, ut et voluptates repudiandae sint et molestiae
									non recusandae. <br> Itaque earum rerum hic tenetur a
									sapiente delectus, ut aut reiciendis voluptatibus maiores alias
									consequatur aut perferendis doloribus asperiores repellat.
								</div>
								<div class="note-options pull-right">
									<a href="#" class="read-note"><i
										class="fa fa-chevron-circle-right"></i> Read</a><a href="#"
										class="delete-note"><i class="fa fa-times"></i> Delete</a>
								</div>
							</div>
							<div class="panel-footer">
								<div class="avatar-note">
									<img src="assets/images/avatar-3-small.jpg" alt="">
								</div>
								<span class="author-note">Steven Thompson</span>
								<time class="timestamp" title="2014-02-18T00:00:00-05:00">
									2014-02-18T00:00:00-05:00 </time>
							</div>
						</div>
						<div class="item">
							<div class="panel-heading">
								<h3>This is yet another Note</h3>
							</div>
							<div class="panel-body">
								<div class="note-short-content">At vero eos et accusamus
									et iusto odio dignissimos ducimus, qui blanditiis praesentium
									voluptatum deleniti atque corrupti, quos dolores...</div>
								<div class="note-content">
									At vero eos et accusamus et iusto odio dignissimos ducimus, qui
									blanditiis praesentium voluptatum deleniti atque corrupti, quos
									dolores et quas molestias excepturi sint, obcaecati cupiditate
									non provident, similique sunt in culpa, qui officia deserunt
									mollitia animi, id est laborum et dolorum fuga. Et harum quidem
									rerum facilis est et expedita distinctio. <br> Excepteur
									sint obcaecat cupiditat non proident, sunt in culpa qui officia
									deserunt mollit anim id est laborum. <br> Nemo enim ipsam
									voluptatem, quia voluptas sit, aspernatur aut odit aut fugit,
									sed quia consequuntur magni dolores eos, qui ratione voluptatem
									sequi nesciunt, neque porro quisquam est, qui dolorem ipsum,
									quia dolor sit, amet, consectetur, adipisci v'elit, sed quia
									non numquam eius modi tempora incidunt, ut labore et dolore
									magnam aliquam quaerat voluptatem. <br> Ut enim ad minima
									veniam, quis nostrum exercitationem ullam corporis suscipit
									laboriosam, nisi ut <strong>aliquid ex ea commodi
										consequatur?</strong>
								</div>
								<div class="note-options pull-right">
									<a href="#" class="read-note"><i
										class="fa fa-chevron-circle-right"></i> Read</a><a href="#"
										class="delete-note"><i class="fa fa-times"></i> Delete</a>
								</div>
							</div>
							<div class="panel-footer">
								<div class="avatar-note">
									<img src="assets/images/avatar-4-small.jpg" alt="">
								</div>
								<span class="author-note">Ella Patterson</span>
								<time class="timestamp" title="2014-02-18T00:00:00-05:00">
									2014-02-18T00:00:00-05:00 </time>
							</div>
						</div>
					</div>
				</div>
			</div>
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
		src='<c:url value="/static/assets/plugins/blockUI/jquery.blockUI.js"/>'></script>
	<script
		src='<c:url value= "/static/assets/plugins/iCheck/jquery.icheck.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/moment/min/moment.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/perfect-scrollbar/src/jquery.mousewheel.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/perfect-scrollbar/src/perfect-scrollbar.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootbox/bootbox.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/jquery.scrollTo/jquery.scrollTo.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/ScrollToFixed/jquery-scrolltofixed-min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/jquery.appear/jquery.appear.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/jquery-cookie/jquery.cookie.js"/>'></script>

	<script
		src='<c:url value="/static/assets/plugins/velocity/jquery.velocity.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/TouchSwipe/jquery.touchSwipe.min.js"/>'></script>
	<!-- end: MAIN JAVASCRIPTS -->
	<!-- start: JAVASCRIPTS REQUIRED FOR SUBVIEW CONTENTS -->
	<script
		src='<c:url value="/static/assets/plugins/owl-carousel/owl-carousel/owl.carousel.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/jquery-mockjax/jquery.mockjax.js"/>'></script>
	<script src='<c:url value="/static/assets/plugins/toastr/toastr.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootstrap-modal/js/bootstrap-modal.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootstrap-modal/js/bootstrap-modalmanager.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/fullcalendar/fullcalendar/fullcalendar.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootstrap-switch/dist/js/bootstrap-switch.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootstrap-select/bootstrap-select.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/DataTables/media/js/jquery.dataTables.min.js"/>'></script>

	<script
		src='<c:url value="/static/assets/plugins/truncate/jquery.truncate.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/summernote/dist/summernote.min.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"/>'></script>
	<script src='<c:url value="/static/assets/js/subview.js"/>'></script>
	<script src='<c:url value="/static/assets/js/subview-examples.js"/>'></script>
	<!-- end: JAVASCRIPTS REQUIRED FOR SUBVIEW CONTENTS -->
	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<script
		src='<c:url value="/static/assets/plugins/ckeditor/ckeditor.js"/>'></script>
	<script
		src='<c:url value="/static/assets/plugins/ckeditor/adapters/jquery.js"/>'></script>
	<script src='<c:url value="/static/assets/js/form-validation.js"/>'></script>
	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CORE JAVASCRIPTS  -->
	<script src='<c:url value="/static/assets/js/main.js"/>'></script>
	<!-- end: CORE JAVASCRIPTS  -->
	<script>
		jQuery(document).ready(function() {
			Main.init();
			SVExamples.init();
			FormValidator.init();
		});
	</script>
</body>
<!-- end: BODY -->
</html>