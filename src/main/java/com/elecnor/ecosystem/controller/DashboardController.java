package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRoles;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingTrackingDAO;
import com.elecnor.ecosystem.dao.DomainDetailDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.dao.UserRolesDAO;
import com.elecnor.ecosystem.service.DashboardService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class DashboardController {

	@Autowired
	DomainApplicationPlanMappingDAO domainApplicationPlanMappingDAO;
	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;
	@Autowired
	DomainApplicationPlanMappingTrackingDAO domainApplicationPlanMappingTrackingDAO;
	@Autowired
	JobsDetailDAO jobsDetailDAO;
	@Autowired
	DashboardService dashboardService;
	@Autowired
	UserRolesDAO userRolesDAO;
	@Autowired
	DomainDetailDAO domainDetailDAO;

	/*@RequestMapping("/dashboard")
	public String getDashboardPage() {
		return "dashboard";
	}*/

	/**
	 * getting the information from domainApplicationPlanMapping
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getUsageDetail", method = RequestMethod.POST)
	@ResponseBody
	public String getAllusageDetails(HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			Long domainId = domainDetail.getDomainId();

			// ArrayList<ApplicationDirectory> allApplicationDetailsList =
			// applicationDetailsDAO.getApplicationDetails();
			ArrayList<DomainApplicationPlanMapping> allusageList = domainApplicationPlanMappingDAO.getDetails(domainId);
			// ArrayList<DomainApplicationPlanMappingTracking>
			// allusageListFromTracking =
			// domainApplicationPlanMappingTrackingDAO.getDetailsFromTracking(domainId);
			ArrayList<JobDetail> allJobDetails = jobsDetailDAO.getAllJobDetails(domainId);
			resultMap.put("allusageList", allusageList);
			// resultMap.put("allApplicationDetailsList",
			// allApplicationDetailsList);
			// resultMap.put("allusageListFromTracking",
			// allusageListFromTracking);
			resultMap.put("logedUserDetails", userDetails);
			resultMap.put("allJobDetails", allJobDetails);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/getUserRoleDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getUserRoleDetails(HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		HashMap<Object, Object> allUserRoleDetails = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			Boolean isTemporaryUser = (Boolean) session.getAttribute("isTemporaryUser");
			if (!isTemporaryUser) {
				UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
				Boolean isBelcoUser = (Boolean) session.getAttribute("isBelcoUser");
				Boolean isReportNTrackUser = (Boolean) session.getAttribute("isReportnTrackUser");
				Long userRole = (Long) session.getAttribute("userRole");
				DomainDetail domainDetail = userDetail.getDomainDetail();
				allUserRoleDetails = dashboardService.getUserJobDetails(userDetail, domainDetail.getDomainId(), isBelcoUser, userRole, session);
				// if user is belcoUser then details in the session are
				// ArrayList<ApplicationDirectory> else
				// ArrayList<DomainApplicationPlanMapping>
				resultMap.put("userRoleId", session.getAttribute("userRole"));
				resultMap.put("userRoleName", userRolesDAO.getUserRoleNameById((Long) session.getAttribute("userRole")));
				resultMap.put("isBelcoUser", isBelcoUser);
				resultMap.put("isReportNTrackUser", isReportNTrackUser);
				resultMap.put("statusReturned", "200");
				resultMap.put("allUserRoleDetails", allUserRoleDetails);
			}
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/getUserRoleBeanByRoleName", method = RequestMethod.POST)
	@ResponseBody
	public String getUserRoleBeanByRoleName(HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {

			UserRoles userRoleBeanToReturn = dashboardService.getUserRoleBeanByRoleName(request.getParameter("roleNameToGetBean"));
			resultMap.put("ajaxResult", "success");
			resultMap.put("userRoleBeanToReturned", userRoleBeanToReturn);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/getOtherCompanyInfo", method = RequestMethod.GET)
	@ResponseBody
	public String getOtherCompanyInfo(HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();

		try{
			resultMap.put("ajaxResult", "success");
			resultMap.put("otherCompanyInfoDetails", domainDetailDAO.getDomainDetail());
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
}
