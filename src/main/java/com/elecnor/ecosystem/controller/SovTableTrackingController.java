package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.SovTableTracking;
import com.elecnor.ecosystem.dao.SovTableTrackingDAO;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class SovTableTrackingController {

	@Autowired
	SovTableTrackingDAO sovTableTrackingDAO;

	/*@RequestMapping("/sovTableTracking")
	public String getSovTableTracking() {
		return "sovTableTracking";
	}

	@ModelAttribute("/sovTableTrackingBean")
	public SovTableTracking getSovTableTrackingBean() {
		System.out.println("Initializing SOV Table Tracking Bean");
		return new SovTableTracking();
	}*/

	@RequestMapping("/getSovTableTrackingList")
	public @ResponseBody String getSovTableTrackingList(@RequestParam("itemId") String itemNo, HttpSession session) {

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			ArrayList<SovTableTracking> sovTableTrackingList = sovTableTrackingDAO.getSovTrackingList(Long
					.parseLong(itemNo));
			map.put("ajaxResult", "success");
			map.put("sovTableTrackingList", sovTableTrackingList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}
}
