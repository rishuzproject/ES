package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.SovCommentsTable;
import com.elecnor.ecosystem.dao.SovCommentsTableDAO;

@Repository
public class SovCommentsTableDAOImpl implements SovCommentsTableDAO{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String addSOVComment(SovCommentsTable sovComment) throws Exception{
		
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(sovComment);
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
	public ArrayList<SovCommentsTable> getAllSOVComments(Long sovId) throws Exception {
		
		ArrayList<SovCommentsTable> sovCommentsDetail = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovCommentsTable where SOV_ID=:sovId")
					.setParameter("sovId", sovId);
			sovCommentsDetail = (ArrayList<SovCommentsTable>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovCommentsDetail;
	}
}
