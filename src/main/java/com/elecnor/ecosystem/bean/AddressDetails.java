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

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ADDRESS_DETAILS")
public class AddressDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_DETAILS_ID")
	private Long addressId;

	@Expose
	@Column(name = "ADDRESS_LINE_1")
	private String address_line_1;

	@Expose
	@Column(name = "ADDRESS_LINE_2")
	private String address_line_2;

	@Expose
	@Column(name = "STATE")
	private String state;

	@Expose
	@Column(name = "CITY")
	private String city;

	@Expose
	@Column(name = "ZIP_CODE")
	private String zipCode;

	@Expose
	@Column(name = "ADDRESS_TYPE")
	private String address_type;

	@Expose
	@Column(name = "STATUS")
	private String status;

	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "MODULE_ID")
	@Expose
	private Long moduleId;

	@Column(name = "MODULE_NAME")
	@Expose
	private String moduleName;

	// -----------------------------------------------------Getter Setter-----------------------------------------------------

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddress_line_1() {
		return address_line_1;
	}

	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress_type() {
		return address_type;
	}

	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserDetail getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}



 

}
