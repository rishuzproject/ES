<!-- #MAIN CONTENT -->

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

.editableCellStyle {
	background: rgb(237, 239, 141);
}

.custPosition {
	position: relative;
	top: 10px;
	left: 20px;
	font-size: 18px;
}

.smart-form .checkbox input+i:after {
	font: 400 13px/14px FontAwesome;
}

.smart-form .checkbox i {
	width: 15px;
	height: 12px;
}
</style>
<style>
.fuelux .wizard ul li {
	border-right: 1px solid #ccc;
	font-size: 13px !important;
	padding-right: 10px;
	padding-left: 10px;
	border-radius: 0px
}

.fuelux .wizard ul li.active {
	font-weight: bold !important;
	border-top: 4px solid #57889c;
	background: #fff;
	border-radius: 0px;
}

.custStyle {
	background: #eee;
	color: #666;
	font-weight: bold;
	font-size: 11px;
}
</style>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-6">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-stack-exchange txt-color-blue">&nbsp</i>
			<spring:message code="ChangeOrder.PageHead.ChangeOrder" />
			<span> &gt;<spring:message
					code="ChangeOrder.PageHead.ManageChangeOrderProcess" /></span>
		</h1>
	</div>
	<!-- <div class="col-xs-12 col-sm-5 col-md-5 col-lg-6">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					Total Change <span class="txt-color-blue" id="totalUsers"></span>

				</h5>
			</li>
		</ul>
	</div> -->
</div>


