/*
 * DO NOT REMOVE : GLOBAL FUNCTIONS!
 * 
 * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS // activate tooltips
 * $("[rel=tooltip]").tooltip(); // activate popovers
 * $("[rel=popover]").popover(); // activate popovers with hover states
 * $("[rel=popover-hover]").popover({ trigger: "hover" }); // activate inline
 * charts runAllCharts(); // setup widgets setup_widgets_desktop(); // run form
 * elements runAllForms();
 * 
 * *******************************
 * 
 * pageSetUp() is needed whenever you load a page. It initializes and checks for
 * all basic elements of the page and makes rendering easier.
 * 
 */

pageSetUp();

/*
 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE eg alert("my home function");
 * 
 * var applicationDetailPagefunction = function() { ... }
 * loadScript("js/plugin/_PLUGIN_NAME_.js", applicationDetailPagefunction);
 * 
 */

// PAGE RELATED SCRIPTS
// applicationDetailPagefunction
var laddaButton = 0;
$.root_ = $("body");
// var initApp = function(a) {
// return
// a.SmartActions = function() {
// var a = {
// alertBox : function(a) {
// $
// .SmartMessageBox(
// {
// title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
// content : a.data("logout-msg")
// || "<br>This role will be "
// + "permanently deleted . Only Admin can recover it.<br>"
// + ""
// + "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark
// bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
// buttons : "[No][Yes]"
// }, function(a) {
// if (a == "Yes") {
//												
// }
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
// try {
// initApp.SmartActions();
// } catch (e) {
// console.log(e);
// }
//
// });
applicationDetailTable = null;
var applicationDetailPagefunction = function() {

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

	applicationDetailTable = $('#applicationDetailTable')
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
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
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:setApplicationToUpdate('"
												+ full[4]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='delete' data-toggle='tooltip' data-action='alertBox' href='javascript:void(0)' onclick=\"javascript:setApplicationToDelete('"
												+ full[4]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#applicationDetailTable'),
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
	getApplicationDetails();
	/* END BASIC */
};

// load related plugins

loadScript("assets/js/datatable/jquery.dataTables.min.js", function() {
	loadScript("assets/js/datatable/dataTables.tableTools.min.js", function() {
		loadScript("assets/js/datatable/dataTables.bootstrap.min.js",
				function() {
					loadScript(
							"assets/js/datatable/datatables.responsive.min.js",
							applicationDetailPagefunction);
				});
	});
});

// Form Validations
$("#appDetailForm")
		.validate(
				{
					// Rules for form validation
					rules : {
						applicationName : {
							required : true,
						},

						applicationLink : {
							required : true,
						},
						description : {
							required : function() {
								return (document
										.getElementById('listUnderAppStore').checked == true)
							}
						}
					},

					// Messages for form validation
					messages : {
						applicationName : {
							required : "Enter the name",
						},
						applicationLink : {
							required : "Enter the link",
						},
						description : {
							required : "Enter the description",
						}
					},

					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					},
					submitHandler : function(form) {
						submitControllerAppDetail();
					}
				});

