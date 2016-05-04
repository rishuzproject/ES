package com.elecnor.ecosystem.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="DEPARTMENT_HIERARCHY_DETAILS")
public class DepartmentHierarchyDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HIERARCHY_ID")
	private Long  deptHierarchyId;
	
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private DepartmentDirectory departmentId;
	
	@ManyToOne
	@JoinColumn(name="ASSOCIATED_DEPARTMENT")
	private DepartmentDirectory associatedDepartmentId;
	
	@Column(name="TYPE")
	private String type;

	public Long getDeptHierarchyId() {
		return deptHierarchyId;
	}

	public void setDeptHierarchyId(Long deptHierarchyId) {
		this.deptHierarchyId = deptHierarchyId;
	}

	public DepartmentDirectory getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(DepartmentDirectory departmentId) {
		this.departmentId = departmentId;
	}

	public DepartmentDirectory getAssociatedDepartmentId() {
		return associatedDepartmentId;
	}

	public void setAssociatedDepartmentId(DepartmentDirectory associatedDepartmentId) {
		this.associatedDepartmentId = associatedDepartmentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
