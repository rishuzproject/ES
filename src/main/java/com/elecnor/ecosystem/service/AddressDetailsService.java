package com.elecnor.ecosystem.service;

import java.util.List;

import com.elecnor.ecosystem.bean.AddressDetails;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;

public interface AddressDetailsService {

	String saveUpdateAddressDetails(List<AddressDetails> addressDetails,
			UserDetail userDetails, DomainDetail domainDetailsBean) throws Exception;

}
