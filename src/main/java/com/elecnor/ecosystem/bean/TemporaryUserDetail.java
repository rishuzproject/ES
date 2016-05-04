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
@Table(name = "TEMPORARY_USER_DETAIL")
public class TemporaryUserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEMPORARY_USER_NO")
	@Expose
	private Long temporaryUserNo;

	@Column(name = "USER_EMAIL_ID")
	@Expose
	private String userEmailId;

	@Column(name = "USER_ACTIVATION_LINK")
	@Expose
	private String userActivationLink;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVATION_START_DATE")
	@Expose
	private Date activationStartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVATION_END_DATE")
	@Expose
	private Date activationEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// bi-directional many-to-one association to SovDirectory
	@Expose
	@ManyToOne
	@JoinColumn(name = "SOV_ID")
	private SovDirectory sovDirectory;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	
	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	public Long getTemporaryUserNo() {
		return temporaryUserNo;
	}

	public void setTemporaryUserNo(Long temporaryUserNo) {
		this.temporaryUserNo = temporaryUserNo;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserActivationLink() {
		return userActivationLink;
	}

	public void setUserActivationLink(String userActivationLink) {
		this.userActivationLink = userActivationLink;
	}

	public Date getActivationStartDate() {
		return activationStartDate;
	}

	public void setActivationStartDate(Date activationStartDate) {
		this.activationStartDate = activationStartDate;
	}

	public Date getActivationEndDate() {
		return activationEndDate;
	}

	public void setActivationEndDate(Date activationEndDate) {
		this.activationEndDate = activationEndDate;
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

	public SovDirectory getSovDirectory() {
		return sovDirectory;
	}

	public void setSovDirectory(SovDirectory sovDirectory) {
		this.sovDirectory = sovDirectory;
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
