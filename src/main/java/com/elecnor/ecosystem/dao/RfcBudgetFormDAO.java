package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;

public interface RfcBudgetFormDAO {

		// Get and add method for Budget Material Form
		public List<BudgetFormMat> getMaterialBudgetData(String rfcLogId) throws Exception;
		public void addMaterialFormData(BudgetFormMat materialBudgetBean) throws Exception;
		
		// Get and add method for Budget Sub-Contractor Form
		public List<BudgetFormSubContractor> getSubContractorBudgetData(String rfcLogId) throws Exception;
		public void addSubContractorFormData(BudgetFormSubContractor subContractorBudgetBean) throws Exception;
		
		// Get and add method for Budget Labor Form
		public List<BudgetFormLabor> getLaborBudgetData(String rfcLogId) throws Exception;
		public void addLaborFormData(BudgetFormLabor laborBudgetBean) throws Exception;
		
		// Get and add method for Budget Equipment Form
		public List<BudgetFormEquip> getEquipBudgetData(String rfcLogId) throws Exception;
		public void addEquipFormData(BudgetFormEquip equipBudgetBean) throws Exception;
		
		// Get and add method for Budget Direct Job Form
		public List<BudgetFormDje> getDirectJobBudgetData(String rfcLogId) throws Exception;
		public void addDJEFormData(BudgetFormDje djeBudgetBean) throws Exception;
		
		// Get and add method for Budget Indirect Form
		public List<BudgetFormIndirect> getIndirectBudgetData(String rfcLogId) throws Exception;	
		public void addIndirectFormData(BudgetFormIndirect indirectBudgetBean) throws Exception;
		
		// Get and add method for Budget Project Admin Form
		public List<BudgetFormProjectAdmin> getProjAdminBudgetData(String rfcLogId) throws Exception;	
		public void addProjectAdminFormData(BudgetFormProjectAdmin projectAdminBudgetBean) throws Exception;
}
