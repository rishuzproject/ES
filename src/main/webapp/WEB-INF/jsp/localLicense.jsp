<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>
<style>
.stateLicErrorBlock
{
  box-shadow: 3px 5px 11px #836463;border-right: 5px solid #953b39;background:#fff;color:#836463;height: 160px; overflow-y: auto
}
.stateLicErrorHeader
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
.uploadBtnCustom
{
 padding:6px !important;
}
/* Mimic table appearance */
    div.table {
      display: table;
    }
    div.table .file-row {
      display: table-row;	
    }
    div.table .file-row > div {
      display: table-cell;
      vertical-align: top;
      border-top: 1px solid #ddd;
      padding:2px 37px 3px 0px;
      /* padding: 8px; */
    }
    div.table .file-row:nth-child(odd) {
      background: #f9f9f9;
    }



    /* The total progress gets shown by event listeners */
    #total-progress {
      opacity: 0;
      transition: opacity 0.3s linear;
    }

    /* Hide the progress bar when finished */
    #previews .file-row.dz-success .progress {
      opacity: 0;
      transition: opacity 0.3s linear;
    }

    /* Hide the delete button initially */
    #previews .file-row .delete {
      display: none;
    }

    /* Hide the start and cancel buttons and show the delete button */

    #previews .file-row.dz-success .start,
    #previews .file-row.dz-success .cancel {
      display: none;
    }
    #previews .file-row.dz-success .delete {
      display: block;
    }
