package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.DomainApplicationPlanMappingTracking;

public interface DomainApplicationPlanMappingTrackingDAO {

	String addUpdateTrackingDetails(
			DomainApplicationPlanMappingTracking domainApplicationPlanMappingTracking) throws Exception;

	ArrayList<DomainApplicationPlanMappingTracking> getDetailsFromTracking(
			Long domainId) throws Exception;

}
