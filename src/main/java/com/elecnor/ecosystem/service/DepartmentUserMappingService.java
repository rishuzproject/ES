package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.DepartmentUserMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;

public interface DepartmentUserMappingService {

	void addDepartmentUserMapping(UserDetail user, DepartmentType departmentId,
			DepartmentUserMapping departmentUserMapping, UserDetail loggedInUser, String userType) throws Exception;

	String saveUserType(DomainDetail domainDetail, String selectedUsersId,
			DepartmentType result, UserDetail userDetail, String userType) throws Exception;

}
