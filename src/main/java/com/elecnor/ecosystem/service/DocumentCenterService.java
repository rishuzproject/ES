package com.elecnor.ecosystem.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DocumentCenter;

public interface DocumentCenterService {

	public String saveDocumentDetails(DocumentCenter documentCenterBean, MultipartFile fileUploaded) throws Exception;
	public List<DocumentCenter> getDocumentsList(Long domainId) throws Exception;
	public String deleteDocumentById(Long documentId, int fileId) throws Exception;
	public String setDocumetDelete(long descId) throws Exception;
}
