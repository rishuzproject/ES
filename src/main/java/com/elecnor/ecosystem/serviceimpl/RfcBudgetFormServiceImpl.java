package com.elecnor.ecosystem.serviceimpl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.dao.RfcBudgetFormDAO;
import com.elecnor.ecosystem.service.RfcBudgetFormService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author Harsh Verma
 *
 */
@Service
public class RfcBudgetFormServiceImpl implements RfcBudgetFormService {
	
	@Autowired
	RfcBudgetFormDAO rfcBudgetFormDAO;

	@Override
	public List<BudgetFormMat> getMaterialBudgetData(String rfcLogId) throws Exception {
		List<BudgetFormMat> allMaterialBudgetDetails = rfcBudgetFormDAO.getMaterialBudgetData(rfcLogId);
		return allMaterialBudgetDetails;
	}
	

	@Override
	public List<BudgetFormSubContractor> getSubContractorBudgetData(String rfcLogId) throws Exception {
		List<BudgetFormSubContractor> allSubContractorBudgetDetails = rfcBudgetFormDAO.getSubContractorBudgetData(rfcLogId);
		return allSubContractorBudgetDetails;
	}
	

	@Override
	public List<BudgetFormLabor> getLaborBudgetData(String rfcLogId) throws Exception {
		List<BudgetFormLabor> allLaborBudgetDetails = rfcBudgetFormDAO.getLaborBudgetData(rfcLogId);
		return allLaborBudgetDetails;
	}
	

	@Override
	public List<BudgetFormEquip> getEquipBudgetData(String rfcLogId) throws Exception {
		List<BudgetFormEquip> allEquipBudgetDetails = rfcBudgetFormDAO.getEquipBudgetData(rfcLogId);
		return allEquipBudgetDetails;
	}
	

	@Override
	public List<BudgetFormDje> getDirectJobBudgetData(String rfcLogId) throws Exception {
		List<BudgetFormDje> allDirectJobBudgetDetails = rfcBudgetFormDAO.getDirectJobBudgetData(rfcLogId);
		return allDirectJobBudgetDetails;
	}
	

	@Override
	public List<BudgetFormIndirect> getIndirectBudgetData(String rfcLogId) throws Exception {
		List<BudgetFormIndirect> allIndirectBudgetDetails = rfcBudgetFormDAO.getIndirectBudgetData(rfcLogId);
		return allIndirectBudgetDetails;
	}
	

	@Override
	public List<BudgetFormProjectAdmin> getProjAdminBudgetData(String rfcLogId) throws Exception {
		List<BudgetFormProjectAdmin> allProjAdminBudgetDetails = rfcBudgetFormDAO.getProjAdminBudgetData(rfcLogId);
		return allProjAdminBudgetDetails;
	}

	@Override
	public void addUpdateMatBudgetForm(JsonArray jsonArray)
			throws Exception {
		for(int i=0; i <= jsonArray.size() - 1; i++){
			BudgetFormMat budgetFormObj = new BudgetFormMat();
			RfcLog rfcObj = new RfcLog();
			JsonObject jObject = jsonArray.get(i).getAsJsonObject();
			String sNo = jObject.get("sno").getAsString();
			String miscTax = jObject.get("MiscTax").getAsString();
			String salesTax = jObject.get("SalesTax").getAsString();
			String description = jObject.get("Description").getAsString();
			String activity = jObject.get("Activity").getAsString();
			String material = jObject.get("Material").getAsString();
			String quoted = jObject.get("Quoted").getAsString();
			String rfcLogID = jObject.get("RfcLogID").getAsString();
			
			budgetFormObj.setRowId(BigInteger.valueOf(Long.valueOf(sNo)));
			budgetFormObj.setMiscMat(Double.valueOf(miscTax));
			budgetFormObj.setSalesTax(Double.valueOf(salesTax));
			budgetFormObj.setActivityDescription(description);
			budgetFormObj.setActivityNumber(Integer.valueOf(activity));
			budgetFormObj.setMaterial(Double.valueOf(material));
			budgetFormObj.setQuotedItems(Double.valueOf(quoted));
			budgetFormObj.setSubmittedDate(new Date());
			rfcObj.setsNo(Integer.valueOf(rfcLogID));
			budgetFormObj.setRfcLog(rfcObj);
			rfcBudgetFormDAO.addMaterialFormData(budgetFormObj);
		}
	}

