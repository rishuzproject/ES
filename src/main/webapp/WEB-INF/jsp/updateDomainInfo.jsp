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
#editCompanyAddress , #editDomainName ,#editCompanyPhoneNo ,#editUploadImage ,#editCompanyPhoneCarrier ,#editPreferredContactMode{
    display: none;
}
.lihover:hover #editCompanyAddress , .lihover:hover #editDomainName ,.lihover:hover #editCompanyPhoneNo ,.lihover:hover #editUploadImage ,.lihover:hover #editCompanyPhoneCarrier ,.lihover:hover #editPreferredContactMode{
    display: block;
}

</style>
</head>
<div class="row">
	
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
											<img src="./assets/img/greenEco.png" style="height: 65px; width: 115px; position: relative; top: -18px;border:3px solid #E2E2E2" alt="Belco logo">
										</div>
										<div class="col-sm-8" id="domainInformation">
										
											<h1 style="border-bottom: 1px dashed rgba(0,0,0,.2);  margin-bottom: 0px; padding-bottom:15px; padding-top:14px;">
												<span class="semi-bold" style="color: #214e75;">Domain Information</span>
												
											</h1>
											
											<form method="POST" id="manageDomainForm" commandName = "updateDomainInfoForm">
											<input type="hidden" name = "domainId" id="domainId">
											<ul class="list-unstyled">
												
												<li class="lihover">
													<div id="hideShowCompanyName">
													<p class="text-muted">
														<i class="fa fa-building" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;
														<span rel="tooltip" data-placement="right" data-original-title="Company Name" class="txt-color-darken edit" style="cursor:pointer;color: #214e75 !important;font-size:14px;" id="setCompanyNameId"></span>
													</p>
													</div>
													<div class="smart-form hide" id="testDivId">
													<div class="col-md-12 " id="editCompanyNameField">
													<header>Company Name</header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="input"><i class="icon-append fa fa-building mandatoryStyle"></i>
																<input type="text" name="companyName" id="companyName" placeholder="Company Name ..">
																 <b class="tooltip tooltip-bottom-right">Enter your company name</b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer>
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="companyNameButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left"  id="companyNameCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr></hr>
													<br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowCompanyAddress">
													<p class="text-muted">
														<i class="fa fa-road" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;
														<span rel="tooltip" data-placement="right" data-original-title="Company Address" class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setCompanyAddressId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editCompanyAddressField">
													<header>Company Address</header>
													<fieldset>
													<div class="row">
														<section  class="col col-md-12">
															<label class="textarea textarea-resizable"><i class="icon-append fa fa-road mandatoryStyle"></i>										
																<textarea rows="4" class="custom-scroll" name="companyAddress" id="companyAddress" placeholder="Company Address .."></textarea> 
																<b class="tooltip tooltip-bottom-right">Enter your Company address</b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer>
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="companyAddressButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left"  id="companyAddressCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr></hr>
													<br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowDomainName">
													<p class="text-muted">
														<i class="fa fa-globe" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;
														<span rel="tooltip" data-placement="right" data-original-title="Domain Name" class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setDomainNameId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editDomainNameField">
													<header>Domain Name</header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="input"><i class="icon-append fa fa-globe mandatoryStyle"></i>
																<input type="text" name="domainName" id="domainName" placeholder="Domain Name .."> 
																<b class="tooltip tooltip-bottom-right">Enter your domain name</b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer>
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="domainNameButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left" id="domainNameCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr></hr>
													<br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowUploadImage">
													<p class="text-muted">
														<i class="fa fa-upload" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;
														<span rel="tooltip" data-placement="right" data-original-title="Logo Name" class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setUploadLogoId">logo.jpeg</span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editUploadImageField">
													<header>Upload New Logo</header>
													<fieldset>
													<div class="row">
																	<section>
																	<div class="col col-lg-10">
																		<label for="uploadImageLabel" class="input input-file">
																			<div class="button">
																				<input type="file" name="uploadImageFile" id="inputId"
																					accept="image/*"  onchange="showMyImage(this);this.parentNode.nextSibling.value = this.value" >
																				Browse
									 										</div><input type="text" name="uploadImageFileName" id="uploadImageFileName"
																			placeholder="Choose document to Upload" style="color:#2E6074"
																			readonly="">
																	    </label>
																		</div>
																		<div class="col col-lg-2" style="position:relative;top:-2px; padding:0px;border:1px solid #eee;text-align: center">
																		
																		<img src="./assets/img/greenEco.png" id="compLogo" style="height: 35px; width: 96px; " alt="Comp. logo">
																		
																		</div>
																	</section>
																</div>
													</fieldset>
													<footer>
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="uploadImageButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left"  id="uploadImageCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr></hr>
													<br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowCompanyPhoneNo">
													<p class="text-muted">
														<i class="fa fa-phone" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;
														<span rel="tooltip" data-placement="right" data-original-title="Company Phone Number" class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setCompanyPhoneNumberId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editCompanyPhoneNoField">
													<header>Company Phone Number</header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="input"> <i class="icon-append fa fa-phone mandatoryStyle"></i> <input type="text" name="companyPhoneNumber" id="companyPhoneNumber" placeholder="Company Phone Number ..">
																<b class="tooltip tooltip-bottom-right">Enter your company's phone number</b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer>
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="companyPhoneNumberButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left" id="companyPhoneNumberCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr></hr>
													<br>
													</div>
													</div>
												</li>
												<li class="lihover">
													<div id="hideShowCompanyPhoneCarrier">
													<p class="text-muted">
														<i class="fa fa-phone-square" style="color: #4a94d4;font-size:14px;"></i>&nbsp;&nbsp;
														<span rel="tooltip" data-placement="right" data-original-title="Company Phone Carrier" class="txt-color-darken edit" style="color: #214e75 !important;font-size:14px;cursor:pointer;" id="setCompanyPhoneCarrierId"></span>
													</p>
													</div>
													<div class="smart-form hide">
													<div class="col-md-12 " id="editCompanyPhoneCarrierField">
													<header>Company Phone Carrier</header>
													<fieldset>
													<div class="row">
														<section class="col col-md-12">
															<label class="select"> <!-- <i class="icon-append fa fa-phone-square"></i> <input type="text" name="companyPhoneCarrier" id="companyPhoneCarrier" placeholder="Company Phone Carrier .."> --> 
															<select class="form-control show-tick" id="companyPhoneCarrier" name="companyPhoneCarrier">

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
													<i class="mandatoryIconStyle"></i>
															<b class="tooltip tooltip-bottom-right">Enter your company's phone carrier</b>
															</label>
														</section>
													</div>
													</fieldset>
													<footer>
														<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="companyPhoneCarrierButton" onclick="setActionType(this);">
															<spring:message code="Button.Update" />
														</button>
														<button type="button" class="btn btn-default pull-left" id="companyPhoneCarrierCancelButton">
															<spring:message code="Button.Cancel" />
														</button>
													</footer>
													<hr></hr>
													<br>
													</div>
													</div>
												</li>
												<br>
											</ul>
											</form>
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

