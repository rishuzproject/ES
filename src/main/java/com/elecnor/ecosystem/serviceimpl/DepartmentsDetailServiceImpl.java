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

import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.DepartmentUserMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DepartmentUserMappingDAO;
import com.elecnor.ecosystem.dao.DepartmentsDetailDAO;
import com.elecnor.ecosystem.helper.DepartmentTypeHelper;
import com.elecnor.ecosystem.service.DepartmentUserMappingService;
import com.elecnor.ecosystem.service.DepartmentsDetailService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.ExcelErrorDetails;
import com.elecnor.ecosystem.util.UploadFileUtility;
import com.elecnor.ecosystem.util.Utility;

@Service
public class DepartmentsDetailServiceImpl implements DepartmentsDetailService {

	@Autowired
	DepartmentsDetailDAO departmentsDetailDAO;

	@Autowired
	DepartmentUserMappingDAO departmentUserMappingDAO;

	@Autowired
	DepartmentUserMappingService departmentUserMappingService;

	@Override
	public String saveSelectedUsers(int[] arrayList, ArrayList<UserDetail> usersList, DepartmentType departmentId,
			UserDetail loggedInUser, String userType) throws Exception{
		String result = null;
		try {
			int count = 0;
			for (UserDetail user : usersList) {
				if (count < arrayList.length) {
					System.out.println(user.getUserId().intValue() == arrayList[count]);
					if (user.getUserId().intValue() == arrayList[count]) {
						DepartmentUserMapping departmentUserMapping = new DepartmentUserMapping();
						departmentUserMapping.setDomainDetail(loggedInUser.getDomainDetail());
						departmentUserMappingService.addDepartmentUserMapping(user, departmentId,
								departmentUserMapping, loggedInUser, userType);
						count++;
					}

				} else {
					break;
				}
			}
		} catch (Exception e) {
			result = e.getMessage();
			throw e;
		}
		return result;
	}

	@Override
	public DepartmentType setAddUpdateDepartmentDetailService(long departmentId, DepartmentType departmentForm, UserDetail userDetail,
			DomainDetail domainDetail) throws Exception {
		
		try {
			if (departmentId == -1) {
				departmentForm.setDepartmentId(departmentId);
				DepartmentType result = null;
				departmentForm.setStatus("ACTIVE");
				departmentForm.setDomainDetail(domainDetail);
				departmentForm.setSubmittedDate(new Date());
				departmentForm.setSubmittedBy(userDetail);
				result = departmentsDetailDAO.setAddDepartmentDetails(departmentForm);
				return result;
			} else {
				departmentForm.setDomainDetail(domainDetail);
				departmentForm.setUpdatedDate(new Date());
				departmentForm.setUpdatedBy(userDetail);
				departmentUserMappingDAO.deleteUserAssignmentstoDept(departmentForm.getDepartmentId().intValue());
				departmentsDetailDAO.updateDepartmentDetails(departmentForm);
				ArrayList<DepartmentType> departments = departmentsDetailDAO.getAllDepartments();
				for (DepartmentType result : departments) {
					if (result.getDepartmentId().intValue() == departmentForm.getDepartmentId().intValue()) {
						return result;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> uploadDepartmentFile(MultipartFile fileUploaded, HttpSession session,
			int confirmUploadId) throws Exception {
		// confirmUploadId- Default value -1, it implies that Excel is being for
		// the first time, If 1 It means user accepts that even if problem
		// exists in present excel, continue with Save operation.

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<ExcelErrorDetails> errorList = new ArrayList<ExcelErrorDetails>();
		ArrayList<DepartmentType> departmentList = new ArrayList<DepartmentType>();

		Utility util = new Utility();
		UploadFileUtility upUltil = new UploadFileUtility();
		DepartmentTypeHelper departmentTypeHelper = new DepartmentTypeHelper();

		boolean hasErrorOccured = false;
		boolean isValidSchema;
		int i;

		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");

		try {
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
					isValidSchema = upUltil.isSchemaValid(workBook, ConstantUtil.DEPARTMENT_TYPE_SHEETNUM,
							ConstantUtil.DEPARTMENT_TYPE_HEADER_ROWNUM, ConstantUtil.DEPARTMENT_TYPE_EXCEL_FORMAT);
					if (!isValidSchema) {
						return upUltil.getErrorMessage(ConstantUtil.ERROR_HEADER_VALIDATION_ERROR);
					}
				}
				// Schema Validation Ends
				// Check Validation For Confirmation from user-- Pending

				// Data Validation Starts
				Sheet sheet = workBook.getSheetAt(ConstantUtil.DEPARTMENT_TYPE_SHEETNUM);
				for (i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					Map<String, Object> rowValidationResult = new HashMap<String, Object>();
					// if (confirmUploadId != 1) {
					rowValidationResult = departmentTypeHelper.validateRowDataAndFetchBean(row, userDetail);
					if (rowValidationResult.get("departmentTypeBean") == null) {
						errorList.addAll((Collection<? extends ExcelErrorDetails>) rowValidationResult.get("errorList"));
						hasErrorOccured = true;
					} else {
						departmentList.add((DepartmentType) rowValidationResult.get("departmentTypeBean"));
					}
					// }
				}
				if ((confirmUploadId == 1) || (confirmUploadId != 1 && !hasErrorOccured)) {
					errorList = saveDepartmentList(departmentList);
					if (errorList.isEmpty()) {
						errorList = null;
					}
					return util.responseBuilder(errorList);

				} else {

					return util.responseBuilder(errorList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultMap;
	}

	public ArrayList<ExcelErrorDetails> saveDepartmentList(List<DepartmentType> departmentList) throws Exception{

		DepartmentType deptDAOResult = null;
		UploadFileUtility upUltil = new UploadFileUtility();
		int i = 1;
		ArrayList<ExcelErrorDetails> deptDAOErrorList = new ArrayList<ExcelErrorDetails>();
		try {
			for (DepartmentType departmentTypeObj : departmentList) {
				deptDAOResult = null;
					deptDAOResult = departmentsDetailDAO.setAddDepartmentDetails(departmentTypeObj);
				if (deptDAOResult != null) {
					deptDAOErrorList.add(upUltil.getExcelErrorDetails(i, -1, "Error in saving to database"));
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return deptDAOErrorList;
	}
	
	@Override
	public String saveTypeOfUser(DepartmentType deptType, DomainDetail domainDetail, String selectedUsersId,
			DepartmentType result2, UserDetail userDetail, String selectedStaffId) throws Exception{
		
		String result = null;
		try {
			if (deptType != null) {
				if ((selectedUsersId != null && selectedUsersId != "")) {
					result = departmentUserMappingService.saveUserType(domainDetail, selectedUsersId, deptType,
							userDetail, ConstantUtil.DEPARTMENT_HEAD);
				}
				if (selectedStaffId != null && selectedStaffId != "") {
					result = departmentUserMappingService.saveUserType(domainDetail, selectedStaffId, deptType,
							userDetail, ConstantUtil.DEPARTMENT_STAFF);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
