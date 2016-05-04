package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.LicenseDirectory;
import com.elecnor.ecosystem.dao.LicenseDirectoryDAO;

@Repository
public class LicenseDirectoryDAOImpl implements LicenseDirectoryDAO{
	@Autowired
	private SessionFactory sessionFactory;
	private ArrayList<LicenseDirectory> allLicenses;
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<LicenseDirectory> getLicenseDetails(long signUpDomainId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Entered DAO layer" + signUpDomainId);
		try{
			Query query = sessionFactory.getCurrentSession().createQuery(
				"from LicenseDirectory where DOMAIN_ID=:signUpDomainId");//based on domain id license details has to be selected
		query.setParameter("signUpDomainId", signUpDomainId);
		allLicenses = (ArrayList<LicenseDirectory>) query.list();
		
	} catch (HibernateException e) {
		e.printStackTrace();
		throw e;
	}
		return allLicenses;
	}
	@Override
	@Transactional
	public String addLicenseRecord(LicenseDirectory licenseDirectory) throws Exception {
		// TODO Auto-generated method stub
		String ex = null;
		Session ses = null;
		try {
			ses = (Session) sessionFactory.getCurrentSession();
			ses.save(licenseDirectory);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return ex;
	}

	
	@Override
	@Transactional
	public String deleteLicense(long licenseId) throws Exception{
		String ex = null;
		try {
			System.out.println("Entered the deletion area");
			Query query = sessionFactory.getCurrentSession().createQuery(
							"update from LicenseDirectory set STATUS ='INACTIVE'  where LICENSE_ID=:licenseId");
			query.setParameter("licenseId", licenseId);
			query.executeUpdate();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return ex;
	}
	@Override
	@Transactional
	public String updateLicenseRecord(LicenseDirectory licenseDirectory) throws Exception {
		String ex = null;
		try {
			
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update from LicenseDirectory set LICENSE_ID=:licenseId,STATE=:state,LICENSE_NUMBER=:licenseNumber,EXPIRY_DATE=:expiryDate,PRIMARY_PERSON=:primaryPerson,SUBMITTED_BY=:submittedBy,UPDATED_BY=:updatedBy,TYPE=:type,LOCAL_JURISDICTION=:localJurisdiction where LICENSE_ID=:licenseId");
			query.setParameter("licenseId", licenseDirectory.getLicenseId());
			query.setParameter("licenseNumber", licenseDirectory.getLicenseNumber());
			query.setParameter("state", licenseDirectory.getState());
			query.setParameter("expiryDate", licenseDirectory.getExpiryDate());
			query.setParameter("primaryPerson", licenseDirectory.getPrimaryPerson());
			query.setParameter("updatedBy", licenseDirectory.getLicenseUpdatedBy());
			query.setParameter("submittedBy", licenseDirectory.getLicenseSubmittedBy());
			query.setParameter("type", licenseDirectory.getType());
			query.setParameter("localJurisdiction", licenseDirectory.getLocalJurisdiction());
			query.executeUpdate();

		} catch (HibernateException e) {
			ex = e.getMessage();
			throw e;
		}
		return ex;
	}

}
