package com.elecnor.ecosystem.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.elecnor.ecosystem.bean.BudgetCode;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

public class BudgetCodeHelper {

	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {

		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		boolean isRowHavingErrors = false;
		BudgetCode budgetCode = new BudgetCode();
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();
		double doubleValue;

		// validation for CostType
		colNum = ConstantUtil.BUDGET_CODE_DATA_COST_TYPE;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			budgetCode.setCostType(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for ActivityNo
		colNum = ConstantUtil.BUDGET_CODE_DATA_ACTIVITY_NO;
		try {
			doubleValue = (long) row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			budgetCode.setActivityNumber((int)(doubleValue));
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_INTEGER_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validity for ActivityDescription
		colNum = ConstantUtil.BUDGET_CODE_DATA_ACTIVITY_DESCRIPTION;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			budgetCode.setActivityDescription(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}
		
		budgetCode.setSubmittedBy(userDetail);
		budgetCode.setSubmittedDate(new Date());
		budgetCode.setDomainDetail(userDetail.getDomainDetail());
		budgetCode.setStatus("ACTIVE");
		
		ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil.getExcelErrorDetailsBasedOnBeanValidations(
				row.getRowNum(), budgetCode);
		if (excelErrorDetailsBasedOnBeanValidations != null) {
			isRowHavingErrors = true;
			rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
		}

		if (isRowHavingErrors) {
			rowValidationReturn.put("errorList", rowErrorList);
			rowValidationReturn.put("budgetCodeBean", null);

		} else {
			rowValidationReturn.put("errorList", null);
			rowValidationReturn.put("budgetCodeBean", budgetCode);
		}
		return (HashMap<String, Object>) rowValidationReturn;
	}
}
