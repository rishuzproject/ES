package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.JobDetail;

public interface JobsDetailDAOForDownstreamApp {

	public ArrayList<JobDetail> getJobDetails(Long domainId) throws Exception;
	
	public JobDetail getJobById(long jobId);
	
	public ArrayList<String> getJobNamesByDomainId(long domainId) throws Exception;
}
