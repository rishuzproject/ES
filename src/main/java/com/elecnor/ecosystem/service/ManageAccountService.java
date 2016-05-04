package com.elecnor.ecosystem.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface ManageAccountService {

	public String setEmailSendFromManageAccount(HttpServletRequest request, MultipartFile fileUpload, HttpSession session) throws Exception;
}