</style>
</head>
<!-- #MAIN CONTENT -->
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-sitemap fa-fw txt-color-blue "></i>
			<spring:message code="LocalLicense.PageHead.License"/>
			<span> &gt;&nbsp; 
			<spring:message code="LocalLicense.PageHead.LocalLicense"/>
			</span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="LocalLicense.PageHead.TotalLocalLicense"/>
					<span class="txt-color-blue" id="totalLocalLicenses"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<div class="modal fade" id="localLicenseModal" tabindex="-1" role="dialog"
		aria-labelledby="localLicenseModal" aria-hidden="true" data-dismiss = "modal"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						 aria-hidden="true" onclick="resetLLicenseForm();">
						<span class="badge pull-right inbox-badge customColor">×</span>
					</button>
					<h4 class="modal-title" id="itemModalTitle">
						<i class="fa fa-sitemap fa-fw txt-color-blue "></i>&nbsp;
						<spring:message code="LocalLicense.Form.AddNewLocalLicense"/>
					</h4>
				</div>
				<div class="modal-body no-padding">
					<div class="fuelux">
						<div class="wizard-example" style="display: none;">
							<div class="wizard" id="MyWizard" style="border-radius: 0;">
								<ul class="steps">
									<li class="active" data-target="#step1"><span
										class="badge badge-info">1</span><spring:message code="LocalLicense.Form.Page"/> 1<span
										class="chevron"></span></li>
									<li data-target="#step2" class=""><span class="badge">2</span><spring:message code="LocalLicense.Form.Page"/>
										Info.<span class="chevron"></span></li>
								<!-- 	<li data-target="#step3" class=""><span class="badge">3</span>Form Page 3<span
										class="chevron"></span></li> -->
								</ul>
							</div>
						</div>
						<form id="localLicenseForm" class="smart-form" method="post">
						<div class="step-content">
							<div class="step-pane active" id="step1">
									<fieldset>
							<div class="row">
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-sort-numeric-asc mandatoryStyle"></i>
										<input type="text" name="licenseNumber" id="licenseNumber" placeholder="<spring:message code="LocalLicense.label.LicenseNumber"/>" > 
										<b class="tooltip tooltip-bottom-right"><spring:message code="LocalLicense.tooltip.LicenseNumber"/></b>
									    <input type="hidden" id="licenseAction" name="licenseAction"> 
						            <input type="hidden" id="licenseId" name="licenseId"> 
									</label>
								</section>
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-building mandatoryStyle"></i>
										<input type="text" name="localJurisdiction" id="localJurisdiction" 
										placeholder="<spring:message code="LocalLicense.select.LocalJurisdiction"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="LocalLicense.tooltip.LocalJurisdiction"/></b>
									</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-calendar mandatoryStyle"></i>
										<input type="text" name="expiryDate" id="expiryDate" placeholder="<spring:message code="LocalLicense.label.ExpiryDate"/>" class="valid"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="LocalLicense.tooltip.ExpiryDate"/></b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-user"></i>
										<input type="text" name="primaryPerson" id="primaryPerson" placeholder="<spring:message code="LocalLicense.label.PrimaryPerson"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="LocalLicense.tooltip.PrimaryPerson"/></b>
									</label>
								</section>
							</div>
							<div  class="row" id="labelDiv" name="licenseDescription">
							<section class="col col-12">
								<label class="input">
									<!-- <a href="javascript:void(0);" id="labelDesc" onclick="toggleDescription();">
								 		Add License Description
								 	</a> -->
								 	<a href="javascript:toggleDiv('descDiv');"><spring:message code="LocalLicense.label.AddLicenseDescription"/></a>
								</label>
							</section>
							</div>
							<div class="row" id="descDiv" style="display:none;">
							
							<section class="col col-md-12">
								<label class="textarea">
									<i class="icon-append fa fa-comment"></i> 										
										<textarea name="licenseDescription" id="licenseDescription" class="form-control" rows="6" placeholder="<spring:message code="LocalLicense.textarea.LicenseDescription"/>"></textarea> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="LocalLicense.tooltip.LicenseDescription"/></b> 
								</label>
							</section>
							</div>
						</fieldset>
							</div>

							

							<div class="step-pane" id="step2">
									<fieldset>
							<section>
								<div class="row">
									<div class="col col-lg-12">
									<div class="alert alert-info alert-block hide" id="info">
				 						<a class="close" data-dismiss="alert" id="infoDiv" href="#">×</a>
											<h4 class="alert-heading"><spring:message code="LocalLicense.Upload.UploadFilehere"/>.</h4>
									</div>
									<div class="alert alert-block alert-warning hide" id="incorrectFormat">
				 						<a class="close" data-dismiss="alert" id="incorrectFormatDiv" href="#">×</a>
											<h4 class="alert-heading"><spring:message code="LocalLicense.Upload.Fileformatdoesnotmatch"/>.</h4>
									</div>
									<div class="alert alert-block alert-warning hide" id="incorrectEmail">
				 						<a class="close" data-dismiss="alert" id="incorrectEmailDiv" href="#">×</a>
											<h4 class="alert-heading"><spring:message code="LocalLicense.Upload.Emailvalidationfailed"/>.</h4>
									</div>
									<div class="alert alert-block alert-warning hide" id="incorrectPhno">
				 						<a class="close" data-dismiss="alert" id="incorrectPhnoDiv" href="#">×</a>
											<h4 class="alert-heading"><spring:message code="LocalLicense.Upload.PhoneNumberValidationfailed"/>.</h4>
									</div>
									<div class="alert alert-block alert-warning hide" id="largeFileSize">
				 						<a class="close" data-dismiss="alert" id="largeFileSizeDiv" href="#">×</a>
											<h4 class="alert-heading"><spring:message code="LocalLicense.Upload.Someotherproblemoccured"/>.</h4>
									</div>
										<div class="fileupload fileupload-new" data-provides="fileupload">
											<section>
												<label fogr="templateFileLabel" class="control-label fileupload-exists" style="padding-bottom: 10px;">
												<spring:message code="LocalLicense.Upload.SelectFiletoupload"/>: 
												</label>
													<span data-original-title="" title="" style="color: red; margin-top: 22px">
														<b> *</b>
													</span>
													<label for="file" class="input input-file">
															<div class="button"><input type="file" name="templateFile" id="templateFile" onchange="this.parentNode.nextSibling.value = this.value">
															<spring:message code="LocalLicense.Upload.Browse"/>: 
															</div><input type="text" placeholder="No file chosen" readonly="">
													</label>
											</section>
										</div>
									</div>
								</div>
							</section>
							<section id="errorSection" style="display:none;">
							<div class="alert alert-danger custErrorHeader">
								<strong><spring:message code="LocalLicense.Upload.ErrorOccured"/> !</strong>
							</div>
							<div class="alert alert-danger fade in custErrorBlock" id="errorBlock">
			                    </div>
							</section>


                             <!-- start of new upload -->
						<%-- <section>
							<div class="" id="container">

								<div id="actions" class="">

									<div class="col-lg-7">
										<!-- The fileinput-button span is used to style the file input field as button -->
										<span class="btn btn-success fileinput-button uploadBtnCustom"> <i
											class="glyphicon glyphicon-plus"></i>
											 <span><spring:message code="LocalLicense.Upload.Addfiles"/>...</span>
										</span>
										<button type="submit" class="btn btn-primary start uploadBtnCustom">
											<i class="glyphicon glyphicon-upload "></i> 
											<span><spring:message code="LocalLicense.Upload.Startupload"/></span>
										</button>
										<button type="reset" class="btn btn-warning cancel uploadBtnCustom">
											<i class="glyphicon glyphicon-ban-circle "></i> 
											<span><spring:message code="LocalLicense.Upload.Cancelupload"/></span>
										</button>
									</div>

									<div class="col-lg-5">
										<span class="fileupload-process">
											<div id="total-progress" class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
												<div class="progress-bar progress-bar-success"
													style="width: 0%;" data-dz-uploadprogress></div>
											</div>
										</span>
									</div>
								</div>

								<div class="table table-striped" class="files" id="previews">

									<div id="template" class="file-row">
										<div style="text-align:center;vertical-align: middle;">
											<span class="preview"><img data-dz-thumbnail /></span>
										</div>
										<div style="text-align:center;vertical-align: middle;">
											<p class="name" data-dz-name></p>
											<strong class="error text-danger" data-dz-errormessage></strong>
										</div>
										<div style="text-align:center;vertical-align: middle;">
											<p class="size" data-dz-size></p>
											<div class="progress progress-striped active"
												role="progressbar" aria-valuemin="0" aria-valuemax="100"
												aria-valuenow="0">
												<div class="progress-bar progress-bar-success"
													style="width: 0%;" data-dz-uploadprogress></div>
											</div>
										</div>
										<div style="text-align:center;vertical-align: middle;">
											<button class="btn btn-primary start uploadBtnCustom">
												<i class="glyphicon glyphicon-upload"></i> 
												<span><spring:message code="LocalLicense.Upload.Start"/></span>
											</button>
											<button data-dz-remove class="btn btn-warning cancel uploadBtnCustom">
												<i class="glyphicon glyphicon-ban-circle"></i> 
												<span><spring:message code="LocalLicense.Upload.Cancel"/></span>
											</button>
											<button data-dz-remove class="btn btn-danger delete uploadBtnCustom">
												<i class="glyphicon glyphicon-trash"></i> 
												<span><spring:message code="LocalLicense.Upload.Delete"/></span>
											</button>
										</div>
									</div>

								</div>


							</div>
						</section>
						<!-- end of new upload -->
							
							
						</fieldset> --%>
								</div>
						</div>
						<footer>
							
						     <button type="button" class="btn btn-primary"
								id="btnWizardNext" ><spring:message code="LocalLicense.Form.Next"/></button>
								
								
							  <span class="hide" id="submitbuttons">
							  <button type="button"class="btn btn-default pull-left" id="btnWizardPrev" type="button"
								value="Back"><spring:message code="LocalLicense.Form.Back"/></button> 
						      <button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="resetLLicenseForm();">
								 		<spring:message code="Button.Cancel" />
								</button>
								<button type="button" class="btn btn-primary" id="reset"  onclick="resetLLicenseForm();"
									>
									<i class="fa fa-undo"></i>
									<spring:message code="Button.Reset" />
								</button>
								<button type="button" class="btn btn-primary ladda-button" 
									id="saveandcontinue" onclick="handleSavingOperation('saveandcontinue');">
									<i class="fa fa-forward"></i>
									<spring:message code="Button.SaveContinue" />
								</button>
								<button type="button" class="btn btn-primary ladda-button" data-style="expand-right" id="save" onclick="handleSavingOperation('save');">
									<i class="fa fa-floppy-o"></i>
									<spring:message code="Button.Save" />
								</button>
						</span>
						</footer>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="localLicUploadModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="cancelLLicenseUploadForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
						<h4 class="modal-title" id="modalUploadTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Add New Local License</h4>
				</div>
				<div class="modal-body no-padding">
					<form id="localLicUploadForm" method="POST" class="smart-form">
						<input type="hidden" id="confirmProjectUploadId" name="confirmProjectUploadId">
						<input type="hidden" id="className" name="className">
						<fieldset>
							<section>
								<label for="localLicTemplateFileLabel" class="input input-file">
									<div class="button">
										<input type="file" name="localLicTemplateFile" id="localLicTemplateFile"
											onchange="this.parentNode.nextSibling.value = this.value">
										Browse
									</div><input type="text" name="localLicFileName" id="localLicFileName"
									placeholder="Choose document to Upload"
									readonly="">
								</label>
							</section>
							
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary button" id="saveLocalLicUpload">
									<i class="fa fa-floppy-o"></i> &nbsp; Save
							</button>
							<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="cancelLLicenseUploadForm()">Cancel
							</button>
						</footer>
					</form>
				</div>
			</div>
		</div>
	</div>

