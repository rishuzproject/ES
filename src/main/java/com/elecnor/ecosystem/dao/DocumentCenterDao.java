package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.DocumentCenter;

public interface DocumentCenterDao {

	public String setDocumentDetailsSave(DocumentCenter documentCenterBean) throws Exception;
	public ArrayList<DocumentCenter> getDocumentsList(Long domainId) throws Exception;
	public String deleteDocumentById(long documentId) throws Exception;
	public DocumentCenter setDocumetDowload(long descId) throws Exception;
}
