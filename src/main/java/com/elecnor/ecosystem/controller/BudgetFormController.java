package com.elecnor.ecosystem.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.BudgetCodeDAO;
import com.elecnor.ecosystem.helper.BudgetFormHelper;
import com.elecnor.ecosystem.service.BudgetCodeService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class BudgetFormController {

	@Autowired
	BudgetCodeService budgetCodeService;
	@Autowired
	BudgetCodeDAO budgetCodeDAO;

	/*@RequestMapping(value = "/budgetForm")
	public String getBudgetFormDetailsPage() {
		return "budgetForm";
	}

	@ModelAttribute(value = "/budgetCodeForm")
	public BudgetCode registerBudgetCode() {
		return new BudgetCode();
	}*/

	/**
	 * method for save and update application user
	 * 
	 * @param budgetCodeDetail
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateBudgetFormAction", method = RequestMethod.POST)
	public @ResponseBody String setSaveOrUpdateBudgetCode(
			@ModelAttribute("budgetCodeForm") BudgetCode budgetCodeDetail, HttpSession session,
			HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetailsBean = userDetails.getDomainDetail();
			String result = budgetCodeService.setSaveOrUpdateBudgetCode(budgetCodeDetail, userDetails,
					domainDetailsBean);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for get all budget details
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getBudgetFormDetail", method = RequestMethod.POST)
	@ResponseBody
	public String getAllBudgetCodeDetails(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			ArrayList<BudgetCode> allBudgetCodeList = null;
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			Long domainId = domainDetail.getDomainId();
			allBudgetCodeList = budgetCodeService.getAllBudgetCodeDetails(domainId);
			map.put("allBudgetCodeList", allBudgetCodeList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	/**
	 * Method for delete
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/budgetCodeDeleteAction", method = RequestMethod.POST)
	@ResponseBody
	public String setApplicationUserDelete(HttpSession session, HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		BudgetFormHelper budgetFormHelper = new BudgetFormHelper();
		try {
			Long activityId;
			activityId = budgetFormHelper.setActivityIdToDelete(request);
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			String result = budgetCodeDAO.setBudgetCodeDelete(activityId, userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for save data using template
	 * 
	 * @param confirmBudgetUploadId
	 * @param fileUploaded
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/budgetTemplateController", method = RequestMethod.POST)
	@ResponseBody
	public String saveBudgetTemplateDetails(
			@RequestParam(value = "confirmBudgetUploadId", defaultValue = "-1") int confirmBudgetUploadId,
			@RequestParam(value = "budgetTemplateFile") MultipartFile fileUploaded, HttpServletRequest request,
			HttpSession session) throws IOException {
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = budgetCodeService.uploadBudgetFile(fileUploaded, session, confirmBudgetUploadId);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(resultMap);
	}

}