<!-- MODAL FOR CONFIRMATION -->						
					
<div class="modal fade" id="localLicUploadConfirmation" tabindex="-1"
  role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
   <div class="modal-content">
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"
       aria-hidden="true" onclick="resetLLicenseConfirmation()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
      <h4 class="modal-title" id="localLicConfirmModalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Error in the template File</h4>
    </div>
    <div class="modal-body no-padding">
    <fieldset>
    	<section style="text-align: center;" id = "lLicenseConfirmationHeader">
			<div class="space"></div>
				<h4 class="smaller">
					<b>Error Occured while uploading</b>
				</h4>
				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Continue to insert the error values as it is</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Cancel to cancel and upload again</li>
				</ul>
		</section>
    	<section id="localLicErrorSection" style="display:none;">
			<div class="alert alert-danger localLicErrorHeader">
				<strong>Error Occured !</strong>
			</div>
			<div class="alert alert-danger fade in localLicErrorBlock" id="localLicErrorBlock"> </div>
		</section>
    </fieldset>
    <footer>
    	<button type="submit" class="btn btn-primary button" id="saveLocalLicConfirmUpload" onclick="setLocalLicUploadConfirmation(this.id)">
				<i class="fa fa-forward"></i> &nbsp;Continue
	 	</button>
	 	<button type="button" class="btn btn-default" id="cancelLLicenseUpload"
	 	 	data-dismiss="modal" onclick="setLocalLicUploadConfirmation(this.id);resetLLicenseConfirmation();cancelLLicenseUploadForm()">Cancel
	 	</button>
   	</footer>
    </div>
   </div>
  </div>
 </div>	