<!-- Add External RFC Modal Starts here -->
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="externalRfcModal" data-backdrop="static"
		data-keyboard="false" role="dialog" aria-labelledby="remoteModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" style="width: 800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title" id="headingAddUpdate">
						<i class="fa fa-suitcase txt-color-blue">&nbsp;</i>
					</h4>
				</div>
				<div class="modal-body no-padding">
					<div class="content">
						<div class="widget-body fuelux tabbable">

							<div class="wizard " style="border-radius: 0px">
								<ul class="steps" id="myTab">
									<li class="active" id="rfcInfoLi"><a href="#step1"><spring:message
												code="ChangeOrder.AddChangeOrderModal.TabHeading.RFCInfo" /></a></li>
									<li class="hide" id="revenueLi"><a href="#step2"><spring:message
												code="ChangeOrder.AddChangeOrderModal.TabHeading.Revenue&Cost" /></a></li>
									<li class="hide" id="approvalLi"><a href="#step3"><spring:message
												code="ChangeOrder.AddChangeOrderModal.TabHeading.ApprovalFields" /></a></li>
								</ul>
								<div class="actions">
									<button type="button" id="saveBtn"
										onclick='setSubmitType(this.id);$("#manageChangeOrderForm").submit();'
										class="btn btn-sm btn-primary">
										<i class="fa fa-floppy-o"></i>
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.SaveRFC" />
									</button>
									<button type="button" id="prevBtn" onclick="btnPrevTab();"
										class="btn btn-sm btn-primary">
										<i class="fa fa-arrow-left"></i>
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.Previous" />
									</button>
									<button type="button" class="btn btn-sm btn-success addingId"
										id="" onclick="btnNextTab();">
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.Next" />
										<i class="fa fa-arrow-right"></i>
									</button>
								</div>

							</div>
							<!-- Tab panes -->

							<div class="step-content"
								style="border: 1px solid #ccc; border-top: none;">
								<form id="manageChangeOrderForm" class="smart-form"
									enctype="multipart/form-data">
									<input type="hidden" name="sNoHidden" id="extsNo"> <input
										type="hidden" name="rfcUserResponseStatus"
										id="rfcUserResponseStatus"> <input type="hidden"
										name="jobIdHidden" id="jobIdHidden"> <input
										type="hidden" id="rfcStatusHidden"> <input
										type="hidden" name="submitType" id="submitType"> <input
										type="hidden" name="approvedDate" id="approvedDate" value="">
									<div class="step-pane" id="loadingDiv"
										style="height: 501px; position: absolute; top: 0px; z-index: 122;">
										<div class="smart-form">
											<fieldset
												style="padding-top: 15px; height: 481px; background: rgba(124, 104, 108, 0.34); width: 768px;">
												<div class="text-center error-box"
													style="position: relative; top: 15%;">
													<h1 class="error-text tada animated"
														style="font-size: 380%;">
														<i class="fa  fa-fw fa-gear txt-color-blue"
															style="-moz-transform: rotate(180deg); -ms-transform: rotate(180deg); -o-transform: rotate(180deg);"></i>
														<spring:message
															code="ChangeOrder.AddChangeOrderModal.Interval.Loading" />
													</h1>
													<h2 class="">
														<strong class="" id="loadingText"
															style="font-size: 20px; color: #fff; font-weight: 500"><i
															class="fa fa-fw fa-hand-o-right"></i> <spring:message
																code="ChangeOrder.AddChangeOrderModal.Interval.PleaseWait" /></strong>
													</h2>
													<br>
												</div>
											</fieldset>
										</div>
									</div>
									<div class="step-pane active rfcDescription" id="step1"
										style="height: 501px">
										<!-- wizard form starts here -->
										<div class="smart-form">
											<fieldset style="padding-top: 15px">
												<div class="">
													<section class="">
														<label class="input"><i
															class="icon-append fa fa-sort-alpha-asc mandatoryStyle"></i> <input
															type="text" name="rfcDesc" id="extrfcDesc"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.RFCInfo.Placeholder.RFCDescription"/>">
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.RFCInfo.Tooltips.RFCDescription" /></b>
														</label>
													</section>
												</div>
												<div class="row">
													<section class="col col-6">
														<label class="input"><i
															class="icon-append fa fa-sort-numeric-asc mandatoryStyle"></i> <input
															type="text" name="rfcReferenceNo" id="rfcReferenceNo"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.RFCInfo.Placeholder.ReferenceNumber"/>">
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.RFCInfo.Tooltips.ReferenceNumber" /></b>
														</label>
													</section>
													<section class="col col-6">
														<label class="input"><i
															class="icon-append fa fa-sort-numeric-asc"></i> <input
															type="text" name="originationReferenceNumber"
															id="extOrgRefNo"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.RFCInfo.Placeholder.OriginalRefNo"/>"
															readonly style="background: #eee"> <b
															class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.RFCInfo.Tooltips.OriginalRefNo" /></b>
														</label>
													</section>
												</div>
												<div class="row">
													<section class="col col-6">
														<label class="input"><i
															class="icon-append fa fa-sort-numeric-asc"></i> <input
															type="text" name="companyRfcNo" id="companyRfcNo"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.RFCInfo.Placeholder.CompanyRFCNo"/>">
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.RFCInfo.Tooltips.CompanyRFCNo" /></b>
														</label>
													</section>
													<section class="col col-6">
														<label class="input"><i
															class="icon-append fa fa-calendar"></i> <input
															type="text" name="origDate" id="extorigDate"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.RFCInfo.Placeholder.OriginationDate"/>"
															disabled style="background: #eee"> <b
															class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.RFCInfo.Tooltips.OriginationDate" /></b>
														</label>
													</section>
												</div>
												<div class="row">
													<section class="col col-6">
														<label class="select"> <select
															class="form-control" id="extstatus" name="rfcStatus"
															onchange="calculateMaster('external')">
																<!-- <option value="Pending">Pending</option> -->
														</select> <i></i>
														</label>
													</section>
													<section class="col col-6">
														<label class="select"> <select
															class="form-control selectpicker show-tick"
															id="extRfcType" name="rfcType"
															onchange="setRevenueValuesForInternal();">
																<option value="External"><spring:message
																		code="ChangeOrder.AddChangeOrderModal.RFCInfo.RfcTypeSelect.External" /></option>
																<option value="Internal"><spring:message
																		code="ChangeOrder.AddChangeOrderModal.RFCInfo.RfcTypeSelect.Internal" /></option>
														</select> <i></i>
														</label>
													</section>
												</div>

												<div class="">
													<section class="">
														<label class="select"> <select
															class="form-control selectpicker show-tick"
															id="extrfcMapping" name="rfcMappingDB"
															onchange="rfcMappingCal(this.value)">
																<option value=""><spring:message
																		code="ChangeOrder.AddChangeOrderModal.RFCInfo.ExtRfcMappingSelect.RfcMapping" /></option>
														</select> <i></i>
														</label>
													</section>
												</div>
												<div class="row" style="display: none;"
													id="rfcMappingTxtDiv">
													<section class="col col-12">
														<label class="input"><i
															class="icon-append fa fa-calendar"></i> <input
															type="text" name="rfcMapping" id="rfcMappingTxt"
															placeholder="" style="background: #eee"> <b
															class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.RFCInfo.Tooltips.ChangeOrderMapping" /></b>
														</label>
													</section>
												</div>
												<div class="">
													<section class="">
														<label class="textarea textarea-resizable"> <i
															class="icon-append fa fa-comment"></i> <textarea
																class="form-control" rows="3" name="rfcNotes"
																id="extrfcNotes"
																placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.RFCInfo.Placeholder.RfcNotes"/>"></textarea>
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.RFCInfo.Tooltips.RfcNotes" /></b>
															<font color="red" size="1px"><p align="right">
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.RFCInfo.MaximumCharacters" />
																</p></font> <br>
														</label>
													</section>
												</div>
											</fieldset>
										</div>
									</div>
									<div class="step-pane revenueCost" id="step2">
										<div class="smart-form">
											<fieldset style="padding-top: 15px">
												<div class="">
													<section>
														<label for="templateFileLabel" class="input input-file">
															<!-- <div class="button">
														<input type="file" name="templateFile" id="templateFile"
															onchange="this.parentNode.nextSibling.value = this.value">
														Browse
													</div> --> <input type="file" name="myFile" id="myFile"
															placeholder="Choose document to Upload"
															style="color: #2E6074" readonly=""
															onchange="checkFileSize(this);">
														</label>
													</section>
												</div>
											</fieldset>
										</div>
										<fieldset style="padding-top: 15px">
											<div class="widget-body" style="border: 1px solid #ccc;">

												<div class="panel-group smart-accordion-default"
													id="accordion">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion"
																	href="#collapseOne" aria-expanded="false" class="">
																	<i
																	class="fa fa-fw fa-plus-circle txt-color-green pull-right"></i>
																	<i
																	class="fa fa-fw fa-minus-circle txt-color-red pull-right"></i>
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.Quotation.Label.OverAllQuotation" />
																</a>
															</h4>
														</div>
														<div id="collapseOne" class="panel-collapse collapse in"
															aria-expanded="false">
															<div class="panel-body no-padding">
																<div class="smart-form">
																	<fieldset>
																		<div class="row">
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="quoted" id="extquoted"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.Quotation.Placeholder.QuotedAmount"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.Quotation.Tooltips.QuotedAmount" /></b>
																				</label>
																			</section>
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="expApproval" id="extexpApproval"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.Quotation.Placeholder.ExpectedApprovalAmount"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.Quotation.Tooltips.ExpectedApprovalAmount" /></b>
																				</label>
																			</section>
																		</div>
																		<div class="row">
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">%</i> <input type="text"
																					name="factor" id="extfactor"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.Quotation.Placeholder.ApprovalFactor"/>"
																					onkeyup="calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.Quotation.Tooltips.ApprovalFactor" /></b>
																				</label>
																			</section>
																		</div>
																	</fieldset>
																</div>
															</div>
														</div>
													</div>
													<div class="panel panel-default">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion"
																	href="#collapseTwo" class="collapsed"
																	aria-expanded="false"> <i
																	class="fa fa-fw fa-plus-circle txt-color-green pull-right"></i>
																	<i
																	class="fa fa-fw fa-minus-circle txt-color-red pull-right"></i>
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.MaterialCosts.Label.MaterialCosts" />
																</a>
															</h4>
														</div>
														<div id="collapseTwo" class="panel-collapse collapse"
															aria-expanded="false">
															<div class="panel-body">
																<div class="smart-form">
																	<fieldset>
																		<div class="row">
																			<section class="col col-1">
																				<a data-toggle="collapse" href="#materialTable">
																					<i id="mat" onclick="fetchMatBudgetFormData()"
																					class="fa  fa-list pull-right txt-color-blue custPosition"
																					title="ADD additional field"></i>
																				</a>
																			</section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="material" id="extmaterial"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.MaterialCosts.Placeholder.MaterialQuoted"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.MaterialCosts.Tooltips.MaterialQuoted" /></b>
																				</label>
																			</section>
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">%</i> <input type="text"
																					name="materialFactor" id="extApprovalfactor"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.MaterialCosts.Placeholder.MaterialCostFactor"/>"
																					onkeyup="calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.MaterialCosts.Tooltips.MaterialCostFactor" /></b>
																				</label>
																			</section>
																		</div>
																		<div class="row">
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="actualMaterialCost"
																					id="extActualMaterialCost"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.MaterialCosts.Placeholder.ActualMaterialCost"/>"
																					readonly="readonly"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.MaterialCosts.Tooltips.ActualMaterialCost" /></b>
																				</label>
																			</section>
																		</div>
																	</fieldset>
																</div>
																<div class="panel-body no-padding collapse closePanel"
																	id="materialTable">
																	<div class="alert alert-info fade in"
																		style="margin-bottom: 0px; border-left: 0px;">
																		Additional Material Cost Fields</div>
																	<table id="matHeaderTable"
																		class="table table-bordered table-condensed">
																		<thead style="pointer-events: none">
																			<tr class="custStyle">
																				<th style="width: 50%">MISC TAX (%)
																				</td>
																				<th style="width: 50%">SALES TAX (%)
																				</td>
																		</thead>
																	</table>
																	<table class="table table-bordered table-condensed">
																		<thead>
																			<tr class="custStyle">
																				<td style="width: 50%">TOTAL ($ VALUE INCLS
																					TAX)</td>
																				<td id="matTotal"></td>
																			</tr>
																			<tr>
																				<td colspan="8" style="background: white;"></td>
																			</tr>
																		</thead>
																	</table>
																	<table id="materialCostTable"
																		class="table table-bordered table-condensed">
																		<thead>
																			<tr>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Name: activate to sort column ascending"
																					style="width: 19px;">#</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 77px;">Cost Type</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">Activity Description</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">Activity #</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">$ Value Incls Tax</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px; pointer-events: none;">Material</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px; pointer-events: none;">Quoted</th>
																			</tr>
																		</thead>
																	</table>
																	<footer style="padding: 0px 13px 6px;">
																		<button type="button" onclick="closeBudgetModel()"
																			class="btn-xs btn-default pull-right"
																			style="position: relative; top: 4px" data-dismiss="">
																			Cancel</button>
																		<button type="button"
																			onclick="saveMaterialBudgetForm()"
																			class="btn-xs btn-primary pull-right"
																			style="position: relative; top: 4px"
																			id="saveMatJobBtn">
																			<i class="fa fa-floppy-o"></i> &nbsp; Save Changes
																		</button>
																	</footer>
																</div>
															</div>
														</div>
													</div>
													<div class="panel panel-default">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion"
																	href="#collapseThree" class="collapsed"
																	aria-expanded="false"> <i
																	class="fa fa-fw fa-plus-circle txt-color-green pull-right"></i>
																	<i
																	class="fa fa-fw fa-minus-circle txt-color-red pull-right"></i>
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.SubContractCost.Label.SubContractCost" />
																</a>
															</h4>
														</div>
														<div id="collapseThree" class="panel-collapse collapse "
															aria-expanded="false">
															<div class="panel-body">
																<div class="smart-form">
																	<fieldset>
																		<div class="row">
																			<section class="col col-1">
																				<a data-toggle="collapse"
																					href="#subcontractTableTab"> <i
																					onclick="fetchSubContractBudgetFormData()"
																					class="fa  fa-list pull-right txt-color-blue custPosition"
																					title="ADD additional field"></i>
																				</a>
																			</section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="subContract" id="extsubContract"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.SubContractCost.Placeholder.SubcontractQuoted"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.SubContractCost.Tooltips.SubcontractQuoted" /></b>
																				</label>
																			</section>
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">%</i> <input type="text"
																					name="subcontractorFactor"
																					id="extSubcontractCostFactor"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.SubContractCost.Placeholder.SubcontractCostFactor"/>"
																					onkeyup="calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.SubContractCost.Tooltips.SubcontractCostFactor" /></b>
																				</label>
																			</section>
																		</div>
																		<div class="row">
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="actualSubcontractCost"
																					id="extActualSubcontractCost"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.SubContractCost.Placeholder.ActualSubcontractCost"/>"
																					readonly="readonly"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.SubContractCost.Tooltips.ActualSubcontractCost" /></b>
																				</label>
																			</section>
																		</div>
																	</fieldset>
																</div>
																<div class="panel-body no-padding collapse closePanel"
																	id="subcontractTableTab">
																	<div class="alert alert-info fade in"
																		style="margin-bottom: 0px; border-left: 0px;">
																		Additional Subcontract Cost Fields</div>
																	<table class="table table-bordered table-condensed">
																		<thead>
																			<tr class="custStyle">
																				<td colspan="5" style="width: 64.4%">TOTAL ($
																					VALUE INCLS TAX)</td>
																				<td colspan="3" id="subTotal"></td>
																			</tr>
																			<tr>
																				<td colspan="8" style="background: #fff;"></td>
																			</tr>
																		</thead>
																	</table>
																	<table id="subcontractTable"
																		class="table table-bordered table-condensed">
																		<thead>
																			<tr>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Name: activate to sort column ascending"
																					style="width: 19px;">#</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 77px;">Cost Type</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">Activity Description</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">Activity #</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">$ Value Incls Tax</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px; pointer-events: none;">Quoted</th>
																			</tr>
																		</thead>
																	</table>
																	<footer style="padding: 0px 13px 6px;">
																		<button type="button"
																			class="btn-xs btn-default pull-right"
																			onclick="closeBudgetModel()"
																			style="position: relative; top: 4px" data-dismiss="">
																			Cancel</button>
																		<button type="button"
																			onclick="saveSubContrctBudgetForm()"
																			class="btn-xs btn-primary pull-right"
																			style="position: relative; top: 4px"
																			id="saveSubContrctJobBtn">
																			<i class="fa fa-floppy-o"></i> &nbsp; Save Changes
																		</button>
																	</footer>
																</div>
															</div>
														</div>
													</div>
													<div class="panel panel-default">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion"
																	href="#collapseFour" class="collapsed"
																	aria-expanded="false"> <i
																	class="fa fa-fw fa-plus-circle txt-color-green pull-right"></i>
																	<i
																	class="fa fa-fw fa-minus-circle txt-color-red pull-right"></i>
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Label.LaborCost" />
																</a>
															</h4>
														</div>
														<div id="collapseFour" class="panel-collapse collapse"
															aria-expanded="false">
															<div class="panel-body">
																<div class="smart-form">
																	<fieldset>
																		<div class="row">
																			<section class="col col-1">
																				<a data-toggle="collapse" href="#laborCostTableTab">
																					<i onclick="fetchLaborBudgetFormData()"
																					class="fa  fa-list pull-right txt-color-blue custPosition"
																					title="ADD additional field"></i>
																				</a>
																			</section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">#</i> <input type="text"
																					name="labrHr" id="extlabrHr"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Placeholder.LaborHours"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Tooltips.LaborHours" /></b>
																				</label>
																			</section>
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="labr" id="extlabr"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Placeholder.LaborQuoted"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Tooltips.LaborQuoted" /></b>
																				</label>
																			</section>
																		</div>
																		<div class="row">
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">%</i> <input type="text"
																					name="laborFactor" id="extLaborfactor"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Placeholder.LaborCostFactor"/>"
																					onkeyup="calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Tooltips.LaborCostFactor" /></b>
																				</label>
																			</section>
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="actualLaborCost" id="extActualLaborCost"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Tooltips.ActualLaborCost"/>"
																					readonly="readonly"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.LaborCost.Tooltips.ActualLaborCost" /></b>
																				</label>
																			</section>
																		</div>
																	</fieldset>
																</div>
																<div class="panel-body no-padding collapse closePanel"
																	id="laborCostTableTab">
																	<div class="alert alert-info fade in"
																		style="margin-bottom: 0px; border-left: 0px;">
																		Additional Labor Cost Fields</div>
																	<table id="laborHeaderCostTable"
																		class="table table-bordered table-condensed">
																		<thead style="pointer-events: none">
																			<tr class="custStyle">
																				<th style="width: 20%">LAB W/BDN
																				</td>
																				<th style="width: 20%">LAB WO/BDN
																				</td>
																				<th style="width: 20%">BURDEN
																				</td>
																				<th style="width: 20%">FRINGES
																				</td>
																				<th style="width: 20%">FOREMAN RATE
																				</td>
																			</tr>
																		</thead>
																	</table>
																	<table class="table table-bordered table-condensed">
																		<thead>
																			<tr class="custStyle">
																				<td style="width: 50%">TOTAL LABOR COST</td>
																				<td id="laborTotal"></td>
																			</tr>
																			<tr>
																				<td colspan="9" style="background: #fff;"></td>
																			</tr>
																		</thead>
																	</table>
																	<table id="laborCostTableList"
																		class="table table-bordered table-condensed">
																		<thead>
																			<tr>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Name: activate to sort column ascending"
																					style="width: 19px;"></th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 77px;">Cost Type</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 162px;">Activity Description</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 68px;">Activity #</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">Total Labor Cost</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px; pointer-events: none;">Hrs</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px; pointer-events: none;">Burden
																					($)</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px; pointer-events: none;">Frings
																					($)</th>
																			</tr>
																		</thead>
																	</table>
																	<footer style="padding: 0px 13px 6px;">
																		<button type="button" onclick="closeBudgetModel()"
																			class="btn-xs btn-default pull-right"
																			style="position: relative; top: 4px" data-dismiss="">
																			Cancel</button>
																		<button type="button" onclick="saveLaborBudgetForm()"
																			class="btn-xs btn-primary pull-right"
																			style="position: relative; top: 4px"
																			id="saveLaborBtn">
																			<i class="fa fa-floppy-o"></i> &nbsp; Save Changes
																		</button>
																	</footer>
																</div>
															</div>
														</div>
													</div>
													<div class="panel panel-default">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion"
																	href="#collapseFive" class="collapsed"
																	aria-expanded="false"> <i
																	class="fa fa-fw fa-plus-circle txt-color-green pull-right"></i>
																	<i
																	class="fa fa-fw fa-minus-circle txt-color-red pull-right"></i>
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.EquipmentCost.Label.EquipmentCost" />
																</a>
															</h4>
														</div>
														<div id="collapseFive" class="panel-collapse collapse"
															aria-expanded="false">
															<div class="panel-body">
																<div class="smart-form">
																	<fieldset>
																		<div class="row">
																			<section class="col col-1">
																				<a data-toggle="collapse" href="#equipmentTableTab">
																					<i onclick="fetchEquipBudgetFormData()"
																					class="fa  fa-list pull-right txt-color-blue custPosition"
																					title="ADD additional field"></i>
																				</a>
																			</section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="equip" id="extequip"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.EquipmentCost.Placeholder.Equipment"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.EquipmentCost.Tooltips.Equipment" /></b>
																				</label>
																			</section>
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="ownedEquipment" id="extOwnEquip"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.EquipmentCost.Placeholder.OwnedEquipment"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.EquipmentCost.Tooltips.OwnedEquipment" /></b>
																				</label>
																			</section>
																		</div>
																	</fieldset>
																</div>
																<div class="panel-body no-padding collapse closePanel"
																	id="equipmentTableTab">
																	<div class="alert alert-info fade in"
																		style="margin-bottom: 0px; border-left: 0px;">
																		Additional Equipment Cost Fields</div>
																	<table class="table table-bordered table-condensed">
																		<thead>
																			<tr class="custStyle">
																				<td colspan="4" style="width: 64.4%">TOTAL ($
																					VALUE INCLS TAX)</td>
																				<td colspan="3" id="equipTotal"></td>
																			</tr>
																			<tr>
																				<td colspan="9" style="background: #fff;"></td>
																			</tr>
																		</thead>
																	</table>
																	<table id="equipmentTable"
																		class="table table-bordered table-condensed">
																		<thead>
																			<tr>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Name: activate to sort column ascending"
																					style="width: 19px;">#</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 77px;">Cost Type</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">Activity Description</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">Activity #</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px;">$ Value Incls Tax</th>
																				<th data-class="expand" class="sorting" tabindex="0"
																					aria-controls="dt_basic" rowspan="1" colspan="1"
																					aria-label=" Column Name: activate to sort column ascending"
																					style="width: 102px; pointer-events: none;">Cost</th>
																			</tr>
																		</thead>
																	</table>
																	<footer style="padding: 0px 13px 6px;">
																		<button type="button"
																			class="btn-xs btn-default pull-right"
																			onclick="closeBudgetModel()"
																			style="position: relative; top: 4px" data-dismiss="">
																			Cancel</button>
																		<button type="button"
																			class="btn-xs btn-primary pull-right"
																			onclick="saveEquipBudgetForm()"
																			style="position: relative; top: 4px"
																			id="saveEquipJobBtn">
																			<i class="fa fa-floppy-o"></i> &nbsp; Save Changes
																		</button>
																	</footer>
																</div>
															</div>
														</div>
													</div>
													<div class="panel panel-default">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion"
																	href="#collapseSix" class="collapsed"
																	aria-expanded="false"> <i
																	class="fa fa-fw fa-plus-circle txt-color-green pull-right"></i>
																	<i
																	class="fa fa-fw fa-minus-circle txt-color-red pull-right"></i>
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Label.OtherCosts" />
																</a>
															</h4>
														</div>
														<div id="collapseSix" class="panel-collapse collapse"
															aria-expanded="false">
															<div class="panel-body">
																<div class="smart-form">
																	<fieldset>
																		<div class="row">
																			<section class="col col-1">
																				<a data-toggle="collapse" data-parent="#otherCosts"
																					href="#directJobTable"> <i
																					onclick="fetchDirectBudgetFormData()"
																					class="fa  fa-list pull-right txt-color-blue custPosition"
																					title="ADD additional field"></i>
																				</a>
																			</section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="dirJobCost" id="extdirJobCost"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Placeholder.DirectJobCosts"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Tooltips.DirectJobCosts" /></b>
																				</label>
																			</section>
																			<section class="col col-1">
																				<a data-toggle="collapse" data-parent="#otherCosts"
																					href="#projectAdminTable"> <i
																					onclick="fetchProjAdminBudgetFormData()"
																					class="fa  fa-list pull-right txt-color-blue custPosition"
																					title="ADD additional field"></i>
																				</a>
																			</section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="projAdmin" id="extprojAdmin"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Placeholder.ProjectAdmin"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Tooltips.ProjectAdmin" /></b>
																				</label>
																			</section>
																		</div>
																		<div class="row">
																			<section class="col col-1">
																				<a data-toggle="collapse" data-parent="#otherCosts"
																					href="#indirectTable"> <i
																					onclick="fetchInDirectBudgetFormData()"
																					class="fa  fa-list pull-right txt-color-blue custPosition"
																					title="ADD additional field"></i>
																				</a>
																			</section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="indirCost" id="extindirCost"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Placeholder.IndirectCost"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Tooltips.IndirectCost" /></b>
																				</label>
																			</section>
																			<section class="col col-1"></section>
																			<section class="col col-5">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="contingency" id="extcontingency"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Placeholder.Contingency"/>"
																					onkeyup="formatCurrencyToUSFormat(this.id , this.value); calculateMaster('external')"
																					onchange="calculateMaster('external')"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.OtherCosts.Tooltips.Contingency" /></b>
																				</label>
																			</section>
																		</div>
																	</fieldset>
																</div>
																<div id="otherCosts">
																	<div class="panel">
																		<div class="panel-body no-padding collapse closePanel"
																			id="directJobTable">
																			<div class="alert alert-info fade in"
																				style="margin-bottom: 0px; border-left: 0px;">
																				<i class="fa-fw fa fa-info"></i>Additional Direct
																				Job Costs Fields
																			</div>
																			<table class="table table-bordered table-condensed">
																				<thead>
																					<tr class="custStyle">
																						<td colspan="5" style="width: 64.4%">TOTAL ($
																							VALUE INCLS TAX)</td>
																						<td colspan="4" id="dirJobTotal">213</td>
																					</tr>
																					<tr>
																						<td colspan="9" style="background: #fff;"></td>
																					</tr>
																				</thead>
																			</table>
																			<table id="directJobTableList"
																				class="table table-bordered table-condensed">
																				<thead>
																					<tr>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Name: activate to sort column ascending"
																							style="width: 19px;">#</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 57px;">Cost Type</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 295px;">Activity Description</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 42px;">Activity #</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 133px;">$ Value Incls Tax</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 45px; pointer-events: none;">Cost</th>
																					</tr>
																				</thead>
																				<tbody>
																					<!-- 
																					<tr>
																						<td><label class="checkbox"> <input
																								type="checkbox" name="checkbox-inline">
																								<i></i>
																						</label></td>
																						<td>fff</td>
																						<td>ffff</td>
																						<td>dsds</td>
																						<td>asa</td>
																						<td style="background: rgb(236, 237, 185)">240</td>
																						<td></td>
																					</tr> -->
																				</tbody>
																			</table>
																			<footer style="padding: 0px 13px 6px;">
																				<button type="button" onclick="closeBudgetModel()"
																					class="btn-xs btn-default pull-right"
																					style="position: relative; top: 4px"
																					data-dismiss="">Cancel</button>
																				<button type="button"
																					onclick="saveDirJobBudgetForm()"
																					class="btn-xs btn-primary pull-right"
																					style="position: relative; top: 4px"
																					id="saveDirJobBtn">
																					<i class="fa fa-floppy-o"></i> &nbsp; Save Changes
																				</button>
																			</footer>
																		</div>
																	</div>
																	<div class="panel">
																		<div class="panel-body no-padding collapse closePanel"
																			id="projectAdminTable">
																			<div class="alert alert-info fade in"
																				style="margin-bottom: 0px; border-left: 0px;">
																				<i class="fa-fw fa fa-info"></i>Additional Project
																				Admin Fields
																			</div>
																			<table class="table table-bordered table-condensed">
																				<thead>
																					<tr class="custStyle">
																						<td colspan="5" style="width: 64.4%">TOTAL ($
																							VALUE INCLS TAX)</td>
																						<td colspan="4" id="projAdminTotal"></td>
																					</tr>
																					<tr>
																						<td colspan="9" style="background: #fff;"></td>
																					</tr>
																				</thead>
																			</table>
																			<table id="projAdminTableList"
																				class="table table-bordered table-condensed">
																				<thead>
																					<tr>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Name: activate to sort column ascending"
																							style="width: 19px;">#</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 57px;">Cost Type</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 295px;">Activity Description</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 42px;">Activity #</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 133px;">$ Value Incls Tax</th>
																					</tr>
																				</thead>
																			</table>
																			<footer style="padding: 0px 13px 6px;">
																				<button type="button" onclick="closeBudgetModel()"
																					class="btn-xs btn-default pull-right"
																					style="position: relative; top: 4px"
																					data-dismiss="">Cancel</button>
																				<button type="button"
																					onclick="saveProjAdminBudgetForm()"
																					class="btn-xs btn-primary pull-right"
																					style="position: relative; top: 4px"
																					id="saveJobBtn">
																					<i class="fa fa-floppy-o"></i> &nbsp; Save Changes
																				</button>
																			</footer>
																		</div>
																	</div>
																	<div class="panel">
																		<div class="panel-body no-padding collapse closePanel"
																			id="indirectTable">
																			<div class="alert alert-info fade in"
																				style="margin-bottom: 0px; border-left: 0px;">
																				<i class="fa-fw fa fa-info"></i>Additional Indirect
																				Cost Fields
																			</div>
																			<table class="table table-bordered table-condensed">
																				<thead>
																					<tr class="custStyle">
																						<td colspan="5" style="width: 64.4%">TOTAL ($
																							VALUE INCLS TAX)</td>
																						<td colspan="4" id="indirTotal">213</td>
																					</tr>
																					<tr>
																						<td colspan="9" style="background: #fff;"></td>
																					</tr>
																				</thead>
																			</table>
																			<table id="indirTableList"
																				class="table table-bordered table-condensed">
																				<thead>
																					<tr>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Name: activate to sort column ascending"
																							style="width: 19px;">#</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 57px;">Cost Type</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 295px;">Activity Description</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 42px;">Activity #</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 133px;">$ Value Incls Tax</th>
																						<th data-class="expand" class="sorting"
																							tabindex="0" aria-controls="dt_basic" rowspan="1"
																							colspan="1"
																							aria-label=" Column Name: activate to sort column ascending"
																							style="width: 133px;">Material</th>
																					</tr>
																				</thead>
																			</table>
																			<footer style="padding: 0px 13px 6px;">
																				<button type="button" onclick="closeBudgetModel()"
																					class="btn-xs btn-default pull-right"
																					style="position: relative; top: 4px"
																					data-dismiss="">Cancel</button>
																				<button type="button"
																					onclick="saveIndirBudgetForm()"
																					class="btn-xs btn-primary pull-right"
																					style="position: relative; top: 4px"
																					id="saveIndirBtn">
																					<i class="fa fa-floppy-o"></i> &nbsp; Save Changes
																				</button>
																			</footer>
																		</div>
																	</div>

																</div>
															</div>
														</div>
													</div>

													<div class="panel panel-default">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion"
																	href="#collapseSeven" aria-expanded="false"
																	class="collapsed"> <i
																	class="fa fa-fw fa-plus-circle txt-color-green pull-right"></i>
																	<i
																	class="fa fa-fw fa-minus-circle txt-color-red pull-right"></i>
																	<spring:message
																		code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.CostSummary.Label.CostSummary" />
																</a>
															</h4>
														</div>
														<div id="collapseSeven" class="panel-collapse collapse"
															aria-expanded="false">
															<div class="panel-body no-padding">
																<div class="smart-form">
																	<fieldset>
																		<div class="row">
																			<section class="col col-6">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="exttotalCost" id="exttotalCost"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.CostSummary.Placeholder.TotalCost"/>"
																					readonly="readonly"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.CostSummary.Tooltips.TotalCost" /></b>
																				</label>
																			</section>
																			<section class="col col-6">
																				<label class="input"><i
																					class="icon-append fa">$</i> <input type="text"
																					name="extgrossProfit" id="extgrossProfit"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.CostSummary.Placeholder.GrossProfit"/>"
																					readonly="readonly"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.CostSummary.Tooltips.GrossProfit" /></b>
																				</label>
																			</section>
																		</div>
																		<div class="row">
																			<section class="col col-6">
																				<label class="input"><i
																					class="icon-append fa">%</i> <input type="text"
																					name="extgrossMargin" id="extgrossMargin"
																					placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.CostSummary.Placeholder.GrossMargin"/>"
																					readonly="readonly"> <b
																					class="tooltip tooltip-bottom-right"><spring:message
																							code="ChangeOrder.AddChangeOrderModal.Revenue&Cost.CostSummary.Tooltips.GrossMargin" /></b>
																				</label>
																			</section>
																		</div>
																	</fieldset>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</fieldset>
									</div>

									<div class="step-pane approvalProcess" id="step3"
										style="height: 501px">
										<div class="smart-form">
											<fieldset style="padding-top: 15px">
												<div class="row">
													<section class="col col-6">
														<label class="input"><i
															class="icon-append fa  fa-sort-numeric-asc"></i> <input
															type="text" name="custRefNo" id="extcustRefNo"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Placeholder.CustomerReferenceNumber"/>">
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Tooltips.CustomerReferenceNumber" /></b>
														</label>
													</section>
													<section class="col col-6">
														<label class="input"><i
															class="icon-append fa  fa-sort-alpha-asc"></i> <input
															type="text" name="custRefNoDesc" id="extcustRefNoDesc"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Placeholder.CustomerReferenceDescription"/>">
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Tooltips.CustomerReferenceDescription" /></b>
														</label>
													</section>
												</div>
												<div class="row">
													<section class="col col-6">
														<label class="input"><i class="icon-append fa">$</i>
															<input type="text" name="approved" id="extapproved"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Placeholder.ApprovedAmount"/>"
															onkeyup="formatCurrencyToUSFormat(this.id , this.value) ; calculateMaster('external')"
															onchange="calculateMaster('external')"><b
															class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Tooltips.ApprovedAmount" /></b>
														</label>
													</section>
													<section class="col col-6">
														<label class="input"><i
															class="icon-append fa fa-calendar"></i> <input
															type="text" name="approvedDate" id="extapprovedDate"
															placeholder="<spring:message code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Placeholder.ApprovedDate"/>">
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ChangeOrder.AddChangeOrderModal.ApprovalFields.OtherCosts.Tooltips.ApprovedDate" /></b>
														</label>
													</section>
												</div>
											</fieldset>
										</div>
									</div>
								</form>
							</div>
							<div class="smart-form">
								<footer>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
									<button type="button" class="btn btn-primary hide"
										id="rejectBtn"
										onClick="setRfcUserResponseStatusValue('Rejected')">
										<i class="fa fa-times"></i> &nbsp;
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.Reject" />
									</button>
									<button type="button" class="btn btn-primary hide"
										id="approveBtn"
										onClick="setRfcUserResponseStatusValue('Approved')">
										<i class="fa fa-check"></i> &nbsp;
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.Approve" />
									</button>
									<button type="button" class="btn btn-primary hide"
										id="reopenBtn" onClick="setRfcReOpen()">
										<i class="fa fa-forward"></i> &nbsp;
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.ReOpen" />
									</button>
									<button type="button" class="btn btn-primary hide"
										id="updateBtn"
										onclick='setSubmitType(this.id);$("#manageChangeOrderForm").submit();'>
										<i class="fa fa-forward"></i> &nbsp;
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.Update" />
									</button>
									<button type="button" class="btn btn-primary hide"
										id="customerApproval"
										onClick="setSubmitType(this.id);setRfcUserResponseStatusValue('Send For Customer Approval')">
										<i class="fa fa-send"></i> &nbsp;
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.SendForCustomerApproval" />
									</button>
									<button type="button" class="btn btn-primary hide"
										id="resendCustomerApproval"
										onClick="setRfcUserResponseStatusValue('Resend For Customer Approval')">
										<i class="fa fa-send"></i> &nbsp;
										<spring:message
											code="ChangeOrder.AddChangeOrderModal.Buttons.ResendForCustomerApproval" />
									</button>
								</footer>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- ADD EXTERNAL RFC MODAL ENDS HERE -->

