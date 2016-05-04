package com.elecnor.ecosystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.DepartmentHierarchyDetails;
import com.elecnor.ecosystem.dao.DepartmentHierarchyDAO;
import com.elecnor.ecosystem.service.DepartmentHierarchyService;

@Service
public class DepartmentHierarchyServiceImpl implements DepartmentHierarchyService {

	@Autowired
	DepartmentHierarchyDAO departmentHierarchyDAO;

	@Override
	public String addDepartmentHierarchy(DepartmentDirectory savedDepartment, List<DepartmentDirectory> associatedParentDepartments, List<DepartmentDirectory> associatedChildDepartments) {

		String status = null;
		for (DepartmentDirectory department : associatedParentDepartments) {
			DepartmentHierarchyDetails departmentHierarchy = new DepartmentHierarchyDetails();// adding
																								// new
																								// department
																								// to
																								// hierarchy
			departmentHierarchy.setDepartmentId(savedDepartment);
			departmentHierarchy.setAssociatedDepartmentId(department);
			departmentHierarchy.setType("PARENT");
			status = departmentHierarchyDAO.addDepartmentHierarchy(departmentHierarchy);
			DepartmentHierarchyDetails parentDepartmentHierarchy = new DepartmentHierarchyDetails();// pointing
																									// exisisting
																									// parent
																									// records
																									// to
																									// new
																									// record
			parentDepartmentHierarchy.setAssociatedDepartmentId(savedDepartment);
			parentDepartmentHierarchy.setDepartmentId(department);
			parentDepartmentHierarchy.setType("CHILD");
			status = departmentHierarchyDAO.addDepartmentHierarchy(parentDepartmentHierarchy);
		}

		for (DepartmentDirectory department : associatedChildDepartments) {

			// adding new department to hierarchy
			DepartmentHierarchyDetails departmentHierarchy = new DepartmentHierarchyDetails();
			departmentHierarchy.setDepartmentId(savedDepartment);
			departmentHierarchy.setAssociatedDepartmentId(department);
			departmentHierarchy.setType("CHILD");
			status = departmentHierarchyDAO.addDepartmentHierarchy(departmentHierarchy);

			/* pointing exisiting child records to new record */
			DepartmentHierarchyDetails childDepartmentHierarchy = new DepartmentHierarchyDetails();
			childDepartmentHierarchy.setAssociatedDepartmentId(savedDepartment);
			childDepartmentHierarchy.setDepartmentId(department);
			childDepartmentHierarchy.setType("PARENT");
			status = departmentHierarchyDAO.addDepartmentHierarchy(childDepartmentHierarchy);
		}

		return status;
	}

	@Override
	public List<DepartmentHierarchyDetails> getParentDepartment(Integer deptId) {
		return departmentHierarchyDAO.getParentDepartment(deptId);

	}

}
