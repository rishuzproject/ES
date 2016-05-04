package com.elecnor.ecosystem.service;

import java.util.HashMap;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.google.gson.JsonArray;

public interface ApplicationInvoiceService {

	String setSaveOrUpdateInvoice(JsonArray jsonArray,
			UserDetail userDetails,DomainDetail domainDetailsBean) throws Exception;

	HashMap<String, Object> uploadDepartmentFile() throws Exception;

}
