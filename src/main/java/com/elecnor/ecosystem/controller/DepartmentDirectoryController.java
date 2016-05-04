package com.elecnor.ecosystem.controller;

/*
 @Author Rishabh
 */

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
import com.elecnor.ecosystem.bean.OrganizationDirectory;
import com.elecnor.ecosystem.service.DepartmentDirectoryService;
import com.elecnor.ecosystem.service.OrganizationDirectoryService;
import com.elecnor.ecosystem.util.Utility;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DepartmentDirectoryController {

	@Autowired
	DepartmentDirectoryService deptDirService;

	@Autowired
	Utility util;
	
	@Autowired
	OrganizationDirectoryService organizationDirectoryService;
	
	@RequestMapping("/manageDepartments")
	public String getDashboardPage() {
		return "departmentView";
	}
	
	

	/*
	 * This method will get Department List Based onOrganisation Id Written For
	 * Add User Screen
	 * 
	 * @ Rishabh
	 */
	@RequestMapping(value = "/getDeptListInOrgUnit", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getDeptListInOrgUnit(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody String organisationId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {

			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = mapper.getFactory();
			JsonParser parser = factory.createParser(organisationId);
			JsonNode actualObj = mapper.readTree(parser);

			JsonNode valueObj = actualObj.get("organisationId");
			Long orgId = Long.valueOf(valueObj.textValue());

			List<DepartmentDirectory> deptList = deptDirService.getDeptListInOrgUnit(orgId);
			resultMap = util.responseBuilderCustom(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	@RequestMapping(value="/addDepartmentInOrganization",method=RequestMethod.POST)
	public @ResponseBody HashMap<Object,Object> addDepartmentInOrganization(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="organizationId") Long organisationId,@ModelAttribute("departmentDetail")DepartmentDirectory departmentDirectory){
		   HashMap<Object,Object> resultMap=new HashMap<Object,Object>();
             try{
            	 
            	 OrganizationDirectory selectedOrganizationDirectory=organizationDirectoryService.getOrganisationById(organisationId);
            	 String status=deptDirService.addorUpdateDepartment(departmentDirectory, selectedOrganizationDirectory);
                 resultMap=util.responseBuilder(status);
             
             }
             catch(Exception e){
            	 e.printStackTrace();
            	 util.handleException(resultMap, e);
             }
		
		return resultMap;
		
	}
	@RequestMapping(value="/addDepartmentHierarchyInOrganization",method=RequestMethod.POST)
	public @ResponseBody HashMap<Object,Object> addDepartmentHierarchyInOrganization(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="departmentId") int departmentId,@RequestParam(value="parentDepartmentId") List<Integer>  parentDepartmentIds,
			@RequestParam(value="childDepartmentId") List<Integer> childDepartmentIds){
		   HashMap<Object,Object> resultMap=new HashMap<Object,Object>();
             try{
            	 
            	 DepartmentDirectory  selectedDepartment=deptDirService.getDeptDirectoryById(departmentId);
            	 String status=deptDirService.addorUpdateDepartmentHierarchy(selectedDepartment, parentDepartmentIds, childDepartmentIds);
                 resultMap=util.responseBuilder(status);
             
             }
             catch(Exception e){
            	 e.printStackTrace();
            	 util.handleException(resultMap, e);
             }
		
		return resultMap;
		
	}
	@RequestMapping(value="/deleteDepartmentInOrganization",method=RequestMethod.POST)
	public @ResponseBody HashMap<Object,Object>  deleteDepartmentInOrganization(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="departmentId")int departmentId){
		HashMap<Object,Object> resultMap=new HashMap<Object,Object>();
		try{
			
			String status=deptDirService.deleteDepartment(departmentId);
			resultMap=util.responseBuilder(status);
		
		}
		catch(Exception e){
			e.printStackTrace();
			util.handleException(resultMap, e);
		}
	return resultMap;	
	}

	@RequestMapping(value="/getDepartmentsForHierarchy",method=RequestMethod.GET)
	public @ResponseBody HashMap<Object,Object> getDepartmentsForHierarchy(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="organizationId")int organizationId){
		HashMap<Object,Object> resultMap=new HashMap<Object,Object>();
		try{
			
		}
		catch(Exception e){
			
		}
		return null;
	}
}
