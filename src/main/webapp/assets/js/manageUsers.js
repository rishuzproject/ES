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
ApplicationUserDetailsTable = null;
PendingUserDetailsTable = null;
var userLaddaButton = 0;
var userIdToDelete = 0;
// $.root_ = $("body");
// var initApp = function(a) {
// return a.SmartActions = function() {
// var a = {
// alertBox: function(a) {
// $.SmartMessageBox({
// title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
// content: a.data("logout-msg") || "<br>This User will be "+
// "permanently deleted and cannot be recovered.Only Admin can recover
// it.<br>"+"" +
// "<i class='ace-icon fa fa-hand-o-right blue
// bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete ?</b>",
// buttons: "[No][Yes]"
// }, function(a) {
// if(a=="Yes"){setUserToDelete();}
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

$(document).ready(function(){
	getUserRoles();
	getOrganisations();
	$("#userRoles").on("change keyup",function(){
		if($("#userRoles").val() != ""){
			$("#userRoles").next().children().prev().removeClass("selectPickerMandatory");
			$("#userRoles").parent().next().remove();
		}
	});
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
	ApplicationUserDetailsTable = $('#userDetailsTable')
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
										return full[7] == null ? "" : full[7];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[1] == null ? "" : full[1];
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
									sClass : "center",
									mRender : function(data, type, full) {
										return full[4] == null ? "" : full[4];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[5] == null ? "" : full[5];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[6] == null ? "" : full[6];
									}
								},

								{
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setUserToUpdate('"
												+ full[0]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setUserId('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#userDetailsTable'),
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
	
	PendingUserDetailsTable = $('#pendingUserDetailsTable')
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
								return full[7] == null ? "" : full[7];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[1] == null ? "" : full[1];
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
							sClass : "center",
							mRender : function(data, type, full) {
								return full[4] == null ? "" : full[4];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[5] == null ? "" : full[5];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[6] == null ? "" : full[6];
							}
						},

						{
							mData : null,
							sClass : "center",
							mRender : function(data, type, full) {
								return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setUserToUpdate('"
										+ full[0]
										+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setUserId('"
										+ full[0]
										+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
							}
						} ],
				"preDrawCallback" : function() {
					// Initialize the responsive datatables helper once.
					if (!responsiveHelper_dt_basic) {
						responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
								$('#pendingUserDetailsTable'),
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
	
	getApplicationUserDetails();

	/* END BASIC */
};

var checkTypeUser="";
function getOrganisations(){
	$.ajax({
		url : "getOrganizations",
		type : "POST",
		contentType : 'text/plain',
		success : function(result) {
			userRoles = result;
//			console.log(result.reason)
			if(userRoles.ajaxResult == "success"){
				var elementId = document.getElementById("organisationName");
				elementId.options.length = 1;
				var roleslistLength=userRoles.reason.length;
//				console.log(roleslistLength);
				for (var i=0; i <roleslistLength;i++){    
			        var optn = document.createElement("OPTION");
				    optn.text = userRoles.reason[i].organizationName;
				    optn.value = userRoles.reason[i].organizationId;
				    elementId.options.add(optn);
				}
				$(".selectpicker").selectpicker("refresh");
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ userRoles.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
		}
	});
}
function getUserRoles() {
	userRoles = "userRoles";
	$.ajax({
		url : "getUserRolesToSelect",
		type : "POST",
		contentType : 'text/plain',
		data : '{"userRoles": "' + userRoles
				+ '"}',
		success : function(result) {
			userRoles =  JSON.parse(result);;
			if(userRoles.ajaxResult == "success"){
				checkTypeUserMethod();
				var elementId = document.getElementById("userRoles");
				elementId.options.length = 1;
				/*if(checkTypeUser)
				var roleslistLength=userRoles.allRolesList.length;          must be uncommented once alla roles are defined for all companies
				else
					var roleslistLength=userRoles.allRolesList.length-1;*/
				
				
				var roleslistLength=userRoles.allRolesList.length;
//				console.log(roleslistLength);
				for (var i=0; i <roleslistLength;i++){    
			        var optn = document.createElement("OPTION");
				    optn.text = userRoles.allRolesList[i].roleName;
				    optn.value = userRoles.allRolesList[i].roleId;
				    elementId.options.add(optn);
				}
				$(".selectpicker").selectpicker("refresh");
				$("#userRoles").next().children().prev().children().next().addClass("caretMandatory");
			}
			else{
				
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ userRoles.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
		}
	});
}

function checkTypeUserMethod()
{
	$.ajax({
		url : "checkUserType",
		type : "POST",
		async:false,
		success : function(result) {
			
			var responseTemp = result;
			checkTypeUser=responseTemp.isBelcouser;
			
		}
	});
}

function getAssociatedRoles(userId){
	var data={"userId":userId};
	$.ajax({
		url:"getAssociatedRoleDetails",
		method:"POST",
		data:data,
		success:function(result){
//			console.log(result);
			var actualResult=JSON.parse(result);
//			console.log(actualResult);
			$("#userRoles").val(actualResult.roleIdsList);
			$("#userRoles").selectpicker("refresh");
			
		}
		
	});
}


function setUserId(userIdToDel) {
	userIdToDelete = userIdToDel;
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This User will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							setUserToDelete();
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


// Form Validations
$("#manage_user_form").validate({
	// Rules for form validation
	rules : {
		emailId : {
			required : true, 
			email : true
		},
		password : {
			required : true,
			minlength : 3,
			maxlength : 50
		},
		confirmPassword : {
			required : true,
			minlength : 3,
			maxlength : 50,
			equalTo : '#manage_user_form #password'
		},
		firstName : {
			required : true,
			maxlength : 34
		},
		nickName : {
			maxlength : 10,
		},
		phoneNumber : {
			required : function(element) {
				return $('input:radio[name=preferredContactMode]:checked').val() != "email" && $('input:radio[name=preferredContactMode]:checked').val() != undefined;
			},
			maxlength : 10,
			digits : true,
		},
		phoneCarrier : {
			required : function(element) {
				return $('input:radio[name=preferredContactMode]:checked').val() != "email" && $('input:radio[name=preferredContactMode]:checked').val() != undefined;
			}
		},
		preferredContactMode : {
			required : true
		},
		userRoles : {
			required : true
		}
	},

	// Messages for form validation
	messages : {
		emailId : {
			required : 'Please enter your email address',
			email : 'Please enter a VALID email address'
		},
		password : {
			required : 'Please enter your password'
		},
		confirmPassword : {
			required : 'Please enter your password one more time',
			equalTo : 'Please enter the same password as above'
		},
		firstName : {
			required : 'Please enter your first name'
		},
		nickName : {
			maxlength : 'Nickname must not be more than 10 characters'
		},
		phoneNumber : {
			required : 'Please enter your Phone Number(Upto 10 characters)',
			digits : 'Please enter valid phone number'
		},
		phoneCarrier : {
			required : 'Please select your phone carrier'

		},
		preferredContactMode : {
			required : 'Please select preferred contact mode'
		},
		userRoles : {
			required : 'Please select at least 1 role'
		}
	},
	ignore: ":hidden:not(.selectpicker)",
	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	invalidHandler: function(e, validator){
//		if(validator.errorList.length){
//			$('.tabsDetails a[href="#' + jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id') + '"]').tab('show');
//			gritterForErrorMsgs("Mandatory fields in previous tabs are empty..Enter those fields first");
//		}
		for(var i=0;i<validator.errorList.length;i++){
			if(validator.errorList[i].message == "Please select at least 1 role"){
				$("#userRoles").next().children().prev().addClass("selectPickerMandatory");
			}
		}
	},
	submitHandler : function(form) {
		ajaxCallForSaveOrUpdateApplicationUser(form.id);
	}
});

function ajaxCallForSaveOrUpdateApplicationUser(formId) {
	
	var selectedRolesNames = [];
	var selectedRolesId = [];
	
	if($('#userRoles option:selected').text()==""){	//This is a temporary fix -meghanajagruthi
		gritterForErrorMsgs("Please select role before you save user");
		return false;
	}
	
	$('#manage_user_form #userRoles :selected').each(function () {
		   var $this = $(this);
		   if ($this.length) {
		    var selText = $this.text();
		    var selVal = $this.val();
		    selectedRolesNames.push(selText);
		    selectedRolesId.push(selVal);
		   }
		});
//	console.log($("#" + formId).serialize());
	$('#manage_user_form').append($('<input type="hidden" name="selectedRolesNames" id="selectedRolesNames"/>').val(selectedRolesNames));
	$('#manage_user_form').append($('<input type="hidden" name="selectedRolesId" id="selectedRolesId"/>').val(selectedRolesId));
	var laddaRef = Ladda.create(userLaddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "saveOrUpdateApplicationUserAction",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {

					laddaRef.stop();
					//result = JSON.parse(result);
					var actionType = "";
					if ($("#userId").val() != "") {
						actionType = "update";
					}

					if (result.ajaxResult == "success") {

						if ($("#userActionType").val() != "saveandcontinue") {

							$("#remoteModal").modal("hide");

						}
						resetUserForm();
						if (actionType != "") {
							gritterForSucessMsgs("User has been updated successfully.");
						} else {
							gritterForSucessMsgs("User has been saved successfully.");
						}
						getApplicationUserDetails();
					} else {

						if (result.reason == "emaiIdExist") {
							$('label').removeClass("state-error");
							$('label').removeClass("state-success");
							$('#emailId').val("");
							gritterForErrorMsgs("Email ID already exists");
						} else {
							$("#remoteModal").modal("hide");
							resetUserForm();
							gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
									+ result.reason);
						}
					}
				},
				error : function() {
					laddaRef.stop();
					$("#remoteModal").modal("hide");
					resetUserForm();
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
	$('#selectedRolesNames').remove();
	$('#selectedRolesId').remove();
}
var applicationUserDetails;
function getApplicationUserDetails() {
	applicationUserDetails = "user";
	$
			.ajax({
				url : "getUserDetails",
				type : "POST",
				contentType : 'text/plain',
				data : '{"applicationUserDetails": "' + applicationUserDetails
						+ '"}',
				success : function(result) {
					applicationUserDetails = JSON.parse(result);
					//applicationUserDetails = result;
//					console.log(applicationUserDetails);
					var totUsers, activeUsers, inactiveUsers, countId, pendingUserCount;
					totUsers = activeUsers = inactiveUsers = countId = pendingUserCount = 0;
					var nickName, submittedDate, lastName;
					nickName = submittedDate = lastName = "";
					ApplicationUserDetailsTable.fnClearTable();
					PendingUserDetailsTable.fnClearTable();
					for (var t = 0; t < applicationUserDetails.allApplicationUserList.length; t++) {
						totUsers++;
						if (applicationUserDetails.allApplicationUserList[t].nickName != undefined
								&& applicationUserDetails.allApplicationUserList[t].nickName != null) {
							nickName = applicationUserDetails.allApplicationUserList[t].nickName;
						}
						if (applicationUserDetails.allApplicationUserList[t].lastName != undefined
								&& applicationUserDetails.allApplicationUserList[t].lastName != null) {
							lastName = applicationUserDetails.allApplicationUserList[t].lastName;
						}
						if (applicationUserDetails.allApplicationUserList[t].submittedDate != undefined
								&& applicationUserDetails.allApplicationUserList[t].submittedDate != null) {
							submittedDate = moment(new Date(applicationUserDetails.allApplicationUserList[t].submittedDate)).format('MM-DD-YYYY');
						}
						if (applicationUserDetails.allApplicationUserList[t].status != undefined
								&& applicationUserDetails.allApplicationUserList[t].status == "ACTIVE") {
							activeUsers++;
							ApplicationUserDetailsTable
									.fnAddData(
											[
													applicationUserDetails.allApplicationUserList[t].userId,
													applicationUserDetails.allApplicationUserList[t].firstName,
													lastName,
													nickName,
													applicationUserDetails.allApplicationUserList[t].phoneNumber,
													applicationUserDetails.allApplicationUserList[t].preferredContactMode,
													submittedDate, ++countId ],
											false);
						} else if (applicationUserDetails.allApplicationUserList[t].status != undefined
								&& applicationUserDetails.allApplicationUserList[t].status == "PENDING ACTIVATION") {
							inactiveUsers++;
							PendingUserDetailsTable
							.fnAddData(
									[
											applicationUserDetails.allApplicationUserList[t].userId,
											applicationUserDetails.allApplicationUserList[t].firstName,
											lastName,
											nickName,
											applicationUserDetails.allApplicationUserList[t].phoneNumber,
											applicationUserDetails.allApplicationUserList[t].preferredContactMode,
											submittedDate, ++pendingUserCount ],
									false);
						}
					}
					ApplicationUserDetailsTable.fnDraw();
					PendingUserDetailsTable.fnDraw();
					setUsersCount(totUsers, activeUsers, inactiveUsers);
				}
			});
}
function setUsersCount(totUsers, activeUsers, inactiveUsers) {
	$("#totalUsersId").html("&nbsp;" + totUsers);
	$("#activeUsersId")
			.html(
					"<i class='fa fa-thumbs-o-up' data-rel='bootstrap-tooltip'	title='Increased'></i>&nbsp;"
							+ activeUsers);
	$("#inactiveUsersId")
			.html(
					"<i class='fa fa-thumbs-o-down' data-rel='bootstrap-tooltip'	title='Increased'></i>&nbsp;"
							+ inactiveUsers);
}
function resetUserForm() {
	$("#saveandcontinue").show();
	$("#pwdLabel").show();
	$("#confPwdLabel").show();
	$("#saveButtonId").html("<i class='fa fa-floppy-o'></i> Save");
	$("#modelTitleId").html("<i class='fa fa-user fa-fw txt-color-blue'></i>Add New User");
	var validator = $("#manage_user_form").validate();
	validator.resetForm();
	$('label').removeClass("state-error");
	$('label').removeClass("state-success");
	$('#emailId').val("");
	$('#userId').val("");
	$('#password').val("");
	$('#confirmPassword').val("");
	$('#firstName').val("");
	$('#middleName').val("");
	$('#lastName').val("");
	$('#nickName').val("");
	$('#phoneCarrier').val("");
	$('#phoneNumber').val("");
	$('input:radio[name=preferredContactMode]').prop('checked', false);
	/*$('#userRoles').val('');*/
	$("#userRoles option:selected").removeAttr("selected");
//	$('#phoneCarrier').selectpicker('refresh');
	$('#userRoles').selectpicker('refresh');
	$("#isDeptHead").prop('checked', false);
	$('#designationName').find('option').remove().end().append(
	'<option value="">Choose Designation</option>');
	$('#supervisorName').find('option').remove().end().append(
	'<option value="">Choose Supervisor</option>');
	$('#departmentName').find('option').remove().end().append(
	'<option value="">Choose Department</option>');
	$('#organisationName').val("");
	$("#userRoles").next().children().prev().removeClass("selectPickerMandatory");
}

function setUserToUpdate(userId) {
	$("#modelTitleId").html("<i class='fa fa-user fa-fw txt-color-blue'></i>Update User");
	$("#remoteModal").modal("show");
	getAssociatedRoles(userId);
//	console.log("-->");
//	console.log(applicationUserDetails);
	for (var u = 0; u < applicationUserDetails.allApplicationUserList.length; u++) {
		if (userId == applicationUserDetails.allApplicationUserList[u].userId) {
//			console.log( applicationUserDetails.allApplicationUserList[u]);
			var middleName, lastName, nickName, phoneCarrier;
			middleName = lastName = nickName = phoneCarrier = "";
			if (applicationUserDetails.allApplicationUserList[u].middleName != undefined
					&& applicationUserDetails.allApplicationUserList[u].middleName != null) {
				middleName = applicationUserDetails.allApplicationUserList[u].middleName;
			}
			if (applicationUserDetails.allApplicationUserList[u].lastName != undefined
					&& applicationUserDetails.allApplicationUserList[u].lastName != null) {
				lastName = applicationUserDetails.allApplicationUserList[u].lastName;
			}
			if (applicationUserDetails.allApplicationUserList[u].nickName != undefined
					&& applicationUserDetails.allApplicationUserList[u].nickName != null) {
				nickName = applicationUserDetails.allApplicationUserList[u].nickName;
			}
			if (applicationUserDetails.allApplicationUserList[u].phoneCarrier != undefined
					&& applicationUserDetails.allApplicationUserList[u].phoneCarrier != null) {
				phoneCarrier = applicationUserDetails.allApplicationUserList[u].phoneCarrier;
			}
             
			if (applicationUserDetails.allApplicationUserList[u].isDeptHead != undefined
					&& applicationUserDetails.allApplicationUserList[u].isDeptHead != null) {
				if(applicationUserDetails.allApplicationUserList[u].isDeptHead==1){
					$("#isDeptHead").prop('checked', true);
				}
				else{
					$("#isDeptHead").prop('checked', false);	
				}
				
			}
			$("#emailId").val(
					applicationUserDetails.allApplicationUserList[u].emailId);
			$("#password").val("abc");
			$("#confirmPassword").val("abc");
			$("#pwdLabel").hide();
			$("#confPwdLabel").hide();
			if(applicationUserDetails.allApplicationUserList[u].orgUnitId != undefined){
				$("#organisationName").val(applicationUserDetails.allApplicationUserList[u].orgUnitId.organizationId);
			}
			if(applicationUserDetails.allApplicationUserList[u].deptUnitId != undefined){
				if(applicationUserDetails.allApplicationUserList[u].desigId != undefined){
					if(applicationUserDetails.allApplicationUserList[u].superviserId != undefined){
						getDepartmentsByOrgId($("#organisationName").val(),
								applicationUserDetails.allApplicationUserList[u].deptUnitId.departmentId,
								applicationUserDetails.allApplicationUserList[u].desigId.designationId,
								applicationUserDetails.allApplicationUserList[u].superviserId.userId);
					}else{
						getDepartmentsByOrgId($("#organisationName").val(),
								applicationUserDetails.allApplicationUserList[u].deptUnitId.departmentId,
								applicationUserDetails.allApplicationUserList[u].desigId.designationId);
					}
				}else{
					getDepartmentsByOrgId($("#organisationName").val(),
							applicationUserDetails.allApplicationUserList[u].deptUnitId.departmentId);
				}
			}else if(applicationUserDetails.allApplicationUserList[u].orgUnitId != undefined){
				getDepartmentsByOrgId($("#organisationName").val());
			}
			$("#firstName").val(
					applicationUserDetails.allApplicationUserList[u].firstName);
			$("#middleName").val(middleName);
			$("#lastName").val(lastName);
			$("#nickName").val(nickName);
			$("#phoneCarrier").val(phoneCarrier);
			$("#phoneNumber")
					.val(
							applicationUserDetails.allApplicationUserList[u].phoneNumber);
			$("#userId").val(userId);
			$('input:radio[name=preferredContactMode]:checked').val();
			$(
					'input[name="preferredContactMode"][value="'
							+ applicationUserDetails.allApplicationUserList[u].preferredContactMode
							+ '"]').prop('checked', true);
			$("#saveButtonId").html(
					"<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
			$('.selectpicker').selectpicker('refresh');
			break;
		}
	}
}

function setUserToDelete() {
	var dataObj = {
		userIdToDel : userIdToDelete,
	};
	$
			.ajax({
				url : "applicationUserDeleteAction",
				type : "POST",
				data : dataObj,
				success : function(result) {
					result = JSON.parse(result);
					// CLearing Project Table
//					console.log(result);
//					console.log(result.ajaxResult);
					if (result.ajaxResult == "success") {
						getApplicationUserDetails();
						gritterForSucessMsgs("User has been deleted successfully.");
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
function settingDefaultNickName() {
	$("#nickName").val($("#firstName").val());
}
function setUserActionType(userActionType, userLaddaId) {
	$("#userActionType").val(userActionType);
	userLaddaButton = userLaddaId;
}
// on change of organisation select box event 
$("#organisationName").on("change",function(e){
	var value = $("#organisationName").val();
	if(value != ""){
		getDepartmentsByOrgId(value);
	}
	else{
		$('#departmentName').find('option').remove().end().append(
		'<option value="">Choose Department</option>');
	}
});
function getDepartmentsByOrgId(value,flag,desFlag,supFlag){
	var dataObj = {
			organisationId : value,
		};
	$.ajax({
		url : "getDeptListInOrgUnit",
		type : "POST",
		contentType : 'text/plain',
		data : JSON.stringify(dataObj),
		success : function(result) {
			userRoles = result;
			if(userRoles.ajaxResult == "success"){
				var elementId = document.getElementById("departmentName");
				elementId.options.length = 1;
				var roleslistLength=userRoles.reason.length;
				for (var i=0; i <roleslistLength;i++){    
			        var optn = document.createElement("OPTION");
				    optn.text = userRoles.reason[i].departmentName;
				    optn.value = userRoles.reason[i].departmentId;
				    elementId.options.add(optn);
				}
				$(".selectpicker").selectpicker("refresh");
				if(flag != undefined){
					$("#departmentName option").each(function() {
						  if($(this).val() == flag) {
						    $(this).attr('selected', 'selected'); 
						    getDesignationByDeptId($(this).val(),desFlag,supFlag);
						  }                        
						});
				}
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ userRoles.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
		}
	});
}
//on change of department select box event 
$("#departmentName").on("change",function(e){
	var value = $("#departmentName").val();
	if(value != ""){
		getDesignationByDeptId(value);
	}
	else{
		$('#designationName').find('option').remove().end().append(
		'<option value="">Choose Designation</option>');
	}
});

function getDesignationByDeptId(value,flag,supFlag){
	var dataObj = {
			departmentId : value,
		};
	$.ajax({
		url : "getDesignationInDeptUnit",
		type : "POST",
		contentType : 'text/plain',
		data : JSON.stringify(dataObj),
		success : function(result) {
			userRoles = result;
			if(userRoles.ajaxResult == "success"){
				var elementId = document.getElementById("designationName");
				elementId.options.length = 1;
				var roleslistLength=userRoles.reason.length;
				for (var i=0; i <roleslistLength;i++){    
			        var optn = document.createElement("OPTION");
				    optn.text = userRoles.reason[i].designationName;
				    optn.value = userRoles.reason[i].designationId;
				    elementId.options.add(optn);
				}
				$(".selectpicker").selectpicker("refresh");
				if(flag != undefined){
					$("#designationName option").each(function() {
						  if($(this).val() == flag) {
						    $(this).attr('selected', 'selected'); 
						    getSupervisorByDesigtId($(this).val(),supFlag);
						  }                        
						});
				}
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ userRoles.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
		}
	});
}
//on change of department select box event 
$("#designationName").on("change",function(e){
	var value = $("#designationName").val();
	if(value != ""){
		getSupervisorByDesigtId(value);
	}
	else{
		$('#supervisorName').find('option').remove().end().append(
		'<option value="">Choose Supervisor</option>');
	}
});

function getSupervisorByDesigtId(value,flag){
	var dataObj = {
			designationId : value,
			departmentId : $("#departmentName").val()
		};
	$.ajax({
		url : "getSupervisorDetails",
		type : "POST",
		contentType : 'text/plain',
		data : JSON.stringify(dataObj),
		success : function(result) {
			userRoles = result;
			if(userRoles.ajaxResult == "success"){
				var elementId = document.getElementById("supervisorName");
				elementId.options.length = 1;
				var roleslistLength=userRoles.reason.length;
				for (var i=0; i <roleslistLength;i++){    
			        var optn = document.createElement("OPTION");
				    optn.text = userRoles.reason[i].firstName;
				    optn.value = userRoles.reason[i].userId;
				    elementId.options.add(optn);
				}
				$(".selectpicker").selectpicker("refresh");
				if(flag != undefined){
					$("#supervisorName option").each(function() {
						  if($(this).val() == flag) {
						    $(this).attr('selected', 'selected');            
						  }                        
						});
				}
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ userRoles.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
		}
	});
}
