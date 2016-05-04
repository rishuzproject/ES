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
 * The persistent class for the budget_form_dje database table.
 * 
 * @Ashutosh
 * 
 * @Vaibhav Modifing the Bi-Direction Mappings and Expose for the unwanted
 *          attributes.
 */
@Entity
@Table(name = "BUDGET_FORM_DJE")
public class BudgetFormDje implements Serializable {
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

	@Column(name = "COST")
	@Expose
	private Double cost;

	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;
	
	@Column(name = "MATERIAL")
	@Expose
	private double material;

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

	public BudgetFormDje() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
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

	public double getMaterial() {
		return material;
	}

	public void setMaterial(double material) {
		this.material = material;
	}
	
	

}