	@Override
	public void addUpdateSubContractorBudgetForm(JsonArray jsonArray)
			throws Exception {
		for(int i=0; i <= jsonArray.size() - 1; i++){
			BudgetFormSubContractor budgetSubConObj = new BudgetFormSubContractor();
			RfcLog rfcObj = new RfcLog();
			JsonObject jObject = jsonArray.get(i).getAsJsonObject();
			String sNo = jObject.get("sno").getAsString();
			String description = jObject.get("Description").getAsString();
			String activity = jObject.get("Activity").getAsString();
			String quoted = jObject.get("Quoted").getAsString();
			String rfcLogID = jObject.get("RfcLogID").getAsString();
			
			budgetSubConObj.setRowId(BigInteger.valueOf(Long.valueOf(sNo)));
			budgetSubConObj.setActivityDescription(description);
			budgetSubConObj.setActivityNumber(Integer.valueOf(activity));
			budgetSubConObj.setQuotedItems(Double.valueOf(quoted));
			budgetSubConObj.setSubmittedDate(new Date());
			rfcObj.setsNo(Integer.valueOf(rfcLogID));
			budgetSubConObj.setRfcLog(rfcObj);
			rfcBudgetFormDAO.addSubContractorFormData(budgetSubConObj);
		}
	}

	@Override
	public void addUpdateLaborBudgetForm(JsonArray jsonArray)
			throws Exception {
		for(int i=0; i <= jsonArray.size() - 1; i++){
			BudgetFormLabor budgetLaborObj = new BudgetFormLabor();
			RfcLog rfcObj = new RfcLog();
			JsonObject jObject = jsonArray.get(i).getAsJsonObject();
			String sNo = jObject.get("sno").getAsString();
			String description = jObject.get("Description").getAsString();
			String activity = jObject.get("Activity").getAsString();
			String rfcLogID = jObject.get("RfcLogID").getAsString();
			String LAB_W_BDN = jObject.get("LAB_W_BDN").getAsString();
			String LAB_WO_BDN = jObject.get("LAB_WO_BDN").getAsString();
			String HOURS = jObject.get("HOURS").getAsString();
			String BURDEN = jObject.get("BURDEN").getAsString();
			String FRINGES = jObject.get("FRINGES").getAsString();
			String FOREMAN_RATE = jObject.get("FOREMAN_RATE").getAsString();
			
			
			budgetLaborObj.setRowId(BigInteger.valueOf(Long.valueOf(sNo)));
			budgetLaborObj.setActivityDescription(description);
			budgetLaborObj.setActivityNumber(Integer.valueOf(activity));
			budgetLaborObj.setLabWBdn(Double.valueOf(LAB_W_BDN));
			budgetLaborObj.setLabWoBdn(Double.valueOf(LAB_WO_BDN));
			budgetLaborObj.setHours(Double.valueOf(HOURS));
			budgetLaborObj.setFringes(Double.valueOf(FRINGES));
			budgetLaborObj.setForemanRate(Double.valueOf(FOREMAN_RATE));
			budgetLaborObj.setBurden(Double.valueOf(BURDEN));
			budgetLaborObj.setSubmittedDate(new Date());
			rfcObj.setsNo(Integer.valueOf(rfcLogID));
			budgetLaborObj.setRfcLog(rfcObj);
			rfcBudgetFormDAO.addLaborFormData(budgetLaborObj);
		}
	}

	@Override
	public void addUpdateEquipBudgetForm(JsonArray jsonArray)
			throws Exception {
		for(int i=0; i <= jsonArray.size() - 1; i++){
			BudgetFormEquip budgetEquipObj = new BudgetFormEquip();
			RfcLog rfcObj = new RfcLog();
			JsonObject jObject = jsonArray.get(i).getAsJsonObject();
			String sNo = jObject.get("sno").getAsString();
			String description = jObject.get("Description").getAsString();
			String activity = jObject.get("Activity").getAsString();
			String cost = jObject.get("Cost").getAsString();
			String rfcLogID = jObject.get("RfcLogID").getAsString();
			
			budgetEquipObj.setRowId(BigInteger.valueOf(Long.valueOf(sNo)));
			budgetEquipObj.setActivityDescription(description);
			budgetEquipObj.setActivityNumber(Integer.valueOf(activity));
			budgetEquipObj.setCost(Double.valueOf(cost));
			budgetEquipObj.setSubmittedDate(new Date());
			rfcObj.setsNo(Integer.valueOf(rfcLogID));
			budgetEquipObj.setRfcLog(rfcObj);
			rfcBudgetFormDAO.addEquipFormData(budgetEquipObj);
		}
	}

