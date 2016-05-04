pageSetUp();

//addressDetailsDataTable = null;
vendorDetailsDataTable=null;
var vendorIdToDelete=0;
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
//                        content: a.data("logout-msg") || "<br>This vendor will be "+
//						"permanently deleted and cannot be recovered.Only Admin can	recover it.<br>"+"" +
//						"<i class='ace-icon fa fa-hand-o-right blue bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete ?</b>",
//                        buttons: "[No][Yes]"
//                    }, function(a) {
//                    	if(a=="Yes"){vendorDelete();}
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
//       
//    });
  
    $(document).ready(function() {
//       $("#addressSaveButton").hide();
       $("#addressInfo").hide();
//       $("#addressTable").hide();
//       $("#dt_basic_wrapper").hide();
       $(".clickTiHide").click(function(){
   		$("#addressInfo").toggle();
     });


   $(".editable-cancel").click(function(){
       $("#addressInfo").toggle();
     });

    });
var pagefunction = function() {
	var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	
	vendorDetailsDataTable = $('#vendorListTable')
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
								return full[4];
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
								return full[3] == null ? "" : full[3];
							}
						},
						{
							mData : null,
							sClass : "center",
							mRender : function(data, type, full) {
								return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:vendorUpdate('"
									+ full[0]
									+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setVendorId('"
									+ full[0]
									+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
							}
						} ],
				"preDrawCallback" : function() {
					// Initialize the responsive datatables helper once.
					if (!responsiveHelper_dt_basic) {
						responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
								$('#vendorListTable'),
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
	vendorDetailsDataTable.fnSetColumnVis(2, false,false);
	/*addressDetailsDataTable = $('#addressTable')
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
		});*/
getVendorDetails();
/*getAddressDetails();
*/getStates();
/* END BASIC */
};

//load related plugins

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


function resetVendorForm(){
	
	var validator = $("#vendor_form").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$('input').val('');
	$("#vendorName").val("");
	$("#vendorLegalName").val("");
	$("#vendorLegalStatus").val("");
	$("#vendorOwnership").val("");
	$("#vendorAddress").val("");
	$("#vendorMailingAddress").val("");
	$("#vendorEmail").val("");
	$("#vendorWebsite").val("");
	$("#representativeName").val("");
	$("#representativePhone").val("");
	$("#representativeAddress").val("");
	$("#irs").val("");
	$("#businessType").val("");
	
	$("#irs").attr("placeholder", "IRS");
	$("#businessType").attr("placeholder", "Business Type");
	$("#representativeName").attr("placeholder", "Representative Name");
	$("#representativePhone").attr("placeholder", "Representative Phone");
	$("#representativeAddress").attr("placeholder", "Representative Address");
	$("#modalTitleId").html("Add New Vendor");
	$("#saveandcontinue").show();
	$("#save").html("Save");
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

function setVendorId(vendorId) {
	vendorIdToDelete = vendorId;
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This vendor will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					vendorDelete();
				}
			});
};

$.validator.addMethod("validateURL",function(value, element){
	var myURL = value;
	var expression = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/gi;
	var regex = new RegExp(expression);
	return (myURL.match(regex));
},"Enter valid URL");
$("#vendor_form").validate({
	// Rules for form validation
	rules : {
		vendorName : {
			required : true,
		},
		vendorLegalName : {
			required : true,
		},
		vendorLegalStatus : {
			required : true,
		},
		vendorOwnership : {
			required : true,
		},
		vendorAddress : {
			required : true,
		},
		vendorPhoneNo : {
			required : true,
			digits : true,
		},
		vendorFax : {
			digits : true,
		},
		vendorEmail : {
//			required : true,
			email : true,
		},
		vendorWebsite : {
			required : true,
			validateURL : true
		},
		representativePhone : {
			digits : true,
		}
	},

	// Messages for form validation
	messages : {
		vendorName : {
			required : "Enter the vendor name",
		},
		vendorLegalName : {
			required : "Enter the vendor legal name",
		},
		vendorLegalStatus : {
			required : "Enter the vendor legal status",
		},
		vendorOwnership : {
			required : "Enter the vendor ownership",
		},
		vendorAddress : {
			required : "Enter the vendor address",
		},
		vendorPhoneNo : {
			required : "Enter the phone number",
			digits : "Enter the phone number in digits",
		},
		vendorFax : {
			digits : "Enter the fax in digits",
		},
		vendorEmail : {
//			required : "Enter the vendor email",
			email : "Enter valid email",
		},
		vendorWebsite : {
			required : "Enter the vendor website",
			validateURL : "Enter valid URL",
		},
		representativePhone : {
			digits : "Enter the phone in digits",
		}
	},
	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForAddNewVendor(form.id);
	}
});

