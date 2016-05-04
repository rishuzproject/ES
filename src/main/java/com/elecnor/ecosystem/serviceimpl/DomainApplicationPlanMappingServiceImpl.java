package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;
import com.elecnor.ecosystem.service.DomainApplicationPlanMappingService;

@Service
public class DomainApplicationPlanMappingServiceImpl implements DomainApplicationPlanMappingService{

	@Autowired
	DomainApplicationPlanMappingDAO appPlanMappingDAO;
	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;
	
	@Override
	public ArrayList<DomainApplicationPlanMapping> getApplicationPlans(Long domainId) throws Exception {
		
		ArrayList<DomainApplicationPlanMapping> appPlansList = appPlanMappingDAO.getDetails(domainId);
		System.out.println("Service controller "+appPlansList.size());
		return appPlansList;
	}

	@Override
	public ArrayList<ApplicationDirectory> getAllApplications(Long domainId)
			throws Exception {
		ArrayList<ApplicationDirectory> allApplications = applicationDetailsDAO.getApplicationDetails();
		return allApplications;
	}

}
