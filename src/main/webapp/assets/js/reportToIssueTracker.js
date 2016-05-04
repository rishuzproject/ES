/**
 * Anantha MeghanaJagruthi
 */

function resetCreateIssueForm() {
	$("#issueSno").val("");
	$('#createIssueFormId')[0].reset();
	$("#createNewIssueHeaderId").text("Create New Issue");
	$("#createIssueButtonSpanId").text("Create");
	// code for resetting ace_file_input.
	/*
	 * var file_input = $('#uploadFile');
	 * file_input.ace_file_input('reset_input');
	 */
}

function validateUploadFile() {

	var totalFiles;
	var totalFilesSize = 0;
	var checkInputMethod = $("#uploadFile").data().ace_input_method;
	if (checkInputMethod == "drop") {
		totalFiles = $("#uploadFile").data('ace_input_files');
	} else {
		totalFiles = $('#uploadFile')[0].files;
	}
	for (var len = 0; len < totalFiles.length; len++) {
		totalFilesSize = totalFilesSize + totalFiles[len].size;
	}
	// validation for 5MB.
	if (totalFilesSize > 5 * Math.pow(2, 20)) {
		gritterWarningMsg("The attachement size cannot exceed 5MB.");
		/*
		 * var file_input = $('#uploadFile');
		 * file_input.ace_file_input('reset_input');
		 */
		return false;
	}

	return true;
}

function validateCreateIssueForm() {
	/* Validation for text_field */

	if ($('#applicationId').val() == "") {
		gritterForErrorMsgs('Application has to be selected.');
		return false;
	}

	if ($('#issueTypeId').val() == "") {
		gritterForErrorMsgs('Issue type has to be selected.');
		return false;
	}

	if ($('#summary').val() == "") {
		gritterForErrorMsgs("Summary cannot be empty.");
		document.getElementById('summary').focus();
		return false;
	}

	if ($('#statusId').val() == "") {
		gritterForErrorMsgs('Status has to be selected.');
		return false;
	}

	return true;
}

function getDateByAddingNoOfDaysByExcludingWeekends(fromDate, days) {
	var count = 0;
	while (count < days) {
		fromDate.setDate(fromDate.getDate() + 1);
		if (fromDate.getDay() != 0 && fromDate.getDay() != 6) // Skip weekends
			count++;
	}
	return fromDate;
}

