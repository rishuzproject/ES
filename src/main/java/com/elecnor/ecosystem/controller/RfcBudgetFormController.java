package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.helper.RfcBudgetFormHelper;
import com.elecnor.ecosystem.service.RfcBudgetFormService;
import com.elecnor.ecosystem.util.Utility;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * 
 * @author Harsh Verma
 *
 */
@Controller
public class RfcBudgetFormController {

	@Autowired
	RfcBudgetFormService rfcBudgetFormService;
	
	/**
	 * Method for get all Material Cost Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/GetMatBudgetInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getMaterialBudgetData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		RfcBudgetFormHelper rfcBudgetFormHelper = new RfcBudgetFormHelper();
		String rfcLogId = request.getParameter("rfcLogId");
		try {
			List<BudgetFormMat> matBudget = rfcBudgetFormService.getMaterialBudgetData(rfcLogId);
			resultMap = rfcBudgetFormHelper.checkListAndSetResultMap(matBudget, "MaterialBudget");
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for get all Sub-Contractor Cost Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/GetSubContractorBudgetInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getSubContractorBudgetData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		RfcBudgetFormHelper rfcBudgetFormHelper = new RfcBudgetFormHelper();
		String rfcLogId = request.getParameter("rfcLogId");
		try {
			List<BudgetFormSubContractor> subContrctudget = rfcBudgetFormService.getSubContractorBudgetData(rfcLogId);
			resultMap = rfcBudgetFormHelper.checkListAndSetResultMap(subContrctudget, "SubContractorBudget");
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for get all Labor Cost Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/GetLaborBudgetInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getLaborBudgetData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		RfcBudgetFormHelper rfcBudgetFormHelper = new RfcBudgetFormHelper();
		String rfcLogId = request.getParameter("rfcLogId");
		try {
			List<BudgetFormLabor> laborbudget = rfcBudgetFormService.getLaborBudgetData(rfcLogId);
			resultMap = rfcBudgetFormHelper.checkListAndSetResultMap(laborbudget, "LaborBudget");
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for get all Equipment Cost Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/GetEquipBudgetInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getEquipBudgetData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		RfcBudgetFormHelper rfcBudgetFormHelper = new RfcBudgetFormHelper();
		String rfcLogId = request.getParameter("rfcLogId");
		try {
			List<BudgetFormEquip> equipBudget = rfcBudgetFormService.getEquipBudgetData(rfcLogId);
			resultMap = rfcBudgetFormHelper.checkListAndSetResultMap(equipBudget, "EquipBudget");
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for get all DirectJob Cost Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/GetDirectJobBudgetInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getDirectJobBudgetData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String rfcLogId = request.getParameter("rfcLogId");
		RfcBudgetFormHelper rfcBudgetFormHelper = new RfcBudgetFormHelper();
		try {
			List<BudgetFormDje> dirJobBudget = rfcBudgetFormService.getDirectJobBudgetData(rfcLogId);
			resultMap = rfcBudgetFormHelper.checkListAndSetResultMap(dirJobBudget, "DirectJobBudget");
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for get all Indirect Cost Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/GetIndirectBudgetInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getIndirectBudgetData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		RfcBudgetFormHelper rfcBudgetFormHelper = new RfcBudgetFormHelper();
		String rfcLogId = request.getParameter("rfcLogId");
		try {
			List<BudgetFormIndirect> indirectbudget = rfcBudgetFormService.getIndirectBudgetData(rfcLogId);
			resultMap = rfcBudgetFormHelper.checkListAndSetResultMap(indirectbudget, "IndirectBudget");
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * Method for get all Project-Admin Cost Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/GetProjAdminBudgetInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getProjAdminBudgetData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Utility util = new Utility();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String rfcLogId = request.getParameter("rfcLogId");
		RfcBudgetFormHelper rfcBudgetFormHelper = new RfcBudgetFormHelper();
		try {
			List<BudgetFormProjectAdmin> projAdminBudget = rfcBudgetFormService.getProjAdminBudgetData(rfcLogId);
			resultMap = rfcBudgetFormHelper.checkListAndSetResultMap(projAdminBudget, "ProjAdminBudget");
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * Method for add update Material Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @param tableData
	 */
	@RequestMapping(value = "/AddUpdateMaterialBudgetForm", method = RequestMethod.POST)
	public void addUpdateMaterialFormData(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestBody String tableData){
		Utility util = new Utility();
		 JsonArray jsonArray = new JsonParser().parse(tableData).getAsJsonArray();
		 try {
			rfcBudgetFormService.addUpdateMatBudgetForm(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}
	
	/**
	 * Method for add update Sub-Contractor Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @param tableData
	 */
	@RequestMapping(value = "/AddUpdateSubContrctBudgetForm", method = RequestMethod.POST)
	public void addUpdateSubContrctFormData(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestBody String tableData){
		 JsonArray jsonArray = new JsonParser().parse(tableData).getAsJsonArray();
		 Utility util = new Utility();
		 try {
			rfcBudgetFormService.addUpdateSubContractorBudgetForm(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}
	
	/**
	 * Method for add update Labor Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @param tableData
	 */
	@RequestMapping(value = "/AddUpdateLaborBudgetForm", method = RequestMethod.POST)
	public void addUpdateLaborFormData(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestBody String tableData){
		 JsonArray jsonArray = new JsonParser().parse(tableData).getAsJsonArray();
		 Utility util = new Utility();
		 try {
			rfcBudgetFormService.addUpdateLaborBudgetForm(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}
	
	/**
	 * Method for add update Equipment Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @param tableData
	 */
	@RequestMapping(value = "/AddUpdateEquipBudgetForm", method = RequestMethod.POST)
	public void addUpdateEquipFormData(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestBody String tableData){
		 JsonArray jsonArray = new JsonParser().parse(tableData).getAsJsonArray();
		 Utility util = new Utility();
		 try {
			rfcBudgetFormService.addUpdateEquipBudgetForm(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}
	
	/**
	 * Method for add update Direct Job Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @param tableData
	 */
	@RequestMapping(value = "/AddUpdateDirJobBudgetForm", method = RequestMethod.POST)
	public void addUpdateDirJobFormData(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestBody String tableData){
		 JsonArray jsonArray = new JsonParser().parse(tableData).getAsJsonArray();
		 Utility util = new Utility();
		 try {
			rfcBudgetFormService.addUpdateDirectJobBudgetForm(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}
	
	/**
	 * Method for add update Indirect Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @param tableData
	 */
	@RequestMapping(value = "/AddUpdateIndirectBudgetForm", method = RequestMethod.POST)
	public void addUpdateIndirectFormData(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestBody String tableData){
		 JsonArray jsonArray = new JsonParser().parse(tableData).getAsJsonArray();
		 Utility util = new Utility();
		 try {
			rfcBudgetFormService.addUpdateIndirectBudgetForm(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}
	
	/**
	 * Method for add update Project-Admin Budget Form Data
	 * @param request
	 * @param response
	 * @param session
	 * @param tableData
	 */
	@RequestMapping(value = "/AddUpdateProjAdminBudgetForm", method = RequestMethod.POST)
	public void addUpdateProjAdminFormData(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, @RequestBody String tableData){
		 JsonArray jsonArray = new JsonParser().parse(tableData).getAsJsonArray();
		 Utility util = new Utility();
		 try {
			rfcBudgetFormService.addUpdateProjectAdminBudgetForm(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
	}
	
	@RequestMapping(value="/getRFCValuesForPD",method=RequestMethod.GET)
	public @ResponseBody String getRFCValuesForPD(){  //for temporary use -meghana
		 Utility util = new Utility();
		 HashMap<Object,Object> resultMap=new HashMap<Object,Object>();
		ArrayList<RfcLog> rfclogsList=new ArrayList<RfcLog>();
		try{
		for(int i=0;i<5;i++){
			RfcLog rfcLog=new RfcLog();
			rfcLog.setsNo(i);
			rfcLog.setRfcDesc("RFC    "+(i+1));
			rfclogsList.add(rfcLog);
		}
		resultMap.put("ajaxResult", "success");
		resultMap.put("rfclogsList", rfclogsList);
		
		}
		catch(Exception e){
			resultMap.put("ajaxResult", "Failure");
			resultMap.put("reason", e.getMessage());
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}
	
}
