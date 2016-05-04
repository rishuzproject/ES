package com.elecnor.ecosystem.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.PaymentPojo;
import com.elecnor.ecosystem.bean.UserDetail;


public interface PaymentService {

	
	
	
//	String saveOrUpdatePaymentDetails(UserDetail userDetails, DomainDetail domainDetails, ArrayList<ApplicationPlanDirectory> selectedApps,
//			PaymentPojo paymentPojo) throws Exception;

	String saveOrUpdatePaymentDetails(UserDetail userDetails, DomainDetail domainDetails, HttpServletRequest request, HttpSession httpSession,
			Boolean isTempUser, String[] selectedPlanIds, PaymentPojo paymentPojo) throws Exception;

}