function setDueDatebasedOnSeverity() {

	var delDate = null;
	var curDate = new Date();
	if (($("select[name='severityId'").find('option:selected').text())
			.toLowerCase() == "low"
			&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {

		delDate = moment(
				getDateByAddingNoOfDaysByExcludingWeekends(curDate, 10))
				.format("MM-DD-YYYY");
	} else if (($("select[name='severityId'").find('option:selected').text())
			.toLowerCase() == "normal"
			&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {
		delDate = moment(getDateByAddingNoOfDaysByExcludingWeekends(curDate, 5))
				.format("MM-DD-YYYY");

	} else if (($("select[name='severityId'").find('option:selected').text())
			.toLowerCase() == "high"
			&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {
		delDate = moment(getDateByAddingNoOfDaysByExcludingWeekends(curDate, 3))
				.format("MM-DD-YYYY");
	} else if (($("select[name='severityId'").find('option:selected').text())
			.toLowerCase() == "urgent"
			&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {
		delDate = moment(getDateByAddingNoOfDaysByExcludingWeekends(curDate, 1))
				.format("MM-DD-YYYY");

	}
	$("#dueDate").val(delDate);
}

function getLookUpDetailsToCreateIssue() {

	$("#createIssue").modal("show");

	getApplicationDetails();
	$("#reportedDate").val(moment(new Date()).format("MM-DD-YYYY"));
	$("#dueDate").val(
			moment(getDateByAddingNoOfDaysByExcludingWeekends(new Date(), 10))
					.format("MM-DD-YYYY"));

}

function reportThisIssue() {
	if (!validateUploadFile()) {
		return;
	} else if (!validateCreateIssueForm()) {
		return;
	}
	$.ajax({
		url : "reportToIssueTracker",
		data : new FormData($("#createIssueFormId")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(result) {
			var data = new FormData($("#attachmentUploadForm")[0]);
			gritterForSucessMsgs("Ticket has been reported successfully.");
			resetCreateIssueForm();
			$("#createIssue").modal("hide");
		},
		error : function(error) {
			console.log("error");
		}
	});
}

$("#createIssueTab").one("click", function() {

	var e = document.getElementById("statusId");
	var r = document.createElement("option");
	r.text = "New";
	r.value = "8";
	try {
		/* if($('select#statusId option').length==0){ */
		e.add(r, select.options[null]);
		// }

	} catch (n) {
		e.add(r, null);
	}
	$(".selectpicker").selectpicker("refresh");

});

function getIssueTypesFromIssueTracker() {
	$.ajax({
		url : "getAllIssueTypesFromIssueTracker",
		type : "GET",
		success : function(result) {
			var issueTypesList = JSON.parse(result);
			initializeSelectBoxesInForm("issueTypeId", issueTypesList);
		}
	});

}

function getSeveritiesFromIssueTracker() {

	$.ajax({
		url : "getAllSeverityFromIssueTracker",
		type : "GET",
		success : function(result) {
			var severities = JSON.parse(result);
			initializeSelectBoxesInForm("severityId", severities);
		}
	});
}

function getProjectsListFromEcosystem(id) {
	var data = {
		"applicationId" : id
	};
	$.ajax({
		url : "getAllProjectsFromIssueTracker",
		method : "POST",
		data : data,
		success : function(result) {
			var tempResult = JSON.parse(result);
			initializeSelectBoxesInForm("relatedProject", tempResult);
		}
	});
}

function getApplicationDetails() {
	$
			.ajax({
				url : "RedirectedFromDownStreamApp/getApplicationDetailsFromIssueTracker",
				method : "POST",
				success : function(result) {
					var tempResult = JSON.parse(result);
					initializeSelectBoxesInForm("applicationId", tempResult);
				},
				complete : function() {
					getProjectsListFromEcosystem($("#applicationId").val());
					getIssueTypesFromIssueTracker();
					getSeveritiesFromIssueTracker();
					getModulesListFromIssueTracker($("#applicationId").val());
				}
			});
}
function getModulesListFromIssueTracker(id) {
	var data = {
		"applicationId" : id
	};
	$.ajax({
		url : "getAllModulesFromIssueTracker",
		type : "POST",
		data : data,
		success : function(result) {
			var moduleList = JSON.parse(result);
			initializeSelectBoxesInForm("applicationModuleId", moduleList);
		}
	});
}

function initializeSelectBoxesInForm(selectBoxId, values) {
	var n;
	$('#' + selectBoxId).empty();
	var e = document.getElementById(selectBoxId);
	for (n in values) {
		if (values[n] == undefined)
			continue;
		var r = document.createElement("option");
		r.text = values[n];
		r.value = n;
		try {
			e.add(r, select.options[null]);
		} catch (n) {
			e.add(r, null);
		}
	}
	$(".selectpicker").selectpicker("refresh");

}

function redirectToIssueTracker() {
	$
			.ajax({
				type : 'POST',
				url : 'redirectToIssueTrackerApp',
				async : false,
				success : function(result) {
					var responseDetails = JSON.parse(result);
					if (responseDetails.ajaxResult == "success") {
						$("#redirectToIssueTrackerFormId").prop("action",
								responseDetails.issueTrackerUrl);
						$("#emailidForIssueTracker").val(
								responseDetails.emailId);
						$("#passwordForIssueTracker").val(
								responseDetails.password);
						$("#appIdForIssueTracker").val(
								responseDetails.applicationId);
						if ($("#emailidForIssueTracker").val() != ""
								&& $("#passwordForIssueTracker").val() != ""
								&& $("#appIdForIssueTracker").val() != "") {
							$("#redirectToIssueTrackerFormId").submit();
						}

					} else {
						gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}
				},
				error : function() {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});

}