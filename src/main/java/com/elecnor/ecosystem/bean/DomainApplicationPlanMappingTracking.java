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
 * The persistent class for the domain_application_plan_mapping_tracking
 * database table.
 * 
 */
@Entity
@Table(name = "DOMAIN_APPLICATION_PLAN_MAPPING_TRACKING")
public class DomainApplicationPlanMappingTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUBSCRIPTION_TRACKING_ID")
	private Long subscriptionTrackingId;

	@Expose
	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_EXPIRY_DATE")
	private Date planExpiryDate;

	@Expose
	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_START_DATE")
	private Date planStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

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

	// bi-directional many-to-one association to ApplicationPlanDirectory
	@ManyToOne
	@Expose
	@JoinColumn(name = "PLAN_ID")
	private ApplicationPlanDirectory applicationPlanDirectory;

	// bi-directional many-to-one association to DomainApplicationPlanMapping
	@ManyToOne
	@Expose
	@JoinColumn(name = "SUBSCRIPTION_ID")
	private DomainApplicationPlanMapping domainApplicationPlanMapping;
	
	// bi-directional many-to-one association to ApplicationDirectory
	@ManyToOne
	@Expose
	@JoinColumn(name = "APPLICATION_ID")
	private ApplicationDirectory applicationDirectory;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserDetail userDetail;

	public DomainApplicationPlanMappingTracking() {
	}

	public Date getPlanExpiryDate() {
		return this.planExpiryDate;
	}

	public void setPlanExpiryDate(Date planExpiryDate) {
		this.planExpiryDate = planExpiryDate;
	}

	public Date getPlanStartDate() {
		return this.planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
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

	public ApplicationPlanDirectory getApplicationPlanDirectory() {
		return this.applicationPlanDirectory;
	}

	public void setApplicationPlanDirectory(
			ApplicationPlanDirectory applicationPlanDirectory) {
		this.applicationPlanDirectory = applicationPlanDirectory;
	}

	public DomainApplicationPlanMapping getDomainApplicationPlanMapping() {
		return this.domainApplicationPlanMapping;
	}

	public void setDomainApplicationPlanMapping(
			DomainApplicationPlanMapping domainApplicationPlanMapping) {
		this.domainApplicationPlanMapping = domainApplicationPlanMapping;
	}

	/**
	 * @return the domainDetail
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	/**
	 * @param domainDetail the domainDetail to set
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}

	/**
	 * @return the applicationDirectory
	 */
	public ApplicationDirectory getApplicationDirectory() {
		return applicationDirectory;
	}

	/**
	 * @param applicationDirectory the applicationDirectory to set
	 */
	public void setApplicationDirectory(ApplicationDirectory applicationDirectory) {
		this.applicationDirectory = applicationDirectory;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	/**
	 * @return the submittedBy
	 */
	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @param submittedBy the submittedBy to set
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
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the subscriptionTrackingId
	 */
	public Long getSubscriptionTrackingId() {
		return subscriptionTrackingId;
	}

	/**
	 * @param subscriptionTrackingId the subscriptionTrackingId to set
	 */
	public void setSubscriptionTrackingId(Long subscriptionTrackingId) {
		this.subscriptionTrackingId = subscriptionTrackingId;
	}

}