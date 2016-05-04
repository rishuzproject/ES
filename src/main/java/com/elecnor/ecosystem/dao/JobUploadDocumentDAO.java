package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.JobUploadDocument;

public interface JobUploadDocumentDAO {
	
	public JobUploadDocument saveDocumentIds(JobUploadDocument jobUploadDocument);
	
	public Integer updateDocumentIds(JobUploadDocument jobUploadDocument);
	
	public List<JobUploadDocument> getUploadDocumentIds(Integer id);
	
	public JobUploadDocument getJobUploadDocument(String fieldName,Integer id);
	
	public Integer deleteDocumentId(String ids);

}
