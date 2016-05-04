<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<style>
.smart-form footer {
  padding: 0px 14px 12px !important;
}
.lihover{
      padding-top: 21px;
      border-bottom: 1px solid #eee;
}
#editMail ,#editNickName ,#editPhoneNo ,#editPhoneCarrier ,#editPreferredContactMode{
    display: none;
}
.lihover:hover #editName ,.lihover:hover #editMail ,.lihover:hover #editNickName ,.lihover:hover #editPhoneNo ,.lihover:hover #editPhoneCarrier ,.lihover:hover #editPreferredContactMode{
    display:block;
}
</style>
</head>
<div class="row">

	<!-- <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">PAGE HEADER<i class="fa-fw fa fa-user"></i> User Profile 
		</h1>
	</div> -->
	
</div>
<div class="row"><br></div>
<div class="row">

	<div class="col-sm-12">

			<div class="well well-sm">

				<div class="row">

					<div class="col-md-12">
						<div class="well well-light well-sm no-margin no-padding">

							<div class="row">

								<div class="col-sm-12">

									<div class="row">

										<div class="col-sm-2 profile-pic">
											<img src="assets/img/appuser.jpg">
										</div>
										<div class="col-sm-6" id="userInformation">
										
											<h1 style="border-bottom: 1px dashed rgba(0,0,0,.2); padding-bottom:15px; padding-top:14px;  margin-bottom: 0px;">
												<span class="semi-bold" style="color: #214e75;"><spring:message code="MyProfile.UserProfile.Header" /></span>
												
											</h1>
											<form method="POST" id="manageUserForm" class="">
											<input type="hidden" name = "userId" id="userId">
											<input type="hidden" name = "role" id="role">
											<input type="hidden" name = "status" id="status">
											<ul class="list-unstyled">
												
												<li class="lihover">
													<div id="hideShowName">
													<p class="text-muted">
														<i class="fa fa-user" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;
														<span  class="txt-color-darken edit" rel="tooltip" data-placement="right" data-original-title="Name " style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setNameId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editNameField">
													<header><spring:message code="MyProfile.UserProfile.label.Name" /></header>
													<fieldset>
													<div class="row">
														<section class="col col-6">
															<label class="input"> <i class="icon-append fa fa-user mandatoryStyle"></i>
																<input type="text" name="firstName" id="firstName" placeholder="<spring:message code="ManageUser.label.FirstName" />" onkeyup="settingDefaultNickName()">
																 <b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.FirstName" /></b>
															</label>
														</section>
														<section class="col col-6">
															<label class="input"> <i class="icon-append fa fa-user"></i>
																<input type="text" name="middleName" id="middleName" placeholder="<spring:message code="ManageUser.label.MiddleName" />">
																 <b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.MiddleName" /></b>
															</label>
														</section>
													</div>
													<div class="row">
														<section class="col col-6">
															<label class="input"><i class="icon-append fa fa-user"></i>
																<input type="text" name="lastName" id="lastName" placeholder="<spring:message code="ManageUser.label.LastName" />"> 
																<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.LastName" /></b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer class="userProfile" class="userProfile">
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="nameButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left"  id="nameCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr><br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowEmail">
													<p class="text-muted">
														<i class="fa fa-envelope" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Email " class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setEmailId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editEmailField">
													<header><spring:message code="MyProfile.UserProfile.label.Email" /></header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="input"> <i class="icon-append fa fa-envelope mandatoryStyle"></i> <input type="email" name="emailId" id="emailId" placeholder="<spring:message code="ManageUser.label.EmailId" />"> 
															<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.EmailID" /></b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer class="userProfile">
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="emailButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left"  id="emailCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr><br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowNickName">
													<p class="text-muted">
														<i class="fa fa-weixin" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Nickname " class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setNickNameId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editNickNameField">
													<header><spring:message code="MyProfile.UserProfile.label.NickName" /></header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="input"><i class="icon-append fa fa-user"></i>
																<input type="text" name="nickName" id="nickName" placeholder="<spring:message code="ManageUser.label.NickName" />"> 
																<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.NickName" /></b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer class="userProfile">
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="nickNameButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left" id="nickNameCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr><br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowPhoneNo">
													<p class="text-muted">
														<i class="fa fa-phone" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Phone Number " class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setPhoneNumberId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editPhoneNoField">
													<header><spring:message code="MyProfile.UserProfile.label.PhoneNumber" /></header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="input"> <i class="icon-append fa fa-phone"></i> <input type="text" name="phoneNumber" id="phoneNumber" placeholder="<spring:message code="ManageUser.label.PhoneNumber" />">
																<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.PhoneNumber" /></b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer class="userProfile">
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="phoneNumberButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left" id="phoneNumberCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
												<hr><br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowPhoneCarrier">
													<p class="text-muted">
														<i class="fa fa-phone-square" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Phone Carrier " class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setPhoneCarrierId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editPhoneCarrierField">
													<header><spring:message code="MyProfile.UserProfile.label.PhoneCarrier" /></header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="input"> <%-- <i class="icon-append fa fa-phone-square"></i> <input type="text" name="phoneCarrier" id="phoneCarrier" placeholder="<spring:message code="ManageUser.label.PhoneCarrier" />"> --%> 
															<select class="form-control selectpicker show-tick" id="phoneCarrier" name="phoneCarrier">

																<option value="">--Select PhoneCarrier--</option>
																<option value="at&t">At&t</option>
																<option value="t-mobile">T-Mobile</option>
																<option value="verizon">Verizon</option>
																<option value="sprint">Sprint</option>
																<option value="virgin">Virgin</option>
																<option value="tracfone">Tracfone</option>
																<option value="nextel">Nextel</option>
																<option value="alltel">Alltel</option>
																<option value="ptel">Ptel</option>
																<option value="usCellular">US Cellular</option>
															</select>
															<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.PhoneCarrier" /></b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer class="userProfile">
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="phoneCarrierButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left" id="phoneCarrierCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr><br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowPreferredContactMode">
													<p class="text-muted">
														<i class="fa fa-globe" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Preferred Contact " class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="preferedContactModeId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editPreferredContactModeField">
													<header><spring:message code="MyProfile.UserProfile.label.PreferredContactMode" /></header>
													<fieldset>
													<div class="row">
														<section class="col col-6">
															<label style="margin-top: 3px ;font-size: 14px;font-weight: 500;">
																<spring:message code="MyProfile.UserProfile.label.PreferredContactMode" /> 
																<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.PreferredContactMode" /></b>
															</label>
														</section>
														<section class="col col-2">
															<label class="radio">
																<input type="radio" name="preferredContactMode" id="preferredContactMode" value="phone"> 
																<i></i> <spring:message code="ManageUser.radiobutton.option1.PreferredContactMode" />
															</label>
														</section>
														<section class="col col-2">
															<label class="radio">
																<input type="radio" name="preferredContactMode" id="preferredContactMode" value="email">
																<i></i> <spring:message code="ManageUser.radiobutton.option2.PreferredContactMode" />
															</label>
														</section>
														<section class="col col-2">
															<label class="radio">
																<input type="radio" name="preferredContactMode" id="preferredContactMode" value="both"> 
																<i></i> <spring:message code="ManageUser.radiobutton.option3.PreferredContactMode" />
															</label>
														</section>
													</div>
													</fieldset>
													<footer class="userProfile">
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="preferedContactButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left" id="preferedContactCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr><br>
													</div>
													</div>
												</li>
													<li class="lihover">
													<div id="hideShowUserRoles">
													<p class="text-muted">
														<i class="fa fa-users" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Application Roles " class="txt-color-darken" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setUserApplicationRoleId"></span>
													</p>
													</div>
													
												</li>
												<li class="lihover">
													<div id="hideShowUserOrganization">
													<p class="text-muted">
														<i class="fa fa-users" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Organization " class="txt-color-darken" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setOrganizationId"></span>
													</p>
													</div>
													
												</li>
												<li class="lihover">
													<div id="hideShowUserDepartment">
													<p class="text-muted">
														<i class="fa fa-users" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;<span rel="tooltip" data-placement="right" data-original-title="Department " class="txt-color-darken" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setDepartmentId"></span>
													</p>
													</div>
													
												</li>
												<br>
											</ul>
											</form>
										</div>
										
										<div class="col-sm-4">
										
										<h1 style="padding-bottom:15px; padding-top:14px;" id="hideShowPassword">
										<a class="semi-bold" style="font-weight: 100 !important;font-size: 18px; cursor:pointer;position: relative;left: 30%;" onclick="showPasswordDiv()"><spring:message code="MyProfile.UpdatePassword.Header" /></a>
										</h1>
										
										<div id="edithideShowPassword" class="hide">
										<form method="POST" id="updatePasswordForm" class="smart-form">
											<header><spring:message code="MyProfile.UpdatePassword.Header" /></header>
												<fieldset>
					
													<section>
														<label class="label"><spring:message code="ManageUser.label.EmailId" /></label>
														<label class="input"> <i class="icon-append fa fa-envelope-o mandatoryStyle"></i>
															<input type="email" id="confirmEmailId" name="email" placeholder="<spring:message code="ManageUser.label.EmailId" />">
															<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.EmailID" /></b> </label>
													</section>
													
													<section>
														<label class="label"><spring:message code="MyProfile.UpdatePassword.label.OldPassword" /></label>
														<label class="input"> <i class="icon-append fa fa-lock mandatoryStyle"></i>
															<input type="password" name="oldPassword" placeholder="<spring:message code="MyProfile.UpdatePassword.label.OldPassword" />" id="oldPassword">
															<b class="tooltip tooltip-bottom-right"><spring:message code="MyProfile.UpdatePassword.tooltip.OldPassword" /></b> </label>
													</section>
													
													<section>
														<label class="label"><spring:message code="MyProfile.UpdatePassword.label.NewPassword" /></label>
														<label class="input"> <i class="icon-append fa fa-lock mandatoryStyle"></i>
															<input type="password" name="newPassword" placeholder="<spring:message code="MyProfile.UpdatePassword.label.NewPassword" />" id="newPassword">
															<b class="tooltip tooltip-bottom-right"><spring:message code="MyProfile.UpdatePassword.tooltip.NewPassword" /></b> </label>
													</section>
													
													<section>
														<label class="label"><spring:message code="MyProfile.UpdatePassword.label.ConfirmPassword" /></label>
														<label class="input"> <i class="icon-append fa fa-lock mandatoryStyle"></i>
															<input type="password" name="confirmPassword" placeholder="<spring:message code="MyProfile.UpdatePassword.label.ConfirmPassword" />" id="confirmPassword">
															<b class="tooltip tooltip-bottom-right"><spring:message code="MyProfile.UpdatePassword.tooltip.ConfirmPassword" /></b> </label>
													</section>
													
												</fieldset>
					                          <footer>
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="changePasswordButton" onclick="setActionType(this)">
														<spring:message code="Button.Update" />
													</button>
														<button type="button" class="btn btn-default pull-left" onclick="hidePasswordDiv()">
														<spring:message code="Button.Cancel" />
													</button>
													</footer>
													<hr>
											</form>
											</div>
											<br>
										</div>

									</div>

								</div>

							</div>

						</div>

					</div>
					
				</div>

			</div>


	</div>

