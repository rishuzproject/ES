package com.elecnor.ecosystem.restservicecontroller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.elecnor.ecosystem.bean.EmailAttachmentBean;
//import com.elecnor.ecosystem.bean.EmailNotificationBean;
import com.elecnor.ecosystem.bean.NotificationCenterBean;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.EmailSenderES;
import com.email.utility.bean.EmailAttachmentBean;
import com.email.utility.bean.EmailNotificationBean;
import com.google.gson.Gson;

@Controller
public class EmailNotificationController {

	/*
	 * @Autowired EmailLogDetailsService emailLogDetailsService;
	 * 
	 * @Autowired EmailService emailService;
	 */

	@Autowired
	EmailSenderES emailSender;

	/*
	 * @RequestMapping(value="/excludeInterceptor/setEmailLogInEcosystem")
	 * 
	 * @ResponseBody public void fetchAllJobs(String emailLogJson){ Gson gson =
	 * new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
	 * "MM-dd-yyyy").create(); try { JsonObject o = gson.fromJson(emailLogJson,
	 * JsonObject.class); String toEmail = gson.fromJson(o.get("emailTo"),
	 * String.class); String ccEmail = gson.fromJson(o.get("emailCc"),
	 * String.class); String bccEmail = gson.fromJson(o.get("emailBcc"),
	 * String.class); String emailSubject = gson.fromJson(o.get("emailSubject"),
	 * String.class); String emailText = gson.fromJson(o.get("emailContent"),
	 * String.class); Boolean logRead = gson.fromJson(o.get("logRead"),
	 * Boolean.class); Boolean isException = gson.fromJson(o.get("isException"),
	 * Boolean.class); String attachment = gson.fromJson(o.get("attachment"),
	 * String.class); UserDetail userDetail =
	 * gson.fromJson(o.get("userDetails"), UserDetail.class); // thread for
	 * sending email Thread thread = new Thread(new EmailThread(toEmail,
	 * ccEmail, bccEmail, emailSubject, emailText, isException, attachment));
	 * thread.start();
	 * 
	 * emailLogDetailsService.setEmailLogDetailsBean(emailLogJson, userDetail);
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * } }
	 */

	@RequestMapping(value = "/excludeInterceptor/sendNotificationEmail")
	@ResponseBody
	public String sendNotificationEmail(@RequestParam("emailJsonString") String emailJsonString, @RequestParam("sourceAppName") String sourceAppName, HttpServletResponse response) {
		String responseString = ConstantUtil.SUCCESS;
		try {
			Gson gson = new Gson();
			NotificationCenterBean notificationBean = gson.fromJson(emailJsonString, NotificationCenterBean.class);
			notificationBean.setEmailSourceApp(sourceAppName);

			// This is to get the attchment list.
			// EmailNotificationBean emailBean = gson.fromJson(emailJsonString,
			// EmailNotificationBean.class);
			// List<EmailAttachmentBean> attachmentList = null;
			// if(emailBean!=null && emailBean.getEmailAttachmentList()!=null)
			// attachmentList = emailBean.getEmailAttachmentList();
			// else
			// attachmentList = new ArrayList<EmailAttachmentBean>();

			// Below method will Send the email and Save in the Notification
			// table.
			emailSender.sendNotificationMail(notificationBean, null);

			// responseString = emailService.logAndSendEmail(emailJsonString);
		} catch (Exception e) {
			responseString = ConstantUtil.FAILURE;
			e.printStackTrace();
		}
		return responseString;
	}

	@RequestMapping(value = "/excludeInterceptor/sendNotificationEmailWithAttachment", method = RequestMethod.POST)
	@ResponseBody
	public String sendNotificationEmailWithAttachment(@RequestBody EmailNotificationBean emailBean, HttpServletRequest request, HttpServletResponse response) {
		String responseString = ConstantUtil.SUCCESS;
		Gson gson = new Gson();
		try {
			String emailJsonString = gson.toJson(emailBean);
			NotificationCenterBean notificationBean = gson.fromJson(emailJsonString, NotificationCenterBean.class);
			// notificationBean.setEmailSourceApp(sourceAppName);

			// This is to get the attchment list.
			EmailNotificationBean emailNotificationBean = gson.fromJson(emailJsonString, EmailNotificationBean.class);
			List<EmailAttachmentBean> attachmentList = null;
			if (emailNotificationBean != null && emailNotificationBean.getEmailAttachmentList() != null)
				attachmentList = emailNotificationBean.getEmailAttachmentList();
			else
				attachmentList = new ArrayList<EmailAttachmentBean>();
			// Below method will Send the email and Save in the Notification
			// table.
			emailSender.sendNotificationMail(notificationBean, attachmentList);
		} catch (Exception e) {
			responseString = ConstantUtil.FAILURE;
			e.printStackTrace();
		}
		return responseString;
	}
}
