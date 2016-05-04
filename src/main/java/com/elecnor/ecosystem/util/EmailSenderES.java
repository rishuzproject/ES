package com.elecnor.ecosystem.util;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
/*import javax.activation.DataHandler;
 import javax.activation.DataSource;
 import javax.activation.FileDataSource;
 */
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import com.elecnor.ecosystem.bean.EmailAttachmentBean;
import com.elecnor.ecosystem.bean.NotificationCenterBean;
import com.elecnor.ecosystem.service.NotificationCenterService;
import com.email.utility.bean.EmailAttachmentBean;

/**
 * @author Vaibhav
 * 
 */
@Service
public class EmailSenderES {
	

	@Autowired
	NotificationCenterService notificationCenterService;
//	@Autowired
//    NotificationCenterBean emailNotificationBean ;
	/*@Autowired
    NotificationCenterBean emailNotificationBean ;*/
	
	@Value("${email.environment.mode}")
	String emailEnvironmentMode;
	
	@Value("${dev.mode.to.email}")
	String devModeToEmail;
	
	@Value("${dev.mode.cc.email}")
	String devModeCcEmail;
	
	@Value("${dev.mode.bcc.email}")
	String devModeBccEmail;
	
	@Value("${exception.mail.subject}")
	String exceptionMailSubject;
	
	Logger logger = Logger.getLogger(EmailSenderES.class);

	public EmailSenderES() {
	}

	private String protocol;
	private String host;
	private int smtpPortNo;
	private String smtpHost;
	private String emailId;
	private String password;

	public Store store = null;
	public Session session = null;
	public Properties props = null;

