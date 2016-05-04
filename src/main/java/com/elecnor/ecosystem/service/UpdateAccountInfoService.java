package com.elecnor.ecosystem.service;

import javax.servlet.http.HttpSession;

import com.elecnor.ecosystem.bean.UserDetail;

public interface UpdateAccountInfoService {

	String updatePassword(String email, String oldPassword, String newPassword) throws Exception;

	String updateUserDetails(UserDetail applicationUserDetail,
			HttpSession session) throws Exception;

}
