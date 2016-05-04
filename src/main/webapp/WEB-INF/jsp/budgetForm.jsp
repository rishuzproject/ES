<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<style>
.sparks-info {
min-width: 50px;
}
.budgetErrorBlock
{
  box-shadow: 3px 5px 11px #836463;border-right: 5px solid #953b39;background:#fff;color:#836463;height: 160px; overflow-y: auto
}
.budgetErrorHeader
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
#sparks li {
  padding: 0 7px !important;
  }
</style>
</head>

<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/budgetForm.js"></script>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-5">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-lg fa-fw fa-barcode txt-color-blue"></i> <spring:message code="BudgetCode.PageHead.Heading"/> <span>&gt; <spring:message code="BudgetCode.PageHead.SubHeading"/> </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-7">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="BudgetCode.PageHead.Total"/> <span class="txt-color-blue" id="totalId"></span>
				</h5>
			</li>
			 <li class="sparks-info">
				<h5>
					<spring:message code="BudgetCode.PageHead.Mat"/>  <span class="txt-color-purple" id="matId"></span>
				</h5>

			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="BudgetCode.PageHead.Sub"/>  <span class="txt-color-greenDark" id="subId"></span>
				</h5>

			</li> 
			<li class="sparks-info">
				<h5>
				<spring:message code="BudgetCode.PageHead.DJE"/><span class="txt-color-greenDark" id="djeId"></span>
				</h5>

			</li> 
			<li class="sparks-info">
				<h5>
					<spring:message code="BudgetCode.PageHead.Indir"/><span class="txt-color-greenDark" id="indirId"></span>
				</h5>

			</li> 
			<li class="sparks-info">
				<h5>
					<spring:message code="BudgetCode.PageHead.Eqip"/><span class="txt-color-greenDark" id="eqipId"></span>
				</h5>

			</li> 
			<li class="sparks-info">
				<h5>
					<spring:message code="BudgetCode.PageHead.ProjAd"/><span class="txt-color-greenDark" id="projadId"></span>
				</h5>

			</li> 
			<li class="sparks-info">
				<h5>
					<spring:message code="BudgetCode.PageHead.Lbr"/><span class="txt-color-greenDark" id="lbrId"></span>
				</h5>

			</li> 
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="budgetFormRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="resetbudgetForm()">
						<span class="badge pull-right inbox-badge customColor">×</span>
					</button>
					<h4 class="modal-title" id="modaltitleIdForBudgetCodes"><i class="fa fa-lg fa-fw fa-barcode txt-color-blue"></i>&nbsp;<spring:message code="BudgetCode.Form.AddNewBudgetCode"/></h4>
				</div>
				<div class="modal-body no-padding">

					<form id="budget_form_id"  method="POST" class="smart-form" commandName = "budgetCodeForm">
						<input type="hidden" name="activityId" id="activityId" >
						<input type="hidden" id="typeOfAction" name="typeOfAction">
						<fieldset>
							<div class="row">
								<section class="col col-6">
									<label class="select">  <select
											class="form-control show-tick" id="costType"
											name="costType">

												<option value=""><spring:message code="BudgetCode.label.CostType"/></option>
												<option value="MAT">Material</option>
												<option value="SUB">Sub Contractor</option>
												<option value="DJE">Direct Job</option>
												<option value="INDIRECT">Indirect</option>
												<option value="EQUIP">Equipment</option>
												<option value="PROJECT ADMIN">Project Admin</option>
												<option value="LABOR">Labor</option>
										</select>
										<i class="mandatoryIconStyle"></i>
										</label>
								</section>
								<section class="col col-6">
									<label class="input">
									<i class="icon-append fa fa-sort-numeric-desc mandatoryStyle"></i>
										<input type="text" class="form-control"
											name="activityNumber" id="activityNumber" placeholder="<spring:message code="BudgetCode.label.ActivityNumber"/>" >
											<b class="tooltip tooltip-bottom-right"><spring:message code="BudgetCode.tooltip.ActivityNumber"/></b>
									</label>
								</section>
							</div>
							
								<div class="row">
								<section>
									<div class="col col-lg-12">
										<label class="textarea"> 
										<i class="icon-append fa fa-comment mandatoryStyle"></i>
										<textarea  cols="4" maxlength="1000" name="activityDescription" id="activityDescription" 
										placeholder="<spring:message code="BudgetCode.label.ActivityDescription"/>"></textarea>
										<b class="tooltip tooltip-bottom-right"><spring:message code="BudgetCode.tooltip.ActivityDescription"/></b>
										<font color="red" size="1px"><p align="right"><spring:message code="BudgetCode.label.ActivityDescription.limit"/></p></font><br>
									</label>
									</div>
								</section>
								</div>
								
						</fieldset>

						<footer>
						<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetbudgetForm()"><spring:message code="Button.Cancel"/></button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetbudgetForm()">
								<i class="fa fa-undo"></i> &nbsp;<spring:message code="Button.Reset"/>
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right"
								id="saveandcontinue" onclick="setActionType(this.id,this)">
								<i class="fa fa-forward"></i> &nbsp;<spring:message code="Button.SaveContinue"/>
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right"
							 id="save"  onclick="setActionType(this.id,this)">
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
	<div class="modal fade" id="budgetUploadModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="cancelBudgetUploadForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
						<h4 class="modal-title" id="modalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Add New Budget</h4>
				</div>
				<div class="modal-body no-padding">
					<form id="budgetUploadForm" method="POST" class="smart-form">
						<input type="hidden" id="confirmBudgetUploadId" name="confirmBudgetUploadId">
						<fieldset>
							<section>
								<label for="budgetTemplateFileLabel" class="input input-file">
									<div class="button">
										<input type="file" name="budgetTemplateFile" id="budgetTemplateFile"
											onchange="this.parentNode.nextSibling.value = this.value">
										<spring:message code="BudgetCode.UploadFileModal.Browse"/>
									</div><input type="text" name="budgetFileName" id="budgetFileName"
									placeholder="<spring:message code="BudgetCode.UploadFileModal.Placeholder"/>"
									readonly="">
								</label>
							</section>
							
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary button" id="saveBudgetUpload">
									<i class="fa fa-floppy-o"></i> &nbsp; <spring:message code="Button.Save"/>
							</button>
							<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="cancelBudgetUploadForm()"><spring:message code="Button.Cancel"/>
							</button>
						</footer>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- MODAL FOR CONFIRMATION -->						
					
	<div class="modal fade" id="budgetUploadConfirmation" tabindex="-1"
  role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
   <div class="modal-content">
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"
       aria-hidden="true" onclick="resetBudgetConfirmation()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
      <h4 class="modal-title" id="budgetConfirmModalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Error in the template File</h4>
    </div>
    <div class="modal-body no-padding">
    <fieldset>
    	<section style="text-align: center;" id = "budgetConfirmationHeader">
			<div class="space"></div>
				<h4 class="smaller">
					<b>Error Occured while uploading</b>
				</h4>
				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Continue to insert the error values as it is</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Cancel to cancel and upload again</li>
				</ul>
		</section>
    	<section id="budgetErrorSection" style="display:none;">
			<div class="alert alert-danger budgetErrorHeader">
				<strong>Error Occured !</strong>
			</div>
			<div class="alert alert-danger fade in budgetErrorBlock" id="errorBlock"> </div>
		</section>
    </fieldset>
    <footer>
    	<button type="submit" class="btn btn-primary button" id="saveBudgetConfirmUpload" onclick="setBudgetUploadConfirmation(this.id)">
				<i class="fa fa-forward"></i> &nbsp;Continue
	 	</button>
	 	<button type="button" class="btn btn-default" id="cancelBudgetUpload"
	 	 	data-dismiss="modal" onclick="setBudgetUploadConfirmation(this.id);resetBudgetConfirmation();cancelBudgetUploadForm()">Cancel
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
					&nbsp; <a href="" data-toggle="modal"
						data-target="#budgetFormRemoteModal" onclick="resetbudgetForm()"> <span class="widget-icon"
						style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">&nbsp;<spring:message code="BudgetCode.Form.AddNewBudgetCode"/></h2>
					</a><%-- &nbsp;|&nbsp;&nbsp;
						  
					
					<a href="" data-toggle="modal" data-target="#uploadModal" onclick="setModalName('<spring:message code="Upload.BudgetCode"/>')">
							 <span class="widget-icon" style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
							 </span>
								<h2 style="color: #E4E4E4;"><spring:message code="BudgetCode.UploadFileModal.Label"/></h2>
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
							<table id="budgetFormsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 20px;" class="center">
											#</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="BudgetCode.DataTable.TableHead.Column2"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Comp Address: activate to sort column ascending"
											style="width: 200px;"> <spring:message code="BudgetCode.DataTable.TableHead.Column3"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Comp Email: activate to sort column ascending"
											style="width: 404px;"> <spring:message code="BudgetCode.DataTable.TableHead.Column4"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
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

<!-- END #MAIN CONTENT -->