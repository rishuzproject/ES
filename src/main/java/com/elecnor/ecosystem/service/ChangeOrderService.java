package com.elecnor.ecosystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.UserDetail;

public interface ChangeOrderService {

	public RfcLog addRfcService(RfcLog log, UserDetail loginUserProfileBean, JobDetail selectedProjectBean, HttpSession session, HttpServletRequest request) throws Exception;
	
	public List<RfcLog> getChangeOrderListByJobId(String jobIdStr) throws Exception;

}
