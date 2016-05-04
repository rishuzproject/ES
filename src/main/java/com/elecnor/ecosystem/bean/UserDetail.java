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
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.google.gson.annotations.Expose;

/**
 * The persistent class for the user_details database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "USER_DETAILS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	@Expose
	private Long userId;

	@Expose
	@Column(name = "EMAIL_ID")
	private String emailId;

	@Expose
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Expose
	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Expose
	@Column(name = "LAST_NAME")
	private String lastName;

	@Expose
	@Column(name = "NICK_NAME")
	private String nickName;

	// @Expose
	@Column(name = "PASSWORD")
	private String password;

	@Expose
	@Column(name = "PHONE_CARRIER")
	private String phoneCarrier;

	@Expose
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Expose
	@Column(name = "PREFERRED_CONTACT_MODE")
	private String preferredContactMode;

	/*
	 * @Expose
	 * 
	 * @Column(name = "ROLE") private String roleNameEcosystem;
	 */

	@Expose
	@Transient
	private String role;

	@Expose
	@Column(name = "STATUS")
	private String status;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// field for getting decrypted password while updating
	// @Expose
	@Transient
	private String decryptedPassword;

	// fields from redesign branch
	@Column(name = "IS_DEPARTMENT_HEAD")
	@Expose
	Boolean isDeptHead;

	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_UNIT_ID")
	@Expose
	OrganizationDirectory orgUnitId;

	// Removing Transient cause @Formula annotated fields doesn't map to the DB
	// column, By Vaibhav.
	// @Transient
	// @Expose
	@Formula("ORGANIZATION_UNIT_ID")
	private Long orgTransientId;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_DIRECTORY_ID")
	@Expose
	// @JsonIgnore
	DepartmentDirectory deptUnitId;

	// @Transient
	// @Expose
	@Formula("DEPARTMENT_DIRECTORY_ID")
	private Integer deptTransientId;

	// @Transient
	// @Expose
	@Formula("DESIGNATION_DIRECTORY_ID")
	private Integer desigTransientId;

	@ManyToOne
	@JoinColumn(name = "DESIGNATION_DIRECTORY_ID")
	@Expose
	@JsonIgnore
	private DesignationDirectory desigId;

	@ManyToOne
	@JoinColumn(name = "SUPERVISOR_ID")
	@JsonIgnore
	private UserDetail superviserId;

	/* Below are the fields which will be exposed on behalf of supervisor @Rishabh*/
	@Transient
	@Expose
	private Long superviserIdExpose;

	@Transient
	@Expose
	private String superviserFirstNameExpose;

	@Transient
	@Expose
	private String superviserEmailIdExpose;
	
	/*---------------*/

	@Expose
	@Formula("DOMAIN_ID")
	private Long domainIdTransient;

	@OneToMany(mappedBy = "userId")
	@JsonIgnore
	private Set<DepartmentUserMapping> departmentUserMappingUserDetails;

	@Expose
	@Transient
	// @Formula("(select COMPANY_ADDRESS from DOMAIN_DETAILS d where
	// d.DOMAIN_ID=DOMAIN_ID)")
	private String domainAddressTransient;

	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<DepartmentUserMapping>
	 * departmentUserMappingSubmittedBy;
	 */

	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<DepartmentUserMapping>
	 * departmentUserMappingUpdatedBy;
	 */

	// bi-directional many-to-one association to ApplicationDirectory
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<ApplicationDirectory>
	 * applicationDirectoriesSubmittedBy;
	 */

	// bi-directional many-to-one association to ApplicationDirectory
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<ApplicationDirectory>
	 * applicationDirectoriesUpdatedBy;
	 */

	// bi-directional many-to-one association to ApplicationPlanDirectory
	/*
	 * @OneToMany(mappedBy = "submittedBy") private
	 * Set<ApplicationPlanDirectory> applicationPlanDirectoriesSubmittedBy;
	 */

	// bi-directional many-to-one association to ApplicationPlanDirectory
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<ApplicationPlanDirectory>
	 * applicationPlanDirectoriesUpdatedBy;
	 */

	// bi-directional many-to-one association to ApplicationUserMapping
	@OneToMany(mappedBy = "userId")
	@JsonIgnore
	private Set<ApplicationUserMapping> applicationUserMappingsUserId;

	// bi-directional many-to-one association to ApplicationUserMapping
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<ApplicationUserMapping>
	 * applicationUserMappingsSubmittedBy;
	 */

	// bi-directional many-to-one association to ApplicationUserMapping
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<ApplicationUserMapping>
	 * applicationUserMappingsUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to VendorDirectory
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<VendorDirectory>
	 * vendorDirectoriesSubmittedBy;
	 */
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<RfcTakeoffSheet>
	 * rfcTakeOffSheetubmittedBy;
	 */

	// bi-directional many-to-one association to User Roles Mapping
	@OneToMany(mappedBy = "userRolesDetail")
	@JsonIgnore
	private Set<UserRolesMapping> userRolesMappingId;

	// bi-directional many-to-one association to VendorDirectory
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<ApplicationUserMapping>
	 * vendorDirectoriesUpdatedBy;
	 */

	// bi-directional many-to-one association to BudgetCode
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<BudgetCode>
	 * budgetCodesSubmittedBy;
	 */

	// bi-directional many-to-one association to BudgetCode
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<BudgetCode>
	 * budgetCodesUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to CustomerDirectory
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<CustomerDirectory>
	 * customerDirectoriesSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to CustomerDirectory
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<CustomerDirectory>
	 * customerDirectoriesUpdatedBy;
	 */

	// bi-directional many-to-one association to BudgetCode
	/*
	 * @OneToMany(mappedBy = "submittedBy") private
	 * Set<DomainApplicationPlanMapping>
	 * domainApplicationPlanMappingsSubmittedBy;
	 */

	// bi-directional many-to-one association to BudgetCode
	/*
	 * @OneToMany(mappedBy = "updatedBy") private
	 * Set<DomainApplicationPlanMapping> domainApplicationPlanMappingsUpdatedBy;
	 */

	// bi-directional many-to-one association to BudgetCode
	/*
	 * @OneToMany(mappedBy = "submittedBy") private
	 * Set<DomainApplicationPlanMappingTracking>
	 * domainApplicationPlanMappingTrackingsSubmittedBy;
	 */

	// bi-directional many-to-one association to BudgetCode
	/*
	 * @OneToMany(mappedBy = "updatedBy") private
	 * Set<DomainApplicationPlanMappingTracking>
	 * domainApplicationPlanMappingTrackingsUpdatedBy;
	 */

	// bi-directional many-to-one association to DepartmentType
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<DepartmentType>
	 * departmentTypesSubmittedBy;
	 */

	// bi-directional many-to-one association to DepartmentType
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<DepartmentType>
	 * departmentTypesUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to ProjectType
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<ProjectType>
	 * projectTypesSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to ProjectType
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<ProjectType>
	 * projectTypesUpdatedBy;
	 */

	// bi-directional many-to-one association to DocumentCenter
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<DocumentCenter>
	 * documentCentersSubmittedBy;
	 */

	// bi-directional many-to-one association to DocumentCenter
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<DocumentCenter>
	 * documentCentersUpdatedBy;
	 */

	// bi-directional many-to-one association to DocumentCenter
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<ApplicationInvoice>
	 * applicationInvoicesSubmittedBy;
	 */

	// bi-directional many-to-one association to DocumentCenter
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<ApplicationInvoice>
	 * applicationInvoicesUpdatedBy;
	 */

	// bi-directional many-to-one association to JobDetail
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<JobDetail>
	 * jobDetailsSubmittedBy;
	 */

	// bi-directional many-to-one association to JobDetail
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<JobDetail>
	 * jobDetailsUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to JobDetail
	 * 
	 * @OneToMany(mappedBy = "usedBy") private Set<CouponDetails>
	 * couponDetailsUsedBy;
	 */

	/*
	 * // bi-directional many-to-one association to JobDetail
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<CouponDetails>
	 * couponDetailsUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to BudgetFormDje
	 * 
	 * @OneToMany(mappedBy = "submittedByUserDetail") private Set<BudgetFormDje>
	 * budgetFormDjeSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormDje
	 * 
	 * @OneToMany(mappedBy = "updatedByUserDetail") private Set<BudgetFormDje>
	 * budgetFormDjeUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to BudgetFormEquip
	 * 
	 * @OneToMany(mappedBy = "submittedByUserDetail") private
	 * Set<BudgetFormEquip> budgetFormEquipSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormEquip
	 * 
	 * @OneToMany(mappedBy = "updatedByUserDetail") private Set<BudgetFormEquip>
	 * budgetFormEquipUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to BudgetFormIndirect
	 * 
	 * @OneToMany(mappedBy = "submittedByUserDetail") private
	 * Set<BudgetFormIndirect> budgetFormIndirectSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormDje
	 * 
	 * @OneToMany(mappedBy = "updatedByUserDetail") private
	 * Set<BudgetFormIndirect> budgetFormIndirectUpdatedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormLabor
	 * 
	 * @OneToMany(mappedBy = "submittedByUserDetail") private
	 * Set<BudgetFormLabor> budgetFormLaborSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormLabor
	 * 
	 * @OneToMany(mappedBy = "updatedByUserDetail") private Set<BudgetFormLabor>
	 * budgetFormLaborUpdatedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormMat
	 * 
	 * @OneToMany(mappedBy = "updatedByUserDetail") private Set<BudgetFormMat>
	 * budgetFormMatUpdatedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormMat
	 * 
	 * @OneToMany(mappedBy = "submittedByUserDetail") private Set<BudgetFormMat>
	 * budgetFormMatSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormProjectAdmin
	 * 
	 * @OneToMany(mappedBy = "updatedByUserDetail") private
	 * Set<BudgetFormProjectAdmin> budgetFormProjectAdminUpdatedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormLabor
	 * 
	 * @OneToMany(mappedBy = "submittedByUserDetail") private
	 * Set<BudgetFormProjectAdmin> budgetFormProjectAdminSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormSubContractor
	 * 
	 * @OneToMany(mappedBy = "submittedByUserDetail") private
	 * Set<BudgetFormSubContractor> budgetFormSubContractorSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to BudgetFormDje
	 * 
	 * @OneToMany(mappedBy = "updatedByUserDetail") private
	 * Set<BudgetFormSubContractor> budgetFormSubContractorUpdatedBy;
	 */

	// bi-directional many-to-one association to AddressDetail
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<AddressDetails>
	 * addressDetailsSubmittedBy;
	 */

	// bi-directional many-to-one association to AddressDetail
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<AddressDetails>
	 * addressDetailsUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to CouponDetail
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<CouponDetails>
	 * couponDetailsSubmittedBy;
	 */

	// bi-directional many-to-one association to JobDetail
	@OneToMany(mappedBy = "foreman")
	@JsonIgnore
	private Set<JobDetail> jobDetailsForeman;

	// bi-directional many-to-one association to JobDetail
	@OneToMany(mappedBy = "manager")
	@JsonIgnore
	private Set<JobDetail> jobDetailsManager;
	// bi-directional many-to-one association to JobDetail
	@OneToMany(mappedBy = "supervisor")
	@JsonIgnore
	private Set<JobDetail> jobDetailsSupervisor;

	/*
	 * // bi-directional many-to-one association to LicenseDirectory
	 * 
	 * @OneToMany(mappedBy = "licenseSubmittedBy") private Set<LicenseDirectory>
	 * licenseDirectorySubmittedBy;
	 * 
	 * // bi-directional many-to-one association to LicenseDirectory
	 * 
	 * @OneToMany(mappedBy = "licenseUpdatedBy") private Set<LicenseDirectory>
	 * licenseDirectoryUpdatedBy;
	 */

	// bi-directional many-to-one association to JobDetail
	/*
	 * @OneToMany(mappedBy = "submittedBy") private
	 * Set<ApplicationInvoicePlanMapping>
	 * applicationInvoicePlanMappingSubmittedBy;
	 */

	/*
	 * // bi-directional many-to-one association to HolidayCalendar
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<HolidayCalendar>
	 * holidayCalendarSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to JobDetail
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<HolidayCalendar>
	 * holidayCalendarUpdatedBy;
	 */

	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	// @Expose
	@JoinColumn(name = "DOMAIN_ID")
	@JsonIgnore
	private DomainDetail domainDetail;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	@JsonIgnore
	private UserDetail submittedBy;

	// bi-directional many-to-one association to UserDetail
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<UserDetail>
	 * userDetailsSubmittedBy;
	 */

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	@JsonIgnore
	private UserDetail updatedBy;

	// bi-directional many-to-one association to UserDetail
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<UserDetail>
	 * userDetailsUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to ItemDb
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<ItemDB>
	 * itemDbSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to ItemDb
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<ItemDB> itemDbUpdatedBy;
	 */
	// bi-directional many-to-one association to SOV Directory
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<SovDirectory>
	 * sovDirectorySubmittedBy;
	 */

	// bi-directional many-to-one association to SOV Directory
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<SovDirectory>
	 * sovDirectoryUpdatedBy;
	 */

	// bi-directional many-to-one association to SOV Reviews Table
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<SovReviewsTable>
	 * sovReviewsTableSubmittedBy;
	 */

	// bi-directional many-to-one association to SOV Reviews Table
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<SovReviewsTable>
	 * sovReviewsTableUpdatedBy;
	 */

	// bi-directional many-to-one association to SOV Table Tracking
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<SovTableTracking>
	 * sovTableTrackingSubmittedBy;
	 */

	// bi-directional many-to-one association to SOV Table Tracking
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<SovTableTracking>
	 * sovTableTrackingUpdatedBy;
	 */

	// bi-directional many-to-one association to SOV Table
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<SovTable>
	 * sovTableSubmittedBy;
	 */

	// bi-directional many-to-one association to SOV Table
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<SovTable>
	 * sovTableUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to SOV Comments Table
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<SovCommentsTable>
	 * sovCommentsTableUpdatedBy;
	 * 
	 * // bi-directional many-to-one association to SOV Comments Table
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<SovCommentsTable>
	 * sovCommentsTableSubmittedBy;
	 */

	// bi-directional many-to-one association to Email Log Details
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<EmailLogDetails>
	 * emailLogDetailsSubmittedBy;
	 */

	/*
	 * // bi-directional many-to-one association to Temporary user
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<TemporaryUserDetail>
	 * temporaryUserUpdatedBy;
	 * 
	 * // bi-directional many-to-one association to Temporary user
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<TemporaryUserDetail>
	 * temporaryUserSubmittedBy;
	 */

	// bi-directional many-to-one association to Module Directory
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<ModuleDirectory>
	 * moduleDirectorySubmittedBy;
	 */

	// bi-directional many-to-one association to Module Directory
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<ModuleDirectory>
	 * moduleDirectoryUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to Sub Module Directory
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private Set<SubModuleDirectory>
	 * subModuleDirectorySubmittedBy;
	 * 
	 * // bi-directional many-to-one association to Sub Module Directory
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<SubModuleDirectory>
	 * subModuleDirectoryUpdatedBy;
	 */

	// bi-directional many-to-one association to Role View Mapping
	/*
	 * @OneToMany(mappedBy = "submittedBy") private Set<RoleViewMapping>
	 * roleViewMappingSubmittedBy;
	 */

	// bi-directional many-to-one association to Role View Mapping
	/*
	 * @OneToMany(mappedBy = "updatedBy") private Set<RoleViewMapping>
	 * roleViewMappingUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to Temporary User View Mapping
	 * 
	 * @OneToMany(mappedBy = "userId") private Set<TemporaryUserViewMapping>
	 * temporaryUserViewMappingUserId;
	 * 
	 * // bi-directional many-to-one association to Temporary User View Mapping
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private
	 * Set<TemporaryUserViewMapping> temporaryUserViewMappingSubmittedBy;
	 * 
	 * // bi-directional many-to-one association to Temporary User View Mapping
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<TemporaryUserViewMapping>
	 * temporaryUserViewMappingUpdatedBy;
	 */

	/*
	 * // bi-directional many-to-one association to Unit Abbreviation Directory
	 * 
	 * @OneToMany(mappedBy = "submittedBy") private
	 * Set<UnitAbbreviationDirectory> unitAbbreviationDirectorySubmittedBy;
	 * 
	 * // bi-directional many-to-one association to Unit Abbreviation Directory
	 * 
	 * @OneToMany(mappedBy = "updatedBy") private Set<UnitAbbreviationDirectory>
	 * unitAbbreviationDirectoryUpdatedBy;
	 */

	/*
	 * public Set<RfcTakeoffSheet> getRfcTakeOffSheetubmittedBy() { return
	 * rfcTakeOffSheetubmittedBy; }
	 * 
	 * public void setRfcTakeOffSheetubmittedBy(Set<RfcTakeoffSheet>
	 * rfcTakeOffSheetubmittedBy) { this.rfcTakeOffSheetubmittedBy =
	 * rfcTakeOffSheetubmittedBy; }
	 */

	/*
	 * public Set<VendorDirectory> getVendorDirectoriesSubmittedBy() { return
	 * vendorDirectoriesSubmittedBy; }
	 * 
	 * public void setVendorDirectoriesSubmittedBy(Set<VendorDirectory>
	 * vendorDirectoriesSubmittedBy) { this.vendorDirectoriesSubmittedBy =
	 * vendorDirectoriesSubmittedBy; }
	 */

	/*
	 * public Set<ApplicationUserMapping> getVendorDirectoriesUpdatedBy() {
	 * return vendorDirectoriesUpdatedBy; }
	 * 
	 * public void setVendorDirectoriesUpdatedBy(Set<ApplicationUserMapping>
	 * vendorDirectoriesUpdatedBy) { this.vendorDirectoriesUpdatedBy =
	 * vendorDirectoriesUpdatedBy; }
	 */

	public Set<UserRolesMapping> getUserRolesMappingId() {
		return userRolesMappingId;
	}

	public void setUserRolesMappingId(Set<UserRolesMapping> userRolesMappingId) {
		this.userRolesMappingId = userRolesMappingId;
	}

	/*
	 * public Set<BudgetFormDje> getBudgetFormDjeSubmittedBy() { return
	 * budgetFormDjeSubmittedBy; }
	 * 
	 * public void setBudgetFormDjeSubmittedBy(Set<BudgetFormDje>
	 * budgetFormDjeSubmittedBy) { this.budgetFormDjeSubmittedBy =
	 * budgetFormDjeSubmittedBy; }
	 * 
	 * public Set<BudgetFormDje> getBudgetFormDjeUpdatedBy() { return
	 * budgetFormDjeUpdatedBy; }
	 * 
	 * public void setBudgetFormDjeUpdatedBy(Set<BudgetFormDje>
	 * budgetFormDjeUpdatedBy) { this.budgetFormDjeUpdatedBy =
	 * budgetFormDjeUpdatedBy; }
	 */

	/*
	 * public Set<BudgetFormEquip> getBudgetFormEquipSubmittedBy() { return
	 * budgetFormEquipSubmittedBy; }
	 * 
	 * public void setBudgetFormEquipSubmittedBy(Set<BudgetFormEquip>
	 * budgetFormEquipSubmittedBy) { this.budgetFormEquipSubmittedBy =
	 * budgetFormEquipSubmittedBy; }
	 * 
	 * public Set<BudgetFormEquip> getBudgetFormEquipUpdatedBy() { return
	 * budgetFormEquipUpdatedBy; }
	 * 
	 * public void setBudgetFormEquipUpdatedBy(Set<BudgetFormEquip>
	 * budgetFormEquipUpdatedBy) { this.budgetFormEquipUpdatedBy =
	 * budgetFormEquipUpdatedBy; }
	 */

	/*
	 * public Set<BudgetFormIndirect> getBudgetFormIndirectSubmittedBy() {
	 * return budgetFormIndirectSubmittedBy; }
	 * 
	 * public void setBudgetFormIndirectSubmittedBy(Set<BudgetFormIndirect>
	 * budgetFormIndirectSubmittedBy) { this.budgetFormIndirectSubmittedBy =
	 * budgetFormIndirectSubmittedBy; }
	 * 
	 * public Set<BudgetFormIndirect> getBudgetFormIndirectUpdatedBy() { return
	 * budgetFormIndirectUpdatedBy; }
	 * 
	 * public void setBudgetFormIndirectUpdatedBy(Set<BudgetFormIndirect>
	 * budgetFormIndirectUpdatedBy) { this.budgetFormIndirectUpdatedBy =
	 * budgetFormIndirectUpdatedBy; }
	 * 
	 * public Set<BudgetFormLabor> getBudgetFormLaborSubmittedBy() { return
	 * budgetFormLaborSubmittedBy; }
	 * 
	 * public void setBudgetFormLaborSubmittedBy(Set<BudgetFormLabor>
	 * budgetFormLaborSubmittedBy) { this.budgetFormLaborSubmittedBy =
	 * budgetFormLaborSubmittedBy; }
	 * 
	 * public Set<BudgetFormLabor> getBudgetFormLaborUpdatedBy() { return
	 * budgetFormLaborUpdatedBy; }
	 * 
	 * public void setBudgetFormLaborUpdatedBy(Set<BudgetFormLabor>
	 * budgetFormLaborUpdatedBy) { this.budgetFormLaborUpdatedBy =
	 * budgetFormLaborUpdatedBy; }
	 * 
	 * public Set<BudgetFormMat> getBudgetFormMatUpdatedBy() { return
	 * budgetFormMatUpdatedBy; }
	 * 
	 * public void setBudgetFormMatUpdatedBy(Set<BudgetFormMat>
	 * budgetFormMatUpdatedBy) { this.budgetFormMatUpdatedBy =
	 * budgetFormMatUpdatedBy; }
	 * 
	 * public Set<BudgetFormMat> getBudgetFormMatSubmittedBy() { return
	 * budgetFormMatSubmittedBy; }
	 * 
	 * public void setBudgetFormMatSubmittedBy(Set<BudgetFormMat>
	 * budgetFormMatSubmittedBy) { this.budgetFormMatSubmittedBy =
	 * budgetFormMatSubmittedBy; }
	 * 
	 * public Set<BudgetFormProjectAdmin> getBudgetFormProjectAdminUpdatedBy() {
	 * return budgetFormProjectAdminUpdatedBy; }
	 * 
	 * public void
	 * setBudgetFormProjectAdminUpdatedBy(Set<BudgetFormProjectAdmin>
	 * budgetFormProjectAdminUpdatedBy) { this.budgetFormProjectAdminUpdatedBy =
	 * budgetFormProjectAdminUpdatedBy; }
	 * 
	 * public Set<BudgetFormProjectAdmin> getBudgetFormProjectAdminSubmittedBy()
	 * { return budgetFormProjectAdminSubmittedBy; }
	 * 
	 * public void
	 * setBudgetFormProjectAdminSubmittedBy(Set<BudgetFormProjectAdmin>
	 * budgetFormProjectAdminSubmittedBy) {
	 * this.budgetFormProjectAdminSubmittedBy =
	 * budgetFormProjectAdminSubmittedBy; }
	 * 
	 * public Set<BudgetFormSubContractor>
	 * getBudgetFormSubContractorSubmittedBy() { return
	 * budgetFormSubContractorSubmittedBy; }
	 * 
	 * public void
	 * setBudgetFormSubContractorSubmittedBy(Set<BudgetFormSubContractor>
	 * budgetFormSubContractorSubmittedBy) {
	 * this.budgetFormSubContractorSubmittedBy =
	 * budgetFormSubContractorSubmittedBy; }
	 * 
	 * public Set<BudgetFormSubContractor> getBudgetFormSubContractorUpdatedBy()
	 * { return budgetFormSubContractorUpdatedBy; }
	 * 
	 * public void
	 * setBudgetFormSubContractorUpdatedBy(Set<BudgetFormSubContractor>
	 * budgetFormSubContractorUpdatedBy) { this.budgetFormSubContractorUpdatedBy
	 * = budgetFormSubContractorUpdatedBy; }
	 */

	/**
	 * @return the couponDetailsUsedBy
	 */
	/*
	 * public Set<CouponDetails> getCouponDetailsUsedBy() { return
	 * couponDetailsUsedBy; }
	 * 
	 *//**
		 * @param couponDetailsUsedBy
		 *            the couponDetailsUsedBy to set
		 */
	/*
	 * public void setCouponDetailsUsedBy(Set<CouponDetails>
	 * couponDetailsUsedBy) { this.couponDetailsUsedBy = couponDetailsUsedBy; }
	 * 
	 *//**
		 * @return the couponDetailsUpdatedBy
		 */
	/*
	 * public Set<CouponDetails> getCouponDetailsUpdatedBy() { return
	 * couponDetailsUpdatedBy; }
	 * 
	 *//**
		 * @param couponDetailsUpdatedBy
		 *            the couponDetailsUpdatedBy to set
		 */
	/*
	 * public void setCouponDetailsUpdatedBy(Set<CouponDetails>
	 * couponDetailsUpdatedBy) { this.couponDetailsUpdatedBy =
	 * couponDetailsUpdatedBy; }
	 * 
	 *//**
		 * @return the couponDetailsSubmittedBy
		 */
	/*
	 * public Set<CouponDetails> getCouponDetailsSubmittedBy() { return
	 * couponDetailsSubmittedBy; }
	 * 
	 *//**
		 * @param couponDetailsSubmittedBy
		 *            the couponDetailsSubmittedBy to set
		 *//*
		 * public void setCouponDetailsSubmittedBy(Set<CouponDetails>
		 * couponDetailsSubmittedBy) { this.couponDetailsSubmittedBy =
		 * couponDetailsSubmittedBy; }
		 */

	/**
	 * @return the applicationInvoicePlanMappingSubmittedBy
	 */
	/*
	 * public Set<ApplicationInvoicePlanMapping>
	 * getApplicationInvoicePlanMappingSubmittedBy() { return
	 * applicationInvoicePlanMappingSubmittedBy; }
	 * 
	 *//**
		 * @param applicationInvoicePlanMappingSubmittedBy
		 *            the applicationInvoicePlanMappingSubmittedBy to set
		 *//*
		 * public void setApplicationInvoicePlanMappingSubmittedBy(Set<
		 * ApplicationInvoicePlanMapping>
		 * applicationInvoicePlanMappingSubmittedBy) {
		 * this.applicationInvoicePlanMappingSubmittedBy =
		 * applicationInvoicePlanMappingSubmittedBy; }
		 */

	/**
	 * @return the holidayCalendarSubmittedBy
	 */
	/*
	 * public Set<HolidayCalendar> getHolidayCalendarSubmittedBy() { return
	 * holidayCalendarSubmittedBy; }
	 * 
	 *//**
		 * @param holidayCalendarSubmittedBy
		 *            the holidayCalendarSubmittedBy to set
		 */
	/*
	 * public void setHolidayCalendarSubmittedBy(Set<HolidayCalendar>
	 * holidayCalendarSubmittedBy) { this.holidayCalendarSubmittedBy =
	 * holidayCalendarSubmittedBy; }
	 * 
	 *//**
		 * @return the holidayCalendarUpdatedBy
		 */
	/*
	 * public Set<HolidayCalendar> getHolidayCalendarUpdatedBy() { return
	 * holidayCalendarUpdatedBy; }
	 * 
	 *//**
		 * @param holidayCalendarUpdatedBy
		 *            the holidayCalendarUpdatedBy to set
		 *//*
		 * public void setHolidayCalendarUpdatedBy(Set<HolidayCalendar>
		 * holidayCalendarUpdatedBy) { this.holidayCalendarUpdatedBy =
		 * holidayCalendarUpdatedBy; }
		 */

	/**
	 * @return the licenseDirectorySubmittedBy
	 */
	/*
	 * public Set<LicenseDirectory> getLicenseDirectorySubmittedBy() { return
	 * licenseDirectorySubmittedBy; }
	 * 
	 *//**
		 * @param licenseDirectorySubmittedBy
		 *            the licenseDirectorySubmittedBy to set
		 */
	/*
	 * public void setLicenseDirectorySubmittedBy(Set<LicenseDirectory>
	 * licenseDirectorySubmittedBy) { this.licenseDirectorySubmittedBy =
	 * licenseDirectorySubmittedBy; }
	 * 
	 *//**
		 * @return the licenseDirectoryUpdatedBy
		 */

	/*
	 * public Set<LicenseDirectory> getLicenseDirectoryUpdatedBy() { return
	 * licenseDirectoryUpdatedBy; }
	 * 
	 *//**
		 * @param licenseDirectoryUpdatedBy
		 *            the licenseDirectoryUpdatedBy to set
		 *//*
		 * public void setLicenseDirectoryUpdatedBy(Set<LicenseDirectory>
		 * licenseDirectoryUpdatedBy) { this.licenseDirectoryUpdatedBy =
		 * licenseDirectoryUpdatedBy; }
		 */

	/*
	 * public Set<SovReviewsTable> getSovReviewsTableSubmittedBy() { return
	 * sovReviewsTableSubmittedBy; }
	 * 
	 * public void setSovReviewsTableSubmittedBy(Set<SovReviewsTable>
	 * sovReviewsTableSubmittedBy) { this.sovReviewsTableSubmittedBy =
	 * sovReviewsTableSubmittedBy; }
	 * 
	 * public Set<SovReviewsTable> getSovReviewsTableUpdatedBy() { return
	 * sovReviewsTableUpdatedBy; }
	 * 
	 * public void setSovReviewsTableUpdatedBy(Set<SovReviewsTable>
	 * sovReviewsTableUpdatedBy) { this.sovReviewsTableUpdatedBy =
	 * sovReviewsTableUpdatedBy; }
	 */

	/*
	 * public Set<TemporaryUserDetail> getTemporaryUserSubmittedBy() { return
	 * temporaryUserSubmittedBy; }
	 * 
	 * public void setTemporaryUserSubmittedBy(Set<TemporaryUserDetail>
	 * temporaryUserSubmittedBy) { this.temporaryUserSubmittedBy =
	 * temporaryUserSubmittedBy; }
	 * 
	 * public Set<TemporaryUserDetail> getTemporaryUserUpdatedBy() { return
	 * temporaryUserUpdatedBy; }
	 * 
	 * public void setTemporaryUserUpdatedBy(Set<TemporaryUserDetail>
	 * temporaryUserUpdatedBy) { this.temporaryUserUpdatedBy =
	 * temporaryUserUpdatedBy; }
	 */

	/*
	 * public Set<SovDirectory> getSovDirectorySubmittedBy() { return
	 * sovDirectorySubmittedBy; }
	 * 
	 * public void setSovDirectorySubmittedBy(Set<SovDirectory>
	 * sovDirectorySubmittedBy) { this.sovDirectorySubmittedBy =
	 * sovDirectorySubmittedBy; }
	 * 
	 * public Set<SovDirectory> getSovDirectoryUpdatedBy() { return
	 * sovDirectoryUpdatedBy; }
	 * 
	 * public void setSovDirectoryUpdatedBy(Set<SovDirectory>
	 * sovDirectoryUpdatedBy) { this.sovDirectoryUpdatedBy =
	 * sovDirectoryUpdatedBy; }
	 * 
	 * public Set<SovTableTracking> getSovTableTrackingSubmittedBy() { return
	 * sovTableTrackingSubmittedBy; }
	 * 
	 * public void setSovTableTrackingSubmittedBy(Set<SovTableTracking>
	 * sovTableTrackingSubmittedBy) { this.sovTableTrackingSubmittedBy =
	 * sovTableTrackingSubmittedBy; }
	 * 
	 * public Set<SovTableTracking> getSovTableTrackingUpdatedBy() { return
	 * sovTableTrackingUpdatedBy; }
	 * 
	 * public void setSovTableTrackingUpdatedBy(Set<SovTableTracking>
	 * sovTableTrackingUpdatedBy) { this.sovTableTrackingUpdatedBy =
	 * sovTableTrackingUpdatedBy; }
	 * 
	 * public Set<SovTable> getSovTableSubmittedBy() { return
	 * sovTableSubmittedBy; }
	 * 
	 * public void setSovTableSubmittedBy(Set<SovTable> sovTableSubmittedBy) {
	 * this.sovTableSubmittedBy = sovTableSubmittedBy; }
	 * 
	 * public Set<SovTable> getSovTableUpdatedBy() { return sovTableUpdatedBy; }
	 * 
	 * public void setSovTableUpdatedBy(Set<SovTable> sovTableUpdatedBy) {
	 * this.sovTableUpdatedBy = sovTableUpdatedBy; }
	 */

	/*
	 * public Set<SovCommentsTable> getSovCommentsTableUpdatedBy() { return
	 * sovCommentsTableUpdatedBy; }
	 * 
	 * public void setSovCommentsTableUpdatedBy(Set<SovCommentsTable>
	 * sovCommentsTableUpdatedBy) { this.sovCommentsTableUpdatedBy =
	 * sovCommentsTableUpdatedBy; }
	 * 
	 * public Set<SovCommentsTable> getSovCommentsTableSubmittedBy() { return
	 * sovCommentsTableSubmittedBy; }
	 * 
	 * public void setSovCommentsTableSubmittedBy(Set<SovCommentsTable>
	 * sovCommentsTableSubmittedBy) { this.sovCommentsTableSubmittedBy =
	 * sovCommentsTableSubmittedBy; }
	 */

	public UserDetail() {
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneCarrier() {
		return this.phoneCarrier;
	}

	public void setPhoneCarrier(String phoneCarrier) {
		this.phoneCarrier = phoneCarrier;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPreferredContactMode() {
		return this.preferredContactMode;
	}

	public void setPreferredContactMode(String preferredContactMode) {
		this.preferredContactMode = preferredContactMode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}

	/*
	 * public Set<ApplicationDirectory> getApplicationDirectoriesSubmittedBy() {
	 * return applicationDirectoriesSubmittedBy; }
	 * 
	 * public void
	 * setApplicationDirectoriesSubmittedBy(Set<ApplicationDirectory>
	 * applicationDirectoriesSubmittedBy) {
	 * this.applicationDirectoriesSubmittedBy =
	 * applicationDirectoriesSubmittedBy; }
	 * 
	 * public Set<ApplicationDirectory> getApplicationDirectoriesUpdatedBy() {
	 * return applicationDirectoriesUpdatedBy; }
	 * 
	 * public void setApplicationDirectoriesUpdatedBy(Set<ApplicationDirectory>
	 * applicationDirectoriesUpdatedBy) { this.applicationDirectoriesUpdatedBy =
	 * applicationDirectoriesUpdatedBy; }
	 * 
	 * public Set<ApplicationPlanDirectory>
	 * getApplicationPlanDirectoriesSubmittedBy() { return
	 * applicationPlanDirectoriesSubmittedBy; }
	 * 
	 * public void
	 * setApplicationPlanDirectoriesSubmittedBy(Set<ApplicationPlanDirectory>
	 * applicationPlanDirectoriesSubmittedBy) {
	 * this.applicationPlanDirectoriesSubmittedBy =
	 * applicationPlanDirectoriesSubmittedBy; }
	 * 
	 * public Set<ApplicationPlanDirectory>
	 * getApplicationPlanDirectoriesUpdatedBy() { return
	 * applicationPlanDirectoriesUpdatedBy; }
	 * 
	 * public void
	 * setApplicationPlanDirectoriesUpdatedBy(Set<ApplicationPlanDirectory>
	 * applicationPlanDirectoriesUpdatedBy) {
	 * this.applicationPlanDirectoriesUpdatedBy =
	 * applicationPlanDirectoriesUpdatedBy; }
	 */

	public Long getDomainIdTransient() {
		return domainIdTransient;
	}

	public void setDomainIdTransient(Long domainIdTransient) {
		this.domainIdTransient = domainIdTransient;
	}

	public String getDomainAddressTransient() {
		return domainAddressTransient;
	}

	public void setDomainAddressTransient(String domainAddressTransient) {
		this.domainAddressTransient = domainAddressTransient;
	}

	public Set<ApplicationUserMapping> getApplicationUserMappingsUserId() {
		return applicationUserMappingsUserId;
	}

	public void setApplicationUserMappingsUserId(Set<ApplicationUserMapping> applicationUserMappingsUserId) {
		this.applicationUserMappingsUserId = applicationUserMappingsUserId;
	}

	/*
	 * public Set<ApplicationUserMapping>
	 * getApplicationUserMappingsSubmittedBy() { return
	 * applicationUserMappingsSubmittedBy; }
	 * 
	 * public void
	 * setApplicationUserMappingsSubmittedBy(Set<ApplicationUserMapping>
	 * applicationUserMappingsSubmittedBy) {
	 * this.applicationUserMappingsSubmittedBy =
	 * applicationUserMappingsSubmittedBy; }
	 * 
	 * public Set<ApplicationUserMapping> getApplicationUserMappingsUpdatedBy()
	 * { return applicationUserMappingsUpdatedBy; }
	 * 
	 * public void
	 * setApplicationUserMappingsUpdatedBy(Set<ApplicationUserMapping>
	 * applicationUserMappingsUpdatedBy) { this.applicationUserMappingsUpdatedBy
	 * = applicationUserMappingsUpdatedBy; }
	 * 
	 * public Set<BudgetCode> getBudgetCodesSubmittedBy() { return
	 * budgetCodesSubmittedBy; }
	 * 
	 * public void setBudgetCodesSubmittedBy(Set<BudgetCode>
	 * budgetCodesSubmittedBy) { this.budgetCodesSubmittedBy =
	 * budgetCodesSubmittedBy; }
	 */

	/*
	 * public String getRoleNameEcosystem() { return roleNameEcosystem; }
	 * 
	 * public void setRoleNameEcosystem(String roleNameEcosystem) {
	 * this.roleNameEcosystem = roleNameEcosystem; setRole(roleNameEcosystem); }
	 */

	/*
	 * public Set<BudgetCode> getBudgetCodesUpdatedBy() { return
	 * budgetCodesUpdatedBy; }
	 * 
	 * public void setBudgetCodesUpdatedBy(Set<BudgetCode> budgetCodesUpdatedBy)
	 * { this.budgetCodesUpdatedBy = budgetCodesUpdatedBy; }
	 */

	/*
	 * public Set<CustomerDirectory> getCustomerDirectoriesSubmittedBy() {
	 * return customerDirectoriesSubmittedBy; }
	 * 
	 * public void setCustomerDirectoriesSubmittedBy(Set<CustomerDirectory>
	 * customerDirectoriesSubmittedBy) { this.customerDirectoriesSubmittedBy =
	 * customerDirectoriesSubmittedBy; }
	 * 
	 * public Set<CustomerDirectory> getCustomerDirectoriesUpdatedBy() { return
	 * customerDirectoriesUpdatedBy; }
	 * 
	 * public void setCustomerDirectoriesUpdatedBy(Set<CustomerDirectory>
	 * customerDirectoriesUpdatedBy) { this.customerDirectoriesUpdatedBy =
	 * customerDirectoriesUpdatedBy; }
	 */

	/*
	 * public Set<DomainApplicationPlanMapping>
	 * getDomainApplicationPlanMappingsSubmittedBy() { return
	 * domainApplicationPlanMappingsSubmittedBy; }
	 * 
	 * public void setDomainApplicationPlanMappingsSubmittedBy(Set<
	 * DomainApplicationPlanMapping> domainApplicationPlanMappingsSubmittedBy) {
	 * this.domainApplicationPlanMappingsSubmittedBy =
	 * domainApplicationPlanMappingsSubmittedBy; }
	 * 
	 * public Set<DomainApplicationPlanMapping>
	 * getDomainApplicationPlanMappingsUpdatedBy() { return
	 * domainApplicationPlanMappingsUpdatedBy; }
	 * 
	 * public void setDomainApplicationPlanMappingsUpdatedBy(Set<
	 * DomainApplicationPlanMapping> domainApplicationPlanMappingsUpdatedBy) {
	 * this.domainApplicationPlanMappingsUpdatedBy =
	 * domainApplicationPlanMappingsUpdatedBy; }
	 * 
	 * public Set<DomainApplicationPlanMappingTracking>
	 * getDomainApplicationPlanMappingTrackingsSubmittedBy() { return
	 * domainApplicationPlanMappingTrackingsSubmittedBy; }
	 * 
	 * public void setDomainApplicationPlanMappingTrackingsSubmittedBy(
	 * Set<DomainApplicationPlanMappingTracking>
	 * domainApplicationPlanMappingTrackingsSubmittedBy) {
	 * this.domainApplicationPlanMappingTrackingsSubmittedBy =
	 * domainApplicationPlanMappingTrackingsSubmittedBy; }
	 * 
	 * public Set<DomainApplicationPlanMappingTracking>
	 * getDomainApplicationPlanMappingTrackingsUpdatedBy() { return
	 * domainApplicationPlanMappingTrackingsUpdatedBy; }
	 * 
	 * public void setDomainApplicationPlanMappingTrackingsUpdatedBy(
	 * Set<DomainApplicationPlanMappingTracking>
	 * domainApplicationPlanMappingTrackingsUpdatedBy) {
	 * this.domainApplicationPlanMappingTrackingsUpdatedBy =
	 * domainApplicationPlanMappingTrackingsUpdatedBy; }
	 */

	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}

	/*
	 * public Set<UserDetail> getUserDetailsSubmittedBy() { return
	 * userDetailsSubmittedBy; }
	 * 
	 * public void setUserDetailsSubmittedBy(Set<UserDetail>
	 * userDetailsSubmittedBy) { this.userDetailsSubmittedBy =
	 * userDetailsSubmittedBy; }
	 */

	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

	/*
	 * public Set<UserDetail> getUserDetailsUpdatedBy() { return
	 * userDetailsUpdatedBy; }
	 * 
	 * public void setUserDetailsUpdatedBy(Set<UserDetail> userDetailsUpdatedBy)
	 * { this.userDetailsUpdatedBy = userDetailsUpdatedBy; }
	 */

	/*
	 * public Set<DepartmentType> getDepartmentTypesSubmittedBy() { return
	 * departmentTypesSubmittedBy; }
	 * 
	 * public void setDepartmentTypesSubmittedBy(Set<DepartmentType>
	 * departmentTypesSubmittedBy) { this.departmentTypesSubmittedBy =
	 * departmentTypesSubmittedBy; }
	 * 
	 * public Set<DepartmentType> getDepartmentTypesUpdatedBy() { return
	 * departmentTypesUpdatedBy; }
	 * 
	 * public void setDepartmentTypesUpdatedBy(Set<DepartmentType>
	 * departmentTypesUpdatedBy) { this.departmentTypesUpdatedBy =
	 * departmentTypesUpdatedBy; }
	 */

	/*
	 * public Set<ProjectType> getProjectTypesSubmittedBy() { return
	 * projectTypesSubmittedBy; }
	 * 
	 * public void setProjectTypesSubmittedBy(Set<ProjectType>
	 * projectTypesSubmittedBy) { this.projectTypesSubmittedBy =
	 * projectTypesSubmittedBy; }
	 * 
	 * public Set<ProjectType> getProjectTypesUpdatedBy() { return
	 * projectTypesUpdatedBy; }
	 * 
	 * public void setProjectTypesUpdatedBy(Set<ProjectType>
	 * projectTypesUpdatedBy) { this.projectTypesUpdatedBy =
	 * projectTypesUpdatedBy; }
	 */

	/*
	 * public Set<DocumentCenter> getDocumentCentersSubmittedBy() { return
	 * documentCentersSubmittedBy; }
	 * 
	 * public void setDocumentCentersSubmittedBy(Set<DocumentCenter>
	 * documentCentersSubmittedBy) { this.documentCentersSubmittedBy =
	 * documentCentersSubmittedBy; }
	 * 
	 * public Set<DocumentCenter> getDocumentCentersUpdatedBy() { return
	 * documentCentersUpdatedBy; }
	 * 
	 * public void setDocumentCentersUpdatedBy(Set<DocumentCenter>
	 * documentCentersUpdatedBy) { this.documentCentersUpdatedBy =
	 * documentCentersUpdatedBy; }
	 * 
	 * public Set<ApplicationInvoice> getApplicationInvoicesSubmittedBy() {
	 * return applicationInvoicesSubmittedBy; }
	 * 
	 * public void setApplicationInvoicesSubmittedBy(Set<ApplicationInvoice>
	 * applicationInvoicesSubmittedBy) { this.applicationInvoicesSubmittedBy =
	 * applicationInvoicesSubmittedBy; }
	 * 
	 * public Set<ApplicationInvoice> getApplicationInvoicesUpdatedBy() { return
	 * applicationInvoicesUpdatedBy; }
	 * 
	 * public void setApplicationInvoicesUpdatedBy(Set<ApplicationInvoice>
	 * applicationInvoicesUpdatedBy) { this.applicationInvoicesUpdatedBy =
	 * applicationInvoicesUpdatedBy; }
	 */

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/*
	 * public Set<JobDetail> getJobDetailsSubmittedBy() { return
	 * jobDetailsSubmittedBy; }
	 * 
	 * public void setJobDetailsSubmittedBy(Set<JobDetail>
	 * jobDetailsSubmittedBy) { this.jobDetailsSubmittedBy =
	 * jobDetailsSubmittedBy; }
	 * 
	 * public Set<JobDetail> getJobDetailsUpdatedBy() { return
	 * jobDetailsUpdatedBy; }
	 * 
	 * public void setJobDetailsUpdatedBy(Set<JobDetail> jobDetailsUpdatedBy) {
	 * this.jobDetailsUpdatedBy = jobDetailsUpdatedBy; }
	 */

	public Set<JobDetail> getJobDetailsForeman() {
		return jobDetailsForeman;
	}

	public void setJobDetailsForeman(Set<JobDetail> jobDetailsForeman) {
		this.jobDetailsForeman = jobDetailsForeman;
	}

	public Set<JobDetail> getJobDetailsManager() {
		return jobDetailsManager;
	}

	public void setJobDetailsManager(Set<JobDetail> jobDetailsManager) {
		this.jobDetailsManager = jobDetailsManager;
	}

	public Set<JobDetail> getJobDetailsSupervisor() {
		return jobDetailsSupervisor;
	}

	public void setJobDetailsSupervisor(Set<JobDetail> jobDetailsSupervisor) {
		this.jobDetailsSupervisor = jobDetailsSupervisor;
	}

	public String getDecryptedPassword() {
		return decryptedPassword;
	}

	public void setDecryptedPassword(String decryptedPassword) {
		this.decryptedPassword = password;
	}

	/**
	 * @return the itemDbSubmittedBy
	 */
	/*
	 * public Set<ItemDB> getItemDbSubmittedBy() { return itemDbSubmittedBy; }
	 * 
	 *//**
		 * @param itemDbSubmittedBy
		 *            the itemDbSubmittedBy to set
		 */
	/*
	 * public void setItemDbSubmittedBy(Set<ItemDB> itemDbSubmittedBy) {
	 * this.itemDbSubmittedBy = itemDbSubmittedBy; }
	 * 
	 *//**
		 * @return the itemDbUpdatedBy
		 */

	/*
	 * public Set<ItemDB> getItemDbUpdatedBy() { return itemDbUpdatedBy; }
	 * 
	 *//**
		 * @param itemDbUpdatedBy
		 *            the itemDbUpdatedBy to set
		 *//*
		 * public void setItemDbUpdatedBy(Set<ItemDB> itemDbUpdatedBy) {
		 * this.itemDbUpdatedBy = itemDbUpdatedBy; }
		 */
	/*
	 * public Set<EmailLogDetails> getEmailLogDetailsSubmittedBy() { return
	 * emailLogDetailsSubmittedBy; }
	 * 
	 * public void setEmailLogDetailsSubmittedBy(Set<EmailLogDetails>
	 * emailLogDetailsSubmittedBy) { this.emailLogDetailsSubmittedBy =
	 * emailLogDetailsSubmittedBy; }
	 * 
	 * public Set<ModuleDirectory> getModuleDirectorySubmittedBy() { return
	 * moduleDirectorySubmittedBy; }
	 * 
	 * public void setModuleDirectorySubmittedBy(Set<ModuleDirectory>
	 * moduleDirectorySubmittedBy) { this.moduleDirectorySubmittedBy =
	 * moduleDirectorySubmittedBy; }
	 * 
	 * public Set<ModuleDirectory> getModuleDirectoryUpdatedBy() { return
	 * moduleDirectoryUpdatedBy; }
	 * 
	 * public void setModuleDirectoryUpdatedBy(Set<ModuleDirectory>
	 * moduleDirectoryUpdatedBy) { this.moduleDirectoryUpdatedBy =
	 * moduleDirectoryUpdatedBy; }
	 */

	/*
	 * public Set<SubModuleDirectory> getSubModuleDirectorySubmittedBy() {
	 * return subModuleDirectorySubmittedBy; }
	 * 
	 * public void setSubModuleDirectorySubmittedBy(Set<SubModuleDirectory>
	 * subModuleDirectorySubmittedBy) { this.subModuleDirectorySubmittedBy =
	 * subModuleDirectorySubmittedBy; }
	 * 
	 * public Set<SubModuleDirectory> getSubModuleDirectoryUpdatedBy() { return
	 * subModuleDirectoryUpdatedBy; }
	 * 
	 * public void setSubModuleDirectoryUpdatedBy(Set<SubModuleDirectory>
	 * subModuleDirectoryUpdatedBy) { this.subModuleDirectoryUpdatedBy =
	 * subModuleDirectoryUpdatedBy; }
	 */

	/*
	 * public Set<RoleViewMapping> getRoleViewMappingSubmittedBy() { return
	 * roleViewMappingSubmittedBy; }
	 * 
	 * public void setRoleViewMappingSubmittedBy(Set<RoleViewMapping>
	 * roleViewMappingSubmittedBy) { this.roleViewMappingSubmittedBy =
	 * roleViewMappingSubmittedBy; }
	 * 
	 * public Set<RoleViewMapping> getRoleViewMappingUpdatedBy() { return
	 * roleViewMappingUpdatedBy; }
	 * 
	 * public void setRoleViewMappingUpdatedBy(Set<RoleViewMapping>
	 * roleViewMappingUpdatedBy) { this.roleViewMappingUpdatedBy =
	 * roleViewMappingUpdatedBy; }
	 */

	/*
	 * public Set<TemporaryUserViewMapping> getTemporaryUserViewMappingUserId()
	 * { return temporaryUserViewMappingUserId; }
	 * 
	 * public void
	 * setTemporaryUserViewMappingUserId(Set<TemporaryUserViewMapping>
	 * temporaryUserViewMappingUserId) { this.temporaryUserViewMappingUserId =
	 * temporaryUserViewMappingUserId; }
	 * 
	 * public Set<TemporaryUserViewMapping>
	 * getTemporaryUserViewMappingSubmittedBy() { return
	 * temporaryUserViewMappingSubmittedBy; }
	 * 
	 * public void
	 * setTemporaryUserViewMappingSubmittedBy(Set<TemporaryUserViewMapping>
	 * temporaryUserViewMappingSubmittedBy) {
	 * this.temporaryUserViewMappingSubmittedBy =
	 * temporaryUserViewMappingSubmittedBy; }
	 * 
	 * public Set<TemporaryUserViewMapping>
	 * getTemporaryUserViewMappingUpdatedBy() { return
	 * temporaryUserViewMappingUpdatedBy; }
	 * 
	 * public void
	 * setTemporaryUserViewMappingUpdatedBy(Set<TemporaryUserViewMapping>
	 * temporaryUserViewMappingUpdatedBy) {
	 * this.temporaryUserViewMappingUpdatedBy =
	 * temporaryUserViewMappingUpdatedBy; }
	 */
	/*
	 * public Set<DepartmentUserMapping> getDepartmentUserMappingSubmittedBy() {
	 * return departmentUserMappingSubmittedBy; }
	 * 
	 * public void
	 * setDepartmentUserMappingSubmittedBy(Set<DepartmentUserMapping>
	 * departmentUserMappingSubmittedBy) { this.departmentUserMappingSubmittedBy
	 * = departmentUserMappingSubmittedBy; }
	 * 
	 * public Set<DepartmentUserMapping> getDepartmentUserMappingUpdatedBy() {
	 * return departmentUserMappingUpdatedBy; }
	 * 
	 * public void setDepartmentUserMappingUpdatedBy(Set<DepartmentUserMapping>
	 * departmentUserMappingUpdatedBy) { this.departmentUserMappingUpdatedBy =
	 * departmentUserMappingUpdatedBy; }
	 */

	public Set<DepartmentUserMapping> getDepartmentUserMappingUserDetails() {
		return departmentUserMappingUserDetails;
	}

	public void setDepartmentUserMappingUserDetails(Set<DepartmentUserMapping> departmentUserMappingUserDetails) {
		this.departmentUserMappingUserDetails = departmentUserMappingUserDetails;
	}

	/*
	 * public Set<UnitAbbreviationDirectory>
	 * getUnitAbbreviationDirectorySubmittedBy() { return
	 * unitAbbreviationDirectorySubmittedBy; }
	 * 
	 * public void
	 * setUnitAbbreviationDirectorySubmittedBy(Set<UnitAbbreviationDirectory>
	 * unitAbbreviationDirectorySubmittedBy) {
	 * this.unitAbbreviationDirectorySubmittedBy =
	 * unitAbbreviationDirectorySubmittedBy; }
	 * 
	 * public Set<UnitAbbreviationDirectory>
	 * getUnitAbbreviationDirectoryUpdatedBy() { return
	 * unitAbbreviationDirectoryUpdatedBy; }
	 * 
	 * public void
	 * setUnitAbbreviationDirectoryUpdatedBy(Set<UnitAbbreviationDirectory>
	 * unitAbbreviationDirectoryUpdatedBy) {
	 * this.unitAbbreviationDirectoryUpdatedBy =
	 * unitAbbreviationDirectoryUpdatedBy; }
	 */

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsDeptHead() {
		return isDeptHead;
	}

	public void setIsDeptHead(Boolean isDeptHead) {
		this.isDeptHead = isDeptHead;
	}

	public OrganizationDirectory getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(OrganizationDirectory orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public DepartmentDirectory getDeptUnitId() {
		return deptUnitId;
	}

	public void setDeptUnitId(DepartmentDirectory deptUnitId) {
		this.deptUnitId = deptUnitId;
	}

	public DesignationDirectory getDesigId() {
		return desigId;
	}

	public void setDesigId(DesignationDirectory desigId) {
		this.desigId = desigId;
	}

	public UserDetail getSuperviserId() {
		return superviserId;
	}

	public void setSuperviserId(UserDetail superviserId) {
		this.superviserId = superviserId;
	}

	/**
	 * @return the orgTransientId
	 */
	public Long getOrgTransientId() {
		return orgTransientId;
	}

	/**
	 * @param orgTransientId
	 *            the orgTransientId to set
	 */
	public void setOrgTransientId() {
		this.orgTransientId = orgUnitId.getOrganizationId();
	}

	/**
	 * @return the deptTransientId
	 */
	public Integer getDeptTransientId() {
		return deptTransientId;
	}

	/**
	 * @param deptTransientId
	 *            the deptTransientId to set
	 */
	public void setDeptTransientId() {
		this.deptTransientId = deptUnitId.getDepartmentId();
	}

	/**
	 * @return the desigTransientId
	 */
	public Integer getDesigTransientId() {
		return desigTransientId;
	}

	/**
	 * @param desigTransientId
	 *            the desigTransientId to set
	 */
	public void setDesigTransientId() {
		this.desigTransientId = desigId.getDesignationId();
	}

	public Long getSuperviserIdExpose() {
		return superviserIdExpose;
	}

	public void setSuperviserIdExpose(Long superviserIdExpose) {
		this.superviserIdExpose = superviserIdExpose;
	}

	public String getSuperviserFirstNameExpose() {
		return superviserFirstNameExpose;
	}

	public void setSuperviserFirstNameExpose(String superviserFirstNameExpose) {
		this.superviserFirstNameExpose = superviserFirstNameExpose;
	}

	public String getSuperviserEmailIdExpose() {
		return superviserEmailIdExpose;
	}

	public void setSuperviserEmailIdExpose(String superviserEmailIdExpose) {
		this.superviserEmailIdExpose = superviserEmailIdExpose;
	}

}