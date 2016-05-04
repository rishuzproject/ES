
pageSetUp();
		// Model i agree button
	/*	$("#i-agree").click(function() {
			$this = $("#terms");
			if ($this.checked) {
				$('#myModal').modal('toggle');
			} else {
				$this.prop('checked', true);
				$('#myModal').modal('toggle');
			}
		});*/
DepartmentDetailsTable = null;
var laddaButton = 0;
var deptIdToDelete = 0;

//$.root_ = $("body");
//var   initApp = function(a) {
//        return a.SmartActions = function() {
//            var a = {
//                alertBox: function(a) {
//                    $.SmartMessageBox({
//                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
//                        content: a.data("logout-msg") || "<br>This department will be "+
//						"permanently deleted and cannot be recovered.Only Admin can	recover it.<br>"+"" +
//						"<i class='ace-icon fa fa-hand-o-right blue bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete ?</b>",
//                        buttons: "[No][Yes]"
//                    }, function(a) {
//                    	if(a=="Yes"){departmentDelete();}
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
	DepartmentDetailsTable = $('#departmentDetailsTable')
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
												return full[2];
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
									         return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:departmentUpdate('"
									          + full[0]
									          + "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a class='deleteConfirmDialog' title='delete' data-toggle='modal' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setDepartmentId('"
									          + full[0]
									          + "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									        }
									       } 
										],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#departmentDetailsTable'),
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
	getDepartmentDetails();
	/* END BASIC */
};
function setDepartmentId(deptId) {
	deptIdToDelete = deptId;
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Department will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					departmentDelete();
				}
			});

};

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

