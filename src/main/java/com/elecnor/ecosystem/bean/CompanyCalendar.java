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
 * 
 * @author Vaibhav
 * @Vaibhav
 * Modifing Bi-Direction Mappings and Expose for the unwanted attributes.
 */
@Entity
@Table(name = "COMPANY_CALENDAR")
public class CompanyCalendar implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EVENT_ID")
	@Expose
	private Long eventId;
	
	@Expose
	@Column(name = "EVENT_TYPE")
	private String eventType;
	
	@Expose
	@Column(name = "CALENDAR_EVENT_TYPE")
	private String calendarEventType;
	
	@Expose
	@Column(name = "EVENT_COLOR_CODE")
	private String eventColorCode;
	
	@Expose
	@Column(name = "EVENT_TITLE")
	private String eventTitle;
	
	@Expose
	@Column(name = "EVENT_DESCRIPTION")
	private String eventDescription;
	
	@Expose
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "FROM_DATE")
	private Date fromDate;

	@Expose
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "TO_DATE")
	private Date toDate;
	
	// bi-directional many-to-one association to DomainDetail
	@ManyToOne
	//@Expose
	@JoinColumn(name = "DOMAIN_ID")
	private DomainDetail domainDetail;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@Expose
	@JoinColumn(name = "SUBMITTED_BY")
	private UserDetail submittedBy;
	
	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDetail updatedBy;
	
	/**
	 * 
	 */
	public CompanyCalendar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param eventId
	 */
	public CompanyCalendar(Long eventId) {
		super();
		this.eventId = eventId;
	}

	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventColorCode
	 */
	public String getEventColorCode() {
		return eventColorCode;
	}

	/**
	 * @param eventColorCode the eventColorCode to set
	 */
	public void setEventColorCode(String eventColorCode) {
		this.eventColorCode = eventColorCode;
	}

	/**
	 * @return the eventTitle
	 */
	public String getEventTitle() {
		return eventTitle;
	}

	/**
	 * @param eventTitle the eventTitle to set
	 */
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	/**
	 * @return the eventDescription
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * @param eventDescription the eventDescription to set
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	 * @return the updatedBy
	 */
	public UserDetail getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(UserDetail updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCalendarEventType() {
		return calendarEventType;
	}

	public void setCalendarEventType(String calendarEventType) {
		this.calendarEventType = calendarEventType;
	}
	
	
}
