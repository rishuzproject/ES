package com.elecnor.ecosystem.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.UserDetail;

public interface BudgetCodeService {

	public String setSaveOrUpdateBudgetCode(BudgetCode budgetCodeDetail,UserDetail userDetails, DomainDetail domainDetailsBean) throws Exception;

	public HashMap<String, Object> uploadBudgetFile(MultipartFile fileUploaded,HttpSession session, int confirmBudgetUploadId) throws Exception;

	public void AddBudgetForms(RfcLog rfcLogBean, HttpSession session) throws Exception;

	public ArrayList<BudgetCode> getAllBudgetCodeDetails(Long domainId) throws Exception;

	public String getBelcoMatDescription(Integer belcoMatCode) throws Exception;
	
	
	
}