<script type="text/javascript" src="assets/js/updateDomainInfo.js"></script>
<script>
	pageSetUp();
</script>
<script>
/* function hide(target) {
    $('#editCompanyNameField').addClass('hide');
    $('#editCompanyAddressField').addClass('hide');
    $('#editDomainNameField').addClass('hide');
    $('#editCompanyPhoneNoField').addClass('hide');
    $('#editCompanyPhoneCarrierField').addClass('hide');
    $('#editPreferredContactModeField').addClass('hide');
    $('#editUploadImageField').addClass('hide');
    $('#hideShowCompanyName').removeClass('hide');
    $('#hideShowCompanyAddress').removeClass('hide');
    $('#hideShowDomainName').removeClass('hide');
    $('#hideShowCompanyPhoneNo').removeClass('hide');
    $('#hideShowCompanyPhoneCarrier').removeClass('hide');
    $('#hideShowPreferredContactMode').removeClass('hide');
    $('#edithideShowPassword').addClass('hide');
	$('#hideShowPassword').removeClass('hide');
	$('#domainInformation').removeClass('hide');
	$('#hideShowUploadImage').removeClass('hide');
	setDomainInfoDetails();
	console.log(1)
} */
</script>

<script>
/* function hideShowCompanyName()
{
	  $('#editCompanyNameField').removeClass('hide');
	  $('#hideShowCompanyName').addClass('hide');
	  $("#editCompanyAddress").addClass('hide'); 
	  $("#editDomainName").addClass('hide'); 
	  $("#editCompanyPhoneNo").addClass('hide'); 
	  $("#editCompanyPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide'); 
	  $('#editUploadImageField').addClass('hide');
	  $('#editUploadImage').addClass('hide');
	  console.log(2)
}
function hideShowCompanyAddress()
{
	 $('#editCompanyAddressField').removeClass('hide');
	 $('#hideShowCompanyAddress').addClass('hide');
	 $("#editCompanyName").addClass('hide'); 
	 $("#editCompanyAddress").removeClass('hide'); 
	 $("#editDomainName").addClass('hide'); 
	 $("#editCompanyPhoneNo").addClass('hide'); 
	 $("#editCompanyPhoneCarrier").addClass('hide'); 
	 $("#editPreferredContactMode").addClass('hide');
	 $('#editUploadImageField').addClass('hide');
	 $('#editUploadImage').addClass('hide');
	  console.log(3)
}
function hideShowDomainName()
{
	 $('#editDomainNameField').removeClass('hide');
	 $('#hideShowDomainName').addClass('hide');
	 $("#editCompanyName").addClass('hide'); 
	 $("#editCompanyAddress").addClass('hide'); 
	  $("#editDomainName").removeClass('hide'); 
	  $("#editCompanyPhoneNo").addClass('hide'); 
	  $("#editCompanyPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide');  
	  $('#editUploadImageField').addClass('hide');
	  $('#editUploadImage').addClass('hide');
	  console.log(4)
}
function hideShowUploadImage()
{
	$('#editUploadImageField').removeClass('hide');
	$('#hideShowUploadImage').addClass('hide');
	 $('#editCompanyAddressField').addClass('hide');
	 $('#hideShowCompanyAddress').removeClass('hide');
	  $("#editCompanyName").addClass('hide'); 
	 $("#editCompanyAddress").addClass('hide'); 
	  $("#editDomainName").addClass('hide'); 
	  $("#editCompanyPhoneNo").addClass('hide'); 
	  $("#editCompanyPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide');
	  console.log(5)
}
function hideShowCompanyPhoneNo()
{
	 $('#editCompanyPhoneNoField').removeClass('hide');
	 $('#hideShowCompanyPhoneNo').addClass('hide');
	 $("#editCompanyName").addClass('hide'); 
	 $("#editCompanyAddress").addClass('hide'); 
	  $("#editDomainName").addClass('hide'); 
	  $("#editCompanyPhoneNo").removeClass('hide'); 
	  $("#editCompanyPhoneCarrier").addClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide'); 
	  $('#editUploadImageField').addClass('hide');
	  $('#editUploadImage').addClass('hide');
	  console.log(6)
}
function hideShowCompanyPhoneCarrier()
{
	 $('#editCompanyPhoneCarrierField').removeClass('hide');
	 $('#hideShowCompanyPhoneCarrier').addClass('hide');
	 $("#editCompanyName").addClass('hide'); 
	 $("#editCompanyAddress").addClass('hide'); 
	  $("#editDomainName").addClass('hide'); 
	  $("#editCompanyPhoneNo").addClass('hide');
	  $("#editCompanyPhoneCarrier").removeClass('hide'); 
	  $("#editPreferredContactMode").addClass('hide'); 
	  $('#editUploadImage').addClass('hide');
	  $('#editUploadImageField').addClass('hide');
	  console.log(7)
}
function hideShowPreferredContactMode()
{
	 $('#editPreferredContactModeField').removeClass('hide');
	 $('#hideShowPreferredContactMode').addClass('hide');
	 $("#editCompanyName").addClass('hide'); 
	 $("#editCompanyAddress").addClass('hide');  
	  $("#editDomainName").addClass('hide');  
	  $("#editCompanyPhoneNo").addClass('hide');  
	  $("#editCompanyPhoneCarrier").addClass('hide');  
	  $("#editPreferredContactMode").removeClass('hide'); 
	  $('#editUploadImageField').addClass('hide');
	  console.log(8)
} 
function hideShowPassword()
{
	console.log(33333)
	 $('#edithideShowPassword').removeClass('hide');
	 $('#hideShowPassword').addClass('hide');
	 $('#domainInformation').addClass('hide');
	 console.log(9)
}
function showOnClick(){
	console.log(10)
	$("#editCompanyName").removeClass('hide'); 
	 $("#editCompanyAddress").removeClass('hide'); 
	  $("#editDomainName").removeClass('hide'); 
	  $("#editCompanyPhoneNo").removeClass('hide'); 
	  $("#editCompanyPhoneCarrier").removeClass('hide'); 
	  $("#editPreferredContactMode").removeClass('hide');
	  $("#editUploadImage").removeClass('hide');
}*/
</script>
<script>
function showMyImage(fileInput) {
    var files = fileInput.files;
    for (var i = 0; i < files.length; i++) {           
        var file = files[i];
        var imageType = /image.*/;     
        if (!file.type.match(imageType)) {
            continue;
        }           
        var img=document.getElementById("compLogo");            
        img.file = file;    
        var reader = new FileReader();
        reader.onload = (function(aImg) { 
            return function(e) { 
                aImg.src = e.target.result; 
            }; 
        })(img);
        reader.readAsDataURL(file);
    }    
}

