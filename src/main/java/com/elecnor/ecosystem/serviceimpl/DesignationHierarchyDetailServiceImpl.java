package com.elecnor.ecosystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DesignationDirectory;
import com.elecnor.ecosystem.bean.DesignationHierarchyDetails;
import com.elecnor.ecosystem.dao.DesignationHierarchyDetailsDAO;
import com.elecnor.ecosystem.service.DesignationHierarchyDetailService;

@Service
public class DesignationHierarchyDetailServiceImpl implements DesignationHierarchyDetailService{
	
	@Autowired
	DesignationHierarchyDetailsDAO desigHierarchy;

	@Override
	public List<DesignationHierarchyDetails> getParentDesignationsForGivenId(int designationId) {
		return desigHierarchy.getParentDesignationsForGivenId(designationId);
	}
	
	
	@Override
	public String addDesignationHierarchy(DesignationDirectory savedDesignation, List<DesignationDirectory> associatedParentDepartments, List<DesignationDirectory> associatedChildDepartments) throws Exception {

		String status = null;
		for (DesignationDirectory designation : associatedParentDepartments) {
			DesignationHierarchyDetails designationHierarchy = new DesignationHierarchyDetails();// adding
																								// new
																								// designation
																								// to
																								// hierarchy
			designationHierarchy.setAssociatedDesignation(savedDesignation);
			designationHierarchy.setAssociatedParentDesignation(designation);
			designationHierarchy.setType("PARENT");
			status = desigHierarchy.addDesignationHierarchy(designationHierarchy);
			DesignationHierarchyDetails parentDesignationHierarchy = new DesignationHierarchyDetails();// pointing exisisting parent records to new record
			designationHierarchy.setAssociatedDesignation(designation);
			designationHierarchy.setAssociatedParentDesignation(savedDesignation);
			parentDesignationHierarchy.setType("CHILD");
			status = desigHierarchy.addDesignationHierarchy(parentDesignationHierarchy);
		}

		for (DesignationDirectory designation : associatedChildDepartments) {

			// adding new designation to hierarchy
			DesignationHierarchyDetails designationHierarchy = new DesignationHierarchyDetails();
			designationHierarchy.setAssociatedDesignation(savedDesignation);
			designationHierarchy.setAssociatedParentDesignation(designation);
			designationHierarchy.setType("CHILD");
			status = desigHierarchy.addDesignationHierarchy(designationHierarchy);

			/* pointing exisiting child records to new record */
			DesignationHierarchyDetails childDepartmentHierarchy = new DesignationHierarchyDetails();
			childDepartmentHierarchy.setAssociatedParentDesignation(savedDesignation);
			childDepartmentHierarchy.setAssociatedDesignation(designation);
			childDepartmentHierarchy.setType("PARENT");
			status = desigHierarchy.addDesignationHierarchy(childDepartmentHierarchy);
		}

		return status;
	}

}
