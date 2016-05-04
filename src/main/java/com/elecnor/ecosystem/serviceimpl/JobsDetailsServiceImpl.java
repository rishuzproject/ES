/**
 * 
 */
package com.elecnor.ecosystem.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.net.URLCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRolesMapping;
import com.elecnor.ecosystem.dao.ContractorDirectoryDAO;
import com.elecnor.ecosystem.dao.CustomerDirectoryDAO;
import com.elecnor.ecosystem.dao.DepartmentDirectoryDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.dao.TypesOfProjectDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.UserRolesMappingDAO;
import com.elecnor.ecosystem.dao.VendorDirectoryDAO;
import com.elecnor.ecosystem.service.JobsDetailService;
import com.elecnor.ecosystem.util.Utility;

/**
 * @author Ashutosh
 */
@Service
public class JobsDetailsServiceImpl implements JobsDetailService {

	@Autowired
	JobsDetailDAO jobsDetailDao;

	@Autowired
	CustomerDirectoryDAO customerDirectoryDAO;

	@Autowired
	UserDetailDAO userDetailDAO;

	@Autowired
	UserRolesMappingDAO userRolesMappingDAO;

	@Autowired
	TypesOfProjectDAO typeProjectDAO;
	@Autowired
	VendorDirectoryDAO vendorDirectoryDAO;
	@Autowired
	ContractorDirectoryDAO contractorDirectoryDAO;
	@Autowired
	DepartmentDirectoryDAO departmentsDetailDAO;

