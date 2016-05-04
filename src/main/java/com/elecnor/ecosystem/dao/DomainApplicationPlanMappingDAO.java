package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;

public interface DomainApplicationPlanMappingDAO {

	DomainApplicationPlanMapping addDomainApplicationPlanMappingDetails(DomainApplicationPlanMapping domainApplicationPlanMapping) throws Exception;

	DomainApplicationPlanMapping getPlanIfExistForDomain(Long domainId,Long applicationId)throws Exception;

	String updateDomainApplicationPlanMappingDetails(DomainApplicationPlanMapping domainApplicationPlanMapping,Long subscriptionId)throws Exception;

	ArrayList<DomainApplicationPlanMapping> getDetails(Long domainId)throws Exception;
	
	 public ArrayList<DomainApplicationPlanMapping> getPlanDetailsBySubscriptionId(String subscriptionId)throws Exception;

}
