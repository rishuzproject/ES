/**
 * 
 * @author Anantha MeghanaJagruthi
 * 
 */



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
	 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE
	 * eg alert("my home function");
	 * 
	 * var pagefunction = function() {
	 *   ...
	 * }
	 * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
	 * 
	 */

	// PAGE RELATED SCRIPTS
	// pagefunction	
	projectTypeDetailsTable=null;
	var pagefunction = function() {
		//console.log("cleared");

		/* // DOM Position key index //
		
			l - Length changing (dropdown)
			f - Filtering input (search)
			t - The Table! (datatable)
			i - Information (records)
			p - Pagination (paging)
			r - pRocessing 
			< and > - div elements
			<"#id" and > - div with an id
			<"class" and > - div with a class
			<"#id.class" and > - div with an id and class
			
			Also see: http://legacy.datatables.net/usage/features
		 */

		/* BASIC ;*/
//		$.root_ = $("body");
//		var   initApp = function(a) {
//		        return a.SmartActions = function() {
//		            var a = {
//		                alertBox: function(a) {
//		                    $.SmartMessageBox({
//		                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
//		                        content: a.data("logout-msg") || "<br>This role will be "+
//								"permanently deleted . Only Admin can	recover it.<br>"+"" +
//								"<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
//		                        buttons: "[No][Yes]"
//		                    }, function(a) {
//		                    	if(a=="Yes"){submitController();}
//		                    });
//		                }
//		            };
//		            $.root_.on("click", '[data-action="alertBox"]', function(b) {
//		                var c = $(this);
//		                a.alertBox(c), b.preventDefault(), c = null;
//		            });
//		        },  a
//		    }({});
//		    jQuery(document).ready(function() {
//		        initApp.SmartActions(); 
//		       
//		    });
		var responsiveHelper_dt_basic = undefined;

		var breakpointDefinition = {
			tablet : 1024,
			phone : 480
		};

		projectTypeDetailsTable=$('#typesOfProjectTable')
				.dataTable(
						{
							"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
									+ "t"
									+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
							"autoWidth" : true,
							aoColumns : [{
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
								mData : null,
								sClass : "center",
								mRender : function(data, type, full) {
									return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:setProjectTypeToUpdate('"
										+ full[2]
										+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setProjectTypeToDelete('"
											+ full[2]
											+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
								}
							} ],
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_dt_basic) {
									responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
											$('#typesOfProjectTable'),
											breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_dt_basic
										.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_dt_basic.respond();
							}
						});
		getProjectTypeDetails();

		/* END BASIC */
	};
//	$(document).on("click", ".deleteConfirmDialog", function() {
//		alert("Enetered delete js");
//		$("#projectTypeId").val($(this).data("id"));
//		$("#projectTypeAction").val("Delete");
//
//	})
function setProjectTypeToDelete(id)
	{
	$("#projectTypeId").val(id);
	for(var i=0;i<result.projectTypeDetails.length;i++)
	{
	    if(id==result.projectTypeDetails[i].projectTypeId)
	    {
	        $("#projectTypeName").val(result.projectTypeDetails[i].projectTypeName);	
	        break;
	    }
	}
 	$("#projectTypeAction").val("delete");
 	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Project Type will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					submitController();
				}
			});
 	
	}
	// load related plugins

	loadScript(
			"assets/js/datatable/jquery.dataTables.min.js",
			function() {
				loadScript(
						"assets/js/datatable/dataTables.tableTools.min.js",
						function() {
							loadScript(
									"assets/js/datatable/dataTables.bootstrap.min.js",
									function() {
										loadScript(
												"assets/js/datatable/datatables.responsive.min.js",
												pagefunction);
									});
						});
			});
	
	// Form Validations
	$("#projectTypeForm").validate({
		// Rules for form validation
		rules : {
			projectTypeName : {
				required :true,
			}
		},
			
		// Messages for form validation
		messages : {
			projectTypeName : {
				required :"Enter the project type"
			}
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function(form) {
			
			submitController();
			
        }
	});
	function setProjectTypeId(action)
	{
		
		if($("#projectTypeAction").val()=="")
		$("#projectTypeAction").val(action);
		
	}
function resetProjectTypeForm(){
	$("#modalTitleIdForProjTypes").html("<i class='fa fa-file fa-fw txt-color-blue' ></i> Add New Project");
		 var validator = $("#projectTypeForm").validate();
		 validator.resetForm();
		 $('label').removeClass("state-success");
		 $('label').removeClass("state-error");
		 $('input').val('');
		 $("#saveandcontinue").show();
		 $("#save").html("Save");
		 }
function setProjectTypeToUpdate(id)
{
	$("#remoteModal").modal("show");
	$("#projectTypeAction").val("update");
	$("#projectTypeId").val(id);
	$("#saveandcontinue").hide();
	$("#save").html("Update");
	$("#modalTitleIdForProjTypes").html("<i class='fa fa-file fa-fw txt-color-blue' ></i> Update Project");
	for(var i=0;i<result.projectTypeDetails.length;i++)
		{
		    if(id==result.projectTypeDetails[i].projectTypeId)
		    {
		        $("#projectTypeName").val(result.projectTypeDetails[i].projectTypeName);	
		        break;
		    }
		}
	
	
}
function getProjectTypeDetails()
{
	
	
	$.ajax({
		url:"getProjectTypeDetails",
		type:"POST",
		success:function(resultJSON)
		{
			
			result=JSON.parse(resultJSON);
			$("#totalInvoices").html(result.projectTypeDetails.length);
			projectTypeDetailsTable.fnClearTable();
			var count=0;
			for(var i=0;i<result.projectTypeDetails.length;i++)
			{
				
				if(result.projectTypeDetails[i].status=="ACTIVE"){
					++count;
				projectTypeDetailsTable
				.fnAddData([count,result.projectTypeDetails[i].projectTypeName,result.projectTypeDetails[i].projectTypeId],false);
				
				}
			}
			projectTypeDetailsTable.fnDraw();
		}
	});
}

function submitController(){
	
	$.ajax({
		url:"projectTypeFormController",
		type:"POST",
		data:$("#projectTypeForm").serialize(),
		success:function(resultant)
		{
			resultant=JSON.parse(resultant);
			projectTypeDetailsTable.fnClearTable();
			if(($("#projectTypeAction").val()=="save")||($("#projectTypeAction").val()=="update")||($("#projectTypeAction").val()=="delete")){
				$("#remoteModal").modal("hide");
				
			}
			
			if (resultant.ajaxResult === "success") {
				getProjectTypeDetails();
				if(($("#projectTypeAction").val()=="save")||($("#projectTypeAction").val()=="saveandcontinue"))
					{
					$("#projectTypeAction").val("save");
					}
				 gritterForSucessMsgs("A record of project type has been "+$("#projectTypeAction").val()+"d successfully.");
				 resetProjectTypeForm();
			} else {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ resultant.reason);
			}
			
		},
		error : function() {
			gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
		}
		
	
	
	});
}

