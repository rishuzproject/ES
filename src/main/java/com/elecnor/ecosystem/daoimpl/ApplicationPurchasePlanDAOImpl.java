package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.ApplicationPlanDirectory;
import com.elecnor.ecosystem.dao.ApplicationPurchasePlanDAO;

@Repository
public class ApplicationPurchasePlanDAOImpl implements ApplicationPurchasePlanDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	
	public String setAddPurchasePlanDAO(ApplicationPlanDirectory applicationPlan) throws Exception{
		
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(applicationPlan);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationPlanDirectory> getPurchasePlanDAO() throws Exception{
		
			ArrayList<ApplicationPlanDirectory> allPlanList = null;
			try {
				Query query = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from ApplicationPlanDirectory where STATUS='ACTIVE'");
				allPlanList = (ArrayList<ApplicationPlanDirectory>)query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}
			return allPlanList;
	}
	@Override
	@Transactional
	public ApplicationPlanDirectory getPurchasePlanDAO(long planId) throws Exception{
			ApplicationPlanDirectory planDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from ApplicationPlanDirectory where STATUS='ACTIVE' AND PLAN_ID=:planId");
			query.setParameter("planId", planId);
			planDetails = (ApplicationPlanDirectory) query.list().get(0);
		} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}
			return planDetails;
	}
	
	@Override
	@Transactional
	public String updatePurchasePlanDAO(ApplicationPlanDirectory applicationPlan) throws Exception{
		
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("update ApplicationPlanDirectory set APPLICATION_ID=:applicationId, UPDATED_BY=:updatedBy,UPDATED_DATE=:updatedDate,MAX_NUMBER_OF_USER=:maxNumberOfUser,PLAN_VALIDITY_IN_MONTHS=:planValidityInMonths,PRICE=:price,DATA_USAGE=:dataUsage,PLAN_NAME=:planName,PLAN_DESCRIPTION=:planDescription,PLAN_ADDITIONAL_DESCRIPTION=:planAdditionalDescription where PLAN_ID=:planId");
			query.setParameter("applicationId", applicationPlan.getApplicationDirectory());
			query.setParameter("updatedBy",applicationPlan.getUpdatedBy());
			query.setParameter("updatedDate",applicationPlan.getUpdatedDate());
			query.setParameter("maxNumberOfUser",applicationPlan.getMaxNumberOfUser());
			query.setParameter("dataUsage", applicationPlan.getDataUsage());
			query.setParameter("planValidityInMonths",applicationPlan.getPlanValidityInMonths());
			query.setParameter("price",applicationPlan.getPrice());
			query.setParameter("planName",applicationPlan.getPlanName());
			query.setParameter("planDescription",applicationPlan.getPlanDescription());
			query.setParameter("planAdditionalDescription",applicationPlan.getPlanAdditionalDescription());
			query.setParameter("planId",applicationPlan.getPlanId());
			query.executeUpdate();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	@Transactional
	public String deletePlan(int planId) throws Exception{
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update ApplicationPlanDirectory set STATUS='INACTIVE' where PLAN_ID=:planId");
			query.setParameter("planId", planId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<ApplicationPlanDirectory> getApplicationPlanDetails(long userId) throws Exception{
		ArrayList<ApplicationPlanDirectory> allPlanList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from ApplicationPlanDirectory where STATUS='ACTIVE' and SUBMITTED_BY=:userId");
			query.setParameter("userId", userId);
			allPlanList = (ArrayList<ApplicationPlanDirectory>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allPlanList;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ApplicationPlanDirectory getApplicationPlanDetailsByPlanId(long planId) throws Exception{
		ApplicationPlanDirectory selectedPlan = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from ApplicationPlanDirectory where PLAN_ID=:planId");
			query.setParameter("planId", planId);
			selectedPlan = (ApplicationPlanDirectory)query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return selectedPlan;
	}
}

