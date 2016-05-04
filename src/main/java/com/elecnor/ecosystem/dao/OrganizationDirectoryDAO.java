package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.OrganizationDirectory;

public interface OrganizationDirectoryDAO {

	public List<OrganizationDirectory> getAllOrganizations() throws Exception;

	public String addOrUpdateOrganizationDetail(OrganizationDirectory selectedOrganization);
	public String deleteSelectedOrganization(Long organizationId)throws Exception;
	public Integer checkSelectedOrganizationExists(Long organizationId)throws Exception;
	
	public OrganizationDirectory getOrganisationById(Long id)throws Exception;

	

}
