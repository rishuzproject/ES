$(document).ready(function(){
	checkTypeOfUser();
	getNotificationCount();
	$("#briefNotification").click(function(){
		showNotifications();
		showIssueReported();
		$("#unreadNotifs").html(0);
		$('.popover').addClass("notificationPopupStyle");
		$('.popover-content').addClass("popoverContentStyle");
		$('.popover-content').addClass("custom-scroll");
		$('.popover-footer').html("");
		$('.popover-footer').append('<span id="dateFormater">'+myDateFormatter()+'</span><button type="button" onclick="refreshContent()" class="refreshButton btn btn-xs btn-default pull-right">'+
				'<i class="fa fa-refresh"></i></button>');
	});
	$('body').on('click', function (e) {
	    $('[data-toggle="popover"]').each(function () {
	        //the 'is' for buttons that trigger popups
	        //the 'has' for icons within a button that triggers a popup
	        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
	            $(this).popover('hide');
	        }
	    });
	});
});
function refreshContent(){
	$("#notifications").html("");
	$("#issueReported").html("");
	$("#notifications").html("<span><i class='fa fa-gear fa-2 fa-spin'></i> Loading...</span>");
	$("#issueReported").html("<span><i class='fa fa-gear fa-2 fa-spin'></i> Loading...</span>");
	showNotifications();
	showIssueReported();
	$("#dateFormater").html(myDateFormatter());
}
(function () {
    var tabContent = $('#popover-content').html();
    $('#briefNotification').popover({
        'html': true,
        template: '<div class="popover"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content">'+
        '</div><div class="popover-footer" style="height:35px;"></div></div>', 
        'content': function () {
            $('#popover-content').remove();
            return tabContent;
        }
    });
})();

function myDateFormatter() {
    var d = new Date();
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var time = d.toLocaleTimeString().toLowerCase().replace(/([\d]+:[\d]+):[\d]+(\s\w+)/g, "$1$2");
    var date = "Last updated on: "+month + "-" + day + "-" + year + " "+time;

    return date;
}

