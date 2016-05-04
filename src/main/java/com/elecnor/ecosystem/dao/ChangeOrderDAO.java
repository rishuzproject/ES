package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.RfcLogTracking;
import com.elecnor.ecosystem.bean.RfcSubCostTypeBean;
import com.elecnor.ecosystem.bean.RfcTakeoffSheet;

public interface ChangeOrderDAO {

	int reOpenApprovedRfc(int rfcNumber) throws Exception;

	String getLastOrinalReferenceNumberBasedOnType(String hqlQuery) throws Exception;

	RfcLog getRfcDetails(int sNo) throws Exception;

	List<RfcLogTracking> getRfcTrackingDetails(int rfcLogId) throws Exception;

	void insertRfcTracking(RfcLogTracking rfcBeanTracking) throws Exception;

	String deleteRfcLogList(int sNo, boolean status, int mappingId) throws Exception;

	String deleteRestoreRfcLogList(int sNo, boolean status) throws Exception;

	RfcLog insertOrUpdateRfcLogList(RfcLog rfcLog) throws Exception;

	ArrayList<RfcLog> getAllRfcLogList(int projId) throws Exception;
	
	public List<RfcSubCostTypeBean> getSubcostList(int s_no) throws Exception;
	
	public void addRfcTakeSheet(RfcTakeoffSheet rfcTakeoffSheet);
	
	public ArrayList<RfcTakeoffSheet> getAllTakeSheetsList(Long jobId);
	
	public ArrayList<RfcLog> getAllRfcLogs() throws Exception;

	public List<RfcLog> getAllActiveRfcLogListForProject(int parseInt) throws Exception;

	public List<RfcLog> getChangeOrderListByJobId(String jobIdStr) throws Exception;

	

}
