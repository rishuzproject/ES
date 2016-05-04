package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.UserRoles;

public interface UserRolesDAO {
	
	public ArrayList<UserRoles> getAllUserRoles() throws Exception;
	public String getUserRoleNameById(long roleId) throws Exception;

}