	private void doInit() throws Exception{
		PropertyFileReader propertReader = PropertyFileReader.getInstance();
		emailId = propertReader.getStringProperty("errorMailFrom");
		password = propertReader.getStringProperty("errorMailPassword");
		protocol = propertReader.getStringProperty("mailProtocol");
		host = propertReader.getStringProperty("mailHost");
		smtpHost = propertReader.getStringProperty("mailSmtpHost");
		smtpPortNo = propertReader.getIntProperty("mailSmtpPortNo");
		props = System.getProperties();
		props.setProperty("mail.store.protocol", protocol);
		Authenticator auth = new EmailAuthentication(emailId, password);
		session = Session.getDefaultInstance(props, auth);
		store = session.getStore("pop3");
		store.connect(host, emailId, password);
		props.put("mail.smtp.port", smtpPortNo);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "false");
		props.put("mail.smtp.ssl.enable", "false");
	}

	public void sendExceptionMail(String msgContent) {
		try {
			// Commenting this code, Reading all these values from spring resource property. - By Vaibhav
			/*PropertyFileReader propertyReader = PropertyFileReader.getInstance();
			String msgRecipients = propertyReader.getStringProperty("errorMailTo");
			String cc = propertyReader.getStringProperty("errorMailCC");
			String subject = propertyReader.getStringProperty("errorMailSubject");*/

			// bcc & attachmentPos is hardcoded NULL in case of exception email
			sendMail(devModeToEmail, devModeCcEmail, devModeBccEmail, exceptionMailSubject, msgContent, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendNotificationMail(String toList, String ccList, String bccList, String emailSubject,
			String emailText, boolean isException , String attachmentpos) throws Exception{
		try {
			// In case of Exception email, Read the Property file to get To/CC/Sub from property file.
			if(isException){
				PropertyFileReader propertyReader = PropertyFileReader.getInstance();
				toList = propertyReader.getStringProperty("errorMailTo");
				ccList = propertyReader.getStringProperty("errorMailCC");
				emailSubject = propertyReader.getStringProperty("errorMailSubject");
			}
			// bcc & attachmentPos is hardcoded NULL in case of exception email
			sendMail(toList, ccList, bccList, emailSubject, emailText, null);
			
			/*emailNotificationBean.setAllValues(toList, ccList, bccList, emailSubject, emailText);
			sendMail(toList, ccList, bccList, emailSubject, emailText, attachmentpos);
			/*NotificationCenterBean emailNotificationBean = new NotificationCenterBean();
			emailNotificationBean.setAllValues(toList, ccList, bccList, emailSubject, emailText);
			System.out.println(emailNotificationBean);
			notificationCenterService.saveNotification(emailNotificationBean);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendNotificationMail(NotificationCenterBean notificationBean, List<EmailAttachmentBean> attachmentList) throws Exception{
		try {
	
			// Emails will be send on in Production Mode
			// In Test Mode - All emails will be sent to Development Team
			// In Dev Mode, No email will be sent 
			if(emailEnvironmentMode!=null && emailEnvironmentMode.equalsIgnoreCase(ConstantUtil.ENVIRONMENT_PRODUCTION))
				sendMail(notificationBean.getEmailTo(), notificationBean.getEmailCc(), notificationBean.getEmailBcc(), 
						notificationBean.getEmailSubject(), notificationBean.getEmailContent(), attachmentList);
			else if(emailEnvironmentMode!=null && emailEnvironmentMode.equalsIgnoreCase(ConstantUtil.ENVIRONMENT_TEST))
				sendMail(devModeToEmail, devModeCcEmail, devModeBccEmail, 
						notificationBean.getEmailSubject(), notificationBean.getEmailContent(), attachmentList);
			
			// Save Email Bean to DB.
			notificationCenterService.saveNotification(notificationBean);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void sendMail(String to, String cc, String bcc, String subject,
			String msgContent, List<EmailAttachmentBean> emailAttachmentList) throws Exception{

		PropertyFileReader propertyFileReader = PropertyFileReader.getInstance();
		doInit();
		msgContent += "<br/><br/><br/><br/> This is a system generated email, do not reply to this email id.";
		MimeMessage message = new MimeMessage(session);
		// Set From: header field of the header.
		message.setFrom(new InternetAddress(emailId));
		// Set To: header field of the header.
		message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		if (cc != null && cc.length() != 0) 
			message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));

		if (bcc != null && bcc.length() != 0) {
			bcc = bcc.replaceAll(";", ",");
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
		}
		// Set Subject: header field
		message.setSubject(subject);
		/* Start Here */
		// Create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		// Fill the message
		messageBodyPart.setText("<pre style='font-size:16px;font-family: Calibri'>" + msgContent + "</pre>", null, "html");
		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		//please don't delete this code before deleting please let me know @Siva Sankar
		//this code is for sending email with attachment
		if(emailAttachmentList!=null && !emailAttachmentList.isEmpty()){
			for(EmailAttachmentBean emailAttachmentBean : emailAttachmentList){
				MimeBodyPart attachmentPart = new MimeBodyPart();

				DataSource source = new ByteArrayDataSource(emailAttachmentBean.getFileContent(), "text/html");
				attachmentPart.setDataHandler(new DataHandler(source));
				attachmentPart.setFileName(emailAttachmentBean.getFileName());

				multipart.addBodyPart(attachmentPart);
			}
			/*if (attachmentpos != null && attachmentpos.contains("&&&&")) {
				MimeBodyPart attachmentPart = new MimeBodyPart();
				String[] strArray = attachmentpos.split("&&&&");
				String attchFileContent = strArray[0];
				String attchFileName = strArray[1].trim();
				DataSource source = new FileDataSource(attchFileContent);
				attachmentPart.setDataHandler(new DataHandler(source));
				attachmentPart.setFileName(attchFileName);
				multipart.addBodyPart(attachmentPart);
			}*/
		}
		// Set text message part
		multipart.addBodyPart(messageBodyPart);
		// Send the complete message parts
		message.setContent(multipart);
		// Send message
		Transport.send(message);
		/* End Here */

		System.out.println("Sent message successfully.... Sent to " + to); 
	}

	public static void main(String[] agrs) throws Exception {

		System.out.println("==== Sending email ==== ");
		EmailSenderES emainConn = new EmailSenderES();
		emainConn.sendMail("ankur.srivastav@cerridsolutions.com", "", "", "Test",
				"Test", null);
		// emainConn.getMailConnection("vdixit@cerridsolutions.com" ,
		// "vaibhav123");
		// emainConn.sendMail("vdixit@cerridsolutions.com", "vdixit", "Test",
		// "Test Mail", "vdixit@cerridsolutions.com",
		// "vdixit@cerridsolutions.com", "");
		emainConn.sendExceptionMail("");
	}
}

class EmailAuthentication extends Authenticator {

	private String password;
	private String emailId;

	public EmailAuthentication(String emailId, String password) {
		this.password = password;
		this.emailId = emailId;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(emailId, password);
	}
}
