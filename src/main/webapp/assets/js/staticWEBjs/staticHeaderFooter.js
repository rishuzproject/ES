function getMappedRoles(userRoleCookie) {
	$("#loginEmailId").addClass('ui-autocomplete-loading');
	var elementId = document.getElementById("userRolesForLogin");
	elementId.options.length = 1;
	var cookieValueFoundInRolesList = false;
	if ($("#loginEmailId").val().trim() != "") {
		var emailId = $("#loginEmailId").val();
		userMappedRoles = "userMappedRoles";
		$
				.ajax({
					url : "getUserMappedRoles",
					type : "POST",
					data : {
						"emailId" : emailId
					},
					success : function(result) {
						defaultRoleValue = "";
						userMappedRoles = JSON.parse(result);
						if (userMappedRoles.statusReturned == "200") {
							if (userMappedRoles.userRolesList.length != 0) {
								var elementId = document
										.getElementById("userRolesForLogin");
								elementId.options.length = 1;
								for (var i = 0; i < userMappedRoles.userRolesList.length; i++) {
									if (userMappedRoles.userRolesList[i].userRolesId.roleId == userRoleCookie) {
										cookieValueFoundInRolesList = true;
									}
									if (userMappedRoles.userRolesList[i].isDefaultRole) {
										defaultRoleValue = userMappedRoles.userRolesList[i].userRolesId.roleId;
									}
									var optn = document.createElement("OPTION");
									optn.text = userMappedRoles.userRolesList[i].userRolesId.roleName;
									optn.value = userMappedRoles.userRolesList[i].userRolesId.roleId;
									elementId.options.add(optn);
								}
								if (userRoleCookie != undefined
										&& cookieValueFoundInRolesList) {
									$(
											'#userRolesForLogin option[value="'
													+ userRoleCookie + '"]')
											.prop('selected', true);
									$("#userRolesForLogin").show();
									if (userRoleCookie == defaultRoleValue) {
										$("#defaultRole").show();
										$("#beforeSelectDefault").hide();
										$("#defaultRoleLabel").hide();
									} else {
										$("#defaultRoleLabel").show();
										$("#defaultRole").hide();
										$("#beforeSelectDefault").show();
									}
								} else {
									$(
											'#userRolesForLogin option[value="'
													+ defaultRoleValue + '"]')
											.prop('selected', true);
									if (defaultRoleValue == "") {
										$("#defaultRoleLabel").show();
										$("#defaultRole").hide();
										$("#beforeSelectDefault").show();
									} else {
										$("#defaultRole").show();
										$("#beforeSelectDefault").hide();
										$("#defaultRoleLabel").hide();
									}
								}
								$("#userRolesForLogin").show();

							} else {
								$("#defaultRole").hide();
								$("#beforeSelectDefault").hide();
								$("#userRolesForLogin").hide();
							}
						} else {
							gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
									+ userMappedRoles.reason);
						}
					},
					complete : function() {
						$("#loginEmailId").removeClass('ui-autocomplete-loading');
						$("#loginSubmit").removeAttr('disabled');
					}
				});
	}
}

$("#userRolesForLogin").change(function() {
	if ($("#userRolesForLogin").val() == defaultRoleValue) {
		$("#defaultRole").show();
		$("#beforeSelectDefault").hide();
		$("#defaultRoleLabel").hide();
	} else if($("#userRolesForLogin").val() == ""){
		$("#defaultRole").hide();
		$("#beforeSelectDefault").show();
	} else {
		$("#defaultRole").hide();
		$("#beforeSelectDefault").show();
		$("#defaultRoleLabel").show();
	}
});

