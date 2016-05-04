package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.helper.UserDetailHelper;
import com.elecnor.ecosystem.util.ConstantUtil;

@Repository
public class UserDetailDAOImpl implements UserDetailDAO {

	@Autowired
	SessionFactory sessionFactory;

	// method for saving user
	@Override
	// @Transactional("masterDB")
	@Transactional
	public UserDetail saveApplicationUser(UserDetail applicationUserDetail) throws Exception {
		UserDetail userDetail = null;
		try {
			Session hibSes = sessionFactory.getCurrentSession();
			userDetail = (UserDetail) hibSes.merge(applicationUserDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userDetail;
	}

	// method for updating user
	@Override
	@Transactional
	public String updateUser(UserDetail userDetail) throws Exception {

		String result = null;
		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("update UserDetail set FIRST_NAME=:firName,NICK_NAME=:nickName," + "MIDDLE_NAME=:midName,LAST_NAME=:lastName,EMAIL_ID=:email,PHONE_CARRIER=:phoneCar,"
							+ "IS_DEPARTMENT_HEAD=:isDeptId,PHONE_NUMBER=:phoneNum,PREFERRED_CONTACT_MODE=:prefContMode,ORGANIZATION_UNIT_ID=:orgId,SUPERVISOR_ID=:supervisorid,DEPARTMENT_DIRECTORY_ID=:deptId,DESIGNATION_DIRECTORY_ID=:desigId,UPDATED_BY=:updBy,UPDATED_DATE=:updDate "
							+ " where USER_ID=:userId");
			query.setParameter("firName", userDetail.getFirstName());
			query.setParameter("nickName", userDetail.getNickName());
			query.setParameter("midName", userDetail.getMiddleName());
			query.setParameter("lastName", userDetail.getLastName());
			query.setParameter("email", userDetail.getEmailId().toLowerCase());
			query.setParameter("deptId", userDetail.getDeptUnitId());
			query.setParameter("orgId", userDetail.getOrgUnitId());
			query.setParameter("desigId", userDetail.getDesigId());
			query.setParameter("supervisorid", userDetail.getSuperviserId());
			query.setParameter("isDeptId", userDetail.getIsDeptHead());
			// query.setParameter("role", userDetail.getRoleNameEcosystem());
			// NOT PRESENT IN NEW SCHEMA add this statement in the query
			// ROLE=:role
			query.setParameter("phoneCar", userDetail.getPhoneCarrier());
			query.setParameter("phoneNum", userDetail.getPhoneNumber());
			query.setParameter("prefContMode", userDetail.getPreferredContactMode());
			query.setParameter("updBy", userDetail.getUpdatedBy());
			query.setParameter("updDate", userDetail.getUpdatedDate());
			query.setParameter("userId", userDetail.getUserId());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// method for getting all user details
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<UserDetail> getAllUserDetails(Long domainId) throws Exception {
		ArrayList<UserDetail> applicationUserList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where DOMAIN_ID=:domainId and STATUS != :status");
			query.setParameter("domainId", domainId);
			query.setParameter("status", ConstantUtil.INACTIVE);
			applicationUserList = (ArrayList<UserDetail>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return applicationUserList;
	}

	// method for deleting a user(we just making user status as INACTIVE and
	// updating updatedBy, updatedDate)
	@Override
	@Transactional
	public Integer setApplicationUserDelete(long userId, UserDetail userDetails) throws Exception {
		Integer result = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("update UserDetail set STATUS=:status,UPDATED_BY=:updBy, UPDATED_DATE=:updDate where USER_ID=:userId");
			query.setParameter("status", ConstantUtil.INACTIVE);
			query.setParameter("updBy", userDetails.getUserId());
			query.setParameter("updDate", new Date());
			query.setParameter("userId", userId);
			result = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// method for checking whether email is already exist or not
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean isEmailIdExists(String emailIdToCheck) throws Exception {
		ArrayList<UserDetail> userDetailsBean = null;
		UserDetailHelper userDetailHelper = new UserDetailHelper();
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where EMAIL_ID=:email");
			query.setParameter("email", emailIdToCheck);
			userDetailsBean = (ArrayList<UserDetail>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userDetailHelper.checkIfUserDetailBeanExists(userDetailsBean);
	}

	// this method is used for getting user details
	@Override
	@Transactional
	public UserDetail getUserDetails(String emailId) throws Exception {
		UserDetail userDetailsBean = null;
		Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where EMAIL_ID=:email and STATUS ='ACTIVE'");
		query.setParameter("email", emailId);
		try {
			userDetailsBean = (UserDetail) query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userDetailsBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<UserDetail> getUserDetails(long domainId) throws Exception {
		ArrayList<UserDetail> alluserDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where DOMAIN_ID =:domainId");
			query.setParameter("domainId", domainId);
			alluserDetails = (ArrayList<UserDetail>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return alluserDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<UserDetail> getUserDetailListById(String userIds) throws Exception {
		ArrayList<UserDetail> alluserDetails = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where USER_ID IN (" + userIds + ")");
			alluserDetails = (ArrayList<UserDetail>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return alluserDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public boolean isEmailIdExists(String emailId, Long userId) throws Exception {
		ArrayList<UserDetail> userDetailsBean = null;
		UserDetailHelper userDetailHelper = new UserDetailHelper();
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where EMAIL_ID=:email and USER_ID !=:userId and STATUS != :status");
			query.setParameter("email", emailId);
			query.setParameter("userId", userId);
			query.setParameter("status", ConstantUtil.INACTIVE);
			userDetailsBean = (ArrayList<UserDetail>) query.list();
			return userDetailHelper.checkIfEmailIdExists(userDetailsBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// this method is used for getting user details for activation
	@Override
	@Transactional
	public UserDetail getUserDetailsForActivation(String emailId) throws Exception {
		UserDetail userDetailsBean = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where EMAIL_ID=:email and STATUS=:status");
			query.setParameter("email", emailId);
			query.setParameter("status", ConstantUtil.PENDINGACTIVE);
			userDetailsBean = (UserDetail) query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userDetailsBean;
	}

	@Override
	@Transactional
	public UserDetail getUserBean(Long userId) throws Exception {
		UserDetail exposeUserDetailBean = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where USER_ID=:userId");
			query.setParameter("userId", userId);
			exposeUserDetailBean = (UserDetail) query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return exposeUserDetailBean;
	}

	@Override
	@Transactional
	public String setNewManageUserToActivate(String emailId, String userName) throws Exception {
		String result = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("update UserDetail set STATUS='ACTIVE' where EMAIL_ID=:emailID and STATUS = 'PENDING ACTIVATION'");
			query.setParameter("emailID", emailId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public UserDetail getAdminOfSelectedCompany(long domainId) throws Exception {
		UserDetail adminUser = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where DOMAIN_ID=:domainId and STATUS != :status and ROLE=:role");
			query.setParameter("domainId", domainId);
			query.setParameter("status", ConstantUtil.INACTIVE);
			query.setParameter("role", "ADMIN");
			adminUser = (UserDetail) query.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return adminUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	// @Transactional("masterDB")
	public List<UserDetail> getSupervisorListBasedOnDesigId(String parentDesignationIds) throws Exception {
		List<UserDetail> supervisorList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail where STATUS = 'ACTIVE' AND DESIGNATION_DIRECTORY_ID IN(" + parentDesignationIds + ")");
			supervisorList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supervisorList;
	}

	@Override
	// @Transactional("masterDB")
	@Transactional
	public UserDetail getUserDetailById(Long userDetailId) {
		UserDetail userDetail = null;
		try {
			userDetail = (UserDetail) sessionFactory.getCurrentSession().load(UserDetail.class, userDetailId);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return userDetail;
	}

}
