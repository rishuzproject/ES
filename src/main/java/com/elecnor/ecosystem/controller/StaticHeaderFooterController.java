package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRolesMapping;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.UserRolesMappingDAO;
import com.elecnor.ecosystem.helper.StaticHeaderFooterHelper;
import com.elecnor.ecosystem.service.TemporaryRegistrationService;
import com.elecnor.ecosystem.service.TemporaryUserDetailService;
import com.elecnor.ecosystem.service.UserDetailService;
import com.elecnor.ecosystem.service.UserRolesMappingService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class StaticHeaderFooterController {

	@Autowired
	UserDetailService userProfileService;
	@Autowired
	TemporaryRegistrationService temporaryRegistrationService;
	@Autowired
	UserDetailDAO userDetailDAO;
	@Autowired
	UserRolesMappingDAO userRolesMappingDAO;
	@Autowired
	TemporaryUserDetailService temporaryUserDetailService;
	@Autowired
	UserRolesMappingService userRolesMappingService;
	
	Logger logger = LoggerFactory.getLogger(StaticHeaderFooterController.class);

	/*@RequestMapping("/staticHeaderFooter")
	public String getStaticIndex() {
		return "staticHeaderFooter";
	}*/

	/**
	 * method for save application user
	 * 
	 * @param temporarySignupDetail
	 * @param loginTypeForActivation
	 * @param session
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/getUserMappedRoles", method = RequestMethod.POST)
	public @ResponseBody String getUserMappedRoles(HttpSession session, HttpServletRequest request) {
		String userEmailId = request.getParameter("emailId");
		List<UserRolesMapping> userRolesList = new ArrayList<UserRolesMapping>();
		Utility util = new Utility();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			
			UserDetail selectedUser=userDetailDAO.getUserDetails(userEmailId.toLowerCase());
			// Checking if userEmailId is a valid ID, otherwise returning a failure response.
			if(selectedUser == null){
				logger.info(userEmailId + " is not a valid login ID.");
				map.put("statusReturned", "400");
				map.put("reason", userEmailId + " is not a valid login ID.");
				
				return util.getJsonResult(map);
			}
			userRolesList = userRolesMappingDAO.getRoleForUser(selectedUser.getUserId());
			session.setAttribute("userRolesList", userRolesList);
			map.put("statusReturned", "200");
			map.put("userRolesList", userRolesList);
		} catch (Exception e) {
			map.put("statusReturned", "400");
			map.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(map);
	}

	@RequestMapping(value = "/saveTemporaryRegistrationFormAction", method = RequestMethod.POST)
	public @ResponseBody String setSaveApplicationUser(@ModelAttribute("temporaryRegistrationForm") UserDetail temporarySignupDetail,
			@RequestParam(value = "loginTypeForActivation", defaultValue = "normalLogin") String loginTypeForActivation, HttpSession session,
			HttpServletRequest request) {
		// While Registering a user checking if email Id already exits in User
		// Detail, Check for the Tempory User is already done on UI(js file)
		String result = null;
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		String userName = request.getParameter("userName");
		try {
			boolean isExist = userDetailDAO.isEmailIdExists(temporarySignupDetail.getEmailId());
			if (!(isExist)) {
				try {
					if (userName != null) {
						String name[] = userName.split(" ");
						temporarySignupDetail.setFirstName(name[0]);
						if (name.length == 2) {
							temporarySignupDetail.setLastName(name[1]);
						} else if (name.length == 3) {
							temporarySignupDetail.setMiddleName(name[1]);
							temporarySignupDetail.setLastName(name[2]);
						}
					}
				} catch (Exception e1) {
					temporarySignupDetail.setFirstName(userName);
				}
				result = temporaryRegistrationService.registerNewUser(temporarySignupDetail, loginTypeForActivation);
			} else {
				result = "Email Id Already Exists. Please Use Forgot Password Link.";
			}
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for activate Temporary UserAccount
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activateTemporaryUserAccount")
	public ModelAndView activateTemporaryUserAccount(HttpServletRequest request) {

		String emailId = "";
		String name = "";
		String loginType = "";
		String loginFrom = "";
		String result = "";
		Utility util = new Utility();
		ModelAndView mav = new ModelAndView();
		try {
			if (request.getParameter("emailID") != null) {
				emailId = request.getParameter("emailID");
			}
			if (request.getParameter("LoginType") != null) {
				loginType = request.getParameter("LoginType");
			}
			if (request.getParameter("Name") != null) {
				name = request.getParameter("Name");
			}
			if (request.getParameter("LoginFrom") != null) {
				loginFrom = request.getParameter("LoginFrom");
			}
			result = temporaryRegistrationService.activateNewUserAccount(emailId, name, loginType, loginFrom);
			if (loginType.equalsIgnoreCase("normalLogin") || loginType.equalsIgnoreCase("manageUsers") || loginType.equalsIgnoreCase("")) {
				mav = new ModelAndView("staticPageHome");
			} else {
				mav = new ModelAndView("belcoLogin");
			}
			if (result == null) {
				mav.addObject("resultForActivating", "Your Account has been activated now.Please Login");
			} else {
				mav.addObject("errorResultForActivating", "Your account could not be activated. Please request for a new activation link. ");
			}
		} catch (Exception e) {
			util.sendExceptionEmail(e);
		}
		return mav;
	}

	/**
	 * checking if the email is existed or not from Temporary User Details Table
	 * Only
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	// @RequestMapping(value = "/checkEmailIdInTemporaryUserDetails", method =
	// RequestMethod.POST)
	// public @ResponseBody String checkEmailIdIfExists(HttpSession session,
	// HttpServletRequest request) {
	// String emailId = null;
	// String json = null;
	// Utility util = new Utility();
	// try {
	// if (request.getParameter("emailId") != null) {
	// emailId = request.getParameter("emailId").trim();
	// }
	// boolean isExist =
	// temporarayRegistrationDAO.checkEmailIdIfExists(emailId);
	// Gson gson = new
	// GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	// json = gson.toJson(isExist);
	// return json;
	// } catch (Exception e) {
	// HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
	// resultMap.put("ajaxResult", "failure");
	// resultMap.put("reason", e.getMessage());
	// util.sendExceptionEmail(e);
	// return util.getJsonResult(resultMap);
	// }
	//
	// }

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getDashboardPage() {
		return "home";
	}

	/*@RequestMapping(value = "/belcoHome", method = RequestMethod.GET)
	public String getBelcoHomePage() {
		return "belcoHome";
	}*/

	/**
	 * This Methods will be used to authenticate the user.@Reviewed 24/Sep/2014
	 * changed
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView LoginAuthentication(HttpSession session, HttpServletRequest request, Model model,
			@RequestParam(value = "loginEmailId") String emailId, @RequestParam(value = "loginPassword") String password,
			@RequestParam(value = "loginTypeForLogin", defaultValue = "normalLogin") String loginTypeForLogin,
			@RequestParam(value = "defaultRoleCheckBox", defaultValue = "off") String defaultCheck,
			@RequestParam(value = "userRolesForLogin", defaultValue = "-1") String userRoleSelectedForDefault) {
		ModelAndView mav = new ModelAndView();
		Utility util = new Utility();
		try {
			// Even If status is In active, we will get a selected user Detail
			// Both temp user and permanent user are in same table
			UserDetail selectedUser = userProfileService.authenticateUser(emailId.toLowerCase(), password);
			// If Selected User Is null, It means it is not present in
			// USER_DETAILS table, either the user is invalid or a temporary
			// User.
			StaticHeaderFooterHelper helper = new StaticHeaderFooterHelper();
			if (selectedUser == null) {
				mav.addObject("ErrorMsg", " Invalid Credentials !!!");
				mav = helper.getMAVForLoginPage(loginTypeForLogin, mav);
			} else if (selectedUser != null && selectedUser.getStatus().equalsIgnoreCase("ACTIVE") && selectedUser.getDomainDetail() != null) {
				userRolesMappingService.getAllUserRolesToInitializeInStaticList();
				session.setAttribute("selectedUser", selectedUser);
				session.setAttribute("isTemporaryUser", false);
				// If No role is selected , we return response back to login
				// page.
				if (Long.parseLong(userRoleSelectedForDefault) == -1) {
					mav = helper.getMAVForLoginPage(loginTypeForLogin, mav);
					mav.addObject("ErrorMsg", " Please select a role from Select User Role field");
				} else if (userRolesMappingService.roleAuthorization(selectedUser.getUserId(), Long.parseLong(userRoleSelectedForDefault))) {
					session.setAttribute("userRole", Long.parseLong(userRoleSelectedForDefault));
					if (defaultCheck.equalsIgnoreCase("on")) {
						userRolesMappingDAO.setDefaultRole(Long.parseLong(userRoleSelectedForDefault), selectedUser.getEmailId());
					}
					if (loginTypeForLogin.equalsIgnoreCase("normalLogin")) {
						session.setAttribute("isBelcoUser", false);
						if (Long.parseLong(userRoleSelectedForDefault) == 8) // must
																				// be
																				// changed
							session.setAttribute("isReportnTrackUser", true);
						else
							session.setAttribute("isReportnTrackUser", false);
					} else {
						session.setAttribute("isBelcoUser", true);
						session.setAttribute("isReportnTrackUser", false);
					}
					mav = new ModelAndView("redirect:" + "home#dashboard");
				} else {
					mav = helper.getMAVForLoginPage(loginTypeForLogin, mav);
					mav.addObject("ErrorMsg", " Your Email id is not mapped to this role. Please Contact your Admin.");
				}
			} else if (selectedUser != null && selectedUser.getStatus().equalsIgnoreCase("ACTIVE") && selectedUser.getDomainDetail() == null) {
				userRolesMappingService.getAllUserRolesToInitializeInStaticList();
				session.setAttribute("selectedUser", selectedUser);
				session.setAttribute("isTemporaryUser", true);
				if (loginTypeForLogin.equalsIgnoreCase("normalLogin")) {
					session.setAttribute("isBelcoUser", false);
				} else {
					session.setAttribute("isBelcoUser", true);
				}
				mav.setViewName("home");
			} else if (selectedUser != null && !selectedUser.getStatus().equalsIgnoreCase("ACTIVE")) {
				mav = helper.getMAVForLoginPage(loginTypeForLogin, mav);
				mav.addObject("ErrorMsg",
						" Your Email Id Has Not Been Activated. If you have not received the activation link , Please Request for Activation Link again.");

			}
			return mav;
		} catch (Exception e) {
			mav = new ModelAndView("error");
			mav.addObject("exception", e);
			util.sendExceptionEmail(e);
			return mav;
		}
	}

	/**
	 * This is used set the session
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pageAuthorization", method = RequestMethod.POST)
	@ResponseBody
	public String pageAuthorization(HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			resultMap.put("userDetails", session.getAttribute("selectedUser"));
			resultMap.put("istempuser", ((Boolean) session.getAttribute("isTemporaryUser")));
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}

		return util.getJsonResult(resultMap);

	}

	/**
	 * This method used for sending the activation link.@reviewed 25-Sep-2014
	 * changed
	 * 
	 * @param session
	 * @param request
	 * @param emailId
	 * @param loginType
	 * @return
	 */
	@RequestMapping(value = "/sendingEmailForActivation", method = RequestMethod.POST)
	public ModelAndView sendingEmailActivation(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "activationEmailId") String emailId,
			@RequestParam(value = "loginType", defaultValue = "normalLogin") String loginType) {
		String result = null;
		ModelAndView mav = new ModelAndView();
		Utility util = new Utility();
		StaticHeaderFooterHelper helper = new StaticHeaderFooterHelper();
		try {
			result = userProfileService.sendActivationLinkIfPresentInUserDetails(emailId, loginType);
			mav = helper.getMAVForLoginPage(loginType, mav);
			if (result == "success") {
				mav.addObject("ActivationMsg", " We have sent an activation link to your registered email id. Please check your inbox !!");
			} else {
				mav.addObject("ActivationErrorMsg", " Your Email Id does not exists in our system.Please register yourself first.");
			}
		} catch (Exception e) {
			mav = new ModelAndView("error");
			mav.addObject("exception", e);
			util.sendExceptionEmail(e);
		}
		return mav;
	}

	// 24/Sep/2014 changed
	/**
	 * This method used for sending the password to the user
	 * 
	 * @param session
	 * @param request
	 * @param emailId
	 * @param loginTypeForPass
	 * @return
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ModelAndView forgotPassword(HttpSession session, HttpServletRequest request, @RequestParam(value = "sendingEmailId") String emailId,
			@RequestParam(value = "loginTypeForPass", defaultValue = "normalLogin") String loginTypeForPass) {
		String result = null;
		ModelAndView mav = new ModelAndView();
		Utility util = new Utility();
		try {
			result = userProfileService.getUserDetails(emailId);

			StaticHeaderFooterHelper helper = new StaticHeaderFooterHelper();
			mav = helper.getMAVForLoginPage(loginTypeForPass, mav);
			if (result != null) {
				mav.addObject("PasswordMsg", " We have sent password to your registered email id. Please check your inbox !!");
			} else {
				mav.addObject("PasswordErrorMsg", " Your Email Id does Not Exists !!!");
			}
			return mav;
		} catch (Exception e) {
			mav = new ModelAndView("error");
			mav.addObject("exception", e);
			util.sendExceptionEmail(e);
			return mav;
		}

	}

	@RequestMapping("/customerSOVItemApproval")
	public String requestForCustApproval(@RequestParam("sovId") String sovId, @RequestParam("loginId") String customerEmailId, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			Boolean result = temporaryUserDetailService.validateTemporaryUser(Long.parseLong(sovId), customerEmailId);
			System.out.println("service result :" + result);
			session.setAttribute("sovId", Long.parseLong(sovId));
			// resultMap = util.responseBuilder(result);
			// to be removed later
			if (result) {
				System.out.println("in static");
				mav = new ModelAndView("sovTable");
			} else
				mav = new ModelAndView("error");
		} catch (Exception e) {
			// e.printStackTrace();
			// resultMap.put("ajaxResult", "failure");
			// resultMap.put("reason", e.getMessage());
			// util.sendExceptionEmail(e);
			System.out.println("in static catch");
			mav = new ModelAndView("error");
		}
		return "redirect:" + ConstantUtil.REDIRECT_TO_SOV_TABLE;
		// return util.getJsonResult(resultMap);
	}
}
