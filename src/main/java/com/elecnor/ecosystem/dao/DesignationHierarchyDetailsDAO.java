package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.DesignationHierarchyDetails;

public interface DesignationHierarchyDetailsDAO {
	
	public List<DesignationHierarchyDetails> getParentDesignationsForGivenId(int designationId);


	public String deleteDesignationHierarchyDetails(int designationId)
			throws Exception;


 public String addDesignationHierarchy (DesignationHierarchyDetails designationHierarchyToBeAdded)throws Exception;

}
