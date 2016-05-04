<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
div#invoicePlanTable_length.dataTables_length {
	display: none;
}
</style>
<!-- #MAIN CONTENT -->
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-book txt-color-blue"></i>
			<spring:message code="ApplicationInvoice.PageHead.Heading" />
			<span>&gt; <spring:message
					code="ApplicationInvoice.PageHead.SubHeading" />
			</span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="ApplicationInvoice.PageHead.TotalInvoices" />
					<span class="txt-color-blue" id="totalInvoicesId"></span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="ApplicationInvoice.PageHead.PendingInvoices" />
					<span class="txt-color-purple" id="pendingInvoicesId"><i
						class="fa fa-thumbs-o-down" data-rel="bootstrap-tooltip"
						title="Increased"></i></span>
				</h5>

			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="ApplicationInvoice.PageHead.PaidInvoices" />
					<span class="txt-color-greenDark" id="paidInvoicesId"><i
						class="fa fa-thumbs-o-up"></i></span>
				</h5>

			</li>
			<li class="sparks-info">
				<h5>
					<spring:message
						code="ApplicationInvoice.PageHead.CancelledInvoices" />
					<span class="txt-color-greenDark" id="cancelledInvoicesId"><i
						class="fa fa-thumbs-o-down"></i></span>
				</h5>

			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="invoiceRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" style="width: 750px">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetInvoiceForm();">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title" id="modalTitleId">
						<i class="fa fa-book txt-color-blue"></i>
						<spring:message code="ApplicationInvoice.Form.GenerateInvoice" />
					</h4>
				</div>
				<div class="modal-body no-padding">
					<div class="content">
						<ul id="myTab1" class="nav nav-tabs">
							<li class="active" id="plans"><a href="#s1" data-toggle="tab"> <i
									class="fa fa-fw fa-lg fa-edit"></i> <spring:message code="ApplicationInvoice.TabName.Plans" />
							</a></li>
							<li class="" id="cart"><a href="#s2" data-toggle="tab"><i
									class="fa fa-fw fa-lg fa-shopping-cart"></i> <spring:message code="ApplicationInvoice.TabName.MyCart" /> <span
									class="label bg-color-blue txt-color-white" id="cartId"></span></a></li>
						</ul>


						<div class="tab-content ">
							<div class="tab-pane active" id="s1" style="padding-top: 10px;">

								<fieldset id="putInside">
									<div id="dt_basic_wrapper"
										class="dataTables_wrapper form-inline no-footer">
										<div id="applicationDetailTable_wrapper"
											class="dataTables_wrapper form-inline no-footer">
											<table id="invoicePlanTable"
												class="table table-striped table-bordered table-hover dataTable no-footer"
												width="100%" role="grid"
												aria-describedby="applicationDetailTable_info"
												style="width: 100%;">
												<thead>
													<tr role="row">
														<th data-class="expand" class="center sorting_asc"
															tabindex="0" aria-controls="applicationDetailTable"
															rowspan="1" colspan="1"
															aria-label="
															SNO: activate to sort column ascending"
															style="width: 25px;" aria-sort="ascending"><spring:message code="ApplicationInvoice.PlansTable.Column.Index" /></th>
														<th data-class="expand" class="center sorting"
															tabindex="0" aria-controls="applicationDetailTable"
															rowspan="1" colspan="1"
															aria-label="
															Name: activate to sort column ascending"
															style="width: 95px;"><i
															class="fa fa-fw fa-sort-alpha-asc txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
															<spring:message code="ApplicationInvoice.PlansTable.Column.Application" /></th>
														<th data-class="expand" class="center sorting"
															tabindex="0" aria-controls="applicationDetailTable"
															rowspan="1" colspan="1"
															aria-label="
															Link: activate to sort column ascending"
															style="width: 209px;"><i
															class="fa fa-fw fa-font txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
															<spring:message code="ApplicationInvoice.PlansTable.Column.PlanName" /></th>
														<th data-hide="phone,tablet" class="center sorting"
															tabindex="0" aria-controls="applicationDetailTable"
															rowspan="1" colspan="1"
															aria-label="
															Description: activate to sort column ascending"
															style="width: 188px;"><i
															class="fa fa-fw fa-money txt-color-blue hidden-md hidden-sm hidden-xs"></i>
															<spring:message code="ApplicationInvoice.PlansTable.Column.Price" /></th>
														<th data-hide="phone,tablet" class="center sorting"
															tabindex="0" aria-controls="applicationDetailTable"
															rowspan="1" colspan="1"
															aria-label="
														: activate to sort column ascending"
															style="width: 37px;"></th>
													</tr>
												</thead>
												<tbody>
												<!-- 	<tr class="odd">
														<td>1</td>
														<td>MPR</td>
														<td>Plan 1</td>
														<td>30 $/month</td>
														<td>
															<button class="btn btn-xs btn-default" onclick="addToCart()">
																<i class="fa  fa-arrow-right"></i>
															</button>
														</td>
													</tr>
													<tr class="odd">
														<td>2</td>
														<td>PD</td>
														<td>Plan 1</td>
														<td>30 $/month</td>
														<td>
															<button class="btn btn-xs btn-default" onclick="addToCart()">
																<i class="fa  fa-arrow-right"></i>
															</button>
														</td>
													</tr>
													<tr class="odd">
														<td>3</td>
														<td>MPR</td>
														<td>Plan 2</td>
														<td>30 $/month</td>
														<td>
															<button class="btn btn-xs btn-default" onclick="addToCart()">
																<i class="fa  fa-arrow-right"></i>
															</button>
														</td>
													</tr>
													<tr class="odd">
														<td>4</td>
														<td>FTS</td>
														<td>Plan 1</td>
														<td>30 $/month</td>
														<td>
															<button class="btn btn-xs btn-default" onclick="addToCart()">
																<i class="fa  fa-arrow-right"></i>
															</button>
														</td>
													</tr>
													<tr class="odd">
														<td>5</td>
														<td>MPR</td>
														<td>Plan 3</td>
														<td>30 $/month</td>
														<td>
															<button class="btn btn-xs btn-default" onclick="addToCart()">
																<i class="fa  fa-arrow-right"></i>
															</button>
														</td>
													</tr>
													<tr class="odd">
														<td>6</td>
														<td>FTS</td>
														<td>Plan 2</td>
														<td>30 $/month</td>
														<td>
				 											<button class="btn btn-xs btn-default" onclick="addToCart()">
																<i class="fa  fa-arrow-right"></i>
															</button>
														</td>
													</tr> -->
												</tbody>
											</table>
										</div>
									</div>
								</fieldset>
							</div>


							<div class="tab-pane" id="s2">

								<form id="application_invoice_form" method="POST"
									class="smart-form" commandName="applicationInvoiceForm">
									<input type="hidden" name="invoiceId" id="invoiceId"> <input
										type="hidden" id="typeOfAction" name="typeOfAction">
									<fieldset>
										<section>
											<div class="row">
												<div class="col col-lg-12">
													<label class="input"> <select
														class="form-control selectpicker show-tick"
														id="domainDetail" name="domainDetailUI">

															<option value=""><spring:message
																	code="ApplicationInvoice.label.DomainName" /></option>
															<!-- <option value="Cerrid">Cerrid</option>
															<option value="Belco">Belco</option>
															<option value="Infosys">Infosys</option> -->
													</select>
													</label>
												</div>
											</div>
										</section>

										<div class="row">
											<section class="col col-6">

												<label class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													name="invoiceGeneratedDate" id="invoiceGeneratedDate"
													placeholder="<spring:message code="ApplicationInvoice.label.StartDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="ApplicationInvoice.tooltip.Startdate" /></b>
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i
													class="icon-append fa fa-calendar"></i> <input type="text"
													class="form-control  hasDatepicker" name="invoiceDueDate"
													id="invoiceDueDate"
													placeholder="<spring:message code="ApplicationInvoice.label.EndDate"/>">
													<b class="tooltip tooltip-bottom-right"><spring:message
															code="ApplicationInvoice.tooltip.Enddate" /></b>
												</label>
											</section>
										</div>
										<section>
											<div class="row">
												<div class="col col-lg-12">
													<label class="input"> <select
														class="form-control selectpicker show-tick"
														id="invoiceType" name="invoiceType">

															<option value="Paid" selected="selected"><spring:message code="ApplicationInvoice.MyCartForm.PaymentStatus.Paid" /></option>
															<option value="Pending"><spring:message code="ApplicationInvoice.MyCartForm.PaymentStatus.Pending" /></option>
															<option value="Cancelled"><spring:message code="ApplicationInvoice.MyCartForm.PaymentStatus.Cancelled" /></option>
													</select>
													</label>
												</div>
											</div>
										</section>
										<div class="row">
											<section class="col col-lg-12">
												<label class="input"><i
													class="icon-append fa fa-money"></i> <input type="text"
													name="additionalCharges" id="additionalCharges"
													placeholder="<spring:message code="ApplicationInvoice.Placeholder.AdditionalCharges" />"> <b
													class="tooltip tooltip-bottom-right"><spring:message code="ApplicationInvoice.Tooltips.AdditionalCharges" /></b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-lg-6 pull right">
												<label class="checkbox"> <input type="checkbox"
													name="isLateFeesApplicable" id="lateFeeCheckboxId" onclick="addLateFees()"> <i></i><spring:message code="ApplicationInvoice.MyCart.Selection.LateFeeApplication" />
												</label>
											</section>
											<section class="col col-lg-6">
												<label class="checkbox"> <input type="checkbox"
													name=isInterestApplicable id="interestCheckbox1Id" onclick="addInterest()"> <i></i><spring:message code="ApplicationInvoice.MyCart.Selection.InterestApplication" />
												</label>
											</section>
										</div>

									</fieldset>
									<div id="dt_basic_wrapper"
										class="dataTables_wrapper form-inline no-footer">
										<table id="applicationPurchasePlanTable"
											class="table table-striped table-bordered table-hover dataTable no-footer"
											width="100%" role="grid" aria-describedby="dt_basic_info"
											style="width: 100%;">
											<thead>
												<tr role="row">
													<th data-hide="phone" class="sorting_asc" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="ID: activate to sort column ascending"
														style="width: 41px;" class="center"><spring:message code="ApplicationInvoice.MyCartTable.Column.Index" />#</th>
													<th data-class="expand" class="sorting" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-label=" Name: activate to sort column ascending"
														style="width: 75px;"><i
														class="fa fa-fw fa-font txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
														<spring:message code="ApplicationInvoice.MyCartTable.Column.Application" /></th>	
													<th data-class="expand" class="sorting" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-label=" Name: activate to sort column ascending"
														style="width: 75px;"><i
														class="fa fa-fw fa-font txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
														<spring:message code="ApplicationInvoice.MyCartTable.Column.PlanName" /></th>
													<th data-class="expand" class="sorting" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-label=" Name: activate to sort column ascending"
														style="width: 75px;"><i
														class="fa fa-fw fa-calendar txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
														<spring:message code="ApplicationInvoice.MyCartTable.Column.StartDate" /></th>
													<th data-hide="phone,tablet" class="sorting" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-label=" Date: activate to sort column ascending"
														style="width: 75px;"><i
														class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>
														<spring:message code="ApplicationInvoice.MyCartTable.Column.EndDate" /></th>
													<th data-hide="phone,tablet" class="sorting" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-label=" Date: activate to sort column ascending"
														style="width: 65px;"><i
														class="fa fa-fw fa-usd txt-color-blue hidden-md hidden-sm hidden-xs"></i>
														<spring:message code="ApplicationInvoice.MyCartTable.Column.Price" /></th>
													<th data-hide="phone,tablet" class="sorting" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-label=" Date: activate to sort column ascending"
														style="width: 66px;"><i
														class="fa fa-fw fa-usd txt-color-blue hidden-md hidden-sm hidden-xs"></i>
														<spring:message code="ApplicationInvoice.MyCartTable.Column.ExtraCharges" /></th>
													<th data-class="expand" class="sorting" tabindex="0"
														aria-controls="dt_basic" rowspan="1" colspan="1"
														aria-label=" Comp Name: activate to sort column ascending"
														style="width: 44px;"><i
														class="fa fa-fw fa-usd txt-color-blue hidden-md hidden-sm hidden-xs"></i>
														<spring:message code="ApplicationInvoice.MyCartTable.Column.Total" /></th>
												</tr>
											</thead>
											<tbody>
												<!-- <tr class="odd">
													<td valign="top">MPR</td>
													<td valign="top">12-02-2014</td>
													<td valign="top">30-02-2015</td>
													<td valign="top">30 $/month</td>
													<td valign="top">10 $</td>
													<td valign="top">40</td>
													<td>
														<button class="btn btn-xs btn-default">
															<i class="fa fa-times"></i>
														</button>
													</td>
													<td>
														<button class="btn btn-xs btn-default">
															<i class="fa fa-save"></i>
														</button>
													</td>
												</tr> -->
											</tbody>
										</table>
									</div>

									<footer style="background: #fff" >
										<section class="col col-lg-12" id="lateFeesId">
											<h5 class="col col-lg-6">
												<strong><spring:message code="ApplicationInvoice.Label.LateFees" /></strong>
											</h5>
											<h4 class="col col-lg-6 pull-right">
												<strong><span class="text-success pull-right" id="lateFeesAmount"></span></strong>
											</h4>

										</section>
										<section class="col col-lg-12" id="interestId">
											<h5 class="col col-lg-6">
												<strong><spring:message code="ApplicationInvoice.Label.Interest" /></strong>
											</h5>
											<h4 class="col col-lg-6 pull-right">
												<strong><span class="text-success pull-right" id="interestAmount"></span></strong>
											</h4>

										</section>

										<section class="col col-lg-12">
											<h5 class="col col-lg-6 ">
												<strong><spring:message code="ApplicationInvoice.Label.TotalAmount" /></strong>
											</h5>
											<h4 class="col col-lg-6 pull-right">
												<strong><span class="text-success pull-right" id="totalAmount"></span></strong>
											</h4>

										</section>

									</footer>
									<footer>
										<button type="button" class="btn btn-default"
											data-dismiss="modal" onclick="resetInvoiceForm()">
											<spring:message code="Button.Cancel" />
										</button>
										<button type="button" class="btn btn-primary" id="reset"
											onclick="resetInvoiceForm()">
											<i class="fa fa-undo"></i> &nbsp;
											<spring:message code="Button.Reset" />
										</button>
										<button type="submit" class="btn btn-primary ladda-button"
											data-style="expand-right" id="saveandcontinue"
											onclick="setActionType(this.id,this)">
											<i class="fa fa-forward"></i> &nbsp;
											<spring:message code="Button.SaveContinue" />
										</button>
										<button type="submit" class="btn btn-primary  ladda-button"
											data-style="expand-right" id="save"
											onclick="setActionType(this.id,this)">
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
	</div>
	<!-- END MODAL -->

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
						data-target="#invoiceRemoteModal"> <span class="widget-icon"
						style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;" onclick="showCurrentDate();" id="cusFilter">
							<spring:message code="ApplicationInvoice.Form.GenerateInvoice" />
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

						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="applicationInvoiceTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;" class="center">#</th>
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="Invoice No: activate to sort column ascending"
											style="width: 150px;" class="center"><i
											class="icon-append fa fa-sort-numeric-asc"></i> &nbsp; <spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column2" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Domain Name: activate to sort column ascending"
											style="width: 150px;"><i
											class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; <spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column3" /></th>
