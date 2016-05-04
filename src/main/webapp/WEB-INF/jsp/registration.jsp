<!DOCTYPE html>
<html lang="en-us" id="extr-page">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Ecosystem Registration</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- #CSS Links -->
<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/font-awesome.min.css">

<!-- Ecosystem Styles : Please note (Ecosystem-production.css) was created using LESS variables -->
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/smartadmin-skins.min.css">

<!-- Ecosystem RTL Support is under construction
			 This RTL CSS will be released in version 1.5
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> -->

<!-- We recommend you use "your_style.css" to override Ecosystem
		     specific styles this will also ensure you retrain your customization with each Ecosystem update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/demo.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/datepicker.css">
<!-- JS dependencies -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/userRegistration.js"></script>
<!-- #FAVICONS -->
<link rel="shortcut icon" href="assets/img/favicon/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="assets/img/favicon/favicon.ico"
	type="image/x-icon">

<!-- #GOOGLE FONT -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

<!-- #APP SCREEN / ICONS -->
<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
<link rel="apple-touch-icon"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/sptouch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/touch-icon-iphone-retina.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/touch-icon-ipad-retina.pngl">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- For Ladda Buttons @hari -->
<script src="assets/js/spin.min.js"></script>
<script src="assets/js/ladda.min.js"></script>
<link rel="stylesheet" href="assets/css/ladda.min.css">

</head>

<body
	class="animated fadeInDown  desktop-detected menu-on-top pace-done">
	<div class="pace  pace-inactive">
		<div class="pace-progress" data-progress-text="100%"
			data-progress="99" style="width: 100%;">
			<div class="pace-progress-inner"></div>
		</div>
		<div class="pace-activity"></div>
	</div>

	<header id="header">

		<div id="logo-group">
			<span id="logo"><img src="./assets/img/logo.png"
				style="height: 35px; width: 116px; position: relative; top: -5px;"
				alt="EcoSystem"> </span>
		</div>
		<span id="extr-page-header-space"> <span class="hidden-mobile">
				Already Registered?</span> <a href="login" class="btn btn-danger">Login</a>
		</span>

	</header>

	<div id="main" role="main">

		<!-- MAIN CONTENT -->
		<div id="content" class="container">

			<div class="row">
				<div
					class="col-xs-12 col-sm-12 col-md-7 col-lg-7 hidden-xs hidden-sm">
					<h1 class="txt-color-red login-header-big">Ecosystem</h1>
					<div class="hero">

						<div class="pull-left login-desc-box-l">
							<h4 class="paragraph-header">
								Smart way of handling your business. Manage all your application
								at a single place ! See all your projects, users, stats, log,
								usage info at a single place.<br> Just sign in to feel the
								next-gen business tool.
							</h4>
						</div>

						<img src="assets/img/EcosystemLogo.jpg"
							class="pull-right display-image" alt="" style="width: 210px">

					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<h5 class="about-heading">About Ecosystem -</h5>
							<p>Ecosystem provides you up to date information of your
								business. It gathers data from all your suscribed applications,
								and represents it in most effective way.</p>
						</div>

					</div>

				</div>
				<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
					<div class="well no-padding">

						<form id="userRegistrationForm" class="smart-form client-form"
							novalidate="novalidate" method="post"
							commandName="temporaryRegistrationForm">
							<header> Registration </header>

							<fieldset>
								<section>
									<label class="input"> <i
										class="icon-append fa fa-envelope"></i> <input type="email"
										name="emailId" placeholder="Email address" id="emailId">
										<b class="tooltip tooltip-bottom-right">Needed to verify
											your account</b>
									</label>
								</section>

								<section>
									<label class="input"> <i class="icon-append fa fa-lock"></i>
										<input type="password" name="password" placeholder="Password"
										id="password"> <b class="tooltip tooltip-bottom-right">Don't
											forget your password</b>
									</label>
								</section>

								<section>
									<label class="input"> <i class="icon-append fa fa-lock"></i>
										<input type="password" name="passwordConfirm"
										placeholder="Confirm password" id="confirmPassword"> <b
										class="tooltip tooltip-bottom-right">Don't forget your
											password</b>
									</label>
								</section>
								<section>
									<label class="input"> <i class="icon-append fa fa-user"></i>
										<input type="text" id="userName" name="userName"
										placeholder="User Name"> <b
										class="tooltip tooltip-bottom-right">Please enter User
											Name</b>
									</label>
								</section>
							</fieldset>
							<footer>
								<button id="regButtonId" data-style="expand-right" data-color="blue" data-size="xl" class="width-45 btn btn-large btn-primary pull-right ladda-button" type="submit">Register</button>
							</footer>

							<div class="message">
								<div class = "alert alert-success fade in">
								<i class="fa fa-check" align = "center"></i>
								<p>Thank you for your registration!</p>
								</div>
							</div>
							
								<div class="message1">
									<div class = "alert alert-warning fade in">
									<h4 class="alert-heading">This Email ID is already added in the system. Do you want to<a href="forgot">&nbsp;Reset password?</a></h4>
								</div>
							</div>
						</form>

					</div>
					<!-- <p class="note text-center">*FREE Registration ends on October
						2015.</p>
					<h5 class="text-center">- Or sign in using -</h5>
					<ul class="list-inline text-center">
						<li><a href="javascript:void(0);"
							class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
						</li>
						<li><a href="javascript:void(0);"
							class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
						</li>
						<li><a href="javascript:void(0);"
							class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
						</li>
					</ul> -->
				</div>
			</div>
		</div>

	</div>

	<!--================================================== -->

	<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
	<script type="text/javascript" async="" src="assets/js/ga.js"></script>
	<script src="assets/js/pace.min.js"></script>

	<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	<script src="assets/js/jquery.min.js"></script>
	<script>
		if (!window.jQuery) {
			document
					.write('<script src="js/libs/jquery-2.0.2.min.js"><\/script>');
		}
	</script>

	<script src="assets/js/jquery-ui.min.js"></script>
	<script>
		if (!window.jQuery.ui) {
			document
					.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
		}
	</script>

	<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

	<!-- BOOTSTRAP JS -->
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- JQUERY VALIDATE -->
	<script src="assets/js/jquery.validate.min.js"></script>

	<!-- JQUERY MASKED INPUT -->
	<script src="assets/js/jquery.maskedinput.min.js"></script>

	<!--[if IE 8]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->

	<!-- MAIN APP JS FILE -->
	<script src="assets/js/app.min.js"></script>

	<script type="text/javascript">
		runAllForms();
		$(".message1").hide();
		// Model i agree button
		$("#i-agree").click(function() {
			$this = $("#terms");
			if ($this.checked) {
				$('#myModal').modal('toggle');
			} else {
				$this.prop('checked', true);
				$('#myModal').modal('toggle');
			}
		});
	</script>

</body>
</html>
