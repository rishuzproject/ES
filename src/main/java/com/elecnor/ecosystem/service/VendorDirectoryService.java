package com.elecnor.ecosystem.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.VendorDirectory;

public interface VendorDirectoryService {

	public String addVendor(VendorDirectory vendorDirectory,UserDetail userDetail,DomainDetail domainDetail) throws Exception;

	public HashMap<String, Object> uploadFile(MultipartFile fileUploaded, HttpSession session, int confirmUploadId) throws Exception;

	//celValidatePojoUtil>> uploadedExcelData(List<MultipartFile> fileUploaded, int confirmValue);
	
	public List<VendorDirectory> getVendorListByDomainId(Long domainId) throws Exception;
	
}
