package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.DepartmentHierarchyDetails;

public interface DepartmentHierarchyDAO {

	public String addDepartmentHierarchy(DepartmentHierarchyDetails departmentHierarchyToBeAdded);

	public String deleteDepartmentHierarchy(int Departmentid);

	public List<DepartmentHierarchyDetails> getParentDepartment(Integer deptId);

}
