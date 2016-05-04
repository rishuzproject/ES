package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.bean.SovTable;
import com.elecnor.ecosystem.bean.UserDetail;

public interface SovTableService {

	public String addUpdateItemsInSOV(SovTable sovTable, UserDetail userDetail, SovDirectory sovDirectory) throws Exception;

	public String rejectSOVItem(SovReviewsTable sovReviewsTable, UserDetail userDetail, SovDirectory sovDirectory) throws Exception;

	public String approveSOVItem(long itemNo, String approvalComment, UserDetail userDetail, SovDirectory sovDirectory) throws Exception;

	public String requestForApproval(Long sovId, UserDetail supervisorDetail) throws Exception;

	public String requestForCustomerApproval(SovDirectory sovDirectory, CustomerDirectory customerDirectory,
			UserDetail userDetail, String cutomerApprovalEmailTo, String emailLinkActivationDuration,
			String customerApprovalEmailBody, String cutomerApprovalEmailCc, String cutomerApprovalEmailBcc) throws Exception;
	
	public String setSOVInternalApprovalStatus(Long sovId) throws Exception;
}
