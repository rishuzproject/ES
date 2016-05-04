<!DOCTYPE html>
<!-- saved from url=(0087)http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/datatables.html -->
<html lang="en-us">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Data Tables</title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- #CSS Links -->
<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="./Data Tables_files/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="./Data Tables_files/font-awesome.min.css">

<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
<link rel="stylesheet" type="text/css" media="screen"
	href="./Data Tables_files/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="./Data Tables_files/smartadmin-skins.min.css">

<!-- SmartAdmin RTL Support is under construction
			 This RTL CSS will be released in version 1.5
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> -->

<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen"
	href="./Data Tables_files/demo.min.css">

<!-- #FAVICONS -->
<link rel="shortcut icon"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/favicon/favicon.ico"
	type="image/x-icon">

<link rel="icon"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/favicon/favicon.ico"
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
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/touch-icon-ipad-retina.png">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- Startup image for web apps -->
<link rel="apple-touch-startup-image"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/ipad-landscape.png"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/ipad-portrait.png"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image"
	href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/img/splash/iphone.png"
	media="screen and (max-device-width: 320px)">

<style type="text/css">
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
<body class="desktop-detected" style="">

	<!-- #HEADER -->
	<header id="header">
		<div id="logo-group">

			<!-- PLACE YOUR LOGO HERE -->
			<span id="logo"> <img src="./Data Tables_files/logo.png"
				alt="SmartAdmin">
			</span>
			<!-- END LOGO PLACEHOLDER -->

			<!-- Note: The activity badge color changes when clicked and resets the number to 0
					 Suggestion: You may want to set a flag when this happens to tick off all checked messages / notifications -->
			<span id="activity" class="activity-dropdown"> <i
				class="fa fa-user"></i> <b
				class="badge bg-color-red bounceIn animated"> 21 </b>
			</span>

			<!-- AJAX-DROPDOWN : control this dropdown height, look and feel from the LESS variable file -->
			<div class="ajax-dropdown">

				<!-- the ID links are fetched via AJAX to the ajax container "ajax-notifications" -->
				<div class="btn-group btn-group-justified" data-toggle="buttons">
					<label class="btn btn-default"> <input type="radio"
						name="activity" id="ajax/notify/mail.html"> Msgs (14)
					</label> <label class="btn btn-default"> <input type="radio"
						name="activity" id="ajax/notify/notifications.html">
						notify (3)
					</label> <label class="btn btn-default"> <input type="radio"
						name="activity" id="ajax/notify/tasks.html"> Tasks (4)
					</label>
				</div>

				<!-- notification content -->
				<div class="ajax-notifications custom-scroll">

					<div class="alert alert-transparent">
						<h4>Click a button to show messages here</h4>
						This blank page message helps protect your privacy, or you can
						show the first message here automatically.
					</div>

					<i class="fa fa-lock fa-4x fa-border"></i>

				</div>
				<!-- end notification content -->

				<!-- footer: refresh area -->
				<span> Last updated on: 12/12/2013 9:43AM
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
				class="project-selector dropdown-toggle" data-toggle="dropdown">Recent
				projects <i class="fa fa-angle-down"></i>
			</span>

			<!-- Suggestion: populate this list with fetch and push technique -->
			<ul class="dropdown-menu">
				<li><a href="javascript:void(0);">Online e-merchant
						management system - attaching integration with the iOS</a></li>
				<li><a href="javascript:void(0);">Notes on pipeline
						upgradee</a></li>
				<li><a href="javascript:void(0);">Assesment Report for
						merchant account</a></li>
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

			<!-- #MOBILE -->
			<!-- Top menu profile link : this shows only when top menu is active -->
			<ul id="mobile-profile-img"
				class="header-dropdown-list hidden-xs padding-5">
				<li class=""><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"
					class="dropdown-toggle no-margin userdropdown"
					data-toggle="dropdown"> <img
						src="./Data Tables_files/sunny.png" alt="John Doe" class="online">
				</a>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"><i
								class="fa fa-cog"></i> Setting</a></li>
						<li class="divider"></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/profile.html"
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
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/login.html"
							class="padding-10 padding-top-5 padding-bottom-5"
							data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i>
								<strong><u>L</u>ogout</strong></a></li>
					</ul></li>
			</ul>

			<!-- logout button -->
			<div id="logout" class="btn-header transparent pull-right">
				<span> <a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/login.html"
					title="Sign Out" data-action="userLogout"
					data-logout-msg="You can improve your security further after logging out by closing this opened browser"><i
						class="fa fa-sign-out"></i></a>
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
			<form
				action="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/search.html"
				class="header-search pull-right">
				<input id="search-fld" type="text" name="param"
					placeholder="Find reports and more">
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
			<div id="speech-btn"
				class="btn-header transparent pull-right hidden-sm hidden-xs">
				<div>
					<a href="javascript:void(0)" title="Voice Command"
						data-action="voiceCommand"><i class="fa fa-microphone"></i></a>
					<div class="popover bottom">
						<div class="arrow"></div>
						<div class="popover-content">
							<h4 class="vc-title">
								Voice command activated <br>
								<small>Please speak clearly into the mic</small>
							</h4>
							<h4 class="vc-title-error text-center">
								<i class="fa fa-microphone-slash"></i> Voice command failed <br>
								<small class="txt-color-red">Must <strong>"Allow"</strong>
									Microphone
								</small> <br>
								<small class="txt-color-red">Must have <strong>Internet
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
			</div>
			<!-- end voice command -->

			<!-- multiple lang dropdown : find all flags in the flags page -->
			<ul class="header-dropdown-list hidden-xs">
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="./Data Tables_files/blank.gif" class="flag flag-us"
						alt="United States"> <span> US</span> <i
						class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu pull-right">
						<li class="active"><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-us"
								alt="United States"> English (US)</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-fr"
								alt="France"> Français</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-es"
								alt="Spanish"> Español</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-de"
								alt="German"> Deutsch</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-jp"
								alt="Japan"> 日本語</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-cn"
								alt="China"> 中文</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-it"
								alt="Italy"> Italiano</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-pt"
								alt="Portugal"> Portugal</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-ru"
								alt="Russia"> Русский язык</a></li>
						<li><a href="javascript:void(0);"><img
								src="./Data Tables_files/blank.gif" class="flag flag-kp"
								alt="Korea"> 한국어</a></li>

					</ul></li>
			</ul>
			<!-- end multiple lang -->

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
				data-action="toggleShortcut"> <img
					src="./Data Tables_files/sunny.png" alt="me" class="online">
					<span> john.doe </span> <i class="fa fa-angle-down"></i>
			</a>

			</span>
		</div>
		<!-- end user info -->

		<!-- NAVIGATION : This navigation is also responsive

			To make this navigation dynamic please make sure to link the node
			(the reference to the nav > ul) after page load. Or the navigation
			will not initialize.
			-->
		<nav>
			<!-- 
				NOTE: Notice the gaps after each icon usage <i></i>..
				Please note that these links work a bit different than
				traditional href="" links. See documentation for details.
				-->

			<ul style="">
				<li class=""><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/dashboard.html"
					title="Dashboard"><i class="fa fa-lg fa-fw fa-home"></i> <span
						class="menu-item-parent">Dashboard</span></a></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/inbox.html"><i
						class="fa fa-lg fa-fw fa-inbox"></i> <span
						class="menu-item-parent">Inbox</span><span
						class="badge pull-right inbox-badge">14</span></a></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
						class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span
						class="menu-item-parent">Graphs</span><b class="collapse-sign"><em
							class="fa fa-plus-square-o"></em></b></a>
					<ul>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/flot.html">Flot
								Chart</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/morris.html">Morris
								Charts</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/inline-charts.html">Inline
								Charts</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/dygraphs.html">Dygraphs
								<span class="badge pull-right inbox-badge bg-color-yellow">new</span>
						</a></li>
					</ul></li>
				<li class="active open"><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
						class="fa fa-lg fa-fw fa-table"></i> <span
						class="menu-item-parent">Tables</span><b class="collapse-sign"><em
							class="fa fa-minus-square-o"></em></b></a>
					<ul style="display: block;">
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/table.html">Normal
								Tables</a></li>
						<li class="active"><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/datatables.html">Data
								Tables <span class="badge inbox-badge bg-color-greenLight">v1.10</span>
						</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/jqgrid.html">Jquery
								Grid</a></li>
					</ul></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
						class="fa fa-lg fa-fw fa-pencil-square-o"></i> <span
						class="menu-item-parent">Forms</span><b class="collapse-sign"><em
							class="fa fa-plus-square-o"></em></b></a>
					<ul>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/form-elements.html">Smart
								Form Elements</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/form-templates.html">Smart
								Form Layouts</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/validation.html">Smart
								Form Validation</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/bootstrap-forms.html">Bootstrap
								Form Elements</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/plugins.html">Form
								Plugins</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/wizard.html">Wizards</a>
						</li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/other-editors.html">Bootstrap
								Editors</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/dropzone.html">Dropzone</a>
						</li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/image-editor.html">Image
								Cropping <span
								class="badge pull-right inbox-badge bg-color-yellow">new</span>
						</a></li>
					</ul></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
						class="fa fa-lg fa-fw fa-desktop"></i> <span
						class="menu-item-parent">UI Elements</span><b
						class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
					<ul>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/general-elements.html">General
								Elements</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/buttons.html">Buttons</a>
						</li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">Icons<b
								class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
							<ul>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/fa.html"><i
										class="fa fa-plane"></i> Font Awesome</a></li>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/glyph.html"><i
										class="glyphicon glyphicon-plane"></i> Glyph Icons</a></li>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/flags.html"><i
										class="fa fa-flag"></i> Flags</a></li>
							</ul></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/grid.html">Grid</a>
						</li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/treeview.html">Tree
								View</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/nestable-list.html">Nestable
								Lists</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/jqui.html">JQuery
								UI</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/typography.html">Typography</a>
						</li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">Six
								Level Menu<b class="collapse-sign"><em
									class="fa fa-plus-square-o"></em></b>
						</a>
							<ul>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
										class="fa fa-fw fa-folder-open"></i> Item #2<b
										class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
									<ul>
										<li><a
											href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
												class="fa fa-fw fa-folder-open"></i> Sub #2.1 <b
												class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
											<ul>
												<li><a
													href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
														class="fa fa-fw fa-file-text"></i> Item #2.1.1</a></li>
												<li><a
													href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
														class="fa fa-fw fa-plus"></i> Expand<b
														class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
													<ul>
														<li><a
															href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
																class="fa fa-fw fa-file-text"></i> File</a></li>
														<li><a
															href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
																class="fa fa-fw fa-trash-o"></i> Delete</a></li>
													</ul></li>
											</ul></li>
									</ul></li>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
										class="fa fa-fw fa-folder-open"></i> Item #3<b
										class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>

									<ul>
										<li><a
											href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
												class="fa fa-fw fa-folder-open"></i> 3ed Level <b
												class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
											<ul>
												<li><a
													href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
														class="fa fa-fw fa-file-text"></i> File</a></li>
												<li><a
													href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
														class="fa fa-fw fa-file-text"></i> File</a></li>
											</ul></li>
									</ul></li>
							</ul></li>
					</ul></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/calendar.html"><i
						class="fa fa-lg fa-fw fa-calendar"><em>3</em></i> <span
						class="menu-item-parent">Calendar</span></a></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/widgets.html"><i
						class="fa fa-lg fa-fw fa-list-alt"></i> <span
						class="menu-item-parent">Widgets</span></a></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/gallery.html"><i
						class="fa fa-lg fa-fw fa-picture-o"></i> <span
						class="menu-item-parent">Gallery</span></a></li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/gmap-xml.html"><i
						class="fa fa-lg fa-fw fa-map-marker"></i> <span
						class="menu-item-parent">GMap Skins</span><span
						class="badge bg-color-greenLight pull-right inbox-badge">9</span></a>
				</li>
				<li><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
						class="fa fa-lg fa-fw fa-windows"></i> <span
						class="menu-item-parent">Miscellaneous</span><b
						class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
					<ul>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
								class="fa fa-file"></i> Other Pages<b class="collapse-sign"><em
									class="fa fa-plus-square-o"></em></b></a>
							<ul>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/forum.html">Forum
										Layout</a></li>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/profile.html">Profile</a>
								</li>
								<li><a
									href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/timeline.html">Timeline</a>
								</li>
							</ul></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/pricing-table.html">Pricing
								Tables</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/invoice.html">Invoice</a>
						</li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/login.html"
							target="_top">Login</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/register.html"
							target="_top">Register</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/lock.html"
							target="_top">Locked Screen</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/error404.html">Error
								404</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/error500.html">Error
								500</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/blank_.html">Blank
								Page</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/email-template.html">Email
								Template</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/search.html">Search
								Page</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/ckeditor.html">CK
								Editor</a></li>
					</ul></li>
				<li class="top-menu-hidden"><a
					href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#"><i
						class="fa fa-lg fa-fw fa-cube txt-color-blue"></i> <span
						class="menu-item-parent">SmartAdmin Intel</span><b
						class="collapse-sign"><em class="fa fa-plus-square-o"></em></b></a>
					<ul>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/difver.html"><i
								class="fa fa-stack-overflow"></i> Different Versions</a></li>
						<li><a
							href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/ajax/applayout.html"><i
								class="fa fa-cube"></i> App Settings</a></li>
						<li><a
							href="http://bootstraphunter.com/smartadmin/BUGTRACK/track_/documentation/index.html"
							target="_blank"><i class="fa fa-book"></i> Documentation</a></li>
						<li><a
							href="http://bootstraphunter.com/smartadmin/BUGTRACK/track_/"
							target="_blank"><i class="fa fa-bug"></i> Bug Tracker</a></li>
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
				<li>Tables</li>
				<li>Data Tables</li>
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

			<div class="demo">
				<span id="demo-setting"><i
					class="fa fa-cog txt-color-blueDark"></i></span>
				<form>
					<legend class="no-padding margin-bottom-10">Layout Options</legend>
					<section>
						<label><input name="subscription" id="smart-fixed-header"
							type="checkbox" class="checkbox style-0"><span>Fixed
								Header</span></label><label><input type="checkbox" name="terms"
							id="smart-fixed-navigation" class="checkbox style-0"><span>Fixed
								Navigation</span></label><label><input type="checkbox" name="terms"
							id="smart-fixed-ribbon" class="checkbox style-0"><span>Fixed
								Ribbon</span></label><label><input type="checkbox" name="terms"
							id="smart-fixed-footer" class="checkbox style-0"><span>Fixed
								Footer</span></label><label><input type="checkbox" name="terms"
							id="smart-fixed-container" class="checkbox style-0"><span>Inside
								<b>.container</b>
								<div class="font-xs text-right">(non-responsive)</div>
						</span></label><label style="display: block;"><input type="checkbox"
							id="smart-topmenu" class="checkbox style-0"><span>Menu
								on <b>top</b>
						</span></label> <span id="smart-bgimages" style="display: none;"></span>
					</section>
					<section>
						<h6 class="margin-top-10 semi-bold margin-bottom-5">Clear
							Localstorage</h6>
						<a href="javascript:void(0);"
							class="btn btn-xs btn-block btn-primary" id="reset-smart-widget"><i
							class="fa fa-refresh"></i> Factory Reset</a>
					</section>
					<h6 class="margin-top-10 semi-bold margin-bottom-5">SmartAdmin
						Skins</h6>
					<section id="smart-styles">
						<a href="javascript:void(0);" id="smart-style-0"
							data-skinlogo="img/logo.png"
							class="btn btn-block btn-xs txt-color-white margin-right-5"
							style="background-color: #4E463F;"><i
							class="fa fa-check fa-fw" id="skin-checked"></i>Smart Default</a><a
							href="javascript:void(0);" id="smart-style-1"
							data-skinlogo="img/logo-white.png"
							class="btn btn-block btn-xs txt-color-white"
							style="background: #3A4558;">Dark Elegance</a><a
							href="javascript:void(0);" id="smart-style-2"
							data-skinlogo="img/logo-blue.png"
							class="btn btn-xs btn-block txt-color-darken margin-top-5"
							style="background: #fff;">Ultra Light</a><a
							href="javascript:void(0);" id="smart-style-3"
							data-skinlogo="img/logo-pale.png"
							class="btn btn-xs btn-block txt-color-white margin-top-5"
							style="background: #f78c40">Google Skin</a>
					</section>
				</form>
			</div>
		</div>
		<!-- END RIBBON -->



	</div>
	<!-- END #MAIN PANEL -->

	<!-- #PAGE FOOTER -->
	<div class="page-footer">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<span class="txt-color-white">SmartAdmin WebApp © 2013-2014</span>
			</div>

			<div class="col-xs-6 col-sm-6 text-right hidden-xs">
				<div class="txt-color-white inline-block">
					<i class="txt-color-blueLight hidden-mobile">Last account
						activity <i class="fa fa-clock-o"></i> <strong>52 mins
							ago &nbsp;</strong>
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
			<li><a
				href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/inbox.html"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa fa-envelope fa-4x"></i> <span>Mail
							<span class="label pull-right bg-color-darken">14</span>
					</span>
				</span>
			</a></li>
			<li><a
				href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/calendar.html"
				class="jarvismetro-tile big-cubes bg-color-orangeDark"> <span
					class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>Calendar</span>
				</span>
			</a></li>
			<li><a
				href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/gmap-xml.html"
				class="jarvismetro-tile big-cubes bg-color-purple"> <span
					class="iconbox"> <i class="fa fa-map-marker fa-4x"></i> <span>Maps</span>
				</span>
			</a></li>
			<li><a
				href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/invoice.html"
				class="jarvismetro-tile big-cubes bg-color-blueDark"> <span
					class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice
							<span class="label pull-right bg-color-darken">99</span>
					</span>
				</span>
			</a></li>
			<li><a
				href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/gallery.html"
				class="jarvismetro-tile big-cubes bg-color-greenLight"> <span
					class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery
					</span>
				</span>
			</a></li>
			<li><a
				href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#ajax/profile.html"
				class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span
					class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My
							Profile </span>
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
	<script type="text/javascript" async="" src="./Data Tables_files/ga.js"></script>
	<script src="./Data Tables_files/jquery.min.js"></script>
	<script>
			if (!window.jQuery) {
				document.write('<script src="js/libs/jquery-2.0.2.min.js"><\/script>');
			}
		</script>

	<script src="./Data Tables_files/jquery-ui.min.js"></script>
	<script>
			if (!window.jQuery.ui) {
				document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
			}
		</script>

	<!-- IMPORTANT: APP CONFIG -->
	<script src="./Data Tables_files/app.config.js"></script>

	<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
	<script src="./Data Tables_files/jquery.ui.touch-punch.min.js"></script>

	<!-- BOOTSTRAP JS -->
	<script src="./Data Tables_files/bootstrap.min.js"></script>

	<!-- CUSTOM NOTIFICATION -->
	<script src="./Data Tables_files/SmartNotification.min.js"></script>

	<!-- JARVIS WIDGETS -->
	<script src="./Data Tables_files/jarvis.widget.min.js"></script>

	<!-- EASY PIE CHARTS -->
	<script src="./Data Tables_files/jquery.easy-pie-chart.min.js"></script>

	<!-- SPARKLINES -->
	<script src="./Data Tables_files/jquery.sparkline.min.js"></script>

	<!-- JQUERY VALIDATE -->
	<script src="./Data Tables_files/jquery.validate.min.js"></script>

	<!-- JQUERY MASKED INPUT -->
	<script src="./Data Tables_files/jquery.maskedinput.min.js"></script>

	<!-- JQUERY SELECT2 INPUT -->
	<script src="./Data Tables_files/select2.min.js"></script>

	<!-- JQUERY UI + Bootstrap Slider -->
	<script src="./Data Tables_files/bootstrap-slider.min.js"></script>

	<!-- browser msie issue fix -->
	<script src="./Data Tables_files/jquery.mb.browser.min.js"></script>

	<!-- FastClick: For mobile devices: you can disable this in app.js -->
	<script src="./Data Tables_files/fastclick.min.js"></script>

	<!--[if IE 8]>
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
		<![endif]-->

	<!-- Demo purpose only -->
	<script src="./Data Tables_files/demo.min.js"></script>

	<!-- MAIN APP JS FILE -->
	<script src="./Data Tables_files/app.min.js"></script>

	<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
	<!-- Voice command : plugin -->
	<script src="./Data Tables_files/voicecommand.min.js"></script>
	<div class="modal fade" id="voiceModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<!-- Your GOOGLE ANALYTICS CODE Below -->
	<script type="text/javascript">
		
		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-43548732-3']);
		  _gaq.push(['_trackPageview']);
		
		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();
		
		</script>



	<div id="divSmallBoxes"></div>
	<div id="divMiniIcons"></div>
	<div id="divbigBoxes"></div>
	<div id="ui-datepicker-div"
		class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"
		style="position: absolute; top: 821px; left: 1145px; z-index: 1; display: none;">
		<div
			class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all">
			<a class="ui-datepicker-prev ui-corner-all" data-handler="prev"
				data-event="click"
				title="&lt;i class=&quot;fa fa-chevron-left&quot;&gt;&lt;/i&gt;"><span
				class="ui-icon ui-icon-circle-triangle-w"><i
					class="fa fa-chevron-left"></i></span></a><a
				class="ui-datepicker-next ui-corner-all" data-handler="next"
				data-event="click"
				title="&lt;i class=&quot;fa fa-chevron-right&quot;&gt;&lt;/i&gt;"><span
				class="ui-icon ui-icon-circle-triangle-e"><i
					class="fa fa-chevron-right"></i></span></a>
			<div class="ui-datepicker-title">
				<span class="ui-datepicker-month">August</span>&nbsp;<span
					class="ui-datepicker-year">2014</span>
			</div>
		</div>
		<table class="ui-datepicker-calendar">
			<thead>
				<tr>
					<th class="ui-datepicker-week-end"><span title="Sunday">Su</span></th>
					<th><span title="Monday">Mo</span></th>
					<th><span title="Tuesday">Tu</span></th>
					<th><span title="Wednesday">We</span></th>
					<th><span title="Thursday">Th</span></th>
					<th><span title="Friday">Fr</span></th>
					<th class="ui-datepicker-week-end"><span title="Saturday">Sa</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td
						class=" ui-datepicker-week-end ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">1</a></td>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">2</a></td>
				</tr>
				<tr>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">3</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">4</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">5</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">6</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">7</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">8</a></td>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">9</a></td>
				</tr>
				<tr>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">10</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">11</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">12</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">13</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">14</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">15</a></td>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">16</a></td>
				</tr>
				<tr>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">17</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">18</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">19</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">20</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">21</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">22</a></td>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">23</a></td>
				</tr>
				<tr>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">24</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">25</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">26</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">27</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">28</a></td>
					<td class=" " data-handler="selectDay" data-event="click"
						data-month="7" data-year="2014"><a class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">29</a></td>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">30</a></td>
				</tr>
				<tr>
					<td class=" ui-datepicker-week-end " data-handler="selectDay"
						data-event="click" data-month="7" data-year="2014"><a
						class="ui-state-default"
						href="http://192.241.236.31/themes/preview/smartadmin/1.4.1/ajaxversion/#">31</a></td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
					<td
						class=" ui-datepicker-week-end ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript"
		src="./Data Tables_files/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="./Data Tables_files/dataTables.colVis.min.js"></script>
	<script type="text/javascript"
		src="./Data Tables_files/dataTables.tableTools.min.js"></script>
	<script type="text/javascript"
		src="./Data Tables_files/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript"
		src="./Data Tables_files/datatables.responsive.min.js"></script>
</body>
</html>