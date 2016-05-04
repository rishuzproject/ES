package com.elecnor.ecosystem.helper;

import javax.servlet.http.HttpSession;

public class DomainInfoInDetailHelper {

	public Long getDomainIdFromSession(HttpSession session) throws Exception {

		Long domainId = 0L;
		try {
			if (session.getAttribute("domainId") != null) {
				domainId = (Long) session.getAttribute("domainId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return domainId;
	}

}
