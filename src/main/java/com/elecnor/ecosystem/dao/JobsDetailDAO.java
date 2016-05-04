/**
 * 
 */
package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.JobDetail;

/**
 * @author Ashutosh
 */
public interface JobsDetailDAO {
	public ArrayList<JobDetail> getActiveJobDetails(Long domainId) throws Exception;

	public ArrayList<JobDetail> getPendingJobDetails(Long domainId) throws Exception;
	
	public ArrayList<JobDetail> getAllJobDetails(Long domainId) throws Exception;
	
	public JobDetail saveJob(JobDetail jobDetail) throws Exception;

	public String updateJob(JobDetail jobDetail, Long jobCustomer, Long jobType, Long jobForeman, Long jobManager, Long jobSupervisor, Long jobExecutive, Long jobPurchasingAgent, Long vendorsList,
			Integer jobDepartmentType, Long contractor, String status, DomainDetail domailDetail, String startDate, String endDate, String CODate, String extendedDate, String Description,
			long updatedBy, String sovType, Long jobAccountant, Long jobWarehouseManager, Long jobSuperindent, String jobEstimator) throws Exception;

	public String deleteJob(long jobId) throws Exception;

	public JobDetail getJobById(long jobId);

	public JobDetail getJobBean(long jobId) throws Exception;

	/*public boolean isJobNumExixsting(Long jobId, String jobNumber);*/

	public void deleteJobDatailsWhenExceptionInAPICall(long jobId);

	public void duplicateProjectRfc(long originalJobId, long duplicateJobId) throws Exception;

	public ArrayList<String> getJobNamesByDomainId(long domainId) throws Exception;

	public ArrayList<JobDetail> getJobDetailsInvolvingUser(Long userId, Long domainId) throws Exception;

	public ArrayList<JobDetail> getAggregateReportListByJobID(String query) throws Exception;

	// Methods Added by Vaibhav.
	public List<JobDetail> getActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public List<JobDetail> getInActiveJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public List<JobDetail> getClosedJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public List<JobDetail> getDuplicateJobListByUserRoleAndID(String userId, String userRole) throws Exception;

	public JobDetail getJobDetailByJobId(String jobId) throws Exception;

	// End by Vaibhav

	// method to update days out data if the data is updated in MPR @Ashutosh
	public int updateDaysOutDataForProject(JobDetail jobDetailDaysOut) throws Exception;

	boolean isJobNumExixsting(String jobNumber);

	public boolean isJobNumExixsting(String jobId, String jobNumber);

}
