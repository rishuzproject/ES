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

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.ProjectType;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.TypesOfProjectDAO;
import com.elecnor.ecosystem.service.TypeOfProjectsService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class TypesOfProjectsController {
	@Autowired
	TypesOfProjectDAO typesOfProjectDAO;
	@Autowired
	TypeOfProjectsService typesOfProjectService;
	
	/*@RequestMapping("/typesOfProject")
	public String getTypesOfProjectPage() {
		return "typesOfProject";
	}

	@ModelAttribute(value = "projectTypeForm")
	public ProjectType getProjectType() {
		System.out.println("Initialising the bean");
		return new ProjectType();
	}*/

	/**
	 * Getting all the records from the Project Type Table
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProjectTypeDetails", method = RequestMethod.POST) 
	public @ResponseBody String getProjectTypeDetails(HttpSession session,
			HttpServletRequest request) {
		
		ArrayList<ProjectType> allProjectTypes ;
		session = request.getSession(false);
		Utility util =new Utility();
		HashMap<Object, Object> resultantProjectTypes = new HashMap<Object, Object>();
		try{
			 UserDetail userDetails=(UserDetail)session.getAttribute("selectedUser");
			 DomainDetail domainDetail = userDetails.getDomainDetail();
			long signUpDomainId = domainDetail.getDomainId();
			allProjectTypes = typesOfProjectDAO.getAllProjectTypes(signUpDomainId);
			resultantProjectTypes.put("projectTypeDetails", allProjectTypes);
		}
	 catch (Exception e) {
		util.handleException(resultantProjectTypes, e);
	}
	return util.getJsonResult(resultantProjectTypes);
}
	// changed by hari
	/**
	 * Handling Add,Update and Delete operations on the Record of Project Type Table
	 * @param projTypeBean
	 * @param projectTypeAction
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/projectTypeFormController", method = RequestMethod.POST) 
	public @ResponseBody String handleProjectTypesOperations(
			@ModelAttribute("projectTypeForm") ProjectType projTypeBean,
			@RequestParam("projectTypeAction") String projectTypeAction,
			HttpSession session, HttpServletRequest request) {
		
		session = request.getSession(false);
		Utility util =new Utility();
		String result = null;
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try{
			if (projectTypeAction.equalsIgnoreCase("save")||projectTypeAction.equalsIgnoreCase("saveandcontinue") || projectTypeAction.equalsIgnoreCase("update")) {
				result = typesOfProjectService.addProjectType(projTypeBean,session);
			} else if (projectTypeAction.equalsIgnoreCase("Delete")) {
	             result = typesOfProjectDAO.deleteProjectTypeRecord(projTypeBean.getProjectTypeId());
			} 
			resultMap = util.responseBuilder(result);
		}
		catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	
	}
	

}
