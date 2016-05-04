/**
 * 
 */
pageSetUp();

unitAbbreviationTable = null;
var abbreviationIdToDelete = 0;

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
	unitAbbreviationTable = $('#unitAbbreviationTable')
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
										return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:unitAbbreviationUpdate('"
												+ full[0]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a class='deleteConfirmDialog' title='delete' data-toggle='modal' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setAbbreviationId('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#unitAbbreviationTable'),
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
	getUnitAbbreviationDetails();
	/* END BASIC */
};

function setAbbreviationId(abbreviationId) {
	abbreviationIdToDelete = abbreviationId;
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This Unit Abbreviation will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							abbreviationDelete();
						}
					});
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

$("#unitAbbreviationForm").validate({
	// Rules for form validation
	rules : {
		abbreviationName : {
			required : true,
		},
		abbreviationMeaning : {
			required : true,
		}
	},
		
	// Messages for form validation
	messages : {
		abbreviationName : {
			required : 'Please enter the abbreviation name',
		},
		abbreviationMeaning : {
			required : 'Please enter the abbreviation meaning',
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler: function(form) {
        //form.submit();
        ajaxCallForAddNewUnitAbbreviation(form.id);
    }
});

function  resetUnitAbbreviationForm(){
	var validator = $("#unitAbbreviationForm").validate();
	validator.resetForm();
			$('label').removeClass("state-success");
			$('label').removeClass("state-error");
			$('input').val('');
			$("#modalTitleId").html("Add New Unit Abbreviation");
			$("#saveandcontinue").show();
			$("#save").html("Save");
}

function ajaxCallForAddNewUnitAbbreviation(formId){
	//var laddaRef = Ladda.create(laddaButton);
	//laddaRef.start( $("#" + formId).serialize());
	$
		.ajax({
			url : "addUpdateUnitAbbreviation",
			type : "POST",
			data : $("#" + formId).serialize(),
			success : function(result) {
				//laddaRef.stop();
				if($("#submitId").val()=="save")
					$("#unitAbbreviationModal").modal("hide");
				result = JSON.parse(result);
				if (result.ajaxResult == "success"){
					if($("#unitAbbreviationAction").val()=="update"){
						gritterForSucessMsgs("A record of unit abbreviation type has been successfully updated.");
					}
					else{
						gritterForSucessMsgs("A record of unit abbreviation type has been successfully saved.");
					}
					getUnitAbbreviationDetails();
				}
				else {
					$("#unitAbbreviationModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
							+ result.reason);
				}
				resetUnitAbbreviationForm();
			},
			error : function() {
				//laddaRef.stop();
				resetUnitAbbreviationForm();
				$("#unitAbbreviationModal").modal("hide");
				gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
			}
	});
}

function getUnitAbbreviationDetails(){
	unitAbbreviationDetails = "unitAbbreviationDetails";
	$.ajax({
		url : "getUnitAbbreviationForDatatables",
		type : "POST",
		contentType : 'text/plain',
		data : '{"unitAbbreviationDetails": "' + unitAbbreviationDetails
				+ '"}',
		success : function(result) {
			unitAbbreviationDetails = JSON.parse(result);
			if(unitAbbreviationDetails.ajaxResult == "success"){
				unitAbbreviationTable.fnClearTable();
			$("#totalUnitsCount").html(unitAbbreviationDetails.allAbbreviationsList.length);
			for (var t = 0; t < unitAbbreviationDetails.allAbbreviationsList.length; t++) {
				unitAbbreviationTable
						.fnAddData(
								[
								 		unitAbbreviationDetails.allAbbreviationsList[t].abbreviationId,
										unitAbbreviationDetails.allAbbreviationsList[t].abbreviationName,
										unitAbbreviationDetails.allAbbreviationsList[t].abbreviationMeaning,
										(t+1)],
										false);
			}
			unitAbbreviationTable.fnDraw();
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ unitAbbreviationDetails.reason);
			}
		}
	});
}

function unitAbbreviationUpdate(id){
	
	$("#modalTitleId").html("Update Unit Abbreviation");
	$("#unitAbbreviationModal").modal("show");
	$("#unitAbbreviationAction").val("update");
	for(var i=0; i<unitAbbreviationDetails.allAbbreviationsList.length;i++){
		if(id==unitAbbreviationDetails.allAbbreviationsList[i].abbreviationId){
			$("#abbreviationName").val(unitAbbreviationDetails.allAbbreviationsList[i].abbreviationName);
			$("#abbreviationMeaning").val(unitAbbreviationDetails.allAbbreviationsList[i].abbreviationMeaning);
			$("#abbreviationId").val(id);
			$("#save").html("<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
			break;
			}
		}
}

function abbreviationDelete(){
	$("#unitAbbreviationAction").val("delete");
	//var deptId = $("#departmentId").val();
	var dataObj = {
		abbreviationIdToDel : abbreviationIdToDelete,
	};
	
	$
	.ajax({
		url : "deleteUnitAbbreviation",
		type : "POST",
		data : dataObj,
		success : function(result) {
			result = JSON.parse(result);
			// CLearing Department Table
			if (result.ajaxResult == "success") {
				gritterForSucessMsgs("A record of unit abbreviation type has been successfully deleted");
				getUnitAbbreviationDetails();
			} else {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ result.reason);
			}
			resetUnitAbbreviationForm();
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

		}
	});
}

function submitType(buttonId, laddaButtonTemp)
{
		$("#submitId").val(buttonId);
		laddaButton = laddaButtonTemp;
}