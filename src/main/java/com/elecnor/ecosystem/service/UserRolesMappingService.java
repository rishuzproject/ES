package com.elecnor.ecosystem.service;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.UserRoles;


public interface UserRolesMappingService {

	public Boolean roleAuthorization(Long userId, Long userRoleSelectedForDefault) throws Exception;
	public ArrayList<UserRoles> getAllUserRolesToInitializeInStaticList() throws Exception;
}
