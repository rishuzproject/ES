package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * The persistent class for the application_plan_directory database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "APPLICATION_PLAN_DIRECTORY")
public class ApplicationPlanDirectory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAN_ID")
	private Long planId;

	@Expose
	@Column(name = "DATA_USAGE")
	private Double dataUsage;

	@Expose
	@Column(name = "MAX_NUMBER_OF_USER")
	private Integer maxNumberOfUser;

	@Expose
	@Column(name = "PLAN_ADDITIONAL_DESCRIPTION")
	private String planAdditionalDescription;

	@Expose
	@Column(name = "PLAN_DESCRIPTION")
	private String planDescription;

	@Column(name = "PLAN_NAME")
	@Expose
	private String planName;

	@Expose
	@Column(name = "PLAN_VALIDITY_IN_MONTHS")
	private BigInteger planValidityInMonths;

	@Expose
	@Column(name = "PRICE")
	private Double price;

	@Column(name = "STATUS")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// bi-directional many-to-one association to ApplicationDirectory
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "APPLICATION_ID")
	@Expose
	private ApplicationDirectory applicationDirectory;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	// bi-directional many-to-one association to DomainApplicationPlanMapping
	@OneToMany(mappedBy = "applicationPlanDirectory")
	private Set<DomainApplicationPlanMapping> domainApplicationPlanMappings;

	// bi-directional many-to-one association to
	// DomainApplicationPlanMappingTracking
	@OneToMany(mappedBy = "applicationPlanDirectory")
	private Set<DomainApplicationPlanMappingTracking> domainApplicationPlanMappingTrackings;

	public Double getDataUsage() {
		return this.dataUsage;
	}

	public void setDataUsage(Double dataUsage) {
		this.dataUsage = dataUsage;
	}

	public Integer getMaxNumberOfUser() {
		return this.maxNumberOfUser;
	}

	public void setMaxNumberOfUser(Integer maxNumberOfUser) {
		this.maxNumberOfUser = maxNumberOfUser;
	}

	public String getPlanAdditionalDescription() {
		return this.planAdditionalDescription;
	}

	public void setPlanAdditionalDescription(String planAdditionalDescription) {
		this.planAdditionalDescription = planAdditionalDescription;
	}

	public String getPlanDescription() {
		return this.planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BigInteger getPlanValidityInMonths() {
		return this.planValidityInMonths;
	}

	public void setPlanValidityInMonths(BigInteger planValidityInMonths) {
		this.planValidityInMonths = planValidityInMonths;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public ApplicationDirectory getApplicationDirectory() {
		return this.applicationDirectory;
	}

	public void setApplicationDirectory(
			ApplicationDirectory applicationDirectory) {
		this.applicationDirectory = applicationDirectory;
	}

	public Set<DomainApplicationPlanMapping> getDomainApplicationPlanMappings() {
		return this.domainApplicationPlanMappings;
	}

	public void setDomainApplicationPlanMappings(
			Set<DomainApplicationPlanMapping> domainApplicationPlanMappings) {
		this.domainApplicationPlanMappings = domainApplicationPlanMappings;
	}

	public Set<DomainApplicationPlanMappingTracking> getDomainApplicationPlanMappingTrackings() {
		return this.domainApplicationPlanMappingTrackings;
	}

	public void setDomainApplicationPlanMappingTrackings(
			Set<DomainApplicationPlanMappingTracking> domainApplicationPlanMappingTrackings) {
		this.domainApplicationPlanMappingTrackings = domainApplicationPlanMappingTrackings;
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
	 * @return the planId
	 */
	public Long getPlanId() {
		return planId;
	}

	/**
	 * @param planId
	 *            the planId to set
	 */
	public void setPlanId(Long planId) {
		this.planId = planId;
	}

}