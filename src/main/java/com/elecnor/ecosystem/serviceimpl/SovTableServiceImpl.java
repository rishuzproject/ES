package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.bean.SovTable;
import com.elecnor.ecosystem.bean.TemporaryUserDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.SovDirectoryDAO;
import com.elecnor.ecosystem.dao.SovReviewsTableDAO;
import com.elecnor.ecosystem.dao.SovTableDAO;
import com.elecnor.ecosystem.dao.SovTableTrackingDAO;
import com.elecnor.ecosystem.dao.TemporaryUserDetailDAO;
import com.elecnor.ecosystem.service.SovTableService;
import com.elecnor.ecosystem.util.EmailSenderES;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.elecnor.ecosystem.util.Utility;
import com.email.utility.bean.EmailNotificationBean;
import com.email.utility.executor.EmailTaskExecutor;

@Service
public class SovTableServiceImpl implements SovTableService {

	@Autowired
	SovTableDAO sovTableDAO;

	@Autowired
	SovTableTrackingDAO sovTableTrackingDAO;

	@Autowired
	SovReviewsTableDAO sovReviewsTableDAO;

	@Autowired
	SovDirectoryDAO sovDirectoryDAO;

	@Autowired
	TemporaryUserDetailDAO temporaryUserDetailDAO;
	@Autowired
	EmailTaskExecutor emailHandlerThread;

	/*
	 * @Autowired EmailLogDetailsService emailLogDetailsService;
	 */

