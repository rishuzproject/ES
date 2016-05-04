package com.elecnor.ecosystem.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.UserRolesDAO;
import com.elecnor.ecosystem.helper.HomeHelper;
import com.elecnor.ecosystem.service.DomainApplicationPlanMappingService;
import com.elecnor.ecosystem.service.HomeService;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.elecnor.ecosystem.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class HomeController {

	/*@Autowired
	LogsDetailService logDetailService;*/
	@Autowired
	DomainApplicationPlanMappingService applicationPlanMappingService;
	@Autowired
	UserRolesDAO userRolesDAO;
	@Autowired
	HomeService homeService;

	/**
	 * this method is used for getting user details from session
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getUserDetailsForHome", method = RequestMethod.POST)
	@ResponseBody
	public String getUserDetailsForHome(HttpSession session) {
		Boolean isTempUser = (Boolean) session.getAttribute("isTemporaryUser");
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		HomeHelper homeHelper = new HomeHelper();
		try {

			resultMap = homeHelper.checkForTemporaryUserAndGetUserDetails(isTempUser,
					session.getAttribute("selectedUser"), session.getAttribute("userRolesList"),
					session.getAttribute("userRole"));
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
		

	}

	/**
	 * Method for get application names
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getApplicationNames", method = RequestMethod.POST)
	@ResponseBody
	public String getApplicationNames(HttpSession session) {
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		Boolean isBelcouser = (Boolean) session.getAttribute("isBelcoUser");
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		Logger log = Logger.getLogger(HomeController.class.getName());
		try {
			log.error(userDetail);
			// if logged user is belco user then we are getting all applications
			// from applications directory and setting into recent applications
			// drop down list.
			// else we are getting only active applications per particular
			// domain from domainApplicationPlanDirectory
			resultMap  = homeService.getApplicationDetails(isBelcouser, userDetail.getDomainDetail());
			session.setAttribute("plans", resultMap.get("plans"));
			resultMap.put("logedUser", userDetail);
			resultMap.put("isBelcouser", isBelcouser);
			resultMap.put("userRoleId", session.getAttribute("userRole"));
			resultMap.put("userRoleName", userRolesDAO.getUserRoleNameById((Long) session.getAttribute("userRole")));
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResultForDate(resultMap);

	}

	@RequestMapping(value = "/checkUserType")
	@ResponseBody
	public String checkUserType(HttpSession session) {
		Map<String, Object> userType = new HashMap<String, Object>();
		String json = "";
		try {
			userType.put("isBelcouser", session.getAttribute("isBelcoUser"));
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MM-dd-yyyy").create();
			json = gson.toJson(userType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw e;
		}
		return json;
	}

	

}
