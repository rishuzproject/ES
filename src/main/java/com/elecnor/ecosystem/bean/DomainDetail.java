package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the domain_details database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "DOMAIN_DETAILS")
public class DomainDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOMAIN_ID")
	@Expose
	private Long domainId;

	@Expose
	@Column(name = "COMPANY_ADDRESS")
	private String companyAddress;

	@Expose
	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Expose
	@Column(name = "COMPANY_PHONE_CARRIER")
	private String companyPhoneCarrier;

	@Expose
	@Column(name = "COMPANY_PHONE_NUMBER")
	private String companyPhoneNumber;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DOMAIN_CREATED_DATE")
	private Date domainCreatedDate;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "DOMAIN_NAME")
	@Expose
	private String domainName;

	@Expose
	@Column(name = "STATUS")
	private String status;

	@Expose
	@Column(name = "SUBMITTED_EMAIL_ID")
	private String submittedEmailId;

	@Expose
	@Column(name = "SUBMITTED_NAME")
	private String submittedName;

	@Expose
	@Column(name = "SUBMITTED_USER_ID")
	private Long submittedUserId;
	
	//@Expose
	@Column(name="DOMAIN_LOGO")
    private byte[] domainLogo;

	// bi-directional many-to-one association to ApplicationInvoice
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<ApplicationInvoice> applicationInvoices;*/

	// bi-directional many-to-one association to BudgetCode
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<BudgetCode> budgetCodes;*/

	// bi-directional many-to-one association to DepartmentType
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<DepartmentType> departmentTypes;*/

	// bi-directional many-to-one association to DocumentCenter
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<DocumentCenter> documentCenters;*/

	/*// bi-directional many-to-one association to ItemDb
	@OneToMany(mappedBy = "domainDetail")
	private Set<ItemDB> itemDbs;*/

	/*// bi-directional many-to-one association to VendorDirectory
	@OneToMany(mappedBy = "domainDetail")
	private Set<VendorDirectory> vendorDirectories;*/

	/*// bi-directional many-to-one association to LicenseDirectory
	@OneToMany(mappedBy = "domainDetail")
	private Set<LicenseDirectory> licenseDirectories;*/

	// bi-directional many-to-one association to LicenseDirectory
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMapping;*/

	/*// bi-directional many-to-one association to HolidayCalendar
	@OneToMany(mappedBy = "domainDetail")
	private Set<HolidayCalendar> holidayCalendar;*/

	/*public Set<VendorDirectory> getVendorDirectories() {
		return vendorDirectories;
	}

	public void setVendorDirectories(Set<VendorDirectory> vendorDirectories) {
		this.vendorDirectories = vendorDirectories;
	}*/

	/**
	 * @return the applicationInvoicePlanMapping
	 */
	/*public Set<ApplicationInvoicePlanMapping> getApplicationInvoicePlanMapping() {
		return applicationInvoicePlanMapping;
	}

	*//**
	 * @param applicationInvoicePlanMapping
	 *            the applicationInvoicePlanMapping to set
	 *//*
	public void setApplicationInvoicePlanMapping(Set<ApplicationInvoicePlanMapping> applicationInvoicePlanMapping) {
		this.applicationInvoicePlanMapping = applicationInvoicePlanMapping;
	}*/
