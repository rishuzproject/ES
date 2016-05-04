package com.elecnor.ecosystem.dao;

import java.math.BigInteger;
import java.util.ArrayList;

import com.elecnor.ecosystem.bean.DomainDetail;

public interface DomainDetailDAO {

	ArrayList<DomainDetail> getDomainDetail()throws Exception;

	DomainDetail saveDomainDetail(DomainDetail domainDetail)throws Exception;

	String updateDomainDetail(DomainDetail domainDetail, Long domainId)throws Exception;

	boolean isDomainNameExist(String domainName, Long domainIdToCheck)throws Exception;

	ArrayList<DomainDetail> getDomainDetail(long domainId)throws Exception;

	DomainDetail getExposeDomainBean(BigInteger selectedDomainId) throws Exception;

}
