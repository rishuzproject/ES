package com.elecnor.ecosystem.service;

import java.util.List;

import com.elecnor.ecosystem.bean.JobUploadDocument;


public interface JobUploadDocumentService {
	
	public JobUploadDocument saveDocumentIds(List<Integer> fileIds,String fieldName, Integer jobId);
	
	public Integer updateDocumentIds(Integer docId,String fieldName,List<Integer> fileIds);
	
	public List<JobUploadDocument> getUploadDocumentIds(Integer id);
	
	public JobUploadDocument getJobUploadDocument(String fieldName,Integer id);
	
	public Integer deleteDocumentId(String jobId,String docId,String fieldName);

}
