package com.elecnor.ecosystem.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.TemporaryUserDetail;
import com.elecnor.ecosystem.dao.TemporaryUserDetailDAO;

@Repository
public class TemporaryUserDetailDAOImpl implements TemporaryUserDetailDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String customerLinkActivation(TemporaryUserDetail temporaryUserDetail) throws Exception{
		
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(temporaryUserDetail);
		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	@Transactional
	public TemporaryUserDetail getTemporaryUserDetails(long sovId, String customerEmailId) throws Exception {
		
		TemporaryUserDetail temporaryUserDetail = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from TemporaryUserDetail where SOV_ID=:sovId and USER_EMAIL_ID=:customerEmailId");
			query.setParameter("sovId", sovId);
			query.setParameter("customerEmailId", customerEmailId);
			temporaryUserDetail = (TemporaryUserDetail) query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return temporaryUserDetail;
	}
}
