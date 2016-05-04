package com.elecnor.ecosystem.controller;

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

import com.elecnor.ecosystem.bean.ApplicationUserRole;
import com.elecnor.ecosystem.dao.RoleDescriptionDAO;
import com.elecnor.ecosystem.service.RoleDescriptionService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class RolesDescriptionController {

	@Autowired
	RoleDescriptionDAO roleDescriptionDAO;
	@Autowired
	RoleDescriptionService roleDescriptionService;

	/*@RequestMapping("/rolesDescription")
	public String getrolesDescriptionPage() {
		return "rolesDescription";
	}

	@ModelAttribute(value = "roleDescriptionForm")
	public ApplicationUserRole getProjectType() {
		System.out.println("Initialising the bean");
		return new ApplicationUserRole();
	}*/

	/**
	 * Getting all the records from the Project Type Table
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getRoleDetails", method = RequestMethod.POST)
	public @ResponseBody String getRoleDetails(HttpSession session, HttpServletRequest request) {

		Utility util = new Utility();
		HashMap<Object, Object> resultantRoles = new HashMap<Object, Object>();
		try {
			session = request.getSession(false);
			resultantRoles.put("roleDetails", roleDescriptionDAO.getAllRoles());
		} catch (Exception e) {
			util.handleException(resultantRoles, e);
		}
		return util.getJsonResult(resultantRoles);
		
	}

	/**
	 * 
	 * @param session
	 * @param appUserRoleBean
	 * @param request
	 * @param actionOnRole
	 * @return
	 */
	@RequestMapping(value = "/rolesFormController", method = RequestMethod.POST)
	public @ResponseBody String handleOperationsOnRole(HttpSession session,
			@ModelAttribute("roleDescriptionForm") ApplicationUserRole appUserRoleBean, HttpServletRequest request,
			@RequestParam("roleAction") String actionOnRole) {

		String result = null;
		Utility util = new Utility();
		try {
			
			if (actionOnRole.equalsIgnoreCase("save") || actionOnRole.equalsIgnoreCase("update")
					|| actionOnRole.equalsIgnoreCase("saveandcontinue")) {
				result = roleDescriptionService.addOrUpdateRole(appUserRoleBean);
			} else if (actionOnRole.equalsIgnoreCase("delete")) {
				result = roleDescriptionDAO.deleteRole(appUserRoleBean.getRoleId());
			}
			if (result == null) {
				result = "success";
			}
		} catch (Exception e) {
			util.sendExceptionEmail(e);
		}
		return result;
	}
}
