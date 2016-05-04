package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.BudgetFormDje;
import com.elecnor.ecosystem.bean.BudgetFormEquip;
import com.elecnor.ecosystem.bean.BudgetFormIndirect;
import com.elecnor.ecosystem.bean.BudgetFormLabor;
import com.elecnor.ecosystem.bean.BudgetFormMat;
import com.elecnor.ecosystem.bean.BudgetFormProjectAdmin;
import com.elecnor.ecosystem.bean.BudgetFormSubContractor;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.BudgetCodeDAO;
import com.elecnor.ecosystem.helper.BudgetCodeHelper;
import com.elecnor.ecosystem.service.BudgetCodeService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;
import com.elecnor.ecosystem.util.Utility;

@Service
public class BudgetCodeServiceImpl implements BudgetCodeService {

	@Autowired
	BudgetCodeDAO budgetCodeDAO;

	@Override
	public String setSaveOrUpdateBudgetCode(BudgetCode budgetCodeDetail, UserDetail userDetails, DomainDetail domainDetailsBean) throws Exception {

		String result = null;
		long activityId = 0;
		try {
			if (budgetCodeDetail.getActivityId() != null) {
				activityId = budgetCodeDetail.getActivityId();
			}
			int isExist = budgetCodeDAO.checkActivityNumberisExist(activityId, domainDetailsBean.getDomainId(), budgetCodeDetail.getActivityNumber());
			if (isExist != 0) {
				result = "actNumExist";
			} else {
				budgetCodeDetail.setDomainDetail(domainDetailsBean);
				budgetCodeDetail.setStatus("ACTIVE");
				if (budgetCodeDetail.getActivityId() == null) {
					budgetCodeDetail.setSubmittedDate(new Date());
					budgetCodeDetail.setSubmittedBy(userDetails);
					result = budgetCodeDAO.setSaveBudgetCode(budgetCodeDetail);

				} else {
					budgetCodeDetail.setUpdatedDate(new Date());
					budgetCodeDetail.setUpdatedBy(userDetails);
					result = budgetCodeDAO.setUpdateBudgetCode(budgetCodeDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> uploadBudgetFile(MultipartFile fileUploaded, HttpSession session, int confirmUploadId) throws Exception {

		// confirmUploadId- Default value -1, it implies that Excel is being for
		// the first time, If 1 It means user accepts that even if problem
		// exists in present excel, continue with Save operation.

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<ExcelErrorDetails> errorList = new ArrayList<ExcelErrorDetails>();
		ArrayList<BudgetCode> budgetList = new ArrayList<BudgetCode>();

		Utility util = new Utility();
		UploadFileUtility upUltil = new UploadFileUtility();
		BudgetCodeHelper budgetCodeHelper = new BudgetCodeHelper();

		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;

		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");

		if (!fileUploaded.isEmpty()) {
			Workbook workBook = upUltil.readExcelFileFromMultipart(fileUploaded);
			if (workBook == null) {
				return upUltil.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
			}
			// Schema Validation - Checks Whether Row header name is same as we
			// specified.
			// Validate schema only when the document is uploaded and not when
			// user sends confirmation
			if (confirmUploadId != 1) {
				isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.BUDGET_CODE_SHEETNUM, ConstantUtil.BUDGET_CODE_HEADER_ROWNUM, ConstantUtil.BUDGET_CODE_EXCEL_FORMAT);
				if (!isValidSchema) {
					return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
				}
			}
			// Schema Validation Ends
			// Check Validation For Confirmation from user-- Pending

			// Data Validation Starts
			Sheet sheet = workBook.getSheetAt(ConstantUtil.BUDGET_CODE_SHEETNUM);
			for (i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				Map<String, Object> rowValidationResult = new HashMap<String, Object>();
				// if (confirmUploadId != 1) {
				rowValidationResult = budgetCodeHelper.validateRowDataAndFetchBean(row, userDetail);
				if (rowValidationResult.get("budgetCodeBean") == null) {
					errorList.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
					hasErrorOccured = true;
				} else {
					budgetList.add((BudgetCode) rowValidationResult.get("budgetCodeBean"));
				}
				// }
			}
			if ((confirmUploadId == 1) || (confirmUploadId != 1 && !hasErrorOccured)) {
				errorList = saveBudgetList(budgetList);
				if (errorList.isEmpty()) {
					errorList = null;
				}
				return util.responseBuilder(errorList);

			} else {

				return util.responseBuilder(errorList);
			}
		}
		return resultMap;
	}

	public ArrayList<ExcelErrorDetails> saveBudgetList(List<BudgetCode> budgetList) throws Exception {

		String budgetDAOResult = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int i = 1;
		ArrayList<ExcelErrorDetails> budgetDAOErrorList = new ArrayList<ExcelErrorDetails>();
		for (BudgetCode budgetCodeObj : budgetList) {
			budgetDAOResult = "";
			try {
				budgetDAOResult = budgetCodeDAO.setSaveBudgetCode(budgetCodeObj);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (budgetDAOResult != null) {
				budgetDAOErrorList.add(upUtil.getExcelErrorDetails(i, -1, "Error in saving to database"));
			}
			i++;
		}
		return budgetDAOErrorList;
	}

	@Override
	public void AddBudgetForms(RfcLog rfcLogBean, HttpSession session) throws Exception {
		UserDetail userDetail = (UserDetail) session.getAttribute("seletedUser");

		// START MATERIAL FORM //
		List<BudgetCode> activityList = budgetCodeDAO.getActivityList("MAT");
		BudgetFormMat matBudgetBean = new BudgetFormMat();
		for (BudgetCode activityBean : activityList) {
			matBudgetBean.setActivityDescription(activityBean.getActivityDescription());
			matBudgetBean.setActivityNumber(activityBean.getActivityNumber());
			matBudgetBean.setMaterial(0.0);
			matBudgetBean.setMiscMat(0.0);
			matBudgetBean.setQuotedItems(0.0);
			matBudgetBean.setSalesTax(0.0);
			matBudgetBean.setSubmittedByUserDetail(userDetail);
			matBudgetBean.setSubmittedDate(new java.util.Date());
			matBudgetBean.setRfcLog(rfcLogBean);
			matBudgetBean.setJobDetail(rfcLogBean.getJobDetail());
			budgetCodeDAO.addMaterialFormData(matBudgetBean);
		}
		// END MATERIAL FORM //

		// START SUB-CONTRACTOR FORM //
		activityList = budgetCodeDAO.getActivityList("SUB");
		BudgetFormSubContractor subBudgetBean = new BudgetFormSubContractor();
		for (BudgetCode activityBean : activityList) {
			subBudgetBean.setActivityDescription(activityBean.getActivityDescription());
			subBudgetBean.setActivityNumber(activityBean.getActivityNumber());
			subBudgetBean.setQuotedItems(0.0);
			subBudgetBean.setSubmittedByUserDetail(userDetail);
			subBudgetBean.setSubmittedDate(new java.util.Date());
			subBudgetBean.setRfcLog(rfcLogBean);
			subBudgetBean.setJobDetail(rfcLogBean.getJobDetail());
			budgetCodeDAO.addSubContractorFormData(subBudgetBean);
		}
		// END SUB-CONTRACTOR FORM //

		// START DJE FORM //
		activityList = budgetCodeDAO.getActivityList("DJE");
		BudgetFormDje djeBudgetBean = new BudgetFormDje();
		for (BudgetCode activityBean : activityList) {
			djeBudgetBean.setActivityDescription(activityBean.getActivityDescription());
			djeBudgetBean.setActivityNumber(activityBean.getActivityNumber());
			djeBudgetBean.setCost(0.0);
			djeBudgetBean.setSubmittedByUserDetail(userDetail);
			djeBudgetBean.setSubmittedDate(new java.util.Date());
			djeBudgetBean.setRfcLog(rfcLogBean);
			djeBudgetBean.setJobDetail(rfcLogBean.getJobDetail());
			budgetCodeDAO.addDJEFormData(djeBudgetBean);
		}
		// END DJE FORM //

		// START EQUIP FORM //
		activityList = budgetCodeDAO.getActivityList("EQUIP");
		BudgetFormEquip equipBudgetBean = new BudgetFormEquip();
		for (BudgetCode activityBean : activityList) {
			equipBudgetBean.setActivityDescription(activityBean.getActivityDescription());
			equipBudgetBean.setActivityNumber(activityBean.getActivityNumber());
			equipBudgetBean.setCost(0.0);
			equipBudgetBean.setSubmittedByUserDetail(userDetail);
			equipBudgetBean.setSubmittedDate(new java.util.Date());
			equipBudgetBean.setRfcLog(rfcLogBean);
			equipBudgetBean.setJobDetail(rfcLogBean.getJobDetail());
			budgetCodeDAO.addEquipFormData(equipBudgetBean);
		}
		// END EQUIP FORM //

		// START INDIRECT FORM //
		activityList = budgetCodeDAO.getActivityList("INDIRECT");
		BudgetFormIndirect indirectBudgetBean = new BudgetFormIndirect();
		for (BudgetCode activityBean : activityList) {
			indirectBudgetBean.setActivityDescription(activityBean.getActivityDescription());
			indirectBudgetBean.setActivityNumber(activityBean.getActivityNumber());
			indirectBudgetBean.setMaterial(0.0);
			indirectBudgetBean.setSubmittedByUserDetail(userDetail);
			indirectBudgetBean.setSubmittedDate(new java.util.Date());
			indirectBudgetBean.setRfcLog(rfcLogBean);
			indirectBudgetBean.setJobDetail(rfcLogBean.getJobDetail());
			budgetCodeDAO.addIndirectFormData(indirectBudgetBean);
		}
		// END INDIRECT FORM //

		// START PROJECT ADMIN FORM //
		activityList = budgetCodeDAO.getActivityList("PROJECT ADMIN");
		BudgetFormProjectAdmin projectAdminBudgetBean = new BudgetFormProjectAdmin();
		for (BudgetCode activityBean : activityList) {
			projectAdminBudgetBean.setActivityDescription(activityBean.getActivityDescription());
			projectAdminBudgetBean.setActivityNumber(activityBean.getActivityNumber());
			projectAdminBudgetBean.setHours(0.0);
			projectAdminBudgetBean.setRate(0.0);
			projectAdminBudgetBean.setSubmittedByUserDetail(userDetail);
			projectAdminBudgetBean.setSubmittedDate(new java.util.Date());
			projectAdminBudgetBean.setRfcLog(rfcLogBean);
			projectAdminBudgetBean.setJobDetail(rfcLogBean.getJobDetail());
			budgetCodeDAO.addProjectAdminFormData(projectAdminBudgetBean);
		}
		// END PROJECT ADMIN FORM //

		// START LABOR FORM //
		activityList = budgetCodeDAO.getActivityList("LABOR");
		BudgetFormLabor laborBudgetBean = new BudgetFormLabor();
		for (BudgetCode activityBean : activityList) {
			laborBudgetBean.setActivityDescription(activityBean.getActivityDescription());
			laborBudgetBean.setActivityNumber(activityBean.getActivityNumber());
			laborBudgetBean.setHours(0.0);
			laborBudgetBean.setBurden(0.0);
			laborBudgetBean.setFringes(0.0);
			laborBudgetBean.setLabWBdn(0.0);
			laborBudgetBean.setLabWoBdn(0.0);
			laborBudgetBean.setSubmittedByUserDetail(userDetail);
			laborBudgetBean.setSubmittedDate(new java.util.Date());
			laborBudgetBean.setRfcLog(rfcLogBean);
			laborBudgetBean.setJobDetail(rfcLogBean.getJobDetail());
			budgetCodeDAO.addLaborFormData(laborBudgetBean);
		}
		// END LABOR FORM //
	}

	@Override
	public ArrayList<BudgetCode> getAllBudgetCodeDetails(Long domainId) throws Exception {
		ArrayList<BudgetCode> allBudgetCodeList = null;
		try {
			if (domainId == null) {
				allBudgetCodeList = budgetCodeDAO.getAllBudgetCodeDetails();
			} else {
				allBudgetCodeList = budgetCodeDAO.getAllBudgetCodeDetails(domainId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allBudgetCodeList;
	}

	@Override
	public String getBelcoMatDescription(Integer belcoMatCode)
			throws Exception {

		try {
			return budgetCodeDAO.getBelcoMatDescription(belcoMatCode);
		} catch (Exception e) {
			throw e;
		}
	}
}
