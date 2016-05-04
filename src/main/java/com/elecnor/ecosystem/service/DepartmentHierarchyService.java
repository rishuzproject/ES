package com.elecnor.ecosystem.service;

import java.util.List;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.DepartmentHierarchyDetails;

public interface DepartmentHierarchyService {

	String addDepartmentHierarchy(DepartmentDirectory savedDepartment, List<DepartmentDirectory> associatedDepartments, List<DepartmentDirectory> childDepartments);

	public List<DepartmentHierarchyDetails> getParentDepartment(Integer deptId);
}
