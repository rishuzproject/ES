/*  @author A.MeghanaJagruthi*/
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

import com.file.handler.bean.FileUploadBean;
import com.google.gson.annotations.Expose;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */
@Entity
@Table(name = "RFC_TAKEOFF_SHEET")
public class RfcTakeoffSheet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FILEID")
	@Expose
	private Integer fileId;
	
	
	/*@Column(name = "FILENAME")
	@Expose
	private String fileName;
	
	@Column(name = "CONTENTS")
	private byte[] contents;*/
	
	@ManyToOne
	@JoinColumn(name = "RFC_LOG_SNO") //many to one relation
	@Expose
	private RfcLog rfcLogSno;
	
	@ManyToOne
	@JoinColumn(name = "SUBMITTEDBY")
	//@Expose
	private UserDetail submittedBy;
	
	
	@Column(name="DOCUMENT_ID")
	private Integer associatedDocument;
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private JobDetail associatedJob;
	
	/*public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getContents() {
		return contents;
	}
	public void setContents(byte[] contents) {
		this.contents = contents;
	}*/
	public RfcLog getRfcLogSno() {
		return rfcLogSno;
	}
	public void setRfcLogSno(RfcLog rfcLogSno) {
		this.rfcLogSno = rfcLogSno;
	}
	public UserDetail getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(UserDetail submittedBy) {
		this.submittedBy = submittedBy;
	}
	/*public Date getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}*/
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public Integer getAssociatedDocument() {
		return associatedDocument;
	}
	public void setAssociatedDocument(Integer associatedDocument) {
		this.associatedDocument = associatedDocument;
	}
	public JobDetail getAssociatedJob() {
		return associatedJob;
	}
	public void setAssociatedJob(JobDetail associatedJob) {
		this.associatedJob = associatedJob;
	}
}
