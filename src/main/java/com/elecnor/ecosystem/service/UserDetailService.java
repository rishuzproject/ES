package com.elecnor.ecosystem.service;


import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.DesignationDirectory;
import com.elecnor.ecosystem.bean.DesignationHierarchyDetails;
import com.elecnor.ecosystem.bean.UserDetail;

public interface UserDetailService {

	public String saveOrUpdateUser(UserDetail applicationUserDetail, boolean isBelcoUser,UserDetail loggedInUser, String[] selectedRolesNames, String[] selectedRolesId) throws Exception;

	public UserDetail authenticateUser(String emailId, String password) throws Exception;
	
	public String sendActivationEmail(String emailId, String loginType) throws Exception;

	public String getUserDetails(String emailId) throws Exception;
	
	public String sendActivationLinkIfPresentInUserDetails(String emailId, String loginType) throws Exception;

	public List<UserDetail> getSupervisorListBasedOnDesigId(List<DesignationHierarchyDetails> desigHierarchyList,List<DesignationDirectory> desigDirList) throws Exception;
	
	public UserDetail getUserDetailById(Long userDetailId);
	
	public ArrayList<UserDetail> getUserDetailListById(StringBuilder  userIds) throws Exception;
}
