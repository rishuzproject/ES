package com.elecnor.ecosystem.helper;

import java.util.HashMap;

import com.elecnor.ecosystem.bean.UserDetail;

public class HomeHelper {

	public HashMap<Object, Object> checkForTemporaryUserAndGetUserDetails(Boolean isTempUser, Object selectedUser, Object userRolesList,
			Object userRole) throws Exception {
		// TODO Auto-generated method stub
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			UserDetail userDetails = (UserDetail) selectedUser;
			// domain logo code..do not remove
			// DomainDetail domainDetail = userDetails.getDomainDetail();
			// String domainLogoString = new
			// String(Base64.encode(domainDetail.getDomainLogo()));
			// resultMap.put("domainLogo", domainLogoString);
			resultMap.put("userRolesList", userRolesList);
			resultMap.put("loggedInRole", userRole);
			resultMap.put("userDetails", userDetails.getFirstName());
			resultMap.put("userLastName", userDetails.getLastName());
			resultMap.put("istempuser", isTempUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return resultMap;
	}
}
