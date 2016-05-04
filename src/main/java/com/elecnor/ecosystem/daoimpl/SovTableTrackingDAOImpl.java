package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.SovTableTracking;
import com.elecnor.ecosystem.dao.SovTableTrackingDAO;

@Repository
public class SovTableTrackingDAOImpl implements SovTableTrackingDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<SovTableTracking> getSovTrackingList(long itemNo) throws Exception {

		ArrayList<SovTableTracking> sovTrackingList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovTableTracking where ITEM_NO=:itemNo")
					.setParameter("itemNo", itemNo);
			sovTrackingList = (ArrayList<SovTableTracking>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovTrackingList;
	}

	@Override
	@Transactional
	public String addNewTrackingEntry() throws Exception {
		
		String result = null;
		try {
			Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO SOV_TABLE_TRACKING(ITEM_NO,SOV_ID,WORK_DESCRIPTION) SELECT ITEM_NO,SOV_ID,WORK_DESCRIPTION FROM SOV_TABLE");
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
