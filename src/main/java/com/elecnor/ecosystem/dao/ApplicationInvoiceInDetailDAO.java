package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.ApplicationInvoicePlanMapping;

public interface ApplicationInvoiceInDetailDAO {

	ArrayList<ApplicationInvoicePlanMapping> getAllResults(long invoiceId)throws Exception;

	String addPlansToInvoice(
			ApplicationInvoicePlanMapping applicationInvoicePlanMapping);


	ArrayList<ApplicationInvoicePlanMapping> getAllResults() throws Exception;

}
