package com.elecnor.ecosystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ApplicationInvoice;
import com.elecnor.ecosystem.bean.ApplicationInvoicePlanMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.dao.ApplicationInvoiceDAO;
import com.elecnor.ecosystem.dao.ApplicationInvoiceInDetailDAO;
import com.elecnor.ecosystem.dao.DomainDetailDAO;
import com.elecnor.ecosystem.helper.ApplicationInvoiceHelper;
import com.elecnor.ecosystem.service.ApplicationInvoiceService;
import com.elecnor.ecosystem.util.Utility;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class ApplicationInvoiceController {

	@Autowired
	ApplicationInvoiceDAO applicationInvoiceDAO;

	@Autowired
	ApplicationInvoiceService applicationInvoiceService;

	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;

	@Autowired
	ApplicationInvoiceInDetailDAO applicationInvoiceInDetailDAO;

	@Autowired
	DomainDetailDAO domainDetailDAO;

	/*@RequestMapping("/applicationInvoice")
	public String getApplicationDetailPage() {
		return "applicationInvoice";
	}

	@ModelAttribute(value = "/applicationInvoiceForm")
	public ApplicationInvoice generateInvoice() {
		return new ApplicationInvoice();
	}*/

	/**
	 * Method for fetch Invoice Details of Application
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getApplicationInvoiceDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getApplicationInvoiceDetails(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			ArrayList<ApplicationInvoice> allInvoiceDetailsList = applicationInvoiceDAO
					.getAllInvoiceDetails(domainDetail.getDomainId());
			ArrayList<ApplicationInvoicePlanMapping> applicationInvoicePlanMapping = applicationInvoiceInDetailDAO
					.getAllResults();
			ArrayList<DomainDetail> allDomainDetails = domainDetailDAO.getDomainDetail();

			map.put("allInvoiceDetailsList", allInvoiceDetailsList);
			map.put("applicationInvoicePlanMapping", applicationInvoicePlanMapping);
			map.put("allDomainDetails", allDomainDetails);
		} catch (Exception e) {
			map = util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	/**
	 * method for save and update application user
	 * 
	 * @param session
	 * @param request
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateApplicationInvoiceAction", method = RequestMethod.POST)
	public @ResponseBody String setSaveOrUpdateApplicationInvoice(HttpSession session, HttpServletRequest request,
			@RequestBody String mode) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			JsonArray jsonArray = new JsonParser().parse(mode).getAsJsonArray();
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetailsBean = userDetails.getDomainDetail();
			String result = applicationInvoiceService.setSaveOrUpdateInvoice(jsonArray, userDetails, domainDetailsBean);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for perform delete operation
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invoiceDeleteAction", method = RequestMethod.POST)
	@ResponseBody
	public String setApplicationInvoiceToDelete(HttpSession session, HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		Long invoiceId;
		ApplicationInvoiceHelper applicationInvoiceHelper = new ApplicationInvoiceHelper();
		try {
			invoiceId = applicationInvoiceHelper.setInvoiceIdToDelete(request);
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			String result = applicationInvoiceDAO.setApplicationInvoiceToDelete(invoiceId, userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for cancel Application Invoice
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invoiceCancelAction", method = RequestMethod.POST)
	@ResponseBody
	public String setApplicationInvoiceToCancel(HttpSession session, HttpServletRequest request) {
		Long invoiceId = 0L;
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		ApplicationInvoiceHelper applicationInvoiceHelper = new ApplicationInvoiceHelper();
		try {
			invoiceId = applicationInvoiceHelper.setInvoiceIdToDelete(request);
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			String result = applicationInvoiceDAO.setApplicationInvoiceToCancel(invoiceId, userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for save data using template
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gettingValuesFromProperties", method = RequestMethod.POST)
	@ResponseBody
	public String saveDepartmentTemplateDetails(HttpServletRequest request, HttpSession session) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		try {
			resultMap = applicationInvoiceService.uploadDepartmentFile();
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(resultMap);
	}
}
