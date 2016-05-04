package com.elecnor.ecosystem.helper;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.elecnor.ecosystem.bean.UserDetail;

public class UserDetailHelper {

	public boolean checkIfUserDetailBeanExists(ArrayList<UserDetail> userDetailsBean) {

		if (userDetailsBean == null || userDetailsBean.size() == 0)
			return false;
		else
			return true;
	}

	public boolean checkIfEmailIdExists(ArrayList<UserDetail> userDetailsBean) {

		if (userDetailsBean == null || userDetailsBean.size() == 0)
			return false;
		else
			return true;
	}

	public int setUserIdToDelete(HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub
		int userId = 0;
		try {
			if (request.getParameter("userIdToDel") != null && request.getParameter("userIdToDel") != "") {
				userId = Integer.parseInt(request.getParameter("userIdToDel").trim());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return userId;
	}

}
