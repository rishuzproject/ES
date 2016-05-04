package com.elecnor.ecosystem.serviceimpl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.DepartmentUserMapping;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DepartmentUserMappingDAO;
import com.elecnor.ecosystem.service.DepartmentUserMappingService;
import com.elecnor.ecosystem.service.DepartmentsDetailService;

@Service
public class DepartmentUserMappingServiceImpl implements DepartmentUserMappingService {

	@Autowired
	DepartmentUserMappingDAO departmentUserMappingDAO;
	@Autowired
	DepartmentsDetailService departmentsDetailService;

	@Override
	public void addDepartmentUserMapping(UserDetail user, DepartmentType departmentId,
			DepartmentUserMapping departmentUserMapping, UserDetail loggedInUser, String userType) throws Exception{
		try {
			departmentUserMapping.setDepartmentId(departmentId);
			departmentUserMapping.setUserId(user);
			departmentUserMapping.setUserType(userType);
			departmentUserMapping.setSubmittedBy(loggedInUser);
			departmentUserMapping.setSubmittedDate(new Date());
			departmentUserMappingDAO.addUserToDept(departmentUserMapping);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public String saveUserType(DomainDetail domainDetail, String selectedUsersId, DepartmentType result,
			UserDetail userDetail, String userType) throws Exception{
		
		try {
			if (selectedUsersId.contains("[")) {
				selectedUsersId = selectedUsersId.substring(1, selectedUsersId.length() - 1);
				selectedUsersId = selectedUsersId.replaceAll("\"", "");
			}
			String arrayList[] = selectedUsersId.split(",");
			int array[] = new int[arrayList.length];
			for (int i = 0; i < arrayList.length; i++) {
				if (arrayList[i] != "")
					array[i] = Integer.parseInt(arrayList[i]);
			}
			ArrayList<UserDetail> usersList;
			usersList = departmentUserMappingDAO.getUserDetailsByDomain(domainDetail.getDomainId());
			
			return departmentsDetailService.saveSelectedUsers(array, usersList, result, userDetail, userType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
