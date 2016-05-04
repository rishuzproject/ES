package com.elecnor.ecosystem.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.VendorDirectory;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;

public class VendorDirectoryHelper {

	public HashMap<String, Object> validateRowDataAndFetchBean(Row row, UserDetail userDetail) {
		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		UploadFileUtility upUtil = new UploadFileUtility();
		int colNum;
		boolean isRowHavingErrors = false;
		VendorDirectory vendorDirectory = new VendorDirectory();
		Map<String, Object> rowValidationReturn = new HashMap<String, Object>();

		// validation for Vendor Name
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_VENDOR_NAME;// Constants
		stringToCheck = "";
		try {
			stringToCheck = (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
			vendorDirectory.setVendorName(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Vendor Legal Name
		stringToCheck = "";
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_VENDOR_LEGAL_NAME;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			vendorDirectory.setVendorLegalName(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Vendor Legal Status
		stringToCheck = "";
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_VENDOR_LEGAL_STATUS;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			vendorDirectory.setVendorLegalStatus(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Ownership
		stringToCheck = "";
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_OWNERSHIP;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			vendorDirectory.setVendorOwnership(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for vendor phone
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_PHONE;
		stringToCheck = "";
		try {
			stringToCheck = String.valueOf((int) (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			vendorDirectory.setVendorPhoneNo(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(upUtil
					.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_INTEGER_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Vendor Email
		stringToCheck = "";
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_EMAIL;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			vendorDirectory.setVendorEmail(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Vendor Address
		stringToCheck = "";
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_ADDRESS;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
//			vendorDirectory.setVendorAddress(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// validation for Vendor Address
		stringToCheck = "";
		colNum = ConstantUtil.VENDOR_DIRECTORY_DATA_WEBSITE;
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			vendorDirectory.setVendorWebsite(stringToCheck);
		} catch (IllegalStateException illegalStateException) {
			rowErrorList
					.add(upUtil.getExcelErrorDetails(rowNumber, colNum, ConstantUtil.ERROR_STRING_VALIDATION_ERROR));
			isRowHavingErrors = true;
		}

		// if (isRowHavingErrors) {
		// rowValidationReturn.put("errorList", rowErrorList);
		// rowValidationReturn.put("vendorDirectoryBean", null);
		//
		// } else {
		vendorDirectory.setSubmittedBy(userDetail);
		vendorDirectory.setSubmittedDate(new Date());
		vendorDirectory.setDomainDetail(userDetail.getDomainDetail());
		vendorDirectory.setStatus("ACTIVE");

		/*try {
			vendorDirectory.setVendorMailingAddress(row
					.getCell(ConstantUtil.VENDOR_DIRECTORY_DATA_MAILING_ADDRESS, Row.CREATE_NULL_AS_BLANK)
					.getStringCellValue().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			vendorDirectory.setVendorMailingAddress("");
		}*/

		try {
			String checkValue = String.valueOf((int) (row.getCell(ConstantUtil.VENDOR_DIRECTORY_DATA_FAX,
					Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			if (checkValue.equalsIgnoreCase("0")) {
				vendorDirectory.setVendorFax("");
			} else {
				vendorDirectory.setVendorFax(checkValue);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			vendorDirectory.setVendorFax("");
		}

		try {
			vendorDirectory.setRepresentativeName(row
					.getCell(ConstantUtil.VENDOR_DIRECTORY_DATA_REPRESENTATIVE_NAME, Row.CREATE_NULL_AS_BLANK)
					.getStringCellValue().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			vendorDirectory.setRepresentativeName("");
		}

		try {
			String checkValue = String.valueOf((int) (row.getCell(
					ConstantUtil.VENDOR_DIRECTORY_DATA_REPRESENTATIVE_PHONE, Row.CREATE_NULL_AS_BLANK)
					.getNumericCellValue()));
			if (checkValue.equalsIgnoreCase("0")) {
				vendorDirectory.setRepresentativePhone("");
			} else {
				vendorDirectory.setRepresentativePhone(checkValue);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			vendorDirectory.setRepresentativePhone("");
		}

		/*try {
			vendorDirectory.setRepresentativeAddress(row
					.getCell(ConstantUtil.VENDOR_DIRECTORY_DATA_REPRESENTATIVE_ADDRESS, Row.CREATE_NULL_AS_BLANK)
					.getStringCellValue().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			vendorDirectory.setRepresentativeAddress("");
		}*/

		try {
			vendorDirectory.setIrs(row.getCell(ConstantUtil.VENDOR_DIRECTORY_DATA_IRS, Row.CREATE_NULL_AS_BLANK)
					.getStringCellValue().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			vendorDirectory.setIrs("");
		}

		try {
			vendorDirectory.setBusinessType(row
					.getCell(ConstantUtil.VENDOR_DIRECTORY_DATA_BUSINESS_TYPE, Row.CREATE_NULL_AS_BLANK)
					.getStringCellValue().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			vendorDirectory.setBusinessType("");
		}

		ExcelErrorDetails excelErrorDetailsBasedOnBeanValidations = upUtil.getExcelErrorDetailsBasedOnBeanValidations(
				row.getRowNum(), vendorDirectory);
		if (excelErrorDetailsBasedOnBeanValidations != null) {
			isRowHavingErrors = true;
			rowErrorList.add(excelErrorDetailsBasedOnBeanValidations);
		}

		// }
		if (isRowHavingErrors) {
			rowValidationReturn.put("errorList", rowErrorList);
			rowValidationReturn.put("vendorDirectoryBean", null);

		} else {
			rowValidationReturn.put("errorList", null);
			rowValidationReturn.put("vendorDirectoryBean", vendorDirectory);
		}
		return (HashMap<String, Object>) rowValidationReturn;

	}

	public int setVendorIdToDelete(HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub
		int vendorId = 0;
		try {
			if (request.getParameter("vendorIdToDel") != null
					&& request.getParameter("vendorIdToDel") != "") {
				vendorId = Integer.parseInt(request.getParameter(
						"vendorIdToDel").trim());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return vendorId;
	}
}
