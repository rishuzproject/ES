package com.elecnor.ecosystem.service;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;

public interface DomainApplicationPlanMappingService {

	public ArrayList<DomainApplicationPlanMapping> getApplicationPlans(Long domainId) throws Exception;
	public ArrayList<ApplicationDirectory> getAllApplications(Long domainId) throws Exception;
}
