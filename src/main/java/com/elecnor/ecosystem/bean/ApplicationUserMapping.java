package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 * The persistent class for the application_user_mapping database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "APPLICATION_USER_MAPPING")
public class ApplicationUserMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MAPPING_ID")
	private Long mappingId;

	@Column(name = "ROLE_ID")
	private BigInteger roleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// bi-directional many-to-one association to ApplicationDirectory
	@ManyToOne
	@JoinColumn(name = "APPLICATION_ID")
	private ApplicationDirectory applicationDirectory;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserDetail userId;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	public ApplicationUserMapping() {
	}

	public BigInteger getRoleId() {
		return this.roleId;
	}

	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
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

	public ApplicationDirectory getApplicationDirectory() {
		return this.applicationDirectory;
	}

	public void setApplicationDirectory(
			ApplicationDirectory applicationDirectory) {
		this.applicationDirectory = applicationDirectory;
	}

	/**
	 * @return the userId
	 */
	public UserDetail getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(UserDetail userId) {
		this.userId = userId;
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
	 * @return the mappingId
	 */
	public Long getMappingId() {
		return mappingId;
	}

	/**
	 * @param mappingId
	 *            the mappingId to set
	 */
	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

}