package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.service.DomainApplicationPlanMappingService;
import com.elecnor.ecosystem.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService{

	@Autowired
	DomainApplicationPlanMappingService applicationPlanMappingService;
	
	public HashMap<Object, Object> getApplicationDetails(Boolean isBelcouser, DomainDetail domainDetail) throws Exception {
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			if (isBelcouser != null && isBelcouser) {
				ArrayList<ApplicationDirectory> appActiveApplications = applicationPlanMappingService
						.getAllApplications(domainDetail.getDomainId());
				resultMap.put("plans", appActiveApplications);
			} else {
				ArrayList<DomainApplicationPlanMapping> plans = applicationPlanMappingService
						.getApplicationPlans(domainDetail.getDomainId());
				resultMap.put("plans", plans);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return resultMap;
	}
}
