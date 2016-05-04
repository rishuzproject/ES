<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/grid.locale-en.js"></script>
<script type="text/javascript" src="assets/js/jquery.jqGrid.js"></script>
<script type="text/javascript" src="assets/js/d3.min.js"></script>
<script type="text/javascript" src="assets/js/d3-tip.js"></script>
<script type="text/javascript" src="assets/js/sovDirectory.js"></script>
<link href="assets/css/ui.jqgrid.css" rel="stylesheet">
<link href="assets/css/jquery-ui.theme.css" rel="stylesheet">
<style>
x axis {
	stroke-width: 2px;
}

y axis {
	stroke-width: 2px;
}

polyline {
	opacity: .3;
	stroke: black;
	stroke-width: 2px;
	fill: none;
}

.d3-tip {
	line-height: 1;
	font-weight: bold;
	padding: 12px;
	background: rgba(0, 0, 0, 0.8);
	color: #fff;
	border-radius: 2px;
}

/
Creates a small triangle extender for the tooltip /
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

/
Style northward tooltips differently /
.d3-tip.n:after {
	margin: -1px 0 0 0;
	top: 100%;
	left: 0;
}
</style>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-6">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-gear fa-fw txt-color-blue"></i> <spring:message code="SovDirectory.PageHead.Heading" /> <span>
				&gt; <spring:message code="SovDirectory.PageHead.SubHeading" /> </span>
		</h1>
	</div>

	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-6">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="SovDirectory.PageHead.TotalApprovedSOVInfo" /> <span class="txt-color-blue"
						id="totalApprovedSov"></span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="SovDirectory.PageHead.TotalSOVAwaitingApprovalInfo" /> <span class="txt-color-blue"
						id="totalSovAwaitingApproval"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>


<div class="row">
	<input type="hidden" id="projectId" name="projectId">
	<div class="col-md-12" style="padding-bottom: 10px;" id="selectDivId">
		<select class="form-control  show-tick" id="selectProject"
			style="height: 40px; cursor: pointer" name="selectProject">
			<!-- <option value="">--Select a Project---</option> -->
		</select>
	</div>
	<div class="col-md-1" id="backBtnId">
	<button id="reset" class="btn btn-primary"
	style="float: right; display: none;height: 39px;" onclick="resetSelectedProject();"><spring:message code="SovDirectory.SelectProject.Back" /></button>
   </div>
</div>

<div class="tab-content">
	<div class="tab-pane active" id="summaryProject">
		<div class="">
			<section class="panel panel-default">
				<div class="panel-heading text-center"
					style="font-size: initial; background: white;">
					<i class="fa fa-bar-chart-o"></i>&nbsp;&nbsp; <em><strong><spring:message code="SovDirectory.OverallChart.Heading" /></strong></em>
				</div>
				<div id="barChartForAllProjects" style="background-color: #ededed;"></div>
			</section>
		</div>

	</div>
</div>

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="sovRemoteModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetSOVDetailsForm()">
						<span class="badge pull-right inbox-badge customColor"><spring:message code="Sov.Button.CancelText" /></span>
					</button>
					<h4 class="modal-title" id="modalTitleId"><i class="fa fa-gear fa-fw txt-color-blue"></i> <spring:message code="SovDirectory.Form.AddNewSOV" /></h4>
				</div>
				<div class="modal-body no-padding">

					<form id="sovDetailsForm" method="POST" class="smart-form">
						<fieldset>
							<div class="row">
								<section>
									<input type="hidden" name="jobIdHidden" id="jobIdHidden">
									<input type="hidden" name="sovId" id="sovId">
									<div class="col col-lg-12">
										<label class="input"> <input type="text"
											class="form-control" name="sovName" id="sovName"
											placeholder="<spring:message code="SovDirectory.label.SOVName"/>"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="SovDirectory.Tooltip.SOVName" /></b>
										</label><br>
									</div>
								</section>
							</div>
							<section>
								
								<select class="form-control  show-tick" id="sovType"
			                      name="sovType">
			                 <option value=""><spring:message code="SovDirectory.Dropdown.Default" /></option> 
			                 <option value="Percentage"><spring:message code="SovDirectory.Dropdown.Percentage" /></option>
			                 <option value="Unit"><spring:message code="SovDirectory.Dropdown.Unit" /></option>
			                 <option value="Milestone"><spring:message code="SovDirectory.Dropdown.Milestone" /></option>
			                 <option value="Hybrid"><spring:message code="SovDirectory.Dropdown.Hybrid" /></option>
			                 
		                   </select>
								

							</section>


						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetSOVDetailsForm()">
								<spring:message code="Button.Cancel" />
							</button>
							<button type="button" class="btn btn-primary" id="resetFormModal"
								onclick="resetSOVDetailsForm()">
								<i class="fa fa-undo"></i> &nbsp;
								<spring:message code="Button.Reset" />
							</button>
							<button type="button" class="btn btn-primary ladda-button"
								data-style="expand-right" id="saveandcontinue"
								onclick="addUpdateSOV('saveandcontinue')">
								<i class="fa fa-forward"></i> &nbsp;
								<spring:message code="Button.SaveContinue" />
							</button>
							<button type="button" class="btn btn-primary ladda-button"
								data-style="expand-right" id="save"
								onclick="addUpdateSOV('save')">
								<i class="fa fa-floppy-o"></i> &nbsp;
								<spring:message code="Button.Save" />
							</button>


						</footer>

					</form>
				</div>


			</div>
		</div>
	</div>
	<!-- END MODAL -->

