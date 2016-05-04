package com.elecnor.ecosystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.JobUploadDocument;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.BudgetCodeDAO;
import com.elecnor.ecosystem.dao.ContractorDirectoryDAO;
import com.elecnor.ecosystem.dao.CustomerDirectoryDAO;
import com.elecnor.ecosystem.dao.DepartmentDirectoryDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAO;
import com.elecnor.ecosystem.dao.TypesOfProjectDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.dao.VendorDirectoryDAO;
import com.elecnor.ecosystem.helper.AddressDetailsHelper;
import com.elecnor.ecosystem.job.processor.JobSetupProcessor;
import com.elecnor.ecosystem.service.AddressDetailsService;
import com.elecnor.ecosystem.service.JobUploadDocumentService;
import com.elecnor.ecosystem.service.JobsDetailService;
import com.elecnor.ecosystem.service.UserDetailService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;
import com.file.handler.bean.FileUploadBean;
import com.file.handler.service.FileUploadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Vaibhav
 * @Vaibhav Removing Navigation API to Navigation Controller.
 */

@Controller
public class JobsDetailController {

	@Autowired
	JobsDetailService jobsService;
	@Autowired
	JobsDetailDAO jobsDao;
	@Autowired
	UserDetailDAO userDao;
	@Autowired
	VendorDirectoryDAO vendorDirectoryDAO;
	@Autowired
	TypesOfProjectDAO typesOfProjectDAO;
	@Autowired
	CustomerDirectoryDAO customerDirectoryDAO;
	@Autowired
	ContractorDirectoryDAO contractorDirectoryDAO;
	@Autowired
	DepartmentDirectoryDAO departmentDetailsDAO;
	@Autowired
	JobsDetailDAO jobsDetailDAO;
	@Autowired
	FileUploadService uploader;
	@Autowired
	JobUploadDocumentService jobUploadDocService;
	@Autowired
	Utility util;
	@Autowired
	AddressDetailsService addressDetailService;
	@Autowired
	AddressDetailsHelper addressHelper;
	@Autowired
	BudgetCodeDAO budgetCodeDao;
	@Autowired
	JobSetupProcessor processorThread;
	@Autowired
	UserDetailService userDetailService;

	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	/*
	 * @RequestMapping("/project") public String getDashboardPage() { return
	 * "jobsDetail"; }
	 * 
	 * @ModelAttribute("manageJobForm") public JobDetail registerJobProfile() {
	 * return new JobDetail(); }
	 */

	/**
	 * Method for get all jobs detail
	 * 
	 * @param session
	 * @return
	 */
	// changes by Meghanajagruthi
	@RequestMapping(value = "/checkJobNumber", method = RequestMethod.POST)
	@ResponseBody
	public String checkJobNum(HttpSession session, HttpServletRequest request) {

		String jobNumber = null;
		String jobId = null;
		boolean isExist = false;
		if (request.getParameter("jobNumber") != null && !request.getParameter("jobNumber").equals("")) {
			jobNumber = request.getParameter("jobNumber").trim();
		}

		if (request.getParameter("jobId").trim().equals("0") || request.getParameter("jobId").equals("")) {
			isExist = jobsDetailDAO.isJobNumExixsting(jobNumber);
		} else {
			isExist = jobsDetailDAO.isJobNumExixsting(jobId, jobNumber);
		}

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(isExist);
		return json;
	}

	@RequestMapping(value = "/getActiveJobDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getActiveJobDetails(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			Long domainId = domainDetail.getDomainId();
			ArrayList<JobDetail> allJobsList = jobsDao.getActiveJobDetails(domainId);

			/*
			 * ArrayList<VendorDirectory> allVendorsList=
			 * vendorDirectoryDAO.getVendorDetailsForJobs();
			 * ArrayList<ProjectType>
			 * allProjectTypes=typesOfProjectDAO.getAllProjectTypes(domainId);
			 * List<DepartmentDirectory>
			 * allDepartmentTypes=departmentDetailsDAO.getAllDepartments();
			 * ArrayList<CustomerDirectory>
			 * allCustomersList=customerDirectoryDAO
			 * .getAllCustomersList(domainId); ArrayList<ContractorDirectory>
			 * allContractorsList
			 * =contractorDirectoryDAO.getAllContractorList(domainId);
			 */

			// HashMap<Object,Object> allUserList =
			// jobsService.getRoleBasedUserDetails(domainId);

			map.put("allJobsList", allJobsList);
			/*
			 * map.put("userList", allUserList); map.put("allVendorsList",
			 * allVendorsList); map.put("allProjectTypes", allProjectTypes);
			 * map.put("allDepartments", allDepartmentTypes);
			 * map.put("allCustomersList", allCustomersList);
			 * map.put("allContractorsList", allContractorsList);
			 */

		} catch (Exception e) {
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResultForDate(map);
	}

