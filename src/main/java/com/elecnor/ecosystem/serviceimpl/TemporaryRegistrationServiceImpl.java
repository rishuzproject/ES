package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.service.TemporaryRegistrationService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.EncrypterDecrypter;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.email.utility.bean.EmailNotificationBean;
import com.email.utility.executor.EmailTaskExecutor;

@Service
public class TemporaryRegistrationServiceImpl implements TemporaryRegistrationService {

	@Autowired
	UserDetailDAO userDetailDAO;
	@Autowired
	EmailTaskExecutor emailHandlerThread;

	/*
	 * @Autowired EmailLogDetailsService emailLogDetailsService;
	 */

	@Override
	public String registerNewUser(UserDetail temporarySignupDetail, String loginTypeForActivation) throws Exception {
		EncrypterDecrypter ed = new EncrypterDecrypter();
		String encPassword = ed.encryptData(temporarySignupDetail.getPassword().trim());
		String encryptEmailId = (ed.encryptData(temporarySignupDetail.getEmailId().trim().toLowerCase()));
		String encryptName = ed.encryptData(temporarySignupDetail.getFirstName());
		temporarySignupDetail.setPassword(encPassword);
		temporarySignupDetail.setStatus(ConstantUtil.PENDINGACTIVE);
		temporarySignupDetail.setSubmittedDate(new Date());
		// temporarySignupDetail.setSignupDate();
		temporarySignupDetail = userDetailDAO.saveApplicationUser(temporarySignupDetail);
		if (temporarySignupDetail != null) {
			sendActivationLink(encryptName, encryptEmailId, temporarySignupDetail, loginTypeForActivation);
			return null;
		}
		return "User Registration Failed !!!";
	}

	// This method is used for sending the activation link
	private void sendActivationLink(String encryptName, String encryptEmailId, UserDetail temporarySignupDetail, String loginTypeForActivation) throws Exception {
		String hostName = PropertyFileReader.getInstance().getStringProperty("hostName");
		String portNumber = PropertyFileReader.getInstance().getStringProperty("portNumber");
		String appName = PropertyFileReader.getInstance().getStringProperty("applicationName");

		String actionUrl = "http://" + hostName + ":" + portNumber + "/" + appName + "/activateTemporaryUserAccount?emailID=" + encryptEmailId + "&Name=" + encryptName + "&LoginType="
				+ loginTypeForActivation;
		String emailText = "Hello " + temporarySignupDetail.getFirstName() + ", ";
		emailText += "\nThanks for showing interest and registering in <a href='http://" + hostName + ":" + portNumber + "/" + appName + "/'> Ecosystem.<a> " + " Please <a href='" + actionUrl
				+ "'>click here to activate</a>  your account and enjoy our services. \nThanks!";
		EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
		emailNotificationBean.setEmailTo(temporarySignupDetail.getEmailId());
		emailNotificationBean.setEmailSubject("Ecosystem : Activation Link");
		emailNotificationBean.setEmailContent(emailText);
		emailNotificationBean.setEmailFrom(temporarySignupDetail.getEmailId());
		emailNotificationBean.setSubmittedBy(temporarySignupDetail.getUserId());
		emailNotificationBean.setSubmittedDate(new Date());
		emailNotificationBean.setDomainId(temporarySignupDetail.getDomainIdTransient());
		emailHandlerThread.executeEmailTask(emailNotificationBean);
	}

	// This method is used for activating the account
	@Override
	public String activateNewUserAccount(String emailId, String name, String loginType, String loginFrom) {
		String result = null;
		try {
			EncrypterDecrypter ed = new EncrypterDecrypter();
			String encName = name.replace(' ', '+');
			String encEmailId = emailId.replace(' ', '+');
			String decEmailId = ed.decryptData(encEmailId);
			String decName = ed.decryptData(encName);

			userDetailDAO.setNewManageUserToActivate(decEmailId, decName);
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	// this method is used for sending password to user
	@SuppressWarnings("unused")
	private void sendPassword(UserDetail temporarySignupDetail, String password) throws Exception {
		String hostName = PropertyFileReader.getInstance().getStringProperty("hostName");
		String portNumber = PropertyFileReader.getInstance().getStringProperty("portNumber");
		String appName = PropertyFileReader.getInstance().getStringProperty("applicationName");

		String actionUrl = "Please find the password given below.\n Password : " + password;
		String emailText = "Hello " + temporarySignupDetail.getFirstName() + ", ";
		emailText += "\nThanks for showing interest and registring in <a href='http://" + hostName + ":" + portNumber + "/" + appName + "/'> Ecosystem<a>. " + "\n" + actionUrl
				+ "\n Enjoy our services. \nThanks!";
		EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
		emailNotificationBean.setEmailTo(temporarySignupDetail.getEmailId());
		emailNotificationBean.setEmailSubject("Ecosystem : Forgotten Password");
		emailNotificationBean.setEmailContent(emailText);
		emailNotificationBean.setEmailFrom(temporarySignupDetail.getEmailId());
		emailNotificationBean.setSubmittedBy(temporarySignupDetail.getUserId());
		emailNotificationBean.setSubmittedDate(new Date());
		emailNotificationBean.setDomainId(temporarySignupDetail.getDomainIdTransient());
		emailHandlerThread.executeEmailTask(emailNotificationBean);
	}
}
