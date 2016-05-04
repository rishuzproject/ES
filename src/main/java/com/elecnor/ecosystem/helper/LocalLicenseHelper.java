package com.elecnor.ecosystem.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.elecnor.ecosystem.bean.LicenseDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

public class LocalLicenseHelper {
	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {
		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		boolean isRowHavingErrors = false;
		LicenseDirectory licenseDirectory=new LicenseDirectory();
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();
		
		colNum=ConstantUtil.LOCAL_LICENSE_DATA_LOCAL_JURISDICTION;
		try{
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			licenseDirectory.setLocalJurisdiction(stringToCheck);
		}
		catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
			
		}
		stringToCheck="";
		colNum=ConstantUtil.LOCAL_LICENSE_DATA_LICENSE_NO;
		try{
		stringToCheck = String.valueOf((int)row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
		licenseDirectory.setLicenseNumber(stringToCheck);
		
		}
		catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_INTEGER_VALIDATION_ERROR));
			isRowHavingErrors = true;
			
		}
		

		//validate Expiry date
		stringToCheck = "";
		colNum = ConstantUtil.LOCAL_LICENSE_DATA_EXPIRY_DATE;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			DateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
			Date date = format.parse(stringToCheck);
			licenseDirectory.setExpiryDate(date);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		licenseDirectory.setStatus("ACTIVE");
		licenseDirectory.setType("LOCAL");
		licenseDirectory.setLicenseSubmittedBy(userDetail);
		licenseDirectory.setDomainDetail(userDetail.getDomainDetail());
		//validate Primary Person
		
		stringToCheck = "";
		colNum = ConstantUtil.LOCAL_LICENSE_DATA_PRIMARY_PERSON;
		try{
			
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
			licenseDirectory.setPrimaryPerson(stringToCheck);
			
		}
		catch (Exception e) {
			
			licenseDirectory.setPrimaryPerson("");
			
		}
		ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil
				.getExcelErrorDetailsBasedOnBeanValidations(row.getRowNum(), licenseDirectory);
		if (excelErrorDetailsBasedOnBeanValidations != null) {
			isRowHavingErrors = true;
			rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
		}

	//}
		if (isRowHavingErrors) {
			rowValidationReturn.put("errorList", rowErrorList);
			rowValidationReturn.put("licenseDirectoryBean", null);

		} else {
	rowValidationReturn.put("errorList", null);
	rowValidationReturn.put("licenseDirectoryBean", licenseDirectory);
		}
	return (HashMap<String, Object>) rowValidationReturn;

	}
}
