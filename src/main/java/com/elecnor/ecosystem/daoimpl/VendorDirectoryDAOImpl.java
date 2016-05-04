package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.VendorDirectory;
import com.elecnor.ecosystem.dao.VendorDirectoryDAO;

@Repository
public class VendorDirectoryDAOImpl implements VendorDirectoryDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String updateVendor(VendorDirectory vendorDirectory) throws Exception{
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("update VendorDirectory set VENDOR_NAME=:compName,VENDOR_LEGAL_NAME=:compLegalName,VENDOR_LEGAL_STATUS=:compLegalStatus,OWNERSHIP=:ownership,"
							+ " REPRESENTATIVE_NAME=:repName,REPRESENTATIVE_PHONE=:repPhone,IRS=:irs,BUSINESS_TYPE=:bussType,"
							+ "VENDOR_PHONE_NO=:compPhoneNo,VENDOR_FAX=:compFax,VENDOR_EMAIL=:compEmail,"
							+ " VENDOR_WEBSITE=:compWebsite,UPDATED_BY=:updatedBy,UPDATED_DATE=:updatedDate where VENDOR_ID=:compId");
			query.setParameter("compName", vendorDirectory.getVendorName());
			query.setParameter("compLegalName",vendorDirectory.getVendorLegalName());
			query.setParameter("compLegalStatus", vendorDirectory.getVendorLegalStatus());
			query.setParameter("ownership", vendorDirectory.getVendorOwnership());
//			query.setParameter("compAddress", vendorDirectory.getVendorAddress());
		/*	query.setParameter("compMailAddress", vendorDirectory.getVendorMailingAddress());*/
			query.setParameter("compPhoneNo", vendorDirectory.getVendorPhoneNo());
			query.setParameter("compFax", vendorDirectory.getVendorFax());
			query.setParameter("compEmail", vendorDirectory.getVendorEmail());
			query.setParameter("compWebsite", vendorDirectory.getVendorWebsite());
			query.setParameter("repName",vendorDirectory.getRepresentativeName());
			/*query.setParameter("repAddr",vendorDirectory.getRepresentativeAddress());*/
			query.setParameter("repPhone",vendorDirectory.getRepresentativePhone());
			query.setParameter("irs", vendorDirectory.getIrs());
			query.setParameter("bussType", vendorDirectory.getBusinessType());
			query.setParameter("updatedBy", vendorDirectory.getUpdatedBy());
			query.setParameter("updatedDate", vendorDirectory.getUpdatedDate());
			query.setParameter("compId", vendorDirectory.getVendorId());
			query.executeUpdate();
			result=vendorDirectory.getVendorId().toString();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	@Transactional
	public String addNewVendor(VendorDirectory vendorDirectory) throws Exception{
		
		String result = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			VendorDirectory vendor=(VendorDirectory) hibSes.merge(vendorDirectory);
			result=vendor.getVendorId().toString();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<VendorDirectory> getAllVendors(Long domainId) throws Exception{
		
		ArrayList<VendorDirectory> allVendorList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("from VendorDirectory where STATUS='ACTIVE' AND DOMAIN_ID=:domainId").setParameter("domainId", domainId);
			allVendorList = (ArrayList<VendorDirectory>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allVendorList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<VendorDirectory> getAllVendorsForJobs() throws Exception{
		
		ArrayList<VendorDirectory> allVendorList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("from VendorDirectory where STATUS='ACTIVE'");
			allVendorList = (ArrayList<VendorDirectory>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allVendorList;
	}
	
	@Override
	@Transactional
	public String deleteVendor(int vendorId) throws Exception{
		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("update VendorDirectory set STATUS='INACTIVE' where VENDOR_ID=:vendorId");
			query.setParameter("vendorId",vendorId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public VendorDirectory getVendorsById(long vendorId) throws Exception{
		// TODO Auto-generated method stub
		VendorDirectory vendorDirectory=null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from VendorDirectory  where VENDOR_ID=:vendorId");
			query.setParameter("vendorId", vendorId);
			vendorDirectory=(VendorDirectory)query.list().get(0);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return vendorDirectory;
	}
	
	//adding pending vendor details
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<VendorDirectory> getVendorDetailsForJobs()
			throws Exception {
		ArrayList<VendorDirectory> allVendorList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("from VendorDirectory where STATUS='ACTIVE' or STATUS='PENDING'");
			allVendorList = (ArrayList<VendorDirectory>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allVendorList;
	}

	@Override
	@Transactional
	public List<VendorDirectory> getVendorListByDomainId(Long domainId) throws Exception{
		// TODO Auto-generated method stub
		Query query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createQuery("select new VendorDirectory(vendorId, vendorName) from VendorDirectory "
							+ "where (STATUS='ACTIVE' OR STATUS='PENDING') AND DOMAIN_ID = :domainId");
			query.setParameter("domainId", domainId);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return query.list();
	}
	
}
