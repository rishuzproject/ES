package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.ApplicationUserRole;
import com.elecnor.ecosystem.dao.RoleDescriptionDAO;


@Repository
public class RoleDescriptionDAOImpl implements RoleDescriptionDAO{
	@Autowired
	SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationUserRole> getAllRoles() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ApplicationUserRole> roleList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from  ApplicationUserRole");
			roleList = (ArrayList<ApplicationUserRole>) query.list();

		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		}
		return roleList;
		
	}
	@Override
	@Transactional
	public String addOrUpdateRole(ApplicationUserRole applicationUserRoleBean) {
		// TODO Auto-generated method stub
		String ex=null;
		try
		{
		Session session = sessionFactory.getCurrentSession();
		session.merge(applicationUserRoleBean);
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		return ex;
	}
	@Override
	@Transactional
	public String deleteRole(long roleId) {
		
		String ex=null;
		try
		{
		
		Query query = sessionFactory.getCurrentSession().createQuery("delete from ApplicationUserRole where ROLE_ID=:roleId");
		query.setParameter("roleId", roleId);
		query.executeUpdate();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			System.out.println("the exception is"+e.getMessage());
		}
		return ex;
	}

}
