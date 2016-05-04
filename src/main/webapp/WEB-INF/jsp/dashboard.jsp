<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>
.axis path, .axis line {
	fill: none;
	stroke: #000;
	shape-rendering: crispEdges;
}

path.color0 {
	fill: #1072b8;
}

path.color1 {
	fill: #eee;
}

.bar {
	fill: steelblue;
}

.bar:hover {
	fill: orangered;
}

.x.axis path {
	display: none;
}

.d3-tip {
	line-height: 1;
	font-weight: bold;
	padding: 12px;
	background: rgba(0, 0, 0, 0.8);
	color: #fff;
	border-radius: 2px;
}

/* Creates a small triangle extender for the tooltip */
.d3-tip:after {
	box-sizing: border-box;
	display: inline;
	font-size: 10px;
	width: 100%;
	line-height: 1;
	color: rgba(0, 0, 0, 0.8);
	content: "\25BC";
	position: absolute;
	text-align: center;
}

/* Style northward tooltips differently */
.d3-tip.n:after {
	margin: -1px 0 0 0;
	top: 100%;
	left: 0;
}
/* Style for background for d3 charts */
.panel-default {
	border-color: #e9e9e9;
}

.panel {
	margin-bottom: 20px;
	background-color: white;
	border: 1px solid transparent;
	border-radius: 2px;
	-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
}

.panel .panel-heading {
	text-transform: uppercase;
}

.panel-default>.panel-heading {
	color: #767676;
	background-color: #f6f6f6;
	border-color: #e9e9e9;
}

.panel-heading {
	padding: 15px 15px;
	border-bottom: 1px solid transparent;
	border-top-right-radius: 1px;
	border-top-left-radius: 1px;
}

.text-center {
	text-align: center;
}

.tooltip {
	position: absolute;
	display: none;
	width: auto;
	height: auto;
	background: none repeat scroll 0 0 white;
	border: 0 none;
	border-radius: 8px 8px 8px 8px;
	box-shadow: -3px 3px 15px #888888;
	color: black;
	font: 12px sans-serif;
	padding: 5px;
	text-align: center;
}

.appBtnstyle {
	background: rgba(214, 221, 231, 1) !important;
	color: #305D97 !important;
	/* height: 34px !important; */
	min-width: 65px !important;
	padding: 4px !important;
}
</style>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa-fw fa fa-home txt-color-blue"></i> <spring:message code="Dashboard.PageHead.Heading"/> <span>&gt;
				<spring:message code="Dashboard.PageHead.SubHeading"/></span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<h1 id="appsUlId" class="">
         
		</h1>
	</div>
</div>

<div class="col-sm-12" id="noRolesAssigned" style="display:none">
<div class="row">
	<div class="well"
		style="text-align: center; padding-top: 5px; background-color: #ACCACA; border: 1px solid black">
		<h1 class="semi-bold"><spring:message code="Dashboard.Message.ForNoMappedJobs"/></h1>
	</div>
</div>
</div>

<!-- widget grid -->

<section id="widget-grid-supervisor" class="" style="display:none">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					&nbsp; 
						<h2 style="color: #E4E4E4;">
							
						</h2>
					<span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->
					<div class="widget-body no-padding">
					<div class="alert alert-block alert-info">
						<h4><b style="font-size: 14px;"><spring:message code="Dashboard.Message.ForSupervisor"/></b></h4>
					</div>
					
						<div id="supervisorData"
							class="dataTables_wrapper form-inline no-footer">
					</div>
				</div>
			</div>
			</div>
		</article>
	</div>
</section>


<section id="widget-grid-executive" class="" style="display:none">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					&nbsp; 
						<h2 style="color: #E4E4E4;">
							
						</h2>
					<span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->
					
					<div class="widget-body no-padding">
					<div class="alert alert-block alert-info">
						<h4><b style="font-size: 14px;"><spring:message code="Dashboard.Message.ForExecutive"/></b></h4>
					</div>
						<div id="executiveData"
							class="dataTables_wrapper form-inline no-footer">
					</div>
				</div>
			</div>
			</div>
		</article>
	</div>
