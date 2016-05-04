<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<style>
.vendorErrorBlock {
	box-shadow: 3px 5px 11px #836463;
	border-right: 5px solid #953b39;
	background: #fff;
	color: #836463;
	height: 160px;
	overflow-y: auto
}

.vendorErrorHeader {
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
/* .mandatoryStyle {
	color : #ed1c24 !important;
} */
</style>
</head>

<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/vendorDirectory.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-user fa-fw txt-color-blue"></i> Vendors <span>&gt;
				Manage Vendors </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					TOTAL VENDORS <span id="totalVendors" class="txt-color-blue"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>

<div>

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="vendorRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
							id="submitId">
							<input type="hidden" name="status" id="status" value="ACTIVE">
						<fieldset class="custom-scroll" style="max-height: 500px; overflow-y: scroll; overflow-x: hidden;">
							<section>
								<div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i
											class="icon-append fa fa-map-marker mandatoryStyle"></i> <input type="text"
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
											class="icon-append fa fa-map-marker mandatoryStyle"></i> <input type="text"
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
											<option value="">Select Organization Type</option>
											<option value="Corporation">Corporation</option>
											<option value="Joint Venture">Joint Venture</option>
											<option value="Non Profit Organization">Non Profit
												Organization</option>
											<option value="Government Agency">Government Agency</option>
											<option value="State Owned">State Owned</option>
											<option value="LLC">LLC</option>
											<option value="Others">Others</option>
									</select> <i class="mandatoryIconStyle"></i></label>
								</section>
								<section class="col col-6">
									<label class="select"> <select
										class="form-control selectpicker show-tick"
										id="vendorOwnership" name="vendorOwnership">
											<option value="">Select Ownership</option>
											<option value="Public">Public</option>
											<option value="Private">Private</option>
									</select> <i class="mandatoryIconStyle"></i>
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
											class="fa fa-plus-circle clickToHideOffice"></i>
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
							<input type="hidden" id="addressActionType" name="addressActionType">
							<div class="well" style="padding: 10px">
								<section>
							      <div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i class="icon-append fa fa-map-marker mandatoryStyle"></i> 
										<input type="text" name="address_line_1" id="address_line_1"
										 placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.AddressLine1.Tooltip"/></b>
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
										<label class="input"> <i class="icon-append fa fa-map-marker"></i> 
										<input type="text" name="address_line_2" id="address_line_2"
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
										id="state" name="state">
											<option value=""><spring:message code="Address.State.PlaceHolder"/></option>
									</select> <i class="mandatoryIconStyle"></i></label>
									<em id="addressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<label class="select"><select
										class="form-control show-tick"
										id="city" name="city">
											<option value=""><spring:message code="Address.City.PlaceHolder"/></option>
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
										<label class="input"> <i class="icon-append fa fa-sort-numeric-desc mandatoryStyle"></i> 
										<input type="text" name="zipCode" id="zipCode"
										 placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.ZipCode.Tooltip"/></b>
										</label>
										<em id="addressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<button type="button" title="Save" id="addressSaveButton"
										 onclick="saveAddressForm();" class="btn btn-primary btn-sm ">
											<i class="fa fa-floppy-o" ></i>&nbsp;
											<spring:message code="Button.Save"/>
										</button>
										<button type="button" title="Update" id="addressUpdateButton" 
										onclick="updateAddressForm();" class="btn btn-primary btn-sm " style="display:none">
										<i class="fa fa-thumbs-up" ></i>&nbsp;
										<spring:message code="Button.Update"/>
										</button>
										<button type="button" title="Cancel" id="addressCancelButton" 
										onclick="resetAddressForm();" class="btn btn-default btn-sm editable-cancel">
										<spring:message code="Button.Cancel"/>
										 </button>
									</div>
								</div>
							  </section>
						</div>
							</section>
							<div id="addressDetails" class="row" style="display: none;"></div>
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
											class="icon-append fa fa-phone mandatoryStyle"></i> <input type="text"
											name="vendorPhoneNo" id="vendorPhoneNo"
											placeholder="Vendor Phone No"> <b
											class="tooltip tooltip-bottom-right">Enter the vendor
												phone</b>
										</label>
									</div>
									<div class="col col-lg-6">
										<label class="input"> <i
											class="icon-append fa fa-sort-alpha-asc mandatoryStyle"></i> <input
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
											class="fa fa-plus-circle clickToHideRep"></i>
										</label>
									</div>
								</div>
							</section>
							<section id="repAddressInfo" style="display: none;">
							<input type="hidden" id="repAddressId" name="repAddressId">
							<input type="hidden" id="repAddressActionType" name="repAddressActionType">
							<div class="well" style="padding: 10px">
								<section>
							      <div class="row">
									<div class="col col-lg-12">
										<label class="input"> <i class="icon-append fa fa-map-marker mandatoryStyle"></i> 
										<input type="text" name="repAddress_line_1" id="repAddress_line_1"
										 placeholder="<spring:message code="Address.AddressLine1.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.AddressLine1.Tooltip"/></b>
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
										<label class="input"> <i class="icon-append fa fa-map-marker"></i> 
										<input type="text" name="repAddress_line_2" id="repAddress_line_2"
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
										id="repState" name="repState">
											<option value=""><spring:message code="Address.State.PlaceHolder"/></option>
									</select> <i class="mandatoryIconStyle"></i></label>
									<em id="repAddressStateErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressStateErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<label class="select"><select
										class="form-control show-tick"
										id="repCity" name="repCity">
											<option value=""><spring:message code="Address.City.PlaceHolder"/></option>
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
										<label class="input"> <i class="icon-append fa fa-sort-numeric-desc mandatoryStyle"></i> 
										<input type="text" name="repZipCode" id="repZipCode"
										 placeholder="<spring:message code="Address.ZipCode.PlaceHolder"/>"> 
										<b class="tooltip tooltip-bottom-right"><spring:message code="Address.ZipCode.Tooltip"/></b>
										</label>
										<em id="repAddressZipCodeErr" style="font-style: normal;display: none;font-size: 11px;color:#D56161;">
																<spring:message code="JobDetails.NewJobCreation.PlaceHolder.addressZipCodeErr" />
															</em>
									</div>
									<div class="col col-lg-6">
										<button type="button" title="Save" id="repAddressSaveButton" 
										onclick="saveRepAddressForm();" class="btn btn-primary btn-sm ">
											<i class="fa fa-floppy-o" ></i>&nbsp;
											<spring:message code="Button.Save"/>
										</button>
										<button type="button" title="Update" id="repAddressUpdateButton" 
										onclick="updateRepAddressForm();" class="btn btn-primary btn-sm " style="display:none">
										<i class="fa fa-thumbs-up" ></i>&nbsp;
										<spring:message code="Button.Update"/>
										</button>
										<button type="button" title="Cancel" id="repAddressCancelButton" 
										onclick="resetRepAddressForm();" class="btn btn-default btn-sm repEditable-cancel">
										<spring:message code="Button.Cancel"/>
										 </button>
									</div>
								</div>
							  </section>
						</div>
							</section>
							<div id="repAaddressDetails" class="row" style="display: none;"></div>
							<div class="row">
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-sort-numeric-desc"></i>
										<input type="text" name="irs" id="irs" placeholder="NAICS">
										<b class="tooltip tooltip-bottom-right">NAICS</b> </label>
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
								data-dismiss="modal" onclick="resetVendorForm()">Cancel</button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetVendorForm()">
								<i class="fa fa-undo"></i> &nbsp;Reset
							</button>
							<button type="submit" class="btn btn-primary ladda-button"
								data-style="expand-right" id="saveandcontinue"
								onclick="setActionType(this.id,this)">
								<i class="fa fa-forward"></i> &nbsp;Save&Continue
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
<!-- Button trigger modal -->


<!-- MODAL PLACE HOLDER -->
<div class="modal fade" id="vendorUploadModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="cancelVendorUploadForm()">
					<span class="badge pull-right inbox-badge customColor">&times;</span>
				</button>
				<h4 class="modal-title" id="modalTitleId">
					<i class="fa fa-user fa-fw txt-color-blue"></i> Add New Vendor
				</h4>
			</div>
			<div class="modal-body no-padding">
				<form id="vendorUploadForm" method="POST" class="smart-form">
					 <input type="hidden" id="className" name="className">
						<input type="hidden" id="confirmProjectUploadId" name="confirmProjectUploadId">
					<fieldset>
						<section>
							<label for="templateFileLabel" class="input input-file">
								<div class="button">
									<input type="file" name="templateFile" id="templateFile"
										onchange="this.parentNode.nextSibling.value = this.value">
									Browse
								</div><input type="text" name="fileName" id="fileName"
								placeholder="Choose document to Upload" readonly="">
							</label>
						</section>

					</fieldset>
					<footer>
						<button type="submit" class="btn btn-primary button" id="save">
							<i class="fa fa-floppy-o"></i> &nbsp; Save
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="cancelVendorUploadForm()">Cancel</button>
					</footer>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- MODAL FOR CONFIRMATION -->

<div class="modal" id="vendorUploadConfirmation" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="resetConfirmationModal()">
					<span class="badge pull-right inbox-badge customColor">&times;</span>
				</button>
				<h4 class="modal-title" id="modalTitleId">
					<i class="fa fa-user fa-fw txt-color-blue"></i> Error in the template File
				</h4>
			</div>
			<div class="modal-body no-padding">
				<div class="smart-form">
				<fieldset class="smart-form">
<!-- 					<section style="text-align: center;" id = "confirmationHeader"> -->
<!-- 						<div class="space"></div> -->
<!-- 						<h4 class="smaller"> -->
<!-- 							<b>Error Occured while uploading</b> -->
<!-- 						</h4> -->
<!-- 					</section> -->

					<section id="errorSection" style="display: none;">
						<div class="alert alert-danger vendorErrorHeader" id="confirmationHeader">
							<strong>Error Occured while uploading !!</strong>
						</div>
						<ul class="list-unstyled spaced inline bigger-110 margin-15">
							<li class="" id=""><i class="ace-icon fa fa-hand-o-right txt-color-red" ></i> Select Continue to insert the valid rows</li>
							<li><i class="ace-icon fa fa-hand-o-right txt-color-red"></i> Select Cancel to cancel and upload again</li>
						</ul>
						<br>
						<div class="alert alert-danger fade in vendorErrorBlock"
							id="errorBlock"></div>
					</section>
				</fieldset>
				
				<footer>
					<a  title='Download Document' onclick="downloadErrorFile()">Click</a>
					<button type="submit" class="btn btn-primary button"
						id="saveVendorUpload"
						onclick="setVendorUploadConfirmation(this.id)">
						<i class="fa fa-forward"></i> &nbsp;Continue
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" id="cancelVendorUpload"
						onclick="setVendorUploadConfirmation(this.id);resetConfirmationModal();cancelVendorUploadForm();">Cancel</button>
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
					</div>
					&nbsp; <a href="" data-toggle="modal"
						data-target="#vendorRemoteModal"> <span class="widget-icon"
						style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">Add New Vendor</h2>
					</a><!-- &nbsp;|&nbsp;&nbsp; --> <%-- <a href="" data-toggle="modal"
						data-target="#uploadModal"  onclick="setModuleName('<spring:message code="Upload.VendorDirectory"/>')"> 
						<span class="widget-icon"
						style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
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
							<table id="vendorListTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
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
											style="width: 150px;">Vendor Name</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;">Vendor Address</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;">Vendor Phone</th>
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
</section>


<script>
$(".clickToHideOffice").click(function(){
		$("#addressInfo").toggle();
		$(".clickToHideOffice").toggleClass("fa-minus-circle");
  });
  
$(".clickToHideRep").click(function(){
		$("#repAddressInfo").toggle();
		$(".clickToHideRep").toggleClass("fa-minus-circle");
  });

$(".repEditable-cancel").click(function(){
	$("#repAddressInfo").toggle();
	$(".clickToHideRep").toggleClass("fa-minus-circle");
});
$(".editable-cancel").click(function(){
    $("#addressInfo").toggle();
    $(".clickToHideOffice").toggleClass("fa-minus-circle");
  });

$(".clickTable").click(function(){
    $("#addressTable").toggle();
    $("#dt_basic_wrapper").toggle();
  });

</script>









