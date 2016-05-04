package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DomainDetailDAO;
import com.elecnor.ecosystem.helper.DomainDetailHelper;
import com.elecnor.ecosystem.service.DomainDetailService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class DomainDetailController {

	@Autowired
	DomainDetailDAO domainDetailDAO;

	/**
	 * Method for get domain details
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@Autowired
	DomainDetailService domainDetailService;

	@RequestMapping("/updateDomainInfo")
	public String getUpdateDomainInfoPage() {
		return "updateDomainInfo";
	}

	/*// To Be Discussed with hari
	@ModelAttribute(value = "/updateDomainInfoForm")
	public DomainDetail generateDomainInfo() {
		return new DomainDetail();
	}*/

	@RequestMapping(value = "/getDomainInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getDomainDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		Boolean isTemporaryUser = (Boolean) session.getAttribute("isTemporaryUser");
		if (!isTemporaryUser) {
			try {
				UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
				DomainDetail domainDetails = userDetails.getDomainDetail();
				long signUpDomainId = domainDetails.getDomainId();
				ArrayList<DomainDetail> domainDetail = domainDetailDAO.getDomainDetail(signUpDomainId);
				map.put("allDomainDetails", domainDetail);
			} catch (Exception e) {
				util.handleException(map, e);
			}

		}
		return util.getJsonResult(map);
	}

	// method for save and update application user
	@RequestMapping(value = "/updateDomainInfo", method = RequestMethod.POST)

	public @ResponseBody
	String setUpdateDomainInfo(
			@ModelAttribute("updateDomainInfoForm") DomainDetail domainDetail,
			//domain logo code..do not remove
			//@RequestParam("templateFile") MultipartFile domainLogo,
			HttpSession session, HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		DomainDetailHelper domainDetailHelper = new DomainDetailHelper();
		try {

			UserDetail userDetails = (UserDetail) session
					.getAttribute("selectedUser");
			//domain logo code..do not remove
			//domainDetail = util.getMultipartFileAsBlob(domainDetail, domainLogo);
			String result = domainDetailService.setSaveOrUpdateDomain(
					domainDetail, userDetails);
			resultMap = domainDetailHelper.getResultMap(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

}
