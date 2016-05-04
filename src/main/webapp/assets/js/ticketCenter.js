pageSetUp();
// Model i agree button
/*
 * $("#i-agree").click(function() { $this = $("#terms"); if ($this.checked) {
 * $('#myModal').modal('toggle'); } else { $this.prop('checked', true);
 * $('#myModal').modal('toggle'); } });
 */
reportedIssuesDetailsTable = null;
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
	reportedIssuesDetailsTable = $('#reportedIssuesTable')
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
						"bFilter" : true,
						"bSearchable" : false,
						"bInfo" : true,
						"bDestroy" : true,
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#reportedIssuesTable'),
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

	/* END BASIC */
};

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

$("#report_issue_form").validate({
	// Rules for form validation
	rules : {
		productName : {
			required : true,

		},
		moduleName : {
			required : true,

		},
		projectName : {
			required : true,

		},
		severity : {
			required : true,

		},
		description : {
			required : true,
		}
	},

	// Messages for form validation
	messages : {
		productName : {
			required : "Please select the product"

		},
		moduleName : {
			required : "Please select the module"

		},
		projectName : {
			required : "Please select the project"

		},
		severity : {
			required : "Please select the severity"

		},
		description : {
			required : "Please enter the description"

		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
//		form.submit();
		gritterForInfoMsgs('This functionality is under construction');
		$("#reportIssueRemoteModal").modal("hide");
	}
});

function resetReportIssueForm() {
	var validator = $("#report_issue_form").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#description").val("");
	$("#additionalComments").val("");
	$("#productName").val("");
	$("#moduleName").val("");
	$("#projectName").val("");
	$("#severity").val("");
	$("#fileName").val("");
}
$('#productName').change(function() {
	
	var selValue = $('#productName').val();
	var e = document.getElementById("moduleName");
	$('#moduleName').find('option').remove().end().append(
	'<option value="">--Select Module--</option>');
	
    var ecoModuleText = ["Dashboard","App Store","Manage Account","Billing & Invoice","Jobs","Manage User","Customer Directory","Project Types","Manage Department","Manage Item DB","Budget Code"];
    var mprModuleText = ["Projects","RFC","Manpower","Projection","Cashflow","Reports","Budget Form","Dashboard","Manage Users"];
    var pdModuleText = ["Dashboard","Projects","Purchase Directive","Purchase Order","Profile"];
    var ftsModuleText = ["Dashboard","Fixture","Electrical/Architectural","Tracking","Fixture Issues","Change Order","Manage User"];
	if (selValue == "ecosystem") {
		for (var n = 0; n < ecoModuleText.length; n++) {
			var r = document.createElement("option");
			r.text = ecoModuleText[n];
			r.value = ecoModuleText[n];
			try {
				e.add(r, select.options[null]);
			} catch (i) {
				e.add(r, null);
			}
		}
		//$(".selectpicker").selectpicker("refresh");
	} else if (selValue == "mpr") {

		for (var n = 0; n < mprModuleText.length; n++) {
			var r = document.createElement("option");
			r.text = mprModuleText[n];
			r.value = mprModuleText[n];
			try {
				e.add(r, select.options[null]);
			} catch (i) {
				e.add(r, null);
			}
		}
		//$(".selectpicker").selectpicker("refresh");
	} else if (selValue == "pd") {

		for (var n = 0; n < pdModuleText.length; n++) {
			var r = document.createElement("option");
			r.text = pdModuleText[n];
			r.value = pdModuleText[n];
			try {
				e.add(r, select.options[null]);
			} catch (i) {
				e.add(r, null);
			}
		}
		//$(".selectpicker").selectpicker("refresh");
	} else if (selValue == "fts") {

		for (var n = 0; n < ftsModuleText.length; n++) {
			var r = document.createElement("option");
			r.text = ftsModuleText[n];
			r.value = ftsModuleText[n];
			try {
				e.add(r, select.options[null]);
			} catch (i) {
				e.add(r, null);
			}
		}
		//$(".selectpicker").selectpicker("refresh");
	}
});
