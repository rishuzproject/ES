package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.JobUploadDocument;
import com.elecnor.ecosystem.dao.JobUploadDocumentDAO;
import com.elecnor.ecosystem.service.JobUploadDocumentService;

@Service
public class JobUploadDocumentServiceImpl implements JobUploadDocumentService {

	@Autowired
	JobUploadDocumentDAO jobUploadDao;

	@Override
	public JobUploadDocument saveDocumentIds(List<Integer> fileIdList, String fieldName, Integer jobId) {
		StringBuilder fileIds = new StringBuilder();
		for (Integer i : fileIdList) {
			fileIds.append(i);
			fileIds.append(",");
		}
		fileIds = fileIds.deleteCharAt(fileIds.lastIndexOf(","));
		JobUploadDocument jobUploadDocument = new JobUploadDocument();
		jobUploadDocument.setAppUploadFileId(fileIds.toString());
		jobUploadDocument.setAssociatedField(fieldName);
		jobUploadDocument.setJobId(jobId);

		return jobUploadDao.saveDocumentIds(jobUploadDocument);
	}

	@Override
	public List<JobUploadDocument> getUploadDocumentIds(Integer id) {
		return jobUploadDao.getUploadDocumentIds(id);
	}

	@Override
	public Integer updateDocumentIds(Integer docId, String prevIds, List<Integer> fileIdsList) {

		StringBuilder fileIds = new StringBuilder();
		for (Integer i : fileIdsList) {
			fileIds.append(i);
			fileIds.append(",");
		}
		if (prevIds.equalsIgnoreCase("DELETE")) {
			fileIds.deleteCharAt(fileIds.lastIndexOf(","));
		} else {
			fileIds = fileIds.append(prevIds);
		}

		JobUploadDocument jobUploadDocument = new JobUploadDocument();
		jobUploadDocument.setAppUploadFileId(fileIds.toString());
		jobUploadDocument.setId(docId);

		return jobUploadDao.updateDocumentIds(jobUploadDocument);
	}

	public List<String> getFileIdList(String fileIds) {

		List<String> fileIdList = new ArrayList<String>();
		if (fileIds.contains(",")) {
			fileIdList = Arrays.asList(fileIds.split(","));
		}else{
			fileIdList.add(fileIds);
		}

		return fileIdList;
	}

	@Override
	public JobUploadDocument getJobUploadDocument(String fieldName, Integer id) {

		return jobUploadDao.getJobUploadDocument(fieldName, id);
	}

	@Override
	public Integer deleteDocumentId(String jobId, String docId, String fieldName) {
		JobUploadDocument jobUploadDoc = getJobUploadDocument(fieldName, Integer.valueOf(jobId));
		Integer result = 0;
		List<Integer> idListInteger = new ArrayList<Integer>();
		if (null != jobUploadDoc) {
			List<String> idList = getFileIdList(jobUploadDoc.getAppUploadFileId());
			for (String i : idList) {
				if (i.equals(docId)) {
					continue;
				} else {
					idListInteger.add(Integer.valueOf(i));
				}
			}
			result = updateDocumentIds(jobUploadDoc.getId(), "DELETE", idListInteger);
		}

		return result;
	}

}
