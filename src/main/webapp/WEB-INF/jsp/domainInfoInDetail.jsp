<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="assets/js/domainInfoInDetail.js"></script>
<head>
<style>
.no-padding .dataTables_wrapper table {
border-left: 1px solid #ccc !important;
border-right: 1px solid #ccc !important;
border-bottom: 1px solid #ccc !important;
}
</style>
</head>
<script type="text/javascript" src="assets/js/manageUsers.js"></script>
<!-- #MAIN CONTENT -->


	<!-- /.modal -->
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title">
			<i class="fa fa-tencent-weibo fa-fw txt-color-blue"></i><spring:message code="DomainInfo.PageHead.Heading"/><span> &gt; 
			<spring:message code="DomainInfo.PageHead.SubHeading"/></span>
		</h1>
	</div>
</div>


<!-- Datatable code starts -->

<!-- widget grid -->
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
					&nbsp; <a >
						<h2 style="color: #E4E4E4;"><spring:message code="DomainInfo.Title.BasicInformation"/></h2>
					</a> <span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<div role="content">
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">
						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">


							<div class="col-lg-6" style="padding:10px">
							<p style="text-align: center;background:#D6DDE7;color:#305D8C;padding:5px ;margin-bottom:0px"> <b><spring:message code="DomainInfo.Title.ContactPersonBasicInformation"/></b> </p>
						
							<form method="POST" id="" class="smart-form" style="border: 1px solid #ccc;
padding: 15px;">
									<section>
										<label class="input"> <i
											class="icon-append fa fa-user"></i> <input type="text"
											name="name" id="name" placeholder="Contact person name"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="DomainInfo.Tooltips.EnterName"/></b>
										</label>
									</section>
									<section>
										<label class="input"> <i
											class="icon-append fa fa-phone-square"></i> <input
											type="text" name="phoneCarrier" id="phoneCarrier"
											placeholder="Phone carrier"> <b
											class="tooltip tooltip-bottom-right"><spring:message
													code="ManageUser.tooltip.PhoneCarrier" /></b>
										</label>
									</section>
									<section>
										<label class="input"> <i
											class="icon-append fa fa-phone-square"></i> <input
											type="text" name="phoneNumber" id="phoneNumber"
											placeholder="Phone number"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="DomainInfo.Tooltips.EnterPhoneNumber"/></b>
										</label>
									</section>
									<section>
										<label class="input"> <i
											class="icon-append fa fa-phone-square"></i> <input
											type="text" name="preferredContactCarrier"
											id="preferredContactCarrier"
											placeholder="Preferred contact number"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="DomainInfo.Tooltips.EnterPreferredContactNumber"/></b>
										</label>
									</section>
							</form>
							</div>
							
							<div class="col-lg-6" style="padding:10px;">
							<p style="text-align: center;background:#D6DDE7;color:#305D8C;padding:5px ;margin-bottom:0px"> <b><spring:message code="DomainInfo.Title.CompanyBasicInformation"/></b> </p>
						
							<form method="POST" id="" class="smart-form" style="border: 1px solid #ccc;
padding: 15px;">
									<section>
										<label class="input"> <i
											class="icon-append fa fa-user"></i> <input type="text"
											name="companyName" id="companyName" placeholder="Company name"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="DomainInfo.Tooltips.EnterCompanyName"/></b>
										</label>
									</section>
									<section>
										<label class="input"> <i
											class="icon-append fa fa-home"></i> <input
											type="text" name="companyAddress" id="companyAddress"
											placeholder="Company address"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="DomainInfo.Tooltips.EnterCompanyAddress"/></b>
										</label>
									</section>
									<section>
										<label class="input"> <i
											class="icon-append fa fa-phone-square"></i> <input
											type="text" name="companyPhoneCarrier" id="companyPhoneCarrier"
											placeholder="Company phone carrier"> <b
											class="tooltip tooltip-bottom-right"><spring:message
													code="ManageUser.tooltip.PhoneCarrier" /></b>
										</label>
									</section>
									<section>
										<label class="input"> <i
											class="icon-append fa fa-phone-square"></i> <input
											type="text" name="companyPhoneNo" id="companyPhoneNo"
											placeholder="Comapny phone number"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="DomainInfo.Tooltips.EnterCompanyPhoneNumber"/></b>
										</label>
									</section>
							</form>
							</div>
							
							
							<div class="col-lg-12" style="padding:8px;">
							<p style="text-align: center;background:#D6DDE7;color:#305D8C;padding:5px ;margin-bottom:0px"> <b><spring:message code="DomainInfo.Title.SubscriptionPlanInformation"/></b> </p>
						
						<div style="border: 1px solid #ccc;
