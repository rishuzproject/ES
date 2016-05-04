<!-- #MAIN CONTENT -->

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>
.custPosition {
	position: relative;
	top: 10px;
	left: 20px;
}

select option[default] {
	color: #333;
}

.smart-form .checkbox input+i:after {
	font: 400 13px/14px FontAwesome;
}

.smart-form .checkbox i {
	width: 15px;
	height: 12px;
}

.round {
	background-color: white;
	border-radius: 50%;
	border: 1x solid grey;
	padding: 10px;
}

.roleHighlight {
	color: #A6A600;
	font-weight: bold !important;
}

.disableStyles {
	/* pointer-events: none;
	color: #ccc !important; */
}
#startDate , #endDate , #coDate ,#extendedDate {
       pointer-events: none;
}
.pointerEvent{
    pointer-events: none;
}
</style>
<form action="fileDownload" id="jobUploadForm_single" method="GET"
	name="jobUploadForm_single" style="display: none">
	<input type="hidden" name="fileId" id="fileId" />
</form>
<form action="bulkDownload" id="jobUploadForm_bulk" method="GET"
	name="jobUploadForm_bulk" style="display: none">
	<input type="hidden" name="bulk_jobId" id="bulk_jobId" />
</form>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-suitcase txt-color-blue">&nbsp</i>
			<spring:message code="Jobs.PageHead.Heading" />
			<span> &gt; <spring:message code="Jobs.PageHead.SubHeading" /></span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>

					<spring:message code="Jobs.PageHead.TotalJobsInfo" />
					<span class="txt-color-blue" id="totalUsers"></span>

				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="Jobs.PageHead.ActiveJobsInfo" />
					<span class="txt-color-purple" id="activeUsers"></span>
				</h5>

			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="Jobs.PageHead.InactiveJobsInfo" />
					<span class="txt-color-greenDark" id="inactiveUsers"></span>
				</h5>

			</li>
			<%-- <li class="sparks-info">
				<h5>
					<spring:message code="Jobs.PageHead.ClosedJobsInfo" />
					<span class="txt-color-red" id="closedUsers"></span>
				</h5>

			</li> --%>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="jobDetailModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" style="width: 650px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="resetJobDetailForm()" aria-hidden="true">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title" id="modalTitleIdForJobs">
						<i class="fa fa-suitcase txt-color-blue">&nbsp;</i>
						<spring:message code="Jobs.Form.AddNewJob" />
					</h4>
				</div>
				<div class="modal-body no-padding">
					<div class="content tabsDetails">
						<ul class="nav nav-tabs" role="tablist" style="padding-top: 3px;">
							<li class="active"><a href="#information" role="tab"
								data-toggle="tab"><spring:message
										code="Jobs.AddNewJob.Tab.Information" /> <b class="badge jobCountStyle"
									id="informationTab"
									style="color: #666; background-color: yellow;"
									title="content&#13;new">0</b> </a></li>
							<li class="disableStyles updateStyles"><a href="#personnel"
								role="tab" data-toggle="tab"><spring:message
										code="Jobs.AddNewJob.Tab.Personnel" /> <b class="badge jobCountStyle"
									id="personnelTab"
									style="color: #666; background-color: yellow;">0</b> </a></li>
							<li class="disableStyles updateStyles"><a href="#legal"
								role="tab" data-toggle="tab"><spring:message
										code="Jobs.AddNewJob.Tab.Legal" /> <b class="badge jobCountStyle"
									id="legalTab" style="color: #666; background-color: yellow;">0</b>
							</a></li>
							<li class="disableStyles updateStyles"><a href="#settings"
								role="tab" data-toggle="tab"><spring:message
										code="Jobs.AddNewJob.Tab.Settings" /> <b class="badge jobCountStyle"
									id="settingsTab" style="color: #666; background-color: yellow;">0</b>
							</a></li>
							<li class="disableStyles updateStyles"><a href="#bidBudget"
								role="tab" data-toggle="tab"><spring:message
										code="Jobs.AddNewJob.Tab.Budget" /> <b class="badge jobCountStyle"
									id="budgetTab" style="color: #666; background-color: yellow;">0</b>
							</a></li>
						</ul>
						<!-- Tab panes -->
						<form id="manageJobForm" class="smart-form"
							commandName="manageJobForm">
							<input type="hidden" name="jobId" id="jobId"> <input
								type="hidden" name="jobAction" id="jobAction">
							<div class="tab-content">
								<div class="tab-pane active" id="information">
									<fieldset class="custom-scroll"
										style="height: 470px; overflow-y: scroll; overflow-x: hidden;">
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.label.JobName" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="input"><i
													class="icon-append fa fa-suitcase mandatoryStyle"></i> <input type="text"
													name="jobName" id="jobName"
													class="informationRoleHighlights"
													fieldName="<spring:message code="Jobs.Information.label.JobName" />"
													placeholder="<spring:message code="Jobs.Information.label.JobName"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.JobName" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Financials.label.BidNumber" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc mandatoryStyle"></i> <input
													type="text" name="bidNumber" id="bidNumber"
													class="informationRoleHighlights"
													fieldName="<spring:message code="Jobs.Financials.label.BidNumber" />"
													placeholder="<spring:message code="Jobs.Financials.label.BidNumber"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Financials.tooltip.BidNumber" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.label.JobNumber" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="jobNumber" id="jobNumber"
													class="informationRoleHighlights"
													fieldName="<spring:message code="Jobs.Information.label.JobNumber" />	"
													placeholder="<spring:message code="Jobs.Information.label.JobNumber"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.JobNumber" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.text.Customer" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="select"> <select
													class="form-control  show-tick informationRoleHighlights"
													id="customerDirectory"
													fieldName="<spring:message code="Jobs.Information.text.Customer" />"
													name="customerDirectory">
														<option value=""><spring:message
																code="Jobs.Information.label.ChooseCustomer" /></option>
												</select> <i class="mandatoryIconStyle"></i>
												</label>
											</section>
										</div>
										<%-- <div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin:3px;font-size:14px;"> 
												
													<spring:message code="JobDetails.NewJobCreation.PlaceHolder.JobAddress" />	
												</label>
											</section>	
											<section class="col col-lg-9">
												<label class="input"><i
													class="icon-append fa fa-home"></i> <input type="text" class="informationRoleHighlights"
													fieldName = "<spring:message code="JobDetails.NewJobCreation.PlaceHolder.JobAddress" />"
													name="jobAddress" id="jobAddress" placeholder="<spring:message code="JobDetails.NewJobCreation.PlaceHolder.JobAddress"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message code="JobDetails.NewJobCreation.Tooltip.JobAddress"/></b> </label>
											</section>
										</div> --%>
										<div class="row">
											<section class="col col-lg-6">
												<label class="input" style="margin: 3px; font-size: 14px;">
													<spring:message
														code="JobDetails.NewJobCreation.PlaceHolder.JobAddress" />
												</label>
											</section>
											<section class="col col-lg-6">
												<label class="input"> <i
													style="float: right; color: #4e7a8c; font-size: 20px; cursor: pointer"
													fieldName="<spring:message code="JobDetails.NewJobCreation.PlaceHolder.JobAddress" />"
													class="fa fa-plus-circle clickTiHide" id="jobAddress"></i>
												</label>
											</section>
										</div>
										<div class="row" id="addressErr" style="display: none;">
										<section class="col col-lg-3"></section>
											<section class="col col-lg-9">
												<label class="input" style="margin: 3px; font-size: 11px;color:#D56161">
													<spring:message
														code="JobDetails.NewJobCreation.PlaceHolder.JobAddressError" />
												</label>
											</section>
										</div>

										<section id="addressInfo" style="display: none;">
											<input type="hidden" id="addressFormId" name="addressFormId">
											<div class="well" style="padding: 10px">
												<section>
													<div class="row">
														<div class="col col-lg-12">
															<label class="input"> <i
																class="icon-append fa fa-map-marker mandatoryStyle"></i> <input
																type="text" name="address_line_1" id="address_line_1"
																placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>">
																<b class="tooltip tooltip-bottom-right"><spring:message
																		code="Address.AddressLine1.Tooltip" /></b>
															</label>
															<em id="addressLine1Err" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressLine1Err" />
															</em>
														</div>
													</div>
												</section>
												<section>
													<div class="row">
														<div class="col col-lg-12">
															<label class="input"> <i
																class="icon-append fa fa-map-marker"></i> <input
																type="text" name="address_line_2" id="address_line_2"
																placeholder="<spring:message code="Address.AddressLine2.PlaceHolder"/>">
																<b class="tooltip tooltip-bottom-right"><spring:message
																		code="Address.AddressLine2.Tooltip" /></b>
															</label>
														</div>
													</div>
												</section>
												<section>
													<div class="row">
														<div class="col col-lg-6">
															<label class="select"><select
																class="form-control show-tick" id="state" name="state">
																	<option value=""><spring:message
																			code="Address.State.PlaceHolder" /></option>
															</select> <i class="mandatoryIconStyle"></i></label>
															<em id="addressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
														</div>
														<div class="col col-lg-6">
															<label class="select"><select
																class="form-control show-tick" id="city" name="city">
																	<option value=""><spring:message
																			code="Address.City.PlaceHolder" /></option>
															</select> <i class="mandatoryIconStyle"></i></label>
															<em id="addressCityErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressCityErr" />
															</em>
														</div>
													</div>
												</section>
												<section>
													<div class="row">
														<div class="col col-lg-6">
															<label class="input"> <i
																class="icon-append fa fa-sort-numeric-desc mandatoryStyle"></i> <input
																type="text" name="zipCode" id="zipCode"
																placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>">
																<b class="tooltip tooltip-bottom-right"><spring:message
																		code="Address.ZipCode.Tooltip" /></b>
															</label>
															<em id="addressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
														</div>
														<div class="col col-lg-6">
															<button type="button" title="Save" id="addressSaveButton"
																onclick="saveAddressForm();"
																class="btn btn-primary btn-sm ">
																<i class="fa fa-floppy-o"></i>&nbsp;
																<spring:message code="Button.Save" />
															</button>
															<button type="button" title="Update"
																id="addressUpdateButton" onclick="updateAddressForm();"
																class="btn btn-primary btn-sm " style="display: none">
																<i class="fa fa-thumbs-up"></i>&nbsp;
																<spring:message code="Button.Update" />
															</button>
															<button type="button" title="Cancel"
																id="addressCancelButton" onclick="resetAddressForm();"
																class="btn btn-default btn-sm editable-cancel">
																<spring:message code="Button.Cancel" />
															</button>
														</div>
													</div>
												</section>
											</div>
										</section>
										<div id="addressDetails" class="row" style="display: none;"></div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.text.ProjectType" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="select"> <select
													fieldName="<spring:message code="Jobs.Information.text.ProjectType" />"
													class="form-control  show-tick informationRoleHighlights"
													id="projectTypeBean" name="projectTypeBean">
														<option value=""><spring:message
																code="Jobs.Information.label.ProjectType" /></option>

												</select> <i></i>
												</label>
											</section>
										</div>

										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Financials.label.BidPrice" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="input"><i class="icon-prepend mandatoryStyle fa">$</i>
													<input type="text" name="bidPrice" id="bidPrice"
													class="numbers informationRoleHighlights"
													fieldName="<spring:message code="Jobs.Financials.label.BidPrice" />"
													placeholder="<spring:message code="Jobs.Financials.label.BidPrice"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Financials.tooltip.BidPrice" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.text.Department" />
												</label>
											</section>
											<section class="col col-lg-9 ">
												<label class="select"> <select
													class="form-control  show-tick informationRoleHighlights"
													id="departmentType"
													fieldName="<spring:message code="Jobs.Information.text.Department" />"
													name="departmentType">

														<option value="">
															<spring:message
																code="Jobs.Information.label.ChooseDepartment" />
														</option>

												</select> <i class="mandatoryIconStyle"></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.text.Vendor" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="select"> <select
													class="form-control  show-tick informationRoleHighlights"
													id="vendorList"
													fieldName="<spring:message code="Jobs.Information.text.Vendor" />"
													name="vendorList">
														<option value=""><spring:message
																code="Jobs.Information.label.ChooseVendor" /></option>

												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.text.Contractor" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="select"> <select
													class="form-control  show-tick informationRoleHighlights"
													id="contractorDirectory"
													fieldName="<spring:message code="Jobs.Information.text.Contractor" />"
													name="contractorDirectory">
														<option value=""><spring:message
																code="Jobs.Information.label.ChooseContractor" /></option>

												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.text.SovType" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="select"> <select
													fieldName="<spring:message code="Jobs.Information.text.SovType" />"
													class="form-control  show-tick informationRoleHighlights"
													id="sovType" name="sovType">
														<option value=""><spring:message
																code="JobDetails.NewJobCreation.SOVSelectOption.Default" /></option>
														<option value="Unit"><spring:message
																code="JobDetails.NewJobCreation.SOVSelectOption.Unit" /></option>
														<option value="Milestone"><spring:message
																code="JobDetails.NewJobCreation.SOVSelectOption.Milestone" /></option>
														<option value="Percentage"><spring:message
																code="JobDetails.NewJobCreation.SOVSelectOption.Percentage" /></option>
														<option value="Hybrid"><spring:message
																code="JobDetails.NewJobCreation.SOVSelectOption.Hybrid" /></option>
												</select><i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.label.StartDate" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													fieldName="<spring:message code="Jobs.Information.label.StartDate" />"
													class="form-control informationRoleHighlights datepickerStyle"
													name="startDate" id="startDate" readonly
													placeholder="<spring:message code="Jobs.Information.label.StartDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.StartDate" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.label.EndDate" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label id="endDateLb" class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													fieldName="<spring:message code="Jobs.Information.label.EndDate" />"
													class="form-control informationRoleHighlights datepickerStyle"
													name="endDate" id="endDate" readonly
													placeholder="<spring:message code="Jobs.Information.label.EndDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.EndDate" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.label.CODate" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label id="coDateLb" class="input pointerEvent"> <i
													class="icon-append fa fa-calendar"></i> <input type="text" tabindex="-1"
													fieldName="<spring:message code="Jobs.Information.label.CODate" />"
													class="form-control informationRoleHighlights datepickerStyle"
													name="coDate" id="coDate"
													placeholder="<spring:message code="Jobs.Information.label.CODate"/>"
													> <b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.CODate" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Information.label.ExtendedDate" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label id="extendedDateLb" class="input pointerEvent"> <i
													class="icon-append fa fa-calendar"></i> <input type="text" tabindex="-1"
													fieldName="<spring:message code="Jobs.Information.label.ExtendedDate" />"
													class="form-control informationRoleHighlights datepickerStyle"
													name="extendedDate" id="extendedDate" 
													placeholder="<spring:message code="Jobs.Information.label.ExtendedDate"/>"
													> <b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.ExtendedDate" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="JobDetails.NewJobCreation.Placeholder.ArchitectId" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="input"><i
													class="icon-append fa  fa-sort-numeric-desc"></i><input
													type="text"
													fieldName="<spring:message code="JobDetails.NewJobCreation.Placeholder.ArchitectId" />"
													class="form-control informationRoleHighlights"
													name="architectId" id="architectId"
													placeholder="<spring:message code="JobDetails.NewJobCreation.Placeholder.ArchitectId"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="JobDetails.NewJobCreation.Tooltip.ArchitectId" /></b> </label>
											</section>

										</div>
										<div class="row">
											<section class="col col-lg-3">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="JobDetails.NewJobCreation.Placeholder.Description" />
												</label>
											</section>
											<section class="col col-lg-9">
												<label class="textarea textarea-resizable"> <i
													class="icon-append fa fa-comment"></i> <textarea
														fieldName="<spring:message code="JobDetails.NewJobCreation.Placeholder.Description" />"
														class="form-control informationRoleHighlights" rows="3"
														name="description" id="description"
														placeholder="<spring:message code="JobDetails.NewJobCreation.Placeholder.Description"/>"></textarea>
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="JobDetails.NewJobCreation.Tooltip.Description" /></b> <font
													color="red" size="1px"><p align="right">
															<spring:message
																code="Jobs.Information.label.Description.limit" />
														</p></font><br>
												</label>
											</section>
										</div>
									</fieldset>
								</div>
								<div class="tab-pane" id="personnel">
									<fieldset class="custom-scroll"
										style="height: 470px; overflow-y: scroll; overflow-x: hidden;">
										<div class="row">
											<hr>
											<label class="input"
												style="color: green; text-align: center;">
												Operations </label>
											<hr>
											<br>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.Executive" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													class="form-control show-tick personnelRoleHighlights"
													fieldName="<spring:message code="Jobs.Personnel.label.Executive" />"
													id="executive" name="executive">
														<option value=""><spring:message
																code="Jobs.Information.label.ChooseExecutive" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.Supervisor" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													fieldName="<spring:message code="Jobs.Personnel.label.Supervisor" />"
													class="form-control show-tick personnelRoleHighlights"
													id="supervisor" name="supervisor">
														<option value=""><spring:message
																code="Jobs.Information.label.ChooseSupervisor" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>

										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.Manager" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													class="form-control show-tick personnelRoleHighlights"
													id="manager"
													fieldName="<spring:message code="Jobs.Personnel.label.Manager" />"
													name="manager">
														<option value=""><spring:message
																code="Jobs.Information.label.ChooseManager" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.Estimator" />
												</label>
											</section>
											<section class="col col-8">
												<label class="input"> <i
													class="icon-append fa fa-user"></i> <input type="text"
													class="form-control personnelRoleHighlights"
													name="estimator" id="estimator"
													fieldName="<spring:message code="Jobs.Personnel.label.Estimator" />"
													placeholder="<spring:message code="Jobs.Personnel.label.Estimator"/>"
													readonly> <b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Personnel.validation.ChooseEstimator" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.Superintendent" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													class="form-control show-tick personnelRoleHighlights"
													fieldName="<spring:message code="Jobs.Personnel.label.Superintendent" />"
													id="superindent" name="superindent">
														<option value=""><spring:message
																code="Jobs.Personnel.label.ChooseSuperintendent" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.Foreman" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													class="form-control show-tick personnelRoleHighlights"
													id="foreman"
													fieldName="<spring:message code="Jobs.Personnel.label.Foreman" />"
													name="foreman">
														<option value=""><spring:message
																code="Jobs.Information.label.ChooseForeman" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Personnel.label.WarehouseManager" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													class="form-control show-tick personnelRoleHighlights"
													fieldName="<spring:message code="Jobs.Personnel.label.WarehouseManager" />"
													id="warehouseManager" name="warehouseManager">
														<option value=""><spring:message
																code="Jobs.Personnel.label.ChooseWarehouseManager" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<hr>
											<label class="input"
												style="color: green; text-align: center;"> Finance </label>
											<hr>
											<br>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.Accountant" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													class="form-control show-tick personnelRoleHighlights"
													fieldName="<spring:message code="Jobs.Personnel.label.Accountant" />"
													id="accountant" name="accountant">
														<option value=""><spring:message
																code="Jobs.Personnel.label.ChooseAccountant" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Personnel.label.PurchasingAgent" />
												</label>
											</section>
											<section class="col col-8">
												<label class="select"> <select
													class="form-control show-tick personnelRoleHighlights"
													fieldName="<spring:message code="Jobs.Personnel.label.PurchasingAgent" />"
													id="purchasingAgent" name="purchasingAgent">
														<option value=""><spring:message
																code="Jobs.Information.label.ChoosePurchasingAgent" /></option>
												</select> <i></i>
												</label>
											</section>
										</div>
									</fieldset>
								</div>
								<div class="tab-pane" id="legal">
									<fieldset class="custom-scroll"
										style="height: 470px; overflow-y: scroll; overflow-x: hidden;">
										<div class="row">
											<section class="col col-8">
												<label class="input " style="margin: 3px; font-size: 14px;">
													<spring:message code="Jobs.Legal.label.ContractDocuments" />
												</label>
											</section>
											<section class="col col-4">
												<button type="button" class="btn btn-primary"
													id="downloadDocumentsId" title="Download All Documents"
													style="margin-left: 30px;" disabled>
													<i class="fa fa-cloud-download" style="margin-right: -5px;"></i>
												</button>
											</section>


										</div>
										<div class="row documents hide">
											<section class="col col-lg-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.NoticetoProceed" />
												</label>
											</section>
											<section class="col col-lg-4">
												<label class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													class="form-control datepickerStyle" name="noticeReceivedDate"
													id="noticeReceivedDate"
													placeholder="<spring:message code="Jobs.Legal.label.NoticeDateReceived"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.ExtendedDate" /></b>
												</label>
											</section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file" 
															fieldName="<spring:message code="Jobs.Legal.label.NoticetoProceed" />"
															class="legalRoleHighlights" name="noticeToProceed"
															id="noticeToProceed" multiple disabled> <span
															class="fa-stack noticeToProceedIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="noticeToProceedLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide noticeToProceedFileLists "></div>
										<div class="row" id="noticeErr" style="display: none;">
										<section class="col col-lg-6"></section>
											<section class="col col-lg-6">
												<em style="font-style: normal;font-size: 11px;color:#D56161;">
														<spring:message code="JobDetails.NewJobCreation.PlaceHolder.UploadDocuments" />
												</em>
											</section>
										</div>
										<div class="row documents hide">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.ExecutedContract" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.ExecutedContract" />"
															class="legalRoleHighlights" name="executedContract"
															id="executedContract" multiple disabled> <span
															class="fa-stack executedContractIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="exectedContractLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide exectedContractFileLists "></div>
										<div class="row" id="exectedContractErr" style="display: none;">
										<section class="col col-lg-6"></section>
											<section class="col col-lg-6">
												<em style="font-style: normal;font-size: 11px;color:#D56161;">
														<spring:message code="JobDetails.NewJobCreation.PlaceHolder.UploadDocuments" />
												</em>
											</section>
										</div>
										<div class="row documents hide">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.RFI" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.RFI" />"
															class="legalRoleHighlights" name="rfi" id="rfi" multiple disabled>
														<span class="fa-stack rfiIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right" id="rfiLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide rfiFileLists "></div>
										<div class="row documents hide">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.Submittals" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.Submittals" />"
															class="legalRoleHighlights" name="submittals"
															id="submittals" multiple disabled> <span
															class="fa-stack submittalsIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="submittalsLists" style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide submittalsFileLists"></div>
										<div class="row documents hide">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.DrawingLog" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.DrawingLog" />"
															class="legalRoleHighlights" name="drawingLog"
															id="drawingLog" multiple disabled> <span
															class="fa-stack drawingLogIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="drawingLogLists" style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide drawingLogFileLists"></div>
										<div class="row documents hide">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.legal.label.Communications" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.legal.label.Communications" />"
															class="legalRoleHighlights" name="communications"
															id="communications" multiple disabled> <span
															class="fa-stack communicationsIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="communicationsLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide communicationsFileLists"></div>
										<div class="row documents hide">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.TransmittalsSheets" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.TransmittalsSheets" />"
															class="legalRoleHighlights" name="transmittalSheets"
															id="transmittalSheets" multiple disabled> <span
															class="fa-stack transmittalSheetsIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="transmittalsSheetsLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide transmittalsSheetsFileLists"></div>
										<div class="row documents hide">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.BidDocuments" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.BidDocuments" />"
															class="legalRoleHighlights" name="bidCommunications"
															id="bidCommunications" multiple disabled> <span
															class="fa-stack bidCommunicationsIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="bidDocumentsLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide bidDocumentsFileLists"></div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Legal.label.InsuranceCertificate" />
												</label>
											</section>
											<section class="col col-lg-4">
												<label class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													class="form-control datepickerStyle" name="insuranceSentDate"
													id="insuranceSentDate"
													placeholder="<spring:message code="Jobs.Legal.label.SentDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.ExtendedDate" /></b>
												</label>
											</section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.InsuranceCertificate" />"
															class="legalRoleHighlights" name="insuranceCertificate"
															id="insuranceCertificate" disabled> <span
															class="fa-stack insuranceCertificateIcon"> <i
															class="fa fa-upload fa-stack-2x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="insuranceCertificateLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide insuranceCertificateFileLists"></div>
										<div class="row" id="insuranceErr" style="display: none;">
										<section class="col col-lg-6"></section>
											<section class="col col-lg-6">
												<em style="font-style: normal;font-size: 11px;color:#D56161;">
														<spring:message code="JobDetails.NewJobCreation.PlaceHolder.UploadDocuments" />
												</em>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Legal.label.PerformanceOrPaymentBond" />
												</label>
											</section>
											<section class="col col-lg-4">
												<label class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													class="form-control datepickerStyle" name="performanceSentDate"
													id="performanceSentDate"
													placeholder="<spring:message code="Jobs.Legal.label.SentDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.ExtendedDate" /></b>
												</label>
											</section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.PerformanceOrPaymentBond" />"
															class="legalRoleHighlights" name="paymentBond"
															id="paymentBond" disabled> <span
															class="fa-stack paymentBondIcon"> <i
															class="fa fa-upload fa-stack-2x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="paymentBondLists" style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide paymentBondFileLists"></div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Legal.label.CaliforniaPreliminaryNotice" />
												</label>
											</section>
											<section class="col col-lg-4">
												<label class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													class="form-control datepickerStyle" name="cpnSentDate" id="cpnSentDate"
													placeholder="<spring:message code="Jobs.Legal.label.SentDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Information.tooltip.ExtendedDate" /></b>
												</label>
											</section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.CaliforniaPreliminaryNotice" />"
															class="legalRoleHighlights"
															name="californiaPreliminaryNotice"
															id="californiaPreliminaryNotice" disabled> <span
															class="fa-stack californiaPreliminaryNoticeIcon">
															<i class="fa fa-upload fa-stack-2x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="californiaPreliminaryNoticeLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide californiaPreliminaryNoticeFileLists">

										</div>
										<div class="row" id="cpnErr" style="display: none;">
										<section class="col col-lg-6"></section>
											<section class="col col-lg-6">
												<em style="font-style: normal;font-size: 11px;color:#D56161;">
														<spring:message code="JobDetails.NewJobCreation.PlaceHolder.UploadDocuments" />
												</em>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-6">
												<label class="checkbox"> <input type="checkbox"
													name="ownerControlledInsuranceProg"
													id="ownerControlledInsuranceProg"> <i></i>
												<spring:message
														code="Jobs.Legal.label.OwnerControlledInsuranceProgram" />
												</label>
											</section>
											<section class="col col-lg-4">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.OwnerControlledInsuranceProgram" />"
															class="legalRoleHighlights"
															name="ownerControlledInsuranceProgDocuments"
															id="ownerControlledInsuranceProgDocuments" disabled>
														<span
															class="fa-stack ownerControlledInsuranceProgDocumentsIcon">
															<i class="fa fa-upload fa-stack-2x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="ownerControlledInsuranceProgDocumentsLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div
											class="row hide ownerControlledInsuranceProgDocumentsFileLists">

										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.OtherDocuments" />
												</label>
											</section>
											<section class="col col-4"></section>
											<section class="col col-lg-2">
												<label for="jobTemplateFileLabel" class="input input-file" title="Can not upload Documents before saving job">
													<div class="button" style="height: 25px;">
														<input type="file"
															fieldName="<spring:message code="Jobs.Legal.label.OtherDocuments" />"
															class="legalRoleHighlights" name="otherDocuments"
															id="otherDocuments" multiple disabled> <span
															class="fa-stack otherDocumentsIcon"> <i
															class="fa fa-upload fa-stack-2x"></i> <i
															class="fa fa-arrow-up fa-stack-1x"></i>
														</span>
													</div>
												</label>
											</section>
											<section class="col col-lg-2">
												<i class="fa fa-plus-square fa-4 pull-right"
													id="otherDocumentsLists"
													style="cursor: pointer; margin: 7px;"></i>
											</section>
										</div>
										<div class="row hide otherDocumentsFileLists"></div>
										<div class="row">
											<section class="col col-lg-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.TypeOfContract" />
												</label>
											</section>
											<section class="col col-lg-4">
												<label class="select"> <select
													class="form-control  show-tick legalRoleHighlights"
													fieldName="<spring:message code="Jobs.Legal.label.TypeOfContract" />"
													id="typeOfContract" name="typeOfContract">
														<option value=""><spring:message
																code="Jobs.Legal.label.select.TypeOfContract" /></option>
														<option value="Lumpsum"><spring:message
																code="Jobs.Legal.label.select.Lumpsum" /></option>
														<option value="TME"><spring:message
																code="Jobs.Legal.label.select.TME" /></option>
														<option value="CostPlus"><spring:message
																code="Jobs.Legal.label.select.CostPlus" /></option>
														<option value="Other"><spring:message
																code="Jobs.Legal.label.select.Other" /></option>
												</select><i></i>
												</label>
											</section>
											<section class="col col-lg-4">
												<label class="input"> <i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" class="form-control "
													name="otherTypeOfContract" id="otherTypeOfContract"
													placeholder="<spring:message code="Jobs.Legal.label.TypeOfContract"/>"
													disabled style="background: #F2F2F2"> <b
													class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Legal.label.tooltip.TypeOfContract" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.ContractAmount" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa  fa-sort-numeric-desc"></i> <input
													fieldName="<spring:message code="Jobs.Legal.label.ContractAmount" />"
													type="text" name="contractAmount" id="contractAmount"
													class="numbers legalRoleHighlights"
													placeholder="<spring:message code="Jobs.Legal.label.ContractAmount"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Legal.tootip.ContractAmount" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Legal.label.ContractNumber" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa  fa-sort-numeric-desc"></i> <input
													fieldName="<spring:message code="Jobs.Legal.label.ContractNumber" />"
													type="text" name="contractNumber" id="contractNumber"
													class="legalRoleHighlights"
													placeholder="<spring:message code="Jobs.Legal.label.ContractNumber"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Legal.tootip.ContractNumber" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-12">
												<label class="checkbox"> <input type="checkbox"
													fieldName="<spring:message code="Jobs.Legal.label.CertifiedPayroll" />"
													class="legalRoleHighlights" name="isCertifiedPayroll"
													id="isCertifiedPayroll"> <i></i>
												<spring:message code="Jobs.Legal.label.CertifiedPayroll" />
												</label>
											</section>
										</div>
									</fieldset>
								</div>
								<div class="tab-pane" id="settings">
									<fieldset class="custom-scroll"
										style="height: 470px; overflow-y: scroll; overflow-x: hidden;">
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Settings.text.CollectionDayout" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa  fa-sort-numeric-desc"></i> <input
													fieldName="<spring:message code="Jobs.Settings.text.CollectionDayout" />"
													type="text" name="collectionDayOut" id="collectionDayOut"
													class="settingsRoleHighlights"
													placeholder="<spring:message code="Jobs.Settings.label.CollectionDayout"/>"
													value="<spring:message code="Jobs.Settings.value.CollectionDayout"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.CollectionDayout" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Settings.text.RetentionDayout" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa  fa-sort-numeric-desc"></i> <input
													fieldName="<spring:message code="Jobs.Settings.text.RetentionDayout" />"
													type="text" name="retentionDayOut" id="retentionDayOut"
													class="settingsRoleHighlights"
													placeholder="<spring:message code="Jobs.Settings.label.RetentionDayout"/>"
													value="<spring:message code="Jobs.Settings.value.RetentionDayout"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.RetentionDayout" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Settings.text.Retention" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i class="icon-append fa">%</i>
													<input type="text" name="retentionPercent"
													id="retentionPercent" class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.text.Retention" />"
													placeholder="<spring:message code="Jobs.Settings.label.Retention"/>"
													value="<spring:message code="Jobs.Settings.value.Retention"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.Retention" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.text.MaterialDaysoutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													fieldName="<spring:message code="Jobs.Settings.text.MaterialDaysoutInMonths" />"
													type="text" name="materialDayOut" id="materialDayOut"
													class="settingsRoleHighlights"
													placeholder="<spring:message code="Jobs.Settings.label.MaterialDaysoutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.MaterialDaysoutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.MaterialDaysoutInMonths" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.text.SubcontractorDaysoutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="subcontractorDayOut"
													id="subcontractorDayOut" class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.text.SubcontractorDaysoutInMonths" />"
													placeholder="<spring:message code="Jobs.Settings.label.SubcontractorDaysoutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.SubcontractorDaysoutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.SubcontractorDaysoutInMonths" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.text.DirectJobDaysOutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="directJobDayOut" id="directJobDayOut"
													class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.text.DirectJobDaysOutInMonths" />"
													placeholder="<spring:message code="Jobs.Settings.label.DirectJobDaysOutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.DirectJobDaysOutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.DirectJobDaysOutInMonths" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.text.RentalEquipmentsDaysOutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="rentalEquipmentDayOut"
													id="rentalEquipmentDayOut" class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.text.RentalEquipmentsDaysOutInMonths" />"
													placeholder="<spring:message code="Jobs.Settings.label.RentalEquipmentsDaysOutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.RentalEquipmentsDaysOutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.RentalEquipmentsDaysOutInMonths" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.text.EnterOwnedEquipmentDaysOutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="ownedEquipmentDayOut"
													id="ownedEquipmentDayOut" class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.text.EnterOwnedEquipmentDaysOutInMonths" />"
													placeholder="<spring:message code="Jobs.Settings.label.EnterOwnedEquipmentDaysOutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.EnterOwnedEquipmentDaysOutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.EnterOwnedEquipmentDaysOutInMonths" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.text.ProjectAdminDaysOutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="projAdminDayOut" id="projAdminDayOut"
													class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.text.ProjectAdminDaysOutInMonths" />"
													placeholder="<spring:message code="Jobs.Settings.label.ProjectAdminDaysOutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.ProjectAdminDaysOutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.ProjectAdminDaysOutInMonths" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.text.LaborDaysOutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="laborDayOut" id="laborDayOut"
													class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.text.LaborDaysOutInMonths" />"
													placeholder="<spring:message code="Jobs.Settings.label.LaborDaysOutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.LaborDaysOutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.LaborDaysOutInMonths" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Settings.label.IndirectDaysOutInMonths" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa  fa-sort-numeric-desc"></i> <input
													type="text" name="indirectDayOut" id="indirectDayOut"
													class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Settings.label.IndirectDaysOutInMonths" />"
													placeholder="<spring:message code="Jobs.Settings.label.IndirectDaysOutInMonths"/>"
													value="<spring:message code="Jobs.Settings.value.IndirectDaysOutInMonths"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Settings.tooltip.IndirectDaysOutInMonths" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="JobDetails.NewJobCreation.text.ActivationPeriod" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa  fa-sort-numeric-desc"></i> <input
													type="text" name="activationValidityTimePeriod"
													id="activationValidityTimePeriod"
													class="settingsRoleHighlights"
													fieldName="<spring:message code="JobDetails.NewJobCreation.text.ActivationPeriod" />"
													placeholder="<spring:message code="JobDetails.NewJobCreation.Placeholder.ActivationPeriod"/>"
													value=30> <b class="tooltip tooltip-bottom-right"><spring:message
															code="JobDetails.NewJobCreation.Tooltip.ActivationPeriod" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.Financials.text.ReportMargin" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i class="icon-append fa">%</i>
													<input type="text" name="reportMargin" id="reportMargin"
													class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Financials.text.ReportMargin" />"
													placeholder="<spring:message code="Jobs.Financials.label.ReportMargin"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Financials.tooltip.ReportMargin" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Financials.text.PerformanceTargetMargin" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i class="icon-append fa">%</i>
													<input type="text" name="performanceTargetMargin"
													id="performanceTargetMargin" class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Financials.text.PerformanceTargetMargin" />"
													placeholder="<spring:message code="Jobs.Financials.label.PerformanceTargetMargin"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Financials.tooltip.PerformanceTargetMargin" /></b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message
														code="Jobs.Financials.text.LabourBonusTarget" />
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i class="icon-append fa">%</i>
													<input type="text" name="laborBonusTargetOverBudget"
													id="laborBonusTargetOverBudget"
													class="settingsRoleHighlights"
													fieldName="<spring:message code="Jobs.Financials.text.LabourBonusTarget" />"
													placeholder="<spring:message code="Jobs.Financials.label.LabourBonusTarget"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.Financials.tooltip.LabourBonusTarget" /></b> </label>
											</section>
										</div>
									</fieldset>
								</div>
								<div class="tab-pane" id="bidBudget">
									<fieldset class="custom-scroll"
										style="height: 470px; overflow-y: scroll; overflow-x: hidden;">
										<div class="row">
											<section class="col col-4"></section>
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">
													<spring:message code="Jobs.AddNewJob.Tab.BidBudget" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">
													<spring:message code="Jobs.AddNewJob.Tab.OperationsBudget" />
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.MaterialCost" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="bidBudgetMaterialCost"
													id="bidBudgetMaterialCost"
													class="numbers numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.MaterialCost" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.MaterialCost"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.MaterialCost" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="operationsBudgetMaterialCosts"
													id="operationsBudgetMaterialCosts"
													class="numbers numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.MaterialCost" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.MaterialCost"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.MaterialCost" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.SubContractors" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="bidBudgetSubcontractorsCost"
													id="bidBudgetSubcontractorsCost"
													class="numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.SubContractors" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.SubContractors"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.SubContractors" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="operationsSubcontractorsCosts"
													id="operationsSubcontractorsCosts"
													class="numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.SubContractors" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.SubContractors"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.SubContractors" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.Direct" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="bidBudgetDirectJobCosts"
													id="bidBudgetDirectJobCosts"
													class="numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.Direct" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.Direct"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.Direct" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="operationsBudgetDirectJobCost"
													id="operationsBudgetDirectJobCost"
													class="numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.Direct" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.Direct"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.Direct" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.Equipment" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="bidBudgetRentalEquipmentCosts"
													id="bidBudgetRentalEquipmentCosts"
													class="numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.Equipment" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.Equipment"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.Equipment" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text"
													name="operationsBudgetRentalEquipmentCost"
													id="operationsBudgetRentalEquipmentCost"
													class="numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.Equipment" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.Equipment"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.Equipment" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.OwnedEquipment" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="bidBudgetOwnedEquipmentsCosts"
													id="bidBudgetOwnedEquipmentsCosts"
													class="numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.OwnedEquipment" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.OwnedEquipment"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.OwnedEquipment" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="operationsOwnedEquipmentCost"
													id="operationsOwnedEquipmentCost"
													class="numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.OwnedEquipment" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.OwnedEquipment"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.OwnedEquipment" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.ProjectAdmin" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text"
													name="bidBudgetProjectAdministrationCost"
													id="bidBudgetProjectAdministrationCost"
													class="numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.ProjectAdmin" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.ProjectAdmin"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.ProjectAdmin" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text"
													name="operationsProjectAdministrationCost"
													id="operationsProjectAdministrationCost"
													class="numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.ProjectAdmin" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.ProjectAdmin"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.ProjectAdmin" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.LaborCost" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="bidBudgetLaborCost"
													id="bidBudgetLaborCost"
													class="numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.LaborCost" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.LaborCost"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.LaborCost" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name=operationsBudgetLaborCost
													id="operationsBudgetLaborCost"
													class="numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.LaborCost" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.LaborCost"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.LaborCost" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-4">
												<label class="input" style="margin: 3px; font-size: 14px;">

													<spring:message code="Jobs.BidBudget.label.IndirectCost" />
												</label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="bidBudgetIndirectExpenses"
													id="bidBudgetIndirectExpenses"
													class="numbers budgetRoleHighlights"
													fieldName="Bid Budget <spring:message code="Jobs.BidBudget.label.IndirectCost" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.IndirectCost"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.IndirectCost" /></b> </label>
											</section>
											<section class="col col-4">
												<label class="input"><i class="icon-prepend fa">$</i>
													<input type="text" name="operationsIndirectExpenses"
													id="operationsIndirectExpenses"
													class="numbers budgetRoleHighlights"
													fieldName="Operations Budget <spring:message code="Jobs.BidBudget.label.IndirectCost" />"
													placeholder="<spring:message code="Jobs.BidBudget.label.IndirectCost"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Jobs.BidBudget.tootip.IndirectCost" /></b> </label>
											</section>
										</div>
									</fieldset>
								</div>
							</div>
							<footer>
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="resetJobDetailForm()">
									<spring:message code="Button.Cancel" />
								</button>
								<button type="button" class="btn btn-primary" id="reset"
									onclick="resetJobDetailForm()">
									<i class="fa fa-undo"></i> &nbsp;
									<spring:message code="Button.Reset" />
								</button>
								<button type="submit" class="btn btn-primary ladda-button"
									data-style="expand-right" id="saveAndContinueJobBtn"
									onclick="setAction('saveAndContinue');">
									<i class="fa fa-forward"></i> &nbsp;
									<spring:message code="Button.SaveContinue" />
								</button>
								<button type="submit" class="btn btn-primary ladda-button"
									data-style="expand-right" id="saveJobBtn"
									onclick="setAction('save');">
									<i class="fa fa-floppy-o"></i> &nbsp;
									<spring:message code="Button.Save" />
								</button>
							</footer>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END MODAL -->
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="customerRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetCustomerForm()">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title" id="modelTitleId">
						<i class="fa fa-users fa-fw txt-color-blue"></i>
						<spring:message code="CustomerDirectory.Form.AddNewCustomer" />
					</h4>
				</div>
				<div class="modal-body no-padding">

					<form id="customer_form" method="POST" class="smart-form">
						<input type="hidden" id="companyId" name="companyId"> <input
							type="hidden" id="typeOfAction" name="typeOfAction">
						<fieldset
							style="max-height: 500px; overflow-y: auto; overflow-x: hidden;">
							<section>
								<div class="row">
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-user"></i> <input type="text"
											name="companyName" id="companyName"
											placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.CompanyName"/>">
											<b class="tooltip tooltip-bottom-right"><spring:message
													code="CustomerDirectory.AddNewCustomer.tooltip.CompanyName" /></b>
										</label>
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-sort-alpha-asc"></i> <input
											type="text" name="companyNumber" id="companyNumber"
											placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.CompanyNumber"/>">
											<b class="tooltip tooltip-bottom-right"><spring:message
													code="CustomerDirectory.AddNewCustomer.tooltip.CompanyNumber" /></b>
										</label>
									</div>
								</div>
							</section>

							<section>
								<div class="row">
									<div class="col col-lg-6" style="font-weight: bold;">
										<spring:message
											code="CustomerDirectory.AddNewCustomer.label.OfficeAddress" />
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											style="float: right; color: #4e7a8c; font-size: 20px; cursor: pointer"
											class="fa fa-plus-circle custclickTiHide"></i>
										</label>
									</div>
								</div>
							</section>
							<div class="row" id="custaddressErr" style="display: none;">
											<section class="col col-lg-12">
												<label class="input" style="margin: 3px; font-size: 11px;color:#D56161">
													<spring:message
														code="JobDetails.NewJobCreation.PlaceHolder.JobAddressError" />
												</label>
											</section>
										</div>
							<section id="custaddressInfo" style="display: none;">
								<input type="hidden" id="custaddressFormId" name="custaddressFormId">
								<div class="well" style="padding: 10px">
									<section>
										<div class="row">
											<div class="col col-lg-12">
												<label class="input"> <i
													class="icon-append fa fa-map-marker"></i> <input
													type="text" name="custaddress_line_1" id="custaddress_line_1"
													placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.AddressLine1.Tooltip" /></b>
												</label>
												<em id="custaddressLine1Err" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
													<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressLine1Err" />
												</em>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-lg-12">
												<label class="input"> <i
													class="icon-append fa fa-map-marker"></i> <input
													type="text" name="custaddress_line_2" id="custaddress_line_2"
													placeholder="<spring:message code="Address.AddressLine2.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.AddressLine2.Tooltip" /></b>
												</label>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-lg-6">
												<label class="select"><select
													class="form-control show-tick" id="custstate" name="custstate">
														<option value=""><spring:message
																code="Address.State.PlaceHolder" /></option>
												</select> <i></i></label>
												<em id="custaddressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
											</div>
											<div class="col col-lg-6">
												<label class="select"><select
													class="form-control show-tick" id="custcity" name="custcity">
														<option value=""><spring:message
																code="Address.City.PlaceHolder" /></option>
												</select> <i></i></label>
												<em id="custaddressCityErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressCityErr" />
															</em>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-lg-6">
												<label class="input"> <i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="custzipCode" id="custzipCode"
													placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.ZipCode.Tooltip" /></b>
												</label>
												<em id="custaddressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
											</div>
											<div class="col col-lg-6">
												<button type="button" title="Save" id="custaddressSaveButton"
													onclick="custsaveAddressForm();"
													class="btn btn-primary btn-sm ">
													<i class="fa fa-floppy-o"></i>&nbsp;
													<spring:message code="Button.Save" />
												</button>
												<button type="button" title="Update"
													id="custaddressUpdateButton" onclick="custupdateAddressForm();"
													class="btn btn-primary btn-sm " style="display: none">
													<i class="fa fa-thumbs-up"></i>&nbsp;
													<spring:message code="Button.Update" />
												</button>
												<button type="button" title="Cancel"
													id="custaddressCancelButton" onclick="custresetAddressForm();"
													class="btn btn-default btn-sm custeditable-cancel">
													<spring:message code="Button.Cancel" />
												</button>
											</div>
										</div>
									</section>
								</div>
							</section>
							<div id="custaddressDetails" class="row" style="display: none;"></div>
							<section>
								<div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i
											class="icon-append fa fa-envelope"></i> <input type="email"
											name="companyEmail" id="companyEmail"
											placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.CompanyEmail"/>">
											<b class="tooltip tooltip-bottom-right"><spring:message
													code="CustomerDirectory.AddNewCustomer.tooltip.CompanyEmail" /></b>
										</label>
									</div>
								</div>
							</section>
							<div class="row">
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-phone"></i>
										<input type="tel" name="phoneNumber" id="phoneNumber"
										placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.PhoneNumber"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="CustomerDirectory.AddNewCustomer.tooltip.PhoneNumber" /></b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-fax"></i>
										<input type="tel" name="fax" id="fax"
										placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.FaxNumber"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="CustomerDirectory.AddNewCustomer.tooltip.FaxNumber" /></b>
									</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-user"></i><input
										type="text" name="representativeName" id="representativeName"
										placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.RepresentativeName"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="CustomerDirectory.AddNewCustomer.tooltip.RepresentativeName" /></b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"><i
										class="icon-append fa fa-mobile"></i> <input type="tel"
										name="representativePhone" id="representativePhone"
										placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.RepresentativePhone"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="CustomerDirectory.AddNewCustomer.tooltip.RepresentativePhone" /></b>
									</label>
								</section>
							</div>
							<section>
								<div class="row">
									<div class="col col-lg-6" style="font-weight: bold;">
										<spring:message
											code="CustomerDirectory.AddNewCustomer.label.RepresentativeAddress" />
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											style="float: right; color: #4e7a8c; font-size: 20px; cursor: pointer"
											class="fa fa-plus-circle custclickToHideRep"></i>
										</label>
									</div>
								</div>
							</section>


							<section id="custrepAddressInfo" style="display: none;">
								<input type="hidden" id="custrepAddressFormId"
									name="custrepAddressFormId">
								<div class="well" style="padding: 10px">
									<section>
										<div class="row">
											<div class="col col-lg-12">
												<label class="input"> <i
													class="icon-append fa fa-map-marker"></i> <input
													type="text" name="custrepAddress_line_1" id="custrepAddress_line_1"
													placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.AddressLine1.Tooltip" /></b>
												</label>
												<em id="custrepAddressLine1Err" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressLine1Err" />
															</em>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-lg-12">
												<label class="input"> <i
													class="icon-append fa fa-map-marker"></i> <input
													type="text" name="custrepAddress_line_2" id="custrepAddress_line_2"
													placeholder="<spring:message code="Address.AddressLine2.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.AddressLine2.Tooltip" /></b>
												</label>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-lg-6">
												<label class="select"><select
													class="form-control show-tick" id="custrepState" name="custrepState">
														<option value=""><spring:message
																code="Address.State.PlaceHolder" /></option>
												</select> <i></i></label>
												<em id="custrepAddressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
											</div>
											<div class="col col-lg-6">
												<label class="select"><select
													class="form-control show-tick" id="custrepCity" name="custrepCity">
														<option value=""><spring:message
																code="Address.City.PlaceHolder" /></option>
												</select> <i></i></label>
												<em id="custrepAddressCityErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressCityErr" />
															</em>
											</div>
										</div>
									</section>
									<section>
										<div class="row">
											<div class="col col-lg-6">
												<label class="input"> <i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="custrepZipCode" id="custrepZipCode"
													placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.ZipCode.Tooltip" /></b>
												</label>
												<em id="custrepAddressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
											</div>
											<div class="col col-lg-6">
												<button type="button" title="Save" id="custrepAddressSaveButton"
													onclick="custsaveRepAddressForm();"
													class="btn btn-primary btn-sm ">
													<i class="fa fa-floppy-o"></i>&nbsp;
													<spring:message code="Button.Save" />
												</button>
												<button type="button" title="Update"
													id="custrepAddressUpdateButton" onclick="custupdateRepAddressForm();"
													class="btn btn-primary btn-sm " style="display: none">
													<i class="fa fa-thumbs-up"></i>&nbsp;
													<spring:message code="Button.Update" />
												</button>
												<button type="button" title="Cancel"
													id="custaddressCancelButton" onclick="custresetRepAddressForm();"
													class="btn btn-default btn-sm custrepEditable-cancel">
													<spring:message code="Button.Cancel" />
												</button>
											</div>
										</div>
									</section>
								</div>
							</section>
								<div id="custrepAaddressDetails" class="row" style="display: none;"></div>
							<div class="row">
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-phone"></i>
										<input type="text" name="irs" id="irs"
										placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.IRS"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="CustomerDirectory.AddNewCustomer.tooltip.IRS" /></b> </label>
								</section>
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-fax"></i>
										<input type="text" name="businessType" id="businessType"
										placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.BusinessType"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="CustomerDirectory.AddNewCustomer.tooltip.BusinessType" /></b>
									</label>
								</section>
							</div>

						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="customerBackView()">Back</button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetCustomerForm()">
								<i class="fa fa-undo"></i> &nbsp;
								<spring:message code="Button.Reset" />
							</button>
							<button type="submit" class="btn btn-primary ladda-button"
								id="save" data-style="expand-right"
								onclick="setActionType(this.id,this);">
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

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="vendorRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetVendorForm()">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title" id="modalTitleId">
						<i class="fa fa-user fa-fw txt-color-blue"></i> Add New Vendor
					</h4>
				</div>
				<div class="modal-body no-padding">

					<form id="vendor_form" method="POST" class="smart-form">
						<input type="hidden" name="vendorTypeAction" id="vendorTypeAction"
							value="save"> <input type="hidden" name="vendorId"
							id="vendorId"> <input type="hidden" name="submitId"
							id="submitId"> <input type="hidden" name="status"
							id="status" value="PENDING">
						<fieldset style="max-height: 500px; overflow-y: auto; overflow-x: hidden;">
							<section>
								<div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i
											class="icon-append fa fa-map-marker"></i> <input type="text"
											name="vendorName" id="vendorName" placeholder="Vendor Name">
											<b class="tooltip tooltip-bottom-right">Enter the vendor
												name</b>
										</label>
									</div>
								</div>
							</section>
							<section>
								<div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i
											class="icon-append fa fa-map-marker"></i> <input type="text"
											name="vendorLegalName" id="vendorLegalName"
											placeholder="Vendor Legal Name"> <b
											class="tooltip tooltip-bottom-right">Enter the vendor
												legal name</b>
										</label>
									</div>
								</div>
							</section>
							<div class="row">
								<section class="col col-6">
									<label class="select"><select
										class="form-control selectpicker show-tick"
										id="vendorLegalStatus" name="vendorLegalStatus">
											<option value="">Select Status</option>
											<option value="Corporation">Corporation</option>
											<option value="Joint Venture">Joint Venture</option>
											<option value="Non Profit Organization">Non Profit
												Organization</option>
											<option value="Government Agency">Government Agency</option>
											<option value="State Owned">State Owned</option>
									</select> <i></i></label>
								</section>
								<section class="col col-6">
									<label class="select"> <select
										class="form-control selectpicker show-tick"
										id="vendorOwnership" name="vendorOwnership">
											<option value="">Select Ownership</option>
											<option value="Public">Public</option>
											<option value="Private">Private</option>
									</select> <i></i>
									</label>
								</section>
							</div>

							<section>
								<div class="row">
									<div class="col col-lg-6" style="font-weight: bold;">
										<spring:message
											code="CustomerDirectory.AddNewCustomer.label.OfficeAddress" />
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											style="float: right; color: #4e7a8c; font-size: 20px; cursor: pointer"
											class="fa fa-plus-circle venclickToHideOffice"></i>
										</label>
									</div>
								</div>
							</section>
							<div class="row" id="venaddressErr" style="display: none;">
											<section class="col col-lg-12">
												<label class="input" style="margin: 3px; font-size: 11px;color:#D56161">
													<spring:message
														code="JobDetails.NewJobCreation.PlaceHolder.JobAddressError" />
												</label>
											</section>
										</div>
							<section id="venaddressInfo" style="display: none;">
							<input type="hidden" id="venaddressFormId" name="venaddressFormId">
							<input type="hidden" id="venaddressActionType" name="venaddressActionType">
							<div class="well" style="padding: 10px">
								<section>
							      <div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i class="icon-append fa fa-map-marker"></i> 
										<input type="text" name="venaddress_line_1" id="venaddress_line_1"
										 placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.AddressLine1.Tooltip"/></b>
										</label>
										<em id="venaddressLine1Err" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
													<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressLine1Err" />
												</em>
									</div>
								</div>
							  </section>
								<section>
							      <div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i class="icon-append fa fa-map-marker"></i> 
										<input type="text" name="venaddress_line_2" id="venaddress_line_2"
										 placeholder="<spring:message code="Address.AddressLine2.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.AddressLine2.Tooltip"/></b>
										</label>
									</div>
								</div>
							  </section>
							   <section>
							      <div class="row">
							      <div class="col col-lg-6">
										<label class="select"><select
										class="form-control show-tick"
										id="venstate" name="venstate">
											<option value=""><spring:message code="Address.State.PlaceHolder"/></option>
									</select> <i></i></label>
									<em id="venaddressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<label class="select"><select
										class="form-control show-tick"
										id="vencity" name="vencity">
											<option value=""><spring:message code="Address.City.PlaceHolder"/></option>
									</select> <i></i></label>
									<em id="venaddressCityErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressCityErr" />
															</em>
									</div>
								</div>
							  </section>
							  <section>
							      <div class="row">
									<div class="col col-lg-6">
										<label class="input"> <i class="icon-append fa fa-sort-numeric-desc"></i> 
										<input type="text" name="venzipCode" id="venzipCode"
										 placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.ZipCode.Tooltip"/></b>
										</label>
										<em id="venaddressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<button type="button" title="Save" id="venaddressSaveButton"
										 onclick="vensaveAddressForm();" class="btn btn-primary btn-sm ">
											<i class="fa fa-floppy-o" ></i>&nbsp;
											<spring:message code="Button.Save"/>
										</button>
										<button type="button" title="Update" id="venaddressUpdateButton" 
										onclick="venupdateAddressForm();" class="btn btn-primary btn-sm " style="display:none">
										<i class="fa fa-thumbs-up" ></i>&nbsp;
										<spring:message code="Button.Update"/>
										</button>
										<button type="button" title="Cancel" id="venaddressCancelButton" 
										onclick="venresetAddressForm();" class="btn btn-default btn-sm veneditable-cancel">
										<spring:message code="Button.Cancel"/>
										 </button>
									</div>
								</div>
							  </section>
						</div>
							</section>
							<div id="venaddressDetails" class="row" style="display: none;"></div>
							<!-- <section>
								<div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i style="color: #4e7a8c;font-size:20px;cursor:pointer" title="Click to add adress"
											class="icon-append fa fa-plus-circle clickTiHide" title="Add address"></i>
											<input type="text"
											name="vendorMailingAddress" id="vendorMailingAddress"
											placeholder="Vendor Address(if different from above)"> <b
											class="tooltip tooltip-bottom-right">Enter the vendor
												address</b>
										</label>
									</div>
								</div>
							</section>			 -->
							
							<section>
								<div class="row">
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-user"></i> <input type="text"
											name="vendorPhoneNo" id="vendorPhoneNo"
											placeholder="Vendor Phone No"> <b
											class="tooltip tooltip-bottom-right">Enter the vendor
												phone</b>
										</label>
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-sort-alpha-asc"></i> <input
											type="text" name="vendorWebsite" id="vendorWebsite"
											placeholder="Vendor Website"> <b
											class="tooltip tooltip-bottom-right">Enter the vendor
												website</b>
										</label>
									</div>
								</div>
							</section>
							<section>
								<div class="row">
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-map-marker"></i> <input type="text"
											name="vendorEmail" id="vendorEmail"
											placeholder="Vendor Email"> <b
											class="tooltip tooltip-bottom-right">Enter the vendor
												email</b>
										</label>
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-sort-alpha-asc"></i> <input
											type="text" name="vendorFax" id="vendorFax"
											placeholder="Vendor Fax"> <b
											class="tooltip tooltip-bottom-right">Enter the vendor fax</b>
										</label>
									</div>
								</div>
							</section>

							<div class="row">
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-user"></i><input
										type="text" name="representativeName" id="representativeName"
										placeholder="Representative Name"> <b
										class="tooltip tooltip-bottom-right">Representative Name</b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"><i
										class="icon-append fa fa-mobile"></i> <input type="tel"
										name="representativePhone" id="representativePhone"
										placeholder="Representative Phone"> <b
										class="tooltip tooltip-bottom-right">Representative Phone</b>
									</label>
								</section>
							</div>
							
							<section>
								<div class="row">
									<div class="col col-lg-6" style="font-weight: bold;">
										<spring:message
											code="CustomerDirectory.AddNewCustomer.label.RepresentativeAddress" />
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											style="float: right; color: #4e7a8c; font-size: 20px; cursor: pointer"
											class="fa fa-plus-circle venclickToHideRep"></i>
										</label>
									</div>
								</div>
							</section>
							<section id="venrepAddressInfo" style="display: none;">
							<input type="hidden" id="venrepAddressId" name="venrepAddressId">
							<input type="hidden" id="venrepAddressActionType" name="venrepAddressActionType">
							<div class="well" style="padding: 10px">
								<section>
							      <div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i class="icon-append fa fa-map-marker"></i> 
										<input type="text" name="venrepAddress_line_1" id="venrepAddress_line_1"
										 placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.AddressLine1.Tooltip"/></b>
										</label>
										<em id="venrepAddressLine1Err" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressLine1Err" />
															</em>
									</div>
								</div>
							  </section>
								<section>
							      <div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i class="icon-append fa fa-map-marker"></i> 
										<input type="text" name="venrepAddress_line_2" id="venrepAddress_line_2"
										 placeholder="<spring:message code="Address.AddressLine2.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.AddressLine2.Tooltip"/></b>
										</label>
									</div>
								</div>
							  </section>
							   <section>
							      <div class="row">
							      <div class="col col-lg-6">
										<label class="select"><select
										class="form-control show-tick"
										id="venrepState" name="venrepState">
											<option value=""><spring:message code="Address.State.PlaceHolder"/></option>
									</select> <i></i></label>
									<em id="venrepAddressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<label class="select"><select
										class="form-control show-tick"
										id="venrepCity" name="venrepCity">
											<option value=""><spring:message code="Address.City.PlaceHolder"/></option>
									</select> <i></i></label>
									<em id="venrepAddressCityErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressCityErr" />
															</em>
									</div>
								</div>
							  </section>
							  <section>
							      <div class="row">
									<div class="col col-lg-6">
										<label class="input"> <i class="icon-append fa fa-sort-numeric-desc"></i> 
										<input type="text" name="venrepZipCode" id="venrepZipCode"
										 placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.ZipCode.Tooltip"/></b>
										</label>
										<em id="venrepAddressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<button type="button" title="Save" id="venrepAddressSaveButton" 
										onclick="vensaveRepAddressForm();" class="btn btn-primary btn-sm ">
											<i class="fa fa-floppy-o" ></i>&nbsp;
											<spring:message code="Button.Save"/>
										</button>
										<button type="button" title="Update" id="venrepAddressUpdateButton" 
										onclick="venupdateRepAddressForm();" class="btn btn-primary btn-sm " style="display:none">
										<i class="fa fa-thumbs-up" ></i>&nbsp;
										<spring:message code="Button.Update"/>
										</button>
										<button type="button" title="Cancel" id="venrepAddressCancelButton" 
										onclick="venresetRepAddressForm();" class="btn btn-default btn-sm venrepEditable-cancel">
										<spring:message code="Button.Cancel"/>
										 </button>
									</div>
								</div>
							  </section>
						</div>
							</section>
							<div id="venrepAaddressDetails" class="row" style="display: none;"></div>
							<div class="row">
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-phone"></i>
										<input type="text" name="irs" id="irs" placeholder="IRS">
										<b class="tooltip tooltip-bottom-right">IRS</b> </label>
								</section>
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-fax"></i>
										<input type="text" name="businessType" id="businessType"
										placeholder="Business Type"> <b
										class="tooltip tooltip-bottom-right">Business Type</b> </label>
								</section>
							</div>

						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="vendorBackView()">Back</button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetVendorForm()">
								<i class="fa fa-undo"></i> &nbsp;Reset
							</button>
							<button type="submit" class="btn btn-primary ladda-button"
								data-style="expand-right" id="save" data-style="expand-right"
								onclick="setActionType(this.id,this);">
								<i class="fa fa-floppy-o"></i> &nbsp; Save
							</button>
						</footer>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- END MODAL -->