<section id="widget-grid" class="">
	<div class="row">
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
					&nbsp; <a href="" data-toggle="modal" data-target="#localLicenseModal" >
						<span class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color:#E4E4E4">
							&nbsp;
							<spring:message code="LocalLicense.Form.AddNewLocalLicense" />
						</h2>
					</a><!-- &nbsp;|&nbsp;&nbsp; -->
					
					<%-- <a href="" data-toggle="modal" data-target="#uploadModal" onclick="setModuleName('<spring:message code="Upload.LLicenseDirectory"/>')">
							 <span class="widget-icon" style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
							 </span>
								<h2 style="color: #E4E4E4;">Upload File</h2>
						  </a> --%>
					
					 <span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>
				<div role="content">
					<div class="jarviswidget-editbox">
					
					</div>
					<div class="widget-body no-padding">
						<div id="localLicenseTable_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="localLicenseTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="center sorting" tabindex="0" aria-controls="localLicenseTable" rowspan="1" colspan="1" style="width: 34px;">
												<spring:message code="LocalLicense.DataTable.TableHead.Column1" />
											</th>
											<th data-class="expand" class="center sorting" tabindex="0" aria-controls="localLicenseTable" rowspan="1" colspan="1" style="width: 95px;"><i
												class="fa fa-fw fa-sort-numeric-asc text-muted hidden-md hidden-sm hidden-xs"></i>
												<spring:message code="LocalLicense.DataTable.TableHead.Column2" />
											</th>
											<th data-hide="phone,tablet" class="center sorting"
												tabindex="0" aria-controls="localLicenseTable" rowspan="1"
												colspan="1"
												style="width: 93px;"><i
												class="fa fa-fw fa-building text-muted hidden-md hidden-sm hidden-xs"></i>
												<spring:message code="LocalLicense.DataTable.TableHead.Column3" />
											</th>
											<th class="center sorting" tabindex="0"
												aria-controls="localLicenseTable" rowspan="1" colspan="1"
												style="width: 87px;"><i
												class="fa fa-fw fa-calendar hidden-md hidden-sm hidden-xs"></i>
												<spring:message code="LocalLicense.DataTable.TableHead.Column4" />
											</th>
											<!-- <th class="center sorting" tabindex="0"
												aria-controls="localLicenseTable" rowspan="1" colspan="1"
												style="width: 87px;"><i
												class="fa fa-fw fa-calendar hidden-md hidden-sm hidden-xs"></i>
												Documents
											</th> -->
											<th data-hide="phone" class="center sorting_asc" tabindex="0"
												rowspan="1" colspan="1"
												style="width: 131px;" aria-sort="ascending"><i
												class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
												<spring:message code="LocalLicense.DataTable.TableHead.Column5" />
											</th>
											<th data-hide="phone,tablet" class="center sorting"
												tabindex="0" aria-controls="localLicenseTable" rowspan="1"
												colspan="1" 
												style="width: 25px;">
											</th>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!-- <script type="text/javascript" src="assets/js/loader.min.js"></script> -->
