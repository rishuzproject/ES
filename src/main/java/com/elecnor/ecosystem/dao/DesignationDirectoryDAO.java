package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.DesignationDirectory;

public interface DesignationDirectoryDAO {
	
	public List<DesignationDirectory> getDesignationInDeptUnit(String deptId);
	
	public DesignationDirectory getDesignationById(Integer designationId);

	DesignationDirectory addorUpdateDesignationInDepartment(
			DesignationDirectory designationToBeSaved) throws Exception;

	public String deleteDesignation(int designationDirectoryId);

}