</div>
<!-- MODAL FOR UPLOADING -->

<!-- for uploading documents for legal tab -->

<div style="display: none">
	<form id="attachmentsForm" class="smart-form">
		<fieldset>
			<section>
				<label for="jobTemplateFileLabel" class="input input-file">
					<div class="button">
						<input type="file" name="attachments" id="attachments">
					</div> <input type="text" name="attachmentsName" id="attachmentsName">
					<input type="text" name="attachmentsId" id="attachmentsId">
				</label>
			</section>
		</fieldset>
	</form>
</div>


<div class="modal fade" id="jobUploadModal" tabindex="-1" role="dialog"
	aria-labelledby="remoteModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="cancelJobUploadForm()">
					<span class="badge pull-right inbox-badge customColor">&times;</span>
				</button>
				<h4 class="modal-title" id="jobModalUploadTitleId">
					<i class="fa fa-users fa-fw txt-color-blue"></i>
					<spring:message code="JobDetails.AddJobModal.Heading" />
				</h4>
			</div>
			<div class="modal-body no-padding">
				<form id="jobUploadForm" method="POST" class="smart-form">
					<input type="hidden" id="confirmJobUploadId"
						name="confirmJobUploadId">
					<fieldset>
						<section>
							<label for="jobTemplateFileLabel" class="input input-file">
								<div class="button">
									<input type="file" name="jobTemplateFile" id="jobTemplateFile"
										onchange="setJobUploadFileName(this.value)"> <i
										class="fa fa-cloud-upload"></i>
								</div> <input type="text" name="jobFileName" id="jobFileName"
								placeholder="<spring:message code="JobDetails.AddJobModal.Placeholder" />"
								readonly="">
							</label>
						</section>
					</fieldset>
					<footer>
						<button type="submit" class="btn btn-primary button"
							id="saveJobUpload">
							<i class="fa fa-floppy-o"></i> &nbsp;
							<spring:message code="Button.Save" />
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="cancelJobUploadForm()">
							<spring:message code="Button.Cancel" />
						</button>
					</footer>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- END MODAL FOR UPLOADING -->

