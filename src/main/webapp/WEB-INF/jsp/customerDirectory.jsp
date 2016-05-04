<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<style>
.custErrorBlock {
	box-shadow: 3px 5px 11px #836463;
	border-right: 5px solid #953b39;
	background: #fff;
	color: #836463;
	height: 160px;
	overflow-y: auto
}

.custErrorHeader {
	border-bottom: 1px solid #953b39;
	border-right: 5px solid #953b39;
	margin-bottom: 0px !important
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
<style>
.uploadBtnCustom {
	padding: 6px !important;
}
/* Mimic table appearance */
div.table {
	display: table;
}

div.table .file-row {
	display: table-row;
}

div.table .file-row>div {
	display: table-cell;
	vertical-align: top;
	border-top: 1px solid #ddd;
	padding: 2px 37px 3px 0px;
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
#previews .file-row.dz-success .start, #previews .file-row.dz-success .cancel
	{
	display: none;
}

#previews .file-row.dz-success .delete {
	display: block;
}

.smart-form * {
	box-sizing: inherit !important;
}
/* .mandatoryStyle {
	color : #ed1c24 !important;
} */

</style>
<script type="text/javascript" src="assets/js/dropzone.min.js"></script>
</head>
<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/customerDirectory.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-users fa-fw txt-color-blue"></i>
			<spring:message code="CustomerDirectory.Pagehead.Heading" />
			<span>&gt; <spring:message
					code="CustomerDirectory.Pagehead.SubHeading" />
			</span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="CustomerDirectory.Pagehead.TotalCustomers" />
					<span id="totalCustomers" class="txt-color-blue"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>
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
						<fieldset class="custom-scroll"
							style="max-height: 500px; overflow-y: scroll; overflow-x: hidden;">
							<section>
								<div class="row">
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-user mandatoryStyle"></i> <input type="text"
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
											class="fa fa-plus-circle clickTiHide"></i>
										</label>
									</div>
								</div>
							</section>
							<div class="row" id="addressErr" style="display: none;">
											<section class="col col-lg-12">
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
									<label class="input"><i class="icon-append fa fa-phone mandatoryStyle"></i>
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
									<label class="input"><i class="icon-append fa fa-sort-numeric-desc"></i>
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
							<div class="row">
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-user mandatoryStyle"></i><input
										type="text" name="representativeName" id="representativeName"
										placeholder="<spring:message code="CustomerDirectory.AddNewCustomer.label.RepresentativeName"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="CustomerDirectory.AddNewCustomer.tooltip.RepresentativeName" /></b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"><i
										class="icon-append fa fa-mobile mandatoryStyle"></i> <input type="tel"
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
											class="fa fa-plus-circle clickToHideRep"></i>
										</label>
									</div>
								</div>
							</section>


							<section id="repAddressInfo" style="display: none;">
								<input type="hidden" id="repAddressFormId"
									name="repAddressFormId">
								<div class="well" style="padding: 10px">
									<section>
										<div class="row">
											<div class="col col-lg-12">
												<label class="input"> <i
													class="icon-append fa fa-map-marker mandatoryStyle"></i> <input
													type="text" name="repAddress_line_1" id="repAddress_line_1"
													placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.AddressLine1.Tooltip" /></b>
												</label>
												<em id="repAddressLine1Err" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
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
													type="text" name="repAddress_line_2" id="repAddress_line_2"
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
													class="form-control show-tick" id="repState" name="repState">
														<option value=""><spring:message
																code="Address.State.PlaceHolder" /></option>
												</select> <i class="mandatoryIconStyle"></i></label>
												<em id="repAddressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
											</div>
											<div class="col col-lg-6">
												<label class="select"><select
													class="form-control show-tick" id="repCity" name="repCity">
														<option value=""><spring:message
																code="Address.City.PlaceHolder" /></option>
												</select> <i class="mandatoryIconStyle"></i></label>
												<em id="repAddressCityErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
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
													type="text" name="repZipCode" id="repZipCode"
													placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="Address.ZipCode.Tooltip" /></b>
												</label>
												<em id="repAddressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
											</div>
											<div class="col col-lg-6">
												<button type="button" title="Save" id="repAddressSaveButton"
													onclick="saveRepAddressForm();"
													class="btn btn-primary btn-sm ">
													<i class="fa fa-floppy-o"></i>&nbsp;
													<spring:message code="Button.Save" />
												</button>
												<button type="button" title="Update"
													id="repAddressUpdateButton" onclick="updateRepAddressForm();"
													class="btn btn-primary btn-sm " style="display: none">
													<i class="fa fa-thumbs-up"></i>&nbsp;
													<spring:message code="Button.Update" />
												</button>
												<button type="button" title="Cancel"
													id="addressCancelButton" onclick="resetRepAddressForm();"
													class="btn btn-default btn-sm repEditable-cancel">
													<spring:message code="Button.Cancel" />
												</button>
											</div>
										</div>
									</section>
								</div>
							</section>
								<div id="repAaddressDetails" class="row" style="display: none;"></div>

						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetCustomerForm()">
								<spring:message code="Button.Cancel" />
							</button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetCustomerForm()">
								<i class="fa fa-undo"></i> &nbsp;
								<spring:message code="Button.Reset" />
							</button>
							<button type="submit" class="btn btn-primary ladda-button"
								data-style="expand-right" id="saveandcontinue"
								onclick="setActionType(this.id,this)">
								<i class="fa fa-forward"></i> &nbsp;
								<spring:message code="Button.SaveContinue" />
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

<!-- MODAL PLACE HOLDER -->
<div class="modal fade" id="customerUploadModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="cancelCustomerUploadForm()">
					<span class="badge pull-right inbox-badge customColor">&times;</span>
				</button>
				<h4 class="modal-title" id="modalTitleId">
					<i class="fa fa-upload fa-fw txt-color-blue"></i> Upload File
				</h4>
			</div>
			<div class="modal-body no-padding">
				<form id="customerUploadForm" method="POST" class="smart-form"
					enctype="multipart/form-data">
					<input type="hidden" id="className" name="className"> <input
						type="hidden" id="confirmProjectUploadId"
						name="confirmProjectUploadId">

					<fieldset>
						<section>
							<label for="templateFileLabel" class="input input-file">
								<div class="button">
									<input type="file" name="projectTemplateFile"
										id="projectTemplateFile"
										onchange="this.parentNode.nextSibling.value = this.value">
									Browse
								</div>
								<input type="text" name="fileName" id="fileName"
								placeholder="Choose document to Upload" style="color: #2E6074"
								readonly="">
							</label>
						</section>

					</fieldset>
					<footer>
						<!-- 							<button type="submit" class="btn btn-primary button" id="saveValidate" style="display:block;"> -->
						<!-- 									<i class="fa fa-floppy-o"></i> &nbsp; Save -->
						<!-- 							</button> -->
						<!-- 							<button type="button" class="btn btn-primary button" id="saveUpload" style="display:none;" onclick="setUploadConfirmation(this.id)"> -->
						<!-- 						     	 <i class="fa fa-floppy-o"></i> &nbsp; Continue -->
						<!-- 						    </button> -->
						<!-- 							<button type="button" class="btn btn-default pull-left" -->
						<!-- 									data-dismiss="modal" onclick="resetCustomerUploadForm()">Cancel -->
						<!-- 							</button> -->

						<button type="submit" class="btn btn-primary button" id="upload">
							<i class="fa fa-floppy-o"></i> &nbsp; Save
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="cancelCustomerUploadForm()">Cancel</button>
					</footer>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- MODAL PLACE HOLDER -->
<div class="modal fade" id="customerUploadConfirmation" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="resetCustomerConfirmationModal()">
					<span class="badge pull-right inbox-badge customColor">&times;</span>
				</button>
				<h4 class="modal-title" id="modalTitleId">
					<i class="fa fa-exclamation-triangle txt-color-red"></i> Error in
					the template File
				</h4>
			</div>
			<div class="modal-body no-padding">
				<div class="smart-form">
					<fieldset class="smart-form">
						<!--<section style="text-align: center;" id ="customerConfirmationHeader">
			<div class="space"></div>
				 <h4 class="smaller">
					<b>Error Occured while uploading</b>
				</h4> 
		</section>-->
						<section id="errorSection" style="display: none;">
							<div class="alert alert-danger customerErrorHeader"
								id="customerConfirmationHeader">
								<strong>Error Occured while uploading !!</strong>
							</div>
							<ul class="list-unstyled spaced inline bigger-110 margin-15">
								<li class="" id=""><i
									class="ace-icon fa fa-hand-o-right txt-color-red"></i> Select
									Continue to insert the error values as it is</li>
								<li><i class="ace-icon fa fa-hand-o-right txt-color-red"></i>
									Select Cancel to cancel and upload again</li>
							</ul>
							<br>
							<div class="alert alert-danger fade in customerErrorBlock"
								id="errorBlock"></div>
						</section>
					</fieldset>
					<footer>
						<button type="submit" class="btn btn-primary button"
							id="saveUpload" onclick="setCustomerUploadConfirmation(this.id)">
							<i class="fa fa-forward"></i> &nbsp;Continue
						</button>
						<button type="button" class="btn btn-default"
							id="cancelCustomerUpload" data-dismiss="modal"
							onclick="setCustomerUploadConfirmation(this.id);resetCustomerConfirmationModal();cancelCustomerUploadForm();">Cancel
						</button>
					</footer>
				</div>
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
						<!-- <a href="javascript:void(0);"
							class="button-icon jarviswidget-delete-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Delete"><i
							class="fa fa-times"></i></a> -->
					</div>
					<!-- <div class="widget-toolbar" role="menu">
						<a data-toggle="dropdown"
							class="dropdown-toggle color-box selector"
							href="javascript:void(0);"></a>

						<ul
							class="dropdown-menu arrow-box-up-right color-select pull-right">
							<li><span class="bg-color-green"
								data-widget-setstyle="jarviswidget-color-green" rel="tooltip"
								data-placement="left" data-original-title="Green Grass"></span></li>
							<li><span class="bg-color-greenDark"
								data-widget-setstyle="jarviswidget-color-greenDark"
								rel="tooltip" data-placement="top"
								data-original-title="Dark Green"></span></li>
							<li><span class="bg-color-greenLight"
								data-widget-setstyle="jarviswidget-color-greenLight"
								rel="tooltip" data-placement="top"
								data-original-title="Light Green"></span></li>
							<li><span class="bg-color-purple"
								data-widget-setstyle="jarviswidget-color-purple" rel="tooltip"
								data-placement="top" data-original-title="Purple"></span></li>
							<li><span class="bg-color-magenta"
								data-widget-setstyle="jarviswidget-color-magenta" rel="tooltip"
								data-placement="top" data-original-title="Magenta"></span></li>
							<li><span class="bg-color-pink"
								data-widget-setstyle="jarviswidget-color-pink" rel="tooltip"
								data-placement="right" data-original-title="Pink"></span></li>
							<li><span class="bg-color-pinkDark"
								data-widget-setstyle="jarviswidget-color-pinkDark" rel="tooltip"
								data-placement="left" data-original-title="Fade Pink"></span></li>
							<li><span class="bg-color-blueLight"
								data-widget-setstyle="jarviswidget-color-blueLight"
								rel="tooltip" data-placement="top"
								data-original-title="Light Blue"></span></li>
							<li><span class="bg-color-teal"
								data-widget-setstyle="jarviswidget-color-teal" rel="tooltip"
								data-placement="top" data-original-title="Teal"></span></li>
							<li><span class="bg-color-blue"
								data-widget-setstyle="jarviswidget-color-blue" rel="tooltip"
								data-placement="top" data-original-title="Ocean Blue"></span></li>
							<li><span class="bg-color-blueDark"
								data-widget-setstyle="jarviswidget-color-blueDark" rel="tooltip"
								data-placement="top" data-original-title="Night Sky"></span></li>
							<li><span class="bg-color-darken"
								data-widget-setstyle="jarviswidget-color-darken" rel="tooltip"
								data-placement="right" data-original-title="Night"></span></li>
							<li><span class="bg-color-yellow"
								data-widget-setstyle="jarviswidget-color-yellow" rel="tooltip"
								data-placement="left" data-original-title="Day Light"></span></li>
							<li><span class="bg-color-orange"
								data-widget-setstyle="jarviswidget-color-orange" rel="tooltip"
								data-placement="bottom" data-original-title="Orange"></span></li>
							<li><span class="bg-color-orangeDark"
								data-widget-setstyle="jarviswidget-color-orangeDark"
								rel="tooltip" data-placement="bottom"
								data-original-title="Dark Orange"></span></li>
							<li><span class="bg-color-red"
								data-widget-setstyle="jarviswidget-color-red" rel="tooltip"
								data-placement="bottom" data-original-title="Red Rose"></span></li>
							<li><span class="bg-color-redLight"
								data-widget-setstyle="jarviswidget-color-redLight" rel="tooltip"
								data-placement="bottom" data-original-title="Light Red"></span></li>
							<li><span class="bg-color-white"
								data-widget-setstyle="jarviswidget-color-white" rel="tooltip"
								data-placement="right" data-original-title="Purity"></span></li>
							<li><a href="javascript:void(0);"
								class="jarviswidget-remove-colors" data-widget-setstyle=""
								rel="tooltip" data-placement="bottom"
								data-original-title="Reset widget color to default">Remove</a></li>
						</ul>
					</div> -->
					&nbsp; <a href="" data-toggle="modal"
						data-target="#customerRemoteModal"> <span class="widget-icon"
						style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">
							<spring:message code="CustomerDirectory.Form.AddNewCustomer" />
						</h2>
					</a><!--  &nbsp;|&nbsp;&nbsp; <a href="" data-toggle="modal"
						data-target="#customerUploadModal"
						onclick="setModalName('CUSTOMER_EXCEL_FORMAT')"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">Upload File</h2>
					</a>  --><span class="jarviswidget-loader"><i
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
							<table id="customersListTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								style="max-width: 100% !important;" role="grid"
								aria-describedby="dt_basic_info">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;">#</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message
												code="CustomerDirectory.Datatable.TableHead.Column2" /></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Comp Address: activate to sort column ascending"
											style="width: 404px;"><i
											class="icon-append fa fa-map-marker"></i>
										<spring:message
												code="CustomerDirectory.Datatable.TableHead.Column3" /></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Comp Email: activate to sort column ascending"
											style="width: 150px;"><i
											class="icon-append fa fa-envelope"></i>
										<spring:message
												code="CustomerDirectory.Datatable.TableHead.Column4" /></th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Phone: activate to sort column ascending"
											style="width: 200px;"><i
											class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message
												code="CustomerDirectory.Datatable.TableHead.Column5" /></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Rep Name: activate to sort column ascending"
											style="width: 150px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message
												code="CustomerDirectory.Datatable.TableHead.Column6" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label="Rep Address: activate to sort column ascending"
											style="width: 225px;"><i
											class="icon-append fa fa-map-marker"></i>
										<spring:message
												code="CustomerDirectory.Datatable.TableHead.Column7" /></th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Rep Phone: activate to sort column ascending"
											style="width: 154px;"><i
											class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message
												code="CustomerDirectory.Datatable.TableHead.Column8" /></th>
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

<script>
	$(".clickTiHide").click(function() {
		$("#addressInfo").toggle();
		$(".clickTiHide").toggleClass("fa-minus-circle");
	});

	$(".editable-cancel").click(function() {
		$("#addressInfo").toggle();
		$(".clickTiHide").toggleClass("fa-minus-circle");
	});

	$(".clickToHideRep").click(function() {
		$("#repAddressInfo").toggle();
		$(".clickToHideRep").toggleClass("fa-minus-circle");
	});

	$(".repEditable-cancel").click(function() {
		$("#repAddressInfo").toggle();
		$(".clickToHideRep").toggleClass("fa-minus-circle");
	});

	$(".clickTable").click(function() {
		$("#addressTable").toggle();
		$("#dt_basic_wrapper").toggle();
	});
</script>

<!-- end of new upload js -->

<!-- END #MAIN CONTENT -->