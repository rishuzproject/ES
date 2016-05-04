package com.elecnor.ecosystem.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.UserDetail;

public interface CustomerDirectoryService {

	public CustomerDirectory saveOrUpdateCustomer(CustomerDirectory customerDirectoryForm, UserDetail userDetails) throws Exception;
	
	public HashMap<String, Object> uploadFile(MultipartFile fileUploaded, HttpSession session, int confirmValue) throws Exception;

	public String deleteCustomer(HttpServletRequest request, UserDetail userDetails) throws Exception;
	
	//public Map<String, List<ExcelValidatePojoUtil>> uploadedExcelData(List<MultipartFile> fileUploaded, int confirmValue);
	
	public List<CustomerDirectory> getCustomerListByDomainId(Long domainId) throws Exception;
}
