package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.AddressDetails;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.States;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.helper.AddressDetailsHelper;
import com.elecnor.ecosystem.service.AddressDetailsService;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class AddressDetailsController {

	@Autowired
	AddressDetailsDAO addressDetailsDAO;

	@Autowired
	AddressDetailsService addressDetailsService;

	@Autowired
	Utility util;

	@RequestMapping(value = "/getAddressDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAddressDetails(HttpSession session, @RequestParam(value = "moduleId") Long moduleId, @RequestParam(value = "moduleName") String moduleName) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			ArrayList<AddressDetails> allAddressDetails = addressDetailsDAO.getAddressDetails(moduleId,moduleName.toUpperCase());
			map.put("allAddressDetails", allAddressDetails);
		} catch (Exception e) {
			map = util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	@RequestMapping(value = "/saveAddressDetails", method = RequestMethod.POST)
	public @ResponseBody String saveAddressDetails(HttpSession session, HttpServletRequest request, @RequestBody String addressDetailsJson) {
		/**/
		AddressDetailsHelper addressDetailsHelper = new AddressDetailsHelper();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<AddressDetails> addressDetails = new ArrayList<AddressDetails>();
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			addressDetails = addressDetailsHelper.getAddressDetailFromJson(addressDetailsJson, userDetails, "save");
			DomainDetail domainDetailsBean = userDetails.getDomainDetail();
			String status = addressDetailsService.saveUpdateAddressDetails(addressDetails, userDetails, domainDetailsBean);
			if (null == status) {
				map.put("ajaxResult", "success");
			}

		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
			map = util.responseBuilder(e.getMessage());
		}
		return util.getJsonResult(map);
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAddress(HttpSession session, HttpServletRequest request, @RequestParam(value = "moduleId") String jobId) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			Integer moduleId = null;
			if (null != jobId && !jobId.trim().isEmpty()) {
				moduleId = Integer.valueOf(jobId);
				UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
				String result = addressDetailsDAO.setDeleteAddressByUser(moduleId, userDetails);
				resultMap = util.responseBuilder(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/getStates", method = RequestMethod.GET)
	@ResponseBody
	public String getStates() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<States> states = addressDetailsDAO.getStates();
			resultMap = util.responseBuilderCustom(states);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/getCities", method = RequestMethod.GET)
	@ResponseBody
	public String getCities(HttpSession session, HttpServletRequest request) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String stateCode = request.getParameter("stateCode");
		try {
			List<String> cities = addressDetailsDAO.getCities(stateCode);
			resultMap = util.responseBuilderCustom(cities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return util.getJsonResult(resultMap);
	}
}
