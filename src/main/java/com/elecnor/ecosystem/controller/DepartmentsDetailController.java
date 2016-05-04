package com.elecnor.ecosystem.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DepartmentUserMappingDAO;
import com.elecnor.ecosystem.dao.DepartmentsDetailDAO;
import com.elecnor.ecosystem.helper.DepartmentDetailsHelper;
import com.elecnor.ecosystem.service.DepartmentUserMappingService;
import com.elecnor.ecosystem.service.DepartmentsDetailService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class DepartmentsDetailController {

	@Autowired
	DepartmentsDetailService departmentsDetailService;

	@Autowired
	DepartmentsDetailDAO departmentsDetailDAO;

	@Autowired
	DepartmentUserMappingService departmentUserMappingService;
	@Autowired
	DepartmentUserMappingDAO departmentUserMappingDAO;

	/*@RequestMapping("/departmentDetails")
	public String getDepartmentDetailPage() {
		return "departmentDetails";
	}

	@ModelAttribute(value = "/departmentForm")
	public DepartmentType registerDepartment() {
		return new DepartmentType();
	}*/

	/**
	 * Method for add department data
	 * 
	 * @param departmentDetail
	 * @param departmentTypeAction
	 * @param departmentId
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addDepartmentAction", method = RequestMethod.POST)
	public @ResponseBody String addDepartment(
			@ModelAttribute("departmentForm") DepartmentType departmentDetail,
			@RequestParam("departmentTypeAction") String departmentTypeAction,
			@RequestParam("selectedUsersId") String selectedUsersId,
			@RequestParam("selectedStaffId") String selectedStaffId,
			@RequestParam(value = "departmentId", defaultValue = "-1") long departmentId,
			HttpSession session, HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		String resultant = null;
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DepartmentType result = null;
			DomainDetail domainDetail = userDetail.getDomainDetail();
			result = departmentsDetailService
					.setAddUpdateDepartmentDetailService(departmentId,
							departmentDetail, userDetail, domainDetail);
			if (!(selectedUsersId.equalsIgnoreCase("null") && selectedStaffId.equalsIgnoreCase("null"))) {
				resultant = departmentsDetailService.saveTypeOfUser(result,
						domainDetail, selectedUsersId, result, userDetail,
						selectedStaffId);
			}
			resultMap = util.responseBuilder(resultant);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for get department list
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getDepartmentDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAllDepartmentDetails(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			ArrayList<DepartmentType> allDepartmentList = departmentsDetailDAO
					.getAllDepartments(domainDetail.getDomainId());
			map.put("ajaxResult", "success");
			map.put("allDepartmentList", allDepartmentList);
		} catch (Exception e) {
			map = util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	/**
	 * Method for delete
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteDepartmentAction", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDepartment(HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		int deptId;
		DepartmentDetailsHelper departmentDetailsHelper = new DepartmentDetailsHelper();
		try {
			deptId = departmentDetailsHelper.setDepartmentIdToDelete(request);
			String result = departmentsDetailDAO.deleteDepartment(deptId);
			String resultant = departmentUserMappingDAO
					.deleteUserAssignmentstoDept(deptId);
			resultMap = departmentDetailsHelper.getResultMap(result, resultant);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for save data using template
	 * 
	 * @param confirmDepartmentUploadId
	 * @param fileUploaded
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/departmentTemplateController", method = RequestMethod.POST)
	@ResponseBody
	public String saveDepartmentTemplateDetails(
			@RequestParam(value = "confirmDepartmentUploadId", defaultValue = "-1") int confirmDepartmentUploadId,
			@RequestParam(value = "departmentTemplateFile") MultipartFile fileUploaded,
			HttpServletRequest request, HttpSession session) throws IOException {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		try {
			resultMap = departmentsDetailService.uploadDepartmentFile(
					fileUploaded, session, confirmDepartmentUploadId);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(resultMap);
	}
}
