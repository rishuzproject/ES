package com.elecnor.ecosystem.service;


import com.elecnor.ecosystem.bean.DomainDetail;

public interface RegistrationDetailsService {
	public String saveRegistrationDetails(DomainDetail signupDomainDetail, String emailId,String name,String nickname,String phonecarrier,String phonenumber,String domainname,String companyName,String companyAddress);
	public String activateNewUserAccount(String emailId, String name);

}