/*
	// bi-directional many-to-one association to ProjectType
	@OneToMany(mappedBy = "domainDetail")
	private Set<ProjectType> projectTypes;*/

	// bi-directional many-to-one association to UserDetail
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<UserDetail> userDetails;*/

	/*@OneToMany(mappedBy = "domainDetail")
	private Set<UserDetail> deptUserDetails;*/

	
	// bi-directional many-to-one association to UserDetail
	// @OneToMany(mappedBy = "signupDomainId")
	// private Set<CustomerDirectory> customerDetails;

	// bi-directional many-to-one association to DomainApplicationPlanMapping
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<DomainApplicationPlanMapping> domainApplicationPlanMappings;*/

	// bi-directional many-to-one association to
	// domainApplicationPlanMappingTrackings
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<DomainApplicationPlanMappingTracking> domainApplicationPlanMappingTrackings;*/

	// bi-directional many-to-one association to JobDetail
	/*@OneToMany(mappedBy = "domainDetail")
	private Set<JobDetail> jobDetails;*/

	// bi-directional many-to-one association to Role View Mapping
	/*@OneToMany(mappedBy = "domainId")
	private Set<RoleViewMapping> roleViewMapping;*/

	/*// bi-directional many-to-one association to Temporary User View Mapping
	@OneToMany(mappedBy = "domainId")
	private Set<TemporaryUserViewMapping> temporaryUserViewMapping;*/

	/*// bi-directional many-to-one association to Temporary User View Mapping
	@OneToMany(mappedBy = "domainId")
	private Set<UnitAbbreviationDirectory> unitAbbreviationDirectory;*/

	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhoneCarrier() {
		return this.companyPhoneCarrier;
	}

	public void setCompanyPhoneCarrier(String companyPhoneCarrier) {
		this.companyPhoneCarrier = companyPhoneCarrier;
	}

	public String getCompanyPhoneNumber() {
		return this.companyPhoneNumber;
	}

	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}

	public Date getDomainCreatedDate() {
		return this.domainCreatedDate;
	}

	public void setDomainCreatedDate(Date domainCreatedDate) {
		this.domainCreatedDate = domainCreatedDate;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmittedEmailId() {
		return submittedEmailId;
	}

	public void setSubmittedEmailId(String submittedEmailId) {
		this.submittedEmailId = submittedEmailId;
	}

	public String getSubmittedName() {
		return submittedName;
	}

	public void setSubmittedName(String submittedName) {
		this.submittedName = submittedName;
	}

	public Long getSubmittedUserId() {
		return submittedUserId;
	}

	public void setSubmittedUserId(Long submittedUserId) {
		this.submittedUserId = submittedUserId;
	}

	public byte[] getDomainLogo() {
		return domainLogo;
	}

	public void setDomainLogo(byte[] domainLogo) {
		this.domainLogo = domainLogo;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/*public Set<BudgetCode> getBudgetCodes() {
		return this.budgetCodes;
	}

	public void setBudgetCodes(Set<BudgetCode> budgetCodes) {
		this.budgetCodes = budgetCodes;
	}*/

	/*public Set<DepartmentType> getDepartmentTypes() {
		return this.departmentTypes;
	}

	public void setDepartmentTypes(Set<DepartmentType> departmentTypes) {
		this.departmentTypes = departmentTypes;
	}*/

	/*public Set<DocumentCenter> getDocumentCenters() {
		return this.documentCenters;
	}

	public void setDocumentCenters(Set<DocumentCenter> documentCenters) {
		this.documentCenters = documentCenters;
	}*/

	/*public Set<ItemDB> getItemDbs() {
		return this.itemDbs;
	}

	public void setItemDbs(Set<ItemDB> itemDbs) {
		this.itemDbs = itemDbs;
	}*/

	/*public Set<ProjectType> getProjectTypes() {
		return this.projectTypes;
	}

	public void setProjectTypes(Set<ProjectType> projectTypes) {
		this.projectTypes = projectTypes;
	}
*/
	/*public Set<ApplicationInvoice> getApplicationInvoices() {
		return applicationInvoices;
	}

	public void setApplicationInvoices(Set<ApplicationInvoice> applicationInvoices) {
		this.applicationInvoices = applicationInvoices;
	}*/

	/*public Set<UserDetail> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Set<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}*/

	/*public Set<DomainApplicationPlanMapping> getDomainApplicationPlanMappings() {
		return domainApplicationPlanMappings;
	}

	public void setDomainApplicationPlanMappings(Set<DomainApplicationPlanMapping> domainApplicationPlanMappings) {
		this.domainApplicationPlanMappings = domainApplicationPlanMappings;
	}*/

	/**
	 * @return the holidayCalendar
	 *//*
	public Set<HolidayCalendar> getHolidayCalendar() {
		return holidayCalendar;
	}

	*//**
	 * @param holidayCalendar
	 *            the holidayCalendar to set
	 *//*
	public void setHolidayCalendar(Set<HolidayCalendar> holidayCalendar) {
		this.holidayCalendar = holidayCalendar;
	}
*/
	/**
	 * @return the licenseDirectories
	 *//*
	public Set<LicenseDirectory> getLicenseDirectories() {
		return licenseDirectories;
	}

	*//**
	 * @param licenseDirectories
	 *            the licenseDirectories to set
	 *//*
	public void setLicenseDirectories(Set<LicenseDirectory> licenseDirectories) {
		this.licenseDirectories = licenseDirectories;
	}*/

	/**
	 * @return the domainApplicationPlanMappingTrackings
	 */
	/*public Set<DomainApplicationPlanMappingTracking> getDomainApplicationPlanMappingTrackings() {
		return domainApplicationPlanMappingTrackings;
	}

	*//**
	 * @param domainApplicationPlanMappingTrackings
	 *            the domainApplicationPlanMappingTrackings to set
	 *//*
	public void setDomainApplicationPlanMappingTrackings(
			Set<DomainApplicationPlanMappingTracking> domainApplicationPlanMappingTrackings) {
		this.domainApplicationPlanMappingTrackings = domainApplicationPlanMappingTrackings;
	}*/

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	/*public Set<JobDetail> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(Set<JobDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}

	public Set<RoleViewMapping> getRoleViewMapping() {
		return roleViewMapping;
	}

	public void setRoleViewMapping(Set<RoleViewMapping> roleViewMapping) {
		this.roleViewMapping = roleViewMapping;
	}*/

	/*public Set<TemporaryUserViewMapping> getTemporaryUserViewMapping() {
		return temporaryUserViewMapping;
	}

	public void setTemporaryUserViewMapping(Set<TemporaryUserViewMapping> temporaryUserViewMapping) {
		this.temporaryUserViewMapping = temporaryUserViewMapping;
	}*/

	/*public Set<UnitAbbreviationDirectory> getUnitAbbreviationDirectory() {
		return unitAbbreviationDirectory;
	}

	public void setUnitAbbreviationDirectory(Set<UnitAbbreviationDirectory> unitAbbreviationDirectory) {
		this.unitAbbreviationDirectory = unitAbbreviationDirectory;
	}
*/
	/*public Set<UserDetail> getDeptUserDetails() {
		return deptUserDetails;
	}

	public void setDeptUserDetails(Set<UserDetail> deptUserDetails) {
		this.deptUserDetails = deptUserDetails;
	}*/

	// public Set<CustomerDirectory> getCustomerDetails() {
	// return customerDetails;
	// }
	//
	// public void setCustomerDetails(Set<CustomerDirectory> customerDetails) {
	// this.customerDetails = customerDetails;
	// }

}