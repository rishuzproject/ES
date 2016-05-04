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
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.DepartmentUserMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DepartmentUserMappingDAO;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class DepartmentUserMappingController {
	@Autowired
	DepartmentUserMappingDAO departmentUserMappingDAO;

	@ModelAttribute(value = "/departmentUserMapping")
	public DepartmentUserMapping getsLicense() {
		return new DepartmentUserMapping();
	}

	@RequestMapping(value = "/getAssignedUsersByDept", method = RequestMethod.POST)
	public @ResponseBody String getAssignedUsersByDept(HttpSession session, HttpServletRequest request)
			throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		try {
			Long deptId = Long.parseLong(request.getParameter("deptId"));
			UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
			ArrayList<DepartmentUserMapping> usersDept = departmentUserMappingDAO.getAssignedUsersByDept(userDetail
					.getDomainDetail().getDomainId(), deptId);
			resultMap.put("deptUsers", usersDept);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/getUserDetailsByDomain", method = RequestMethod.POST)
	public @ResponseBody String getUserDetailsByDomain(HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			ArrayList<UserDetail> userList = departmentUserMappingDAO
					.getUserDetailsByDomain(domainDetail.getDomainId());
			map.put("ajaxResult", "success");
			map.put("usersList", userList);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(map);
	}

}
