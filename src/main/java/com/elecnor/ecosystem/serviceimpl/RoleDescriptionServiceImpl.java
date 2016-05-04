package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.ApplicationUserRole;
import com.elecnor.ecosystem.dao.RoleDescriptionDAO;
import com.elecnor.ecosystem.service.RoleDescriptionService;

@Service
public class RoleDescriptionServiceImpl implements RoleDescriptionService{
@Autowired
RoleDescriptionDAO appUserMappingDAO;
	@Override
	public String addOrUpdateRole(ApplicationUserRole appUserRoleBean) {
		// TODO Auto-generated method stub
		String str=null;
		try{
	    appUserRoleBean.setSubmittedDate(new Date());
	    appUserMappingDAO.addOrUpdateRole(appUserRoleBean);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			str=e.getMessage();
		}
		return str;
	}

}
