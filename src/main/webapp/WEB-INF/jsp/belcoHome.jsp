<!DOCTYPE html>
<html lang="en-us">
<head>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Home- Elecnor</title>
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

<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->

<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/smartadmin-skins.min.css">

<!-- SmartAdmin RTL Support is under construction
			 This RTL CSS will be released in version 1.5
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> -->

<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/demo.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/datepicker.css">

<!-- #FAVICONS -->
<link rel="shortcut icon" href="assets/img/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="assets/img/favicon.ico" type="image/x-icon">

<!-- #GOOGLE FONT -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/smartadmin-production.min.css">

<style type="text/css">

/* CSS for shortcuts starts from here */
.shortcutAlign {
	text-align: center !important;
}

/* CSS for shortcuts ends here */

/* Css for Summernote Editor Starts from here */
.smart-form .btn {
	padding-right: 12px;
	padding-bottom: 4px;
	padding-left: 4px;
	padding-top: 4px;
}

.note-editor {
	border: 1px solid #a9a9a9 !important;
}

.note-editor .note-editable {
	background: #fff !important;
}

.note-resizebar {
	display: none !important;
}

;

/* Css for Summernote Editor Ends here */
.jqstooltip {
	position: absolute;
	left: 0px;
	top: 0px;
	visibility: hidden;
	background: rgb(0, 0, 0) transparent;
	background-color: rgba(0, 0, 0, 0.6);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
	color: white;
	font: 10px arial, san serif;
	text-align: left;
	white-space: nowrap;
	padding: 5px;
	border: 1px solid white;
	z-index: 10000;
}

.jqsfield {
	color: white;
	font: 10px arial, san serif;
	text-align: left;
}

.datepicker {
	z-index: 99999;
}

.modal-header {
	background-color: rgba(248, 248, 248, .9);
}

h4.modal-title {
	font-weight: 600;
}

.customColor {
	background: black;
}

table,th.sorting,th.sorting_asc {
	text-align: center;
}

.nav-tabs>li.active>a {
	box-shadow: 0 -4px 0 #57889c;
}

.jarviswidget-ctrls a {
	color: white;
}
</style>
<style>
/* css for plus btn for opening  all modal in each jsp */
.customLabel {
	padding: 3px 12px;
	border-bottom: 1px solid #5C5353;
	border-right: 1px solid #5C5353;
	border-top: 1px solid #5C5353;
}

.modalBtnGroup {
	position: relative;
	top: 3px;
}

.customBtnName {
	padding: 3px 12px;
	background-color: #707070;
	border: 1px solid #515A63;
}

.customBtnName:hover {
	color: #fff;
}
</style>
<style>
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
/* css for tooltip in dashboard */
.divStyle {
	background: black;
	text-align: center;
	font: 25px sans-serif;
}

.divFontStyle {
	color: #fff;
}
</style>
</head>

<!--

	TABLE OF CONTENTS.
	
	Use search to find needed section.
	
	===================================================================
	
	|  01. #CSS Links                |  all CSS links and file paths  |
	|  02. #FAVICONS                 |  Favicon links and file paths  |
	|  03. #GOOGLE FONT              |  Google font link              |
	|  04. #APP SCREEN / ICONS       |  app icons, screen backdrops   |
	|  05. #BODY                     |  body tag                      |
	|  06. #HEADER                   |  header tag                    |
	|  07. #PROJECTS                 |  project lists                 |
	|  08. #TOGGLE LAYOUT BUTTONS    |  layout buttons and actions    |
	|  09. #MOBILE                   |  mobile view dropdown          |
	|  10. #SEARCH                   |  search field                  |
	|  11. #NAVIGATION               |  left panel & navigation       |
	|  12. #MAIN PANEL               |  main panel                    |
	|  13. #MAIN CONTENT             |  content holder                |
	|  14. #PAGE FOOTER              |  page footer                   |
	|  15. #SHORTCUT AREA            |  dropdown shortcuts area       |
	|  16. #PLUGINS                  |  all scripts and plugins       |
	
	===================================================================
	
	-->

<!-- #BODY -->
<!-- Possible Classes

		* 'smart-skin-{SKIN#}'
		* 'smart-rtl'         - Switch theme mode to RTL (will be avilable from version SmartAdmin 1.5)
		* 'menu-on-top'       - Switch to top navigation (no DOM change required)
		* 'hidden-menu'       - Hides the main menu but still accessable by hovering over left edge
		* 'fixed-header'      - Fixes the header
		* 'fixed-navigation'  - Fixes the main menu
		* 'fixed-ribbon'      - Fixes breadcrumb
		* 'fixed-footer'      - Fixes footer
		* 'container'         - boxed layout mode (non-responsive: will not work with fixed-navigation & fixed-ribbon)
	-->
