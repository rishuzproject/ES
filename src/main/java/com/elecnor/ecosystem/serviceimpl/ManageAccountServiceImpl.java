package com.elecnor.ecosystem.serviceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.service.ManageAccountService;
import com.elecnor.ecosystem.util.Utility;

@Service
public class ManageAccountServiceImpl implements ManageAccountService {
	/*@Autowired
	EmailLogDetailsService emailLogDetailsService;*/

	@Override
	public String setEmailSendFromManageAccount(HttpServletRequest request, MultipartFile fileUpload,HttpSession session) throws Exception {

		Utility util = new Utility();
		String result = null;
		/*try {

			String toEmail = null;
			String ccEmail = null;
			String bccEmail = null;
			String emailText = "";
			String attachement = null;
			String emailSub = null;
			if (request.getParameter("toEmail") != null && !request.getParameter("toEmail").equals("")) {
				toEmail = request.getParameter("toEmail").replace(";", ",");
			}
			if (request.getParameter("ccEmail") != null && !request.getParameter("ccEmail").equals("")) {
				ccEmail = request.getParameter("ccEmail").replace(";", ",");
			}
			if (request.getParameter("bccEmail") != null && !request.getParameter("bccEmail").equals("")) {
				bccEmail = request.getParameter("bccEmail").replace(";", ",");
			}
			if (request.getParameter("emailSubject") != null && !request.getParameter("emailSubject").equals("")) {
				emailSub = request.getParameter("emailSubject");
			}
			if (request.getParameter("emailBodyMAHidden") != null
					&& !request.getParameter("emailBodyMAHidden").equals("")) {
				emailText += request.getParameter("emailBodyMAHidden");
			}

			if (!fileUpload.isEmpty()) {
				String url = session.getServletContext().getRealPath("/resource");
				File file = new File(url + "/" + fileUpload.getOriginalFilename());
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream fop = new FileOutputStream(file);
				fop.write(fileUpload.getBytes());
				fop.flush();
				fop.close();
				attachement = file.getPath() + "&&&& " + fileUpload.getOriginalFilename();
			}
			Thread thread = new Thread(new EmailThread(toEmail, ccEmail, bccEmail, emailSub, emailText, false, attachement));
			thread.start();
			
			//set email log
			Map<Object, Object> logMap = new HashMap<Object, Object>();
			logMap.put("emailTo", toEmail);
			logMap.put("emailFrom", "ECOSYSTEM");
			logMap.put("emailCc", ccEmail);
			logMap.put("emailBcc", bccEmail);
			logMap.put("emailSubject", emailSub);
			logMap.put("emailContent", emailText);
			logMap.put("logRead", false);
			emailLogDetailsService.setEmailLogDetailsBean(util.getJsonResult(logMap), null);
//			result = emailLogDetailsService.setEmailLogDetailsBean(cutomerApprovalEmailTo, userDetail.getEmailId(), cutomerApprovalEmailCc, cutomerApprovalEmailBcc,
//					"Request For Approval", emailText, null, userDetail);
		} catch (IOException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (Exception ex) {
			result = ex.getMessage();
			ex.printStackTrace();
		}*/
		return result;
	}

}
