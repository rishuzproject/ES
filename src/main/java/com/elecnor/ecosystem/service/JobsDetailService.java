/**
 * 
 */
package com.elecnor.ecosystem.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.UserDetail;

/**
 * @author Ashutosh
 */
public interface JobsDetailService {

	public JobDetail getJobBean(long jobId) throws Exception;

	public JobDetail saveJobDetails(Long domainDetailID, JobDetail jobDetail, Long jobCustomer, Long jobType, Long jobForeman, Long jobManager, Long jobSupervisor, Long jobExecutive,
			Long jobPurchasingAgent, Long vendorsList, Integer jobDepartmentType, Long contractor, String status, DomainDetail domainDetail, long userId, String sovType, Long jobAccountant,
			Long jobWarehouseManager, Long jobSuperindent, String jobEstimator, UserDetail userDetails, HttpServletRequest request) throws Exception;

	// public HashMap<String, Object> uploadJobFile(MultipartFile fileUploaded,
	// HttpSession session,
	// int confirmJobUploadId) throws Exception;

	public String setJobToDuplicate(Long jobId, UserDetail logedUserDetails) throws Exception;
	
	public String getEmailIds(List<UserDetail> users);

	public HashMap<Object, Object> getRoleBasedUserDetails(Long domainId) throws Exception;

	// Methods added by Vaibhav
	public List<JobDetail> getActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public List<JobDetail> getInActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public List<JobDetail> getClosedJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public List<JobDetail> getDuplicateJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public JobDetail getJobDetailByJobId(String jobId) throws Exception;

	// End by Vaibhav

	public List<JobDetail> getAggregateReportListByJobId(String query) throws Exception;

	public String updateJobDetails(JobDetail jobDetail, Long jobCustomer, Long jobType, long jobForeman, long jobManager, long jobSupervisor, long jobExecutive, long jobPurchaIntegersingAgent,
			Long vendorsList, Integer jobDepartmentType, Long contractor, String status, DomainDetail domainDetail, String startDate, String endDate, String cODate, String extendedDate,
			String description, Long userId, String sovType, long jobAccountant, long jobWarehouseManager, long jobSuperindent, String jobEstimator, UserDetail userDetails, HttpServletRequest request)
			throws Exception;

	// method to update days out data if the data is updated in MPR @Ashutosh
	public String updateDaysOutDataForProject(String jsonString) throws Exception;
}
