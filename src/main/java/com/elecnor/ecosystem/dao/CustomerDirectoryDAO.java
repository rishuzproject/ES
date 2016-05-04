package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.CustomerDirectory;

public interface CustomerDirectoryDAO {

	public CustomerDirectory saveCustomer(CustomerDirectory customerListForm) throws Exception;
	public String setCustomerUpdate(CustomerDirectory customerListForm) throws Exception;
	public ArrayList<CustomerDirectory> getAllCustomersList() throws Exception;
	public String setCustomerDelete(long compId) throws Exception;
	public CustomerDirectory getCustomerById(long customerId) throws Exception;
	ArrayList<CustomerDirectory> getAllCustomersList(Long domainId) throws Exception;
//	public String addNewCustomer(CustomerDirectory vendorDirectory);
	
	public List<CustomerDirectory> getCustomerListByDomainId(Long domainId) throws Exception;
}
