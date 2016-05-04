package com.elecnor.ecosystem.serviceimpl;

/*
 @Author Rishabh
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.OrganizationDirectory;
import com.elecnor.ecosystem.dao.DepartmentDirectoryDAO;
import com.elecnor.ecosystem.dao.DepartmentHierarchyDAO;
import com.elecnor.ecosystem.service.DepartmentDirectoryService;
import com.elecnor.ecosystem.service.DepartmentHierarchyService;

@Service
public class DepartmentDirectoryServiceImpl implements DepartmentDirectoryService {

	@Autowired
	DepartmentDirectoryDAO deptDirDAO;
	@Autowired
	DepartmentHierarchyService departmentHierarchyService;
	
	@Autowired
	DepartmentHierarchyDAO departmentHierarchyDAO;

	@Override
	public List<DepartmentDirectory> getDeptListInOrgUnit(Long organisationId) {
		return deptDirDAO.getDeptListInOrgUnit(organisationId);
	}

	@Override
	public DepartmentDirectory getDeptDirectoryById(Integer departmentId) throws HibernateException {
		return deptDirDAO.getDeptDirectoryById(departmentId);
	}

	@Override
	public String  addorUpdateDepartment(DepartmentDirectory departmentToBeSaved,OrganizationDirectory associatedOrganization){
	
		System.out.println(departmentToBeSaved.getClass());
		departmentToBeSaved.setAssociatedOrganization(associatedOrganization);
		deptDirDAO.saveOrUpdateDepartment(departmentToBeSaved);
		return null;
	}
	@Override
	public String addorUpdateDepartmentHierarchy(DepartmentDirectory selectedDepartment,List<Integer> parentIds,List<Integer> childIds){
		
		
		if(selectedDepartment.getDepartmentId()!=null){
			//delete the existing mapping logic must come here
			String deleteStatus=deleteDepartment(selectedDepartment.getDepartmentId());
			if(deleteStatus!=null)
				return deleteStatus;	
		}
		List<DepartmentDirectory> parentDepartments=deptDirDAO.getDepartmentsBasedOnIds(parentIds);
		List<DepartmentDirectory> childDepartments=deptDirDAO.getDepartmentsBasedOnIds(childIds);
		String status=departmentHierarchyService.addDepartmentHierarchy(selectedDepartment, parentDepartments,childDepartments);
		if(status!=null){
			return status;
		}
		
		return null;
	}
	
	@Override
	public String deleteDepartment(int departmentId){
		String departmentHierarchyStatus=departmentHierarchyDAO.deleteDepartmentHierarchy(departmentId);
		if(departmentHierarchyStatus==null){
			String departmentStatus=deptDirDAO.deleteDepartment(departmentId);
			 return departmentStatus;
		}
		return departmentHierarchyStatus;
	}

	@Override
	public List<DepartmentDirectory> getDepartmentListByDomainId(Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		return deptDirDAO.getDepartmentListByDomainId(domainId);
	}
	
}
