package com.elecnor.ecosystem.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface BulkUploadService {
	public HashMap<String, Object> uploadFile(MultipartFile fileUploaded,
			HttpSession session, String className) throws Exception;

}
