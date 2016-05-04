package com.elecnor.ecosystem.serviceimpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.BulkUploadDAO;
import com.elecnor.ecosystem.service.BulkUploadService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;
import com.elecnor.ecosystem.util.Utility;
import com.elecnor.ecosystem.util.ValidatorUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Service
public class BulkUploadServiceImpl implements BulkUploadService {
	@Autowired
    Utility util;
	@Autowired
	UploadFileUtility uploadUtility ;
	@Autowired
	BulkUploadDAO bulkUploadDAO;

	// set values for common methods during bulk upload through files
	public <E> E setCommonMethods(E element, UserDetail userDetail)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Method m = element.getClass().getMethod("setSubmittedBy",
				UserDetail.class);
		m.invoke(element, userDetail);
		Method m1 = element.getClass().getMethod("setStatus", String.class);
		m1.invoke(element, "ACTIVE");
		Method m2 = element.getClass()
				.getMethod("setSubmittedDate", Date.class);
		m2.invoke(element, new Date());
		Method m3 = element.getClass().getMethod("setDomainDetail",
				DomainDetail.class);
		m3.invoke(element, userDetail.getDomainDetail());

		return element;

	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String, Object>  saveFile(Workbook workBook,String className,UserDetail userDetail) throws JsonSyntaxException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException 
	{
		Sheet sheet = workBook.getSheetAt(ConstantUtil.SHEETNUM);
		Row headerRow = sheet.getRow(0);
		ArrayList<String> header = new ArrayList<String>();
		int j = 0;
		boolean isErrorOccured = false;
		ArrayList list = new ArrayList();
		ArrayList<ExcelErrorDetails> exceptionlist = new ArrayList<ExcelErrorDetails>();
		while (headerRow.getCell(j) != null) {
			header.add(headerRow.getCell(j).getStringCellValue());
			j++;
		}

		int loopCounter;
		String jsonString = "";
		//Since the first row contains hidden bean property values and second row contains column names in the excel sheet
		//The data starts from 3rd row i.e loopCounter=2
		for (loopCounter = 2; loopCounter <= sheet.getLastRowNum(); loopCounter++) {
			Row row = sheet.getRow(loopCounter);
			if (row == null) {
				continue;
			}

			HashMap<String, Object> rowContent = new HashMap<String, Object>();
			
			for (int k = 0; k < header.size(); k++) {
				String rowContents = "";
				if (row.getCell(k) != null) {
					rowContents = "" + row.getCell(k);
					rowContent.put(header.get(k), rowContents);
				} else
					rowContent.put(header.get(k), rowContents);
			}
			jsonString = util.getJsonResultWithoutExposeString(rowContent);
			Gson gson = new Gson();
			if(className.equalsIgnoreCase("SLicenseDirectory")||className.equalsIgnoreCase("LLicenseDirectory"))
			{
				className=className.substring(1, className.length());
			}
			String beanName = ConstantUtil.PACKAGE_NAME+className;
			Class projectType = Class.forName(beanName);
			
			if (!validateData(gson, jsonString, projectType,
					exceptionlist, isErrorOccured, loopCounter)) {
				list.add(setCommonMethods(
						gson.fromJson(jsonString, projectType), userDetail));
				
			}
			jsonString = "";
		 }
		if (exceptionlist.isEmpty())
			exceptionlist = null;
		bulkUploadDAO.saveBulkUpdate(list);
		System.out.println("the list is as follows");
		System.out.println(util.responseBuilder(exceptionlist));
		return util.responseBuilder(exceptionlist);
		}
		
		
		
		//return null;

	//}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean validateData(Gson gson, String jsonString,
			Class projectType, ArrayList<ExcelErrorDetails> exceptionlist,
			boolean isErrorOccured, int i) throws NoSuchMethodException,
			SecurityException {
		Set<ConstraintViolation<?>> constraints = null;
		System.out.println(gson.fromJson(jsonString,projectType).getClass());
		constraints = new ValidatorUtil().validate(gson.fromJson(jsonString,
				projectType));
		System.out.println("The total number of errors "+constraints);
		if (null != constraints) {
			for (final ConstraintViolation<?> violation : constraints) {
				ExcelErrorDetails error = new ExcelErrorDetails();
				error.setRowNumber(i);
				error.setErrorMessage(violation.getMessage());
				exceptionlist.add(error);
			}
			isErrorOccured = true;
			
		}
		return isErrorOccured;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> uploadFile(MultipartFile fileUploaded,
			HttpSession session, String className) throws Exception {
	
		

		UserDetail userDetail = (UserDetail) session
				.getAttribute("selectedUser");
		boolean isValidSchema;
		
		if (!fileUploaded.isEmpty()) {

			Workbook workBook = uploadUtility
					.readExcelFileFromMultipart(fileUploaded);
			if (workBook == null) {
				return uploadUtility
						.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
			}
			// Schema Validation - Checks Whether Row header name is same as we
			// specified.
			// Validate schema only when the document is uploaded and not when
			// user sends confirmation

			isValidSchema = uploadUtility.isSchemaValid(workBook,
					ConstantUtil.SHEETNUM,
					ConstantUtil.PROJECT_TYPE_HEADER_ROWNUM, className);
			if (!isValidSchema) {
				return uploadUtility
						.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);

			}
			// Schema Validation Ends
			// Check Validation For Confirmation from user-- Pending

			// Data Validation Starts
			return saveFile(workBook, className,userDetail) ;

		}
		return null;
	}

}