<body class="desktop-detected" id="mainDiv">

	<!-- End Of Modal Box -->
	<!-- #HEADER -->
	<header id="header">
		<div id="logo-group">

			<!-- PLACE YOUR LOGO HERE -->
			<span id="logo">
				<center>
					<img src="./assets/img/greenEco.png"
						style="height: 35px; width: 75px; position: relative; top: -5px;"
						alt="Belco logo">
				</center>
			</span>
			<!-- END LOGO PLACEHOLDER -->

			<!-- Note: The activity badge color changes when clicked and resets the number to 0
					 Suggestion: You may want to set a flag when this happens to tick off all checked messages / notifications -->
			<span title="Notification" id="activity" class="activity-dropdown">
				<i class="fa fa-user"></i> <b
				class="badge bg-color-red bounceIn animated"> 10 </b>
			</span>

			<!-- AJAX-DROPDOWN : control this dropdown height, look and feel from the LESS variable file -->
			<div class="ajax-dropdown">

				<!-- the ID links are fetched via AJAX to the ajax container "ajax-notifications" -->
				<div class="btn-group btn-group-justified" data-toggle="buttons">
					<label class="btn btn-default" id="mail"
						onclick="getnotification(this.id)"> <input type="radio"
						name="activity" id="notification"> Msgs <span
						class="logCount"></span>
					</label> <label class="btn btn-default" id="notifications"
						onclick="getnotification(this.id)"> <input type="radio"
						name="activity" id="notification"> notify <span
						class="logCount"></span>
					</label> <label class="btn btn-default" id="tasks"
						onclick="getnotification(this.id)"> <input type="radio"
						name="activity" id="notification"> Tasks <span
						class="logCount"></span>
					</label>
				</div>

				<!-- notification content -->
				<div class="ajax-notifications custom-scroll" id="notificationList">

					<div class="alert alert-transparent">
						<h4>Click a button to show messages here</h4>
						This blank page message helps protect your privacy, or you can
						show the first message here automatically.
					</div>

					<i class="fa fa-lock fa-4x fa-border"></i>

				</div>
				<!-- end notification content -->

				<!-- footer: refresh area -->
				<span> <span id="statusTime"></span>
					<button type="button"
						data-loading-text="&lt;i class=&#39;fa fa-refresh fa-spin&#39;&gt;&lt;/i&gt; Loading..."
						class="btn btn-xs btn-default pull-right">
						<i class="fa fa-refresh"></i>
					</button>
				</span>
				<!-- end footer -->

			</div>
			<!-- END AJAX-DROPDOWN -->
		</div>

		<!-- #PROJECTS: projects dropdown -->
		<div class="project-context hidden-xs">

			<span class="label">Projects:</span> <span
				class="project-selector dropdown-toggle" data-toggle="dropdown"><spring:message
					code="Home.MainPageHead.RecentApplications" /> <b
				class="badge bounceIn animated" style="background: #5cb85c"
				id="selectedProjectSpan"> </b> <i class="fa fa-angle-down"></i> </span>

			<!-- Suggestion: populate this list with fetch and push technique -->
			<ul class="dropdown-menu" id="projectsPlan">
				<!-- <li onclick="changeSelectedProjectSpan(this.value);" value="1">
					<a target="_blank" href="http://54.186.90.0:8080/ElecnorMPR">MPR</a></li>
				<li onclick="changeSelectedProjectSpan(this.value);" value="2">
					<a target="_blank" href="http://54.186.90.0:8080/ElecnorFTS">FTS</a></li>
				<li onclick="changeSelectedProjectSpan(this.value);" value="3">
					<a target="_blank" href="http://54.186.90.0:8080/ElecnorPD">PD</a></li> -->
				<li class="divider"></li>
				<li><a href="javascript:void(0);"><i
						class="fa fa-power-off"></i> Clear</a></li>
			</ul>
			<!-- end dropdown-menu-->

		</div>
		<!-- end projects dropdown -->

		<!-- #TOGGLE LAYOUT BUTTONS -->
		<!-- pulled right: nav area -->
		<div class="pull-right">

			<!-- collapse menu button -->
			<div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" data-action="toggleMenu"
					title="Collapse Menu"><i class="fa fa-reorder"></i></a>
				</span>
			</div>
			<!-- end collapse menu -->
			<!-- <div id="addCart" class="btn-header transparent pull-right">
				<span> <a title="Add Cart"><i class="fa fa-shopping-cart">
							Cart</i> <b class="badge bounceIn animated"> 0</b></a>
				</span>
			</div> -->

			<!-- #MOBILE -->
			<!-- Top menu profile link : this shows only when top menu is active -->
			<ul id="mobile-profile-img"
				class="header-dropdown-list hidden-xs padding-5">
				<li class=""><a href="#"
					class="dropdown-toggle no-margin userdropdown"
					data-toggle="dropdown"><i
						class="fa fa-user fa-fw txt-color-blue " style=""></i> </a>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"><i
								class="fa fa-cog"></i> Setting</a></li>
						<li class="divider"></li>
						<li><a href="#"
							class="padding-10 padding-top-0 padding-bottom-0"> <i
								class="fa fa-user"></i> <u>P</u>rofile
						</a></li>
						<li class="divider"></li>
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"
							data-action="toggleShortcut"><i class="fa fa-arrow-down"></i>
								<u>S</u>hortcut</a></li>
						<li class="divider"></li>
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"
							data-action="launchFullscreen"><i class="fa fa-arrows-alt"></i>
								Full <u>S</u>creen</a></li>
						<li class="divider"></li>
						<li><a href="#"
							class="padding-10 padding-top-5 padding-bottom-5"
							data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i>
								<strong><u>L</u>ogout</strong></a></li>
					</ul></li>
			</ul>

			<!-- logout button -->
			<div id="logout" class="btn-header transparent pull-right">
				<span> <a title="Sign Out" onclick="logoutToStaticHome()"
					data-action="" data-logout-msg=""><i class="fa fa-sign-out"></i></a>
				</span>
			</div>
			<!-- end logout button -->

			<!-- search mobile button (this is hidden till mobile view port) -->
			<div id="search-mobile" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0)" title="Search"><i
						class="fa fa-search"></i></a>
				</span>
			</div>
			<!-- end search mobile button -->

			<!-- #SEARCH -->
			<!-- input: search field -->
			<form action="#search.html" class="header-search pull-right">
				<input id="search-fld" type="text" name="param"
					placeholder="Find projects and more">
				<button type="submit">
					<i class="fa fa-search"></i>
				</button>
				<a href="javascript:void(0);" id="cancel-search-js"
					title="Cancel Search"><i class="fa fa-times"></i></a>
			</form>
			<!-- end input: search field -->

			<!-- fullscreen button -->
			<div id="fullscreen" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0);"
					data-action="launchFullscreen" title="Full Screen"><i
						class="fa fa-arrows-alt"></i></a>
				</span>
			</div>
			<!-- end fullscreen button -->

			<!-- #Voice Command: Start Speech -->
			<!-- <div id="speech-btn"
				class="btn-header transparent pull-right hidden-sm hidden-xs">
				<div>
					<a href="javascript:void(0)" title="Voice Command"
						data-action="voiceCommand"><i class="fa fa-microphone"></i></a>
					<div class="popover bottom">
						<div class="arrow"></div>
						<div class="popover-content">
							<h4 class="vc-title">
								Voice command activated <br> <small>Please speak
									clearly into the mic</small>
							</h4>
							<h4 class="vc-title-error text-center">
								<i class="fa fa-microphone-slash"></i> Voice command failed <br>
								<small class="txt-color-red">Must <strong>"Allow"</strong>
									Microphone
								</small> <br> <small class="txt-color-red">Must have <strong>Internet
										Connection</strong></small>
							</h4>
							<a href="javascript:void(0);" class="btn btn-success"
								onclick="commands.help()">See Commands</a> <a
								href="javascript:void(0);"
								class="btn bg-color-purple txt-color-white"
								onclick="$(&#39;#speech-btn .popover&#39;).fadeOut(50);">Close
								Popup</a>
						</div>
					</div>
				</div>
			</div> -->
			<!-- end voice command -->

			<!-- start of multi language -->
			<ul class="header-dropdown-list hidden-xs">
				<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="assets/img/blank.gif" class="flag flag-us"
						alt="United States" id="anchorTagForFlag"> <span
						id="countrySpan"> US</span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu pull-right">
						<li value="1" onclick="changeLangFlag(this.value);"><a
							href="home?lang=en"><img src="assets/img/blank.gif"
								class="flag flag-us" alt="United States"> English (US)</a></li>
						<li value="2" onclick="changeLangFlag(this.value);"><a
							href="home?lang=es_ES"><img src="assets/img/blank.gif"
								class="flag flag-es" alt="Spanish"> Espanol</a></li>
						<!-- <li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-fr" alt="France">
								Français</a></li>
						<li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-de" alt="German">
								Deutsch</a></li>
						<li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-jp" alt="Japan">
								日本語</a></li>
						<li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-cn" alt="China">
								中文</a></li>
						<li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-it" alt="Italy">
								Italiano</a></li>
						<li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-pt" alt="Portugal">
								Portugal</a></li>
						<li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-ru" alt="Russia">
								Русский язык</a></li>
						<li><a href="javascript:void(0);"><img
								src="assets/img/blank.gif" class="flag flag-kp" alt="Korea">
								한국어</a></li> -->
					</ul></li>
			</ul>
			<!-- ends of multi language -->
		</div>
		<!-- end pulled right: nav area -->

	</header>
	<!-- END HEADER -->

	<!-- #NAVIGATION -->
	<!-- Left panel : Navigation area -->
	<!-- Note: This width of the aside area can be adjusted through LESS variables -->
	<aside id="left-panel">

		<!-- User info -->
		<div class="login-info">
			<span> <!-- User image size is adjusted inside CSS, it should stay as is -->

				<a href="javascript:void(0);" id="show-shortcut"
				data-action="toggleShortcut"><i class="fa fa-lg fa-fw fa-user"></i>
					<span id="nameId"></span> <i class="fa fa-angle-down"></i> </a>

			</span>
		</div>
		<!-- end user info -->


		<nav>
			<!--  adding id by hari -->

			<ul style="">
				<li class="active"><a id="appStore" href="belcoApplicationStore"><i
						class="fa fa-lg fa-fw fa-shopping-cart"></i> <span
						class="menu-item-parent"><spring:message
								code="Home.SidePageHead.Applications" /></span></a></li>
				<li><a href="dashboard" title="Dashboard" id="dashboardId"><i
						class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent"><spring:message
								code="Home.SidePageHead.Dashboard" /></span></a></li>
				<li id="applicationInvoiceId"><a href="applicationInvoice"
					id="appInvoice" id="billingInvoiceNavigate"><i
						class="fa fa-lg fa-fw fa-book"></i> <span class="menu-item-parent"><spring:message
								code="Home.SidePageHead.BillingInvoice" /></span></a></li>
				<%-- <li id="projectId"><a href="project" id="proj"><i
						class="fa fa-lg fa-fw fa-suitcase"></i> <span
						class="menu-item-parent"><spring:message
								code="Home.SidePageHead.Jobs" /></span></a></li> --%>
				<li id=""><a href="#" ><i
						class="fa fa-lg fa-fw fa-suitcase"></i> <span
						class="menu-item-parent"><spring:message
								code="Home.SidePageHead.Jobs" /></span></a>
							<ul style="display: none;">
								<li id="chagOrder"><a href="project" id="changeOrder"><i
						class="fa fa-lg fa-fw fa-suitcase"></i> <span
						class="menu-item-parent">Manage Job</span></a></li>
						<li id="chagOrder"><a href="changeOrder" id="changeOrder"><i
						class="fa fa-lg fa-fw fa-stack-exchange"></i> <span
						class="menu-item-parent">Change Order</span></a></li>
							</ul>
				</li>				
				<li id="manageUserId"><a href="manageUser" id="mngUser"><i
						class="fa fa-lg fa-fw fa-user"></i> <span class="menu-item-parent"><spring:message
								code="Home.SidePageHead.ManageUser" /></span><span
						class="badge pull-right inbox-badge"></span></a></li>
				<li class="active" id="accountSettingId"><a href="#"
					id="accSettings"><i class="fa fa-lg fa-fw fa-gears"></i> <span
						class="menu-item-parent"><spring:message
								code="Home.SidePageHead.AccountSetting" /></span></a>
					<ul id="accSettingsMenu" style="display: none;">
						<li id="customerDirectoryId"><a href="customer"
							id="custDirectory"><i class="fa fa-lg fa-fw fa-group"></i> <span
								class="menu-item-parent"><spring:message
										code="Home.SidePageHead.CustomerDirectory" /></span></a></li>

						<li id="vendorDirectoryId"><a href="vendorDirectory"
							id="vendorDir"><i class="fa fa-lg fa-fw fa-group"></i> <span
								class="menu-item-parent"><spring:message
										code="Home.SidePageHead.VendorDirectory" /></span></a></li>
						<li id="contractDirectoryId"><a href="contractorDirectory"
							id="contractorDir"><i class="fa fa-lg fa-fw fa-group"></i> <span
								class="menu-item-parent"><spring:message
										code="Home.SidePageHead.ContractorDirectory" /></span></a></li>

						<li class="active" id="licenseID"><a href="#" id="license"><i
								class="fa fa-lg fa-fw  fa-key"></i> <span
								class="menu-item-parent"><spring:message
										code="Home.SidePageHead.License" /></span></a>
							<ul style="display: none;">
								<li id="stateLicense"><a href="stateLicense"
									id="statLicense"><i class="fa fa-lg fa-map-marker"></i> <span
										class="menu-item-parent"><spring:message
												code="Home.SidePageHead.StateLicense" /></span></a></li>
								<li id="localicense"><a href="localLicense"
									id="localLicense"><i class="fa fa-lg fa-fw fa-barcode"></i>
										<span class="menu-item-parent"><spring:message
												code="Home.SidePageHead.LocalLicense" /></span></a></li>
							</ul></li>

						<li id="typesOfProjectId"><a href="typesOfProject"
							id="projectType"><i class="fa fa-lg fa-fw fa-file"></i> <span
								class="menu-item-parent"><spring:message
										code="Home.SidePageHead.ProjectTypes" /></span></a></li>
						<li id="departmentDetailsId"><a href="departmentDetails"
							id="deptDetails"><i class="fa fa-lg fa-fw fa-gear"></i> <span
								class="menu-item-parent"><spring:message
										code="Home.SidePageHead.ManageDepartment" /></span></a></li>
						<li id="itemDetailId"><a href="itemDetail" id="maalDetails"><i
								class="fa fa-lg  fa-sitemap"></i> <span class="menu-item-parent"><spring:message
										code="Home.SidePageHead.ManageItemDB" /></span></a></li>
						<li id="budgetFormId"><a href="budgetForm" id="budgtForm"><i
								class="fa fa-lg fa-fw fa-barcode"></i> <span
								class="menu-item-parent"><spring:message
										code="Home.SidePageHead.BudgetCode" /></span></a></li>
					</ul></li>






			</ul>
		</nav>
		<span class="minifyme" data-action="minifyMenu"> <i
			class="fa fa-arrow-circle-left hit"></i>
		</span>

	</aside>
	<!-- END NAVIGATION -->

	<!-- #MAIN PANEL -->
	<div id="main" role="main">

		<!-- RIBBON -->
		<!-- Adding Settings UI dynamically @demo.min.js By Ankur- Removing from here coz of problem -->
		<div id="ribbon">

			<span class="ribbon-button-alignment"> <span id="refresh"
				class="btn btn-ribbon" data-action="resetWidgets"
				data-title="refresh" rel="tooltip" data-placement="bottom"
				data-original-title="&lt;i class=&#39;text-warning fa fa-warning&#39;&gt;&lt;/i&gt; Warning! This will reset all your widget settings."
				data-html="true"
				data-reset-msg="Would you like to RESET all your saved widgets and clear LocalStorage?"><i
					class="fa fa-refresh"></i></span>
			</span>

			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<li>Home</li>
				<li>Dashboard</li>
			</ol>
			<!-- end breadcrumb -->

			<!-- You can also add more buttons to the
				ribbon for further usability

				Example below:

				<span class="ribbon-button-alignment pull-right" style="margin-right:25px">
					<span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa fa-grid"></i> Change Grid</span>
					<span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa fa-plus"></i> Add</span>
					<span id="search" class="btn btn-ribbon" data-title="search"><i class="fa fa-search"></i> <span class="hidden-mobile">Search</span></span>
				</span> -->


		</div>
		<!-- END RIBBON -->
		<!-- Adding Modal Box, for reusability. This modal is used for sending Emails -->
		<!-- Modal Box Starts -->
		<div class="modal fade" id="emailComposeModalOpen" tabindex="-1"
			role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="resetComposeEmailForm()">
							<span class="badge pull-right inbox-badge customColor">
								&times;</span>
						</button>
						<h4 class="modal-title">
							<i class="icon-append fa fa-envelope-o txt-color-blue"></i>
							&nbsp;
							<spring:message code="ComposeMail.Heading" />
						</h4>
					</div>
					<div class="modal-body no-padding">

						<form method="POST" id="compose_email_form" class="smart-form"
							novalidate="novalidate">

							<fieldset>
								<section>
									<label class="input"> <i class="icon-append fa fa-user"></i>
										<input type="text" name="toEmail" id="toEmail"
										placeholder="<spring:message code="ComposeMail.label.To"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="ComposeMail.tooltip.To" /></b>
									</label>
								</section>

								<section>
									<label class="input"> <i class="icon-append fa fa-user"></i>
										<input type="text" name="ccEmail" id="ccEmail"
										placeholder="<spring:message code="ComposeMail.label.Cc"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="ComposeMail.tooltip.Cc" /></b>
									</label>
								</section>

								<section>
									<label class="input"> <i class="icon-append fa fa-tag"></i>
										<input type="text" name="emailSubject" id="emailSubject"
										placeholder="<spring:message code="ComposeMail.label.Subject"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="ComposeMail.tooltip.Subject" /></b>
									</label>
								</section>

								<section>
									<label for="file" class="input input-file">
										<div class="button">
											<input type="file" name="fileUploaded" id="fileUploaded"
												onchange="setFilePathToInputFieldMA(this.value);"><i
												class="fa fa-paperclip fa-lg"></i> &nbsp;
											<spring:message code="ComposeMail.button.BrowseAttachment" />
										</div> <input type="text" name="fileName" id="fileName"
										placeholder="<spring:message code="ComposeMail.label.Attachment"/>"
										readonly=""> <b class="tooltip tooltip-bottom-right"><spring:message
												code="ComposeMail.tooltip.Attachment" /></b>
									</label>
								</section>

								<section>
									<textarea class="emailBody" name="emailBodyMA" id="emailBodyMA"></textarea>
									<textarea style="display: none" name="emailBodyMAHidden"
										id="emailBodyMAHidden"></textarea>
									<b class="tooltip tooltip-bottom-right"></b>
								</section>

							</fieldset>

							<footer>
								<button class="btn btn-primary pull-right" type="submit"
									id="send">
									<spring:message code="ComposeMail.button.SendMail" />
									&nbsp;&nbsp;<i class="fa fa-share"></i>
								</button>
							</footer>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal Box Ends -->
		<!-- #MAIN CONTENT -->
		<div id="content" style="opacity: 1;"></div>
		<!-- #MAIN CONTENT ENds -->
	</div>
	<!-- END #MAIN PANEL -->

	<!-- #PAGE FOOTER -->
	<div class="page-footer" style="position: absolute; top: 100%">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<span class="txt-color-white"></span>
			</div>

			<div class="col-xs-6 col-sm-6 text-right hidden-xs">
				<div class="txt-color-white inline-block">
					<i class="txt-color-blueLight hidden-mobile">Last active <i
						class="fa fa-clock-o"></i> <strong>52 mins ago &nbsp;</strong>
					</i>
					<div class="btn-group dropup">
						<button
							class="btn btn-xs dropdown-toggle bg-color-blue txt-color-white"
							data-toggle="dropdown">
							<i class="fa fa-link"></i> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right text-left">
							<li>
								<div class="padding-5">
									<p class="txt-color-darken font-sm no-margin">Download
										Progress</p>
									<div class="progress progress-micro no-margin">
										<div class="progress-bar progress-bar-success"
											style="width: 50%;"></div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="padding-5">
									<p class="txt-color-darken font-sm no-margin">Server Load</p>
									<div class="progress progress-micro no-margin">
										<div class="progress-bar progress-bar-success"
											style="width: 20%;"></div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="padding-5">
									<p class="txt-color-darken font-sm no-margin">
										Memory Load <span class="text-danger">*critical*</span>
									</p>
									<div class="progress progress-micro no-margin">
										<div class="progress-bar progress-bar-danger"
											style="width: 70%;"></div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="padding-5">
									<button class="btn btn-block btn-default">refresh</button>
								</div>
							</li>
						</ul>
					</div>
					<!-- end btn-group-->
				</div>
				<!-- end div-->
			</div>
			<!-- end col -->
		</div>
		<!-- end row -->
	</div>
	<!-- END FOOTER -->

	<!-- #SHORTCUT AREA : With large tiles (activated via clicking user name tag)
			 Note: These tiles are completely responsive, you can add as many as you like -->
	<div id="shortcut">
		<ul>
			<li id="updateAccountInfoId"><a href="#updateAccountInfo"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa fa-user fa-4x"></i> <span
						class="shortcutAlign"><spring:message
								code="Home.SidePageHead.MyProfile" /> </span>
				</span>
			</a></li>
			<li id="updateDomainInfoId"><a href="#updateDomainInfo"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa fa-globe fa-4x"></i> <span
						class="shortcutAlign"><spring:message
								code="Home.SidePageHead.DomainInfo" /> </span>
				</span>
			</a></li>
			<li id="documentCenterId"><a href="#documentCenter"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa  fa-file-text fa-4x"></i> <span
						class="shortcutAlign"><spring:message
								code="Home.SidePageHead.DocumentCenter" /> </span>
				</span>
			</a></li>
			<li id="ticketCenterId"><a href="#ticketCenter"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa  fa-ticket fa-4x"></i> <span
						class="shortcutAlign"><spring:message
								code="Home.SidePageHead.TicketCenter" /></span>
				</span>
			</a></li>
			<li id="notificationId"><a href="#notification"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa fa-bullhorn fa-4x"></i> <span
						class="shortcutAlign"><spring:message
								code="Home.SidePageHead.Notification" /> </span>
				</span>
			</a></li>
			<li id="holidayId"><a href="#holiday"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span
						class="shortcutAlign">Holidays </span>
				</span>
			</a></li>
			<li id="couponId"><a href="#couponDetails"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa fa-barcode fa-4x"></i> <span
						class="shortcutAlign">Coupons </span>
				</span>
			</a></li>

		</ul>
	</div>



	<!-- END SHORTCUT AREA -->

	<!--================================================== -->

	<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->


	<!-- #PLUGINS -->
	<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	<script type="text/javascript" async="" src="assets/js/ga.js"></script>
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

	<!-- IMPORTANT: APP CONFIG -->
	<script src="assets/js/app.config.js"></script>

	<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>

	<!-- BOOTSTRAP JS -->
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- CUSTOM NOTIFICATION -->
	<script src="assets/js/SmartNotification.min.js"></script>

	<!-- JARVIS WIDGETS -->
	<script src="assets/js/jarvis.widget.min.js"></script>

	<!-- EASY PIE CHARTS -->
	<script src="assets/js/jquery.easy-pie-chart.min.js"></script>

	<!-- SPARKLINES -->
	<script src="assets/js/jquery.sparkline.min.js"></script>

	<!-- JQUERY VALIDATE -->
	<script src="assets/js/jquery.validate.min.js"></script>

	<!-- JQUERY MASKED INPUT -->
	<script src="assets/js/jquery.maskedinput.min.js"></script>

	<!-- JQUERY SELECT2 INPUT -->
	<!--<script src="assets/js/select2.min.js"></script>
 -->
	<!-- JQUERY UI + Bootstrap Slider -->
	<script src="assets/js/bootstrap-slider.min.js"></script>

	<!-- browser msie issue fix -->
	<script src="assets/js/jquery.mb.browser.min.js"></script>

	<!-- FastClick: For mobile devices: you can disable this in app.js -->
	<script src="assets/js/fastclick.min.js"></script>

	<!--[if IE 8]>
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
		<![endif]-->

	<!-- Demo purpose only -->
	<script src="assets/js/demo.min.js"></script>

	<!-- MAIN APP JS FILE -->
	<script src="assets/js/app.min.js"></script>

	<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
	<!-- Voice command : plugin -->
	<script src="assets/js/voicecommand.min.js"></script>
	<div class="modal fade" id="voiceModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>




	<div id="divSmallBoxes"></div>
	<div id="divMiniIcons"></div>
	<div id="divbigBoxes"></div>
	<script type="text/javascript" src="assets/js/customAppStoreTempUser.js"></script>
	<script type="text/javascript" src="assets/js/jquery.flot.cust.min.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery.fullcalendar.min.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery.flot.resize.min.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery-jvectormap-world-mill-en.js"></script>
	<script type="text/javascript"
		src="assets/js/jquery.flot.tooltip.min.js"></script>
	<script src="assets/js/datepicker.js"></script>
	<div class="jvectormap-label"></div>
	<div id="flotTip" style="display: none; position: absolute;"></div>
