
var notificationLaddaId = 0;

$(document).ready(function(){
	
	function updateNotificationData(lastLogId){

	}
	getNotificationLists();

	$('.msg-body').on("click",function()
			{
		if($('.msg-body').css('white-space') == 'normal')
	{
//		alert('s');
	$('.msg-body').css({"white-space":"nowrap"});
	}});
	$("#forwardBtn").click(function(){
		forwardNotification();
	});
});
function onHover()
{
	$('.msg-body').bind('click',function() {
		$(this).css('white-space','normal');
	});  
}
function getNotificationLists(){
	$.ajax({
		url : "getNotificationListForUser",
		type : "GET",
		success : function(result) {
	 		response = result;
//	 		console.log(response);
	 		$("#notificationCenterLists").html("");
			var list = $("#notificationCenterLists").append('<ul class="notification-body" id="nl" style="padding-left: 0px;"></ul>').find('ul');
			for (i = response.length-1; i>=0 ; i--){
				if(response[i].isEmailRead == false){
					list.append('<li><span class="">'+
								'<a class="msg" style="padding-left:0px !important">'+
								'<span class="from">'+response[i].emailFrom+
								'<i class="icon-paperclip"></i></span>'+
								'<time>'+new Date(response[i].submittedDate)+'</time>'+
								'<span class="msg-body">'+response[i].emailSubject+'</span>'+
								'<button type="button" id="read_'+ response[i].emailNotificationId+ '"onclick="showData('+response[i].emailNotificationId+')" class="btn btn-primary" style="left: 94%;position: relative;margin-top: -20px;">Read</button></span><time style="padding-right: 3%;">'+'</a></span></li>');
				}else{
					list.append('<li><span class="unread">'+
								'<a class="msg" style="padding-left:0px !important">'+
								'<span class="from">'+response[i].emailFrom+
								'<i class="icon-paperclip"></i></span>'+
								'<time>'+new Date(response[i].submittedDate)+'</time>'+
								'<span class="msg-body">'+response[i].emailSubject+'</span>'+
								'<button type="button" id="read_'+ response[i].emailNotificationId+ '"onclick="showData('+response[i].emailNotificationId+')" class="btn btn-primary" style="left: 94%;position: relative;margin-top: -20px;">Read</button></span></a></span></li>');
				}
				//$("#statusTime").html("Last updated on : "+ response.logDetails[i].submittedDate);
			}
			
//			updateNotificationData(response.logDetails[response.logDetails.length-1].LogId);
			$("#unreadNotifs").html(0);
			onHover();
		}
	});
}
function removeUnreadClass(liValue){
	$('span:first', liValue).addClass("unread");
}
function showData(id){
	console.log(id);
	markNotificationAsRead(id);
	showDataInModal(id);
}
function markNotificationAsRead(id){
	$.ajax({
	url : "markNotificationAsRead",
	data : { emailNotificationId : id },
	type : "GET",
	success : function(result) {
		if(result == "success"){
			$("#read_"+id).parent().parent().addClass("unread");
		}
	}
});
}
function showDataInModal(id){
	$("#notificationCenterRemoteModal").modal("show");
	for (i = 0; i<response.length; i++){
		if(response[i].emailNotificationId == id){
			$("#emailNotificationId").val(response[i].emailNotificationId);
			$("#emailFrom").val(response[i].emailFrom);
			$("#emailTo").val(response[i].emailTo);
			$("#emailCc").val(response[i].emailCc);
			$("#emailBcc").val(response[i].emailBcc);
			$("#emailSubject").html(response[i].emailSubject);
			var content = response[i].emailContent;
			$("#normalContent").html(content);
			$("#notificationsubmittedDate").val(new Date(response[i].submittedDate));
			break;
		}
	}
		
}
function resetNotificationForm(){
	var validator = $("#addNotificationForm").validate();
	validator.resetForm();
	$('label').removeClass("state-error");
	$('label').removeClass("state-success");
	$("#emailNotificationId").val("");
	$("#emailFrom").val("");
	$("#emailTo").val("");
	$("#emailCc").val("");
	$("#emailBcc").val("");
	$("#emailSubject").html("");
	$("#emailContent").html("");
	$("#forwardBtn").show();
	$("#sendBtn").hide();
	$("#emailContent").hide();
	$("#forwardContent").hide();
	$("#emailTo").attr("readonly",true);
	$("#emailCc").attr("readonly",true);
	$("#emailBcc").attr("readonly",true);
}
function forwardNotification(){
	var content = $("#normalContent").html();
	var replayContent = "<hr><br>---------- Forwarded message ----------"+"<br>";
	replayContent += "From: "+$("#emailFrom").val()+"<br>";
	replayContent += "Date: "+$("#notificationsubmittedDate").val()+"<br>";
	replayContent += "Subject: "+$("#emailSubject").html()+"<br>";
	replayContent += "To: "+$("#emailTo").val()+"<br>";
	if($("#emailCc").val() != ""){
		replayContent += "Cc: "+$("#emailCc").val()+"<br>";
	}
	if($("#emailBcc").val() != ""){
		replayContent += "Bcc: "+$("#emailBcc").val()+"<br>";
	}
	replayContent += "<br><br>"+content;
	$("#normalContent").html("");
	$("#emailContent").html("<br>");
	$("#forwardContent").html(replayContent);
	var sub = "Fwd: "+$("#emailSubject").html();
	$("#emailSubject").html(sub);
	$("#emailTo").val("");
	$("#emailCc").val("");
	$("#emailBcc").val("");
	$("#emailTo").attr("readonly",false);
	$("#emailCc").attr("readonly",false);
	$("#emailBcc").attr("readonly",false);
	$("#emailTo").focus();
	$("#emailContent").show();
	$("#forwardContent").show();
	$("#forwardBtn").hide();
	$("#sendBtn").show();
}

