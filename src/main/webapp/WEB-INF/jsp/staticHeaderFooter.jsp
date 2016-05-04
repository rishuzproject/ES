<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<title>Ecosystem</title>
<link rel="shortcut icon" href="assets/img/favicon.ico"
	type="image/icon">

<meta name="description" content="">

<script src="./assets/js/staticWEBjs/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/jquery-ui.min.js"></script>
<!-- CSS -->
<link href="./assets/css/staticWEBcss/bootstrap.css" rel="stylesheet"
	media="screen">
<link href="./assets/css/staticWEBcss/animate.min.css" rel="stylesheet"
	media="screen">
<link href="./assets/css/staticWEBcss/slidebars.css" rel="stylesheet"
	media="screen">
<link href="./assets/css/staticWEBcss/style-blue.css" rel="stylesheet"
	media="screen" title="default">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/font-awesome.min.css">
<!-- <link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/smartadmin-production.min.css">	 -->

<!-- dependencies for ladda buttons starts-->
<link rel="stylesheet" href="assets/css/ladda-themeless.min.css">
<script src="assets/js/spin.min.js"></script>
<script src="assets/js/ladda.min.js"></script>
<!-- dependencies for ladda buttons ends-->

<style>
.input-group-addon {
	border-radius: 0;
	background-color: #7093A1;
	border-color: #7093A1;
	color: #fff;
}

.carousel-razon {
	background-color: #0099da;
	background-color: rgba(122, 147, 158, 0.9);
}

.carousel-control .glyphicon-chevron-left {
	left: 22%;
}

.carousel-control .glyphicon-chevron-right {
	right: 22%
}

.carousel-list li {
	font-size: 1.2em;
}

.customColor {
	color: red;
}

.modal-dialog {
	margin: 73px auto;
}

.allThreeButton {
	z-index: 1;
}

.icon-ar.icon-ar-lg {
	width: 70px;
	height: 70px;
	font-size: 35px;
	line-height: 2.2;
}

.custBlock {
	display: block;
}

.activeTab {
	background: #4d7db3;
	color: #fff;
}

/* for header css */
.main-header {
	background-color: #f1f1f1 !important;
	border-bottom: solid 1px #ddd !important;
}

.main-header .page-title {
	color: black !important;
}

.fixed {
	position: fixed;
	top: 0;
	z-index: 100;
	width: 90%;
}

.dropdownStyle {
	display: inline-block;
	margin-left: 20px;
	z-index: 60000000;
	vertical-align: top;
}

.styleRegister {
	margin: 0;
	display: inline-block;
	padding: 7px 10px;
	color: #000;
	background-color: #f1f1f1;
	-webkit-box-shadow: inset 0 0 1px #ffffff;
	-moz-box-shadow: inset 0 0 1px #ffffff;
	box-shadow: inset 0 0 1px #ffffff;
	border: solid 1px #e3e6e8;
	border-top: 0;
}

.styleRegister:hover {
	text-decoration: none;
}

.styleBackLogin:hover {
	text-decoration: none;
}

.checkbox label {
	padding-left: 0px !important;
}

/* For validation purpose by hari */

/*  .form-group {
margin: 0;
outline: 0;
color: #666;
position: relative;
} 
 .form-group .input {
position: relative;
display: block;
font-weight: 400;
}  */
.state-error+em {
	display: block;
	position: relative;
	top: -15px;
	font-style: normal;
	font-size: 11px;
	line-height: 15px;
	color: #D56161;
}
/* gritter custom css */
.custSize {
	font-size: 37px;
}

.SmallBox span {
	letter-spacing: 0px !important;
	margin: 0px 0 !important;
}

.custPosit {
	top: 6px !important;
	font-size: 17px;
}

.customStyle {
	display: table !important;
}

.SmallBox {
	position: fixed;
	right: 5px;
	/* top: 20px; */
	width: 430px;
	color: #fff;
	z-index: 9999;
	overflow: hidden;
	border: 1px solid transparent;
	bottom: 50px;
}

.SmallBox .foto {
	position: absolute;
	left: 17px;
	top: 30%;
}

.SmallBox .textoFoto {
	width: 78%;
	margin: 3px 20px 3px 80px;
	float: left;
}

