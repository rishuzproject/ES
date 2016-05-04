/**
 * @author Anantha MeghanaJagruthi
 * 
 */
/*
 * DO NOT REMOVE : GLOBAL FUNCTIONS!
 * 
 * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS // activate tooltips
 * $("[rel=tooltip]").tooltip(); // activate popovers
 * $("[rel=popover]").popover(); // activate popovers with hover states
 * $("[rel=popover-hover]").popover({ trigger: "hover" }); // activate inline
 * charts runAllCharts(); // setup widgets setup_widgets_desktop(); // run form
 * elements runAllForms();
 * 
 * *******************************
 * 
 * pageSetUp() is needed whenever you load a page. It initializes and checks for
 * all basic elements of the page and makes rendering easier.
 * 
 */

pageSetUp();

/*
 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE eg alert("my home function");
 * 
 * var pagefunction = function() { ... }
 * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
 * 
 */

// PAGE RELATED SCRIPTS
// pagefunction
// $.root_ = $("body");
// var initApp = function(a) {
// return a.SmartActions = function() {
// var a = {
// alertBox: function(a) {
// $.SmartMessageBox({
// title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
// content: a.data("logout-msg") || "<br>This role will be "+
// "permanently deleted . Only Admin can recover it.<br>"+"" +
// "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark
// bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
// buttons: "[No][Yes]"
// }, function(a) {
// if(a=="Yes"){deleteJobController();}
// });
// }
// };
// $.root_.on("click", '[data-action="alertBox"]', function(b) {
// var c = $(this);
// a.alertBox(c), b.preventDefault(), c = null;
// });
// }, a
// }({});
// jQuery(document).ready(function() {
// initApp.SmartActions();
//       
// });
var jobDetailsTable = null;
var pendingjobDetailsTable = null;
var total;
var informationTab = 0;
var personnelTab = 0;
var legalTab = 0;
var settingsTab = 0;
var budgetTab = 0;
var addressArray = [];
var custAddressArray = [];
var custrepAddressArray = [];
var venaddressArray = [];
var venrepAddressArray = [];
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
	jobDetailsTable = $('#jobTable')
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
						aoColumns : [
								{
									sClass : "center",
									mRender : function(data, type, full) {
										id = full[0];
										return full[0] == null ? "" : full[0];
									}

								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[11] == null ? "" : full[11];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[10] == null ? "" : full[10];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[1] == null ? "" : full[1];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[2] == null ? "" : full[2];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[3] == null ? "" : full[3];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[4] == null ? "" : full[4];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[5] == null ? "" : full[5];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[6] == null ? "" : full[6];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[7] == null ? "" : full[7];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[8] == null ? "" : full[8];
									}
								},
								{
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:updateJobDetails('"
												+ full[9]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setJobId('"
												+ full[9]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a> </div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#jobTable'), breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});
	pendingjobDetailsTable = $('#pendingjobTable')
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
				aoColumns : [
						{
							sClass : "center",
							mRender : function(data, type, full) {
								id = full[0];
								return full[0] == null ? "" : full[0];
							}

						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[11] == null ? "" : full[11];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[10] == null ? "" : full[10];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[1] == null ? "" : full[1];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[2] == null ? "" : full[2];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[3] == null ? "" : full[3];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[4] == null ? "" : full[4];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[5] == null ? "" : full[5];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[6] == null ? "" : full[6];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[7] == null ? "" : full[7];
							}
						},
						{
							sClass : "center",
							mRender : function(data, type, full) {
								return full[8] == null ? "" : full[8];
							}
						},
						{
							mData : null,
							sClass : "center",
							mRender : function(data, type, full) {
								return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:updateJobDetails('"
										+ full[9]
										+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setJobId('"
										+ full[9]
										+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a> </div>";
							}
						} ],
				"preDrawCallback" : function() {
					// Initialize the responsive datatables helper once.
					if (!responsiveHelper_dt_basic) {
						responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
								$('#jobTable'), breakpointDefinition);
					}
				},
				"rowCallback" : function(nRow) {
					responsiveHelper_dt_basic.createExpandIcon(nRow);
				},
				"drawCallback" : function(oSettings) {
					responsiveHelper_dt_basic.respond();
				}
			});
	materialCostTable = $('#materialCostTable')
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
						"bFilter" : false,
						"bSearchable" : false,
						"bInfo" : true,
						"bDestroy" : true,
						"paging" : false,
						"ordering" : false,
						"info" : false,
						"bLengthChange" : false,
						aoColumns : [
								{
									sClass : "center",
									mRender : function(data, type, full) {
										id = full[0];
										return full[0];
									}

								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[1];
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
										return full[4];
									}
								},
								{
									sClass : "center editable",
									mRender : function(data, type, full) {
										return full[5];
									}
								},
								{
									sClass : "center editable",
									mRender : function(data, type, full) {
										return full[6];
									}
								},
								{
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setJobId('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#materialCostTable'),
										breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						},
						"fnDrawCallback" : function() {
							$('.editable')
									.editable(
											function(sVal) {
												return sVal;
											},
											{
												type : 'text',
												placeholder : '',
												"callback" : function(sValue, y) {
													var nodes = materialCostTable
															.fnGetNodes();
													var updatedValue = 0;
													var totalSum = 0;
													for (var i = 0; i < nodes.length; i++) {
														updatedValue = 0;
														// Price-
														// 5th
														// Coulumn
														updatedValue = convertor2Int($(
																nodes[i]).find(
																"td:eq(5)")
																.html());
														totalSum = convertor2Int($(
																nodes[i]).find(
																"td:eq(6)")
																.html());
														if (updatedValue < 0) {
															gritterNotificationsForMandatoryFields("Negative values are not allowed");
															$(nodes[i]).find(
																	"td:eq(5)")
																	.html("0");
															return;
														}
														if (totalSum < 0) {
															gritterNotificationsForMandatoryFields("Negative values are not allowed");
															$(nodes[i]).find(
																	"td:eq(6)")
																	.html("0");
															return;
														}

														// Condition
														// to
														// check
														// isNaN
														// for
														// quantity
														// entered
														if (!isNaN(updatedValue)) {

															$(nodes[i])
																	.find(
																			"td:eq(5)")
																	.html(
																			updatedValue
																					.toFixed(2));
														}
														if (!isNaN(totalSum)) {

															$(nodes[i])
																	.find(
																			"td:eq(6)")
																	.html(
																			totalSum
																					.toFixed(2));
														}

													}

												},
												"tooltip" : 'Click to enter quantity.',
												onblur : "submit",
												onsubmit : function(settings,
														td) {
													var input = $(td).find(
															'input');
													var original = input.val();
													if (!isNaN(original)) {
														return true;
													} else {
														input
																.css(
																		'background-color',
																		'#f7f7f7')
																.css('color',
																		'#000');
														return false;
													}
												},

											});
						}
					});
	getActiveJobDetails();
	getStates();
	// getAllJobDocuments();
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
/* start of initializing datepicker */
/*
 * $("#startDate").datepicker({ format : "mm-dd-yyyy" }).on('changeDate',
 * function(ev) { $('#startDate').datepicker('hide'); });
 * 
 * $("#endDate").datepicker({ format : "mm-dd-yyyy" }).on('changeDate',
 * function(ev) { $('#endDate').datepicker('hide'); });
 */

//$("#extendedDate").datepicker({
//	format : "mm-dd-yyyy"
//}).on('changeDate', function(ev) {
//	$('#extendedDate').datepicker('hide');
//});
//
//$("#coDate").datepicker({
//	format : "mm-dd-yyyy"
//}).on('changeDate', function(ev) {
//	$('#coDate').datepicker('hide');
//});

$("#noticeReceivedDate").datepicker({
	format : "mm-dd-yyyy"
}).on('changeDate', function(ev) {
	$('#noticeReceivedDate').datepicker('hide');
});

$("#insuranceSentDate").datepicker({
	format : "mm-dd-yyyy"
}).on('changeDate', function(ev) {
	$('#insuranceSentDate').datepicker('hide');
});

$("#performanceSentDate").datepicker({
	format : "mm-dd-yyyy"
}).on('changeDate', function(ev) {
	$('#performanceSentDate').datepicker('hide');
});

$("#cpnSentDate").datepicker({
	format : "mm-dd-yyyy"
}).on('changeDate', function(ev) {
	$('#cpnSentDate').datepicker('hide');
});
/* end of initializing datepicker */
$.validator.addMethod("extendedDateHigherThanEndDate",
		function(value, element) {
			// If false, the validation fails and the message below is displayed
			var myDate = value;
			return (this.optional(element) || Date.parse(myDate) > Date
					.parse($("#endDate").val()));
		}, "Date must be higher than end date");
$.validator.addMethod("endDateHigherThanStartDate", function(value, element) {
	// If false, the validation fails and the message below is displayed
	var myDate = value;
	return (this.optional(element) || Date.parse(myDate) > Date.parse($(
			"#startDate").val()));
}, "End date must be higher than start date");

// check if job number already exists
$.validator
		.addMethod(
				"checkJobNumber",
				function(value, element) {
					var jobId = 0;
					if (value == "") {
						return true;
					}
					if ($("#saveJobBtn").html() != "<i class='fa fa-floppy-o'></i> &nbsp; Save") {
						jobId = $('#jobId').val();
					}
					var isExist = false;
					var dataObj = {
						jobNumber : value,
						jobId : jobId
					};
					$.ajax({
						type : 'POST',
						url : 'checkJobNumber',
						data : dataObj,
						async : false,
						success : function(result) {
							isExist = JSON.parse(result);
							if (isExist == true) {
								return !isExist;
							}
						}
					});
					return !isExist;
				}, "Project Number already exists");

// Form Validations
$("#manageJobForm")
		.validate(
				{
					// Rules for form validation
					rules : {
						jobName : {
							required : true
						},

						jobNumber : {
							// required : true,
							checkJobNumber : true
						},
						bidNumber : {
							required : true,
							number : true
						},
						departmentType : {
							required : true
						},
						supervisor : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							}
						},
						customerDirectory : {
							required : true
						},
						bidPrice : {
							number : true,
							required : true
						},
						architectId : {
							number : true,
						},
						originalContractValue : {
							number : true,
						},
						reportMargin : {
							number : true,
							required : function(element) {
									return $("#jobNumber").val().length > 0;
							}
						},
						performanceTargetMargin : {
							number : true,
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							}
						},
						laborBonusTargetOverBudget : {
							number : true,
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							}
						},
						autoApprovalLimit : {
							number : true,
						},
						extendedDate : {
							extendedDateHigherThanEndDate : true,
						},
						description : {
							maxlength : 1000,
						},
						jobAddress : {
							maxlength : 500,
						},
						endDate : {
							endDateHigherThanStartDate : true,
						},
						coDate : {
							extendedDateHigherThanEndDate : true,
						},
						collectionDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						retentionDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						retentionPercent : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						materialDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						subcontractorDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						directJobDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						rentalEquipmentDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						ownedEquipmentDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						projAdminDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						laborDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						indirectDayOut : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						activationValidityTimePeriod : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						contractAmount : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						noticeReceivedDate : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
						},
						insuranceSentDate : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
						},
						cpnSentDate : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
						},
						bidBudgetMaterialCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsBudgetMaterialCosts : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						bidBudgetSubcontractorsCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsSubcontractorsCosts : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						bidBudgetDirectJobCosts : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsBudgetDirectJobCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						bidBudgetRentalEquipmentCosts : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsBudgetRentalEquipmentCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						bidBudgetOwnedEquipmentsCosts : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsOwnedEquipmentCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						bidBudgetProjectAdministrationCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsProjectAdministrationCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						bidBudgetLaborCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsBudgetLaborCost : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						bidBudgetIndirectExpenses : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
						operationsIndirectExpenses : {
							required : function(element) {
								return $("#jobNumber").val().length > 0;
							},
							number : true,
						},
					},

					// Messages for form validation
					messages : {
						jobName : {
							required : "Enter your jobname",
							alphanumeric : "Enter only alphabets and numbers"
						},
						description : {
							maxlength : "Enter upto 1000 characters",
						},
						bidNumber : {
							required : "Enter the bid number"
						},
						jobNumber : {
							// required : "Enter your jobnumber",
							alphanumeric : "Enter only alphabets and numbers"
						},
						departmentType : {
							required : "Please select the department"
						},
						jobAddress : {
							maxlength : "Enter upto 500 characters",
						},
						supervisor : {
							required : "Please select the Job Supervisor"
						},
						customerDirectory : {
							required : "Please select the customer"
						},
						bidPrice : {
							required : "Please enter the bid price",
							number : "Please enter only number",
						},
						architectId : {
							number : "Please enter only number",
						},
						originalContractValue : {
							number : "Please enter only number",
						},
						reportMargin : {
							required : "Please enter the report margin",
							number : "Please enter only number",
						},
						performanceTargetMargin : {
							required : "Please enter the performance target margin",
							number : "Please enter only number",
						},
						autoApprovalLimit : {
							// required : "Please enter the auto approval
							// limit",
							number : "Please enter only number"
						},
						laborBonusTargetOverBudget : {
							required : "Please enter the labor bonus target over budget",
							number : "Please enter only number",
						},
						activationValidityTimePeriod : {
							required : "Please enter the activation validity time period",
							number : "Please enter only number",
						},
						indirectDayOut : {
							required : "Please enter the indirect dayOut",
							number : "Please enter only number",
						},
						laborDayOut : {
							required : "Please enter the labort dayOut",
							number : "Please enter only number",
						},
						projAdminDayOut : {
							required : "Please enter the project admin dayOut",
							number : "Please enter only number",
						},
						ownedEquipmentDayOut : {
							required : "Please enter the owned equipment dayOut",
							number : "Please enter only number",
						},
						rentalEquipmentDayOut : {
							required : "Please enter the rental equipment dayOut",
							number : "Please enter only number",
						},
						directJobDayOut : {
							required : "Please enter the direct job dayOut",
							number : "Please enter only number",
						},
						subcontractorDayOut : {
							required : "Please enter the subcontractor dayOut",
							number : "Please enter only number",
						},
						materialDayOut : {
							required : "Please enter the material dayOut",
							number : "Please enter only number",
						},
						retentionPercent : {
							required : "Please enter the retention percent",
							number : "Please enter only number",
						},
						retentionDayOut : {
							required : "Please enter the retention dayOut",
							number : "Please enter only number",
						},
						collectionDayOut : {
							required : "Please enter the collection dayOut",
							number : "Please enter only number",
						},
						contractAmount : {
							required : "Please enter the contract amount",
							number : "Please enter only number",
						},
						cpnSentDate : {
							required : "Please enter the sent date",
						},
						insuranceSentDate : {
							required : "Please enter the sent date",
						},
						noticeReceivedDate : {
							required : "Please enter the received date",
						},
						bidBudgetIndirectExpenses : {
							required : "Please enter the bid budget indirect cost",
							number : "Please enter only number",
						},
						operationsIndirectExpenses : {
							required : "Please enter the operations budget indirect cost",
							number : "Please enter only number",
						},
						bidBudgetLaborCost : {
							required : "Please enter the bid budget labor cost",
							number : "Please enter only number",
						},
						operationsBudgetLaborCost : {
							required : "Please enter the operations budget labor cost",
							number : "Please enter only number",
						},
						bidBudgetProjectAdministrationCost : {
							required : "Please enter the bid budget project administration cost",
							number : "Please enter only number",
						},
						operationsProjectAdministrationCost : {
							required : "Please enter the operations budget project administration cost",
							number : "Please enter only number",
						},
						bidBudgetOwnedEquipmentsCosts : {
							required : "Please enter the bid budget owned equipment cost",
							number : "Please enter only number",
						},
						operationsOwnedEquipmentCost : {
							required : "Please enter the operations budget owned equipment cost",
							number : "Please enter only number",
						},
						bidBudgetRentalEquipmentCosts : {
							required : "Please enter the bid budget rental equipment cost",
							number : "Please enter only number",
						},
						operationsBudgetRentalEquipmentCost : {
							required : "Please enter the operations budget rental equipment cost",
							number : "Please enter only number",
						},
						bidBudgetDirectJobCosts : {
							required : "Please enter the bid budget direct cost",
							number : "Please enter only number",
						},
						operationsBudgetDirectJobCost : {
							required : "Please enter the operations budget direct cost",
							number : "Please enter only number",
						},
						bidBudgetSubcontractorsCost : {
							required : "Please enter the bid budget subcontractors cost",
							number : "Please enter only number",
						},
						operationsSubcontractorsCosts : {
							required : "Please enter the operations budget subcontractors cost",
							number : "Please enter only number",
						},
						bidBudgetMaterialCost : {
							required : "Please enter the bid budget material cost",
							number : "Please enter only number",
						},
						operationsBudgetMaterialCosts : {
							required : "Please enter the operations budget material cost",
							number : "Please enter only number",
						},
					},
					ignore : "",
					invalidHandler: function(e, validator){
//			            if(validator.errorList.length)
//			            $('.tabsDetails a[href="#' + jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id') + '"]').tab('show')
//						console.log(validator.errorList);
						$('.datepickerStyle').datepicker('hide');
						if(validator.errorList.length){
							$('.tabsDetails a[href="#' + jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id') + '"]').tab('show');
							gritterForErrorMsgs("Mandatory fields in previous tabs are empty..Enter those fields first");
						}
					},
					// Do not change code below
					errorPlacement : function(error, element) {
						// console.log(validJobNumber);
						$('.datepickerStyle').datepicker('hide');
						error.insertAfter(element.parent());
					},
					submitHandler : function(form) {
//						if (checkPreviousTabs()) {
						$('.datepickerStyle').datepicker('hide');
							saveUpdateJobDetails();
//						} else {
//							gritterForErrorMsgs("Mandatory fields in previous tabs are empty..Enter those fields first");
//						}
					}
				});
