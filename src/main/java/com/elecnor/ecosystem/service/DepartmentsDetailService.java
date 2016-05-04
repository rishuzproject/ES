package com.elecnor.ecosystem.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.bean.DepartmentType;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;

public interface DepartmentsDetailService {

//	public DepartmentType setAddDepartmentDetailService(DepartmentType departmentForm,
//			UserDetail userDetail, DomainDetail domainDetail) throws Exception;

	public HashMap<String, Object> uploadDepartmentFile(MultipartFile fileUploaded, HttpSession session,
			int confirmDepartmentUploadId) throws Exception;

	

	public String saveSelectedUsers(int[] array,
			ArrayList<UserDetail> usersList, DepartmentType departmentId,
			UserDetail loggedInUser, String userType) throws Exception;

//	DepartmentType updateDepartmentDetailService(DepartmentType departmentForm,
//			UserDetail userDetail, DomainDetail domainDetail) throws Exception;

	DepartmentType setAddUpdateDepartmentDetailService(long departmentId, DepartmentType departmentForm,
			UserDetail userDetail, DomainDetail domainDetail) throws Exception;

	public String saveTypeOfUser(DepartmentType result, DomainDetail domainDetail, String selectedUsersId,
			DepartmentType result2, UserDetail userDetail, String string) throws Exception;
}