	@Override
	public JobDetail saveJobDetails(Long domainDetailId, JobDetail jobDetail, Long jobCustomer, Long jobType, Long jobForeman, Long jobManager, Long jobSupervisor, Long jobExecutive,
			Long jobPurchasingAgent, Long vendorsList, Integer jobDepartmentType, Long contractor, String status, DomainDetail domailDetail, long jobId, String sovType, Long jobAccountant,
			Long jobWarehouseManager, Long jobSuperindent, String jobEstimator, UserDetail userDetails, HttpServletRequest request) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		if (request.getParameter("architectId") != null && request.getParameter("architectId") != "") {
			jobDetail.setArchitectId(Long.parseLong(request.getParameter("architectId")));
		}
		if (request.getParameter("activationValidityTimePeriod") != null && request.getParameter("activationValidityTimePeriod") != "") {
			jobDetail.setActivationValidityTimePeriod(Integer.parseInt(request.getParameter("activationValidityTimePeriod")));
		}
		if (request.getParameter("autoApprovalLimit") != null && request.getParameter("autoApprovalLimit") != "") {
			jobDetail.setAutoApprovalLimit(Double.parseDouble(request.getParameter("autoApprovalLimit")));
		}
		if (request.getParameter("bidPrice") != null && request.getParameter("bidPrice") != "") {
			jobDetail.setBidPrice(Double.parseDouble(request.getParameter("bidPrice").replace(",", "")));
		}
		if (request.getParameter("coDate") != null && request.getParameter("coDate") != "") {
			jobDetail.setCoDate(formatter.parse(request.getParameter("coDate")));
		}
		if (request.getParameter("collectionDayOut") != null && request.getParameter("collectionDayOut") != "") {
			jobDetail.setCollectionDayOut(Integer.parseInt(request.getParameter("collectionDayOut")));
		}
		if (request.getParameter("description") != null && request.getParameter("description") != "") {
			jobDetail.setDescription(request.getParameter("description"));
		}
		if (request.getParameter("directJobDayOut") != null && request.getParameter("directJobDayOut") != "") {
			jobDetail.setDirectJobDayOut(Integer.parseInt(request.getParameter("directJobDayOut")));
		}
		if (request.getParameter("endDate") != null && request.getParameter("endDate") != "") {
			jobDetail.setEndDate(formatter.parse(request.getParameter("endDate")));
		}
		if (request.getParameter("extendedDate") != null && request.getParameter("extendedDate") != "") {
			jobDetail.setExtendedDate(formatter.parse(request.getParameter("extendedDate")));
		}
		if (request.getParameter("startDate") != null && request.getParameter("startDate") != "") {
			jobDetail.setStartDate(formatter.parse(request.getParameter("startDate")));
		}
		if (request.getParameter("indirectDayOut") != null && request.getParameter("indirectDayOut") != "") {
			jobDetail.setIndirectDayOut(Integer.parseInt(request.getParameter("indirectDayOut")));
		}
		if (request.getParameter("jobName") != null && request.getParameter("jobName") != "") {
			jobDetail.setJobName(request.getParameter("jobName"));
		}
		if (request.getParameter("jobNumber") != null && request.getParameter("jobNumber") != "") {
			jobDetail.setJobNumber(request.getParameter("jobNumber"));
		} else {
			jobDetail.setStatus("PENDING");
		}
		if (request.getParameter("bidNumber") != null && request.getParameter("bidNumber") != "") {
			jobDetail.setBidNumber(request.getParameter("bidNumber"));
		}
		/*
		 * if (request.getParameter("jobAddress") != null &&
		 * request.getParameter("jobAddress") != "") {
		 * jobDetail.setJobAddress(request.getParameter("jobAddress")); }
		 */
		if (request.getParameter("laborBonusTargetOverBudget") != null && request.getParameter("laborBonusTargetOverBudget") != "") {
			jobDetail.setLaborBonusTargetOverBudget(Float.parseFloat(request.getParameter("laborBonusTargetOverBudget")));
		}
		if (request.getParameter("laborDayOut") != null && request.getParameter("laborDayOut") != "") {
			jobDetail.setLaborDayOut(Integer.parseInt(request.getParameter("laborDayOut")));
		}
		if (request.getParameter("materialDayOut") != null && request.getParameter("materialDayOut") != "") {
			jobDetail.setMaterialDayOut(Integer.parseInt(request.getParameter("materialDayOut")));
		}
		if (request.getParameter("originalContractValue") != null && request.getParameter("originalContractValue") != "") {
			jobDetail.setOriginalContractValue(Double.parseDouble(request.getParameter("originalContractValue")));
		}
		if (request.getParameter("ownedEquipmentDayOut") != null && request.getParameter("ownedEquipmentDayOut") != "") {
			jobDetail.setOwnedEquipmentDayOut(Integer.parseInt(request.getParameter("ownedEquipmentDayOut")));
		}
		if (request.getParameter("performanceTargetMargin") != null && request.getParameter("performanceTargetMargin") != "") {
			jobDetail.setPerformanceTargetMargin(Float.parseFloat(request.getParameter("performanceTargetMargin")));
		}
		if (request.getParameter("projAdminDayOut") != null && request.getParameter("projAdminDayOut") != "") {
			jobDetail.setProjAdminDayOut(Integer.parseInt(request.getParameter("projAdminDayOut")));
		}
		if (request.getParameter("rentalEquipmentDayOut") != null && request.getParameter("rentalEquipmentDayOut") != "") {
			jobDetail.setRentalEquipmentDayOut(Integer.parseInt(request.getParameter("rentalEquipmentDayOut")));
		}
		if (request.getParameter("reportMargin") != null && request.getParameter("reportMargin") != "") {
			jobDetail.setReportMargin(Float.parseFloat(request.getParameter("reportMargin")));
		}
		if (request.getParameter("retentionDayOut") != null && request.getParameter("retentionDayOut") != "") {
			jobDetail.setRetentionDayOut(Integer.parseInt(request.getParameter("retentionDayOut")));
		}
		if (request.getParameter("retentionPercent") != null && request.getParameter("retentionPercent") != "") {
			jobDetail.setRetentionPercent(Float.parseFloat(request.getParameter("retentionPercent")));
		}
		if (request.getParameter("subcontractorDayOut") != null && request.getParameter("subcontractorDayOut") != "") {
			jobDetail.setSubcontractorDayOut(Integer.parseInt(request.getParameter("subcontractorDayOut")));
		}
		if (request.getParameter("insuranceSentDate") != null && request.getParameter("insuranceSentDate") != "") {
			jobDetail.setInsuranceSentDate(formatter.parse(request.getParameter("insuranceSentDate")));
		}
		if (request.getParameter("performanceSentDate") != null && request.getParameter("performanceSentDate") != "") {
			jobDetail.setPerformanceSentDate(formatter.parse(request.getParameter("performanceSentDate")));
		}
		if (request.getParameter("cpnSentDate") != null && request.getParameter("cpnSentDate") != "") {
			jobDetail.setCpnSentDate(formatter.parse(request.getParameter("cpnSentDate")));
		}
		if (request.getParameter("isCertifiedPayroll") != null && request.getParameter("isCertifiedPayroll") != "") {
			jobDetail.setIsCertifiedPayroll(true);
		} else {
			jobDetail.setIsCertifiedPayroll(false);
		}
		if (request.getParameter("ownerControlledInsuranceProg") != null && request.getParameter("ownerControlledInsuranceProg") != "") {
			jobDetail.setOwnerControlledInsuranceProg(true);
		} else {
			jobDetail.setOwnerControlledInsuranceProg(false);
		}
		if (request.getParameter("noticeReceivedDate") != null && request.getParameter("noticeReceivedDate") != "") {
			jobDetail.setNoticeReceivedDate(formatter.parse(request.getParameter("noticeReceivedDate")));
		}
		if (request.getParameter("typeOfContract") != null && request.getParameter("typeOfContract") != "") {
			jobDetail.setTypeOfContract(request.getParameter("typeOfContract"));
		}
		if (request.getParameter("otherTypeOfContract") != null && request.getParameter("otherTypeOfContract") != "") {
			jobDetail.setOtherTypeOfContract(request.getParameter("otherTypeOfContract"));
		}
		if (request.getParameter("contractAmount") != null && request.getParameter("contractAmount") != "") {
			jobDetail.setContractAmount(Float.parseFloat(request.getParameter("contractAmount").replace(",", "")));
		}
		if (request.getParameter("contractNumber") != null && request.getParameter("contractNumber") != "") {
			jobDetail.setContractNumber(Long.parseLong(request.getParameter("contractNumber")));
		}
		if (request.getParameter("bidBudgetMaterialCost") != null && request.getParameter("bidBudgetMaterialCost") != "") {
			jobDetail.setBidBudgetMaterialCost(Float.parseFloat(request.getParameter("bidBudgetMaterialCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetSubcontractorsCost") != null && request.getParameter("bidBudgetSubcontractorsCost") != "") {
			jobDetail.setBidBudgetSubcontractorsCost(Float.parseFloat(request.getParameter("bidBudgetSubcontractorsCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetDirectJobCosts") != null && request.getParameter("bidBudgetDirectJobCosts") != "") {
			jobDetail.setBidBudgetDirectJobCosts(Float.parseFloat(request.getParameter("bidBudgetDirectJobCosts").replace(",", "")));
		}
		if (request.getParameter("bidBudgetRentalEquipmentCosts") != null && request.getParameter("bidBudgetRentalEquipmentCosts") != "") {
			jobDetail.setBidBudgetRentalEquipmentCosts(Float.parseFloat(request.getParameter("bidBudgetRentalEquipmentCosts").replace(",", "")));
		}
		if (request.getParameter("bidBudgetOwnedEquipmentsCosts") != null && request.getParameter("bidBudgetOwnedEquipmentsCosts") != "") {
			jobDetail.setBidBudgetOwnedEquipmentsCosts(Float.parseFloat(request.getParameter("bidBudgetOwnedEquipmentsCosts").replace(",", "")));
		}
		if (request.getParameter("bidBudgetProjectAdministrationCost") != null && request.getParameter("bidBudgetProjectAdministrationCost") != "") {
			jobDetail.setBidBudgetProjectAdministrationCost(Float.parseFloat(request.getParameter("bidBudgetProjectAdministrationCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetLaborCost") != null && request.getParameter("bidBudgetLaborCost") != "") {
			jobDetail.setBidBudgetLaborCost(Float.parseFloat(request.getParameter("bidBudgetLaborCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetIndirectExpenses") != null && request.getParameter("bidBudgetIndirectExpenses") != "") {
			jobDetail.setBidBudgetIndirectExpenses(Float.parseFloat(request.getParameter("bidBudgetIndirectExpenses").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetMaterialCosts") != null && request.getParameter("operationsBudgetMaterialCosts") != "") {
			jobDetail.setOperationsBudgetMaterialCosts(Float.parseFloat(request.getParameter("operationsBudgetMaterialCosts").replace(",", "")));
		}
		if (request.getParameter("operationsSubcontractorsCosts") != null && request.getParameter("operationsSubcontractorsCosts") != "") {
			jobDetail.setOperationsSubcontractorsCosts(Float.parseFloat(request.getParameter("operationsSubcontractorsCosts").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetDirectJobCost") != null && request.getParameter("operationsBudgetDirectJobCost") != "") {
			jobDetail.setOperationsBudgetDirectJobCost(Float.parseFloat(request.getParameter("operationsBudgetDirectJobCost").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetRentalEquipmentCost") != null && request.getParameter("operationsBudgetRentalEquipmentCost") != "") {
			jobDetail.setOperationsBudgetRentalEquipmentCost(Float.parseFloat(request.getParameter("operationsBudgetRentalEquipmentCost").replace(",", "")));
		}
		if (request.getParameter("operationsOwnedEquipmentCost") != null && request.getParameter("operationsOwnedEquipmentCost") != "") {
			jobDetail.setOperationsOwnedEquipmentCost(Float.parseFloat(request.getParameter("operationsOwnedEquipmentCost").replace(",", "")));
		}
		if (request.getParameter("operationsProjectAdministrationCost") != null && request.getParameter("operationsProjectAdministrationCost") != "") {
			jobDetail.setOperationsProjectAdministrationCost(Float.parseFloat(request.getParameter("operationsProjectAdministrationCost").replace(",", "")));
		}
		if (request.getParameter("operationsIndirectExpenses") != null && request.getParameter("operationsIndirectExpenses") != "") {
			jobDetail.setOperationsIndirectExpenses(Float.parseFloat(request.getParameter("operationsIndirectExpenses").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetLaborCost") != null && request.getParameter("operationsBudgetLaborCost") != "") {
			jobDetail.setOperationsBudgetLaborCost(Float.parseFloat(request.getParameter("operationsBudgetLaborCost").replace(",", "")));
		}

		ArrayList<UserDetail> userDetailList = userDetailDAO.getAllUserDetails(domainDetailId);
		// jobDetail.setDomainDetail(domailDetail);
		jobDetail.setSubmittedDate(new Date());
		// jobDetail.setStatus("ACTIVE");
		if (jobCustomer > 0) {
			jobDetail.setCustomerDirectory(customerDirectoryDAO.getCustomerById(jobCustomer));
		} else {
			jobDetail.setCustomerDirectory(null);
		}
		if (jobType > 0) {
			jobDetail.setProjectTypeBean(typeProjectDAO.getProjectById(jobType));
		} else {
			jobDetail.setProjectTypeBean(null);
		}
		if (vendorsList > 0) {
			jobDetail.setVendorList(vendorDirectoryDAO.getVendorsById(vendorsList));
		} else {
			jobDetail.setVendorList(null);
		}
		if (jobDepartmentType > 0) {
			jobDetail.setDepartmentType(departmentsDetailDAO.getDeptDirectoryById(jobDepartmentType));
		} else {
			jobDetail.setDepartmentType(null);
		}
		if (contractor > 0) {
			jobDetail.setContractorDirectory(contractorDirectoryDAO.getContractorDetailByID(contractor));
		} else {
			jobDetail.setContractorDirectory(null);
		}
		if (jobForeman > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobForeman) {
					jobDetail.setForeman(obj);
					break;
				}
			}
		} else {
			jobDetail.setForeman(null);
		}

		if (jobManager > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobManager) {
					jobDetail.setManager(obj);
					break;
				}
			}
		} else {
			jobDetail.setManager(null);
		}

		if (jobSupervisor > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobSupervisor) {
					jobDetail.setSupervisor(obj);
					break;
				}
			}
		} else {
			jobDetail.setSupervisor(null);
		}
		if (jobExecutive > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobExecutive) {
					jobDetail.setExecutive(obj);
					break;
				}
			}
		} else {
			jobDetail.setExecutive(null);
		}
		if (jobPurchasingAgent > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobPurchasingAgent) {
					jobDetail.setPurchasingAgent(obj);
					break;
				}
			}
		} else {
			jobDetail.setPurchasingAgent(null);
		}
		if (jobAccountant > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobAccountant) {
					jobDetail.setAccountant(obj);
					break;
				}
			}
		} else {
			jobDetail.setAccountant(null);
		}

		if (jobWarehouseManager > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobWarehouseManager) {
					jobDetail.setWarehouseManager(obj);
					break;
				}
			}
		} else {
			jobDetail.setWarehouseManager(null);
		}
		if (jobSuperindent > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobSuperindent) {
					jobDetail.setSuperindent(obj);
					break;
				}
			}
		} else {
			jobDetail.setSuperindent(null);
		}
		if (jobEstimator != "") {
			jobDetail.setEstimator(jobEstimator);
			for (UserDetail obj : userDetailList) {
				if (obj.getFirstName() == jobEstimator) {
					break;
				}
			}
		} else {
			jobDetail.setEstimator(null);
		}
		String res = null;
		jobDetail.setSovType(sovType);
		JobDetail savedJobDetails = null;
		try {
			savedJobDetails = jobsDetailDao.saveJob(jobDetail);
			// the following email sent is incomplete because department head
			// dao call is not implemented
			sendNotificationToDepartmentHead(jobDetail, userDetails);
			// if(savedJobDetails != null){
			// String apiResponse =
			// util.addCashflowManpowerForProject(savedJobDetails.getJobId(),
			// util.formatDate2USStandard(savedJobDetails.getStartDate()),
			// util.formatDate2USStandard(savedJobDetails.getEndDate()),
			// util.formatDate2USStandard(savedJobDetails.getExtendedDate()),
			// util.formatDate2USStandard(savedJobDetails.getCoDate()),
			// savedJobDetails.getSubmittedBy().getUserId(), "save");
			// if(!"success".equalsIgnoreCase(apiResponse)){
			// jobsDao.deleteJobDatailsWhenExceptionInAPICall(savedJobDetails.getJobId());
			// res = apiResponse;
			// }
			// }
		} catch (Exception e) {
			res = e.getMessage();
			throw e;
		}

