package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.elecnor.ecosystem.bean.UserDetail;

public interface BudgetCodeDAO {

	public ArrayList<BudgetCode> getAllBudgetCodeDetails() throws Exception;

	public String setBudgetCodeDelete(Long activityId, UserDetail userDetails) throws Exception;

	public String setSaveBudgetCode(BudgetCode budgetCodeDetail) throws Exception;

	public ArrayList<BudgetCode> getAllBudgetCodeDetails(Long DomainDetail) throws Exception;

	public int checkActivityNumberisExist(long activityId, long domainId,Integer activityNumber) throws Exception;

	public String setUpdateBudgetCode(BudgetCode budgetCodeDetail) throws Exception;

	List<BudgetCode> getActivityList(String costType) throws Exception;
	
	public void updateLaborFormDataRfcIdAndActivityNumber(BudgetFormLabor laborBudgetBean) throws Exception;

	public void addMaterialFormData(BudgetFormMat materialBudgetBean) throws Exception;
	
	public void addSubContractorFormData(BudgetFormSubContractor subContractorBudgetBean) throws Exception;
	
	public void addEquipFormData(BudgetFormEquip equipBudgetBean) throws Exception;
	
	public void addDJEFormData(BudgetFormDje djeBudgetBean) throws Exception;
	
	public void addIndirectFormData(BudgetFormIndirect indirectBudgetBean) throws Exception;
	
	public String updateMaterialFormDataBasedOnRfcIdAndActivityNumber(BudgetFormMat materialBudgetBean) throws Exception;
	
	public void addProjectAdminFormData(BudgetFormProjectAdmin projectAdminBudgetBean) throws Exception;

	public void addLaborFormData(BudgetFormLabor laborBudgetBean) throws Exception;

	public String getBelcoMatDescription(Integer belcoMatCode) throws Exception;
	
}
