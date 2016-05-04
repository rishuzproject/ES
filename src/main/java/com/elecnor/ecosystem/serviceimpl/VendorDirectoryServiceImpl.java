package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.VendorDirectory;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.dao.VendorDirectoryDAO;
import com.elecnor.ecosystem.helper.VendorDirectoryHelper;
import com.elecnor.ecosystem.service.VendorDirectoryService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;
import com.elecnor.ecosystem.util.Utility;

@Service
public class VendorDirectoryServiceImpl implements VendorDirectoryService {

	@Autowired
	VendorDirectoryDAO vendorDirectoryDAO;
	@Autowired
	AddressDetailsDAO addressDetailsDAO;

	@Override
	public String addVendor(VendorDirectory vendorDirectory, UserDetail userDetail, DomainDetail domainDetail)
			throws Exception {

		String result = "";
		try {
			if (!(vendorDirectory.getVendorId() == -1)) {
				vendorDirectory.setUpdatedBy(userDetail);
				vendorDirectory.setUpdatedDate(new Date());
				vendorDirectory.setDomainDetail(domainDetail);
				result = vendorDirectoryDAO.updateVendor(vendorDirectory);
			} else {
				vendorDirectory.setSubmittedBy(userDetail);
				vendorDirectory.setSubmittedDate(new Date());
//				vendorDirectory.setStatus("ACTIVE");
				vendorDirectory.setDomainDetail(domainDetail);
				result = vendorDirectoryDAO.addNewVendor(vendorDirectory);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
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
		ArrayList<VendorDirectory> vendorList = new ArrayList<VendorDirectory>();
		UploadFileUtility upUltil = new UploadFileUtility();
		VendorDirectoryHelper vendorDirectoryHelper = new VendorDirectoryHelper();

		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;

		try {
			UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");

			if (!fileUploaded.isEmpty()) {
				
				Workbook workBook = upUltil.readExcelFileFromMultipart(fileUploaded);
				if (workBook == null) {
					return upUltil.getErrorMessage(ConstantUtil.ERROR_FILE_READING_ERROR);
				}
				// Schema Validation - Checks Whether Row header name is same as we
				// specified.
				//Validate schema only when the document is uploaded and not when user sends confirmation 
				if(confirmUploadId != 1){
					isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.VENDOR_DIRECTORY_SHEETNUM,
							ConstantUtil.VENDOR_DIRECTORY_HEADER_ROWNUM, ConstantUtil.VENDOR_EXCEL_FORMAT);
					if (!isValidSchema) {
						return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
					}
				}
				// Schema Validation Ends
				// Check Validation For Confirmation from user-- Pending
				
				// Data Validation Starts
				Sheet sheet = workBook.getSheetAt(ConstantUtil.VENDOR_DIRECTORY_SHEETNUM);
				for (i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					Map<String, Object> rowValidationResult = new HashMap<String, Object>();
					//if (confirmUploadId != 1) {
						rowValidationResult = vendorDirectoryHelper.validateRowDataAndFetchBean(row, userDetail);
						if (rowValidationResult.get("vendorDirectoryBean") == null) {
							errorList
									.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
							hasErrorOccured = true;
						} else {
							vendorList.add((VendorDirectory) rowValidationResult.get("vendorDirectoryBean"));
						}
					//}
				}
				if ((confirmUploadId == 1) || (confirmUploadId != 1 && !hasErrorOccured)) {
					errorList = saveVendorList(vendorList);
					if (errorList.isEmpty()) {
						errorList = null;
					}
					return util.responseBuilder(errorList);

				} else {
					
					return util.responseBuilder(errorList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} 
		return resultMap;
	}

	// Remove this and call add vendor method
	public ArrayList<ExcelErrorDetails> saveVendorList(List<VendorDirectory> vendorList) {
		String daoResult = null;
		UploadFileUtility upUltil = new UploadFileUtility();
		int i = 1;
		ArrayList<ExcelErrorDetails> daoErrorList = new ArrayList<ExcelErrorDetails>();
		for (VendorDirectory vendorDirectoryObj : vendorList) {
			daoResult = null;
			try {
				daoResult = vendorDirectoryDAO.addNewVendor(vendorDirectoryObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (daoResult != null) {
				daoErrorList.add(upUltil.getExcelErrorDetails(i, -1, "Cannot Save this row number"));
			}
			i++;
		}
		return daoErrorList;
	}

	@Override
	public List<VendorDirectory> getVendorListByDomainId(Long domainId) throws Exception{
		// TODO Auto-generated method stub
		return vendorDirectoryDAO.getVendorListByDomainId(domainId);
	}

}