package com.elecnor.ecosystem.expose;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.dao.VendorDirectoryDAO;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class VendorExpose {

	@Autowired
	VendorDirectoryDAO vendorDirectoryDAO;

	@RequestMapping(value = "/excludeInterceptor/getVendorListByDomainId")
	public @ResponseBody String getVendorListByDomainId(
			HttpServletRequest request) {
		Utility util = new Utility();
		Long domainId = Long.parseLong(request.getParameter("id"));
		try {
			return util.getJsonResult(vendorDirectoryDAO
					.getAllVendors(domainId));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
