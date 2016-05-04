package com.elecnor.ecosystem.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.service.ManageAccountService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class ManageAccountController {

	@Autowired
	ManageAccountService manageAccountService;

	/*@RequestMapping(value = "/manageAccount")
	public String getManageAccountPage() {
		return "manageAccount";
	}*/

	/**
	 * Method for send mail from manage account
	 * @param fileUpload
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/setEmailSendFromManageAccount", method = RequestMethod.POST)
	@ResponseBody
	public String setEmailSendFromManageAccount(
			@RequestParam(value = "fileUploaded") MultipartFile fileUpload,
			HttpServletRequest request, HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			String result = manageAccountService.setEmailSendFromManageAccount(request, fileUpload,session);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
}
