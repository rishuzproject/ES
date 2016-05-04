package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DomainApplicationPlanMappingTracking;
import com.elecnor.ecosystem.dao.DomainApplicationPlanMappingTrackingDAO;

@Repository
public class DomainApplicationPlanMappingTrackingDAOImpl implements DomainApplicationPlanMappingTrackingDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String addUpdateTrackingDetails(
			DomainApplicationPlanMappingTracking domainApplicationPlanMappingTracking) throws Exception{
		String result=null;
		try{
			Session hibses=sessionFactory.getCurrentSession();
			hibses.merge(domainApplicationPlanMappingTracking);
		}catch(Exception e){
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<DomainApplicationPlanMappingTracking> getDetailsFromTracking(
			Long domainId) throws Exception {
		ArrayList<DomainApplicationPlanMappingTracking> details = null;
		try{
		Query query = sessionFactory.getCurrentSession().createQuery("from DomainApplicationPlanMappingTracking "
				+ "where DOMAIN_ID =:domainId");
		query.setParameter("domainId", domainId);
		details = (ArrayList<DomainApplicationPlanMappingTracking>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return details;
	}

}
