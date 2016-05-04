package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DepartmentUserMapping;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DepartmentUserMappingDAO;

@Repository
public class DepartmentUserMappingDAOImpl implements DepartmentUserMappingDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ArrayList<DepartmentUserMapping> getAssignedUsersByDept(long domainId, long deptId) throws Exception {
		ArrayList<DepartmentUserMapping> deptUsers = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from DepartmentUserMapping where DOMAIN_ID=:domainId and DEPARTMENT_ID=:deptId");// based
																		// on domain id, user details has to
																		// be selected
			query.setParameter("domainId", domainId);
			query.setParameter("deptId", deptId);
			deptUsers = (ArrayList<DepartmentUserMapping>) query.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return deptUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<UserDetail> getUserDetailsByDomain(long domainId) throws Exception {

		ArrayList<UserDetail> userDetails;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where DOMAIN_ID=:domainId");
																	// based on domain id, user details has to
																	// be selected
			query.setParameter("domainId", domainId);
			userDetails = (ArrayList<UserDetail>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return userDetails;

	}

	@Override
	@Transactional
	public void addUserToDept(DepartmentUserMapping departmentBean) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(departmentBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	@Transactional
	public String deleteUserAssignmentstoDept(int departmentId) throws Exception {
		String result = "";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"delete from DepartmentUserMapping where DEPARTMENT_ID=:departmentId");
			query.setParameter("departmentId", departmentId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
