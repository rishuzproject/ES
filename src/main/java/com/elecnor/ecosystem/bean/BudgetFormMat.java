package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the budget_form_mat database table.
 * 
 * @author Ashutosh
 * 
 * @Vaibhav Modifing Bi-Direction Mappings and Expose for the unwanted
 *          attributes.
 */
@Entity
@Table(name = "BUDGET_FORM_MAT")
public class BudgetFormMat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROW_ID")
	@Expose
	private BigInteger rowId;

	@Column(name = "ACTIVITY_DESCRIPTION", length = 500)
	@Expose
	private String activityDescription;

	@Column(name = "ACTIVITY_NUMBER")
	@Expose
	private Integer activityNumber;

	@Column(name = "MATERIAL")
	@Expose
	private Double material;

	@Column(name = "MISC_MAT")
	@Expose
	private Double miscMat;

	@Column(name = "QUOTED_ITEMS")
	@Expose
	private Double quotedItems;

	@Column(name = "SALES_TAX")
	@Expose
	private Double salesTax;

	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	// bi-directional many-to-one association to RfcLog
	@ManyToOne
	@JoinColumn(name = "RFC_LOG_ID")
	private RfcLog rfcLog;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedByUserDetail;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedByUserDetail;

	// Uni-directional many-to-one association to Job Details
	@ManyToOne
	@JoinColumn(name = "JOB_ID")
	private JobDetail jobDetail;

	/**
	 * @return the jobDetail
	 */
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	/**
	 * @param jobDetail
	 *            the jobDetail to set
	 */
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	@Transient
	@Expose
	private double valInclsTax;

	/**
	 * @return the rowId
	 */
	public BigInteger getRowId() {
		return rowId;
	}

	/**
	 * @param rowId
	 *            the rowId to set
	 */
	public void setRowId(BigInteger rowId) {
		this.rowId = rowId;
	}

	/**
	 * @return the activityDescription
	 */
	public String getActivityDescription() {
		return activityDescription;
	}

	/**
	 * @param activityDescription
	 *            the activityDescription to set
	 */
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	/**
	 * @return the activityNumber
	 */
	public Integer getActivityNumber() {
		return activityNumber;
	}

	/**
	 * @param activityNumber
	 *            the activityNumber to set
	 */
	public void setActivityNumber(Integer activityNumber) {
		this.activityNumber = activityNumber;
	}

	/**
	 * @return the material
	 */
	public Double getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(Double material) {
		this.material = material;
	}

	/**
	 * @return the miscMat
	 */
	public Double getMiscMat() {
		return miscMat;
	}

	/**
	 * @param miscMat
	 *            the miscMat to set
	 */
	public void setMiscMat(Double miscMat) {
		this.miscMat = miscMat;
	}

	/**
	 * @return the quotedItems
	 */
	public Double getQuotedItems() {
		return quotedItems;
	}

	/**
	 * @param quotedItems
	 *            the quotedItems to set
	 */
	public void setQuotedItems(Double quotedItems) {
		this.quotedItems = quotedItems;
	}

	/**
	 * @return the salesTax
	 */
	public Double getSalesTax() {
		return salesTax;
	}

	/**
	 * @param salesTax
	 *            the salesTax to set
	 */
	public void setSalesTax(Double salesTax) {
		this.salesTax = salesTax;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate
	 *            the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the rfcLog
	 */
	public RfcLog getRfcLog() {
		return rfcLog;
	}

	/**
	 * @param rfcLog
	 *            the rfcLog to set
	 */
	public void setRfcLog(RfcLog rfcLog) {
		this.rfcLog = rfcLog;
	}

	/**
	 * @return the submittedByUserDetail
	 */
	public UserDetail getSubmittedByUserDetail() {
		return submittedByUserDetail;
	}

	/**
	 * @param submittedByUserDetail
	 *            the submittedByUserDetail to set
	 */
	public void setSubmittedByUserDetail(UserDetail submittedByUserDetail) {
		this.submittedByUserDetail = submittedByUserDetail;
	}

	/**
	 * @return the updatedByUserDetail
	 */
	public UserDetail getUpdatedByUserDetail() {
		return updatedByUserDetail;
	}

	/**
	 * @param updatedByUserDetail
	 *            the updatedByUserDetail to set
	 */
	public void setUpdatedByUserDetail(UserDetail updatedByUserDetail) {
		this.updatedByUserDetail = updatedByUserDetail;
	}

	/**
	 * @param valInclsTax
	 *            the valInclsTax to set
	 */
	public void setValInclsTax(double valInclsTax) {
		this.valInclsTax = valInclsTax;
	}

	/**
	 * 
	 * @return the valInclsTax
	 */
	public double getValInclsTax() {
		valInclsTax = Math.round((((1 + (this.getMiscMat() / 100)) * this.getMaterial()) + this.getQuotedItems()) * (1 + (this.getSalesTax() / 100)));
		return valInclsTax;
	}

}