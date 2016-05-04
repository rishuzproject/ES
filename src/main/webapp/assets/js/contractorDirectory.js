pageSetUp();
		// Model i agree button
	/*
	 * $("#i-agree").click(function() { $this = $("#terms"); if ($this.checked) {
	 * $('#myModal').modal('toggle'); } else { $this.prop('checked', true);
	 * $('#myModal').modal('toggle'); } });
	 */
addressDetailsDataTable = null;
contractorDetailsTable = null;
var contractorIdToDel = 0;
var addressIdToDel = 0;
var laddaButton = 0;
var addressArray = [];
var repAddressArray = [];

//$.root_ = $("body");
//var   initApp = function(a) {
//        return a.SmartActions = function() {
//            var a = {
//                alertBox: function(a) {
//                    $.SmartMessageBox({
//                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
//                        content: a.data("logout-msg") || "<br>This contractor will be "+
//						"permanently deleted . Only Admin can	recover it.<br>"+"" +
//						"<br/> <i class='ace-icon fa fa-hand-o-right bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
//                        buttons: "[No][Yes]"
//                    }, function(a) {
//                    	
//                    	if(a=="Yes"){setContractorToDelete(contractorIdToDel);}
//                    });
//                }
//            };
//            $.root_.on("click", '[data-action="alertBox"]', function(b) {
//                var c = $(this);
//                a.alertBox(c), b.preventDefault(), c = null;
//            });
//        },  a
//    }({});
//    jQuery(document).ready(function() {
//        initApp.SmartActions(); 
//    });
   
