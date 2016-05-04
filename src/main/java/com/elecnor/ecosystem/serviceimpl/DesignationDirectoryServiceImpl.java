package com.elecnor.ecosystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.DepartmentHierarchyDetails;
import com.elecnor.ecosystem.bean.DesignationDirectory;
import com.elecnor.ecosystem.dao.DepartmentHierarchyDAO;
import com.elecnor.ecosystem.dao.DesignationDirectoryDAO;
import com.elecnor.ecosystem.dao.DesignationHierarchyDetailsDAO;
import com.elecnor.ecosystem.service.DesignationDirectoryService;
import com.elecnor.ecosystem.service.DesignationHierarchyDetailService;

@Service
public class DesignationDirectoryServiceImpl implements DesignationDirectoryService {

	@Autowired
	DesignationDirectoryDAO desigDAO;

	@Autowired
	DepartmentHierarchyDAO departmentDao;

	@Autowired
	DesignationHierarchyDetailsDAO designationHierarchyDetailsDAO;

	@Autowired
	DesignationHierarchyDetailService designationHierarchyService;

	@Override
	public List<DesignationDirectory> getDesignationInDeptUnit(String departmentId) {
		// First Get all Parent Department IDs
		List<DepartmentHierarchyDetails> deptList = departmentDao.getParentDepartment(Integer.valueOf(departmentId));
		StringBuilder parentDeptIds = new StringBuilder();
		if (null != deptList && deptList.size() > 0) {
			for (DepartmentHierarchyDetails info : deptList) {
				parentDeptIds.append(info.getAssociatedDepartmentId().getDepartmentId());
				parentDeptIds.append(",");
			}
		}
		parentDeptIds.append(departmentId);
		return desigDAO.getDesignationInDeptUnit(parentDeptIds.toString());
	}

	@Override
	public DesignationDirectory getDesignationById(Integer designationId) {
		return desigDAO.getDesignationById(designationId);
	}

	@Override
	public String addOrUpdateDesignationInDepartment(DesignationDirectory designationToBesaved, DepartmentDirectory associatedDepartment) throws Exception {
		String result = "";
		designationToBesaved.setAssociatedDepartment(associatedDepartment);
		desigDAO.addorUpdateDesignationInDepartment(designationToBesaved);
		return result;
	}

	@Override
	public String addOrUpdateDesignationHierarchyInDepartment(DesignationDirectory designationToBeEdited, String childIds, String parentIds) throws Exception {

		String result = "";
		result = designationHierarchyDetailsDAO.deleteDesignationHierarchyDetails(designationToBeEdited.getDesignationId());
		if (!result.equalsIgnoreCase("success")) {
			return result;
		}

		List<DesignationDirectory> childDesignations = desigDAO.getDesignationInDeptUnit(childIds);
		List<DesignationDirectory> parentDesignations = desigDAO.getDesignationInDeptUnit(parentIds);
		String status = designationHierarchyService.addDesignationHierarchy(designationToBeEdited, parentDesignations, childDesignations);
		if (status != null) {
			return status;
		}

		return null;
	}

	@Override
	public String deleteDesignation(int designationId) throws Exception {
		String departmentHierarchyStatus = designationHierarchyDetailsDAO.deleteDesignationHierarchyDetails(designationId);
		if (departmentHierarchyStatus == null) {
			String departmentStatus = desigDAO.deleteDesignation(designationId);
			return departmentStatus;
		}
		return departmentHierarchyStatus;
	}

}
