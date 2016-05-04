package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Vaibhav
 * This Entity will have all the Notification Raised by downstream application & Ecosystem itself.
 *
 */
@Component
@Entity
@Table(name = "NOTIFICATION_CENTER")
public class NotificationCenterBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMAIL_NOTIFICATION_ID")
	@Expose
	private Long emailNotificationId;
	
	@Column(name = "EMAIL_FROM")
	@Expose
	private String emailFrom;
	
	@Column(name = "EMAIL_TO")
	@Expose
	private String emailTo;
	
	@Column(name = "EMAIL_CC")
	@Expose
	private String emailCc;
	
	@Column(name = "EMAIL_BCC")
	@Expose
	private String emailBcc;
	
	@Column(name = "EMAIL_SUBJECT")
	@Expose
	private String emailSubject;
	
	@Column(name = "EMAIL_CONTENT")
	@Expose
	private String emailContent;
	
	@Column(name = "EMAIL_SOURCE_APP")
	@Expose
	private String emailSourceApp;
	
	@Column(name = "IS_EMAIL_READ")
	@Expose
	private Boolean isEmailRead;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;
	
	@Column(name = "SUBMITTED_BY")
	@Expose
	private Long submittedBy;
	
	@Column(name = "DOMAIN_ID")
	@Expose
	private Long domainId;
	


	
	public void setAllValues(String toList, String ccList, String bccList, String emailSubject, String emailText/*,UserDetail userDetail*/){
		emailTo=toList;
		emailCc=ccList;
		emailBcc=bccList;
		this.emailSubject=emailSubject;
		emailContent=emailText;
		/*submittedBy=userDetail.getUserId();
		domainDetail=userDetail.getDomainDetail().getDomainId();*/
	}

	public NotificationCenterBean() {

	}

	/**
	 * @return the emailNotificationId
	 */
	public Long getEmailNotificationId() {
		return emailNotificationId;
	}

	/**
	 * @param emailNotificationId the emailNotificationId to set
	 */
	public void setEmailNotificationId(Long emailNotificationId) {
		this.emailNotificationId = emailNotificationId;
	}

	/**
	 * @return the emailFrom
	 */
	public String getEmailFrom() {
		return emailFrom;
	}

	/**
	 * @param emailFrom the emailFrom to set
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/**
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}

	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	/**
	 * @return the emailCc
	 */
	public String getEmailCc() {
		return emailCc;
	}

	/**
	 * @param emailCc the emailCc to set
	 */
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	/**
	 * @return the emailBcc
	 */
	public String getEmailBcc() {
		return emailBcc;
	}

	/**
	 * @param emailBcc the emailBcc to set
	 */
	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}

	/**
	 * @return the emailSubject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @param emailSubject the emailSubject to set
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * @return the emailContent
	 */
	public String getEmailContent() {
		return emailContent;
	}

	/**
	 * @param emailContent the emailContent to set
	 */
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	/**
	 * @return the emailSourceApp
	 */
	public String getEmailSourceApp() {
		return emailSourceApp;
	}

	/**
	 * @param emailSourceApp the emailSourceApp to set
	 */
	public void setEmailSourceApp(String emailSourceApp) {
		this.emailSourceApp = emailSourceApp;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the isEmailRead
	 */
	public Boolean getIsEmailRead() {
		return isEmailRead;
	}

	/**
	 * @param isEmailRead the isEmailRead to set
	 */
	public void setIsEmailRead(Boolean isEmailRead) {
		this.isEmailRead = isEmailRead;
	}

	/**
	 * @return the submittedBy
	 */
	public Long getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @param submittedBy the submittedBy to set
	 */
	public void setSubmittedBy(Long submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @return the domainId
	 */
	public Long getDomainId() {
		return domainId;
	}

	/**
	 * @param domainId the domainId to set
	 */
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
}
