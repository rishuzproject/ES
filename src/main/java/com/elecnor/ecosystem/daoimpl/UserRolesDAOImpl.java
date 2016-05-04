package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.UserRoles;
import com.elecnor.ecosystem.dao.UserRolesDAO;

@Repository
public class UserRolesDAOImpl implements UserRolesDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	// method for saving user
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<UserRoles> getAllUserRoles() throws Exception {
		
		ArrayList<UserRoles> userRolesList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserRoles");
			userRolesList = (ArrayList<UserRoles>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return userRolesList;
	}

	@Override
	@Transactional
	public String getUserRoleNameById(long roleId) throws Exception {
		String userRoleName = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("select roleName from UserRoles where ROLE_ID=:roleId");
			query.setParameter("roleId", roleId);
			userRoleName = (String) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return userRoleName;
	}
}
