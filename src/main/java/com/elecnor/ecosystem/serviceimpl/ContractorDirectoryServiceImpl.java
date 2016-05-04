package com.elecnor.ecosystem.serviceimpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.ContractorDirectory;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.dao.ContractorDirectoryDAO;
import com.elecnor.ecosystem.helper.ContractorDirectoryHelper;
import com.elecnor.ecosystem.service.ContractorDirectoryService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.PropertyFileReader;
import com.elecnor.ecosystem.util.UploadFileUtility;
import com.elecnor.ecosystem.util.Utility;

@Service
public class ContractorDirectoryServiceImpl implements ContractorDirectoryService {

	@Autowired
	ContractorDirectoryDAO contrctorDirectoryDAO;
	@Autowired
	AddressDetailsDAO addressDetailsDAO;

	@Override
	public ContractorDirectory setSaveOrUpdateContractor(ContractorDirectory contractorDirectoryForm, UserDetail userDetail, DomainDetail domainDetail) throws Exception{

		String result = null;
		ContractorDirectory contractorDirectory = null;
		contractorDirectoryForm.setDomainDetail(domainDetail);
		contractorDirectoryForm.setStatus("ACTIVE");
		if(contractorDirectoryForm.getContractorId() == null){
			contractorDirectoryForm.setSubmittedBy(userDetail);
			contractorDirectoryForm.setSubmittedDate(new Date());
			contractorDirectory = contrctorDirectoryDAO.setContractorSave(contractorDirectoryForm);
		}else{
			contractorDirectoryForm.setUpdatedBy(userDetail);
			contractorDirectoryForm.setUpdatedDate(new Date());
			contractorDirectory = contrctorDirectoryDAO.setContractorUpdate(contractorDirectoryForm);
		}
		
		return contractorDirectory;
	}
	
