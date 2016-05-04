package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.ApplicationInvoice;
import com.elecnor.ecosystem.bean.UserDetail;


public interface ApplicationInvoiceDAO {

	ArrayList<ApplicationInvoice> getAllInvoiceDetails(long signUpDomainId) throws Exception;

	ApplicationInvoice setSaveInvoice(ApplicationInvoice applicationInvoiceDetail) throws Exception;

	String setUpdateInvoice(ApplicationInvoice applicationInvoiceDetail) throws Exception;

	String setApplicationInvoiceToDelete(Long invoiceId, UserDetail userDetails) throws Exception;

	String setApplicationInvoiceToCancel(Long invoiceId, UserDetail userDetails) throws Exception;

	ArrayList<ApplicationInvoice> getInvoiceDetails(long domainId) throws Exception;



}
