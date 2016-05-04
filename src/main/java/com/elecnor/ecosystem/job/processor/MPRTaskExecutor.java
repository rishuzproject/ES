package com.elecnor.ecosystem.job.processor;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.dao.ApplicationDetailsDAO;

/**
 * 
 * @author Vaibhav
 *
 */
@Component
public class MPRTaskExecutor /* implements TaskExecutor */ {

	@Autowired
	ApplicationDetailsDAO applicationDetailsDAO;

	// @Override
	public boolean ExecuteTaskForNewJob(String startDate, String endDate, String oldStartDt, String oldEndDt, boolean isUpdate, Long projectid, String submittedBy) throws Exception {
		// Getting application url
		ArrayList<ApplicationDirectory> allApplicationDetailsList = applicationDetailsDAO.getApplicationDetails();
		String urlHeader = "";
		boolean result = false;
		for (ApplicationDirectory dir : allApplicationDetailsList) {
			if (dir.getApplicationName().trim().equalsIgnoreCase("MPR")) {
				urlHeader = dir.getApplicationLink();
				break;
			}
		}
		RestTemplate restTemplate = new RestTemplate();
		String url = "excludeFilter/addOrUpdateCashflow?startDate=" + startDate + "&endDate=" + endDate + "&oldStartDate=" + oldStartDt + "&oldEndDate=" + oldEndDt + "&isUpdate=" + isUpdate
				+ "&projectId=" + projectid + "&submittedBy=" + submittedBy;
		try {
			String callStatus = restTemplate.getForObject(urlHeader + url, String.class);
			if (callStatus.trim().equalsIgnoreCase("success")) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// @Override
	public boolean ExecuteTaskForUpdateJob() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	// @Override
	public boolean ExecuteTaskForDeleteJob() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
