package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.bean.SovTable;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.SovDirectoryDAO;
import com.elecnor.ecosystem.dao.SovTableDAO;
import com.elecnor.ecosystem.service.SovTableService;
import com.elecnor.ecosystem.service.TemporaryUserDetailService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class SovTableController {

	@Autowired
	SovTableService sovTableService;

	@Autowired
	SovTableDAO sovTableDAO;

	@Autowired
	SovDirectoryDAO sovDirectoryDAO;

	@Autowired
	TemporaryUserDetailService temporaryUserDetailService;

/*	@RequestMapping("/sovTable")
	public String getSovTable() {
		return "sovTable";
	}

	@ModelAttribute(value = "/sovTableBean")
	public SovTable getSovTableBean() {
		System.out.println("Initializing SOV Table Bean");
		return new SovTable();
	}*/

	@RequestMapping("/getSessionVariables")
	public @ResponseBody String getSessionVariables(HttpSession session, HttpServletRequest request) {
		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			SovDirectory sovDirectory = sovDirectoryDAO.getSOVDetails((Long) session.getAttribute("sovId"));
			session.setAttribute("selectedSOV", sovDirectory);
			resultMap.put("selectedProject", session.getAttribute("selectedprojectforSOV"));
			resultMap.put("statusReturned", "200");
			resultMap.put("selectedSOV", sovDirectory);
			resultMap.put("userRole", session.getAttribute("userRole"));
			resultMap.put("emailBody", ConstantUtil.SOV_CUSTOMER_APPROVAL_REQUEST_EMAIL_BODY);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping("/addUpdateItemsInSOVTable")
	public @ResponseBody String addUpdateItemsInSOVTable(@ModelAttribute("sovTableBean") SovTable sovTable,
			HttpSession session, HttpServletRequest request) {

		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
		try {
			String result = sovTableService.addUpdateItemsInSOV(sovTable, userDetail, sovDirectory);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping("/getItemDetailsInSOV")
	public @ResponseBody String getItemDetailsInSOV(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		ArrayList<SovTable> allSOVList = null;
		SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
		try {
			allSOVList = sovTableDAO.getSOVDetailsFromSOVTable(sovDirectory.getSovId());
			// map.put("statusReturned", "200");
			// map.put("allSOVList", allSOVList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(allSOVList);
		// return util.getJsonResult(map);
	}

	@RequestMapping("/getItemDetailsFromSOVTable")
	public @ResponseBody String getItemDetailsFromSOVTable(@RequestParam("itemNo") String itemNo, HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			ArrayList<SovTable> itemDetailList = sovTableDAO.getItemDetailsFromSOV(Long.parseLong(itemNo));
			map.put("ajaxResult", "success");
			map.put("itemDetailList", itemDetailList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	@RequestMapping("/deleteItemFromSOVTable")
	public @ResponseBody String deleteItemFromSOVTable(@RequestParam("id") String itemNo, HttpSession session,
			HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			String result = sovTableDAO.deleteSOVItem(Long.parseLong(itemNo));
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping("/rejectItemInSOVTable")
	public @ResponseBody String rejectItemInSOVTable(
			@ModelAttribute("sovReviewsTableBean") SovReviewsTable sovReviewsTable,
			@RequestParam("itemNo") String itemNo, HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
		SovTable sovTable = new SovTable();
		sovTable.setItemNo(Long.parseLong(itemNo));
		sovReviewsTable.setSovTable(sovTable);
		try {
			String result = sovTableService.rejectSOVItem(sovReviewsTable, userDetail, sovDirectory);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping("/approveItemInSOVTable")
	public @ResponseBody String approveItemInSOVTable(@RequestParam("approvedItemId") String approvedItemId,
			@RequestParam("approvalComment") String approvalComment, HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
		try {
			String result = sovTableService.approveSOVItem(Long.parseLong(approvedItemId), approvalComment, userDetail,
					sovDirectory);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping("/requestSOVForApproval")
	public @ResponseBody String requestSOVForApproval(HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
			String result = sovTableService.requestForApproval(sovDirectory.getSovId(), sovDirectory.getJobDetail()
					.getSupervisor());
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping("/requestForCustApproval")
	public @ResponseBody String requestForCustApproval(
			@RequestParam("cutomerApprovalEmailTo") String cutomerApprovalEmailTo,
			@RequestParam("emailLinkActivationDuration") String emailLinkActivationDuration,
			@RequestParam("customerApprovalEmailBody") String customerApprovalEmailBody,
			@RequestParam("cutomerApprovalEmailCc") String cutomerApprovalEmailCc,
			@RequestParam("cutomerApprovalEmailBcc") String cutomerApprovalEmailBcc, HttpSession session,
			HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			SovDirectory sovDirectory = (SovDirectory) session.getAttribute("selectedSOV");
			UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
			String result = sovTableService.requestForCustomerApproval(sovDirectory, sovDirectory.getJobDetail()
					.getCustomerDirectory(), userDetail, cutomerApprovalEmailTo, emailLinkActivationDuration,
					customerApprovalEmailBody, cutomerApprovalEmailCc, cutomerApprovalEmailBcc);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

}
