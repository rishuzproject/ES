package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.dao.UserRolesDAO;
import com.elecnor.ecosystem.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class BelcoLoginController {

	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;
	@Autowired
	UserRolesDAO userRolesDAO;

	/*@RequestMapping("/belcoLogin")
	public String getBelcoEcosystemLogin() {
		return "belcoLogin";
	}*/

	// method for getting loged user details for providing email id and password
	// to access PD Application
	@RequestMapping(value = "/getApplicationLinksForBelcoUsers")
	@ResponseBody
	public String getApplicationLinksForBelcoUsers(HttpSession session) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		Utility utility = new Utility();
		try {
			ArrayList<ApplicationDirectory> applicationList = applicationDetailsDAO.getApplicationDetails();
			userMap.put("logedUser", (UserDetail) session.getAttribute("selectedUser"));
			userMap.put("ajaxResult", "success");
			userMap.put("isBelcouser", (Boolean) session.getAttribute("isBelcoUser"));
			userMap.put("userRoleId", session.getAttribute("userRole"));
			userMap.put("userRoleName", userRolesDAO.getUserRoleNameById((Long) session.getAttribute("userRole")));
			userMap.put("applicationList", applicationList);
		} catch (Exception e) {
			e.printStackTrace();
			userMap.put("ajaxResult", "failure");
			userMap.put("reason", e.getMessage());
			utility.sendExceptionEmail(e);
		}
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MM-dd-yyyy").create();
		String json = gson.toJson(userMap);
		return json;
	}
}
