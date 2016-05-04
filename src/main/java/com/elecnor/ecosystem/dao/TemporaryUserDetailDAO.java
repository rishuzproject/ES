package com.elecnor.ecosystem.dao;

import com.elecnor.ecosystem.bean.TemporaryUserDetail;

public interface TemporaryUserDetailDAO {

	public String customerLinkActivation(TemporaryUserDetail temporaryUserDetail) throws Exception;

	public TemporaryUserDetail getTemporaryUserDetails(long sovId, String customerEmailId) throws Exception;

}
