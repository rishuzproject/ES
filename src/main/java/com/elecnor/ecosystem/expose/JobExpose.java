package com.elecnor.ecosystem.expose;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.dao.JobsDetailDAOForDownstreamApp;
import com.elecnor.ecosystem.service.JobsDetailService;
import com.elecnor.ecosystem.util.ConstantUtil;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class JobExpose {

//	@Autowired
//	JobsDetailDAO jobsDao;
	
	@Autowired
	JobsDetailDAOForDownstreamApp jobsDetailDAOForDownstreamApp;

	@Autowired
	JobsDetailService jobsDetailService;

	@Autowired
	Utility util;
	
	@Autowired
	AddressDetailsDAO addressDetailsDAO;

	@RequestMapping(value = "/excludeInterceptor/getJobListByDomainId")
	public @ResponseBody String getJobListByDomainId(HttpServletRequest request) {
		Utility util = new Utility();
		Long domainId = Long.parseLong(request.getParameter("id"));
		try {
			return util.getJsonResult(jobsDetailDAOForDownstreamApp.getJobDetails(domainId));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/excludeInterceptor/getJobDetailsByJobId")
	public @ResponseBody String getJobDetailsByJobId(HttpServletRequest request) {
		Utility util = new Utility();
		long jobId = 0;
		try {
			if (request.getParameter("jobId") != null && request.getParameter("jobId").trim() != "") {
				jobId = Long.parseLong(request.getParameter("jobId"));
			}
			JobDetail jobDetail = jobsDetailDAOForDownstreamApp.getJobById(jobId);
			jobDetail.setJobAddress(addressDetailsDAO.getAddressDetails(jobId, ConstantUtil.MODULE_NAME_FOR_JOB_TYPE));
			jobDetail.setUpdatedByName(jobDetail.getUpdatedByName());
			jobDetail.setSubmittedByName(jobDetail.getSubmittedByName());
			jobDetail.setDepartmentname(jobDetail.getDepartmentname());
			jobDetail.setAutoApprovalLimit(10000d);// Hardcoding for testing.
			return util.getJsonResultExposingTransientField(jobDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/excludeInterceptor/getJobNamesByDomainId")
	public @ResponseBody String getJobNamesByDomainId(HttpServletRequest request) {
		Utility util = new Utility();
		long domainId = 0;
		try {
			if (request.getParameter("domainId") != null && request.getParameter("domainId").trim() != "") {
				domainId = Long.parseLong(request.getParameter("domainId"));
			}
			ArrayList<String> jobDetail = jobsDetailDAOForDownstreamApp.getJobNamesByDomainId(domainId);
			return util.getJsonResult(jobDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	/**
	 * This method is to get the Active Job List based on User Role and User ID.
	 * 
	 * @param request
	 *            , userId & userRole - Get Parameter
	 * @return List of Job Bean in json format
	 */
	@RequestMapping(value = "/excludeInterceptor/getActiveJobListByUserRoleAndID", method = RequestMethod.GET)
	public @ResponseBody String getActiveJobListByUserRoleAndID(HttpServletRequest request, @RequestParam(value = "userId") String userId, @RequestParam(value = "userRole") String userRole)
			throws Exception {

		return util.getJsonResult(jobsDetailService.getActiveJobListByUserRoleAndID(userId, userRole));
	}

	/**
	 * This method is to get the In-Active Job List based on User Role and User
	 * ID.
	 * 
	 * @param request
	 *            , userId & userRole - Get Parameter
	 * @return List of Job Bean in json format
	 */
	@RequestMapping(value = "/excludeInterceptor/getInActiveJobListByUserRoleAndID", method = RequestMethod.GET)
	public @ResponseBody String getInActiveJobListByUserRoleAndID(HttpServletRequest request, @RequestParam(value = "userId") String userId, @RequestParam(value = "userRole") String userRole)
			throws Exception {

		return util.getJsonResult(jobsDetailService.getInActiveJobListByUserRoleAndID(userId, userRole));
	}

	/**
	 * This method is to get the Closed Job List based on User Role and User ID.
	 * 
	 * @param request
	 *            , userId & userRole - Get Parameter
	 * @return List of Job Bean in json format
	 */
	@RequestMapping(value = "/excludeInterceptor/getClosedJobListByUserRoleAndID", method = RequestMethod.GET)
	public @ResponseBody String getClosedJobListByUserRoleAndID(HttpServletRequest request, @RequestParam(value = "userId") String userId, @RequestParam(value = "userRole") String userRole)
			throws Exception {

		return util.getJsonResult(jobsDetailService.getClosedJobListByUserRoleAndID(userId, userRole));
	}

	/**
	 * This method is to get the Duplicate Job List based on User Role and User
	 * ID.
	 * 
	 * @param request
	 *            , userId & userRole - Get Parameter
	 * @return List of Job Bean in json format
	 */
	@RequestMapping(value = "/excludeInterceptor/getDuplicateJobListByUserRoleAndID", method = RequestMethod.GET)
	public @ResponseBody String getDuplicateJobListByUserRoleAndID(HttpServletRequest request, @RequestParam(value = "userId") String userId, @RequestParam(value = "userRole") String userRole)
			throws Exception {

		return util.getJsonResult(jobsDetailService.getDuplicateJobListByUserRoleAndID(userId, userRole));
	}

	/**
	 * This method is to get the Job Detail based on Job ID.
	 * 
	 * @param request
	 *            , jobId - Get Parameter
	 * @return Job Bean in json format
	 */
	@RequestMapping(value = "/excludeInterceptor/getJobDetailByJobId", method = RequestMethod.GET)
	public @ResponseBody String getJobDetailByJobId(HttpServletRequest request, @RequestParam(value = "jobId") String jobIdStr) throws Exception {

		return util.getJsonResult(jobsDetailService.getJobDetailByJobId(jobIdStr));
	}

	/**
	 * This method is to update Days Out Data based on Job ID.
	 * 
	 * @param request
	 *            , CashFlowCollectionRetentionBean in String format - Get
	 *            Parameter
	 * @return String (Success/Failure)
	 * 
	 * @Ashutosh
	 */
	@RequestMapping(value = "/excludeInterceptor/updateDaysOutDataForProject", method = RequestMethod.GET)
	public @ResponseBody String updateDaysOutDataForProject(HttpServletRequest request, @RequestParam("jsonDataForDaysOut") String jsonDataForDaysOut) throws Exception {
		return util.getJsonResult(jobsDetailService.updateDaysOutDataForProject(jsonDataForDaysOut));
	}

}