		return savedJobDetails;
	}

	// public HashMap<String, Object> uploadJobFile(MultipartFile fileUploaded,
	// HttpSession session,
	// int confirmJobUploadId) throws Exception{
	//
	// HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// ArrayList<ExcelErrorDetails> errorList = new
	// ArrayList<ExcelErrorDetails>();
	// ArrayList<JobDetail> jobList = new ArrayList<JobDetail>();
	// UploadFileUtility upUtil=new UploadFileUtility();
	// boolean hasErrorOccured = false;
	// int isValidSchema = 1;
	// int i;
	// int sheetStart;
	// //here
	// String stringSheetStart = "JOB_TYPE_SHEETNUM";
	//
	// UserDetail userDetail = (UserDetail)
	// session.getAttribute("selectedUser");
	// DomainDetail domainDetail = userDetail.getDomainDetail();
	//
	// if (!fileUploaded.isEmpty()) {
	// //Workbook workBook=upUtil.readExcelFile(fileUploaded);
	// Workbook workBook = null;
	// try {
	// workBook = new XSSFWorkbook(fileUploaded.getInputStream());
	// } catch (Exception e) {
	// try {
	// workBook = new HSSFWorkbook(fileUploaded.getInputStream());
	// } catch (IOException e1) {
	// }
	// }
	// if(workBook == null){
	// errorList.add(getExcelErrorDetails(0, 0,
	// "Wrong File Type"));
	// resultMap.put("ajaxResult", "error");
	// resultMap.put("reason", errorList);
	// return resultMap;
	// }
	// // Schema Validation - Checks Whether Row header name is same as we
	// // specified.
	// isValidSchema = getSchemaValidation(workBook);
	// if (isValidSchema != 1) {
	// hasErrorOccured = true;
	// errorList.add(getExcelErrorDetails(0, 0,
	// "Header Validation Failed"));
	// resultMap.put("ajaxResult", "error");
	// resultMap.put("reason", errorList);
	// return resultMap;
	// }
	//
	// // Schema Validation Ends
	//
	// // Data Validation Starts
	// sheetStart = readFromHeader(null,stringSheetStart);
	// Sheet sheet = workBook.getSheetAt(sheetStart);
	// for (i=1;i<=sheet.getLastRowNum();i++) {
	// Row row = sheet.getRow(i);
	// if(row == null){
	// continue;
	// }
	// if (confirmJobUploadId != 1) {
	// ArrayList<ExcelErrorDetails> rowErrorDetailList = new
	// ArrayList<ExcelErrorDetails>();
	// rowErrorDetailList = validateRowData(row);
	// if (rowErrorDetailList != null) {
	// errorList.addAll(rowErrorDetailList);
	// hasErrorOccured = true;
	// }
	// }
	// if ((confirmJobUploadId != 1 && !hasErrorOccured) || confirmJobUploadId
	// == 1){
	// jobList.add(getJobDetails(row,domainDetail,userDetail));
	// }
	// }
	// if ((confirmJobUploadId == 1)
	// || (confirmJobUploadId != 1 && !hasErrorOccured)) {
	// errorList = saveJobList(jobList);
	// if (errorList.isEmpty()) {
	// resultMap.put("ajaxResult", "success");
	// resultMap.put("reason", null);
	// } else {
	// resultMap.put("ajaxResult", "error");
	// resultMap.put("reason", errorList);
	// }
	// } else {
	// resultMap.put("ajaxResult", "error");
	// resultMap.put("reason", errorList);
	//
	// }
	//
	// } else {
	// resultMap.put("ajaxResult", "error");
	// resultMap
	// .put("reason",
	// "Cannot Find the excel file. Please refresh the page and try again. If
	// this problem persists report it to Dev. Team.");
	//
	// }
	// return resultMap;
	// }
	//
	// private Integer getSchemaValidation(Workbook workBook) throws Exception {
	// ArrayList<String> excelHeader = new ArrayList<String>();
	//
	// int j = 0;
	// //here
	// //Strings for reading from property file
	// String sheetNumberString = "JOB_TYPE_SHEETNUM";
	// int sheetNumber = readFromHeader(null, sheetNumberString);
	// //here
	// //Values obtained from property file
	// String headerRowStringForProp = "JOB_TYPE_HEADER_ROWNUM";
	// Sheet sheet = workBook.getSheetAt(sheetNumber);
	//
	// int headerRowNum = readFromHeader(null,headerRowStringForProp);
	// Row rowHeader = sheet.getRow(headerRowNum);
	// while (rowHeader.getCell(j) != null) {
	// String headerVal = rowHeader.getCell(j).getStringCellValue();
	// excelHeader.add(headerVal);
	// j++;
	// }
	// return readFromHeader(excelHeader,null);
	// }
	//
	// public Integer readFromHeader(ArrayList<String> excelHeader, String
	// valToCheck) {
	// int result = 0;
	// try {
	// ClassLoader cl = PropertyFileReader.class.getClassLoader();
	// InputStream in = cl.getResourceAsStream("header.properties");
	// Properties prop = new Properties();
	// prop.load(in);
	// if(valToCheck==null){
	// String excelUploadedHeader = excelHeader.toString().substring(1,
	// excelHeader.toString().indexOf("]"));
	// //here
	// if (excelUploadedHeader.equalsIgnoreCase(prop
	// .getProperty(ConstantUtil.JOB_TYPE_EXCEL_FORMAT))) {
	// result = 1;
	// } else {
	// result = 0;
	// }
	// }
	// else{
	// return Integer.valueOf(prop.getProperty(valToCheck));
	// }
	// }
	// catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return result;
	// }
	//
	// public ArrayList<ExcelErrorDetails> validateRowData(Row row) {
	// int rowNumber = row.getRowNum();
	// ArrayList<ExcelErrorDetails> rowErrorList = new
	// ArrayList<ExcelErrorDetails>();
	// String stringToCheck = "";
	// int colNum;
	// String fieldToCheck = "";
	// Utility util = new Utility();
	// Date startDate = null;
	// Date endDate = null;
	// Date extendedDate = null;
	// int[] validationRequest = new int[2];
	// int initErrorLength = 0;
	// int finalErrorLength = 0;
	//
	// //validation for existing job number remaining
	//
	//
	// //mandatory field validation for Supervisor remaining
	//
	//
	// //validation for foreign keys
	// //validation for Foreman
	// fieldToCheck = "JOB_TYPE_DATA_FOREMAN_ID";
	// colNum = readFromHeader(null, fieldToCheck);
	// initErrorLength = rowErrorList.size(); //size of error list before
	// validation
	// validationRequest[0] = 4; //For email type
	// validationRequest[1] = 0; //For non mandatory field
	//
	// //validate Foreman field
	// rowErrorList =
	// util.checkForNullValueInExcelValidation(validationRequest,row,colNum,rowErrorList);
	// finalErrorLength = rowErrorList.size(); //size of error list after
	// validation
	// if(initErrorLength == finalErrorLength){ //go to next step of validation
	// only if there are no validation failures
	// try {
	// //check if email id exists in DB
	// if(!userDetailDAO.isEmailIdExists(stringToCheck)){
	// rowErrorList.add(getExcelErrorDetails(rowNumber, colNum,
	// "Foreman ID does not exist. Choose value from lookup table"));
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// rowErrorList.add(getExcelErrorDetails(rowNumber, colNum,
	// "Error occured : "+ e.getMessage()));
	// }
	// }
	//
	// //validation
	// //for
	// //dates
	//
	// //for start date
	// fieldToCheck = "JOB_TYPE_DATA_START_DATE";
	// colNum = readFromHeader(null, fieldToCheck);
	// initErrorLength = rowErrorList.size(); //size of error list before
	// validation
	// validationRequest[0] = 3; //For date type
	// validationRequest[1] = 0; //For non mandatory field
	//
	// //validate start date
	// rowErrorList =
	// util.checkForNullValueInExcelValidation(validationRequest,row,colNum,rowErrorList);
	// finalErrorLength = rowErrorList.size(); //size of error list after
	// validation
	// if(initErrorLength == finalErrorLength){ //go to next step of validation
	// only if there are no validation failures
	// try {
	// stringToCheck = row.getCell(colNum,
	// Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	// //validate parsing
	// startDate = util.validateDateParsing(stringToCheck);
	// } catch (IllegalStateException illegalStateException) {
	// illegalStateException.printStackTrace();
	// }
	// }
	//
	// //for end date
	// fieldToCheck = "JOB_TYPE_DATA_END_DATE";
	// colNum = readFromHeader(null, fieldToCheck);
	// initErrorLength = rowErrorList.size();
	// //validationRequest same as before..no need to set again
	//
	// //validate end date
	// rowErrorList =
	// util.checkForNullValueInExcelValidation(validationRequest,row,colNum,rowErrorList);
	// finalErrorLength = rowErrorList.size();
	// if(initErrorLength == finalErrorLength){
	// try {
	// stringToCheck = row.getCell(colNum,
	// Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	// //validate parsing
	// endDate = util.validateDateParsing(stringToCheck);
	// } catch (IllegalStateException illegalStateException) {
	// illegalStateException.printStackTrace();
	// }
	// }
	//
	// //validation for extended date
	// fieldToCheck = "JOB_TYPE_DATA_EXTENDED_DATE";
	// colNum = readFromHeader(null, fieldToCheck);
	// initErrorLength = rowErrorList.size();
	// //validationRequest same as before..no need to set again
	//
	// //validate extended date
	// rowErrorList =
	// util.checkForNullValueInExcelValidation(validationRequest,row,colNum,rowErrorList);
	// finalErrorLength = rowErrorList.size();
	// if(initErrorLength == finalErrorLength){
	// try {
	// stringToCheck = row.getCell(colNum,
	// Row.CREATE_NULL_AS_BLANK).getStringCellValue();
	// //validate parsing
	// extendedDate = util.validateDateParsing(stringToCheck);
	// } catch (IllegalStateException illegalStateException) {
	// illegalStateException.printStackTrace();
	// }
	// }
	//
	// //validation for CO date
	// fieldToCheck = "JOB_TYPE_DATA_CO_DATE";
	// colNum = readFromHeader(null, fieldToCheck);
	// //validationRequest same as before..no need to set again
	//
	// //validate extended date
	// rowErrorList =
	// util.checkForNullValueInExcelValidation(validationRequest,row,colNum,rowErrorList);
	//
	// //date validations for extended date > end date > start date
	// if(endDate != null && startDate != null){
	// if(endDate.compareTo(startDate)<0){
	// rowErrorList.add(getExcelErrorDetails(rowNumber, colNum,
	// "End Date cannot be before Start Date"));
	// }
	// }
	// if(endDate != null && extendedDate != null){
	// if(extendedDate.compareTo(endDate)<0){
	// rowErrorList.add(getExcelErrorDetails(rowNumber, colNum,
	// "Extended Date cannot be before End Date"));
	// }
	// }
	//
	// if(!rowErrorList.isEmpty()){
	// return rowErrorList;
	// }
	// else
	// return null;
	// }
	//
	// public JobDetail getJobDetails(Row row,
	// DomainDetail domainDetail,UserDetail userDetail) {
	//
	// JobDetail jobDetail = new JobDetail();
	//
	// jobDetail.setSubmittedBy(userDetail);
	// jobDetail.setSubmittedDate(new Date());
	// jobDetail.setDomainDetail(domainDetail);
	// jobDetail.setStatus("ACTIVE");
	//
	// // try {
	// // departmentType.setDepartmentName(row.getCell(0,
	// Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim());
	// // } catch (Exception e) {
	// // departmentType.setDepartmentName("");
	// // }
	// //job details left to set
	// //departmentType.setJobDetails(jobDetails);
	//
	// return jobDetail;
	// }
	//
	// public ExcelErrorDetails getExcelErrorDetails(int rowNum, int colNum,
	// String msg) {
	// ExcelErrorDetails e = new ExcelErrorDetails();
	// e.setRowNumber(rowNum);
	// e.setColNumber(colNum);
	// e.setErrorMessage(msg);
	// if (colNum != -1) {
	// e.setExcelCell(ConstantUtil.columnMapping[colNum] + rowNum);
	// }
	// return e;
	// }
	//
	// public ArrayList<ExcelErrorDetails> saveJobList(List<JobDetail> jobList)
	// {
	//
	// String jobDAOResult = "";
	// int i = 1;
	// ArrayList<ExcelErrorDetails> jobDAOErrorList = new
	// ArrayList<ExcelErrorDetails>();
	// for (JobDetail jobTypeObj : jobList) {
	// jobDAOResult = "";
	// try {
	// jobDAOResult = jobsDao.saveJob(jobTypeObj);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// if(jobDAOResult != null){
	// jobDAOErrorList.add(getExcelErrorDetails(i, -1,
	// "Error in saving to database"));
	// }
	// i++;
	// }
	// return jobDAOErrorList;
	// }

	@Override
	public String setJobToDuplicate(Long originalJobId, UserDetail logedUserDetails) throws Exception {

		Utility util = new Utility();
		JobDetail duplicateJobdetailsToSave = new JobDetail();
		String res = null;
		try {
			JobDetail originalJobDetailsBean = jobsDetailDao.getJobBean(originalJobId);

			if (originalJobDetailsBean != null) {
				duplicateJobdetailsToSave.setJobName(originalJobDetailsBean.getJobName());
				duplicateJobdetailsToSave.setJobNumber(originalJobDetailsBean.getJobNumber() + "_D");
				duplicateJobdetailsToSave.setSupervisor(originalJobDetailsBean.getSupervisor());
				duplicateJobdetailsToSave.setStatus("DUPLICATE");
				duplicateJobdetailsToSave.setSubmittedDate(new Date());
				duplicateJobdetailsToSave.setSubmittedBy(logedUserDetails);

				if (originalJobDetailsBean.getAutoApprovalLimit() != null) {
					duplicateJobdetailsToSave.setAutoApprovalLimit(originalJobDetailsBean.getAutoApprovalLimit());
				}
				if (originalJobDetailsBean.getBidPrice() != null) {
					duplicateJobdetailsToSave.setBidPrice(originalJobDetailsBean.getBidPrice());
				}
				if (originalJobDetailsBean.getCoDate() != null) {
					duplicateJobdetailsToSave.setCoDate(originalJobDetailsBean.getCoDate());
				}
				if (originalJobDetailsBean.getCollectionDayOut() != null) {
					duplicateJobdetailsToSave.setCollectionDayOut(originalJobDetailsBean.getCollectionDayOut());
				}
				if (originalJobDetailsBean.getDescription() != null) {
					duplicateJobdetailsToSave.setDescription(originalJobDetailsBean.getDescription());
				}
				if (originalJobDetailsBean.getDirectJobDayOut() != null) {
					duplicateJobdetailsToSave.setDirectJobDayOut(originalJobDetailsBean.getDirectJobDayOut());
				}
				if (originalJobDetailsBean.getEndDate() != null) {
					duplicateJobdetailsToSave.setEndDate(originalJobDetailsBean.getEndDate());
				}
				if (originalJobDetailsBean.getExtendedDate() != null) {
					duplicateJobdetailsToSave.setExtendedDate(originalJobDetailsBean.getExtendedDate());
				}
				if (originalJobDetailsBean.getIndirectDayOut() != null) {
					duplicateJobdetailsToSave.setIndirectDayOut(originalJobDetailsBean.getIndirectDayOut());
				}
				// if (originalJobDetailsBean.getJobAddress() != null) {
				// duplicateJobdetailsToSave.setJobAddress(originalJobDetailsBean.getJobAddress());
				// }
				if (originalJobDetailsBean.getLaborBonusTargetOverBudget() != null) {
					duplicateJobdetailsToSave.setLaborBonusTargetOverBudget(originalJobDetailsBean.getLaborBonusTargetOverBudget());
				}
				if (originalJobDetailsBean.getLaborDayOut() != null) {
					duplicateJobdetailsToSave.setLaborDayOut(originalJobDetailsBean.getLaborDayOut());
				}
				if (originalJobDetailsBean.getMaterialDayOut() != null) {
					duplicateJobdetailsToSave.setMaterialDayOut(originalJobDetailsBean.getMaterialDayOut());
				}
				if (originalJobDetailsBean.getOriginalContractValue() != null) {
					duplicateJobdetailsToSave.setOriginalContractValue(originalJobDetailsBean.getOriginalContractValue());
				}
				if (originalJobDetailsBean.getOwnedEquipmentDayOut() != null) {
					duplicateJobdetailsToSave.setOwnedEquipmentDayOut(originalJobDetailsBean.getOwnedEquipmentDayOut());
				}
				if (originalJobDetailsBean.getPerformanceTargetMargin() != null) {
					duplicateJobdetailsToSave.setPerformanceTargetMargin(originalJobDetailsBean.getPerformanceTargetMargin());
				}
				if (originalJobDetailsBean.getProjAdminDayOut() != null) {
					duplicateJobdetailsToSave.setProjAdminDayOut(originalJobDetailsBean.getProjAdminDayOut());
				}
				if (originalJobDetailsBean.getRentalEquipmentDayOut() != null) {
					duplicateJobdetailsToSave.setRentalEquipmentDayOut(originalJobDetailsBean.getRentalEquipmentDayOut());
				}
				if (originalJobDetailsBean.getReportMargin() != null) {
					duplicateJobdetailsToSave.setReportMargin(originalJobDetailsBean.getReportMargin());
				}
				if (originalJobDetailsBean.getRetentionDayOut() != null) {
					duplicateJobdetailsToSave.setRetentionDayOut(originalJobDetailsBean.getRetentionDayOut());
				}
				if (originalJobDetailsBean.getRetentionPercent() != null) {
					duplicateJobdetailsToSave.setRetentionPercent(originalJobDetailsBean.getRetentionPercent());
				}
				if (originalJobDetailsBean.getStartDate() != null) {
					duplicateJobdetailsToSave.setStartDate(originalJobDetailsBean.getStartDate());
				}
				if (originalJobDetailsBean.getSubcontractorDayOut() != null) {
					duplicateJobdetailsToSave.setSubcontractorDayOut(originalJobDetailsBean.getSubcontractorDayOut());
				}
				if (originalJobDetailsBean.getCustomerDirectory() != null) {
					duplicateJobdetailsToSave.setCustomerDirectory(originalJobDetailsBean.getCustomerDirectory());
				}
				if (originalJobDetailsBean.getContractorDirectory() != null) {
					duplicateJobdetailsToSave.setContractorDirectory(originalJobDetailsBean.getContractorDirectory());
				}
				if (originalJobDetailsBean.getDepartmentType() != null) {
					duplicateJobdetailsToSave.setDepartmentType(originalJobDetailsBean.getDepartmentType());
				}
				if (originalJobDetailsBean.getDomainDetail() != null) {
					duplicateJobdetailsToSave.setDomainDetail(originalJobDetailsBean.getDomainDetail());
				}
				if (originalJobDetailsBean.getVendorList() != null) {
					duplicateJobdetailsToSave.setVendorList(originalJobDetailsBean.getVendorList());
				}
				if (originalJobDetailsBean.getProjectTypeBean() != null) {
					duplicateJobdetailsToSave.setProjectTypeBean(originalJobDetailsBean.getProjectTypeBean());
				}
				if (originalJobDetailsBean.getForeman() != null) {
					duplicateJobdetailsToSave.setForeman(originalJobDetailsBean.getForeman());
				}
				if (originalJobDetailsBean.getManager() != null) {
					duplicateJobdetailsToSave.setManager(originalJobDetailsBean.getManager());
				}
				if (originalJobDetailsBean.getExecutive() != null) {
					duplicateJobdetailsToSave.setExecutive(originalJobDetailsBean.getExecutive());
				}
				if (originalJobDetailsBean.getPurchasingAgent() != null) {
					duplicateJobdetailsToSave.setPurchasingAgent(originalJobDetailsBean.getPurchasingAgent());
				}
			}
			JobDetail savedDuplicateJobDetails = jobsDetailDao.saveJob(duplicateJobdetailsToSave);
			if (savedDuplicateJobDetails != null) {
				String apiResponse = util.addRelatedDataIntoMPRForDuplicateProject(originalJobDetailsBean.getJobId(), savedDuplicateJobDetails.getJobId());
				if (!"success".equalsIgnoreCase(apiResponse)) {
					jobsDetailDao.deleteJobDatailsWhenExceptionInAPICall(savedDuplicateJobDetails.getJobId());
					res = apiResponse;
				}
			}
		} catch (Exception e) {
			res = e.getMessage();
			throw e;
		}

		return res;

	}

	@Override
	public HashMap<Object, Object> getRoleBasedUserDetails(Long domainId) throws Exception {

		Utility util = new Utility();
		HashMap<Object, Object> userMapBasedOnRole = null;
		try {
			List<UserRolesMapping> allUserList = userRolesMappingDAO.getRoleList();
			if (!(allUserList.isEmpty())) {
				userMapBasedOnRole = util.getUserListBasedOnRoles(allUserList, domainId);
			} else {
				// if empty user details..to be added
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return userMapBasedOnRole;
	}

	/**
	 * This method is to get the Active Job List based on User Role and User ID.
	 * 
	 * @param request
	 *            , userId & userRole
	 * @return List<JobDetail>
	 */
	@Override
	public List<JobDetail> getActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		// TODO Auto-generated method stub

		return jobsDetailDao.getActiveJobListByUserRoleAndID(userId, userRole);
	}

	/**
	 * This method is to get the Inactive Job List based on User Role and User
	 * ID.
	 * 
	 * @param request
	 *            , userId & userRole
	 * @return List<JobDetail>
	 */
	@Override
	public List<JobDetail> getInActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		// TODO Auto-generated method stub

		return jobsDetailDao.getInActiveJobListByUserRoleAndID(userId, userRole);
	}

	/**
	 * This method is to get the Closed Job List based on User Role and User ID.
	 * 
	 * @param request
	 *            , userId & userRole
	 * @return List<JobDetail>
	 */
	@Override
	public List<JobDetail> getClosedJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		// TODO Auto-generated method stub

		return jobsDetailDao.getClosedJobListByUserRoleAndID(userId, userRole);
	}

	/**
	 * This method is to get the Duplicate Job List based on User Role and User
	 * ID.
	 * 
	 * @param request
	 *            , userId & userRole
	 * @return List<JobDetail>
	 */
	@Override
	public List<JobDetail> getDuplicateJobListByUserRoleAndID(String userId, String userRole) throws Exception {
		// TODO Auto-generated method stub

		return jobsDetailDao.getDuplicateJobListByUserRoleAndID(userId, userRole);
	}

	/**
	 * This method is to get the Job Detail based on JobId.
	 * 
	 * @param request
	 *            , jobIdStr
	 * @return List<JobDetail>
	 */
	@Override
	public JobDetail getJobDetailByJobId(String jobIdStr) throws Exception {
		// TODO Auto-generated method stub

		return jobsDetailDao.getJobDetailByJobId(jobIdStr);
	}

	/*
	 * This method is to get Job list For Aggregate report Summary
	 * 
	 * @ Rishabh
	 */
	@Override
	public List<JobDetail> getAggregateReportListByJobId(String query) throws Exception {
		// TODO Auto-generated method stub
		return jobsDetailDao.getAggregateReportListByJobID(query);

	}

	// This method used for sending notification to selected users for the
	// created job
	private void sendNotificationToUser(UserDetail obj, JobDetail jobDetail, UserDetail userDetails) {
		String emailText = "";
		String emailSubject = "ElecnorEs : Assigned to a new project";
		String toEmail = obj.getEmailId();
		String ccEmail = null;
		String toText = null;
		String ccText = null;
		Utility util = new Utility();
		emailText = "Hello " + obj.getFirstName() + ", <br><br>" + "This is to inform you, that you have been involed in the project " + jobDetail.getJobName()
				+ ". Please login and fill the respective fields for the project";
		String emailJson = util.setEmailLogInEcosystem(toText, userDetails.getEmailId(), ccText, null, emailSubject, emailText, false, false, null, userDetails);
		// Here the method from centralized email should be used
		// Thread thread = new Thread(new
		// com.elecnor.ecosystem.util.NewEmailThread(emailJson));
		// thread.start();
	}

	@Override
	public String updateJobDetails(JobDetail jobDetail, Long jobCustomer, Long jobType, long jobForeman, long jobManager, long jobSupervisor, long jobExecutive, long jobPurchasingAgent,
			Long vendorsList, Integer jobDepartmentType, Long contractor, String status, DomainDetail domainDetail, String startDate, String endDate, String cODate, String extendedDate,
			String description, Long userId, String sovType, long jobAccountant, long jobWarehouseManager, long jobSuperindent, String jobEstimator, UserDetail userDetails, HttpServletRequest request)
					throws Exception {
		String exception = null;
		ArrayList<UserDetail> userDetailList = userDetailDAO.getAllUserDetails(domainDetail.getDomainId());
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		if (request.getParameter("jobId") != null && request.getParameter("jobId") != "") {
			jobDetail.setJobId(Long.parseLong(request.getParameter("jobId")));
		}
		if (request.getParameter("architectId") != null && request.getParameter("architectId") != "") {
			jobDetail.setArchitectId(Long.parseLong(request.getParameter("architectId")));
		}
		if (request.getParameter("activationValidityTimePeriod") != null && request.getParameter("activationValidityTimePeriod") != "") {
			jobDetail.setActivationValidityTimePeriod(Integer.parseInt(request.getParameter("activationValidityTimePeriod")));
		}
		if (request.getParameter("autoApprovalLimit") != null && request.getParameter("autoApprovalLimit") != "") {
			jobDetail.setAutoApprovalLimit(Double.parseDouble(request.getParameter("autoApprovalLimit")));
		}
		if (request.getParameter("bidPrice") != null && request.getParameter("bidPrice") != "") {
			jobDetail.setBidPrice(Double.parseDouble(request.getParameter("bidPrice").replace(",", "")));
		}
		if (request.getParameter("coDate") != null && request.getParameter("coDate") != "") {
			jobDetail.setCoDate(formatter.parse(request.getParameter("coDate")));
		}
		if (request.getParameter("collectionDayOut") != null && request.getParameter("collectionDayOut") != "") {
			jobDetail.setCollectionDayOut(Integer.parseInt(request.getParameter("collectionDayOut")));
		}
		if (request.getParameter("description") != null && request.getParameter("description") != "") {
			jobDetail.setDescription(request.getParameter("description"));
		}
		if (request.getParameter("directJobDayOut") != null && request.getParameter("directJobDayOut") != "") {
			jobDetail.setDirectJobDayOut(Integer.parseInt(request.getParameter("directJobDayOut")));
		}
		if (request.getParameter("endDate") != null && request.getParameter("endDate") != "") {
			jobDetail.setEndDate(formatter.parse(request.getParameter("endDate")));
		}
		if (request.getParameter("extendedDate") != null && request.getParameter("extendedDate") != "") {
			jobDetail.setExtendedDate(formatter.parse(request.getParameter("extendedDate")));
		}
		if (request.getParameter("startDate") != null && request.getParameter("startDate") != "") {
			jobDetail.setStartDate(formatter.parse(request.getParameter("startDate")));
		}
		if (request.getParameter("indirectDayOut") != null && request.getParameter("indirectDayOut") != "") {
			jobDetail.setIndirectDayOut(Integer.parseInt(request.getParameter("indirectDayOut")));
		}
		if (request.getParameter("jobName") != null && request.getParameter("jobName") != "") {
			jobDetail.setJobName(request.getParameter("jobName"));
		}
		if (request.getParameter("jobNumber") != null && request.getParameter("jobNumber") != "") {
			jobDetail.setJobNumber(request.getParameter("jobNumber"));
		} else {
			jobDetail.setStatus("PENDING");
		}
		if (request.getParameter("bidNumber") != null && request.getParameter("bidNumber") != "") {
			jobDetail.setBidNumber(request.getParameter("bidNumber"));
		}
		// if (request.getParameter("jobAddress") != null &&
		// request.getParameter("jobAddress") != "") {
		// jobDetail.setJobAddress(request.getParameter("jobAddress"));
		// }
		if (request.getParameter("laborBonusTargetOverBudget") != null && request.getParameter("laborBonusTargetOverBudget") != "") {
			jobDetail.setLaborBonusTargetOverBudget(Float.parseFloat(request.getParameter("laborBonusTargetOverBudget")));
		}
		if (request.getParameter("laborDayOut") != null && request.getParameter("laborDayOut") != "") {
			jobDetail.setLaborDayOut(Integer.parseInt(request.getParameter("laborDayOut")));
		}
		if (request.getParameter("materialDayOut") != null && request.getParameter("materialDayOut") != "") {
			jobDetail.setMaterialDayOut(Integer.parseInt(request.getParameter("materialDayOut")));
		}
		if (request.getParameter("originalContractValue") != null && request.getParameter("originalContractValue") != "") {
			jobDetail.setOriginalContractValue(Double.parseDouble(request.getParameter("originalContractValue")));
		}
		if (request.getParameter("ownedEquipmentDayOut") != null && request.getParameter("ownedEquipmentDayOut") != "") {
			jobDetail.setOwnedEquipmentDayOut(Integer.parseInt(request.getParameter("ownedEquipmentDayOut")));
		}
		if (request.getParameter("performanceTargetMargin") != null && request.getParameter("performanceTargetMargin") != "") {
			jobDetail.setPerformanceTargetMargin(Float.parseFloat(request.getParameter("performanceTargetMargin")));
		}
		if (request.getParameter("projAdminDayOut") != null && request.getParameter("projAdminDayOut") != "") {
			jobDetail.setProjAdminDayOut(Integer.parseInt(request.getParameter("projAdminDayOut")));
		}
		if (request.getParameter("rentalEquipmentDayOut") != null && request.getParameter("rentalEquipmentDayOut") != "") {
			jobDetail.setRentalEquipmentDayOut(Integer.parseInt(request.getParameter("rentalEquipmentDayOut")));
		}
		if (request.getParameter("reportMargin") != null && request.getParameter("reportMargin") != "") {
			jobDetail.setReportMargin(Float.parseFloat(request.getParameter("reportMargin")));
		}
		if (request.getParameter("retentionDayOut") != null && request.getParameter("retentionDayOut") != "") {
			jobDetail.setRetentionDayOut(Integer.parseInt(request.getParameter("retentionDayOut")));
		}
		if (request.getParameter("retentionPercent") != null && request.getParameter("retentionPercent") != "") {
			jobDetail.setRetentionPercent(Float.parseFloat(request.getParameter("retentionPercent")));
		}
		if (request.getParameter("subcontractorDayOut") != null && request.getParameter("subcontractorDayOut") != "") {
			jobDetail.setSubcontractorDayOut(Integer.parseInt(request.getParameter("subcontractorDayOut")));
		}
		if (request.getParameter("insuranceSentDate") != null && request.getParameter("insuranceSentDate") != "") {
			jobDetail.setInsuranceSentDate(formatter.parse(request.getParameter("insuranceSentDate")));
		}
		if (request.getParameter("performanceSentDate") != null && request.getParameter("performanceSentDate") != "") {
			jobDetail.setPerformanceSentDate(formatter.parse(request.getParameter("performanceSentDate")));
		}
		if (request.getParameter("cpnSentDate") != null && request.getParameter("cpnSentDate") != "") {
			jobDetail.setCpnSentDate(formatter.parse(request.getParameter("cpnSentDate")));
		}
		if (request.getParameter("isCertifiedPayroll") != null && request.getParameter("isCertifiedPayroll") != "") {
			jobDetail.setIsCertifiedPayroll(true);
		} else {
			jobDetail.setIsCertifiedPayroll(false);
		}
		if (request.getParameter("ownerControlledInsuranceProg") != null && request.getParameter("ownerControlledInsuranceProg") != "") {
			jobDetail.setOwnerControlledInsuranceProg(true);
		} else {
			jobDetail.setOwnerControlledInsuranceProg(false);
		}
		if (request.getParameter("noticeReceivedDate") != null && request.getParameter("noticeReceivedDate") != "") {
			jobDetail.setNoticeReceivedDate(formatter.parse(request.getParameter("noticeReceivedDate")));
		}
		if (request.getParameter("typeOfContract") != null && request.getParameter("typeOfContract") != "") {
			jobDetail.setTypeOfContract(request.getParameter("typeOfContract"));
		}
		if (request.getParameter("otherTypeOfContract") != null && request.getParameter("otherTypeOfContract") != "") {
			jobDetail.setOtherTypeOfContract(request.getParameter("otherTypeOfContract"));
		}
		if (request.getParameter("contractAmount") != null && request.getParameter("contractAmount") != "") {
			jobDetail.setContractAmount(Float.parseFloat(request.getParameter("contractAmount").replace(",", "")));
		}
		if (request.getParameter("contractNumber") != null && request.getParameter("contractNumber") != "") {
			jobDetail.setContractNumber(Long.parseLong(request.getParameter("contractNumber")));
		}
		if (request.getParameter("bidBudgetMaterialCost") != null && request.getParameter("bidBudgetMaterialCost") != "") {
			jobDetail.setBidBudgetMaterialCost(Float.parseFloat(request.getParameter("bidBudgetMaterialCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetSubcontractorsCost") != null && request.getParameter("bidBudgetSubcontractorsCost") != "") {
			jobDetail.setBidBudgetSubcontractorsCost(Float.parseFloat(request.getParameter("bidBudgetSubcontractorsCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetDirectJobCosts") != null && request.getParameter("bidBudgetDirectJobCosts") != "") {
			jobDetail.setBidBudgetDirectJobCosts(Float.parseFloat(request.getParameter("bidBudgetDirectJobCosts").replace(",", "")));
		}
		if (request.getParameter("bidBudgetRentalEquipmentCosts") != null && request.getParameter("bidBudgetRentalEquipmentCosts") != "") {
			jobDetail.setBidBudgetRentalEquipmentCosts(Float.parseFloat(request.getParameter("bidBudgetRentalEquipmentCosts").replace(",", "")));
		}
		if (request.getParameter("bidBudgetOwnedEquipmentsCosts") != null && request.getParameter("bidBudgetOwnedEquipmentsCosts") != "") {
			jobDetail.setBidBudgetOwnedEquipmentsCosts(Float.parseFloat(request.getParameter("bidBudgetOwnedEquipmentsCosts").replace(",", "")));
		}
		if (request.getParameter("bidBudgetProjectAdministrationCost") != null && request.getParameter("bidBudgetProjectAdministrationCost") != "") {
			jobDetail.setBidBudgetProjectAdministrationCost(Float.parseFloat(request.getParameter("bidBudgetProjectAdministrationCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetLaborCost") != null && request.getParameter("bidBudgetLaborCost") != "") {
			jobDetail.setBidBudgetLaborCost(Float.parseFloat(request.getParameter("bidBudgetLaborCost").replace(",", "")));
		}
		if (request.getParameter("bidBudgetIndirectExpenses") != null && request.getParameter("bidBudgetIndirectExpenses") != "") {
			jobDetail.setBidBudgetIndirectExpenses(Float.parseFloat(request.getParameter("bidBudgetIndirectExpenses").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetMaterialCosts") != null && request.getParameter("operationsBudgetMaterialCosts") != "") {
			jobDetail.setOperationsBudgetMaterialCosts(Float.parseFloat(request.getParameter("operationsBudgetMaterialCosts").replace(",", "")));
		}
		if (request.getParameter("operationsSubcontractorsCosts") != null && request.getParameter("operationsSubcontractorsCosts") != "") {
			jobDetail.setOperationsSubcontractorsCosts(Float.parseFloat(request.getParameter("operationsSubcontractorsCosts").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetDirectJobCost") != null && request.getParameter("operationsBudgetDirectJobCost") != "") {
			jobDetail.setOperationsBudgetDirectJobCost(Float.parseFloat(request.getParameter("operationsBudgetDirectJobCost").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetRentalEquipmentCost") != null && request.getParameter("operationsBudgetRentalEquipmentCost") != "") {
			jobDetail.setOperationsBudgetRentalEquipmentCost(Float.parseFloat(request.getParameter("operationsBudgetRentalEquipmentCost").replace(",", "")));
		}
		if (request.getParameter("operationsOwnedEquipmentCost") != null && request.getParameter("operationsOwnedEquipmentCost") != "") {
			jobDetail.setOperationsOwnedEquipmentCost(Float.parseFloat(request.getParameter("operationsOwnedEquipmentCost").replace(",", "")));
		}
		if (request.getParameter("operationsProjectAdministrationCost") != null && request.getParameter("operationsProjectAdministrationCost") != "") {
			jobDetail.setOperationsProjectAdministrationCost(Float.parseFloat(request.getParameter("operationsProjectAdministrationCost").replace(",", "")));
		}
		if (request.getParameter("operationsIndirectExpenses") != null && request.getParameter("operationsIndirectExpenses") != "") {
			jobDetail.setOperationsIndirectExpenses(Float.parseFloat(request.getParameter("operationsIndirectExpenses").replace(",", "")));
		}
		if (request.getParameter("operationsBudgetLaborCost") != null && request.getParameter("operationsBudgetLaborCost") != "") {
			jobDetail.setOperationsBudgetLaborCost(Float.parseFloat(request.getParameter("operationsBudgetLaborCost").replace(",", "")));
		}
		if (jobCustomer > 0) {
			jobDetail.setCustomerDirectory(customerDirectoryDAO.getCustomerById(jobCustomer));
		} else {
			jobDetail.setCustomerDirectory(null);
		}
		if (jobType > 0) {
			jobDetail.setProjectTypeBean(typeProjectDAO.getProjectById(jobType));
		} else {
			jobDetail.setProjectTypeBean(null);
		}
		if (vendorsList > 0) {
			jobDetail.setVendorList(vendorDirectoryDAO.getVendorsById(vendorsList));
		} else {
			jobDetail.setVendorList(null);
		}
		if (jobDepartmentType > 0) {
			jobDetail.setDepartmentType(departmentsDetailDAO.getDeptDirectoryById(jobDepartmentType));
		} else {
			jobDetail.setDepartmentType(null);
		}
		if (contractor > 0) {
			jobDetail.setContractorDirectory(contractorDirectoryDAO.getContractorDetailByID(contractor));
		} else {
			jobDetail.setContractorDirectory(null);
		}
		if (jobForeman > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobForeman) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setForeman(obj);
					break;
				}
			}
		} else {
			jobDetail.setForeman(null);
		}

		if (jobManager > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobManager) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setManager(obj);
					break;
				}
			}
		} else {
			jobDetail.setManager(null);
		}

		if (jobSupervisor > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobSupervisor) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setSupervisor(obj);
					break;
				}
			}
		} else {
			jobDetail.setSupervisor(null);
		}
		if (jobExecutive > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobExecutive) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setExecutive(obj);
					break;
				}
			}
		} else {
			jobDetail.setExecutive(null);
		}
		if (jobPurchasingAgent > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobPurchasingAgent) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setPurchasingAgent(obj);
					break;
				}
			}
		} else {
			jobDetail.setPurchasingAgent(null);
		}
		if (jobAccountant > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobAccountant) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setAccountant(obj);
					break;
				}
			}
		} else {
			jobDetail.setAccountant(null);
		}

		if (jobWarehouseManager > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobWarehouseManager) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setWarehouseManager(obj);
					break;
				}
			}
		} else {
			jobDetail.setWarehouseManager(null);
		}
		if (jobSuperindent > 0) {
			for (UserDetail obj : userDetailList) {
				if (obj.getUserId() == jobSuperindent) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					jobDetail.setSuperindent(obj);
					break;
				}
			}
		} else {
			jobDetail.setSuperindent(null);
		}
		if (jobEstimator != "") {
			jobDetail.setEstimator(jobEstimator);
			for (UserDetail obj : userDetailList) {
				if (obj.getFirstName() == jobEstimator) {
					sendNotificationToUser(obj, jobDetail, userDetails);
					break;
				}
			}
		} else {
			jobDetail.setEstimator(null);
		}
		exception = jobsDetailDao.updateJob(jobDetail, jobCustomer, jobType, jobForeman, jobManager, jobSupervisor, jobExecutive, jobPurchasingAgent, vendorsList, jobDepartmentType, contractor,
				status, domainDetail, startDate, endDate, cODate, extendedDate, description, userId, sovType, jobAccountant, jobWarehouseManager, jobSuperindent, jobEstimator);
		return exception;
	}

	// This method used for sending notification to the department head for the
	// created job @Incomplete
	private void sendNotificationToDepartmentHead(JobDetail jobDetail, UserDetail userDetails) {
		String emailText = "";
		String emailSubject = "ElecnorES : Assigned to a new project";
		String toEmail = userDetails.getEmailId();
		String ccEmail = null;
		String toText = null;
		String ccText = null;
		Utility util = new Utility();
		emailText = "Hello " + userDetails.getFirstName() + ", <br><br>" + "This is to inform you, that you have been involed in the project " + jobDetail.getJobName()
				+ ". Please login and fill the respective fields for the project";
		String emailJson = util.setEmailLogInEcosystem(toText, userDetails.getEmailId(), ccText, null, emailSubject, emailText, false, false, null, userDetails);
		// Here the method from centralized email should be used
		// Thread thread = new Thread(new
		// com.elecnor.ecosystem.util.NewEmailThread(emailJson));
		// thread.start();
	}

	@Override
	public JobDetail getJobBean(long jobId) throws Exception {
		return jobsDetailDao.getJobBean(jobId);
	}

	// Service method to update days out data if the data is updated in MPR
	// @Ashutosh
	@Override
	public String updateDaysOutDataForProject(String jsonString) throws Exception {
		URLCodec codec = new URLCodec();
		System.out.println("days out json String  " + jsonString);
		System.out.println("*****" + codec.decode(jsonString));
		return "success";
	}

	// @Rishabh
	// This method to get all user emails comma seperated
	@Override
	public String getEmailIds(List<UserDetail> users) {
		StringBuilder emails = new StringBuilder();
		if (users != null && users.size()>0) {
			for (UserDetail userDetail : users) {
				emails.append(userDetail.getEmailId()).append(",");
			}
			emails = emails.deleteCharAt(emails.lastIndexOf(","));
		}
		return emails.toString();
	}
}