	@Override
	public String addUpdateItemsInSOV(SovTable sovTable, UserDetail userDetail, SovDirectory sovDirectory) throws Exception {

		String result = "";
		sovTable.setSovDirectory(sovDirectory);
		sovTable.setStatus("ACTIVE");
		try {
			if (sovTable.getItemNo() == null || sovTable.getItemNo() == 0) {
				sovTable.setSubmittedBy(userDetail);
				sovTable.setSubmittedDate(new Date());
				sovTable.setStatus("ACTIVE");
			} else {
				// update existing item
				// update tracking table
				sovTableTrackingDAO.addNewTrackingEntry();

				sovTable.setUpdatedBy(userDetail);
				sovTable.setUpdatedDate(new Date());
			}
			result = sovTableDAO.addUpdateItems(sovTable);
			// check the status of all the items..if all are approved then
			// change the status of SOV to approved
			result = setSOVInternalApprovalStatus(sovDirectory.getSovId());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String rejectSOVItem(SovReviewsTable sovReviewsTable, UserDetail userDetail, SovDirectory sovDirectory) throws Exception {

		String result = "";
		try {
			result = sovTableDAO.setApprovalSOVItem(sovReviewsTable.getSovTable().getItemNo(), false);

			// update internal approval status in SOV Directory
			result = sovDirectoryDAO.setSOVInternalApprovalStatus(sovDirectory.getSovId(), "REJECTED");

			// adding in reviews table
			sovReviewsTable.setSovDirectory(sovDirectory);
			sovReviewsTable.setApprovalType("INTERNAL");
			sovReviewsTable.setSubmittedBy(userDetail);
			sovReviewsTable.setSubmittedDate(new Date());
			result = sovReviewsTableDAO.addUpdateSOVItemReview(sovReviewsTable);

			// check the status of all the items..if all are approved then
			// change the status of SOV to approved
			result = setSOVInternalApprovalStatus(sovDirectory.getSovId());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String approveSOVItem(long itemNo, String approvalComment, UserDetail userDetail, SovDirectory sovDirectory) throws Exception {

		String result = "";
		try {
			// if(customer){
			// result = sovTableDAO.setExternalApprovalSOVItem(itemNo, true);
			// }
			// else{
			result = sovTableDAO.setApprovalSOVItem(itemNo, true);
			// }
			// update internal approval status in SOV Directory
			result = sovDirectoryDAO.setSOVInternalApprovalStatus(sovDirectory.getSovId(), "APPROVED");

			// adding in reviews table
			if (!(approvalComment.equalsIgnoreCase("") || approvalComment == null)) {

				SovReviewsTable sovReviewsTable = new SovReviewsTable();
				SovTable sovTable = new SovTable();
				sovTable.setItemNo(itemNo);
				// if(customer){
				// sovReviewsTable.setApprovalType("EXTERNAL");
				// }
				// else{
				sovReviewsTable.setApprovalType("INTERNAL");
				// }
				sovReviewsTable.setApprovalComments(approvalComment);
				sovReviewsTable.setSovTable(sovTable);
				sovReviewsTable.setSubmittedBy(userDetail);
				sovReviewsTable.setSubmittedDate(new Date());
				sovReviewsTable.setSovDirectory(sovDirectory);
				result = sovReviewsTableDAO.addUpdateSOVItemReview(sovReviewsTable);
			}
			// check the status of all the items..if all are approved then
			// change the status of SOV to approved
			result = setSOVInternalApprovalStatus(sovDirectory.getSovId());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String requestForApproval(Long sovId, UserDetail supervisorDetail) throws Exception {

		String result = "";
		try {
			result = sovDirectoryDAO.setSOVInternalApprovalStatus(sovId, "REQUEST_FOR_APPROVAL");
			// send email to customer
			EmailSenderES emailConnection = new EmailSenderES();
			emailConnection.sendNotificationMail(supervisorDetail.getEmailId(), null, null, "SUBJ", "email text", false, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String requestForCustomerApproval(SovDirectory sovDirectory, CustomerDirectory customerDirectory, UserDetail userDetail, String cutomerApprovalEmailTo, String emailLinkActivationDuration,
			String customerApprovalEmailBody, String cutomerApprovalEmailCc, String cutomerApprovalEmailBcc) throws Exception {

		String result = "";
		try {
			if (customerDirectory != null) {
				// send email to customer
				String hostName = PropertyFileReader.getInstance().getStringProperty("hostName");
				String portNumber = PropertyFileReader.getInstance().getStringProperty("portNumber");
				String appName = PropertyFileReader.getInstance().getStringProperty("applicationName");

				String actionUrl = "http://" + hostName + ":" + portNumber + "/" + appName + "/customerSOVItemApproval?sovId=" + sovDirectory.getSovId() + "&loginId=" + cutomerApprovalEmailTo;
				String emailText = customerApprovalEmailBody + "\n\nPlease <a href='" + actionUrl + "'>click here</a> to see the requested items. \nThanks!";
				EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
				emailNotificationBean.setEmailTo(cutomerApprovalEmailTo);
				emailNotificationBean.setEmailCc(cutomerApprovalEmailCc);
				emailNotificationBean.setEmailBcc(cutomerApprovalEmailBcc);
				emailNotificationBean.setEmailSubject("Request For Approval");
				emailNotificationBean.setEmailContent(emailText);
				emailNotificationBean.setEmailFrom(userDetail.getEmailId());
				emailNotificationBean.setSubmittedBy(userDetail.getUserId());
				emailNotificationBean.setSubmittedDate(new Date());
				emailNotificationBean.setDomainId(userDetail.getDomainIdTransient());
				emailHandlerThread.executeEmailTask(emailNotificationBean);

				// store customer activation link entry in database
				TemporaryUserDetail temporaryUserDetail = new TemporaryUserDetail();
				temporaryUserDetail.setActivationStartDate(new Date());
				temporaryUserDetail.setSubmittedBy(userDetail);
				temporaryUserDetail.setSovDirectory(sovDirectory);
				temporaryUserDetail.setSubmittedDate(new Date());
				temporaryUserDetail.setUserActivationLink(actionUrl);
				temporaryUserDetail.setUserEmailId(cutomerApprovalEmailTo);
				temporaryUserDetail.setActivationEndDate(new Utility().addDaysToDate(temporaryUserDetail.getActivationStartDate(), Integer.parseInt(emailLinkActivationDuration)));
				result = temporaryUserDetailDAO.customerLinkActivation(temporaryUserDetail);
				result = sovTableDAO.setSOVExternalRequestStatus(sovDirectory.getSovId(), "REQUEST_FOR_CUSTOMER_APPROVAL");
			} else {
				result = "No customer is selected for this job. Please select one to request for approval";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public String setSOVInternalApprovalStatus(Long sovId) throws Exception {
		String result = "";
		try {
			if (sovTableDAO.checkApprovalOfAllItems(sovId) == 0) {
				result = sovDirectoryDAO.approveSOVStatus(sovId, "APPROVED");
			} else {
				result = sovDirectoryDAO.approveSOVStatus(sovId, null);
			}
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
