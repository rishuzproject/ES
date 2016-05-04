package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.SovDirectory;

public interface SovDirectoryDAO {

	public String addUpdateSOV(SovDirectory sovDirectory) throws Exception;

	public ArrayList<SovDirectory> getSOVListFromDirectory(long projectId) throws Exception;

	String deleteSov(long sovId);

	public String setSOVInternalApprovalStatus(Long sovId, String status) throws Exception;
	
	public SovDirectory getSOVDetails(long sovId) throws Exception;

	public String approveSOVStatus(Long sovId, String approvalStatus) throws Exception;
}
