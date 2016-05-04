/**
 * 
 */
pageSetUp();

/*
 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE eg alert("my home function");
 * 
 * var applicationPurchasePlanPagefunction = function() { ... }
 * loadScript("js/plugin/_PLUGIN_NAME_.js",
 * applicationPurchasePlanPagefunction);
 * 
 */

// PAGE RELATED SCRIPTS
// applicationPurchasePlanPagefunction
ApplicationPurchasePlanTable = null;
var laddaButton = 0;

$.root_ = $("body");
// var initApp = function(a) {
// return a.SmartActions = function() {
// var a = {
// alertBox: function(a) {
// $.SmartMessageBox({
// title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
// content: a.data("logout-msg") || "<br>This plan will be "+
// "permanently deleted and cannot be recovered.Only Admin can recover
// it.<br>"+"" +
// "<i class='ace-icon fa fa-hand-o-right blue
// bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete ?</b>",
// buttons: "[No][Yes]"
// }, function(a) {
// if(a=="Yes"){planDelete();}
// });
// }
// };
// $.root_.on("click", '[data-action="alertBox"]', function(b) {
// var c = $(this);
// a.alertBox(c), b.preventDefault(), c = null;
// });
// }, a
// }({});
// jQuery(document).ready(function() {
// initApp.SmartActions();
//       
// });

var applicationPurchasePlanPagefunction = function() {

	/*
	 * // DOM Position key index //
	 * 
	 * l - Length changing (dropdown) f - Filtering input (search) t - The
	 * Table! (datatable) i - Information (records) p - Pagination (paging) r -
	 * pRocessing < and > - div elements <"#id" and > - div with an id <"class"
	 * and > - div with a class <"#id.class" and > - div with an id and class
	 * 
	 * Also see: http://legacy.datatables.net/usage/features
	 */

	/* BASIC ; */
	var responsiveHelper_dt_basic_Plan = undefined;
	var breakpointDefinitionPlan = {
		tablet : 1024,
		phone : 480
	};

	ApplicationPurchasePlanTable = $('#applicationPurchasePlanTable')
			.dataTable(
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
										return full[6];
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
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:planUpdate('"
												+ full[0]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a class='deleteConfirmDialog' title='delete' data-toggle='modal' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setPlanToDelete('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							try {
								// Initialize the responsive datatables helper
								// once.
								if (!responsiveHelper_dt_basic_Plan) {
									responsiveHelper_dt_basic_Plan = new ResponsiveDatatablesHelper(
											$('#applicationPurchasePlanTable'),
											breakpointDefinitionPlan);
								}
							} catch (e) {
								console.log("responsive error");
							}
						},
						"rowCallback" : function(nRow) {
							try {
								responsiveHelper_dt_basic_Plan
										.createExpandIcon(nRow);
							} catch (e) {
								console.log("responsive error");
							}
						},
						"drawCallback" : function(oSettings) {
							try {
								responsiveHelper_dt_basic_Plan.respond();
							} catch (e) {
								console.log("responsive error");
							}
						}
					});
	getApplicationPurchasePlanDetails();

	/* END BASIC */
};
// load related plugins

loadScript("assets/js/datatable/jquery.dataTables.min.js", function() {
	loadScript("assets/js/datatable/dataTables.tableTools.min.js", function() {
		loadScript("assets/js/datatable/dataTables.bootstrap.min.js",
				function() {
					loadScript(
							"assets/js/datatable/datatables.responsive.min.js",
							applicationPurchasePlanPagefunction);
				});
	});
});
function resetPurchaseForm() {
	var validator = $("#managePurchaseForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$('input').val('')
	$("#applicationName").val("");
	$("#planId").val("");
	$("#planName").val("");
	$("#planDescription").val("");
	$("#planAdditionalDescription").val("");
	$("#price").val("");
	$("#dataUsage").val("");
	$("#planValidityInMonths").val("");
	$("#maxNumberOfUser").val("");
	$("#modalTitleId").html("<i class='fa fa-fw fa-lg fa-edit txt-color-blue'></i>Add New Plan");
	$("#saveandcontinue").show();
	$("#save").html("Save");
}

function setPlanToDelete(planId) {
	$.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This Plan will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							deletePlanByPlanId(planId);
						}
					});
};

