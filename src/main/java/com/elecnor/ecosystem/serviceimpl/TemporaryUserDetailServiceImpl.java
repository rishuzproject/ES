package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.TemporaryUserDetail;
import com.elecnor.ecosystem.dao.TemporaryUserDetailDAO;
import com.elecnor.ecosystem.service.TemporaryUserDetailService;

@Service
public class TemporaryUserDetailServiceImpl implements TemporaryUserDetailService{

	@Autowired
	TemporaryUserDetailDAO temporaryUserDetailDAO;
	
	@Override
	public Boolean validateTemporaryUser(long sovId, String customerEmailId) throws Exception {
		
		Boolean isvalid = false;
		try {
			TemporaryUserDetail temporaryUserDetail = temporaryUserDetailDAO.getTemporaryUserDetails(sovId, customerEmailId);
			if(temporaryUserDetail != null){
				Date currentDate = new Date();
				if((currentDate.compareTo(temporaryUserDetail.getActivationEndDate()))<=0){
					isvalid = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isvalid;
	}
}
