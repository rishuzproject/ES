package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationPurchasePlanDAO;
import com.elecnor.ecosystem.service.ApplicationPurchasePlanService;

@Service
public class ApplicationPurchasePlanServiceImpl implements ApplicationPurchasePlanService {

	@Autowired
	ApplicationPurchasePlanDAO applicationPurchasePlanDAO;

	public String setAddPurchasePlan(ApplicationPlanDirectory applicationPlan,
			ApplicationDirectory applicationDirectory, UserDetail userDetail) throws Exception {

		String result = "";
		applicationPlan.setApplicationDirectory(applicationDirectory);
		if (!(applicationPlan.getPlanId() == -1)) {
			applicationPlan.setUpdatedBy(userDetail);
			applicationPlan.setUpdatedDate(new Date());
			result = applicationPurchasePlanDAO.updatePurchasePlanDAO(applicationPlan);
		} else {
			applicationPlan.setSubmittedBy(userDetail);
			applicationPlan.setSubmittedDate(new Date());
			applicationPlan.setStatus("ACTIVE");
			result = applicationPurchasePlanDAO.setAddPurchasePlanDAO(applicationPlan);
		}
		return result;
	}
}
