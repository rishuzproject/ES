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

import com.elecnor.ecosystem.bean.ContractorDirectory;
import com.elecnor.ecosystem.dao.ContractorDirectoryDAO;

/**
 * 
 * @author Harsh Verma
 *
 */
@Repository
public class ContractorDirectoryDAOImpl implements ContractorDirectoryDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	/**
	 * Method for save contractor data
	 */
	@Override
	@Transactional
	public ContractorDirectory setContractorSave(ContractorDirectory contractorListForm) throws Exception {
		ContractorDirectory result = null;
		try {			
			Session hibSes = sessionFactory.getCurrentSession();
			result = (ContractorDirectory)hibSes.merge(contractorListForm);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	/**
	 * Method for update contractor data
	 */
	@Override
	@Transactional
	public ContractorDirectory setContractorUpdate(ContractorDirectory contractorListForm) throws Exception {
		String result = null;
		try {
			String buildQuery = "update ContractorDirectory set CONTRACTOR_NAME=:compName,CONTRACTOR_EMAIL=:compEmail,"
									+ "PHONE_NUMBER=:phoneNumber,FAX=:fax,REPRESENTATIVE_NAME=:repName,REPRESENTATIVE_PHONE=:repPhone,"
									+ "BUSINESS_TYPE=:bussType,UPDATED_BY=:updatBy,UPDATED_DATE=:updDate,CONTRACTOR_NUMBER=:comNumber where CONTRACTOR_ID=:compId";
							
			Query query = sessionFactory.getCurrentSession().createQuery(buildQuery);
			
			query.setParameter("compName", contractorListForm.getContractorName());
//			query.setParameter("corpAddr",contractorListForm.getCorporateOfficeAddress());
			query.setParameter("compEmail", contractorListForm.getContractorEmail());
			query.setParameter("phoneNumber", contractorListForm.getPhoneNumber());
			query.setParameter("fax", contractorListForm.getFax());
			query.setParameter("repName",contractorListForm.getRepresentativeName());
//			query.setParameter("repAddr",contractorListForm.getRepresentativeAddress());
			query.setParameter("repPhone",contractorListForm.getRepresentativePhone());
			query.setParameter("bussType", contractorListForm.getBusinessType());
			query.setParameter("updatBy", contractorListForm.getUpdatedBy());
			query.setParameter("updDate", contractorListForm.getUpdatedDate());
			query.setParameter("comNumber", contractorListForm.getContractorNumber());
			query.setParameter("compId", contractorListForm.getContractorId());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return contractorListForm;
	}

	/**
	 * Method for fetch all contractors data
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ContractorDirectory> getAllContractorList() throws Exception {
		ArrayList<ContractorDirectory> allContractorDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from ContractorDirectory where STATUS='ACTIVE'");
			allContractorDetails = (ArrayList<ContractorDirectory>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allContractorDetails;
	}
	
	/**
	 * Method for delete contractor data on basis of contractor ID
	 */
	@Override
	@Transactional
	public String setContractorDelete(long conId) throws Exception {
		String result = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery("update ContractorDirectory set status=:status where contractorId=:compId");
			query.setParameter("status", "INACTIVE");
			query.setParameter("compId", conId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	/**
	 * Method for fetch contractor data on the basis of domain ID
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ContractorDirectory> getAllContractorList(Long domainId) throws Exception {
		ArrayList<ContractorDirectory> allContractorDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from ContractorDirectory where status='ACTIVE' and DOMAIN_ID=:domainId");
			query.setParameter("domainId", domainId);
			allContractorDetails = (ArrayList<ContractorDirectory>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allContractorDetails;
	}

	@Override
	@Transactional
	public ContractorDirectory getContractorDetailByID(long conId) throws Exception {
		ContractorDirectory contractorDirectory=null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from ContractorDirectory  where CONTRACTOR_ID=:conId");
			query.setParameter("conId", conId);
			contractorDirectory=(ContractorDirectory)query.list().get(0);

		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return contractorDirectory;
	}

	@Override
	@Transactional
	public List<ContractorDirectory> getContractorListByDomainId(Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("select new ContractorDirectory(contractorId , contractorName) "
					+ "from ContractorDirectory where status='ACTIVE' and DOMAIN_ID=:domainId");
			query.setParameter("domainId", domainId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return query.list();
	}

}
