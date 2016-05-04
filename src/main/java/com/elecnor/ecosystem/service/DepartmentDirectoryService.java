package com.elecnor.ecosystem.service;

/*
 @Author Rishabh
 */

import java.util.List;

import org.hibernate.HibernateException;

import com.elecnor.ecosystem.bean.DepartmentDirectory;
import com.elecnor.ecosystem.bean.OrganizationDirectory;

public interface DepartmentDirectoryService {

	public List<DepartmentDirectory> getDeptListInOrgUnit(Long oraganisationId);

	public DepartmentDirectory getDeptDirectoryById(Integer departmentId) throws HibernateException;

	public String addorUpdateDepartmentHierarchy(DepartmentDirectory departmentToBeSaved, List<Integer> parentIds, List<Integer> childIds);

 	public String deleteDepartment(int departmentId);

	String addorUpdateDepartment(DepartmentDirectory departmentToBeSaved,
			OrganizationDirectory associatedOrganization);
	
	public List<DepartmentDirectory> getDepartmentListByDomainId(Long domainId) throws Exception;

}
