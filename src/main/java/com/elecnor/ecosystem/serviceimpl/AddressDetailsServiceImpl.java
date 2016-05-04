package com.elecnor.ecosystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.AddressDetails;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.AddressDetailsDAO;
import com.elecnor.ecosystem.service.AddressDetailsService;

@Service
public class AddressDetailsServiceImpl implements AddressDetailsService {

	@Autowired
	AddressDetailsDAO addressDetailsDAO;

	@Override
	public String saveUpdateAddressDetails(List<AddressDetails> addressDetails, UserDetail userDetails, DomainDetail domainDetailsBean) throws Exception {
		return addressDetailsDAO.saveUpdateAddress(addressDetails);
	}

}
