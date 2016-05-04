package com.elecnor.ecosystem.expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.service.ChangeOrderService;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class ChangeOrderExpose {
	
	@Autowired
	ChangeOrderService changeOrderService;
	
	@Autowired
	Utility util;
	
	// This method Can be Removed , By Vaibhav 
	/*@RequestMapping(value = "/excludeInterceptor/getChangeOrderByJobId")
	public @ResponseBody String getJobDetailsByJobId(HttpServletRequest request) {
		Utility util = new Utility();
		long jobId = 0;
		try {
			if (request.getParameter("jobId") != null
					&& request.getParameter("jobId").trim() != "") {
				jobId = Long.parseLong(request.getParameter("jobId"));
			}
			ArrayList<RfcLog> rfcList = changeOrderDao.getAllRfcLogList((int) jobId);
			return util.getJsonResult(rfcList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	/**
	 * This method is to get the Change Order List based on Job ID. 
	 * @param request , jobId - Get Parameter
	 * @return Change Order List in json format
	 */
	@RequestMapping(value = "/excludeInterceptor/getChangeOrderListByJobId", method = RequestMethod.GET)
	public @ResponseBody String getChangeOrderListByJobId( HttpServletRequest request , 
			@RequestParam(value="jobId") String jobIdStr) throws Exception {

		return util.getJsonResult(changeOrderService.getChangeOrderListByJobId(jobIdStr));
	}
	
	@RequestMapping(value = "/excludeInterceptor/getChangeOrderListByJobIdForPD")
	public @ResponseBody String getChangeOrderListByJobIdForPD(HttpServletRequest request) throws Exception {

		Utility util = new Utility();
		String jobIdStr = request.getParameter("id");
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		List<RfcLog> rfclogsList = new ArrayList<RfcLog>();
		RfcLog rfcLog = new RfcLog();
		rfcLog.setsNo(0);
		rfcLog.setCustRefNo("N/A");
		rfcLog.setRfcDesc("N/A");
		rfclogsList.add(rfcLog);
		rfclogsList.addAll(changeOrderService.getChangeOrderListByJobId(jobIdStr));
		resultMap.put("ajaxResult", "success");
		resultMap.put("rfclogsList", rfclogsList);
		return util.getJsonResult(resultMap);
	}
}
