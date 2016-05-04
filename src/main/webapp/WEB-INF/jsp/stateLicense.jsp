<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>

<style>

.localLicErrorBlock
{
  box-shadow: 3px 5px 11px #836463;border-right: 5px solid #953b39;background:#fff;color:#836463;height: 160px; overflow-y: auto
}
.localLicErrorHeader
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

<script type="text/javascript"
	src="assets/js/sLicenseDirectory.js"></script>
<script type="text/javascript">
loadScript("assets/js/datatable/jquery.dataTables.min.js", function() {
	loadScript("assets/js/datatable/dataTables.tableTools.min.js", function() {
		loadScript("assets/js/datatable/dataTables.bootstrap.min.js",
				function() {
					loadScript(
							"assets/js/datatable/datatables.responsive.min.js",
							pagefunction);
				});
	});
});

</script>

</head>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title">
			<i class="glyphicon glyphicon-user txt-color-blue  colorBlue"></i> <spring:message code="StateLicense.PageHead.License"/> <span>&gt; 
			<spring:message code="StateLicense.PageHead.State"/></span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h4>
					 <spring:message code="StateLicense.PageHead.TotalLicenses"/>  <span class="txt-color-blue" id="totalCount"></span>
				</h4>
			</li>
		</ul>
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
					<!-- <div class="btn-group modalBtnGroup" >
						<button class=" btn customBtnName" data-toggle="modal" data-target="#addLicenseModal">
							<span class="fa fa-plus" style="color:#fff"></span>
						</button>
						<label class="customLabel">Generate new 
							State License</label>
					</div> -->
					<a href=""  data-toggle="modal" data-target="#addStateLicenseModal" onclick="resetSLicenseForm();">
						<span class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color:#E4E4E4">
							&nbsp;
							<spring:message code="StateLicense.Form.AddNewStateLicense"/>
						</h2>
					</a> <!-- &nbsp;|&nbsp;&nbsp; -->
					
					<%-- <a href="" data-toggle="modal" data-target="#uploadModal" onclick="setModuleName('<spring:message code="Upload.SLicenseDirectory"/>')">
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
							<table id="sLicenseDetailsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;"><i
											class="fa fa-fw fa-key txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											 <spring:message code="StateLicense.DataTable.TableHead.Column1"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Email: activate to sort column ascending"
											style="width: 110px;"><i
											class="fa fa-fw fa-user txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="StateLicense.DataTable.TableHead.Column2"/> </th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 95px;">
											<spring:message code="StateLicense.DataTable.TableHead.Column3"/> </th>
											<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 95px;">
											<spring:message code="StateLicense.DataTable.TableHead.Column4"/> </th>
											<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 95px;">
											<spring:message code="StateLicense.DataTable.TableHead.Column5"/> </th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" : activate to sort column ascending"
											style="width: 30px;"></th>
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

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
	<!-- Button trigger modal -->
	
	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="addStateLicenseModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true" data-dismiss = "modal"   data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetSLicenseForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
					<h4 class="modal-title" id="modelTitleIdForStateLiecence"><i class="glyphicon glyphicon-user txt-color-blue  colorBlue"></i> <spring:message code="StateLicense.Form.AddNewStateLicense"/></h4>
				</div>
				<div class="modal-body no-padding">
					<form  id="stateLicenseForm"
						class="smart-form" method="post">
						<fieldset>
						<section>
						<label class="input">
						<i class="icon-append fa fa-sort-numeric-asc mandatoryStyle"></i><input type="text"
								  id="licenseNumber" name="licenseNumber"  placeholder="<spring:message code="StateLicense.label.LicenseNumber"/>"> <b
									class="tooltip tooltip-bottom-right"><spring:message code="StateLicense.tooltip.LicenseNumber"/></b>
									<input type="hidden" id="licenseAction" name="licenseAction"> 
						            <input type="hidden" id="licenseId" name="licenseId"> 
								</label>
						</section>
						<section>
								<label class="select">
								<select class="form-control selectpicker show-tick" id="usStates" name="usStates">
									<option value=""><spring:message code="StateLicense.select.USStates"/></option>
								</select><i class="mandatoryIconStyle"></i> </label>
						</section>
						
							<section>
							<label class="input"> 
							<i  class="icon-append fa fa-calendar mandatoryStyle"></i> <input type="text"
													class="form-control" name="expiryDate" id="expiryDate"
													placeholder="<spring:message code="StateLicense.label.ExpiryDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message code="StateLicense.tooltip.ExpiryDate"/></b>
												</label>
								
							</section>
							<section>
								<label class="input"> <i
									class="icon-append fa fa-user"></i> <input type="text"
									name="primaryPerson" id="primaryPerson" placeholder="<spring:message code="StateLicense.label.PrimaryPerson"/>"> <b
									class="tooltip tooltip-bottom-right"><spring:message code="StateLicense.tooltip.PrimaryPerson"/></b>
									
								</label>
							</section>
							<div  class="row" id="labelDiv">
							<section class="col col-12">
								<label class="input">
								 	<a href="javascript:toggleDiv('descDiv');"><spring:message code="StateLicense.label.AddLicenseDescription"/></a>
								</label>
							</section>
							</div>
							<div class="row" id="descDiv" style="display:none;">
							
							<section class="col col-md-12">
								<label class="textarea">
									<i class="icon-append fa fa-comment"></i> 										
										<textarea name="licenseDescription" id="licenseDescription" class="form-control" rows="6" placeholder="<spring:message code="StateLicense.textarea.LicenseDescription"/>"></textarea> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="StateLicense.tooltip.LicenseDescription"/></b> 
								</label>
							</section>
							</div>
							
							
						</fieldset>
						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" ><spring:message code="Button.Cancel"/></button>
							<button type="button" class="btn btn-primary" id="reset" onclick="resetSLicenseForm();">
								<i class="fa fa-undo"></i> &nbsp;<spring:message code="Button.Reset"/>
							</button>
							<button type="submit" class="btn btn-primary"
								id="saveandcontinue" onclick="setAction('saveandcontinue');">
								<i class="fa fa-forward"></i> &nbsp;<spring:message code="Button.SaveContinue"/>
							</button>
							<button type="submit" class="btn btn-primary" id="saveButtonId" onclick="setAction('save');">
								<i class="fa fa-floppy-o"></i> &nbsp; <spring:message code="Button.Save"/>
							</button>
						</footer>

					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- END MODAL -->

