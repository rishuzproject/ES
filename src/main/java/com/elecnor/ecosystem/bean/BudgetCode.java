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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the budget_codes database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "BUDGET_CODES")
public class BudgetCode implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACTIVITY_ID")
	private Long activityId;

	@Expose
	@Column(name = "ACTIVITY_DESCRIPTION")
	@NotBlank(message = "Activity Description is a Mandatory Field")
	private String activityDescription;

	@Expose
	@Column(name = "ACTIVITY_NUMBER")
	@Digits(integer=11, fraction = 0, message = "Activity Number should only have digits")
	@Min(value=1, message = "Activity Number is a mandatory field")
	private Integer activityNumber;

	@Expose
	@Column(name = "COST_TYPE")
	@NotBlank(message = "Cost Type is a Mandatory Field")
	private String costType;

	@Expose
	@Column(name = "STATUS")
	private String status;

	//@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	//@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	//@Expose
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	//@Expose
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	//@Expose
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	public BudgetCode() {
	}

	public String getActivityDescription() {
		return this.activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public Integer getActivityNumber() {
		return this.activityNumber;
	}

	public void setActivityNumber(Integer activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getCostType() {
		return this.costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
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
	 * @return the activityId
	 */
	public Long getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

}