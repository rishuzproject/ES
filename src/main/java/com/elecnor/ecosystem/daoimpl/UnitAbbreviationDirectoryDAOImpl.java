package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.UnitAbbreviationDirectory;
import com.elecnor.ecosystem.dao.UnitAbbreviationDirectoryDAO;
import com.elecnor.ecosystem.util.Utility;

@Repository
public class UnitAbbreviationDirectoryDAOImpl implements UnitAbbreviationDirectoryDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public String addUnitAbbreviation(UnitAbbreviationDirectory unitAbbreviationBean) throws Exception{
		
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(unitAbbreviationBean);
		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String updateUnitAbbreviation(UnitAbbreviationDirectory unitAbbreviationBean) throws Exception{
		
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update UnitAbbreviationDirectory set ABBREVIATION_NAME=:abbreviationName,ABBREVIATION_MEANING=:abbreviationMeaning,UPDATED_BY=:updatedBy,UPDATED_DATE=:updatedDate where ABBREVIATION_ID=:abbreviationId");
			query.setParameter("abbreviationName", unitAbbreviationBean.getAbbreviationName());
			query.setParameter("abbreviationMeaning", unitAbbreviationBean.getAbbreviationMeaning());
			query.setParameter("updatedBy", unitAbbreviationBean.getUpdatedBy());
			query.setParameter("updatedDate", unitAbbreviationBean.getUpdatedDate());
			query.setParameter("abbreviationId", unitAbbreviationBean.getAbbreviationId());
			query.executeUpdate();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<UnitAbbreviationDirectory> getAllAbbreviationsDAO(Long domainId) throws Exception {
		
		ArrayList<UnitAbbreviationDirectory> unitAbbreviationList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from UnitAbbreviationDirectory where DOMAIN_ID=:domainId and STATUS=:status ORDER BY SERIAL_NO")
					.setParameter("domainId", domainId)
					.setParameter("status","ACTIVE");
			unitAbbreviationList = (ArrayList<UnitAbbreviationDirectory>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return unitAbbreviationList;
	}
	
	@Override
	@Transactional
	public int getLastSerialNo(Long domainId) throws Exception {
		Utility util = new Utility();
		Object maxSerialNo;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT MAX(SERIAL_NO) FROM UNIT_ABBREVIATION_DIRECTORY where DOMAIN_ID=:domainId");
			query.setParameter("domainId", domainId);
			maxSerialNo = query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		//return 0 if value returned is null(when no entries are present in database)
		return util.checkForNullValueFromHibernate(maxSerialNo);
	}
	
	@Override
	@Transactional
	public String deleteUnitAbbreviation(Long abbreviationId) throws Exception {
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("update UnitAbbreviationDirectory set STATUS='INACTIVE' where ABBREVIATION_ID=:abbreviationId");
			query.setParameter("abbreviationId",abbreviationId);
			query.executeUpdate();
		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
