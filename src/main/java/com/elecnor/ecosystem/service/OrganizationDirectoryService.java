package com.elecnor.ecosystem.service;

import java.util.List;

import com.elecnor.ecosystem.bean.OrganizationDirectory;

public interface OrganizationDirectoryService {
	public List<OrganizationDirectory> getAllOrganizations() throws Exception;
	
	public String addOrUpdateOrganizationDetail(OrganizationDirectory selectedOrganization);


	public String deleteOrganization(Long organizationId) throws Exception;
	
	public OrganizationDirectory getOrganisationById(Long id)throws Exception;

}