</section>


<!-- widget grid -->
<section id="widget-grid-manager" class="" style="display:none">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					&nbsp; 
						<h2 style="color: #E4E4E4;">
							
						</h2>
					<span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->
					
					<div class="widget-body no-padding">
					<div class="alert alert-block alert-info">
						<h4><b style="font-size: 14px;"><spring:message code="Dashboard.Message.ForManager"/></b></h4>
					</div>
						<div id="managerData"
							class="dataTables_wrapper form-inline no-footer">
					</div>
				</div>
			</div>
			</div>
		</article>
	</div>
</section>


<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="modalForPswdVerification" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"><span class="badge pull-right inbox-badge customColor">×</span></button>
					<h4 class="modal-title" id="modalTitleIdForProjTypes"><i class="fa fa-file fa-fw txt-color-blue" ></i><spring:message code="ProjectTypes.Form.AddNewProject"/></h4>
				</div>
				<div class="modal-body no-padding">

					<form id="passwordVerificationForm" method="POST" class="smart-form">

						<fieldset>
							<section class="11">
										<label class="input">
										<input type="password" name="password" id ="password"  placeholder="Enter password">
										</label>
										<input type="hidden" name="selectedCompanyId" id ="selectedCompanyId">
							
									
							</section>
						</fieldset>

						<footer>
						<button type="button" class="btn btn-default" data-dismiss="modal" onclick="resetProjectTypeForm()"><spring:message code="Button.Cancel"/></button>
						<button type="button" class="btn btn-primary" id="save" onClick="switchCompany()"><i class="fa fa-floppy-o"></i> &nbsp;<spring:message code="Button.Save"/></button>
							
						</footer>

					</form>
				</div>


			</div>
		</div>
	</div>
	<!-- END MODAL -->

</div>

<div style="display:none" id="otherCompaniesInfo">
	<div role="content">	
					<!-- widget content -->
					<div class="widget-body no-padding">

						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="otherCompaniesInfoTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th  class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 20px;" class="center">
											#</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="OtherCompanyInfo.Datatable.TableHead.Column1"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Comp Address: activate to sort column ascending"
											style="width: 200px;"> <spring:message code="OtherCompanyInfo.Datatable.TableHead.Column2"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Comp Email: activate to sort column ascending"
											style="width: 404px;"> <spring:message code="OtherCompanyInfo.Datatable.TableHead.Column3"/></th>
										
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Comp Email: activate to sort column ascending"
											style="width: 404px;"> <spring:message code="OtherCompanyInfo.Datatable.TableHead.Column4"/></th>
										<th  class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" : activate to sort column ascending"
											style="width: 30px;"></th>	
									</tr>
								</thead>
								
								<!-- <tbody>
								<tr>
								<td class="center">1</td>
								<td class="center">MAT</td>
								<td class="center">Activity1</td>
								<td class="center">activity description1</td>
								</tr>
								<tr>
								<td class="center">2</td>
								<td class="center">SUB</td>
								<td class="center">Activity2</td>
								<td class="center">activity description1</td>
								</tr>

								</tbody> -->
							</table>

						</div>

					</div>
					<!-- end widget content -->

				</div>
</div>
<!-- widget grid -->
<section id="widget-grid-foreman" class="" style="display:none">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					&nbsp; 
						<h2 style="color: #E4E4E4;">
							
						</h2>
					<span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->
					
					<div class="widget-body no-padding">
					<div class="alert alert-block alert-info">
						<h4><b style="font-size: 14px;"><spring:message code="Dashboard.Message.ForForeman"/></b></h4>
					</div>
						<div id="foremanData"
							class="dataTables_wrapper form-inline no-footer">
					</div>
				</div>
			</div>
			</div>
		</article>
	</div>
</section>


