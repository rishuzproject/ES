package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.ecosystem.util.Utility;
import com.google.gson.annotations.Expose;

/**
 * The persistent class for the rfc_log database table.
 * 
 * @author Ashutosh
 */
@Entity
@Table(name = "RFC_LOG")
public class RfcLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "S_NO")
	@Expose
	private Integer sNo;

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
	@DateTimeFormat(pattern = "MM-dd-yyyy")
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
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
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
	private Date submittedDate;

	@Column(name = "UPDATED_BY", length = 40)
	@Expose
	private String updatedBy;

	// bi-directional many-to-one association to BudgetFormDje
	@OneToMany(mappedBy = "rfcLog")
	private Set<BudgetFormDje> budgetFormDjes;

	// bi-directional many-to-one association to BudgetFormEquip
	@OneToMany(mappedBy = "rfcLog")
	private Set<BudgetFormEquip> budgetFormEquips;

	// bi-directional many-to-one association to BudgetFormIndirect
	@OneToMany(mappedBy = "rfcLog")
	private Set<BudgetFormIndirect> budgetFormIndirects;

	// bi-directional many-to-one association to BudgetFormLabor
	@OneToMany(mappedBy = "rfcLog")
	private Set<BudgetFormLabor> budgetFormLabors;

	// bi-directional many-to-one association to BudgetFormMat
	@OneToMany(mappedBy = "rfcLog")
	private Set<BudgetFormMat> budgetFormMats;

	// bi-directional many-to-one association to BudgetFormProjectAdmin
	@OneToMany(mappedBy = "rfcLog")
	private Set<BudgetFormProjectAdmin> budgetFormProjectAdmins;

	// bi-directional many-to-one association to BudgetFormSubContractor
	@OneToMany(mappedBy = "rfcLog")
	private Set<BudgetFormSubContractor> budgetFormSubContractors;

	/*@OneToMany(mappedBy = "rfcLogSno")
	private Set<RfcTakeoffSheet> rfcTakeOffSheet;*/

	// Uni-directional many-to-one association to JobDetail
	@ManyToOne
	@JoinColumn(name = "JOB_ID")
	@Expose
	private JobDetail jobDetail;

	// bi-directional many-to-one association to RfcSubCostTypeBean
	@OneToMany(mappedBy = "rfcLogSno")
	private List<RfcSubCostTypeBean> rfcSubCostTypeBeans;

	/*// bi-directional many-to-one association to RfcLogTracking
	@OneToMany(mappedBy = "rfcLog")
	private Set<RfcLogTracking> rfcLogTrackings;*/

	@Column(name = "RFC_USER_RESPONSE_STATUS")
	@Expose
	private String rfcUserResponseStatus;

	@Column(name = "CUST_REF_NO_DESC")
	@Expose
	private String custRefNoDesc;
	
	@Column(name = "RFC_APPROVED_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Temporal(TemporalType.DATE)
	@Expose
	private Date approvedDate;

	@Column(name = "RFC_FILE_UPLOADED_LOCATION")
	private String rfcFileLocation;

	@Column(name = "RFC_FILE_UPLOADED_NAME")
	private String rfcFileName;

	@Transient
	private MultipartFile myFile;

	@Expose
	@Column(name = "COMPANY_RFC_NUMBER")
	private String companyRfcNo;

	@Expose
	@Column(name = "RFC_REFERENCE_NUMBER")
	private String rfcReferenceNo;

	/*public Set<RfcTakeoffSheet> getRfcTakeOffSheet() {
		return rfcTakeOffSheet;
	}

	public void setRfcTakeOffSheet(Set<RfcTakeoffSheet> rfcTakeOffSheet) {
		this.rfcTakeOffSheet = rfcTakeOffSheet;
	}*/
	
	/**
	 * @return the rfcUserResponseStatus
	 */
	public String getRfcUserResponseStatus() {
		return rfcUserResponseStatus;
	}

	/**
	 * @param rfcUserResponseStatus
	 *            the rfcUserResponseStatus to set
	 */
	public void setRfcUserResponseStatus(String rfcUserResponseStatus) {
		this.rfcUserResponseStatus = rfcUserResponseStatus;
	}

	/**
	 * @return the custRefNoDesc
	 */
	public String getCustRefNoDesc() {
		return custRefNoDesc;
	}

	/**
	 * @param custRefNoDesc
	 *            the custRefNoDesc to set
	 */
	public void setCustRefNoDesc(String custRefNoDesc) {
		this.custRefNoDesc = custRefNoDesc;
	}

	/**
	 * @return the approvedDate
	 */
	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * @param approvedDate
	 *            the approvedDate to set
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	/**
	 * @return the rfcFileLocation
	 */
	public String getRfcFileLocation() {
		return rfcFileLocation;
	}

	/**
	 * @param rfcFileLocation
	 *            the rfcFileLocation to set
	 */
	public void setRfcFileLocation(String rfcFileLocation) {
		this.rfcFileLocation = rfcFileLocation;
	}

	/**
	 * @return the rfcFileName
	 */
	public String getRfcFileName() {
		return rfcFileName;
	}

	/**
	 * @param rfcFileName
	 *            the rfcFileName to set
	 */
	public void setRfcFileName(String rfcFileName) {
		this.rfcFileName = rfcFileName;
	}

	/**
	 * @return the myFile
	 */
	public MultipartFile getMyFile() {
		return myFile;
	}

	/**
	 * @param myFile
	 *            the myFile to set
	 */
	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	}

	@Transient
	@Expose
	private String[] rfcMapping;

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

	public RfcLog() {
	}

	public Double getCalBasedOn() {
		calBasedOn = (Utility.checkNullForGetters(quoted) * Utility.checkNullForGetters(factor) / 100);
		return Utility.checkNullForGetters(calBasedOn);
	}

	public void setCalBasedOn(Double calBasedOn) {
		this.calBasedOn = calBasedOn;
	}

	public Double getTotalCost() {
		totalCost = (Utility.checkNullForGetters(material) * Utility.checkNullForGetters(materialFactor) / 100)
				+ (Utility.checkNullForGetters(subContract) * Utility.checkNullForGetters(subcontractorFactor) / 100) + Utility.checkNullForGetters(dirJobCost) + Utility.checkNullForGetters(equip)
				+ Utility.checkNullForGetters(ownedEquipment) + Utility.checkNullForGetters(projAdmin) + (Utility.checkNullForGetters(labr) * Utility.checkNullForGetters(laborFactor) / 100)
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
		return Utility.checkNullForGetters(grossMargin);
	}

	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}

	public String getRfcMappingDB() {
		return rfcMappingDB;
	}

	public void setRfcMappingDB(String rfcMappingDB) {
		this.rfcMappingDB = rfcMappingDB;
	}

	public String[] getRfcMapping() {
		return rfcMapping;
	}

	public void setRfcMapping(String[] rfcMapping) {
		this.rfcMapping = rfcMapping;
	}

	/**
	 * @return the sNo
	 */
	public Integer getsNo() {
		return sNo;
	}

	/**
	 * @param sNo
	 *            the sNo to set
	 */
	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}

	/**
	 * @return the approved
	 */
	public Double getApproved() {
		return Utility.checkNullForGetters(approved);
	}

	/**
	 * @param approved
	 *            the approved to set
	 */
	public void setApproved(Double approved) {
		this.approved = approved;
	}

	/**
	 * @return the contingency
	 */
	public Double getContingency() {
		return Utility.checkNullForGetters(contingency);
	}

	/**
	 * @param contingency
	 *            the contingency to set
	 */
	public void setContingency(Double contingency) {
		this.contingency = contingency;
	}

	/**
	 * @return the custRefNo
	 */
	public String getCustRefNo() {
		return custRefNo;
	}

	/**
	 * @param custRefNo
	 *            the custRefNo to set
	 */
	public void setCustRefNo(String custRefNo) {
		this.custRefNo = custRefNo;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the dirJobCost
	 */
	public Double getDirJobCost() {
		return Utility.checkNullForGetters(dirJobCost);
	}

	/**
	 * @param dirJobCost
	 *            the dirJobCost to set
	 */
	public void setDirJobCost(Double dirJobCost) {
		this.dirJobCost = dirJobCost;
	}

	/**
	 * @return the equip
	 */
	public Double getEquip() {
		return Utility.checkNullForGetters(equip);
	}

	/**
	 * @param equip
	 *            the equip to set
	 */
	public void setEquip(Double equip) {
		this.equip = equip;
	}

	/**
	 * @return the expApproval
	 */
	public Double getExpApproval() {
		return Utility.checkNullForGetters(expApproval);
	}

	/**
	 * @param expApproval
	 *            the expApproval to set
	 */
	public void setExpApproval(Double expApproval) {
		this.expApproval = expApproval;
	}

	/**
	 * @return the factor
	 */
	public Double getFactor() {
		return factor;
	}

	/**
	 * @param factor
	 *            the factor to set
	 */
	public void setFactor(Double factor) {
		this.factor = factor;
	}

	/**
	 * @return the indirCost
	 */
	public Double getIndirCost() {
		return Utility.checkNullForGetters(indirCost);
	}

	/**
	 * @param indirCost
	 *            the indirCost to set
	 */
	public void setIndirCost(Double indirCost) {
		this.indirCost = indirCost;
	}

	/**
	 * @return the laborFactor
	 */
	public Double getLaborFactor() {
		return laborFactor;
	}

	/**
	 * @param laborFactor
	 *            the laborFactor to set
	 */
	public void setLaborFactor(Double laborFactor) {
		this.laborFactor = laborFactor;
	}

	/**
	 * @return the labr
	 */
	public Double getLabr() {
		return Utility.checkNullForGetters(labr);
	}

	/**
	 * @param labr
	 *            the labr to set
	 */
	public void setLabr(Double labr) {
		this.labr = labr;
	}

	/**
	 * @return the labrHr
	 */
	public Double getLabrHr() {
		return Utility.checkNullForGetters(labrHr);
	}

	/**
	 * @param labrHr
	 *            the labrHr to set
	 */
	public void setLabrHr(Double labrHr) {
		this.labrHr = labrHr;
	}

	/**
	 * @return the lastStatUp
	 */
	public Date getLastStatUp() {
		return lastStatUp;
	}

	/**
	 * @param lastStatUp
	 *            the lastStatUp to set
	 */
	public void setLastStatUp(Date lastStatUp) {
		this.lastStatUp = lastStatUp;
	}

	/**
	 * @return the material
	 */
	public Double getMaterial() {
		return Utility.checkNullForGetters(material);
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(Double material) {
		this.material = material;
	}

	/**
	 * @return the materialFactor
	 */
	public Double getMaterialFactor() {
		return materialFactor;
	}

	/**
	 * @param materialFactor
	 *            the materialFactor to set
	 */
	public void setMaterialFactor(Double materialFactor) {
		this.materialFactor = materialFactor;
	}

	/**
	 * @return the origDate
	 */
	public Date getOrigDate() {
		return origDate;
	}

	/**
	 * @param origDate
	 *            the origDate to set
	 */
	public void setOrigDate(Date origDate) {
		this.origDate = origDate;
	}

	/**
	 * @return the originationReferenceNumber
	 */
	public String getOriginationReferenceNumber() {
		return originationReferenceNumber;
	}

	/**
	 * @param originationReferenceNumber
	 *            the originationReferenceNumber to set
	 */
	public void setOriginationReferenceNumber(String originationReferenceNumber) {
		this.originationReferenceNumber = originationReferenceNumber;
	}

	/**
	 * @return the ownedEquipment
	 */
	public Double getOwnedEquipment() {
		return Utility.checkNullForGetters(ownedEquipment);
	}

	/**
	 * @param ownedEquipment
	 *            the ownedEquipment to set
	 */
	public void setOwnedEquipment(Double ownedEquipment) {
		this.ownedEquipment = ownedEquipment;
	}

	/**
	 * @return the projAdmin
	 */
	public Double getProjAdmin() {
		return Utility.checkNullForGetters(projAdmin);
	}

	/**
	 * @param projAdmin
	 *            the projAdmin to set
	 */
	public void setProjAdmin(Double projAdmin) {
		this.projAdmin = projAdmin;
	}

	/**
	 * @return the quoted
	 */
	public Double getQuoted() {
		return Utility.checkNullForGetters(quoted);
	}

	/**
	 * @param quoted
	 *            the quoted to set
	 */
	public void setQuoted(Double quoted) {
		this.quoted = quoted;
	}

	/**
	 * @return the rfcDesc
	 */
	public String getRfcDesc() {
		return rfcDesc;
	}

	/**
	 * @param rfcDesc
	 *            the rfcDesc to set
	 */
	public void setRfcDesc(String rfcDesc) {
		this.rfcDesc = rfcDesc;
	}

	/**
	 * @return the rfcNotes
	 */
	public String getRfcNotes() {
		return rfcNotes;
	}

	/**
	 * @param rfcNotes
	 *            the rfcNotes to set
	 */
	public void setRfcNotes(String rfcNotes) {
		this.rfcNotes = rfcNotes;
	}

	/**
	 * @return the rfcStatus
	 */
	public String getRfcStatus() {
		return rfcStatus;
	}

	/**
	 * @param rfcStatus
	 *            the rfcStatus to set
	 */
	public void setRfcStatus(String rfcStatus) {
		this.rfcStatus = rfcStatus;
	}

	/**
	 * @return the rfcType
	 */
	public String getRfcType() {
		return rfcType;
	}

	/**
	 * @param rfcType
	 *            the rfcType to set
	 */
	public void setRfcType(String rfcType) {
		this.rfcType = rfcType;
	}

	/**
	 * @return the subContract
	 */
	public Double getSubContract() {
		return Utility.checkNullForGetters(subContract);
	}

	/**
	 * @param subContract
	 *            the subContract to set
	 */
	public void setSubContract(Double subContract) {
		this.subContract = subContract;
	}

	/**
	 * @return the subcontractorFactor
	 */
	public Double getSubcontractorFactor() {
		return subcontractorFactor;
	}

	/**
	 * @param subcontractorFactor
	 *            the subcontractorFactor to set
	 */
	public void setSubcontractorFactor(Double subcontractorFactor) {
		this.subcontractorFactor = subcontractorFactor;
	}

	/**
	 * @return the submittedBy
	 */
	public String getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @param submittedBy
	 *            the submittedBy to set
	 */
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param date
	 *            the submittedDate to set
	 */
	public void setSubmittedDate(Date date) {
		this.submittedDate = date;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the budgetFormDjes
	 */
	public Set<BudgetFormDje> getBudgetFormDjes() {
		return budgetFormDjes;
	}

	/**
	 * @param budgetFormDjes
	 *            the budgetFormDjes to set
	 */
	public void setBudgetFormDjes(Set<BudgetFormDje> budgetFormDjes) {
		this.budgetFormDjes = budgetFormDjes;
	}

	/**
	 * @return the budgetFormEquips
	 */
	public Set<BudgetFormEquip> getBudgetFormEquips() {
		return budgetFormEquips;
	}

	/**
	 * @param budgetFormEquips
	 *            the budgetFormEquips to set
	 */
	public void setBudgetFormEquips(Set<BudgetFormEquip> budgetFormEquips) {
		this.budgetFormEquips = budgetFormEquips;
	}

	/**
	 * @return the budgetFormIndirects
	 */
	public Set<BudgetFormIndirect> getBudgetFormIndirects() {
		return budgetFormIndirects;
	}

	/**
	 * @param budgetFormIndirects
	 *            the budgetFormIndirects to set
	 */
	public void setBudgetFormIndirects(Set<BudgetFormIndirect> budgetFormIndirects) {
		this.budgetFormIndirects = budgetFormIndirects;
	}

	/**
	 * @return the budgetFormLabors
	 */
	public Set<BudgetFormLabor> getBudgetFormLabors() {
		return budgetFormLabors;
	}

	/**
	 * @param budgetFormLabors
	 *            the budgetFormLabors to set
	 */
	public void setBudgetFormLabors(Set<BudgetFormLabor> budgetFormLabors) {
		this.budgetFormLabors = budgetFormLabors;
	}

	/**
	 * @return the budgetFormMats
	 */
	public Set<BudgetFormMat> getBudgetFormMats() {
		return budgetFormMats;
	}

	/**
	 * @param budgetFormMats
	 *            the budgetFormMats to set
	 */
	public void setBudgetFormMats(Set<BudgetFormMat> budgetFormMats) {
		this.budgetFormMats = budgetFormMats;
	}

	/**
	 * @return the budgetFormProjectAdmins
	 */
	public Set<BudgetFormProjectAdmin> getBudgetFormProjectAdmins() {
		return budgetFormProjectAdmins;
	}

	/**
	 * @param budgetFormProjectAdmins
	 *            the budgetFormProjectAdmins to set
	 */
	public void setBudgetFormProjectAdmins(Set<BudgetFormProjectAdmin> budgetFormProjectAdmins) {
		this.budgetFormProjectAdmins = budgetFormProjectAdmins;
	}

	/**
	 * @return the budgetFormSubContractors
	 */
	public Set<BudgetFormSubContractor> getBudgetFormSubContractors() {
		return budgetFormSubContractors;
	}

	/**
	 * @param budgetFormSubContractors
	 *            the budgetFormSubContractors to set
	 */
	public void setBudgetFormSubContractors(Set<BudgetFormSubContractor> budgetFormSubContractors) {
		this.budgetFormSubContractors = budgetFormSubContractors;
	}

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
	 * @return the rfcLogTrackings
	 *//*
	public Set<RfcLogTracking> getRfcLogTrackings() {
		return rfcLogTrackings;
	}*/

	public String getCompanyRfcNo() {
		return companyRfcNo;
	}

	public void setCompanyRfcNo(String companyRfcNo) {
		this.companyRfcNo = companyRfcNo;
	}

	public String getRfcReferenceNo() {
		return rfcReferenceNo;
	}

	public void setRfcReferenceNo(String rfcReferenceNo) {
		this.rfcReferenceNo = rfcReferenceNo;
	}

	/**
	 * @param rfcLogTrackings
	 *            the rfcLogTrackings to set
	 *//*
	public void setRfcLogTrackings(Set<RfcLogTracking> rfcLogTrackings) {
		this.rfcLogTrackings = rfcLogTrackings;
	}*/

	public List<RfcSubCostTypeBean> getRfcSubCostTypeBeans() {
		return rfcSubCostTypeBeans;
	}

	public void setRfcSubCostTypeBeans(List<RfcSubCostTypeBean> rfcSubCostTypeBeans) {
		this.rfcSubCostTypeBeans = rfcSubCostTypeBeans;
	}
}