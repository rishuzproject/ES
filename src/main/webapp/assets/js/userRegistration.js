/**
 * 
 */


		// Validation
		$(function() {
			// Validation
			$("#userRegistrationForm").validate({

				// Rules for form validation
				//debug:true
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
			    	 checkEmailID($("#emailId").val());
			    	 
			     },

				// Do not change code below
				errorPlacement : function(error, element) {
					error.insertAfter(element.parent());
				}
			});

		});
function resetForm()
{
	$('label').removeClass("state-success");
	$("#emailId").val('');
	$("#password").val('');
	$("#confirmPassword").val('');
	$("#userName").val('');
//	$("#terms").prop('checked', false);
}
function checkEmailID(emailId) {
	var isExist = false;
	var dataObj = {
		emailId : emailId
	};
	$
			.ajax({
				type : 'POST',
				url : 'chkEmailID',
				data : dataObj,
				async : false,
				success : function(result) {
					isExist = JSON.parse(result);
					if (isExist == true) {
						$("#emailId").val('')
						$("#emailId").focus();
						$(".message1").show();
						
					} else{
						submitDetailsUser();
					}
				}
			});
}
function submitDetailsUser()
{
//	console.log("submitting details");
	$.ajax({
		url : "saveTemporaryRegistrationFormAction",
		type : "POST",
		data : $("#userRegistrationForm").serialize(),
		success : function(result) {
			result = JSON.parse(result);
			$(".message").show();
			resetForm();
		},
		error : function(result) {
//			console.log("error");
			console.log(result);
//			console.log(result);
		}
	});
}
function gritterForErrorMsgs(msg){
	$.smallBox({
		title : "Danger box",
		content : msg,
		color : "#C46A69",
		timeout: 8000,
		icon : "fa fa-warning shake animated"
	});
}