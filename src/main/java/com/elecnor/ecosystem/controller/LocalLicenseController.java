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

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.LicenseDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.LicenseDirectoryDAO;
import com.elecnor.ecosystem.service.LicenseDirectoryService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class LocalLicenseController {
	@Autowired
	LicenseDirectoryDAO lLicenseDirectoryDAO;
	@Autowired
	LicenseDirectoryService lLicenseDirectoryService;
	
	/*@RequestMapping("/localLicense")
	public String getlocalLicensePage() {
		return "localLicense";
	}
	
	@ModelAttribute(value = "localLicenseForm")
	public LicenseDirectory getlLicense() {
		return new LicenseDirectory();
	}*/
    
	/**
	 * Method for get license details
	 * @param request
	 * @param session
	 * @param lLicenseDirectory
	 * @return
	 */
	@RequestMapping(value = "/getlLicenseDetails", method = RequestMethod.POST)
	public @ResponseBody String getlLicenseDetails(
			HttpServletRequest request,HttpSession session,
			@ModelAttribute("stateLicenseForm") LicenseDirectory lLicenseDirectory) {
		session = request.getSession(false);
		UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
		DomainDetail domainDetail = userDetails.getDomainDetail();
		HashMap<Object, Object> resultantLicenses = new HashMap<Object, Object>();
		Utility util=new Utility();
		try{
			ArrayList<LicenseDirectory> lLicenseDetails = lLicenseDirectoryDAO.getLicenseDetails(domainDetail.getDomainId());
			resultantLicenses.put("lLicenseDetails", lLicenseDetails);
		}
		catch(Exception e)
		{
			util.handleException(resultantLicenses, e);
		}
        return util.getJsonResultForDate(resultantLicenses);	

	}
	
	/**
	 * Method for save data
	 * @param session
	 * @param request
	 * @param lLicenseDirectory
	 * @param action
	 * @return
	 */
	@RequestMapping(value = "/savelLicenseDetails", method = RequestMethod.POST)
	public @ResponseBody String handlingSavingOperations(
			HttpSession session,HttpServletRequest request,
			@ModelAttribute("localLicenseForm") LicenseDirectory lLicenseDirectory,
			@RequestParam("licenseAction") String action
			
			) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			String result = null;
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			result = lLicenseDirectoryService.addUpdateLocalLicenseService(lLicenseDirectory, domainDetail, action);
			resultMap = util.responseBuilder(result);
		}
		catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for delete
	 * @param session
	 * @param request
	 * @param licenseId
	 * @return
	 */
	@RequestMapping(value="/deleteLLicense", method = RequestMethod.POST)
	public @ResponseBody String sdeleteLicense(HttpSession session,HttpServletRequest request,
			@RequestParam("licenseId")int licenseId){
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try{
			String  result = lLicenseDirectoryDAO.deleteLicense(licenseId);
			resultMap = util.responseBuilder(result);
		}
		catch (Exception e) {
			util.handleException(resultMap, e);
		}
			return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for save using template
	 * @param confirmLocalLicUploadId
	 * @param fileUploaded
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/localLicenseTemplateController", method = RequestMethod.POST)
	@ResponseBody
	public String saveLocalLicTemplateDetails(
			@RequestParam(value = "confirmLocalLicUploadId", defaultValue = "-1") int confirmLocalLicUploadId,
			@RequestParam(value = "localLicTemplateFile") MultipartFile fileUploaded,
			HttpServletRequest request, HttpSession session) throws IOException {

		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = lLicenseDirectoryService.uploadLocalLicFile(fileUploaded, session, confirmLocalLicUploadId);
		}catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
     return util.getJsonResultWithoutExposeString(resultMap);
 }
  
}
