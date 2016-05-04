package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.dao.SovDirectoryDAO;

@Repository
public class SovDirectoryDAOImpl implements SovDirectoryDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String addUpdateSOV(SovDirectory sovDirectory) throws Exception {

		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(sovDirectory);
		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<SovDirectory> getSOVListFromDirectory(long projId) throws Exception {
		
		ArrayList<SovDirectory> sovDirectoryList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovDirectory where PROJECT_ID=:projectId and STATUS='ACTIVE'");
			query.setParameter("projectId", projId);
			sovDirectoryList = (ArrayList<SovDirectory>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovDirectoryList;
	}
	
	@Override
	@Transactional
	public String deleteSov(long sovId)
	{
		System.out.println("sov id is : "+sovId);
		String result = null;
		try {
				Query query = sessionFactory.getCurrentSession().createQuery("update SovDirectory set STATUS='INACTIVE' where SOV_ID=:sovId");
				query.setParameter("sovId", sovId);
				query.executeUpdate();
		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	@Transactional
	public String setSOVInternalApprovalStatus(Long sovId, String status) throws Exception {
		
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update SovDirectory set INTERNAL_APPROVAL_STATUS=:status where SOV_ID=:sovId");
			query.setParameter("sovId", sovId);
			query.setParameter("status", status);
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
	public SovDirectory getSOVDetails(long sovId) throws Exception {
		
		SovDirectory sovDirectory = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovDirectory where SOV_ID=:sovId");
			query.setParameter("sovId", sovId);
			sovDirectory = (SovDirectory) query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovDirectory;
	}
	
	@Override
	@Transactional
	public String approveSOVStatus(Long sovId, String approvalStatus) throws Exception {
		String result = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("update SovDirectory set INTERNAL_APPROVAL_STATUS=:approvalStatus where SOV_ID=:sovId");
			query.setParameter("sovId", sovId);
			query.setParameter("approvalStatus", approvalStatus);
			query.executeUpdate();
		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
