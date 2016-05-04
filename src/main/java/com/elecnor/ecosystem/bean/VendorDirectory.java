package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 *
 */
@Entity
@Table(name = "VENDOR_DIRECTORY")
public class VendorDirectory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VENDOR_ID")
	@Expose
	private Long vendorId;

	@Column(name = "VENDOR_NAME")
	@Expose
	@NotBlank(message = "Vendor Name is a Mandatory Field")
	private String vendorName;

	@Column(name = "VENDOR_LEGAL_NAME")
	@Expose
	@NotBlank(message = "Vendor Legal Name is a Mandatory Field")
	private String vendorLegalName;

	@Column(name = "VENDOR_LEGAL_STATUS")
	@Expose
	@NotBlank(message = "Vendor Legal Status is a Mandatory Field")
	private String vendorLegalStatus;

	@Column(name = "OWNERSHIP")
	@Expose
	@NotBlank(message = "Vendor Ownership is a Mandatory Field")
	private String vendorOwnership;

	/*@Column(name = "VENDOR_ADDRESS")
	@Expose
	@NotBlank(message = "Vendor Address is a Mandatory Field")
	private String vendorAddress;*/

	/*@Column(name = "VENDOR_MAILING_ADDRESS")
	@Expose
	//@Pattern(regexp="/^(?=.*[a-z])/i", message="not valid")
	private String vendorMailingAddress;*/

	@Column(name = "VENDOR_PHONE_NO")
	@Expose
	@NotBlank(message = "Vendor Phone No is a Mandatory Field")
	@Pattern(regexp = "(^$|[0-9]*)", message = "Phone Number should contain only digits")
	@Length(max = 15, message = "Vendor Phone No should have a maximum of 15 characters")
	private String vendorPhoneNo;

	@Column(name = "VENDOR_FAX")
	@Expose
	@Pattern(regexp = "(^$|[0-9]*)", message = "Phone Number should contain only digits")
	private String vendorFax;

	@Column(name = "VENDOR_EMAIL")
	@Expose
	@NotBlank(message = "Vendor Email is a Mandatory Field")
	@Email(message = "Email Format is incorrect")
	private String vendorEmail;

	@Column(name = "VENDOR_WEBSITE")
	@Expose
	@NotBlank(message = "Vendor Website is a Mandatory Field")
	//@URL(message = "Website Format is incorrect")
	private String vendorWebsite;

	@Column(name = "STATUS")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// Uni-directional many-to-one association to DomainDetail
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	/*@OneToMany(mappedBy = "vendorList")
	private Set<JobDetail> jobDetails;*/

	/*public Set<JobDetail> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(Set<JobDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*@Expose
	@Column(name = "REPRESENTATIVE_ADDRESS")
	private String representativeAddress;*/

	@Expose
	@Column(name = "REPRESENTATIVE_NAME")
	private String representativeName;

	@Expose
	@Column(name = "REPRESENTATIVE_PHONE")
	//@Digits(fraction = 0, integer = 15, message ="Vendor Phone No should contain only digits")
	private String representativePhone;

	@Expose
	@Column(name = "IRS")
	private String irs;

	@Expose
	@Column(name = "BUSINESS_TYPE")
	private String businessType;

	public VendorDirectory() {
	}

	/**
	 * @param vendorId
	 * @param vendorName
	 */
	public VendorDirectory(Long vendorId, String vendorName) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorLegalName() {
		return vendorLegalName;
	}

	public void setVendorLegalName(String vendorLegalName) {
		this.vendorLegalName = vendorLegalName;
	}

	public String getVendorLegalStatus() {
		return vendorLegalStatus;
	}

	public void setVendorLegalStatus(String vendorLegalStatus) {
		this.vendorLegalStatus = vendorLegalStatus;
	}

	public String getVendorOwnership() {
		return vendorOwnership;
	}

	public void setVendorOwnership(String ownership) {
		this.vendorOwnership = ownership;
	}

	/*public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}*/

	/*public String getVendorMailingAddress() {
		return vendorMailingAddress;
	}

	public void setVendorMailingAddress(String vendorMailingAddress) {
		this.vendorMailingAddress = vendorMailingAddress;
	}*/

	public String getVendorPhoneNo() {
		return vendorPhoneNo;
	}

	public void setVendorPhoneNo(String vendorPhoneNo) {
		this.vendorPhoneNo = vendorPhoneNo;
	}

	public String getVendorFax() {
		return vendorFax;
	}

	public void setVendorFax(String vendorFax) {
		this.vendorFax = vendorFax;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getVendorWebsite() {
		return vendorWebsite;
	}

	public void setVendorWebsite(String vendorWebsite) {
		this.vendorWebsite = vendorWebsite;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
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

	/*public String getRepresentativeAddress() {
		return representativeAddress;
	}

	public void setRepresentativeAddress(String representativeAddress) {
		this.representativeAddress = representativeAddress;
	}
*/
	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getRepresentativePhone() {
		return representativePhone;
	}

	public void setRepresentativePhone(String representativePhone) {
		this.representativePhone = representativePhone;
	}

	public String getIrs() {
		return irs;
	}

	public void setIrs(String irs) {
		this.irs = irs;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public static void main(String[] args) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		VendorDirectory car = new VendorDirectory();
		/*car.setVendorMailingAddress("a11");*/
		Set<ConstraintViolation<VendorDirectory>> constraintViolations = validator.validate(car);
		for (Iterator<ConstraintViolation<VendorDirectory>> iterator = constraintViolations.iterator(); iterator.hasNext();) {
			ConstraintViolation<VendorDirectory> constraintViolation = iterator.next();
			System.out.println(constraintViolation.getMessage());
		}
	}

}