function ajaxCallForAddNewVendor(formId){
	if (addressArray.length == 0) {
		$('#addressErr').css("display", "block");
	} else {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$.ajax({
		url : "addVendorAction",
		type : "POST",
		data : $("#" + formId).serialize(),
		success : function(result) {
			laddaRef.stop();
			if ($("#submitId").val() == "save")
				$("#vendorRemoteModal").modal("hide");
			result = JSON.parse(result);
			if (result.ajaxResult == "success") {
				//for adding address -- vendorId is required
				saveAddressDetails(result.vendorId);
				 if($("#vendorTypeAction").val()=="update"){
				 gritterForSucessMsgs("A record of vendor type has been successfully updated.");
				 }
				 else{
				 gritterForSucessMsgs("A record of vendor type has been successfully saved.");
				 }
				getVendorDetails();
				//getAddressDetails();
				resetVendorForm();
				resetAddressForm();
			} else {
				 $("#vendorRemoteModal").modal("hide");
				 gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :" + result.reason);
			}
		},
		error : function() {
			laddaRef.stop();
			resetVendorForm();
			resetAddressForm();
			gritterForErrorMsgs("Some problem occured");
			 $("#vendorRemoteModal").modal("hide");
			 gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
		}
	});
	}
}

function getVendorDetails(){
	
	vendorDetails = "vendorDetails";
	$.ajax({
		url : "getVendorAction",
		type : "POST",
		contentType : 'text/plain',
		data : '{"vendorDetails": "'
			+ vendorDetails + '"}',
		success : function(result) {
			vendorDetails = JSON.parse(result);
			if(vendorDetails.ajaxResult == "success"){
				vendorDetailsDataTable.fnClearTable();
				for (var t = 0; t < vendorDetails.allVendorList.length; t++) {
					vendorDetailsDataTable.fnAddData([
					                                  vendorDetails.allVendorList[t].vendorId,
					                                  vendorDetails.allVendorList[t].vendorName,
					                                  vendorDetails.allVendorList[t].vendorAddress,
					                                  vendorDetails.allVendorList[t].vendorPhoneNo,
					                                  (t+1) 
					                                  ],false);
			}
			vendorDetailsDataTable.fnDraw();
			$("#totalVendors").text(vendorDetails.allVendorList.length);
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ vendorDetails.reason);
			}
		}
	});
}