</div>

<div>
	<form id="sovIdForSession" class="smart-form" method="POST"
		style="opacity: 0">
			<input type="hidden" name="sessionSovId" id="sessionSovId">
	</form>
</div>

<!-- Datatable code starts -->

<!-- widget grid -->

<section id="widget-grid" class="">
	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget">
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
					
					&nbsp; <a href="" data-toggle="modal" data-target="#sovRemoteModal"
						onclick=" resetSOVDetailsForm();"> <span class="widget-icon"
						style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;"> <spring:message code="SovDirectory.Action.CreateSOV" /> </h2>
					</a> <span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="modal-body no-padding">
					<div class="content">
						<ul id="myTab1" class="nav nav-tabs">
							<li class="active" id="plans">
							<a href="#s1" data-toggle="tab" id="sovTab1"> <spring:message code="SovDirectory.Tab1" /> </a>
							</li>
							<li class="" id="cart">
							<a href="#s2" data-toggle="tab" id="sovTab2"> <spring:message code="SovDirectory.Tab2" /> </a>
							</li>
						</ul>
						<div class="tab-content ">
							<div class="tab-pane active" id="s1" style="padding-top: 10px;">
								<fieldset id="">
									<div class=" col-lg-12">
								<section class="panel panel-default">
									<div class="panel-heading text-center"
										style="font-size: initial; background: white;">
										<i class="fa fa-bar-chart-o"></i>&nbsp;&nbsp; <em><strong><spring:message code="SovDirectory.SelectedProjectChart.Heading" /></strong></em>
									</div>
									<div id="barChartForSelectedProject" style="background-color: #ededed;"></div>
								</section>
							</div>
								</fieldset>
							</div>
							<div class="tab-pane" id="s2">
							<fieldset id="">
                               <div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="sovDetailsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 20px;"><spring:message code="SovDirectory.JobDatatable.TableHead.Column1" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="Jobs.Datatable.TableHead.Column2" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="Jobs.Datatable.TableHead.Column3" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="Jobs.Datatable.TableHead.Column4" /></th>

										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 10px;"></th>
								</thead>
								<tbody>
								</tbody>
							</table>
                       </div>
								</fieldset>
							</div>
						</div>
					</div>
				</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- Till Here -->


		</article>
		<!-- WIDGET END -->

	</div>

	<!-- end row -->

	<!-- end row -->

</section>
<!-- end widget grid -->
<div id="jqg" style="display: none;">
	<table id="jQGridDemo"></table>
	<div id="pager"></div>
</div>

<script>
$('#selectProject').on('change',function(){
	if($('#selectProject').val()==""){
		$('#backBtnId').addClass('hide');
		$('#selectDivId').removeClass('col-md-11');
		$('#selectDivId').addClass('col-md-12');
	}
	else{
		$('#selectDivId').removeClass('col-md-12');
		$('#selectDivId').addClass('col-md-11');
		$('#backBtnId').removeClass('hide');
	}
	/* var $target = $('html,body'); 
	$target.animate({scrollTop: $target.height()}, 1000); */
});
</script>
<!-- END #MAIN CONTENT -->

<!-- <div role="content">
	<table id="jQGridDemo"></table>
	<div id="pager"></div>
</div>
<div>
	<button type="button" onclick="changeColAttr();" title="Change" class="btn btn-primary btn-sm "></button>
	<button type="button" onclick="changeColAttr1();" title="Chang"></button>
</div> -->