<!-- MODAL FOR UPLOAD CONFIRMATION -->

<!-- MODAL FOR CONFIRMATION -->

<div class="modal fade" id="jobUploadConfirmation" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="resetJobConfirmation()">
					<span class="badge pull-right inbox-badge customColor">&times;</span>
				</button>
				<h4 class="modal-title" id="jobConfirmModalTitleId">
					<i class="fa fa-users fa-fw txt-color-blue"></i> Error in the
					template File
				</h4>
			</div>
			<div class="modal-body no-padding">
				<fieldset>
					<section style="text-align: center;" id="jobConfirmationHeader">
						<div class="space"></div>
						<h4 class="smaller">
							<b>Error Occured while uploading</b>
						</h4>
						<ul class="list-unstyled spaced inline bigger-110 margin-15">
							<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select
								Continue to insert the error values as it is</li>
							<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select
								Cancel to cancel and upload again</li>
						</ul>
					</section>
					<section id="jobErrorSection" style="display: none;">
						<div class="alert alert-danger jobErrorHeader">
							<strong>Error Occured !</strong>
						</div>
						<div class="alert alert-danger fade in jobErrorBlock"
							id="jobErrorBlock"></div>
					</section>
				</fieldset>
				<footer>
					<button type="submit" class="btn btn-primary button"
						id="saveJobConfirmUpload"
						onclick="setJobUploadConfirmation(this.id)">
						<i class="fa fa-forward"></i> &nbsp;Continue
					</button>
					<button type="button" class="btn btn-default" id="cancelJobUpload"
						data-dismiss="modal"
						onclick="setJobUploadConfirmation(this.id);resetJobConfirmation();cancelJobUploadForm()">Cancel
					</button>
				</footer>
			</div>
		</div>
	</div>
