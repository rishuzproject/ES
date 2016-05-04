package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.UserRoles;
import com.elecnor.ecosystem.dao.UserRolesDAO;
import com.elecnor.ecosystem.dao.UserRolesMappingDAO;
import com.elecnor.ecosystem.service.UserRolesMappingService;

@Service
public class UserRolesMappingServiceImpl implements UserRolesMappingService {

	public static ArrayList<UserRoles> allUserRolesStaticList = null;
	
	@Autowired
	UserRolesMappingDAO userRolesMappingDAO;
	@Autowired
	UserRolesDAO userRolesDAO;

	@Override
	public Boolean roleAuthorization(Long userId, Long roleId) throws Exception {

		Boolean result = false;
		try {
			if (userRolesMappingDAO.userRoleAuthorization(userId, roleId) > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public ArrayList<UserRoles> getAllUserRolesToInitializeInStaticList() throws Exception {
		try{
			if(allUserRolesStaticList == null){
				allUserRolesStaticList = userRolesDAO.getAllUserRoles();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return allUserRolesStaticList;
	}
}
