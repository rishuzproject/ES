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
@Table(name = "COUPON_DETAILS")
public class CouponDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUPON_ID")
	private Long couponId;
	
	@Expose
	@Column(name = "COUPON_CODE")
	private String couponCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@Column(name = "VALIDITY")
	@Expose
	private Date validity;
	
	@Expose
	@Column(name = "DISCOUNT_AMOUNT")
	private Double discountAmount;
	
	@Expose
	@Column(name = "ADDITIONAL_USER")
	private Integer additionalUser;
	
	@Expose
	@Column(name = "ADDITIONAL_SPACE")
	private Double additionalSpace;
	
	@Expose
	@Column(name = "IS_ONE_TIME_APPLICABLE")
	private Boolean isOneTimeApplicable;
	
	@Expose
	@Column(name = "IS_USED")
	private Boolean isUsed;
	
	@Expose
	@Column(name = "STATUS")
	private String status;
	
	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "USED_BY")
	//@Expose
	private UserDetail usedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;
	
	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "SUBMITTED_BY")
	@Expose
	private UserDetail submittedBy;
	
	// Uni-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	@Expose
	private UserDetail updatedBy;
	/**
	 * @return the couponId
	 */
	public Long getCouponId() {
		return couponId;
	}
	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	/**
	 * @return the couponCode
	 */
	public String getCouponCode() {
		return couponCode;
	}
	/**
	 * @param couponCode the couponCode to set
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	/**
	 * @return the validity
	 */
	public Date getValidity() {
		return validity;
	}
	/**
	 * @param validity the validity to set
	 */
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	/**
	 * @return the discountAmount
	 */
	public Double getDiscountAmount() {
		return discountAmount;
	}
	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	/**
	 * @return the additionalSpace
	 */
	public Double getAdditionalSpace() {
		return additionalSpace;
	}
	/**
	 * @param additionalSpace the additionalSpace to set
	 */
	public void setAdditionalSpace(Double additionalSpace) {
		this.additionalSpace = additionalSpace;
	}
	public Integer getAdditionalUser() {
		return additionalUser;
	}
	public void setAdditionalUser(Integer additionalUser) {
		this.additionalUser = additionalUser;
	}
	/**
	 * @return the isOneTimeApplicable
	 */
	public Boolean getIsOneTimeApplicable() {
		return isOneTimeApplicable;
	}
	/**
	 * @param isOneTimeApplicable the isOneTimeApplicable to set
	 */
	public void setIsOneTimeApplicable(Boolean isOneTimeApplicable) {
		this.isOneTimeApplicable = isOneTimeApplicable;
	}
	/**
	 * @return the isUsed
	 */
	public Boolean getIsUsed() {
		return isUsed;
	}
	/**
	 * @param isUsed the isUsed to set
	 */
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	/**
	 * @return the usedBy
	 */
	public UserDetail getUsedBy() {
		return usedBy;
	}
	/**
	 * @param usedBy the usedBy to set
	 */
	public void setUsedBy(UserDetail usedBy) {
		this.usedBy = usedBy;
	}
	/**
	 * @return the submittedDate
	 */
	public Date getSubmittedDate() {
		return submittedDate;
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

}
