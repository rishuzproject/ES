package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.bean.UserDetail;

public interface ApplicationPurchasePlanService {

	public String setAddPurchasePlan(ApplicationPlanDirectory applicationPlan,
			ApplicationDirectory applicationDirectory, UserDetail userDetail) throws Exception;
}