function showNotifications(){
	$.ajax({
		url : "getNotificationListForUser",
		type : "GET",
		success : function(result) {
	 		response = result;
	 		$("#notifications").html("");
	 		var days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
	 		var monthname=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	 		var list = $("#notifications").append('<ul class="notification-body" id="nl" style="padding-left: 0px;"></ul>').find('ul');
			for (i = response.length-1; i>=0 ; i--){
				var date = new Date(response[i].submittedDate);
				var dateFormat =  days[date.getDay()]+", "+monthname[date.getMonth()]+" "+date.getDay();
				if(response[i].isEmailRead == false){
					list.append('<li><span >'+
								'<a class="msg" style="padding-left:0px !important">'+
								'<span class="from">'+response[i].emailFrom+
								'<i class="icon-paperclip"></i></span>'+
								'<time>'+dateFormat+'</time>'+
								'<span class="msg-body">'+response[i].emailSubject+'</span>'+
								'</span><time style="padding-right: 3%;">'+'</a></span></li>');
				}else{
					list.append('<li><span class="unread">'+
								'<a class="msg" style="padding-left:0px !important">'+
								'<span class="from">'+response[i].emailFrom+
								'<i class="icon-paperclip"></i></span>'+
								'<time>'+dateFormat+'</time>'+
								'<span class="msg-body">'+response[i].emailSubject+'</span>'+
								'</span></a></span></li>');
				}
			}
			
		}
	});
}
function showIssueReported(){
	$.ajax({
		url : "getIssueReportedListForUser",
		type : "GET",
		success : function(result) {
	 		issueResponse = JSON.parse(result);
	 		$("#issueReported").html("");
	 		var list = $("#issueReported").append('<ul class="notification-body" id="nlIssue" style="padding-left: 0px;"></ul>').find('ul');
			for (i = issueResponse.reportedIssueList.length-1; i>=0 ; i--){
				var dueDate = new Date(issueResponse.reportedIssueList[i].dueDate);
				var barStyle;
				if(dueDate < new Date()){
					barStyle = "progress-bar-danger";
				}else{
					barStyle = "progress-bar-success";
				}
				
				var assigneeName="";
				if(issueResponse.reportedIssueList[i].assigneeName!=undefined&&issueResponse.reportedIssueList[i].assigneeName!=null){
					assigneeName=issueResponse.reportedIssueList[i].assigneeName;
				}
				
				var updatedDate="";
				var dateFormat1 ="";
				var dateFormat = (dueDate.getMonth() + 1)+"-"+dueDate.getDate()+"-"+dueDate.getFullYear();
				if(issueResponse.reportedIssueList[i].updatedDate!=undefined&&issueResponse.reportedIssueList[i].updatedDate!=null){
					updatedDate = new Date(issueResponse.reportedIssueList[i].updatedDate);
					dateFormat1 = (updatedDate.getMonth() + 1)+"-"+updatedDate.getDate()+"-"+updatedDate.getFullYear();
				}
				
				
				 
					list.append('<li><span>'+
								'<a class="msg" style="padding-left:0px !important">'+
								'<span class="from">'+issueResponse.reportedIssueList[i].applicationName+
								': '+issueResponse.reportedIssueList[i].summary+' </span>'+
								'<span class="msg-body" style="width:90%">'+issueResponse.reportedIssueList[i].severity+
								': '+assigneeName+
								'</span><span style="float: right;margin-top: -18px;">'+issueResponse.reportedIssueList[i].percentageDone+'%'+
								'</span>'+
								'<div class="progress progress-striped active" style="height: 13px;"> <div class="progress-bar '+barStyle+' " role="progressbar" aria-valuenow="'+issueResponse.reportedIssueList[i].percentageDone+'" aria-valuemin="0" aria-valuemax="100" style="width: '+issueResponse.reportedIssueList[i].percentageDone+'%"></div></div>'+
								'<span style="margin-top:-15px;">'+dateFormat+
								'</span><span style="float: right;margin-top: -18px;">'+" last updated on "+dateFormat1+
								'</span></a></span></li>');
				
			}
			
		}
	});
}
function checkTypeOfUser(){
	$.ajax({
	url : "checkUserType",
	type : "POST",
	success : function(result) {
		var response = JSON.parse(result);
		if(response.isBelcouser==false){
			$("#applicationInvoiceId").show();
			$("#manageAccountId").show();
		}
	}
});
}

function getNotificationCount(){
	$.ajax({
	url : "getNotificationCount",
	type : "GET",
	success : function(result) {
//		console.log(result);
		$("#unreadNotifs").html(result.notificationCount);
	}
});
}

$("#uploadForm").validate({
	rules : {
		templateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		templateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		console.log("ajax call for template");
		submitUploadForm();
	}
});

function setModuleName(sheetName)
{
	$("#apiName").val(sheetName);
	
}

function setUploadConfirmation(buttonId){
	 
	$("#uploadConfirmation").modal("hide"); 
//	if(buttonId == "saveConfirmUpload"){
//	  $("#confirmUploadId").val(1);
//	  
//	}
//	else
//	  $("#saveConfirmUpload").val(-1);
}

function resetConfirmation(){
	$("#saveConfirmUpload").show();
	$("#confirmationHeader").show();
	$("#cancelUpload").html("Cancel");
	$("#uploadConfirmation").modal("hide");
}

var errors="";
function downloadErrorFile(){
	
	console.log(JSON.stringify(errors.reason));
	var errorRecords = errors.reason;
	errorString = JSON.stringify(errorRecords);
	window.location.href="/ElecnorES/downloadErrorFile?errorString="+errorString;

	
}

