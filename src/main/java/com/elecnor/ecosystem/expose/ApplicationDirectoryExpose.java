package com.elecnor.ecosystem.expose;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class ApplicationDirectoryExpose {

	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;
	
	@RequestMapping(value = "/excludeInterceptor/getApplicationDetailsByApplicationId")
	public @ResponseBody String getApplicationDetailsByApplicationId(HttpServletRequest request) {
		Utility util = new Utility();
		String responce = null;
		try {
			int applicationId = Integer.valueOf(request.getParameter("applicationId"));
			ApplicationDirectory applicationDetailsBean = applicationDetailsDAO.getApplicationDetailsByApplicationId(applicationId);
			if (applicationDetailsBean != null) {
				responce = util.getJsonResult(applicationDetailsBean);
			} else {
				responce = util.getJsonResult("failed");
			}
			
		} catch (Exception e) {
			responce = util.getJsonResult("failed");
			e.printStackTrace();
		}
		return responce;
	}
	
	@RequestMapping(value = "/excludeInterceptor/getApplicationDetailsByApplicationName")
	public @ResponseBody String getApplicationDetailsByApplicationName(HttpServletRequest request) {
		Utility util = new Utility();
		String responce = null;
		try {
			String applicationName = request.getParameter("applicationName");
			ApplicationDirectory applicationDetailsBean = applicationDetailsDAO.getApplicationDetailsByApplicationName(applicationName);
			if (applicationDetailsBean != null) {
				responce = util.getJsonResult(applicationDetailsBean);
			} else {
				responce = util.getJsonResult("failed");
			}
			
		} catch (Exception e) {
			responce = util.getJsonResult("failed");
			e.printStackTrace();
		}
		return responce;
	}
}