</div>

<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="stateLicUploadModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="cancelSLicenseUploadForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
						<h4 class="modal-title" id="modalUploadTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Add New State License</h4>
				</div>
				<div class="modal-body no-padding">
					<form id="stateLicUploadForm" method="POST" class="smart-form">
						<input type="hidden" id="confirmProjectUploadId" name="confirmProjectUploadId">
						<input type="hidden" id="className" name="className">
						<fieldset>
							<section>
								<label for="stateLicTemplateFileLabel" class="input input-file">
									<div class="button">
										<input type="file" name="stateLicTemplateFile" id="stateLicTemplateFile"
											onchange="this.parentNode.nextSibling.value = this.value">
										Browse
									</div><input type="text" name="stateLicFileName" id="stateLicFileName"
									placeholder="Choose document to Upload"
									readonly="">
								</label>
							</section>
							
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary button" id="saveStateLicUpload">
									<i class="fa fa-floppy-o"></i> &nbsp; Save
							</button>
							<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="cancelSLicenseUploadForm()">Cancel
							</button>
						</footer>
					</form>
				</div>
			</div>
		</div>
	</div>

<!-- MODAL FOR CONFIRMATION -->						
					
<div class="modal fade" id="stateLicUploadConfirmation" tabindex="-1"
  role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"   data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
   <div class="modal-content">
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"
       aria-hidden="true" onclick="resetSLicenseConfirmation()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
      <h4 class="modal-title" id="stateLicConfirmModalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Error in the template File</h4>
    </div>
    <div class="modal-body no-padding">
    <fieldset>
    	<section style="text-align: center;" id = "sLicenseConfirmationHeader">
			<div class="space"></div>
				<h4 class="smaller">
					<b>Error Occured while uploading</b>
				</h4>
				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Continue to insert the error values as it is</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Cancel to cancel and upload again</li>
				</ul>
		</section>
    	<section id="stateLicErrorSection" style="display:none;">
			<div class="alert alert-danger stateLicErrorHeader">
				<strong>Error Occured !</strong>
			</div>
			<div class="alert alert-danger fade in stateLicErrorBlock" id="stateLicErrorBlock"> </div>
		</section>
    </fieldset>
    <footer>
    	<button type="submit" class="btn btn-primary button" id="saveStateLicConfirmUpload" onclick="setStateLicUploadConfirmation(this.id)">
				<i class="fa fa-forward"></i> &nbsp;Continue
	 	</button>
	 	<button type="button" class="btn btn-default" id="cancelSLicenseUpload"
	 	 	data-dismiss="modal" onclick="setStateLicUploadConfirmation(this.id);resetSLicenseConfirmation();cancelSLicenseUploadForm();">Cancel
	 	</button>
   	</footer>
    </div>
   </div>
  </div>
 </div>	

<!-- END #MAIN CONTENT -->
<script>
function toggleDiv(divId) {
	   $("#" + divId).toggle();
	  }
</script>