	/**
	 * Method for save or update job details
	 * 
	 * @param jobDetail
	 * @param request
	 * @param session
	 * @return @ModelAttribute("manageJobForm") JobDetail jobDetail,
	 * @RequestParam(value = "jobAction") String action,
	 */
	@RequestMapping(value = "/saveUpdateJobDetails", method = RequestMethod.POST)
	public @ResponseBody String saveUpdateJobDetails(HttpServletRequest request, HttpSession session) {
		Long jobCustomer = 0L, jobType = 0L, vendorsList = 0L, contractor = 0L;
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		JobDetail jobDetail = new JobDetail();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			jobDetail.setDomainDetail(domainDetail);
			long jobForeman = 0L;
			long jobManager = 0L, jobSupervisor = 0L, jobExecutive = 0L, jobPurchasingAgent = 0L;
			long jobAccountant = 0L, jobWarehouseManager = 0L, jobSuperindent = 0L;
			String jobEstimator = "";
			Integer jobDepartmentType = 0;
			StringBuilder jobRelatedUsers = new StringBuilder();

			if (request.getParameter("customerDirectory") != null && request.getParameter("customerDirectory") != "") {
				jobCustomer = Long.parseLong(request.getParameter("customerDirectory"));
			}
			if (request.getParameter("projectTypeBean") != null && request.getParameter("projectTypeBean") != "") {
				jobType = Long.parseLong(request.getParameter("projectTypeBean"));
			}
			if (request.getParameter("foreman") != null && !request.getParameter("foreman").trim().equalsIgnoreCase("")) {
				jobForeman = Long.parseLong(request.getParameter("foreman"));
				jobRelatedUsers.append(jobForeman).append(",");
			}
			if (request.getParameter("executive") != null && !request.getParameter("executive").trim().equalsIgnoreCase("")) {
				jobExecutive = Long.parseLong(request.getParameter("executive"));
				jobRelatedUsers.append(jobExecutive).append(",");
			}
			if (request.getParameter("purchasingAgent") != null && !request.getParameter("purchasingAgent").trim().equalsIgnoreCase("")) {
				jobPurchasingAgent = Long.parseLong(request.getParameter("purchasingAgent"));
			}
			if (request.getParameter("manager") != null && !request.getParameter("manager").trim().equalsIgnoreCase("")) {
				jobManager = Long.parseLong(request.getParameter("manager"));
				jobRelatedUsers.append(jobManager).append(",");
			}
			if (request.getParameter("supervisor") != null && !request.getParameter("supervisor").trim().equalsIgnoreCase("")) {
				jobSupervisor = Long.parseLong(request.getParameter("supervisor"));
				jobRelatedUsers.append(jobSupervisor).append(",");
			}
			if (request.getParameter("vendorList") != null && request.getParameter("vendorList") != "") {
				vendorsList = Long.parseLong(request.getParameter("vendorList"));
			}
			if (request.getParameter("departmentType") != null && request.getParameter("departmentType") != "") {
				jobDepartmentType = Integer.parseInt(request.getParameter("departmentType"));
			}
			if (request.getParameter("contractorDirectory") != null && request.getParameter("contractorDirectory") != "") {
				contractor = Long.parseLong(request.getParameter("contractorDirectory"));
			}
			if (request.getParameter("accountant") != null && !request.getParameter("accountant").trim().equalsIgnoreCase("")) {
				jobAccountant = Long.parseLong(request.getParameter("accountant"));
				jobRelatedUsers.append(jobAccountant).append(",");
			}
			if (request.getParameter("warehouseManager") != null && !request.getParameter("warehouseManager").trim().equalsIgnoreCase("")) {
				jobWarehouseManager = Long.parseLong(request.getParameter("warehouseManager"));
			}
			if (request.getParameter("superindent") != null && !request.getParameter("superindent").trim().equalsIgnoreCase("")) {
				jobSuperindent = Long.parseLong(request.getParameter("superindent"));
				jobRelatedUsers.append(jobSuperindent).append(",");
			}
			if (request.getParameter("estimator") != null && !request.getParameter("estimator").trim().equalsIgnoreCase("")) {
				jobEstimator = request.getParameter("estimator");
			}
			if (null != userDetails.getSuperviserId() && 0 != userDetails.getSuperviserId().getUserId())
				jobRelatedUsers.append(userDetails.getSuperviserId().getUserId());
			
			String status = request.getParameter("status");
			String action = request.getParameter("jobAction");
			String exception = null;
			String sovType = request.getParameter("sovType");
			JobDetail savedJobDetail = null;
			List<UserDetail> jobUsers = userDetailService.getUserDetailListById(jobRelatedUsers);
			String emailTo = jobsService.getEmailIds(jobUsers);
			if (action.equalsIgnoreCase("update")) {
				JobDetail tempJobDetail = null;
				String stDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				String extendedDate = request.getParameter("extendedDate");
				String CODate = request.getParameter("coDate");
				String Description = request.getParameter("description");
				String oldStartDt = "";
				String oldEndDt = "";

				if (null != request.getParameter("jobId") && !request.getParameter("jobId").trim().isEmpty()) {
					tempJobDetail = jobsService.getJobBean(Integer.parseInt(request.getParameter("jobId")));
				}
				if (null != tempJobDetail && null != tempJobDetail.getStartDate() && null != tempJobDetail.getEndDate()) {
					oldStartDt = formatter.format(tempJobDetail.getStartDate());
					oldEndDt = formatter.format(util.getOlderDate(tempJobDetail.getEndDate(), tempJobDetail.getCoDate(), tempJobDetail.getExtendedDate()));
				}
				// String result =
				// util.addCashflowManpowerForProject(jobDetail.getJobId(),
				// startDate, endDate, extendedDate, CODate,
				// userDetails.getUserId(), "update");
				// if("success".equalsIgnoreCase(result)){
				exception = jobsService.updateJobDetails(jobDetail, jobCustomer, jobType, jobForeman, jobManager, jobSupervisor, jobExecutive, jobPurchasingAgent, vendorsList, jobDepartmentType,
						contractor, status, userDetails.getDomainDetail(), stDate, endDate, CODate, extendedDate, Description, userDetails.getUserId(), sovType, jobAccountant, jobWarehouseManager,
						jobSuperindent, jobEstimator, userDetails, request);

				endDate = getGreaterDate(endDate, CODate, extendedDate);
				try {
					Integer.parseInt(exception);
					map.put("ajaxResult", "success");
					map.put("reason", exception);
					// Call Thread Executer for MPR CASH-FLOW & MAN-POWER DATA
					if (!stDate.trim().equals("") && !endDate.trim().equals("")) {
						if (null == oldStartDt && null == oldEndDt) {
							// Should perform save functionality
							cashFlowManpowerDataExecuter(stDate, endDate, oldStartDt, oldEndDt, false, jobDetail.getJobId(), null, null,emailTo);
						} else {
							// Should perform update functionality
							cashFlowManpowerDataExecuter(stDate, endDate, oldStartDt, oldEndDt, true, jobDetail.getJobId(), null, null,emailTo);
						}

					}
				} catch (Exception e) {
					map.put("ajaxResult", "failure");
					map.put("reason", e.getMessage());
				}

			} else {
				jobDetail.setSubmittedBy(userDetails);

				savedJobDetail = jobsService.saveJobDetails(userDetails.getDomainDetail().getDomainId(), jobDetail, jobCustomer, jobType, jobForeman, jobManager, jobSupervisor, jobExecutive,
						jobPurchasingAgent, vendorsList, jobDepartmentType, contractor, status, domainDetail, userDetails.getUserId(), sovType, jobAccountant, jobWarehouseManager, jobSuperindent,
						jobEstimator, userDetails, request);

				// AddBudgetForms(savedJobDetail, userDetails);
				map.put("ajaxResult", "success");
				map.put("reason", savedJobDetail.getJobId());

				// Call Thread Executer for MPR CASH-FLOW & MAN-POWER DATA
				String newStartDate = "";
				String newEndDate = "";
				if (null != savedJobDetail.getStartDate() && null != savedJobDetail.getEndDate()) {
					newStartDate = formatter.format(savedJobDetail.getStartDate());
					newEndDate = formatter.format(savedJobDetail.getEndDate());
				}
				cashFlowManpowerDataExecuter(newStartDate, newEndDate, null, null, false, savedJobDetail.getJobId(), savedJobDetail, userDetails, emailTo);

			}

		} catch (Exception e) {
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(map);
	}

