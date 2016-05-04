package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.SovDirectoryDAO;
import com.elecnor.ecosystem.service.SovDirectoryService;

@Service
public class SovDirectoryServiceImpl implements SovDirectoryService {

	@Autowired
	SovDirectoryDAO sovDirectoryDAO;

	public String addUpdateSOV(SovDirectory sovDirectory, UserDetail userDetail,JobDetail jobDetail) throws Exception {

		sovDirectory.setJobDetail(jobDetail);
		sovDirectory.setStatus("ACTIVE");
		String result = "";
		if (sovDirectory.getSovId() == null) { // create new SOV
			sovDirectory.setSubmittedBy(userDetail);
			sovDirectory.setSubmittedDate(new Date());
		} else { // update existing SOV
			sovDirectory.setUpdatedBy(userDetail);
			sovDirectory.setUpdatedDate(new Date());
		}
		try {
			result = sovDirectoryDAO.addUpdateSOV(sovDirectory);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