function checkPreviousTabs() {
	var jobNoValid = $("#jobNumber").val();
	var noticeValid = $(".noticeToProceedFileLists").html();
	var executeValid = $(".exectedContractFileLists").html();
	var cpnValid = $(".insuranceCertificateFileLists").html();
	var insuValid = $(".californiaPreliminaryNoticeFileLists").html();
	var flag = false,flag1,flag2,flag3,flag4;
	if(jobNoValid != "" ){
		if(noticeValid.length > 0){
			flag1 = true;
			$("#noticeErr").hide();
		}else{
			flag1 = false;
			$("#noticeErr").show();
		}
		if(executeValid.length > 0){
			flag2 = true;
			$("#exectedContractErr").hide();
		}else{
			flag2 = false;
			$("#exectedContractErr").show();
		}
		if(cpnValid.length > 0){
			flag3 = true;
			$("#insuranceErr").hide();
		}else{
			flag3 = false;
			$("#insuranceErr").show();
		}
		if(insuValid.length > 0){
			flag4 = true;
			$("#cpnErr").hide();
		}else{
			flag4 = false;
			$("#cpnErr").show();
		}
		flag = flag1 && flag2 && flag3 && flag4;
	}else{
		flag = true;
	}
	
	return flag;
}
function resetJobDetailForm() {
	//$('#endDateLb').removeClass('pointerEvent');
	var validator = $("#manageJobForm").validate();
	validator.resetForm();
	$('label').removeClass("state-error");
	$('section').removeClass("state-error");
	$('label').removeClass("state-success");
	$('#coDateLb').addClass('pointerEvent');
	$('#extendedDateLb').addClass('pointerEvent');
	$("#modalTitleIdForJobs")
			.html(
					"<i class='fa fa-suitcase txt-color-blue'>&nbsp;</i> Request New Job Creation");
	$("#saveAndContinueJobBtn").show();
	$("#cpnErr").hide();
	$("#insuranceErr").hide();
	$("#exectedContractErr").hide();
	$("#noticeErr").hide();
	$("#saveJobBtn").html("<i class='fa fa-floppy-o'></i> &nbsp; Save");
	$(".updateStyles").addClass("disableStyles");
	$("#jobAction").val("");
	$("#jobId").val("");
	$("#jobName").val("");
	$("#jobNumber").val("");
	$('#jobNumber').prop("readonly",false);
	$("#jobAddress").val("");
	$("#startDate").val("");
	$("#endDate").val("");
	$("#coDate").val("");
	$("#extendedDate").val("");
	$('#coDate').prop("tabindex","-1");
	$('#extendedDate').prop("tabindex","-1");
	$("#description").val("");
	$("#bidPrice").val("");
	$("#bidNumber").val("");
	$("#originalContractValue").val("");
	$("#reportMargin").val("");
	$("#performanceTargetMargin").val("");
	$("#laborBonusTargetOverBudget").val("");
	$("#autoApprovalLimit").val("");
	$("#insuranceSentDate").val("");
	$("#performanceSentDate").val("");
	$("#cpnSentDate").val("");
	$("#noticeReceivedDate").val("");
	$("#otherTypeOfContract").val("");
	$("#contractAmount").val("");
	$("#contractNumber").val("");
	$("#isCertifiedPayroll").prop("checked", false);
	$("#ownerControlledInsuranceProg").prop("checked", false);
	$('form select').val("");
	$("#noticeToProceed").val("");
	$("#executedContract").val("");
	$("#rfi").val("");
	$("#submittals").val("");
	$("#drawingLog").val("");
	$("#communications").val("");
	$("#transmittalSheets").val("");
	$("#bidCommunications").val("");
	$("#insuranceCertificate").val("");
	$("#paymentBond").val("");
	$("#californiaPreliminaryNotice").val("");
	$("#ownerControlledInsuranceProgDocuments").val("");
	$("#otherDocuments").val("");
	$("#bidBudgetMaterialCost").val("");
	$("#operationsBudgetMaterialCosts").val("");
	$("#bidBudgetSubcontractorsCost").val("");
	$("#operationsSubcontractorsCosts").val("");
	$("#bidBudgetDirectJobCosts").val("");
	$("#operationsBudgetDirectJobCost").val("");
	$("#bidBudgetRentalEquipmentCosts").val("");
	$("#operationsBudgetRentalEquipmentCost").val("");
	$("#bidBudgetOwnedEquipmentsCosts").val("");
	$("#operationsOwnedEquipmentCost").val("");
	$("#bidBudgetProjectAdministrationCost").val("");
	$("#operationsProjectAdministrationCost").val("");
	$("#bidBudgetLaborCost").val("");
	$("#operationsBudgetLaborCost").val("");
	$("#bidBudgetIndirectExpenses").val("");
	$("#operationsIndirectExpenses").val("");
	$("#collectionDayOut").val(90);
	$("#retentionDayOut").val(180);
	$("#retentionPercent").val(10);
	$("#materialDayOut").val(90);
	$("#subcontractorDayOut").val(9);
	$("#directJobDayOut").val(0);
	$("#rentalEquipmentDayOut").val(90);
	$("#ownedEquipmentDayOut").val(0);
	$("#projAdminDayOut").val(30);
	$("#laborDayOut").val(0);
	$("#indirectDayOut").val(0);
	$("#activationValidityTimePeriod").val(30);
	$("#architectId").val('');
	// legal tab reset
	$(".noticeToProceedFileLists").empty();
	$(".exectedContractFileLists").empty();
	$(".rfiFileLists").empty();
	$(".otherDocumentsFileLists").empty();
	$(".submittalsFileLists").empty();
	$(".drawingLogFileLists").empty();
	$(".communicationsFileLists").empty();
	$(".transmittalsSheetsFileLists").empty();
	$(".bidDocumentsFileLists").empty();
	$(".insuranceCertificateFileLists").empty();
	$(".paymentBondFileLists").empty();
	$(".californiaPreliminaryNoticeFileLists").empty();
	$(".ownerControlledInsuranceProgDocumentsFileLists").empty();
	$("#otherDocuments").prop("disabled", true);
	$("#californiaPreliminaryNotice").prop("disabled", true);
	$("#paymentBond").prop("disabled", true);
	$("#insuranceCertificate").prop("disabled", true);
	$("#bidCommunications").prop("disabled", true);
	$("#transmittalSheets").prop("disabled", true);
	$("#communications").prop("disabled", true);
	$("#drawingLog").prop("disabled", true);
	$("#submittals").prop("disabled", true);
	$("#rfi").prop("disabled", true);
	$("#executedContract").prop("disabled", true);
	$("#noticeToProceed").prop("disabled", true);
	$("#ownerControlledInsuranceProgDocuments").prop("disabled", true);
	
	$("#otherDocuments").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#californiaPreliminaryNotice").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#paymentBond").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#insuranceCertificate").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#bidCommunications").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#transmittalSheets").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#communications").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#drawingLog").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#submittals").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#rfi").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#executedContract").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#noticeToProceed").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#ownerControlledInsuranceProgDocuments").parent().parent().attr("title", "Can not upload Documents before saving job");
	$("#downloadDocumentsId").prop("disabled", true);
	// $("#informationTab").parent().parent().addClass("active");
	highlightCount();
	$("#addressInfo").hide();
	resetAddressForm();
	$("#addressDetails").html("");
	addressArray = [];
}
function initializeAllSelectBox(responseData) {
	// var lastName = "";
	// $('#vendorList').find('option').remove().end().append(
	// '<option value=""> Choose Vendor</option>');
	// $('#customerDirectory').find('option').remove().end().append(
	// '<option value=""> Choose Customer</option>');
	// $('#departmentType').find('option').remove().end().append(
	// '<option value=""> Choose Department</option>');
	// $('#contractorDirectory').find('option').remove().end().append(
	// '<option value="">Choose Contractor</option>');
	// $('#supervisor').find('option').remove().end().append(
	// '<option value="">Choose Supervisor</option>');
	// $('#manager').find('option').remove().end().append(
	// '<option value="">Choose Manager</option>');
	// $('#foreman').find('option').remove().end().append(
	// '<option value="">Choose Foreman</option>');
	// $('#executive').find('option').remove().end().append(
	// '<option value="">Choose Executive</option>');
	// $('#purchasingAgent').find('option').remove().end().append(
	// '<option value="">Choose Purchasing Agent</option>');
	// $('#accountant').find('option').remove().end().append(
	// '<option value="">Choose Accountant</option>');
	// $('#warehouseManager').find('option').remove().end().append(
	// '<option value="">Choose Warehouse Manager</option>');
	// $('#superindent').find('option').remove().end().append(
	// '<option value="">Choose Superindent</option>');
	// // $('#estimator').find('option').remove().end().append(
	// // '<option value="">Choose Estimator</option>');
	//
	// var supervisorList = $("#supervisor");
	// var managerList = $("#manager");
	// var foremanList = $("#foreman");
	// var executiveList = $("#executive");
	// var purchasingAgentList = $("#purchasingAgent");
	// var accountantList = $("#accountant");
	// var warehouseManagerList = $("#warehouseManager");
	// var superindentList = $("#superindent");
	// var estimatorList = $("#estimator");
	// var vendorsList = $("#vendorList");
	// var customersList = $("#customerDirectory");
	// var departmentsList = $("#departmentType");
	// var contractorsList = $("#contractorDirectory");
	// for (var i = 0; i < responseData.allContractorsList.length; i++) {
	// contractorsList.append("<option value="
	// + responseData.allContractorsList[i].contractorId + ">"
	// + responseData.allContractorsList[i].contractorName
	// + "</option>");
	// }
	// for (var i = 0; i < responseData.allCustomersList.length; i++) {
	// customersList.append("<option value="
	// + responseData.allCustomersList[i].companyId + ">"
	// + responseData.allCustomersList[i].companyName + "</option>");
	// }
	// customersList.append("<option value='other'>Other</option>");
	// for (var i = 0; i < responseData.allDepartments.length; i++) {
	// departmentsList.append("<option value="
	// + responseData.allDepartments[i].departmentId + ">"
	// + responseData.allDepartments[i].departmentName + "</option>");
	// }
	// for (var i = 0; i < responseData.allVendorsList.length; i++) {
	// vendorsList.append("<option value="
	// + responseData.allVendorsList[i].vendorId + ">"
	// + responseData.allVendorsList[i].vendorName + "</option>");
	// }
	// vendorsList.append("<option value='other'>Other</option>");
	// for(var i=0; i < responseData.userList.supervisorList.length; i++){
	// lastName = "";
	// if (responseData.userList.supervisorList[i].status == "ACTIVE") {
	// if( responseData.userList.supervisorList[i].lastName != undefined &&
	// responseData.userList.supervisorList[i].lastName != null){
	// lastName = responseData.userList.supervisorList[i].lastName;
	// }
	// supervisorList.append("<option value="
	// + responseData.userList.supervisorList[i].userId + ">"
	// + responseData.userList.supervisorList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.managerList.length; i++){
	// lastName = "";
	// if (responseData.userList.managerList[i].status == "ACTIVE") {
	// if( responseData.userList.managerList[i].lastName != undefined &&
	// responseData.userList.managerList[i].lastName != null){
	// lastName = responseData.userList.managerList[i].lastName;
	// }
	// managerList.append("<option value="
	// + responseData.userList.managerList[i].userId + ">"
	// + responseData.userList.managerList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.foremanList.length; i++){
	// lastName = "";
	// if (responseData.userList.foremanList[i].status == "ACTIVE") {
	// if( responseData.userList.foremanList[i].lastName != undefined &&
	// responseData.userList.foremanList[i].lastName != null){
	// lastName = responseData.userList.foremanList[i].lastName;
	// }
	// foremanList.append("<option value="
	// + responseData.userList.foremanList[i].userId + ">"
	// + responseData.userList.foremanList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.executiveList.length; i++){
	// lastName = "";
	// if (responseData.userList.executiveList[i].status == "ACTIVE") {
	// if( responseData.userList.executiveList[i].lastName != undefined &&
	// responseData.userList.executiveList[i].lastName != null){
	// lastName = responseData.userList.executiveList[i].lastName;
	// }
	// executiveList.append("<option value="
	// + responseData.userList.executiveList[i].userId + ">"
	// + responseData.userList.executiveList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.purchasingAgentList.length; i++){
	// lastName = "";
	// if (responseData.userList.purchasingAgentList[i].status == "ACTIVE") {
	// if( responseData.userList.purchasingAgentList[i].lastName != undefined &&
	// responseData.userList.purchasingAgentList[i].lastName != null){
	// lastName = responseData.userList.purchasingAgentList[i].lastName;
	// }
	// purchasingAgentList.append("<option value="
	// + responseData.userList.purchasingAgentList[i].userId + ">"
	// + responseData.userList.purchasingAgentList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.warehouseManagerList.length; i++){
	// lastName = "";
	// if (responseData.userList.warehouseManagerList[i].status == "ACTIVE") {
	// if( responseData.userList.warehouseManagerList[i].lastName != undefined
	// &&
	// responseData.userList.warehouseManagerList[i].lastName != null){
	// lastName = responseData.userList.warehouseManagerList[i].lastName;
	// }
	// warehouseManagerList.append("<option value="
	// + responseData.userList.warehouseManagerList[i].userId + ">"
	// + responseData.userList.warehouseManagerList[i].firstName + " " +
	// lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.accountantList.length; i++){
	// lastName = "";
	// if (responseData.userList.accountantList[i].status == "ACTIVE") {
	// if( responseData.userList.accountantList[i].lastName != undefined &&
	// responseData.userList.accountantList[i].lastName != null){
	// lastName = responseData.userList.accountantList[i].lastName;
	// }
	// accountantList.append("<option value="
	// + responseData.userList.accountantList[i].userId + ">"
	// + responseData.userList.accountantList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.superIntendentList.length; i++){
	// lastName = "";
	// if (responseData.userList.superIntendentList[i].status == "ACTIVE") {
	//	
	// if( responseData.userList.superIntendentList[i].lastName != undefined &&
	// responseData.userList.superIntendentList[i].lastName != null){
	// lastName = responseData.userList.superIntendentList[i].lastName;
	// }
	// superindentList.append("<option value="
	// + responseData.userList.superIntendentList[i].userId + ">"
	// + responseData.userList.superIntendentList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for(var i=0; i < responseData.userList.estimatorList.length; i++){
	// lastName = "";
	// if (responseData.userList.estimatorList[i].status == "ACTIVE") {
	// estimatorList.append("<option value="
	// + responseData.userList.estimatorList[i].userId + ">"
	// + responseData.userList.estimatorList[i].firstName + " " + lastName
	// + "</option>");
	// }
	// }
	// for (var n = 0; n < responseData.userList.length; n++) {
	//
	// lastName = "";
	// if (responseData.userList[n].status == "ACTIVE") {
	// supervisorList.append("<option value="
	// + responseData.userList[n].userId + ">"
	// + responseData.userList[n].firstName + " " + lastName
	// + "</option>");
	// managerList.append("<option value="
	// + responseData.userList[n].userId + ">"
	// + responseData.userList[n].firstName + " " + lastName
	// + "</option>");
	// foremanList.append("<option value="
	// + responseData.userList[n].userId + ">"
	// + responseData.userList[n].firstName + " " + lastName
	// + "</option>");
	// executiveList.append("<option value="
	// + responseData.userList[n].userId + ">"
	// + responseData.userList[n].firstName + " " + lastName
	// + "</option>");
	// purchasingAgentList.append("<option value="
	// + responseData.userList[n].userId + ">"
	// + responseData.userList[n].firstName + " " + lastName
	// + "</option>");
	// }
	//
	// }
	// for(var i=0;i<userDetailsForRoles.userRolesList.length;i++){
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleId ==
	// userDetailsForRoles.loggedInRole){
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'ESTIMATOR'.toLowerCase()){
	// $("#estimator").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'SUPERVISOR'.toLowerCase()){
	// $("#supervisor").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'executive'.toLowerCase()){
	// $("#executive").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'manager'.toLowerCase()){
	// $("#manager").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'foreman'.toLowerCase()){
	// $("#foreman").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'purchasing Agent'.toLowerCase()){
	// $("#purchasingAgent").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'accountant'.toLowerCase()){
	// $("#accountant").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'warehouse Manager'.toLowerCase()){
	// $("#warehouseManager").val(userDetailsForRoles.userId);
	// }
	// if(userDetailsForRoles.userRolesList[i].userRolesId.roleName.toLowerCase()
	// == 'superindent'.toLowerCase()){
	// $("#superindent").val(userDetailsForRoles.userId);
	// }
	// }
	// }
}

function intializeProjectType(projectTypeList) {
	$('#projectTypeBean').find('option').remove().end().append(
			'<option value=""> Choose Job Type</option>');
	var jobtypeList = $("#projectTypeBean");
	projectTypeList = JSON.parse(projectTypeList);
	for (var i = 0; i < projectTypeList.length; i++) {
		jobtypeList.append("<option value=" + projectTypeList[i].projectTypeId
				+ ">" + projectTypeList[i].projectTypeName + "</option>");
	}
}
function intializevendorsList(vendorsList) {
	$('#vendorList').find('option').remove().end().append(
			'<option value=""> Choose Vendor</option>');
	var vendorsAllList = $("#vendorList");
	// console.log(vendorsList);
	vendorsList = JSON.parse(vendorsList);
	for (var i = 0; i < vendorsList.length; i++) {
		vendorsAllList.append("<option value=" + vendorsList[i].vendorId + ">"
				+ vendorsList[i].vendorName + "</option>");
	}
	vendorsAllList.append("<option value='other'>Other</option>");
}
function intializedepartmentList(departmentList) {
	$('#departmentType').find('option').remove().end().append(
			'<option value=""> Choose Department</option>');
	departmentList = JSON.parse(departmentList);
	var departmentsAllList = $("#departmentType");
	for (var i = 0; i < departmentList.length; i++) {
		departmentsAllList.append("<option value="
				+ departmentList[i].departmentId + ">"
				+ departmentList[i].departmentName + "</option>");
	}
}

