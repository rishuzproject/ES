package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationPurchasePlanDAO;
import com.elecnor.ecosystem.helper.ApplicationPurchasePlanHelper;
import com.elecnor.ecosystem.service.ApplicationPurchasePlanService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class ApplicationPurchasePlanController {

	@Autowired
	ApplicationPurchasePlanService applicationPurchasePlanService;

	@Autowired
	ApplicationPurchasePlanDAO applicationPurchasePlanDAO;

	/*@RequestMapping("/applicationPurchasePlanDetails")
	public String getApplicationDetailPage() {
		return "applicationPurchasePlanDetails";
	}

	@ModelAttribute(value = "/purchasePlanForm")
	public ApplicationPlanDirectory registerPlan() {
		return new ApplicationPlanDirectory();
	}*/

	/**
	 * Method for add purchased plan
	 * @param applicationPlan
	 * @param planId
	 * @param applicationId
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addPurchasePlanAction", method = RequestMethod.POST)
	public @ResponseBody String addPurchasePlan(
			@ModelAttribute("purchasePlanForm") ApplicationPlanDirectory applicationPlan,
			@RequestParam(value = "planId", defaultValue = "-1") long planId,
			@RequestParam(value = "applicationName", defaultValue = "-1") long applicationId,
			HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		ApplicationDirectory applicationDirectory = new ApplicationDirectory();

		try {
			UserDetail userDetail = (UserDetail) session.getAttribute("selectedUserDetails");
			applicationPlan.setPlanId(planId);
			applicationDirectory.setApplicationId(applicationId);
			String result = applicationPurchasePlanService.setAddPurchasePlan(applicationPlan, applicationDirectory, userDetail);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for get purchased plan list
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getPurchasePlanAction", method = RequestMethod.POST)
	@ResponseBody
	public String getPurchasePlan(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			ArrayList<ApplicationPlanDirectory> allPlanList = applicationPurchasePlanDAO.getPurchasePlanDAO();
			map.put("ajaxResult", "success");
			map.put("allPlanList", allPlanList);
		} catch (Exception e) {
			map = util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	/**
	 * Method for delete
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deletePlanAction", method = RequestMethod.POST)
	@ResponseBody
	public String deletePlan(HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		ApplicationPurchasePlanHelper applicationPurchasePlanHelper = new ApplicationPurchasePlanHelper();
		int planId;
		try {
			planId = applicationPurchasePlanHelper.setApplicationPlanToDelete(request);
			String result = applicationPurchasePlanDAO.deletePlan(planId);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

}
