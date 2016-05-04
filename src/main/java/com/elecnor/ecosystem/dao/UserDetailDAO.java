package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.UserDetail;

public interface UserDetailDAO {

	public UserDetail saveApplicationUser(UserDetail applicationUserDetail) throws Exception;
	
//	public UserDetail setUserSave(UserDetail applicationUserDetailDAO) throws Exception;

	public ArrayList<UserDetail> getUserDetailListById(String  userIds) throws Exception;
	
	public String updateUser(UserDetail applicationUserDetail) throws Exception;

	public ArrayList<UserDetail> getAllUserDetails(Long domainId) throws Exception;

	public Integer setApplicationUserDelete(long userId, UserDetail userDetails) throws Exception;

	public boolean isEmailIdExists(String emailIdToCheck) throws Exception;

	public UserDetail getUserDetails(String emailId) throws Exception;

	public ArrayList<UserDetail> getUserDetails(long domainId) throws Exception;

	public boolean isEmailIdExists(String emailId, Long userId) throws Exception;

	public UserDetail getUserDetailsForActivation(String emailId) throws Exception;

	public UserDetail getUserBean(Long userId) throws Exception;

	public String setNewManageUserToActivate(String emailId, String userName) throws Exception;

	UserDetail getAdminOfSelectedCompany(long domainId) throws Exception;

	public List<UserDetail> getSupervisorListBasedOnDesigId(String parentDesignatioIds) throws Exception;

	public UserDetail getUserDetailById(Long userDetailId);
}
