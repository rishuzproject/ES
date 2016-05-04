package com.elecnor.ecosystem.helper;

import javax.servlet.http.HttpServletRequest;

public class ApplicationInvoiceInDetailHelper {

	public String getRedirectionPage(HttpServletRequest request) {
		
		if (request.getParameter("invoiceId") != null && request.getParameter("invoiceId") != "") {
			return "applicationInvoiceInDetails";
		}
		else {
			return "";
		}
	}

}
