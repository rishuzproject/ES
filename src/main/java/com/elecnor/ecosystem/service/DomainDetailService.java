package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;

public interface DomainDetailService {

	String setSaveOrUpdateDomain(DomainDetail domainDetail,
			UserDetail userDetails) throws Exception;

}
