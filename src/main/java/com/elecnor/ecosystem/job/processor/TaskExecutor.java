package com.elecnor.ecosystem.job.processor;

public interface TaskExecutor {

	public boolean ExecuteTaskForNewJob(String startDate, String endDate, String oldEndDt, String oldStartDt, boolean isUpdate,Long projectid,String submittedBy) throws Exception;
	
	public boolean ExecuteTaskForUpdateJob() throws Exception;
	
	public boolean ExecuteTaskForDeleteJob() throws Exception;
}
