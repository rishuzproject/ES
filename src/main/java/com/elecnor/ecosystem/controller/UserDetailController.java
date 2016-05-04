package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.DesignationDirectory;
import com.elecnor.ecosystem.bean.DesignationHierarchyDetails;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.OrganizationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRoles;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.UserRolesDAO;
import com.elecnor.ecosystem.dao.UserRolesMappingDAO;
import com.elecnor.ecosystem.helper.UserDetailHelper;
import com.elecnor.ecosystem.service.DepartmentDirectoryService;
import com.elecnor.ecosystem.service.DepartmentHierarchyService;
import com.elecnor.ecosystem.service.DesignationDirectoryService;
import com.elecnor.ecosystem.service.DesignationHierarchyDetailService;
import com.elecnor.ecosystem.service.OrganizationDirectoryService;
import com.elecnor.ecosystem.service.UserDetailService;
import com.elecnor.ecosystem.util.Utility;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class UserDetailController {
	

	@Autowired
	Utility util;

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	UserDetailService applicationUserDetailService;
	
	@Autowired
	UserDetailDAO applicationUserDetailDAO;
	
	@Autowired
	UserRolesDAO userRolesDAO;
	
	@Autowired
	DesignationHierarchyDetailService desigHerarchyService;

	@Autowired
	OrganizationDirectoryService orgService;

	@Autowired
	DesignationDirectoryService desigService;
	
	@Autowired
	UserRolesMappingDAO useRolesMappingDao;

	@Autowired
	DepartmentDirectoryService deptService;

	@Autowired
	DepartmentHierarchyService deptHierarchyService;

	/*@RequestMapping("/manageUser")
	public String getDashboardPage() {
		return "manageUsers";
	}

	@ModelAttribute(value = "/applicationUserForm")
	public UserDetail registerUserProfile() {
		return new UserDetail();
	}*/

	/**
	 * method for save and update application user
	 * @param applicationUserDetail
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "/getUserRolesToSelect", method = RequestMethod.POST)
	@ResponseBody
	public String getUserRolesToSelect() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			ArrayList<UserRoles> allRolesList = userRolesDAO.getAllUserRoles();
			map.put("ajaxResult", "success");
			map.put("allRolesList", allRolesList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}
	
	@RequestMapping(value = "/saveOrUpdateApplicationUserAction", method = RequestMethod.POST)
	public @ResponseBody HashMap<Object, Object> saveOrUpdateApplicationUser(@ModelAttribute("applicationUserForm") UserDetail applicationUserDetail, HttpSession session, HttpServletRequest request) {
		String[] selectedRolesNames = request.getParameter("selectedRolesNames").split(",");
		String[] selectedRolesId = request.getParameter("selectedRolesId").split(",");
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {

			OrganizationDirectory orgDir = null;
			if (!request.getParameter("organisationName").isEmpty()) {
				Long orgId = Long.valueOf(request.getParameter("organisationName"));
				orgDir = orgService.getOrganisationById(orgId);
				if (null != orgDir) {
					applicationUserDetail.setOrgUnitId(orgDir);
				}
			}

			DepartmentDirectory deptDir = null;
			if (!request.getParameter("departmentName").isEmpty()) {
				Integer deptId = Integer.valueOf(request.getParameter("departmentName"));
				deptDir = deptService.getDeptDirectoryById(deptId);
				if (null != deptDir) {
					applicationUserDetail.setDeptUnitId(deptDir);
				}
			}

			DesignationDirectory desigDir = null;
			if (!request.getParameter("designationName").isEmpty()) {
				Integer desigId = Integer.valueOf(request.getParameter("designationName"));
				desigDir = desigService.getDesignationById(desigId);
				if (null != desigDir) {
					applicationUserDetail.setDesigId(desigDir);
				}
			}

			UserDetail supervisorDetail = null;
			if (!request.getParameter("supervisorName").isEmpty()) {
				Long userDetailId = Long.valueOf(request.getParameter("supervisorName"));
				supervisorDetail = userDetailService.getUserDetailById(userDetailId);
				if (null != supervisorDetail) {
					applicationUserDetail.setSuperviserId(supervisorDetail);
				}
			}

			// applicationUserDetail.setDeptUnitId(deptDir);
			// applicationUserDetail.setDesigId(desigDir);
			// applicationUserDetail.setSuperviserId(supervisorDetail);

			UserDetail loggedInUser = (UserDetail) session.getAttribute("selectedUser");
			Boolean isBelcoUser = (Boolean) session.getAttribute("isBelcoUser");
			String result = userDetailService.saveOrUpdateUser(applicationUserDetail, isBelcoUser, loggedInUser, selectedRolesNames, selectedRolesId);
			resultMap = util.responseBuilder(result);

		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return resultMap;
	}

	/**
	 * method for getting all user list
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAllUserDetails(HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			Long domainId = domainDetail.getDomainId();
			ArrayList<UserDetail> allApplicationUserList = applicationUserDetailDAO.getAllUserDetails(domainId);
			resultMap.put("allApplicationUserList", allApplicationUserList);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	@RequestMapping(value = "/getAssociatedRoleDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAssociatedRoles(@RequestParam(value="userId")long userId,HttpServletRequest request){
		HashMap<String, Object> map=new HashMap<String, Object>();
		Utility util=new Utility();
		try{
			List<Integer> roleIds=useRolesMappingDao.getRoleIdsForUser(userId);
			map.put("roleIdsList", roleIds);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return util.getJsonResultWithoutExposeString(map);
	}

	/**
	 * method for deleting user
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/applicationUserDeleteAction", method = RequestMethod.POST)
	@ResponseBody
	public String setApplicationUserDelete(HttpServletRequest request,
			HttpSession session) {
		UserDetailHelper userDetailHelper = new UserDetailHelper();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			int userId = 0;
			userId = userDetailHelper.setUserIdToDelete(request);
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			int result = applicationUserDetailDAO.setApplicationUserDelete(userId, userDetails);
			if(result != 0){
				resultMap = util.responseBuilder(true);
			}else{
				resultMap = util.responseBuilder(false);
			}
			
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/*
	 * This method will return supervisor List
	 * 
	 * @Rishabh
	 */
	@RequestMapping(value = "/getSupervisorDetails", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getSupervisorDetails(HttpServletRequest request, HttpSession session, @RequestBody String designationId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		List<DepartmentHierarchyDetails> deptHierarchyList = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = mapper.getFactory();
			JsonParser parser = factory.createParser(designationId);
			JsonNode actualObj = mapper.readTree(parser);

			JsonNode desigObj = actualObj.get("designationId");
			JsonNode deptObj = actualObj.get("departmentId");

			Integer desigId = Integer.valueOf(desigObj.textValue());
			Integer deptId = Integer.valueOf(deptObj.textValue());
			
			/*
			 * Get all parent Departments for selected department to get all
			 * parent designation
			 */
//			deptHierarchyList = deptHierarchyService.getParentDepartment(deptId);
//			StringBuilder deptIds = new StringBuilder();
//			for (DepartmentHierarchyDetails info : deptHierarchyList) {
//				deptIds.append(info.getAssociatedDepartmentId().getDepartmentId()).append(",");
//			}
//			String departmentIds = deptIds.deleteCharAt(deptIds.lastIndexOf(",")).toString();
			
			/*
			 * get ALL parent designations
			 */
			List<DesignationDirectory> desigDirList = desigService.getDesignationInDeptUnit(String.valueOf(deptId));
			List<DesignationHierarchyDetails> desigHierarchyList = desigHerarchyService.getParentDesignationsForGivenId(desigId);
			
			/* get Supervisor from Designation */
			try{
				return util.responseBuilderCustom(userDetailService.getSupervisorListBasedOnDesigId(desigHierarchyList, desigDirList));
			} catch(Exception e) {
				return util.responseBuilderCustom(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	
	
	
}
