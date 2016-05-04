/* 
 * Hari Prasad
 */
//var pagefunction = function() {
//	getUserDetailsForPayment();
//};
//loadScript("assets/js/datatable/jquery.dataTables.min.js", function() {
//	loadScript("assets/js/datatable/dataTables.tableTools.min.js", function() {
//		loadScript("assets/js/datatable/dataTables.bootstrap.min.js",
//				function() {
//					loadScript(
//							"assets/js/datatable/datatables.responsive.min.js",
//							pagefunction);
//				});
//	});
//});
$('.ValidateId')
		.on(
				'click',
				function() {
					// Form Validations
					var form = $("#userFormInPayment");
					form
							.validate({
								// Rules for form validation
								rules : {
									firstName : {
										required : true,
										maxlength : 34
									},
									nickName : {
										maxlength : 10,
									},
									phoneNumber : {
										required : true,
										maxlength : 15,
										digits : true,
									},
									phoneCarrier : {
										required : true,

									},
									preferredContactMode : {
										required : true
									},
									companyName : {
										required : true
									},
									companyAddress : {
										required : true
									},
									companyPhoneCarrier : {
										required : true,
									},
									companyPhoneNumber : {
										required : true,
										maxlength : 15,
										digits : true,
									},
									name : {
										required : true,
									}
								},

								// Messages for form validation
								messages : {
									firstName : {
										required : 'Please enter your first name'
									},
									nickName : {
										maxlength : 'Nickname must not be more than 10 characters'
									},
									phoneNumber : {
										required : 'Please enter your Phone Number(Upto 15 characters)',
										digits : 'Please enter valid phone number'
									},
									phoneCarrier : {
										required : 'Please enter your phone carrier'

									},
									preferredContactMode : {
										required : 'Please select preferred contact mode'
									},
									companyName : {
										required : 'Please enter the company name'
									},
									companyAddress : {
										required : 'Please enter the company address'
									},
									companyPhoneCarrier : {
										required : 'Please enter your phone carrier'
									},
									companyPhoneNumber : {
										required : 'Please enter the company Phone Number(Upto 15 characters)',
										digits : 'Please enter valid phone number'
									},
									name : {
										required : "Please enter the name on the card",
									}
								},

								// Do not change code below
								errorPlacement : function(error, element) {
									error.insertAfter(element.parent());
								},
								submitHandler : function(form) {
									// ajaxCallForSaveOrUpdatePaymentDetails();
								}
							});
					if (!form.valid()) {
						/* event.preventDefault(); */
						// $('#userinfo').addClass('hide');
					} else {
						var user = document.getElementById("userinfo");
						if ($("#userinfo").hasClass("userClass")
								&& !($("#userinfo").hasClass("hide"))) {
							$('#userinfo').addClass('hide');
							$('#companyinfo').removeClass('hide');
							return false;
						}
						;
						if ($("#companyinfo").hasClass("companyClass")
								&& !($("#companyinfo").hasClass("hide"))) {
							$('#companyinfo').addClass('hide');
							$('#summary').removeClass('hide');
							return false;
						}
						;

					}

				});

jQuery(function($) {
	$("#paymentByCard").click(function() {
		var $form = $("#userFormInPayment");
		// Disable the submit button to prevent repeated clicks
		$form.find('button').prop('disabled', true);

		Stripe.card.createToken($form, stripeResponseHandler);

		// Prevent the form from submitting with the default action
		return false;
	})
});

function stripeResponseHandler(status, response) {
	var $form = $("#userFormInPayment");
	if (response.error) {
		// Show the errors on the form
		gritterForErrorMsgs(response.error.message);
		$form.find('button').prop('disabled', false);
	} else {
		// response contains id and card, which contains additional card details
		var token = response.id;
		// Insert the token into the form so it gets submitted to the server
		$form
				.append($('<input type="hidden" name="stripeToken" />').val(
						token));

		// and submit
		ajaxCallForSaveOrUpdatePaymentDetails();
	}
}

function setPaymentType(id) {
	$("#paymentType").val(id);
}