<!-- widget grid -->
<section id="widget-grid-purchasingAgent" class="" style="display:none">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					&nbsp; 
						<h2 style="color: #E4E4E4;">
							
						</h2>
					<span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->
					
					<div class="widget-body no-padding">
					<div class="alert alert-block alert-info">
						<h4><b style="font-size: 14px;"><spring:message code="Dashboard.Message.ForPurchasingAgent"/></b></h4>
					</div>
						<div id="purchasingAgentData"
							class="dataTables_wrapper form-inline no-footer">
					</div>
				</div>
			</div>
			</div>
		</article>
	</div>
</section>


<!-- widget grid -->
<section id="widget-grid-admin" class="" style="display:none">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					&nbsp; 
						<h2 style="color: #E4E4E4;">
							
						</h2>
					<span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->
					
					<div class="widget-body no-padding">
					<div class="alert alert-block alert-info">
						<h4><b style="font-size: 14px;"><spring:message code="Dashboard.Message.ForAdmin"/></b></h4>
					</div>
						<div id="adminData"
							class="dataTables_wrapper form-inline no-footer">
					</div>
				</div>
			</div>
			</div>
		</article>
	</div>
</section>

<!-- widget grid -->

<!-- start alert box and its script -->
<!-- <div id="logout" class="btn-header transparent pull-right">
				<span> <a href="login" title="Sign Out" data-action="alertBox"
					data-logout-msg="">
					<i class="fa fa-bell"></i>
					</a>
				</span>
			</div> -->

<script>
	 /* 	$.root_ = $("body");
var   initApp = function(a) {
        return a.SmartActions = function() {
            var a = {
                alertBox: function(a) {
                    function b() {
                        window.location = a.attr("href")
                    }
                    $.SmartMessageBox({
                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Alert Box ?",
                        content: a.data("logout-msg") || "This is default message ,you can write custom message too",
                        buttons: "[No][Yes]"
                    }, function(a) {
                        "Yes" == a && ($.root_.addClass("animated fadeOutUp"), setTimeout(b, 1e3))
                    })
                }
            };
            $.root_.on("click", '[data-action="alertBox"]', function(b) {
                var c = $(this);
                a.alertBox(c), b.preventDefault(), c = null
            })
        },  a
    }({});
	
 jQuery(document).ready(function() {
        initApp.SmartActions(); 
    });  */
        </script>
<!-- end alert box and its script -->


<!-- starts html and script for glitter -->
<!-- <button type="button" id="successGlitter" class="btn btn-success">
	Success Glitter</button>
<button type="button" id="infoGlitter" class="btn btn-info">
	Info Glitter</button>
<button type="button" id="dangerGlitter" class="btn btn-danger">
	Danger Glitter</button>		
 -->
<script type="text/javascript">
		$('#successGlitter').click(function() {
			$.smallBox({
				title : "<b>Success box</b>",
				content : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad min",
				color : "#739E73",
				timeout: 5000,
				icon : "fa fa-check animated custSize bounce",
			    iconSmall : "fa fa-times fadeInRight animated custPosit"
			});
		}),
		$('#infoGlitter').click(function() {
			$.smallBox({
				title : "<b>Info box</b>",
				content : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad min",
				color : "#5384AF",
				icon : "fa fa-bell bounce animated custSize",
				timeout: 5000,
			    iconSmall : "fa fa-times fadeInRight animated custPosit"
			});
		}),$('#dangerGlitter').click(function() {
			$.smallBox({
				title : "<b>Danger box</b>",
				content : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad min",
				color : "#C46A69",
				timeout: 5000,
				icon : "fa fa-warning shake animated custSize",
				 iconSmall : "fa fa-times fadeInRight animated custPosit"
			});
		})

</script>

<!-- ends html and script for glitter -->
<script type="text/javascript" src="assets/js/d3.min.js"></script>
<script type="text/javascript" src="assets/js/d3-tip.js"></script>
<script type="text/javascript" src="assets/js/dashboard.js"></script>
<script type="text/javascript" src="assets/js/dashboard_reportNtrack.js"></script>

<!-- <script src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"></script> -->