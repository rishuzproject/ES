package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.ContractorDirectory;

public interface ContractorDirectoryDAO {

	public ContractorDirectory setContractorSave(ContractorDirectory ContractorListForm) throws Exception;
	public ContractorDirectory setContractorUpdate(ContractorDirectory ContractorListForm) throws Exception;
	public ArrayList<ContractorDirectory> getAllContractorList() throws Exception;
	public String setContractorDelete(long conId) throws Exception;
	public ArrayList<ContractorDirectory> getAllContractorList(Long domainId) throws Exception;
    public ContractorDirectory getContractorDetailByID(long conId) throws Exception;
    
    public List<ContractorDirectory> getContractorListByDomainId(Long domainId) throws Exception;
}
