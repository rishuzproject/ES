<!-- #MAIN CONTENT -->

<head>
<style>

.projectErrorBlock
{
  box-shadow: 3px 5px 11px #836463;border-right: 5px solid #953b39;background:#fff;color:#836463;height: 160px; overflow-y: auto
}
.projectErrorHeader
{
 border-bottom: 1px solid #953b39;border-right: 5px solid #953b39;margin-bottom:0px !important
}
.alert.alert-danger::-webkit-scrollbar {
	height: 9px;
	width: 9px;
}

.alert.alert-danger::-webkit-scrollbar-thumb {
	background-color: rgb(216, 168, 168);
}

.alert.alert-danger::-webkit-scrollbar-thumb:hover {
	background-color: rgb(216, 168, 168);
}

.alert.alert-danger::-webkit-scrollbar-thumb:active {
	background-color: rgb(216, 168, 168);
}
</style>
</head>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript" src="assets/js/projectType.js"></script>
<script type="text/javascript" src="assets/js/jquery.gritter.min.js"></script>
<link href="assets/css/jquery.gritter.css" rel="stylesheet">
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title">
			<i class="fa fa-file fa-fw txt-color-blue"></i><spring:message code="ProjectTypes.Pagehead.Heading"/><span>&gt;<spring:message code="ProjectTypes.Pagehead.SubHeading"/>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="ProjectTypes.Pagehead.TotalInvoices"/> <span class="txt-color-blue" id="totalInvoices"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="remoteModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"><span class="badge pull-right inbox-badge customColor">×</span></button>
					<h4 class="modal-title" id="modalTitleIdForProjTypes"><i class="fa fa-file fa-fw txt-color-blue" ></i><spring:message code="ProjectTypes.Form.AddNewProject"/></h4>
				</div>
				<div class="modal-body no-padding">

					<form id="projectTypeForm" method="POST" class="smart-form">

						<fieldset>
							<section class="11">
									<label class="input"> <i class="icon-append fa fa-file-text mandatoryStyle"></i>
										<input type="text" name="projectTypeName" id ="projectTypeName" placeholder="<spring:message code="ProjectTypes.AddNewProject.label.ProjectType"/>">
										<input type="hidden" name="projectTypeAction" id ="projectTypeAction">
										<input type="hidden" name="projectTypeId" id ="projectTypeId">
										<b class="tooltip tooltip-bottom-right"><spring:message code="ProjectTypes.AddNewProject.tooltip.ProjectType"/></b>
									</label>
							</section>
						</fieldset>

						<footer>
						<button type="button" class="btn btn-default" data-dismiss="modal" onclick="resetProjectTypeForm()"><spring:message code="Button.Cancel"/></button>
						<button type="button" class="btn btn-primary" id="reset" onClick="resetProjectTypeForm()"><i class="fa fa-undo"></i> &nbsp;<spring:message code="Button.Reset"/></button>
						<button type="submit" class="btn btn-primary" id="saveandcontinue" onclick="setProjectTypeId('saveandcontinue');"> <i class="fa fa-forward"></i> &nbsp;<spring:message code="Button.SaveContinue"/></button>
						<button type="submit" class="btn btn-primary" id="save" onclick="setProjectTypeId('save');"><i class="fa fa-floppy-o"></i> &nbsp;<spring:message code="Button.Save"/></button>
							
						</footer>

					</form>
				</div>


			</div>
		</div>
	</div>
	<!-- END MODAL -->

</div>

<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="projectUploadModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"   data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="cancelProjectUploadForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
						<h4 class="modal-title" id="modalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Add New Project</h4>
				</div>
				<div class="modal-body no-padding">
					<form id="projectUploadForm" method="POST" class="smart-form">
					   <input type="hidden" id="className" name="className">
						<input type="hidden" id="confirmProjectUploadId" name="confirmProjectUploadId">
						<fieldset>
							<section>
								<label for="projectTemplateFileLabel" class="input input-file">
									<div class="button">
										<input type="file" name="projectTemplateFile" id="projectTemplateFile"
											onchange="this.parentNode.nextSibling.value = this.value">
										Browse
									</div><input type="text" name="projectFileName" id="projectFileName"
									placeholder="Choose document to Upload"
									readonly="">
								</label>
							</section>
							
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary button" id="saveProjectUpload">
									<i class="fa fa-floppy-o"></i> &nbsp; Save
							</button>
							<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="cancelProjectUploadForm()">Cancel
							</button>
						</footer>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL FOR CONFIRMATION -->						
					
	<div class="modal fade" id="projectUploadConfirmation" tabindex="-1"
  role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"   data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
   <div class="modal-content">
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"
       aria-hidden="true" onclick="resetProjectConfirmation()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
      <h4 class="modal-title" id="projectConfirmModalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Error in the template File</h4>
    </div>
    <div class="modal-body no-padding">
    <fieldset>
    	<section style="text-align: center;" id = "projectConfirmationHeader">
			<div class="space"></div>
				<h4 class="smaller">
					<b>Error Occured while uploading</b>
				</h4>
				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Continue to insert the error values as it is</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Cancel to cancel and upload again</li>
				</ul>
		</section>
    	<section id="projectErrorSection" style="display:none;">
			<div class="alert alert-danger projectErrorHeader">
				<strong>Error Occured !</strong>
			</div>
			<div class="alert alert-danger fade in projectErrorBlock" id="errorBlock"> </div>
		</section>
    </fieldset>
    <footer>
    	<button type="submit" class="btn btn-primary button" id="saveProjectConfirmUpload" onclick="setProjectUploadConfirmation(this.id)">
				<i class="fa fa-forward"></i> &nbsp;Continue
	 	</button>
	 	<button type="button" class="btn btn-default" id="cancelProjectUpload"
	 	 	data-dismiss="modal" onclick="setProjectUploadConfirmation(this.id);resetProjectConfirmation();cancelProjectUploadForm()">Cancel
	 	</button>
   	</footer>
    </div>
   </div>
  </div>
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
					<a href="" data-toggle="modal" data-target="#remoteModal"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;"><spring:message code="ProjectTypes.Form.AddNewProject"/></h2>
					</a><!--  &nbsp;|&nbsp;&nbsp; -->
					<%-- <a href="" data-toggle="modal" data-target="#uploadModal" onclick="setModuleName('<spring:message code="Upload.ProjectType"/>')">
							 <span class="widget-icon" style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
							 </span>
								<h2 style="color: #E4E4E4;">Upload File</h2>
						  </a> --%>
					
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

					<!-- widget content -->
					<div class="widget-body no-padding">

						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="typesOfProjectTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 10px;">
											<spring:message code="ProjectTypes.Datatable.TableHead.Column1"/></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 500px;"><i
											class="fa fa-fw fa-file-text text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ProjectTypes.Datatable.TableHead.Column2"/></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 10px;">
											</th>

										</tr>
										
								</thead>
								<tbody>
									
								</tbody>
							</table>

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
<!--end of initializing datepicker -->

<!-- END #MAIN CONTENT -->