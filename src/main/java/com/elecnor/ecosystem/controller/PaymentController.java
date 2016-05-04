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

import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.PaymentPojo;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationPurchasePlanDAO;
import com.elecnor.ecosystem.helper.PaymentHelper;
import com.elecnor.ecosystem.service.PaymentService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	String result = null;
	String promoCode;
	String email=null;
	// ArrayList<ApplicationPlanDirectory> selectedPlans;
	@Autowired
	ApplicationPurchasePlanDAO applicationPurchasePlanDAO;

	/*@RequestMapping("/payment")
	public String getApplicationDetailPage() {
		return "payment";
	}*/

	/**
	 * Method for save cookie data
	 * 
	 * @param request
	 * @param tableData
	 * @param spromoCode
	 * @return
	 */
	@RequestMapping(value = "/saveCookieData", method = RequestMethod.POST)
	@ResponseBody
	public String saveCookieDetails(HttpServletRequest request, @RequestParam(value = "selectedPlans") String tableData,@RequestParam(value = "promoCode") String spromoCode,HttpSession session) {
		promoCode = spromoCode;
		session.setAttribute("selectedPlans", tableData);
		UserDetail user= (UserDetail)session.getAttribute("selectedUser");
		email=user.getEmailId();
		return "success";
	}

	/**
	 * Method for get cookie data
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/getCookieData", method = RequestMethod.POST)
	public @ResponseBody String getCookieDetails(HttpServletRequest request, HttpSession session) {
		String selectedPlans = (String) session.getAttribute("selectedPlans");

		selectedPlans = selectedPlans.substring(1, selectedPlans.length() - 1);
		Utility util = new Utility();
		ArrayList<ApplicationPlanDirectory> selectedPlansList = new ArrayList<ApplicationPlanDirectory>();
		String array[] = selectedPlans.split(",");
		HashMap<String, Object> sPlans = new HashMap<String, Object>();
		try {
			for (int i = 0; i < array.length; i++) {
				int tempId = Integer.parseInt(array[i]);
				selectedPlansList.add(applicationPurchasePlanDAO.getApplicationPlanDetailsByPlanId(tempId));
			}
			sPlans.put("loggedInEmailId", email);
			sPlans.put("selectedPlansList", selectedPlansList);
			sPlans.put("promoCode", (String) session.getAttribute("promoCode"));
		} catch (Exception e) {
			sPlans.put("ajaxResult", "failure");
			sPlans.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(sPlans);
	}

	@ModelAttribute(value = "/userFormInPayment")
	public UserDetail registerUserProfile() {
		return new UserDetail();
	}

	/**
	 * method is used for getting user details from session
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserDetailsForPayment", method = RequestMethod.POST)
	@ResponseBody
	public String getUserDetailsForPayment(HttpSession session) throws Exception {

		Boolean isTempUser = (Boolean) session.getAttribute("isTemporaryUser");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			map.put("userDetails", userDetails);
			map.put("istempuser", isTempUser);
		} catch (Exception e) {
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultForDate(map);

	}

	/**
	 * Method for save or update payment details
	 * 
	 * @param userDetails
	 * @param session
	 * @param request
	 * @return
	 */
	// Refactoring Code- Ankur(Incomplete, moving to MPR + Spring integration)
	@RequestMapping(value = "/saveOrUpdatePaymentDetailsAction", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdatePaymentDetailsAction(@ModelAttribute("userFormInPayment") UserDetail userDetails, HttpSession session,
			//domain logo code..do not remove
			//@RequestParam("templateFile") MultipartFile domainLogo,
			HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();

		try {
			// Initialization Starts
			PaymentHelper paymentHelper = new PaymentHelper();
			// Initialization Ends

			Boolean isTempUser = (Boolean) session.getAttribute("isTemporaryUser");
			UserDetail loggedInUserDetails = (UserDetail) session.getAttribute("selectedUser");
			// Get User Details(User Details)

			userDetails = paymentHelper.getUpdatedUserDetailsBean(request, userDetails, loggedInUserDetails);
			// Get Domain Details(Company Details)
			//domain logo code..domainLogo to be sent as the third parameter instead of null
			DomainDetail domainDetail = paymentHelper.getDomainDetailBeanFromRequest(request, userDetails, null);
			// Get Payment Pojo From Request, currency is missing @anshu
			PaymentPojo paymentPojo = paymentHelper.getPaymentPojoBeanFromRequest(request, userDetails.getEmailId());
			// Get Selected Plan Details
			String selectedPlanIds[] = paymentHelper.getSelectedPlanIds(request);

			// selectedPlans = selectedPlansList;
			String resultPayment = paymentService.saveOrUpdatePaymentDetails(userDetails, domainDetail, request, session, isTempUser,
					selectedPlanIds, paymentPojo);
			// ModelAndView mav=new ModelAndView();
			if (resultPayment == null) {
				resultMap.put("ajaxResult", "success");
				resultMap.put("RedirectedUrl", "applicationInvoice");
			} else {
				if (resultPayment.startsWith("ERROR:")) {
					resultMap.put("ajaxResult", "failure");
					resultMap.put("reason", resultPayment);
					resultMap.put("RedirectedUrl", "applicationStore");
				} else {
					resultMap.put("ajaxResult", "Payment success");
					resultMap.put("keyGenerated", resultPayment);
					resultMap.put("RedirectedUrl", "applicationInvoice");
				}
			}
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);

	}
}
