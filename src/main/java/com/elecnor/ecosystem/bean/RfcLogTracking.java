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
import javax.persistence.Transient;

import com.elecnor.ecosystem.util.Utility;
import com.google.gson.annotations.Expose;

/**
 * The persistent class for the rfc_log_tracking database table.
 * 
 * @author Ashutosh
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */
@Entity
@Table(name = "RFC_LOG_TRACKING")
public class RfcLogTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "S_NO_TRACKING")
	@Expose
	private Integer sNoTracking;

	@Column(name = "APPROVED")
	@Expose
	private Double approved;

	@Column(name = "CONTINGENCY")
	@Expose
	private Double contingency;

	@Column(name = "CUST_REF_NO", length = 50)
	@Expose
	private String custRefNo;

	@Column(name = "DELETED")
	@Expose
	private Boolean deleted;

	@Column(name = "DIR_JOB_COST")
	@Expose
	private Double dirJobCost;

	@Column(name = "EQUIP")
	@Expose
	private Double equip;

	@Column(name = "EXP_APPROVAL")
	@Expose
	private Double expApproval;

	@Column(name = "FACTOR")
	@Expose
	private Double factor;

	@Column(name = "INDIR_COST")
	@Expose
	private Double indirCost;

	@Column(name = "LABOR_FACTOR")
	@Expose
	private Double laborFactor;

	@Column(name = "LABR")
	@Expose
	private Double labr;

	@Column(name = "LABR_HR")
	@Expose
	private Double labrHr;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_STAT_UP")
	@Expose
	private Date lastStatUp;

	@Column(name = "MATERIAL")
	@Expose
	private Double material;

	@Column(name = "MATERIAL_FACTOR")
	@Expose
	private Double materialFactor;

	@Temporal(TemporalType.DATE)
	@Column(name = "ORIG_DATE")
	@Expose
	private Date origDate;

	@Column(name = "ORIGINATION_REFERENCE_NUMBER", length = 100)
	@Expose
	private String originationReferenceNumber;

	@Column(name = "OWNED_EQUIPMENT")
	@Expose
	private Double ownedEquipment;

	@Column(name = "PROJ_ADMIN")
	@Expose
	private Double projAdmin;

	@Column(name = "PROJECT_ID")
	@Expose
	private Integer projectId;

	@Column(name = "QUOTED")
	@Expose
	private Double quoted;

	@Column(name = "RFC_DESC", length = 200)
	@Expose
	private String rfcDesc;

	@Column(name = "RFC_MAPPING", length = 100)
	@Expose
	private String rfcMappingDB;

	@Column(name = "RFC_NOTES", length = 500)
	@Expose
	private String rfcNotes;

	@Column(name = "RFC_STATUS", length = 20)
	@Expose
	private String rfcStatus;

	@Column(name = "RFC_TYPE", length = 10)
	@Expose
	private String rfcType;

	@Column(name = "SUB_CONTRACT")
	@Expose
	private Double subContract;

	@Column(name = "SUBCONTRACTOR_FACTOR")
	@Expose
	private Double subcontractorFactor;

	@Column(name = "SUBMITTED_BY", length = 40)
	@Expose
	private String submittedBy;

	@Column(name = "SUBMITTED_DATE", nullable = false)
	@Expose
	private Date submittedDate;

	@Column(name = "UPDATED_BY", length = 40)
	@Expose
	private String updatedBy;

	// Uni-directional many-to-one association to RfcLog
	@ManyToOne
	@JoinColumn(name = "S_NO")
	private RfcLog rfcLog;

	@Transient
	@Expose
	private Double calBasedOn;
	
	@Transient
	@Expose
	private Double totalCost;
	
	@Transient
	@Expose
	private Double grossProfit;
	
	@Transient
	@Expose
	private Double grossMargin;

	public Double getCalBasedOn() {
		calBasedOn = (Utility.checkNullForGetters(quoted) * Utility.checkNullForGetters(factor) / 100);
		return Utility.checkNullForGetters(calBasedOn);
	}

	public void setCalBasedOn(Double calBasedOn) {
		this.calBasedOn = calBasedOn;
	}

	public Double getTotalCost() {
		totalCost = (Utility.checkNullForGetters(material) * Utility.checkNullForGetters(materialFactor) / 100)
				+ (Utility.checkNullForGetters(subContract) * Utility.checkNullForGetters(subcontractorFactor) / 100)
				+ Utility.checkNullForGetters(dirJobCost) + Utility.checkNullForGetters(equip) + Utility.checkNullForGetters(ownedEquipment)
				+ Utility.checkNullForGetters(projAdmin) + (Utility.checkNullForGetters(labr) * Utility.checkNullForGetters(laborFactor) / 100)
				+ Utility.checkNullForGetters(indirCost) + Utility.checkNullForGetters(contingency);
		return Utility.checkNullForGetters(totalCost);
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getGrossProfit() {
		grossProfit = Utility.checkNullForGetters(calBasedOn) - Utility.checkNullForGetters(totalCost);
		return Utility.checkNullForGetters(grossProfit);
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	public Double getGrossMargin() {
		if (Utility.checkNullForGetters(calBasedOn) != 0) {
			grossMargin = (Utility.checkNullForGetters(grossProfit) / Utility.checkNullForGetters(calBasedOn) * 100);
		} else {
			grossMargin = 0.0;
		}
		System.out.println("gross margin"+grossMargin);
		return Utility.checkNullForGetters(grossMargin);
	}

	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}
	public RfcLogTracking() {
	}

	public Double getApproved() {
		return this.approved;
	}

	public void setApproved(Double approved) {
		this.approved = approved;
	}

	public Double getContingency() {
		return this.contingency;
	}

	public void setContingency(Double contingency) {
		this.contingency = contingency;
	}

	public String getCustRefNo() {
		return this.custRefNo;
	}

	public void setCustRefNo(String custRefNo) {
		this.custRefNo = custRefNo;
	}

	public Double getDirJobCost() {
		return this.dirJobCost;
	}

	/**
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setDirJobCost(Double dirJobCost) {
		this.dirJobCost = dirJobCost;
	}

	public Double getEquip() {
		return this.equip;
	}

	public void setEquip(Double equip) {
		this.equip = equip;
	}

	public Double getExpApproval() {
		return this.expApproval;
	}

	public void setExpApproval(Double expApproval) {
		this.expApproval = expApproval;
	}

	public Double getFactor() {
		return this.factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Double getIndirCost() {
		return this.indirCost;
	}

	public void setIndirCost(Double indirCost) {
		this.indirCost = indirCost;
	}

	public Double getLaborFactor() {
		return this.laborFactor;
	}

	public void setLaborFactor(Double laborFactor) {
		this.laborFactor = laborFactor;
	}

	public Double getLabr() {
		return this.labr;
	}

	public void setLabr(Double labr) {
		this.labr = labr;
	}

	public Double getLabrHr() {
		return this.labrHr;
	}

	public void setLabrHr(Double labrHr) {
		this.labrHr = labrHr;
	}

	public Date getLastStatUp() {
		return this.lastStatUp;
	}

	public void setLastStatUp(Date lastStatUp) {
		this.lastStatUp = lastStatUp;
	}

	public Double getMaterial() {
		return this.material;
	}

	public void setMaterial(Double material) {
		this.material = material;
	}

	public Double getMaterialFactor() {
		return this.materialFactor;
	}

	public void setMaterialFactor(Double materialFactor) {
		this.materialFactor = materialFactor;
	}

	public Date getOrigDate() {
		return this.origDate;
	}

	public void setOrigDate(Date origDate) {
		this.origDate = origDate;
	}

	public String getOriginationReferenceNumber() {
		return this.originationReferenceNumber;
	}

	public void setOriginationReferenceNumber(String originationReferenceNumber) {
		this.originationReferenceNumber = originationReferenceNumber;
	}

	public Double getOwnedEquipment() {
		return this.ownedEquipment;
	}

	public void setOwnedEquipment(Double ownedEquipment) {
		this.ownedEquipment = ownedEquipment;
	}

	public Double getProjAdmin() {
		return this.projAdmin;
	}

	public void setProjAdmin(Double projAdmin) {
		this.projAdmin = projAdmin;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Double getQuoted() {
		return this.quoted;
	}

	public void setQuoted(Double quoted) {
		this.quoted = quoted;
	}

	public String getRfcDesc() {
		return this.rfcDesc;
	}

	public void setRfcDesc(String rfcDesc) {
		this.rfcDesc = rfcDesc;
	}

	/**
	 * @return the sNoTracking
	 */
	public Integer getsNoTracking() {
		return sNoTracking;
	}

	/**
	 * @param sNoTracking the sNoTracking to set
	 */
	public void setsNoTracking(Integer sNoTracking) {
		this.sNoTracking = sNoTracking;
	}

	/**
	 * @return the rfcMappingDB
	 */
	public String getRfcMappingDB() {
		return rfcMappingDB;
	}

	/**
	 * @param rfcMappingDB the rfcMappingDB to set
	 */
	public void setRfcMappingDB(String rfcMappingDB) {
		this.rfcMappingDB = rfcMappingDB;
	}

	public String getRfcNotes() {
		return this.rfcNotes;
	}

	public void setRfcNotes(String rfcNotes) {
		this.rfcNotes = rfcNotes;
	}

	public String getRfcStatus() {
		return this.rfcStatus;
	}

	public void setRfcStatus(String rfcStatus) {
		this.rfcStatus = rfcStatus;
	}

	public String getRfcType() {
		return this.rfcType;
	}

	public void setRfcType(String rfcType) {
		this.rfcType = rfcType;
	}

	public Double getSubContract() {
		return this.subContract;
	}

	public void setSubContract(Double subContract) {
		this.subContract = subContract;
	}

	public Double getSubcontractorFactor() {
		return this.subcontractorFactor;
	}

	public void setSubcontractorFactor(Double subcontractorFactor) {
		this.subcontractorFactor = subcontractorFactor;
	}

	public String getSubmittedBy() {
		return this.submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public Date getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public RfcLog getRfcLog() {
		return this.rfcLog;
	}

	public void setRfcLog(RfcLog rfcLog) {
		this.rfcLog = rfcLog;
	}

}