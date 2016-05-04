package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.elecnor.ecosystem.bean.AddressDetails;
import com.elecnor.ecosystem.bean.States;
import com.elecnor.ecosystem.bean.UserDetail;

public interface AddressDetailsDAO {

	String saveUpdateAddress(List<AddressDetails> addressDetails) throws Exception;

	String updateAddress(AddressDetails addressDetails) throws Exception;

	ArrayList<AddressDetails> getAddressDetails(Long domainId,String moduleName) throws Exception;

	String deleteAddress(Long addressId, UserDetail userDetails) throws Exception;

	String setDeleteAddressByUser(int compId, UserDetail userDetails)throws Exception;
	
	List<States> getStates();
	
	List<String> getCities(String stateCode);

}
