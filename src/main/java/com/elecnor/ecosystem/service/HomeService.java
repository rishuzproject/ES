package com.elecnor.ecosystem.service;

import java.util.HashMap;

import com.elecnor.ecosystem.bean.DomainDetail;

public interface HomeService {

	public HashMap<Object, Object> getApplicationDetails(Boolean isBelcouser, DomainDetail domainDetail) throws Exception;

}
