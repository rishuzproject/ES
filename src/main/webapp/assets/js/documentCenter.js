/* DO NOT REMOVE : GLOBAL FUNCTIONS!
 *
 * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS
 *
 * // activate tooltips
 * $("[rel=tooltip]").tooltip();
 *
 * // activate popovers
 * $("[rel=popover]").popover();
 *
 * // activate popovers with hover states
 * $("[rel=popover-hover]").popover({ trigger: "hover" });
 *
 * // activate inline charts
 * runAllCharts();
 *
 * // setup widgets
 * setup_widgets_desktop();
 *
 * // run form elements
 * runAllForms();
 *
 ********************************
 *
 * pageSetUp() is needed whenever you load a page.
 * It initializes and checks for all basic elements of the page
 * and makes rendering easier.
 *
 */

pageSetUp();

/*
 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE eg alert("my home function");
 * 
 * var pagefunction = function() { ... }
 * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
 * 
 */

// PAGE RELATED SCRIPTS
// pagefunction
documentDetailsTable = null;

var documetnIdToDel = 0;
var docLaddaButton = 0;

//$.root_ = $("body");
//var   initApp = function(a) {
//        return a.SmartActions = function() {
//            var a = {
//                alertBox: function(a) {
//                    $.SmartMessageBox({
//                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
//                        content: a.data("logout-msg") || "<br>This document will be "+
//						"permanently deleted and cannot be recovered.Only Admin can	recover it.<br>"+"" +
//						"<i class='ace-icon fa fa-hand-o-right blue bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete ?</b>",
//                        buttons: "[No][Yes]"
//                    }, function(a) {
//                    	if(a=="Yes"){setDocumentToDelete(documetnIdToDel);}
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
	documentDetailsTable = $('#documentCenterTable')
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
												return full[5];
											}
										},
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return "<a  title='Download Document' href= 'javascript:void(0)' onclick=\"javascript:setDocumentToDowload('"
												+ full[4]
												+ "')\">"
												+ full[2]
												+ "</a>";
												
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
												return "<div class='pull-right action-buttons'><a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setDocumentId('"
											+ full[0]
											+ "','"+full[4]
											+"')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
											}
										} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#documentCenterTable'),
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
	getDocumentDetails();

	/* END BASIC */
};
function setDocumentId(docId,fileId){
	documetnIdToDel = docId;
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Document will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					setDocumentToDelete(documetnIdToDel,fileId);
				}
			});
}

function setDocumentToDelete(compId,fileDeleteId){
    var dataObj = {
    		documentId : compId,
    		fileId : fileDeleteId
    	};
    console.log(dataObj);
    $.ajax({
		url : "deleteDocumentById",
		type : "GET",
		data : dataObj,
		success : function(result) {
			result = result;
			// CLearing Project Table
			if (result.ajaxResult == "success") {
				getDocumentDetails();
				 gritterForSucessMsgs("Document has been deleted successfully.");
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


function getDocumentDetails() {
	documetDetails = "user";
	$
			.ajax({
				url : "getDocumentsList",
				type : "GET",
				success : function(result) {
					documetDetails = result;
					documentDetailsTable.fnClearTable();
					var docCount = 0;
					$("#totalDocumentsId").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+documetDetails.allDocumentDetailsList.length);
					for (var t = 0; t < documetDetails.allDocumentDetailsList.length; t++) {

						documentDetailsTable
								.fnAddData(
										[
												documetDetails.allDocumentDetailsList[t].documentCenterId,
												documetDetails.allDocumentDetailsList[t].documentId,
												documetDetails.allDocumentDetailsList[t].documentName,
												documetDetails.allDocumentDetailsList[t].documentDescription,
												documetDetails.allDocumentDetailsList[t].fileId,
												++docCount],
										false);
					}
					documentDetailsTable.fnDraw();
				}
			});
}


// Form Validations
$("#addDocumentForm").validate({
	// Rules for form validation
	rules : {
		documentName : {
			required : true
		},

		/*documentId : {
			required : true
		},*/
		documentDescription : {
			required : true,
			maxlength : 2000
		},
		fileName : {
			required : true
		},
	},

	// Messages for form validation
	messages : {
		documentName : {
			required : "Enter the document name"
		},
		/*documentId : {
			required : "Enter your document number"
		},*/
		fileName : {
			required : "Choose the Document to Upload"
		},
		documentDescription : {
			required : "Please provide document description",
			maxlength : "Enter the description in less than 2000 characters"
		},
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		ajaxCallForSaveDocumentDetails(form.id);
	}
});

function checkFileSize(thisObj){
	if(thisObj.files[0].size > 15*Math.pow(2, 20) ){
		thisObj.parentNode.nextSibling.value = "";
		$("#fileName").val("");
		gritterForErrorMsgs("The attachement size cannot exceed 15MB.");
	} else {
		thisObj.parentNode.nextSibling.value = thisObj.value;
	}
}

function ajaxCallForSaveDocumentDetails(formId) {
	var laddaRef = Ladda.create(docLaddaButton);
	laddaRef.start();
	$
			.ajax({
				url : 'saveDocumentDetails',
				data : new FormData($("#" + formId)[0]),
				cache : false,
				contentType : false,
				processData : false,
				type : 'POST',
				success : function(data) {
					laddaRef.stop();
					getDocumentDetails();
					resetApplicationForm();
					if($("#docActionType").val() != "saveandcontinue"){
						$("#documentCenterRemoteModal").modal("hide");
					}
					
					result = data;
					if (result.ajaxResult == "success") {
						gritterForSucessMsgs("Document Details has been saved successfuly.");
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					laddaRef.stop();
					resetApplicationForm();
					$("#documentCenterRemoteModal").modal("hide");
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
				}
			});
}
function setDocumentToDowload(downloadDocId){
	
	// Commented by Vaibhav, Submitting a form to download a file.
	/*var data={
			fileId:downloadDocId
			};
	$
	.ajax({
		url : 'downloadDocumentById',
		type:'GET',
		data : data,
		success : function() {
			
				gritterForSucessMsgs("Document has been downloaded successfully.");
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
		}
	});*/
	
	console.log(downloadDocId);
	if (downloadDocId != undefined && downloadDocId != null && downloadDocId != "") {
		console.log(downloadDocId);
		$("#fileId").val(downloadDocId);
		$("#documentCenterDownload").submit();
	}
}
function resetApplicationForm() {
	var validator = $("#addDocumentForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#documentId").val("");
	$("#documentCenterId").val("");
	$("#documentName").val("");
	$("#documentDescription").val("");
	$("#fileName").val("");
	$('input').val('');
}
function setDocActionType(buttonId, docLaddaButtonTemp){
	$("#docActionType").val(buttonId);
	docLaddaButton = docLaddaButtonTemp;
}
