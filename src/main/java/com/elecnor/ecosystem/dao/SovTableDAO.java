package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.SovTable;

public interface SovTableDAO {

	public String addUpdateItems(SovTable sovTable) throws Exception;

	public ArrayList<SovTable> getSOVDetailsFromSOVTable(long sovId) throws Exception;

	public ArrayList<SovTable> getItemDetailsFromSOV(long itemNo) throws Exception;
	
	public String setApprovalSOVItem(Long itemNo, Boolean approvalStatus) throws Exception;

	public String deleteSOVItem(long itemNo) throws Exception;

	public String setExternalApprovalSOVItem(long itemNo, boolean status) throws Exception;

	public String setSOVExternalRequestStatus(Long sovId, String status) throws Exception;

	public int checkApprovalOfAllItems(Long sovId) throws Exception;
	ArrayList<SovTable> getAllItemDetailsFromSOV() throws Exception;

	public ArrayList<SovTable> getSOVDetailsFromSOVTableForProject(long parseLong) throws Exception;

}
