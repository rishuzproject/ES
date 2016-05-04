package com.elecnor.ecosystem.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping(value = "/getDesignHierarchy", method = RequestMethod.POST)
public class DesignationHierarchyDetailController {
	
	public @ResponseBody HashMap<String, Object> getDesignHierarchy(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String designationId) {
		
		
		return null;
	}
	
	@RequestMapping(value="/addDesignationInDepartment",method=RequestMethod.POST)
	public @ResponseBody HashMap<String,String> addDesignationInDepartment(){
		
		return null;
		
	}

}
