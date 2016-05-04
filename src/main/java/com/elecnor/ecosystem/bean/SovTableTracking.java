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

/**
 * The persistent class for the sov_table_tracking database table.
 * 
 */
@Entity
@Table(name = "SOV_TABLE_TRACKING")
public class SovTableTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOV_TRACKING_ID")
	private String sovTrackingId;

	@Column(name = "BILLING_TYPE")
	private String billingType;

	@Column(name = "EQUIPMENT_TOTAL_COST")
	private double equipmentTotalCost;

	@Column(name = "IS_APPROVED")
	private byte isApproved;

	@Column(name = "LABOR_TOTAL_COST")
	private double laborTotalCost;

	@Column(name = "MATERIAL_TOTAL_COST")
	private double materialTotalCost;

	@Column(name = "OTHER_COST")
	private double otherCost;

	private int quantity;

	@Column(name = "SCHEDULED_VALUE")
	private double scheduledValue;

	@Column(name = "SCHEDULED_VALUE_PCENT")
	private double scheduledValuePcent;

	@Column(name = "TOTAL_COST")
	private double totalCost;

	@Column(name = "UNIT")
	private int unit;

	@Column(name = "UNIT_PRICE")
	private double unitPrice;

	@Column(name = "WORK_DESCRIPTION")
	private String workDescription;

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

	// bi-directional many-to-one association to SovDirectory
	@ManyToOne
	@JoinColumn(name = "SOV_ID")
	private SovDirectory sovDirectory;

	// bi-directional many-to-one association to SovTable
	@ManyToOne
	@JoinColumn(name = "ITEM_NO")
	private SovTable sovTable;

	public SovTableTracking() {
	}

	public String getSovTrackingId() {
		return this.sovTrackingId;
	}

	public void setSovTrackingId(String sovTrackingId) {
		this.sovTrackingId = sovTrackingId;
	}

	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public double getEquipmentTotalCost() {
		return this.equipmentTotalCost;
	}

	public void setEquipmentTotalCost(double equipmentTotalCost) {
		this.equipmentTotalCost = equipmentTotalCost;
	}

	public byte getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(byte isApproved) {
		this.isApproved = isApproved;
	}

	public double getLaborTotalCost() {
		return this.laborTotalCost;
	}

	public void setLaborTotalCost(double laborTotalCost) {
		this.laborTotalCost = laborTotalCost;
	}

	public double getMaterialTotalCost() {
		return this.materialTotalCost;
	}

	public void setMaterialTotalCost(double materialTotalCost) {
		this.materialTotalCost = materialTotalCost;
	}

	public double getOtherCost() {
		return this.otherCost;
	}

	public void setOtherCost(double otherCost) {
		this.otherCost = otherCost;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getScheduledValue() {
		return this.scheduledValue;
	}

	public void setScheduledValue(double scheduledValue) {
		this.scheduledValue = scheduledValue;
	}

	public double getScheduledValuePcent() {
		return this.scheduledValuePcent;
	}

	public void setScheduledValuePcent(double scheduledValuePcent) {
		this.scheduledValuePcent = scheduledValuePcent;
	}

	public double getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getUnit() {
		return this.unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getWorkDescription() {
		return this.workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public SovDirectory getSovDirectory() {
		return this.sovDirectory;
	}

	public void setSovDirectory(SovDirectory sovDirectory) {
		this.sovDirectory = sovDirectory;
	}

	public SovTable getSovTable() {
		return this.sovTable;
	}

	public void setSovTable(SovTable sovTable) {
		this.sovTable = sovTable;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}

	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

}