/* $('a.pull-right').on('click',function() {
	     $(this).parents('.lihover')[0].style.borderBottom="none";
	     $(this).parents('.lihover')[0].style['padding-top']="10px";
	  });
$('footer button').on('click',function(){
	$(this).parents('.lihover')[0].style.borderBottom="1px solid #eee";
	$(this).parents('.lihover')[0].style['padding-top']="21px";
	}); */  

$('span.edit').on('click',function(){
		var qq = $(this).parents('li');
		qq.children()[0].style.display="none";
		$(qq.children()[1]).removeClass("hide");
		
		$(this).parents('.lihover').siblings().find('a').css('display','none');
		
		$(this).parents('.lihover')[0].style.borderBottom="none";
	    $(this).parents('.lihover')[0].style['padding-top']="10px";
	});
	
	$('footer button').on('click',function(){
		var qq = $(this).parents('li');
		qq.children()[0].style.display="block";
		$(qq.children()[1]).addClass("hide");
		
		if($(this).hasClass('pull-left')){
			setDomainInfoDetails();
		}
		
		$(this).parents('.lihover').siblings().find('a').css('display','');

		 $(this).parents('.lihover')[0].style.borderBottom="1px solid #eee";
		$(this).parents('.lihover')[0].style['padding-top']="21px";
		});	
	
</script>
