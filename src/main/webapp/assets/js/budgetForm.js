pageSetUp();
budgetFormDetailsTable = null;
var laddaButton = 0;
var activityIdToDelete = 0;

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
	budgetFormDetailsTable = $('#budgetFormsTable')
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
											mData : null,
											sClass : "center",
											mRender : function(data, type, full) {
												return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setBudgetCodeToUpdate('"
										           + full[0]
										           + "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setBudgetCodeId('"
										           + full[0]
										           + "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
											}
										} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#budgetFormsTable'),
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
	getBudgetFormDetails();
	/* END BASIC */
};
// for delete action
function setBudgetCodeId(activityId){
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Budget Code will be permanently deleted and cannot be recovered. Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					setBudgetCodeToDelete(activityId);
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

$("#budget_form_id").validate({
	// Rules for form validation
	rules : {
		costType : {
			required : true,
			
		},
		activityNumber:{
			required: true,
			digits : true
		},
		activityDescription:{
			required: true,
		}
	},
		
	// Messages for form validation
	messages : {
		costType : {
			required : "Please select the  cost type"
			
		},
		activityNumber : {
			required : "Please enter the activity number",
			digits : 'Please enter valid activity number'
			
		},
		activityDescription : {
			required : "Please enter the activity description"
			
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler: function(form) {
		ajaxCallForSaveOrUpdateBudgetForm(form.id);
	}
});

function ajaxCallForSaveOrUpdateBudgetForm(formId) {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "saveOrUpdateBudgetFormAction",
				type : "POST",
				data : $("#budget_form_id").serialize(),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					var actionType = "";
					if($("#activityId").val() != ""){
						actionType = "update";
					}
					//modal should not be closed if save and continue or error is there(activity no already exists)
					if($("#typeOfAction").val() != "saveandcontinue" && result.ajaxResult == "success"){
					     $("#budgetFormRemoteModal").modal("hide");
					}
					// CLearing Project Table
					if (result.ajaxResult == "success") {
						getBudgetFormDetails();
						// Resetting Forms
						if(actionType != ""){
							  gritterForSucessMsgs("Budget Code has been updated successfully.");
                          } else {
                          	 gritterForSucessMsgs("Budget Code has been saved successfully.");
                          }
						resetbudgetForm();
					} else {
						$("#activityNumber").val("");
						if(result.reason == "actNumExist")
							{
							gritterForErrorMsgs("Activity Number already exists."
									);
							}else{
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);}
					}
				},
				error : function() {
					laddaRef.stop();
					resetbudgetForm();
					$("#budgetFormRemoteModal").modal("hide");
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
}

function getBudgetFormDetails() {
	budgetFormDetails = "budgetCodes";
	var costType, activityNumber, activityDescription;
	$
			.ajax({
				url : "getBudgetFormDetail",
				type : "POST",
				contentType : 'text/plain',
				data : '{"budgetFormDetails": "' + budgetFormDetails
						+ '"}',
				success : function(result) {
					budgetFormDetails = JSON.parse(result);
					budgetFormDetailsTable.fnClearTable();
					for (var t = 0; t < budgetFormDetails.allBudgetCodeList.length; t++) {
						costType = activityNumber = activityDescription = "";
						if(budgetFormDetails.allBudgetCodeList[t].costType != null || budgetFormDetails.allBudgetCodeList[t].costType != undefined){
							costType = budgetFormDetails.allBudgetCodeList[t].costType;
						}
						if(budgetFormDetails.allBudgetCodeList[t].activityNumber != null || budgetFormDetails.allBudgetCodeList[t].activityNumber != undefined){
							activityNumber = budgetFormDetails.allBudgetCodeList[t].activityNumber;
						}
						if(budgetFormDetails.allBudgetCodeList[t].activityDescription != null || budgetFormDetails.allBudgetCodeList[t].activityDescription != undefined){
							activityDescription = budgetFormDetails.allBudgetCodeList[t].activityDescription;
						}
						budgetFormDetailsTable
								.fnAddData(
										[
												budgetFormDetails.allBudgetCodeList[t].activityId,
												costType,
												activityNumber,
												activityDescription,
												t+1],
										false);
					}
					settingCostTypeCount(budgetFormDetails);
					budgetFormDetailsTable.fnDraw();
				}
			});
}

function setBudgetCodeToUpdate(activityId){
    $("#modaltitleIdForBudgetCodes").html("<i class='fa fa-lg fa-fw fa-barcode txt-color-blue'></i>&nbsp;Update Budget Code");
	$("#budgetFormRemoteModal").modal("show");
	for (var u = 0; u < budgetFormDetails.allBudgetCodeList.length; u++) {
		if(activityId == budgetFormDetails.allBudgetCodeList[u].activityId){
			$("#activityId").val(budgetFormDetails.allBudgetCodeList[u].activityId);
			$("#costType").val(budgetFormDetails.allBudgetCodeList[u].costType);
			$("#activityNumber").val(budgetFormDetails.allBudgetCodeList[u].activityNumber);
			$("#activityDescription").val(budgetFormDetails.allBudgetCodeList[u].activityDescription);
			$("#save").html(
			"<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
			break;
		}
	}
}

function setBudgetCodeToDelete(activityId){
	  // $("#activityId").val(activityId);
	    var dataObj = {
	    		activityIdToDel : activityId,
	    	};
	    $.ajax({
			url : "budgetCodeDeleteAction",
			type : "POST",
			data : dataObj,
			success : function(result) {
				result = JSON.parse(result);
				// CLearing Project Table
				if (result.ajaxResult == "success") {
					getBudgetFormDetails();
					gritterForSucessMsgs("Budget Code has been deleted successfully.");
				} else {
					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
							+ result.reason);
				}
			},
			error : function() {
				$("#budgetFormModal").modal("hide");
				gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

			}
		});
}
function resetbudgetForm(){
	var validator = $( "#budget_form_id" ).validate();
	validator.resetForm();
			$('label').removeClass("state-success");
			$('label').removeClass("state-error");
			$("#modaltitleIdForBudgetCodes").html("<i class='fa fa-lg fa-fw fa-barcode txt-color-blue'></i>&nbsp;Add New Budget Code");
			$("#activityId").val('');
			$("#activityDescription").val("");
			$("#activityNumber").val("");
			$("#costType").val("");
			$("#save").html(
			"<i class='fa fa-floppy-o'></i> &nbsp; Save ");
			$("#saveandcontinue").show();
}
function settingCostTypeCount(budgetFormDetails){
	var totalcount=matcount=subcount=djecount=indircount=eqipcount=projadcount=lbrcount=0;
	for (var t = 0; t < budgetFormDetails.allBudgetCodeList.length; t++) {
		if(budgetFormDetails.allBudgetCodeList[t].costType == "MAT"){
			matcount++;
			}
		if(budgetFormDetails.allBudgetCodeList[t].costType == "SUB"){
			subcount++;
			}
		if(budgetFormDetails.allBudgetCodeList[t].costType == "DJE"){
			djecount++;
			}
		if(budgetFormDetails.allBudgetCodeList[t].costType == "INDIRECT"){
			indircount++;
			}
		if(budgetFormDetails.allBudgetCodeList[t].costType == "EQUIP"){
			eqipcount++;
			}
		if(budgetFormDetails.allBudgetCodeList[t].costType == "PROJECT ADMIN"){
			projadcount++;
			}
		if(budgetFormDetails.allBudgetCodeList[t].costType == "LABOR"){
			lbrcount++;
			}
	}
	totalcount=matcount+subcount+djecount+indircount+eqipcount+projadcount+lbrcount;
	$("#totalId").html(totalcount);
	$("#matId").html("&nbsp;"+matcount);
	$("#subId").html("&nbsp;"+subcount);
	$("#djeId").html("&nbsp;"+djecount);
	$("#indirId").html("&nbsp;"+indircount);
	$("#eqipId").html("&nbsp;"+eqipcount);
	$("#projadId").html("&nbsp;"+projadcount);
	$("#lbrId").html("&nbsp;"+lbrcount);
}
function setActionType(buttonId, laddaButtonTemp){
	$("#typeOfAction").val(buttonId);
	laddaButton = laddaButtonTemp;
}

function resetBudgetUploadForm(){
	$("#confirmBudgetUploadId").val("");
	$("#budgetFileName").val("");
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

$("#budgetUploadForm").validate({
	rules : {
		budgetTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		budgetTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForBudgetTemplate();
	}
});

function ajaxCallForBudgetTemplate(){
	var msg="";
	$
	.ajax({
		url : 'budgetTemplateController',
		data : new FormData($("#budgetUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			$("#budgetUploadModal").modal("hide");
			result = JSON.parse(data);
			if(result.ajaxResult =="failure"){
				resetBudgetUploadForm();
				var out = document.getElementById("errorBlock");
				out.innerHTML = "";
				
				if(result.reason[0].colNumber == -1){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#budgetConfirmationHeader").hide();
					$("#saveBudgetConfirmUpload").hide();
					$("#cancelBudgetUpload").html("Close");
					getBudgetFormDetails();
				}
				
				else if(result.reason[0].excelCell == "A0"){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveBudgetConfirmUpload").hide();
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
				$("#budgetErrorSection").css('display','block');
				$("#budgetUploadConfirmation").modal("show");
			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("A file of budget type has been successfully added");
				resetBudgetUploadForm();
				getBudgetFormDetails();
				cancelBudgetUploadForm();
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
function setBudgetUploadConfirmation(buttonId){
	 
	$("#budgetUploadConfirmation").modal("hide"); 
	if(buttonId == "saveBudgetConfirmUpload"){
	  $("#confirmBudgetUploadId").val(1);
	  ajaxCallForBudgetTemplate();
	}
	else
	  $("#confirmBudgetUploadId").val(-1);
}

function resetBudgetConfirmation(){
	$("#saveBudgetConfirmUpload").show();
	$("#budgetConfirmationHeader").show();
	$("#cancelBudgetUpload").html("Cancel");
	$("#budgetUploadConfirmation").modal("hide");
}

function cancelBudgetUploadForm(){
	var validator = $("#budgetUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}