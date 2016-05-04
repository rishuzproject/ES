package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.VendorDirectory;

public interface VendorDirectoryDAO {

	public String updateVendor(VendorDirectory vendorDirectory) throws Exception;
	public String addNewVendor(VendorDirectory vendorDirectory) throws Exception;
	public ArrayList<VendorDirectory> getAllVendors(Long domainId) throws Exception;
	public String deleteVendor(int vendorId) throws Exception;
	public VendorDirectory getVendorsById(long vendorId) throws Exception;
    public ArrayList<VendorDirectory> getAllVendorsForJobs() throws Exception;
    //for adding pending vendor details
    public ArrayList<VendorDirectory> getVendorDetailsForJobs() throws Exception;
    
    public List<VendorDirectory> getVendorListByDomainId(Long domainId) throws Exception;

	
}
