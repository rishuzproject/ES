package com.elecnor.ecosystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="REPRESENTATIVE_DIRECTORY")
public class RepresentativeDirectory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REPRESENTATIVE_ID")
	@Expose
	private Integer id;

	@Column(name = "NAME")
	@Expose
	private String name;

	@Column(name = "PHONE")
	@Expose
	private Integer phone;
	
	@Column(name = "EMAIL_ID")
	@Expose
	private String emailId;
	
//	@ManyToOne
//	@JoinColumn(name = "COMPANY_ID")
//	Set<CustomerDirectory> customerId;
//	
//	@ManyToOne
//	@JoinColumn(name = "CONTRACTOR_ID")
//	Set<CustomerDirectory> contractorId;
//	
//	@ManyToOne
//	@JoinColumn(name = "VENDOR_ID")
//	Set<CustomerDirectory> vendorId;

	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
//
//	public Set<CustomerDirectory> getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(Set<CustomerDirectory> customerId) {
//		this.customerId = customerId;
//	}
//
//	public Set<CustomerDirectory> getContractorId() {
//		return contractorId;
//	}
//
//	public void setContractorId(Set<CustomerDirectory> contractorId) {
//		this.contractorId = contractorId;
//	}
//
//	public Set<CustomerDirectory> getVendorId() {
//		return vendorId;
//	}
//
//	public void setVendorId(Set<CustomerDirectory> vendorId) {
//		this.vendorId = vendorId;
//	}
//
//	
	
	
	
}
