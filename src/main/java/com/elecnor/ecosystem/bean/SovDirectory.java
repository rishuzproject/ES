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

/**
 * The persistent class for the sov_directory database table.
 * 
 */
@Entity
@Table(name = "SOV_DIRECTORY")
public class SovDirectory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOV_ID")
	@Expose
	private Long sovId;

	@Column(name = "SOV_NAME")
	@Expose
	private String sovName;

	@Column(name = "SOV_TYPE")
	@Expose
	private String sovType;

	@Column(name = "STATUS")
	@Expose
	private String status;

	@Column(name = "INTERNAL_APPROVAL_STATUS")
	@Expose
	private String internalApprovalStatus;

	// bi-directional many-to-one association to SovCustomerReview
	@OneToMany(mappedBy = "sovDirectory")
	private Set<SovReviewsTable> sovCustomerReviews;

	// bi-directional many-to-one association to JobDetail
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	//@Expose
	private JobDetail jobDetail;

	// bi-directional many-to-one association to SovTable
	@OneToMany(mappedBy = "sovDirectory")
	private Set<SovTable> sovTables;

	// bi-directional many-to-one association to Temporary User Detail
	@OneToMany(mappedBy = "sovDirectory")
	private Set<TemporaryUserDetail> temporaryUserDetail;

	// bi-directional many-to-one association to SovTableTracking
	@OneToMany(mappedBy = "sovDirectory")
	private Set<SovTableTracking> sovTableTrackings;

	// bi-directional many-to-one association to SovTableTracking
	@OneToMany(mappedBy = "sovDirectory")
	private Set<SovTableTracking> sovCommentsTable;

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

	public SovDirectory() {
	}

	public Long getSovId() {
		return this.sovId;
	}

	public void setSovId(Long sovId) {
		this.sovId = sovId;
	}

	public String getSovName() {
		return sovName;
	}

	public void setSovName(String sovName) {
		this.sovName = sovName;
	}

	public JobDetail getJobDetail() {
		return this.jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public Set<SovTable> getSovTables() {
		return this.sovTables;
	}

	public void setSovTables(Set<SovTable> sovTables) {
		this.sovTables = sovTables;
	}
	
	public Set<TemporaryUserDetail> getTemporaryUserDetail() {
		return temporaryUserDetail;
	}

	public void setTemporaryUserDetail(Set<TemporaryUserDetail> temporaryUserDetail) {
		this.temporaryUserDetail = temporaryUserDetail;
	}

	public Set<SovTableTracking> getSovTableTrackings() {
		return this.sovTableTrackings;
	}

	public void setSovTableTrackings(Set<SovTableTracking> sovTableTrackings) {
		this.sovTableTrackings = sovTableTrackings;
	}

	public Set<SovTableTracking> getSovCommentsTable() {
		return sovCommentsTable;
	}

	public void setSovCommentsTable(Set<SovTableTracking> sovCommentsTable) {
		this.sovCommentsTable = sovCommentsTable;
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

	public String getSovType() {
		return sovType;
	}

	public void setSovType(String sovType) {
		this.sovType = sovType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInternalApprovalStatus() {
		return internalApprovalStatus;
	}

	public void setInternalApprovalStatus(String internalApprovalStatus) {
		this.internalApprovalStatus = internalApprovalStatus;
	}

	public Set<SovReviewsTable> getSovCustomerReviews() {
		return sovCustomerReviews;
	}

	public void setSovCustomerReviews(Set<SovReviewsTable> sovCustomerReviews) {
		this.sovCustomerReviews = sovCustomerReviews;
	}

}