
var laddaButton = 0;

// Validation for Updating Password
		$(function() {
			// Validation
			$("#updatePasswordForm").validate({

				// Rules for form validation
				//debug:true
				rules : {	
					email : {
						required : true,
						email : true
					},
					oldPassword : {
						required : true
					},
					newPassword : {
						required : true,
						minlength : 3,
						maxlength : 20
					},
					confirmPassword : {
						required : true,
						minlength : 3,
						maxlength : 20,
						equalTo : '#newPassword'
					}
				},

				// Messages for form validation
				messages : {
					
					email : {
						required : 'Please enter your email address',
						email : 'Please enter a VALID email address'
					},
					oldPassword : {
						required : 'Please enter your old pasword'
					},
					newPassword : {
						required : 'Please enter your new password'
					},
					confirmPassword : {
						required : 'Please enter your password one more time',
						equalTo : 'Please enter the same password as above'
					}
				},

		
				// Ajax form submition
			     submitHandler : function(form) {
			    	 updatePassword(form.id);
			     },

				// Do not change code below
				errorPlacement : function(error, element) {
					error.insertAfter(element.parent());
				}
			});

		});
function updatePassword(formId){
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
			$.ajax({
				url : "updatePassword",
				type : "POST",
				data : $("#"+formId).serialize(),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					if(result.ajaxResult == "success"){
					gritterForSucessMsgs("Your password has been updated successfully");
					
					} else if (result.ajaxResult == "failure"){
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
					resetUpdatePasswordForm();
					$('#edithideShowPassword').addClass('hide');
					$('#hideShowPassword').removeClass('hide');
					$('#userInformation').removeClass('hide');
				},
				error : function(result) {
					laddaRef.stop();
					resetUpdatePasswordForm();
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
				}
			});
}

function resetUpdatePasswordForm(){
	var validator = $( "#updatePasswordForm" ).validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$("#email").val('');
	$("#newPassword").val('');
	$("#oldPassword").val('');
	$("#confirmPassword").val('');
}
$(document).ready(function(){
	setUserDetailsForMyProfile();
	
});
function setUserDetailsForMyProfile(){
			$.ajax({
				url : "getUserDetailsForMyProfile",
				type : "POST",
				success : function(result) {
					allResult = JSON.parse(result);
					var firstName ,lastName,middleName,preferedContactModeId;
					middleName = lastName = preferedContactModeId = "";
					if (allResult.userDetails.middleName != undefined
							&& allResult.userDetails.middleName != null) {
						middleName = allResult.userDetails.middleName;
					}
					if (allResult.userDetails.lastName != undefined
							&& allResult.userDetails.lastName != null) {
						lastName = allResult.userDetails.lastName;
					}
					if(allResult.userDetails.preferredContactMode == "phone"){
						preferedContactModeId = "Preferred Contact Mode is Phone";
					} else if(allResult.userDetails.preferredContactMode == "email"){
						preferedContactModeId = "Preferred Contact Mode is Email";
					} else if(allResult.userDetails.preferredContactMode == "both"){
						preferedContactModeId = "Preferred Contact Mode is both Email and Phone";
					}
					firstName=allResult.userDetails.firstName;
					var name=firstName+" "+middleName+" "+lastName;
					$("#userId").val(allResult.userDetails.userId);
					$("#role").val(allResult.userDetails.role);
					$("#status").val(allResult.userDetails.status);
					$("#setNameId").html(name);
					$("#firstName").val(firstName);
					$("#middleName").val(middleName);
					$("#lastName").val(lastName);
					$("#setEmailId").html(allResult.userDetails.emailId);
					$("#emailId").val(allResult.userDetails.emailId);
					$("#setNickNameId").html(allResult.userDetails.nickName);
					$("#nickName").val(allResult.userDetails.nickName);
					$("#setPhoneCarrierId").html(allResult.userDetails.phoneCarrier);
					$("#phoneCarrier").val(allResult.userDetails.phoneCarrier);
					$("#setPhoneNumberId").html(allResult.userDetails.phoneNumber);
					$("#phoneNumber").val(allResult.userDetails.phoneNumber);
					$("#preferedContactModeId").html(preferedContactModeId);
					$('input:radio[name=preferredContactMode]:checked').val();
					$('input[name="preferredContactMode"][value="' + allResult.userDetails.preferredContactMode + '"]').prop('checked', true);
					if(allResult.userDetails.deptUnitId !=undefined){
						$("#setDepartmentId").html(allResult.userDetails.deptUnitId.departmentName);
					}else{
						$("#hideShowUserDepartment").parent().hide();
					}
					if(allResult.userDetails.orgUnitId != undefined){
						$("#setOrganizationId").html(allResult.userDetails.orgUnitId.organizationName);
					}else{
						$("#hideShowUserOrganization").parent().hide();
					}
					getUserRolesByEmailId(allResult.userDetails.emailId);
				},
				error : function(result) {
//					console.log("error");
//					console.log(result.message);
				}
			});
}
//getting user roles based on email id

