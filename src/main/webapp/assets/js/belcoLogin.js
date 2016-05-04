
$(document).ready(function() {
    $.backstretch([
	  "assets/img/4.jpg"
    ], {duration: 3000, fade: 750});

    $('.links a.home').tooltip();
    $('.links a.blog').tooltip();

    /*
        Form validation
    */
	
	$('.login form').submit(function(){
        $(this).find("label[for='email']").html('Email');
        $(this).find("label[for='password']").html('Password');
        $(this).find("label[for='role']").html('Role');
        var email = $(this).find('input#loginEmailId').val();
        var password = $(this).find('input#loginPassword').val();
        var role = $(this).find('#userRolesForLogin').val();
        var roledisplay = $(this).find('#roleDiv').css('display');
        if(email == '') {
            $(this).find("label[for='email']").append("<span style='display:none' class='red'> - Please enter a valid email.</span>");
            $(this).find("label[for='email'] span").fadeIn('medium');
            return false;
        }
        if(password == '') {
            $(this).find("label[for='password']").append("<span style='display:none' class='red'> - Please enter a valid password.</span>");
            $(this).find("label[for='password'] span").fadeIn('medium');
            return false;
        }
        if(role == '') {
        	if(roledisplay != 'none'){
	            $(this).find("label[for='role']").append("<span style='display:none' class='red'> - Please select a role.</span>");
	            $(this).find("label[for='role'] span").fadeIn('medium');
	            return false;
        	}
        }
    });
	
	$('.forgotpassword form').submit(function(){
        $(this).find("label[for='email']").html('Email');
        var email = $(this).find('input#sendingEmailId').val();
        if(email == '') {
            $(this).find("label[for='email']").append("<span style='display:none' class='red'> - Please enter a valid email.</span>");
            $(this).find("label[for='email'] span").fadeIn('medium');
            return false;
        }
    });
	
	$('.activationlink form').submit(function(){
        $(this).find("label[for='email']").html('Email');
        var email = $(this).find('input#activationEmailId').val();
        if(email == '') {
            $(this).find("label[for='email']").append("<span style='display:none' class='red'> - Please enter a valid email.</span>");
            $(this).find("label[for='email'] span").fadeIn('medium');
            return false;
        }
    });

});

