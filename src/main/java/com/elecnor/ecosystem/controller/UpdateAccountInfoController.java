package com.elecnor.ecosystem.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.service.UpdateAccountInfoService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class UpdateAccountInfoController {

	@Autowired
	UpdateAccountInfoService updateAccountInfoService;

	/*@RequestMapping("/updateAccountInfo")
	public String getChangePasswordScreen() {
		return "updateAccountInfo";
	}*/

	/**
	 * this method is used for getting user details from session
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getUserDetailsForMyProfile", method = RequestMethod.POST)
	@ResponseBody
	public String getUserDetailsForMyProfile(HttpSession session) {

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util =new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			map.put("userDetails", userDetails);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);

	}

	/**
	 * method for updating password
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public @ResponseBody String updatePassword(HttpSession session, HttpServletRequest request) {

		String email = request.getParameter("email");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try{
			String result = updateAccountInfoService.updatePassword(email, oldPassword, newPassword);
			resultMap = util.responseBuilder(result);
		} catch(Exception e){
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * method for updating userDetails
	 * @param applicationUserDetail
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/UpdateApplicationUserActionForMyProfile", method = RequestMethod.POST)
	public @ResponseBody String updateUserDetailsInMyProfile(
			@ModelAttribute("applicationUserForm") UserDetail applicationUserDetail, HttpSession session,
			HttpServletRequest request) {
		String result = null;
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			result = updateAccountInfoService.updateUserDetails(applicationUserDetail, session);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
}