	@Override
	public String deleteContractor(String compIdToDel, UserDetail userDetails) throws Exception {
		String result = "";
		try {
			if (compIdToDel != null && compIdToDel != "") {
				int compId = Integer.parseInt(compIdToDel.trim());
				result = contrctorDirectoryDAO.setContractorDelete(compId);
				result = addressDetailsDAO.setDeleteAddressByUser(compId,userDetails);
			} else {
				result = "Cannot Find The Company To Delete. Please refresh the page and try again.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	public HashMap<String, Object> uploadContractorFile(
			MultipartFile fileUploaded, HttpSession session,
			int confirmContractorUploadId) throws Exception{
		
		// confirmContractorUploadId- Default value -1, it implies that Excel is being for
				// the first time, If 1 It means user accepts that even if problem
				// exists in present excel, continue with Save operation.
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		ArrayList<ExcelErrorDetails> contractorErrorList = new ArrayList<ExcelErrorDetails>();
		ArrayList<ContractorDirectory> contractorList = new ArrayList<ContractorDirectory>();
		UploadFileUtility upUltil = new UploadFileUtility();
		ContractorDirectoryHelper  contractorDirectoryHelper = new ContractorDirectoryHelper();

		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;

		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");

				
				
				if (!fileUploaded.isEmpty()) {
					
					Workbook workBook = upUltil.readExcelFileFromMultipart(fileUploaded);
					if (workBook == null) {
						return upUltil.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
					}
					if(confirmContractorUploadId != 1){
						isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.CONTRACTOR_DIRECTORY_SHEETNUM,
								ConstantUtil.CONTRACTOR_DIRECTORY_HEADER_ROWNUM, ConstantUtil.CONTRACTOR_DIRECTORY_EXCEL_FORMAT);
						if (!isValidSchema) {
							return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
						}
					}
					Sheet sheet = workBook.getSheetAt(ConstantUtil.CONTRACTOR_DIRECTORY_SHEETNUM);
					for (i = 1; i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);
						if (row == null) {
							continue;
						}
						Map<String, Object> rowValidationResult = new HashMap<String, Object>();
						rowValidationResult = contractorDirectoryHelper.validateRowDataAndFetchBean(row, userDetail);
						if (rowValidationResult.get("contractorDirectoryBean") == null) {
							contractorErrorList
									.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
							hasErrorOccured = true;
						} else {
							contractorList.add((ContractorDirectory) rowValidationResult.get("contractorDirectoryBean"));
						}
					//}
				}
					
					if ((confirmContractorUploadId == 1) || (confirmContractorUploadId != 1 && !hasErrorOccured)) {
						contractorErrorList = saveContractorList(contractorList);
						if (contractorErrorList.isEmpty()) {
							contractorErrorList = null;
						}
						return util.responseBuilder(contractorErrorList);

					} else {
						
						return util.responseBuilder(contractorErrorList);
					}
				} 
					
			
					
					
				
           
//					Workbook workBook = null;
//					try {
//						workBook = new XSSFWorkbook(fileUploaded.getInputStream());
//					} catch (Exception e) {
//						try {
//							workBook = new HSSFWorkbook(fileUploaded.getInputStream());
//						} catch (IOException e1) {
//						}
//					}
//					if(workBook == null){
//						contractorErrorList.add(getExcelErrorDetails(0, 0,
//								"Wrong File Type"));
//						resultMap.put("ajaxResult", "error");
//						resultMap.put("reason", contractorErrorList);
//						return resultMap;
//					}
//					// Schema Validation - Checks Whether Row header name is same as we
//					// specified.
//					isValidSchema = getSchemaValidation(workBook);
//					if (isValidSchema != 1) {
//						hasErrorOccured = true;
//						contractorErrorList.add(getExcelErrorDetails(0, 0,
//								"Header Validation Failed"));
//						resultMap.put("ajaxResult", "error");
//						resultMap.put("reason", contractorErrorList);
//						return resultMap;
//					}
//					// Schema Validation Ends
//
//					// Data Validation Starts
//					sheetStart = readFromHeader(null,stringSheetStart);
//					Sheet sheet = workBook.getSheetAt(sheetStart);
//					for (i=1;i<=sheet.getLastRowNum();i++) {
//						Row row = sheet.getRow(i);
//						if(row == null){
//							continue;
//						}
//						if (confirmContractorUploadId != 1) {
//							ArrayList<ExcelErrorDetails> rowErrorDetailList = new ArrayList<ExcelErrorDetails>();
//							rowErrorDetailList = validateRowData(row);
//							if (rowErrorDetailList != null) {
//								contractorErrorList.addAll(rowErrorDetailList);
//								hasErrorOccured = true;
//							}
//						}
//						if ((confirmContractorUploadId != 1 && !hasErrorOccured)  || confirmContractorUploadId == 1){
//							contractorList.add(getContractorDirectoryDetails(row,domainDetail,userDetail));
//						}
//					}
//					if ((confirmContractorUploadId == 1)
//							|| (confirmContractorUploadId != 1 && !hasErrorOccured)) {
//						contractorErrorList = saveContractorList(contractorList);
//						if (contractorErrorList.isEmpty()) {
//							resultMap.put("ajaxResult", "success");
//							resultMap.put("reason", null);
//						} else {
//							resultMap.put("ajaxResult", "error");
//							resultMap.put("reason", contractorErrorList);
//						}
//					} else {
//						resultMap.put("ajaxResult", "error");
//						resultMap.put("reason", contractorErrorList);
//					}
//					
//				} else {
//					resultMap.put("ajaxResult", "error");
//					resultMap
//							.put("reason",
//									"Cannot Find the excel file. Please refresh the page and try again. If this problem persists report it to Dev. Team.");
//				}
				return resultMap;
			}

	private Integer getSchemaValidation(Workbook workBook) throws Exception {
				ArrayList<String> excelHeader = new ArrayList<String>();
				
				int j = 0;
				//Strings for reading from property file
				String sheetNumberString = "CONTRACTOR_DIRECTORY_SHEETNUM";
				int sheetNumber = readFromHeader(null, sheetNumberString);
				//Values obtained from property file
				String headerRowStringForProp = "CONTRACTOR_DIRECTORY_HEADER_ROWNUM";
				Sheet sheet = workBook.getSheetAt(sheetNumber);
				
				int headerRowNum = readFromHeader(null,headerRowStringForProp);
				Row rowHeader = sheet.getRow(headerRowNum);
				while (rowHeader.getCell(j) != null) {
					String headerVal = rowHeader.getCell(j).getStringCellValue();
					excelHeader.add(headerVal);
					j++;
				}
				return readFromHeader(excelHeader,null);
			}

