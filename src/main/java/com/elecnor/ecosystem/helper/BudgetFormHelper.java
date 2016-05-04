package com.elecnor.ecosystem.helper;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.elecnor.ecosystem.bean.BudgetCode;

public class BudgetFormHelper {

	public Long setActivityIdToDelete(HttpServletRequest request) throws Exception{

		Long activityId = 0L;
		try {
			if (request.getParameter("activityIdToDel") != null && request.getParameter("activityIdToDel") != "") {
				activityId = Long.parseLong(request.getParameter("activityIdToDel").trim());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}
		return activityId;
	}

	public int checkIfActivityNumberExists(ArrayList<BudgetCode> projList) throws Exception{
		try {
			if (!projList.isEmpty()) {
				return 1;
			}
			else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