<%-- 										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="App Name: activate to sort column ascending"
											style="width: 200px;"><i
											class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; <spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column4" /></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Plan Name: activate to sort column ascending"
											style="width: 150px;"><i
											class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; <spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column5" /></th>
 --%>										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Start Date: activate to sort column ascending"
											style="width: 150px;"><i
											class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>&nbsp;
											<spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column6" /></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="End Date: activate to sort column ascending"
											style="width: 150px;"><i
											class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>&nbsp;
											<spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column7" /></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Additional Charges: activate to sort column ascending"
											style="width: 150px;"><i class="icon-append fa fa-money"></i>&nbsp;
											<spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column9" /></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Invoice Type: activate to sort column ascending"
											style="width: 200px;"><i class="icon-append fa fa-money"></i>&nbsp;
											<spring:message
												code="ApplicationInvoice.DataTable.TableHead.Column8" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" : activate to sort column ascending"
											style="width: 50px;"></th>
									</tr>
								</thead>

								<tbody>
									<!--	<tr>
										<td class="center">1</td>
										<td class="center"><a
											href="home#applicationInvoiceInDetail">I00001</a></td>
										<td class="center">Domain1</td>
										<td class="center">Application1</td>
										<td class="center">Plan1</td>
										<td class="center">8-May-2014</td>
										<td class="center">20-May-2014</td>
									</tr>
									<tr>
										<td class="center">2</td>
										<td class="center"><a
											href="home#applicationInvoiceInDetail">I00002</a></td>
										<td class="center">Domain2</td>
										<td class="center">Application2</td>
										<td class="center">Plan2</td>
										<td class="center">10-Jun-2014</td>
										<td class="center">30-Jun-2014</td>
									</tr>
									<tr>
										<td class="center">3</td>
										<td class="center"><a
											href="home#applicationInvoiceInDetail">I00003</a></td>
										<td class="center">Domain3</td>
										<td class="center">Application3</td>
										<td class="center">Plan3</td>
										<td class="center">8-Aug-2014</td>
										<td class="center">20-Oct-2014</td>
									</tr>-->
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

<!-- END #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/applicationInvoice.js"></script>

<script>
	function customFilter() {
		var itemDetailUL = document.getElementsByClassName('dt-toolbar')[0];

		var htmlPlanFilter = $('<div class="col-sm-6 col-xs-12 hidden-xs"><div id="" class="dataTables_filter"><label><span class="input-group-addon"><i class="fa fa-filter"></i></span><label class="input"> <select class="form-control selectpicker show-tick" onchange = applicationChangeSelectBox() id="appCustFilter" name="appCustFilter"><option value="">Filter : By Application</option><option value="MPR">MPR</option><option value="FTS">FTS</option><option value="PD">PD</option></select></label></label></div></div>');

		htmlPlanFilter.appendTo(itemDetailUL);
	};
</script>
  <script>
	  $("#cusFilter").one("click",function() {
			 customFilter();
			});
</script> 


