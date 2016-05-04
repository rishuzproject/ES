package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;

@Repository
public class ApplicationDetailsDAOImpl implements ApplicationDetailsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private ArrayList<ApplicationDirectory> allApplicationDetails;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<ApplicationDirectory> getApplicationDetails() {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from ApplicationDirectory where STATUS='ACTIVE'");
			allApplicationDetails = (ArrayList<ApplicationDirectory>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allApplicationDetails;
	}

	@Override
	@Transactional
	public String addOrUpdateApplicationDetail(ApplicationDirectory applicationDirectoryBean) {

		String ex = null;
		Session ses = null;
		try {
			ses = (Session) sessionFactory.getCurrentSession();
			ses.merge(applicationDirectoryBean);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return ex;
	}

	@Override
	@Transactional
	public String deleteApplicationDetailsRecord(long appDetailId) {
		String ex = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"update ApplicationDirectory set STATUS='INACTIVE' where APPLICATION_ID=:appDetailId");
			query.setParameter("appDetailId", appDetailId);
			query.executeUpdate();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return ex;
	}

	@Override
	@Transactional
	public ApplicationDirectory getApplicationDetailsByApplicationId(int applicationId) throws Exception {
		ApplicationDirectory applicationDetailsBean = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from ApplicationDirectory where STATUS='ACTIVE' and APPLICATION_ID=:applicationId");
			query.setParameter("applicationId", applicationId);
			applicationDetailsBean = (ApplicationDirectory) query.list().get(0);

		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return applicationDetailsBean;
	}

	@Override
	@Transactional
	public ApplicationDirectory getApplicationDetailsByApplicationName(String applicationName) throws Exception {
		ApplicationDirectory applicationDetailsBean = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from ApplicationDirectory where STATUS='ACTIVE' and APPLICATION_NAME=:applicationName");
			query.setParameter("applicationName", applicationName);
			applicationDetailsBean = (ApplicationDirectory) query.list().get(0);

		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return applicationDetailsBean;
	}

}
