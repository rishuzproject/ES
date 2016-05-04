package com.elecnor.ecosystem.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DesignationDirectory;
import com.elecnor.ecosystem.dao.DesignationDirectoryDAO;

@Repository
public class DesignationDirectoryDAOImpl implements DesignationDirectoryDAO {

	@Autowired
//	@Qualifier("masterDBSessionFactory")
	private SessionFactory sessionFactory;

	private Session session;

	@SuppressWarnings("unchecked")
	@Override
//	@Transactional("masterDB")
	@Transactional
	public List<DesignationDirectory> getDesignationInDeptUnit(String departmentId) {
		List<DesignationDirectory> desigList = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from DesignationDirectory where DEPARTMENT_ID IN ( "+departmentId+" )");
			desigList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return desigList;
	}

	@Override
//	@Transactional("masterDB")
	@Transactional
	public DesignationDirectory getDesignationById(Integer designationId) {
		DesignationDirectory desigDir = null;
		try {
			desigDir = (DesignationDirectory) sessionFactory.getCurrentSession().load(DesignationDirectory.class, designationId);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return desigDir;
	}
	
	
	
	@Override
//	@Transactional("masterDB")
	@Transactional
	public DesignationDirectory addorUpdateDesignationInDepartment(DesignationDirectory designationToBeSaved)throws Exception{
		Session session=sessionFactory.getCurrentSession();
		DesignationDirectory designationSaved=(DesignationDirectory)session.merge(designationToBeSaved);
		return designationSaved;
	}
	
	
	@Override
//	@Transactional("masterDB")
	@Transactional
	public String deleteDesignation(int designationDirectoryId) {
		String status=null;
		try {
			session = sessionFactory.getCurrentSession();
			DesignationDirectory departmentDirectory=(DesignationDirectory) session.get(DesignationDirectory.class, designationDirectoryId);
			session.delete(departmentDirectory);
		} catch (HibernateException e) {
			e.printStackTrace();
			status=e.getMessage();
			throw e;
		}
		return status;
	}
	
}
