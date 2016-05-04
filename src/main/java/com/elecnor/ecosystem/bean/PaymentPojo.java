package com.elecnor.ecosystem.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "PAYMENT_DETAILS")
public class PaymentPojo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PAYMENT_ID")
	@Expose
	private Long paymentId;
	
	@Column(name = "CURRENCY")
	@Expose
	private String currency;
	
	@Column(name = "FINAL_PRICE")
	@Expose
	private float finalPrice;
	
	@Column(name = "LOGGED_USER_EMAIL_ID")
	@Expose
	private String loggedUserEmailId;
	
	@Column(name = "STRIPE_TOKEN")
	@Expose
	private String stripeToken;
	
	@Transient
	private String paymentType;
	
	/*
	 * Getter & Setter Methods
	 */
	
	public PaymentPojo(){
		
	}
	
	public String getStripeToken() {
		return stripeToken;
	}
	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public String getLoggedUserEmailId() {
		return loggedUserEmailId;
	}
	public void setLoggedUserEmailId(String loggedUserEmailId) {
		this.loggedUserEmailId = loggedUserEmailId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public float getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}	
}
