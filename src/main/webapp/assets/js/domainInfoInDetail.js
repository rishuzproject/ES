
subscriptionPlanInfoData = null;
invoiceGenerateInfoData = null;

var pagefunction = function() {
	var responsiveHelper_dt_basic = undefined;
	var breakpointDefinition = {
			tablet : 1024,
			phone : 480
		};
	subscriptionPlanInfoData = $('#subscriptionPlanInfo')
			.dataTable({
				"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
					+ "t"
					+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
			"autoWidth" : true,
			"bFilter" : true,
			"bSearchable" : false,
			"bInfo" : true,
			"bDestroy" : true,
			aoColumns : [
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[0];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[1];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[2];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return "$ "+full[3];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[4]+" MB";
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[5]+" Months";
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[6];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[7];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[8];
								}
							}],
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_dt_basic) {
									responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
											$('#subscriptionPlanInfo'),
											breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_dt_basic.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_dt_basic.respond();
							}
					/*End Basic */	
	});
	invoiceGenerateInfoData = $('#invoiceGenerateInfo').dataTable(
			{
				"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
					+ "t"
					+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
			"autoWidth" : true,
			"bFilter" : true,
			"bSearchable" : false,
			"bInfo" : true,
			"bDestroy" : true,
			aoColumns : [
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return "<a  href='home#applicationInvoiceInDetail?invoiceId="+full[0]+"'>"
									+ full[0]
									+ "</a>";
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[1];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[2];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[3];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[4];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[5];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[6];
								}
							}],
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_dt_basic) {
									responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
											$('#invoiceGenerateInfo'),
											breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_dt_basic.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_dt_basic.respond();
							}
					/*End Basic */	
			});
    $('#companyLagsInfo').dataTable();
    getUserDetailsForPayment();
};
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
// getting the details and populating it
function getUserDetailsForPayment(){
	$.ajax({
		url : "getAllDomainRelatedDetails",
		type : "POST",
		success : function(result){
			allResult = JSON.parse(result);
			console.log(allResult);
			var firstName ,lastName,middleName,name;
			middleName = lastName = name = "";
			if (allResult.alluserDetails[0].middleName != undefined
					&& allResult.alluserDetails[0].middleName != null) {
				middleName = allResult.alluserDetails[0].middleName;
			}
			if (allResult.alluserDetails[0].lastName != undefined
					&& allResult.alluserDetails[0].lastName != null) {
				lastName = allResult.alluserDetails[0].lastName;
			}
			if (allResult.alluserDetails[0].firstName != undefined
					&& allResult.alluserDetails[0].firstName != null) {
				firstName = allResult.alluserDetails[0].firstName;
			}
			name=firstName+" "+middleName+" "+lastName;
				$("#name").val(name);
				$("#phoneCarrier").val(allResult.alluserDetails[0].phoneCarrier);
				$("#phoneNumber").val(allResult.alluserDetails[0].phoneNumber);
				$("#preferredContactCarrier").val(
						allResult.alluserDetails[0].phoneCarrier);
				$("#companyName").val(allResult.allDomainDetails[0].companyName);
				$("#companyAddress").val(allResult.allDomainDetails[0].companyAddress);
				$("#companyPhoneCarrier").val(allResult.allDomainDetails[0].companyPhoneCarrier);
				$("#companyPhoneNo").val(allResult.allDomainDetails[0].companyPhoneNumber);
				subscriptionPlanInfoData.fnClearTable();
				for (var t = 0; t < allResult.domainApplicationPlanMapping.length; t++) {
					subscriptionPlanInfoData
							.fnAddData(
								[
								 	allResult.domainApplicationPlanMapping[t].applicationPlanDirectory.applicationDirectory.applicationName,
								 	allResult.domainApplicationPlanMapping[t].applicationPlanDirectory.planName,
								 	allResult.domainApplicationPlanMapping[t].applicationPlanDirectory.applicationDirectory.status,
								 	allResult.domainApplicationPlanMapping[t].applicationPlanDirectory.price,
								 	allResult.domainApplicationPlanMapping[t].applicationPlanDirectory.dataUsage,
								 	allResult.domainApplicationPlanMapping[t].applicationPlanDirectory.planValidityInMonths,
								 	allResult.domainApplicationPlanMapping[t].applicationPlanDirectory.maxNumberOfUser,
								 	allResult.domainApplicationPlanMapping[t].planStartDate,
								 	allResult.domainApplicationPlanMapping[t].planExpiryDate,
								]);
					}
				subscriptionPlanInfoData.fnDraw();
				// adding to the invoiceGenerateInfo table
				invoiceGenerateInfoData.fnClearTable();
				for (var t = 0; t < allResult.allInvoiceDetailsList.length; t++) {
					var isLateFee = isInterest = false;
					if(allResult.allInvoiceDetailsList[t].isInterestApplicable != null && allResult.allInvoiceDetailsList[t].isInterestApplicable != undefined){
						isInterest = allResult.allInvoiceDetailsList[t].isInterestApplicable;
					}
					if(allResult.allInvoiceDetailsList[t].isLateFeesApplicable != null && allResult.allInvoiceDetailsList[t].isLateFeesApplicable != undefined){
						isLateFee = allResult.allInvoiceDetailsList[t].isLateFeesApplicable;
					}
					invoiceGenerateInfoData
							.fnAddData(
								[
								 	allResult.allInvoiceDetailsList[t].invoiceId,
								 	allResult.allInvoiceDetailsList[t].invoiceGeneratedDate,
								 	allResult.allInvoiceDetailsList[t].invoiceDueDate,
								 	allResult.allInvoiceDetailsList[t].status,
								 	isLateFee,
								 	isInterest,
								 	allResult.allInvoiceDetailsList[t].invoiceType,
								]);
					}
				invoiceGenerateInfoData.fnDraw();
				$("#userCount").html(allResult.alluserDetailsList.length);
				$("#budgetCount").html(allResult.allBudgetCodeList.length);
				$("#projectCount").html(allResult.allProjectTypes.length);
				$("#departmentCount").html(allResult.allDepartmentList.length);
				$("#customerCount").html(allResult.customerDetails.length);
				$("#jobsCount").html(allResult.jobDetailList.length);
				$("#vendorsCount").html(allResult.vendorDirectoryList.length);
				$("#contractorCount").html(allResult.contractorDirectoryList.length);
				$("#licenseCount").html(allResult.licenseDirectoryList.length);
				$("#itemCount").html(allResult.itemDbList.length);
		},
		error : function(result){
			/*  console.log(result.message);*/
//			console.log("error");
		}
	});
}