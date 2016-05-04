/**
 * Anantha MeghanaJagruthi
 */
console.log("JS INIT");
runAllForms();
pageSetUp();
localLicenseTable = null;
var  resultT;
// $.root_ = $("body");
// var initApp = function(a) {
// return a.SmartActions = function() {
// var a = {
// alertBox: function(a) {
// $.SmartMessageBox({
// title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
// content: a.data("logout-msg") || "<br>This role will be "+
// "permanently deleted . Only Admin can recover it.<br>"+""+"<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
// buttons: "[No][Yes]"
// }, function(a) {
// if(a=="Yes"){deleteLocalLicense();}
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
var pagefunction = function() {

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

	localLicenseTable = $('#localLicenseTable')
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
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:updateLocalLicenseDetails('"
												+ full[5]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setLocalLicenseId('"
												+ full[5]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
					"preDrawCallback" : function() {
						// Initialize the responsive datatables helper once.
						if (!responsiveHelper_dt_basic) {
							responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
									$('#localLicenseTable'),
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
	getLocalLicenseDetails();
	/* END BASIC */
};

function getLocalLicenseDetails() {
	console.log("Entered the locallicense");
	$
			.ajax({
				url : "getlLicenseDetails",
				method : "POST",
				success : function(result) {
					console.log("-------Here----------");
					console.log(result);
					resultT = JSON.parse(result);
					var count = 0;
					console.log("Entered the local licenseDetails" + resultT);
					localLicenseTable.fnClearTable();
					console.log("Enter");
					for (var i = 0; i < resultT.lLicenseDetails.length; i++) {

						if (resultT.lLicenseDetails[i].type == "LOCAL"&& resultT.lLicenseDetails[i].status=="ACTIVE") {
							if (resultT.lLicenseDetails[i].primaryPerson == ""
									|| resultT.lLicenseDetails[i].primaryPerson == undefined) {
								pPerson = "";
							} else
								pPerson = resultT.lLicenseDetails[i].primaryPerson;
							
                            
							localLicenseTable
									.fnAddData(
											[
													++count,
													resultT.lLicenseDetails[i].licenseNumber,
													resultT.lLicenseDetails[i].localJurisdiction,
													resultT.lLicenseDetails[i].expiryDate,
													pPerson,
													resultT.lLicenseDetails[i].licenseId ],
											false);

						}
					}
					localLicenseTable.fnDraw();
					$("#totalLocalLicenses").html(count);
				}
			});
}


function updateLocalLicenseDetails(id) {
 
console.log("Entered the update section");
$("#localLicenseModal").modal("show");

//$("#step2").removeClass("active");
$("#saveandcontinue").hide();


$("#save").html("Update");
for(var i=0;i<resultT.lLicenseDetails.length;i++)
	{
	    if(id==resultT.lLicenseDetails[i].licenseId)
	    	{
	    	   $("#licenseId").val(resultT.lLicenseDetails[i].licenseId);
			   $("#licenseAction").val("Update");
	    	   $("#licenseNumber").val(resultT.lLicenseDetails[i].licenseNumber);
	    	   $("#localJurisdiction").val(resultT.lLicenseDetails[i].localJurisdiction);
	    	   $("#expiryDate").val(resultT.lLicenseDetails[i].expiryDate);
	    	   $("#primaryPerson").val(resultT.lLicenseDetails[i].primaryPerson);
	    	   $("#licenseDescription").val(resultT.lLicenseDetails[i].licenseDescription);
	    	   break;
	    	}
	}
//handleSavingOperation(action);
}
function resetLLicenseForm() {

	var validator = $("#localLicenseForm").validate();
	validator.resetForm();
     $("#step1").addClass("active");
     $("#step2").addClass("hide");
	$("#submitbuttons").addClass("hide");
	$("#btnWizardNext").removeClass("hide");
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$('input').val('');
	$("#saveandcontinue").show();
	$("#labelDesc").show();
	$("#licenseDiv").hide();
	$("#save").html("Save");
}
function setLocalLicenseId(id) {

	$("#licenseId").val(id);
	$("#licenseAction").val("Delete");
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This License will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					deleteLocalLicense();
				}
			});
}

$("#expiryDate").datepicker({
	format : "mm-dd-yyyy"
}).on('changeDate', function(ev) {
	$('#expiryDate').datepicker('hide');
});

//$(function() {
//	// Validation
//	$("#localLicenseForm").validate({
//
//		// Rules for form validation
//		rules : {
//			licenseNumber : {
//				required : true,
//			},
//			localJurisdiction: {
//				required : true,
//			},
//			expiryDate : {
//				required : true,
//
//			},
//
//		},
//
//		// Messages for form validation
//		messages : {
//			licenseNumber : {
//				required : 'Please enter the license Number'
//			},
//			localJurisdiction : {
//				required : "Please select any state",
//			},
//			expiryDate : {
//				required : 'Please enter the expiry Date'
//
//			},
//
//		},
//
//		// Ajax form submition
//		submitHandler : function(form) {
//			//handleSavingOperation();
//		},
//
//		// Do not change code below
//		errorPlacement : function(error, element) {
//			error.insertAfter(element.parent());
//		}
//	});
//
//});

function setAction(action) {

	if ($("#licenseAction").val() == "")
		{
		$("#licenseAction").val(action);
		}
}
function handleSavingOperation(action) {

	setAction(action);
	
	
	$
			.ajax({
				url : "savelLicenseDetails",
				method : "POST",
				data : $("#localLicenseForm").serialize(),
				success : function(resultant) {
					resultant = JSON.parse(resultant);
					localLicenseTable.fnClearTable();
					getLocalLicenseDetails();
					
					
					if (($("#licenseAction").val() == "save")
							|| ($("#licenseAction").val() == "Update")) {
                       console.log("Entered the updation");
						$("#localLicenseModal").modal("hide");
						$(".modal-backdrop").hide();

					}

					if (resultant.ajaxResult === "success") {

						if (($("#licenseAction").val() == "save")
								|| ($("#licenseAction").val() == "saveandcontinue")) {
							$("#licenseAction").val("save");

						}
						gritterForSucessMsgs("A record of License Detail has been "
								+ $("#licenseAction").val() + "d successfully.");
						resetLLicenseForm();
						
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ resultant.reason);
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team."
							+ textStatus
							+ "------------------------------------------"
							+ errorThrown);
				}

			});
}

