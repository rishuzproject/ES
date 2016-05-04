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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the sov_customer_review database table.
 * 
 */
@Entity
@Table(name = "SOV_REVIEWS_TABLE")
@NamedQuery(name = "SovReviewsTable.findAll", query = "SELECT s FROM SovReviewsTable s")
public class SovReviewsTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOV_REVIEW_ID")
	private String sovReviewId;

	@Column(name = "APPROVAL_STATUS")
	private String approvalStatus;
	
	@Column(name = "APPROVAL_TYPE")
	@Expose
	private String approvalType;

	@Column(name = "APPROVAL_COMMENTS")
	private String approvalComments;

	// bi-directional many-to-one association to SovDirectory
	@ManyToOne
	@JoinColumn(name = "SOV_ID")
	private SovDirectory sovDirectory;

	// bi-directional many-to-one association to SovTable
	@ManyToOne
	@JoinColumn(name = "ITEM_NO")
	private SovTable sovTable;

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

	public SovReviewsTable() {
	}

	public String getSovReviewId() {
		return this.sovReviewId;
	}

	public void setSovReviewId(String sovReviewId) {
		this.sovReviewId = sovReviewId;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalComments() {
		return this.approvalComments;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public SovDirectory getSovDirectory() {
		return this.sovDirectory;
	}

	public void setSovDirectory(SovDirectory sovDirectory) {
		this.sovDirectory = sovDirectory;
	}

	public SovTable getSovTable() {
		return this.sovTable;
	}

	public void setSovTable(SovTable sovTable) {
		this.sovTable = sovTable;
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