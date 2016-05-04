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

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the budget_form_labor database table.
 * 
 * @author Ashutosh
 * 
 * @Vaibhav Modifing Bi-Direction Mappings and Expose for the unwanted
 *          attributes.
 */
@Entity
@Table(name = "BUDGET_FORM_LABOR")
public class BudgetFormLabor implements Serializable {
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

	@Column(name = "BURDEN")
	@Expose
	private Double burden;

	@Column(name = "FOREMAN_RATE")
	@Expose
	private Double foremanRate;

	@Column(name = "FRINGES")
	@Expose
	private Double fringes;

	@Column(name = "HOURS")
	@Expose
	private Double hours;

	@Column(name = "LAB_W_BDN")
	@Expose
	private Double labWBdn;

	@Column(name = "LAB_WO_BDN")
	@Expose
	private Double labWoBdn;

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
	 * @return the burden
	 */
	public Double getBurden() {
		return burden;
	}

	/**
	 * @param burden
	 *            the burden to set
	 */
	public void setBurden(Double burden) {
		this.burden = burden;
	}

	/**
	 * @return the foremanRate
	 */
	public Double getForemanRate() {
		return foremanRate;
	}

	/**
	 * @param foremanRate
	 *            the foremanRate to set
	 */
	public void setForemanRate(Double foremanRate) {
		this.foremanRate = foremanRate;
	}

	/**
	 * @return the fringes
	 */
	public Double getFringes() {
		return fringes;
	}

	/**
	 * @param fringes
	 *            the fringes to set
	 */
	public void setFringes(Double fringes) {
		this.fringes = fringes;
	}

	/**
	 * @return the hours
	 */
	public Double getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(Double hours) {
		this.hours = hours;
	}

	/**
	 * @return the labWBdn
	 */
	public Double getLabWBdn() {
		return labWBdn;
	}

	/**
	 * @param labWBdn
	 *            the labWBdn to set
	 */
	public void setLabWBdn(Double labWBdn) {
		this.labWBdn = labWBdn;
	}

	/**
	 * @return the labWoBdn
	 */
	public Double getLabWoBdn() {
		return labWoBdn;
	}

	/**
	 * @param labWoBdn
	 *            the labWoBdn to set
	 */
	public void setLabWoBdn(Double labWoBdn) {
		this.labWoBdn = labWoBdn;
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

}