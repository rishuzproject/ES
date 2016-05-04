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

/**
 * The persistent class for the document_center database table.
 * 
 * @Ankur Version 1.0
 */
@Entity
@Table(name = "DOCUMENT_CENTER")
public class DocumentCenter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOCUMENT_CENTER_ID")
	@Expose
	private Long documentCenterId;

	@Expose
	@Column(name = "DOCUMENT_NAME")
	private String documentName;
	
	@Expose
	@Column(name = "DOCUMENT_DESCRIPTION")
	private String documentDescription;
	
	@Column(name = "DOCUMENT_TYPE")
	private String documentType;
	
	@Column(name = "STATUS")
	private String status;
	
	// Uni-directional many-to-one association to DomainDetail
	@Expose
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;
	
	// Uni-directional many-to-one association to UserDetail
	@Expose
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;
		
	@Expose
	@Column(name = "FILE_ID")
	private int fileId;
	
	// Removed these columns from DB, By Vaibhav.
	/*@Column(name = "UPLOADED_DOCUMENT_CONTENT")
	private byte[] uploadedDocumentContent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;*/

	public DocumentCenter() {
	}

	/**
	 * @return the documentCenterId
	 */
	public Long getDocumentCenterId() {
		return documentCenterId;
	}

	/**
	 * @param documentCenterId the documentCenterId to set
	 */
	public void setDocumentCenterId(Long documentCenterId) {
		this.documentCenterId = documentCenterId;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * @return the documentDescription
	 */
	public String getDocumentDescription() {
		return documentDescription;
	}

	/**
	 * @param documentDescription the documentDescription to set
	 */
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	
}