<!-- Datatable code starts -->

<!-- widget grid -->
<section id="widget-grid" class="">
	<!-- <button id="reset"  class="btn btn-primary" style="float : right ; display: none" onclick="reset();">Back</button>
<br>
<br>
<br> -->
	<!-- row -->
	<div class="row">
		<div class="col-md-12" style="padding-bottom: 10px;" id="selectDivId">
			<select class="form-control  show-tick " id="selectProject" data-live-search="true"
				style="height: 40px; cursor: pointer" name="selectProject"
				onchange="getRfcListByprojectId(this.value);" >
				<!-- <option value="">--Select a Project---</option> -->
			</select>
		</div>
		<div class="col-md-1" id="backBtnId">
			<button id="reset" class="btn btn-primary"
				style="float: right; display: none; height: 39px;"
				onclick="reset();">
				<spring:message code="ChangeOrder.AddChangeOrderModal.Buttons.Back" />
			</button>
		</div>
	</div>
	<div class="row">



		<!-- <div class="col-md-12" id="justMessage">
			<div class="text-center col-md-12" style="border: 1px solid #ccc">
				<h1 class="error-text tada animated" style="font-size: 600%;">
					<i class="fa fa-table text-info "></i> RFC Table
				</h1>
				<h2 class="font-xl">
					<strong> </strong>
				</h2>
				<br>
				<p class="lead semi-bold">
					<strong><i class="fa fa-check-square-o"></i> Select a
						Project from above dropdown !</strong><br> <br> <small>
						to get its corresponding Change Order List Table.. </small>
				</p>
			</div>
		</div> -->

		<!-- 		D3 Charts 		-->

		<div class=" col-lg-6" id="barChartDiv">
			<section class="panel panel-default">
				<div class="panel-heading text-center" style="font-size: initial;">
					<i class="fa fa-bar-chart-o"></i>&nbsp;&nbsp; <em><strong><spring:message
								code="ChangeOrder.AddChangeOrderModal.ChartHeaderText.TotalQuotedVsApproved" /></strong></em>
				</div>
				<div id="barChart" style="background-color: #ededed;"></div>
			</section>
		</div>
		<div class=" col-lg-6" id="pieChartDiv">
			<section class="panel panel-default">
				<div class="panel-heading text-center" style="font-size: initial;">
					<i class="fa fa-renren"></i>&nbsp;&nbsp; <em><strong><spring:message
								code="ChangeOrder.AddChangeOrderModal.ChartHeaderText.TotalCostVsGrossProfit" /></strong></em>
				</div>
				<div id="pieChart" style="background-color: #ededed;"></div>
			</section>
		</div>

		<!-- 		D3 Charts Ends 		-->

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable hide"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style=""
				id="widgetId">
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

					&nbsp;&nbsp; <a href="" data-toggle="modal"
						data-target="#externalRfcModal" id="hrefClick"
						onclick="addOptionsToStatus('modal');setRfcOrigDate();"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">
							<spring:message
								code="ChangeOrder.AddChangeOrderModal.LabelText.AddNewChangeOrder" />
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
					<div class="widget-body ">

						<div class="content">
							<ul class="nav nav-tabs" role="tablist" style="padding-top: 3px;">
								<li class="active" onclick="createCharts();"><a
									href="#summaryProject" role="tab" data-toggle="tab"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.Summary" /></a></li>
								<li class=""><a href="#allProject" role="tab"
									data-toggle="tab"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.All" /></a></li>
								<li class=""><a href="#approvedProject" role="tab"
									data-toggle="tab"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.Approved" /></a></li>
								<li class=""><a href="#priceProceedProject" role="tab"
									data-toggle="tab"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.Price&Proceed" /></a></li>
								<li class=""><a href="#pendingProject" role="tab"
									data-toggle="tab"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.Pending" /></a></li>
								<li class=""><a href="#deniedProject" role="tab"
									data-toggle="tab"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.Denied" /></a></li>
								<li class=""><a href="#deletedProject" role="tab"
									data-toggle="tab"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.Deleted" /></a></li>
								<li class=""><a href="#takeOffSheet" role="tab"
									data-toggle="tab" onClick="loadRfcTakeOffSheetsData();"><spring:message
											code="ChangeOrder.ChangeOrderTab.Label.Takeoffsheet" /></a></li>
							</ul>
							<!-- Tab panes -->
							<form id="manageJobForm" class="" commandname="manageJobForm"
								novalidate="novalidate">
								<!-- <input type="hidden" name="jobId" id="jobId">  -->
								<div class="tab-content">
									<div class="tab-pane active" id="summaryProject">
										<div class=" col-lg-6">
											<section class="panel panel-default">
												<div class="panel-heading text-center"
													style="font-size: initial; background: white;">
													<i class="fa fa-bar-chart-o"></i>&nbsp;&nbsp; <em><strong><spring:message
																code="ChangeOrder.AddChangeOrderModal.ChartHeaderText.TotalQuotedVsApproved" /></strong></em>
												</div>
												<div id="barChartForProject"
													style="background-color: #ededed;"></div>
											</section>
										</div>
										<div class=" col-lg-6">
											<section class="panel panel-default">
												<div class="panel-heading text-center"
													style="font-size: initial; background: white;">
													<i class="fa fa-renren"></i>&nbsp;&nbsp; <em><strong><spring:message
																code="ChangeOrder.AddChangeOrderModal.ChartHeaderText.TotalCostVsGrossProfit" /></strong></em>
												</div>
												<div id="pieChartForProject"
													style="background-color: #ededed;"></div>
											</section>
										</div>
									</div>
									<div class="tab-pane" id="allProject">
										<fieldset>
											<div class="row">
												<section class="col-lg-12">
													<table id="allChangeOrderTable"
														class="table table-striped table-bordered table-hover dataTable no-footer"
														width="100%" role="grid" aria-describedby="dt_basic_info"
														style="width: 100%;">
														<thead>
															<tr role="row">
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 120px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCDescription" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 80px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCType" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Status" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"></i> <spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Quoted" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Appr" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 92px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.TotalCost" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Profit" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 82px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Margin" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
														</thead>
														<tbody>

														</tbody>
													</table>
												</section>
											</div>
										</fieldset>
									</div>
									<div class="tab-pane" id="approvedProject">
										<fieldset>
											<div class="row">
												<section class="col-lg-12">
													<table id="approvedChangeOrderTable"
														class="table table-striped table-bordered table-hover dataTable no-footer"
														width="100%" role="grid" aria-describedby="dt_basic_info"
														style="width: 100%;">
														<thead>
															<tr role="row">
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 120px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCDescription" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 80px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCType" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Status" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"></i> <spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Quoted" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Appr" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 92px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.TotalCost" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Profit" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 82px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Margin" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
														</thead>
														<tbody>

														</tbody>
													</table>
												</section>
											</div>
										</fieldset>
									</div>
									<div class="tab-pane" id="priceProceedProject">
										<fieldset>
											<div class="row">
												<section class="col-lg-12">
													<table id="priceProceedChangeOrderTable"
														class="table table-striped table-bordered table-hover dataTable no-footer"
														width="100%" role="grid" aria-describedby="dt_basic_info"
														style="width: 100%;">
														<thead>
															<tr role="row">
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 120px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCDescription" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 80px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCType" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Status" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"></i> <spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Quoted" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Appr" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 92px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.TotalCost" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Profit" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 82px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Margin" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
														</thead>
														<tbody>

														</tbody>
													</table>
												</section>
											</div>
										</fieldset>
									</div>
									<div class="tab-pane" id="pendingProject">
										<fieldset>
											<div class="row">
												<section class="col-lg-12">
													<table id="pendingChangeOrderTable"
														class="table table-striped table-bordered table-hover dataTable no-footer"
														width="100%" role="grid" aria-describedby="dt_basic_info"
														style="width: 100%;">
														<thead>
															<tr role="row">
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 120px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCDescription" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 80px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCType" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Status" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"></i> <spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Quoted" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Appr" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 92px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.TotalCost" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Profit" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 82px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Margin" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
														</thead>
														<tbody>

														</tbody>
													</table>
												</section>
											</div>
										</fieldset>
									</div>
									<div class="tab-pane" id="deniedProject">
										<fieldset>
											<div class="row">
												<section class="col-lg-12">
													<table id="deniedChangeOrderTable"
														class="table table-striped table-bordered table-hover dataTable no-footer"
														width="100%" role="grid" aria-describedby="dt_basic_info"
														style="width: 100%;">
														<thead>
															<tr role="row">
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 120px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCDescription" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 80px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCType" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Status" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"></i> <spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Quoted" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Appr" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 92px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.TotalCost" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Profit" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 82px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Margin" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
														</thead>
														<tbody>

														</tbody>
													</table>
												</section>
											</div>
										</fieldset>
									</div>
									<div class="tab-pane" id="deletedProject">
										<fieldset>
											<div class="row">
												<section class="col-lg-12">
													<table id="deletedChangeOrderTable"
														class="table table-striped table-bordered table-hover dataTable no-footer"
														width="100%" role="grid" aria-describedby="dt_basic_info"
														style="width: 100%;">
														<thead>
															<tr role="row">
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 120px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCDescription" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 80px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.RFCType" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Status" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"></i> <spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Quoted" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 63px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Appr" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 92px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.TotalCost" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Profit" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 82px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.All.Column.Margin" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"></th>
														</thead>
														<tbody>

														</tbody>
													</table>
												</section>
											</div>
										</fieldset>
									</div>

									<div class="tab-pane" id="takeOffSheet">

										<fieldset>
											<div class="row">
												<section class="col-lg-12">

													<table id="takeOffSheetTable"
														class="table table-striped table-bordered table-hover dataTable no-footer"
														width="100%" role="grid" aria-describedby="dt_basic_info"
														style="width: 100%;">
														<thead>
															<tr role="row">
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 19px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.TakeOffSheet.Column.Index" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 120px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.TakeOffSheet.Column.FileName" /></th>
																<th data-class="expand" class="sorting" tabindex="0"
																	aria-controls="dt_basic" rowspan="1" colspan="1"
																	aria-label=" Name: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.TakeOffSheet.Column.RFCDescription" /></th>
																<th data-hide="phone,tablet" class="sorting"
																	tabindex="0" aria-controls="dt_basic" rowspan="1"
																	colspan="1"
																	aria-label=" Date: activate to sort column ascending"
																	style="width: 75px;"><spring:message
																		code="ChangeOrder.ChangeOrderTab.TakeOffSheet.Column.SubmittedDate" /></th>
														</thead>
														<tbody>

														</tbody>
													</table>
												</section>
											</div>
										</fieldset>
									</div>



								</div>
							</form>
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
<script type="text/javascript" src="assets/js/changeOrderDatatables.js"></script>
<script type="text/javascript" src="assets/js/changeOrder.js"></script>
<script>
	$('#selectProject').on('change', function() {
		if ($('#selectProject').val() == "") {
			$('#backBtnId').addClass('hide');
			$('#selectDivId').removeClass('col-md-11');
			$('#selectDivId').addClass('col-md-12');
		} else {
			$('#selectDivId').removeClass('col-md-12');
			$('#selectDivId').addClass('col-md-11');
			$('#backBtnId').removeClass('hide');
			$("#reset").show();
		}
	});

	/* (function(){
		$('#selectProject').selectpicker();	
	})() */
	/* var $tabs = $('.tabbable ul li');
	$('#prevBtn').on('click', function() {
		$tabs.filter('.active').prev('li').find('a').tab('show');
	}); */
	/* $('#nextBtn').on('click', function() {
		$tabs.filter('.active').next('li').find('a').tab('show');
	});  */
	$('#myTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
</script>
<script type="text/javascript" src="assets/js/d3.min.js"></script>
<script type="text/javascript" src="assets/js/d3-tip.js"></script>