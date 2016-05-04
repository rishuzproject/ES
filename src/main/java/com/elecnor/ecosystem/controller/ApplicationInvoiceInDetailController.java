package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ApplicationInvoicePlanMapping;
import com.elecnor.ecosystem.dao.ApplicationInvoiceInDetailDAO;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class ApplicationInvoiceInDetailController {

	@Autowired
	ApplicationInvoiceInDetailDAO applicationInvoiceInDetailDAO;

	/*@RequestMapping("/applicationInvoiceInDetail")
	public String getApplicationDetailPage(HttpServletRequest request,
			HttpSession session) {
		if (request.getParameter("invoiceId") != null && request.getParameter("invoiceId") != "") {
			session.setAttribute("invoiceId",Long.parseLong(request.getParameter("invoiceId")));
			return ConstantUtil.REDIRECT_TO_APPLICATION_INVOICE_IN_DETAILS;
		}
		else {
			return ConstantUtil.REDIRECT_TO_APPLICATION_INVOICE;
		}
	}*/

	/**
	 * Method is used for getting user details from session
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getUserDetailsForInvoiceInDetail", method = RequestMethod.POST)
	@ResponseBody
	public String getUserDetailsForInvoiceInDetail(HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			long invoiceId = (Long) session.getAttribute("invoiceId");
			ArrayList<ApplicationInvoicePlanMapping> applicationInvoicePlanMapping = applicationInvoiceInDetailDAO.getAllResults(invoiceId);
			resultMap.put("applicationInvoicePlanMapping",applicationInvoicePlanMapping);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);

	}
}
