package com.elecnor.ecosystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DocumentCenter;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.service.DocumentCenterService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;
import com.file.handler.bean.FileUploadBean;
import com.file.handler.service.FileUploadService;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class DocumentCenterController {

	@Autowired
	DocumentCenterService documentCenterService;
	
	@Autowired
	FileUploadService fileUploadService;
	
	/*@RequestMapping("/documentCenter")
	public String getApplicationDetailPage() {
		return "documentCenter";
	}*/

	/**
	 * Method for save document details
	 * @param documentCenterBean
	 * @param fileUploaded
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveDocumentDetails", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<Object, Object> saveDocumentDetails(
			@ModelAttribute("documentCenter") DocumentCenter documentCenterBean,
			@RequestParam(value = "fileUploaded") MultipartFile fileUploaded, HttpSession session) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		/*List<MultipartFile> multiList = new ArrayList<MultipartFile>();
		multiList.add(fileUploaded);*/
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			documentCenterBean.setSubmittedBy(userDetails);
			documentCenterBean.setSubmittedDate(new Date());
			documentCenterBean.setDomainDetail(userDetails.getDomainDetail());
			
			String result = documentCenterService.saveDocumentDetails(documentCenterBean, fileUploaded);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return resultMap;
	}

	/**
	 * Method for fetch all user list
	 * @return
	 */
	@RequestMapping(value = "/getDocumentsList", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<Object, Object> getDocumentsList(HttpSession session) {

		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			List<DocumentCenter> allDocumentDetailsList = documentCenterService.getDocumentsList(userDetails.getDomainDetail().getDomainId());
			
			resultMap.put("allDocumentDetailsList", allDocumentDetailsList);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return resultMap;
	}

	/**
	 * Method for delete
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteDocumentById", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<Object, Object> deleteDocumentById(@RequestParam("documentId") Long documentId,
			@RequestParam("fileId") int fileId, HttpServletRequest request) {
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			String result = documentCenterService.deleteDocumentById(documentId, fileId);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return resultMap;
	}

	/**
	 * Method for download document
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/downloadDocumentById", method = RequestMethod.GET)
	public void downloadDocumentById(@RequestParam("fileId") int fileId, HttpServletRequest request,
			HttpServletResponse response) {
		/*Utility util = new Utility();
		DocumentCenterHelper documentCenterHelper = new DocumentCenterHelper();*/
		//HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			
			FileUploadBean fileInfo = fileUploadService.getFileInfo(Integer.valueOf(fileId));
			Map<String, Object> resultMap = fileUploadService.downloadFile(Integer.valueOf(fileId));
			File file = null;

			if (resultMap.containsKey(ConstantUtil.SUCCESS)) {
				file = (File) resultMap.get(ConstantUtil.SUCCESS);
				InputStream inputStream = new FileInputStream(file);
				if (fileInfo.getFileType().trim().equalsIgnoreCase(".txt")) {
					response.setContentType("text/plain");
				} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".doc")) {
					response.setContentType("application/msword");
				} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".xls") || fileInfo.getFileType().trim().equalsIgnoreCase(".xlsx")) {
					response.setContentType("application/vnd.ms-excel");
				} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".pdf")) {
					response.setContentType("application/pdf");
				} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".ppt")) {
					response.setContentType("application/ppt");
				} else {
					response.setContentType("text/plain");
				}
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileName() + "\"");

				FileCopyUtils.copy(inputStream, response.getOutputStream());
				inputStream.close();
				response.flushBuffer();

			}
			
		} catch (Exception e) {
			//util.handleException(resultMap, e);
			e.printStackTrace();
		}
	}

}