$(document).ready(function() {
//    $("#addressSaveButton").hide();
//    $("#addressInfo").hide();
   /* $("#addressTable").hide();*/ //must be changed according to the UI
//    $("#dt_basic_wrapper").hide();
    getStates();
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
	
	contractorDetailsTable = $('#contractorsListTable').dataTable(
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
										return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setContractorToUpdate('"
											+ full[0]
											+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setContractorId('"
											+ full[0]
											+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#contractorsListTable'),breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});
	contractorDetailsTable.fnSetColumnVis(2, false,false);
	contractorDetailsTable.fnSetColumnVis(6, false,false);
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
	
	getContractorDetails();
	/*getAddressDetails();*/
	/* END BASIC */
};

function resetContractorForm(){
	
	
	$("#save").html("<i class='fa fa-floppy-o'></i> &nbsp; Save");
	$("#saveandcontinue").show();
	$("#modelTitleId").html("Add New Contractor");
	var validator = $( "#contracter_form" ).validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#contractorName").val("");
	$("#contractorNumber").val("");
	$("#businessType").val("");
	$("#contractorId").val("");
	$("#corporateOfficeAddress").val("");
	$("#contractorEmail").val("");
	$("#phoneNumber").val("");
	$("#fax").val("");
	$("#representativeName").val("");
	$("#representativePhone").val("");
	$("#representativeAddress").val("");
	$("#contractorName").attr("placeholder", "contractor Name");
	$("#contractorNumber").attr("placeholder", "contractor Number");
	$("#irs").attr("placeholder", "IRS");
	$("#businessType").attr("placeholder", "Business Type");
	$("#corporateOfficeAddress").attr("placeholder", "Office Address");
	$("#contractorEmail").attr("placeholder", "contractor Email");
	$("#phoneNumber").attr("placeholder", "Phone Number");
	$("#fax").attr("placeholder", "Fax Number");
	$("#representativeName").attr("placeholder", "Representative Name");
	$("#representativePhone").attr("placeholder", "Representative Phone");
	$("#representativeAddress").attr("placeholder", "Representative Address");
	$("#typeOfAction").val("");
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

function ajaxCallForSaveOrUpdateContractor(formId) {
	if (addressArray.length == 0) {
		$('#addressErr').css("display", "block");
	} else {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	
	$.ajax({
				url : "saveOrUpdateContractorAction",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					var actionType = "";
					
					if($("#contractorId").val() != ""){
						actionType = "update";
					}
					if($("#typeOfAction").val() != "saveandcontinue"){
					     $("#contractorRemoteModal").modal("hide");
					}
					if (result.ajaxResult == "success") {
						getContractorDetails();
						/*getAddressDetails();*/
						// Resetting Forms
						//for adding address -- contractorId is required
						saveAddressDetails(result.contractorId);
                        if(actionType != ""){
						  gritterForSucessMsgs("Contractor has been updated successfully.");
                        } else {
                        	 gritterForSucessMsgs("Contractor has been saved successfully.");
                        }
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"+ result.reason);
					}
					resetContractorForm();
					resetAddressForm();
				},
				error : function() {
					laddaRef.stop();
					resetContractorForm();
					resetAddressForm();
					$("#contractorRemoteModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
	}
}

function getContractorDetails() {
	contractorList = "contractor";
	$.ajax({
				url : "getAllContractorDetails",
				type : "POST",
				contentType : 'text/plain',
				data : '{"contractorList": "' + contractorList + '"}',
				success : function(result) {
					contractorList = JSON.parse(result);
					console.log(contractorList);
					contractorDetailsTable.fnClearTable();
					
					for (var t = 0; t < contractorList.contractorDetails.length; t++) {
						
						contractorDetailsTable.fnAddData([
												contractorList.contractorDetails[t].contractorId,
												contractorList.contractorDetails[t].contractorName,
												contractorList.contractorDetails[t].corporateOfficeAddress,
												contractorList.contractorDetails[t].contractorEmail,
												contractorList.contractorDetails[t].phoneNumber,
												contractorList.contractorDetails[t].representativeName,
												contractorList.contractorDetails[t].representativeAddress,
												contractorList.contractorDetails[t].representativePhone,
												t+1],false);
					}
					contractorDetailsTable.fnDraw();
					$("#totalContractors").text(contractorList.contractorDetails.length);
				}
			});
}

function setContractorToUpdate(conId){
	
	$("#contractorRemoteModal").modal("show");
	$("#typeOfAction").val("update");
	for (var u = 0; u < contractorList.contractorDetails.length; u++) {
		if(conId == contractorList.contractorDetails[u].contractorId){
			
			var faxTemp, repAddr, compNumber,bussType;
            faxTemp = repAddr = compNumber = bussType = "";
            
            if(contractorList.contractorDetails[u].fax != undefined && contractorList.contractorDetails[u].fax != null){
            	faxTemp = contractorList.contractorDetails[u].fax;
            }
            if(contractorList.contractorDetails[u].representativeAddress != undefined && contractorList.contractorDetails[u].representativeAddress != null){
            	repAddr = contractorList.contractorDetails[u].representativeAddress;
            }
            if(contractorList.contractorDetails[u].contractorNumber != undefined && contractorList.contractorDetails[u].contractorNumber != null){
            	compNumber = contractorList.contractorDetails[u].contractorNumber;
            }
            if(contractorList.contractorDetails[u].businessType != undefined && contractorList.contractorDetails[u].businessType != null){
            	bussType = contractorList.contractorDetails[u].businessType;
            }
            
            $("#contractorName").val(contractorList.contractorDetails[u].contractorName);
			$("#corporateOfficeAddress").val(contractorList.contractorDetails[u].corporateOfficeAddress);
			$("#contractorEmail").val(contractorList.contractorDetails[u].contractorEmail);
			$("#phoneNumber").val(contractorList.contractorDetails[u].phoneNumber);
			$("#fax").val(faxTemp);
			$("#representativeName").val(contractorList.contractorDetails[u].representativeName);
			$("#representativePhone").val(contractorList.contractorDetails[u].representativePhone);
			$("#representativeAddress").val(repAddr);
			$("#contractorNumber").val(compNumber);
			$("#businessType").val(bussType);
			$("#contractorId").val(conId);
			
			$("#save").html(
			"<i class='fa fa-thumbs-up bigger-110'></i> Update");
			document.getElementById("saveandcontinue").style.display="none";
			$("#modelTitleId").html("Update Contractor");
			break;
		}
	}
	getAddressDetails(conId);
}

function setContractorToDelete(compId){
    $("#contractorId").val(compId);
    var dataObj = {
    		compIdToDel : contractorIdToDel,
    	};
    $.ajax({
		url : "contractorDeleteAction",
		type : "POST",
		data : dataObj,
		success : function(result) {
			result = JSON.parse(result);
			// CLearing Project Table
			if (result.ajaxResult == "success") {
				getContractorDetails();
				 gritterForSucessMsgs("Contractor has been deleted successfully.");
			} else {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"	+ result.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
		}
	});
}

function saveAddressDetails(id){
	var addressData= {};
	var address = [];
	addressData.moduleId = id;
	addressData.moduleName = "contractor_Directory";
	address.push( addressArray);
	address.push( repAddressArray);
	addressData.address = address;
	var jobDetails = '{"addressData" : ' + JSON.stringify(addressData) + '}';
	$
	.ajax({
		url : 'saveAddressDetails',
		data : jobDetails,
		type : 'POST',
		contentType : 'application/json',
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

function setActionType(buttonId, laddaButtonTemp){
	$("#typeOfAction").val(buttonId);
	laddaButton = laddaButtonTemp;
}

function setContractorId(conId){
	contractorIdToDel = conId;
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This contractor will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					setContractorToDelete(contractorIdToDel);
				}
			});
}

// load related plugins

loadScript("assets/js/datatable/jquery.dataTables.min.js", function() {
	loadScript("assets/js/datatable/dataTables.tableTools.min.js", function() {
		loadScript("assets/js/datatable/dataTables.bootstrap.min.js",function() {
					loadScript("assets/js/datatable/datatables.responsive.min.js",pagefunction);
				});
	});
});

// Validation
$(function() {
			// Validation
			$("#contracter_form").validate({

				// Rules for form validation
				rules : {
					contractorName : {
						required : true,
						maxlength : 100
					},
					contractorNumber : {
						maxlength : 10
					},
					corporateOfficeAddress : {
						required : true,
						maxlength : 200
					},
					contractorEmail : {
//						required : true,
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
					contractorName : {
						required : 'Please enter the contractor name'
					},
					corporateOfficeAddress : {
						required : 'Please enter the contractor address'
						
					},
					contractorEmail : {
//						required : 'Please enter the contractor email address',
						email : 'Please enter a VALID email address'
					},
					phoneNumber : {
						required : 'Please enter the contractor phone number',
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
					
					ajaxCallForSaveOrUpdateContractor(form.id);
				},

				// Do not change code below
				errorPlacement : function(error, element) {
					error.insertAfter(element.parent());
				}
			});

		});

function resetContractorUploadForm(){
	$("#confirmContractorUploadId").val(null);
	$("#contractorFileName").val(null);
}

$.validator.addMethod("checkSize",function(value, element){
	
	var size = element.files[0].size;
    if(size > 15728640){
    	return false;
    }
    else{
    	return true;
    }
},"Please upload a file with size less than 15 MB");

$("#contractorUploadForm").validate({
	rules : {
		contractorTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		contractorTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForAddContractorTemplate();
	}
});

function ajaxCallForAddContractorTemplate(){
	var msg="";
	$("#className").val("CONTRACTOR_LICENSE_EXCEL_FORMAT");
	$
	.ajax({
		url : 'projectTemplateController',
		data : new FormData($("#contractorUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			$("#contractorUploadModal").modal("hide");
			result = JSON.parse(data);
			if(result.ajaxResult =="failure"){
				resetContractorUploadForm();
				var out = document.getElementById("errorBlock");
				out.innerHTML = "";
				if(result.reason[0].colNumber == -1){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#contractorConfirmationHeader").hide();
					$("#saveContractorConfirmUpload").hide();
					$("#cancelContractorUpload").html("Close");
					getContractorDetails();
				}
				else if(result.reason[0].excelCell == "A0"){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveContractorConfirmUpload").hide();
				}
				else{
					for(i=0;i<result.reason.length;i++){
						if(result.reason[i].excelCell == undefined){
							out.appendChild(document.createTextNode(" Row Number :"+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						}
						else{
							out.appendChild(document.createTextNode(" Cell Address :"+result.reason[i].excelCell+" Error :"+result.reason[i].errorMessage));
						}
						out.appendChild(document.createElement("br"));
					}
				}	
				$("#contractorUploadConfirmation").modal("show");
				$("#contractorErrorSection").css('display','block');
			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("A file of contractor type has been successfully added");
				resetContractorUploadForm();
				getContractorDetails();
				cancelContractorUploadForm();
			}
			else if(result.ajaxResult == "failure"){
				gritterForErrorMsgs("An error occurred : "+ result.reason);
			}
			else{
				gritterForErrorMsgs("Could not be saved.Contact dev");
			}
		},
		error : function() {
			alert("Some problem occured");
		}
	});
}
function setContractorUploadConfirmation(buttonId){
	 
	$("#contractorUploadConfirmation").modal("hide"); 
	if(buttonId == "saveContractorConfirmUpload"){
	  $("#confirmContractorUploadId").val(1);
	  ajaxCallForAddContractorTemplate();
	}
	else
	  $("#confirmContractorUploadId").val(-1);
}

function resetContractorConfirmation(){
	$("#saveContractorConfirmUpload").show();
	$("#contractorConfirmationHeader").show();
	$("#cancelContractorUpload").html("Cancel");
	$("#contractorUploadConfirmation").modal("hide");
}

function cancelContractorUploadForm(){
	var validator = $("#contractorUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}
function saveAddressForm(){
	if (!validateForm()){
		return ;
	} else {
		var dataObj = {
				localAddressType : $("#addressType").val(),
				address : $("#address").val(),
				addressOfType : "Contractor",
				addressUniqueId : $("#contractorId").val(),
				addressId : $("#addressId").val()
			};
//		$.ajax({
//			url : "saveAddressDetails",
//			type : "POST",
//			data : dataObj,
//			success : function(result) {
//				data = JSON.parse(result);
//				var actionType = "";
//				
//				if($("#addressId").val() != ""){
//					actionType = "update";
//				}
//				resetAddressForm();
//				// CLearing Department Table
//				if (data.ajaxResult == "success") {
////					$("#vendorRemoteModal").modal("hide");
//					/*getAddressDetails($("#contractorId").val());*/
//					if(actionType != ""){
//						gritterForSucessMsgs("Address record has been successfully updated");
//					} else {
//					gritterForSucessMsgs("A record of address type has been successfully added");
//					}
//					$("#addressTable").show();
//				    $("#dt_basic_wrapper").show();
//				} else {
//					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :" + result.reason);
//				}
//			},
//			error : function(result) {
////				console.log(result);
//				gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
//			}
//		});
	}
}
function resetAddressForm(){
//	$("#addressId").val("");
//	$("#addressActionType").val("");
//	$("#addressType").val("");
//	$("#address").val("");
//	$('#addressType').attr("placeholder", "Address Type");
//	$('#address').attr("placeholder", "Address");
//	$('label').removeClass("state-error");
//	var div = document.getElementById("addressType");
//	div.style.background = '#F7F7FC';
//	var div1 = document.getElementById("address");
//	div1.style.background = '#F7F7FC';
}
function validateForm() {

	/* Validation for text_area */
//	if ($('#addressType').val() == '') {
//		var div = document.getElementById("addressType");
//		div.style.background = '#fff0f0';
//		$('#labelAddressType').addClass("state-error");
//		$("#addressType").focus();
//		return false;
//	}
//	if ($('#address').val() == '') {
//		var div = document.getElementById("address");
//		div.style.background = '#fff0f0';
//		$('#labelAddress').addClass("state-error");
//		$("#address").focus();
//		return false;
//	}
	return true;
}
function getAddressDetails(conId){
	var data={
			"moduleId":conId,
			"moduleName" : "CONTRACTOR_DIRECTORY"
		};
	$.ajax({
		url : "getAddressDetails",
		type : "POST",
		data:data,
		success : function(result) {
			addressDetails = JSON.parse(result);
			addressDetails = JSON.parse(result);
			for(var i=0;i<addressDetails.allAddressDetails.length;i++){
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
				if(addressDetails.allAddressDetails[i].address_type == "Office"){
					showOfficeAddressForm(dataObj);
				}else if(addressDetails.allAddressDetails[i].address_type == "Representative"){
					showRepAddressForm(dataObj);
				}
			}
			
		},
		error : function(result) {
			console.log(result);
//			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
		}
	});
}
$('#addressType').on('input DOMAttrModified paste change',function () {
	$('#labelAddressType').addClass("state-success");
	var div = document.getElementById("addressType");
	div.style.background = '#f0fff0';
});
$('#address').on('input DOMAttrModified paste change',function () {
	$('#labelAddress').addClass("state-success");
	var div = document.getElementById("address");
	div.style.background = '#f0fff0';
});
function addressUpdate(addressId){
	
	for (var u = 0; u < addressDetails.allAddressDetails.length; u++) {
		if(addressId == addressDetails.allAddressDetails[u].addressId){
            $("#addressId").val(addressDetails.allAddressDetails[u].addressId);
			$("#addressType").val(addressDetails.allAddressDetails[u].localAddressType);
			$("#address").val(addressDetails.allAddressDetails[u].address);
			break;
		}
	}
}
function setAddressId(addressId){
	addressIdToDel = addressId;
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Record of Address will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					setAddressToDelete(addressIdToDel);
				}
			});
}
function setAddressToDelete(addressId){
    $("#addressId").val(addressId);
    var dataObj = {
    		addIdToDel : addressIdToDel,
    	};
//    $.ajax({
//		url : "addressDeleteAction",
//		type : "POST",
//		data : dataObj,
//		success : function(result) {
//			result = JSON.parse(result);
//			resetAddressForm();
//			// CLearing Project Table
//			if (result.ajaxResult == "success") {
//				getAddressDetails($("#contractorId").val());
//				 gritterForSucessMsgs("Address record has been deleted successfully.");
//			} else {
//				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"	+ result.reason);
//			}
//		},
//		error : function() {
//			gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
//		}
//	});
}
function saveAddressForm(){
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
function showOfficeAddressForm(dataObj){
	var fullAddress = "";
	if(dataObj.address_line_1 !=""){
		fullAddress+= dataObj.address_line_1 + ", "
	}else{
		$("#addressLine1Err").css("display","block");
		$("#addressLine1Err").prev().addClass("state-error");
	}
	if(dataObj.address_line_2 !=""){
		fullAddress+= dataObj.address_line_2 + ", "
	}
	if(dataObj.state !="" && dataObj.state !="Select State"){
		fullAddress+= dataObj.state + ", "
	}else{
		$("#addressStateErr").css("display","block");
		$("#addressStateErr").prev().addClass("state-error");
	}
	if(dataObj.city !=""&& dataObj.city !="Select City"){
		fullAddress+= dataObj.city + ", "
	}else{
		$("#addressCityErr").css("display","block");
		$("#addressCityErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode !=""){
		fullAddress+= dataObj.zipCode + ", "
	}else{
		$("#addressZipCodeErr").css("display","block");
		$("#addressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	addressArray.push(dataObj);
	var id = addressArray.length - 1;
	var file = "<section class='col col-lg-12' id='address-"+id+"'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showAddress("+id+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setAddressToDelete("+id+ ");'></i></p></section>";
	$("#addressDetails").append(file);
	$("#addressDetails").show();
	$("#addressInfo").hide();
	resetAddressForm();
	}
}

function saveRepAddressForm(){
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
function showRepAddressForm(dataObj){
	var fullAddress = "";
	if(dataObj.address_line_1 !=""){
		fullAddress+= dataObj.address_line_1 + ", "
	}else{
		$("#repAddressLine1Err").css("display","block");
		$("#repAddressLine1Err").prev().addClass("state-error");
	}
	if(dataObj.address_line_2 !=""){
		fullAddress+= dataObj.address_line_2 + ", "
	}
	if(dataObj.state !="" && dataObj.state !="Select State"){
		fullAddress+= dataObj.state + ", "
	}else{
		$("#repAddressStateErr").css("display","block");
		$("#repAddressStateErr").prev().addClass("state-error");
	}
	if(dataObj.city !=""&& dataObj.city !="Select City"){
		fullAddress+= dataObj.city + ", "
	}else{
		$("#repAddressCityErr").css("display","block");
		$("#repAddressCityErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode !=""){
		fullAddress+= dataObj.zipCode + ", "
	}else{
		$("#repAddressZipCodeErr").css("display","block");
		$("#repAddressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	repAddressArray.push(dataObj);
	var id = repAddressArray.length - 1;
	var file = "<section class='col col-lg-12' id='repAddress-"+id+"'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showRepAddress("+id+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='deleteRepAddress("+id+ ");'></i></p></section>";
	$("#repAaddressDetails").append(file);
	$("#repAaddressDetails").show();
	$("#repAddressInfo").hide();
	resetRepAddressForm();
	}
}

function showAddress(id){
	var object = addressArray[id];
	$("#addressId").val(id);
	$("#address_line_1").val(object.address_line_1);
	$("#address_line_2").val(object.address_line_2);
	$("#state option").each(function() {
		  if($(this).text() == object.state) {
		    $(this).attr('selected', 'selected');   
		    var value = $(this).val();
			flag = "city";
			getCities(value,flag,object.city);
		  }                        
		});
	$('#zipCode').val(object.zipCode);
	$("#addressInfo").show();
	$("#addressSaveButton").hide();
	$("#addressUpdateButton").show();
}


function showRepAddress(id){
	var object = repAddressArray[id];
	$("#repAddressId").val(id);
	$("#repAddress_line_1").val(object.address_line_1);
	$("#repAddress_line_2").val(object.address_line_2);
	$("#repState option").each(function() {
		  if($(this).text() == object.state) {
		    $(this).attr('selected', 'selected');  
		    var value = $(this).val();
			flag = "repCity";
			getCities(value,flag,object.city);
		  }                        
		});
	$('#repZipCode').val(object.zipCode);
	$("#repAddressInfo").show();
	$("#repAddressSaveButton").hide();
	$("#repAddressUpdateButton").show();
}

function updateAddressForm(){
	var addressId = $("#addressId").val();
	addressArray[addressId].address_line_1 = $("#address_line_1").val();
	addressArray[addressId].address_line_2 = $("#address_line_2").val();
	addressArray[addressId].state = $("#state option:selected").text();
	addressArray[addressId].city = $("#city option:selected").text();
	addressArray[addressId].zipCode = $("#zipCode").val();
	var fullAddress = "";
	if(addressArray[addressId].address_line_1 !=""){
		fullAddress+= addressArray[addressId].address_line_1 + ", "
	}else{
		$("#addressLine1Err").css("display","block");
		$("#addressLine1Err").prev().addClass("state-error");
	}
	if(addressArray[addressId].address_line_2 !=""){
		fullAddress+= addressArray[addressId].address_line_2 + ", "
	}
	if(addressArray[addressId].state !="" && addressArray[addressId].state !="Select State"){
		fullAddress+= addressArray[addressId].state + ", "
	}else{
		$("#addressStateErr").css("display","block");
		$("#addressStateErr").prev().addClass("state-error");
	}
	if(addressArray[addressId].city !="" && addressArray[addressId].city !="Select City"){
		fullAddress+= addressArray[addressId].city + ", "
	}else{
		$("#addressCityErr").css("display","block");
		$("#addressCityErr").prev().addClass("state-error");
	}
	if(addressArray[addressId].zipCode !=""){
		fullAddress+= addressArray[addressId].zipCode + ", "
	}else{
		$("#addressZipCodeErr").css("display","block");
		$("#addressZipCodeErr").prev().addClass("state-error");
	}
	if(addressArray[addressId].zipCode != "" && addressArray[addressId].address_line_1 != "" && addressArray[addressId].state != "" && addressArray[addressId].state != "Select State"
		&& addressArray[addressId].city != "" && addressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showAddress("+addressId+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setAddressToDelete("+addressId+ ");'></i></p>"
	$("#address-"+addressId).html("");
	$("#address-"+addressId).html(updateFile);
	resetAddressForm();
	$("#addressInfo").hide();
	}
}


function updateRepAddressForm(){
	var addressId = $("#repAddressId").val();
	repAddressArray[addressId].address_line_1 = $("#repAddress_line_1").val();
	repAddressArray[addressId].address_line_2 = $("#repAddress_line_2").val();
	repAddressArray[addressId].state = $("#repState option:selected").text();
	repAddressArray[addressId].city = $("#repCity option:selected").text();
	repAddressArray[addressId].zipCode = $("#repZipCode").val();
	var fullAddress = "";
	if(repAddressArray[addressId].address_line_1 !=""){
		fullAddress+= repAddressArray[addressId].address_line_1 + ", "
	}else{
		$("#repAddressLine1Err").css("display","block");
		$("#repAddressLine1Err").prev().addClass("state-error");
	}
	if(repAddressArray[addressId].address_line_2 !=""){
		fullAddress+= repAddressArray[addressId].address_line_2 + ", "
	}
	if(repAddressArray[addressId].state !="" && repAddressArray[addressId].state !="Select State"){
		fullAddress+= repAddressArray[addressId].state + ", "
	}else{
		$("#repAddressStateErr").css("display","block");
		$("#repAddressStateErr").prev().addClass("state-error");
	}
	if(repAddressArray[addressId].city !="" && repAddressArray[addressId].city !="Select City"){
		fullAddress+= repAddressArray[addressId].city + ", "
	}else{
		$("#repAddressCityErr").css("display","block");
		$("#repAddressCityErr").prev().addClass("state-error");
	}
	if(repAddressArray[addressId].zipCode !=""){
		fullAddress+= repAddressArray[addressId].zipCode + ", "
	}else{
		$("#repAddressZipCodeErr").css("display","block");
		$("#repAddressZipCodeErr").prev().addClass("state-error");
	}
	if(repAddressArray[addressId].zipCode != "" && repAddressArray[addressId].address_line_1 != "" && repAddressArray[addressId].state != "" && repAddressArray[addressId].state != "Select State"
		&& repAddressArray[addressId].city != "" && repAddressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showRepAddress("+addressId+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='deleteRepAddress("+addressId+ ");'></i></p>"
	$("#repAddress-"+addressId).html("");
	$("#repAddress-"+addressId).html(updateFile);
	resetAddressForm();
	$("#repAddressInfo").hide();
	}
}


function setAddressToDelete(id){
	if(addressArray[id].addressId != undefined){
		deleteAddress(addressArray[id].addressId ,id,"office");
	}else{
		$("#address-"+id).remove();
		delete addressArray[id];
		addressArray.pop(id);
	}
}

function deleteRepAddress(id){
	if(repAddressArray[id].addressId != undefined){
		deleteAddress(repAddressArray[id].addressId,id ,"represenetative");
	}else{
		$("#repAddress-"+id).remove();
		delete repAddressArray[id];
		repAddressArray.pop(id);
	}
}

function deleteAddress(id,addressId,type){
	var data={
		"moduleId":id
	};
	$.ajax({
		url : "deleteAddress",
		type : "POST",
		data:data,
		success : function(result) {
			result = JSON.parse(result);
			if(result.ajaxResult == "success"){
				if(type=="represenetative"){
					$("#repAddress-"+addressId).remove();
					delete repAddressArray[addressId];
					repAddressArray.pop(id);
				}
				else if(type=="office"){
					$("#address-"+addressId).remove();
					delete addressArray[addressId];
					addressArray.pop(id);
				}
				gritterForSucessMsgs("Address has been deleted successfully");
			}
		},
		error : function(result) {
			console.log(result);
//			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
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
function resetAddressForm(){
	$("#addressSaveButton").show();
	$("#addressUpdateButton").hide();
	$("#addressId").val("");
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
	$("#addressErr").css("display","none");
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
function resetRepAddressForm(){
	$("#repAddressSaveButton").show();
	$("#repAddressUpdateButton").hide();
	$("#repAddressId").val("");
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

function addressUpdate(addressId){

for (var u = 0; u < addressDetails.allAddressDetails.length; u++) {
	if(addressId == addressDetails.allAddressDetails[u].addressId){
        $("#addressId").val(addressDetails.allAddressDetails[u].addressId);
		$("#addressType").val(addressDetails.allAddressDetails[u].localAddressType);
		$("#address").val(addressDetails.allAddressDetails[u].address);
		break;
	}
}
}


function getStates(){
//console.log('enetered')
$.ajax({
	url : "getStates",
	type : "GET",
	success : function(result) {
		statesData = JSON.parse(result);
		if(statesData.ajaxResult == "success"){
			var elementId = document.getElementById("state");
			var repState = document.getElementById("repState");
			elementId.options.length = 1;
			repState.options.length = 1;
			for (var i=0; i <statesData.reason.length;i++){    
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

$("#repState").on("change",function(e){
	var value = $("#repState").val();
	flag = "repCity";
	if(value != ""){
		getCities(value,flag);
	}
	else{
		$('#repCity').find('option').remove().end().append(
		'<option value="">Select City</option>');
	}
});


$("#state").on("change",function(e){
	var value = $("#state").val();
	flag = "city";
	if(value != ""){
		getCities(value,flag);
	}
	else{
		$('#city').find('option').remove().end().append(
		'<option value="">Select City</option>');
	}
});

function getCities(value,flag,cityName){
$.ajax({
	url : "getCities",
	type : "GET",
	data : {"stateCode":value},
	success : function(result) {
		citiesData = JSON.parse(result);
//		console.log(responseData)
		if(citiesData.ajaxResult == "success"){
			var elementId = document.getElementById(flag);
			elementId.options.length = 1;
			for (var i=0; i <citiesData.reason.length;i++){    
		        var optn = document.createElement("OPTION");
			    optn.text = citiesData.reason[i];
			    optn.value = i;
			    elementId.options.add(optn);
			}
			if(cityName != undefined){
				$("#"+flag+" option").each(function() {
					  if($(this).text() == cityName) {
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


/*$('#selectState').change(function() {
alert('The option with value ' + $(this).val() + ' and text ' + $(this).text() + ' was selected.');
});*/