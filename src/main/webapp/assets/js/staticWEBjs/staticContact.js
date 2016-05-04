var laddaButton = 0;
$(document)
		.ready(
				function() {
					$('#contactLink').addClass("activeTab");
});

$("#staticContactForm").validate({
	// Rules for form validation
	rules : {
		inputName : {
			required : true,
			
		},
		inputEmail1:{
			required: true,
		},
		inputMessage:{
			required: true,
		}
	},
		
	// Messages for form validation
	messages : {
		inputName : {
			required : "Please enter the name"
			
		},
		inputEmail1 : {
			required : "Please enter the email id"
			
		},
		inputMessage : {
			required : "Please enter the message"
			
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler: function(form) {
		ajaxCallForSendMessage(form.id);
	}
});

function ajaxCallForSendMessage(formId) {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "sendMessage",
				type : "POST",
				data : $("#"+formId).serialize(),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
							  gritterForSucessMsgs("Message has been sent successfully.");
						resetStaticContactForm();
					} else {
						resetStaticContactForm();
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					laddaRef.stop();
					resetStaticContactForm();
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
}
function setActionType(laddaButtonTemp){
	laddaButton = laddaButtonTemp;
}
function resetStaticContactForm(){
	var validator = $( "#staticContactForm" ).validate();
	validator.resetForm();
			$('label').removeClass("state-success");
			$('label').removeClass("state-error");
			$("#inputName").val('');
			$("#inputEmail1").val("");
			$("#inputMessage").val("");
}