	@Override
	public void addUpdateDirectJobBudgetForm(JsonArray jsonArray)
			throws Exception {
		for(int i=0; i <= jsonArray.size() - 1; i++){
			BudgetFormDje budgetDirJobObj = new BudgetFormDje();
			RfcLog rfcObj = new RfcLog();
			JsonObject jObject = jsonArray.get(i).getAsJsonObject();
			String sNo = jObject.get("sno").getAsString();
			String description = jObject.get("Description").getAsString();
			String activity = jObject.get("Activity").getAsString();
			String cost = jObject.get("Cost").getAsString();
			String rfcLogID = jObject.get("RfcLogID").getAsString();
			
			budgetDirJobObj.setRowId(BigInteger.valueOf(Long.valueOf(sNo)));
			budgetDirJobObj.setActivityDescription(description);
			budgetDirJobObj.setActivityNumber(Integer.valueOf(activity));
			budgetDirJobObj.setCost(Double.valueOf(cost));
			budgetDirJobObj.setSubmittedDate(new Date());
			rfcObj.setsNo(Integer.valueOf(rfcLogID));
			budgetDirJobObj.setRfcLog(rfcObj);
			rfcBudgetFormDAO.addDJEFormData(budgetDirJobObj);
		}
	}

	@Override
	public void addUpdateIndirectBudgetForm(JsonArray jsonArray) throws Exception {
		for(int i=0; i <= jsonArray.size() - 1; i++){
			BudgetFormIndirect budgetInDirObj = new BudgetFormIndirect();
			RfcLog rfcObj = new RfcLog();
			JsonObject jObject = jsonArray.get(i).getAsJsonObject();
			String sNo = jObject.get("sno").getAsString();
			String description = jObject.get("Description").getAsString();
			String activity = jObject.get("Activity").getAsString();
			String material = jObject.get("Material").getAsString();
			String rfcLogID = jObject.get("RfcLogID").getAsString();
			
			budgetInDirObj.setRowId(BigInteger.valueOf(Long.valueOf(sNo)));
			budgetInDirObj.setActivityDescription(description);
			budgetInDirObj.setActivityNumber(Integer.valueOf(activity));
			budgetInDirObj.setMaterial(Double.valueOf(material));
			budgetInDirObj.setSubmittedDate(new Date());
			rfcObj.setsNo(Integer.valueOf(rfcLogID));
			budgetInDirObj.setRfcLog(rfcObj);
			rfcBudgetFormDAO.addIndirectFormData(budgetInDirObj);
		}
	}

	@Override
	public void addUpdateProjectAdminBudgetForm(JsonArray jsonArray)
			throws Exception {
		for(int i=0; i <= jsonArray.size() - 1; i++){
			BudgetFormProjectAdmin budgetProjAdminObj = new BudgetFormProjectAdmin();
			RfcLog rfcObj = new RfcLog();
			JsonObject jObject = jsonArray.get(i).getAsJsonObject();
			String sNo = jObject.get("sno").getAsString();
			String description = jObject.get("Description").getAsString();
			String activity = jObject.get("Activity").getAsString();
			String HOURS = jObject.get("HOURS").getAsString();
			String rfcLogID = jObject.get("RfcLogID").getAsString();
			String RATE = jObject.get("RATE").getAsString();
			String VALUE_INC_TAX = jObject.get("VALUE_INC_TAX").getAsString();
			
			budgetProjAdminObj.setRowId(BigInteger.valueOf(Long.valueOf(sNo)));
			budgetProjAdminObj.setActivityDescription(description);
			budgetProjAdminObj.setActivityNumber(Integer.valueOf(activity));
			budgetProjAdminObj.setHours(Double.valueOf(HOURS));
			budgetProjAdminObj.setRate(Double.valueOf(RATE));
			budgetProjAdminObj.setValueIncTax(Double.valueOf(VALUE_INC_TAX));
			budgetProjAdminObj.setSubmittedDate(new Date());
			rfcObj.setsNo(Integer.valueOf(rfcLogID));
			budgetProjAdminObj.setRfcLog(rfcObj);
			rfcBudgetFormDAO.addProjectAdminFormData(budgetProjAdminObj);
		}
	}
	
}
