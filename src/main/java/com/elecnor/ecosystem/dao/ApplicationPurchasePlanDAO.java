package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;

public interface ApplicationPurchasePlanDAO {
	
	public String setAddPurchasePlanDAO(ApplicationPlanDirectory applicationPlan) throws Exception;

	public ArrayList<ApplicationPlanDirectory> getPurchasePlanDAO() throws Exception;

	public String updatePurchasePlanDAO(ApplicationPlanDirectory applicationPlan) throws Exception;

	public String deletePlan(int planId) throws Exception;

	public ArrayList<ApplicationPlanDirectory> getApplicationPlanDetails(long userId) throws Exception;

	public ApplicationPlanDirectory getApplicationPlanDetailsByPlanId(long planId) throws Exception;

	ApplicationPlanDirectory getPurchasePlanDAO(long planId) throws Exception;

}
