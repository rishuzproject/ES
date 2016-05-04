package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.dao.SovReviewsTableDAO;

@Repository
public class SovReviewsTableDAOImpl implements SovReviewsTableDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public String addUpdateSOVItemReview(SovReviewsTable sovReviewsTable) throws Exception{
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(sovReviewsTable);
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
	public ArrayList<SovReviewsTable> getSOVReviewsDetails(long itemNo) throws Exception {

		ArrayList<SovReviewsTable> sovReviewsList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovReviewsTable where ITEM_NO=:itemNo")
					.setParameter("itemNo", itemNo);
			sovReviewsList = (ArrayList<SovReviewsTable>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovReviewsList;
	}
}
