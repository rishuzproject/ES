package com.elecnor.ecosystem.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.UserRolesMapping;
import com.elecnor.ecosystem.dao.UserRolesMappingDAO;

@Repository
public class UserRolesMappingDAOImpl implements UserRolesMappingDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public String addUserRoleMapping(List<UserRolesMapping> userRolesMappingList) throws Exception {
		String result = null;
		for (UserRolesMapping userMapping : userRolesMappingList)
			try {
				Session hibSes = sessionFactory.getCurrentSession();
				hibSes.merge(userMapping);
			} catch (HibernateException e) {
				result = e.getMessage();
				e.printStackTrace();
				throw e;
			}
		return result;
	}
	
	@Override
	@Transactional
	public String addUserRoleMappingForNewDomain(UserRolesMapping userRolesMapping) throws Exception {
		String result = "";
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			hibSes.merge(userRolesMapping);
		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public void deleteMapping(Long userId) throws Exception {

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"delete from UserRolesMapping where USER_ID=:userId");
			query.setParameter("userId", userId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserRolesMapping> getRoleForUser(String userEmailId) throws Exception {
		ArrayList<UserRolesMapping> userRolesList = null;
		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("from UserRolesMapping where USER_EMAIL_ID=:userEmailId")
					.setParameter("userEmailId", userEmailId);
			userRolesList = (ArrayList<UserRolesMapping>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return userRolesList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserRolesMapping> getRoleForUser(Long userId) throws Exception {
		ArrayList<UserRolesMapping> userRolesList = null;
		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("from UserRolesMapping where USER_ID=:userId")
					.setParameter("userId", userId);
			userRolesList = (ArrayList<UserRolesMapping>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return userRolesList;
	}
	
	@Override
	@Transactional
	public List<Integer> getRoleIdsForUser(Long userId){
		ArrayList<Integer> roleIds=null;
		try{
			Query query = sessionFactory.getCurrentSession()
					.createSQLQuery("select ROLE_ID from USER_ROLES_MAPPING where USER_ID=:userId")
					.setParameter("userId", userId);
			roleIds = (ArrayList<Integer>) query.list();
			return roleIds;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@Transactional
	public void setDefaultRole(Long userRoleSelectedForDefault, String emailId) throws Exception {
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update UserRolesMapping set IS_DEFAULT_ROLE=false where USER_EMAIL_ID=:emailId and IS_DEFAULT_ROLE=true");
			query.setParameter("emailId", emailId);
			query.executeUpdate();

			query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update UserRolesMapping set IS_DEFAULT_ROLE=true where USER_EMAIL_ID=:emailId AND ROLE_ID=:userRoleSelectedForDefault");
			query.setParameter("emailId", emailId);
			query.setParameter("userRoleSelectedForDefault", userRoleSelectedForDefault);
			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserRolesMapping> getRoleList() throws Exception {

		ArrayList<UserRolesMapping> userRolesList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserRolesMapping");
			userRolesList = (ArrayList<UserRolesMapping>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return userRolesList;
	}
	
	@Override
	@Transactional
	public int userRoleAuthorization(Long userId, Long roleId) throws Exception {
		BigInteger result = BigInteger.ZERO;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT COUNT(*) FROM USER_ROLES_MAPPING where USER_ID=:userId and ROLE_ID=:roleId");
			query.setParameter("userId", userId);
			query.setParameter("roleId", roleId);
			result = (BigInteger) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result.intValue();
	}
}
