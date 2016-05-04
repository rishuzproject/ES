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

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the domain_application_plan_mapping database table.
 * 
 */
@Entity
@Table(name = "DOMAIN_APPLICATION_PLAN_MAPPING")
public class DomainApplicationPlanMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUBSCRIPTION_ID")
	private Long subscriptionId;

	@Temporal(TemporalType.DATE)
	@Expose
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "PLAN_EXPIRY_DATE")
	private Date planExpiryDate;

	@Temporal(TemporalType.DATE)
	@Expose
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "PLAN_START_DATE")
	private Date planStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// bi-directional many-to-one association to UserDetail
	@Expose
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@Expose
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	// bi-directional many-to-one association to ApplicationPlanDirectory
	@ManyToOne
	@JoinColumn(name = "PLAN_ID")
	@Expose
	private ApplicationPlanDirectory applicationPlanDirectory;

	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	@Expose
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@Expose
	@JoinColumn(name = "USER_ID")
	private UserDetail userDetail;
	
	// bi-directional many-to-one association to ApplicationDirectory
	@ManyToOne
	@JoinColumn(name = "APPLICATION_ID")
	private ApplicationDirectory applicationDirectory;

	// bi-directional many-to-one association to
	// DomainApplicationPlanMappingTracking
	@OneToMany(mappedBy = "domainApplicationPlanMapping")
	private Set<DomainApplicationPlanMappingTracking> domainApplicationPlanMappingTrackings;
	
	// bi-directional many-to-one association to ApplicationInvoicePlanMapping
	@OneToMany(mappedBy = "domainApplicationPlanMapping")
	private Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMappings;

	/**
	 * @return the applicationInvoicePlanMappings
	 */
	public Set<ApplicationInvoicePlanMapping> getApplicationInvoicePlanMappings() {
		return applicationInvoicePlanMappings;
	}

	/**
	 * @param applicationInvoicePlanMappings the applicationInvoicePlanMappings to set
	 */
	public void setApplicationInvoicePlanMappings(
			Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMappings) {
		this.applicationInvoicePlanMappings = applicationInvoicePlanMappings;
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

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Set<DomainApplicationPlanMappingTracking> getDomainApplicationPlanMappingTrackings() {
		return this.domainApplicationPlanMappingTrackings;
	}

	public void setDomainApplicationPlanMappingTrackings(
			Set<DomainApplicationPlanMappingTracking> domainApplicationPlanMappingTrackings) {
		this.domainApplicationPlanMappingTrackings = domainApplicationPlanMappingTrackings;
	}

	public DomainApplicationPlanMappingTracking addDomainApplicationPlanMappingTracking(
			DomainApplicationPlanMappingTracking domainApplicationPlanMappingTracking) {
		getDomainApplicationPlanMappingTrackings().add(
				domainApplicationPlanMappingTracking);
		domainApplicationPlanMappingTracking
				.setDomainApplicationPlanMapping(this);

		return domainApplicationPlanMappingTracking;
	}

	public DomainApplicationPlanMappingTracking removeDomainApplicationPlanMappingTracking(
			DomainApplicationPlanMappingTracking domainApplicationPlanMappingTracking) {
		getDomainApplicationPlanMappingTrackings().remove(
				domainApplicationPlanMappingTracking);
		domainApplicationPlanMappingTracking
				.setDomainApplicationPlanMapping(null);

		return domainApplicationPlanMappingTracking;
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
	 * @return the subscriptionId
	 */
	public Long getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}