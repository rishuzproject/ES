package com.elecnor.ecosystem.controller;

/*
 @Author Rishabh
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.OrganizationDirectory;
import com.elecnor.ecosystem.service.OrganizationDirectoryService;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class OrganizationDirectoryController {

	@Autowired
	OrganizationDirectoryService orgDirService;

	@Autowired
	Utility util;
	

	
	@ModelAttribute(value = "organizationDetailsForm")
	public OrganizationDirectory createOrganization(){
		return new OrganizationDirectory();
	}

	@RequestMapping("/organizationDetails")
	public String getorganizationDetailsPage() {
		return "organizationDetails";
	}

	/*
	 * This method will get All Organisations List Written For Add User Screen
	 * 
	 * @ Rishabh
	 */
	@RequestMapping(value = "/getOrganizations", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getOrganizations(HttpServletRequest request, HttpServletResponse response) {
		List<OrganizationDirectory> orgDirList = new ArrayList<OrganizationDirectory>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			orgDirList = orgDirService.getAllOrganizations();
			resultMap = util.responseBuilderCustom(orgDirList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
    @RequestMapping(value="/addOrUpdateOrganizationDetail",method=RequestMethod.POST)
    public @ResponseBody HashMap<Object,Object> addOrUpdateOrganizationDetail(@ModelAttribute("organizationDetailsForm")OrganizationDirectory organizationDetail){
    	HashMap<Object,Object>resultMap=new HashMap<Object,Object>();
    	String status=null;
    	try{
    		status=orgDirService.addOrUpdateOrganizationDetail(organizationDetail);
    		resultMap=util.responseBuilder(status);
    		
    	}
    	catch(Exception e){
    		resultMap=util.handleException(resultMap, e)  ;
    		}
    	return resultMap;
    }
    
    @RequestMapping(value="/deleteOrganization",method=RequestMethod.POST)
    public @ResponseBody HashMap<Object,Object>deleteOrganization(@RequestParam(value="organizationId")Long organizationId){
    	HashMap<Object,Object> resultmap=new HashMap<Object,Object>();
    	String status=null;
    	try{
    		
    		status=orgDirService.deleteOrganization(organizationId);
    		resultmap=util.responseBuilder(status);
    	}
    	catch(Exception e){
    		resultmap=util.handleException(resultmap, e)  ;
    	}
    	return resultmap;
    }
    
    
}