<script type="text/javascript" src="assets/js/localLicense.js"></script>
<script type="text/javascript" src="assets/js/wizardFuelex.js"></script>
<script type="text/javascript">
//load related plugins
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

<script type="text/javascript">
$(document).ready(function() {
	$('#MyWizard').on('change', function(e, data) {
	  console.log('change');
	});

	$('#MyWizard').on('changed', function(e, data) {
	  console.log('changed');
	});

	$('#MyWizard').on('finished', function(e, data) {
	  console.log('finished');
	});

	$('#btnWizardPrev').on('click', function() {
	  $('#MyWizard').wizard('previous');
	  
	  if(($("#step1").hasClass("active")))
		{
		  $("#btnWizardPrev").addClass("hide");
		  $("#submitbuttons").addClass("hide");
		  $("#btnWizardNext").removeClass("hide");
		  //$("#btnWizardPrev").removeClass("hide");
		  $("#btnWizardNext").removeClass("hide");
		}
	  if(($("#step2").hasClass("active")))
		{
			console.log("iff");
			$("#btnWizardNext").addClass("hide");
			//$("#submitbuttons").addClass("hide");
			 //$("#btnWizardNext").removeClass("hide");
		}
	});

	$('#btnWizardNext').on('click', function() {
		
	var formValid= 	$("#localLicenseForm").validate({
      
		// Rules for form validation
		rules : {
			licenseNumber : {
				required : true,
			},
			localJurisdiction: {
				required : true,
			},
			expiryDate : {
				required : true,

			},
		},

		// Messages for form validation
		messages : {
			licenseNumber : {
				required : 'Please enter the license Number'
			},
			localJurisdiction : {
				required : "Please enter the local jurisdiction",
			},
			expiryDate : {
				required : 'Please enter the expiry Date'

			},

		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});

		if($("#localLicenseForm").valid()){
			console.log("iffffffffff");
			console.log("elseeee");
			$('#MyWizard').wizard('next','foo');
			  $("#btnWizardPrev").removeClass("hide");
			  //alert("aaa1");
			  if(($("#step2").hasClass("active")))
				{
				 // alert("aaa2");
					console.log("iff");
					$("#btnWizardNext").addClass("hide");
					$("#step2").removeClass("hide");
					$("#step1").removeClass("active");
					$("#submitbuttons").removeClass("hide");
					$("#btnWizardPrev").removeClass("hide");
				}
		
		}else{
			console.log("not valid");
		}
	});

	$('#btnWizardStep').on('click', function() {
	  var item = $('#MyWizard').wizard('selectedItem');
	  console.log(item.step);
	});

	$('#MyWizard').on('stepclick', function(e, data) {
	  console.log('step' + data.step + ' clicked');
	  if(data.step===1) {
	  }
	});

	$('#btnStep2').on('click', function(e, data) {
	  $('[data-target=#step2]').trigger("click");
	});
});
        
</script>

<script>
$(document).ready(function() {
    
    var navListItems = $('ul.setup-panel li a'),
        allWells = $('.setup-content');

    allWells.hide();

    navListItems.click(function(e)
    {
        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this).closest('li');
        
        if (!$item.hasClass('disabled')) {
            navListItems.closest('li').removeClass('active');
            $item.addClass('active');
            allWells.hide();
            $target.show();
        }
    });
    
    $('ul.setup-panel li.active a').trigger('click');
    
    // DEMO ONLY //
    $('#next1').on('click', function(e) {
        $('ul.setup-panel li:eq(1)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-2"]').trigger('click');
        $(this).remove();
    })
    $('#next2').on('click', function(e) {
        $('ul.setup-panel li:eq(2)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-3"]').trigger('click');
        $(this).remove();
    })
});
function toggleDiv(divId) {
	   $("#" + divId).toggle();
	  }
</script>