// All the required functions are defined here
function getApplicationDetails() {
	var applicationNameList = new Array();
	while (applicationNameList.length > 0){
		applicationNameList.pop();
	}
	$
			.ajax({
				url : "getApplicationDetails",
				type : "POST",
				success : function(resultJSON) {
					result = JSON.parse(resultJSON);
					var count = 1;
					var applicationCount = 0;
					var description = "";
					console.log(result);

					for (var i = 0; i < result.allApplicationDetailsList.length; i++) {

						if (result.allApplicationDetailsList[i].status == "ACTIVE") {
							applicationCount++;
							applicationNameList.push(result.allApplicationDetailsList[i].applicationName);
							applicationNameList.push(result.allApplicationDetailsList[i].applicationId);
							if (result.allApplicationDetailsList[i].description == undefined
									|| result.allApplicationDetailsList[i].description == null) {

								description = "";
							} else {
								description = result.allApplicationDetailsList[i].description;
							}
							applicationDetailTable
									.fnAddData(
											[
													count,
													result.allApplicationDetailsList[i].applicationName,
													result.allApplicationDetailsList[i].applicationLink,
													description,
													result.allApplicationDetailsList[i].applicationId ],
											false);
							count++;
						}
					}
					populateApplicationName(applicationNameList);
					applicationDetailTable.fnDraw();
					$("#totalApplication").html(applicationCount);
				}
			});
}
function resetApplicationForm() {
	$("#description").val('');
	$("#action").val('');
	$("#submittedDate").val('');
	var validator = $("#appDetailForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#modalTitleIdForApplication").html("<i class='fa fa-fw fa-lg fa-font txt-color-blue'></i>Add New Application");
	$('input').val('');
	$("#listUnderAppStore").prop('checked', false);
	$("#saveAndContinue").show();
	$("#Save").html("Save");

}
function setApplicationAction(action) {

	if ($("#action").val() == "")
		$("#action").val(action);
	if (!(action == "update" || action == "delete")) {
		$("#submittedDate").val(
				moment(new Date()).format('MM/DD/YYYY HH:MM:SS').toString());
	}
}

function setApplicationToUpdate(id) {
	$("#modalTitleIdForApplication").html("<i class='fa fa-fw fa-lg fa-font txt-color-blue'></i>Update Application");
	$("#applicationModal").modal("show");
	$("#applicationId").val(id);
	$("#saveAndContinue").hide();
	$("#Save").html("Update");
	$("#action").val("update");

	for (var i = 0; i < result.allApplicationDetailsList.length; i++) {
		if (id == result.allApplicationDetailsList[i].applicationId) {
			$("#appName").val(
					result.allApplicationDetailsList[i].applicationName);

			$("#applicationLink").val(
					result.allApplicationDetailsList[i].applicationLink);
			$("#description").val(
					result.allApplicationDetailsList[i].description);
			if (result.allApplicationDetailsList[i].listUnderAppStore == true) {
				$("#listUnderAppStore").prop('checked', true);
			}
			$("#submittedDate").val(
					moment(result.allApplicationDetailsList[i].submittedDate)
							.format('MM/DD/YYYY HH:MM:SS').toString());
			break;
		}
	}

}
function setApplicationToDelete(id) {
	$("#applicationId").val(id);
	$("#action").val("delete");
	$.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This Application will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							deleteAnApplication(id);
						}
					});
}
function submitControllerAppDetail() {
	$
			.ajax({
				url : "applicationDetailFormController",
				type : "POST",
				data : $("#appDetailForm").serialize(),
				success : function(resultant) {
					resultant = JSON.parse(resultant);
					applicationDetailTable.fnClearTable();
					if (($("#action").val() == "save")
							|| ($("#action").val() == "update")) {
						$("#applicationModal").modal("hide");

					}

					if (resultant.ajaxResult === "success") {
						getApplicationDetails();
						if (($("#action").val() == "save")
								|| ($("#action").val() == "saveandcontinue")) {
							$("#action").val("save");
						}
						gritterForSucessMsgs("A record of Application Detail has been "
								+ $("#action").val() + "d successfully.");
						resetApplicationForm();
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
function deleteAnApplication(id) {
	var dataObj = {
		projId : id
	};
	$
			.ajax({
				url : "deleteApplicationMethod",
				method : "POST",
				data : dataObj,
				success : function(delResultant) {
					var dResult = JSON.parse(delResultant);
					applicationDetailTable.fnClearTable();
					if (dResult.ajaxResult === "success") {
						getApplicationDetails();
						gritterForSucessMsgs("A record of project type has been "
								+ $("#action").val() + "d successfully.");
						resetApplicationForm();
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ dResult.reason);
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

function submitType(laddaButtonTemp) {
	laddaButton = laddaButtonTemp;
}

function populateApplicationName(list){
	var elementId = document.getElementById("applicationName");
	elementId.options.length = 1;
	for (var i=0; i <list.length;){    
	        var optn = document.createElement("OPTION");
		    optn.text = list[i];
		    optn.value = list[i+1];
		    elementId.options.add(optn);
		    i+=2;
	}   
}