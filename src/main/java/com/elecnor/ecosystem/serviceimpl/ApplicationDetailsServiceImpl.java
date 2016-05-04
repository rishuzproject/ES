package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;
import com.elecnor.ecosystem.service.ApplicationDetailsService;

@Service
public class ApplicationDetailsServiceImpl implements ApplicationDetailsService {
	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;

	@Override
	public String addOrUpdateAppDetail(ApplicationDirectory appDetailBean, UserDetail userDetail) {// User
																// details parameter added from controller
		String result = null;
		appDetailBean.setStatus("ACTIVE");
		try {
			if (appDetailBean.getApplicationId() == null) {
				appDetailBean.setSubmittedDate(new Date());
				appDetailBean.setSubmittedBy(userDetail);
			} else {
				appDetailBean.setUpdatedDate(new Date());
				appDetailBean.setUpdatedBy(userDetail);
			}
			applicationDetailsDAO.addOrUpdateApplicationDetail(appDetailBean);

		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
		}
		return result;
	}
}