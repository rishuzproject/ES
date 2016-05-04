package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DesignationDirectory;
import com.elecnor.ecosystem.bean.DesignationHierarchyDetails;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRoles;
import com.elecnor.ecosystem.bean.UserRolesMapping;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.UserRolesMappingDAO;
import com.elecnor.ecosystem.service.UserDetailService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.EncrypterDecrypter;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.email.utility.bean.EmailNotificationBean;
import com.email.utility.executor.EmailTaskExecutor;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	UserDetailDAO applicationUserDetailDAO;

	@Autowired
	UserDetailDAO userDetailDAO;

	@Autowired
	UserRolesMappingDAO userRolesMappingDAO;

	@Autowired
	EmailTaskExecutor emailHandlerThread;

	// @Autowired
	// EmailLogDetailsService emailLogDetailsService;

	/*
	 * @Autowired EmailLogDetailsService emailLogDetailsService;
	 */
	// method for save or update user

	// method for save or update user
	// changes by meghana
	@Override
	public String saveOrUpdateUser(UserDetail applicationUserDetail, boolean isBelcoUser, UserDetail loggedInUser, String[] selectedRolesNames, String[] selectedRolesId) throws Exception {

		String result = null;
		UserDetail userDetailsaved = null;
		try {
			// UserDetail userDetails = (UserDetail)
			// session.getAttribute("selectedUser");
			// DomainDetail domainDetail = userDetails.getDomainDetail();
			long userIdTemp = 0;
			if (applicationUserDetail.getUserId() != null) {
				userIdTemp = applicationUserDetail.getUserId();
			}
			// checking whether the entered email id is already exist before
			// save or
			// updating user
			boolean isExist = userDetailDAO.isEmailIdExists(applicationUserDetail.getEmailId().toLowerCase(), userIdTemp);
			if (isExist) {
				result = "emaiIdExist";
			} else {
				String loginType = "";
				String encPassword = new EncrypterDecrypter().encryptData(applicationUserDetail.getPassword().trim());
				if (isBelcoUser) {
					loginType = "belcoLogin";
				} else {
					loginType = "normalLogin";
				}
				applicationUserDetail.setPassword(encPassword);
				applicationUserDetail.setDomainDetail(loggedInUser.getDomainDetail());

				if (applicationUserDetail.getUserId() == null) {
					applicationUserDetail.setSubmittedDate(new Date());
					applicationUserDetail.setSubmittedBy(loggedInUser);
					applicationUserDetail.setStatus(ConstantUtil.PENDINGACTIVE);
					applicationUserDetail.setEmailId(applicationUserDetail.getEmailId().toLowerCase());
					userDetailsaved = userDetailDAO.saveApplicationUser(applicationUserDetail);

					if (userDetailsaved != null) {

						result = null;
						sendActivationEmail(userDetailsaved.getEmailId().trim(), loginType);
						// setting user role mapping
						addRolesToUser(selectedRolesId, userDetailsaved, selectedRolesNames);

					}
				} else {
					applicationUserDetail.setUpdatedBy(loggedInUser);
					applicationUserDetail.setUpdatedDate(new Date());
					applicationUserDetail.setStatus(ConstantUtil.ACTIVE);
					result = userDetailDAO.updateUser(applicationUserDetail);

					// delete mappings
					Long userId = applicationUserDetail.getUserId();
					userRolesMappingDAO.deleteMapping(userId);

					// add mappings
					addRolesToUser(selectedRolesId, applicationUserDetail, selectedRolesNames);

				}
			}
		} catch (Exception e1) {
			// send Email
			e1.printStackTrace();
		}
		return result;
	}

	// This method is used for authencating the user
	@Override
	public UserDetail authenticateUser(String emailId, String password) throws Exception {

		UserDetail userDetails = applicationUserDetailDAO.getUserDetails(emailId.toLowerCase());
		String decPassword = "";
		if (userDetails != null) {
			EncrypterDecrypter ed = new EncrypterDecrypter();
			decPassword = ed.decryptData(userDetails.getPassword().trim());
			if (emailId.equals(userDetails.getEmailId()) && password.equals(decPassword)) {
				return userDetails;
			}
		}
		return null;
	}

	// This method is used for getting the user details for forgot password
	@Override
	public String getUserDetails(String emailId) throws Exception {
		String result = null;
		UserDetail userDetails = applicationUserDetailDAO.getUserDetails(emailId.toLowerCase());
		if (userDetails != null) {
			String decryptpassword = new EncrypterDecrypter().decryptData(userDetails.getPassword());
			sendPassword(decryptpassword, userDetails);
			result = "success";
		}
		return result;
	}

	// This method is used for sending password to the user
	private void sendPassword(String decryptpassword, UserDetail userDetails) throws Exception {
		String hostName = PropertyFileReader.getInstance().getStringProperty("hostName");
		String portNumber = PropertyFileReader.getInstance().getStringProperty("portNumber");
		String appName = PropertyFileReader.getInstance().getStringProperty("applicationName");
		String actionUrl = "Please find the password given below.\n Password : " + decryptpassword;
		String emailText = "Hello " + userDetails.getFirstName() + ", ";
		emailText += "\nThanks for showing interest and registring in <a href='http://" + hostName + ":" + portNumber + "/" + appName + "/'> Ecosystem<a>. " + "\n" + actionUrl
				+ "\n Enjoy our services. \nThanks!";
		EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
		emailNotificationBean.setEmailTo(userDetails.getEmailId().toLowerCase());
		emailNotificationBean.setEmailSubject("Ecosystem : Forgotten Password");
		emailNotificationBean.setEmailContent(emailText);
		emailNotificationBean.setEmailFrom(userDetails.getEmailId());
		emailNotificationBean.setSubmittedBy(userDetails.getUserId());
		emailNotificationBean.setSubmittedDate(new Date());
		emailNotificationBean.setDomainId(userDetails.getDomainIdTransient());
		emailHandlerThread.executeEmailTask(emailNotificationBean);
	}

	// This method will check email id in UserDetail Table , if present sends
	// mail to user else returns null.
	@Override
	public String sendActivationLinkIfPresentInUserDetails(String emailId, String loginType) throws Exception {
		String result = null;
		UserDetail userDetails = applicationUserDetailDAO.getUserDetailsForActivation(emailId.toLowerCase());
		EncrypterDecrypter ed = new EncrypterDecrypter();
		if (userDetails != null) {
			String encryptEmailId = ed.encryptData(userDetails.getEmailId());
			String encryptName = ed.encryptData(userDetails.getFirstName());
			sendActivationLink(encryptName, encryptEmailId, userDetails, loginType);
			result = "success";
		}
		return result;
	}

	// This method is used for sending the activation link
	private void sendActivationLink(String encryptName, String encryptEmailId, UserDetail userDetails, String loginType) throws Exception {
		PropertyFileReader propertyFileReader = PropertyFileReader.getInstance();
		String hostName = propertyFileReader.getStringProperty("hostName");
		String portNumber = propertyFileReader.getStringProperty("portNumber");
		String appName = propertyFileReader.getStringProperty("applicationName");
		String loginForTemp = "manageUsers";
		String actionUrl = "http://" + hostName + ":" + portNumber + "/" + appName + "/activateTemporaryUserAccount?emailID=" + encryptEmailId + "&Name=" + encryptName + "&LoginType=" + loginType
				+ "&LoginFrom=" + loginForTemp;
		String emailText = "Hello " + userDetails.getFirstName() + ", ";
		emailText += "\nThanks for showing interest and registering in <a href='http://" + hostName + ":" + portNumber + "/" + appName + "/'> Ecosystem. <a> " + " Please <a href='" + actionUrl
				+ "'>click here to activate</a>  your account and enjoy our services. \nThanks!";
		EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
		emailNotificationBean.setEmailTo(userDetails.getEmailId().toLowerCase());
		emailNotificationBean.setEmailSubject("Ecosystem : Activation Link");
		emailNotificationBean.setEmailContent(emailText);
		emailNotificationBean.setEmailFrom(userDetails.getEmailId());
		emailNotificationBean.setSubmittedBy(userDetails.getUserId());
		emailNotificationBean.setSubmittedDate(new Date());
		emailNotificationBean.setDomainId(userDetails.getDomainIdTransient());
		emailHandlerThread.executeEmailTask(emailNotificationBean);
	}

	@Override
	public List<UserDetail> getSupervisorListBasedOnDesigId(List<DesignationHierarchyDetails> desigHierarchyList, List<DesignationDirectory> desigDirList) throws Exception {
		StringBuilder ids = new StringBuilder();
		if (null != desigHierarchyList) {
			for (DesignationHierarchyDetails desigInfo : desigHierarchyList) {
				ids.append(String.valueOf(desigInfo.getAssociatedParentDesignation().getDesignationId())).append(",");
			}
		}
		if (null != desigDirList) {
			for (DesignationDirectory desigInfo : desigDirList) {
				ids.append(String.valueOf(desigInfo.getDesignationId())).append(",");
			}
		}
		String parentDesignationIds = ids.deleteCharAt(ids.lastIndexOf(",")).toString();
		List<UserDetail> userList = applicationUserDetailDAO.getSupervisorListBasedOnDesigId(parentDesignationIds);
		return userList;
	}

	@Override
	public UserDetail getUserDetailById(Long userDetailId) {
		return applicationUserDetailDAO.getUserDetailById(userDetailId);
	}

	// To add roles for every individual user added by meghana
	public void addRolesToUser(String selectedRolesId[], UserDetail applicationUserDetail, String[] selectedRolesNames) throws Exception {
		List<UserRolesMapping> userRolesMappingList = new ArrayList<UserRolesMapping>();
		for (int i = 0; i < selectedRolesId.length; i++) {

			UserRoles userRoles = new UserRoles();
			UserRolesMapping userRolesMapping = new UserRolesMapping();
			userRolesMapping.setUserRolesDetail(applicationUserDetail);
			userRoles.setRoleId(Long.parseLong(selectedRolesId[i]));
			userRolesMapping.setUserEmailId(applicationUserDetail.getEmailId().toLowerCase());
			userRolesMapping.setRoleName(selectedRolesNames[i]);
			userRolesMapping.setUserRolesId(userRoles);
			userRolesMapping.setIsDefaultRole(false);
			userRolesMappingList.add(userRolesMapping);
		}
		userRolesMappingDAO.addUserRoleMapping(userRolesMappingList);
	}

	// This method will check email id in UserDetail Table , if present sends
	// mail to user else returns null.
	@Override
	public String sendActivationEmail(String emailId, String loginType) throws Exception {
		String result = null;
		UserDetail userDetails = userDetailDAO.getUserDetailsForActivation(emailId);
		EncrypterDecrypter ed = new EncrypterDecrypter();
		if (userDetails != null) {
			String encryptEmailId = ed.encryptData(userDetails.getEmailId().toLowerCase());
			String encryptName = ed.encryptData(userDetails.getFirstName());
			sendActivationEmail(encryptName, encryptEmailId, userDetails, loginType);
			result = "success";
		}
		return result;
	}

	// This method is used for sending the activation link
	private void sendActivationEmail(String encryptName, String encryptEmailId, UserDetail userDetails, String loginType) throws Exception {
		PropertyFileReader propertyFileReader = PropertyFileReader.getInstance();
		String hostName = propertyFileReader.getStringProperty("hostName").trim();
		String portNumber = propertyFileReader.getStringProperty("portNumber");
		String appName = propertyFileReader.getStringProperty("applicationName");
		String loginForTemp = "manageUsers";
		String actionUrl = "http://" + hostName + ":" + portNumber + "/" + appName + "/activateTemporaryUserAccount?emailID=" + encryptEmailId + "&Name=" + encryptName + "&LoginType=" + loginType
				+ "&LoginFrom=" + loginForTemp;

		String emailText = "Hello " + userDetails.getFirstName() + ", ";
		emailText += "\nThanks for showing interest and registering in <a href='http://" + hostName + ":" + portNumber + "/" + appName + "/'> Ecosystem. </a> " + " Please <a href='" + actionUrl
				+ "'>click here to activate</a>  your account and enjoy our services. \nThanks!";
		EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
		emailNotificationBean.setEmailTo(userDetails.getEmailId());
		emailNotificationBean.setEmailSubject("Ecosystem : Activation Link");
		emailNotificationBean.setEmailContent(emailText);
		emailNotificationBean.setEmailFrom(userDetails.getEmailId().toLowerCase());
		emailNotificationBean.setSubmittedBy(userDetails.getUserId());
		emailNotificationBean.setSubmittedDate(new Date());
		emailNotificationBean.setDomainId(userDetails.getDomainIdTransient());
		emailHandlerThread.executeEmailTask(emailNotificationBean);
	}

	@Override
	public ArrayList<UserDetail> getUserDetailListById(StringBuilder userIds) throws Exception {
		if (userIds.length() > 1) {
			if (userIds.lastIndexOf(",") == userIds.length() - 1) {
				userIds = userIds.deleteCharAt(userIds.lastIndexOf(","));
			}
			return userDetailDAO.getUserDetailListById(userIds.toString());
		} else {
			return null;
		}

	}
}
