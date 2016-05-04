package com.elecnor.ecosystem.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.bean.UserRoles;

public interface DashboardService {

	public HashMap<Object, Object> getUserJobDetails(UserDetail userDetail, Long domainId, Boolean isBelcoUser, Long userRole, HttpSession session) throws Exception;
	public UserRoles getUserRoleBeanByRoleName(String userroleName) throws Exception;

}
