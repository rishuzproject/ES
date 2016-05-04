package com.elecnor.ecosystem.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.elecnor.ecosystem.service.DepartmentDirectoryService;
import com.elecnor.ecosystem.service.DesignationDirectoryService;
import com.elecnor.ecosystem.util.Utility;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DesignationDirectoryController {

	@Autowired
	Utility util;

	@Autowired
	DesignationDirectoryService desigService;

	@Autowired
	DepartmentDirectoryService departmentService;

	@RequestMapping("/designationDetails")
	public String getDesignationDetailPage() {
		return "designationDetails";
	}

	@RequestMapping(value = "/getDesignationInDeptUnit", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getDesignationInDeptUnit(HttpServletRequest request, HttpServletResponse response, @RequestBody String departmentId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<DesignationDirectory> deptList = null;
		try {

			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = mapper.getFactory();
			JsonParser parser = factory.createParser(departmentId);
			JsonNode actualObj = mapper.readTree(parser);

			JsonNode valueObj = actualObj.get("departmentId");
			String deptId = String.valueOf(valueObj.textValue());

			if (null != deptId && !deptId.isEmpty()) {
				deptList = desigService.getDesignationInDeptUnit(deptId);
			}

			resultMap = util.responseBuilderCustom(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@RequestMapping(value = "/addDesignationHierarchyInDepartment", method = RequestMethod.POST)
	public @ResponseBody HashMap<Object, Object> addDesignationHierarchyInDepartment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "designationId") int designationId, @RequestParam(value = "parentDesignationId") String parentDesignationIds,
			@RequestParam(value = "childDesignationId") String childDesignationIds) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			DesignationDirectory selectedDesignationDirectory = desigService.getDesignationById(designationId);
			String status = desigService.addOrUpdateDesignationHierarchyInDepartment(selectedDesignationDirectory, parentDesignationIds, childDesignationIds);
			resultMap = util.responseBuilder(status);

		} catch (Exception e) {
			e.printStackTrace();
			util.handleException(resultMap, e);
		}

		return resultMap;
	}

	@RequestMapping(value = "/addDesignationInDepartment", method = RequestMethod.POST)
	public @ResponseBody HashMap<Object, Object> addDesignationInDepartment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "departmentId") int departmentId,
			@ModelAttribute("designationDetails") DesignationDirectory designationDirectory) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			DepartmentDirectory selectedDepartmentDirectory = departmentService.getDeptDirectoryById(departmentId);
			String status = desigService.addOrUpdateDesignationInDepartment(designationDirectory, selectedDepartmentDirectory);
			resultMap = util.responseBuilder(status);

		} catch (Exception e) {
			e.printStackTrace();
			util.handleException(resultMap, e);
		}

		return resultMap;

	}

	@RequestMapping(value = "/deleteDesignationInDepartment", method = RequestMethod.POST)
	public @ResponseBody HashMap<Object, Object> deleteDesignationInDepartment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "designationId") int designationId) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			String status = desigService.deleteDesignation(designationId);
			resultMap = util.responseBuilder(status);
		} catch (Exception e) {
			e.printStackTrace();
			util.handleException(resultMap, e);
		}
		return resultMap;
	}

}
