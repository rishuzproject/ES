package com.elecnor.ecosystem.bean;

import java.util.List;

public class DepartmentHierarchyPOJO {
	
	String name;
	DepartmentDirectory  selectedDepartment;
	  List<DepartmentHierarchyPOJO> children;
	public DepartmentDirectory getSelectedDepartment() {
		return selectedDepartment;
	}
	public void setSelectedDepartment(DepartmentDirectory selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}
	public   List<DepartmentHierarchyPOJO> getChildren() {
		return children;
	}
	public void setChildren(  List<DepartmentHierarchyPOJO> children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String departmentName) {
		this.name = departmentName;
	}

}
