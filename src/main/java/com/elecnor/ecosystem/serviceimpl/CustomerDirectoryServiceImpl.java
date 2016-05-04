package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.dao.CustomerDirectoryDAO;
import com.elecnor.ecosystem.helper.CustomerDirectoryHelper;
import com.elecnor.ecosystem.service.CustomerDirectoryService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;
import com.elecnor.ecosystem.util.Utility;

/**
 * @author Harsh Verma
 */

@Service
public class CustomerDirectoryServiceImpl implements CustomerDirectoryService {

	@Autowired
	CustomerDirectoryDAO customerDetailsDAO;
	@Autowired
	AddressDetailsDAO addressDetailsDAO;
	ArrayList<String> errorReturnList = new ArrayList<String>();

	// ExcelValidator validate = new ExcelValidator();

	@Override
	public CustomerDirectory saveOrUpdateCustomer(CustomerDirectory customerListForm, UserDetail userDetails) throws Exception {

		DomainDetail domainDetailsBean = userDetails.getDomainDetail();
		CustomerDirectory customer = null;
		customerListForm.setDomainDetail(domainDetailsBean);
		customerListForm.setStatus("ACTIVE");
		if (customerListForm.getCompanyId() == null) {
			customerListForm.setSubmittedDate(new Date());
			customerListForm.setSubmittedBy(userDetails);
			customer = customerDetailsDAO.saveCustomer(customerListForm);
		} else {
			customerListForm.setUpdatedDate(new Date());
			customerListForm.setUpdatedBy(userDetails);
			customer = customerDetailsDAO.saveCustomer(customerListForm);
		}

		return customer;
	}
	
	@Override
	public String deleteCustomer(HttpServletRequest request, UserDetail userDetails) throws Exception {
		int compId = 0;
		String result = "";
		try {
			if (request.getParameter("compIdToDel") != null
					&& request.getParameter("compIdToDel") != "") {
				compId = Integer.parseInt(request.getParameter("compIdToDel").trim());
				result = customerDetailsDAO.setCustomerDelete(compId);
				result = addressDetailsDAO.setDeleteAddressByUser(compId,userDetails);
			} else {
				result = "Cannot Find The Company To Delete. Please refresh the page and try again.";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> uploadFile(MultipartFile fileUploaded, HttpSession session, int confirmUploadId)
			throws Exception {
		// confirmUploadId- Default value -1, it implies that Excel is being for
		// the first time, If 1 It means user accepts that even if problem
		// exists in present excel, continue with Save operation.
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Utility util = new Utility();
		ArrayList<ExcelErrorDetails> errorList = new ArrayList<ExcelErrorDetails>();
		ArrayList<CustomerDirectory> customerList = new ArrayList<CustomerDirectory>();
		UploadFileUtility upUltil = new UploadFileUtility();
		CustomerDirectoryHelper customerDirectoryHelper = new CustomerDirectoryHelper();

		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;

		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");

		if (!fileUploaded.isEmpty()) {
			Workbook workBook = upUltil.readExcelFileFromMultipart(fileUploaded);
			if (workBook == null) {
				return upUltil.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
			}
			// Schema Validation - Checks Whether Row header name is same as we
			// specified.
			// Validate schema only when the document is uploaded and not when
			// user sends confirmation
			if (confirmUploadId != 1) {
				isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.CUSTOMER_DIRECTORY_SHEETNUM,
						ConstantUtil.CUSTOMER_DIRECTORY_HEADER_ROWNUM, ConstantUtil.CUSTOMER_EXCEL_FORMAT);
				if (!isValidSchema) {
					return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
				}
			}
			// Schema Validation Ends
			// Check Validation For Confirmation from user-- Pending

			// Data Validation Starts
			Sheet sheet = workBook.getSheetAt(ConstantUtil.CUSTOMER_DIRECTORY_SHEETNUM);
			for (i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				Map<String, Object> rowValidationResult = new HashMap<String, Object>();
				// if (confirmUploadId != 1) {
				rowValidationResult = customerDirectoryHelper.validateRowDataAndFetchBean(row, userDetail);
				if (rowValidationResult.get("customerDirectoryBean") == null) {
					errorList.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
					hasErrorOccured = true;
				} else {
					customerList.add((CustomerDirectory) rowValidationResult.get("customerDirectoryBean"));
				}
				// }
			}
			if ((confirmUploadId == 1) || (confirmUploadId != 1 && !hasErrorOccured)) {
				errorList = saveCustomerList(customerList);
				if (errorList.isEmpty()) {
					errorList = null;
				}
				return util.responseBuilder(errorList);

			} else {
				
				return util.responseBuilder(errorList);
			}
		} 
		return resultMap;
	}

	// Validate a row of data; If There is an error it return ExcelErrorClass
	// Object Else Null

	public ArrayList<ExcelErrorDetails> saveCustomerList(List<CustomerDirectory> customerList) throws Exception {

		CustomerDirectory customerDAOResult = null;
		UploadFileUtility upUltil = new UploadFileUtility();
		int i = 1;
		ArrayList<ExcelErrorDetails> customerDAOErrorList = new ArrayList<ExcelErrorDetails>();
		for (CustomerDirectory customerDirectoryObj : customerList) {
			customerDAOResult = null;
			try {
				customerDAOResult = customerDetailsDAO.saveCustomer(customerDirectoryObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (customerDAOResult == null) {
				customerDAOErrorList.add(upUltil.getExcelErrorDetails(i, -1, "Error in saving to database"));
			}
			i++;
		}
		return customerDAOErrorList;
	}

	@Override
	public List<CustomerDirectory> getCustomerListByDomainId(Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		return customerDetailsDAO.getCustomerListByDomainId(domainId);
	}
}
