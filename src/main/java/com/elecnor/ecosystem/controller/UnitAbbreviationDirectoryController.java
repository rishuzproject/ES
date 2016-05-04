package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UnitAbbreviationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.UnitAbbreviationDirectoryDAO;
import com.elecnor.ecosystem.service.UnitAbbreviationDirectoryService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class UnitAbbreviationDirectoryController {

	@Autowired
	UnitAbbreviationDirectoryService unitAbbreviationDirectoryService;
	
	@Autowired
	UnitAbbreviationDirectoryDAO unitAbbreviationDirectoryDAO;
	
	/*@RequestMapping("/unitAbbreviationDirectory")
	public String getDepartmentDetailPage() {
		return "unitAbbreviationDirectory";
	}
	
	@ModelAttribute(value = "/unitAbbreviationDirectoryBean")
	public UnitAbbreviationDirectory unitAbbreviationDirectory() {
		return new UnitAbbreviationDirectory();
	}*/
	
	@RequestMapping(value = "/addUpdateUnitAbbreviation", method = RequestMethod.POST)
	public @ResponseBody String addUpdateUnitAbbreviation(
			@ModelAttribute("unitAbbreviationDirectoryBean") UnitAbbreviationDirectory unitAbbreviationBean,
			HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			String result = unitAbbreviationDirectoryService
					.addUpdateUnitAbbreviationService(unitAbbreviationBean,
							userDetail, domainDetail);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	@RequestMapping(value = "/getUnitAbbreviationForDatatables", method = RequestMethod.POST)
	@ResponseBody
	public String getUnitAbbreviationForDatatables(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			ArrayList<UnitAbbreviationDirectory> allAbbreviationsList = unitAbbreviationDirectoryDAO
					.getAllAbbreviationsDAO(domainDetail.getDomainId());
			map.put("ajaxResult", "success");
			map.put("allAbbreviationsList", allAbbreviationsList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}
	
	@RequestMapping(value = "/getUnitAbbreviation", method = RequestMethod.POST)
	@ResponseBody
	public String getUnitAbbreviation(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			HashMap<Object, Object> allAbbreviationsList = unitAbbreviationDirectoryService
					.getAllAbbreviations(domainDetail.getDomainId());
			map.put("ajaxResult", "success");
			map.put("allAbbreviationsList", allAbbreviationsList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}
	
	@RequestMapping(value = "/deleteUnitAbbreviation", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUnitAbbreviation(HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		Long abbreviationId = 0L;
		try {
			if (request.getParameter("abbreviationIdToDel") != null
					&& request.getParameter("abbreviationIdToDel") != "") {
				abbreviationId = Long.parseLong(request.getParameter("abbreviationIdToDel")
						.trim());
			}
			String result = unitAbbreviationDirectoryDAO.deleteUnitAbbreviation(abbreviationId);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
}