$("#departmentDetailsForm").validate({
	// Rules for form validation
	rules : {
		departmentName : {
			required : true,
			
		}
	},
		
	// Messages for form validation
	messages : {
		
		departmentName : {
			required : "Please enter the department name"
			
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler: function(form) {
        //form.submit();
        ajaxCallForAddNewDepartment(form.id);
    }
});

function  resetDepartmentDetailsForm(){
	var validator = $("#departmentDetailsForm").validate();
	validator.resetForm();
	resetDualListBox();//resets the dual listbox
			$('label').removeClass("state-success");
			$('label').removeClass("state-error");
			$('input').val('');
			$("#departmentName").val("");
			$("#modalTitleId").html("Add New Department");
			$("#saveandcontinue").show();
			$("#save").html("Save");
			$("#depHead").html('<i class="fa fa-edit txt-color-blue toggleDualListBox" >&nbsp; </i>');
     		$("#depStaff").html('<i class="fa fa-edit txt-color-blue toggleDualListBox1" >&nbsp; </i>');
     		$('.toggleDualListBox').on('click',function(){
     			$('.dualListBoxDiv').toggleClass('hide');
     			$('.dualListBoxDiv1').addClass('hide');
     			$('#departmentStaffID').addClass('custMargin');
     		});
     		
     		$('.toggleDualListBox1').on('click',function(){
     			$('.dualListBoxDiv1').toggleClass('hide');
     			$('.dualListBoxDiv').addClass('hide');
     		});
     		
			$('.dualListBoxDiv').addClass('hide');
			
}

function ajaxCallForAddNewDepartment(formId){
	console.log("!!!!!!!!!   Add New Dept  !!!!!!!!!!!!!!");
	console.log($("#initializeRole").val());
	console.log($("#initializeStaffList").val());
	$("#selectedUsersId").val(JSON.stringify($("#initializeRole").val()));
	$("#selectedStaffId").val(JSON.stringify($("#initializeStaffList").val()));
	
	console.log("serialized form");
	console.log($("#" + formId).serialize());
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start( $("#" + formId).serialize());
	$
		.ajax({
			url : "addDepartmentAction",
			type : "POST",
			data : $("#" + formId).serialize(),
			success : function(result) {
				laddaRef.stop();
				
				if($("#submitId").val()=="save")
					$("#departmentRemoteModal").modal("hide");
				resetDepartmentDetailsForm();	
				result = JSON.parse(result);
				if (result.ajaxResult == "success"){
					if($("#departmentTypeAction").val()=="update"){
						gritterForSucessMsgs("A record of department type has been successfully updated.");
					}
					else{
						gritterForSucessMsgs("A record of department type has been successfully saved.");
					}
					getDepartmentDetails();
					resetDepartmentDetailsForm();
				}
				else {
					$("#departmentRemoteModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
							+ result.reason);
				}
			},
			error : function() {
				laddaRef.stop();
				resetDepartmentDetailsForm();
				$("#departmentRemoteModal").modal("hide");
				gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
			}
	});
}

function getDepartmentDetails(){
	departmentDetails = "department";
	$.ajax({
		url : "getDepartmentDetails",
		type : "POST",
		contentType : 'text/plain',
		data : '{"departmentDetails": "' + departmentDetails
				+ '"}',
		success : function(result) {
			departmentDetails = JSON.parse(result);
			if(departmentDetails.ajaxResult == "success"){
			DepartmentDetailsTable.fnClearTable();
			$("#totalDepartments").html(departmentDetails.allDepartmentList.length);
			for (var t = 0; t < departmentDetails.allDepartmentList.length; t++) {
				DepartmentDetailsTable
						.fnAddData(
								[
										departmentDetails.allDepartmentList[t].departmentId,
										departmentDetails.allDepartmentList[t].departmentName,
										(t+1)],
										false);
			}
			DepartmentDetailsTable.fnDraw();
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ departmentDetails.reason);
			}
		}
	});
}

function resetDualListBox()
{
	 var dualListbox=  $('#initializeRole').bootstrapDualListbox();
	    $("#bootstrap-duallistbox-selected-list_roleName").find('option').each(function() {
	        dualListbox.find('option').data('_selected', false);
	      });
	    $('#bootstrap-duallistbox-selected-list_roleName  option').each(function(index,option){
	 $(option).remove();     	
 	});
	    $('#initializeRole').val("");
	    dualListbox.bootstrapDualListbox('refresh',true);
	    var staffListBox=  $('#initializeStaffList').bootstrapDualListbox();
	    $("#bootstrap-duallistbox-selected-list_staffName").find('option').each(function() {
	    	console.log(staffListBox.find('option').data());
	    	staffListBox.find('option').data('_selected', false);
	      });
	    $('#bootstrap-duallistbox-selected-list_staffName  option').each(function(index,option){
	 $(option).remove();    	
 	});
	    $('#initializeStaffList').val("");
	    staffListBox.bootstrapDualListbox('refresh',true);
}

function departmentUpdate(id){
	$("#modalTitleId").html("Update Department");
	$("#departmentRemoteModal").modal("show");
	$("#departmentTypeAction").val("update");
	resetDualListBox();
	for(var i=0; i<departmentDetails.allDepartmentList.length;i++){
		if(id==departmentDetails.allDepartmentList[i].departmentId){
			$("#departmentName").val(departmentDetails.allDepartmentList[i].departmentName);
			$("#departmentId").val(id);
			$("#save").html("<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
			var deptIds="";
			var departmentId={"deptId":id};
			$.ajax({
				url:"getAssignedUsersByDept",
				type:"POST",
				data:departmentId,
				async:false,
				success:function(result){
					deptIds=JSON.parse(result);
					
				}
			});
			console.log("------department details----");
			console.log(deptIds);
			var deptHeadIds=[],deptStaffIds=[];
			var deptHeadUserId=[],deptStaffUserId=[]
			for(var i=0;i<deptIds.deptUsers.length;i++)
			{
				console.log(deptIds.deptUsers[i].userType);
				if(deptIds.deptUsers[i].userType=="DEPARTMENT HEAD")
					{
					  deptHeadIds.push(deptIds.deptUsers[i]);
					  deptHeadUserId.push(deptIds.deptUsers[i].userId.userId);
					}
				else{
					deptStaffIds.push(deptIds.deptUsers[i]);
					deptStaffUserId.push(deptIds.deptUsers[i].userId.userId);
				}
				
				}
			console.log(deptHeadIds);
			console.log(deptStaffIds);
			 var count=0;
			 $("#initializeRole").find('option').each(function(index,option) {
				 
				 if(count<deptHeadIds.length){
				 if(option.value==deptHeadIds[count].userId.userId){
					 $("#initializeRole option")[index]['selected'] = true;
					 count++;
				 }
				}
			      });
			 var dualListbox=  $('#initializeRole').bootstrapDualListbox();
			 dualListbox.bootstrapDualListbox('refresh',true);
		    count=0;
              $("#initializeStaffList").find('option').each(function(index,option) {
				 
				 if(count<deptStaffIds.length){
				 if(option.value==deptStaffIds[count].userId.userId){
					 $("#initializeStaffList option")[index]['selected'] = true;
					 count++;
				 }
				}
			      });
			 var dualStaffListbox=  $('#initializeStaffList').bootstrapDualListbox();
			 dualStaffListbox.bootstrapDualListbox('refresh',true);
			 updateUsers($('#initializeRole').val(),"depHead");
			 updateUsers($('#initializeStaffList').val(),"depStaff");
			 $('.toggleDualListBox').on('click',function(){
	     			$('.dualListBoxDiv').toggleClass('hide');
	     			$('.dualListBoxDiv1').addClass('hide');
	     			$('#departmentStaffID').addClass('custMargin');
	     		});
	     		
	     		$('.toggleDualListBox1').on('click',function(){
	     			$('.dualListBoxDiv1').toggleClass('hide');
	     			$('.dualListBoxDiv').addClass('hide');
	     		});
			break;
			}
		}
}
function departmentDelete(){
	
	$("#deptDeleteModal").modal("hide");
	$("#departmentTypeAction").val("delete");
	//var deptId = $("#departmentId").val();
	var dataObj = {
		deptIdToDel : deptIdToDelete,
	};
	
	$
	.ajax({
		url : "deleteDepartmentAction",
		type : "POST",
		data : dataObj,
		success : function(result) {
			result = JSON.parse(result);
			resetDepartmentDetailsForm();
			// CLearing Department Table
			if (result.ajaxResult == "success") {
				gritterForSucessMsgs("A record of department type has been successfully deleted");
				getDepartmentDetails();
				resetDepartmentDetailsForm();
			} else {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ result.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

		}
	});
}	

function submitType(buttonId, laddaButtonTemp)
{
		$("#submitId").val(buttonId);
		$("#departmentTypeAction").val(buttonId);
		laddaButton = laddaButtonTemp;
}

function resetDepartmentUploadForm(){
	$("#confirmDepartmentUploadId").val("");
	$("#departmentFileName").val("");
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

$("#departmentUploadForm").validate({
	rules : {
		departmentTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		departmentTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForAddDepartmentTemplate();
	}
});

function ajaxCallForAddDepartmentTemplate(){
	var msg="";
	$("#className").val('DEPARTMENT_TYPE_EXCEL_FORMAT');
	$
	.ajax({
		url : 'projectTemplateController',
		data : new FormData($("#departmentUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			$("#departmentUploadModal").modal("hide");
			result = JSON.parse(data);
			if(result.ajaxResult =="failure"){
				resetDepartmentUploadForm();
				var out = document.getElementById("deptErrorBlock");
				out.innerHTML = "";
				if(result.reason[0].colNumber == -1){
					for(var i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#departmentConfirmationHeader").hide();
					$("#saveDeptConfirmUpload").hide();
					$("#cancelDepartmentUpload").html("Close");
					getDepartmentDetails();
				}
				else if(result.reason[0].excelCell == "A0"){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveDeptConfirmUpload").hide();
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
				$("#departmentUploadConfirmation").modal("show");
				$("#deptErrorSection").css('display','block');
			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("A file of department type has been successfully added");
				resetDepartmentUploadForm();
				getDepartmentDetails();
				cancelDepartmentUploadForm();
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

function setDeptUploadConfirmation(buttonId){
	 
	$("#departmentUploadConfirmation").modal("hide"); 
	if(buttonId == "saveDeptConfirmUpload"){
	  $("#confirmDepartmentUploadId").val(1);
	  ajaxCallForAddDepartmentTemplate();
	}
	else
	  $("#confirmDepartmentUploadId").val(-1);
}

function resetDepartmentConfirmation(){
	$("#saveDeptConfirmUpload").show();
	$("#departmentConfirmationHeader").show();
	$("#cancelDepartmentUpload").html("Cancel");
	$("#departmentUploadConfirmation").modal("hide");
}

function cancelDepartmentUploadForm(){
	var validator = $("#departmentUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}

function setDeptUploadFileName(filePath){
	$("#departmentFileName").val(filePath);
}

function callPaym(){
	console.log("in callPaym function")
	var dataObj = {
		deptIdToDel : 1,
	};
	$
	.ajax({
		url : "paym",
		type : "POST",
		data : dataObj,
		success : function(result) {
			result = JSON.parse(result);
			console.log("RESPONSE::::"+result);
			window.location.href = result;
			resetDepartmentDetailsForm();
			// CLearing Department Table
//			if (result.ajaxResult == "success") {
//				gritterForSucessMsgs("A record of department type has been successfully deleted");
//				getDepartmentDetails();
//				resetDepartmentDetailsForm();
//			} else {
//				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
//						+ result.reason);
//			}
		},
		error : function() {
			console.log("error in ajax call")
			//gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

		}
	});
}
