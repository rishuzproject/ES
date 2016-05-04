package com.elecnor.ecosystem.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

public class CustomerDirectoryHelper {

	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {

		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		CustomerDirectory customerDirectory = new CustomerDirectory();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		boolean isRowHavingErrors = false;
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();

		// validation for CompanyName
		colNum = ConstantUtil.CUSTOMER_DIRECTORY_DATA_COMPANY_NAME;// Constants
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			customerDirectory.setCompanyName(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Company Number
		stringToCheck = "";
		colNum = ConstantUtil.CUSTOMER_DIRECTORY_DATA_COMPANY_NUMBER;
		try {
			stringToCheck = String.valueOf((long) (row.getCell(1, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			customerDirectory.setCompanyNumber(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil
					.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_INTEGER_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for CorporateOfficeAddress
		stringToCheck = "";
		colNum = ConstantUtil.CUSTOMER_DIRECTORY_DATA_OFFICE_ADDRESS;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			/*customerDirectory.setCorporateOfficeAddress(stringToCheck);*/
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for CompanyEmail
		stringToCheck = "";
		colNum = ConstantUtil.CUSTOMER_DIRECTORY_COMPANY_EMAIL;

		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			customerDirectory.setCompanyEmail(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Phone
		stringToCheck = "";
		colNum = ConstantUtil.CUSTOMER_DIRECTORY_DATA_PHONE;

		try {
			stringToCheck = String
					.valueOf((long) (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			customerDirectory.setPhoneNumber(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil
					.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_INTEGER_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for RepresentativeName
		stringToCheck = "";
		colNum = ConstantUtil.CUSTOMER_DIRECTORY_DATA_REPRESENTATIVE_NAME;

		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			customerDirectory.setRepresentativeName(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for RepresentativePhone
		colNum = ConstantUtil.CUSTOMER_DIRECTORY_DATA_REPRESENTATIVE_PHONE;
		try {
			stringToCheck = String
					.valueOf((long) (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			customerDirectory.setRepresentativePhone(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil.getExcelErrorDetails(rowNumber, colNum,
					"Representative Phone should have only numeric characters"));
			isRowHavingErrors = true;
		}

		customerDirectory.setSubmittedBy(userDetail);
		customerDirectory.setDomainDetail(userDetail.getDomainDetail());
		customerDirectory.setSubmittedDate(new Date());
		customerDirectory.setStatus("ACTIVE");

		try {
			String checkValue = String.valueOf((long) (row.getCell(ConstantUtil.CUSTOMER_DIRECTORY_DATA_FAX, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			if (checkValue.equalsIgnoreCase("0")) {
				customerDirectory.setFax("");
			} else {
				customerDirectory.setFax(checkValue);
			}
		} catch (Exception e) {
			customerDirectory.setFax("");
		}

//		try {
//			customerDirectory.setRepresentativeAddress(row.getCell(ConstantUtil.CUSTOMER_DIRECTORY_DATA_REPRESENTATIVE_ADDRESS, Row.CREATE_NULL_AS_BLANK).getStringCellValue()
//					.trim());
//		} catch (Exception e) {
//			customerDirectory.setRepresentativeAddress("");
//		}

		try {
			customerDirectory.setIrs(row.getCell(ConstantUtil.CUSTOMER_DIRECTORY_DATA_IRS, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e) {
			customerDirectory.setIrs("");
		}

		try {
			customerDirectory.setBusinessType(row.getCell(ConstantUtil.CUSTOMER_DIRECTORY_DATA_BUSINESS_TYPE, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e) {
			customerDirectory.setBusinessType("");
		}

		ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil.getExcelErrorDetailsBasedOnBeanValidations(
				row.getRowNum(), customerDirectory);
		if (excelErrorDetailsBasedOnBeanValidations != null) {
			isRowHavingErrors = true;
			rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
		}

		if (isRowHavingErrors) {
			rowValidationReturn.put("errorList", rowErrorList);
			rowValidationReturn.put("customerDirectoryBean", null);

		} else {
			rowValidationReturn.put("errorList", null);
			rowValidationReturn.put("customerDirectoryBean", customerDirectory);
		}

		return (HashMap<String, Object>) rowValidationReturn;
	}

}
