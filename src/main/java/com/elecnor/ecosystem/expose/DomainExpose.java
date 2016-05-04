package com.elecnor.ecosystem.expose;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DomainDetailDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class DomainExpose {

	@Autowired
	UserDetailDAO applicationUserDetailDAO;

	@Autowired
	DomainDetailDAO domainDetailDAO;

	@RequestMapping(value = "/getDomainBeanById")
	@ResponseBody
	public String getDomainBeanById(HttpServletRequest request) {
		UserDetail selectedUserBean;
		Utility util = new Utility();
		Long userId = Long.parseLong(request.getParameter("id"));
		//Server name and port number for authentication
		//System.out.println(request.getServerName() + request.getServerPort());
		try {
			selectedUserBean = applicationUserDetailDAO.getUserBean(userId);
			return util.getJsonResult(selectedUserBean.getDomainDetail());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
