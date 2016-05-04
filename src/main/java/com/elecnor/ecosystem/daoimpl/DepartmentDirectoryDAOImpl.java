package com.elecnor.ecosystem.daoimpl;

/*
 @Author Rishabh
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.dao.DepartmentDirectoryDAO;

@Repository
public class DepartmentDirectoryDAOImpl implements DepartmentDirectoryDAO {

	@Autowired
//	@Qualifier("masterDBSessionFactory")
	private SessionFactory sessionFactory;

	private Session session;

	@Override
//	@Transactional("masterDB")
	@Transactional
	public DepartmentDirectory saveOrUpdateDepartment(DepartmentDirectory departmentDirectory) {
		DepartmentDirectory departmentSaved=null;
		try {
			session = sessionFactory.getCurrentSession();
			departmentSaved=(DepartmentDirectory) session.merge(departmentDirectory);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return departmentSaved;
	}

	@Override
//	@Transactional("masterDB")
	@Transactional
	public String deleteDepartment(int departmentDirectoryId) {
		String status=null;
		try {
			session = sessionFactory.getCurrentSession();
			DepartmentDirectory departmentDirectory=(DepartmentDirectory) session.get(DepartmentDirectory.class, departmentDirectoryId);
			session.delete(departmentDirectory);
		} catch (HibernateException e) {
			e.printStackTrace();
			status=e.getMessage();
			throw e;
		}
		return status;
	}
  
	@SuppressWarnings("unchecked")
	@Override
//	@Transactional("masterDB")
	@Transactional
	public List<DepartmentDirectory> getDepartmentsBasedOnIds(List<Integer> departmentIds){
		List<DepartmentDirectory> departments=null;
		try{
			Query query=(Query) sessionFactory.getCurrentSession().createQuery("from DepartmentDirectory where departmentId in:(:departmentIds)");
			query.setParameterList("departmentIds", departmentIds);
			departments=(List<DepartmentDirectory>)query.list();
			
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		return departments;
	}
	
	@SuppressWarnings("unchecked")
	@Override
//	@Transactional("masterDB")
	@Transactional
	public List<DepartmentDirectory> getDeptListInOrgUnit(Long organisationId) {
		List<DepartmentDirectory> departmentList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from DepartmentDirectory where ORGANIZATION_ID = :organisationId");
			query.setParameter("organisationId", organisationId);
			departmentList = (List<DepartmentDirectory>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return departmentList;
	}

	@Override
//	@Transactional("masterDB")
	@Transactional
	public DepartmentDirectory getDeptDirectoryById(Integer departmentId) throws HibernateException {

		DepartmentDirectory deptDir = null;
		try {
			deptDir = (DepartmentDirectory) sessionFactory.getCurrentSession().load(DepartmentDirectory.class, departmentId);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return deptDir;
	}

	@SuppressWarnings("unchecked")
	@Override
//	@Transactional("masterDB")
	@Transactional
	public List<DepartmentDirectory> getAllDepartments()
			throws HibernateException {
		// TODO Auto-generated method stub
		Query query=sessionFactory.getCurrentSession().createQuery("from DepartmentDirectory");
		// fetching all the records from data base - meghana
		
		return query.list();
		
	}

	@Override
	@Transactional
	public List<DepartmentDirectory> getDepartmentListByDomainId(Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		Query query=sessionFactory.getCurrentSession().createQuery("select new DepartmentDirectory(departmentId, departmentName) "
				+ "from DepartmentDirectory");
		return query.list();
	}

}
