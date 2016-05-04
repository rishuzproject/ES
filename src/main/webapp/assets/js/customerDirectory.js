pageSetUp();
// Model i agree button
/*
 * $("#i-agree").click(function() { $this = $("#terms"); if ($this.checked) {
 * $('#myModal').modal('toggle'); } else { $this.prop('checked', true);
 * $('#myModal').modal('toggle'); } });
 */
customerDetailsTable = null;
addressDetailsDataTable = null;
var customerIdToDel = 0;
var laddaButton = 0;
var compAddressArray = [];
var repcompAddressArray = [];

// $.root_ = $("body");
// var initApp = function(a) {
//	
// return a.SmartActions = function() {
// var a = {
// alertBox: function(a) {
// $.SmartMessageBox({
// title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
// content: a.data("logout-msg") || "<br>This customer will be "+
// "permanently deleted . Only Admin can recover it.<br>"+"" +
// "<br/> <i class='ace-icon fa fa-hand-o-right
// bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
// buttons: "[No][Yes]"
// }, function(a) {
// if(a=="Yes"){setCustomerToDelete(customerIdToDel);}
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

$(document).ready(function() {
	// $("#addressSaveButton").hide();
	// $("#addressInfo").hide();
	$("#addressTable").hide();
	$("#dt_basic_wrapper").show();
	getStates();
	// getCities();
});

var pagefunction = function() {
	// console.log("cleared");

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
	var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	customerDetailsTable = $('#customersListTable')
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
										return full[8];
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
										return full[2] == null ? "" : full[2];
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
										return full[6] == null ? "" : full[6];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[7];
									}
								},

								{
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setCustomerToUpdate('"
												+ full[0]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setCustomerId('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#customersListTable'),
										breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});
	customerDetailsTable.fnSetColumnVis(2, false,false);
	customerDetailsTable.fnSetColumnVis(6, false,false);
	addressDetailsDataTable = $('#addressTable')
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
										return full[3];
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
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:addressUpdate('"
												+ full[0]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setAddressId('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#addressTable'),
										breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});
	getCustomersDetails();
	/* getAddressDetails(); */
	/* END BASIC */
};

function getAddressDetails(id) {
	var data = {
		"moduleId" : id,
		"moduleName" : "CUSTOMER_DIRECTORY"
	};
	$
			.ajax({
				url : "getAddressDetails",
				type : "POST",
				data : data,
				success : function(result) {
					addressDetails = JSON.parse(result);
					for (var i = 0; i < addressDetails.allAddressDetails.length; i++) {
						var dataObj = {
							addressId : addressDetails.allAddressDetails[i].addressId,
							moduleId : addressDetails.allAddressDetails[i].moduleId,
							address_line_1 : addressDetails.allAddressDetails[i].address_line_1,
							address_line_2 : addressDetails.allAddressDetails[i].address_line_2,
							state : addressDetails.allAddressDetails[i].state,
							city : addressDetails.allAddressDetails[i].city,
							zipCode : addressDetails.allAddressDetails[i].zipCode,
							addressType : addressDetails.allAddressDetails[i].address_type
						};
						if (addressDetails.allAddressDetails[i].address_type == "Office") {
							showOfficeAddressForm(dataObj);
						} else if (addressDetails.allAddressDetails[i].address_type == "Representative") {
							showRepAddressForm(dataObj);
						}
					}

				},
				error : function(result) {
					console.log(result);
					// gritterForErrorMsgs('Some Problem Occured. Please refresh
					// the page and try again. If this problem persists, please
					// contact Dev Team.');
				}
			});
}
function setCustomerId(cusId) {
	customerIdToDel = cusId;
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This Customer will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							setCustomerToDelete(customerIdToDel);
						}
					});
}
// load related plugins

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

