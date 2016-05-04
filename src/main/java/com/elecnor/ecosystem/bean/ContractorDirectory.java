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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

/**
 * @author Vaibhav
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */

@Entity
@Table(name = "CONTRACTOR_DIRECTORY")
public class ContractorDirectory implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTRACTOR_ID")
	@Expose
	private Long contractorId;

	@Expose
	@NotBlank(message = "Contractor Name is a Mandatory Field")
	@Column(name = "CONTRACTOR_NAME")
	@Size(max=50)
	private String contractorName;

	/*@Expose
	@NotBlank(message = "Office address is a Mandatory Field")
	@Column(name = "CORPORATE_OFFICE_ADDRESS")
	@Size(max=200)
	private String corporateOfficeAddress;*/

	@Expose
	@NotBlank(message = "Contractor email is a Mandatory Field")
	@Column(name = "CONTRACTOR_EMAIL")
	@Size(max=50)
	private String contractorEmail;

	@Expose
	@NotBlank(message = "Phone Number is a Mandatory Field")
	@Column(name = "PHONE_NUMBER")
	@Size(max=15)
	private String phoneNumber;

	@Expose
	@Column(name = "FAX")
	private String fax;

	/*@Expose
	@Column(name = "REPRESENTATIVE_ADDRESS")
	private String representativeAddress;*/

	@Expose
	@NotBlank(message = "Representative name is a Mandatory Field")
	@Column(name = "REPRESENTATIVE_NAME")
	@Size(max=100)
	private String representativeName;

	@Expose
	@NotBlank(message = "Representative phone is a Mandatory Field")
	@Column(name = "REPRESENTATIVE_PHONE")
	@Size(max=15)
	private String representativePhone;

	@Expose
	@Column(name = "BUSINESS_TYPE")
	private String businessType;

	// Uni-directional many-to-one association to UserDetail
	//@Expose
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// Uni-directional many-to-one association to UserDetail
	//@Expose
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// Uni-directional many-to-one association to UserDetail
	//@Expose
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	//@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	//@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Expose
	@Column(name = "STATUS")
	private String status;

	@Expose
	@NotBlank(message="Contractor number must not be blank")
	@Size(max=10)
	@Column(name = "CONTRACTOR_NUMBER")
	private String contractorNumber;

	public ContractorDirectory() {
		super();
	}
	
	/**
	 * @param contractorId
	 * @param contractorName
	 */
	public ContractorDirectory(Long contractorId, String contractorName) {
		super();
		this.contractorId = contractorId;
		this.contractorName = contractorName;
	}
	
	/**
	 * @return the contractorId
	 */
	public Long getContractorId() {
		return contractorId;
	}

	/**
	 * @param contractorId the contractorId to set
	 */
	public void setContractorId(Long contractorId) {
		this.contractorId = contractorId;
	}

	/**
	 * @return the contractorName
	 */
	public String getContractorName() {
		return contractorName;
	}

	/**
	 * @param contractorName the contractorName to set
	 */
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	/**
	 * @return the corporateOfficeAddress
	 */
	/*public String getCorporateOfficeAddress() {
		return corporateOfficeAddress;
	}

	*//**
	 * @param corporateOfficeAddress the corporateOfficeAddress to set
	 *//*
	public void setCorporateOfficeAddress(String corporateOfficeAddress) {
		this.corporateOfficeAddress = corporateOfficeAddress;
	}*/

	/**
	 * @return the contractorEmail
	 */
	public String getContractorEmail() {
		return contractorEmail;
	}

	/**
	 * @param contractorEmail the contractorEmail to set
	 */
	public void setContractorEmail(String contractorEmail) {
		this.contractorEmail = contractorEmail;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the representativeAddress
	 */
	/*public String getRepresentativeAddress() {
		return representativeAddress;
	}

	*//**
	 * @param representativeAddress the representativeAddress to set
	 *//*
	public void setRepresentativeAddress(String representativeAddress) {
		this.representativeAddress = representativeAddress;
	}*/

	/**
	 * @return the representativeName
	 */
	public String getRepresentativeName() {
		return representativeName;
	}

	/**
	 * @param representativeName the representativeName to set
	 */
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	/**
	 * @return the representativePhone
	 */
	public String getRepresentativePhone() {
		return representativePhone;
	}

	/**
	 * @param representativePhone the representativePhone to set
	 */
	public void setRepresentativePhone(String representativePhone) {
		this.representativePhone = representativePhone;
	}

	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * @return the domainDetail
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	/**
	 * @param domainDetail the domainDetail to set
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}

	/**
	 * @return the submittedBy
	 */
	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @param submittedBy the submittedBy to set
	 */
	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @return the updatedBy
	 */
	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate the submittedDate to set
	 */
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the contractorNumber
	 */
	public String getContractorNumber() {
		return contractorNumber;
	}

	/**
	 * @param contractorNumber the contractorNumber to set
	 */
	public void setContractorNumber(String contractorNumber) {
		this.contractorNumber = contractorNumber;
	}
	
	/*@OneToMany(mappedBy = "contractorDirectory")
	private Set<JobDetail> jobDetails;
	
	public Set<JobDetail> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(Set<JobDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}*/

	

}
