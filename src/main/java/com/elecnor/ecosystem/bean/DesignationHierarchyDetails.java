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
@Table(name="DESIGNATION_HIERARCHY_DETAILS")
public class DesignationHierarchyDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HIERARCHY_ID")
	private Integer hierarchyId;
	
	@ManyToOne
	@JoinColumn(name="DESIGNATION_ID")
	private DesignationDirectory associatedDesignation;
	@ManyToOne
	@JoinColumn(name="PARENT_DESIGNATION_ID")
	private DesignationDirectory associatedParentDesignation;
	
	@Column(name="TYPE")
	private String type;
	
	public Integer getHierarchyId() {
		return hierarchyId;
	}
	public void setHierarchyId(Integer hierarchyId) {
		this.hierarchyId = hierarchyId;
	}
	public DesignationDirectory getAssociatedDesignation() {
		return associatedDesignation;
	}
	public void setAssociatedDesignation(DesignationDirectory associatedDesignation) {
		this.associatedDesignation = associatedDesignation;
	}
	public DesignationDirectory getAssociatedParentDesignation() {
		return associatedParentDesignation;
	}
	public void setAssociatedParentDesignation(
			DesignationDirectory associatedParentDesignation) {
		this.associatedParentDesignation = associatedParentDesignation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
