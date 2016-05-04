package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.SovDirectory;
import com.elecnor.ecosystem.bean.UserDetail;

public interface SovDirectoryService {
	
	public String addUpdateSOV(SovDirectory sovDirectory, UserDetail userDetail,JobDetail jobDetail) throws Exception;

}