</body>
<script>
	/* DO NOT REMOVE : GLOBAL FUNCTIONS!
	 *
	 * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS
	 *
	 * // activate tooltips
	 * $("[rel=tooltip]").tooltip();
	 *
	 * // activate popovers
	 * $("[rel=popover]").popover();
	 *
	 * // activate popovers with hover states
	 * $("[rel=popover-hover]").popover({ trigger: "hover" });
	 *
	 * // activate inline charts
	 * runAllCharts();
	 *
	 * // setup widgets
	 * setup_widgets_desktop();
	 *
	 * // run form elements
	 * runAllForms();
	 *
	 ********************************
	 *
	 * pageSetUp() is needed whenever you load a page.
	 * It initializes and checks for all basic elements of the page
	 * and makes rendering easier.
	 *
	 */

	pageSetUp();
	verifyAndCorrectFlag();
	/* Script to Check Cookie and Change Flag Color */
	function verifyAndCorrectFlag() {
		try {
			if (document.cookie.split("=")[1] == "es_ES") {
				changeLangFlag(2);// Spanish Language In Cookie
			} else if (document.cookie.split("=")[1] == "en") {
				changeLangFlag(1);// English Language In Cookie
			}
		} catch (e) {
			console.log("No Cookie Found");
		}
	}

	/*
	 * PAGE RELATED SCRIPTS
	 */

	// pagefunction
	var pagefunction = function() {

		$(".js-status-update a").click(
				function() {
					var selText = $(this).text();
					var $this = $(this);
					$this.parents('.btn-group').find('.dropdown-toggle').html(
							selText + ' <span class="caret"></span>');
					$this.parents('.dropdown-menu').find('li').removeClass(
							'active');
					$this.parent().addClass('active');
				});

		/*
		 * TODO: add a way to add more todo's to list
		 */

		// initialize sortable
		$("#sortable1, #sortable2").sortable({
			handle : '.handle',
			connectWith : ".todo",
			update : countTasks
		}).disableSelection();

		// check and uncheck
		$('.todo .checkbox > input[type="checkbox"]').click(
				function() {
					var $this = $(this).parent().parent().parent();

					if ($(this).prop('checked')) {
						$this.addClass("complete");

						// remove this if you want to undo a check list once checked
						//$(this).attr("disabled", true);
						$(this).parent().hide();

						// once clicked - add class, copy to memory then remove and add to sortable3
						$this.slideUp(500, function() {
							$this.clone().prependTo("#sortable3").effect(
									"highlight", {}, 800);
							$this.remove();
							countTasks();
						});
					} else {
						// insert undo code here...
					}

				})
		// count tasks
		function countTasks() {

			$('.todo-group-title').each(
					function() {
						var $this = $(this);
						$this.find(".num-of-tasks").text(
								$this.next().find("li").size());
					});

		}

		/*
		 * FULL CALENDAR JS
		 */

		// Load Calendar dependency then setup calendar
		loadScript("assets/js//jquery.fullcalendar.min.js", setupCalendar);

		function setupCalendar() {

			if ($("#calendar").length) {
				var date = new Date();
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();

				var calendar = $('#calendar')
						.fullCalendar(
								{

									editable : true,
									draggable : true,
									selectable : false,
									selectHelper : true,
									unselectAuto : false,
									disableResizing : false,

									header : {
										left : 'title', //,today
										center : 'prev, next, today',
										right : 'month, agendaWeek, agenDay' //month, agendaDay,
									},

									select : function(start, end, allDay) {
										var title = prompt('Event Title:');
										if (title) {
											calendar.fullCalendar(
													'renderEvent', {
														title : title,
														start : start,
														end : end,
														allDay : allDay
													}, true // make the event "stick"
											);
										}
										calendar.fullCalendar('unselect');
									},

									events : [
											{
												title : 'All Day Event',
												start : new Date(y, m, 1),
												description : 'long description',
												className : [ "event",
														"bg-color-greenLight" ],
												icon : 'fa-check'
											},
											{
												title : 'Long Event',
												start : new Date(y, m, d - 5),
												end : new Date(y, m, d - 2),
												className : [ "event",
														"bg-color-red" ],
												icon : 'fa-lock'
											},
											{
												id : 999,
												title : 'Repeating Event',
												start : new Date(y, m, d - 3,
														16, 0),
												allDay : false,
												className : [ "event",
														"bg-color-blue" ],
												icon : 'fa-clock-o'
											},
											{
												id : 999,
												title : 'Repeating Event',
												start : new Date(y, m, d + 4,
														16, 0),
												allDay : false,
												className : [ "event",
														"bg-color-blue" ],
												icon : 'fa-clock-o'
											},
											{
												title : 'Meeting',
												start : new Date(y, m, d, 10,
														30),
												allDay : false,
												className : [ "event",
														"bg-color-darken" ]
											},
											{
												title : 'Lunch',
												start : new Date(y, m, d, 12, 0),
												end : new Date(y, m, d, 14, 0),
												allDay : false,
												className : [ "event",
														"bg-color-darken" ]
											},
											{
												title : 'Birthday Party',
												start : new Date(y, m, d + 1,
														19, 0),
												end : new Date(y, m, d + 1, 22,
														30),
												allDay : false,
												className : [ "event",
														"bg-color-darken" ]
											},
											{
												title : 'Smartadmin Open Day',
												start : new Date(y, m, 28),
												end : new Date(y, m, 29),
												className : [ "event",
														"bg-color-darken" ]
											} ],

									eventRender : function(event, element, icon) {
										if (!event.description == "") {
											element
													.find('.fc-event-title')
													.append(
															"<br/><span class='ultra-light'>"
																	+ event.description
																	+ "</span>");
										}
										if (!event.icon == "") {
											element
													.find('.fc-event-title')
													.append(
															"<i class='air air-top-right fa " + event.icon +
		                        " '></i>");
										}
									}
								});

			}
			;

			/* hide default buttons */
			$('.fc-header-right, .fc-header-center').hide();

		}

		// calendar prev
		$('#calendar-buttons #btn-prev').click(function() {
			$('.fc-button-prev').click();
			return false;
		});

		// calendar next
		$('#calendar-buttons #btn-next').click(function() {
			$('.fc-button-next').click();
			return false;
		});

		// calendar today
		$('#calendar-buttons #btn-today').click(function() {
			$('.fc-button-today').click();
			return false;
		});

		// calendar month
		$('#mt').click(function() {
			$('#calendar').fullCalendar('changeView', 'month');
		});

		// calendar agenda week
		$('#ag').click(function() {
			$('#calendar').fullCalendar('changeView', 'agendaWeek');
		});

		// calendar agenda day
		$('#td').click(function() {
			$('#calendar').fullCalendar('changeView', 'agendaDay');
		});

	}
	// end pagefunction

	// run pagefunction on load
	pagefunction();

	/* function changeSelectedProjectSpan(value) {
		if (value == 1) {
			$("#selectedProjectSpan").html("MPR");
		} else if (value == 2) {
			$("#selectedProjectSpan").html("FTS");
		} else if (value == 3) {
			$("#selectedProjectSpan").html("PD");
		}
	} */
	function changeLangFlag(value) {
		if (value == 1) {
			$("#countrySpan").html("US");
			$("#anchorTagForFlag").removeClass("flag flag-es");
			$("#anchorTagForFlag").addClass("flag flag-us");
		} else if (value == 2) {
			$("#countrySpan").html("ES");
			$("#anchorTagForFlag").removeClass("flag flag-us");
			$("#anchorTagForFlag").addClass("flag flag-es");
		}
	}

	function gritterForSucessMsgs(content) {
		$.smallBox({
			title : "<b>Ecosystem: Success !!</b>",
			content : "<i class='fa fa-clock-o'></i> <i>" + content + "</i>",
			color : "#659265",
			timeout : 4000,
			iconSmall : "fa fa-check fa-2x fadeInRight animated",
		});
	}
	function gritterForErrorMsgs(content) {
		$.smallBox({
			title : "<b>Ecosystem: Failure !!</b>",
			content : "<i class='fa fa-clock-o'></i> <i>" + content + "</i>",
			color : "#C46A69",
			iconSmall : "fa fa-times fa-2x fadeInRight animated",
			timeout : 6000
		});
	}
	function gritterForInfoMsgs(msg) {
		$.smallBox({
			title : "<b>Ecosystem: Info !!</b>",
			content : msg,
			color : "#5384AF",
			icon : "fa fa-bell bounce animated custSize",
			timeout : 5000,
			iconSmall : "fa fa-times fadeInRight animated custPosit"
		});
	}
