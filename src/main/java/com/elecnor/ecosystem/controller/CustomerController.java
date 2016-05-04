package com.elecnor.ecosystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.dao.CustomerDirectoryDAO;
import com.elecnor.ecosystem.service.CustomerDirectoryService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;
import com.file.handler.service.FileUploadService;

/**
 * 
 * @author Vaibhav
 * @Vaibhav Removing Navigation API to Navigation Controller.
 */

@Controller
public class CustomerController {

	@Autowired
	CustomerDirectoryService customerDetailService;
	@Autowired
	CustomerDirectoryDAO customerDetailsDAO;
	@Autowired
	AddressDetailsDAO addressDetailsDAO;
	@Autowired
	FileUploadService uploader;
	@Autowired 
	Utility util;

	/*
	 * @RequestMapping("/customer") public String getCustomerPage() { return
	 * "customerDirectory"; }
	 * 
	 * @ModelAttribute(value = "/customerForm") public CustomerDirectory
	 * registerUserProfile() { return new CustomerDirectory(); }
	 */

	/**
	 * Method for save or update customer data
	 * 
	 * @param customerListForm
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateCustomerAction", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateCustomer(@ModelAttribute("customerForm") CustomerDirectory customerListForm, HttpSession session, HttpServletRequest request) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			CustomerDirectory customer = customerDetailService.saveOrUpdateCustomer(customerListForm, userDetails);
			if (null != customer) {
				resultMap.put("ajaxResult", "success");
				resultMap.put("customerId", customer.getCompanyId());
			}
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for fetch all customers list
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllCustomersDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAllCustomersDetails(HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetailsBean = userDetails.getDomainDetail();
			ArrayList<CustomerDirectory> customersList = customerDetailsDAO.getAllCustomersList(domainDetailsBean.getDomainId());
			resultMap.put("customerDetails", customersList);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for delete
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customerDeleteAction", method = RequestMethod.POST)
	@ResponseBody
	public String setCustomerDelete(HttpServletRequest request, HttpSession session) {
		String result = "";
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			result = customerDetailService.deleteCustomer(request, userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for save data using template
	 * 
	 * @param fileUploaded
	 * @param confirmUploadId
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadCustomer", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveStandardTemplateDetails(@RequestParam(value = "templateFile") MultipartFile fileUploaded, @RequestParam(value = "confirmUploadId", defaultValue = "-1") int confirmUploadId,
			HttpServletRequest request, HttpSession session) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		fileList.add(fileUploaded);
		List<Integer> fileIdList = null;
		try {
			Map<String, Object> fileIdMap = uploader.uploadListOfFile(fileList);
			if(fileIdMap.containsKey(ConstantUtil.SUCCESS)){
				fileIdList = (List<Integer>)fileIdMap.get(ConstantUtil.SUCCESS);
			}
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return resultMap;
	}

}
