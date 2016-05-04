package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DocumentCenter;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DocumentCenterDao;
import com.elecnor.ecosystem.service.DocumentCenterService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.file.handler.service.FileUploadService;

@Service
public class DocumentCenterServiceImpl implements DocumentCenterService {

	@Autowired
	DocumentCenterDao documentCenterDao;
	
	@Autowired
	FileUploadService fileUploadService;

	// method for saving document details
	@Override
	public String saveDocumentDetails(DocumentCenter documentCenterBean, MultipartFile fileUploaded ) throws Exception {
		String result = null;
		int fileId = 0;
		
		try {
			documentCenterBean.setStatus(ConstantUtil.ACTIVE);
			
			if (!fileUploaded.isEmpty()) {
				String docType = fileUploaded.getOriginalFilename().trim().split("\\.")[1];
				documentCenterBean.setDocumentType(docType);
				/*documentCenterBean.setUploadedDocumentContent(fileUploaded.getBytes());*/
				
				fileId = fileUploadService.uploadFile(fileUploaded.getBytes(), fileUploaded.getOriginalFilename(), docType);
			}
			/*if (documentCenterBean.getDocumentCenterId() == null) {
				documentCenterBean.setSubmittedDate(new Date());
				documentCenterBean.setSubmittedBy(userDetailsBean);
			}*/
			if(fileId > 0) {
				documentCenterBean.setFileId(fileId);
				result = documentCenterDao.setDocumentDetailsSave(documentCenterBean);
			} else {
				result = "failure";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
			throw e;
		}
		return result;
	}

	@Override
	public List<DocumentCenter> getDocumentsList(Long domainId) throws Exception {
		// TODO Auto-generated method stub
		return documentCenterDao.getDocumentsList(domainId);
	}

	@Override
	public String deleteDocumentById(Long documentId, int fileId) throws Exception {
		// TODO Auto-generated method stub
		String result = null;
		// Delete document from File System. 
		Map<String, Object> resultMap = fileUploadService.deleteFile(fileId);
		
		if(resultMap.containsKey(ConstantUtil.SUCCESS))
			documentCenterDao.deleteDocumentById(documentId);
		else
			result = ConstantUtil.FAILURE;
		
		return result;
	}
	// method for downloading document details
	@Override
	public String setDocumetDelete(long descId) throws Exception {
		String result = null;
		try {

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
