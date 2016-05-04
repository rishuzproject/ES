package com.elecnor.ecosystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOB_UPLOAD_DOCUMENT")
public class JobUploadDocument {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JOB_UPLOAD_DOC_ID")
	private Integer id;

	@Column(name = "ASSOCIATED_FIELD")
	private String associatedField;

	@Column(name = "APPLICATION_UPLOAD_FILE_ID")
	private String appUploadFileId;
	
	@Column(name = "JOB_ID")
	private Integer jobId;

 
	

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getAssociatedField() {
		return associatedField;
	}

	public void setAssociatedField(String associatedField) {
		this.associatedField = associatedField;
	}

	public String getAppUploadFileId() {
		return appUploadFileId;
	}

	public void setAppUploadFileId(String appUploadFileId) {
		this.appUploadFileId = appUploadFileId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
