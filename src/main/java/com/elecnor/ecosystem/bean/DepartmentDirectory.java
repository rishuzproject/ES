package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;


@Entity
@Table(name="DEPARTMENT_DIRECTORY")
public class DepartmentDirectory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DEPARTMENT_ID")
	@Expose
	private Integer departmentId;
	
	@Column(name="DEPARTMENT_NAME")
	@Expose
	private String departmentName;
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private OrganizationDirectory  associatedOrganization;
	
	@OneToMany(mappedBy="associatedDepartment")
	@JsonIgnore
	private Set<DesignationDirectory> associatedDesignation;

	
	@OneToMany(mappedBy="departmentId")
	@JsonIgnore
	private Set<DepartmentHierarchyDetails> departmentHierarchyId;
	
	@OneToMany(mappedBy="associatedDepartmentId")
	@JsonIgnore
	private Set<DepartmentHierarchyDetails>  associateddepartmentHierarchyId;
	
//	MAPPINGS
	
	@OneToMany(mappedBy = "deptUnitId")
	@JsonIgnore
	private Set<UserDetail> userDetailId;
	
	
	/*@OneToMany(mappedBy = "departmentType")
	private Set<JobDetail> jobDetails;*/
	
//	Getters n Setters
	
	public DepartmentDirectory() {
		super();
	}
	
	/**
	 * @param departmentId
	 * @param departmentName
	 */
	public DepartmentDirectory(Integer departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public Set<DesignationDirectory> getAssociatedDesignation() {
		return associatedDesignation;
	}

	public Set<UserDetail> getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(Set<UserDetail> userDetailId) {
		this.userDetailId = userDetailId;
	}

	public void setAssociatedDesignation(
			Set<DesignationDirectory> associatedDesignation) {
		this.associatedDesignation = associatedDesignation;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public OrganizationDirectory getAssociatedOrganization() {
		return associatedOrganization;
	}

	public void setAssociatedOrganization(
			OrganizationDirectory associatedOrganization) {
		this.associatedOrganization = associatedOrganization;
	}

	public Set<DepartmentHierarchyDetails> getDepartmentHierarchyId() {
		return departmentHierarchyId;
	}

	public void setDepartmentHierarchyId(
			Set<DepartmentHierarchyDetails> departmentHierarchyId) {
		this.departmentHierarchyId = departmentHierarchyId;
	}

	public Set<DepartmentHierarchyDetails> getAssociateddepartmentHierarchyId() {
		return associateddepartmentHierarchyId;
	}

	public void setAssociateddepartmentHierarchyId(
			Set<DepartmentHierarchyDetails> associateddepartmentHierarchyId) {
		this.associateddepartmentHierarchyId = associateddepartmentHierarchyId;
	}

	/*public Set<JobDetail> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(Set<JobDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}
	*/
	

}