			// Validate a row of data; If There is an error it return ExcelErrorClass
			// Object Else Null
	public ArrayList<ExcelErrorDetails> validateRowData(Row row) throws Exception {
				int rowNumber = row.getRowNum();
				ArrayList<ExcelErrorDetails> rowcontractorErrorList = new ArrayList<ExcelErrorDetails>();
				String stringToCheck = "";
				Pattern emailPattern = Pattern.compile(ConstantUtil.EMAIL_FORMAT);
				Pattern phonePattern = Pattern.compile(ConstantUtil.PHONE_FORMAT);
				Matcher matcher;
				int colNum;
				String fieldToCheck = "";
				boolean valueNotValid = false;
				
				//validation for Contractor Name
				fieldToCheck = "CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_NAME";
				colNum = readFromHeader(null, fieldToCheck);
				try {
					stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK)
							.getStringCellValue().trim();
				} catch (IllegalStateException illegalStateException) {
					rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum,
							"Contractor Name should have characters"));
					valueNotValid = true;
				}
				if(!valueNotValid){
					if (stringToCheck.equals("")) {
					
						rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Contractor Name cannot be null"));
						valueNotValid = true;
					}
					if (stringToCheck.length()>100){
						rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Contractor Name cannot be more than 100 characters"));
						valueNotValid = true;
					}
				}
				valueNotValid = false;
				
				//validation for Contractor Email
				fieldToCheck = "CONTRACTOR_DIRECTORY_DATA_CONTRACTOR_EMAIL";
				colNum = readFromHeader(null, fieldToCheck);
				try {
					stringToCheck = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK)
							.getStringCellValue().trim();
				} catch (IllegalStateException illegalStateException) {
					rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum,
							"Contractor Email should have characters"));
					valueNotValid = true;
				}
				if(!valueNotValid){
					matcher = emailPattern.matcher(stringToCheck);
					if (stringToCheck.equals("")) {
						rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum,
								"Email cannot be null"));
						valueNotValid = true;
					}
					if (!(matcher.matches())) {
						rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum, "Invalid email format"));
						valueNotValid = true;
					}
				}
				valueNotValid = false;
				
				//validation for contractor phone
				fieldToCheck = "CONTRACTOR_DIRECTORY_DATA_PHONE";
				colNum = readFromHeader(null, fieldToCheck);
				try {
					stringToCheck = String.valueOf((long)(row.getCell(colNum, Row.CREATE_NULL_AS_BLANK)
							.getNumericCellValue()));
				} catch (IllegalStateException illegalStateException) {
					rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum,
							"Contractor Phone should not have characters"));
					valueNotValid = true;
				}
				if(!valueNotValid){
					matcher = phonePattern.matcher(stringToCheck);
					if(!(matcher.matches())){
						rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum,
								"Wrong Phone Format"));
						valueNotValid = true;
					}
					if(stringToCheck.length()>15){
						rowcontractorErrorList.add(getExcelErrorDetails(rowNumber, colNum,
								"Max Phone length is 15"));
						valueNotValid = true;
					}
				}
				valueNotValid = false;
				
				if(!rowcontractorErrorList.isEmpty()){
					return rowcontractorErrorList;
				}
				else
					return null;
				
			}

	public ContractorDirectory getContractorDirectoryDetails(Row row,
					DomainDetail domainDetail,UserDetail userDetail) throws Exception {
				String checkValue;

				ContractorDirectory contractorDirectory = new ContractorDirectory();
				
				contractorDirectory.setSubmittedBy(userDetail);
				contractorDirectory.setSubmittedDate(new Date());
				contractorDirectory.setDomainDetail(domainDetail);
				contractorDirectory.setStatus("ACTIVE");

				try {
					contractorDirectory.setContractorName(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
				} catch (Exception e) {
					contractorDirectory.setContractorName("");
				}

				try {
					checkValue = String.valueOf((long)(row.getCell(1, Row.CREATE_NULL_AS_BLANK)
							.getNumericCellValue()));
					if(checkValue.equalsIgnoreCase("0")){
						contractorDirectory.setContractorNumber("");
					}
					else{
						contractorDirectory.setContractorNumber(checkValue);
					}
				} catch (Exception e) {
					contractorDirectory.setContractorNumber("");
				}

				try {
//					contractorDirectory.setCorporateOfficeAddress(row
//							.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue()
//							.trim());
				} catch (Exception e) {
//					contractorDirectory.setCorporateOfficeAddress("");
				}

				try {
					contractorDirectory.setContractorEmail(row
							.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue()
							.trim());
				} catch (Exception e) {
					contractorDirectory.setContractorEmail("");
				}

				try {
					checkValue = String.valueOf((long)(row.getCell(4, Row.CREATE_NULL_AS_BLANK)
							.getNumericCellValue()));
					if(checkValue.equalsIgnoreCase("0")){
						contractorDirectory.setPhoneNumber("");
					}
					else{
						contractorDirectory.setPhoneNumber(checkValue);
					}
				} catch (Exception e) {
					contractorDirectory.setPhoneNumber("");
				}

				try {
					checkValue = String.valueOf((long)(row.getCell(5, Row.CREATE_NULL_AS_BLANK)
							.getNumericCellValue()));
					if(checkValue.equalsIgnoreCase("0")){
						contractorDirectory.setFax("");
					}
					else{
						contractorDirectory.setFax(checkValue);
					}
				} catch (Exception e) {
					contractorDirectory.setFax("");
				}

				try {
					contractorDirectory.setRepresentativeName(row
							.getCell(6, Row.CREATE_NULL_AS_BLANK).getStringCellValue()
							.trim());
				} catch (Exception e) {
					contractorDirectory.setRepresentativeName("");
				}

				try {
					checkValue = String.valueOf((long)(row.getCell(7, Row.CREATE_NULL_AS_BLANK)
							.getNumericCellValue()));
					if(checkValue.equalsIgnoreCase("0")){
						contractorDirectory.setRepresentativePhone("");
					}
					else{
					contractorDirectory.setRepresentativePhone(checkValue);
					}
				} catch (Exception e) {
					contractorDirectory.setRepresentativePhone("");
				}

				try {
//					contractorDirectory.setRepresentativeAddress(row.getCell(8, Row.CREATE_NULL_AS_BLANK)
//							.getStringCellValue().trim());
				} catch (Exception e) {
//					contractorDirectory.setRepresentativeAddress("");
				}

				try {
					contractorDirectory.setBusinessType(row
							.getCell(9, Row.CREATE_NULL_AS_BLANK).getStringCellValue()
							.trim());
				} catch (Exception e) {
					contractorDirectory.setBusinessType("");
				}

				return contractorDirectory;
			}

	public ExcelErrorDetails getExcelErrorDetails(int rowNum, int colNum,
					String msg) throws Exception {
				ExcelErrorDetails e = new ExcelErrorDetails();
				e.setRowNumber(rowNum);
				e.setColNumber(colNum);
				e.setErrorMessage(msg);
				if (colNum != -1) {
					e.setExcelCell(ConstantUtil.columnMapping[colNum]+rowNum);
				}
				return e;
			}

	public ArrayList<ExcelErrorDetails> saveContractorList(List<ContractorDirectory> contractorList) throws Exception {

		ContractorDirectory contractorDAOResult = null;
				int i = 1;
				ArrayList<ExcelErrorDetails> contractorDAOErrorList = new ArrayList<ExcelErrorDetails>();
				System.out.println(contractorList.size());
				for (ContractorDirectory contractorDirectoryObj : contractorList) {
					contractorDAOResult = null;
					try {
						contractorDAOResult = contrctorDirectoryDAO.setContractorSave(contractorDirectoryObj);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(contractorDAOResult == null){
						contractorDAOErrorList.add(getExcelErrorDetails(i, -1,
								"Error in saving to database"));
					}
					i++;
				}
				return contractorDAOErrorList;
			}

	public Integer readFromHeader(ArrayList<String> excelHeader, String valToCheck) throws Exception {
				int result = 0;
				try {
					ClassLoader cl = PropertyFileReader.class.getClassLoader();
					InputStream in = cl.getResourceAsStream("header.properties");
					Properties prop = new Properties();
					prop.load(in);
					if(valToCheck==null){
						String excelUploadedHeader = excelHeader.toString().substring(1,
								excelHeader.toString().indexOf("]"));
						if (excelUploadedHeader.equalsIgnoreCase(prop
							.getProperty(ConstantUtil.CONTRACTOR_DIRECTORY_EXCEL_FORMAT))) {
							result = 1;
						} else {
						result = 0;
						}
					}
					else{
						return Integer.valueOf(prop.getProperty(valToCheck));
					}
					}
					catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
			
				return result;
			}

	@Override
	public List<ContractorDirectory> getContractorListByDomainId(Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		return contrctorDirectoryDAO.getContractorListByDomainId(domainId);
	}
}