function intializecustomersList(customersList) {
	$('#customerDirectory').find('option').remove().end().append(
			'<option value=""> Choose Customer</option>');
	var customersAllList = $("#customerDirectory");
	customersList = JSON.parse(customersList);
	for (var i = 0; i < customersList.length; i++) {
		customersAllList.append("<option value=" + customersList[i].companyId
				+ ">" + customersList[i].companyName + "</option>");
	}
	customersAllList.append("<option value='other'>Other</option>");
}
function intializecontractorsList(contractorsList) {
	$('#contractorDirectory').find('option').remove().end().append(
			'<option value="">Choose Contractor</option>');
	var contractorsAllList = $("#contractorDirectory");
	contractorsList = JSON.parse(contractorsList);
	for (var i = 0; i < contractorsList.length; i++) {
		contractorsAllList.append("<option value="
				+ contractorsList[i].contractorId + ">"
				+ contractorsList[i].contractorName + "</option>");
	}
}
function intializeroleBasedUserMap(roleBasedUserMap) {
	$('#supervisor').find('option').remove().end().append(
			'<option value="">Choose Supervisor</option>');
	$('#manager').find('option').remove().end().append(
			'<option value="">Choose Manager</option>');
	$('#foreman').find('option').remove().end().append(
			'<option value="">Choose Foreman</option>');
	$('#executive').find('option').remove().end().append(
			'<option value="">Choose Executive</option>');
	$('#purchasingAgent').find('option').remove().end().append(
			'<option value="">Choose Purchasing Agent</option>');
	$('#accountant').find('option').remove().end().append(
			'<option value="">Choose Accountant</option>');
	$('#warehouseManager').find('option').remove().end().append(
			'<option value="">Choose Warehouse Manager</option>');
	$('#superindent').find('option').remove().end().append(
			'<option value="">Choose Superindent</option>');
	// $('#estimator').find('option').remove().end().append(
	// '<option value="">Choose Estimator</option>');
	roleBasedUserMap = JSON.parse(roleBasedUserMap);
	var supervisorList = $("#supervisor");
	var managerList = $("#manager");
	var foremanList = $("#foreman");
	var executiveList = $("#executive");
	var purchasingAgentList = $("#purchasingAgent");
	var accountantList = $("#accountant");
	var warehouseManagerList = $("#warehouseManager");
	var superindentList = $("#superindent");
	var estimatorList = $("#estimator");
	for (var i = 0; i < roleBasedUserMap.supervisorList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.supervisorList[i].status == "ACTIVE") {
			if (roleBasedUserMap.supervisorList[i].lastName != undefined
					&& roleBasedUserMap.supervisorList[i].lastName != null) {
				lastName = roleBasedUserMap.supervisorList[i].lastName;
			}
			supervisorList.append("<option value="
					+ roleBasedUserMap.supervisorList[i].userId + ">"
					+ roleBasedUserMap.supervisorList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
	for (var i = 0; i < roleBasedUserMap.managerList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.managerList[i].status == "ACTIVE") {
			if (roleBasedUserMap.managerList[i].lastName != undefined
					&& roleBasedUserMap.managerList[i].lastName != null) {
				lastName = roleBasedUserMap.managerList[i].lastName;
			}
			managerList.append("<option value="
					+ roleBasedUserMap.managerList[i].userId + ">"
					+ roleBasedUserMap.managerList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
	for (var i = 0; i < roleBasedUserMap.foremanList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.foremanList[i].status == "ACTIVE") {
			if (roleBasedUserMap.foremanList[i].lastName != undefined
					&& roleBasedUserMap.foremanList[i].lastName != null) {
				lastName = roleBasedUserMap.foremanList[i].lastName;
			}
			foremanList.append("<option value="
					+ roleBasedUserMap.foremanList[i].userId + ">"
					+ roleBasedUserMap.foremanList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
	for (var i = 0; i < roleBasedUserMap.executiveList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.executiveList[i].status == "ACTIVE") {
			if (roleBasedUserMap.executiveList[i].lastName != undefined
					&& roleBasedUserMap.executiveList[i].lastName != null) {
				lastName = roleBasedUserMap.executiveList[i].lastName;
			}
			executiveList.append("<option value="
					+ roleBasedUserMap.executiveList[i].userId + ">"
					+ roleBasedUserMap.executiveList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
	for (var i = 0; i < roleBasedUserMap.purchasingAgentList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.purchasingAgentList[i].status == "ACTIVE") {
			if (roleBasedUserMap.purchasingAgentList[i].lastName != undefined
					&& roleBasedUserMap.purchasingAgentList[i].lastName != null) {
				lastName = roleBasedUserMap.purchasingAgentList[i].lastName;
			}
			purchasingAgentList.append("<option value="
					+ roleBasedUserMap.purchasingAgentList[i].userId + ">"
					+ roleBasedUserMap.purchasingAgentList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
	for (var i = 0; i < roleBasedUserMap.warehouseManagerList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.warehouseManagerList[i].status == "ACTIVE") {
			if (roleBasedUserMap.warehouseManagerList[i].lastName != undefined
					&& roleBasedUserMap.warehouseManagerList[i].lastName != null) {
				lastName = roleBasedUserMap.warehouseManagerList[i].lastName;
			}
			warehouseManagerList.append("<option value="
					+ roleBasedUserMap.warehouseManagerList[i].userId + ">"
					+ roleBasedUserMap.warehouseManagerList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
	for (var i = 0; i < roleBasedUserMap.accountantList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.accountantList[i].status == "ACTIVE") {
			if (roleBasedUserMap.accountantList[i].lastName != undefined
					&& roleBasedUserMap.accountantList[i].lastName != null) {
				lastName = roleBasedUserMap.accountantList[i].lastName;
			}
			accountantList.append("<option value="
					+ roleBasedUserMap.accountantList[i].userId + ">"
					+ roleBasedUserMap.accountantList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
	for (var i = 0; i < roleBasedUserMap.superIntendentList.length; i++) {
		lastName = "";
		if (roleBasedUserMap.superIntendentList[i].status == "ACTIVE") {

			if (roleBasedUserMap.superIntendentList[i].lastName != undefined
					&& roleBasedUserMap.superIntendentList[i].lastName != null) {
				lastName = roleBasedUserMap.superIntendentList[i].lastName;
			}
			superindentList.append("<option value="
					+ roleBasedUserMap.superIntendentList[i].userId + ">"
					+ roleBasedUserMap.superIntendentList[i].firstName + " "
					+ lastName + "</option>");
		}
	}
}

var responseData = "";
function getActiveJobDetails() {
	$.ajax({
		url : "getAllJobDetails",
		type : "POST",
		contentType : 'text/plain',
		success : function(result) {
			jobDetailsTable.fnClearTable();
			pendingjobDetailsTable.fnClearTable();
			responseData = JSON.parse(result);
			total = responseData.allJobsList.length;
			$("#totalUsers").html(total);
			initializeAllSelectBox(responseData);
			addJobsDataToTable(responseData);
		},
		error : function() {
			console.log("error");
		}
	});
}
var documentsList = "";
function getAllJobDocuments(id) {
//	console.log(id);
	$.ajax({
		url : "getDocuments",
		type : "GET",
		data : {
			jobId : id
		},
		contentType : 'text/plain',
		success : function(result) {
			documentsList = JSON.parse(result);
//			console.log(documentsList);
			if ($("#jobId").val() != "") {
				showFileLists($("#jobId").val());
			}
		},
		error : function() {
			console.log("error");
		}
	});
}
function saveUpdateJobDetails() {
	if (!checkPreviousTabs()) {
//		$('#addressErr').css("display", "block");
		gritterForErrorMsgs("Mandatory fields in previous tabs are empty..Enter those fields first");
	} else {
		var laddaRef = null;
		if ($("#jobAction").val() == "save"
				|| $("#jobAction").val() == "update") {
			laddaRef = Ladda.create($("#saveJobBtn")[0]);
		} else {
			laddaRef = Ladda.create($("#saveAndContinueJobBtn")[0]);
		}
		try {
			laddaRef.start();
		} catch (e) {

		}

		var jobForm = $("#manageJobForm").serialize();
		$
				.ajax({
					url : "saveUpdateJobDetails",
					type : "POST",
					// contentType : 'text/plain',
					data : jobForm,
					success : function(data) {
						data = JSON.parse(data);
						try {
							laddaRef.stop();
						} catch (e) {

						}
						// data = JSON.parse(data);
						getActiveJobDetails();
						// getAllJobDocuments();
						if (data.ajaxResult == "success") {
							if ($("#jobAction").val() == "save"
									|| $("#jobAction").val() == "saveAndContinue") {
								gritterForSucessMsgs("A record of job Detail has been saved successfully.");
							} else
								gritterForSucessMsgs("A record of Job Detail has been "
										+ $("#jobAction").val()
										+ "d successfully.");
							saveAddressForJob(data.reason);
						} else {
							gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
									+ data.reason);
						}
						if ($("#jobAction").val() == "save"
								|| $("#jobAction").val() == "update") {
							$("#jobDetailModal").modal("hide");
						}
						resetJobDetailForm();
					},
					error : function(err) {
						try {
							laddaRef.stop();
						} catch (e) {

						}
						$("#jobDetailModal").modal("hide");
						resetJobDetailForm();
						console.log("Error occured.");
					}
				});
	}
}
function saveAddressForJob(jobId) {
	var addressData = {};
	var address = [];
	addressData.moduleId = jobId;
	addressData.moduleName = "Job_Setup";
	address.push(addressArray);
	addressData.address = address;
	var module = '{"addressData" : ' + JSON.stringify(addressData) + '}';
	$
			.ajax({
				url : "saveAddressDetails",
				data : module,
				type : 'POST',
				contentType : 'application/json',
				success : function(data) {
					data = JSON.parse(data);
					// $("#" + name).val("");
					// getAllJobDocuments(jobId);
					if (data.ajaxResult != "success") {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ data.reason);
					}
				},
				error : function() {
					alert("Some problem occured");
				}
			});
}
function uploadDocuments(name) {
	var jobId = $("#jobId").val();
	$("#attachmentsId").val(jobId);
	$("#attachmentsName").val(name);
	var files = $("#" + name)[0].files;
	$("#attachments")[0].files = files
	var data = new FormData($("#attachmentsForm")[0]);
	$
			.ajax({
				url : 'uploadJobDocuments',
				data : data,
				cache : false,
				contentType : false,
				processData : false,
				type : 'POST',
				success : function(data) {
					data = JSON.parse(data);
					$("#" + name).val("");
					getAllJobDocuments(jobId);
					if (data.ajaxResult != "success") {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ data.reason);
					}else{
						gritterForSucessMsgs("File successfully uploaded !!")
					}
				},
				error : function() {
					alert("Some problem occured");
				}
			});
}

function setAction(action) {

	if ($("#jobAction").val() == "")
		$("#jobAction").val(action);

}
function addJobsDataToTable(responseData) {
	var count = 0;

	var closedJobsCount = 0;
	var pendingCount = 0;
	var inactiveCount = 0;
	for (var i = 0; i < responseData.allJobsList.length; i++) {
		var startDate, endDate, coDate, extendedDate, manager, foreman, supervisor;
		startDate = endDate = coDate = extendedDate = manager = foreman = supervisor = "";
		if (responseData.allJobsList[i].startDate != undefined
				|| responseData.allJobsList[i].startDate != null)
			startDate = responseData.allJobsList[i].startDate;
		else
			startDate = "";
		if (responseData.allJobsList[i].endDate != undefined
				|| responseData.allJobsList[i].endDate != null)
			endDate = responseData.allJobsList[i].endDate;
		else
			endDate = "";
		if (responseData.allJobsList[i].coDate != undefined
				|| responseData.allJobsList[i].coDate != null)
			coDate = responseData.allJobsList[i].coDate;
		else
			coDate = "";
		if (responseData.allJobsList[i].extendedDate != undefined
				|| responseData.allJobsList[i].extendedDate != null)
			extendedDate = responseData.allJobsList[i].extendedDate;
		else
			extendedDate = "";

		if (responseData.allJobsList[i].supervisor != null
				|| responseData.allJobsList[i].supervisor != undefined) {
			supervisor = responseData.allJobsList[i].supervisor.firstName
					+ " " + responseData.allJobsList[i].supervisor.lastName;
		}
		if (responseData.allJobsList[i].foreman != null
				|| responseData.allJobsList[i].foreman != undefined) {
			foreman = responseData.allJobsList[i].foreman.firstName + " "
					+ responseData.allJobsList[i].foreman.lastName;
		}
		if (responseData.allJobsList[i].manager != null
				|| responseData.allJobsList[i].manager != undefined) {
			manager = responseData.allJobsList[i].manager.firstName + " "
					+ responseData.allJobsList[i].manager.lastName;
		}
		if (responseData.allJobsList[i].status == "ACTIVE") {
			
			jobDetailsTable.fnAddData([ count + 1,
					responseData.allJobsList[i].jobName, startDate, endDate,
					coDate, extendedDate, manager, supervisor, foreman,
					responseData.allJobsList[i].jobId,
					responseData.allJobsList[i].jobNumber,
					responseData.allJobsList[i].bidNumber ]);
			count++;
//			if(responseData.allJobsList[i].status == "INACTIVE"){
//				inactiveCount++;
//			}
		} else if (responseData.allJobsList[i].status == "CLOSED") {
			
			jobDetailsTable.fnAddData([ count + 1,
					responseData.allJobsList[i].jobName, startDate, endDate,
					coDate, extendedDate, manager, supervisor, foreman,
					responseData.allJobsList[i].jobId,
					responseData.allJobsList[i].jobNumber,
					responseData.allJobsList[i].bidNumber ]);
			closedJobsCount++;
		}
		else if (responseData.allJobsList[i].status == "PENDING") {
			
			pendingjobDetailsTable.fnAddData([ pendingCount + 1,
					responseData.allJobsList[i].jobName, startDate, endDate,
					coDate, extendedDate, manager, supervisor, foreman,
					responseData.allJobsList[i].jobId,
					responseData.allJobsList[i].jobNumber,
					responseData.allJobsList[i].bidNumber ]);
			pendingCount++;
		}
	}

	materialCostTable.fnAddData([ 1, 4, "data", "data", "data", "data", 12 ]);

	$("#activeUsers")
			.html(
					"<i class='fa fa-thumbs-o-up'data-rel='bootstrap-tooltip' title='Increased'></i>&nbsp;"
							+ (count - inactiveCount));
	$("#inactiveUsers").html(
			"<i class='fa fa-thumbs-o-down'></i>&nbsp;"
					+ (pendingCount));

	$("#closedUsers").html(
			"<i class='fa fa-times'></i>&nbsp;" + (closedJobsCount));
}

// updation of job details
function updateJobDetails(id) {
	$("#modalTitleIdForJobs").html(
			"<i class='fa fa-suitcase txt-color-blue'>&nbsp;</i> Update Job");
	$("#jobDetailModal").modal("show");
	$("#saveAndContinueJobBtn").hide();
	$("#saveJobBtn").html(
			"<i class='fa fa-thumbs-up bigger-110'></i> &nbsp; Update");
	$(".updateStyles").removeClass("disableStyles");
	$('#coDateLb').removeClass('pointerEvent');
	$('#extendedDateLb').removeClass('pointerEvent');
	//$('#endDateLb').addClass('pointerEvent');
	
	for (var i = 0; i < responseData.allJobsList.length; i++) // all
	// dropdownlists
	// must be added
	{
		if (id == responseData.allJobsList[i].jobId) {
			$("#jobId").val(id);
			$("#sovType").val(responseData.allJobsList[i].sovType);
			$("#jobName").val(responseData.allJobsList[i].jobName);
			$("#jobNumber").val(responseData.allJobsList[i].jobNumber);
			$("#jobAction").val("update");
			$("#status").val(responseData.allJobsList[i].status);
			$("#jobAddress").val(responseData.allJobsList[i].jobAddress);
			$("#startDate").val(responseData.allJobsList[i].startDate);
			$("#endDate").val(responseData.allJobsList[i].endDate);
			$("#coDate").val(responseData.allJobsList[i].coDate);
			$("#extendedDate").val(responseData.allJobsList[i].extendedDate);
			$('#coDate').removeAttr("tabindex");
			$('#extendedDate').removeAttr("tabindex");
			$("#description").val(responseData.allJobsList[i].description);
			$("#bidPrice").val(
					numberFormat(responseData.allJobsList[i].bidPrice));
			$("#bidNumber").val(responseData.allJobsList[i].bidNumber);
			$("#estimator").val(responseData.allJobsList[i].estimator);
			$("#originalContractValue").val(
					responseData.allJobsList[i].originalContractValue);
			$("#reportMargin").val(responseData.allJobsList[i].reportMargin);
			$("#performanceTargetMargin").val(
					responseData.allJobsList[i].performanceTargetMargin);
			$("#laborBonusTargetOverBudget").val(
					responseData.allJobsList[i].laborBonusTargetOverBudget);
			$("#autoApprovalLimit").val(
					responseData.allJobsList[i].autoApprovalLimit);
			$("#collectionDayOut").val(
					responseData.allJobsList[i].collectionDayOut);
			$("#retentionDayOut").val(
					responseData.allJobsList[i].retentionDayOut);
			$("#retentionPercent").val(
					responseData.allJobsList[i].retentionPercent);
			$("#materialDayOut")
					.val(responseData.allJobsList[i].materialDayOut);
			$("#subcontractorDayOut").val(
					responseData.allJobsList[i].subcontractorDayOut);
			$("#directJobDayOut").val(
					responseData.allJobsList[i].directJobDayOut);
			$("#rentalEquipmentDayOut").val(
					responseData.allJobsList[i].rentalEquipmentDayOut);
			$("#ownedEquipmentDayOut").val(
					responseData.allJobsList[i].ownedEquipmentDayOut);
			$("#projAdminDayOut").val(
					responseData.allJobsList[i].projAdminDayOut);
			$("#laborDayOut").val(responseData.allJobsList[i].laborDayOut);
			$("#activationValidityTimePeriod").val(
					responseData.allJobsList[i].activationValidityTimePeriod);
			$("#indirectDayOut")
					.val(responseData.allJobsList[i].indirectDayOut);
			$("#bidBudgetMaterialCost")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetMaterialCost));
			$("#operationsBudgetMaterialCosts")
					.val(
							numberFormat(responseData.allJobsList[i].operationsBudgetMaterialCosts));
			$("#bidBudgetSubcontractorsCost")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetSubcontractorsCost));
			$("#operationsSubcontractorsCosts")
					.val(
							numberFormat(responseData.allJobsList[i].operationsSubcontractorsCosts));
			$("#bidBudgetDirectJobCosts")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetDirectJobCosts));
			$("#operationsBudgetDirectJobCost")
					.val(
							numberFormat(responseData.allJobsList[i].operationsBudgetDirectJobCost));
			$("#bidBudgetRentalEquipmentCosts")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetRentalEquipmentCosts));
			$("#operationsBudgetRentalEquipmentCost")
					.val(
							numberFormat(responseData.allJobsList[i].operationsBudgetRentalEquipmentCost));
			$("#bidBudgetOwnedEquipmentsCosts")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetOwnedEquipmentsCosts));
			$("#operationsOwnedEquipmentCost")
					.val(
							numberFormat(responseData.allJobsList[i].operationsOwnedEquipmentCost));
			$("#bidBudgetProjectAdministrationCost")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetProjectAdministrationCost));
			$("#operationsProjectAdministrationCost")
					.val(
							numberFormat(responseData.allJobsList[i].operationsProjectAdministrationCost));
			$("#bidBudgetLaborCost")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetLaborCost));
			$("#operationsBudgetLaborCost")
					.val(
							numberFormat(responseData.allJobsList[i].operationsBudgetLaborCost));
			$("#bidBudgetIndirectExpenses")
					.val(
							numberFormat(responseData.allJobsList[i].bidBudgetIndirectExpenses));
			$("#operationsIndirectExpenses")
					.val(
							numberFormat(responseData.allJobsList[i].operationsIndirectExpenses));
			$("#insuranceSentDate").val(
					responseData.allJobsList[i].insuranceSentDate);
			$("#performanceSentDate").val(
					responseData.allJobsList[i].performanceSentDate);
			$("#cpnSentDate").val(responseData.allJobsList[i].cpnSentDate);
			$("#noticeReceivedDate").val(
					responseData.allJobsList[i].noticeReceivedDate);
			$("#typeOfContract")
					.val(responseData.allJobsList[i].typeOfContract);
			$("#otherTypeOfContract").val(
					responseData.allJobsList[i].otherTypeOfContract);
			$("#contractAmount").val(
					numberFormat(responseData.allJobsList[i].contractAmount));
			$("#contractNumber")
					.val(responseData.allJobsList[i].contractNumber);
			if (responseData.allJobsList[i].isCertifiedPayroll) {
				$("#isCertifiedPayroll").prop("checked", true);
			}
			if (responseData.allJobsList[i].ownerControlledInsuranceProg) {
				$("#ownerControlledInsuranceProg").prop("checked", true);
			}
			if (responseData.allJobsList[i].status == null
					|| responseData.allJobsList[i].status == undefined) {
				$("#status").val("");
			} else {

				$("#status").val(responseData.allJobsList[i].status);
			}
			if (responseData.allJobsList[i].customerDirectory == null
					|| responseData.allJobsList[i].customerDirectory == undefined) {
				$("#customerDirectory").val("");
			} else {

				$("#customerDirectory")
						.val(
								responseData.allJobsList[i].customerDirectory.companyId);
			}
			if (responseData.allJobsList[i].projectTypeBean == null
					|| responseData.allJobsList[i].projectTypeBean == undefined) {
				$("#projectTypeBean").val("");
			} else {
				$("#projectTypeBean")
						.val(
								responseData.allJobsList[i].projectTypeBean.projectTypeId);
			}
			if (responseData.allJobsList[i].departmentType == null
					|| responseData.allJobsList[i].departmentType == undefined) {
				$("#departmentType").val("");
			} else {
				$("#departmentType")
						.val(
								responseData.allJobsList[i].departmentType.departmentId);
			}

			if (responseData.allJobsList[i].vendorList == null
					|| responseData.allJobsList[i].vendorList == undefined) {

				$("#vendorList").val("");

			} else {

				$("#vendorList").val(
						responseData.allJobsList[i].vendorList.vendorId);

			}
			if (responseData.allJobsList[i].contractorDirectory == null
					|| responseData.allJobsList[i].contractorDirectory == undefined) {

				$("#contractorDirectory").val("");
			} else {

				$("#contractorDirectory")
						.val(
								responseData.allJobsList[i].contractorDirectory.contractorId);
			}

			if (responseData.allJobsList[i].supervisor == null
					|| responseData.allJobsList[i].supervisor == undefined) {

				$("#supervisor").val("");
			} else {

				$("#supervisor").val(
						responseData.allJobsList[i].supervisor.userId);
			}

			if (responseData.allJobsList[i].foreman == null
					|| responseData.allJobsList[i].foreman == undefined) {

				$("#foreman").val("");
			} else {
				$("#foreman").val(responseData.allJobsList[i].foreman.userId);
			}

			if (responseData.allJobsList[i].manager == null
					|| responseData.allJobsList[i].manager == undefined) {

				$("#manager").val("");
			} else {
				$("#manager").val(responseData.allJobsList[i].manager.userId);
			}

			if (responseData.allJobsList[i].executive == null
					|| responseData.allJobsList[i].executive == undefined) {

				$("#executive").val("");
			} else {
				$("#executive").val(
						responseData.allJobsList[i].executive.userId);
			}

			if (responseData.allJobsList[i].purchasingAgent == null
					|| responseData.allJobsList[i].purchasingAgent == undefined) {

				$("#purchasingAgent").val("");
			} else {
				$("#purchasingAgent").val(
						responseData.allJobsList[i].purchasingAgent.userId);
			}
			if (responseData.allJobsList[i].warehouseManager == null
					|| responseData.allJobsList[i].warehouseManager == undefined) {

				$("#warehouseManager").val("");
			} else {
				$("#warehouseManager").val(
						responseData.allJobsList[i].warehouseManager.userId);
			}
			// if (responseData.allJobsList[i].accountant == null
			// || responseData.allJobsList[i].accountant == undefined) {
			//
			// $("#accountant").val("");
			// } else {
			// $("#accountant").val(
			// responseData.allJobsList[i].accountant.userId);
			// }
			// if (responseData.allJobsList[i].superindent == null
			// || responseData.allJobsList[i].superindent == undefined) {
			//
			// $("#superindent").val("");
			// } else {
			// $("#superindent").val(
			// responseData.allJobsList[i].superindent.userId);
			// }
			if ($('#jobNumber').val() != "") {
				$('#jobNumber').prop("readonly",true);
				$("#coDate").prop("disabled", false);
				$("#extendedDate").prop("disabled", false);
			} else {
				$("#coDate").prop("disabled", true);
				$("#extendedDate").prop("disabled", true);
			}
			$("#otherDocuments").prop("disabled", false);
			$("#californiaPreliminaryNotice").prop("disabled", false);
			$("#paymentBond").prop("disabled", false);
			$("#insuranceCertificate").prop("disabled", false);
			$("#bidCommunications").prop("disabled", false);
			$("#transmittalSheets").prop("disabled", false);
			$("#communications").prop("disabled", false);
			$("#drawingLog").prop("disabled", false);
			$("#submittals").prop("disabled", false);
			$("#rfi").prop("disabled", false);
			$("#executedContract").prop("disabled", false);
			$("#noticeToProceed").prop("disabled", false);
			
			$("#otherDocuments").parent().parent().attr("title", "");
			$("#californiaPreliminaryNotice").parent().parent().attr("title", "");
			$("#paymentBond").parent().parent().attr("title", "");
			$("#insuranceCertificate").parent().parent().attr("title", "");
			$("#bidCommunications").parent().parent().attr("title", "");
			$("#transmittalSheets").parent().parent().attr("title", "");
			$("#communications").parent().parent().attr("title", "");
			$("#drawingLog").parent().parent().attr("title", "");
			$("#submittals").parent().parent().attr("title", "");
			$("#rfi").parent().parent().attr("title", "");
			$("#executedContract").parent().parent().attr("title", "");
			$("#noticeToProceed").parent().parent().attr("title", "");
			$("#ownerControlledInsuranceProgDocuments").parent().parent().attr("title", "");
			$("#downloadDocumentsId").prop("disabled", false);
			break;
		}
	}
	// showFileLists(id);
	getAllJobDocuments(id);
	getAddressDetails(id);
	showDefaultHighlights("update");
}
function showFileLists(id) {
	$(".noticeToProceedFileLists").empty();
	$(".exectedContractFileLists").empty();
	$(".rfiFileLists").empty();
	$(".otherDocumentsFileLists").empty();
	$(".submittalsFileLists").empty();
	$(".drawingLogFileLists").empty();
	$(".communicationsFileLists").empty();
	$(".transmittalsSheetsFileLists").empty();
	$(".bidDocumentsFileLists").empty();
	$(".insuranceCertificateFileLists").empty();
	$(".paymentBondFileLists").empty();
	$(".californiaPreliminaryNoticeFileLists").empty();
	$(".ownerControlledInsuranceProgDocumentsFileLists").empty();
	if (documentsList.noticeToProceed != undefined) {
		for (var i = 0; i < documentsList.noticeToProceed.length; i++) {
			var fileName = documentsList.noticeToProceed[i].fileName;
			var docId = documentsList.noticeToProceed[i].fileId;
			var jobfieldName = "noticeToProceed";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".noticeToProceedFileLists").append(file);
		}
		$("#noticeErr").hide();
	}
	if (documentsList.executedContract != undefined) {
		for (var i = 0; i < documentsList.executedContract.length; i++) {
			var fileName = documentsList.executedContract[i].fileName;
			var docId = documentsList.executedContract[i].fileId;
			var jobfieldName = "executedContract";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".exectedContractFileLists").append(file);
		}
		$("#exectedContractErr").hide();
	}
	if (documentsList.rfi != undefined) {
		for (var i = 0; i < documentsList.rfi.length; i++) {
			var fileName = documentsList.rfi[i].fileName;
			var docId = documentsList.rfi[i].fileId;
			var jobfieldName = "rfi";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".rfiFileLists").append(file);
		}
	}
	if (documentsList.submittals != undefined) {
		for (var i = 0; i < documentsList.submittals.length; i++) {
			var fileName = documentsList.submittals[i].fileName;
			var docId = documentsList.submittals[i].fileId;
			var jobfieldName = "submittals";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".submittalsFileLists").append(file);
		}
	}
	if (documentsList.drawingLog != undefined) {
		for (var i = 0; i < documentsList.drawingLog.length; i++) {
			var fileName = documentsList.drawingLog[i].fileName;
			var docId = documentsList.drawingLog[i].fileId;
			var jobfieldName = "drawingLog";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".drawingLogFileLists").append(file);
		}
	}
	if (documentsList.communications != undefined) {
		for (var i = 0; i < documentsList.communications.length; i++) {
			var fileName = documentsList.communications[i].fileName;
			var docId = documentsList.communications[i].fileId;
			var jobfieldName = "communications";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".communicationsFileLists").append(file);
		}
	}
	if (documentsList.transmittalSheets != undefined) {
		for (var i = 0; i < documentsList.transmittalSheets.length; i++) {
			var fileName = documentsList.transmittalSheets[i].fileName;
			var docId = documentsList.transmittalSheets[i].fileId;
			var jobfieldName = "transmittalSheets";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".transmittalsSheetsFileLists").append(file);
		}
	}
	if (documentsList.bidCommunications != undefined) {
		for (var i = 0; i < documentsList.bidCommunications.length; i++) {
			var fileName = documentsList.bidCommunications[i].fileName;
			var docId = documentsList.bidCommunications[i].fileId;
			var jobfieldName = "bidCommunications";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".bidDocumentsFileLists").append(file);
		}
	}
	if (documentsList.otherDocuments != undefined) {
		for (var i = 0; i < documentsList.otherDocuments.length; i++) {
			var fileName = documentsList.otherDocuments[i].fileName;
			var docId = documentsList.otherDocuments[i].fileId;
			var jobfieldName = "otherDocuments";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".otherDocumentsFileLists").append(file);
		}
	}
	if (documentsList.insuranceCertificate != undefined) {
		for (var i = 0; i < documentsList.insuranceCertificate.length; i++) {
			var fileName = documentsList.insuranceCertificate[i].fileName;
			var docId = documentsList.insuranceCertificate[i].fileId;
			var jobfieldName = "insuranceCertificate";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".insuranceCertificateFileLists").append(file);
		}
		$("#insuranceErr").hide();
	}
	if (documentsList.paymentBond != undefined) {
		for (var i = 0; i < documentsList.paymentBond.length; i++) {
			var fileName = documentsList.paymentBond[i].fileName;
			var docId = documentsList.paymentBond[i].fileId;
			var jobfieldName = "paymentBond";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".paymentBondFileLists").append(file);
		}
	}
	if (documentsList.californiaPreliminaryNotice != undefined) {
		for (var i = 0; i < documentsList.californiaPreliminaryNotice.length; i++) {
			var fileName = documentsList.californiaPreliminaryNotice[i].fileName;
			var docId = documentsList.californiaPreliminaryNotice[i].fileId;
			var jobfieldName = "californiaPreliminaryNotice";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".californiaPreliminaryNoticeFileLists").append(file);
		}
		$("#cpnErr").hide();
	}
	if (documentsList.ownerControlledInsuranceProgDocuments != undefined) {
		for (var i = 0; i < documentsList.ownerControlledInsuranceProgDocuments.length; i++) {
			var fileName = documentsList.ownerControlledInsuranceProgDocuments[i].fileName;
			var docId = documentsList.ownerControlledInsuranceProgDocuments[i].fileId;
			var jobfieldName = "ownerControlledInsuranceProgDocuments";
			var file = "<section class='col col-lg-12'><p style='border: 1px solid #ccc;margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Download Document' href='javascript:void(0)' onclick='setDocumentToDowload("
					+ docId
					+ ")' \>"
					+ fileName
					+ "</a><i title='Delete Document' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setDocumentToDelete("
					+ docId + "," + id + ","+jobfieldName +");'></i></p></section>";
			$(".ownerControlledInsuranceProgDocumentsFileLists").append(file);
		}
	}
	showDefaultHighlights("update");
}
function setJobId(id) {
	$("#jobId").val(id);
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This Job will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							deleteJobController();
						}
					});
}

