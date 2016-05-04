package com.elecnor.ecosystem.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.service.StaticContactService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class StaticContactController {

	@Autowired
	StaticContactService staticContactService;

	/*@RequestMapping("/staticContact")
	public String getStaticContact() {
		return "staticPageContact";
	}*/

	/**
	 * method for sending message
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public @ResponseBody
	String setSaveApplicationUser(HttpSession session,
			HttpServletRequest request) {
		// While Registering a user checking if email Id already exits in User
		// Detail, Check for the Tempory User is already done on UI(js file)
		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			String result = staticContactService.sendMessage(request);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
}
