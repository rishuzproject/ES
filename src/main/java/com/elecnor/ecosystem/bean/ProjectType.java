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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the project_types database table.
 * 
 * @Ankur Version 1.0
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */
@Entity
@Table(name = "PROJECT_TYPES")
public class ProjectType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	@Column(name = "PROJECT_TYPE_ID")
	private Long projectTypeId;

	@Expose
	@Column(name = "PROJECT_TYPE_NAME")
	@NotBlank(message = "Project Type Name is a Mandatory Field")
	@Size(min=1,max=50,message="Size of project type name must vary between 1 and 50")
	private String projectTypeName;

	@Expose
	@Column(name = "STATUS")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	/*@OneToMany(mappedBy = "projectTypeBean")
	private Set<JobDetail> jobDetails;*/

	// Uni-directional many-to-one association to DomainDetail
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	
	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	
	/**
	 * @param projectTypeId
	 * @param projectTypeName
	 */
	public ProjectType(Long projectTypeId, String projectTypeName) {
		super();
		this.projectTypeId = projectTypeId;
		this.projectTypeName = projectTypeName;
	}

	/**
	 * 
	 */
	public ProjectType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProjectTypeName() {
		return this.projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public String getStatus() {
		return this.status;
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

	public DomainDetail getDomainDetail() {
		return this.domainDetail;
	}

	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
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
	 * @return the projectTypeId
	 */
	public Long getProjectTypeId() {
		return projectTypeId;
	}

	/**
	 * @param projectTypeId
	 *            the projectTypeId to set
	 */
	public void setProjectTypeId(Long projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	/**
	 * @return the jobDetails
	 *//*
	public Set<JobDetail> getJobDetails() {
		return jobDetails;
	}

	*//**
	 * @param jobDetails the jobDetails to set
	 *//*
	public void setJobDetails(Set<JobDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}*/

}