function resetProjectUploadForm(){
	$("#confirmProjectUploadId").val(null);
	$("#projectFileName").val(null);
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

$("#projectUploadForm").validate({
	rules : {
		projectTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		projectTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		console.log("ajax call for template");
		ajaxCallForAddProjectTemplate();
	}
});

function ajaxCallForAddProjectTemplate(){
	console.log("in ajax call for template");
	$("#className").val('PROJECT_TYPE_EXCEL_FORMAT');
	
	var msg="";
	$
	.ajax({
		url : 'projectTemplateController',
		data : new FormData($("#projectUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			console.log("again in ajax");
			$("#projectUploadModal").modal("hide");
			result = JSON.parse(data);
			console.log(result);
			if(result.ajaxResult =="failure"){
				resetProjectUploadForm();
				var out = document.getElementById("errorBlock");
				out.innerHTML = "";
				if(result.reason[0].colNumber == -1){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#projectConfirmationHeader").hide();
					$("#saveProjectConfirmUpload").hide();
					$("#cancelProjectUpload").html("Close");
					getProjectTypeDetails();
				}
				else if(result.reason[0].excelCell == "A1"){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveProjectConfirmUpload").hide();
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
				$("#projectUploadConfirmation").modal("show");
				$("#projectErrorSection").css('display','block');
			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("A file of project type has been successfully added");
				resetProjectUploadForm();
				getProjectTypeDetails();
				cancelProjectUploadForm();
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
function setProjectUploadConfirmation(buttonId){
	 
	$("#projectUploadConfirmation").modal("hide"); 
	if(buttonId == "saveProjectConfirmUpload"){
	  $("#confirmProjectUploadId").val(1);
	  console.log("b4");
	  ajaxCallForAddProjectTemplate();
	}
	else
	  $("#saveProjectConfirmUpload").val(-1);
}

function resetProjectConfirmation(){
	$("#saveProjectConfirmUpload").show();
	$("#projectConfirmationHeader").show();
	$("#cancelProjectUpload").html("Cancel");
	$("#projectUploadConfirmation").modal("hide");
}

function cancelProjectUploadForm(){
	var validator = $("#projectUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}