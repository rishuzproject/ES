package com.elecnor.ecosystem.helper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.elecnor.ecosystem.util.Utility;

public class DepartmentDetailsHelper {

	public int setDepartmentIdToDelete(HttpServletRequest request) throws Exception {

		int deptId = 0;
		try {
			if (request.getParameter("deptIdToDel") != null
					&& request.getParameter("deptIdToDel") != "") {
				deptId = Integer.parseInt(request.getParameter("deptIdToDel")
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return deptId;
	}

	public HashMap<Object, Object> getResultMap(String result, String resultant) throws Exception{
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			if (result == null & resultant == null || result == ""
					&& resultant == "") {
				resultMap.put("ajaxResult", "success");
			} else {
				resultMap = util.responseBuilder(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultMap;
	}
}
