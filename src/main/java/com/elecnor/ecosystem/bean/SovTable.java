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

import com.elecnor.ecosystem.util.Utility;
import com.google.gson.annotations.Expose;

/**
 * The persistent class for the sov_table database table.
 * 
 */
@Entity
@Table(name = "SOV_TABLE")
public class SovTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEM_NO")
	@Expose
	private Long itemNo;

	@Column(name = "BILLING_TYPE")
	@Expose
	private String billingType;

	@Column(name = "EQUIPMENT_TOTAL_COST")
	@Expose
	private Double equipmentTotalCost;

	@Column(name = "IS_APPROVED_INTERNAL")
	@Expose
	private Boolean isApprovedInternal;
	
	@Column(name = "IS_APPROVED_EXTERNAL")
	@Expose
	private Boolean isApprovedExternal;
	
	@Column(name="EXTERNAL_REQUEST_STATUS")
	@Expose
	private String externalRequestStatus;

	@Column(name = "LABOR_TOTAL_COST")
	@Expose
	private Double laborTotalCost;

	@Column(name = "MATERIAL_TOTAL_COST")
	@Expose
	private Double materialTotalCost;

	@Column(name = "OTHER_COST")
	@Expose
	private Double otherCost;

	@Column(name = "QUANTITY")
	@Expose
	private Integer quantity;

	@Column(name = "SCHEDULED_VALUE")
	@Expose
	private Double scheduledValue;

	@Column(name = "SCHEDULED_VALUE_PCENT")
	@Expose
	private Double scheduledValuePcent;

	@Column(name = "TOTAL_COST")
	@Expose
	private Double totalCost;

	@Column(name = "UNIT")
	@Expose
	private Integer unit;

	@Column(name = "UNIT_PRICE")
	@Expose
	private Double unitPrice;

	@Column(name = "WORK_DESCRIPTION")
	@Expose
	private String workDescription;
	
	@Column(name="STATUS")
	@Expose
	private String status;

	// bi-directional many-to-one association to SovCustomerReview
	@OneToMany(mappedBy = "sovTable")
	private Set<SovReviewsTable> sovCustomerReviews;

	// bi-directional many-to-one association to SovDirectory
	@Expose
	@ManyToOne
	@JoinColumn(name = "SOV_ID")
	private SovDirectory sovDirectory;

	// bi-directional many-to-one association to SovTableTracking
	@OneToMany(mappedBy = "sovTable")
	private Set<SovTableTracking> sovTableTrackings;

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

	@Override
	public String toString() {
		return "Serial No." + itemNo + "Work description " + workDescription + "Billing type " + billingType;
	}

	public SovTable() {
	}

	public Long getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}

	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public Double getEquipmentTotalCost() {
		return Utility.returnValueForNull(this.equipmentTotalCost);
	}

	public void setEquipmentTotalCost(Double equipmentTotalCost) {
		this.equipmentTotalCost = Utility.returnValueForNull(equipmentTotalCost);
	}

	public Boolean getIsApprovedInternal() {
		return Utility.returnValueForNull(isApprovedInternal);
	}

	public void setIsApprovedInternal(Boolean isApprovedInternal) {
		this.isApprovedInternal = Utility.returnValueForNull(isApprovedInternal);
	}
	
	public Boolean getIsApprovedExternal() {
		return Utility.returnValueForNull(isApprovedExternal);
	}

	public void setIsApprovedExternal(Boolean isApprovedExternal) {
			this.isApprovedExternal = Utility.returnValueForNull(isApprovedExternal);
	}

	public String getExternalRequestStatus() {
		return externalRequestStatus;
	}

	public void setExternalRequestStatus(String externalRequestStatus) {
		this.externalRequestStatus = externalRequestStatus;
	}

	public Double getLaborTotalCost() {
		return Utility.returnValueForNull(this.laborTotalCost);
	}

	public void setLaborTotalCost(Double laborTotalCost) {
		this.laborTotalCost = Utility.returnValueForNull(laborTotalCost);
	}

	public Double getMaterialTotalCost() {
		return Utility.returnValueForNull(materialTotalCost);
	}

	public void setMaterialTotalCost(Double materialTotalCost) {
		this.materialTotalCost = Utility.returnValueForNull(materialTotalCost);
	}

	public Double getOtherCost() {
		return Utility.returnValueForNull(otherCost);
	}

	public void setOtherCost(Double otherCost) {
		this.otherCost = Utility.returnValueForNull(otherCost);
	}

	public Integer getQuantity() {
		return Utility.returnValueForNull(quantity);
	}

	public void setQuantity(Integer quantity) {
		this.quantity = Utility.returnValueForNull(quantity);
	}

	public Double getScheduledValue() {
		return Utility.returnValueForNull(scheduledValue);
	}

	public void setScheduledValue(Double scheduledValue) {
		this.scheduledValue = Utility.returnValueForNull(scheduledValue);
	}

	public Double getScheduledValuePcent() {
		return Utility.returnValueForNull(scheduledValuePcent);
	}

	public void setScheduledValuePcent(Double scheduledValuePcent) {
		this.scheduledValuePcent = Utility.returnValueForNull(scheduledValuePcent);
	}

	public Double getTotalCost() {
		return Utility.returnValueForNull(totalCost);
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = Utility.returnValueForNull(totalCost);
	}

	public Integer getUnit() {
		return Utility.returnValueForNull(unit);
	}

	public void setUnit(Integer unit) {
		this.unit = Utility.returnValueForNull(unit);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getUnitPrice() {
		return Utility.returnValueForNull(unitPrice);
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = Utility.returnValueForNull(unitPrice);
	}

	public String getWorkDescription() {
		return this.workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Set<SovReviewsTable> getSovCustomerReviews() {
		return this.sovCustomerReviews;
	}

	public void setSovCustomerReviews(Set<SovReviewsTable> sovCustomerReviews) {
		this.sovCustomerReviews = sovCustomerReviews;
	}

	public SovDirectory getSovDirectory() {
		return this.sovDirectory;
	}

	public void setSovDirectory(SovDirectory sovDirectory) {
		this.sovDirectory = sovDirectory;
	}

	public Set<SovTableTracking> getSovTableTrackings() {
		return this.sovTableTrackings;
	}

	public void setSovTableTrackings(Set<SovTableTracking> sovTableTrackings) {
		this.sovTableTrackings = sovTableTrackings;
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