package com.elecnor.ecosystem.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.elecnor.ecosystem.bean.NotificationCenterBean;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.service.NotificationCenterService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.EmailSenderES;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class NotificationCenterController {

	/*@Autowired
	LogsDetailService logDetailService;*/
	
	@Value("${issueTracker.url}")
	String issueTrackerURL;
	
	@Autowired
	NotificationCenterService notificationCenterService;
	
	@Autowired
	Utility util;
	
	@Autowired
	EmailSenderES emailSender;

	/*@RequestMapping("/notification")
	public String getApplicationDetailPage() {
		return "notificationCenter";
	}*/

	/**
	 * Method for get all log details
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping(value = "/getNotificationData", method = RequestMethod.POST)
	@ResponseBody
	public String getNotificationData(HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		if ((Boolean) session.getAttribute("isTemporaryUser")) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", "Temporary User");
		} else {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			String user = userDetails.getEmailId();
			try {
				ArrayList<LogDetails> data = logDetailService.getLogByUser(user);
				resultMap.put("ajaxResult", "success");
				resultMap.put("logDetails", data);
			} catch (Exception e) {
				resultMap.put("ajaxResult", "failure");
				resultMap.put("reason", e.getMessage());
				util.sendExceptionEmail(e);
			}
		}
		return util.getJsonResult(resultMap);
	}

	*//**
	 * Method for update logs detail table with true
	 * 
	 * @param session
	 *//*
	@RequestMapping(value = "/updateNotificationData", method = RequestMethod.POST)
	@ResponseBody
	public void updateNotificationData(HttpServletRequest request, HttpSession session) {
		UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
		BigInteger lastLogId = new BigInteger(request.getParameter("lastLogId"));
		Utility util = new Utility();
		try {
			logDetailService.updateLogsOfUser(lastLogId, userDetails.getEmailId());
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}

	}*/

	/**
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getNotificationListForUser", method = RequestMethod.GET)
	@ResponseBody
	public List<NotificationCenterBean> getNotificationListForUser(HttpServletRequest request, HttpSession session) {
		
		List<NotificationCenterBean> notificationList = null;
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			notificationList = notificationCenterService.getNotificationListForUser(userDetails.getEmailId(), userDetails.getDomainDetail().getDomainId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		
		return notificationList;
	}
	
	@RequestMapping(value = "/getNotificationByID", method = RequestMethod.GET)
	@ResponseBody
	public NotificationCenterBean getNotificationByID(@RequestParam("emailNotificationId") Long emailNotificationId ) {
		
		NotificationCenterBean emailNotificationBean = null;
		try {
			emailNotificationBean = notificationCenterService.getNotificationByID(emailNotificationId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		
		return emailNotificationBean;
	}
	
	@RequestMapping(value = "/markNotificationAsRead", method = RequestMethod.GET)
	@ResponseBody
	public String markNotificationAsRead(@RequestParam("emailNotificationId") Long emailNotificationId ) {
		
		String resultStr = null;
		try {
			int updatedRecordCount = notificationCenterService.markNotificationAsRead(emailNotificationId);
			if(updatedRecordCount>0)
				resultStr = ConstantUtil.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultStr = ConstantUtil.FAILURE;
			util.sendExceptionEmail(e);
		}
		
		return resultStr;
	}
	
	// Write a API to get the total number of the Notifications - To show the Notification Count on the landing page.
	@RequestMapping(value = "/getNotificationCount", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Integer> getNotificationCount(HttpServletRequest request, HttpSession session) {
		Integer notificationCount = 0;
		HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			notificationCount = notificationCenterService.getNotificationCount(userDetails.getEmailId(), userDetails.getDomainDetail().getDomainId());
			
			resultMap.put("notificationCount", notificationCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		
		return resultMap;
	}
	
	// Write a API to send the email from Notification Center.
	@RequestMapping(value="/forwardNotificationEmail", method = RequestMethod.POST)
	@ResponseBody

	public String forwardNotificationEmail(@RequestBody NotificationCenterBean emailNotificationBean, HttpServletRequest request, HttpSession session){
		String responseString = ConstantUtil.SUCCESS;
		try{
			
			// Below method will Send the email and Save in the Notification table.
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			emailNotificationBean.setDomainId(userDetails.getDomainDetail().getDomainId());
			emailNotificationBean.setIsEmailRead(false);
			emailNotificationBean.setSubmittedBy(userDetails.getUserId());
			emailNotificationBean.setSubmittedDate(new Date());
			
			emailSender.sendNotificationMail(emailNotificationBean, null);
			
			//responseString = emailService.logAndSendEmail(emailJsonString);
		}
		catch(Exception e)
		{
			responseString = ConstantUtil.FAILURE;
			e.printStackTrace();
		}
		return responseString;
	}
	
	// Write a API to get the issue reported by the user.
	@RequestMapping(value = "/getIssueReportedListForUser", method = RequestMethod.GET)
	@ResponseBody
	public String getIssueReportedListForUser(HttpServletRequest request, HttpSession session) {
		
		// Returning a Hardcoded response now, Needs to be changes and response should be coming from IssueTracker Application
		// Make sure you don't change the attribute names in the response, otherwise UI will break. By Vaibhav. 
	
		
		
		Utility util = new Utility();
		RestTemplate restTemplate = new RestTemplate();
		String json = null;
		UserDetail userDetail=(UserDetail)session.getAttribute("selectedUser");
		try {
			json = restTemplate
					.getForObject(
							issueTrackerURL+"RedirectedFromEcosystem/getRecentIssuesByUser?userId="+userDetail.getUserId(),
							String.class);

			System.out.println(json);
		} catch (Exception e) {

			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			util.handleException(resultMap, e);
			e.printStackTrace();
		}
		return json;
		/*return "{" +
    "\"reportedIssueList\": [" +
        "{" +
            "\"applicationName\": \"MPR\"," +
            "\"summary\": \"Test Summary\"," +
            "\"severityName\": \"HIGH\"," +
            "\"assigneeName\": \"Vaibhav Dixit\"," +
            "\"percentageDone\": 45," +
            "\"dueDate\": \"08/25/2015\"," +
            "\"updatedDate\": \"1428650898000\"" +
        "}," +
        "{" +
            "\"applicationName\": \"MPR\"," +
            "\"summary\": \"Test Summary -2\"," +
            "\"severityName\": \"URGENT\"," +
            "\"assigneeName\": \"Vaibhav Dixit\"," +
            "\"percentageDone\": 60," +
            "\"dueDate\": \"08/20/2015\"," +
            "\"updatedDate\": \"1428650896000\"" +
        "}," +
        "{" +
            "\"applicationName\": \"PD\"," +
            "\"summary\": \"Test Summary\"," +
            "\"severityName\": \"LOW\"," +
            "\"assigneeName\": \"Vaibhav Dixit\"," +
            "\"percentageDone\": 67," +
            "\"dueDate\": \"08/19/2015\"," +
            "\"updatedDate\": \"1428650894500\"" +
        "}," +
        "{" +
            "\"applicationName\": \"FTS\"," +
            "\"summary\": \"Test Summary\"," +
            "\"severityName\": \"MODERATE\"," +
            "\"assigneeName\": \"Vaibhav Dixit\"," +
            "\"percentageDone\": 13," +
            "\"dueDate\": \"1/19/2015\"," +
            "\"updatedDate\": \"1428650893200\"" +
        "}," +
        "{" +
            "\"applicationName\": \"Ecosystem\"," +
            "\"summary\": \"Test Summary\"," +
            "\"severityName\": \"HIGH\"," +
            "\"assigneeName\": \"Vaibhav Dixit\"," +
            "\"percentageDone\": 42," +
            "\"dueDate\": \"09/20/2015\"," +
            "\"updatedDate\": \"1428650899700\"" +
       " }" +
    "]" +
"}";*/
	}
}
