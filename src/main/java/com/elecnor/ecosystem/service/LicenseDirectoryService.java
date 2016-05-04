package com.elecnor.ecosystem.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.LicenseDirectory;

public interface LicenseDirectoryService {

	public String addLicenseRecord(LicenseDirectory licenseDirectory, int states)throws Exception;

	public HashMap<String, Object> uploadStateLicFile(
			MultipartFile fileUploaded, HttpSession session,
			int confirmStateLicUploadId) throws Exception;

	public HashMap<String, Object> uploadLocalLicFile(
			MultipartFile fileUploaded, HttpSession session,
			int confirmLocalLicUploadId) throws Exception;

	public String addUpdateLocalLicenseService(LicenseDirectory lLicenseDirectory, DomainDetail domainDetail,
			String action) throws Exception;
}