// Validation
$(function() {
	// Validation
	$("#customer_form").validate({

		// Rules for form validation
		rules : {
			companyName : {
				required : true,
				maxlength : 100
			},
			companyNumber : {
				maxlength : 10
			},
			// corporateOfficeAddress : {
			// required : true,
			// maxlength : 200
			// },
			companyEmail : {
//				required : true,
				email : true,
				maxlength : 50
			},
			phoneNumber : {
				required : true,
				digits : true,
				maxlength : 15
			},
			representativeName : {
				required : true,
				maxlength : 100
			},
			representativePhone : {
				required : true,
				digits : true,
				maxlength : 15
			},

		},

		// Messages for form validation
		messages : {
			companyName : {
				required : 'Please enter the company name'
			},
			// corporateOfficeAddress : {
			// required : 'Please enter the company address'
			//						
			// },
			companyEmail : {
//				required : 'Please enter the company email address',
				email : 'Please enter a VALID email address'
			},
			phoneNumber : {
				required : 'Please enter the company phone number',
				digits : 'Please enter valid phone number'

			},
			representativeName : {
				required : 'Please enter a representative name'
			},
			representativePhone : {
				required : 'Please enter the representative phone number',
				digits : 'Please enter valid representative phone number'
			},


		},

		// Ajax form submition
		submitHandler : function(form) {
			ajaxCallForSaveOrUpdateCustomer(form.id);
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());

		}
		});
		
		function ajaxCallForSaveOrUpdateCustomer(formId) {
			if (compAddressArray.length == 0) {
				$('#addressErr').css("display", "block");
			} else {
			var laddaRef = Ladda.create(laddaButton);
			laddaRef.start();
			console.log("The form is   "+formId);
			console.log($("#"+formId).serialize());
			$
					.ajax({
						url : "saveOrUpdateCustomerAction",
						type : "POST",
						data : $("#" + formId).serialize(),
						success : function(result) {
							laddaRef.stop();
							result = JSON.parse(result);
							var actionType = "";
							if($("#companyId").val() != ""){
								actionType = "update";
							}
							if($("#typeOfAction").val() != "saveandcontinue"){
							     $("#customerRemoteModal").modal("hide");
							}
							if (result.ajaxResult == "success") {
								getCustomersDetails();
//								getAddressDetails();
    							saveAddressDetails(result.customerId);
								
								// Resetting Forms
                                if(actionType != ""){
								  gritterForSucessMsgs("Customer has been updated successfully.");
                                } else {
                                	 gritterForSucessMsgs("Customer has been saved successfully.");
                                }
							} else {
								gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
									+ result.reason);
							}
							resetCustomerForm();
							resetAddressForm();
						},
						error : function() {
							laddaRef.stop();
							resetCustomerForm();
							resetAddressForm();
							$("#customerRemoteModal").modal("hide");
							gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						}
					});
		}
		}
		
		function resetCustomerForm(){
			
			$("#save").html("<i class='fa fa-floppy-o'></i> &nbsp; Save");
			$("#saveandcontinue").show();
			$("#modelTitleId").html("Add New Customer");
			var validator = $( "#customer_form" ).validate();
			validator.resetForm();
			$('label').removeClass("state-success");
			$('label').removeClass("state-error");
			$("#companyName").val("");
			$("#companyNumber").val("");
			$("#irs").val("");
			$("#businessType").val("");
			$("#companyId").val("");
			$("#corporateOfficeAddress").val("");
			$("#companyEmail").val("");
			$("#phoneNumber").val("");
			$("#fax").val("");
			$("#representativeName").val("");
			$("#representativePhone").val("");
			$("#representativeAddress").val("");
			$("#companyName").attr("placeholder", "Company Name");
			$("#companyNumber").attr("placeholder", "Company Number");
			$("#irs").attr("placeholder", "IRS");
			$("#businessType").attr("placeholder", "Business Type");
			$("#corporateOfficeAddress").attr("placeholder", "Office Address");
			$("#companyEmail").attr("placeholder", "Company Email");
			$("#phoneNumber").attr("placeholder", "Phone Number");
			$("#fax").attr("placeholder", "Fax Number");
			$("#representativeName").attr("placeholder", "Representative Name");
			$("#representativePhone").attr("placeholder", "Representative Phone");
			$("#representativeAddress").attr("placeholder", "Representative Address");
			// for address table
			resetAddressForm();
			$("#addressInfo").hide();
			resetRepAddressForm();
			$("#repAddressInfo").hide();
			compAddressArray = [];
			repcompAddressArray = [];
			$("#addressDetails").hide();
			$("#addressDetails").html("");
			$("#repAaddressDetails").hide();
			$("#repAaddressDetails").html("");

		}
	});

/*});*/