</script>
<script type="text/javascript" src="assets/js/summernote.min.js"></script>

<script>
	$(document).ready(
			function() {
				$('.emailBody').summernote(
						{
							height : 170,
							toolbar : [
									[ 'style', [ 'style' ] ],
									[
											'style',
											[ 'bold', 'italic', 'underline',
													'clear' ] ],
									[ 'para', [ 'ul', 'ol' ] ],
									[ 'fontsize', [ 'fontsize' ] ],
									[ 'color', [ 'color' ] ],
									[ 'height', [ 'height' ] ],
									[ 'insert', [ 'picture', 'link' ] ],
									[ 'table', [ 'table' ] ], ]
						});
			});
</script>
<!-- dependencies for ladda buttons starts-->
<link rel="stylesheet" href="assets/css/ladda-themeless.min.css">
<script src="assets/js/spin.min.js"></script>
<script src="assets/js/ladda.min.js"></script>
<!-- dependencies for ladda buttons ends-->
<!-- dependencies for moment -->
<script type="text/javascript" src="assets/js/moment.min.js"></script>
<!-- dependencies for moment end  -->
<!-- dependencies for d3-->
<script type="text/javascript" src="assets/js/d3.min.js"></script>
<script type="text/javascript" src="assets/js/home.js"></script>
<script type="text/javascript" src="assets/js/datatable/dataTables.editable.js"></script>
<script type="text/javascript" src="assets/js/datatable/jquery.jeditable.js"></script>
<!-- this is used for pageAuthorization -->
<script>
	$(document).ready(function() {
		pageAuthorization();
		logsCount();
	});
	
	/* adding by hari later to be changed */
	function pageAuthorization() {
		$.ajax({
			url : "getUserDetailsForHome",
			type : "POST",
			success : function(result) {
				result = JSON.parse(result);
				console.log(result);
				console.log(result.istempuser);
				var firstName = "";
				if (result.istempuser == true) {
					firstName = result.userDetails;
					customizedLayoutForHome();
					if (result.userDetails.userName != undefined
							&& result.userDetails.userName != null) {
						//	firstName = result.userDetails.userName;
						firstName = result.userDetails;
						//customizedLayout();

					}
					document.getElementById("nameId").innerHTML = firstName;
					//console.log(result.userDetails.userName);
				} else {
					firstName = result.userDetails;
					NormalLayoutForHome();
					if (result.userDetails.firstName != undefined
							&& result.userDetails.firstName != null) {
					//	firstName = result.userDetails.firstName;
						firstName = result.userDetails;
						
						
					}
					document.getElementById("nameId").innerHTML = firstName;
					getApplicationPlan();
				}
			},
			error : function(result) {
				console.log(result.message);
				console.log("error");
				//$("#").hide();
				//$("#").hide();
				//$("#").hide();
				//$("#").hide();
			}
		});
	}
	function getApplicationPlan() {
		console.log("getApplicationPlan");
		$
				.ajax({
					url : "getApplicationNames",
					type : "POST",
					async : false,
					success : function(result) {
						var activePlans = JSON.parse(result);
						console.log(activePlans);
						if (activePlans.ajaxResult == undefined) {

							for (var pc = 0; pc < activePlans.plans.length; pc++) {
								$('#projectsPlan')
										.prepend(
												'<li onclick="changeSelectedProjectSpan(this.value);" value="'
														+ pc
														+ '">'
														+ '<a target="_blank" href="'
														+ activePlans.plans[pc].applicationPlanDirectory.applicationDirectory.applicationLink
														+ 'login?userName='
														+ activePlans.plans[pc].userDetail.emailId
														+ '&&password='
														+ activePlans.plans[pc].userDetail.password
														+ '">'
														+ activePlans.plans[pc].applicationPlanDirectory.applicationDirectory.applicationName
														+ '</a>' + '</li>');
							}
						}

					},
					error : function(result) {
						console.log(result.message);
						console.log("error");
					}
				});
	}
</script>
<script>
	function logoutToStaticHome() {
		$
				.SmartMessageBox(
						{
							title : "<i class='fa fa-sign-out txt-color-orangeDark'></i> Logout <span class='txt-color-orangeDark' id='nameId'><strong >"
									+ "</strong></span>",
							content : "You can improve your security further after logging out by closing this opened browser ",
							buttons : '[No][Yes]'
						}, function(ButtonPressed) {
							if (ButtonPressed === "Yes") {
								location.href = "userLogout";
							}
						});
	}
</script>

</html>
