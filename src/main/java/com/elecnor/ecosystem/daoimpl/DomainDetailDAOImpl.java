package com.elecnor.ecosystem.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.dao.DomainDetailDAO;

@Repository
public class DomainDetailDAOImpl implements DomainDetailDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<DomainDetail> getDomainDetail() throws Exception{
		
		ArrayList<DomainDetail> allDomainDetails = null;
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("from DomainDetail where STATUS = 'ACTIVE'");
			allDomainDetails = (ArrayList<DomainDetail>)query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			throw e;
		}
		return allDomainDetails;
	}

	//this is used to save the domain details
	@Override
	@Transactional
	public DomainDetail saveDomainDetail(DomainDetail domainDetail) throws Exception{
		DomainDetail savedDomDetails = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			savedDomDetails = (DomainDetail) hibSes.merge(domainDetail);
		} catch (HibernateException e) {
			savedDomDetails = null;
			e.printStackTrace();
			throw e;
		}
		return savedDomDetails;
	}

	//this is used to update the domain details
	@Override
	@Transactional
	public String updateDomainDetail(DomainDetail domainDetail, Long domainId) throws Exception{
		String result = null;
		try{
			Query query= sessionFactory.getCurrentSession().createQuery("update DomainDetail set DOMAIN_NAME=:domainName,"
					+ "COMPANY_NAME=:companyName,COMPANY_ADDRESS=:companyAddress,SUBMITTED_EMAIL_ID =:submittedEmailId,SUBMITTED_NAME=:submittedName,"
					+ "SUBMITTED_USER_ID = :submittedUserId, UPDATED_DATE = :updatedDate,COMPANY_PHONE_CARRIER=:companyPhoneCarrier, COMPANY_PHONE_NUMBER=:companyPhoneNumber"
					+ " where  DOMAIN_ID =:domainId");
			query.setParameter("domainName", domainDetail.getDomainName());
			query.setParameter("companyName", domainDetail.getCompanyName());
			query.setParameter("companyAddress", domainDetail.getCompanyAddress());
			query.setParameter("companyPhoneCarrier", domainDetail.getCompanyPhoneCarrier());
			query.setParameter("companyPhoneNumber", domainDetail.getCompanyPhoneNumber());
			query.setParameter("submittedUserId", domainDetail.getSubmittedUserId());
			query.setParameter("submittedEmailId", domainDetail.getSubmittedEmailId());
			query.setParameter("submittedName", domainDetail.getSubmittedName());
			query.setParameter("updatedDate", domainDetail.getUpdatedDate());
			query.setParameter("domainId", domainId);
			query.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	//method for checking domain name is existed
	@SuppressWarnings({ "unchecked", "null" })
	@Override
	@Transactional
	public boolean isDomainNameExist(String domainName, Long domainId) throws Exception{
		ArrayList<DomainDetail> domainDetail = null;
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("from DomainDetail where DOMAIN_NAME=:domainName");
			query.setParameter("domainName", domainName );
//			query.setParameter("domainId", domainId);
			domainDetail = (ArrayList<DomainDetail>)query.list();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		if(domainDetail != null || domainDetail.size() == 1){
			return false;
		}else{
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<DomainDetail> getDomainDetail(long domainId) throws Exception{
		ArrayList<DomainDetail> allDomainDetails = null;
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("from DomainDetail where STATUS = 'ACTIVE' and DOMAIN_ID=:domainId");
			query.setParameter("domainId", domainId);
			allDomainDetails = (ArrayList<DomainDetail>)query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			throw e;
		}
		return allDomainDetails;
	}
	
	@Transactional
	@Override
	public DomainDetail getExposeDomainBean(BigInteger selectedDomainId) throws Exception{
		
		DomainDetail domainBean = null;
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("from DomainDetail where STATUS = 'ACTIVE' and DOMAIN_ID=:domainId");
			query.setParameter("domainId", selectedDomainId);
			domainBean = (DomainDetail)query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			throw e;
		}
		return domainBean;
	}
}
