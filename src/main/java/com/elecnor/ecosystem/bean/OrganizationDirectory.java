package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="ORGANIZATION_DIRECTORY")
public class OrganizationDirectory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ORGANIZATION_ID")
	@Expose
	private Long organizationId;
	
	@Expose
	@Column(name="ORGANIZATION_NAME")
	private String organizationName;

	
	@OneToMany(mappedBy="associatedOrganization")
	@JsonIgnore
	private  Set<DepartmentDirectory>  associatedDepartment;
	
	
	
//	MAPPINGS
	
	@OneToMany(mappedBy = "orgUnitId")
	@JsonIgnore
	private Set<UserDetail> userDetailId;
	
	
	
	
//	Getters n Setters
	
	public Set<UserDetail> getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(Set<UserDetail> userDetailId) {
		this.userDetailId = userDetailId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<DepartmentDirectory> getAssociatedDepartment() {
		return associatedDepartment;
	}

	public void setAssociatedDepartment(
			Set<DepartmentDirectory> associatedDepartment) {
		this.associatedDepartment = associatedDepartment;
	}

}
