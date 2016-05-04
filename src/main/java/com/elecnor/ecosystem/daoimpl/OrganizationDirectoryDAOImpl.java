package com.elecnor.ecosystem.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.OrganizationDirectory;
import com.elecnor.ecosystem.dao.OrganizationDirectoryDAO;

@Repository
public class OrganizationDirectoryDAOImpl implements OrganizationDirectoryDAO {
	
	@Autowired
//	@Qualifier("masterDBSessionFactory")
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
//	@Transactional("masterDB")
	@Transactional
	public List<OrganizationDirectory> getAllOrganizations() {
		List<OrganizationDirectory> orgDirList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from OrganizationDirectory");
			orgDirList = (List<OrganizationDirectory>) query.list();
			System.out.println(orgDirList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orgDirList;
	}

	
	
	
	@Override
//	@Transactional("masterDB")
	@Transactional
	public String addOrUpdateOrganizationDetail(OrganizationDirectory selectedOrganization) {
		String ex = null;
		Session ses = null;
		try {
			ses = (Session) sessionFactory.getCurrentSession();
			ses.merge(selectedOrganization);
		} catch (HibernateException e) {
			e.printStackTrace();
			ex=e.getMessage();
			throw e;
		}
		return ex;
	}

	
	
	
	
	@Override
//	@Transactional("masterDB")
	@Transactional
	public Integer checkSelectedOrganizationExists(Long organizationId) {
		Integer associatedDepartmentsCount = null;
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("FROM DepartmentDirectory WHERE ORGANIZATION_ID =:organizationId ");
			query.setParameter("organizationId", organizationId);
		    query.setMaxResults(1);
		    associatedDepartmentsCount=query.list().size();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return associatedDepartmentsCount;

	}
	
	@Override
	@Transactional("masterDB")
	public String deleteSelectedOrganization(Long organizationId){
		String result=null;
		
		try{
			Query query=sessionFactory.getCurrentSession().createQuery("delete from OrganizationDirectory where ORGANIZATION_ID=:organizationId");
			query.setParameter("organizationId", organizationId);
			query.executeUpdate();
		}
		catch(HibernateException e){
			result=e.getMessage();
			
		}
		return result;
	}




	@Override
//	@Transactional("masterDB")
	@Transactional
	public OrganizationDirectory getOrganisationById(Long organizationId) throws Exception {
		OrganizationDirectory orgDir= null;
		try{
			orgDir = (OrganizationDirectory) sessionFactory.getCurrentSession().load(OrganizationDirectory.class, organizationId);
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		return orgDir;
	}

}
