package com.elecnor.ecosystem.expose;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.service.JobsDetailService;
import com.elecnor.ecosystem.service.UserDetailService;
import com.elecnor.ecosystem.util.Utility;

@Controller
public class AggregateReportExpose {

	@Autowired
	UserDetailService userDetailService;
	
	@Autowired
	JobsDetailService jobDetailService;
	
	@Autowired
	Utility util;
	
	
	@RequestMapping(value = "/excludeInterceptor/getAggregateReportListByJobId", method = RequestMethod.GET)
	public @ResponseBody String getAggregateReportListByJobId( HttpServletRequest request , 
			@RequestParam(value="query") String query) throws Exception {
		 return util.getJsonResult(jobDetailService.getAggregateReportListByJobId(query));
	}
}
