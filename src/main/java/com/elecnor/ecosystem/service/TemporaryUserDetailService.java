package com.elecnor.ecosystem.service;

public interface TemporaryUserDetailService {

	public Boolean validateTemporaryUser(long sovId, String customerEmailId) throws Exception;

	
}
