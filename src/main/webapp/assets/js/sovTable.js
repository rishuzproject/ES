/**
 * 
 */

var pagefunction = function() {
	var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	getSessionVariables();
	getUnitAbbreviationList();
	displaySOVDetails();
	// getting comment
	getSOVAllComments();
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
// delete document.ready and use pagefunction..
// $(document).ready(function() {
// getSessionVariables();
// displaySOVDetails();
// });
var myGrid;
var sessionValues;
var abbreviationList;
var jqGridData = [];
function displaySOVDetails() {
	$("#jqg").show();
	myGrid = $("#jQGridDemo");
	myGrid
			.jqGrid({
				url : 'getItemDetailsInSOV',
				editurl : 'addUpdateItemsInSOVTable',
				datatype : "json",
				height : 'auto',
				rowList : [ 9, 18 ],
				colNames : [ "Internal Approval", "External Approval", "Item No.", "Internal Approval Status",
						"Customer Approval Status", "Approval Comments", "Work Description",
						"Billing Type", "Qty", "Unit", "Unit Price",
						"Scheduled Value", "Scheduled Value %", "Material",
						"Labour", "Equipment", "Other", "Total",
						"External Approval Status" ],

				colModel : [
						{
							name : 'approvalInternal',
							index : 'approvalInternal',
							width : 80,
							sortable : false,
							resizable : false,
							search : false,
							editrules : {
								edithidden : false
							},
							formatter : function(cellvalue, options, rowObject) {

								var rowid = rowObject["itemNo"];
								return "<div ><a title='Approve' data-toggle='tooltip' class='blue'  href=\"javascript:openApprovalModal('"
										+ rowid
										+ "')\"> <b class='badge badgeCheck' ><i class='fa fa-check'></i></b></a>&nbsp<a title='Reject' data-toggle='tooltip' class='blue'  href=\"javascript:openRejectModal('"
										+ rowid
										+ "')\"> <b class='badge badgeTimes' ><i class='fa fa-times'></i></b></a></div>";
							}
						},
						{
							name : 'approvalExternal',
							index : 'approvalExternal',
							width : 80,
							sortable : false,
							resizable : false,
							search : false,
							formatter : function(cellvalue, options, rowObject) {

								var rowid = rowObject["itemNo"];
								return "<div ><a title='Approve' data-toggle='tooltip' class='blue'  href=\"javascript:openApprovalModal('"
										+ rowid
										+ "')\"> <b class='badge badgeCheck' ><i class='fa fa-check'></i></b></a>&nbsp&nbsp<a title='Reject' data-toggle='tooltip' class='blue'  href=\"javascript:openRejectModal('"
										+ rowid
										+ "')\"> <b class='badge badgeTimes'><i class='fa fa-times'></i></b></a></div>";
							}
						},
						{
							name : 'itemNo',
							index : 'itemNo',
							key : true,
							hidden : true,
							editable : true,
							editrules : {
								edithidden : false
							}
						},
						{
							name : 'isApprovedInternal',
							index : 'isApprovedInternal',
							formatter : function(cellvalue, options, rowObject) {
								var isApprovedIntern = rowObject["isApprovedInternal"];
								if (isApprovedIntern == 1) {
									return "Approved";
								} else {
									return "Not Approved";
								}
							},
							editable : false,
							editrules : {
								edithidden : false
							}
						},
						{
							name : 'isApprovedExternal',
							index : 'isApprovedExternal',
							formatter : function(cellvalue, options, rowObject) {
								var isApprovedExtern = rowObject["isApprovedExternal"];
								if (isApprovedExtern == 1) {
									return "Approved";
								} else {
									return "Not Approved";
								}
							},
							// hidden : false,
							editable : false,
							editrules : {
								edithidden : false
							}
						},
						{
							name : 'approvalComment',
							index : 'approvalComment',
							hidden : true,
							editable : true,
							editrules : {
								edithidden : false
							}
						},
						{
							name : 'workDescription',
							index : 'workDescription',
							editable : true,
						},
						{
							name : 'billingType',
							index : 'billingType',
							editable : true,
							formatter : 'select',
							edittype : 'select',
							editoptions : {
								dataEvents : [
										{
											type : 'change',
											fn : function(e) {
												if ($("#billingType").val() == '3') {
													$("#tr_materialTotalCost")
															.show();
													$("#tr_laborTotalCost")
															.show();
													$("#tr_equipmentTotalCost")
															.show();
													$("#tr_otherCost").show();
													$("#tr_totalCost").show();
													// $("#jQGridDemo").jqGrid("setColProp",
													// "quantity", {editoptions
													// : {
													// readonly : true,
													// }});
													// set quantity and unit as
													// read only
													$("#quantity").val("100%");
													$("#quantity").prop(
															'readonly', true);
													$("#unit").prop('readonly',
															true);
												} else {
													$("#tr_materialTotalCost")
															.hide();
													$("#tr_laborTotalCost")
															.hide();
													$("#tr_equipmentTotalCost")
															.hide();
													$("#tr_otherCost").hide();
													$("#tr_totalCost").hide();
													// reset quantity and unit
													// as editable
													$("#quantity").val("");
													$("#quantity").prop(
															'readonly', false);
													$("#unit").prop('readonly',
															false);
												}
											}
										}, ]
							},
						},
						{
							name : 'quantity',
							index : 'quantity',
							editable : true,
							editoptions : {
								dataEvents : [
										{
											type : 'change',
											fn : function(e) {
												var qty = parseFloat($(
														"#quantity").val());
												if (isNaN(qty)) {
													qty = 0;
												}
												var unitPrice = parseFloat($(
														"#unitPrice").val());
												if (isNaN(unitPrice)) {
													unitPrice = 0;
												}
												// if percentage type then
												// scheduled value=unit price
												if ($("#billingType").val() == '3') {
													$("#scheduledValue").val(
															unitPrice);
												} else {
													$("#scheduledValue").val(
															qty * unitPrice);
												}
											}
										}, ]
							}
						},
						{
							name : 'unit',
							index : 'unit',
							editable : true,
							formatter : 'select',
							edittype : 'select',
						},
						{
							name : 'unitPrice',
							index : 'unitPrice',
							editable : true,
							editoptions : {
								dataEvents : [
										{
											type : 'change',
											fn : function(e) {
												var qty = parseFloat($(
														"#quantity").val());
												if (isNaN(qty)) {
													qty = 0;
												}
												var unitPrice = parseFloat($(
														"#unitPrice").val());
												if (isNaN(unitPrice)) {
													unitPrice = 0;
												}
												if ($("#billingType").val() == '3') {
													$("#scheduledValue").val(
															unitPrice);
												} else {
													$("#scheduledValue").val(
															qty * unitPrice);
												}
											}
										}, ]
							}
						},
						{
							name : 'scheduledValue',
							index : 'scheduledValue',
							editable : true,
							editoptions : {
								readonly : 'readonly',
							}
						},
						{
							name : 'scheduledValuePcent',
							index : 'scheduledValuePcent',
							// hidden : true,
							editable : true,
							editrules : {
								edithidden : true
							}
						},
						{
							name : 'materialTotalCost',
							index : 'materialTotalCost',
							editable : true,
							// hidden : true,
							editoptions : {
								dataEvents : [
										{
											type : 'change',
											fn : function(e) {
												var materialCost = parseFloat($(
														"#materialTotalCost")
														.val());
												if (isNaN(materialCost)) {
													materialCost = 0;
												}
												var laborCost = parseFloat($(
														"#laborTotalCost")
														.val());
												if (isNaN(laborCost)) {
													laborCost = 0;
												}
												var equipmentCost = parseFloat($(
														"#equipmentTotalCost")
														.val());
												if (isNaN(equipmentCost)) {
													equipmentCost = 0;
												}
												var otherCost = parseFloat($(
														"#otherCost").val());
												if (isNaN(otherCost)) {
													otherCost = 0;
												}
												$("#totalCost").val(
														materialCost
																+ laborCost
																+ equipmentCost
																+ otherCost);
											}
										}, ]
							}
						},
						{
							name : 'laborTotalCost',
							index : 'laborTotalCost',
							editable : true,
							// hidden : true,
							editoptions : {
								dataEvents : [
										{
											type : 'change',
											fn : function(e) {
												var materialCost = parseFloat($(
														"#materialTotalCost")
														.val());
												if (isNaN(materialCost)) {
													materialCost = 0;
												}
												var laborCost = parseFloat($(
														"#laborTotalCost")
														.val());
												if (isNaN(laborCost)) {
													laborCost = 0;
												}
												var equipmentCost = parseFloat($(
														"#equipmentTotalCost")
														.val());
												if (isNaN(equipmentCost)) {
													equipmentCost = 0;
												}
												var otherCost = parseFloat($(
														"#otherCost").val());
												if (isNaN(otherCost)) {
													otherCost = 0;
												}
												$("#totalCost").val(
														materialCost
																+ laborCost
																+ equipmentCost
																+ otherCost);
											}
										}, ]
							}
						},
						{
							name : 'equipmentTotalCost',
							index : 'equipmentTotalCost',
							editable : true,
							// hidden : true,
							editoptions : {
								dataEvents : [
										{
											type : 'change',
											fn : function(e) {
												var materialCost = parseFloat($(
														"#materialTotalCost")
														.val());
												if (isNaN(materialCost)) {
													materialCost = 0;
												}
												var laborCost = parseFloat($(
														"#laborTotalCost")
														.val());
												if (isNaN(laborCost)) {
													laborCost = 0;
												}
												var equipmentCost = parseFloat($(
														"#equipmentTotalCost")
														.val());
												if (isNaN(equipmentCost)) {
													equipmentCost = 0;
												}
												var otherCost = parseFloat($(
														"#otherCost").val());
												if (isNaN(otherCost)) {
													otherCost = 0;
												}
												$("#totalCost").val(
														materialCost
																+ laborCost
																+ equipmentCost
																+ otherCost);
											}
										}, ]
							}
						},
						{
							name : 'otherCost',
							index : 'otherCost',
							editable : true,
							// hidden : true,
							editoptions : {
								dataEvents : [
										{
											type : 'change',
											fn : function(e) {
												var materialCost = parseFloat($(
														"#materialTotalCost")
														.val());
												if (isNaN(materialCost)) {
													materialCost = 0;
												}
												var laborCost = parseFloat($(
														"#laborTotalCost")
														.val());
												if (isNaN(laborCost)) {
													laborCost = 0;
												}
												var equipmentCost = parseFloat($(
														"#equipmentTotalCost")
														.val());
												if (isNaN(equipmentCost)) {
													equipmentCost = 0;
												}
												var otherCost = parseFloat($(
														"#otherCost").val());
												if (isNaN(otherCost)) {
													otherCost = 0;
												}
												$("#totalCost").val(
														materialCost
																+ laborCost
																+ equipmentCost
																+ otherCost);
											}
										}, ]
							}
						}, {
							name : 'totalCost',
							index : 'totalCost',
							editable : true,
							// hidden : true,
							editoptions : {
								readonly : 'readonly'
							}
						}, {
							name : 'externalRequestStatus',
							index : 'externalRequestStatus',
							hidden : true,
							editable : true,
							editrules : {
								edithidden : false
							}
						} ],
				// 'cellsubmit' : 'addUpdateItemsInSOVTable',
				// editurl: 'addUpdateItemsInSOVTable',
				rowNum : 9,
				rownumbers : true,
				sortname : 'id',
				viewrecords : true,
				sortorder : "desc",
				autowidth : true,
				shrinkToFit : false,
				// caption : "SOV Item Details",
				footerrow : true,
				altRows : true,
				loadonce : true,
				jsonReader : {
					repeatitems : false,
					root : function(obj) {
						return obj;
					},
					page : function(obj) {
						return myGrid.jqGrid('getGridParam', 'page');
					},
					total : function(obj) {
						return Math.ceil(obj.length
								/ myGrid.jqGrid('getGridParam', 'rowNum'));
					},
					records : function(obj) {
						return obj.length;
					}
				},
				pager : '#pager',
				gridview : true,
				viewrecords : true,
				loadComplete : function() {
					// view as per user roles
					userRoleView();
					// set calculable and aggregate fields
					setCalculableFields();
					// get billing type list 
					getBillingTypeList();
					//get drop down for unit
					myGrid.jqGrid("setColProp", "unit", { editoptions: { value: abbreviationList.allAbbreviationsList} });
					// put total row on top
					$("div.ui-jqgrid-sdiv").after($("div.ui-jqgrid-bdiv"));
					// change background color of aggregate row
					$(".ui-jqgrid .ui-jqgrid-sdiv .footrow").css("background",
							"#e3f1fd");
				}
			});
	$('#jQGridDemo')
			.jqGrid(
					'navGrid',
					'#pager',
					{
						edit : true,
						add : true,
						del : true,
						search : false,
						// searchtext : "Search",
						addtext : "Add",
						edittext : "Edit",
						deltext : "Delete"
					},
					{// EDIT EVENTS AND PROPERTIES GOES HERE },
						beforeShowForm : function(form) {
							$('#tr_scheduledValuePcent', form).hide();
							if (($("#billingType").val()) == "3") { // if
																	// billing
																	// type
								// is percentage,
								// show the optional
								// fields
								showOptionalFields();
								$("#quantity").val("100%");
								$("#quantity").prop('readonly', true);
								$("#unit").prop('readonly', true);
							} else { // else hide the optional fields
								hideOptionalFields();
							}
						},
						beforeSubmit : function(postdata, formid) {
							// change string data to numeric
							if (postdata.isApprovedInternal == "Approved") {
								postdata.isApprovedInternal = 1;
							} else {
								postdata.isApprovedInternal = 0;
							}
							// check if scheduled value is equal to total cost
							if (postdata.billingType == "3"
									&& postdata.scheduledValue != postdata.totalCost) {
								return [ false,
										"Scheduled Value and Total Value are different. Pleas check" ];
							}
							// convert scheduled value from 100% to 100
							if (postdata.quantity == "100%") {
								postdata.quantity = 100;
							}
							return [ true ];
						},
						afterSubmit : function() {
							// reload grid after every submit
							reloadGrid();
							jqGridData = myGrid.jqGrid("getGridParam", "data");
							return [ true ];
						},
						closeAfterEdit : true,
						dataheight : 250,
						width : 500
					},
					{
						beforeShowForm : function(form) {
							$('#tr_scheduledValuePcent', form).hide();
							hideOptionalFields();
						},
						beforeSubmit : function(postdata, formid) {
							// check if scheduled value is equal to total cost
							if (postdata.billingType == "3"
									&& postdata.scheduledValue != postdata.totalCost) {
								return [ false,
										"Scheduled Value and Total Value are different. Pleas check" ];
							}
							// convert scheduled value from 100% to 100
							if (postdata.quantity == "100%") {
								postdata.quantity = 100;
							}
							return [ true ];
						},
						afterSubmit : function() {
							myGrid.jqGrid('setGridParam', {
								datatype : 'json'
							}).trigger('reloadGrid');
							jqGridData = myGrid.jqGrid("getGridParam", "data");
							return [ true, '', false ]; // no error and no new
														// rowid
						},
						closeAfterAdd : true,
						dataheight : 250,
						width : 500
					}, {
						url : "deleteItemFromSOVTable"
					});
}

function getBillingTypeList(){
	var listData;
	if(sessionValues.selectedProject.sovType == "Hybrid"){
		listData={'' : 'Choose Billing Type',
				'1' : 'Unit',
				'2' : 'Milestone',
				'3' : 'Percentage'};
	}
	else{
		listData={'' : 'Choose Billing Type',
				'1' : sessionValues.selectedProject.sovType};
	}
	myGrid.jqGrid("setColProp", "billingType", { editoptions: { value: listData} });
	
//	$("#jQGridDemo").jqGrid("setColProp", "workDescription", {
//		hidden : true
//	});
}

function setCalculableFields() {
	myGrid.jqGrid("getGridParam", "data")
	jqGridData = myGrid.jqGrid("getGridParam", "data");
	$("#totalItemsInSOV").html(jqGridData.length);
	var scheduledValueTotal = 0, materialTotal = 0, laborTotal = 0, equipmentTotal = 0, otherTotal = 0, percentTotal = 0;
	for (var i = 0; i < jqGridData.length; i++) {
		scheduledValueTotal += jqGridData[i].scheduledValue;
		materialTotal += jqGridData[i].materialTotalCost;
		laborTotal += jqGridData[i].laborTotalCost;
		equipmentTotal += jqGridData[i].equipmentTotalCost;
		otherTotal += jqGridData[i].otherCost;
		percentTotal += jqGridData[i].totalCost;
	}
	var total = "TOTAL";
	var ids = myGrid.getDataIDs();
	for (var i = 0; i < ids.length; i++) {
		schedValPcent = (parseFloat(myGrid.jqGrid('getCell', ids[i],
				'scheduledValue')) / scheduledValueTotal).toFixed(2);
		if (isNaN(schedValPcent)) {
			schedValPcent = 0;
		}
		myGrid.jqGrid('setCell', ids[i], 'scheduledValuePcent', schedValPcent);
		myGrid.jqGrid('footerData', 'set',
				{
					'scheduledValuePcent' : "<span style='color:red'>" + 1,
					'scheduledValue' : "<span style='color:red'>"
							+ scheduledValueTotal,
					'materialTotalCost' : "<span style='color:red'>"
							+ materialTotal,
					'laborTotalCost' : "<span style='color:red'>" + laborTotal,
					'equipmentTotalCost' : "<span style='color:red'>"
							+ equipmentTotal,
					'otherCost' : "<span style='color:red'>" + otherTotal,
					'totalCost' : "<span style='color:red'>" + percentTotal,
					'unitPrice' : "<span style='color:red'>" + total
				});
	}
}

function reloadGrid() {
	myGrid.setGridParam({
		page : 1
	});
	myGrid.jqGrid('setGridParam', {
		datatype : 'json'
	}).trigger('reloadGrid', [ {
		current : true
	} ]);
	return [ true, '', false ]; // no error and no new rowid
}

function showOptionalFields() {
	$("#tr_materialTotalCost").show();
	$("#tr_laborTotalCost").show();
	$("#tr_equipmentTotalCost").show();
	$("#tr_otherCost").show();
	$("#tr_totalCost").show();
}

function hideOptionalFields() {
	$("#tr_materialTotalCost").hide();
	$("#tr_laborTotalCost").hide();
	$("#tr_equipmentTotalCost").hide();
	$("#tr_otherCost").hide();
	$("#tr_totalCost").hide();
}

function approvalModalSubmit() {
	// $('#jQGridDemo').jqGrid('setRowData', rowId, rowData);
	alert($("#itemId").val());
}

function changeColAttr() {
	// $("#jQGridDemo").jqGrid("setColProp", "quantity", {readonly :
	// 'readonly'});
	$("#jQGridDemo").jqGrid("setColProp", "workDescription", {
		hidden : true
	});
}

function openRejectModal(rowId) {
	$("#sovItemRejectionModal").modal("show");
	$("#itemNo").val(rowId);
}

function openApprovalModal(rowId) {
	$("#sovItemApprovalModal").modal("show");
	$("#approvedItemId").val(rowId);
}

// validation for item rejection form
$("#sovItemRejectionForm").validate({
	// Rules for form validation
	rules : {
		approvalComments : {
			required : true,
		}
	},

	// Messages for form validation
	messages : {
		approvalComments : {
			required : "Please enter the comments to reject this item"
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForItemRejection(form.id);
	}
});

// validation for item approval form
$("#sovItemApprovalForm").validate({
	// Rules for form validation
	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForItemApproval(form.id);
	}
});

function resetSovItemRejectionForm() {
	var validator = $("#sovItemRejectionForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$('input').val('');
	$('#approvalComments').val("");
}

function resetSovItemApprovalForm() {
	var validator = $("#sovItemApprovalForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$('input').val('');
	$('#approvalComment').val("");
}

function ajaxCallForItemRejection(formId) {
	// var laddaRef = Ladda.create(laddaButton);
	// laddaRef.start();
	$
			.ajax({
				url : "rejectItemInSOVTable",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					// laddaRef.stop();
					$("#sovItemRejectionModal").modal("hide");
					resetSovItemRejectionForm();
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
						gritterForSucessMsgs("The item is rejected.");
						reloadGrid();
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					// laddaRef.stop();
					resetSovItemRejectionForm();
					$("#sovItemRejectionModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function ajaxCallForItemApproval(formId) {
	// var laddaRef = Ladda.create(laddaButton);
	// laddaRef.start();
	$
			.ajax({
				url : "approveItemInSOVTable",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					// laddaRef.stop();
					$("#sovItemApprovalModal").modal("hide");
					resetSovItemApprovalForm();
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
						gritterForSucessMsgs("The item is approved.");
						reloadGrid();
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					// laddaRef.stop();
					resetSovItemApprovalForm();
					$("#sovItemApprovalModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

// View according to user role
function userRoleView() {
	var gridData = $('#jQGridDemo').jqGrid('getGridParam', 'data');
	// if already sent for approval
	if (sessionValues.selectedSOV.internalApprovalStatus == "REQUEST_FOR_APPROVAL") {
		$('#requestForApproval').prop('disabled', true);
		$('#requestForApproval').html("Sent For Approval");
		if (sessionValues.userRole >= 4) {
			$("#edit_jQGridDemo").addClass('ui-state-disabled');
			$("#add_jQGridDemo").addClass('ui-state-disabled');
			$("#del_jQGridDemo").addClass('ui-state-disabled');
		}
	} else if (sessionValues.selectedSOV.internalApprovalStatus == "APPROVED") {
		$('#requestForApproval').prop('disabled', true);
		$('#requestForApproval').html("Approved");
	}
	// for manager and above
	if (sessionValues.userRole >= 4) {
		$("#jQGridDemo").hideCol("approval");
	} else {
		$('#requestForApproval').hide();
		$("#sendForCustomerApproval").hide();
		for (var i = 0; i < gridData.length; i++) {
			if (gridData[i].externalRequestStatus != "REQUEST_FOR_CUSTOMER_APPROVAL"
					&& gridData[i].isApprovedInternal) {
				$("#sendForCustomerApproval").show();
				$('#sendForCustomerApproval').prop('disabled', false);
				break;
			}
		}
	}
}

function getSessionVariables() {
	$
			.ajax({
				url : "getSessionVariables",
				type : "POST",
				success : function(result) {
					sessionValues = JSON.parse(result);
					if (sessionValues.statusReturned == "200") {

					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ sessionValues.reason);
					}
				},
				error : function() {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function getUnitAbbreviationList() {
	$
	.ajax({
		url : "getUnitAbbreviation",
		type : "POST",
		success : function(result) {
			abbreviationList = JSON.parse(result);
			if (abbreviationList.statusReturned == "200") {
			} else {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ abbreviationList.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
		}
	});
}

function sendRequestForApproval() {
	$
			.ajax({
				url : "requestSOVForApproval",
				type : "POST",
				success : function(result) {
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
						gritterForSucessMsgs("The SOV is requested for approval.");
						$('#requestForApproval').prop('disabled', true);
						$('#requestForApproval').html("Sent For Approval");
						reloadGrid();
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function requestCustApproval() {
	var schedValPcent = 0;
	var ids = myGrid.getDataIDs();
	for (var i = 0; i < ids.length; i++) {
		if (jqGridData[i].isApprovedInternal) {
			schedValPcent += parseFloat(myGrid.jqGrid('getCell', ids[i],
					'scheduledValuePcent'));
		}
	}
	if (schedValPcent != 1) {
		gritterForErrorMsgs("Request cannot be sent. Scheduled Value does not add up to 100%.")
	} else {
		var companyName = "", companyEmail = "";
		var emailBody;
		if (sessionValues.selectedSOV.jobDetail.customerDirectory != null) {
			companyName = sessionValues.selectedSOV.jobDetail.customerDirectory.companyName;
			companyEmail = sessionValues.selectedSOV.jobDetail.customerDirectory.companyEmail;
		}
		emailBody = "Hello, " + companyName + "\n" + sessionValues.emailBody;
		$("#cutomerApprovalEmailTo").val(companyEmail);
		$("#emailLinkActivationDuration")
				.val(
						sessionValues.selectedSOV.jobDetail.activationValidityTimePeriod);
		$("#customerApprovalEmailBody").val(emailBody);
		$("#customerApprovalModal").modal("show");
	}
}

// validation for item approval form
$("#customerApprovalForm")
		.validate(
				{
					// Rules for form validation
					rules : {
						cutomerApprovalEmailTo : {
							required : true,
							email : true
						},
						emailLinkActivationDuration : {
							required : true,
							digits : true
						},
						customerApprovalEmailBody : {
							required : true,
						},
					},

					// Messages for form validation
					messages : {
						cutomerApprovalEmailTo : {
							required : "Please enter the customer email address",
							email : "Please enter a valid email address"
						},
						emailLinkActivationDuration : {
							required : "Please enter the duration for which the link will be active",
							digits : "Please enter a valid number"
						},
						customerApprovalEmailBody : {
							required : "Please enter the body of the email to be sent",
						},
					},
					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					},
					submitHandler : function(form) {
						// form.submit();
						ajaxCallForCustomerApproval(form.id);
					}
				});

function ajaxCallForCustomerApproval(formId) {
	$
			.ajax({
				url : "requestForCustApproval",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					result = JSON.parse(result);
					$("#customerApprovalModal").modal("hide");
					if (result.ajaxResult == "success") {
						gritterForSucessMsgs("The SOV is requested for customer approval.");
						$('#sendForCustomerApproval').prop('disabled', true);
						$('#sendForCustomerApproval').html(
								"Sent For Customer Approval");
						reloadGrid();
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

// START : js for comment in SOV table
function getSOVAllComments() {
	$.ajax({
		url : "getSOVComments",
		type : "POST",
		contentType : "text/plain",
		success : function(e) {
			selIssueDetails = JSON.parse(e);
			console.log(selIssueDetails);
			setCommentDivOnUiElement(selIssueDetails);
			$('.custFind .profile-info-row').first().css('background',
					'rgb(255, 251, 229)');
		},
	});
}

function setCommentToAddForSOV() {
	if ($("#commentTextArea").val() == "") {

		// gritterError("Please enter your comment.");
		alert('Please enter your comment.');
		$("#commentTextArea").focus();

	} else {

		var dataObj = {
			commentToSave : $("#commentTextArea").val()
		}
		$.ajax({
			url : "addSOVComments",
			type : "POST",
			data : dataObj,
			success : function(e) {
				selIssueDetails = JSON.parse(e);
				console.log(selIssueDetails);
				$("#commentTextArea").val("");
				if (selIssueDetails.ajaxResult == "success") {
					$('.commentField').toggleClass('hide', '');
					$('.commentBtn').toggleClass('hide', '');
					getSOVAllComments();
				} else {
					// gritterError("Some Problem Occured. Please refresh the
					// page and try again. If this problem persists, please
					// contact Dev Team.");
				}
			},
			error : function() {
				$("#commentTextArea").val("");
				alert('some problem occured');
				// gritterError("Some Problem Occured. Please refresh the page
				// and try again. If this problem persists, please contact Dev
				// Team.");
			}
		});
	}

}

function addSOVAllComments() {
	$.ajax({
		url : "addSOVComments",
		type : "POST",
		contentType : "text/plain",
		success : function(e) {
			selIssueDetails = JSON.parse(e);
			console.log(selIssueDetails);
			if (selIssueDetails.ajaxresult == "success") {
				getSOVAllComments();
			} else {
				// gritterError("Some Problem Occured. Please refresh the page
				// and try again. If this problem persists, please contact Dev
				// Team.");
			}
		},
		error : function() {
			// gritterError("Some Problem Occured. Please refresh the page and
			// try again. If this problem persists, please contact Dev Team.");
		}
	});
}
function setCommentDivOnUiElement(selIssueDetails) {
	$("#commentsDivId").empty();
	var divBody = document.getElementById("commentsDivId");
	for (var comListLen = 0; comListLen < selIssueDetails.allSOVComments.length; comListLen++) {
		$("#commentsDivId").show();
		var divTag = document.createElement('div');
		divTag.className = 'profile-info-row';
		divTag.innerHTML = '<div class="profile-info-value"><a><span class="commentedPerName"><i class="ace-icon fa fa-user blue"></i> &nbsp;'
				+ selIssueDetails.allSOVComments[comListLen].submittedBy.firstName
				+ ' '
				+ selIssueDetails.allSOVComments[comListLen].submittedBy.lastName
				+ '</span></a> added a comment - <span class="daysBeforeComment" style="color:rgb(188, 188, 188)"></span><div style="padding-top: 4px;padding-left: 12px;"><span class="editable">'
				+ selIssueDetails.allSOVComments[comListLen].supervisorComments
				+ '</span></div></div>';
		divBody.appendChild(divTag);
	}
}

$('.commentBtn').on('click', function() {
	$('.commentField').toggleClass('hide', '');
	$('.commentBtn').toggleClass('hide', '');
});
$('.commentInBtnGroup').on('click', function() {
	if ($('.commentField').hasClass('hide')) {
		$('.commentField').toggleClass('hide', '');
		$('.commentBtn').toggleClass('hide', '');
		$("body").scrollTop(700);
	} else {
	}
});
$('.hideCommentFieldBtn').on('click', function() {
	$('.commentField').toggleClass('hide', '');
	$('.commentBtn').toggleClass('hide', '');
});

////get the header row which contains
//headerRow = $("#jQGridDemo").closest("div.ui-jqgrid-view")
//    .find("table.ui-jqgrid-htable>thead>tr.ui-jqgrid-labels");
//console.log("a");
//// increase the height of the resizing span
//resizeSpanHeight = 'height: ' + 50 +
//    'px !important; cursor: col-resize;';
//headerRow.find("span.ui-jqgrid-resize").each(function () {
//    this.style.cssText = resizeSpanHeight;
//});
//
//// set position of the dive with the column header text to the middle
//rowHight = headerRow.height();
//headerRow.find("div.ui-jqgrid-sortable").each(function () {
//    var ts = $(this);
//    ts.css('top', (rowHight - ts.outerHeight()) / 2 + 'px');
//});

// ENDS : js for comment in SOV table
