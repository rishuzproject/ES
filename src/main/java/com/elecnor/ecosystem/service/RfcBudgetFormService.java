package com.elecnor.ecosystem.service;

import java.util.List;

import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.google.gson.JsonArray;

public interface RfcBudgetFormService {

	public List<BudgetFormMat> getMaterialBudgetData(String rfcLogId) throws Exception;
	
	public List<BudgetFormSubContractor> getSubContractorBudgetData(String rfcLogId) throws Exception;
	
	public List<BudgetFormLabor> getLaborBudgetData(String rfcLogId) throws Exception;
	
	public List<BudgetFormEquip> getEquipBudgetData(String rfcLogId) throws Exception;
	
	public List<BudgetFormDje> getDirectJobBudgetData(String rfcLogId) throws Exception;
	
	public List<BudgetFormIndirect> getIndirectBudgetData(String rfcLogId) throws Exception;
	
	public List<BudgetFormProjectAdmin> getProjAdminBudgetData(String rfcLogId) throws Exception;
	
	public void addUpdateMatBudgetForm(JsonArray jsonArray) throws Exception;
	
	public void addUpdateSubContractorBudgetForm(JsonArray jsonArray) throws Exception;
	
	public void addUpdateLaborBudgetForm(JsonArray jsonArray) throws Exception;
	
	public void addUpdateEquipBudgetForm(JsonArray jsonArray) throws Exception;
	
	public void addUpdateDirectJobBudgetForm(JsonArray jsonArray) throws Exception;
	
	public void addUpdateIndirectBudgetForm(JsonArray jsonArray) throws Exception;
	
	public void addUpdateProjectAdminBudgetForm(JsonArray jsonArray) throws Exception;
}
