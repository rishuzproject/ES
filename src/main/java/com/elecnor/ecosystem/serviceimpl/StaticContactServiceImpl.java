package com.elecnor.ecosystem.serviceimpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.service.StaticContactService;
import com.email.utility.executor.EmailTaskExecutor;

@Service
public class StaticContactServiceImpl implements StaticContactService{

	@Autowired
	EmailTaskExecutor emailHandlerThread;
	
	@Override
	public String sendMessage(HttpServletRequest request) {
		String result = null;
		String name= request.getParameter("inputName");
		String email = request.getParameter("inputEmail1");
		String message = request.getParameter("inputMessage");
		sendMessageToAdmin(name,email,message);
		return result;
	}

	private void sendMessageToAdmin(String name, String email, String message) {
		// hard coded admin email changed to be later
		// String adminEmail = "asish.rath@cerridsolutions.com";
		// String actionUrl =
		// "Name : "+name+"\nEmail : "+email+"\nMessage : "+message;
		// String emailText = "Hello Admin , ";
		// emailText +=
		// "\nThe following message is posted by the user given below." + "\n" +
		// actionUrl + "\n Enjoy our services. \nThanks!";
		// EmailNotificationBean emailNotificationBean = new
		// EmailNotificationBean();
		// emailNotificationBean.setEmailTo(adminEmail);
		// emailNotificationBean.setEmailSubject("Ecosystem : Message");
		// emailNotificationBean.setEmailContent(emailText);
		// emailNotificationBean.setEmailFrom(temporarySignupDetail.getEmailId());
		// emailNotificationBean.setSubmittedBy(temporarySignupDetail.getUserId());
		// emailNotificationBean.setSubmittedDate(new Date());
		// emailHandlerThread.executeEmailTask(emailNotificationBean);
		// Thread thread = new Thread(new
		// com.elecnor.ecosystem.util.EmailThread(adminEmail, null, null,
		// "Ecosystem : Message", emailText,false, null));
		// thread.start();
	}

}
