package com.elecnor.ecosystem.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.service.UserDetailService;
import com.elecnor.ecosystem.util.EncrypterDecrypter;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class CompanySwitchController {
	
	@Autowired
	UserDetailDAO userDetailDAO;
	
	@Autowired
	UserDetailService userProfileService;
	
	@RequestMapping(value="/switchingCompany" ,method=RequestMethod.POST) 
	@ResponseBody
    public String  getSwitchedCompanyRole(HttpServletRequest request,HttpSession session)
    {
		
		Long domainId=Long.parseLong(request.getParameter("selectedCompanyId"));
		try {
			String emailId="admin@reportntrack.com";
			String password=request.getParameter("password");
			UserDetail selectedUser = userProfileService.authenticateUser(emailId, password);
			UserDetail selectedUserDetail=userDetailDAO.getAdminOfSelectedCompany(domainId);
			session.setAttribute("isReportnTrackUser", false);
			session.setAttribute("userRole", 1);
			session.setAttribute("selectedUser", selectedUserDetail);
			return "success";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;	
    }
	@RequestMapping(value="/getSessionValues", method=RequestMethod.POST)
	@ResponseBody
	public String getSessionValues(HttpSession session) throws Exception
	{
		
		if(session.getAttribute("selectedUser")!=null){
		Utility util = new Utility();
		HashMap<Object,Object> sessionList=new HashMap<Object,Object>();
		UserDetail userDetail=(UserDetail)session.getAttribute("selectedUser");
		sessionList.put("selectedUserDetails", userDetail);
		EncrypterDecrypter decryptedPassword=new EncrypterDecrypter();
		sessionList.put("password",decryptedPassword.decryptData(userDetail.getPassword()));
		return util.getJsonResult(sessionList);
		}
		else
			return null;
		
	}
	
}
