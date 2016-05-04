$(document).ready(function() {
	getApplicationLinksForBelcoUsers();
});

function getApplicationLinksForBelcoUsers() {
	$
			.ajax({
				url : "getApplicationLinksForBelcoUsers",
				type : "POST",
				success : function(result) {
					var response = JSON.parse(result);
					if (response.ajaxResult == "success") {
						if (response.logedUser != undefined
								&& response.logedUser != null) {
							if (response.applicationList != undefined
									&& response.applicationList != null) {
								for (var len = 0; len < response.applicationList.length; len++) {

									var linkLen = response.applicationList[len].applicationLink.length;
									if (response.applicationList[len].applicationLink
											.charAt(linkLen - 1) == "/") {
										if ((response.applicationList[len].applicationName).toLowerCase() == "mpr") {
											$("#mprAppAccessLinkId")
													.prop(
															"href","javascript:redirectToDownstreamApplication('"
															+response.applicationList[len].applicationLink
															+ "login','"
															+ response.logedUser.emailId
															+ "','"
															+ response.logedUser.password
															+ "','"
															+ response.isBelcouser
															+ "','"
															+ response.userRoleId
															+ "','"
															+ ((response.userRoleName != undefined && response.userRoleName != null)?response.userRoleName:"")
															+"','','')");
										} else if ((response.applicationList[len].applicationName).toLowerCase() == "pd") {
											$("#pdAppAccessLinkId")
													.prop(
															"href","javascript:redirectToDownstreamApplication('"
															+response.applicationList[len].applicationLink
																	+ "login','"
																	+ response.logedUser.emailId
																	+ "','"
																	+ response.logedUser.password
																	+ "','"
																	+ response.isBelcouser
																	+ "','"
																	+ response.userRoleId
																	+ "','"
																	+ ((response.userRoleName != undefined && response.userRoleName != null)?response.userRoleName:"")
																	+"','','')");
										} else if ((response.applicationList[len].applicationName).toLowerCase() == "fts") {
											$("#ftsAppAccessLinkId")
													.prop(
															"href", "javascript:redirectToDownstreamApplication('"
															+response.applicationList[len].applicationLink
																	+ "login','"
																	+ response.logedUser.emailId
																	+ "','"
																	+ response.logedUser.password
																	+ "','"
																	+ response.isBelcouser
																	+ "','"
																	+ response.userRoleId
																	+ "','"
																	+ ((response.userRoleName != undefined && response.userRoleName != null)?response.userRoleName:"")
																	+"','','')");
										}

									} else {
										if ((response.applicationList[len].applicationName).toLowerCase() == "mpr") {
											$("#mprAppAccessLinkId")
													.prop(
															"href","javascript:redirectToDownstreamApplication('"
															+response.applicationList[len].applicationLink+"')");
										} else if ((response.applicationList[len].applicationName).toLowerCase() == "pd") {
											$("#pdAppAccessLinkId")
													.prop(
															"href", "javascript:redirectToDownstreamApplication('"
															+response.applicationList[len].applicationLink
																	+ "/login','"
																	+ response.logedUser.emailId
																	+ "','"
																	+ response.logedUser.password
																	+ "','"
																	+ response.isBelcouser
																	+ "','"
																	+ response.userRoleId
																	+ "','"
																	+ ((response.userRoleName != undefined && response.userRoleName != null)?response.userRoleName:"")
																	+"','','')");
										} else if ((response.applicationList[len].applicationName).toLowerCase() == "fts") {
											$("#ftsAppAccessLinkId")
													.prop(
															"href", "javascript:redirectToDownstreamApplication('"
															+response.applicationList[len].applicationLink
																	+ "/login','"
																	+ response.logedUser.emailId
																	+ "','"
																	+ response.logedUser.password
																	+ "','"
																	+ response.isBelcouser
																	+ "','"
																	+ response.userRoleId
																	+ "','"
																	+ ((response.userRoleName != undefined && response.userRoleName != null)?response.userRoleName:"")
																	+"','','')");
										}

									}
								}
							}

						}
					}
				}
			});
}