function deleteAddress(jobId) {
//	console.log(jobId);
	$.ajax({
		url : "deleteAddress",
		method : "POST",
		data : {
			moduleId : jobId
		},
		success : function(result) {
			cosole.log(result);
		}
	});

}
function deleteJobController() {
	var jobIdVal = $("#jobId").val();
	var dataObj = {
		jobId : jobIdVal
	};
	$
			.ajax({
				url : "deleteJobDetails",
				method : "POST",
				data : dataObj,
				success : function(result) {
					var resultant = JSON.parse(result);

					$("#jobDetailModal").modal("hide");
					resetJobDetailForm();
					getActiveJobDetails();
					getAllJobDocuments(jobIdVal);
					if (resultant.ajaxResult == "success") {
						gritterForSucessMsgs("A record of job Detail has been deleted successfully.");
						deleteAddress(jobIdVal);

					} else {

						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ resultant.reason);
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team."
							+ textStatus
							+ "------------------------------------------"
							+ errorThrown);
				}
			});

}

function resetJobUploadForm() {
	$("#confirmJobUploadId").val("");
	$("#jobFileName").val("");
}

$.validator.addMethod("checkSize", function(value, element) {

	var size = element.files[0].size;
	$("#confirmJobUploadId").val("");
	$("#jobFileName").val("");

	if (size > 15728640) {
		return false;
	} else {
		return true;
	}
}, "Please upload a file with size less than 15 MB");

$("#jobUploadForm").validate({
	rules : {
		jobTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		jobTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		form.submit();
		ajaxCallForAddJobTemplate();
	}
});

// function ajaxCallForAddJobTemplate(){
// var msg="";
// $
// .ajax({
// url : 'jobTemplateController',
// data : new FormData($("#jobUploadForm")[0]),
// cache : false,
// contentType : false,
// processData : false,
// type : 'POST',
// success : function(data) {
// $("#jobUploadModal").modal("hide");
// resultForJobUpload = JSON.parse(data);
// if(resultForJobUpload.ajaxResult =="error"){
// resetJobUploadForm();
// var out = document.getElementById("jobErrorBlock");
// out.innerHTML = "";
// if(resultForJobUpload.reason[0].colNumber == -1){
// for(i=0;i<resultForJobUpload.reason.length;i++){
// out.appendChild(document.createTextNode("Row :
// "+resultForJobUpload.reason[i].rowNumber+" Error
// :"+resultForJobUpload.reason[i].errorMessage));
// out.appendChild(document.createElement("br"));
// }
// $("#jobConfirmationHeader").hide();
// $("#saveJobConfirmUpload").hide();
// $("#cancelJobUpload").html("Close");
// getActiveJobDetails();
// }
// else if(resultForJobUpload.reason[0].excelCell == "A0"){
// for(i=0;i<resultForJobUpload.reason.length;i++){
// out.appendChild(document.createTextNode(" Error
// :"+resultForJobUpload.reason[i].errorMessage));
// out.appendChild(document.createElement("br"));
// }
// $("#saveJobConfirmUpload").hide();
// }
// else{
// for(i=0;i<resultForJobUpload.reason.length;i++){
// out.appendChild(document.createTextNode(" Cell Address
// :"+resultForJobUpload.reason[i].excelCell+" Error
// :"+resultForJobUpload.reason[i].errorMessage));
// out.appendChild(document.createElement("br"));
// }
// }
// $("#jobUploadConfirmation").modal("show");
// $("#jobErrorSection").css('display','block');
// }
// else if(resultForJobUpload.ajaxResult == "success"){
// gritterForSucessMsgs("A file of job type has been successfully added");
// resetJobUploadForm();
// getActiveJobDetails();
// cancelJobUploadForm();
// }
// else if(resultForJobUpload.ajaxResult == "failure"){
// gritterForErrorMsgs("An error occurred : "+ resultForJobUpload.reason);
// }
// else{
// gritterForErrorMsgs("Could not be saved.Contact dev");
// }
// },
// error : function() {
// alert("Some problem occured");
// }
// });
// }
//
// function setJobUploadConfirmation(buttonId){
//	 
// $("#jobUploadConfirmation").modal("hide");
// if(buttonId == "saveJobConfirmUpload"){
// $("#confirmJobUploadId").val(1);
// ajaxCallForAddJobTemplate();
// }
// else
// $("#confirmJobUploadId").val(-1);
// }
//
// function resetJobConfirmation(){
// $("#saveJobConfirmUpload").show();
// $("#jobConfirmationHeader").show();
// $("#cancelJobUpload").html("Cancel");
// $("#jobUploadConfirmation").modal("hide");
// }
//
// function cancelJobUploadForm(){
// var validator = $("#jobUploadForm").validate();
// validator.resetForm();
// $('input').val('');
// }
//
// function setJobUploadFileName(filePath){
// $("#jobFileName").val(filePath);
// };
// method for duplicating the project and inserting respective data into MPR
function setJobToDuplicate(jobIdToDuplicateTemp, jobStatus) {

	if (jobStatus == "DUPLICATE") {
		gritterForInfoMsgs("This is already a duplicate project. You cannot duplicate it again.");
	} else if (jobStatus == "CLOSED") {
		gritterForInfoMsgs("This project is CLOSED, you cannot duplicate it.");
	} else {
		$
				.SmartMessageBox(
						{
							title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
							content : "<br>This will be a dummy project and will not be considered in Executive's Dashboard and Report Generation.<br>"
									+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to duplicate this project ?</b>",
							buttons : '[No][Yes]'
						},
						function(ButtonPressed) {
							if (ButtonPressed === "Yes") {
								var dataObj = {
									jobIdToDuplicate : jobIdToDuplicateTemp
								};
								$
										.ajax({
											url : "setJobToDuplicate",
											method : "POST",
											data : dataObj,
											success : function(result) {
												var resultant = JSON
														.parse(result);
												getActiveJobDetails();
												getAllJobDocuments(jobIdToDuplicate);
												if (resultant.ajaxResult == "success") {

													gritterForSucessMsgs("A record of job Detail has been duplicated successfully.");

												} else {

													gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
															+ resultant.reason);
												}

											},
											error : function(jqXHR, textStatus,
													errorThrown) {
												gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team."
														+ textStatus
														+ "------------------------------------------"
														+ errorThrown);
											}
										});
							}
						});
		// bootbox.confirm(
		// "This will be a dummy project and will not be considered in
		// Executive's Dashboard and Report Generation. Are you sure you want to
		// duplicate this project?",
		// function(result) {
		//
		// console.log(result);
		// if (result) {

	}
}
$(document).ready(
		function() {
			$(".clickTiHide").click(function() {
				$(".clickTiHide").toggleClass("fa-minus-circle");
				$("#addressInfo").toggle();
			});

			$(".editable-cancel").click(function() {
				$("#addressInfo").toggle();
				$(".clickTiHide").toggleClass("fa-minus-circle");
			});
			$(".documents").removeClass("hide");
			$('#ownerControlledInsuranceProg').change(
					function() {
						if ($('#ownerControlledInsuranceProg').is(':checked')) {
							$("#ownerControlledInsuranceProgDocuments").prop(
									"disabled", false);
						} else {
							$("#ownerControlledInsuranceProgDocuments").prop(
									"disabled", true);
						}
					});
			$("#downloadDocumentsId").click(function() {
				bulkDownload();
			});
			$(".otherDocumentsIcon").click(function() {
				$("#otherDocuments").trigger('click');
			});
			$(".ownerControlledInsuranceProgDocumentsIcon").click(function() {
				$("#ownerControlledInsuranceProgDocuments").trigger('click');
			});
			$(".californiaPreliminaryNoticeIcon").click(function() {
				$("#californiaPreliminaryNotice").trigger('click');
			});
			$(".paymentBondIcon").click(function() {
				$("#paymentBond").trigger('click');
			});
			$(".insuranceCertificateIcon").click(function() {
				$("#insuranceCertificate").trigger('click');
			});
			$(".bidCommunicationsIcon").click(function() {
				$("#bidCommunications").trigger('click');
			});
			$(".transmittalSheetsIcon").click(function() {
				$("#transmittalSheets").trigger('click');
			});
			$(".communicationsIcon").click(function() {
				$("#communications").trigger('click');
			});
			$(".drawingLogIcon").click(function() {
				$("#drawingLog").trigger('click');
			});
			$(".submittalsIcon").click(function() {
				$("#submittals").trigger('click');
			});
			$(".rfiIcon").click(function() {
				$("#rfi").trigger('click');
			});
			$(".executedContractIcon").click(function() {
				$("#executedContract").trigger('click');
			});
			$(".noticeToProceedIcon").click(function() {
				$("#noticeToProceed").trigger('click');
			});

			$("#typeOfContract").on("change", function(e) {
				var value = $("#typeOfContract").val();
				if (value != "" && value == "Other") {
					$("#otherTypeOfContract").prop("disabled", false);
					$("#otherTypeOfContract").css("background", "none");
				} else {
					$("#otherTypeOfContract").prop("disabled", true);
					$("#otherTypeOfContract").css("background", "#F2F2F2");
				}
			});

			$('.numbers').on('keyup', function(event) {
				var value = $(this).val();
				if (value != "") {
					if (event.which >= 37 && event.which <= 40) {
						event.preventDefault();
					}
					var newvalue = value.replace(/,/g, '');
					var valuewithcomma = Number(newvalue).toLocaleString('en');
					// if(valuewithcomma == "NaN"){
					// $(this).val("");
					// }
					// else{
					// $(this).val(valuewithcomma);
					// }
					$(this).val(valuewithcomma);
				}
			});
			removeHighlights();
			// changeTabValue();
		});
