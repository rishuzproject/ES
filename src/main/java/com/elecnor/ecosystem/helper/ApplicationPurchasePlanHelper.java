package com.elecnor.ecosystem.helper;

import javax.servlet.http.HttpServletRequest;

public class ApplicationPurchasePlanHelper {

	public int setApplicationPlanToDelete(HttpServletRequest request) {
		int planId = 0;
		if (request.getParameter("planIdToDel") != null&& request.getParameter("planIdToDel") != "") {
			planId = Integer.parseInt(request.getParameter("planIdToDel").trim());
		}
		return planId;
	}

}
