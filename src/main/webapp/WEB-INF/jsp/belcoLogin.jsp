<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" href="assets/css/jquery.gritter.css"
	rel="stylesheet">
<meta charset="utf-8">
<title>Belco Ecosystem</title>
<link rel="shortcut icon" href="assets/img/favicon.ico"
	type="image/icon">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=Oleo+Script:400,700'>
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/belcoLogin.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/jquery.gritter.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/font-awesome.min.css">

<style>
.custBlock {
	display: block;
}

.miniPic {
	position: absolute !important;
	bottom: 37px !important;
	right: 12px !important;
}
.ui-autocomplete-loading
{
background-image: url(./assets/img/select2-spinner.gif)!important; 
background-repeat: no-repeat;
background-position: 99% 50%;
padding-right: 27px;
}
</style>
</head>

<body>

	<div class="header">
		<div class="container">
			<div class="row">
				<div class="logo span4">
					<!-- <h1> -->
						<!-- <a class="logo" href="">Belco Ecosystem</a> -->
						<img alt="" src="./assets/img/logo2.png" width=79px style="padding:6px 0px">
					<!-- </h1> -->
				</div>
				<div class="links span8"></div>
			</div>
		</div>
	</div>
	<div>
		<div class="col-md-4"></div>
		<div class="login-container container col-md-4" id="loginForm">
			<div class="row">
				<div class="iphone span3"></div>
				<div class="login span6">
					<form id="login" action="home" method="post" autocomplete="off">
						<input type="hidden" name="loginTypeForLogin"
							id="loginTypeForLogin" value="belcoLogin">
						<h2 style="margin-bottom: 10px;">
							<span>Login to Ecosystem</span><a href="#"
								onClick="showForgotPassword()" class=" blue pull-right">Forgot
								Password ?</a>
						</h2>
						<div class="form-group">
							<label id="emailLabel" for="email">Email</label> <input type="email"
								class="inputbox" id="loginEmailId" name="loginEmailId"
								style="box-shadow: none;" placeholder="Enter your email..."
								onfocus="setIdAndName();" onchange="getMappedRoles()"
								autocomplete="off">
						</div>
						<div class="form-group">
							<label id="passwordLabel" for="password">Password</label> <input type="password"
								class="inputbox passwordInput"
								style="box-shadow: none;" placeholder="Enter your password...">
						</div>
						<div class="form-group login-input" id="roleDiv"
							style="display: none">
							<label id="roleLabel" for="role">Role</label> <select
								class="form-control selectpicker show-tick"
								id="userRolesForLogin" name="userRolesForLogin"
								style="display: none; height: 40px; border-radius: 2px !important;">
								<option value="">Select User Role</option>
							</select> <label id="defaultRoleLabel"
								style="display: none; cursor: pointer; font-size: 13px;">
								<input type="checkbox" style="cursor: pointer;"
								id="defaultRoleCheckBox" name="defaultRoleCheckBox" onclick="">
								Make this default
							</label>
						</div>
						<div class="form-group">
						<div class="checkbox"
							style="margin-top: 0px !important; margin-bottom: 0px !important;">
							<label style="font-size: 14px;"> <input type="checkbox"
								id="rememberme" onclick="settingValuesInCookies()">
								Remember me
							</label>
						</div>
						</div>
						<button type="submit" id="blogin">LOGIN</button>
						<h3></h3>
						<div class="form-group activationPadding">
							<a href="#" onclick="showActivation()" class=" blue pull-left"
								style="font-size: 14px; top: -6px;">Send Activation Link </a>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="forgotpassword-container container hide col-md-4"
			id="forgotPasswordDiv">
			<div class="row">
				<div class="iphone span3"></div>
				<div class="forgotpassword span6">
					<form id="forgotPasswordForm" action="forgotPassword" method="post">
						<input type="hidden" name="loginTypeForPass" id="loginTypeForPass"
							value="belcoLogin">
						<h2>
							<span>Retrieve Password</span>
						</h2>
						<div class="form-group">
							<label for="email">Email</label> <input type="email"
								class="inputbox" id="sendingEmailId" name="sendingEmailId"
								placeholder="Enter your email...">
						</div>
						<div class="form-group" style="margin-top: 15px;">
							<div class="col-md-6">
								<a href="#" class=" blue pull-left" onclick="showLogin()"
									style="font-size: 14px; margin-left: -13px;">Back to Login</a>
							</div>
							<div class="col-md-6">
								<button type="submit" onsubmit="resetForgottenPasswordForm()">SEND</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>

		<div class="activationlink-container container hide col-md-4"
			id="activationLink">
			<div class="row">
				<div class="iphone span3"></div>
				<div class="activationlink span6">
					<form id="activationlink" action="sendingEmailForActivation"
						method="post">
						<input type="hidden" name="loginType" id="loginType"
							value="belcoLogin">
						<h2>
							<span>Send Activation Link</span>
						</h2>
						<div class="form-group">
							<label for="email">Email</label> <input type="email"
								class="inputbox" id="activationEmailId" name="activationEmailId"
								placeholder="Enter your email...">
						</div>
						<div class="form-group" style="margin-top: 15px;">
							<div class="col-md-6">
								<a href="#" class=" blue pull-left" onclick="showLogin()"
									style="font-size: 14px; margin-left: -13px;">Back to Login</a>
							</div>
							<div class="col-md-6">
								<button type="submit">SEND</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>

	</div>

	<!-- Javascript -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/belcoLogin.js"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<script src="assets/js/jquery.gritter.min.js"></script>

	<!-- CUSTOM NOTIFICATION  gritter-->

	<script src="assets/js/SmartNotification.min.js"></script>

	<script>
		try {
			var activation = '${ActivationMsg}';
			var activationError = '${ActivationErrorMsg}';
			var forgot = '${PasswordMsg}';
			var forgotError = '${PasswordErrorMsg}';
			var data = '${ErrorMsg}';

			//for activation
			if (activation != "") {
				setTimeout(function() {
					gritterForSuccessMsgs(activation,
							"<b>Activation Success !!</b>");
				}, 1000);
				console.log(activation);
			}
			if (activationError != "") {
				setTimeout(function() {
					gritterForInfoMsgs("Email ID is already activated !!",
							"<b>Activation Failure !!</b>");
				}, 1000);
				console.log(activationError);
			}

			//for password
			if (forgot != "") {
				setTimeout(function() {
					gritterForSuccessMsgs(forgot,
							"<b>Password Retrieval Success !!</b>");
				}, 1000);
				console.log(forgot);
			}
			if (forgotError != "") {
				setTimeout(function() {
					gritterForLoginErrorMsgs(
							"Invalid Email ID... Please try again !!",
							"<b>Password Retrieval Failure !!</b>");
					;
				}, 1000);
				;
				console.log(forgotError);
			}
			if (data.trim() == "Invalid Credentials !!!") {
				setTimeout(function() {
					gritterForLoginErrorMsgs(
							"Invalid Credentials... Please try again !!",
							"<b>Login Failure !!</b>");
				}, 1000);
			}

			$("#blogin").submit(function(event) {
				// this will prevent the form from submitting 
				event.preventDefault();
			});

		} catch (e) {

			console.log(e);
		}
	</script>

</body>

</html>