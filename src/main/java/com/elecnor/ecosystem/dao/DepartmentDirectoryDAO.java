package com.elecnor.ecosystem.dao;

/*
 @Author Rishabh
 */

import java.util.List;

import org.hibernate.HibernateException;

import com.elecnor.ecosystem.bean.DepartmentDirectory;

/*
 * Department DAO CRUD Operation
 * @ Rishabh
 * 
 */

public interface DepartmentDirectoryDAO {

	public DepartmentDirectory saveOrUpdateDepartment(DepartmentDirectory departmentDirectory) throws HibernateException;

		

	public List<DepartmentDirectory> getDeptListInOrgUnit(Long organisationId) throws HibernateException;
	
	public DepartmentDirectory getDeptDirectoryById(Integer departmentId) throws HibernateException;

	public List<DepartmentDirectory> getDepartmentsBasedOnIds(
			List<Integer> departmentIds);

	public List<DepartmentDirectory> getAllDepartments() throws HibernateException;
	public String deleteDepartment(int departmentDirectoryId) throws HibernateException;
	
	public List<DepartmentDirectory> getDepartmentListByDomainId(Long domainId) throws Exception;

}
