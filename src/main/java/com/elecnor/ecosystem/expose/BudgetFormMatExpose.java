package com.elecnor.ecosystem.expose;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.service.BudgetCodeService;

@Controller
public class BudgetFormMatExpose {

	@Autowired
	BudgetCodeService budgetCodeService;
	
	@RequestMapping(value = "/excludeInterceptor/getBelcoMatDescription")
	public @ResponseBody String getVendorListByDomainId(
			HttpServletRequest request) {
		Integer belcoMatCode = Integer.parseInt(request.getParameter("belcoMatCode"));
		try {
			return budgetCodeService.getBelcoMatDescription(belcoMatCode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
