package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.DomainDetailDAO;
import com.elecnor.ecosystem.service.DomainDetailService;

@Service
public class DomainDetailServiceImpl implements DomainDetailService {

	@Autowired
	DomainDetailDAO domainDetailDAO;

	@Override
	public String setSaveOrUpdateDomain(DomainDetail domainDetail, UserDetail userDetails) throws Exception {
		String result = null;
		long domainId = 0;
		try {
			if (domainDetail.getDomainId() != null) {
				domainId = domainDetail.getDomainId();
			}
			boolean isExist = domainDetailDAO.isDomainNameExist(domainDetail.getDomainName(), domainId);
			if (isExist) {
				result = "domainNameExist";
			} else {
				domainDetail.setSubmittedEmailId(userDetails.getEmailId());
				domainDetail.setSubmittedName(userDetails.getFirstName());
				domainDetail.setSubmittedUserId(userDetails.getUserId());
				domainDetail.setUpdatedDate(new Date());
				result = domainDetailDAO.updateDomainDetail(domainDetail, domainId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
