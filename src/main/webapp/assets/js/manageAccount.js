pageSetUp();
// Model i agree button
/*
 * $("#i-agree").click(function() { $this = $("#terms"); if ($this.checked) {
 * $('#myModal').modal('toggle'); } else { $this.prop('checked', true);
 * $('#myModal').modal('toggle'); } });
 */
acoountDetailsTable = null;
var pagefunction = function() {
	// console.log("cleared");

	/*
	 * // DOM Position key index //
	 * 
	 * l - Length changing (dropdown) f - Filtering input (search) t - The
	 * Table! (datatable) i - Information (records) p - Pagination (paging) r -
	 * pRocessing < and > - div elements <"#id" and > - div with an id <"class"
	 * and > - div with a class <"#id.class" and > - div with an id and class
	 * 
	 * Also see: http://legacy.datatables.net/usage/features
	 */

	/* BASIC ; */
	var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};

	acoountDetailsTable = $('#manageAccountsTable')
			.dataTable(
					{
						"aaSorting" : [ [ 1, "asc" ] ],
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
						"bFilter" : true,
						"bSearchable" : false,
						"bInfo" : true,
						"bDestroy" : true,
						aoColumns : [
								{
									"mData" : null,
									"sClass" : "control center",
									"sDefaultContent" : '<img src="assets/img/details_open.png'
											+ '">',
									"bSortable" : false
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return "<a href='#domainInfoInDetail?domainId="
												+ full[0]
												+ "' return='pagefunction()'>"
												+ full[1] + "</a>";
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[2];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[3];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return "<i	class='icon-append fa fa-envelope' href='#emailComposeModalOpen' data-toggle='modal' data-backdrop='static' style='font-size:18px; cursor:pointer;'></i>";
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return "<i	class='fa fa-thumbs-o-up' style='font-size:18px;'></i>";
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return "<i	class='fa fa-thumbs-o-down' style='font-size:18px;'></i>";
									}
								} ],

						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#manageAccountsTable'),
										breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});
	getAccountDetails();
	/* END BASIC */
};
var anOpen = [];

$(document).on(
		"click",
		"#manageAccountsTable td.control",
		function() {

			var nTr = this.parentNode;
			var i = $.inArray(nTr, anOpen);
			//
			if (i === -1) {
				$('img', this).attr('src', "assets/img/details_close.png");
				var nDetailsRow = acoountDetailsTable.fnOpen(nTr,
						fnFormatDetails(acoountDetailsTable, nTr), 'details');
				$('#innerDetails', nDetailsRow).slideDown();
				anOpen.push(nTr);
			} else {
				$('img', this).attr('src', "assets/img/details_open.png");
				$('#innerDetails', $(nTr).next()[0]).slideUp(function() {
					acoountDetailsTable.fnClose(nTr);
					anOpen.splice(i, 1);
				});
			}
			//
		});

