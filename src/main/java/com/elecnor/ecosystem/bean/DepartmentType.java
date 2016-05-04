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

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the department_types database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "DEPARTMENT_TYPES")
public class DepartmentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEPARTMENT_ID")
	@Expose
	private Long departmentId;

	@Column(name = "DEPARTMENT_NAME")
	@Expose
	@NotBlank(message = "Department Name is a Mandatory Field")
	private String departmentName;

	@Column(name = "STATUS")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	
	@OneToMany(mappedBy="departmentId")
	private  Set<DepartmentUserMapping> departmentDetails; 

	// bi-directional many-to-one association to JobDetail
	@OneToMany(mappedBy = "departmentType")
	private Set<JobDetail> jobDetails;

	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	public DepartmentType() {
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	 * @return the departmentId
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the jobDetails
	 */
	public Set<JobDetail> getJobDetails() {
		return jobDetails;
	}

	/**
	 * @param jobDetails the jobDetails to set
	 */
	public void setJobDetails(Set<JobDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}

	public Set<DepartmentUserMapping>  getDepartmentDetails() {
		return departmentDetails;
	}

	public void setDepartmentDetails(Set<DepartmentUserMapping>  departmentDetails) {
		this.departmentDetails = departmentDetails;
	}

}