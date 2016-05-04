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
public class StateLicenseController {
	@Autowired
	LicenseDirectoryDAO sLicenseDirectoryDAO;
	@Autowired
	LicenseDirectoryService sLicenseDirectoryService;

	/*@RequestMapping("/stateLicense")
	public String getStateLicensePage() {
		return "stateLicense";
	}

	@ModelAttribute(value = "stateLicenseForm")
	public LicenseDirectory getsLicense() {
		return new LicenseDirectory();
	}*/

	/**
	 * Method for get License Details
	 * @param request
	 * @param session
	 * @param sLicenseDirectory
	 * @return
	 */
	@RequestMapping(value = "/getsLicenseDetails", method = RequestMethod.POST)
	public @ResponseBody String getsLicenseDetails(
			HttpServletRequest request,
			HttpSession session,
			@ModelAttribute("stateLicenseForm") LicenseDirectory sLicenseDirectory) {
		session = request.getSession(false);
		Utility util = new Utility();
		UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
		DomainDetail domainDetail = userDetails.getDomainDetail();
		HashMap<Object, Object> resultantLicenses = new HashMap<Object, Object>();
		try {
			ArrayList<LicenseDirectory> sLicenseDetails = sLicenseDirectoryDAO.getLicenseDetails(domainDetail.getDomainId());
			resultantLicenses.put("sLicenseDetails", sLicenseDetails);
		} catch (Exception e) {
			resultantLicenses.put("ajaxResult", "failure");
			resultantLicenses.put("sLicenseDetails", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultForDate(resultantLicenses);
	}

	/**
	 * Method for save
	 * @param session
	 * @param request
	 * @param sLicenseDirectory
	 * @param action
	 * @param states
	 * @return
	 */
	@RequestMapping(value = "/saveLicenseDetails", method = RequestMethod.POST)
	public @ResponseBody String handlingSavingOperations(
			HttpSession session,
			HttpServletRequest request,
			@ModelAttribute("stateLicenseForm") LicenseDirectory sLicenseDirectory,
			@RequestParam("licenseAction") String action,
			@RequestParam("usStates") int states) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
		DomainDetail domainDetail = userDetails.getDomainDetail();
		sLicenseDirectory.setDomainDetail(domainDetail);
		String result = null;
		try {
			if (action.equalsIgnoreCase("save") || action.equalsIgnoreCase("saveandcontinue")) {
				result = sLicenseDirectoryService.addLicenseRecord(sLicenseDirectory, states);
			} else if (action.equalsIgnoreCase("Update")) {
				sLicenseDirectory.setType("STATE");
				sLicenseDirectory.setState(states);
				result = sLicenseDirectoryDAO.updateLicenseRecord(sLicenseDirectory);
			}
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
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
	@RequestMapping(value = "/deleteSLicense", method = RequestMethod.POST)
	public @ResponseBody String sdeleteLicense(HttpSession session,
			HttpServletRequest request, @RequestParam("licenseId") int licenseId) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			String result = sLicenseDirectoryDAO.deleteLicense(licenseId);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);

	}

	/**
	 * Method for save using template
	 * @param confirmStateLicUploadId
	 * @param fileUploaded
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/stateLicenseTemplateController", method = RequestMethod.POST)
	@ResponseBody
	public String saveStateLicTemplateDetails(
			@RequestParam(value = "confirmStateLicUploadId", defaultValue = "-1") int confirmStateLicUploadId,
			@RequestParam(value = "stateLicTemplateFile") MultipartFile fileUploaded,
			HttpServletRequest request, HttpSession session) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		try {
			resultMap = sLicenseDirectoryService.uploadStateLicFile(fileUploaded, session, confirmStateLicUploadId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(resultMap);

	}
}
