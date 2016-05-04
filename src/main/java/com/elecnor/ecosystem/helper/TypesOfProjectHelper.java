package com.elecnor.ecosystem.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.elecnor.ecosystem.bean.ProjectType;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

public class TypesOfProjectHelper {

	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {

		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		boolean isRowHavingErrors = false;
		ProjectType projectType = new ProjectType();
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();

		// validation for Project Name
		colNum = ConstantUtil.PROJECT_TYPE_DATA_PROJECT_NAME;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			projectType.setProjectTypeName(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		projectType.setSubmittedBy(userDetail);
		projectType.setSubmittedDate(new Date());
		projectType.setDomainDetail(userDetail.getDomainDetail());
		projectType.setStatus("ACTIVE");

		ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil.getExcelErrorDetailsBasedOnBeanValidations(
				row.getRowNum(), projectType);
		if (excelErrorDetailsBasedOnBeanValidations != null) {
			isRowHavingErrors = true;
			rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
		}
		
		if (isRowHavingErrors) {
			rowValidationReturn.put("errorList", rowErrorList);
			rowValidationReturn.put("typesOfProjectBean", null);

		} else {
			rowValidationReturn.put("errorList", null);
			rowValidationReturn.put("typesOfProjectBean", projectType);
		}
		return (HashMap<String, Object>) rowValidationReturn;
	}

}
