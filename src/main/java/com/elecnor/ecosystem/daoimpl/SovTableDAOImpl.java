package com.elecnor.ecosystem.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.SovTable;
import com.elecnor.ecosystem.dao.SovTableDAO;

@Repository
public class SovTableDAOImpl implements SovTableDAO {

	@Autowired
	SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.elecnor.ecosystem.dao.SovTableDAO#addUpdateItems(com.elecnor.ecosystem.bean.SovTable)
	 */
	@Override
	@Transactional
	public String addUpdateItems(SovTable sovTable) throws Exception {

		String result = null;
		SovTable sovBean = new SovTable();
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			sovBean = (SovTable) hibSes.merge(sovTable);
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
	public ArrayList<SovTable> getSOVDetailsFromSOVTable(long sovId) throws Exception {

		ArrayList<SovTable> sovItemList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovTable where SOV_ID=:sovId and STATUS='ACTIVE'")
					.setParameter("sovId", sovId);
			sovItemList = (ArrayList<SovTable>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovItemList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<SovTable> getSOVDetailsFromSOVTableForProject(long projectId) throws Exception {

		ArrayList<SovTable> sovItemList = null;
		try {
			SQLQuery query = (SQLQuery) sessionFactory.getCurrentSession().createSQLQuery("select * from SOV_TABLE where STATUS='ACTIVE' and SOV_ID IN (select SOV_ID from SOV_DIRECTORY where PROJECT_ID=:projectId and STATUS='ACTIVE')").addEntity(SovTable.class)
					.setParameter("projectId", projectId);
			sovItemList = (ArrayList<SovTable>) query.list();
			System.out.println("after query");
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovItemList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<SovTable> getItemDetailsFromSOV(long itemNo) throws Exception {
		
		ArrayList<SovTable> sovItemDetail = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovTable where ITEM_NO=:itemNo")
					.setParameter("itemNo", itemNo);
			sovItemDetail = (ArrayList<SovTable>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovItemDetail;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<SovTable> getAllItemDetailsFromSOV() throws Exception {
		
		ArrayList<SovTable> sovItemDetail = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from SovTable where STATUS='ACTIVE'");
					
			sovItemDetail = (ArrayList<SovTable>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return sovItemDetail;
	}
	
	
	@Override
	@Transactional
	public String setApprovalSOVItem(Long itemNo, Boolean approvalStatus) throws Exception {
		
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update SovTable set IS_APPROVED_INTERNAL=:approvalStatus where ITEM_NO=:itemNo");
			query.setParameter("itemNo", itemNo);
			query.setParameter("approvalStatus", approvalStatus);
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
	public String deleteSOVItem(long itemNo) throws Exception{
		
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update SovTable set STATUS='INACTIVE' where ITEM_NO=:itemNo");
			query.setParameter("itemNo", itemNo);
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
	public String setExternalApprovalSOVItem(long itemNo, boolean approvalStatus) throws Exception{
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update SovTable set IS_APPROVED_EXTERNAL=:approvalStatus where ITEM_NO=:itemNo");
			query.setParameter("itemNo", itemNo);
			query.setParameter("approvalStatus", approvalStatus);
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
	public String setSOVExternalRequestStatus(Long sovId, String status) throws Exception {
		
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update SovTable set EXTERNAL_REQUEST_STATUS=:status where SOV_ID=:sovId and IS_APPROVED_INTERNAL=:isApprovedInternal");
			query.setParameter("sovId", sovId);
			query.setParameter("status", status);
			query.setParameter("isApprovedInternal", true);
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
	public int checkApprovalOfAllItems(Long sovId) throws Exception{
		BigInteger noOfApprovedItems = BigInteger.ZERO;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT COUNT(*) FROM SOV_TABLE where SOV_ID=:sovId and STATUS='ACTIVE' and IS_APPROVED_INTERNAL<>TRUE");
			query.setParameter("sovId", sovId);
			noOfApprovedItems = (BigInteger) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return noOfApprovedItems.intValue();
	}
}
