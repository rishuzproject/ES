package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.UserDetail;

public interface TemporaryRegistrationService {

	
	String registerNewUser(UserDetail temporarySignupDetail, String loginTypeForActivation) throws Exception;

	String activateNewUserAccount(String emailId, String name, String loginType, String loginFrom);

}
