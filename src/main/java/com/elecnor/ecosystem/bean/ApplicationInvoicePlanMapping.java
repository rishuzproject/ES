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
 * The persistent class for the application_invoice_plan_mapping database table.
 * 
 */
@Entity
@Table(name="APPLICATION_INVOICE_PLAN_MAPPING")
public class ApplicationInvoicePlanMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INVOICE_SUBSET_ID")
	private int invoiceSubsetId;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	//bi-directional many-to-one association to ApplicationDirectory
	@ManyToOne
	@Expose
	@JoinColumn(name="APPLICATION_ID")
	private ApplicationDirectory applicationDirectory;
	
	// bi-directional many-to-one association to DomainApplicationPlanMapping
	@ManyToOne
	@Expose
	@JoinColumn(name = "SUBSCRIPTION_ID")
	private DomainApplicationPlanMapping domainApplicationPlanMapping;
	
	// bi-directional many-to-one association to DomainApplicationPlanMapping
	@ManyToOne
	@Expose
	@JoinColumn(name = "INVOICE_ID")
	private ApplicationInvoice applicationInvoice;
	
	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	@Expose
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@Expose
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	
	public ApplicationInvoicePlanMapping() {
	}

	public int getInvoiceSubsetId() {
		return this.invoiceSubsetId;
	}

	public void setInvoiceSubsetId(int invoiceSubsetId) {
		this.invoiceSubsetId = invoiceSubsetId;
	}

	public ApplicationDirectory getApplicationDirectory() {
		return this.applicationDirectory;
	}

	public void setApplicationDirectory(ApplicationDirectory applicationDirectory) {
		this.applicationDirectory = applicationDirectory;
	}
	
	/**
	 * @return the domainApplicationPlanMapping
	 */
	public DomainApplicationPlanMapping getDomainApplicationPlanMapping() {
		return domainApplicationPlanMapping;
	}

	/**
	 * @param domainApplicationPlanMapping the domainApplicationPlanMapping to set
	 */
	/**
	 * @param domainApplicationPlanMapping
	 */
	public void setDomainApplicationPlanMapping(
			DomainApplicationPlanMapping domainApplicationPlanMapping) {
		this.domainApplicationPlanMapping = domainApplicationPlanMapping;
	}

	/**
	 * @return the applicationInvoice
	 */
	public ApplicationInvoice getApplicationInvoice() {
		return applicationInvoice;
	}

	/**
	 * @param applicationInvoice the applicationInvoice to set
	 */
	public void setApplicationInvoice(ApplicationInvoice applicationInvoice) {
		this.applicationInvoice = applicationInvoice;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
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

}