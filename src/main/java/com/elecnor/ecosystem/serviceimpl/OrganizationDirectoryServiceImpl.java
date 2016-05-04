package com.elecnor.ecosystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.OrganizationDirectory;
import com.elecnor.ecosystem.dao.OrganizationDirectoryDAO;
import com.elecnor.ecosystem.service.OrganizationDirectoryService;

@Service
public class OrganizationDirectoryServiceImpl implements OrganizationDirectoryService {

	@Autowired
	OrganizationDirectoryDAO organizationDAO;

	@Override
	public List<OrganizationDirectory> getAllOrganizations() throws Exception {
		return organizationDAO.getAllOrganizations();
	}

	@Override
	public String addOrUpdateOrganizationDetail(OrganizationDirectory selectedOrganization) {
		// TODO Auto-generated method stub
		return organizationDAO.addOrUpdateOrganizationDetail(selectedOrganization);
	}

	@Override
	public String deleteOrganization(Long organizationId) throws Exception {

		String result = null;
		int checkRowExists = organizationDAO.checkSelectedOrganizationExists(organizationId);
		if (checkRowExists > 0) {
			result = "Cannot delete row in organization table when associated departments exists.First delete all associated departments and then respective Organization";
		} else {

			result = organizationDAO.deleteSelectedOrganization(organizationId);

		}
		return result;
	}

	@Override
	public OrganizationDirectory getOrganisationById(Long id) throws Exception {

		return organizationDAO.getOrganisationById(id);
	}

}