function deleteLocalLicense() {

	var dataObj = {
		licenseId : $("#licenseId").val()
	};
	$
			.ajax({
				url : "deleteLLicense",
				method : "POST",
				data : dataObj,
				success : function(result) {
					resultant = JSON.parse(result);
					if (resultant.ajaxResult === "success") {

						getLocalLicenseDetails();
						gritterForSucessMsgs("A record of License Detail has been "
								+ $("#licenseAction").val() + "d successfully.");
						resetLLicenseForm();
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ resultant.reason);
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team."
							+ textStatus
							+ "------------------------------------------"
							+ errorThrown);
				}
			});
}

function resetLocalLicUploadForm(){
	$("#confirmStateLicUploadId").val(null);
	$("#localLicFileName").val(null);
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


$("#localLicUploadForm").validate({
	rules : {
		localLicTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		localLicTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		console.log("ajax call for template");
		ajaxCallForAddLocalLicTemplate();
	}
});

function ajaxCallForAddLocalLicTemplate(){
	console.log("in ajax call for dept template");
	$("#className").val("LOCAL_LICENSE_EXCEL_FORMAT");
	var msg="";
	$
	.ajax({
		url : 'projectTemplateController',
		data : new FormData($("#localLicUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			console.log("again in dept ajax");
			$("#localLicUploadModal").modal("hide");
			result = JSON.parse(data);
			console.log(result);
			if(result.ajaxResult =="failure"){
				resetLocalLicUploadForm();
				var out = document.getElementById("localLicErrorBlock");
				out.innerHTML = "";
				if(result.reason[0].colNumber == -1){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#lLicenseConfirmationHeader").hide();
					$("#saveLocalLicConfirmUpload").hide();
					$("#cancelLLicenseUpload").html("Close");
					getLocalLicenseDetails();
				}
				else if(result.reason[0].excelCell == "A0"){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveLocalLicConfirmUpload").hide();
				}
				else{
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Cell Address :"+result.reason[i].excelCell+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
				}	
				$("#localLicUploadConfirmation").modal("show");
				$("#localLicErrorSection").css('display','block');
			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("A file of local license type has been successfully added");
				resetLocalLicUploadForm();
				getLocalLicenseDetails();
				cancelLLicenseUploadForm();
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

function setLocalLicUploadConfirmation(buttonId){
	 
	$("#localLicUploadConfirmation").modal("hide"); 
	if(buttonId == "saveLocalLicConfirmUpload"){
	  $("#confirmLocalLicUploadId").val(1);
	  console.log("b4");
	  ajaxCallForAddLocalLicTemplate();
	}
	else
	  $("#confirmLocalLicUploadId").val(-1);
}

function resetLLicenseConfirmation(){
	$("#saveLocalLicConfirmUpload").show();
	$("#lLicenseConfirmationHeader").show();
	$("#cancelLLicenseUpload").html("Cancel");
	$("#localLicUploadConfirmation").modal("hide");
}

function cancelLLicenseUploadForm(){
	var validator = $("#localLicUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}