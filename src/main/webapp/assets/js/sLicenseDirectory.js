/**
 * Anantha MeghanaJagruthi
 */

pageSetUp();
$("#licenseDiv").hide();
var sLicenseDetailsTemp;
var stateList = [ "Alabama", "Alaska", "Arizona", "Arkansas", "California",
		"Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii",
		"Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
		"Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
		"Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
		"Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
		"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
		"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
		"Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
		"West Virginia", "Wisconsin", "Wyoming" ];

			

			var $state = $("#usStates");
			for (var i = 0; i < stateList.length; i++) {
				$state.append("<option value=" + (i + 1) + ">" + stateList[i]
						+ "</option>");
			}


//			$.root_ = $("body");
//			var   initApp = function(a) {
//			        return a.SmartActions = function() {
//			            var a = {
//			                alertBox: function(a) {
//			                    $.SmartMessageBox({
//			                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
//			                        content: a.data("logout-msg") || "<br>This role will be "+
//									"permanently deleted . Only Admin can	recover it.<br>"+"" +
//									"<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
//			                        buttons: "[No][Yes]"
//			                    }, function(a) {
//			                    	if(a=="Yes"){deleteStateLicense();}
//			                    });
//			                }
//			            };
//			            $.root_.on("click", '[data-action="alertBox"]', function(b) {
//			                var c = $(this);
//			                a.alertBox(c), b.preventDefault(), c = null;
//			            });
//			        },  a
//			    }({});
//			    jQuery(document).ready(function() {
//			        initApp.SmartActions(); 
//			       
//			    });
sLicenseDetailsTableTemp = null;
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
	
	sLicenseDetailsTableTemp = $('#sLicenseDetailsTable')
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
										return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setStateLicenseUpdate('"
												+ full[5]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setStateLicenseId('"
												+ full[5]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#sLicenseDetailsTable'),
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
	getsLicenseDetails();
	/* END BASIC */
};

$("#expiryDate").datepicker({
	format : "mm-dd-yyyy"
}).on('changeDate', function(ev) {
	$('#expiryDate').datepicker('hide');
});
$(function() {
	// Validation
	$("#stateLicenseForm").validate({

		// Rules for form validation
		rules : {
			licenseNumber : {
				required : true,
			},
			usStates : {
				required : true,
			},
			expiryDate : {
				required : true,

			},

		},

		// Messages for form validation
		messages : {
			licenseNumber : {
				required : 'Please enter the license Number'
			},
			usStates : {
				required : "Please select any state",
			},
			expiryDate : {
				required : 'Please enter the expiry Date'

			},

		},

		// Ajax form submition
		submitHandler : function(form) {
			handleSavingOperation();
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});

});

function setAction(action) {
	
	if ($("#licenseAction").val() == "")
		$("#licenseAction").val(action);

}
function getsLicenseDetails() {
	
	$.ajax({
		url : "getsLicenseDetails",
		method : "POST",
		success : function(result) {
			
			sLicenseDetailsTemp = JSON.parse(result);
			sLicenseDetailsTableTemp.fnClearTable();
			var temp = sLicenseDetailsTemp.sLicenseDetails;
			
			var count = 0;
			var pPerson = null;
			for (var i = 0; i < temp.length; i++) {
				
				if (temp[i].type == "STATE"&& temp[i].status=="ACTIVE") {
					if (temp[i].primaryPerson == ""
							|| temp[i].primaryPerson == undefined) {
						pPerson = "";
					} else
						pPerson = temp[i].primaryPerson;
					++count;

					sLicenseDetailsTableTemp.fnAddData([ count,
							temp[i].licenseNumber,
							stateList[temp[i].state - 1], temp[i].expiryDate,
							pPerson, temp[i].licenseId ], false);
					
				}
			}
			sLicenseDetailsTableTemp.fnDraw();
			$("#totalCount").html(count);
		},
	});

	
}

function handleSavingOperation() {
	
	$
			.ajax({
				url : "saveLicenseDetails",
				method : "POST",
				data : $("#stateLicenseForm").serialize(),
				success : function(resultant) {
					resultant = JSON.parse(resultant);
					sLicenseDetailsTableTemp.fnClearTable();
					getsLicenseDetails();
					if (($("#licenseAction").val() == "save")
							|| ($("#licenseAction").val() == "Update")) {
						
						$("#addStateLicenseModal").modal("hide");

					}

					if (resultant.ajaxResult === "success") {
						
						if (($("#licenseAction").val() == "save")
								|| ($("#licenseAction").val() == "saveandcontinue")) {
							$("#licenseAction").val("save");
							
						}
						gritterForSucessMsgs("A record of License Detail has been "
								+ $("#licenseAction").val() + "d successfully.");
						resetSLicenseForm();
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
function setStateLicenseId(id) {
	
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
					deleteStateLicense();
				}
			});
}
function resetSLicenseForm() {
	
	var validator = $("#stateLicenseForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#modelTitleIdForStateLiecence").html("<i class='glyphicon glyphicon-user colorBlue'></i> Add New State License");
	$('input').val('');
	$('form select').val(""); //resetting the dropdown menu
	$("#saveandcontinue").show();
	$("#descDiv").show();
	$("#licenseDescription").val("");
	$("#descDiv").hide();
	
	$("#saveButtonId").html("Save");
}
function setStateLicenseUpdate(id) {
	console.log("Entered statee license edit");
	$("#modelTitleIdForStateLiecence").html("<i class='glyphicon glyphicon-user colorBlue'></i> Update State License");
	$("#addStateLicenseModal").modal("show");
	$("#saveButtonId").html("Update");
	$("#saveandcontinue").hide();
	var temp = sLicenseDetailsTemp.sLicenseDetails;
	
	for (var i = 0; i < temp.length; i++) {
		if (id == temp[i].licenseId) {
			
			$("#licenseId").val(temp[i].licenseId);
			$("#licenseAction").val("Update");
			$("#licenseNumber").val(temp[i].licenseNumber);
			$("#primaryPerson").val(temp[i].primaryPerson);
			$("#expiryDate").val(temp[i].expiryDate);
			$("#usStates").val(temp[i].state);
			$("#licenseDescription").val(temp[i].licenseDescription);
			
			break;
		}
	}

}

function toggleDescription() {
	$("#labelDesc").hide();
	$("#descDiv").show();

}
function deleteStateLicense() {
	
	var dataObj = {
		licenseId : $("#licenseId").val()
	};
	$
			.ajax({
				url : "deleteSLicense",
				method : "POST",
				data : dataObj,
				success : function(result) {
					resultant = JSON.parse(result);
					if (resultant.ajaxResult === "success") {
						
						getsLicenseDetails();
						gritterForSucessMsgs("A record of License Detail has been "
								+ $("#licenseAction").val() + "d successfully.");
						resetSLicenseForm();
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

function resetStateLicUploadForm(){
	$("#confirmStateLicUploadId").val(null);
	$("#stateLicFileName").val(null);
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

$("#stateLicUploadForm").validate({
	rules : {
		stateLicTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		stateLicTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		console.log("ajax call for template");
		ajaxCallForAddStateLicTemplate();
	}
});

function ajaxCallForAddStateLicTemplate(){
	console.log("in ajax call for dept template");
	$("#className").val("STATE_LICENSE_EXCEL_FORMAT");
	var msg="";
	$
	.ajax({
		url : 'projectTemplateController',
		data : new FormData($("#stateLicUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			console.log("again in dept ajax");
			$("#stateLicUploadModal").modal("hide");
			result = JSON.parse(data);
			console.log(result);
			if(result.ajaxResult =="failure"){
				resetStateLicUploadForm();
				var out = document.getElementById("stateLicErrorBlock");
				out.innerHTML = "";
				if(result.reason[0].colNumber == -1){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#sLicenseConfirmationHeader").hide();
					$("#saveStateLicConfirmUpload").hide();
					$("#cancelSLicenseUpload").html("Close");
					getsLicenseDetails();
				}
				else if(result.reason[0].excelCell == "A0"){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveStateLicConfirmUpload").hide();
				}
				else{
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Cell Address :"+result.reason[i].excelCell+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
				}	
				$("#stateLicUploadConfirmation").modal("show");
				$("#stateLicErrorSection").css('display','block');
			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("A file of state license type has been successfully added");
				resetStateLicUploadForm();
				getsLicenseDetails();
				cancelSLicenseUploadForm();
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

function setStateLicUploadConfirmation(buttonId){
	 
	$("#stateLicUploadConfirmation").modal("hide"); 
	if(buttonId == "saveStateLicConfirmUpload"){
	  $("#confirmStateLicUploadId").val(1);
	  console.log("b4");
	  ajaxCallForAddStateLicTemplate();
	}
	else
	  $("#confirmStateLicUploadId").val(-1);
}

function resetSLicenseConfirmation(){
	$("#saveStateLicConfirmUpload").show();
	$("#sLicenseConfirmationHeader").show();
	$("#cancelSLicenseUpload").html("Cancel");
	$("#stateLicUploadConfirmation").modal("hide");
}

function cancelSLicenseUploadForm(){
	var validator = $("#stateLicUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}