$.validator.addMethod(
	    "multiemail",
	    function (value, element) {
	        var email = value.split(/[;,]+/); // split element by , and ;
	        valid = true;
	        for (var i in email) {
	            value = email[i];
	            valid = valid && $.validator.methods.email.call(this, $.trim(value), element);
	        }
	        return valid;
	    },
	    $.validator.messages.multiemail
	);

//Form Validations
$("#addNotificationForm").validate({
	// Rules for form validation
	rules : {
		emailFrom : {
			required : true,
			email : true
		},
		emailTo : {
			required : true,
			multiemail : true
		},
		emailCc : {
			multiemail : true
		},
		emailBcc : {
			multiemail : true
		},
		
	},

	// Messages for form validation
	messages : {
		emailFrom : {
			required : 'Please enter a email address',
			email : 'Please enter a VALID email address'
		},
		emailTo : {
			required : 'Please enter a email address',
			multiemail : 'Please enter a VALID email address, or comma separate'
		},
		emailCc : {
			multiemail : 'Please enter a VALID email address, or comma separate'
		},
		emailBcc : {
			multiemail : 'Please enter a VALID email address, or comma separate'
		},
		
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		sendForwardNotification();
	}
});

function sendForwardNotification(){
	var forwardData = {};
	forwardData.emailFrom = $("#emailFrom").val();
	forwardData.emailTo = $("#emailTo").val();
	forwardData.emailCc = $("#emailCc").val();
	forwardData.emailBcc = $("#emailBcc").val();
	forwardData.emailSubject = $("#emailSubject").html();
	var content = $("#emailContent").html();
	content +=$("#forwardContent").html();
	forwardData.emailContent = content;
	console.log(forwardData);
	var laddaRef = Ladda.create(notificationLaddaId);
	laddaRef.start();
	$.ajax({
		url : "forwardNotificationEmail",
		contentType : "application/json",
		data : JSON.stringify(forwardData),
		type : "POST",
		success : function(result) {
			laddaRef.stop();
			$("#notificationCenterRemoteModal").modal("hide");
			resetNotificationForm();
			if(result == "success"){
				getNotificationLists();
				gritterForSucessMsgs("Notification has been sent successfully.")
			}else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ result);
			}
		},
		error : function() {
		laddaRef.stop();
		$("#notificationCenterRemoteModal").modal("hide");
		resetNotificationForm();
		gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

	}
	});
}
function setNotificationType(laddaId){
	notificationLaddaId = laddaId;
}