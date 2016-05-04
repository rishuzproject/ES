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
@Table(name = "UNIT_ABBREVIATION_DIRECTORY")
public class UnitAbbreviationDirectory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	@Column(name = "ABBREVIATION_ID")
	private Long abbreviationId;

	@Column(name = "ABBREVIATION_NAME")
	@Expose
	private String abbreviationName;
	
	@Column(name="STATUS")
	@Expose
	private String status;
	
	@Column(name = "SERIAL_NO")
	@Expose
	private Integer serialNo;

	@Column(name = "ABBREVIATION_MEANING")
	@Expose
	private String abbreviationMeaning;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainId;

	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	
	private UserDetail submittedBy;
	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;
	
	public UnitAbbreviationDirectory(){
		
	}

	public Long getAbbreviationId() {
		return abbreviationId;
	}

	public void setAbbreviationId(Long abbreviationId) {
		this.abbreviationId = abbreviationId;
	}

	public String getAbbreviationName() {
		return abbreviationName;
	}

	public void setAbbreviationName(String abbreviationName) {
		this.abbreviationName = abbreviationName;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getAbbreviationMeaning() {
		return abbreviationMeaning;
	}

	public void setAbbreviationMeaning(String abbreviationMeaning) {
		this.abbreviationMeaning = abbreviationMeaning;
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

	public DomainDetail getDomainId() {
		return domainId;
	}

	public void setDomainId(DomainDetail domainId) {
		this.domainId = domainId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