function getMappedRoles(userRoleCookie){
	$("#loginEmailId").addClass('ui-autocomplete-loading');
	if($("#loginEmailId").val().trim()!=""){
		var emailId = $("#loginEmailId").val();
		userMappedRoles = "userMappedRoles";
		$.ajax({
			url : "getUserMappedRoles",
			type : "POST",
			data : {"emailId" : emailId},
			success : function(result) {
				userMappedRoles = JSON.parse(result);
				if(userMappedRoles.statusReturned == "200"){
						if(userMappedRoles.userRolesList.length != 0){
							var elementId = document.getElementById("userRolesForLogin");
							elementId.options.length = 1;
							defaultValue="";
							for (var i=0; i <userMappedRoles.userRolesList.length;i++){
								if(userMappedRoles.userRolesList[i].userRolesId.roleId == userRoleCookie){
									cookieValueFoundInRolesList = true;
								}
								if(userMappedRoles.userRolesList[i].isDefaultRole){
									defaultValue = userMappedRoles.userRolesList[i].userRolesId.roleId;
								}
								var optn = document.createElement("OPTION");
								optn.text = userMappedRoles.userRolesList[i].userRolesId.roleName;
								optn.value = userMappedRoles.userRolesList[i].userRolesId.roleId;
								elementId.options.add(optn);
							}
							console.log("-----------"+userRoleCookie != undefined);
							if(userRoleCookie != undefined){
								$('#userRolesForLogin option[value="' + userRoleCookie + '"]').prop('selected', true);
								$("#roleDiv").show();
								$("#userRolesForLogin").show();
								console.log("---------------");
								console.log(userRoleCookie);
								console.log(defaultValue);
								if(userRoleCookie == defaultValue){
									$("#defaultRole").show();
									$('#defaultRoleCheckBox').attr('checked', true);
									
								}
								else{
									$('#defaultRoleCheckBox').attr('checked', false);
									$("#defaultRole").hide();
								}
							}
							else{
								$('#userRolesForLogin option[value="' + defaultValue + '"]').prop('selected', true);
								if(defaultValue == ""){
									$("#defaultRoleLabel").show();
									$("#defaultRole").hide();
								}
								else{
									$("#defaultRole").show();
									$("#defaultRoleLabel").hide();
								}
								
							}
							$("#roleDiv").show();
							$("#userRolesForLogin").show();
							$("#defaultRoleLabel").show();
						}
				}
				else{
					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
							+ userMappedRoles.reason);
				}
			},
			complete : function() {
				$("#loginEmailId").removeClass('ui-autocomplete-loading');
//				$("#blogin").removeAttr('disabled');
			}
		});
	}
}

	function showLogin() {
			$("#loginForm").removeClass("hide");
			$("#forgotPasswordDiv").addClass("hide");
			$("#activationLink").addClass("hide");
			$("#sendingEmailId").val("");
			$("#loginEmailId").val("");
			$("#loginPassword").val("");
			$("#activationEmailId").val("");
			$("#activationEmailId").val("");
		    $("label[for='email']").html("Email");
	}
	function showForgotPassword() {
			$("#forgotPasswordDiv").removeClass("hide");
			$("#loginForm").addClass("hide");			
		}
	function showActivation() {
		$("#activationLink").removeClass("hide");
		$("#loginForm").addClass("hide");			
	}

	
	function checkEmailID(emailId) {
		var isExist = false;
		var dataObj = {
			emailId : emailId
		};
		$.ajax({
			type : 'POST',
			url : 'checkEmailIdInTemporaryUserDetails',
			data : dataObj,
			async : false,
			success : function(result) {
				isExist = JSON.parse(result);
				if (isExist == true) {
					$("#emailId").val('');
					$("#emailId").focus();
					$(".message").show();
					console.log("The EmailId is already Exists. If you have forgotten your password, please request it.");
				} else{
					submitDetailsUser();
				}
			},
			error : function(result){
			}
		});
	 }
	function submitDetailsUser()
	{
		$.ajax({
			url : "saveTemporaryRegistrationFormAction",
			type : "POST",
			data : $("#registerFormId").serialize(),
			success : function(result) {
				console.log(result);
				result = JSON.parse(result);
				console.log(result);
				if(result.ajaxResult == "success"){
					alert("Thank you for your registration!<br>We have send you an activation link. Please check your inbox !!!.");
				} else if (result.ajaxResult == "failure"){
					$("#emailId").val('')
					$("#emailId").focus();
					$(".message").show();
					alert("Some problem occured .Primary reason "+result.reason);
				}else{
				}
			},
			error : function(result) {
				alert();
			}
		});
	}
	
	// For Cookies
	
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
	
	function settingValuesInCookies() {
		if ($('#rememberme').is(':checked')) {
	    var cookies = getJsonStringForLoginForm(); 
		document.cookie = "email" + "=" + cookies[0];
		document.cookie = "password"+"="+cookies[1];
		document.cookie = "defaultRole" + "=" + cookies[2];
		}
	}
	
	function getJsonStringForLoginForm() {
		var contentsSCTable = new Array();
			contentsSCTable[0] = document.getElementById("loginEmailId").value;
			contentsSCTable[1] = document.getElementById("loginPassword").value;
			contentsSCTable[2] = document.getElementById("userRolesForLogin").value;
		cookie = JSON.stringify(contentsSCTable);
			cookie= contentsSCTable;
		return cookie;
	}
	
	function getCookieValues(name, name1) {
		var regexp = new RegExp("(?:^" + name + "|;\s*" + name + ")=(.*?)(?:;|$)","g");
		var test1 = regexp.exec(document.cookie);
		var regexp1 = new RegExp("(?:^" + name + "|;\s*" + name1 + ")=(.*?)(?:;|$)", "g");
		var test2 = regexp1.exec(document.cookie);
		if (test1 == null) {
			if (!(test2 === null))
				return test2[1];
			else
				return null;
		}
		return test1[1];
	}
	
	function fillLoginForm() {
		var cookieArray = cookieHelper(document.cookie, ';');
		var emailCookie = searchValue(cookieArray, "email");
		var emailContents = cookieHelper(emailCookie.trim(), '=');
		var passwordCookie = searchValue(cookieArray, "password");
		var passwordContents = cookieHelper(passwordCookie.trim(), '=');
			$("#loginEmailId").val(emailContents[1]);
			$("#loginPassword").val(passwordContents[1]);
			var userRoleCookieForEcosystem = searchValue(cookieArray, "defaultRole");
			var userRoleContentsForEcosystem = cookieHelper(userRoleCookieForEcosystem.trim(), '=');
			getMappedRoles(userRoleContentsForEcosystem[1]);
	}
	
	$(document).ready(function() {
		if (getCookieValues("email", " email") != null
				&& getCookieValues("password", " password") != null
				&& getCookieValues("defaultRole", " defaultRole") != null) {
			setIdAndName();
			fillLoginForm();
		}
	});
	
	function setIdAndName(){
		var pwdTextbox = $(".passwordInput")[0];
		$(pwdTextbox).attr("id", "loginPassword");
		$(pwdTextbox).attr("name", "loginPassword");
	}
	
	function resetRegisterForm(){
		$('input').val('');
	}
	
	function gritterForSucessMsgs(msg) {
		$.smallBox({
			title : "<b>Ecosystem: Success !!</b>",
			content : msg,
			color : "#739E73",
			icon : "fa fa-check animated custSize bounce",
			iconSmall : "fa fa-times fadeInRight animated custPosit"
		});
	}
	
	function gritterForSuccessMsgs(msg,title) {
		$.smallBox({
			title :title ,
			content : msg,
			color : "#739E73",
			timeout : 5000,
			icon : "fa fa-check animated custSize bounce",
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
	
	
	function gritterForLoginErrorMsgs(msg,title) {
		$.smallBox({
			title :title,
			content : msg,
			color : "#C46A69",
			timeout : 5000,
			icon : "fa fa-warning shake animated custSize",
			iconSmall : "fa fa-times fadeInRight animated custPosit"
		});
	}
	
	function gritterForInfoMsgs(msg,title) {
		$.smallBox({
			title : title,
			content : msg,
			color : "#5384AF",
			icon : "fa fa-bell bounce animated custSize",
			timeout : 5000,
			iconSmall : "fa fa-times fadeInRight animated custPosit"
		});
	}
	