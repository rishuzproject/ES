package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ROLE_VIEW_MAPPING")
public class RoleViewMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MAPPING_ID")
	@Expose
	private Long mappingId;

	@Column(name = "IS_VISIBLE")
	@Expose
	private Boolean isVisible;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// bi-directional many-to-one association to User Roles
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private UserRoles roleId;

	// bi-directional many-to-one association to Application Directory
	@ManyToOne
	@JoinColumn(name = "APPLICATION_ID")
	@Expose
	private ApplicationDirectory applicationId;

	// bi-directional many-to-one association to Module Directory
	@ManyToOne
	@JoinColumn(name = "MODULE_ID")
	@Expose
	private ModuleDirectory moduleId;

	// bi-directional many-to-one association to Sub Module Directory
	@ManyToOne
	@JoinColumn(name = "SUB_MODULE_ID")
	@Expose
	private SubModuleDirectory subModuleId;

	// bi-directional many-to-one association to Domain Detail
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	@Expose
	private DomainDetail domainId;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	public RoleViewMapping() {

	}

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
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

	public UserRoles getRoleId() {
		return roleId;
	}

	public void setRoleId(UserRoles roleId) {
		this.roleId = roleId;
	}

	public ApplicationDirectory getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(ApplicationDirectory applicationId) {
		this.applicationId = applicationId;
	}

	public ModuleDirectory getModuleId() {
		return moduleId;
	}

	public void setModuleId(ModuleDirectory moduleId) {
		this.moduleId = moduleId;
	}

	public SubModuleDirectory getSubModuleId() {
		return subModuleId;
	}

	public void setSubModuleId(SubModuleDirectory subModuleId) {
		this.subModuleId = subModuleId;
	}

	public DomainDetail getDomainId() {
		return domainId;
	}

	public void setDomainId(DomainDetail domainId) {
		this.domainId = domainId;
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

}
