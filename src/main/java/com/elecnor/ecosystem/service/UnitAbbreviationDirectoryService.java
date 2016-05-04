package com.elecnor.ecosystem.service;

import java.util.HashMap;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UnitAbbreviationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;

public interface UnitAbbreviationDirectoryService {

	public String addUpdateUnitAbbreviationService(UnitAbbreviationDirectory unitAbbreviationBean, UserDetail userDetail,
			DomainDetail domainDetail) throws Exception;

	public HashMap<Object, Object> getAllAbbreviations(Long domainId) throws Exception;
	
}
