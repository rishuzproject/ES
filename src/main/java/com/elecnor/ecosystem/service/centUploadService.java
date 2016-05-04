package com.elecnor.ecosystem.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface centUploadService {
	
	public HashMap<String, Object> uploadProjectFile(MultipartFile fileUploaded, HttpSession session,
			int confirmUploadId,String className) throws Exception;

}
