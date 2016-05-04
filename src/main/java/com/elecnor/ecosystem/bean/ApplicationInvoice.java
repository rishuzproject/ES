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
 * The persistent class for the application_invoice database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "APPLICATION_INVOICE")
public class ApplicationInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INVOICE_ID")
	private Long invoiceId;

	@Expose
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "INVOICE_DUE_DATE")
	private Date invoiceDueDate;

	@Expose
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "INVOICE_GENERATED_DATE")
	private Date invoiceGeneratedDate;
	
	@Expose
	@Column(name = "INVOICE_TYPE")
	private String invoiceType;
	
	@Expose
	@Column(name = "ADDITIONAL_CHARGES")
	private Double additionalCharges;

	@Expose
	@Column(name = "IS_INTEREST_APPLICABLE")
	private Boolean isInterestApplicable;

	@Expose
	@Column(name = "IS_LATE_FEES_APPLICABLE")
	private Boolean isLateFeesApplicable;

	@Column(name = "STATUS")
	@Expose
	private String status;

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

	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	@Expose
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;
	
	// bi-directional many-to-one association to ApplicationInvoicePlanMapping
	@OneToMany(mappedBy = "applicationInvoice")
	private Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMapping;

	/**
	 * @return the applicationInvoicePlanMapping
	 */
	public Set<ApplicationInvoicePlanMapping> getApplicationInvoicePlanMapping() {
		return applicationInvoicePlanMapping;
	}

	/**
	 * @param applicationInvoicePlanMapping the applicationInvoicePlanMapping to set
	 */
	public void setApplicationInvoicePlanMapping(
			Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMapping) {
		this.applicationInvoicePlanMapping = applicationInvoicePlanMapping;
	}

	public ApplicationInvoice() {
	}

	public Double getAdditionalCharges() {
		return additionalCharges;
	}

	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Date getInvoiceGeneratedDate() {
		return invoiceGeneratedDate;
	}

	public void setInvoiceGeneratedDate(Date invoiceGeneratedDate) {
		this.invoiceGeneratedDate = invoiceGeneratedDate;
	}

	public void setAdditionalCharges(Double additionalCharges) {
		this.additionalCharges = additionalCharges;
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

	/**
	 * @return the domainDetail
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	/**
	 * @param domainDetail
	 *            the domainDetail to set
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}

	/**
	 * @return the invoiceId
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @param invoiceId
	 *            the invoiceId to set
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return the isInterestApplicable
	 */
	public Boolean isInterestApplicable() {
		return isInterestApplicable;
	}

	/**
	 * @param isInterestApplicable
	 *            the isInterestApplicable to set
	 */
	public void setInterestApplicable(Boolean isInterestApplicable) {
		this.isInterestApplicable = isInterestApplicable;
	}

	/**
	 * @return the isLateFeesApplicable
	 */
	public Boolean isLateFeesApplicable() {
		return isLateFeesApplicable;
	}

	/**
	 * @param isLateFeesApplicable
	 *            the isLateFeesApplicable to set
	 */
	public void setLateFeesApplicable(Boolean isLateFeesApplicable) {
		this.isLateFeesApplicable = isLateFeesApplicable;
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

}