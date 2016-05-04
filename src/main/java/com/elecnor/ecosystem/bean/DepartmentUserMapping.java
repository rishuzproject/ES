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

@Entity
@Table(name = "DEPARTMENT_USER_MAPPING")
public class DepartmentUserMapping implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MAPPING_ID")
	private Long mappingId;
	
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private DepartmentType departmentId;
	
	
	@ManyToOne
	@Expose
	@JoinColumn(name = "USER_ID")
	private UserDetail userId;
	
	
	@Expose
	@Column(name = "USER_TYPE")
	private String userType;
	
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	
	
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;
	
	@Expose
	@DateTimeFormat(pattern = "MM/dd/yyyy h:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SUBMITTED_DATE")
	private Date submittedDate;
	
	
	@Expose
	@DateTimeFormat(pattern = "MM/dd/yyyy h:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail; 
	
	public Long getMappingId() {
		return mappingId;
	}


	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}


	public DepartmentType getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(DepartmentType departmentId) {
		this.departmentId = departmentId;
	}


	public UserDetail getUserId() {
		return userId;
	}


	public void setUserId(UserDetail userId) {
		this.userId = userId;
	}


	

	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
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
	
	
	
	

}
