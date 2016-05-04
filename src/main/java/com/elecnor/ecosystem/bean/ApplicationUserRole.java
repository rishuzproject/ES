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

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the application_user_roles database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "APPLICATION_USER_ROLES")
public class ApplicationUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private Long roleId;

	@Expose
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Expose
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Expose
	@Column(name = "SUBMITTED_BY")
	private String submittedBy;
	
	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	@Expose
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@ManyToOne
	@JoinColumn(name = "APPLICATION_ID")
	private ApplicationDirectory applicationDirectory;

	public ApplicationUserRole() {
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the applicationDirectory
	 */
	public ApplicationDirectory getApplicationDirectory() {
		return applicationDirectory;
	}

	/**
	 * @param applicationDirectory
	 *            the applicationDirectory to set
	 */
	public void setApplicationDirectory(
			ApplicationDirectory applicationDirectory) {
		this.applicationDirectory = applicationDirectory;
	}

}