function getDetailsBasedOnSheetName()
{
	if($("#apiName").val()==='ProjectType')
	     getProjectTypeDetails();
	else if($("#apiName").val()==='DepartmentType')
		getDepartmentDetails();
	else if($("#apiName").val()==='SLicenseDirectory')
		getsLicenseDetails();
	else if($("#apiName").val()==='LLicenseDirectory')
		getLocalLicenseDetails();
	//else if($("#apiName").val()==='CUSTOMER_EXCEL_FORMAT') //lookup required
		
//			else if($("#apiName").val()==='VENDOR_EXCEL_FORMAT')	
//				else if($("#apiName").val()==='CONTRACTOR_EXCEL_FORMAT')	
	 
}
function resetUploadForm(){
	$("#fileName").val("");
}
function cancelUploadForm(){
	var validator = $("#uploadForm").validate();
	validator.resetForm();
	$('input').val('');
}
function submitUploadForm()
{
	
	
	$.ajax({
		url : 'templateController',
		data : new FormData($("#uploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			console.log("again in ajax");
			$("#uploadModal").modal("hide");
			result = JSON.parse(data);
			console.log(result);
			if(result.ajaxResult =="failure"){
				resetUploadForm();
				var out = document.getElementById("errorBlock");
				out.innerHTML = "";
				if(result.reason[0].colNumber == -1){
					for(var i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#confirmationHeader").hide();
					$("#saveConfirmUpload").hide();
					$("#cancelUpload").html("Close");
					getDetailsBasedOnSheetName($("#apiName").val());
					
					
				}
				else if(result.reason[0].excelCell == "A1"){
					
					for(var i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
				    
					$("#saveConfirmUpload").hide();
				}
				else{
					errors=result;
					for(var i=0;i<result.reason.length;i++){
						if(result.reason[i].excelCell == undefined){
							out.appendChild(document.createTextNode(" Row Number :"+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						}
						else{
							out.appendChild(document.createTextNode(" Cell Address :"+result.reason[i].excelCell+" Error :"+result.reason[i].errorMessage));
						}
						out.appendChild(document.createElement("br"));
					}
					gritterForSucessMsgs("Valid records have been successfully uploaded");
					getDetailsBasedOnSheetName($("#apiName").val());
				}	
				$("#uploadConfirmation").modal("show");
				$("#errorSection").css('display','block');
				

			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("The file  has been successfully uploaded");
				getDetailsBasedOnSheetName($("#apiName").val());
				resetUploadForm();
				cancelUploadForm();
			}
			else if(result.ajaxResult == "failure"){
				gritterForErrorMsgs("An error occurred : "+ result.reason);
			}
			else{
				gritterForErrorMsgs("Could not be saved.Contact dev");
			}
		},
		error : function() {
			gritterForErrorMsgs("Some problem occured");
		}
	});
	
}
function getApplicationPlan() {
		/*$.ajax({
			url : "getApplicationNames",
			type : "POST",
			success : function(result) {
				var response = JSON.parse(result);
				console.log(response);
				for (var i = 0; i < response.plans.length; i++){
					$("#projectsPlan").append("<li  onclick='changeSelectedProjectSpan(this.id);' id='"+response.plans[i].applicationPlanDirectory.applicationDirectory.applicationName+"'>" +
							"<a target='_blank' href='"+response.plans[i].applicationPlanDirectory.applicationDirectory.applicationLink+"'>"+response.plans[i].applicationPlanDirectory.applicationDirectory.applicationName+"</a></li>");
				}
				$("#selectedProjectSpan").html(response.plans[0].applicationPlanDirectory.applicationDirectory.applicationName);
			}
		});*/
}

function changeSelectedProjectSpan(value) {
//		$("#selectedProjectSpan").html(value);
}

function redirectToDownstreamApplication(appUrl, userName, password, isBelcoUser, userRoleId, userRoleName, subscriptionId, projId){
	
	$("#redirectToEndAppFormId").attr("action",appUrl);
	$("#userName").val(userName);
	$("#password").val(password);
	$("#isBelcoUser").val(isBelcoUser);
	$("#userRoleId").val(userRoleId);
	$("#userRoleName").val(userRoleName);
	$("#subscriptionId").val(subscriptionId);
	$("#projId").val(projId);
	$("#redirectToEndAppFormId").submit();
}

function logsCount(){
//	var notifCount = 0;
//	$.ajax({
//		url : "getNotificationData",
//		type : "POST",
//		success : function(result) {
//			response = JSON.parse(result);
//			if(response.ajaxResult == "success"){
//			for (var i = 0; i < response.logDetails.length; i++){
//				if(response.logDetails[i].logRead == false){
//					notifCount++;
//				}
//			}
//			$("#unreadNotifs").html(notifCount);
//			}
//		}
//	});
}