function ajaxCallForSaveOrUpdatePaymentDetails() {

	// setting the table data in hidden fields
	$("#summarisedSubscriptionArrayIds").val(JSON.stringify(arrayId));
	$
			.ajax({
				url : "saveOrUpdatePaymentDetailsAction",
				type : "POST",
				data : new FormData($("#userFormInPayment")[0])	,
				cache : false,
				contentType : false,
				processData : false,
				success : function(data) {
					result = JSON.parse(data);
					if (result.ajaxResult == "Payment success" || result.ajaxResult == "success") {
						resetUserForm();
						pageAuthorization();
						gritterForSucessMsgs('Payment has been successfully completed');
						window.location.href = "home#" + result.RedirectedUrl;
					} else if (result.ajaxResult == "failure") {
						gritterForErrorMsgs('Some Problem Occured.Primary reason is : '
								+ result.reason);
						window.location.href = "home#" + result.RedirectedUrl;
					}
				},
				error : function() {
					resetUserForm();
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
					window.location.href = "error";

				}
			});
}
// Gritter notification messages for payment page since it is a different
// module.
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
function resetUserForm() {

	var validator = $("#userFormInPayment").validate();
	validator.resetForm();
	$('label').removeClass("state-error");
	$('label').removeClass("state-success");
	$("#userId").val("");
	$("#emailId").val("");
	$("#password").val("");
	$('#firstName').val("");
	$('#middleName').val("");
	$('#lastName').val("");
	$('#nickName').val("");
	$('#phoneCarrier').val("");
	$('#phoneNumber').val("");
	$('input:radio[name=preferredContactMode]').prop('checked', false);
	$("#domainId").val("");
	$("#companyName").val("");
	$("#companyAddress").val("");
	$("#companyPhoneCarrier").val("");
	$("#companyPhoneNumber").val("");

}

function settingDefaultNickName() {
	$("#nickName").val($("#firstName").val());
}
$(document).ready(function() {
	getUserDetailsForPayment();
});
function getUserDetailsForPayment() {
	$
			.ajax({
				url : "getUserDetailsForPayment",
				type : "POST",
				success : function(result) {
					sessionResult = JSON.parse(result);
					if (sessionResult.istempuser == false) {
						var middleName, lastName, nickName, phoneCarrier;
						middleName = lastName = nickName = phoneCarrier = "";
						if (sessionResult.userDetails.middleName != undefined
								&& sessionResult.userDetails.middleName != null) {
							middleName = sessionResult.userDetails.middleName;
						}
						if (sessionResult.userDetails.lastName != undefined
								&& sessionResult.userDetails.lastName != null) {
							lastName = sessionResult.userDetails.lastName;
						}
						if (sessionResult.userDetails.nickName != undefined
								&& sessionResult.userDetails.nickName != null) {
							nickName = sessionResult.userDetails.nickName;
						}
						if (sessionResult.userDetails.phoneCarrier != undefined
								&& sessionResult.userDetails.phoneCarrier != null) {
							phoneCarrier = sessionResult.userDetails.phoneCarrier;
						}
						console.log($("#userId").val());
						console.log(sessionResult.userDetails.userId);
						$("#userId").val(sessionResult.userDetails.userId);
						console.log($("#userId").val());

						$("#emailId").val(sessionResult.userDetails.emailId);
						$("#password").val(
								sessionResult.userDetails.password.trim());
						$("#firstName")
								.val(sessionResult.userDetails.firstName);
						$("#middleName").val(middleName);
						$("#lastName").val(lastName);
						$("#nickName").val(nickName);
						$("#phoneCarrier").val(phoneCarrier);
						$("#phoneNumber").val(
								sessionResult.userDetails.phoneNumber);
						$('input:radio[name=preferredContactMode]:checked')
								.val();
						$(
								'input[name="preferredContactMode"][value="'
										+ sessionResult.userDetails.preferredContactMode
										+ '"]').prop('checked', true);
						$("#domainId")
								.val(
										sessionResult.userDetails.domainDetail.domainId);
						$("#companyName")
								.val(
										sessionResult.userDetails.domainDetail.companyName);
						$("#companyAddress")
								.val(
										sessionResult.userDetails.domainDetail.companyAddress);
						$("#companyPhoneCarrier")
								.val(
										sessionResult.userDetails.domainDetail.companyPhoneCarrier);
						$("#companyPhoneNumber")
								.val(
										sessionResult.userDetails.domainDetail.companyPhoneNumber);

					} else {
						var middleName, lastName, nickName;
						middleName = lastName = nickName = "";
						if (sessionResult.userDetails.middleName != undefined
								&& sessionResult.userDetails.middleName != null) {
							middleName = sessionResult.userDetails.middleName;
						}
						if (sessionResult.userDetails.lastName != undefined
								&& sessionResult.userDetails.lastName != null) {
							lastName = sessionResult.userDetails.lastName;
						}
						if (sessionResult.userDetails.nickName != undefined
								&& sessionResult.userDetails.nickName != null) {
							nickName = sessionResult.userDetails.nickName;
						}
						$("#firstName")
								.val(sessionResult.userDetails.firstName);
						$("#middleName").val(middleName);
						$("#lastName").val(lastName);
						$("#nickName").val(sessionResult.userDetails.firstName);

						$("#emailId").val(sessionResult.userDetails.emailId);
						$("#password").val(
								sessionResult.userDetails.password.trim());
					}
				},
				error : function(result) {
					/* console.log(result.message); */
					// console.log("error");
				}
			});
}
