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

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the license_directory database table.
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */
@Entity
@Table(name = "LICENSE_DIRECTORY")
public class LicenseDirectory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LICENSE_ID", unique = true, nullable = false)
	private Integer licenseId;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Expose
	@Column(name = "LICENSE_NUMBER", length = 100)
	private String licenseNumber;

	@Expose
	@Column(name = "LOCAL_JURISDICTION", length = 100)
	private String localJurisdiction;
    
	@Expose
	@Column(name = "PRIMARY_PERSON", length = 100)
	private String primaryPerson;

	@Expose
	@Column(name = "STATE", length = 100)
	private Integer state;
    
	@Expose
	@Column(name="STATUS")
	private String status;

	@Expose
	@Column(name = "TYPE", length = 50)
	private String type;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail licenseSubmittedBy;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail licenseUpdatedBy;
    
	@Expose
	@Column(name="LICENSE_DESCRIPTION")
	private String licenseDescription;

	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;

	/**
	 * @return the licenseId
	 */
	public Integer getLicenseId() {
		return licenseId;
	}

	/**
	 * @param licenseId the licenseId to set
	 */
	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the licenseNumber
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/**
	 * @param licenseNumber the licenseNumber to set
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	/**
	 * @return the localJurisdiction
	 */
	public String getLocalJurisdiction() {
		return localJurisdiction;
	}

	/**
	 * @param localJurisdiction the localJurisdiction to set
	 */
	public void setLocalJurisdiction(String localJurisdiction) {
		this.localJurisdiction = localJurisdiction;
	}

	/**
	 * @return the primaryPerson
	 */
	public String getPrimaryPerson() {
		return primaryPerson;
	}

	/**
	 * @param primaryPerson the primaryPerson to set
	 */
	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the licenseSubmittedBy
	 */
	public UserDetail getLicenseSubmittedBy() {
		return licenseSubmittedBy;
	}

	/**
	 * @param licenseSubmittedBy the licenseSubmittedBy to set
	 */
	public void setLicenseSubmittedBy(UserDetail licenseSubmittedBy) {
		this.licenseSubmittedBy = licenseSubmittedBy;
	}

	/**
	 * @return the licenseUpdatedBy
	 */
	public UserDetail getLicenseUpdatedBy() {
		return licenseUpdatedBy;
	}

	/**
	 * @param licenseUpdatedBy the licenseUpdatedBy to set
	 */
	public void setLicenseUpdatedBy(UserDetail licenseUpdatedBy) {
		this.licenseUpdatedBy = licenseUpdatedBy;
	}

	/**
	 * @return the licenseDescription
	 */
	public String getLicenseDescription() {
		return licenseDescription;
	}

	/**
	 * @param licenseDescription the licenseDescription to set
	 */
	public void setLicenseDescription(String licenseDescription) {
		this.licenseDescription = licenseDescription;
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

}