function ajaxCallForSaveOrUpdateCustomer(formId) {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "saveOrUpdateCustomerAction",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					var actionType = "";
					if ($("#companyId").val() != "") {
						actionType = "update";
					}
					if ($("#typeOfAction").val() != "saveandcontinue") {
						$("#customerRemoteModal").modal("hide");
					}
					if (result.ajaxResult == "success") {
						getCustomersDetails();
						// getAddressDetails();
						saveAddressDetails(result.customerId, addressArray,
								repAddressArray);

						// Resetting Forms
						if (actionType != "") {
							gritterForSucessMsgs("Customer has been updated successfully.");
						} else {
							gritterForSucessMsgs("Customer has been saved successfully.");
						}
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
					resetCustomerForm();
					resetAddressForm();
				},
				error : function() {
					laddaRef.stop();
					resetCustomerForm();
					resetAddressForm();
					$("#customerRemoteModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function resetCustomerForm() {

	$("#save").html("<i class='fa fa-floppy-o'></i> &nbsp; Save");
	$("#saveandcontinue").show();
	$("#modelTitleId").html("Add New Customer");
	var validator = $("#customer_form").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#companyName").val("");
	$("#companyNumber").val("");
	$("#irs").val("");
	$("#businessType").val("");
	$("#companyId").val("");
	$("#corporateOfficeAddress").val("");
	$("#companyEmail").val("");
	$("#phoneNumber").val("");
	$("#fax").val("");
	$("#representativeName").val("");
	$("#representativePhone").val("");
	$("#representativeAddress").val("");
	$("#companyName").attr("placeholder", "Company Name");
	$("#companyNumber").attr("placeholder", "Company Number");
	$("#irs").attr("placeholder", "IRS");
	$("#businessType").attr("placeholder", "Business Type");
	$("#corporateOfficeAddress").attr("placeholder", "Office Address");
	$("#companyEmail").attr("placeholder", "Company Email");
	$("#phoneNumber").attr("placeholder", "Phone Number");
	$("#fax").attr("placeholder", "Fax Number");
	$("#representativeName").attr("placeholder", "Representative Name");
	$("#representativePhone").attr("placeholder", "Representative Phone");
	$("#representativeAddress").attr("placeholder", "Representative Address");
	// for address table
	resetAddressForm();
	$("#addressInfo").hide();
	resetRepAddressForm();
	$("#repAddressInfo").hide();
	addressArray = [];
	repAddressArray = [];
	$("#addressDetails").hide();
	$("#addressDetails").html("");
	$("#repAaddressDetails").hide();
	$("#repAaddressDetails").html("");
}
function getCustomersDetails() {
	customersList = "customer";
	$
			.ajax({
				url : "getAllCustomersDetails",
				type : "POST",
				contentType : 'text/plain',
				data : '{"customersList": "' + customersList + '"}',
				success : function(result) {
					customersList = JSON.parse(result);
					customerDetailsTable.fnClearTable();
					for (var t = 0; t < customersList.customerDetails.length; t++) {
						customerDetailsTable
								.fnAddData(
										[
												customersList.customerDetails[t].companyId,
												customersList.customerDetails[t].companyName,
												customersList.customerDetails[t].corporateOfficeAddress,
												customersList.customerDetails[t].companyEmail,
												customersList.customerDetails[t].phoneNumber,
												customersList.customerDetails[t].representativeName,
												customersList.customerDetails[t].representativeAddress,
												customersList.customerDetails[t].representativePhone,
												t + 1 ], false);
					}
					customerDetailsTable.fnDraw();
					$("#totalCustomers").text(
							customersList.customerDetails.length);
				}
			});

}
function setCustomerToUpdate(compId) {

	$("#customerRemoteModal").modal("show");
	$("#typeOfAction").val("update");
	for (var u = 0; u < customersList.customerDetails.length; u++) {
		if (compId == customersList.customerDetails[u].companyId) {
			var faxTemp, repAddr, compNumber, irsTemp, bussType;
			faxTemp = repAddr = compNumber = irsTemp = bussType = "";
			if (customersList.customerDetails[u].fax != undefined
					&& customersList.customerDetails[u].fax != null) {
				faxTemp = customersList.customerDetails[u].fax;
			}
			if (customersList.customerDetails[u].representativeAddress != undefined
					&& customersList.customerDetails[u].representativeAddress != null) {
				repAddr = customersList.customerDetails[u].representativeAddress;
			}
			if (customersList.customerDetails[u].companyNumber != undefined
					&& customersList.customerDetails[u].companyNumber != null) {
				compNumber = customersList.customerDetails[u].companyNumber;
			}
			if (customersList.customerDetails[u].irs != undefined
					&& customersList.customerDetails[u].irs != null) {
				irsTemp = customersList.customerDetails[u].irs;
			}
			if (customersList.customerDetails[u].businessType != undefined
					&& customersList.customerDetails[u].businessType != null) {
				bussType = customersList.customerDetails[u].businessType;
			}
			$("#companyName").val(customersList.customerDetails[u].companyName);
			$("#corporateOfficeAddress").val(
					customersList.customerDetails[u].corporateOfficeAddress);
			$("#companyEmail").val(
					customersList.customerDetails[u].companyEmail);
			$("#phoneNumber").val(customersList.customerDetails[u].phoneNumber);
			$("#fax").val(faxTemp);
			$("#representativeName").val(
					customersList.customerDetails[u].representativeName);
			$("#representativePhone").val(
					customersList.customerDetails[u].representativePhone);
			$("#representativeAddress").val(repAddr);
			$("#companyNumber").val(compNumber);
			$("#irs").val(irsTemp);
			$("#businessType").val(bussType);
			$("#companyId").val(compId);
			$("#save")
					.html("<i class='fa fa-thumbs-up bigger-110'></i> Update");
			document.getElementById("saveandcontinue").style.display = "none";
			$("#modelTitleId").html("Update Customer");
			break;
		}
	}
	getAddressDetails(compId);
}

function setCustomerToDelete(compId) {
	$("#companyId").val(compId);
	var dataObj = {
		compIdToDel : customerIdToDel,
	};
	deleteAddress(compId, [], "");

	$
			.ajax({
				url : "customerDeleteAction",
				type : "POST",
				data : dataObj,
				success : function(result) {
					result = JSON.parse(result);
					// CLearing Project Table
					if (result.ajaxResult == "success") {
						getCustomersDetails();
						gritterForSucessMsgs("Customer has been deleted successfully.");
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
//function saveAddressDetails(id, officeAddressArray, repAddressArray) {
//	var addressData = {};
//	var address = [];
//	addressData.moduleId = id;
//	addressData.moduleName = "customer_Directory";
//	alert(addressData.moduleName);
//	address.push(addressArray);
//	address.push(repAddressArray);
//	addressData.address = address;
//	var jobDetails = '{"addressData" : ' + JSON.stringify(addressData) + '}';
//	$
//			.ajax({
//				url : "saveAddressDetails",
//				data : jobDetails,
//				type : 'POST',
//				contentType : 'application/json',
//				success : function(data) {
//					var data = JSON.parse(data);
//					// $("#" + name).val("");
//					// getAllJobDocuments(jobId);
//					if (data.ajaxResult != "success") {
//						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
//								+ data.reason);
//					}
//				},
//				error : function() {
//					alert("Some problem occured");
//				}
//			});
//
//	}
function saveAddressDetails(id){
	var addressData= {};
	var address = [];
	addressData.moduleId = id;
	addressData.moduleName = "customer_Directory";
	address.push( compAddressArray);
	address.push( repcompAddressArray);
	addressData.address = address;
	var module = '{"addressData" : '+JSON.stringify(addressData)+'}';
	$
	.ajax({
		url : "saveAddressDetails",
		data : module,
		type : 'POST',
		contentType:'application/json',
		success : function(data) {
			var data = JSON.parse(data);
//			$("#" + name).val("");
//			getAllJobDocuments(jobId);
			if (data.ajaxResult != "success") {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ data.reason);
			}
		},
		error : function() {
			alert("Some problem occured");
		}
	});

}
function setActionType(buttonId, laddaButtonTemp) {
	$("#typeOfAction").val(buttonId);
	laddaButton = laddaButtonTemp;
}

/**
 * Resetting Customer Form
 */
function resetCustomerUploadForm() {
	$("#confirmUploadId").val("-1");
	$("#fileName").val(null);
}

$.validator.addMethod("checkSize", function(value, element) {

	var size = element.files[0].size;
	if (size > 15728640) {
		return false;
	} else {
		return true;
	}
}, "Please upload a file with size less than 15 MB");

$("#customerUploadForm").validate({
	rules : {
		templateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		templateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		// console.log("ajax call for template");
		ajaxCallForAddTemplate(form.id);
	}
});

/**
 * Function for save and validate excel
 * 
 * @param formId
 */
function ajaxCallForAddTemplate(formId) {
	// console.log("in ajax call for template");
	$("#className").val("CUSTOMER_EXCEL_FORMAT");
	var msg = "";
	$
			.ajax({
				url : 'projectTemplateController',
				data : new FormData($("#customerUploadForm")[0]),
				cache : false,
				contentType : false,
				processData : false,
				type : 'POST',
				success : function(data) {
					$("#customerUploadModal").modal("hide");
					result = JSON.parse(data);
					if (result.ajaxResult == "failure") {
						resetCustomerUploadForm();
						var out = document.getElementById("errorBlock");
						out.innerHTML = "";

						if (result.reason[0].colNumber == -1) {
							for (i = 0; i < result.reason.length; i++) {
								out
										.appendChild(document
												.createTextNode("Row : "
														+ result.reason[i].rowNumber
														+ " Error :"
														+ result.reason[i].errorMessage));
								out.appendChild(document.createElement("br"));
							}
							$("#customerConfirmationHeader").hide();
							$("#saveUpload").hide();
							$("#cancelCustomerUpload").html("Close");
							getCustomersDetails();
						}

						else if (result.reason[0].excelCell == "A1") {
							for (i = 0; i < result.reason.length; i++) {
								out
										.appendChild(document
												.createTextNode(" Error :"
														+ result.reason[i].errorMessage));
								out.appendChild(document.createElement("br"));
							}
							$("#saveUpload").hide();
						} else {
							for (i = 0; i < result.reason.length; i++) {
								if (result.reason[i].excelCell == undefined) {
									out
											.appendChild(document
													.createTextNode(" Row Number :"
															+ result.reason[i].rowNumber
															+ " Error :"
															+ result.reason[i].errorMessage));
								} else {
									out
											.appendChild(document
													.createTextNode(" Cell Address :"
															+ result.reason[i].excelCell
															+ " Error :"
															+ result.reason[i].errorMessage));
								}
								out.appendChild(document.createElement("br"));
							}
						}
						$("#customerUploadConfirmation").modal("show");
						$("#errorSection").css('display', 'block');

					} else if (result.ajaxResult == "success") {
						gritterForSucessMsgs("A file of customer type has been successfully added");
						resetCustomerUploadForm();
						getCustomersDetails();
						$("#customerUploadConfirmation").modal("hide");
						cancelCustomerUploadForm();
					} else if (result.ajaxResult == "failure") {
						gritterForErrorMsgs("An error occurred : "
								+ result.reason);
					} else {
						gritterForErrorMsgs("Could not be saved.Contact dev");
					}
				},
				error : function() {
					gritterForErrorMsgs("Some problem occured");
				}
			});

	// var html = '';
	// var strVal = 0;
	// for (var event in response) {
	// if(event != "success"){
	// var dataCopy = response[event];
	// //setting file name in error block
	// html += event+".<br />";
	// for (var str in dataCopy) {
	// var mainData = dataCopy[str];
	// //mainData["fileName"] will be undefined if there is no header error
	// if(mainData["fileName"] == undefined){
	// //setting error message
	// html += "&nbsp;&nbsp; Error in row &nbsp;"+mainData["row"]+" , and
	// &nbsp;Cell "+"&nbsp;"+mainData["cell"]+" : "+mainData["error"] +".<br
	// />";
	// }else{
	// //setting error if Header is not valid
	// strVal = 1;
	// html += "&nbsp;&nbsp; Header is not valid in
	// &nbsp;"+mainData["fileName"]+".<br />";
	// break;
	// }
	// }
	// $("#errorBlock").html(html);
	// $("#errorSection").css('display','block');
	// if(strVal == 1){
	// $("#saveValidate").css('display','none');
	// $("#saveUpload").css('display','none');
	// }else{
	// $("#saveUpload").css('display','block');
	// $("#saveValidate").css('display','none');
	// }
	// }else{
	// resetCustomerUploadForm();
	// $("#customerUploadModal").modal("hide");
	// gritterForSucessMsgs("Customer has been added successfully.");
	// }
	// }
	// getCustomersDetails();
	//			  
	// },
	// error : function() {
	// alert("Some problem occured");
	// }
	// });
	// }
}
/**
 * Function for clear session attribute
 */
function clearList() {
	$.ajax({
		url : 'saveExcelUploadController',
		data : new FormData($("#customerUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(result) {

		}
	});
}

/**
 * Function will be called on click of continue
 * 
 * @param buttonId
 */
function setCustomerUploadConfirmation(buttonId) {
	// $("#customerUploadConfirmation").modal("hide");
	// console.log("setting");
	if (buttonId == "saveUpload") {
		$("#confirmUploadId").val(1);
		ajaxCallForAddTemplate(buttonId);
	} else {
		$("#confirmUploadId").val(-1);
	}
}

/**
 * Function for remove file after upload
 * 
 * @param fileName
 */
function removeUploadedFile(fileName) {

	$.ajax({
		url : 'deleteUploadFileController',
		data : new FormData($("#customerUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(result) {

		},
		error : function() {
			gritterForErrorMsgs("Some problem occured");
		}
	});
}

function resetCustomerConfirmationModal() {

	$("#saveUpload").show();
	$("#customerConfirmationHeader").show();
	$("#cancelCustomerUpload").html("Cancel");
	$("#customerUploadConfirmation").modal("hide");
}

function cancelCustomerUploadForm() {
	var validator = $("#customerUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}
function saveAddressForm() {
	var dataObj = {
		address_line_1 : $("#address_line_1").val(),
		address_line_2 : $("#address_line_2").val(),
		state : $("#state option:selected").text(),
		city : $("#city option:selected").text(),
		zipCode : $("#zipCode").val(),
		addressType : "Office"
	};
	showOfficeAddressForm(dataObj);
}
function showOfficeAddressForm(dataObj) {
	var fullAddress = "";
	if (dataObj.address_line_1 != "") {
		fullAddress += dataObj.address_line_1 + ", ";
	}else{
		$("#addressLine1Err").css("display","block");
		$("#addressLine1Err").prev().addClass("state-error");
	}
	if (dataObj.address_line_2 != "") {
		fullAddress += dataObj.address_line_2 + ", ";
	}
	if (dataObj.state != "" && dataObj.state != "Select State") {
		fullAddress += dataObj.state + ", ";
	}else{
		$("#addressStateErr").css("display","block");
		$("#addressStateErr").prev().addClass("state-error");
	}
	if (dataObj.city != "" && dataObj.city != "Select City") {
		fullAddress += dataObj.city + ", ";
	}else{
		$("#addressCityErr").css("display","block");
		$("#addressCityErr").prev().addClass("state-error");
	}
	if (dataObj.zipCode != "") {
		fullAddress += dataObj.zipCode + ", "
	}else{
		$("#addressZipCodeErr").css("display","block");
		$("#addressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
		fullAddress = fullAddress.substr(0, fullAddress.length - 2);
		compAddressArray.push(dataObj);
		var id = compAddressArray.length - 1;
		var file = "<section class='col col-lg-12' id='address-"
				+ id
				+ "'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showAddress("
				+ id
				+ ")' style='cursor:pointer;'\>"
				+ fullAddress
				+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setAddressToDelete("
				+ id + ");'></i></p></section>";
	
		$("#addressDetails").append(file);
		$("#addressDetails").show();
		$("#addressInfo").hide();
		resetAddressForm();
	}
}

function saveRepAddressForm() {
	var dataObj = {
		address_line_1 : $("#repAddress_line_1").val(),
		address_line_2 : $("#repAddress_line_2").val(),
		state : $("#repState option:selected").text(),
		city : $("#repCity option:selected").text(),
		zipCode : $("#repZipCode").val(),
		addressType : "Representative"
	};
	showRepAddressForm(dataObj);
}
function showRepAddressForm(dataObj) {
	var fullAddress = "";
	if (dataObj.address_line_1 != "") {
		fullAddress += dataObj.address_line_1 + ", "
	}else{
		$("#repAddressLine1Err").css("display","block");
		$("#repAddressLine1Err").prev().addClass("state-error");
	}
	if (dataObj.address_line_2 != "") {
		fullAddress += dataObj.address_line_2 + ", "
	}
	if (dataObj.state != "" && dataObj.state != "Select State") {
		fullAddress += dataObj.state + ", "
	}else{
		$("#repAddressStateErr").css("display","block");
		$("#repAddressStateErr").prev().addClass("state-error");
	}
	if (dataObj.city != "" && dataObj.city != "Select City") {
		fullAddress += dataObj.city + ", "
	}else{
		$("#repAddressCityErr").css("display","block");
		$("#repAddressCityErr").prev().addClass("state-error");
	}
	if (dataObj.zipCode != "") {
		fullAddress += dataObj.zipCode + ", "
	}else{
		$("#repAddressZipCodeErr").css("display","block");
		$("#repAddressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
		fullAddress = fullAddress.substr(0, fullAddress.length - 2);
		repcompAddressArray.push(dataObj);
		var id = repcompAddressArray.length - 1;
		var file = "<section class='col col-lg-12' id='repAddress-"+id+"'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showRepAddress("+id+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='deleteRepAddress("+id+ ");'></i></p></section>";
	
		$("#repAaddressDetails").append(file);
		$("#repAaddressDetails").show();
		$("#repAddressInfo").hide();
		resetRepAddressForm();
	}
}



function showAddress(id){
	var object = compAddressArray[id];

	$("#addressFormId").val(id);
	$("#address_line_1").val(object.address_line_1);
	$("#address_line_2").val(object.address_line_2);
	$("#state option").each(function() {
		if ($(this).text() == object.state) {
			$(this).attr('selected', 'selected');
			var value = $(this).val();
			flag = "city";
			getCities(value, flag, object.city);
		}
	});
	$('#zipCode').val(object.zipCode);
	$("#addressInfo").show();
	$("#addressSaveButton").hide();
	$("#addressUpdateButton").show();
}


function showRepAddress(id){
	var object = repcompAddressArray[id];
	$("#repAddressFormId").val(id);
	$("#repAddress_line_1").val(object.address_line_1);
	$("#repAddress_line_2").val(object.address_line_2);
	$("#repState option").each(function() {
		if ($(this).text() == object.state) {
			$(this).attr('selected', 'selected');
			var value = $(this).val();
			flag = "repCity";
			getCities(value, flag, object.city);
		}
	});
	$('#repZipCode').val(object.zipCode);
	$("#repAddressInfo").show();
	$("#repAddressSaveButton").hide();
	$("#repAddressUpdateButton").show();
}

function updateAddressForm() {
	var addressId = $("#addressFormId").val();
	compAddressArray[addressId].address_line_1 = $("#address_line_1").val();
	compAddressArray[addressId].address_line_2 = $("#address_line_2").val();
	compAddressArray[addressId].state = $("#state option:selected").text();
	compAddressArray[addressId].city = $("#city option:selected").text();
	compAddressArray[addressId].zipCode = $("#zipCode").val();
	var fullAddress = "";

	if(compAddressArray[addressId].address_line_1 !=""){
		fullAddress+= compAddressArray[addressId].address_line_1 + ", "
	}else{
		$("#addressLine1Err").css("display","block");
		$("#addressLine1Err").prev().addClass("state-error");
	}
	if(compAddressArray[addressId].address_line_2 !=""){
		fullAddress+= compAddressArray[addressId].address_line_2 + ", "
	}
	if(compAddressArray[addressId].state !="" && compAddressArray[addressId].state !="Select State"){
		fullAddress+= compAddressArray[addressId].state + ", "
	}else{
		$("#addressStateErr").css("display","block");
		$("#addressStateErr").prev().addClass("state-error");
	}
	if(compAddressArray[addressId].city !="" && compAddressArray[addressId].city !="Select City"){
		fullAddress+= compAddressArray[addressId].city + ", "
	}else{
		$("#addressCityErr").css("display","block");
		$("#addressCityErr").prev().addClass("state-error");
	}
	if(compAddressArray[addressId].zipCode !=""){
		fullAddress+= compAddressArray[addressId].zipCode + ", "

	}else{
		$("#addressZipCodeErr").css("display","block");
		$("#addressZipCodeErr").prev().addClass("state-error");
	}
	if(compAddressArray[addressId].zipCode != "" && compAddressArray[addressId].address_line_1 != "" && compAddressArray[addressId].state != "" && compAddressArray[addressId].state != "Select State"
		&& compAddressArray[addressId].city != "" && compAddressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showAddress("
			+ addressId
			+ ")' style='cursor:pointer;'\>"
			+ fullAddress
			+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setAddressToDelete("
			+ addressId + ");'></i></p>"
	$("#address-" + addressId).html("");
	$("#address-" + addressId).html(updateFile);
	resetAddressForm();
	$("#addressInfo").hide();
	}
}

function updateRepAddressForm() {
	var addressId = $("#repAddressFormId").val();
	repcompAddressArray[addressId].address_line_1 = $("#repAddress_line_1").val();
	repcompAddressArray[addressId].address_line_2 = $("#repAddress_line_2").val();
	repcompAddressArray[addressId].state = $("#repState option:selected").text();
	repcompAddressArray[addressId].city = $("#repCity option:selected").text();
	repcompAddressArray[addressId].zipCode = $("#repZipCode").val();
	var fullAddress = "";

	if(repcompAddressArray[addressId].address_line_1 !=""){
		fullAddress+= repcompAddressArray[addressId].address_line_1 + ", "
	}else{
		$("#repAddressLine1Err").css("display","block");
		$("#repAddressLine1Err").prev().addClass("state-error");
	}
	if(repcompAddressArray[addressId].address_line_2 !=""){
		fullAddress+= repcompAddressArray[addressId].address_line_2 + ", "
	}
	if(repcompAddressArray[addressId].state !="" && repcompAddressArray[addressId].state !="Select State"){
		fullAddress+= repcompAddressArray[addressId].state + ", "
	}else{
		$("#repAddressStateErr").css("display","block");
		$("#repAddressStateErr").prev().addClass("state-error");
	}
	if(repcompAddressArray[addressId].city !="" && repcompAddressArray[addressId].city !="Select City"){
		fullAddress+= repcompAddressArray[addressId].city + ", "
	}else{
		$("#repAddressCityErr").css("display","block");
		$("#repAddressCityErr").prev().addClass("state-error");
	}
	if(repcompAddressArray[addressId].zipCode !=""){
		fullAddress+= repcompAddressArray[addressId].zipCode + ", "

	}else{
		$("#repAddressZipCodeErr").css("display","block");
		$("#repAddressZipCodeErr").prev().addClass("state-error");
	}
	if(repcompAddressArray[addressId].zipCode != "" && repcompAddressArray[addressId].address_line_1 != "" && repcompAddressArray[addressId].state != "" && repcompAddressArray[addressId].state != "Select State"
		&& repcompAddressArray[addressId].city != "" && repcompAddressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showRepAddress("
			+ addressId
			+ ")' style='cursor:pointer;'\>"
			+ fullAddress
			+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='deleteRepAddress("
			+ addressId + ");'></i></p>"
	$("#repAddress-" + addressId).html("");
	$("#repAddress-" + addressId).html(updateFile);
	resetAddressForm();
	$("#repAddressInfo").hide();
	}
}


function setAddressToDelete(id){
	if(compAddressArray[id].addressId != undefined){
		deleteAddress(compAddressArray[id].addressId ,id,"office");
	}else{
		$("#address-"+id).remove();
		delete compAddressArray[id];
		compAddressArray.pop(id);
	}
	
//	console.log(compAddressArray);
}

function deleteRepAddress(id){
	if(repcompAddressArray[id].addressId != undefined){
		deleteAddress(repcompAddressArray[id].addressId,id ,"represenetative");
	}else{
		$("#repAddress-"+id).remove();
		delete repcompAddressArray[id];
		repcompAddressArray.pop(id);
	}
}

function deleteAddress(id, addressId, type) {
	var data = {
		"moduleId" : id
	};
	$.ajax({
		url : "deleteAddress",
		type : "POST",
		data : data,
		success : function(result) {
			result = JSON.parse(result);

			if(result.ajaxResult == "success"){
				if(type=="represenetative"){
					$("#repAddress-"+addressId).remove();
					delete repcompAddressArray[addressId];
					repcompAddressArray.pop(id);
				}
				else if(type=="office"){
					$("#address-"+addressId).remove();
					delete compAddressArray[addressId];
					compAddressArray.pop(id);

				}
//				gritterForSucessMsgs("Address has been deleted successfully");
			}
		},
		error : function(result) {
			console.log(result);
			// gritterForErrorMsgs('Some Problem Occured. Please refresh the
			// page and try again. If this problem persists, please contact Dev
			// Team.');
		}
	});
}
$("#address_line_1").on("change keyup",function(){
	if($("#address_line_1").val() != ""){
		$("#address_line_1").parent().removeClass("state-error");
		$('#addressLine1Err').css('display', 'none');
	}
});
$("#state").on("change keyup",function(){
	if($("#state").val() != ""){
		$("#state").parent().removeClass("state-error");
		$('#addressStateErr').css('display', 'none');
	}
});
$("#city").on("change keyup",function(){
	if($("#city").val() != ""){
		$("#city").parent().removeClass("state-error");
		$('#addressCityErr').css('display', 'none');
	}
});
$("#zipCode").on("change keyup",function(){
	if($("#zipCode").val() != ""){
		$("#zipCode").parent().removeClass("state-error");
		$('#addressZipCodeErr').css('display', 'none');
	}
});
function resetAddressForm() {
	$("#addressSaveButton").show();
	$("#addressUpdateButton").hide();
	$("#address_line_1").val("");
	$("#address_line_1").parent().removeClass("state-success");
	$("#address_line_2").val("");
	$("#address_line_2").parent().removeClass("state-success");
	$("#state").val("");
	$("#state").parent().removeClass("state-success");
	$("#city").find('option').remove().end().append(
			'<option value="">Select City</option>');
	$("#city").parent().removeClass("state-success");
	$('#zipCode').val("");
	$("#zipCode").parent().removeClass("state-success");
	$('#addressErr').css("display", "none");
	$("#address_line_1").parent().removeClass("state-error");
	$('#addressLine1Err').css('display', 'none');
	$("#state").parent().removeClass("state-error");
	$('#addressStateErr').css('display', 'none');
	$("#city").parent().removeClass("state-error");
	$('#addressCityErr').css('display', 'none');
	$("#zipCode").parent().removeClass("state-error");
	$('#addressZipCodeErr').css('display', 'none');
}
$("#repAddress_line_1").on("change keyup",function(){
	if($("#repAddress_line_1").val() != ""){
		$("#repAddress_line_1").parent().removeClass("state-error");
		$('#repAddressLine1Err').css('display', 'none');
	}
});
$("#repState").on("change keyup",function(){
	if($("#repState").val() != ""){
		$("#repState").parent().removeClass("state-error");
		$('#repAddressStateErr').css('display', 'none');
	}
});
$("#repCity").on("change keyup",function(){
	if($("#repCity").val() != ""){
		$("#repCity").parent().removeClass("state-error");
		$('#repAddressCityErr').css('display', 'none');
	}
});
$("#repZipCode").on("change keyup",function(){
	if($("#repZipCode").val() != ""){
		$("#repZipCode").parent().removeClass("state-error");
		$('#repAddressZipCodeErr').css('display', 'none');
	}
});
function resetRepAddressForm() {
	$("#repAddressSaveButton").show();
	$("#repAddressUpdateButton").hide();
	$("#repAddress_line_1").val("");
	$("#repAddress_line_1").parent().removeClass("state-success");
	$("#repAddress_line_2").val("");
	$("#repAddress_line_2").parent().removeClass("state-success");
	$("#repState").val("");
	$("#repState").parent().removeClass("state-success");
	$('#repCity').find('option').remove().end().append(
			'<option value="">Select City</option>');
	$("#repCity").parent().removeClass("state-success");
	$('#repZipCode').val("");
	$("#repZipCode").parent().removeClass("state-success");
	$("#repAddress_line_1").parent().removeClass("state-error");
	$('#repAddressLine1Err').css('display', 'none');
	$("#repState").parent().removeClass("state-error");
	$('#repAddressStateErr').css('display', 'none');
	$("#repCity").parent().removeClass("state-error");
	$('#repAddressCityErr').css('display', 'none');
	$("#repZipCode").parent().removeClass("state-error");
	$('#repAddressZipCodeErr').css('display', 'none');
}

function addressUpdate(addressId) {

	for (var u = 0; u < addressDetails.allAddressDetails.length; u++) {
		if (addressId == addressDetails.allAddressDetails[u].addressId) {
			$("#addressId").val(addressDetails.allAddressDetails[u].addressId);
			$("#addressType").val(
					addressDetails.allAddressDetails[u].localAddressType);
			$("#address").val(addressDetails.allAddressDetails[u].address);
			break;
		}
	}
}

function getStates() {
	// console.log('enetered')
	$.ajax({
		url : "getStates",
		type : "GET",
		success : function(result) {
			statesData = JSON.parse(result);
			if (statesData.ajaxResult == "success") {
				var elementId = document.getElementById("state");
				var repState = document.getElementById("repState");
				elementId.options.length = 1;
				repState.options.length = 1;
				for (var i = 0; i < statesData.reason.length; i++) {
					var optn = document.createElement("OPTION");
					var repOptn = document.createElement("OPTION");
					optn.text = statesData.reason[i].state;
					optn.value = statesData.reason[i].state_Code;
					repOptn.text = statesData.reason[i].state;
					repOptn.value = statesData.reason[i].state_Code;
					elementId.options.add(optn);
					repState.options.add(repOptn);
				}
			}
		},
		error : function() {
			console.log("error");
		}
	});
}

$("#repState").on(
		"change",
		function(e) {
			var value = $("#repState").val();
			flag = "repCity";
			if (value != "") {
				getCities(value, flag);
			} else {
				$('#repCity').find('option').remove().end().append(
						'<option value="">Select City</option>');
			}
		});

$("#state").on(
		"change",
		function(e) {
			var value = $("#state").val();
			flag = "city";
			if (value != "") {
				getCities(value, flag);
			} else {
				$('#city').find('option').remove().end().append(
						'<option value="">Select City</option>');
			}
		});

function getCities(value, flag, cityName) {
	$.ajax({
		url : "getCities",
		type : "GET",
		data : {
			"stateCode" : value
		},
		success : function(result) {
			citiesData = JSON.parse(result);
			// console.log(responseData)
			if (citiesData.ajaxResult == "success") {
				var elementId = document.getElementById(flag);
				elementId.options.length = 1;
				for (var i = 0; i < citiesData.reason.length; i++) {
					var optn = document.createElement("OPTION");
					optn.text = citiesData.reason[i];
					optn.value = i;
					elementId.options.add(optn);
				}
				if (cityName != undefined) {
					$("#" + flag + " option").each(function() {
						if ($(this).text() == cityName) {
							$(this).attr('selected', 'selected');
						}
					});
				}
			}
		},
		error : function() {
			console.log("error");
		}
	});
}

/*
 * $('#selectState').change(function() { alert('The option with value ' +
 * $(this).val() + ' and text ' + $(this).text() + ' was selected.'); });
 */
