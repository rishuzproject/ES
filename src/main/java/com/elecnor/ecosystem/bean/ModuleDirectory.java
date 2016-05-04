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
@Table(name = "MODULE_DIRECTORY")
public class ModuleDirectory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MODULE_ID")
	@Expose
	private Long moduleId;

	@Column(name = "MODULE_NAME")
	@Expose
	private String moduleName;

	@Column(name = "MODULE_DIVISION_ID")
	@Expose
	private String moduleDivisionId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// bi-directional many-to-one association to Application Directory
	@ManyToOne
	@JoinColumn(name = "APPLICATION_ID")
	@Expose
	private ApplicationDirectory applicationId;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	// bi-directional many-to-one association to Sub Module Directory
	@OneToMany(mappedBy = "moduleId")
	private Set<SubModuleDirectory> subModuleDirectory;

	// bi-directional many-to-one association to Role View Mapping
	@OneToMany(mappedBy = "moduleId")
	private Set<RoleViewMapping> roleViewMapping;

	// bi-directional many-to-one association to Role View Mapping
	@OneToMany(mappedBy = "moduleId")
	private Set<TemporaryUserViewMapping> temporaryUserViewMapping;

	public ModuleDirectory() {

	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDivisionId() {
		return moduleDivisionId;
	}

	public void setModuleDivisionId(String moduleDivisionId) {
		this.moduleDivisionId = moduleDivisionId;
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

	public ApplicationDirectory getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(ApplicationDirectory applicationId) {
		this.applicationId = applicationId;
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

	public Set<SubModuleDirectory> getSubModuleDirectory() {
		return subModuleDirectory;
	}

	public void setSubModuleDirectory(Set<SubModuleDirectory> subModuleDirectory) {
		this.subModuleDirectory = subModuleDirectory;
	}

	public Set<RoleViewMapping> getRoleViewMapping() {
		return roleViewMapping;
	}

	public void setRoleViewMapping(Set<RoleViewMapping> roleViewMapping) {
		this.roleViewMapping = roleViewMapping;
	}

}
