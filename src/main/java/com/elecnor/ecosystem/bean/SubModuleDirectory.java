package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "SUB_MODULE_DIRECTORY")
public class SubModuleDirectory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUB_MODULE_ID")
	@Expose
	private Long subModuleId;

	@Column(name = "SUB_MODULE_NAME")
	@Expose
	private String subModuleName;

	@Column(name = "DIVISION_ID")
	@Expose
	private String divisionId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// bi-directional many-to-one association to Module Directory
	@ManyToOne
	@JoinColumn(name = "MODULE_ID")
	@Expose
	private ModuleDirectory moduleId;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	// bi-directional many-to-one association to Role View Mapping
	@OneToMany(mappedBy = "subModuleId")
	private Set<RoleViewMapping> roleViewMapping;

	// bi-directional many-to-one association to Temporary User View Mapping
	@OneToMany(mappedBy = "subModuleId")
	private Set<TemporaryUserViewMapping> temporaryUserViewMapping;

	public SubModuleDirectory() {

	}

	public Long getSubModuleId() {
		return subModuleId;
	}

	public void setSubModuleId(Long subModuleId) {
		this.subModuleId = subModuleId;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ModuleDirectory getModuleId() {
		return moduleId;
	}

	public void setModuleId(ModuleDirectory moduleId) {
		this.moduleId = moduleId;
	}

	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}

	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set<RoleViewMapping> getRoleViewMapping() {
		return roleViewMapping;
	}

	public void setRoleViewMapping(Set<RoleViewMapping> roleViewMapping) {
		this.roleViewMapping = roleViewMapping;
	}

	public Set<TemporaryUserViewMapping> getTemporaryUserViewMapping() {
		return temporaryUserViewMapping;
	}

	public void setTemporaryUserViewMapping(Set<TemporaryUserViewMapping> temporaryUserViewMapping) {
		this.temporaryUserViewMapping = temporaryUserViewMapping;
	}

}
