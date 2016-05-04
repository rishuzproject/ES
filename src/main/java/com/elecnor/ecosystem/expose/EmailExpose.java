package com.elecnor.ecosystem.expose;

import org.springframework.stereotype.Controller;

@Controller
public class EmailExpose {

	/*@RequestMapping(value = "/excludeInterceptor/sendNotificationEmail")
	@ResponseBody
	public String sendNotificationEmail(
			HttpServletRequest request) {
		Utility util = new Utility();
		try {
			System.out.println("toEmail"+request.getParameter("toEmail"));
			System.out.println("ccEmail "+request.getParameter("ccEmail"));
			System.out.println("subject"+request.getParameter("subject"));
			System.out.println("msgContent "+request.getParameter("msgContent"));
			
			Thread thread = new Thread(new EmailThread(request.getParameter("toEmail"), request.getParameter("ccEmail"), request.getParameter("bccEmail"), request.getParameter("subject"), request.getParameter("msgContent"), false, request.getParameter("attachmentpos")));
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
