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
@Table(name="DESIGNATION_DIRECTORY")
public class DesignationDirectory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DESIGNATION_ID")
	@Expose
	private Integer designationId;
	
	@Column(name="DESIGNATION_NAME")
	@Expose
	private String designationName;
	
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private DepartmentDirectory associatedDepartment;
	
	@OneToMany(mappedBy="associatedDesignation")
	@JsonIgnore
	private Set<DesignationHierarchyDetails> associatedDesignationHierarchy;
	
	@OneToMany(mappedBy="associatedParentDesignation")
	@JsonIgnore
	private Set<DesignationHierarchyDetails> associatedParentDesignationHierarchy;

	
//	MAPPINGS
	@OneToMany(mappedBy = "desigId")
	@JsonIgnore
	private Set<UserDetail> userDetailId;
	
	
	
//	Getters n Setters

	
	public Set<DesignationHierarchyDetails> getAssociatedDesignationHierarchy() {
		return associatedDesignationHierarchy;
	}

	public Set<UserDetail> getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(Set<UserDetail> userDetailId) {
		this.userDetailId = userDetailId;
	}

	public void setAssociatedDesignationHierarchy(
			Set<DesignationHierarchyDetails> associatedDesignationHierarchy) {
		this.associatedDesignationHierarchy = associatedDesignationHierarchy;
	}

	public Set<DesignationHierarchyDetails> getAssociatedParentDesignationHierarchy() {
		return associatedParentDesignationHierarchy;
	}

	public void setAssociatedParentDesignationHierarchy(
			Set<DesignationHierarchyDetails> associatedParentDesignationHierarchy) {
		this.associatedParentDesignationHierarchy = associatedParentDesignationHierarchy;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public DepartmentDirectory getAssociatedDepartment() {
		return associatedDepartment;
	}

	public void setAssociatedDepartment(DepartmentDirectory associatedDepartment) {
		this.associatedDepartment = associatedDepartment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