function numberFormat(value) {
	var valuewithcomma = value;
	if (valuewithcomma != "" && valuewithcomma != undefined) {
		// console.log(valuewithcomma);
		valuewithcomma = Number(valuewithcomma).toLocaleString('en');
	}
	return valuewithcomma;
}
function showDefaultHighlights(action) {
	// console.log(userDetailsForRoles);
	// $("#personnelTab").parent().addClass("initialSettings");
	// $("#legalTab").parent().addClass("initialSettings");
	// $("#settingsTab").parent().addClass("initialSettings");
	// $("#budgetTab").parent().addClass("initialSettings");
	for (var i = 0; i < userDetailsForRoles.userRolesList.length; i++) {
		if (userDetailsForRoles.userRolesList[i].userRolesId.roleId == userDetailsForRoles.loggedInRole) {
			if (action != "update") {
				if(userDetailsForRoles.userLastName != undefined && userDetailsForRoles.userLastName != null){
					$("#estimator").val(userDetailsForRoles.userDetails +" "+userDetailsForRoles.userLastName);
				}else{
					$("#estimator").val(userDetailsForRoles.userDetails);
				}
			}
			if (userDetailsForRoles.userRolesList[i].userRolesId.roleName
					.toLowerCase() == 'ESTIMATOR'.toLowerCase()) {
				$(".informationRoleHighlights").removeClass(
						"informationRoleHighlights");
				$("#jobName").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#customerDirectory").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#jobAddress").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#projectTypeBean").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#bidNumber").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#bidPrice").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#departmentType").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#vendorList").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#jobName").addClass('informationRoleHighlights');
				$("#customerDirectory").addClass('informationRoleHighlights');
				$("#jobAddress").addClass('informationRoleHighlights');
				$("#projectTypeBean").addClass('informationRoleHighlights');
				$("#bidNumber").addClass('informationRoleHighlights');
				$("#bidPrice").addClass('informationRoleHighlights');
				$("#departmentType").addClass('informationRoleHighlights');
				$("#vendorList").addClass('informationRoleHighlights');
				$("#contractAmount").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#contractNumber").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#typeOfContract").parent().parent().prev().children()
						.addClass('roleHighlight');

				$("#isCertifiedPayroll").parent().css("color", " #A6A600");
				$("#isCertifiedPayroll").parent().css("font-weight", "bold");
				$("#ownerControlledInsuranceProg").parent().css("color",
						" #A6A600");
				$("#ownerControlledInsuranceProg").parent().css("font-weight",
						"bold");
				$("#noticeToProceed").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#rfi").parent().parent().parent().prev().prev().children()
						.addClass('roleHighlight');
				$("#executedContract").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#submittals").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#drawingLog").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#communications").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#transmittalSheets").parent().parent().parent().prev()
						.prev().children().addClass('roleHighlight');
				$("#bidCommunications").parent().parent().parent().prev()
						.prev().children().addClass('roleHighlight');
				$("#otherDocuments").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#californiaPreliminaryNotice").parent().parent().parent()
						.prev().prev().children().addClass('roleHighlight');
				$("#insuranceCertificate").parent().parent().parent().prev()
						.prev().children().addClass('roleHighlight');
				$("#paymentBond").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$(".budgetRoleHighlights").removeClass('budgetRoleHighlights');
				$(".settingsRoleHighlights").removeClass(
						'settingsRoleHighlights');
				$(".personnelRoleHighlights").removeClass(
						'personnelRoleHighlights');

			}
			if (userDetailsForRoles.userRolesList[i].userRolesId.roleName
					.toLowerCase() == 'ACCOUNTANT'.toLowerCase()) {
				$("#accountant").val(userDetailsForRoles.userId);
				$("#jobNumber").parent().parent().prev().children().addClass(
						'roleHighlight');
				$(".informationRoleHighlights").removeClass(
						"informationRoleHighlights");
				$("#jobNumber").addClass('informationRoleHighlights');
				$("#contractAmount").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#contractNumber").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#typeOfContract").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#isCertifiedPayroll").parent().css("color", " #A6A600");
				$("#isCertifiedPayroll").parent().css("font-weight", "bold");
				$("#ownerControlledInsuranceProg").parent().css("color",
						" #A6A600");
				$("#ownerControlledInsuranceProg").parent().css("font-weight",
						"bold");
				$("#noticeToProceed").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#rfi").parent().parent().parent().prev().prev().children()
						.addClass('roleHighlight');
				$("#executedContract").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#submittals").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#drawingLog").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#communications").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#transmittalSheets").parent().parent().parent().prev()
						.prev().children().addClass('roleHighlight');
				$("#bidCommunications").parent().parent().parent().prev()
						.prev().children().addClass('roleHighlight');
				$("#otherDocuments").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$("#californiaPreliminaryNotice").parent().parent().parent()
						.prev().prev().children().addClass('roleHighlight');
				$("#insuranceCertificate").parent().parent().parent().prev()
						.prev().children().addClass('roleHighlight');
				$("#paymentBond").parent().parent().parent().prev().prev()
						.children().addClass('roleHighlight');
				$(".personnelRoleHighlights").removeClass(
						'personnelRoleHighlights');
				$(".budgetRoleHighlights").removeClass('budgetRoleHighlights');
				$(".settingsRoleHighlights").removeClass(
						'settingsRoleHighlights');
			}
			if (userDetailsForRoles.userRolesList[i].userRolesId.roleName
					.toLowerCase() == 'MANAGER'.toLowerCase()
					|| userDetailsForRoles.userRolesList[i].userRolesId.roleName
							.toLowerCase() == 'SUPERVISOR'.toLowerCase()
					|| userDetailsForRoles.userRolesList[i].userRolesId.roleName
							.toLowerCase() == 'EXECUTIVE'.toLowerCase()
					|| userDetailsForRoles.userRolesList[i].userRolesId.roleName
							.toLowerCase() == 'ADMIN'.toLowerCase()) {

				// $('#supervisor').val(12);
				if (userDetailsForRoles.isDeptHead != null
						&& userDetailsForRoles.isDeptHead != false) {
					$(".personnelRoleHighlights").removeClass(
							'personnelRoleHighlights');
				}
				$(".informationRoleHighlights").removeClass(
						"informationRoleHighlights");
				$("#jobName").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#customerDirectory").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#jobAddress").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#projectTypeBean").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#bidNumber").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#contractorDirectory").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#sovType").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#vendorList").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#startDate").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#endDate").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#coDate").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#extendedDate").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#architectId").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#description").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#reportMargin").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#performanceTargetMargin").parent().parent().prev()
						.children().addClass('roleHighlight');
				$("#laborBonusTargetOverBudget").parent().parent().prev()
						.children().addClass('roleHighlight');
				$("#collectionDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#retentionDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#retentionPercent").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#materialDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#subcontractorDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#directJobDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#ownedEquipmentDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#projAdminDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#laborDayOut").parent().parent().prev().children().addClass(
						'roleHighlight');
				$("#indirectDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#activationValidityTimePeriod").parent().parent().prev()
						.children().addClass('roleHighlight');
				$("#rentalEquipmentDayOut").parent().parent().prev().children()
						.addClass('roleHighlight');
				$("#jobName").addClass('informationRoleHighlights');
				$("#customerDirectory").addClass('informationRoleHighlights');
				$("#jobAddress").addClass('informationRoleHighlights');
				$("#projectTypeBean").addClass('informationRoleHighlights');
				$("#bidNumber").addClass('informationRoleHighlights');
				$("#vendorList").addClass('informationRoleHighlights');
				$("#contractorDirectory").addClass('informationRoleHighlights');
				$("#sovType").addClass('informationRoleHighlights');
				$("#startDate").addClass('informationRoleHighlights');
				$("#endDate").addClass('informationRoleHighlights');
				$("#coDate").addClass('informationRoleHighlights');
				$("#extendedDate").addClass('informationRoleHighlights');
				$("#architectId").addClass('informationRoleHighlights');
				$("#description").addClass('informationRoleHighlights');
				if (userDetailsForRoles.userRolesList[i].userRolesId.roleName
						.toLowerCase() == 'MANAGER'.toLowerCase()
						|| userDetailsForRoles.userRolesList[i].userRolesId.roleName
								.toLowerCase() == 'ADMIN'.toLowerCase()) {
					$("#contractAmount").parent().parent().prev().children()
							.addClass('roleHighlight');
					$("#contractNumber").parent().parent().prev().children()
							.addClass('roleHighlight');
					$("#typeOfContract").parent().parent().prev().children()
							.addClass('roleHighlight');
					$("#isCertifiedPayroll").parent().css("color", " #A6A600");
					$("#isCertifiedPayroll").parent()
							.css("font-weight", "bold");
					$("#ownerControlledInsuranceProg").parent().css("color",
							" #A6A600");
					$("#ownerControlledInsuranceProg").parent().css(
							"font-weight", "bold");
					$("#noticeToProceed").parent().parent().parent().prev()
							.prev().children().addClass('roleHighlight');
					$("#rfi").parent().parent().parent().prev().prev()
							.children().addClass('roleHighlight');
					$("#executedContract").parent().parent().parent().prev()
							.prev().children().addClass('roleHighlight');
					$("#submittals").parent().parent().parent().prev().prev()
							.children().addClass('roleHighlight');
					$("#drawingLog").parent().parent().parent().prev().prev()
							.children().addClass('roleHighlight');
					$("#communications").parent().parent().parent().prev()
							.prev().children().addClass('roleHighlight');
					$("#transmittalSheets").parent().parent().parent().prev()
							.prev().children().addClass('roleHighlight');
					$("#bidCommunications").parent().parent().parent().prev()
							.prev().children().addClass('roleHighlight');
					$("#otherDocuments").parent().parent().parent().prev()
							.prev().children().addClass('roleHighlight');
					$("#californiaPreliminaryNotice").parent().parent()
							.parent().prev().prev().children().addClass(
									'roleHighlight');
					$("#insuranceCertificate").parent().parent().parent()
							.prev().prev().children().addClass('roleHighlight');
					$("#paymentBond").parent().parent().parent().prev().prev()
							.children().addClass('roleHighlight');
					if (userDetailsForRoles.userRolesList[i].userRolesId.roleName
							.toLowerCase() == 'ADMIN'.toLowerCase()) {
						$("#bidBudgetMaterialCost").parent().parent().prev()
								.children().addClass('roleHighlight');
						$("#bidBudgetSubcontractorsCost").parent().parent()
								.prev().children().addClass('roleHighlight');
						$("#bidBudgetDirectJobCosts").parent().parent().prev()
								.children().addClass('roleHighlight');
						$("#bidBudgetRentalEquipmentCosts").parent().parent()
								.prev().children().addClass('roleHighlight');
						$("#bidBudgetOwnedEquipmentsCosts").parent().parent()
								.prev().children().addClass('roleHighlight');
						$("#bidBudgetProjectAdministrationCost").parent()
								.parent().prev().children().addClass(
										'roleHighlight');
						$("#bidBudgetLaborCost").parent().parent().prev()
								.children().addClass('roleHighlight');
						$("#bidBudgetIndirectExpenses").parent().parent()
								.prev().children().addClass('roleHighlight');
						$("#jobNumber").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#jobNumber").addClass('informationRoleHighlights');
						$("#bidPrice").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#departmentType").parent().parent().prev()
								.children().addClass('roleHighlight');
						$("#bidPrice").addClass('informationRoleHighlights');
						$("#departmentType").addClass(
								'informationRoleHighlights');
						$("#executive").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#manager").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#supervisor").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#foreman").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#purchasingAgent").parent().parent().prev()
								.children().addClass('roleHighlight');
						$("#accountant").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#warehouseManager").parent().parent().prev()
								.children().addClass('roleHighlight');
						$("#superindent").parent().parent().prev().children()
								.addClass('roleHighlight');
						$("#estimator").parent().parent().prev().children()
								.addClass('roleHighlight');
					} else {
						$("#manager").val(userDetailsForRoles.userId);
						$(".budgetRoleHighlights").removeClass(
								'budgetRoleHighlights');
					}
				} else {
					$(".legalRoleHighlights")
							.removeClass('legalRoleHighlights');
					$("#bidBudgetMaterialCost").parent().parent().prev()
							.children().addClass('roleHighlight');
					$("#bidBudgetSubcontractorsCost").parent().parent().prev()
							.children().addClass('roleHighlight');
					$("#bidBudgetDirectJobCosts").parent().parent().prev()
							.children().addClass('roleHighlight');
					$("#bidBudgetRentalEquipmentCosts").parent().parent()
							.prev().children().addClass('roleHighlight');
					$("#bidBudgetOwnedEquipmentsCosts").parent().parent()
							.prev().children().addClass('roleHighlight');
					$("#bidBudgetProjectAdministrationCost").parent().parent()
							.prev().children().addClass('roleHighlight');
					$("#bidBudgetLaborCost").parent().parent().prev()
							.children().addClass('roleHighlight');
					$("#bidBudgetIndirectExpenses").parent().parent().prev()
							.children().addClass('roleHighlight');
					if (userDetailsForRoles.userRolesList[i].userRolesId.roleName
							.toLowerCase() == 'SUPERVISOR'.toLowerCase()) {
						$("#supervisor").val(userDetailsForRoles.userId);
					}
					if (userDetailsForRoles.userRolesList[i].userRolesId.roleName
							.toLowerCase() == 'executive'.toLowerCase()) {
						$("#executive").val(userDetailsForRoles.userId);
					}
				}
			}

		}
	}
	highlightCount();
	// removeHighlights();
}

function highlightCount() {
	informationTab = 0;
	personnelTab = 0;
	legalTab = 0;
	settingsTab = 0;
	budgetTab = 0;
	var informationString = "";
	$('.informationRoleHighlights').each(function() {
		if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
			informationTab++;
			informationString += $(this).attr("fieldName") + "\n";
		} else {
			// informationTab--;
		}
		if ($(this).attr("id") == 'jobAddress') {
			if ($("#addressDetails").html() == "") {
				informationTab++;
				informationString += $(this).attr("fieldName") + "\n";
			}
		}
	});
	var legalString = "";
	$('.legalRoleHighlights').each(
			function() {
				if ($(this).attr("type") == "file") {
					if ($(this).parent().parent().parent().parent().next()
							.html() == "") {
						legalTab++;
						legalString += $(this).attr("fieldName") + "\n";
					}
				} else if ($(this).attr("type") == 'checkbox') {
					if (!$(this).is(":checked")) {
						legalTab++;
						legalString += $(this).attr("fieldName") + "\n";
					}
				} else {
					if ($(this).val() == "") {
						legalTab++;
						legalString += $(this).attr("fieldName") + "\n";
					}
				}
			});
	var budgetString = "";
	$('.budgetRoleHighlights').each(function() {
		if ($(this).val() == '') {
			budgetTab++;
			budgetString += $(this).attr("fieldName") + "\n";
		} else {
			// budgetTab--;
		}
	});
	var settingsString = "";
	$('.settingsRoleHighlights').each(function() {
		if ($(this).val() == '') {
			settingsTab++;
			settingsString += $(this).attr("fieldName") + "\n";
		} else {
			// settingsTab--;
		}
	});
	var personnelString = "";
	$('.personnelRoleHighlights').each(function() {
		if ($(this).val() == '') {
			personnelTab++;
			personnelString += $(this).attr("fieldName") + "\n";
		} else {
			// informationTab--;
		}
	});
	$("#informationTab").html(informationTab);
	$("#informationTab").attr("title", informationString);
	$("#personnelTab").html(personnelTab);
	$("#personnelTab").attr("title", personnelString);
	$("#legalTab").html(legalTab);
	$("#legalTab").attr("title", legalString);
	$("#settingsTab").html(settingsTab);
	$("#settingsTab").attr("title", settingsString);
	$("#budgetTab").html(budgetTab);
	$("#budgetTab").attr("title", budgetString);
}

$("#otherDocumentsLists").on("click", function() {
	$("#otherDocumentsLists").toggleClass("fa-minus-square");
	if ($(".otherDocumentsFileLists").hasClass("hide")) {
		$(".otherDocumentsFileLists").removeClass("hide");
	} else {
		$(".otherDocumentsFileLists").addClass("hide");
	}
});
$("#noticeToProceedLists").on("click", function() {
	$("#noticeToProceedLists").toggleClass("fa-minus-square");
	if ($(".noticeToProceedFileLists").hasClass("hide")) {
		$(".noticeToProceedFileLists").removeClass("hide");
	} else {
		$(".noticeToProceedFileLists").addClass("hide");
	}

});
$("#exectedContractLists").on("click", function() {
	$("#exectedContractLists").toggleClass("fa-minus-square");
	if ($(".exectedContractFileLists").hasClass("hide")) {
		$(".exectedContractFileLists").removeClass("hide");
	} else {
		$(".exectedContractFileLists").addClass("hide");
	}
});
$("#rfiLists").on("click", function() {
	$("#rfiLists").toggleClass("fa-minus-square");
	if ($(".rfiFileLists").hasClass("hide")) {
		$(".rfiFileLists").removeClass("hide");
	} else {
		$(".rfiFileLists").addClass("hide");
	}
});
$("#submittalsLists").on("click", function() {
	$("#submittalsLists").toggleClass("fa-minus-square");
	if ($(".submittalsFileLists").hasClass("hide")) {
		$(".submittalsFileLists").removeClass("hide");
	} else {
		$(".submittalsFileLists").addClass("hide");
	}
});
$("#drawingLogLists").on("click", function() {
	$("#drawingLogLists").toggleClass("fa-minus-square");
	if ($(".drawingLogFileLists").hasClass("hide")) {
		$(".drawingLogFileLists").removeClass("hide");
	} else {
		$(".drawingLogFileLists").addClass("hide");
	}
});
$("#communicationsLists").on("click", function() {
	$("#communicationsLists").toggleClass("fa-minus-square");
	if ($(".communicationsFileLists").hasClass("hide")) {
		$(".communicationsFileLists").removeClass("hide");
	} else {
		$(".communicationsFileLists").addClass("hide");
	}
});
$("#transmittalsSheetsLists").on("click", function() {
	$("#transmittalsSheetsLists").toggleClass("fa-minus-square");
	if ($(".transmittalsSheetsFileLists").hasClass("hide")) {
		$(".transmittalsSheetsFileLists").removeClass("hide");
	} else {
		$(".transmittalsSheetsFileLists").addClass("hide");
	}
});
$("#bidDocumentsLists").on("click", function() {
	$("#bidDocumentsLists").toggleClass("fa-minus-square");
	if ($(".bidDocumentsFileLists").hasClass("hide")) {
		$(".bidDocumentsFileLists").removeClass("hide");
	} else {
		$(".bidDocumentsFileLists").addClass("hide");
	}
});

$("#insuranceCertificateLists").on("click", function() {
	$("#insuranceCertificateLists").toggleClass("fa-minus-square");
	if ($(".insuranceCertificateFileLists").hasClass("hide")) {
		$(".insuranceCertificateFileLists").removeClass("hide");
	} else {
		$(".insuranceCertificateFileLists").addClass("hide");
	}
});
$("#paymentBondLists").on("click", function() {
	$("#paymentBondLists").toggleClass("fa-minus-square");
	if ($(".paymentBondFileLists").hasClass("hide")) {
		$(".paymentBondFileLists").removeClass("hide");
	} else {
		$(".paymentBondFileLists").addClass("hide");
	}
});
$("#californiaPreliminaryNoticeLists").on("click", function() {
	$("#californiaPreliminaryNoticeLists").toggleClass("fa-minus-square");
	if ($(".californiaPreliminaryNoticeFileLists").hasClass("hide")) {
		$(".californiaPreliminaryNoticeFileLists").removeClass("hide");
	} else {
		$(".californiaPreliminaryNoticeFileLists").addClass("hide");
	}
});
$("#ownerControlledInsuranceProgDocumentsLists").on(
		"click",
		function() {
			$("#ownerControlledInsuranceProgDocumentsLists").toggleClass(
					"fa-minus-square");
			if ($(".ownerControlledInsuranceProgDocumentsFileLists").hasClass(
					"hide")) {
				$(".ownerControlledInsuranceProgDocumentsFileLists")
						.removeClass("hide");
			} else {
				$(".ownerControlledInsuranceProgDocumentsFileLists").addClass(
						"hide");
			}
		});