.miniIcono {
	height: 100%;
	font-size: 20px;
}

.custPosit {
	top: 6px !important;
	font-size: 17px;
}

.miniPic {
	position: absolute;
	bottom: 8px;
	right: 9px;
}

.alert>.close {
	text-shadow: 0 1px 0 #fff;
	filter: alpha(opacity = 30);
	opacity: .8;
	color: #fff;
}

.alert-danger {
	border-color: #953b39;
	color: #fff;
	background: #c26565;
	text-shadow: none;
	padding-top: 0px;
	padding-bottom: 0px;
	margin-bottom: 3px;
}

.alert-success {
	border-color: #8ac38b;
	color: #356635;
	background: #cde0c4;
	text-shadow: none;
	padding-top: 0px;
	padding-bottom: 0px;
	margin-bottom: 3px;
}

.sizeFont {
	font-size: 13px;
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
<!-- <script>
	try {
		console.log('${ErrorMsg}');
		var data ='${ErrorMsg}'; 
		console.log(data);
		if(data != ""){
			console.log("here");
			$("#loginFormMenu").modal('show');
		}
		//var data =  JSON.parse('${foo}');eval('(' + '${ErrorMsg}' + ')');
	//	console.log("Error Msg" + data);
	} catch (e) {
		console.log(e);
	}
</script> -->


<body style="overflow: visible; overflow-y: hidden;">

	<div id="preloader" style="display: none;">
		<div id="status" style="display: none;">&nbsp;</div>
	</div>
	<div id="sb-site">
		<div class="boxed">
			<header id="header-full-top" class=" header-full">
				<div class="container">
					<div class="header-full-title hidden-xs">
						<h1 class="animated fadeInRight">
							<a href="#" class="active"><span> Ecosystem</span></a>
						</h1>
						<p class="animated fadeInRight"
							style="margin-bottom: 5px !important; visibility: hidden">content</p>
					</div>
					<nav class="top-nav" style="z-index: 1;">

						<div class="dropdownStyle animated fadeInDown animation-delay-11">
							<a href="#getSatartedModal" id="newUserForm"
								class="styleRegister" onclick="hideLoginFormModal()"
								style="text-decoration: none;" data-toggle="modal"> <i
								class="fa fa-book"></i> New User
							</a>
						</div>

						<div class="dropdown animated fadeInDown animation-delay-11">
							<a href="#loginModal" id="loginUserForm" class="dropdown-toggle"
								data-toggle="dropdown"><i class="fa fa-user"></i> Login</a>
						</div>

						<div
							class="dropdown-menu dropdown-menu-right dropdown-login-box animated fadeInUp login-dropdown-menu"
							id="loginFormMenu">
							<form role="form" id="showLogin" method="post" action="home" autocomplete="off">
								<button type="button" class="close" data-dismiss="dropdown-menu"
									aria-hidden="true" onclick="hideLogin('loginFormMenu')">×</button>

								<div class="alert alert-danger hide" id="crendialsErrorDiv"
									style="margin-right: 17px;">
									<button class="close" data-dismiss="alert">×</button>
									<div>
										<strong>Ecosystem Error :</strong>
									</div>
									<span class="sizeFont">${ErrorMsg}</span>
								</div>
								<div class="alert alert-success hide" id="crendialsActivatedDiv"
									style="margin-right: 17px;">
									<button class="close" data-dismiss="alert">×</button>
									<div>
										<strong>Ecosystem Success :</strong>
									</div>
									<span class="sizeFont">${resultForActivating}</span>
								</div>
								<div class="alert alert-danger hide"
									id="crendialsActivationLinkDiv" style="margin-right: 17px;">
									<button class="close" data-dismiss="alert">×</button>
									<div>
										<strong>Ecosystem Error :</strong>
									</div>
									<span class="sizeFont">${errorResultForActivating}</span>
								</div>


								<%-- <div>
									<font color="red">${ErrorMsg}</font>
								</div> --%>
								<%-- <div>
									<font color="green">${resultForActivating}</font>
								</div>
								<div>
									<font color="red">${errorResultForActivating}</font>
								</div> --%>
								<h4 style="padding-bottom: 15px;">Ecosystem Login</h4>
								<div class="form-group">
									<div class="form-group login-input customStyle"
										style="padding-bottom: 5px;">
										<span class="input-group-addon"><i class="fa fa-user"></i></span>
										<input type="text" class="form-control" name= "loginEmailId" id ="loginEmailId"
										placeholder="Email ID" onfocus="setIdAndName();"
											onchange="getMappedRoles()" autocomplete="off">
									</div>
									<div class="form-group login-input customStyle"
										style="padding-bottom: 5px;">
										<span class="input-group-addon"><i class="fa fa-lock"></i></span>
										<input type="password" class="form-control passwordInput" placeholder="Password">
									</div>
									<div class="form-group login-input customStyle state-success"
										style="padding-bottom: 5px;">
										<span id="defaultRole" class="input-group-addon"
											style="padding-right: 2px; display: none"> <i
											class="fa fa-user" style="font-size: 18px;"></i> <i
											class="fa  fa-check-circle"
											style="color: green; position: relative; left: -8px; top: 3px;"></i>Default
											Role
										</span> <span class="input-group-addon " id="beforeSelectDefault" style = "display: none"
											> <i class="fa fa-user"></i>
										</span> <select class="form-control show-tick" id="userRolesForLogin" style = "display: none"
											name="userRolesForLogin">
											<option value="">Select User Role</option>
										</select>
									</div>

									<!-- <div class="form-group login-input customStyle" style="padding-bottom:5px;">
									<span class="input-group-addon"><i class="fa fa-user-md"></i></span>
											<select class="form-control selectpicker show-tick" id="userRolesForLogin" name="userRolesForLogin" style="display: none;width:50%;float:left">
												<option value="1">Select User Role</option>
											</select>
									<label style="cursor: pointer;font-size: 13px;width:50%;float:right;text-align:center;position: relative;top: 7px;color: #7093A1;"> 
  											[ <b >Default </b> ] User
										</label>	
									</div> -->
									<div class="input-group login-input">
										<label id="defaultRoleLabel"
											style="display: none; cursor: pointer; font-size: 13px; margin-top: -15px;">
											<input type="checkbox" style="cursor: pointer;"
											id="defaultRoleCheckBox" name="defaultRoleCheckBox"
											onclick=""> Make this default
										</label>
									</div>


									<!-- <div class="form-group login-input customStyle" >
										<span class="input-group-addon"><i class="fa fa-globe"></i></span>
										<input type="password" class="form-control" id="loginDomain" name="loginDomain" placeholder="Domain">
									</div> -->
									<div class="input-group login-input">
										<label style="cursor: pointer; font-size: 13px;"> <input
											type="checkbox" style="cursor: pointer;"
											id="rememberMeCheckBox" name="rememberMeCheckBox"
											onclick="settingValuesInCookies()"> Remember me
										</label>
									</div>
									<div class="checkbox pull-left">
										<label
											style="cursor: pointer; font-size: 13px; color: #0087c1;"
											onclick="showPasswordDiv()"> Forgot Password ? </label> <br>
										<label href="#getSatartedModal" data-toggle="modal"
											data-dismiss="modal" style="font-size: 13px; color: #0087c1;"
											onclick="hideLoginModal()"> Not registered yet ? </label> <br>
										<label style="color: #0087c1; font-size: 13px;"
											onclick="showActivationDiv()"> Send Me Activation
											Link </label>
									</div>
									<div class="checkbox pull-left"></div>
									<button type="submit" id="loginSubmit" disabled
										class="btn btn-ar btn-primary pull-right ladda-button"
										data-style="expand-right" onclick="setLaddaButton(this)"
										style="margin-top: 45px !important; width: 95px;">Login</button>
									<div class="clearfix"></div>
								</div>
							</form>
							<div style="display: none;" id="showNow">
								<h4>Retrieve Password</h4>

								<div class="alert alert-success hide" id="forgotSuccessDiv">
									<button class="close" data-dismiss="alert">×</button>
									<div>
										<strong>Ecosystem Success :</strong>
									</div>
									<span class="sizeFont">${PasswordMsg}</span>
								</div>
								<div class="alert alert-danger hide" id="forgotErrorDiv">
									<button class="close" data-dismiss="alert">×</button>
									<div>
										<strong>Ecosystem Error :</strong>
									</div>
									<span class="sizeFont">${PasswordErrorMsg}</span>
								</div>


								<%-- <div>
									<font color="green">${PasswordMsg}</font>
								</div>
								<div>
									<font color="red">${PasswordErrorMsg}</font>
								</div> --%>

								<form id="forgotPasswordForm" method="post"
									action="forgotPassword">
									<div class="input-group login-input">
										<span class="input-group-addon"><i
											class="fa fa-envelope-o"></i></span> <input type="email"
											class="form-control" id="sendingEmailId"
											name="sendingEmailId" placeholder="Email ID..">
									</div>
									<div class="checkbox pull-left">
										<a onclick="showLoginDiv()" class="styleBackLogin"
											style="cursor: pointer;"> Back to login <i
											class="fa  fa-caret-left"></i></a>
									</div>
									<button type="submit" class="btn btn-ar btn-primary pull-right"
										onsubmit="resetForgottenPasswordForm()">Send</button>
									<div class="clearfix"></div>
								</form>
							</div>
							<div id="showActivation" style="display: none;">
								<h4>Enter your Email ID</h4>
								<div class="alert alert-success hide" id="activationSuccessDiv">
									<button class="close" data-dismiss="alert">×</button>
									<div>
										<strong>Ecosystem Success :</strong>
									</div>
									<span class="sizeFont">${ActivationMsg}</span>
								</div>
								<div class="alert alert-danger hide" id="activationErrorDiv">
									<button class="close" data-dismiss="alert">×</button>
									<div>
										<strong>Ecosystem Error :</strong>
									</div>
									<span class="sizeFont">${ActivationErrorMsg}</span>
								</div>


								<%-- <div>
									<font color="green">${ActivationMsg}</font>
								</div> --%>
								<%-- <div>
									<font color="red">${ActivationErrorMsg}</font>
								</div> --%>
								<form id="sendActivationLinkForm" method="post"
									action="sendingEmailForActivation">
									<div class="input-group login-input"
										style="padding-bottom: 10px;">
										<span class="input-group-addon"><i
											class="fa fa-envelope-o"></i></span> <input type="email"
											class="form-control" name="activationEmailId"
											id="activationEmailId" placeholder="Email ID..">
									</div>
									<p style="font-size: 13px;">Activation Link will be sent to
										this email id.</p>
									<div class="checkbox pull-left">
										<a onclick="showLoginBlock()" class="styleBackLogin"
											style="cursor: pointer;"> Back to login <i
											class="fa  fa-caret-left"></i></a>
									</div>
									<button type="submit" class="btn btn-ar btn-primary pull-right"
										onsubmit="resetForgottenActivationForm()">Submit</button>
									<div class="clearfix"></div>
								</form>
							</div>
						</div>
					</nav>
				</div>
				<!--container ends here  -->
			</header>
			<!--header ends here  -->

			<nav
				class="navbar navbar-static-top navbar-default navbar-header-full navbar-dark"
				style="z-index: 0;" role="navigation" id="header">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <i
								class="fa fa-bars"></i>
						</button>
						<a class="navbar-brand hidden-lg hidden-md hidden-sm active"
							href="#">Eco <span>system</span></a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav" id="menu">
							<li class="findIt" id="homeLink"><a href="staticHome">Home</a>
							</li>
							<li class="findIt" id="productLink"><a href="staticProduct">Products</a>
							</li>
							<li class="findIt" id="aboutLink"><a href="staticAbout">About</a>
							</li>
							<li class="findIt" id="contactLink"><a href="staticContact">Contacts</a>
							</li>
						</ul>
					</div>
					<!-- navbar-collapse -->
				</div>
				<!-- container -->
			</nav>
		</div>
	</div>

	<footer id="footer" style="position: absolute; top: 100%; width: 100%">
		<p>
			© 2014 <a href="#"> Report & Tracking</a> , inc. All rights reserved.
		</p>
	</footer>

	<!--start of modal -->
	<div class="modal fade" id="getSatartedModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetForm()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Register To
						Ecosystem</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="userRegistrationForm"
						novalidate="novalidate" method="post"
						commandName="temporaryRegistrationForm">
						<div class="col-sm-4">
							<p class="alert alert-border alert-info"
								style="background-color: none; border-right-width: 1px !important; height: 307px">
								Your name and email-id is safe with us, we will not be sending
								any <label>adds</label> or <label>promotional events</label> to
								your email-id
							</p>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<label for="userName">Name</label> <input type="text"
									class="form-control" id="userName" name="userName"
									placeholder="Enter name">
							</div>
							<div class="form-group">
								<label for="emailId">Email address</label> <input type="email"
									class="form-control" id="emailId" name="emailId"
									placeholder="Enter email">
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									class="form-control" name="password" id="password"
									placeholder="Enter password">
							</div>
							<div class="form-group">
								<label for="confirmPassword">Re Enter Password</label> <input
									type="password" class="form-control" id="confirmPassword"
									name="passwordConfirm" placeholder="Re-enter your password">
							</div>
						</div>
						<div class="modal-footer" style="border: none;">
							<div class="row col-md-12">
								<div class="col-md-6 pull-left">
									<label href="#loginModal" data-dismiss="modal"
										class="pull-left"
										style="color: #0087c1; font-size: 13px; cursor: pointer;"
										onclick="openLoginModal()"> Already Registered ? </label>
								</div>
								<div class="col-md-6" style="position: relative; left: 59px;">
									<button type="submit"
										class="btn btn-ar btn-primary ladda-button"
										data-style="expand-right" id="regButtonId"
										onclick="setLaddaButtonRegisterPage(this);">Register</button>
									<button type="button" data-dismiss="modal"
										class="btn btn-ar btn-default pull-right"
										onclick="resetForm()">Cancel</button>
								</div>
							</div>
						</div>
						<div class="message">

							<div class="alert alert-danger" id="">
								<button class="close" data-dismiss="alert">×</button>
								<div>
									<strong>This Email ID is already added in the system :
										Have You </strong>
								</div>
								<div>
									<h6 onclick="showPasswordDivFromReg()"
										style="color: rgb(224, 222, 152);; cursor: pointer">
										<i class="ace-icon fa fa-hand-o-right blue bigger-125"></i>
										Forgotten Your Password ?
									</h6>
								</div>
							</div>


							<!-- <div class="alert alert-warning fade in">
								<h4 class="alert-heading">

									This Email ID is already added in the system.Have You<label 
										style="position: relative; right: -40px; cursor: pointer;"
										></label>
								</h4>
							</div> -->
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- end of modal -->
	<div id="back-top" style="display: none;">
		<a href="#header"><i class="fa fa-chevron-up"></i></a>
	</div>

	<div id="divSmallBoxes"></div>
	<div id="divMiniIcons"></div>
	<div id="divbigBoxes"></div>

	<script src="./assets/js/staticWEBjs/bootstrap.min.js"></script>
	<script src="./assets/js/staticWEBjs/login.js"></script>
	<script src="./assets/js/staticWEBjs/staticHeaderFooter.js"></script>
	<!-- JQUERY VALIDATE -->
	<script src="assets/js/jquery.gritter.min.js"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<!-- <script type="text/javascript" src="assets/js/app.min.js"></script> -->
	<script src="assets/js/app.config.js"></script>
	<script src="assets/js/SmartNotification.min.js"></script>
	<script>
		$(document).ready(function() {
			
			var el = $("findIt");

			$(el).click(function() {
				el.each(function() {
					$(this).removeClass("activeTab");
				});
				$(this).addClass("activeTab");
			});
		});
		
		$.ajax({
			url:"getSessionValues",
			type:"POST",
			success:function(temp_result){
				var result=JSON.parse(temp_result);
				if(result.selectedUserDetails!=undefined){
				$("#loginFormMenu").css('display','block')
				$("#loginEmailId").val(result.selectedUserDetails.emailId);
				$("#loginPassword").val(result.password);
				getMappedRoles();
			  }
			}
		});
		
	</script>

	<script>
		$(document).ready(function() {
			$('.dropdown').click(function() {
				$('.login-dropdown-menu').slideToggle();
				$('html, body').animate({
					scrollTop : 0
				}, 800);
			});
		});
	</script>

	<!-- <script>
		$(".dropdown-menu").click(function(e){
		    e.stopPropagation();
		});
	
		$(document).click(function(){
		    $(".dropdown-menu").hide();
		});
	</script> -->

	<script>
		function showPasswordDiv() {
			if ($('#showNow').css('display') == 'none') {
				$("#showLogin").slideUp();
				$("#showNow").slideDown();
			}
		}

		function showLoginDiv() {
			if ($('#showNow').css('display') == 'block') {
				$("#showLogin").slideDown();
				$("#showNow").slideUp();
			}
			resetForgottenPasswordForm();
		}

		function showActivationDiv() {
			if ($('#showActivation').css('display') == 'none') {
				$("#showLogin").slideUp();
				$("#showActivation").slideDown();
			}
			resetLoginForm();
		}

		function showLoginBlock() {
			if ($('#showActivation').css('display') == 'block') {
				$("#showLogin").slideDown();
				$("#showActivation").slideUp();
			}
			resetForgottenActivationForm();
		}

		function openLoginModal() {
			$("#loginFormMenu").slideDown();
			$(".message").hide();
		}

		function hideLoginModal() {
			$("#loginFormMenu").slideUp("fast");
		}

		function hideLoginFormModal() {
			$("#loginFormMenu").slideUp("fast");
		}

		function showPasswordDivFromReg() {
			$("#getSatartedModal").modal('hide');
			$("#loginFormMenu").slideDown();
			$("#showLogin").slideUp();
			$("#showNow").slideDown();
			resetForm();
			$(".message").hide();
		}
	</script>

	<script>
		function hideLogin(element1) {
			$("#beforeSelectDefault").hide();
			$("#defaultRole").hide();
			resetLoginForm();
			element1 = document.getElementById(element1);
			if (element1.style.display == 'block'
					|| element1.style.display == '')
				element1.style.display = 'none';
			else
				element1.style.display = 'block';
			return;
		}
	</script>

	<script>
		$('#showLogin input').keydown(function(e) {
			if (e.keyCode == 13) {
				$('#showLogin').submit();
			}
		});
		$('#forgotPasswordForm input').keydown(function(e) {
			if (e.keyCode == 13) {
				$('#forgotPasswordForm').submit();
			}
		});
		$('#sendActivationLinkForm input').keydown(function(e) {
			if (e.keyCode == 13) {
				$('#sendActivationLinkForm').submit();
			}
		});
	</script>

	<script>
		try {
			var data = '${ErrorMsg}';
			var dataForActivation = '${resultForActivating}';
			var dataForActivationError = '${errorResultForActivating}';
			var activation = '${ActivationMsg}';
			var activationError = '${ActivationErrorMsg}';
			var forgot = '${PasswordMsg}';
			var forgotError = '${PasswordErrorMsg}';

			// for loginCredentials
			if (data != "" || dataForActivation != ""
					|| dataForActivationError != "") {
				console.log("here");
				$("#loginFormMenu").addClass("custBlock");
			}
			if (data != "") {
				$("#crendialsErrorDiv").removeClass("hide");
			}
			if (dataForActivation != "") {
				$("#crendialsActivatedDiv").removeClass("hide");
			}
			if (dataForActivationError != "") {
				$("#crendialsErrorDiv").removeClass("hide");
			}

			// for activationPassword 
			if (activation != "" || activationError != "") {
				$("#loginFormMenu").addClass("custBlock");
				$("#showLogin").slideUp();
				$("#showActivation").slideDown();
			}
			if (activationError != "") {
				$("#activationErrorDiv").removeClass("hide");
			}
			if (activation != "") {
				$("#activationSuccessDiv").removeClass("hide");
			}

			//for forgotPassword
			if (forgot != "" || forgotError != "") {
				$("#loginFormMenu").addClass("custBlock");
				$("#showLogin").slideUp();
				$("#showNow").slideDown();
			}
			if (forgot != "") {
				$("#forgotSuccessDiv").removeClass("hide");
			}
			if (forgotError != "") {
				$("#forgotErrorDiv").removeClass("hide");
			}

			//var data =  JSON.parse('${foo}');eval('(' + '${ErrorMsg}' + ')');
			//	console.log("Error Msg" + data);
		} catch (e) {
			console.log(e);
		}
	</script>
</body>

</html>
