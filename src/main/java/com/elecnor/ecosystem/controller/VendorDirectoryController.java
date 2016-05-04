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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.VendorDirectory;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.dao.VendorDirectoryDAO;
import com.elecnor.ecosystem.helper.VendorDirectoryHelper;
import com.elecnor.ecosystem.service.VendorDirectoryService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class VendorDirectoryController {

	@Autowired
	VendorDirectoryService vendorDirectoryService;

	@Autowired
	VendorDirectoryDAO vendorDirectoryDAO;
	@Autowired
	AddressDetailsDAO addressDetailsDAO;

	/*@RequestMapping("/vendorDirectory")
	public String getStaticHome() {
		return "vendorDirectory";
	}

	@ModelAttribute(value = "/venderForm")
	public VendorDirectory registerVendor() {
		return new VendorDirectory();
	}*/

	/**
	 * Method for add
	 * 
	 * @param vendorDirectory
	 * @param vendorTypeAction
	 * @param vendorId
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addVendorAction", method = RequestMethod.POST)
	public @ResponseBody String addVendor(
			@ModelAttribute("vendorDirectory") VendorDirectory vendorDirectory,
			@RequestParam("vendorTypeAction") String vendorTypeAction,
			@RequestParam(value = "status") String status,
			@RequestParam(value = "vendorId", defaultValue = "-1") long vendorId,
			HttpSession session, HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			// adding this to save another address in AddressDetails
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			vendorDirectory.setVendorId(vendorId);
			vendorDirectory.setStatus(status);
			String result = vendorDirectoryService.addVendor(vendorDirectory,
					userDetail, domainDetail);
			if(result.matches(".*\\d.*")){ //checks whether the string contains a number 
			String temp=null;
			resultMap = util.responseBuilder(temp);
			resultMap.put("vendorId",result);
			}
			else{
			resultMap = util.responseBuilder(result);
			}
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for delete
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteVendorAction", method = RequestMethod.POST)
	@ResponseBody
	public String deleteVendor(HttpServletRequest request, HttpSession session) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Integer vendorId = 0;
		VendorDirectoryHelper vendorDirectoryHelper = new VendorDirectoryHelper();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session
					.getAttribute("selectedUser");
			vendorId = vendorDirectoryHelper.setVendorIdToDelete(request);
			String result = vendorDirectoryDAO.deleteVendor(vendorId);
			Long moduleId=Long.parseLong(vendorId.toString());
			result = addressDetailsDAO.deleteAddress(moduleId,
					userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for get Vendor List
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getVendorAction", method = RequestMethod.POST)
	@ResponseBody
	public String getVendor(HttpSession session) {

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session
					.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			ArrayList<VendorDirectory> allVendorList = vendorDirectoryDAO
					.getAllVendors(domainDetail.getDomainId());
			map.put("ajaxResult", "success");
			map.put("allVendorList", allVendorList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	/**
	 * Method for save data using template
	 * 
	 * @param confirmUploadId
	 * @param fileUploaded
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
//	@RequestMapping(value = "/templateController", method = RequestMethod.POST)
//	@ResponseBody
//	public String saveStandardTemplateDetails(
//			@RequestParam(value = "confirmUploadId", defaultValue = "-1") int confirmUploadId,
//			@RequestParam(value = "templateFile") MultipartFile fileUploaded,
//			HttpServletRequest request, HttpSession session) throws IOException {
//		Utility util = new Utility();
//		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		try {
//			resultMap = vendorDirectoryService.uploadFile(fileUploaded,
//					session, confirmUploadId);
//		} catch (Exception e) {
//			resultMap.put("ajaxResult", "failure");
//			resultMap.put("reason", e.getMessage());
//			util.sendExceptionEmail(e);
//		}
//		return util.getJsonResultWithoutExposeString(resultMap);
//	}
	
	
}
