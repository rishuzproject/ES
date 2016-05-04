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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the customer_directory database table.
 * 
 * @Ankur Version 1.0
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */
@Entity
@Table(name = "CUSTOMER_DIRECTORY")
public class CustomerDirectory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMPANY_ID")
	@Expose
	private Long companyId;

	@Expose
	@Column(name = "BUSINESS_TYPE")
	private String businessType;

	@Expose
	@Column(name = "COMPANY_EMAIL")
	/*@NotBlank(message = "Company Email is a Mandatory Field")
	@Email
	@Length(max = 50, message = "Company Email should have a maximum of 50 characters")*/
	@Email
	private String companyEmail;

	@Expose
	@Column(name = "COMPANY_NAME")
	@NotBlank(message = "Company Name is a Mandatory Field")
	@Length(max = 100, message = "Company Name should have a maximum of 100 characters")
	private String companyName;

	/*@Expose
	@Column(name = "CORPORATE_OFFICE_ADDRESS")
	@NotBlank(message = "Corporate Office Address is a Mandatory Field")
	@Length(max = 200, message = "Corporate Office Address should have a maximum of 200 characters")
	private String corporateOfficeAddress;*/

	@Expose
	@Column(name = "FAX")
	private String fax;

	@Expose
	@Column(name = "IRS")
	private String irs;

	@Expose
	@Column(name = "PHONE_NUMBER")
	@NotBlank(message = "Company Phone No is a Mandatory Field")
	@Pattern(regexp = "(^$|[0-9]*)", message = "Phone Number should contain only digits")
	@Length(max = 15, message = "Company Phone No should have a maximum of 15 characters")
	private String phoneNumber;

	/*@Expose
	@Column(name = "REPRESENTATIVE_ADDRESS")
	private String representativeAddress;*/

	@Expose
	@Column(name = "REPRESENTATIVE_NAME")
	@NotBlank(message = "Representative Name is a Mandatory Field")
	@Length(max = 100, message = "Representative Name should have a maximum of 100 characters")
	private String representativeName;

	@Expose
	@Column(name = "REPRESENTATIVE_PHONE")
	/*@NotBlank(message = "Representative Phone No is a Mandatory Field")*/
	/*@Pattern(regexp = "(^$|[0-9]*)", message = "Phone Number should contain only digits")
	@Length(max = 15, message = "Representative Phone No should have a maximum of 15 characters")*/
	private String representativePhone;

	@Expose
	@Column(name = "STATUS")
	private String status;

	@Expose
	@Column(name = "CUSTOMER_NUMBER")
	/*@Length(max = 10, message = "Company Number should have a maximum of 10 characters")*/
	private String companyNumber;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	/*// bi-directional many-to-one association to JobDetail
	@OneToMany(mappedBy = "customerDirectory")
	private Set<JobDetail> jobDetails;*/

	// Uni-directional many-to-one association to UserDetail
	//@Expose
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	
//	@Formula("USER_ID")
//	@Expose
//	private Long submittedBy_expose;
//	
//	@Expose
//	@Formula("SUBMITTED_DATE")
//	private Long submittedDate_expose;
	

	// Uni-directional many-to-one association to UserDetail
	//@Expose
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	// Uni-directional many-to-one association to UserDetail
	//@Expose
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	public CustomerDirectory() {
		super();
	}

	/**
	 * @param companyId
	 * @param companyName
	 */
	public CustomerDirectory(Long companyId, String companyName) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCompanyEmail() {
		return this.companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/*public String getCorporateOfficeAddress() {
		return this.corporateOfficeAddress;
	}

	public void setCorporateOfficeAddress(String corporateOfficeAddress) {
		this.corporateOfficeAddress = corporateOfficeAddress;
	}*/

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIrs() {
		return this.irs;
	}

	public void setIrs(String irs) {
		this.irs = irs;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/*public String getRepresentativeAddress() {
		return this.representativeAddress;
	}

	public void setRepresentativeAddress(String representativeAddress) {
		this.representativeAddress = representativeAddress;
	}*/

	public String getRepresentativeName() {
		return this.representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getRepresentativePhone() {
		return this.representativePhone;
	}

	public void setRepresentativePhone(String representativePhone) {
		this.representativePhone = representativePhone;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
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

	/**
	 * @return the domainDetail
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	/**
	 * @param domainDetail
	 *            the domainDetail to set
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

//	public Long getSubmittedBy_expose() {
//		return submittedBy_expose;
//	}
//
//	public void setSubmittedBy_expose(Long submittedBy_expose) {
//		this.submittedBy_expose = submittedBy_expose;
//	}
//
//	public Long getSubmittedDate_expose() {
//		return submittedDate_expose;
//	}
//
//	public void setSubmittedDate_expose(Long submittedDate_expose) {
//		this.submittedDate_expose = submittedDate_expose;
//	}

	/*public Set<JobDetail> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(Set<JobDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}*/

//	public static void main(String[] args) {
//
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		Validator validator = factory.getValidator();
//		CustomerDirectory car = new CustomerDirectory();
//		car.setPhoneNumber("11");
//		Set<ConstraintViolation<CustomerDirectory>> constraintViolations = validator.validate(car);
//		for (Iterator<ConstraintViolation<CustomerDirectory>> iterator = constraintViolations.iterator(); iterator
//				.hasNext();) {
//			ConstraintViolation<CustomerDirectory> constraintViolation = iterator.next();
//			System.out.println(constraintViolation.getMessage());
//		}
//	}
}