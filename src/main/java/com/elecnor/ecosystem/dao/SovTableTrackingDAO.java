package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.SovTableTracking;

public interface SovTableTrackingDAO {

	public ArrayList<SovTableTracking> getSovTrackingList(long itemNo) throws Exception;

	public String addNewTrackingEntry() throws Exception;
}
