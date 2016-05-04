package com.elecnor.ecosystem.expose;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.service.DomainApplicationPlanMappingService;
import com.elecnor.ecosystem.service.UserDetailService;
import com.elecnor.ecosystem.util.EncrypterDecrypter;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class UserExpose {
	@Autowired
	UserDetailDAO applicationUserDetailDAO;
	@Autowired
	UserDetailService userDetailService;
	@Autowired
	DomainApplicationPlanMappingService domainApplicationPlanMappingService;
	@Autowired
	DomainApplicationPlanMappingDAO domainApplicationPlanMappingDAO;

	@RequestMapping(value = "/excludeInterceptor/getUserListByDomain")
	public @ResponseBody String getUserListByDomain(HttpServletRequest request) {
		Utility util = new Utility();
		Long domainId = Long.parseLong(request.getParameter("id"));
		try {
			if (!(domainId > 0)) {
				domainId = 13L;
			}
			return util.getJsonResult(applicationUserDetailDAO.getAllUserDetails(domainId));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/excludeInterceptor/getUserBeanByUserId")
	public @ResponseBody String getUserBean(HttpServletRequest request) {
		Utility util = new Utility();
		String responce = null;
		Long userId = Long.parseLong(request.getParameter("id"));
		try {
			responce = util.getJsonResult(applicationUserDetailDAO.getUserBean(userId));
		} catch (Exception e) {
			responce = util.getJsonResult("failed");
			e.printStackTrace();
		}
		return responce;
	}

	/*
	 * // method to check user authentication and plan is active for particular
	 * // application.
	 * 
	 * @RequestMapping(value = "/excludeInterceptor/checkUserAuthAndPlans")
	 * 
	 * @ResponseBody public String checkUserAuthAndPlans(HttpServletRequest
	 * request,
	 * 
	 * @RequestParam("emailId") String userEmailId,
	 * 
	 * @RequestParam("password") String userPwd) { Utility ut = new Utility();
	 * EncrypterDecrypter ed = new EncrypterDecrypter(); String response = null;
	 * try { String userPwdTemp = userPwd.replace(' ', '+'); String decryPwd =
	 * ed.decryptData(userPwdTemp); UserDetail userProfile =
	 * userDetailService.authenticateUser( userEmailId.trim(), decryPwd.trim());
	 * if (userProfile != null) { System.out.println("user expose");
	 * System.out.println(request.getParameter("isBelcoUser"));
	 * System.out.println(request.getParameter("subscriptionId")); if(null !=
	 * request.getParameter("isBelcoUser") &&
	 * request.getParameter("isBelcoUser").equalsIgnoreCase("false")){ //if user
	 * is not belco user then we are checking for the particular application is
	 * have access to this user ArrayList<DomainApplicationPlanMapping>
	 * activePlans =
	 * domainApplicationPlanMappingDAO.getPlanDetailsBySubscriptionId(request.
	 * getParameter("subscriptionId")); if (activePlans != null &&
	 * !activePlans.isEmpty()) { response = ut.getJsonResult(userProfile); }
	 * else { response = ut.getJsonResult("autfai"); } } else { response =
	 * ut.getJsonResult(userProfile); }
	 * 
	 * } else { response = ut.getJsonResult("autfai"); } } catch (Exception e) {
	 * response = ut.getJsonResult("failed"); e.printStackTrace(); } return
	 * response;
	 * 
	 * }
	 */

	@RequestMapping(value = "/excludeInterceptor/checkUserAuthentication")
	public @ResponseBody String checkUserAuthentication(HttpServletRequest request, @RequestParam("emailId") String userEmailId, @RequestParam("password") String userPwd) {
		Utility util = new Utility();
		EncrypterDecrypter ed = new EncrypterDecrypter();
		String responce = null;
		try {
			String userPwdTemp = userPwd.replace(' ', '+');
			String decryPwd = ed.decryptData(userPwdTemp);
			UserDetail userProfile = userDetailService.authenticateUser(userEmailId.trim(), decryPwd.trim());
			if (userProfile != null) {
				responce = util.getJsonResult(userProfile);
			} else {
				responce = util.getJsonResult("failed");
			}

		} catch (Exception e) {
			responce = util.getJsonResult("failed");
			e.printStackTrace();
		}
		return responce;
	}

	@RequestMapping(value = "/excludeInterceptor/getUserBeanByEmailId")
	public @ResponseBody String getUserBeanByEmailId(HttpServletRequest request) {
		Utility util = new Utility();
		EncrypterDecrypter ed = new EncrypterDecrypter();
		String response = null;
		String emailId = request.getParameter("emailId");
		try {
			String userEmailIdTemp = emailId.replace(' ', '+');
			String decEmailId = ed.decryptData(userEmailIdTemp);
			response = util.getJsonResult(applicationUserDetailDAO.getUserDetails(decEmailId));
		} catch (Exception e) {
			response = util.getJsonResult("failed");
			e.printStackTrace();
		}
		return response;
	}

	/*
	 * This method will return UserBean for given email Id It will replace the
	 * above method checkUserAuthAndPlans()
	 */
	@RequestMapping(value = "/excludeInterceptor/getUserDetails")
	public @ResponseBody String getUserDetails(HttpServletRequest request) {
		Utility util = new Utility();
		String response = null;
		String emailId = request.getParameter("emailId");
		UserDetail userDetails = null;
		try {
			userDetails = applicationUserDetailDAO.getUserDetails(emailId);
			DomainDetail domainDetails = userDetails.getDomainDetail();
			userDetails.setDomainAddressTransient(domainDetails.getCompanyAddress());
			if (null != userDetails.getSuperviserId()) {
				if (null != userDetails.getSuperviserId().getEmailId()) {
					userDetails.setSuperviserEmailIdExpose(userDetails.getSuperviserId().getEmailId());
				}
				if (null != userDetails.getSuperviserId().getFirstName()) {
					userDetails.setSuperviserFirstNameExpose(userDetails.getSuperviserId().getFirstName());
				}
				if (null != userDetails.getSuperviserId().getUserId()) {
					userDetails.setSuperviserIdExpose(userDetails.getSuperviserId().getUserId());
				}
			}
			response = util.getJsonResultExposingTransientField(userDetails);
		} catch (Exception e) {
			response = util.getJsonResult("failed");
			e.printStackTrace();
		}
		return response;
	}

}
