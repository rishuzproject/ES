package com.elecnor.ecosystem.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.ContractorDirectory;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.dao.ContractorDirectoryDAO;
import com.elecnor.ecosystem.service.ContractorDirectoryService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class ContractorController {
	
	@Autowired
	ContractorDirectoryService cntrctDirectoryService;
	@Autowired
	ContractorDirectoryDAO cntrctDirDAO;
	@Autowired
	AddressDetailsDAO addressDetailsDAO;
	
	/*@RequestMapping("/contractorDirectory")
	public String getStaticHome() {
		return "contractorDirectory";
	}
	
	@ModelAttribute(value = "/contracterForm")
	public ContractorDirectory registerUserProfile() {
		return new ContractorDirectory();
	}*/

	
	/**
	 * Method for fetch all contractor details
	 * @return
	 */
	@RequestMapping(value = "/getAllContractorDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAllCustomersDetails(HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail)session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			resultMap = new HashMap<Object, Object>();
			ArrayList<ContractorDirectory> contractorList = cntrctDirDAO.getAllContractorList(domainDetail.getDomainId());
			resultMap.put("contractorDetails", contractorList);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for save and update contractor data
	 * @param contrctDirForm
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateContractorAction", method = RequestMethod.POST)
	public @ResponseBody String saveContractorData(
			@ModelAttribute("contracterForm") ContractorDirectory contrctDirForm,HttpSession session,HttpServletRequest request){
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetail.getDomainDetail();
			//adding this to save another address in AddressDetails
			ContractorDirectory contractDirForm = cntrctDirectoryService.setSaveOrUpdateContractor(contrctDirForm, userDetail, domainDetail);
//			resultMap = util.responseBuilder(result);
			if (null != contractDirForm) {
				resultMap.put("ajaxResult", "success");
				resultMap.put("contractorId", contractDirForm.getContractorId());
			}
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for delete contractor details
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/contractorDeleteAction", method = RequestMethod.POST)
	@ResponseBody
	public String setContractorDelete(HttpServletRequest request,HttpSession session) {
		String result = "";
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			result = cntrctDirectoryService.deleteContractor(request.getParameter("compIdToDel"), userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for save data using template
	 * @param confirmContractorUploadId
	 * @param fileUploaded
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/contractorTemplateController", method = RequestMethod.POST)
	@ResponseBody
	public String saveContractorTemplateDetails(
			@RequestParam(value = "confirmContractorUploadId", defaultValue = "-1") int confirmContractorUploadId,
			@RequestParam(value = "contractorTemplateFile") MultipartFile fileUploaded,
			HttpServletRequest request, HttpSession session) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		try {
			resultMap = cntrctDirectoryService.uploadContractorFile(fileUploaded,session, confirmContractorUploadId);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(resultMap);
	}

	

}