padding: 9px;">
							<table id="subscriptionPlanInfo"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
										<tr role="row">
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-sort-alpha-asc text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.ApplicationName"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-sort-alpha-asc text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.PlanName"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw  fa-pinterest text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.Status"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-money text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.Price"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-database text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.DataUsage"/></th>	
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-calendar text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.PlanValidity"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-users text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.MaximumUsers"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-calendar text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.StartDate"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-calendar text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.SubscriptionPlanInformationTable.Column.EndDate"/></th>	
									</tr>
								</thead>
								<tbody>
									

								</tbody>
							</table>
							</div>
							</div>
							
							<div class="col-lg-12" style="padding:8px;">
							<p style="text-align: center;background:#D6DDE7;color:#305D8C;padding:5px ;margin-bottom:0px"> <b><spring:message code="DomainInfo.Title.InvoiceDetails"/></b> </p>
						
						<div style="border: 1px solid #ccc;
padding: 9px;">
							<table id="invoiceGenerateInfo"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
										<tr role="row">
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-sort-alpha-asc text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.InvoiceDetailsTable.Column.InvoiceNumber"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-calendar text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.InvoiceDetailsTable.Column.GeneratedDate"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-calendar text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.InvoiceDetailsTable.Column.DueDate"/></th>	
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw  fa-pinterest text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.InvoiceDetailsTable.Column.Status"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-money text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.InvoiceDetailsTable.Column.LateFeeApplicable"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-pinterest text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.InvoiceDetailsTable.Column.InterestApplicable"/></th>	
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-sort-alpha-asc text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.InvoiceDetailsTable.Column.InvoiceType"/></th>
									</tr>
								</thead>
								<tbody>
									

								</tbody>
							</table>
							</div>
							</div>
							
							
							<div class="col-lg-12" style="padding:8px;">
							<p style="text-align: center;background:#D6DDE7;color:#305D8C;padding:5px ;margin-bottom:0px"> <b><spring:message code="DomainInfo.Title.EcosystemUsageDetails"/></b> </p>
						
						<div style="border: 1px solid #ccc;
padding: 9px;">
							<table id="user" class="table table-bordered table-striped"
								style="clear: both">
								<tbody>
									<tr>

										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Users"/></b></td>
										<td style="width: 25%;" id="userCount"></td>
										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Jobs"/></b></td>
										<td style="width: 25%;" id="jobsCount"></td>
									</tr>
                                    <tr>

										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Vendors"/></b></td>
										<td style="width: 25%;" id="vendorsCount"></td>
										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Contractor"/></b></td>
										<td style="width: 25%;" id="contractorCount"></td>
									</tr>
									<tr>

										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.License"/></b></td>
										<td style="width: 25%;" id="licenseCount"></td>
										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Projects"/></b></td>
										<td style="width: 25%;" id="projectCount"></td>
									</tr>
									<tr>

										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Departments"/></b></td>
										<td style="width: 25%;" id="departmentCount"></td>
										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Items"/></b></td>
										<td style="width: 25%;" id="itemCount"></td>
									</tr>
									<tr>

										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Budgets"/></b></td>
										<td style="width: 25%;" id="budgetCount"></td>
										<td style="width: 25%;"><b><spring:message code="DomainInfo.EcosystemUsageDetails.Attr.Customers"/></b></td>
										<td style="width: 25%;" id="customerCount"></td>
									</tr>
								</tbody>
							</table>
                           </div>
							</div>
							
							
							<div class="col-lg-12" style="padding:8px">
							<p style="text-align: center;background:#D6DDE7;color:#305D8C;padding:5px ;margin-bottom:0px"> <b><spring:message code="DomainInfo.Title.SubscriptionPlanInformation"/></b> </p>
						
						<div style="border: 1px solid #ccc;
padding: 9px;">
							<table id="companyLagsInfo"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
										<tr role="row">
										
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-calendar text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="DomainInfo.Title.IssueReportedAndResolution"/></th>
									</tr>
								</thead>
								<tbody>
									

								</tbody>
							</table>
							</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</article>
		
		

		

	</div>


</section>
<!-- end widget grid -->
<!-- END #MAIN CONTENT -->
