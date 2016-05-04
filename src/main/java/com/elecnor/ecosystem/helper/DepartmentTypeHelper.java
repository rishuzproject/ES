package com.elecnor.ecosystem.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

public class DepartmentTypeHelper {

	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {
		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		boolean isRowHavingErrors = false;
		DepartmentType departmentType = new DepartmentType();
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();

		// validation for Department Name
		colNum = ConstantUtil.DEPARTMENT_TYPE_DATA_DEPARTMENT_NAME;// Constants
		stringToCheck = "";
		try {
			stringToCheck = (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
			departmentType.setDepartmentName(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}
		
		departmentType.setSubmittedBy(userDetail);
		departmentType.setSubmittedDate(new Date());
		departmentType.setDomainDetail(userDetail.getDomainDetail());
		departmentType.setStatus("ACTIVE");
		
		ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil.getExcelErrorDetailsBasedOnBeanValidations(
				row.getRowNum(), departmentType);
		if (excelErrorDetailsBasedOnBeanValidations != null) {
			isRowHavingErrors = true;
			rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
		}

		if (isRowHavingErrors) {
			rowValidationReturn.put("errorList", rowErrorList);
			rowValidationReturn.put("departmentTypeBean", null);

		} else {
			rowValidationReturn.put("errorList", null);
			rowValidationReturn.put("departmentTypeBean", departmentType);
		}
		return (HashMap<String, Object>) rowValidationReturn;
	}

}