function removeHighlights() {
	informationTab = 0;
	personnelTab = 0;
	legalTab = 0;
	settingsTab = 0;
	budgetTab = 0;
	$("#jobName").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#jobName").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
//	$("#jobNumber").focusin(function() {
//		$(".jobCountStyle").css("background-color","red");
//	});
//	$("#jobNumber").focusout(function() {
//		$(".jobCountStyle").css("background-color","yellow");
//	});
	$("#jobNumber").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#jobNumber").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#customerDirectory").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#customerDirectory").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
		if ($("#customerDirectory").val() == "other") {
			$("#jobDetailModal").modal('hide');
			$("#customerRemoteModal").modal('show');
		}
	});
	$("#addressDetails").on(
			"DOMNodeInserted DOMNodeRemoved change paste keyup",
			function() {
				informationTab = 0;
				var informationString = "";
				if ($("#jobAddress").hasClass("informationRoleHighlights")) {
					$('.informationRoleHighlights').each(
							function() {
								if ($(this).val() == ''
										&& $(this).attr("id") != 'jobAddress') {
									informationTab++;
									informationString += $(this).attr(
											"fieldName")
											+ "\n";
								}
								if ($(this).attr("id") == 'jobAddress') {
									if ($("#addressDetails").html() == "") {
										informationTab++;
										informationString += $(this).attr(
												"fieldName")
												+ "\n";
									}
								}
							});
					$("#informationTab").html(informationTab);
					$("#informationTab").attr("title", informationString);
				}
			});
	$("#projectTypeBean").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#projectTypeBean").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#bidNumber").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#bidNumber").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#bidPrice").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#bidPrice").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#departmentType").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#departmentType").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#vendorList").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#vendorList").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
		if ($("#vendorList").val() == "other") {
			$("#jobDetailModal").modal('hide');
			$("#vendorRemoteModal").modal('show');
		}
	});
	$("#contractorDirectory").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#contractorDirectory").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#sovType").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#sovType").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#startDate").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#startDate").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#startDate").focus(function(){
		$('#extendedDate').datepicker('hide');
		$('#endDate').datepicker('hide');
		$('#coDate').datepicker('hide');
	});
	$("#endDate").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#endDate").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#endDate").focus(function(){
		$('#extendedDate').datepicker('hide');
		$('#startDate').datepicker('hide');
		$('#coDate').datepicker('hide');
	});
	$("#coDate").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#coDate").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#coDate").focus(function(){
		$('#extendedDate').datepicker('hide');
		$('#startDate').datepicker('hide');
		$('#endDate').datepicker('hide');
	});
	$("#extendedDate").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#extendedDate").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#extendedDate").focus(function(){
		$('#endDate').datepicker('hide');
		$('#startDate').datepicker('hide');
		$('#coDate').datepicker('hide');
	});
	$("#architectId").focus(function(){
		$('#endDate').datepicker('hide');
		$('#extendedDate').datepicker('hide');
		$('#startDate').datepicker('hide');
		$('#coDate').datepicker('hide');
	});
	$("#architectId").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#architectId").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	$("#description").on("change paste keyup", function() {
		informationTab = 0;
		var informationString = "";
		if ($("#description").hasClass("informationRoleHighlights")) {
			$('.informationRoleHighlights').each(function() {
				if ($(this).val() == '' && $(this).attr("id") != 'jobAddress') {
					informationTab++;
					informationString += $(this).attr("fieldName") + "\n";
				}
				if ($(this).attr("id") == 'jobAddress') {
					if ($("#addressDetails").html() == "") {
						informationTab++;
						informationString += $(this).attr("fieldName") + "\n";
					}
				}
			});
			$("#informationTab").html(informationTab);
			$("#informationTab").attr("title", informationString);
		}
	});
	// for seetings tab
	$("#collectionDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#collectionDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#retentionDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#retentionDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#retentionPercent").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#retentionPercent").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#materialDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#materialDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#subcontractorDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#subcontractorDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#directJobDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#directJobDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#rentalEquipmentDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#rentalEquipmentDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#ownedEquipmentDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#ownedEquipmentDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#projAdminDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#projAdminDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#laborDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#laborDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#indirectDayOut").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#indirectDayOut").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#activationValidityTimePeriod").on(
			"change paste keyup",
			function() {
				settingsTab = 0;
				var settingsString = "";
				if ($("#activationValidityTimePeriod").hasClass(
						"settingsRoleHighlights")) {
					$('.settingsRoleHighlights').each(function() {
						if ($(this).val() == '') {
							settingsTab++;
							settingsString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#settingsTab").html(settingsTab);
					$("#settingsTab").attr("title", settingsString);
				}
			});
	$("#reportMargin").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#reportMargin").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#performanceTargetMargin").on("change paste keyup", function() {
		settingsTab = 0;
		var settingsString = "";
		if ($("#performanceTargetMargin").hasClass("settingsRoleHighlights")) {
			$('.settingsRoleHighlights').each(function() {
				if ($(this).val() == '') {
					settingsTab++;
					settingsString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#settingsTab").html(settingsTab);
			$("#settingsTab").attr("title", settingsString);
		}
	});
	$("#laborBonusTargetOverBudget").on(
			"change paste keyup",
			function() {
				settingsTab = 0;
				var settingsString = "";
				if ($("#laborBonusTargetOverBudget").hasClass(
						"settingsRoleHighlights")) {
					$('.settingsRoleHighlights').each(function() {
						if ($(this).val() == '') {
							settingsTab++;
							settingsString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#settingsTab").html(settingsTab);
					$("#settingsTab").attr("title", settingsString);
				}
			});
	// for budget tab
	$("#bidBudgetMaterialCost").on("change paste keyup", function() {
		budgetTab = 0;
		var budgetString = "";
		if ($("#bidBudgetMaterialCost").hasClass("budgetRoleHighlights")) {
			$('.budgetRoleHighlights').each(function() {
				if ($(this).val() == '') {
					budgetTab++;
					budgetString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#budgetTab").html(budgetTab);
			$("#budgetTab").attr("title", budgetString);
		}
	});
	$("#bidBudgetSubcontractorsCost").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#bidBudgetSubcontractorsCost").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#bidBudgetDirectJobCosts").on("change paste keyup", function() {
		budgetTab = 0;
		var budgetString = "";
		if ($("#bidBudgetDirectJobCosts").hasClass("budgetRoleHighlights")) {
			$('.budgetRoleHighlights').each(function() {
				if ($(this).val() == '') {
					budgetTab++;
					budgetString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#budgetTab").html(budgetTab);
			$("#budgetTab").attr("title", budgetString);
		}
	});
	$("#bidBudgetRentalEquipmentCosts").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#bidBudgetRentalEquipmentCosts").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#bidBudgetOwnedEquipmentsCosts").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#bidBudgetOwnedEquipmentsCosts").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#bidBudgetProjectAdministrationCost").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#bidBudgetProjectAdministrationCost").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#bidBudgetLaborCost").on("change paste keyup", function() {
		budgetTab = 0;
		var budgetString = "";
		if ($("#bidBudgetLaborCost").hasClass("budgetRoleHighlights")) {
			$('.budgetRoleHighlights').each(function() {
				if ($(this).val() == '') {
					budgetTab++;
					budgetString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#budgetTab").html(budgetTab);
			$("#budgetTab").attr("title", budgetString);
		}
	});
	$("#bidBudgetIndirectExpenses").on("change paste keyup", function() {
		budgetTab = 0;
		var budgetString = "";
		if ($("#bidBudgetIndirectExpenses").hasClass("budgetRoleHighlights")) {
			$('.budgetRoleHighlights').each(function() {
				if ($(this).val() == '') {
					budgetTab++;
					budgetString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#budgetTab").html(budgetTab);
			$("#budgetTab").attr("title", budgetString);
		}
	});
	$("#operationsBudgetMaterialCosts").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#operationsBudgetMaterialCosts").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#operationsSubcontractorsCosts").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#operationsSubcontractorsCosts").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#operationsBudgetDirectJobCost").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#operationsBudgetDirectJobCost").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#operationsBudgetRentalEquipmentCost").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#operationsBudgetRentalEquipmentCost").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#operationsOwnedEquipmentCost").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#operationsOwnedEquipmentCost").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#operationsProjectAdministrationCost").on(
			"change paste keyup",
			function() {
				budgetTab = 0;
				var budgetString = "";
				if ($("#operationsProjectAdministrationCost").hasClass(
						"budgetRoleHighlights")) {
					$('.budgetRoleHighlights').each(function() {
						if ($(this).val() == '') {
							budgetTab++;
							budgetString += $(this).attr("fieldName") + "\n";
						}
					});
					$("#budgetTab").html(budgetTab);
					$("#budgetTab").attr("title", budgetString);
				}
			});
	$("#operationsIndirectExpenses").on("change paste keyup", function() {
		budgetTab = 0;
		var budgetString = "";
		if ($("#operationsIndirectExpenses").hasClass("budgetRoleHighlights")) {
			$('.budgetRoleHighlights').each(function() {
				if ($(this).val() == '') {
					budgetTab++;
					budgetString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#budgetTab").html(budgetTab);
			$("#budgetTab").attr("title", budgetString);
		}
	});
	$("#operationsBudgetLaborCost").on("change paste keyup", function() {
		budgetTab = 0;
		var budgetString = "";
		if ($("#operationsBudgetLaborCost").hasClass("budgetRoleHighlights")) {
			$('.budgetRoleHighlights').each(function() {
				if ($(this).val() == '') {
					budgetTab++;
					budgetString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#budgetTab").html(budgetTab);
			$("#budgetTab").attr("title", budgetString);
		}
	});
	// for legal tab
	$("#noticeToProceed").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#noticeToProceed").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#noticeToProceed").val() != "") {
						uploadDocuments("noticeToProceed");
					}
				}
			});
	$("#noticeToProceed").focus(function(){
		$('#noticeReceivedDate').datepicker('hide');
	});
	$("#executedContract").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#executedContract").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#executedContract").val() != "") {
						uploadDocuments("executedContract");
					}
				}
			});
	$("#rfi").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#rfi").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#rfi").val() != "") {
						uploadDocuments("rfi");
					}
				}
			});
	$("#submittals").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#submittals").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#submittals").val() != "") {
						uploadDocuments("submittals");
					}
				}
			});
	$("#drawingLog").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#drawingLog").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#drawingLog").val() != "") {
						uploadDocuments("drawingLog");
					}
				}
			});
	$("#communications").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#communications").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#communications").val() != "") {
						uploadDocuments("communications");
					}
				}
			});
	$("#transmittalSheets").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#transmittalSheets").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#transmittalSheets").val() != "") {
						uploadDocuments("transmittalSheets");
					}
				}
			});
	$("#bidCommunications").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#bidCommunications").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#bidCommunications").val() != "") {
						uploadDocuments("bidCommunications");
					}
				}
			});
	$("#ownerControlledInsuranceProgDocuments")
			.on(
					"change paste keyup click",
					function() {
						legalTab = 0;
						var legalString = "";
						if ($("#ownerControlledInsuranceProgDocuments")
								.hasClass("legalRoleHighlights")) {
							$('.legalRoleHighlights')
									.each(
											function() {
												if ($(this).attr("type") == "file") {
													if ($(this).parent()
															.parent().parent()
															.parent().next()
															.html() == "") {
														legalTab++;
														legalString += $(this)
																.attr(
																		"fieldName")
																+ "\n";
													}
												} else if ($(this).attr("type") == 'checkbox') {
													if (!$(this).is(":checked")) {
														legalTab++;
														legalString += $(this)
																.attr(
																		"fieldName")
																+ "\n";
													}
												} else {
													if ($(this).val() == "") {
														legalTab++;
														legalString += $(this)
																.attr(
																		"fieldName")
																+ "\n";
													}
												}
											});
							$("#legalTab").html(legalTab);
							$("#legalTab").attr("title", legalString);
							if ($("#ownerControlledInsuranceProgDocuments")
									.val() != "") {
								uploadDocuments("ownerControlledInsuranceProgDocuments");
							}
						}
					});
	$("#otherDocuments").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#otherDocuments").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#otherDocuments").val() != "") {
						uploadDocuments("otherDocuments");
					}
				}
			});
	$("#typeOfContract").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#typeOfContract").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
				}
			});
	$("#contractAmount").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#contractAmount").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
				}
			});
	$("#contractNumber").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#contractNumber").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
				}
			});
	$("#isCertifiedPayroll").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#isCertifiedPayroll").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
				}
			});
	$("#californiaPreliminaryNotice").focus(function(){
		$('#cpnSentDate').datepicker('hide');
	});
	$("#californiaPreliminaryNotice").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#californiaPreliminaryNotice").hasClass(
						"legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#californiaPreliminaryNotice").val() != "") {
						uploadDocuments("californiaPreliminaryNotice");
					}
				}
			});
	$("#paymentBond").focus(function(){
		$('#performanceSentDate').datepicker('hide');
	});
	$("#paymentBond").on(
			"change paste keyup click",
			function() {
				legalTab = 0;
				var legalString = "";
				if ($("#paymentBond").hasClass("legalRoleHighlights")) {
					$('.legalRoleHighlights').each(
							function() {
								if ($(this).attr("type") == "file") {
									if ($(this).parent().parent().parent()
											.parent().next().html() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else if ($(this).attr("type") == 'checkbox') {
									if (!$(this).is(":checked")) {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								} else {
									if ($(this).val() == "") {
										legalTab++;
										legalString += $(this)
												.attr("fieldName")
												+ "\n";
									}
								}
							});
					$("#legalTab").html(legalTab);
					$("#legalTab").attr("title", legalString);
					if ($("#paymentBond").val() != "") {
						ownerControlledInsuranceProgDocuments
						uploadDocuments("paymentBond");
					}
				}
			});
	$("#insuranceCertificate").focus(function(){
		$('#insuranceSentDate').datepicker('hide');
	});
	$("#insuranceCertificate")
			.on(
					"change paste keyup click",
					function() {
						legalTab = 0;
						var legalString = "";
						if ($("#insuranceCertificate").hasClass(
								"legalRoleHighlights")) {
							$('.legalRoleHighlights')
									.each(
											function() {
												if ($(this).attr("type") == "file") {
													if ($(this).parent()
															.parent().parent()
															.parent().next()
															.html() == "") {
														legalTab++;
														legalString += $(this)
																.attr(
																		"fieldName")
																+ "\n";
													}
												} else if ($(this).attr("type") == 'checkbox') {
													if (!$(this).is(":checked")) {
														legalTab++;
														legalString += $(this)
																.attr(
																		"fieldName")
																+ "\n";
													}
												} else {
													if ($(this).val() == "") {
														legalTab++;
														legalString += $(this)
																.attr(
																		"fieldName")
																+ "\n";
													}
												}
											});
							$("#legalTab").html(legalTab);
							$("#legalTab").attr("title", legalString);
							if ($("#insuranceCertificate").val() != "") {
								uploadDocuments("insuranceCertificate");
							}
						}
					});
	// for personnel tab
	$("#executive").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#executive").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});
	$("#supervisor").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#supervisor").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});
	$("#manager").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#manager").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});
	$("#foreman").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#foreman").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});
	$("#purchasingAgent").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#purchasingAgent").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});
	$("#accountant").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#accountant").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});
	$("#warehouseManager").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#warehouseManager").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});
	$("#superindent").on("change paste keyup", function() {
		personnelTab = 0;
		var personnelString = "";
		if ($("#superindent").hasClass("personnelRoleHighlights")) {
			$('.personnelRoleHighlights').each(function() {
				if ($(this).val() == '') {
					personnelTab++;
					personnelString += $(this).attr("fieldName") + "\n";
				}
			});
			$("#personnelTab").html(personnelTab);
			$("#personnelTab").attr("title", personnelString);
		}
	});

}
function customerBackView() {
	$("#customerRemoteModal").modal('hide');
	$("#jobDetailModal").modal('show');
}
function vendorBackView() {
	$("#vendorRemoteModal").modal('hide');
	$("#jobDetailModal").modal('show');
}
// Validation for customer form
$(function() {
	// Validation
	$("#customer_form").validate({

		// Rules for form validation
		rules : {
			companyName : {
				required : true,
				maxlength : 100
			},
			companyNumber : {
				maxlength : 10
			},
//			corporateOfficeAddress : {
//				required : true,
//				maxlength : 200
//			},
			companyEmail : {
				required : true,
				email : true,
				maxlength : 50
			},
			phoneNumber : {
				required : true,
				digits : true,
				maxlength : 15
			},
			representativeName : {
				required : true,
				maxlength : 100
			},
			representativePhone : {
				required : true,
				digits : true,
				maxlength : 15
			},

		},

		// Messages for form validation
		messages : {
			companyName : {
				required : 'Please enter the company name'
			},
//			corporateOfficeAddress : {
//				required : 'Please enter the company address'
//
//			},
			companyEmail : {
				required : 'Please enter the company email address',
				email : 'Please enter a VALID email address'
			},
			phoneNumber : {
				required : 'Please enter the company phone number',
				digits : 'Please enter valid phone number'

			},
			representativeName : {
				required : 'Please enter a representative name'
			},
			representativePhone : {
				required : 'Please enter the representative phone number',
				digits : 'Please enter valid representative phone number'
			},

		},

		// Ajax form submition
		submitHandler : function(form) {
			ajaxCallForSaveOrUpdateCustomer(form.id);
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});

});

function ajaxCallForSaveOrUpdateCustomer(formId) {
	if (compAddressArray.length == 0) {
		$('#addressErr').css("display", "block");
	} else {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
//	console.log("The form is   "+formId);
//	console.log($("#"+formId).serialize());
	$
			.ajax({
				url : "saveOrUpdateCustomerAction",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					var actionType = "";
					if($("#companyId").val() != ""){
						actionType = "update";
					}
					if($("#typeOfAction").val() != "saveandcontinue"){
					     $("#customerRemoteModal").modal("hide");
					}
					if (result.ajaxResult == "success") {
						getCustomersDetails();
//						getAddressDetails();
						custsaveAddressDetails(result.customerId);
						
						// Resetting Forms
                        if(actionType != ""){
						  gritterForSucessMsgs("Customer has been updated successfully.");
                        } else {
                        	 gritterForSucessMsgs("Customer has been saved successfully.");
                        }
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
							+ result.reason);
					}
					resetCustomerForm();
					resetAddressForm();
				},
				error : function() {
					laddaRef.stop();
					resetCustomerForm();
					resetAddressForm();
					$("#customerRemoteModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}
}

function resetCustomerForm() {

	$("#save").html("<i class='fa fa-floppy-o'></i> &nbsp; Save");
	$("#saveandcontinue").show();
	$("#modelTitleId").html("Add New Customer");
	var validator = $( "#customer_form" ).validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#companyName").val("");
	$("#companyNumber").val("");
	$("#irs").val("");
	$("#businessType").val("");
	$("#companyId").val("");
	$("#corporateOfficeAddress").val("");
	$("#companyEmail").val("");
	$("#phoneNumber").val("");
	$("#fax").val("");
	$("#representativeName").val("");
	$("#representativePhone").val("");
	$("#representativeAddress").val("");
	$("#companyName").attr("placeholder", "Company Name");
	$("#companyNumber").attr("placeholder", "Company Number");
	$("#irs").attr("placeholder", "IRS");
	$("#businessType").attr("placeholder", "Business Type");
	$("#corporateOfficeAddress").attr("placeholder", "Office Address");
	$("#companyEmail").attr("placeholder", "Company Email");
	$("#phoneNumber").attr("placeholder", "Phone Number");
	$("#fax").attr("placeholder", "Fax Number");
	$("#representativeName").attr("placeholder", "Representative Name");
	$("#representativePhone").attr("placeholder", "Representative Phone");
	$("#representativeAddress").attr("placeholder", "Representative Address");
	// for address table
	custresetAddressForm();
	$("#custaddressInfo").hide();
	custresetRepAddressForm();
	$("#custrepAddressInfo").hide();
	custAddressArray = [];
	custrepAddressArray = [];
	$("#custaddressDetails").hide();
	$("#custaddressDetails").html("");
	$("#custrepAaddressDetails").hide();
	$("#custrepAaddressDetails").html("");
}
function getCustomersDetails() {
	customersList = "customer";
	$.ajax({
		url : "getAllCustomersDetails",
		type : "POST",
		contentType : 'text/plain',
		data : '{"customersList": "' + customersList + '"}',
		success : function(result) {
			$('#customerDirectory').find('option').remove().end().append(
					'<option value=""> Choose Customer</option>');
			var customersList = $("#customerDirectory");
			var customerResult = JSON.parse(result);
			for (var i = 0; i < customerResult.customerDetails.length; i++) {
				customersList.append("<option value="
						+ customerResult.customerDetails[i].companyId + ">"
						+ customerResult.customerDetails[i].companyName
						+ "</option>");
			}
			customersList.append("<option value='other'>Other</option>");
			$("#customerRemoteModal").modal('hide');
			$("#jobDetailModal").modal('show');
		}
	});
}
function setActionType(buttonId, laddaButtonTemp) {
	$("#typeOfAction").val(buttonId);

	laddaButton = laddaButtonTemp;
}
//address for customer
function custsaveAddressDetails(id){
	var addressData= {};
	var address = [];
	addressData.moduleId = id;
	addressData.moduleName = "customerDirectory";
	address.push( custAddressArray);
	address.push( custrepAddressArray);
	addressData.address = address;
	var module = '{"addressData" : '+JSON.stringify(addressData)+'}';
	$
	.ajax({
		url : "saveAddressDetails",
		data : module,
		type : 'POST',
		contentType:'application/json',
		success : function(data) {
			var data = JSON.parse(data);
//			$("#" + name).val("");
//			getAllJobDocuments(jobId);
			if (data.ajaxResult != "success") {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ data.reason);
			}
		},
		error : function() {
			alert("Some problem occured");
		}
	});

}
function custsaveAddressForm() {
	var dataObj = {
		address_line_1 : $("#custaddress_line_1").val(),
		address_line_2 : $("#custaddress_line_2").val(),
		state : $("#custstate option:selected").text(),
		city : $("#custcity option:selected").text(),
		zipCode : $("#custzipCode").val(),
		addressType : "Office"
	};
	custshowOfficeAddressForm(dataObj);
}
function custshowOfficeAddressForm(dataObj) {
	var fullAddress = "";
	if (dataObj.address_line_1 != "") {
		fullAddress += dataObj.address_line_1 + ", ";
	}else{
		$("#custaddressLine1Err").css("display","block");
		$("#custaddressLine1Err").prev().addClass("state-error");
	}
	if (dataObj.address_line_2 != "") {
		fullAddress += dataObj.address_line_2 + ", ";
	}
	if (dataObj.state != "" && dataObj.state != "Select State") {
		fullAddress += dataObj.state + ", ";
	}else{
		$("#custaddressStateErr").css("display","block");
		$("#custaddressStateErr").prev().addClass("state-error");
	}
	if (dataObj.city != "" && dataObj.city != "Select City") {
		fullAddress += dataObj.city + ", ";
	}else{
		$("#custaddressCityErr").css("display","block");
		$("#custaddressCityErr").prev().addClass("state-error");
	}
	if (dataObj.zipCode != "") {
		fullAddress += dataObj.zipCode + ", "
	}else{
		$("#custaddressZipCodeErr").css("display","block");
		$("#custaddressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
		fullAddress = fullAddress.substr(0, fullAddress.length - 2);
		custAddressArray.push(dataObj);
		var id = custAddressArray.length - 1;
		var file = "<section class='col col-lg-12' id='custaddress-"
				+ id
				+ "'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='custshowAddress("
				+ id
				+ ")' style='cursor:pointer;'\>"
				+ fullAddress
				+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='custsetAddressToDelete("
				+ id + ");'></i></p></section>";
	
		$("#custaddressDetails").append(file);
		$("#custaddressDetails").show();
		$("#custaddressInfo").hide();
		custresetAddressForm();
	}
}
function custshowAddress(id){
	var object = custAddressArray[id];

	$("#custaddressFormId").val(id);
	$("#custaddress_line_1").val(object.address_line_1);
	$("#custaddress_line_2").val(object.address_line_2);
	$("#custstate option").each(function() {
		if ($(this).text() == object.state) {
			$(this).attr('selected', 'selected');
			var value = $(this).val();
			flag = "custcity";
			getCities(value, flag, object.city);
		}
	});
	$('#custzipCode').val(object.zipCode);
	$("#custaddressInfo").show();
	$("#custaddressSaveButton").hide();
	$("#custaddressUpdateButton").show();
}
function custupdateAddressForm() {
	var addressId = $("#custaddressFormId").val();
	custAddressArray[addressId].address_line_1 = $("#custaddress_line_1").val();
	custAddressArray[addressId].address_line_2 = $("#custaddress_line_2").val();
	custAddressArray[addressId].state = $("#custstate option:selected").text();
	custAddressArray[addressId].city = $("#custcity option:selected").text();
	custAddressArray[addressId].zipCode = $("#custzipCode").val();
	var fullAddress = "";

	if(custAddressArray[addressId].address_line_1 !=""){
		fullAddress+= custAddressArray[addressId].address_line_1 + ", "
	}else{
		$("#custaddressLine1Err").css("display","block");
		$("#custaddressLine1Err").prev().addClass("state-error");
	}
	if(custAddressArray[addressId].address_line_2 !=""){
		fullAddress+= custAddressArray[addressId].address_line_2 + ", "
	}
	if(custAddressArray[addressId].state !="" && custAddressArray[addressId].state !="Select State"){
		fullAddress+= custAddressArray[addressId].state + ", "
	}else{
		$("#custaddressStateErr").css("display","block");
		$("#custaddressStateErr").prev().addClass("state-error");
	}
	if(custAddressArray[addressId].city !="" && custAddressArray[addressId].city !="Select City"){
		fullAddress+= custAddressArray[addressId].city + ", "
	}else{
		$("#custaddressCityErr").css("display","block");
		$("#custaddressCityErr").prev().addClass("state-error");
	}
	if(custAddressArray[addressId].zipCode !=""){
		fullAddress+= custAddressArray[addressId].zipCode + ", "

	}else{
		$("#custaddressZipCodeErr").css("display","block");
		$("#custaddressZipCodeErr").prev().addClass("state-error");
	}
	if(custAddressArray[addressId].zipCode != "" && custAddressArray[addressId].address_line_1 != "" && custAddressArray[addressId].state != "" && custAddressArray[addressId].state != "Select State"
		&& custAddressArray[addressId].city != "" && custAddressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='custshowAddress("
			+ addressId
			+ ")' style='cursor:pointer;'\>"
			+ fullAddress
			+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='custsetAddressToDelete("
			+ addressId + ");'></i></p>"
	$("#custaddress-" + addressId).html("");
	$("#custaddress-" + addressId).html(updateFile);
	custresetAddressForm();
	$("#custaddressInfo").hide();
	}
}
function custsetAddressToDelete(id){
	$("#custaddress-"+id).remove();
	delete custAddressArray[id];
	custAddressArray.pop(id);
}
$("#custaddress_line_1").on("change keyup",function(){
	if($("#custaddress_line_1").val() != ""){
		$("#custaddress_line_1").parent().removeClass("state-error");
		$('#custaddressLine1Err').css('display', 'none');
	}
});
$("#custstate").on("change keyup",function(){
	if($("#custstate").val() != ""){
		$("#custstate").parent().removeClass("state-error");
		$('#custaddressStateErr').css('display', 'none');
	}
});
$("#custcity").on("change keyup",function(){
	if($("#custcity").val() != ""){
		$("#custcity").parent().removeClass("state-error");
		$('#custaddressCityErr').css('display', 'none');
	}
});
$("#custzipCode").on("change keyup",function(){
	if($("#custzipCode").val() != ""){
		$("#custzipCode").parent().removeClass("state-error");
		$('#custaddressZipCodeErr').css('display', 'none');
	}
});
function custresetAddressForm() {
	$("#custaddressSaveButton").show();
	$("#custaddressUpdateButton").hide();
	$("#custaddress_line_1").val("");
	$("#custaddress_line_1").parent().removeClass("state-success");
	$("#custaddress_line_2").val("");
	$("#custaddress_line_2").parent().removeClass("state-success");
	$("#custstate").val("");
	$("#custstate").parent().removeClass("state-success");
	$("#custcity").find('option').remove().end().append(
			'<option value="">Select City</option>');
	$("#custcity").parent().removeClass("state-success");
	$('#custzipCode').val("");
	$("#custzipCode").parent().removeClass("state-success");
	$('#addressErr').css("display", "none");
	$("#custaddress_line_1").parent().removeClass("state-error");
	$('#custaddressLine1Err').css('display', 'none');
	$("#custstate").parent().removeClass("state-error");
	$('#custaddressStateErr').css('display', 'none');
	$("#custcity").parent().removeClass("state-error");
	$('#custaddressCityErr').css('display', 'none');
	$("#custzipCode").parent().removeClass("state-error");
	$('#custaddressZipCodeErr').css('display', 'none');
}
function custsaveRepAddressForm() {
	var dataObj = {
		address_line_1 : $("#custrepAddress_line_1").val(),
		address_line_2 : $("#custrepAddress_line_2").val(),
		state : $("#custrepState option:selected").text(),
		city : $("#custrepCity option:selected").text(),
		zipCode : $("#custrepZipCode").val(),
		addressType : "Representative"
	};
	custshowRepAddressForm(dataObj);
}
function custshowRepAddressForm(dataObj) {
	var fullAddress = "";
	if (dataObj.address_line_1 != "") {
		fullAddress += dataObj.address_line_1 + ", "
	}else{
		$("#custrepAddressLine1Err").css("display","block");
		$("#custrepAddressLine1Err").prev().addClass("state-error");
	}
	if (dataObj.address_line_2 != "") {
		fullAddress += dataObj.address_line_2 + ", "
	}
	if (dataObj.state != "" && dataObj.state != "Select State") {
		fullAddress += dataObj.state + ", "
	}else{
		$("#custrepAddressStateErr").css("display","block");
		$("#custrepAddressStateErr").prev().addClass("state-error");
	}
	if (dataObj.city != "" && dataObj.city != "Select City") {
		fullAddress += dataObj.city + ", "
	}else{
		$("#custrepAddressCityErr").css("display","block");
		$("#custrepAddressCityErr").prev().addClass("state-error");
	}
	if (dataObj.zipCode != "") {
		fullAddress += dataObj.zipCode + ", ";
	}else{
		$("#custrepAddressZipCodeErr").css("display","block");
		$("#custrepAddressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
		fullAddress = fullAddress.substr(0, fullAddress.length - 2);
		custrepAddressArray.push(dataObj);
		var id = custrepAddressArray.length - 1;
		var file = "<section class='col col-lg-12' id='custrepAddress-"+id+"'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='custshowRepAddress("+id+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='custdeleteRepAddress("+id+ ");'></i></p></section>";
	
		$("#custrepAaddressDetails").append(file);
		$("#custrepAaddressDetails").show();
		$("#custrepAddressInfo").hide();
		custresetRepAddressForm();
	}
}
function custshowRepAddress(id){
	var object = custrepAddressArray[id];
	$("#custrepAddressFormId").val(id);
	$("#custrepAddress_line_1").val(object.address_line_1);
	$("#custrepAddress_line_2").val(object.address_line_2);
	$("#custrepState option").each(function() {
		if ($(this).text() == object.state) {
			$(this).attr('selected', 'selected');
			var value = $(this).val();
			flag = "custrepCity";
			getCities(value, flag, object.city);
		}
	});
	$('#custrepZipCode').val(object.zipCode);
	$("#custrepAddressInfo").show();
	$("#custrepAddressSaveButton").hide();
	$("#custrepAddressUpdateButton").show();
}
function custupdateRepAddressForm() {
	var addressId = $("#custrepAddressFormId").val();
	custrepAddressArray[addressId].address_line_1 = $("#custrepAddress_line_1").val();
	custrepAddressArray[addressId].address_line_2 = $("#custrepAddress_line_2").val();
	custrepAddressArray[addressId].state = $("#custrepState option:selected").text();
	custrepAddressArray[addressId].city = $("#custrepCity option:selected").text();
	custrepAddressArray[addressId].zipCode = $("#custrepZipCode").val();
	var fullAddress = "";

	if(custrepAddressArray[addressId].address_line_1 !=""){
		fullAddress+= custrepAddressArray[addressId].address_line_1 + ", "
	}else{
		$("#custrepAddressLine1Err").css("display","block");
		$("#custrepAddressLine1Err").prev().addClass("state-error");
	}
	if(custrepAddressArray[addressId].address_line_2 !=""){
		fullAddress+= custrepAddressArray[addressId].address_line_2 + ", "
	}
	if(custrepAddressArray[addressId].state !="" && custrepAddressArray[addressId].state !="Select State"){
		fullAddress+= custrepAddressArray[addressId].state + ", "
	}else{
		$("#custrepAddressStateErr").css("display","block");
		$("#custrepAddressStateErr").prev().addClass("state-error");
	}
	if(custrepAddressArray[addressId].city !="" && custrepAddressArray[addressId].city !="Select City"){
		fullAddress+= custrepAddressArray[addressId].city + ", "
	}else{
		$("#custrepAddressCityErr").css("display","block");
		$("#custrepAddressCityErr").prev().addClass("state-error");
	}
	if(custrepAddressArray[addressId].zipCode !=""){
		fullAddress+= custrepAddressArray[addressId].zipCode + ", "

	}else{
		$("#custrepAddressZipCodeErr").css("display","block");
		$("#custrepAddressZipCodeErr").prev().addClass("state-error");
	}
	if(custrepAddressArray[addressId].zipCode != "" && custrepAddressArray[addressId].address_line_1 != "" && custrepAddressArray[addressId].state != "" && custrepAddressArray[addressId].state != "Select State"
		&& custrepAddressArray[addressId].city != "" && custrepAddressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='custshowRepAddress("
			+ addressId
			+ ")' style='cursor:pointer;'\>"
			+ fullAddress
			+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='custdeleteRepAddress("
			+ addressId + ");'></i></p>"
	$("#custrepAddress-" + addressId).html("");
	$("#custrepAddress-" + addressId).html(updateFile);
	custresetRepAddressForm();
	$("#custrepAddressInfo").hide();
	}
}
function custdeleteRepAddress(id){
		$("#custrepAddress-"+id).remove();
		delete custrepAddressArray[id];
		custrepAddressArray.pop(id);
}
$("#custrepAddress_line_1").on("change keyup",function(){
	if($("#custrepAddress_line_1").val() != ""){
		$("#custrepAddress_line_1").parent().removeClass("state-error");
		$('#custrepAddressLine1Err').css('display', 'none');
	}
});
$("#custrepState").on("change keyup",function(){
	if($("#custrepState").val() != ""){
		$("#custrepState").parent().removeClass("state-error");
		$('#custrepAddressStateErr').css('display', 'none');
	}
});
$("#custrepCity").on("change keyup",function(){
	if($("#custrepCity").val() != ""){
		$("#custrepCity").parent().removeClass("state-error");
		$('#custrepAddressCityErr').css('display', 'none');
	}
});
$("#custrepZipCode").on("change keyup",function(){
	if($("#custrepZipCode").val() != ""){
		$("#custrepZipCode").parent().removeClass("state-error");
		$('#repAddressZipCodeErr').css('display', 'none');
	}
});
function custresetRepAddressForm() {
	$("#custrepAddressSaveButton").show();
	$("#custrepAddressUpdateButton").hide();
	$("#custrepAddress_line_1").val("");
	$("#custrepAddress_line_1").parent().removeClass("state-success");
	$("#custrepAddress_line_2").val("");
	$("#custrepAddress_line_2").parent().removeClass("state-success");
	$("#custrepState").val("");
	$("#custrepState").parent().removeClass("state-success");
	$('#custrepCity').find('option').remove().end().append(
			'<option value="">Select City</option>');
	$("#custrepCity").parent().removeClass("state-success");
	$('#custrepZipCode').val("");
	$("#custrepZipCode").parent().removeClass("state-success");
	$("#custrepAddress_line_1").parent().removeClass("state-error");
	$('#custrepAddressLine1Err').css('display', 'none');
	$("#custrepState").parent().removeClass("state-error");
	$('#custrepAddressStateErr').css('display', 'none');
	$("#custrepCity").parent().removeClass("state-error");
	$('#custrepAddressCityErr').css('display', 'none');
	$("#repZipCode").parent().removeClass("state-error");
	$('#custrepAddressZipCodeErr').css('display', 'none');
}

//vendor directory
function resetVendorForm() {

	var validator = $("#vendor_form").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$('input').val('');
	$("#vendorName").val("");
	$("#vendorLegalName").val("");
	$("#vendorLegalStatus").val("");
	$("#vendorOwnership").val("");
	$("#vendorAddress").val("");
	$("#vendorMailingAddress").val("");
	$("#vendorEmail").val("");
	$("#vendorWebsite").val("");
	$("#representativeName").val("");
	$("#representativePhone").val("");
	$("#representativeAddress").val("");
	$("#irs").val("");
	$("#businessType").val("");

	$("#irs").attr("placeholder", "IRS");
	$("#businessType").attr("placeholder", "Business Type");
	$("#representativeName").attr("placeholder", "Representative Name");
	$("#representativePhone").attr("placeholder", "Representative Phone");
	$("#representativeAddress").attr("placeholder", "Representative Address");
	$("#modalTitleId").html("Add New Vendor");
	$("#save").html("<i class='fa fa-floppy-o'></i> &nbsp; Save");
	// for address table
	venresetAddressForm();
	$("#venaddressInfo").hide();
	venresetRepAddressForm();
	$("#venrepAddressInfo").hide();
	venaddressArray = [];
	venrepAddressArray = [];
	$("#venaddressDetails").hide();
	$("#venaddressDetails").html("");
	$("#venrepAaddressDetails").hide();
	$("#venrepAaddressDetails").html("");
}

$.validator
		.addMethod(
				"validateURL",
				function(value, element) {
					var myURL = value;
					var expression = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/gi;
					var regex = new RegExp(expression);
					return (myURL.match(regex));
				}, "Enter valid URL");
$("#vendor_form").validate({
	// Rules for form validation
	rules : {
		vendorName : {
			required : true,
		},
		vendorLegalName : {
			required : true,
		},
		vendorLegalStatus : {
			required : true,
		},
		vendorOwnership : {
			required : true,
		},
		vendorAddress : {
			required : true,
		},
		vendorPhoneNo : {
			required : true,
			digits : true,
		},
		vendorFax : {
			digits : true,
		},
		vendorEmail : {
			required : true,
			email : true,
		},
		vendorWebsite : {
			required : true,
			validateURL : true
		},
		representativePhone : {
			digits : true,
		}
	},

	// Messages for form validation
	messages : {
		vendorName : {
			required : "Enter the vendor name",
		},
		vendorLegalName : {
			required : "Enter the vendor legal name",
		},
		vendorLegalStatus : {
			required : "Enter the vendor legal status",
		},
		vendorOwnership : {
			required : "Enter the vendor ownership",
		},
		vendorAddress : {
			required : "Enter the vendor address",
		},
		vendorPhoneNo : {
			required : "Enter the phone number",
			digits : "Enter the phone number in digits",
		},
		vendorFax : {
			digits : "Enter the fax in digits",
		},
		vendorEmail : {
			required : "Enter the vendor email",
			email : "Enter valid email",
		},
		vendorWebsite : {
			required : "Enter the vendor website",
			validateURL : "Enter valid URL",
		},
		representativePhone : {
			digits : "Enter the phone in digits",
		}
	},
	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForAddNewVendor(form.id);
	}
});

function ajaxCallForAddNewVendor(formId) {
	if (venaddressArray.length == 0) {
		$('#venaddressErr').css("display", "block");
	} else {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "addVendorAction",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					if (result.ajaxResult == "success") {
						vensaveAddressDetails(result.vendorId);
						gritterForSucessMsgs("A record of vendor type has been successfully saved.");
						getVendorDetails();
						resetVendorForm();
					} else {
						$("#vendorRemoteModal").modal("hide");
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					laddaRef.stop();
					resetVendorForm();
					$("#vendorRemoteModal").modal("hide");
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
	}
}

function getVendorDetails() {

	vendorDetails = "vendorDetails";
	$
			.ajax({
				url : "getVendorDetailsForJobs",
				type : "POST",
				contentType : 'text/plain',
				data : '{"vendorDetails": "' + vendorDetails + '"}',
				success : function(result) {
					vendorDetails = JSON.parse(result);
					if (vendorDetails.ajaxResult == "success") {
						$('#vendorList').find('option').remove().end().append(
								'<option value=""> Choose Vendor</option>');
						var vendorsList = $("#vendorList");
						for (var i = 0; i < vendorDetails.allVendorList.length; i++) {
							vendorsList
									.append("<option value="
											+ vendorDetails.allVendorList[i].vendorId
											+ ">"
											+ vendorDetails.allVendorList[i].vendorLegalName
											+ "</option>");
						}
						vendorsList
								.append("<option value='other'>Other</option>");
						$("#vendorRemoteModal").modal('hide');
						$("#jobDetailModal").modal('show');
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ vendorDetails.reason);
					}
				}
			});
}
//address for vendor
function vensaveAddressDetails(id){
	var addressData= {};
	var address = [];
	addressData.moduleId = id;
	addressData.moduleName = "vendorDirectory";
	address.push( venaddressArray);
	address.push( venrepAddressArray);
	addressData.address = address;
	var jobDetails = '{"addressData" :'+JSON.stringify(addressData)+'}';
	$
	.ajax({
		url : 'saveAddressDetails',
		data : jobDetails,
		type : 'POST',
		contentType:'application/json',
		success : function(data) {
			var data = JSON.parse(data);
//			$("#" + name).val("");
//			getAllJobDocuments(jobId);
			if (data.ajaxResult != "success") {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ data.reason);
			}
		},
		error : function() {
			alert("Some problem occured");
		}
	});
}
function vensaveAddressForm(){
	var dataObj = {
			address_line_1 : $("#venaddress_line_1").val(),
			address_line_2 : $("#venaddress_line_2").val(),
			state : $("#venstate option:selected").text(),
			city : $("#vencity option:selected").text(),
			zipCode : $("#venzipCode").val(),
			addressType : "Office"
		};
	venshowOfficeAddressForm(dataObj);
}
function venshowOfficeAddressForm(dataObj){
	var fullAddress = "";
	if(dataObj.address_line_1 !=""){
		fullAddress+= dataObj.address_line_1 + ", ";
	}else{
		$("#venaddressLine1Err").css("display","block");
		$("#venaddressLine1Err").prev().addClass("state-error");
	}
	if(dataObj.address_line_2 !=""){
		fullAddress+= dataObj.address_line_2 + ", ";
	}
	if(dataObj.state !="" && dataObj.state !="Select State"){
		fullAddress+= dataObj.state + ", ";
	}else{
		$("#venaddressStateErr").css("display","block");
		$("#venaddressStateErr").prev().addClass("state-error");
	}
	if(dataObj.city !=""&& dataObj.city !="Select City"){
		fullAddress+= dataObj.city + ", ";
	}else{
		$("#venaddressCityErr").css("display","block");
		$("#venaddressCityErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode !=""){
		fullAddress+= dataObj.zipCode + ", ";
	}else{
		$("#venaddressZipCodeErr").css("display","block");
		$("#venaddressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	venaddressArray.push(dataObj);
	var id = venaddressArray.length - 1;
	var file = "<section class='col col-lg-12' id='venaddress-"+id+"'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='venshowAddress("+id+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='vensetAddressToDelete("+id+ ");'></i></p></section>";
	$("#venaddressDetails").append(file);
	$("#venaddressDetails").show();
	$("#venaddressInfo").hide();
	venresetAddressForm();
	}
}

function vensaveRepAddressForm(){
	var dataObj = {
			address_line_1 : $("#venrepAddress_line_1").val(),
			address_line_2 : $("#venrepAddress_line_2").val(),
			state : $("#venrepState option:selected").text(),
			city : $("#venrepCity option:selected").text(),
			zipCode : $("#venrepZipCode").val(),
			addressType : "Representative"
		};
	venshowRepAddressForm(dataObj);
}
function showRepAddressForm(dataObj){
	var fullAddress = "";
	if(dataObj.address_line_1 !=""){
		fullAddress+= dataObj.address_line_1 + ", ";
	}else{
		$("#venrepAddressLine1Err").css("display","block");
		$("#venrepAddressLine1Err").prev().addClass("state-error");
	}
	if(dataObj.address_line_2 !=""){
		fullAddress+= dataObj.address_line_2 + ", ";
	}
	if(dataObj.state !="" && dataObj.state !="Select State"){
		fullAddress+= dataObj.state + ", ";
	}else{
		$("#venrepAddressStateErr").css("display","block");
		$("#venrepAddressStateErr").prev().addClass("state-error");
	}
	if(dataObj.city !=""&& dataObj.city !="Select City"){
		fullAddress+= dataObj.city + ", ";
	}else{
		$("#venrepAddressCityErr").css("display","block");
		$("#venrepAddressCityErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode !=""){
		fullAddress+= dataObj.zipCode + ", ";
	}else{
		$("#venrepAddressZipCodeErr").css("display","block");
		$("#venrepAddressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	venrepAddressArray.push(dataObj);
	var id = venrepAddressArray.length - 1;
	var file = "<section class='col col-lg-12' id='venrepAddress-"+id+"'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='venshowRepAddress("+id+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='vendeleteRepAddress("+id+ ");'></i></p></section>";
	$("#venrepAaddressDetails").append(file);
	$("#venrepAaddressDetails").show();
	$("#venrepAddressInfo").hide();
	venresetRepAddressForm();
	}
}

function venshowAddress(id){
	var object = venaddressArray[id];
	$("#venaddressFormId").val(id);
	$("#venaddress_line_1").val(object.address_line_1);
	$("#venaddress_line_2").val(object.address_line_2);
	$("#venstate option").each(function() {
		  if($(this).text() == object.state) {
		    $(this).attr('selected', 'selected');  
		    var value = $(this).val();
			flag = "vencity";
			getCities(value,flag,object.city);
		  }                        
		});
	$('#venzipCode').val(object.zipCode);
	$("#venaddressInfo").show();
	$("#venaddressSaveButton").hide();
	$("#venaddressUpdateButton").show();
}


function venshowRepAddress(id){
	var object = venrepAddressArray[id];
	$("#venrepAddressId").val(id);
	$("#venrepAddress_line_1").val(object.address_line_1);
	$("#venrepAddress_line_2").val(object.address_line_2);
	$("#venrepState option").each(function() {
		  if($(this).text() == object.state) {
		    $(this).attr('selected', 'selected'); 
		    var value = $(this).val();
			flag = "venrepCity";
			getCities(value,flag,object.city);
		  }                        
		});
	$('#venrepZipCode').val(object.zipCode);
	$("#venrepAddressInfo").show();
	$("#venrepAddressSaveButton").hide();
	$("#venrepAddressUpdateButton").show();
}

function venupdateAddressForm(){
	var addressId = $("#venaddressFormId").val();
	venaddressArray[addressId].address_line_1 = $("#venaddress_line_1").val();
	venaddressArray[addressId].address_line_2 = $("#venaddress_line_2").val();
	venaddressArray[addressId].state = $("#venstate option:selected").text();
	venaddressArray[addressId].city = $("#vencity option:selected").text();
	venaddressArray[addressId].zipCode = $("#venzipCode").val();
	var fullAddress = "";
	if(venaddressArray[addressId].address_line_1 !=""){
		fullAddress+= venaddressArray[addressId].address_line_1 + ", ";
	}else{
		$("#venaddressLine1Err").css("display","block");
		$("#venaddressLine1Err").prev().addClass("state-error");
	}
	if(venaddressArray[addressId].address_line_2 !=""){
		fullAddress+= venaddressArray[addressId].address_line_2 + ", ";
	}
	if(venaddressArray[addressId].state !="" && venaddressArray[addressId].state !="Select State"){
		fullAddress+= venaddressArray[addressId].state + ", ";
	}else{
		$("#venaddressStateErr").css("display","block");
		$("#venaddressStateErr").prev().addClass("state-error");
	}
	if(venaddressArray[addressId].city !="" && venaddressArray[addressId].city !="Select City"){
		fullAddress+= venaddressArray[addressId].city + ", ";
	}else{
		$("#venaddressCityErr").css("display","block");
		$("#venaddressCityErr").prev().addClass("state-error");
	}
	if(venaddressArray[addressId].zipCode !=""){
		fullAddress+= venaddressArray[addressId].zipCode + ", ";
	}else{
		$("#venaddressZipCodeErr").css("display","block");
		$("#venaddressZipCodeErr").prev().addClass("state-error");
	}
	if(venaddressArray[addressId].zipCode != "" && venaddressArray[addressId].address_line_1 != "" && venaddressArray[addressId].state != "" && venaddressArray[addressId].state != "Select State"
		&& venaddressArray[addressId].city != "" && venaddressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='venshowAddress("+addressId+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='vensetAddressToDelete("+addressId+ ");'></i></p>"
	$("#venaddress-"+addressId).html("");
	$("#venaddress-"+addressId).html(updateFile);
	venresetAddressForm();
	$("#venaddressInfo").hide();
	}
}


function venupdateRepAddressForm(){
	var addressId = $("#venrepAddressId").val();
	venrepAddressArray[addressId].address_line_1 = $("#venrepAddress_line_1").val();
	venrepAddressArray[addressId].address_line_2 = $("#venrepAddress_line_2").val();
	venrepAddressArray[addressId].state = $("#venrepState option:selected").text();
	venrepAddressArray[addressId].city = $("#venrepCity option:selected").text();
	venrepAddressArray[addressId].zipCode = $("#venrepZipCode").val();
	var fullAddress = "";
	if(venrepAddressArray[addressId].address_line_1 !=""){
		fullAddress+= venrepAddressArray[addressId].address_line_1 + ", ";
	}else{
		$("#venrepAddressLine1Err").css("display","block");
		$("#venrepAddressLine1Err").prev().addClass("state-error");
	}
	if(venrepAddressArray[addressId].address_line_2 !=""){
		fullAddress+= venrepAddressArray[addressId].address_line_2 + ", ";
	}
	if(venrepAddressArray[addressId].state !="" && venrepAddressArray[addressId].state !="Select State"){
		fullAddress+= venrepAddressArray[addressId].state + ", ";
	}else{
		$("#venrepAddressStateErr").css("display","block");
		$("#venrepAddressStateErr").prev().addClass("state-error");
	}
	if(venrepAddressArray[addressId].city !="" && venrepAddressArray[addressId].city !="Select City"){
		fullAddress+= venrepAddressArray[addressId].city + ", ";
	}else{
		$("#venrepAddressCityErr").css("display","block");
		$("#venrepAddressCityErr").prev().addClass("state-error");
	}
	if(venrepAddressArray[addressId].zipCode !=""){
		fullAddress+= venrepAddressArray[addressId].zipCode + ", ";
	}else{
		$("#venrepAddressZipCodeErr").css("display","block");
		$("#venrepAddressZipCodeErr").prev().addClass("state-error");
	}
	if(venrepAddressArray[addressId].zipCode != "" && venrepAddressArray[addressId].address_line_1 != "" && venrepAddressArray[addressId].state != "" && venrepAddressArray[addressId].state != "Select State"
		&& repAddressArray[addressId].city != "" && repAddressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='venshowRepAddress("+addressId+")' style='cursor:pointer;'\>"+ fullAddress +"</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='vendeleteRepAddress("+addressId+ ");'></i></p>"
	$("#venrepAddress-"+addressId).html("");
	$("#venrepAddress-"+addressId).html(updateFile);
	venresetAddressForm();
	$("#venrepAddressInfo").hide();
	}
}


function vensetAddressToDelete(id){
		$("#venaddress-"+id).remove();
		delete venaddressArray[id];
		venaddressArray.pop(id);
}

function vendeleteRepAddress(id){
		$("#venrepAddress-"+id).remove();
		delete venrepAddressArray[id];
		venrepAddressArray.pop(id);
}

$("#venaddress_line_1").on("change keyup",function(){
	if($("#venaddress_line_1").val() != ""){
		$("#venaddress_line_1").parent().removeClass("state-error");
		$('#venaddressLine1Err').css('display', 'none');
	}
});
$("#venstate").on("change keyup",function(){
	if($("#venstate").val() != ""){
		$("#venstate").parent().removeClass("state-error");
		$('#venaddressStateErr').css('display', 'none');
	}
});
$("#vencity").on("change keyup",function(){
	if($("#vencity").val() != ""){
		$("#vencity").parent().removeClass("state-error");
		$('#venaddressCityErr').css('display', 'none');
	}
});
$("#venzipCode").on("change keyup",function(){
	if($("#venzipCode").val() != ""){
		$("#venzipCode").parent().removeClass("state-error");
		$('#venaddressZipCodeErr').css('display', 'none');
	}
});
function venresetAddressForm(){
	$("#venaddressSaveButton").show();
	$("#venaddressUpdateButton").hide();
	$("#venaddress_line_1").val("");
	$("#venaddress_line_1").parent().removeClass("state-success");
	$("#venaddress_line_2").val("");
	$("#venaddress_line_2").parent().removeClass("state-success");
	$("#venstate").val("");
	$("#venstate").parent().removeClass("state-success");
	$("#vencity").find('option').remove().end().append(
	'<option value="">Select City</option>');
	$("#vencity").parent().removeClass("state-success");
	$('#venzipCode').val("");
	$("#venzipCode").parent().removeClass("state-success");
	$("#venaddressInfo").hide();
	$("#venaddressErr").css("display","none");
	$("#venaddress_line_1").parent().removeClass("state-error");
	$('#venaddressLine1Err').css('display', 'none');
	$("#venstate").parent().removeClass("state-error");
	$('#venaddressStateErr').css('display', 'none');
	$("#vencity").parent().removeClass("state-error");
	$('#venaddressCityErr').css('display', 'none');
	$("#venzipCode").parent().removeClass("state-error");
	$('#venaddressZipCodeErr').css('display', 'none');
}
$("#venrepAddress_line_1").on("change keyup",function(){
	if($("#venrepAddress_line_1").val() != ""){
		$("#venrepAddress_line_1").parent().removeClass("state-error");
		$('#venrepAddressLine1Err').css('display', 'none');
	}
});
$("#venrepState").on("change keyup",function(){
	if($("#venrepState").val() != ""){
		$("#venrepState").parent().removeClass("state-error");
		$('#venrepAddressStateErr').css('display', 'none');
	}
});
$("#venrepCity").on("change keyup",function(){
	if($("#venrepCity").val() != ""){
		$("#venrepCity").parent().removeClass("state-error");
		$('#venrepAddressCityErr').css('display', 'none');
	}
});
$("#venrepZipCode").on("change keyup",function(){
	if($("#venrepZipCode").val() != ""){
		$("#venrepZipCode").parent().removeClass("state-error");
		$('#venrepAddressZipCodeErr').css('display', 'none');
	}
});
function venresetRepAddressForm(){
	$("#venrepAddressSaveButton").show();
	$("#venrepAddressUpdateButton").hide();
	$("#venrepAddress_line_1").val("");
	$("#venrepAddress_line_1").parent().removeClass("state-success");
	$("#venrepAddress_line_2").val("");
	$("#venrepAddress_line_2").parent().removeClass("state-success");
	$("#venrepState").val("");
	$("#venrepState").parent().removeClass("state-success");
	$('#venrepCity').find('option').remove().end().append(
	'<option value="">Select City</option>');
	$("#venrepCity").parent().removeClass("state-success");
	$('#venrepZipCode').val("");
	$("#venrepZipCode").parent().removeClass("state-success");
	$("#venrepAddress_line_1").parent().removeClass("state-error");
	$('#venrepAddressLine1Err').css('display', 'none');
	$("#venrepState").parent().removeClass("state-error");
	$('#venrepAddressStateErr').css('display', 'none');
	$("#venrepCity").parent().removeClass("state-error");
	$('#venrepAddressCityErr').css('display', 'none');
	$("#venrepZipCode").parent().removeClass("state-error");
	$('#venrepAddressZipCodeErr').css('display', 'none');
}
$("#venrepState").on("change",function(e){
	var value = $("#venrepState").val();
	flag = "venrepCity";
	if(value != ""){
		getCities(value,flag);
	}
	else{
		$('#venrepCity').find('option').remove().end().append(
		'<option value="">Select City</option>');
	}
});


$("#venstate").on("change",function(e){
	var value = $("#venstate").val();
	flag = "vencity";
	if(value != ""){
		getCities(value,flag);
	}
	else{
		$('#vencity').find('option').remove().end().append(
		'<option value="">Select City</option>');
	}
});
$("#custrepState").on("change",function(e){
	var value = $("#custrepState").val();
	flag = "custrepCity";
	if(value != ""){
		getCities(value,flag);
	}
	else{
		$('#custrepCity').find('option').remove().end().append(
		'<option value="">Select City</option>');
	}
});


$("#custstate").on("change",function(e){
	var value = $("#custstate").val();
	flag = "custcity";
	if(value != ""){
		getCities(value,flag);
	}
	else{
		$('#custcity').find('option').remove().end().append(
		'<option value="">Select City</option>');
	}
});
// Single DOWNLOAD
function setDocumentToDowload(fileId) {
//	console.log(fileId);
	if (fileId != undefined && fileId != null && fileId != "") {
//		console.log(fileId);
		$("#fileId").val(fileId);
		$("#jobUploadForm_single").submit();
	}
}
// Bulk Download
function bulkDownload() {
	$("#bulk_jobId").val($("#jobId").val());
	$("#jobUploadForm_bulk").submit();
}
function setDocumentToDelete(docId, id,jobfieldName) {
//console.log(docId, id,jobfieldName);
	documetnIdToDel = docId;
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This Document will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							ajaxCallToDeleteSelDocument(documetnIdToDel, id,jobfieldName);
						}
					});

}

// Incomplete method need to send jobId,DocId,FieldName @Rishabh
function ajaxCallToDeleteSelDocument(documetnIdToDel, id,jobfieldName) {
//	console.log(jobfieldName.name);
	var dataObj = {
			docId : documetnIdToDel,
			jobId : id,
			fieldName : jobfieldName.name,
	};

	$
			.ajax({
				url : "deleteDocument",
				type : "GET",
				data : dataObj,
				success : function(result) {
					result = JSON.parse(result);
					// CLearing Project Table
					if (result.ajaxResult == "success") {
						getAllJobDocuments(id);
						gritterForSucessMsgs("Document has been deleted successfully.");
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
				},
				error : function() {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

// address codes
function saveAddressForm() {
	var dataObj = {
		address_line_1 : $("#address_line_1").val(),
		address_line_2 : $("#address_line_2").val(),
		state : $("#state option:selected").text(),
		city : $("#city option:selected").text(),
		zipCode : $("#zipCode").val(),
		addressType : "Office"
	};
	showOfficeAddressForm(dataObj);
}
function showOfficeAddressForm(dataObj) {
	var fullAddress = "";
	if (dataObj.address_line_1 != "") {
		fullAddress += dataObj.address_line_1 + ", "
	}else{
		$("#addressLine1Err").css("display","block");
		$("#addressLine1Err").prev().addClass("state-error");
	}
	if (dataObj.address_line_2 != "") {
		fullAddress += dataObj.address_line_2 + ", "
	}
	if (dataObj.state != "" && dataObj.state != "Select State") {
		fullAddress += dataObj.state + ", "
	}else{
		$("#addressStateErr").css("display","block");
		$("#addressStateErr").prev().addClass("state-error");
	}
	if (dataObj.city != "" && dataObj.city != "Select City") {
		fullAddress += dataObj.city + ", "
	}else{
		$("#addressCityErr").css("display","block");
		$("#addressCityErr").prev().addClass("state-error");
	}
	if (dataObj.zipCode != "") {
		fullAddress += dataObj.zipCode + ", "
	}else{
		$("#addressZipCodeErr").css("display","block");
		$("#addressZipCodeErr").prev().addClass("state-error");
	}
	if(dataObj.zipCode != "" && dataObj.address_line_1 != "" && dataObj.state != "" && dataObj.state != "Select State"
		&& dataObj.city != "" && dataObj.city != "Select City"){
		fullAddress = fullAddress.substr(0, fullAddress.length - 2);
		addressArray.push(dataObj);
		var id = addressArray.length - 1;
		var file = "<section class='col col-lg-12' id='address-"
				+ id
				+ "'><p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showAddress("
				+ id
				+ ")' style='cursor:pointer;'\>"
				+ fullAddress
				+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setAddressToDelete("
				+ id + ");'></i></p></section>";
		$("#addressDetails").append(file);
		$("#addressDetails").show();
		$("#addressInfo").hide();
		resetAddressForm();
	}
}
function showAddress(id) {
	var object = addressArray[id];
	$("#addressFormId").val(id);
	$("#address_line_1").val(object.address_line_1);
	$("#address_line_2").val(object.address_line_2);
	$("#state option").each(function() {
		if ($(this).text() == object.state) {
			$(this).attr('selected', 'selected');
			var value = $(this).val();
			flag = "city";
			getCities(value,flag, object.city);
		}
	});
	$('#zipCode').val(object.zipCode);
	$("#addressInfo").show();
	$("#addressSaveButton").hide();
	$("#addressUpdateButton").show();
}
function updateAddressForm() {
	var addressId = $("#addressFormId").val();
	addressArray[addressId].address_line_1 = $("#address_line_1").val();
	addressArray[addressId].address_line_2 = $("#address_line_2").val();
	addressArray[addressId].state = $("#state option:selected").text();
	addressArray[addressId].city = $("#city option:selected").text();
	addressArray[addressId].zipCode = $("#zipCode").val();
	var fullAddress = "";
	if (addressArray[addressId].address_line_1 != "") {
		fullAddress += addressArray[addressId].address_line_1 + ", "
	}else{
		$("#addressLine1Err").css("display","block");
		$("#addressLine1Err").prev().addClass("state-error");
	}
	if (addressArray[addressId].address_line_2 != "") {
		fullAddress += addressArray[addressId].address_line_2 + ", "
	}
	if (addressArray[addressId].state != ""
			&& addressArray[addressId].state != "Select State") {
		fullAddress += addressArray[addressId].state + ", "
	}else{
		$("#addressStateErr").css("display","block");
		$("#addressStateErr").prev().addClass("state-error");
	}
	if (addressArray[addressId].city != ""
			&& addressArray[addressId].city != "Select City") {
		fullAddress += addressArray[addressId].city + ", "
	}else{
		$("#addressCityErr").css("display","block");
		$("#addressCityErr").prev().addClass("state-error");
	}
	if (addressArray[addressId].zipCode != "") {
		fullAddress += addressArray[addressId].zipCode + ", "
	}else{
		$("#addressZipCodeErr").css("display","block");
		$("#addressZipCodeErr").prev().addClass("state-error");
	}
	if(addressArray[addressId].zipCode != "" && addressArray[addressId].address_line_1 != "" && addressArray[addressId].state != "" && addressArray[addressId].state != "Select State"
		&& addressArray[addressId].city != "" && addressArray[addressId].city != "Select City"){
	fullAddress = fullAddress.substr(0, fullAddress.length - 2);
	var updateFile = "<p style='margin: 0 0 0px !important;padding: 4px 8px 3px 9px;'><a title='Address' onclick='showAddress("
			+ addressId
			+ ")' style='cursor:pointer;'\>"
			+ fullAddress
			+ "</a><i title='Delete Address' class='fa fa-times' style='float: right;cursor: pointer;color: #dd5a43;' onclick='setAddressToDelete("
			+ addressId + ");'></i></p>"
	$("#address-" + addressId).html("");
	$("#address-" + addressId).html(updateFile);
	resetAddressForm();
	$("#addressInfo").hide();
	}
}

function setAddressToDelete(id) {
	if (addressArray[id].addressId != undefined) {
		deleteAddress(addressArray[id].addressId, id, "office");
	} else {
		$("#address-" + id).remove();
		delete addressArray[id];
		addressArray.pop(id);
	}
	highlightCount();
}

function deleteAddress(id, addressId, type) {
	var data = {
		"moduleId" : id
	};
	$.ajax({
		url : "deleteAddress",
		type : "POST",
		data : data,
		success : function(result) {
			result = JSON.parse(result);
			if (result.ajaxResult == "success") {
				if (type == "represenetative") {
					$("#repAddress-" + addressId).remove();
					delete repAddressArray[addressId];
				} else if (type == "office") {
					$("#address-" + addressId).remove();
					delete addressArray[addressId];
					addressArray.pop(id);
				}
//				gritterForSucessMsgs("Address has been deleted successfully");
			}
		},
		error : function(result) {
			console.log(result);
			// gritterForErrorMsgs('Some Problem Occured. Please refresh the
			// page and try again. If this problem persists, please contact Dev
			// Team.');
		}
	});
}

$("#address_line_1").on("change keyup",function(){
	if($("#address_line_1").val() != ""){
		$("#address_line_1").parent().removeClass("state-error");
		$('#addressLine1Err').css('display', 'none');
	}
});
$("#state").on("change keyup",function(){
	if($("#state").val() != ""){
		$("#state").parent().removeClass("state-error");
		$('#addressStateErr').css('display', 'none');
	}
});
$("#city").on("change keyup",function(){
	if($("#city").val() != ""){
		$("#city").parent().removeClass("state-error");
		$('#addressCityErr').css('display', 'none');
	}
});
$("#zipCode").on("change keyup",function(){
	if($("#zipCode").val() != ""){
		$("#zipCode").parent().removeClass("state-error");
		$('#addressZipCodeErr').css('display', 'none');
	}
});

function resetAddressForm() {
	$("#addressSaveButton").show();
	$("#addressUpdateButton").hide();
	$("#address_line_1").val("");
	$("#address_line_1").parent().removeClass("state-success");
	$("#address_line_1").parent().removeClass("state-error");
	$("#address_line_2").val("");
	$("#address_line_2").parent().removeClass("state-success");
	$("#state").val("");
	$("#state").parent().removeClass("state-success");
	$("#state").parent().removeClass("state-error");
	$("#city").find('option').remove().end().append(
			'<option value="">Select City</option>');
	$("#city").parent().removeClass("state-success");
	$("#city").parent().removeClass("state-error");
	$('#zipCode').val("");
	$("#zipCode").parent().removeClass("state-success");
	$("#zipCode").parent().removeClass("state-error");
	$('#addressErr').css('display', 'none');
	$('#addressStateErr').css('display', 'none');
	$('#addressCityErr').css('display', 'none');
	$('#addressZipCodeErr').css('display', 'none');
	$('#addressLine1Err').css('display', 'none');
}
function addressUpdate(addressId) {

	for (var u = 0; u < addressDetails.allAddressDetails.length; u++) {
		if (addressId == addressDetails.allAddressDetails[u].addressId) {
			$("#addressId").val(addressDetails.allAddressDetails[u].addressId);
			$("#addressType").val(
					addressDetails.allAddressDetails[u].localAddressType);
			$("#address").val(addressDetails.allAddressDetails[u].address);
			break;
		}
	}
}

function getStates() {
	// console.log('enetered')
	$.ajax({
		url : "getStates",
		type : "GET",
		success : function(result) {
			statesData = JSON.parse(result);
			if (statesData.ajaxResult == "success") {
				var elementId = $("#state");
				var custstate = $("#custstate");
				var custrepState = $("#custrepState");
				var venstate = $("#venstate");
				var venrepState = $("#venrepState");
//				elementId.options.length = 1;
				for (var i = 0; i < statesData.reason.length; i++) {
					var optn = "<option value='"+statesData.reason[i].state_Code+"'>"+statesData.reason[i].state+"</option>";
//					optn.text = statesData.reason[i].state;
//					optn.value = statesData.reason[i].state_Code;
//					elementId.options.add(optn);
					elementId.append(optn);
					custstate.append(optn);
					custrepState.append(optn);
					venstate.append(optn);
					venrepState.append(optn);
//					console.log(elementId);
				}
			}
		},
		error : function() {
			console.log("error");
		}
	});
}

$("#state").on(
		"change",
		function(e) {
			var value = $("#state").val();
			if (value != "") {
				flag = "city";
				getCities(value,flag);
			} else {
				$('#city').find('option').remove().end().append(
						'<option value="">Select City</option>');
			}
		});

function getCities(value,flag, cityName) {
	$.ajax({
		url : "getCities",
		type : "GET",
		data : {
			"stateCode" : value
		},
		success : function(result) {
			citiesData = JSON.parse(result);
			// console.log(responseData)
			if (citiesData.ajaxResult == "success") {
				var elementId = document.getElementById("city");
				elementId.options.length = 1;
				for (var i = 0; i < citiesData.reason.length; i++) {
					var optn = document.createElement("OPTION");
					optn.text = citiesData.reason[i];
					optn.value = i;
					elementId.options.add(optn);
				}
				if (cityName != undefined) {
					$("#"+flag+" option").each(function() {
						if ($(this).text() == cityName) {
							$(this).attr('selected', 'selected');
						}
					});
				}
			}
		},
		error : function() {
			console.log("error");
		}
	});
}

function getAddressDetails(id) {
	var data = {
		"moduleId" : id,
		"moduleName" : "JOB_SETUP"
	};
	$
			.ajax({
				url : "getAddressDetails",
				type : "POST",
				data : data,
				success : function(result) {
					addressDetails = JSON.parse(result);
					for (var i = 0; i < addressDetails.allAddressDetails.length; i++) {
						var dataObj = {
							addressId : addressDetails.allAddressDetails[i].addressId,
							moduleId : addressDetails.allAddressDetails[i].moduleId,
							address_line_1 : addressDetails.allAddressDetails[i].address_line_1,
							address_line_2 : addressDetails.allAddressDetails[i].address_line_2,
							state : addressDetails.allAddressDetails[i].state,
							city : addressDetails.allAddressDetails[i].city,
							zipCode : addressDetails.allAddressDetails[i].zipCode,
							addressType : addressDetails.allAddressDetails[i].address_type
						};
						if (addressDetails.allAddressDetails[i].address_type == "Office") {
							showOfficeAddressForm(dataObj);
						} else if (addressDetails.allAddressDetails[i].address_type == "Representative") {
							showRepAddressForm(dataObj);
						}
					}

				},
				error : function(result) {
					console.log(result);
					// gritterForErrorMsgs('Some Problem Occured. Please refresh
					// the page and try again. If this problem persists, please
					// contact Dev Team.');
				}
			});
}