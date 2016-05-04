package com.elecnor.ecosystem.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.service.ApplicationDetailsService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */
@Controller
public class ApplicationDetailController {

	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;

	@Autowired
	ApplicationDetailsService applicationDetailsService;

	/*@RequestMapping("/applicationDetail")
	public String getApplicationDetailPage() {
		return "applicationDetail";
	}

	@ModelAttribute(value = "appDetailForm")
	public ApplicationDirectory getApplicationDetails() {
		return new ApplicationDirectory();
	}*/

	/**
	 * Method for get details of project
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getApplicationDetails", method = RequestMethod.POST)
	public @ResponseBody String getApplicationDetails(HttpSession session, HttpServletRequest request) {
		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			ArrayList<ApplicationDirectory> allApplicationDetailsList = applicationDetailsDAO.getApplicationDetails();
			resultMap.put("allApplicationDetailsList", allApplicationDetailsList);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * Handling Add,Update and Delete operations on the Record of Project Type
	 * Table
	 * 
	 * @param appDetailBean
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applicationDetailFormController", method = RequestMethod.POST)
	public @ResponseBody String handleProjectTypesOperations(
			@ModelAttribute("appDetailForm") ApplicationDirectory appDetailBean, HttpSession session,
			HttpServletRequest request) {

		session = request.getSession(false);
		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		String result = null;
		try {
			result = applicationDetailsService.addOrUpdateAppDetail(appDetailBean, userDetail);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for delete data of application
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteApplicationMethod", method = RequestMethod.POST)
	public @ResponseBody String deleteApplicationDetail(HttpServletRequest request) {
		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			long applicationId = Long.parseLong(request.getParameter("projId"));
			String result = applicationDetailsDAO.deleteApplicationDetailsRecord(applicationId);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
}