	/*
	 * This method calls thread which will push data into cashflow and manpower
	 * in MPR application
	 * 
	 * @Rishabh
	 */

	public void cashFlowManpowerDataExecuter(String startDate, String endDate, String oldStartDt, String oldEndDt, boolean isUpdate, Long jobId, JobDetail jobDetail, UserDetail userDetail,String emailTo) {
		try {
			processorThread.setUpJobInDownstreamApps(startDate, endDate, oldStartDt, oldEndDt, isUpdate, jobId, jobDetail, userDetail,emailTo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getGreaterDate(String endDate, String coDate, String extendedDate) throws ParseException {
		Date endCal = null;
		Date coCal = null;
		Date extnCal = null;
		if (null != endDate && !endDate.isEmpty()) {
			endCal = formatter.parse(endDate);
		}
		if (null != coDate && !coDate.isEmpty()) {
			coCal = formatter.parse(coDate);
		}
		if (null != extendedDate && !extendedDate.isEmpty()) {
			extnCal = formatter.parse(extendedDate);
		}
		if (endCal != null || coCal != null || extnCal != null) {
			return formatter.format(util.getOlderDate(endCal, coCal, extnCal));
		} else {
			return "";
		}
	}

	/**
	 * Method for delete
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteJobDetails", method = RequestMethod.POST)
	public @ResponseBody String deleteJobDetails(HttpServletRequest request) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			Long jobId = Long.parseLong(request.getParameter("jobId"));
			String exception = jobsDetailDAO.deleteJob(jobId);
			map = util.responseBuilder(exception);
		} catch (Exception e) {
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(map);

	}

	// @RequestMapping(value = "/jobTemplateController", method =
	// RequestMethod.POST)
	// @ResponseBody
	// public String saveJobTemplateDetails(
	// @RequestParam(value = "confirmJobUploadId", defaultValue = "-1") int
	// confirmJobUploadId,
	// @RequestParam(value = "jobTemplateFile") MultipartFile fileUploaded,
	// HttpServletRequest request, HttpSession session) throws IOException {
	// HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// Utility util = new Utility();
	// try {
	// resultMap = jobsService.uploadJobFile(
	// fileUploaded, session, confirmJobUploadId);
	// } catch (Exception e) {
	// resultMap.put("ajaxResult", "failure");
	// resultMap.put("reason", e.getMessage());
	// util.sendExceptionEmail(e);
	// }
	// return util.getJsonResultWithoutExposeString(resultMap);
	// }

	@RequestMapping(value = "/setJobToDuplicate", method = RequestMethod.POST)
	public @ResponseBody String setJobToDuplicate(HttpServletRequest request, HttpSession session) {
		UserDetail logedUserDetails = (UserDetail) session.getAttribute("selectedUser");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			Long jobId = Long.parseLong(request.getParameter("jobIdToDuplicate"));
			String exception = jobsService.setJobToDuplicate(jobId, logedUserDetails);
			map = util.responseBuilder(exception);
		} catch (Exception e) {
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			e.printStackTrace();
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(map);

	}

	/*
	 * This method will upload document to ther server
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadJobDocuments", method = RequestMethod.POST)
	public @ResponseBody String uploadJobDocuments(HttpServletRequest request, HttpSession session, @RequestParam(value = "attachments") List<MultipartFile> files) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String fieldName = request.getParameter("attachmentsName");
		String jobId = request.getParameter("attachmentsId");
		JobUploadDocument info = null;
		try {
			if (null != jobId && !jobId.isEmpty()) {
				// Calling FileHandler to upload file to the server
				Map<String, Object> resultMap = uploader.uploadListOfFile(files);
				if (resultMap.containsKey(ConstantUtil.SUCCESS)) {
					List<Integer> fileIds = (List<Integer>) resultMap.get(ConstantUtil.SUCCESS);
					// info = jobsService.getJobBean(Long.valueOf(jobId));
					info = jobUploadDocService.getJobUploadDocument(fieldName, Integer.valueOf(jobId));
					// Check whether its an update call or save call
					if (null != info) {
						// UPDATE
						Integer docId = info.getId();
						jobUploadDocService.updateDocumentIds(docId, info.getAppUploadFileId(), fileIds);
						map.put("ajaxResult", ConstantUtil.SUCCESS);
					} else {
						// SAVE
						jobUploadDocService.saveDocumentIds(fileIds, fieldName, Integer.valueOf(jobId));
						map.put("ajaxResult", ConstantUtil.SUCCESS);
					}

				} else {
					map.put("ajaxResult", ConstantUtil.FAILURE);
					map.put("reason", resultMap.get(ConstantUtil.FAILURE));
				}
			}
		} catch (Exception e) {
			map.put("ajaxResult", ConstantUtil.FAILURE);
			map.put("reason", e.getMessage());
			e.printStackTrace();
		}
		return util.getJsonResult(map);
	}

	/*
	 * This method will DELETE Document
	 */
	@RequestMapping(value = "/deleteDocument", method = RequestMethod.GET)
	public @ResponseBody String deleteDocument(HttpServletRequest request, HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String docId = request.getParameter("docId");
		String jobId = request.getParameter("jobId");
		String fieldName = request.getParameter("fieldName");
		try {
			if (null != docId && !docId.isEmpty() && null != jobId && !jobId.isEmpty() && null != fieldName && !fieldName.isEmpty()) {
				Map<String, Object> result = uploader.deleteFile(Integer.valueOf(docId));
				if (result.containsKey(ConstantUtil.SUCCESS)) {
					if ((Integer) result.get(ConstantUtil.SUCCESS) == 1) {
						if (jobUploadDocService.deleteDocumentId(jobId, docId, fieldName) == 1) {
							map.put("ajaxResult", ConstantUtil.SUCCESS);
							map.put("reason", "Document Deleted Successfully");
						}
					}
				}
			} else {
				map.put("ajaxResult", ConstantUtil.FAILURE);
				map.put("reason", "required parameter is empty - docId :" + docId + " jobId :" + jobId + " fieldName :" + fieldName);
			}
		} catch (Exception e) {
			map.put("ajaxResult", ConstantUtil.FAILURE);
			map.put("reason", e.getMessage());
			e.printStackTrace();
		}

		return util.getJsonResult(map);
	}

	/*
	 * This method will get Job specific all Document
	 */
	@RequestMapping(value = "/getDocuments", method = RequestMethod.GET)
	public @ResponseBody String getAllDocuments(HttpServletRequest request, HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String jobId = request.getParameter("jobId");
		try {
			if (null != jobId && !jobId.isEmpty()) {
				List<JobUploadDocument> jobDocsList = jobUploadDocService.getUploadDocumentIds(Integer.valueOf(jobId));
				if (null != jobDocsList && !jobDocsList.isEmpty()) {
					for (JobUploadDocument jobDoc : jobDocsList) {
						List<FileUploadBean> filesBean = uploader.getFileInfoList(jobDoc.getAppUploadFileId());
						map.put(jobDoc.getAssociatedField(), filesBean);
					}
				}
			} else {
				map.put("ajaxResult", ConstantUtil.FAILURE);
				map.put("reason", "No id get from request");
			}
		} catch (Exception e) {
			map.put("ajaxResult", ConstantUtil.FAILURE);
			map.put("reason", e.getMessage());
			e.printStackTrace();
		}

		return util.getJsonResult(map);
	}

	@RequestMapping(value = "/bulkDownload", method = RequestMethod.GET)
	public @ResponseBody String bulkDownload(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String jobId = request.getParameter("bulk_jobId");
		File zipFile = null;
		InputStream targetStream = null;
		try {
			if (null != jobId && !jobId.isEmpty()) {
				List<JobUploadDocument> jobDocsList = jobUploadDocService.getUploadDocumentIds(Integer.valueOf(jobId));
				if (null != jobDocsList && !jobDocsList.isEmpty()) {
					StringBuilder fileIds = new StringBuilder();
					for (JobUploadDocument jobDoc : jobDocsList) {
						fileIds.append(jobDoc.getAppUploadFileId()).append(",");
					}
					fileIds = fileIds.deleteCharAt(fileIds.lastIndexOf(","));
					List<String> fileIdList = Arrays.asList(fileIds.toString().split(","));

					Map<String, Object> resultMap = uploader.bulkDownload(fileIdList);

					if (resultMap.containsKey(ConstantUtil.SUCCESS)) {
						zipFile = new File((String) resultMap.get(ConstantUtil.SUCCESS));
						targetStream = new FileInputStream(zipFile);
						response.setContentType("application/zip");
						response.setContentLength((int) zipFile.length());
						response.setHeader("Content-Disposition", "attachment; filename=\"" + "job_ecosystem.zip" + "\"");
						FileCopyUtils.copy(targetStream, response.getOutputStream());
					}
				}
			} else {
				map.put("ajaxResult", ConstantUtil.FAILURE);
				map.put("reason", "No id get from request");
			}
		} catch (Exception e) {
			map.put("ajaxResult", ConstantUtil.FAILURE);
			map.put("reason", e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				targetStream.close();
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}

			uploader.deleteZip();
		}

		return util.getJsonResult(map);
	}

	/*
	 * This method will download Single File
	 */
	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public @ResponseBody String fileDownload(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String fileId = request.getParameter("fileId");
		File file = null;
		try {
			if (null != fileId && !fileId.isEmpty()) {

				FileUploadBean fileInfo = uploader.getFileInfo(Integer.valueOf(fileId));
				Map<String, Object> resultMap = uploader.downloadFile(Integer.valueOf(fileId));

				if (resultMap.containsKey(ConstantUtil.SUCCESS)) {
					file = (File) resultMap.get(ConstantUtil.SUCCESS);
					InputStream targetStream = new FileInputStream(file);
					if (fileInfo.getFileType().trim().equalsIgnoreCase(".txt")) {
						response.setContentType("text/plain");
					} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".doc")) {
						response.setContentType("application/msword");
					} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".xls")) {
						response.setContentType("application/vnd.ms-excel");
					} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".pdf")) {
						response.setContentType("application/pdf");
					} else if (fileInfo.getFileType().trim().equalsIgnoreCase(".ppt")) {
						response.setContentType("application/ppt");
					} else {
						response.setContentType("application/octet-stream");
					}
					response.setContentLength((int) file.length());
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileName() + "\"");

					FileCopyUtils.copy(targetStream, response.getOutputStream());
					targetStream.close();
					response.flushBuffer();

				}
			}
			System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			map.put("ajaxResult", ConstantUtil.FAILURE);
			map.put("reason", e.getMessage());
			e.printStackTrace();
		}

		return util.getJsonResult(file.getAbsolutePath());
	}

	@RequestMapping(value = "/getPendingJobDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getPendingJobDetails(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			Long domainId = domainDetail.getDomainId();
			ArrayList<JobDetail> allJobsList = jobsDao.getActiveJobDetails(domainId);
			map.put("allJobsList", allJobsList);
		} catch (Exception e) {
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
			e.printStackTrace();
		}
		return util.getJsonResultForDate(map);
	}

	@RequestMapping(value = "/getAllJobDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getAllJobDetails(HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			Long domainId = domainDetail.getDomainId();
			ArrayList<JobDetail> allJobsList = jobsDao.getAllJobDetails(domainId);
			map.put("allJobsList", allJobsList);
		} catch (Exception e) {
			map.put("ajaxResult", "failure");
			map.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
			e.printStackTrace();
		}
		return util.getJsonResultForDate(map);
	}

}