</div>

<!-- END MODAL FOR UPLOAD CONFIRMATION -->

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

					&nbsp; <a href="" data-toggle="modal" data-target="#jobDetailModal"
						onclick="resetJobDetailForm();showDefaultHighlights();"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">
							<spring:message code="Jobs.Form.AddNewJob" />
						</h2>
					</a> <a href="" data-toggle="modal" data-target="#uploadModal" style="display:none" 
						onclick="setModuleName('<spring:message code="Upload.JobDetail"/>')">
						<span class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">
							<spring:message code="Option.UploadFile" />
						</h2>
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
					<div class="widget-body no-padding">
						<div class="alert alert-block alert-info">
						<h4><b style="font-size: 15px;"><spring:message code="Jobs.TableHead.Active"/></b></h4>
					</div>
						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="jobTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">

										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 19px;">#</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 77px;"><spring:message
												code="Jobs.Financials.label.BidNumber" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 77px;"><spring:message
												code="Jobs.Datatable.TableHead.Column1" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 102px;"><spring:message
												code="Jobs.Datatable.TableHead.Column2" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 75px;"><spring:message
												code="Jobs.Datatable.TableHead.Column3" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 63px;"></i> <spring:message
												code="Jobs.Datatable.TableHead.Column4" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 63px;"><spring:message
												code="Jobs.Datatable.TableHead.Column5" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 92px;"><spring:message
												code="Jobs.Datatable.TableHead.Column6" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 75px;"><spring:message
												code="Jobs.Datatable.TableHead.Column7" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 82px;"><spring:message
												code="Jobs.Datatable.TableHead.Column8" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 75px;"><spring:message
												code="Jobs.Datatable.TableHead.Column9" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 20px;"></th>
								</thead>
								<tbody>
									<!-- <tr>
										<td>1</td>
										<td>Accountant</td>
										<td>04-08-2014</td>
										<td>06-08-2014</td>
										<td>08-08-2014</td>
										<td>10-08-2014</td>
										<td>Jim Geblin</td>
										<td>GDesoto</td>
										<td>John Hahn</td>
									</tr>
									<tr>
										<td>2</td>
										<td>Foreman</td>
										<td>04-08-2014</td>
										<td>06-08-2014</td>
										<td>08-08-2014</td>
										<td>10-08-2014</td>
										<td>Jim Geblin</td>
										<td>GDesoto</td>
										<td>John Hahn</td>
									</tr>
									<tr>
										<td>3</td>
										<td>Manager</td>
										<td>14-08-2014</td>
										<td>16-08-2014</td>
										<td>18-08-2014</td>
										<td>20-08-2014</td>
										<td>Jim Geblin</td>
										<td>GDesoto</td>
										<td>John Hahn</td>
									</tr>
									<tr>
										<td>4</td>
										<td>Manager</td>
										<td>24-08-2014</td>
										<td>26-08-2014</td>
										<td>08-09-2014</td>
										<td>10-09-2014</td>
										<td>Jim Geblin</td>
										<td>GDesoto</td>
										<td>John Hahn</td>
									</tr> -->
								</tbody>
							</table>
						</div>
					</div>
					<!-- end widget content -->
				</div>
				<!-- end widget div -->
				<!-- widget div-->
				<div role="content">
					<br>
					<br>
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body no-padding">
						<div class="alert alert-block alert-info">
						<h4><b style="font-size: 15px;"><spring:message code="Jobs.TableHead.Pending"/></b></h4>
					</div>
						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="pendingjobTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">

										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 19px;">#</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 77px;"><spring:message
												code="Jobs.Financials.label.BidNumber" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 77px;"><spring:message
												code="Jobs.Datatable.TableHead.Column1" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 102px;"><spring:message
												code="Jobs.Datatable.TableHead.Column2" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 75px;"><spring:message
												code="Jobs.Datatable.TableHead.Column3" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 63px;"></i> <spring:message
												code="Jobs.Datatable.TableHead.Column4" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 63px;"><spring:message
												code="Jobs.Datatable.TableHead.Column5" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 92px;"><spring:message
												code="Jobs.Datatable.TableHead.Column6" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 75px;"><spring:message
												code="Jobs.Datatable.TableHead.Column7" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 82px;"><spring:message
												code="Jobs.Datatable.TableHead.Column8" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 75px;"><spring:message
												code="Jobs.Datatable.TableHead.Column9" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 20px;"></th>
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
</section>
<script type="text/javascript" src="assets/js/jobsDetail.js"></script>
<script>
	var projectTypeList = '${projectTypeList}';
	var vendorsList = '${vendorsList}';
	var departmentList = '${departmentList}';
	var customersList = '${customersList}';
	var contractorsList = '${contractorsList}';
	var roleBasedUserMap = '${roleBasedUserMap}';
	intializeProjectType(projectTypeList);
	intializevendorsList(vendorsList);
	intializedepartmentList(departmentList);
	intializecustomersList(customersList);
	intializecontractorsList(contractorsList);
	intializeroleBasedUserMap(roleBasedUserMap);
	$( document ).ready(function() {
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
				.getDate(), 0, 0, 0, 0);

		var checkin = $('#startDate').datepicker({
			format : "mm-dd-yyyy",
			/* onRender : function(date) {
				return date.valueOf() < now.valueOf() ? 'disabled' : '';
			} */
		}).on('changeDate', function(ev) {
			$('#endDate').datepicker('update');
			if (ev.date.valueOf() > checkout.date.valueOf()) {
				var newDate = new Date(ev.date)
				newDate.setDate(newDate.getDate() + 1);
				//checkout.setValue(newDate);
			}
			checkin.hide();
			$('#endDate')[0].focus();
		}).data('datepicker');
		
		var checkout = $('#endDate').datepicker({
			format : "mm-dd-yyyy",
			onRender : function(date) {
				return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
			if(!$('#extendedDateLb').hasClass('pointerEvent')){
				
				$('#coDate').datepicker('update');
				if (ev.date.valueOf() > coOut.date.valueOf()) {
					var newDate = new Date(ev.date)
					newDate.setDate(newDate.getDate() + 1);
					//coOut.setValue(newDate);
				}
				
				$('#extendedDate').datepicker('update');
				if (ev.date.valueOf() > extOut.date.valueOf()) {
					var newDate = new Date(ev.date)
					newDate.setDate(newDate.getDate() + 1);
					//extOut.setValue(newDate);
				};
				
			}
			checkout.hide();
		}).data('datepicker');	
		
		var coOut = $('#coDate').datepicker({
			format : "mm-dd-yyyy",
			onRender : function(date) {
				return date.valueOf() <= checkout.date.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
			if (ev.date.valueOf() > extOut.date.valueOf()) {
				var newDate = new Date(ev.date)
				newDate.setDate(newDate.getDate() + 1);
			};
			coOut.hide();
		}).data('datepicker');	
		
		var extOut = $('#extendedDate').datepicker({
			format : "mm-dd-yyyy",
			onRender : function(date) {
				return date.valueOf() <= checkout.date.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
			if (ev.date.valueOf() > coOut.date.valueOf()) {
				var newDate = new Date(ev.date)
				newDate.setDate(newDate.getDate() + 1);
			};
			extOut.hide();
		}).data('datepicker');	
		
	})
	
	
	$(".custclickTiHide").click(function() {
		$("#custaddressInfo").toggle();
		$(".custclickTiHide").toggleClass("fa-minus-circle");
	});

	$(".custeditable-cancel").click(function() {
		$("#custaddressInfo").toggle();
		$(".custclickTiHide").toggleClass("fa-minus-circle");
	});

	$(".custclickToHideRep").click(function() {
		$("#custrepAddressInfo").toggle();
		$(".custclickToHideRep").toggleClass("fa-minus-circle");
	});

	$(".custrepEditable-cancel").click(function() {
		$("#custrepAddressInfo").toggle();
		$(".custclickToHideRep").toggleClass("fa-minus-circle");
	});
	$(".venclickToHideOffice").click(function() {
		$("#venaddressInfo").toggle();
		$(".venclickToHideOffice").toggleClass("fa-minus-circle");
	});

	$(".veneditable-cancel").click(function() {
		$("#venaddressInfo").toggle();
		$(".venclickToHideOffice").toggleClass("fa-minus-circle");
	});

	$(".venclickToHideRep").click(function() {
		$("#venrepAddressInfo").toggle();
		$(".venclickToHideRep").toggleClass("fa-minus-circle");
	});

	$(".venrepEditable-cancel").click(function() {
		$("#venrepAddressInfo").toggle();
		$(".venclickToHideRep").toggleClass("fa-minus-circle");
	});
	
</script>
<!-- <script type="text/javascript" src="assets/js/customerDirectory.js"></script>
 -->
<!-- end widget grid -->
<!-- END #MAIN CONTENT -->

