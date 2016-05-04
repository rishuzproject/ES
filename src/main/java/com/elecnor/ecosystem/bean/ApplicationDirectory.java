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

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the application_directory database table.
 * 
 * @Ankur Version 1.0
 */
/**
 * @author Admin
 *
 */
@Entity
@Table(name = "APPLICATION_DIRECTORY")
public class ApplicationDirectory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APPLICATION_ID")
	private Long applicationId;

	@Expose
	@Column(name = "APPLICATION_LINK")
	private String applicationLink;

	@Expose
	@Column(name = "APPLICATION_NAME")
	private String applicationName;

	@Expose
	@Column(name = "DESCRIPTION")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Expose
	@Column(name = "LIST_UNDER_APP_STORE")
	private Boolean listUnderAppStore;

	@Expose
	@Column(name = "STATUS")
	private String status;

	@Expose
	@DateTimeFormat(pattern = "MM/dd/yyyy h:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	// bi-directional many-to-one association to ApplicationPlanDirectory
	@OneToMany(mappedBy = "applicationDirectory")
	private Set<ApplicationPlanDirectory> applicationPlanDirectories;

	// bi-directional many-to-one association to ApplicationUserMapping
	@OneToMany(mappedBy = "applicationDirectory")
	private Set<ApplicationUserMapping> applicationUserMappings;

	// bi-directional many-to-one association to ApplicationUserMapping
	@OneToMany(mappedBy = "applicationDirectory")
	private Set<ApplicationUserRole> applicationUserRoles;

	// bi-directional many-to-one association to ApplicationInvoicePlanMapping
	@OneToMany(mappedBy = "applicationDirectory")
	private Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMappings;

	// bi-directional many-to-one association to Module Directory
	@OneToMany(mappedBy = "applicationId")
	private Set<ModuleDirectory> moduleDirectory;

	// bi-directional many-to-one association to Role View Mapping
	@OneToMany(mappedBy = "applicationId")
	private Set<RoleViewMapping> roleViewmapping;

	// bi-directional many-to-one association to Temporary User View mapping
	@OneToMany(mappedBy = "applicationId")
	private Set<TemporaryUserViewMapping> temporaryUserViewMapping;

	public ApplicationDirectory() {
	}

	public String getApplicationLink() {
		return this.applicationLink;
	}

	public void setApplicationLink(String applicationLink) {
		this.applicationLink = applicationLink;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getStatus() {
		return this.status;
	}

	public Boolean isListUnderAppStore() {
		return listUnderAppStore;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<ApplicationPlanDirectory> getApplicationPlanDirectories() {
		return this.applicationPlanDirectories;
	}

	public void setApplicationPlanDirectories(Set<ApplicationPlanDirectory> applicationPlanDirectories) {
		this.applicationPlanDirectories = applicationPlanDirectories;
	}

	public Set<ApplicationUserMapping> getApplicationUserMappings() {
		return this.applicationUserMappings;
	}

	public void setApplicationUserMappings(Set<ApplicationUserMapping> applicationUserMappings) {
		this.applicationUserMappings = applicationUserMappings;
	}

	/**
	 * @return the submittedBy
	 */
	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @param submittedBy
	 *            the submittedBy to set
	 */
	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @return the updatedBy
	 */
	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the applicationUserRoles
	 */
	public Set<ApplicationUserRole> getApplicationUserRoles() {
		return applicationUserRoles;
	}

	/**
	 * @param applicationUserRoles
	 *            the applicationUserRoles to set
	 */
	public void setApplicationUserRoles(Set<ApplicationUserRole> applicationUserRoles) {
		this.applicationUserRoles = applicationUserRoles;
	}

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the applicationInvoicePlanMappings
	 */
	public Set<ApplicationInvoicePlanMapping> getApplicationInvoicePlanMappings() {
		return applicationInvoicePlanMappings;
	}

	/**
	 * @param applicationInvoicePlanMappings
	 *            the applicationInvoicePlanMappings to set
	 */
	public void setApplicationInvoicePlanMappings(Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMappings) {
		this.applicationInvoicePlanMappings = applicationInvoicePlanMappings;
	}

	public Set<ModuleDirectory> getModuleDirectory() {
		return moduleDirectory;
	}

	public void setModuleDirectory(Set<ModuleDirectory> moduleDirectory) {
		this.moduleDirectory = moduleDirectory;
	}

	public Set<RoleViewMapping> getRoleViewmapping() {
		return roleViewmapping;
	}

	public void setRoleViewmapping(Set<RoleViewMapping> roleViewmapping) {
		this.roleViewmapping = roleViewmapping;
	}

	public Set<TemporaryUserViewMapping> getTemporaryUserViewMapping() {
		return temporaryUserViewMapping;
	}

	public void setTemporaryUserViewMapping(Set<TemporaryUserViewMapping> temporaryUserViewMapping) {
		this.temporaryUserViewMapping = temporaryUserViewMapping;
	}

}