package com.elecnor.ecosystem.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.service.BulkUploadService;
import com.elecnor.ecosystem.util.Utility;

/**
 * Method for save data using template
 * 
 * @param confirmProjectUploadId
 * @param fileUploaded
 * @param request
 * @param session
 * @return
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws IllegalAccessException
 * @throws InstantiationException
 */

@Controller
public class BulkUploadController {
	@Autowired
	BulkUploadService bulkUploadService;
	Utility util;

	@RequestMapping(value = "/templateController", method = RequestMethod.POST)
	@ResponseBody
	public String templateController(@RequestParam(value = "apiName", defaultValue = "") String className,
			@RequestParam(value = "templateFile") MultipartFile fileUploaded, HttpServletRequest request,
			HttpSession session) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();

		try {
			resultMap = bulkUploadService.uploadFile(fileUploaded, session, className);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultWithoutExposeString(resultMap);
	}

	@RequestMapping(value = "/downloadErrorFile", method = RequestMethod.GET)
	public void downloadErrorFile(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			response.setContentType("text/csv");
			String errorList = request.getParameter("errorString");
			errorList = errorList.replaceAll("\\{", "\n").replaceAll("\\}", "").replaceAll("\\[", "")
					.replaceAll("\\]", "").replaceAll("\"colNumber\":0", "");
			System.out.println(errorList);
			response.setContentLength(errorList.getBytes().length);
			response.setHeader("Content-Disposition", "attachment; filename=" + "errorRecords.csv" + "");
			FileCopyUtils.copy(errorList.getBytes(), response.getOutputStream());
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
	}
}