// Form Validations
$("#managePurchaseForm").validate({
	// Rules for form validation
	rules : {
		applicationName : {
			required : true,
		},

		planDescription : {
			required : true,
			maxlength : 2000
		},
		planName : {
			required : true,
		},
		price : {
			required : true,
			digits : true,
		},
		dataUsage : {
			digits : true,
		},
		planValidityInMonths : {
			required : true,
			digits : true,
		},
		maxNumberOfUser : {
			required : true,
			digits : true,
		}
	},

	// Messages for form validation
	messages : {
		applicationName : {
			required : " Please select the application name",
		},

		planDescription : {
			required : "Enter the description about the plan",

		},
		price : {
			required : "Enter the price of the plan",
			digits : "Enter the price in digits",
		},
		planName : {
			required : "Enter the plan name",
		},
		dataUsage : {
			digits : "Enter the numeric data usage"
		},
		planValidityInMonths : {
			required : "Enter the validity in months",
			digits : "Enter the plan validity in months(in numbers)",
		},
		maxNumberOfUser : {
			required : "Enter the maximum number of users",
			digits : "Enter the maximum number of users in digits",
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForAddNewPurchasePlan(form.id);
	}
});
function submitTypeAppPurch(buttonId, laddaButtonTemp) {

	$("#submitId").val(buttonId);
	laddaButton = laddaButtonTemp;
}
function ajaxCallForAddNewPurchasePlan(formId) {

	var laddaRef = "";
	if (laddaButton != 0) {
		laddaRef = Ladda.create(laddaButton);
		laddaRef.start();
	}

	$
			.ajax({
				url : "addPurchasePlanAction",
				type : "POST",
				data : $("#managePurchaseForm").serialize(),
				success : function(result) {

					if (laddaRef != "") {
						laddaRef.stop();
					}

					if ($("#submitId").val() == "save")
						$("#planModal").modal("hide");
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
						if ($("#planTypeAction").val() == "update") {
							gritterForSucessMsgs("A record of plan type has been successfully updated.");
						} else {
							gritterForSucessMsgs("A record of plan type has been successfully saved.");
						}

						getApplicationPurchasePlanDetails();
						resetPurchaseForm();
					} else {
						$("#planModal").modal("hide");
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ resultant.reason);
					}
				},
				error : function() {
					if (laddaRef != "") {
						laddaRef.stop();
					}
					resetPurchaseForm();

					$("#planModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function getApplicationPurchasePlanDetails() {
	applicationPurchasePlanDetails = "applicationPurchasePlanDetails";
	$
			.ajax({
				url : "getPurchasePlanAction",
				type : "POST",
				contentType : 'text/plain',
				data : '{"applicationPurchasePlanDetails": "'
						+ applicationPurchasePlanDetails + '"}',
				success : function(result) {
					applicationPurchasePlanDetails = JSON.parse(result);
					if (applicationPurchasePlanDetails.ajaxResult == "success") {
						ApplicationPurchasePlanTable.fnClearTable();
						$("#totalPlan")
								.html(
										applicationPurchasePlanDetails.allPlanList.length);
						for (var t = 0; t < applicationPurchasePlanDetails.allPlanList.length; t++) {
							ApplicationPurchasePlanTable
									.fnAddData(
											[
													applicationPurchasePlanDetails.allPlanList[t].planId,
													applicationPurchasePlanDetails.allPlanList[t].planName,
													applicationPurchasePlanDetails.allPlanList[t].planDescription,
													applicationPurchasePlanDetails.allPlanList[t].price,
													applicationPurchasePlanDetails.allPlanList[t].planValidityInMonths,
													applicationPurchasePlanDetails.allPlanList[t].maxNumberOfUser,
													(t + 1) ], false);
						}
						ApplicationPurchasePlanTable.fnDraw();
					}
					else{
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ applicationPurchasePlanDetails.reason);
					}
				}
			});
}

function planUpdate(id) {

	$("#modalTitleId").html("<i class='fa fa-fw fa-lg fa-edit txt-color-blue'></i>Update Plan");
	$("#planModal").modal("show");
	$("#planTypeAction").val("update");
	for (var i = 0; i < applicationPurchasePlanDetails.allPlanList.length; i++) {
		if (id == applicationPurchasePlanDetails.allPlanList[i].planId) {
			$("#applicationName")
					.val(
							applicationPurchasePlanDetails.allPlanList[i].applicationDirectory.applicationId);
			$("#planName").val(
					applicationPurchasePlanDetails.allPlanList[i].planName);
			$("#planDescription")
					.val(
							applicationPurchasePlanDetails.allPlanList[i].planDescription);
			$("#planAdditionalDescription")
					.val(
							applicationPurchasePlanDetails.allPlanList[i].planAdditionalDescription);
			$("#price")
					.val(applicationPurchasePlanDetails.allPlanList[i].price);
			$("#dataUsage").val(
					applicationPurchasePlanDetails.allPlanList[i].dataUsage);
			$("#maxNumberOfUser")
					.val(
							applicationPurchasePlanDetails.allPlanList[i].maxNumberOfUser);
			$("#planValidityInMonths")
					.val(
							applicationPurchasePlanDetails.allPlanList[i].planValidityInMonths);
			$("#planId").val(id);
			$("#save")
					.html("<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
			break;
		}
	}
}

function deletePlanByPlanId(planId) {

	$("#planDeleteModal").modal("hide");
	$("#planTypeAction").val("delete");
	var dataObj = {
		planIdToDel : planId,
	};
	$
			.ajax({
				url : "deletePlanAction",
				type : "POST",
				data : dataObj,
				success : function(result) {
					result = JSON.parse(result);
					resetPurchaseForm();

					// CLearing Department Table
					if (result.ajaxResult == "success") {

						$("#planModal").modal("hide");
						gritterForSucessMsgs("A record of department type has been successfully deleted");
						getApplicationPurchasePlanDetails();
						resetPurchaseForm();
					} else {

						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {

					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
}
