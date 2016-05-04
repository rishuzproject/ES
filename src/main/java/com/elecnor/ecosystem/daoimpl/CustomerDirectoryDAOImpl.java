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

import com.elecnor.ecosystem.bean.CustomerDirectory;
import com.elecnor.ecosystem.dao.CustomerDirectoryDAO;

@Repository
public class CustomerDirectoryDAOImpl implements CustomerDirectoryDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public CustomerDirectory saveCustomer(CustomerDirectory customerListForm) throws Exception {
		CustomerDirectory customerList = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			customerList = (CustomerDirectory)hibSes.merge(customerListForm);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return customerList;
	}

	@Override
	@Transactional
	public String setCustomerUpdate(CustomerDirectory customerListForm) throws Exception {

		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update CustomerDirectory set COMPANY_NAME=:compName,COMPANY_EMAIL=:compEmail,"
									+ "PHONE_NUMBER=:phoneNumber,FAX=:fax,REPRESENTATIVE_NAME=:repName,REPRESENTATIVE_PHONE=:repPhone,IRS=:irs,"
									+ "BUSINESS_TYPE=:bussType,UPDATED_BY=:updatBy,UPDATED_DATE=:updDate,CUSTOMER_NUMBER=:comNumber where COMPANY_ID=:compId");
			query.setParameter("compName", customerListForm.getCompanyName());
			/*query.setParameter("corpAddr",
					customerListForm.getCorporateOfficeAddress());*/
			query.setParameter("compEmail", customerListForm.getCompanyEmail());
			query.setParameter("phoneNumber", customerListForm.getPhoneNumber());
			query.setParameter("fax", customerListForm.getFax());
			query.setParameter("repName",customerListForm.getRepresentativeName());
			/*query.setParameter("repAddr",customerListForm.getRepresentativeAddress());*/
			query.setParameter("repPhone",customerListForm.getRepresentativePhone());
			query.setParameter("irs", customerListForm.getIrs());
			query.setParameter("bussType", customerListForm.getBusinessType());
			query.setParameter("updatBy", customerListForm.getUpdatedBy());
			query.setParameter("updDate", customerListForm.getUpdatedDate());
			query.setParameter("comNumber", customerListForm.getCompanyNumber());
			query.setParameter("compId", customerListForm.getCompanyId());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<CustomerDirectory> getAllCustomersList() throws Exception {
		ArrayList<CustomerDirectory> allCustomersDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from CustomerDirectory where STATUS='ACTIVE'");
			allCustomersDetails = (ArrayList<CustomerDirectory>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allCustomersDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<CustomerDirectory> getAllCustomersList(Long domainId) throws Exception {
		ArrayList<CustomerDirectory> allCustomersDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from CustomerDirectory where STATUS='ACTIVE' and DOMAIN_ID=:domainId")
					.setParameter("domainId", domainId);
			allCustomersDetails = (ArrayList<CustomerDirectory>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allCustomersDetails;
	}

	@Override
	@Transactional
	public String setCustomerDelete(long compId) throws Exception {
		String result = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update CustomerDirectory set STATUS=:status where COMPANY_ID=:compId");
			query.setParameter("status", "INACTIVE");
			query.setParameter("compId", compId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public CustomerDirectory getCustomerById(long companyId) throws Exception {
		// TODO Auto-generated method stub
		CustomerDirectory companyRecord = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from CustomerDirectory where COMPANY_ID=:compId");
			query.setParameter("compId", companyId);
			companyRecord=(CustomerDirectory) query.list().get(0);
		} catch (HibernateException e) {
			 e.printStackTrace();
			 throw e;
		}
		return companyRecord;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CustomerDirectory> getCustomerListByDomainId(Long domainId)
			throws Exception {
		 List<CustomerDirectory> custList = null;
		Query query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createQuery("select new CustomerDirectory(companyId , companyName)" +
							"from CustomerDirectory where STATUS='ACTIVE' and DOMAIN_ID=:domainId")
					.setParameter("domainId", domainId);
			custList = (List<CustomerDirectory>)query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return custList;
	}

}
