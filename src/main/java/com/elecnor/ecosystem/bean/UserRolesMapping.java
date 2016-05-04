package com.elecnor.ecosystem.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "USER_ROLES_MAPPING")
public class UserRolesMapping implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_MAPPING_ID")
	@Expose
	private Long roleMappingId;
	
	@Column(name = "IS_DEFAULT_ROLE")
	@Expose
	private Boolean isDefaultRole;
	
	@Column(name = "ROLE_NAME")
	@Expose
	private String roleName;
	
	@Column(name = "USER_EMAIL_ID")
	@Expose
	private String userEmailId;

	// bi-directional many-to-one association to User Detail
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserDetail userRolesDetail;
	
	// bi-directional many-to-one association to User Role
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	@Expose
	private UserRoles userRolesId;
	
	public UserRolesMapping(){
		
	}
	
	public Long getRoleMappingId() {
		return roleMappingId;
	}

	public void setRoleMappingId(Long roleMappingId) {
		this.roleMappingId = roleMappingId;
	}

	public Boolean getIsDefaultRole() {
		return isDefaultRole;
	}

	public void setIsDefaultRole(Boolean isDefaultRole) {
		this.isDefaultRole = isDefaultRole;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public UserDetail getUserRolesDetail() {
		return userRolesDetail;
	}

	public void setUserRolesDetail(UserDetail userRolesDetail) {
		this.userRolesDetail = userRolesDetail;
	}

	public UserRoles getUserRolesId() {
		return userRolesId;
	}

	public void setUserRolesId(UserRoles userRolesId) {
		this.userRolesId = userRolesId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
