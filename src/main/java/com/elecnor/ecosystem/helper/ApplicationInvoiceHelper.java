package com.elecnor.ecosystem.helper;

import javax.servlet.http.HttpServletRequest;

public class ApplicationInvoiceHelper {

	public Long setInvoiceIdToDelete(HttpServletRequest request) throws Exception{
		Long invoiceId = 0L;
		try {
			if (request.getParameter("invoiceIdToDel") != null && request.getParameter("invoiceIdToDel") != "") {
				invoiceId = Long.parseLong(request.getParameter("invoiceIdToDel").trim());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}
		return invoiceId;
	}

}