function vendorUpdate(id){
    
	$("#modalTitleId").html("Update Vendor");
	$("#vendorRemoteModal").modal("show");
	$("#vendorTypeAction").val("update");
	for (var i = 0; i < vendorDetails.allVendorList.length; i++) {
		if (id == vendorDetails.allVendorList[i].vendorId){
			$("#vendorId").val(vendorDetails.allVendorList[i].vendorId);
			$("#vendorName").val(vendorDetails.allVendorList[i].vendorName);
			$("#vendorLegalName").val(vendorDetails.allVendorList[i].vendorLegalName);
			$("#vendorLegalStatus").val(vendorDetails.allVendorList[i].vendorLegalStatus);
			$("#vendorOwnership").val(vendorDetails.allVendorList[i].vendorOwnership);
			$("#vendorAddress").val(vendorDetails.allVendorList[i].vendorAddress);
			$("#vendorMailingAddress").val(vendorDetails.allVendorList[i].vendorMailingAddress);
			$("#vendorPhoneNo").val(vendorDetails.allVendorList[i].vendorPhoneNo);
			$("#vendorFax").val(vendorDetails.allVendorList[i].vendorFax);
			$("#vendorEmail").val(vendorDetails.allVendorList[i].vendorEmail);
			$("#vendorWebsite").val(vendorDetails.allVendorList[i].vendorWebsite);
			$("#representativeName").val(vendorDetails.allVendorList[i].representativeName);
			$("#representativePhone").val(vendorDetails.allVendorList[i].representativePhone);
			$("#representativeAddress").val(vendorDetails.allVendorList[i].representativeAddress);
			$("#irs").val(vendorDetails.allVendorList[i].irs);
			$("#businessType").val(vendorDetails.allVendorList[i].businessType);
			
			$("#save").html("<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
			break;
			}
		}
	getAddressDetails(id);
}
	
function vendorDelete(){
	
	$("#vendorTypeAction").val("delete");
	var dataObj = {
		vendorIdToDel : vendorIdToDelete,
	};
	deleteAddress(vendorIdToDelete, [], "");
	$.ajax({
		url : "deleteVendorAction",
		type : "POST",
		data : dataObj,
		success : function(result) {
			result = JSON.parse(result);
			resetVendorForm();
			resetAddressForm();
			// CLearing Department Table
			if (result.ajaxResult == "success") {
				$("#vendorRemoteModal").modal("hide");
				gritterForSucessMsgs("A record of vendor type has been successfully deleted");
				getVendorDetails();
				resetVendorForm();
			} else {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :" + result.reason);
			}
		},
		error : function() {
			 gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

		}
	});
}

function setActionType(buttonId, laddaButtonTemp)
{
	$("#submitId").val(buttonId);
	laddaButton = laddaButtonTemp;
}

function resetVendorUploadForm(){
	$("#confirmUploadId").val("");
	$("#fileName").val("");
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

$("#vendorUploadForm").validate({
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
		ajaxCallForAddTemplate();
	}
});

function ajaxCallForAddTemplate(){
	$("#className").val("VENDOR_EXCEL_FORMAT");
	var msg="";
	$
	.ajax({
		url : 'projectTemplateController',
		data : new FormData($("#vendorUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			$("#vendorUploadModal").modal("hide");
			resultFromTemplate = JSON.parse(data);
			if(resultFromTemplate.ajaxResult =="failure"){
				resetVendorUploadForm();
				var out = document.getElementById("errorBlock");
				out.innerHTML = "";
				
				if(resultFromTemplate.reason[0].colNumber == -1 ){
					for(i=0;i<resultFromTemplate.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+resultFromTemplate.reason[i].rowNumber+" Error :"+resultFromTemplate.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#confirmationHeader").hide();
					$("#saveVendorUpload").hide();
					$("#cancelVendorUpload").html("Close");
					getVendorDetails();
				}
				else if(resultFromTemplate.reason[0].excelCell == "A1"){
					for(i=0;i<resultFromTemplate.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+resultFromTemplate.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveVendorUpload").hide();
				}
				else{
					for(i=0;i<resultFromTemplate.reason.length;i++){
						if(resultFromTemplate.reason[i].excelCell == undefined){
							out.appendChild(document.createTextNode(" Row Number :"+resultFromTemplate.reason[i].rowNumber+" Error :"+resultFromTemplate.reason[i].errorMessage));
						}
						else{
							out.appendChild(document.createTextNode(" Cell Address :"+resultFromTemplate.reason[i].excelCell+" Error :"+resultFromTemplate.reason[i].errorMessage));
						}
						out.appendChild(document.createElement("br"));
					}
				}	
				$("#vendorUploadConfirmation").modal("show");
				$("#errorSection").css('display','block');
				
			}
			else if(resultFromTemplate.ajaxResult == "success"){
				gritterForSucessMsgs("A file of vendor type has been successfully added");
				resetVendorUploadForm();
				getVendorDetails();
				cancelVendorUploadForm();
			}
			else if(resultFromTemplate.ajaxResult == "failure"){
				gritterForErrorMsgs("An error occurred : "+ resultFromTemplate.reason);
			}
			else{
				gritterForErrorMsgs("Could not be saved.Contact dev");
			}
		},
		error : function() {
			gritterForErrorMsgs("Some problem occured");
		}
	});
}
function setVendorUploadConfirmation(buttonId){
	 
	$("#vendorUploadConfirmation").modal("hide"); 
	if(buttonId == "saveVendorUpload"){
	  $("#confirmUploadId").val(1);
	  ajaxCallForAddTemplate();
	}
	else
	  $("#confirmUploadId").val(-1);
}

function resetConfirmationModal(){

	$("#saveVendorUpload").show();
	$("#confirmationHeader").show();
	$("#cancelVendorUpload").html("Cancel");
	$("#vendorUploadConfirmation").modal("hide");
}

function cancelVendorUploadForm(){
	var validator = $("#vendorUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}


function getAddressDetails(id){
	var data={
			"moduleId":id,
			"moduleName" : "VENDOR_DIRECTORY"
		};
	$.ajax({
		url : "getAddressDetails",
		type : "POST",
		data:data,
		success : function(result) {
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
function saveAddressDetails(id){
	var addressData= {};
	var address = [];
	addressData.moduleId = id;
	addressData.moduleName = "vendor_Directory";
	address.push( addressArray);
	address.push( repAddressArray);
	addressData.address = address;
	var jobDetails = '{"addressData" :'+JSON.stringify(addressData)+'}';
	$
	.ajax({
		url : 'saveAddressDetails',
		data : jobDetails,
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
function saveAddressForm(){
	var dataObj = {
			address_line_1 : $("#address_line_1").val(),
			address_line_2 : $("#address_line_2").val(),
			state : $("#state option:selected").text(),
			city : $("#city option:selected").text(),
			zipCode : $("#zipCode").val(),
			addressType : "Office"
		};
	showOfficeAddressForm(dataObj)
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
	$("#addressFormId").val(id);
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
	var addressId = $("#addressFormId").val();
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
	console.log(addressId);
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
	$("#addressInfo").hide();
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