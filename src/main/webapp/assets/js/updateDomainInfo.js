$(document).ready(function(){
	setDomainInfoDetails();
	
});
function setDomainInfoDetails(){
			$.ajax({
				url : "getDomainInfo",
				type : "POST",
				success : function(result) {
					allResult = JSON.parse(result);
					$("#domainId").val(allResult.allDomainDetails[0].domainId);
					$("#setCompanyNameId").html(allResult.allDomainDetails[0].companyName);
					$("#companyName").val(allResult.allDomainDetails[0].companyName);
					$("#setCompanyAddressId").html(allResult.allDomainDetails[0].companyAddress);
					$("#companyAddress").val(allResult.allDomainDetails[0].companyAddress);
					$("#setCompanyPhoneCarrierId").html(allResult.allDomainDetails[0].companyPhoneCarrier);
					$("#companyPhoneCarrier").val(allResult.allDomainDetails[0].companyPhoneCarrier);
					$("#setCompanyPhoneNumberId").html(allResult.allDomainDetails[0].companyPhoneNumber);
					$("#companyPhoneNumber").val(allResult.allDomainDetails[0].companyPhoneNumber);
					$("#setDomainNameId").html(allResult.allDomainDetails[0].domainName);
					$("#domainName").val(allResult.allDomainDetails[0].domainName);
					$('.selectpicker').selectpicker('refresh');
				},
				error : function(result) {
//					console.log("error");
//					console.log(result.message);
				}
			});
}

//Form Validations
$("#manageDomainForm").validate({
	// Rules for form validation
	rules : {
		companyName : {
			required : true,
		},
		companyAddress : {
			required : true,
		},
		domainName : {
			required : true,
		},
		companyPhoneNumber : {
			required : true,
			maxlength : 15,
			digits : true,
		},
		companyPhoneCarrier : {
			required : true,

		}
	},

	// Messages for form validation
	messages : {
		companyName : {
			required : 'Please enter your company name',
		},
		companyAddress : {
			required : 'Please select your company address'
		},
		domainName : {
			required : 'Please select your domain name'
		},
		companyPhoneNumber : {
			required : 'Please enter your Phone Number(Upto 15 characters)',
			digits : 'Please enter valid phone number'
		},
		companyPhoneCarrier : {
			required : 'Please enter your phone carrier'

		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		ajaxCallForUpdateDomainInfo(form.id);
	}
});

function ajaxCallForUpdateDomainInfo(formId) {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "updateDomainInfo",
				type : "POST",
				data : new FormData($("#" + formId)[0])	,
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {
					
					laddaRef.stop();
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
							  gritterForSucessMsgs("Domain info has been updated successfully.");
					} else {
						
						if(result.reason == "domainNameExist")
						{
							$('label').removeClass("state-error");
							$('label').removeClass("state-success");
							$('#domainName').val("");
						    gritterForErrorMsgs("Domain name is already Exist.");
						}else{
					       gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"+ result.reason);}
					   }
					/*$('#editCompanyNameField').addClass('hide');
				    $('#editCompanyAddressField').addClass('hide');
				    $('#editDomainNameField').addClass('hide');
				    $('#editCompanyPhoneNoField').addClass('hide');
				    $('#editCompanyPhoneCarrierField').addClass('hide');
				    $('#hideShowCompanyName').removeClass('hide');
				    $('#hideShowCompanyAddress').removeClass('hide');
				    $('#hideShowDomainName').removeClass('hide');
				    $('#hideShowCompanyPhoneNo').removeClass('hide');
				    $('#hideShowCompanyPhoneCarrier').removeClass('hide');*/
					
				    setDomainInfoDetails();
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