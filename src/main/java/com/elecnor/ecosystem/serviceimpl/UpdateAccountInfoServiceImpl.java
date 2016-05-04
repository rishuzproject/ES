package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.UpdateAccountInfoDAO;
import com.elecnor.ecosystem.dao.UserDetailDAO;
import com.elecnor.ecosystem.service.UpdateAccountInfoService;
import com.elecnor.ecosystem.util.EncrypterDecrypter;

@Service
public class UpdateAccountInfoServiceImpl implements UpdateAccountInfoService {
	
	@Autowired
	UpdateAccountInfoDAO updateAccountInfoDAO;
	@Autowired
	UserDetailDAO userDetailDAO;

	//method for updating password
	@Override
	public String updatePassword(String email, String oldPassword,
			String newPassword) throws Exception {
			String result = null;
			try {
				UserDetail userDetail = userDetailDAO.getUserDetails(email);
				EncrypterDecrypter ed = new EncrypterDecrypter();
				String decPassword = ed.decryptData(userDetail.getPassword().trim());
				if(oldPassword.equalsIgnoreCase(decPassword)){
					String encPassword = ed.encryptData(newPassword);
					result = updateAccountInfoDAO.updatePassword(email,encPassword);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		return result;
	}
	//method for updating user details
	@Override
	public String updateUserDetails(UserDetail applicationUserDetail,
			HttpSession session) throws Exception {
		String result = null;
		try {
			boolean isExist = userDetailDAO
					.isEmailIdExists(applicationUserDetail.getEmailId(),applicationUserDetail.getUserId());
			if (isExist) {
				result = "emaiIdExist";
			} else {
			applicationUserDetail.setUpdatedDate(new Date());
			applicationUserDetail.setUpdatedBy(applicationUserDetail);
			result = userDetailDAO.updateUser(applicationUserDetail);
			}
			UserDetail userDetailsBean = userDetailDAO.getUserDetails(applicationUserDetail.getEmailId());
			session.setAttribute("selectedUser", userDetailsBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
