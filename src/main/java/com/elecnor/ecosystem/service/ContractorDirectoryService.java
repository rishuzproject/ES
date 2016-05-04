package com.elecnor.ecosystem.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.ContractorDirectory;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;

public interface ContractorDirectoryService {

	public ContractorDirectory setSaveOrUpdateContractor(ContractorDirectory contractorDirectoryForm, UserDetail userDetail, DomainDetail domainDetail) throws Exception;;

	public HashMap<String, Object> uploadContractorFile(
			MultipartFile fileUploaded, HttpSession session,
			int confirmContractorUploadId) throws Exception;

	public String deleteContractor(String parameter, UserDetail userDetails) throws Exception;
	
	public List<ContractorDirectory> getContractorListByDomainId(Long domainId) throws Exception;
}
