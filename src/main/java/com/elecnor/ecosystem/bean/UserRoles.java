package com.elecnor.ecosystem.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "USER_ROLES")
public class UserRoles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	@Expose
	private Long roleId;

	@Expose
	@Column(name = "ROLE_NAME")
	private String roleName;

	@Expose
	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;

	// bi-directional many-to-one association to User Roles Mapping
	@OneToMany(mappedBy = "userRolesId")
	private Set<UserRolesMapping> userRolesMappingRoleId;

	// bi-directional many-to-one association to Role View mapping
	@OneToMany(mappedBy = "roleId")
	private Set<RoleViewMapping> roleViewMapping;

	public UserRoles() {

	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<UserRolesMapping> getUserRolesMappingRoleId() {
		return userRolesMappingRoleId;
	}

	public void setUserRolesMappingRoleId(Set<UserRolesMapping> userRolesMappingRoleId) {
		this.userRolesMappingRoleId = userRolesMappingRoleId;
	}

	public Set<RoleViewMapping> getRoleViewMapping() {
		return roleViewMapping;
	}

	public void setRoleViewMapping(Set<RoleViewMapping> roleViewMapping) {
		this.roleViewMapping = roleViewMapping;
	}
	
}
