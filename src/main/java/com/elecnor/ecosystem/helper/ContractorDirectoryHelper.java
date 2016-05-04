package com.elecnor.ecosystem.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.elecnor.ecosystem.bean.ContractorDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

public class ContractorDirectoryHelper {
	
	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {
		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		boolean isRowHavingErrors = false;
		ContractorDirectory contractorDirectory = new ContractorDirectory();
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();

		// validation for Vendor Name
		colNum = ConstantUtil.CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_NAME;// Constants
		stringToCheck = "";
		try {
			stringToCheck = (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
			contractorDirectory.setContractorName(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			
			isRowHavingErrors = true;
			illegalStateException.printStackTrace();
			
		}
		// validation for Contractor Name
		stringToCheck = "";
		colNum = ConstantUtil.CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_NAME;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			contractorDirectory.setContractorName(stringToCheck);;
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
			illegalStateException.printStackTrace();
		}
		
		colNum = ConstantUtil.CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_NO;
		try {
			stringToCheck = String.valueOf((int)row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
			contractorDirectory.setContractorNumber(stringToCheck);
		} catch (Exception e) {
			contractorDirectory.setContractorNumber("");
			e.printStackTrace();
		}
		
		// validation for Contractor phone
		colNum = ConstantUtil.CONTRACTOR_DIRECTORY_DATA_PHONE;
		stringToCheck = "";
		try {
			stringToCheck = String.valueOf((int) (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			contractorDirectory.setPhoneNumber(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil
					.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_INTEGER_VALIDATION_ERROR));
			isRowHavingErrors = true;
			illegalStateException.printStackTrace();
			
		}
		

		// validation for Contractor Email
		stringToCheck = "";
		colNum = ConstantUtil.CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_EMAIL;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			contractorDirectory.setContractorEmail(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
			illegalStateException.printStackTrace();
		}
	
			contractorDirectory.setSubmittedBy(userDetail);
			contractorDirectory.setSubmittedDate(new Date());
			contractorDirectory.setDomainDetail(userDetail.getDomainDetail());
			contractorDirectory.setStatus("ACTIVE");

			try {
//				contractorDirectory.setCorporateOfficeAddress(row
//						.getCell(ConstantUtil.CONTRACTOR_DIRECTORY_DATA_OFFICE_ADDRESS, Row.CREATE_NULL_AS_BLANK)
//						.getStringCellValue().trim());
			} catch (IllegalStateException illegalStateException) {
				rowErrorList
				.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
		           isRowHavingErrors = true;
		           illegalStateException.printStackTrace();
	      }
			

			try {
				String checkValue = String.valueOf((int) (row.getCell(ConstantUtil.CONTRACTOR_DIRECTORY_DATA_FAX,
						Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
				if (checkValue.equalsIgnoreCase("0")) {
					contractorDirectory.setFax("");
				} else {
					contractorDirectory.setFax(checkValue);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				contractorDirectory.setFax("");
				e.printStackTrace();
			}
			try {
				contractorDirectory.setRepresentativeName(row
						.getCell(ConstantUtil.CONTRACTOR_DIRECTORY_DATA_REPRESENTATIVE_NAME, Row.CREATE_NULL_AS_BLANK)
						.getStringCellValue().trim());
			}catch (IllegalStateException illegalStateException) {
				rowErrorList
				.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
		           isRowHavingErrors = true;
		           illegalStateException.printStackTrace();
	      }

			try {
				String checkValue = String.valueOf((int) (row.getCell(
						ConstantUtil.CONTRACTOR_DIRECTORY_DATA_REPRESENTATIVE_PHONE, Row.CREATE_NULL_AS_BLANK)
						.getNumericCellValue()));
			contractorDirectory.setRepresentativePhone(checkValue);
				
			} catch (IllegalStateException illegalStateException) {
				rowErrorList
				.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
		           isRowHavingErrors = true;
		           illegalStateException.printStackTrace();
	      }
			
			
			try {
//				contractorDirectory.setRepresentativeAddress(row
//						.getCell(ConstantUtil.CONTRACTOR_DIRECTORY_DATA_REPRESENTATIVE_ADDRESS, Row.CREATE_NULL_AS_BLANK)
//						.getStringCellValue().trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
//				contractorDirectory.setRepresentativeAddress("");
				e.printStackTrace();
			}


			try {
				contractorDirectory.setBusinessType(row
						.getCell(ConstantUtil.CONTRACTOR_DIRECTORY_DATA_BUSINESS_TYPE, Row.CREATE_NULL_AS_BLANK)
						.getStringCellValue().trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				contractorDirectory.setBusinessType("");
				e.printStackTrace();
			}

			ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil
					.getExcelErrorDetailsBasedOnBeanValidations(row.getRowNum(), contractorDirectory);
			if (excelErrorDetailsBasedOnBeanValidations != null) {
				isRowHavingErrors = true;
				rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
			}

		//}
			if (isRowHavingErrors) {
				rowValidationReturn.put("errorList", rowErrorList);
				rowValidationReturn.put("contractorDirectoryBean", null);
	
			} else {
		rowValidationReturn.put("errorList", null);
		rowValidationReturn.put("contractorDirectoryBean", contractorDirectory);
			}
		return (HashMap<String, Object>) rowValidationReturn;
	}

}