function getUserRolesByEmailId(emailId){
	$.ajax({
		url : "getUserMappedRoles",
		type : "POST",
		data : {"emailId" : emailId},
		success : function(result) {
			var roleList = "";
			userMappedRoles = JSON.parse(result);
			if(userMappedRoles.statusReturned == "200"){
					if(userMappedRoles.userRolesList.length != 0){
						for (var i=0; i <userMappedRoles.userRolesList.length;i++){
							roleList += userMappedRoles.userRolesList[i].userRolesId.roleName + ", ";
						}
					}
			}
			roleList = roleList.substr(0,roleList.length-2);
			$("#setUserApplicationRoleId").html(roleList);
		},
		error : function(result) {
//			console.log("error");
//			console.log(result.message);
		}
	});
}


//Form Validations
$("#manageUserForm").validate({
	// Rules for form validation
	rules : {
		emailId : {
			required : true,
			email : true
		},
		firstName : {
			required : true,
			maxlength : 34
		},
		nickName : {
			maxlength : 10,
		},
		phoneNumber : {
//			required : true,
			maxlength : 15,
			digits : true,
		},
		phoneCarrier : {
			required : true,

		},
		preferredContactMode : {
			required : true
		}
	},

	// Messages for form validation
	messages : {
		emailId : {
			required : 'Please enter your email address',
			email : 'Please enter a VALID email address'
		},
		firstName : {
			required : 'Please select your first name'
		},
		nickName : {
			maxlength : 'Nickname must not be more than 10 characters'
		},
		phoneNumber : {
//			required : 'Please enter your Phone Number(Upto 15 characters)',
			digits : 'Please enter valid phone number'
		},
		phoneCarrier : {
			required : 'Please enter your phone carrier'

		},
		preferredContactMode : {
			required : 'Please select preferred contact mode'
		},
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		ajaxCallForUpdateApplicationUser(form.id);
	}
});

function ajaxCallForUpdateApplicationUser(formId) {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "UpdateApplicationUserActionForMyProfile",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					
					laddaRef.stop();
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
							  gritterForSucessMsgs("User has been updated successfully.");
					} else {
						
						if(result.reason == "emaiIdExist")
						{
							$('label').removeClass("state-error");
							$('label').removeClass("state-success");
							$('#emailId').val("");
						    gritterForErrorMsgs("Email id is already Exist.");
						}else{
					       gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"+ result.reason);}
					   }
					/*$('#editNameField').addClass('hide');
				    $('#editEmailField').addClass('hide');
				    $('#editNickNameField').addClass('hide');
				    $('#editPhoneNoField').addClass('hide');
				    $('#editPhoneCarrierField').addClass('hide');
				    $('#editPreferredContactModeField').addClass('hide');
				    $('#hideShowName').removeClass('hide');
				    $('#hideShowEmail').removeClass('hide');
				    $('#hideShowNickName').removeClass('hide');
				    $('#hideShowPhoneNo').removeClass('hide');
				    $('#hideShowPhoneCarrier').removeClass('hide');
				    $('#hideShowPreferredContactMode').removeClass('hide');*/
					setUserDetailsForMyProfile();
				},
				error : function() {
					laddaRef.stop();
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
}

function setActionType(laddaButtonTemp){
	laddaButton = laddaButtonTemp;
}