package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.ApplicationUserRole;

public interface RoleDescriptionDAO {

	public ArrayList<ApplicationUserRole> getAllRoles() throws Exception;
	public String addOrUpdateRole(ApplicationUserRole applicationUserRoleBean);
	public String deleteRole(long roleId);
}
