dashboardDetailsTable = null;
var selectedRow = null;
var dataTableForRole;
var pagefunction = function() {

	var responsiveHelper_dt_basic = undefined;
	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};

	getUserRoleDetails();
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

$(document).ready(function() {

});

function redirectToParticularApp(urlValue, subcriptionId, projId,
		userRoleNameTosend) {
	var roleName = "";
	if (userRoleNameTosend == "jobsAsSupervisor") {
		roleName = "Supervisor";
	} else if (userRoleNameTosend == "jobsAsManager") {
		roleName = "Manager";
	} else if (userRoleNameTosend == "jobsAsExecutive") {
		roleName = "Executive";
	} else if (userRoleNameTosend == "jobsAsForeman") {
		roleName = "Foreman";
	} else if (userRoleNameTosend == "jobsAsPurchasingAgent") {
		roleName = "Purchasing Agent";
	} else if (userRoleNameTosend == "jobDetailsForAdmin") {
		roleName = "Admin";
	}

	var dataObj = {
		roleNameToGetBean : roleName
	};
	if (urlValue != "") {

		$
				.ajax({
					type : 'POST',
					url : 'getUserRoleBeanByRoleName',
					data : dataObj,
					async : false,
					success : function(result) {
						var userRoleBean = JSON.parse(result);
						if (userRoleBean.ajaxResult == "success") {
							if (projId != 0 && projId != null && projId != ""
									&& projId != undefined) {
								redirectToDownstreamApplication(
										urlValue,
										userRoleDetails.allUserRoleDetails.logedUserDetails.emailId,
										userRoleDetails.allUserRoleDetails.logedUserDetails.password,
										userRoleDetails.isBelcoUser,
										userRoleBean.userRoleBeanToReturned.roleId,
										userRoleBean.userRoleBeanToReturned.roleName,
										subcriptionId, projId);
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

}

function getUserRoleDetails() {
	userRoleDetails = "userRoleDetails";
	$
			.ajax({
				url : "getUserRoleDetails",
				type : "POST",
				contentType : 'text/plain',
				data : '{"userRoleDetails": "' + userRoleDetails + '"}',
				success : function(result) {
					userRoleDetails = JSON.parse(result);
					showAppLinks();
					if (userRoleDetails.ajaxResult != "failure") {
						if (userRoleDetails.isReportNTrackUser) {
							$("#otherCompaniesInfo").show();
							initOtherCompaniesInfoTable();
						} else {
							if (userRoleDetails.allUserRoleDetails.jobDetailsForAdmin.length == 0
									&& userRoleDetails.allUserRoleDetails.jobsAsExecutive.length == 0
									&& userRoleDetails.allUserRoleDetails.jobsAsForeman.length == 0
									&& userRoleDetails.allUserRoleDetails.jobsAsManager.length == 0
									&& userRoleDetails.allUserRoleDetails.jobsAsPurchasingAgent.length == 0
									&& userRoleDetails.allUserRoleDetails.jobsAsSupervisor.length == 0) {
								$("#noRolesAssigned").show();
							} else {
								if (userRoleDetails.allUserRoleDetails.jobDetailsForAdmin.length != 0) {
									$("#widget-grid-admin").show();
									addTable("adminData", "tableIdForAdmin");
									initializeDataTable("tableIdForAdmin");
									fillDataForRole("jobDetailsForAdmin");
								}
								if (userRoleDetails.allUserRoleDetails.jobsAsExecutive.length != 0) {
									$("#widget-grid-executive").show();
									addTable("executiveData",
											"tableIdForExecutive");
									initializeDataTable("tableIdForExecutive");
									fillDataForRole("jobsAsExecutive");
								}
								if (userRoleDetails.allUserRoleDetails.jobsAsForeman.length != 0) {
									$("#widget-grid-foreman").show();
									addTable("foremanData", "tableIdForForeman");
									initializeDataTable("tableIdForForeman");
									fillDataForRole("jobsAsForeman");
								}
								if (userRoleDetails.allUserRoleDetails.jobsAsManager.length != 0) {
									$("#widget-grid-manager").show();
									addTable("managerData", "tableIdForManager");
									initializeDataTable("tableIdForManager");
									fillDataForRole("jobsAsManager");
								}
								if (userRoleDetails.allUserRoleDetails.jobsAsPurchasingAgent.length != 0) {
									$("#widget-grid-purchasingAgent").show();
									addTable("purchasingAgentData",
											"tableIdForPurchasingAgent");
									initializeDataTable("tableIdForPurchasingAgent");
									fillDataForRole("jobsAsPurchasingAgent");
								}
								if (userRoleDetails.allUserRoleDetails.jobsAsSupervisor.length != 0) {
									$("#widget-grid-supervisor").show();
									addTable("supervisorData",
											"tableIdForSupervisor");
									initializeDataTable("tableIdForSupervisor");
									fillDataForRole("jobsAsSupervisor");
								}
							}
						}
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being:"
								+ userRoleDetails.reason);
					}
				},
				error : function() {
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}
/*
 * This method will check whether the session is expired or not if not we are
 * making any link(mngUsr) clicked manually
 * so that it would redirect to session expire page
 */
function navigateToDownstreamApp(appUrl, userName, password, isBelcoUser,
		userRoleId, userRoleName, subscriptionId, projId) {
	$.ajax({
		url : "navigateToDownstreamApp",
		type : "GET",
		contentType : 'text/plain',
		success : function(result) {
			if (result == "success") {
				redirectToDownstreamApplication(appUrl, userName, password,
						isBelcoUser, userRoleId, userRoleName, subscriptionId,
						projId);
			} else {
				$('#mngUser').click();
			}
		}
	});

}

function showAppLinks() {

	if (userRoleDetails.allUserRoleDetails.allusageList != undefined
			&& userRoleDetails.allUserRoleDetails.allusageList != null) {

		if (userRoleDetails.isBelcoUser) {

			for (var ul = 0; ul < userRoleDetails.allUserRoleDetails.allusageList.length; ul++) {

				var linkLen = userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink.length;
				if (userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
						.charAt(linkLen - 1) == "/") {
					$('#appsUlId')
							.append(
									"<div class='btn-header transparent pull-right'><span style=''> <a style='cursor:pointer !important' class='appBtnstyle' href='javascript:void(0);' onclick=\"javascript:navigateToDownstreamApp('"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
											+ "login','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.emailId
											+ "','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.password
											+ "','"
											+ userRoleDetails.isBelcoUser
											+ "','"
											+ userRoleDetails.userRoleId
											+ "','"
											+ ((userRoleDetails.userRoleName != undefined && userRoleDetails.userRoleName != null) ? userRoleDetails.userRoleName
													: "")
											+ "','','')\">"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationName
											+ '</a>' + '</span><div>');
				} else {
					$('#appsUlId')
							.append(
									"<div class='btn-header transparent pull-right'><span style=''> <a  style='cursor:pointer !important' class='appBtnstyle' href='javascript:void(0);' onclick=\"javascript:navigateToDownstreamApp('"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
											+ "/login','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.emailId
											+ "','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.password
											+ "','"
											+ userRoleDetails.isBelcoUser
											+ "','"
											+ userRoleDetails.userRoleId
											+ "','"
											+ ((userRoleDetails.userRoleName != undefined && userRoleDetails.userRoleName != null) ? userRoleDetails.userRoleName
													: "")
											+ "','','')\">"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationName
											+ '</a>' + '</span><div>');
				}
			}

		} else {

			for (var ul = 0; ul < userRoleDetails.allUserRoleDetails.allusageList.length; ul++) {

				var linkLen = userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink.length;
				if (userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
						.charAt(linkLen - 1) == "/") {
					$('#appsUlId')
							.append(
									"<div class='btn-header transparent pull-right'><span style=''> <a target='_blank' style='cursor:pointer !important' class='appBtnstyle' href='javascript:void(0);' onclick=\"javascript:navigateToDownstreamApp('"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
											+ "login','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.emailId
											+ "','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.password
											+ "','"
											+ userRoleDetails.isBelcoUser
											+ "','"
											+ userRoleDetails.userRoleId
											+ "','"
											+ ((userRoleDetails.userRoleName != undefined && userRoleDetails.userRoleName != null) ? userRoleDetails.userRoleName
													: "")
											+ "','"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].subscriptionId
											+ "','')\">"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationName
											+ '</a>' + '</span><div>');
				} else {
					$('#appsUlId')
							.append(
									"<div class='btn-header transparent pull-right'><span style=''> <a target='_blank' style='cursor:pointer !important' class='appBtnstyle' href='javascript:void(0);' onclick=\"javascript:navigateToDownstreamApp('"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
											+ "/login','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.emailId
											+ "','"
											+ userRoleDetails.allUserRoleDetails.logedUserDetails.password
											+ "','"
											+ userRoleDetails.isBelcoUser
											+ "','"
											+ userRoleDetails.userRoleId
											+ "','"
											+ ((userRoleDetails.userRoleName != undefined && userRoleDetails.userRoleName != null) ? userRoleDetails.userRoleName
													: "")
											+ "','"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].subscriptionId
											+ "','')\">"
											+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationName
											+ '</a>' + '</span><div>');
				}
			}
		}

	}
}

function fillDataForRole(role) {
	dataTableForRole.fnClearTable();
	var originalContractValue, margin, lastSubmittedMPR, manager, foreman, purchasingAgent, alerts;

	var indexSelectTagStart = "<select name='appName' onchange='redirectToParticularApp(this.value,";
	var indexSelectTagDefault = ");'> <option value=''>Choose one application</option>";
	var indexURL1 = "";
	var indexURL2 = "";
	if (userRoleDetails.allUserRoleDetails.allusageList != undefined
			&& userRoleDetails.allUserRoleDetails.allusageList != null) {

		if (userRoleDetails.isBelcoUser) {
			indexSelectTagStart += "\"\",";
			for (var ul = 0; ul < userRoleDetails.allUserRoleDetails.allusageList.length; ul++) {

				var linkLen = userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink.length;

				if (userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
						.charAt(linkLen - 1) == "/") {
					indexURL1 += "<option value='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
							+ "login'><a  href='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
							+ "'>"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationName
							+ "</a></option>";

				} else {
					indexURL1 += "<option value='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
							+ "/login'><a  href='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationLink
							+ "'>"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationName
							+ "</a></option>";

				}
			}
		} else {
			indexSelectTagStart += "\"\",";
			for (var ul = 0; ul < userRoleDetails.allUserRoleDetails.allusageList.length; ul++) {

				var linkLen = userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink.length;

				if (userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
						.charAt(linkLen - 1) == "/") {
					indexURL1 += "<option value='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
							+ "login?subscriptionId="
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].subscriptionId
							+ "'><a  href='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
							+ "'>"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationName
							+ "</a></option>";

				} else {
					indexURL1 += "<option value='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
							+ "/login?subscriptionId="
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].subscriptionId
							+ "'><a  href='"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationLink
							+ "'>"
							+ userRoleDetails.allUserRoleDetails.allusageList[ul].applicationPlanDirectory.applicationDirectory.applicationName
							+ "</a></option>";

				}
			}
		}

	}

	// var indexSelectTagEnd = "</select>";

	for (var t = 0; t < userRoleDetails.allUserRoleDetails[role].length; t++) {
		originalContractValue = "", margin = "", lastSubmittedMPR = "",
				manager = "", foreman = "", purchasingAgent = "",
				alerts = "<a><u><b>Alerts</b></u></a>";
		if (userRoleDetails.allUserRoleDetails[role][t].originalContractValue != undefined
				&& userRoleDetails.allUserRoleDetails[role][t].originalContractValue != null) {
			originalContractValue = userRoleDetails.allUserRoleDetails[role][t].originalContractValue
		}
		if (userRoleDetails.allUserRoleDetails[role][t].manager != undefined
				&& userRoleDetails.allUserRoleDetails[role][t].manager != null) {
			manager = userRoleDetails.allUserRoleDetails[role][t].manager.firstName
		}
		if (userRoleDetails.allUserRoleDetails[role][t].foreman != undefined
				&& userRoleDetails.allUserRoleDetails[role][t].foreman != null) {
			foreman = userRoleDetails.allUserRoleDetails[role][t].foreman.firstName
		}
		if (userRoleDetails.allUserRoleDetails[role][t].purchasingAgent != undefined
				&& userRoleDetails.allUserRoleDetails[role][t].purchasingAgent != null) {
			purchasingAgent = userRoleDetails.allUserRoleDetails[role][t].purchasingAgent.firstName
		}
		userRoleDetails.allUserRoleDetails[role][t].originalContractValue
		var supervisor = "";
		if(userRoleDetails.allUserRoleDetails[role][t].supervisor != undefined
				&& userRoleDetails.allUserRoleDetails[role][t].supervisor != 'undefined'){
			supervisor = userRoleDetails.allUserRoleDetails[role][t].supervisor.firstName;
		}
		dataTableForRole
				.fnAddData(
						[
								userRoleDetails.allUserRoleDetails[role][t].jobName
										+ " - "
										+ userRoleDetails.allUserRoleDetails[role][t].jobNumber,
								originalContractValue,
								margin,
								lastSubmittedMPR,
								supervisor,
								manager, foreman, purchasingAgent, alerts ],
						// indexSelectTagStart
						// + ""
						// + userRoleDetails.allUserRoleDetails[role][t].jobId
						// + ",\"" + role + "\""
						// + indexSelectTagDefault + indexURL1
						// + indexSelectTagEnd,
						// userRoleDetails.allUserRoleDetails[role][t].jobId ],
						false);
	}
	dataTableForRole.fnDraw();
}
function addTable(parentDivId, tableId) {
	$("#" + parentDivId)
			.append(
					"<div id='dt_basic_wrapper'"
							+ "class='dataTables_wrapper form-inline no-footer'>"
							+ '<table id="'
							+ tableId
							+ '"'
							+ 'class="table table-striped table-bordered table-hover dataTable no-footer"'
							+ 'width="100%" role="grid" aria-describedby="dt_basic_info"'
							+ 'style="width: 100%;">'
							+ '<thead>'
							+ '<tr role="row">'
							+ '<th data-class="expand" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" Project: activate to sort column ascending"'
							+ 'style="width: 30px;">Project'
							+ '<th data-class="expand" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" Contract Value: activate to sort column ascending"'
							+ 'style="width: 60px;">Contract Value'
							+ '<th data-hide="phone,tablet" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" Margin: activate to sort column ascending"'
							+ 'style="width: 140px;">Margin'
							+ '<th data-hide="phone,tablet" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" : activate to sort column ascending"'
							+ 'style="width: 10px;">Last Submitted MPR</th>'
							+ '<th data-hide="phone,tablet" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" : activate to sort column ascending"'
							+ 'style="width: 10px;">Supervisor</th>'
							+ '<th data-hide="phone,tablet" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" : activate to sort column ascending"'
							+ 'style="width: 10px;">Manager</th>'
							+ '<th data-hide="phone,tablet" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" : activate to sort column ascending"'
							+ 'style="width: 10px;">Foreman</th>'
							+ '<th data-hide="phone,tablet" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" : activate to sort column ascending"'
							+ 'style="width: 10px;">Purchasing Agent</th>'
							+ '<th data-hide="phone,tablet" class="sorting" tabindex="0"'
							+ 'aria-controls="dt_basic" rowspan="1" colspan="1"'
							+ 'aria-label=" : activate to sort column ascending"'
							+ 'style="width: 10px;">Alerts</th>'
							// + '<th data-hide="phone,tablet" class="sorting"
							// tabindex="0"'
							// + 'aria-controls="dt_basic" rowspan="1"
							// colspan="1"'
							// + 'aria-label=" : activate to sort column
							// ascending"'
							/* + 'style="width: 10px;">Apps</th>' */
							+ '<tbody>' + '</tbody>');
}
function initializeDataTable(tableId) {
	var responsiveHelper_dt_basic = undefined;
	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	dataTableForRole = $('#' + tableId)
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
						aoColumns : [ {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[0] == null ? "" : full[0];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[1] == null ? "" : full[1];
//								return full[1];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[2] == null ? "" : full[2];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[3] == null ? "" : full[3];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[4] == null ? "" : full[4];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[5] == null ? "" : full[5];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[6] == null ? "" : full[6];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[7] == null ? "" : full[7];
							}
						}, {
							sClass : "center",
							mRender : function(data, type, full) {
								return full[8] == null ? "" : full[8];
							}
						// }
						// , {
						// mData : null,
						// sClass : "center",
						// mRender : function(data, type, full) {
						// return full[9];
						// }
						} ],
						"preDrawCallback" : function() {
							// Initialize the responsive
							// datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#' + tableId), breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});
}