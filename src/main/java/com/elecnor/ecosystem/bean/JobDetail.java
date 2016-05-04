package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the job_details database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "JOB_DETAILS")
public class JobDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JOB_ID")
	@Expose
	private Long jobId;

	@Column(name="SOV_TYPE")
	@Expose
	private String sovType;
	
	@Column(name="ACTIVATION_VALIDITY_TIMEPERIOD")
	@Expose
	private Integer activationValidityTimePeriod;

	@Column(name = "AUTO_APPROVAL_LIMIT")
	@Expose
	private Double autoApprovalLimit;

	@Column(name = "BID_PRICE")
	@Expose
	private Double bidPrice;

	@Temporal(TemporalType.DATE)
	@Column(name = "CO_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date coDate;

	@Column(name = "COLLECTION_DAY_OUT")
	@Expose
	private Integer collectionDayOut;

	@Column(name = "DESCRIPTION")
	@Expose
	private String description;

	@Column(name = "DIRECT_JOB_DAY_OUT")
	@Expose
	private Integer directJobDayOut;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "EXTENDED_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date extendedDate;

	@Column(name = "INDIRECT_DAY_OUT")
	@Expose
	private Integer indirectDayOut;

	@Column(name = "JOB_NAME")
	@Expose
	private String jobName;

	@Column(name = "JOB_NUMBER")
	@Expose
	private String jobNumber;

//	@Column(name = "JOB_ADDRESS")
	@Transient
	@Expose
	private ArrayList<AddressDetails> jobAddress;
	
	@Column(name = "BID_NUMBER")
	@Expose
	private String bidNumber;

	@Column(name = "LABOR_BONUS_TARGET_OVER_BUDGET")
	@Expose
	private Float laborBonusTargetOverBudget;

	@Column(name = "LABOR_DAY_OUT")
	@Expose
	private Integer laborDayOut;

	@Column(name = "MATERIAL_DAY_OUT")
	@Expose
	private Integer materialDayOut;

	@Column(name = "ORIGINAL_CONTRACT_VALUE")
	@Expose
	private Double originalContractValue;

	@Column(name = "OWNED_EQUIPMENT_DAY_OUT")
	@Expose
	private Integer ownedEquipmentDayOut;

	@Column(name = "PERFORMANCE_TARGET_MARGIN")
	@Expose
	private Float performanceTargetMargin;

	@Column(name = "PROJ_ADMIN_DAY_OUT")
	@Expose
	private Integer projAdminDayOut;

	@Column(name = "RENTAL_EQUIPMENT_DAY_OUT")
	@Expose
	private Integer rentalEquipmentDayOut;

	@Column(name = "REPORT_MARGIN")
	@Expose
	private Float reportMargin;

	@Column(name = "RETENTION_DAY_OUT")
	@Expose
	private Integer retentionDayOut;

	@Column(name = "RETENTION_PERCENT")
	@Expose
	private Float retentionPercent;

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date startDate;

	@Column(name = "STATUS")
	@Expose
	private String status;

	@Column(name = "SUBCONTRACTOR_DAY_OUT")
	@Expose
	private Integer subcontractorDayOut;
	
	
//	@ManyToOne
//	@JoinColumn(name = "JOB_UPLOAD_DOC_ID")
//	private JobUploadDocument jobUploadDocID;
	
	
	// Adding this field as it is aprt of settings in MPR
	@Column(name = "UPDATED_BILLING_SCHEDULE_DAY_OUT")
	@Expose
	private Integer updatedBillingScheduleDayOut;
	
	/**
	 * @return the updatedBillingScheduleDayOut
	 */
	public Integer getUpdatedBillingScheduleDayOut() {
		return updatedBillingScheduleDayOut;
	}

	/**
	 * @param updatedBillingScheduleDayOut the updatedBillingScheduleDayOut to set
	 */
	public void setUpdatedBillingScheduleDayOut(Integer updatedBillingScheduleDayOut) {
		this.updatedBillingScheduleDayOut = updatedBillingScheduleDayOut;
	}

	/**
	 * @return the architectId
	 */
	public Long getArchitectId() {
		return architectId;
	}

	/**
	 * @param architectId the architectId to set
	 */
	public void setArchitectId(Long architectId) {
		this.architectId = architectId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// bi-directional many-to-one association to CustomerDirectory
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	@Expose
	private CustomerDirectory customerDirectory;

	@ManyToOne
	@JoinColumn(name = "CONTRACTOR_ID")
	@Expose
	private ContractorDirectory contractorDirectory;
	
	
	@Expose
	@Column(name="ARCHITECT_ID")
	private Long architectId;

	public Long getArchitectNumber() {
		return architectId;
	}

	public void setArchitectNumber(Long architectId) {
		this.architectId = architectId;
	}

	public ContractorDirectory getContractorDirectory() {
		return contractorDirectory;
	}

	public void setContractorDirectory(ContractorDirectory contractorDirectory) {
		this.contractorDirectory = contractorDirectory;
	}

	// bi-directional many-to-one association to DepartmentType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID")
	@Expose
	private DepartmentDirectory departmentType;

	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	//@Expose
	private DomainDetail domainDetail;
	
	@ManyToOne
	@JoinColumn(name = "VENDOR_ID")
	@Expose
	private VendorDirectory vendorList;

	public VendorDirectory getVendorList() {
		return vendorList;
	}

	public void setVendorList(VendorDirectory vendorList) {
		this.vendorList = vendorList;
	}

	// bi-directional many-to-one association to ProjectType
	@ManyToOne
	@JoinColumn(name = "PROJECT_TYPE")
	@Expose
	private ProjectType projectTypeBean;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UPDATED_BY")
	//@Expose
	private UserDetail updatedBy;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "FOREMAN")
	@Expose
	private UserDetail foreman;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "MANAGER")
	@Expose
	private UserDetail manager;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUPERVISOR")
	@Expose
	private UserDetail supervisor;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "EXECUTIVE")
	@Expose
	private UserDetail executive;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "PURCHASING_AGENT")
	@Expose
	private UserDetail purchasingAgent;
	
	//new fields added for redesigning
	
	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "ACCOUNTANT")
	@Expose
	private UserDetail accountant;
	
	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "WAREHOUSE_MANAGER")
	@Expose
	private UserDetail warehouseManager;
	
		// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUPERINDENT")	
	@Expose
	private UserDetail superindent;
	
	@Column(name="ESTIMATOR")
	@Expose
	private String estimator;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INSURANCE_SENT_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date insuranceSentDate;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PERFORMANCE_SENT_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date performanceSentDate;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CPN_SENT_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date cpnSentDate;
	
	@Column(name="IS_CERTIFIED_PAYROLL")
	@Expose
	private Boolean isCertifiedPayroll;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "NOTICE_RECEIVED_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Expose
	private Date noticeReceivedDate;
	
	
	@Column(name="OWNER_CONTROLLED_INSURANCE_PROG")
	@Expose
	private Boolean ownerControlledInsuranceProg;
	
	@Column(name="TYPE_OF_CONTRACT")
	@Expose
	private String typeOfContract;
	
	@Column(name = "OTHER_TYPE_OF_CONTRACT")
	@Expose
	private String otherTypeOfContract;
	
	@Column(name="CONTRACT_AMOUNT")
	@Expose
	private Float contractAmount;
	
	@Column(name="CONTRACT_NUMBER")
	@Expose
	private Long contractNumber;
	
	@Column(name="BID_BUGET_MATERIAL_COST")
	@Expose
	private Float bidBudgetMaterialCost;
	
	@Column(name="BID_BUDGET_SUBCONTRACTORS_COST")
	@Expose
	private Float  bidBudgetSubcontractorsCost;
	
	@Column(name="BID_BUDGET_DIRECT_JOB_COSTS")
	@Expose
	private Float  bidBudgetDirectJobCosts;
	
	@Column(name="BID_BUDGDET_RENTAL_EQUIPMENT_COSTS")
	@Expose
	private Float  bidBudgetRentalEquipmentCosts;
	
	@Column(name="BID_BUDGET_OWNED_EQUIPMENTS_COSTS")
	@Expose
	private Float bidBudgetOwnedEquipmentsCosts;
	
	@Column(name="BID_BUDGET_PROJECT_ADMINISTRATION_COSTS")
	@Expose
	private Float  bidBudgetProjectAdministrationCost;
	
	@Column(name="BID_BUDGET_LABOR_COST")
	@Expose
	private Float  bidBudgetLaborCost;
	
	@Column(name="BID_BUDGET_INDIRECT_EXPENSES")
	@Expose
	private Float  bidBudgetIndirectExpenses;
	
	
	@Column(name="OPERATIONS_BUDGET_MATERIAL_COSTS")
	@Expose
	private Float  operationsBudgetMaterialCosts;
	
	@Column(name="OPERATIONS_COST_SUBCONTRACTORS_COST")
	@Expose
	private Float  operationsSubcontractorsCosts;
	
	
	@Column(name="OPERATIONS_BUDGET_DIRECT_JOB_COST")
	@Expose
	private Float  operationsBudgetDirectJobCost;
	
	@Column(name="OPERATIONS_RENTAL_EQUIPMENT_COST")
	@Expose
	private Float  operationsBudgetRentalEquipmentCost;
	
	@Column(name="OPERATIONS_OWNED_EQUIPMENT_COST")
	@Expose
	private Float  operationsOwnedEquipmentCost;
	
	@Column(name="OPERATIONS_PROJECT_ADMINISTRATION_COST")
	@Expose
	private Float  operationsProjectAdministrationCost;
	
	@Column(name="OPERATIONS_INDIRECT_EXPENSES")
	@Expose
	private Float  operationsIndirectExpenses;
	
	@Column(name="OPERATIONS_LABOR_COST")
	@Expose
	private Float  operationsBudgetLaborCost;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUBMITTED_BY")
	//@Expose
	private UserDetail submittedBy;

	/*// bi-directional many-to-one association to RfcLog
	@OneToMany(mappedBy = "jobDetail")
	private Set<RfcLog> rfcLogs;*/

	// bi-directional many-to-one association to RfcLog
	/*@OneToMany(mappedBy = "jobDetail")
	private Set<SovDirectory> sovDirectory;*/

	@Transient
	@Expose
	private String submittedByName;

	@Transient
	@Expose
	private String updatedByName;

	@Transient
	@Expose
	private String departmentname;

	public JobDetail() {
	}

	public Double getAutoApprovalLimit() {
		return this.autoApprovalLimit;
	}

	public void setAutoApprovalLimit(Double autoApprovalLimit) {
		this.autoApprovalLimit = autoApprovalLimit;
	}

	public Double getBidPrice() {
		return this.bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Date getCoDate() {
		return this.coDate;
	}

	public void setCoDate(Date coDate) {
		this.coDate = coDate;
	}

	public Integer getCollectionDayOut() {
		return this.collectionDayOut;
	}

	public void setCollectionDayOut(Integer collectionDayOut) {
		this.collectionDayOut = collectionDayOut;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDirectJobDayOut() {
		return this.directJobDayOut;
	}

	public void setDirectJobDayOut(Integer directJobDayOut) {
		this.directJobDayOut = directJobDayOut;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getExtendedDate() {
		return this.extendedDate;
	}

	public void setExtendedDate(Date extendedDate) {
		this.extendedDate = extendedDate;
	}

	public Integer getIndirectDayOut() {
		return this.indirectDayOut;
	}

	public void setIndirectDayOut(Integer indirectDayOut) {
		this.indirectDayOut = indirectDayOut;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobNumber() {
		return this.jobNumber;
	}

	public ArrayList<AddressDetails> getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(ArrayList<AddressDetails>	jobAddress) {
		this.jobAddress = jobAddress;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Float getLaborBonusTargetOverBudget() {
		return this.laborBonusTargetOverBudget;
	}

	public void setLaborBonusTargetOverBudget(Float laborBonusTargetOverBudget) {
		this.laborBonusTargetOverBudget = laborBonusTargetOverBudget;
	}

	public Integer getLaborDayOut() {
		return this.laborDayOut;
	}

	public void setLaborDayOut(Integer laborDayOut) {
		this.laborDayOut = laborDayOut;
	}

	public Integer getMaterialDayOut() {
		return this.materialDayOut;
	}

	public void setMaterialDayOut(Integer materialDayOut) {
		this.materialDayOut = materialDayOut;
	}

	public Double getOriginalContractValue() {
		return this.originalContractValue;
	}

	public void setOriginalContractValue(Double originalContractValue) {
		this.originalContractValue = originalContractValue;
	}

	public Integer getOwnedEquipmentDayOut() {
		return this.ownedEquipmentDayOut;
	}

	public void setOwnedEquipmentDayOut(Integer ownedEquipmentDayOut) {
		this.ownedEquipmentDayOut = ownedEquipmentDayOut;
	}

	public Float getPerformanceTargetMargin() {
		return this.performanceTargetMargin;
	}

	public void setPerformanceTargetMargin(Float performanceTargetMargin) {
		this.performanceTargetMargin = performanceTargetMargin;
	}

	public Integer getProjAdminDayOut() {
		return this.projAdminDayOut;
	}

	public void setProjAdminDayOut(Integer projAdminDayOut) {
		this.projAdminDayOut = projAdminDayOut;
	}

	public Integer getRentalEquipmentDayOut() {
		return this.rentalEquipmentDayOut;
	}

	public void setRentalEquipmentDayOut(Integer rentalEquipmentDayOut) {
		this.rentalEquipmentDayOut = rentalEquipmentDayOut;
	}

	public Float getReportMargin() {
		return this.reportMargin;
	}

	public void setReportMargin(Float reportMargin) {
		this.reportMargin = reportMargin;
	}

	public Integer getRetentionDayOut() {
		return this.retentionDayOut;
	}

	public void setRetentionDayOut(Integer retentionDayOut) {
		this.retentionDayOut = retentionDayOut;
	}

	public Float getRetentionPercent() {
		return this.retentionPercent;
	}

	public void setRetentionPercent(Float retentionPercent) {
		this.retentionPercent = retentionPercent;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSubcontractorDayOut() {
		return this.subcontractorDayOut;
	}

	public void setSubcontractorDayOut(Integer subcontractorDayOut) {
		this.subcontractorDayOut = subcontractorDayOut;
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

	public CustomerDirectory getCustomerDirectory() {
		return this.customerDirectory;
	}

	public void setCustomerDirectory(CustomerDirectory customerDirectory) {
		this.customerDirectory = customerDirectory;
	}

	public DepartmentDirectory getDepartmentType() {
		return this.departmentType;
	}

	public void setDepartmentType(DepartmentDirectory departmentType) {
		this.departmentType = departmentType;
	}

	public DomainDetail getDomainDetail() {
		return this.domainDetail;
	}

	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}

	public ProjectType getProjectTypeBean() {
		return this.projectTypeBean;
	}

	public void setProjectTypeBean(ProjectType projectTypeBean) {
		this.projectTypeBean = projectTypeBean;
	}

	/**
	 * @return the jobId
	 */
	public Long getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the updatedBy
	 */
	public UserDetail getUpdatedBy() {
		getUpdatedByName();
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
		getUpdatedByName();
	}

	/**
	 * @return the foreman
	 */
	public UserDetail getForeman() {
		return foreman;
	}

	/**
	 * @param foreman
	 *            the foreman to set
	 */
	public void setForeman(UserDetail foreman) {
		this.foreman = foreman;
	}

	/**
	 * @return the manager
	 */
	public UserDetail getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(UserDetail manager) {
		this.manager = manager;
	}

	/**
	 * @return the supervisor
	 */
	public UserDetail getSupervisor() {
		return supervisor;
	}

	/**
	 * @param supervisor
	 *            the supervisor to set
	 */
	public void setSupervisor(UserDetail supervisor) {
		this.supervisor = supervisor;
	}

	public UserDetail getExecutive() {
		return executive;
	}

	public void setExecutive(UserDetail executive) {
		this.executive = executive;
	}

	public UserDetail getPurchasingAgent() {
		return purchasingAgent;
	}

	public void setPurchasingAgent(UserDetail purchasingAgent) {
		this.purchasingAgent = purchasingAgent;
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

	/*public Set<RfcLog> getRfcLogs() {
		return rfcLogs;
	}

	public void setRfcLogs(Set<RfcLog> rfcLogs) {
		this.rfcLogs = rfcLogs;
	}
*/
	public String getSubmittedByName() {

		if (this.submittedBy != null) {
			this.submittedByName = this.submittedBy.getFirstName();
			return submittedByName;
		} else {
			return submittedByName;
		}
	}

	public void setSubmittedByName(String submittedByName) {
		if (this.submittedBy != null) {
			this.submittedByName = this.submittedBy.getFirstName();
		} else {
			this.submittedByName = submittedByName;
		}

	}

	public String getUpdatedByName() {
		this.updatedByName = "";
		if (this.updatedBy != null) {
			this.updatedByName = this.updatedBy.getFirstName();
			return updatedByName;
		} else {
			return updatedByName;
		}
	}

	public void setUpdatedByName(String updatedByName) {
		if (this.updatedBy != null) {
			this.updatedByName = this.updatedBy.getFirstName();
		} else {
			this.updatedByName = updatedByName;
		}

	}

	public String getDepartmentname() {
		this.departmentname = "";
		if (this.departmentType != null) {
			this.departmentname = this.departmentType.getDepartmentName();
			return departmentname;
		} else {
			return departmentname;
		}
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	@Override
	public String toString() {
		return "Job Name : " + jobName + "Job Number : " + jobNumber + "Id : " + jobId + "Start Da : " + startDate + "End Da : " + endDate
				+ "Status : " + status;
	}

	/**
	 * @return the sovDirectory
	 */
	/*public Set<SovDirectory> getSovDirectory() {
		return sovDirectory;
	}

	*//**
	 * @param sovDirectory the sovDirectory to set
	 *//*
	public void setSovDirectory(Set<SovDirectory> sovDirectory) {
		this.sovDirectory = sovDirectory;
	}*/

	public String getSovType() {
		return sovType;
	}

	public void setSovType(String sovType) {
		this.sovType = sovType;
	}
	
	public Integer getActivationValidityTimePeriod() {
		return activationValidityTimePeriod;
	}

	public void setActivationValidityTimePeriod(Integer activationValidityTimePeriod) {
		this.activationValidityTimePeriod = activationValidityTimePeriod;
	}

	public String getBidNumber() {
		return bidNumber;
	}

	public void setBidNumber(String bidNumber) {
		this.bidNumber = bidNumber;
	}

	public UserDetail getAccountant() {
		return accountant;
	}

	public void setAccountant(UserDetail accountant) {
		this.accountant = accountant;
	}

	public UserDetail getWarehouseManager() {
		return warehouseManager;
	}

	public void setWarehouseManager(UserDetail warehouseManager) {
		this.warehouseManager = warehouseManager;
	}

	public UserDetail getSuperindent() {
		return superindent;
	}

	public void setSuperindent(UserDetail superindent) {
		this.superindent = superindent;
	}

	public String getEstimator() {
		return estimator;
	}

	public void setEstimator(String estimator) {
		this.estimator = estimator;
	}

	public Date getInsuranceSentDate() {
		return insuranceSentDate;
	}

	public void setInsuranceSentDate(Date insuranceSentDate) {
		this.insuranceSentDate = insuranceSentDate;
	}

	public Date getPerformanceSentDate() {
		return performanceSentDate;
	}

	public void setPerformanceSentDate(Date performanceSentDate) {
		this.performanceSentDate = performanceSentDate;
	}

	public Date getCpnSentDate() {
		return cpnSentDate;
	}

	public void setCpnSentDate(Date cpnSentDate) {
		this.cpnSentDate = cpnSentDate;
	}

	public Boolean getIsCertifiedPayroll() {
		return isCertifiedPayroll;
	}

	public void setIsCertifiedPayroll(Boolean isCertifiedPayroll) {
		this.isCertifiedPayroll = isCertifiedPayroll;
	}

	public Date getNoticeReceivedDate() {
		return noticeReceivedDate;
	}

	public void setNoticeReceivedDate(Date noticeReceivedDate) {
		this.noticeReceivedDate = noticeReceivedDate;
	}

	public Boolean getOwnerControlledInsuranceProg() {
		return ownerControlledInsuranceProg;
	}

	public void setOwnerControlledInsuranceProg(Boolean ownerControlledInsuranceProg) {
		this.ownerControlledInsuranceProg = ownerControlledInsuranceProg;
	}

	public String getTypeOfContract() {
		return typeOfContract;
	}

	public void setTypeOfContract(String typeOfContract) {
		this.typeOfContract = typeOfContract;
	}

	public String getOtherTypeOfContract() {
		return otherTypeOfContract;
	}

	public void setOtherTypeOfContract(String otherTypeOfContract) {
		this.otherTypeOfContract = otherTypeOfContract;
	}

	public Float getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Float contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Long getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(Long contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Float getBidBudgetMaterialCost() {
		return bidBudgetMaterialCost;
	}

	public void setBidBudgetMaterialCost(Float bidBudgetMaterialCost) {
		this.bidBudgetMaterialCost = bidBudgetMaterialCost;
	}

	public Float getBidBudgetSubcontractorsCost() {
		return bidBudgetSubcontractorsCost;
	}

	public void setBidBudgetSubcontractorsCost(Float bidBudgetSubcontractorsCost) {
		this.bidBudgetSubcontractorsCost = bidBudgetSubcontractorsCost;
	}

	public Float getBidBudgetDirectJobCosts() {
		return bidBudgetDirectJobCosts;
	}

	public void setBidBudgetDirectJobCosts(Float bidBudgetDirectJobCosts) {
		this.bidBudgetDirectJobCosts = bidBudgetDirectJobCosts;
	}

	public Float getBidBudgetRentalEquipmentCosts() {
		return bidBudgetRentalEquipmentCosts;
	}

	public void setBidBudgetRentalEquipmentCosts(Float bidBudgetRentalEquipmentCosts) {
		this.bidBudgetRentalEquipmentCosts = bidBudgetRentalEquipmentCosts;
	}

	public Float getBidBudgetOwnedEquipmentsCosts() {
		return bidBudgetOwnedEquipmentsCosts;
	}

	public void setBidBudgetOwnedEquipmentsCosts(Float bidBudgetOwnedEquipmentsCosts) {
		this.bidBudgetOwnedEquipmentsCosts = bidBudgetOwnedEquipmentsCosts;
	}

	public Float getBidBudgetProjectAdministrationCost() {
		return bidBudgetProjectAdministrationCost;
	}

	public void setBidBudgetProjectAdministrationCost(
			Float bidBudgetProjectAdministrationCost) {
		this.bidBudgetProjectAdministrationCost = bidBudgetProjectAdministrationCost;
	}

	public Float getBidBudgetLaborCost() {
		return bidBudgetLaborCost;
	}

	public void setBidBudgetLaborCost(Float bidBudgetLaborCost) {
		this.bidBudgetLaborCost = bidBudgetLaborCost;
	}

	public Float getBidBudgetIndirectExpenses() {
		return bidBudgetIndirectExpenses;
	}

	public void setBidBudgetIndirectExpenses(Float bidBudgetIndirectExpenses) {
		this.bidBudgetIndirectExpenses = bidBudgetIndirectExpenses;
	}

	public Float getOperationsBudgetMaterialCosts() {
		return operationsBudgetMaterialCosts;
	}

	public void setOperationsBudgetMaterialCosts(Float operationsBudgetMaterialCosts) {
		this.operationsBudgetMaterialCosts = operationsBudgetMaterialCosts;
	}

	public Float getOperationsSubcontractorsCosts() {
		return operationsSubcontractorsCosts;
	}

	public void setOperationsSubcontractorsCosts(Float operationsSubcontractorsCosts) {
		this.operationsSubcontractorsCosts = operationsSubcontractorsCosts;
	}

	public Float getOperationsBudgetDirectJobCost() {
		return operationsBudgetDirectJobCost;
	}

	public void setOperationsBudgetDirectJobCost(Float operationsBudgetDirectJobCost) {
		this.operationsBudgetDirectJobCost = operationsBudgetDirectJobCost;
	}

	public Float getOperationsBudgetRentalEquipmentCost() {
		return operationsBudgetRentalEquipmentCost;
	}

	public void setOperationsBudgetRentalEquipmentCost(
			Float operationsBudgetRentalEquipmentCost) {
		this.operationsBudgetRentalEquipmentCost = operationsBudgetRentalEquipmentCost;
	}

	public Float getOperationsOwnedEquipmentCost() {
		return operationsOwnedEquipmentCost;
	}

	public void setOperationsOwnedEquipmentCost(Float operationsOwnedEquipmentCost) {
		this.operationsOwnedEquipmentCost = operationsOwnedEquipmentCost;
	}

	public Float getOperationsProjectAdministrationCost() {
		return operationsProjectAdministrationCost;
	}

	public void setOperationsProjectAdministrationCost(
			Float operationsProjectAdministrationCost) {
		this.operationsProjectAdministrationCost = operationsProjectAdministrationCost;
	}

	public Float getOperationsIndirectExpenses() {
		return operationsIndirectExpenses;
	}

	public void setOperationsIndirectExpenses(Float operationsIndirectExpenses) {
		this.operationsIndirectExpenses = operationsIndirectExpenses;
	}

	public Float getOperationsBudgetLaborCost() {
		return operationsBudgetLaborCost;
	}

	public void setOperationsBudgetLaborCost(Float operationsBudgetLaborCost) {
		this.operationsBudgetLaborCost = operationsBudgetLaborCost;
	}
//
//	public JobUploadDocument getJobUploadDocID() {
//		return jobUploadDocID;
//	}
//
//	public void setJobUploadDocID(JobUploadDocument jobUploadDocID) {
//		this.jobUploadDocID = jobUploadDocID;
//	}
	
}