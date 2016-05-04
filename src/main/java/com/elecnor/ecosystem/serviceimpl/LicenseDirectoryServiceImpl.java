package com.elecnor.ecosystem.serviceimpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.LicenseDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.LicenseDirectoryDAO;
import com.elecnor.ecosystem.helper.LocalLicenseHelper;
import com.elecnor.ecosystem.helper.StateLicenseHelper;
import com.elecnor.ecosystem.service.LicenseDirectoryService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.elecnor.ecosystem.util.UploadFileUtility;
import com.elecnor.ecosystem.util.Utility;

@Service
public class LicenseDirectoryServiceImpl implements LicenseDirectoryService {
	@Autowired
	LicenseDirectoryDAO sLicenseDirectory;
	
	@Override
	public String addUpdateLocalLicenseService(LicenseDirectory lLicenseDirectory, DomainDetail domainDetail,
			String action) throws Exception {
		
		String result = "";
		try {
			lLicenseDirectory.setDomainDetail(domainDetail);
			lLicenseDirectory.setType("LOCAL");
			lLicenseDirectory.setStatus("ACTIVE");
			if (action.equalsIgnoreCase("save")|| action.equalsIgnoreCase("saveandcontinue")) {
				result = sLicenseDirectory.addLicenseRecord(lLicenseDirectory);
			 } else if (action.equalsIgnoreCase("Update")) {
				result=sLicenseDirectory.updateLicenseRecord(lLicenseDirectory);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String addLicenseRecord(LicenseDirectory licenseDirectory, int states) throws Exception {
		// TODO Auto-generated method stub
		
		String result=null;
		try{
			licenseDirectory.setState(states);
			licenseDirectory.setType("STATE");
			licenseDirectory.setStatus("ACTIVE");
			licenseDirectory.setLicenseSubmittedBy(null);
			licenseDirectory.setLicenseUpdatedBy(null);
			result = sLicenseDirectory.addLicenseRecord(licenseDirectory);
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> uploadStateLicFile(MultipartFile fileUploaded, HttpSession session,
			int confirmStateLicUploadId) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<ExcelErrorDetails> stateLicErrorList = new ArrayList<ExcelErrorDetails>();
		ArrayList<LicenseDirectory> stateLicenseList = new ArrayList<LicenseDirectory>();
		UploadFileUtility upUltil = new UploadFileUtility();
		StateLicenseHelper stateLicenseHelper=new StateLicenseHelper();
		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;
		
		Utility util = new Utility();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		
		try {
			if (!fileUploaded.isEmpty()) {
				
				Workbook workBook = upUltil.readExcelFileFromMultipart(fileUploaded);
				if (workBook == null) {
					return upUltil.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
				}
				if(confirmStateLicUploadId != 1){
					isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.STATE_LICENSE_SHEETNUM,
							ConstantUtil.STATE_LICENSE_HEADER_ROWNUM, ConstantUtil.STATE_LICENSE_EXCEL_FORMAT);
					if (!isValidSchema) {
						return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
					}
				}
				Sheet sheet=null;
				try{
				 sheet = workBook.getSheetAt(ConstantUtil.STATE_LICENSE_SHEETNUM);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				for (i = 1; i <= sheet.getLastRowNum(); i++) {
					System.out.println(i);
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					Map<String, Object> rowValidationResult = new HashMap<String, Object>();
					//if (confirmUploadId != 1) {
						rowValidationResult = stateLicenseHelper.validateRowDataAndFetchBean(row, userDetail);
						if (rowValidationResult.get("licenseDirectoryBean") == null) {
							stateLicErrorList.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
							hasErrorOccured = true;
						} else {
							stateLicenseList.add((LicenseDirectory) rowValidationResult.get("licenseDirectoryBean"));
						}
					}
						
						if ((confirmStateLicUploadId == 1) || (confirmStateLicUploadId != 1 && !hasErrorOccured)) {
							stateLicErrorList = saveStateLicenseList(stateLicenseList);
							if (stateLicErrorList.isEmpty()) {
								stateLicErrorList = null;
							}
							return util.responseBuilder(stateLicErrorList);

						} else {
							
							return util.responseBuilder(stateLicErrorList);
						}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return resultMap;
//			try {
//				workBook = new XSSFWorkbook(fileUploaded.getInputStream());
//			} catch (Exception e) {
//				try {
//					workBook = new HSSFWorkbook(fileUploaded.getInputStream());
//				} catch (IOException e1) {
//				}
//			}
//			if (workBook == null) {
//				stateLicErrorList.add(getExcelErrorDetails(0, 0, "Wrong File Type"));
//				resultMap.put("ajaxResult", "error");
//				resultMap.put("reason", stateLicErrorList);
//				return resultMap;
//			}
//			// Schema Validation - Checks Whether Row header name is same as we
//			// specified.
//			isValidSchema = getSchemaValidation(workBook);
//			if (isValidSchema != 1) {
//				hasErrorOccured = true;
//				stateLicErrorList.add(getExcelErrorDetails(0, 0, "Header Validation Failed"));
//				resultMap.put("ajaxResult", "error");
//				resultMap.put("reason", stateLicErrorList);
//				return resultMap;
//			}
//
//			// Schema Validation Ends
//
//			// Data Validation Starts
//			sheetStart = readFromHeader(null, stringSheetStart);
//			Sheet sheet = workBook.getSheetAt(sheetStart);
//			for (i = 1; i <= sheet.getLastRowNum(); i++) {
//				Row row = sheet.getRow(i);
//				if (row == null) {
//					continue;
//				}
//				if (confirmStateLicUploadId != 1) {
//					ArrayList<ExcelErrorDetails> rowErrorDetailList = new ArrayList<ExcelErrorDetails>();
//					rowErrorDetailList = validateRowData(row);
//					if (rowErrorDetailList != null) {
//						stateLicErrorList.addAll(rowErrorDetailList);
//						hasErrorOccured = true;
//					}
//				}
//				if ((confirmStateLicUploadId != 1 && !hasErrorOccured) || confirmStateLicUploadId == 1) {
//					stateLicenseList.add(getStateLicenseDetails(row, domainDetail, userDetail));
//				}
//			}
//			if ((confirmStateLicUploadId == 1) || (confirmStateLicUploadId != 1 && !hasErrorOccured)) {
//				stateLicErrorList = saveStateLicenseList(stateLicenseList);
//				if (stateLicErrorList.isEmpty()) {
//					resultMap.put("ajaxResult", "success");
//					resultMap.put("reason", null);
//				} else {
//					resultMap.put("ajaxResult", "error");
//					resultMap.put("reason", stateLicErrorList);
//				}
//			} else {
//				resultMap.put("ajaxResult", "error");
//				resultMap.put("reason", stateLicErrorList);
//
//			}
//
//		} else {
//			resultMap.put("ajaxResult", "error");
//			resultMap
//					.put("reason",
//							"Cannot Find the excel file. Please refresh the page and try again. If this problem persists report it to Dev. Team.");
//
//		}
//		return resultMap;
	}

	private Integer getSchemaValidation(Workbook workBook) throws Exception {
		ArrayList<String> excelHeader = new ArrayList<String>();

		int j = 0;
		// Strings for reading from property file
		String sheetNumberString = "STATE_LICENSE_SHEETNUM";
		int sheetNumber = readFromHeader(null, sheetNumberString);
		// Values obtained from property file
		String headerRowStringForProp = "STATE_LICENSE_HEADER_ROWNUM";
		Sheet sheet = workBook.getSheetAt(sheetNumber);

		int headerRowNum = readFromHeader(null, headerRowStringForProp);
		Row rowHeader = sheet.getRow(headerRowNum);
		while (rowHeader.getCell(j) != null) {
			String headerVal = rowHeader.getCell(j).getStringCellValue();
			excelHeader.add(headerVal);
			j++;
		}
		return readFromHeader(excelHeader, null);
	}

	public ArrayList<ExcelErrorDetails> validateRowData(Row row) {
		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		int colNum;
		String fieldToCheck = "";
		boolean valueNotValid = false;

		// validation for License Number
		fieldToCheck = "STATE_LICENSE_DATA_LICENSE_NO";
		colNum = readFromHeader(null, fieldToCheck);
		try {
			stringToCheck = String
					.valueOf((long) (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "License No. should not have characters"));
			valueNotValid = true;
		}
		if (!valueNotValid) {
			if (stringToCheck.equals("0")) {

				rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "License Number cannot be null"));
			}
		}
		valueNotValid = false;

		// validation for State
		fieldToCheck = "STATE_LICENSE_DATA_STATE";
		colNum = readFromHeader(null, fieldToCheck);
		try {
			stringToCheck = String.valueOf((int) (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Choose state from the given options"));
			valueNotValid = true;
		}
		valueNotValid = false;

		// validation for Expiry Date
		fieldToCheck = "STATE_LICENSE_DATA_EXPIRY_DATE";
		colNum = readFromHeader(null, fieldToCheck);
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(getExcelErrorDetails(rowNumber, colNum,
					"Expiry date should be in proper format and with '-' or '.' as separators"));
			valueNotValid = true;
		}
		if (!valueNotValid) {
			if (stringToCheck == null) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Expiry Date cannot be null"));
			}
			if (!stringToCheck.matches("^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)[0-9]{2}$")) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Wrong Date Format"));
			}
		}
		valueNotValid = false;

		if (!rowErrorList.isEmpty()) {
			return rowErrorList;
		} else
			return null;
	}

	public LicenseDirectory getStateLicenseDetails(Row row, DomainDetail domainDetail, UserDetail userDetail) {

		LicenseDirectory stateLicDirectory = new LicenseDirectory();
		String inputDateInString = "";
		Date inputDate = null;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String checkValue;

		stateLicDirectory.setLicenseSubmittedBy(userDetail);		
		stateLicDirectory.setDomainDetail(domainDetail);
		stateLicDirectory.setType("STATE");
		stateLicDirectory.setStatus("ACTIVE");

		try {
			int stateId = getStateId(row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
			if (stateId == -1) {
				stateLicDirectory.setState(null);
			} else {
				stateLicDirectory.setState(stateId);
			}
		} catch (Exception e1) {
			stateLicDirectory.setState(null);
		}

		try {
			checkValue = String.valueOf((long) (row.getCell(0, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			if (checkValue.equalsIgnoreCase("0")) {
				stateLicDirectory.setLicenseNumber(null);
			} else {
				stateLicDirectory.setLicenseNumber(checkValue);
			}
		} catch (Exception e1) {
			stateLicDirectory.setLicenseNumber(null);
		}

		try {
			inputDateInString = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			inputDate = dateFormat.parse(inputDateInString);
			stateLicDirectory.setExpiryDate(inputDate);
		} catch (Exception e1) {
			stateLicDirectory.setExpiryDate(null);
		}

		try {
			stateLicDirectory.setPrimaryPerson(row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e) {
			stateLicDirectory.setPrimaryPerson("");
		}

		try {
			stateLicDirectory.setLicenseDescription(row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue()
					.trim());
		} catch (Exception e) {
			stateLicDirectory.setLicenseDescription("");
		}

		return stateLicDirectory;
	}

	public int getStateId(String stateName) {

		if (stateName.equalsIgnoreCase("")) {
			return 0;
		} else if (stateName.equalsIgnoreCase("Alabama")) {
			return 1;
		} else if (stateName.equalsIgnoreCase("Alaska")) {
			return 3;

		} else if (stateName.equalsIgnoreCase("Arizona")) {

			return 4;
		} else if (stateName.equalsIgnoreCase("Arkansas")) {
			return 5;

		} else if (stateName.equalsIgnoreCase("California")) {
			return 6;

		} else if (stateName.equalsIgnoreCase("Colorado")) {
			return 7;

		} else if (stateName.equalsIgnoreCase("Connecticut")) {
			return 8;

		} else if (stateName.equalsIgnoreCase("Delaware")) {
			return 9;

		} else if (stateName.equalsIgnoreCase("Delaware")) {
			return 10;

		} else if (stateName.equalsIgnoreCase("Florida")) {
			return 11;

		} else if (stateName.equalsIgnoreCase("Georgia")) {
			return 12;

		} else if (stateName.equalsIgnoreCase("Hawaii")) {
			return 13;

		} else if (stateName.equalsIgnoreCase("Idaho")) {
			return 14;

		} else if (stateName.equalsIgnoreCase("Illinois")) {
			return 15;

		} else if (stateName.equalsIgnoreCase("Iowa")) {
			return 16;

		} else if (stateName.equalsIgnoreCase("Kansas")) {
			return 17;
		} else if (stateName.equalsIgnoreCase("Kentucky")) {
			return 18;
		} else if (stateName.equalsIgnoreCase("Louisiana")) {
			return 19;
		} else if (stateName.equalsIgnoreCase("Maine")) {
			return 20;
		} else if (stateName.equalsIgnoreCase("Maryland")) {
			return 21;
		} else if (stateName.equalsIgnoreCase("Massachusetts")) {
			return 22;
		} else if (stateName.equalsIgnoreCase("Minnesota")) {
			return 23;
		} else if (stateName.equalsIgnoreCase("Mississippi")) {
			return 24;
		} else if (stateName.equalsIgnoreCase("Missouri")) {
			return 25;
		} else if (stateName.equalsIgnoreCase("Montana")) {
			return 26;
		} else if (stateName.equalsIgnoreCase("Nebraska")) {
			return 27;
		} else if (stateName.equalsIgnoreCase("Nevada")) {
			return 28;
		} else if (stateName.equalsIgnoreCase("New Hampshire")) {
			return 29;
		} else if (stateName.equalsIgnoreCase("New Jersey")) {
			return 30;
		} else if (stateName.equalsIgnoreCase("New Mexico")) {
			return 31;
		} else if (stateName.equalsIgnoreCase("New York")) {
			return 32;
		} else if (stateName.equalsIgnoreCase("North Carolina")) {
			return 33;
		} else if (stateName.equalsIgnoreCase("North Dakota")) {
			return 35;
		} else if (stateName.equalsIgnoreCase("Ohio")) {
			return 34;
		} else if (stateName.equalsIgnoreCase("Oklahoma")) {
			return 35;
		} else if (stateName.equalsIgnoreCase("Oregon")) {
			return 36;
		} else if (stateName.equalsIgnoreCase("Pennsylvania")) {
			return 37;
		} else if (stateName.equalsIgnoreCase("Rhode Island")) {
			return 38;
		} else if (stateName.equalsIgnoreCase("South Carolina")) {
			return 39;
		} else if (stateName.equalsIgnoreCase("South Dakota")) {
			return 40;
		} else if (stateName.equalsIgnoreCase("Tennessee")) {
			return 41;
		} else if (stateName.equalsIgnoreCase("Texas")) {
			return 42;
		} else if (stateName.equalsIgnoreCase("Utah")) {
			return 43;
		} else if (stateName.equalsIgnoreCase("Vermont")) {
			return 44;
		} else if (stateName.equalsIgnoreCase("Virginia")) {
			return 45;
		} else if (stateName.equalsIgnoreCase("Washington")) {
			return 46;
		} else if (stateName.equalsIgnoreCase("West Virginia")) {
			return 47;
		} else if (stateName.equalsIgnoreCase("Wisconsin")) {
			return 48;
		} else if (stateName.equalsIgnoreCase("Wyoming")) {
			return 49;
		} else
			return -1;

	}

	public ExcelErrorDetails getExcelErrorDetails(int rowNum, int colNum, String msg) {
		ExcelErrorDetails e = new ExcelErrorDetails();
		e.setRowNumber(rowNum);
		e.setColNumber(colNum);
		e.setErrorMessage(msg);
		if (colNum != -1) {
			e.setExcelCell(ConstantUtil.columnMapping[colNum] + rowNum);
		}
		return e;
	}

	public ArrayList<ExcelErrorDetails> saveStateLicenseList(List<LicenseDirectory> stateLicenseList) {

		String sLicenseDAOResult = "";
		int i = 1;
		UploadFileUtility upUltil = new UploadFileUtility();
		ArrayList<ExcelErrorDetails> sLicDAOErrorList = new ArrayList<ExcelErrorDetails>();
		for (LicenseDirectory stateLicObject : stateLicenseList) {
			
			sLicenseDAOResult = "";
			try {
				sLicenseDAOResult = sLicenseDirectory.addLicenseRecord(stateLicObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (sLicenseDAOResult != null) {
				sLicDAOErrorList.add(upUltil.getExcelErrorDetails(i, -1, "Error in saving to database"));
			}
			
			i++;
		}
		return sLicDAOErrorList;
	}

	public Integer readFromHeader(ArrayList<String> excelHeader, String valToCheck) {
		int result = 0;
		try {
			ClassLoader cl = PropertyFileReader.class.getClassLoader();
			InputStream in = cl.getResourceAsStream("header.properties");
			Properties prop = new Properties();
			prop.load(in);
			if (valToCheck == null) {
				String excelUploadedHeader = excelHeader.toString().substring(1, excelHeader.toString().indexOf("]"));
				if (excelUploadedHeader.equalsIgnoreCase(prop.getProperty(ConstantUtil.STATE_LICENSE_EXCEL_FORMAT))) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				return Integer.valueOf(prop.getProperty(valToCheck));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public HashMap<String, Object> uploadLocalLicFile(MultipartFile fileUploaded, HttpSession session,
			int confirmLocalLicUploadId) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		ArrayList<ExcelErrorDetails> errorList = new ArrayList<ExcelErrorDetails>();
		ArrayList<LicenseDirectory> localLicenseList = new ArrayList<LicenseDirectory>();
		UploadFileUtility upUltil = new UploadFileUtility();
		LocalLicenseHelper LocalLicenseDirectoryHelper = new LocalLicenseHelper();

		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;

		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		

		if (!fileUploaded.isEmpty()) {
			
			Workbook workBook = upUltil.readExcelFileFromMultipart(fileUploaded);
			if (workBook == null) {
				return upUltil.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
			}
			
			if(confirmLocalLicUploadId != 1){
				isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.LOCAL_LICENSE_SHEETNUM,
						ConstantUtil.LOCAL_LICENSE_HEADER_ROWNUM, ConstantUtil.LOCAL_LICENSE_EXCEL_FORMAT);
				if (!isValidSchema) {
					return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
				}
			}
			Sheet sheet = workBook.getSheetAt(ConstantUtil.LOCAL_LICENSE_SHEETNUM);
			for (i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
			
				Map<String, Object> rowValidationResult = new HashMap<String, Object>();
				//if (confirmUploadId != 1) {
					rowValidationResult = LocalLicenseDirectoryHelper.validateRowDataAndFetchBean(row, userDetail);
					if (rowValidationResult.get("licenseDirectoryBean") == null) {
						errorList
								.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
						hasErrorOccured = true;
					} else {
						localLicenseList.add((LicenseDirectory) rowValidationResult.get("licenseDirectoryBean"));
					}
				//}
			}
			if ((confirmLocalLicUploadId == 1) || (confirmLocalLicUploadId != 1 && !hasErrorOccured)) {
				errorList = saveLocalLicenseList(localLicenseList);
				if (errorList.isEmpty()) {
					errorList = null;
				}
				return util.responseBuilder(errorList);

			} else {
				
				return util.responseBuilder(errorList);
			}
			
		}
//			Workbook workBook = null;
//			try {
//				workBook = new XSSFWorkbook(fileUploaded.getInputStream());
//			} catch (Exception e) {
//				try {
//					workBook = new HSSFWorkbook(fileUploaded.getInputStream());
//				} catch (IOException e1) {
//				}
//			}
//			if (workBook == null) {
//				localLicErrorList.add(getExcelErrorDetails(0, 0, "Wrong File Type"));
//				resultMap.put("ajaxResult", "error");
//				resultMap.put("reason", localLicErrorList);
//				return resultMap;
//			}
//			// Schema Validation - Checks Whether Row header name is same as we
//			// specified.
//			isValidSchema = getLocalSchemaValidation(workBook);
//			if (isValidSchema != 1) {
//				hasErrorOccured = true;
//				localLicErrorList.add(getExcelErrorDetails(0, 0, "Header Validation Failed"));
//				resultMap.put("ajaxResult", "error");
//				resultMap.put("reason", localLicErrorList);
//				return resultMap;
//			}
//			// Schema Validation Ends
//
//			// Data Validation Starts
//			sheetStart = readFromLocalHeader(null, stringSheetStart);
//			Sheet sheet = workBook.getSheetAt(sheetStart);
//			for (i = 1; i <= sheet.getLastRowNum(); i++) {
//				Row row = sheet.getRow(i);
//				if (row == null) {
//					continue;
//				}
//				if (confirmLocalLicUploadId != 1) {
//					ArrayList<ExcelErrorDetails> rowErrorDetailList = new ArrayList<ExcelErrorDetails>();
//					rowErrorDetailList = validateLocalRowData(row);
//					if (rowErrorDetailList != null) {
//						localLicErrorList.addAll(rowErrorDetailList);
//						hasErrorOccured = true;
//					}
//				}
//				if ((confirmLocalLicUploadId != 1 && !hasErrorOccured) || confirmLocalLicUploadId == 1) {
//					localLicenseList.add(getLocalLicenseDetails(row, domainDetail, userDetail));
//				}
//			}
//			if ((confirmLocalLicUploadId == 1) || (confirmLocalLicUploadId != 1 && !hasErrorOccured)) {
//				localLicErrorList = saveLocalLicenseList(localLicenseList);
//				if (localLicErrorList.isEmpty()) {
//					resultMap.put("ajaxResult", "success");
//					resultMap.put("reason", null);
//				} else {
//					resultMap.put("ajaxResult", "error");
//					resultMap.put("reason", localLicErrorList);
//				}
//			} else {
//				resultMap.put("ajaxResult", "error");
//				resultMap.put("reason", localLicErrorList);
//
//			}
//
//		} else {
//			resultMap.put("ajaxResult", "error");
//			resultMap
//					.put("reason",
//							"Cannot Find the excel file. Please refresh the page and try again. If this problem persists report it to Dev. Team.");
//
//		}
		return resultMap;
	}

	private Integer getLocalSchemaValidation(Workbook workBook) throws Exception {
		ArrayList<String> excelHeader = new ArrayList<String>();

		int j = 0;
		// Strings for reading from property file
		String sheetNumberString = "LOCAL_LICENSE_SHEETNUM";
		int sheetNumber = readFromLocalHeader(null, sheetNumberString);
		// Values obtained from property file
		String headerRowStringForProp = "LOCAL_LICENSE_HEADER_ROWNUM";
		Sheet sheet = workBook.getSheetAt(sheetNumber);

		int headerRowNum = readFromLocalHeader(null, headerRowStringForProp);
		Row rowHeader = sheet.getRow(headerRowNum);
		while (rowHeader.getCell(j) != null) {
			String headerVal = rowHeader.getCell(j).getStringCellValue();
			excelHeader.add(headerVal);
			j++;
		}
		return readFromLocalHeader(excelHeader, null);
	}

	public ArrayList<ExcelErrorDetails> validateLocalRowData(Row row) {
		int rowNumber = row.getRowNum();
		ArrayList<ExcelErrorDetails> rowErrorList = new ArrayList<ExcelErrorDetails>();
		String stringToCheck = "";
		int colNum;
		String fieldToCheck = "";
		boolean valueNotValid = false;

		// validation for License Number
		fieldToCheck = "LOCAL_LICENSE_DATA_LICENSE_NO";
		colNum = readFromLocalHeader(null, fieldToCheck);
		try {
			stringToCheck = String.valueOf((int) (row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "License No. should not have characters"));
			valueNotValid = true;
		}
		if (!valueNotValid) {
			if (stringToCheck.equals("0")) {

				rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "License Number cannot be null"));
			}
		}

		// validation for Local Jurisdiction
		fieldToCheck = "LOCAL_LICENSE_DATA_LOCAL_JURISDICTION";
		colNum = readFromLocalHeader(null, fieldToCheck);
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Local Jurisdiction should have characters"));
			valueNotValid = true;
		}
		if (!valueNotValid) {
			if (stringToCheck.equals("")) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Local Jurisdiction cannot be null"));
			}
		}

		// validation for Expiry Date
		fieldToCheck = "LOCAL_LICENSE_DATA_EXPIRY_DATE";
		colNum = readFromHeader(null, fieldToCheck);
		try {
			stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		} catch (IllegalStateException illegalStateException) {
			rowErrorList.add(getExcelErrorDetails(rowNumber, colNum,
					"Expiry date should be in proper format and with '-' or '.' as separators"));
			valueNotValid = true;
		}
		if (!valueNotValid) {
			if (stringToCheck == null) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Expiry Date cannot be null"));
			}
			if (!stringToCheck.matches("^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)[0-9]{2}$")) {
				rowErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Wrong Date Format"));
			}
		}

		if (!rowErrorList.isEmpty()) {
			return rowErrorList;
		} else
			return null;
	}

	public LicenseDirectory getLocalLicenseDetails(Row row, DomainDetail domainDetail, UserDetail userDetail) {

		LicenseDirectory localLicDirectory = new LicenseDirectory();
		String inputDateInString = "";
		Date inputDate = null;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String checkValue;

		localLicDirectory.setLicenseSubmittedBy(userDetail);
		localLicDirectory.setDomainDetail(domainDetail);
		localLicDirectory.setType("LOCAL");
		localLicDirectory.setStatus("ACTIVE");

		try {
			checkValue = String.valueOf((int) (row.getCell(0, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()));
			if (checkValue.equalsIgnoreCase("0")) {
				localLicDirectory.setLicenseNumber("");
			} else {
				localLicDirectory.setLicenseNumber(checkValue);
			}
		} catch (Exception e1) {
			localLicDirectory.setLicenseNumber("");
		}

		try {
			localLicDirectory
					.setLocalJurisdiction(row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e1) {
			localLicDirectory.setLocalJurisdiction("");
		}

		try {
			localLicDirectory.setPrimaryPerson(row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
		} catch (Exception e1) {
			localLicDirectory.setPrimaryPerson("");
		}

		try {
			inputDateInString = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
			inputDate = dateFormat.parse(inputDateInString);
			localLicDirectory.setExpiryDate(inputDate);
		} catch (Exception e1) {
			localLicDirectory.setExpiryDate(null);
		}

		return localLicDirectory;
	}

	public ArrayList<ExcelErrorDetails> saveLocalLicenseList(List<LicenseDirectory> localLicenseList) {

		String lLicenseDAOResult = "";
		int i = 1;
		ArrayList<ExcelErrorDetails> lLicDAOErrorList = new ArrayList<ExcelErrorDetails>();
		for (LicenseDirectory localLicObject : localLicenseList) {
			lLicenseDAOResult = "";
			try {
				lLicenseDAOResult = sLicenseDirectory.addLicenseRecord(localLicObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (lLicenseDAOResult != null) {
				lLicDAOErrorList.add(getExcelErrorDetails(i, -1, "Error in saving to database"));
			}
			i++;
		}
		return lLicDAOErrorList;
	}

	public Integer readFromLocalHeader(ArrayList<String> excelHeader, String valToCheck) {
		int result = 0;
		try {
			ClassLoader cl = PropertyFileReader.class.getClassLoader();
			InputStream in = cl.getResourceAsStream("header.properties");
			Properties prop = new Properties();
			prop.load(in);
			if (valToCheck == null) {
				String excelUploadedHeader = excelHeader.toString().substring(1, excelHeader.toString().indexOf("]"));

				if (excelUploadedHeader.equalsIgnoreCase(prop.getProperty(ConstantUtil.LOCAL_LICENSE_EXCEL_FORMAT))) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				return Integer.valueOf(prop.getProperty(valToCheck));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