</div>

<script type="text/javascript" src="assets/js/updateAccountInfo.js">
</script>
<script>
pageSetUp();
</script>
<!-- <script>
function hide(target) {
    $('#editNameField').addClass('hide');
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
    $('#hideShowPreferredContactMode').removeClass('hide');
    $('#edithideShowPassword').addClass('hide');
	$('#hideShowPassword').removeClass('hide');
	$('#userInformation').removeClass('hide');
	setUserDetailsForMyProfile();
}
</script> -->

<script>
/* function hideShowName()
{
	 $('#editNameField').removeClass('hide');
	 $('#hideShowName').addClass('hide');
	  $("#editMail").addClass('hide'); 
	  $("#editNickName").addClass('hide'); 
	  $("#editPhoneNo").addClass('hide'); 
	  $("#editPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide'); 
}
function hideShowEmail()
{
	 $('#editEmailField').removeClass('hide');
	 $('#hideShowEmail').addClass('hide');
	   $("#editName").addClass('hide'); 
	 $("#editMail").removeClass('hide'); 
	  $("#editNickName").addClass('hide'); 
	  $("#editPhoneNo").addClass('hide'); 
	  $("#editPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide');  
}
function hideShowNickName()
{
	 $('#editNickNameField').removeClass('hide');
	 $('#hideShowNickName').addClass('hide');
	 $("#editName").addClass('hide'); 
	 $("#editMail").addClass('hide'); 
	  $("#editNickName").removeClass('hide'); 
	  $("#editPhoneNo").addClass('hide'); 
	  $("#editPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide');  
}
function hideShowPhoneNo()
{
	 $('#editPhoneNoField').removeClass('hide');
	 $('#hideShowPhoneNo').addClass('hide');
	 $("#editName").addClass('hide'); 
	 $("#editMail").addClass('hide'); 
	  $("#editNickName").addClass('hide'); 
	  $("#editPhoneNo").removeClass('hide'); 
	  $("#editPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide'); 
}
function hideShowPhoneCarrier()
{
	 $('#editPhoneCarrierField').removeClass('hide');
	 $('#hideShowPhoneCarrier').addClass('hide');
	 $("#editName").addClass('hide'); 
	 $("#editMail").addClass('hide'); 
	  $("#editNickName").addClass('hide'); 
	  $("#editPhoneNo").addClass('hide');
	  $("#editPhoneCarrier").removeClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide'); 
}
function hideShowPreferredContactMode()
{
	 $('#editPreferredContactModeField').removeClass('hide');
	 $('#hideShowPreferredContactMode').addClass('hide');
	 $("#editName").addClass('hide'); 
	 $("#editMail").addClass('hide');  
	  $("#editNickName").addClass('hide');  
	  $("#editPhoneNo").addClass('hide');  
	  $("#editPhoneCarrier").addClass('hide');  
	  $("#editPreferredContactMode").removeClass('hide');  
}
function hideShowPassword()
{
	 $('#edithideShowPassword').removeClass('hide');
	 $('#hideShowPassword').addClass('hide');
	 $('#userInformation').addClass('hide');
}
function showOnClick(){
	$("#editName").removeClass('hide'); 
	 $("#editMail").removeClass('hide'); 
	  $("#editNickName").removeClass('hide'); 
	  $("#editPhoneNo").removeClass('hide'); 
	  $("#editPhoneCarrier").removeClass('hide'); 
	  $("#editPreferredContactMode").removeClass('hide'); 
} */

