package com.elecnor.ecosystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "STATES")
public class States {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATE_CODE")
	@Expose
	private String state_Code;
	
	
	@Column(name = "STATE")
	@Expose
	private String state;

	
	
	public String getState_Code() {
		return state_Code;
	}

	public void setState_Code(String state_Code) {
		this.state_Code = state_Code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	

}
