package com.elecnor.ecosystem.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RFC_COST_DETAILS")
public class RfcSubCostTypeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RFC_LOG_SUB_COST_ID")
	private int rfcLogSubCostId;

	@Column(name = "ACTIVITY_ID")
	private int activityId;

	@Column(name = "PROJECT_ID")
	private int projectId;
	@Column(name = "QUANTITY")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "RFC_LOG_SNO")
	private RfcLog rfcLogSno;

	@Column(name = "ACTIVITY_DESCRIPTION")
	private String activityDescription;

	@Column(name = "COST_TYPE")
	private String costType;

	@Column(name = "QUOTED_PRICE")
	private Double quotePrice;

	@Column(name = "ACTIVITY_NUMBER")
	private int activityNumber;

	@Column(name = "SUBMITTED_BY")
	private String submittedBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Transient
	private double salesTax = 0;

	public int getRfcLogSubCostId() {
		return rfcLogSubCostId;
	}

	public void setRfcLogSubCostId(int rfcLogSubCostId) {
		this.rfcLogSubCostId = rfcLogSubCostId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public RfcLog getRfcLogSno() {
		return rfcLogSno;
	}

	public void setRfcLogSno(RfcLog rfcLogSno) {
		this.rfcLogSno = rfcLogSno;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public Double getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(Double quotePrice) {
		this.quotePrice = quotePrice;
	}

	public int getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(int activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
}