function showPasswordDiv()
{
	 $('#edithideShowPassword').removeClass('hide');
	 $('#confirmEmailId').val($("#emailId").val());
	 $('#hideShowPassword').addClass('hide');
	 $('#userInformation').addClass('hide');
}
function hidePasswordDiv()
{
	 $('#edithideShowPassword').addClass('hide');
	 $('#hideShowPassword').removeClass('hide');
	 $('#userInformation').removeClass('hide');
}

$('span.edit').on('click',function(){
	var qq = $(this).parents('li');
	qq.children()[0].style.display="none";
	$(qq.children()[1]).removeClass("hide");
	
	var ss = $(this).parents('.lihover').siblings();
    ss.find('a').css('display','none');
	
	$(this).parents('.lihover')[0].style.borderBottom="none";
    $(this).parents('.lihover')[0].style['padding-top']="10px";
});

$('footer.userProfile button').on('click',function(){
	if($(this).hasClass('pull-left')){
		setUserDetailsForMyProfile();
	}
	var ss = $(this).parents('.lihover').siblings();
    ss.find('a').css('display','');
    
	var qq = $(this).parents('li');
	qq.children()[0].style.display="block";
	$(qq.children()[1]).addClass("hide");
	
	$(this).parents('.lihover')[0].style.borderBottom="1px solid #eee";
	$(this).parents('.lihover')[0].style['padding-top']="21px";
	});	
</script>
