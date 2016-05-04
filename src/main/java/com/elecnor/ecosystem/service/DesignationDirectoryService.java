package com.elecnor.ecosystem.service;

import java.util.List;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.DesignationDirectory;

public interface DesignationDirectoryService {
	
	public List<DesignationDirectory> getDesignationInDeptUnit(String departmentId);

	public DesignationDirectory getDesignationById(Integer designationId);

	public String addOrUpdateDesignationHierarchyInDepartment(
			DesignationDirectory designationToBesaved, String childIds,
			String parentIds)
			throws Exception;

	public String deleteDesignation(int designationId) throws Exception;

	String addOrUpdateDesignationInDepartment(
			DesignationDirectory designationToBesaved,
			DepartmentDirectory associatedDepartment) throws Exception;
}
