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

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */
@Entity
@Table(name = "SOV_COMMENTS_TABLE")
public class SovCommentsTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOV_COMMENT_ID")
	@Expose
	private Long sovCommentId;

	@Column(name = "SUPERVISOR_COMMENTS")
	@Expose
	private String supervisorComments;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// Uni-directional many-to-one association to SovDirectory
	@ManyToOne
	@JoinColumn(name = "SOV_ID")
	//@Expose
	private SovDirectory sovDirectory;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	//@Expose
	private UserDetail submittedBy;
	
	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	public SovCommentsTable() {

	}

	public Long getSovCommentId() {
		return sovCommentId;
	}

	public void setSovCommentId(Long sovCommentId) {
		this.sovCommentId = sovCommentId;
	}

	public String getSupervisorComments() {
		return supervisorComments;
	}

	public void setSupervisorComments(String supervisorComments) {
		this.supervisorComments = supervisorComments;
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
