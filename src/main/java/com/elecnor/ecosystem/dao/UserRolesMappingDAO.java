package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.UserRolesMapping;

public interface UserRolesMappingDAO {
	
	public String addUserRoleMapping(List<UserRolesMapping> userRolesMappingList) throws Exception;

	public void deleteMapping(Long userId) throws Exception;

	public List<UserRolesMapping> getRoleForUser(String userEmailId) throws Exception;

	public void setDefaultRole(Long userRoleSelectedForDefault, String emailId) throws Exception;

	public List<UserRolesMapping> getRoleList() throws Exception;

	public int userRoleAuthorization(Long userId, Long roleId) throws Exception;

	public String addUserRoleMappingForNewDomain(UserRolesMapping userRolesMapping) throws Exception;

	public List<Integer> getRoleIdsForUser(Long userId);

	List<UserRolesMapping> getRoleForUser(Long userId) throws Exception;

}
