package com.elecnor.ecosystem.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileUtility {

	// Comments
	public Workbook readExcelFileFromMultipart(MultipartFile fileUploaded) {
		Workbook workBook = null;
		try {
			workBook = new XSSFWorkbook(fileUploaded.getInputStream());
		} catch (Exception e) {
			try {
				workBook = new HSSFWorkbook(fileUploaded.getInputStream());
			} catch (Exception e1) {
			}
		}
		return workBook;
	}

	// Comments
	public ExcelErrorDetails getExcelErrorDetails(int rowNum, int colNum, String msg) {
		ExcelErrorDetails e = new ExcelErrorDetails();
		e.setRowNumber(rowNum + 1);
		e.setColNumber(colNum);
		e.setErrorMessage(msg);
		if (colNum != -1) {
			e.setExcelCell(ConstantUtil.columnMapping[colNum] + (rowNum + 1));
		}
		return e;
	}

	public ExcelErrorDetails getExcelErrorDetails(int rowNum, String msg) {
		ExcelErrorDetails e = new ExcelErrorDetails();
		e.setRowNumber(rowNum + 1);
		e.setErrorMessage(msg);
		return e;
	}

	// Comment
	public HashMap<String, Object> getErrorMessage(String errorMessage) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ArrayList<ExcelErrorDetails> errorList = new ArrayList<ExcelErrorDetails>();
			errorList.add(getExcelErrorDetails(0, 0, errorMessage));
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", errorList);
		} catch (Exception e) {
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", "Error Occured !!");
		}
		return resultMap;
	}

	// comment
	/*
	 * 1- Input Parameters-sheet number , header row number strings 2-
	 * Logic-Using parameters, read sheet number and header row number of the
	 * workbook 3- Expected Output-
	 */
	public boolean isSchemaValid(Workbook workBook, int sheetNumber, String headerRowNumberKey,
			String headerSchemaFormatKey) throws Exception {
		try {
			HeaderPropertyFileReader instance = HeaderPropertyFileReader.getInstance();
			int j = 0;
			boolean isValidSchema = true;
			int headerRowNum = instance.getIntProperty(headerRowNumberKey);
			String valueFromHeaderPropertyFile = instance.getStringProperty(headerSchemaFormatKey);
			String valueFromHeaderPropertyFileArray[] = valueFromHeaderPropertyFile.split(",");
			// Strings for reading from property file
			// Values obtained from property file
			Sheet sheet = workBook.getSheetAt(sheetNumber);
			Row rowHeader = sheet.getRow(headerRowNum);

			while (rowHeader.getCell(j) != null) {
				if (!rowHeader.getCell(j).getStringCellValue().trim()
						.equalsIgnoreCase(valueFromHeaderPropertyFileArray[j].trim())) {
					isValidSchema = false;
					break;
				}
				j++;
			}
			return isValidSchema;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ExcelErrorDetails getExcelErrorDetailsBasedOnBeanValidations(int rowNum, Object object) {
		String msg = "";
		Validator validator = ValidationUtility.initialize();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
		for (Iterator<?> iterator = constraintViolations.iterator(); iterator.hasNext();) {
			ConstraintViolation<?> constraintViolation = (ConstraintViolation<?>) iterator.next();
			msg = msg+constraintViolation.getMessage()+", ";
		}
		if (msg.length() > 0) {
			return getExcelErrorDetails(rowNum, msg);
		}
		return null;
	}
}
