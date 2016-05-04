package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.DepartmentUserMapping;
import com.elecnor.ecosystem.bean.UserDetail;

public interface DepartmentUserMappingDAO {
	public ArrayList<UserDetail>getUserDetailsByDomain(long domainId)throws Exception;

	void addUserToDept(DepartmentUserMapping departmentBean) throws Exception;

	String deleteUserAssignmentstoDept(int departmentId) throws Exception;

	ArrayList<DepartmentUserMapping> getAssignedUsersByDept(long domainId,
			long deptId) throws Exception;
}
