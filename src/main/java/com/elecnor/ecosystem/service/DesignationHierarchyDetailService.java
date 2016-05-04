package com.elecnor.ecosystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DesignationDirectory;
import com.elecnor.ecosystem.bean.DesignationHierarchyDetails;

@Service
public interface DesignationHierarchyDetailService {
	
	public List<DesignationHierarchyDetails> getParentDesignationsForGivenId(int designationId);

	public String addDesignationHierarchy(DesignationDirectory savedDesignation,
			List<DesignationDirectory> associatedParentDepartments,
			List<DesignationDirectory> associatedChildDepartments)
			throws Exception;

}