function fnFormatDetails(oTable, nTr) {
	sOut = '<div class="tabbable" id="innerDetails"><ul class="nav nav-tabs" id="myTab"><li class="active"><a data-toggle="tab"href="#FTS">FTS</a></li><li><a data-toggle="tab" href="#MPR">MPR</a></li><li><a data-toggle="tab" href="#PD">PD</a></li></ul><div class="tab-content"></br><div id="FTS" class="tab-pane in active pull-left"><div class="infobox-container"><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "FTS Plan1"
			+ '<div class="infobox-content">Subscribed Plan</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "08-Aug-2014"
			+ '<div class="infobox-content">Payment Due Date</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "1,000"
			+ '<div class="infobox-content">Payment Due</div></div></div><div class="infobox infobox-blue"><div	class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "100"
			+ '<div class="infobox-content">No. of Users</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "1,000"
			+ '<div class="infobox-content">Data Usage Allowance</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "100"
			+ '<div class="infobox-content">Data Used</div></div></div></div></div><div id="MPR" class="tab-pane pull-left"><div class="infobox-container"><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "MPR Plan1"
			+ '<div class="infobox-content">Subscribed Plan</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "08-Aug-2014"
			+ '<div class="infobox-content">Payment Due Date</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "1,000"
			+ '<div class="infobox-content">Payment Due</div></div></div><div class="infobox infobox-blue"><div	class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "100"
			+ '<div class="infobox-content">No. of Users</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "1,000"
			+ '<div class="infobox-content">Data Usage Allowance</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "100"
			+ '<div class="infobox-content">Data Used</div></div></div></div></div><div id="PD" class="tab-pane pull-left"><div class="infobox-container"><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "PD Plan1"
			+ '<div class="infobox-content">Subscribed Plan</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "08-Aug-2014"
			+ '<div class="infobox-content">Payment Due Date</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "1,000"
			+ '<div class="infobox-content">Payment Due</div></div></div><div class="infobox infobox-blue"><div	class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "100"
			+ '<div class="infobox-content">No. of Users</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "1,000"
			+ '<div class="infobox-content">Data Usage Allowance</div></div></div><div class="infobox infobox-blue"><div class="infobox-icon"><i class="icon-comments"></i></div><div class="infobox-data">'
			+ "100"
			+ '<div class="infobox-content">Data Used</div></div></div></div></div></div></div>';
	return sOut;
}
// load related plugins

loadScript("assets/js/datatable/jquery.dataTables.min.js", function() {
	loadScript("assets/js/datatable/dataTables.tableTools.min.js", function() {
		loadScript("assets/js/datatable/dataTables.bootstrap.min.js",
				function() {
					loadScript(
							"assets/js/datatable/datatables.responsive.min.js",
							pagefunction);
				});
	});
});

function getAccountDetails() {
	$.ajax({
		url : "getDomainInfo",
		type : "POST",
		success : function(result) {
			result = JSON.parse(result);
			acoountDetailsTable.fnClearTable();
			for (var t = 0; t < result.allDomainDetails.length; t++) {
				acoountDetailsTable.fnAddData([
						result.allDomainDetails[t].domainId,
						result.allDomainDetails[t].companyName,
						result.allDomainDetails[t].domainName,
						result.allDomainDetails[t].status ], false, false,
						false);
			}
			acoountDetailsTable.fnDraw();
			$("#totalAccounts").html(t);
		},
		error : function(error) {
			console.log(error);
		}
	});
}

function resetComposeEmailForm() {

	var validator = $("#compose_email_form").validate();
	validator.resetForm();
	$('label').removeClass("state-error");
	$('label').removeClass("state-success");

	$('#toEmail').val("");
	$('#ccEmail').val("");
	$('#emailSubject').val("");
	$('#fileUploaded').val("");
	$('#fileName').val("");
	$('#emailBodyMAHidden').val("");
	$("#emailBodyMA").code("");
}

$("#compose_email_form").validate({
	// Rules for form validation
	rules : {
		toEmail : {
			required : true
		}
	},

	// Messages for form validation
	messages : {
		toEmail : {
			required : 'Please enter To email id'
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		ajaxCallForSendEmail(form.id);
	}
});

function setFilePathToInputFieldMA(thisObj) {
//	$("#fileName").val(filePath);
	if(thisObj.files[0].size > 15728640 ){
		thisObj.parentNode.nextSibling.value = "";
		$("#fileName").val("");
		gritterForErrorMsgs("The attachement size cannot exceed 15MB.");
	} else {
		$("#fileName").val(thisObj.value);
	}
}
function ajaxCallForSendEmail(formId) {
	$("#emailBodyMAHidden").val($("#emailBodyMA").code());
	$
			.ajax({
				url : 'setEmailSendFromManageAccount',
				data : new FormData($("#" + formId)[0]),
				cache : false,
				contentType : false,
				processData : false,
				type : 'POST',
				success : function(data) {
					resetComposeEmailForm();
					$("#emailComposeModalOpen").modal("hide");
					result = JSON.parse(data);
					if (result.ajaxResult == "success") {
						gritterForSucessMsgs("Email has been sent successfully");
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					resetComposeEmailForm();
					$("#emailComposeModalOpen").modal("hide");
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
}