$(".message").hide();
var cookie;
var laddaButton = 0;
// Validation
$(function() {
	// Validation
	$("#userRegistrationForm").validate({

		// Rules for form validation
		// debug:true
		rules : {
			emailId : {
				required : true,
				email : true
			},
			password : {
				required : true,
				minlength : 3,
				maxlength : 20
			},
			passwordConfirm : {
				required : true,
				minlength : 3,
				maxlength : 20,
				equalTo : '#password'
			},
			userName : {
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
			passwordConfirm : {
				required : 'Please enter your password one more time',
				equalTo : 'Please enter the same password as above'
			},
			userName : {
				required : 'Please enter your user name'
			}
		},

		// Ajax form submition
		submitHandler : function(form) {
			submitDetailsUser();
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});

});
// validation for login
$(function() {
	// Validation
	$("#showLogin").validate({
		// Rules for form validation
		// debug:true
		ignore : [],
		rules : {
			loginEmailId : {
				required : true,
				email : true
			},
			loginPassword : {
				required : true,
			},
//			userRolesForLogin : {
//				required : true
//			},
		},

		// Messages for form validation
		messages : {

			loginEmailId : {
				required : 'Please enter your email address',
				email : 'Please enter a VALID email address'
			},
			loginPassword : {
				required : 'Please enter your password'
			},
//			userRolesForLogin : {
//				required : function() {
//					return "Please select a role";
//				}
//			}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		// Ajax form submition
		submitHandler : function(form) {
			form.submit();
		}

	// Do not change code below

	});

});
function resetForm() {
	var validator = $("#userRegistrationForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$("#emailId").val('');
	$("#password").val('');
	$("#confirmPassword").val('');
	$("#userName").val('');
	// $("#terms").prop('checked', false);
	$('.form-group').removeClass("state-error");
}
function checkEmailID(emailId) {
	var isExist = false;
	var dataObj = {
		emailId : emailId
	};
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				type : 'POST',
				url : 'checkEmailIdInTemporaryUserDetails',
				data : dataObj,
				async : false,
				success : function(result) {
					laddaRef.stop();
					isExist = JSON.parse(result);
					if (isExist == true) {
						$("#emailId").val('')
						$("#emailId").focus();
						$(".message").show();
						gritterForErrorMsgs("The EmailId is already Exists. If you have forgotten your password, please request it.");
					} else {
						submitDetailsUser();
					}
				},
				error : function(result) {
					laddaRef.stop();
				}
			});
}
function submitDetailsUser() {
	$
			.ajax({
				url : "saveTemporaryRegistrationFormAction",
				type : "POST",
				data : $("#userRegistrationForm").serialize(),
				success : function(result) {
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
						$("#getSatartedModal").modal('hide');
						gritterForSucessMsgs("Thank you for your registration!<br>We have send you an activation link. Please check your inbox !!!.");
						resetForm();
					} else if (result.ajaxResult == "failure") {
						$("#emailId").val('')
						$("#emailId").focus();
						$(".message").show();
						gritterForErrorMsgs("Some problem occured .Primary reason "
								+ result.reason);
					} else {
						gritterForErrorMsgs("If the problem persists .Please contact the Dev team");
					}
				},
				error : function(result) {
				}
			});
}

function resetLoginForm() {
	var validator = $("#showLogin").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$("#loginEmailId").val('');
	$("#loginPassword").val('');
	$("#userRolesForLogin").val('');
	$("#userRolesForLogin").hide();
	$("#defaultLabel").hide();
}
// Validation for Forgot password
$(function() {
	// Validation
	$("#forgotPasswordForm").validate({

		// Rules for form validation
		// debug:true
		rules : {
			forgotemailid : {
				required : true,
				email : true
			}
		},
		// Messages for form validation
		messages : {

			forgotemailid : {
				required : 'Please enter your email address',
				email : 'Please enter a VALID email address'
			}
		},
		// Ajax form submition
		submitHandler : function(form) {
			form.submit();
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});

});
function resetForgottenPasswordForm() {
	var validator = $("#forgotPasswordForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	// $("#forgotEmailId").val('');
	$('input').val('');
}
// validation for activation link
$(function() {
	// Validation
	$("#sendActivationLinkForm").validate({

		// Rules for form validation
		// debug:true
		rules : {
			activationEmailId : {
				required : true,
				email : true
			}
		},
		// Messages for form validation
		messages : {

			activationEmailId : {
				required : 'Please enter your email address',
				email : 'Please enter a VALID email address'
			}
		},
		// Ajax form submition
		submitHandler : function(form) {
			form.submit();
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});

});
function resetForgottenActivationForm() {
	var validator = $("#sendActivationLinkForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$("#activationEmailId").val('');
}
function gritterForSucessMsgs(msg) {
	$.smallBox({
		title : "<b>Ecosystem: Success !!</b>",
		content : msg,
		color : "#739E73",
		timeout : 5000,
		icon : "fa fa-check animated custSize bounce",
		iconSmall : "fa fa-times fadeInRight animated custPosit"
	});
}
function gritterForErrorMsgs(msg) {
	$.smallBox({
		title : "<b>Ecosystem: Failure !!</b>",
		content : msg,
		color : "#C46A69",
		timeout : 5000,
		icon : "fa fa-warning shake animated custSize",
		iconSmall : "fa fa-times fadeInRight animated custPosit"
	});
}
function gritterForInfoMsgs(msg) {
	$.smallBox({
		title : "<b>Ecosystem: Info !!</b>",
		content : msg,
		color : "#5384AF",
		icon : "fa fa-bell bounce animated custSize",
		timeout : 5000,
		iconSmall : "fa fa-times fadeInRight animated custPosit"
	});
}
function setLaddaButtonRegisterPage(laddaButtonTemp) {
	laddaButton = laddaButtonTemp;
}
// for cookies
function cookieHelper(string, symbol) {
	return string.split(symbol);
}
function searchValue(cookieArray, string) {

	var length = string.length - 1;
	var resultantCookie = null;

	for (var i = 0; i < cookieArray.length; i++) {

		cookieArray[i] = cookieArray[i].trim();

		if (cookieArray[i].charAt(string.length) === '=') {
			for (var j = 0; j < string.length; j++) {

				if ((cookieArray[i].charAt(j) === string.charAt(j))
						&& length > 0) {
					length--;
				} else {
					break;
				}
			}

		}
		if (length == 0) {

			resultantCookie = cookieArray[i];
			break;
		}
	}

	return resultantCookie;
}
function getCookieValues(name, name1) {
	var regexp = new RegExp("(?:^" + name + "|;\s*" + name + ")=(.*?)(?:;|$)",
			"g");
	var test1 = regexp.exec(document.cookie);
	var regexp1 = new RegExp(
			"(?:^" + name + "|;\s*" + name1 + ")=(.*?)(?:;|$)", "g");
	var test2 = regexp1.exec(document.cookie);
	if (test1 == null) {
		if (!(test2 === null))
			return test2[1];
		else
			return null;
	}
	return test1[1];
}
function settingValuesInCookies() {
	if ($('#rememberMeCheckBox').is(':checked')) {
		var cookies = getJsonStringForLoginForm();
		document.cookie = "email" + "=" + cookies[0];
		document.cookie = "password" + "=" + cookies[1];
		document.cookie = "defaultRole" + "=" + cookies[2];
	}
}
function getJsonStringForLoginForm() {
	
	
	var contentsSCTable = new Array();
	contentsSCTable[0] = document.getElementById("loginEmailId").value;
	contentsSCTable[1] = document.getElementById("loginPassword").value;
	contentsSCTable[2] = document.getElementById("userRolesForLogin").value;
	cookie = JSON.stringify(contentsSCTable);
	cookie = contentsSCTable;
	return cookie;
}
function fillLoginForm() {
	var cookieArray = cookieHelper(document.cookie, ';');
	var emailCookie = searchValue(cookieArray, "email");
	var emailContents = cookieHelper(emailCookie.trim(), '=');
	var passwordCookie = searchValue(cookieArray, "password");
	var passwordContents = cookieHelper(passwordCookie.trim(), '=');
	var userRoleCookieForEcosystem = searchValue(cookieArray, "defaultRole");
	var userRoleContentsForEcosystem = cookieHelper(userRoleCookieForEcosystem
			.trim(), '=');
	$("#loginEmailId").val(emailContents[1]);
	$("#loginPassword").val(passwordContents[1]);
	getMappedRoles(userRoleContentsForEcosystem[1]);
}
$(document).ready(
		function() {
			if (getCookieValues("email", " email") != null
					&& getCookieValues("password", " password") != null
					&& getCookieValues("defaultRole", " defaultRole") != null) {
				setIdAndName();
				fillLoginForm();
				$( "#rememberMeCheckBox" ).prop( "checked", true );
			}
		});
function setIdAndName(){
	var pwdTextbox = $(".passwordInput")[0];
	$(pwdTextbox).attr("id", "loginPassword");
	$(pwdTextbox).attr("name", "loginPassword");
}