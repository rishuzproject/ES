package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DomainApplicationPlanMapping;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingDAO;

@Repository
public class DomainApplicationPlanMappingDAOImpl implements DomainApplicationPlanMappingDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public DomainApplicationPlanMapping addDomainApplicationPlanMappingDetails(DomainApplicationPlanMapping domainApplicationPlanMapping) throws Exception {


		DomainApplicationPlanMapping domainApplicationPlanMappingBean = null;
		try {
			Session hibses = sessionFactory.getCurrentSession();
			domainApplicationPlanMappingBean = (DomainApplicationPlanMapping) hibses.merge(domainApplicationPlanMapping);
		} catch (Exception e) {
			// result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return domainApplicationPlanMappingBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public DomainApplicationPlanMapping getPlanIfExistForDomain(Long domainId, Long applicationId) throws Exception {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from DomainApplicationPlanMapping where DOMAIN_ID =:domainId and APPLICATION_ID =:applicationId");
		query.setParameter("domainId", domainId);
		query.setParameter("applicationId", applicationId);
		ArrayList<DomainApplicationPlanMapping> details = (ArrayList<DomainApplicationPlanMapping>) query.list();
		if (details != null && details.size() > 0) {
			return details.get(0);
		} else {
			return null;
		}
		}


	@Override
	@Transactional
	public String updateDomainApplicationPlanMappingDetails(DomainApplicationPlanMapping domainApplicationPlanMapping, Long subscriptionId) throws Exception {
		String result = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"update DomainApplicationPlanMapping set PLAN_START_DATE=:startDate,PLAN_EXPIRY_DATE=:endDate,UPDATED_DATE=:updatedDate,"

							+ "UPDATED_BY=:updatedBy where SUBSCRIPTION_ID=:subId");
			query.setParameter("startDate", domainApplicationPlanMapping.getPlanStartDate());
			query.setParameter("endDate", domainApplicationPlanMapping.getPlanExpiryDate());
			query.setParameter("updatedDate", domainApplicationPlanMapping.getUpdatedDate());
			query.setParameter("updatedBy", domainApplicationPlanMapping.getUpdatedBy());
			query.setParameter("subId", subscriptionId);
			query.executeUpdate();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<DomainApplicationPlanMapping> getDetails(Long domainId) throws Exception {
		ArrayList<DomainApplicationPlanMapping> details = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from DomainApplicationPlanMapping " + "where DOMAIN_ID =:domainId and PLAN_EXPIRY_DATE >= CURDATE()");
			query.setParameter("domainId", domainId);
			//
			details = (ArrayList<DomainApplicationPlanMapping>) query.list();
			System.out.println(query.list().size());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return details;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<DomainApplicationPlanMapping> getPlanDetailsBySubscriptionId(String subscriptionId) throws Exception {
		ArrayList<DomainApplicationPlanMapping> details = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from DomainApplicationPlanMapping " + "where SUBSCRIPTION_ID =:subscriptionId and PLAN_EXPIRY_DATE >= CURDATE()");

			query.setParameter("subscriptionId", subscriptionId);
			details = (ArrayList<DomainApplicationPlanMapping>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return details;
	}

}
