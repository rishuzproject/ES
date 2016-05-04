package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.DepartmentType;

public interface DepartmentsDetailDAO {

	public DepartmentType setAddDepartmentDetails(DepartmentType departmentForm) throws Exception;
	public String updateDepartmentDetails(DepartmentType departmentForm) throws Exception;
	public ArrayList<DepartmentType> getAllDepartments() throws Exception;
	public String deleteDepartment(long deptId) throws Exception;
	public ArrayList<DepartmentType> getAllDepartments(Long domainId) throws Exception;
	public DepartmentType getDepartmentById(long deptId) throws Exception;
	
}
