package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.SovReviewsTable;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.SovReviewsTableDAO;
import com.elecnor.ecosystem.service.SovReviewsTableService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class SovReviewsTableController {

	@Autowired
	SovReviewsTableService sovReviewsTableService;

	@Autowired
	SovReviewsTableDAO sovReviewsTableDAO;
	
	/*@RequestMapping("/sovReviewsTable")
	public String getSovReviewsTable() {
		return "sovReviewsTable";
	}

	@ModelAttribute(value = "/sovReviewsTableBean")
	public SovReviewsTable getSovReviewsTableBean() {
		System.out.println("Initializing SOV Reviews Table Bean");
		return new SovReviewsTable();
	}*/

	@RequestMapping("/addSOVItemReview")
	public @ResponseBody String addSOVItemReview(@ModelAttribute("sovReviewsTableBean") SovReviewsTable sovReviewsTable,
			HttpSession session, HttpServletRequest request) {

		Utility util = new Utility();
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		UserDetail userDetail = (UserDetail) session.getAttribute("selectedUser");
		try {
			String result = sovReviewsTableService.addNewSOVItemReview(sovReviewsTable, userDetail);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("ajaxResult", "failure");
			resultMap.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(resultMap);
	}
	
	@RequestMapping("/getSOVItemReview")
	public @ResponseBody String getSOVItemReview(@RequestParam("itemNo") String itemNo,
			HttpSession session) {
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			ArrayList<SovReviewsTable> itemReviewList = sovReviewsTableDAO.getSOVReviewsDetails(Long.parseLong(itemNo));
			map.put("statusReturned", "200");
			map.put("allSOVList", itemReviewList);
		} catch (Exception e) {
			map.put("statusReturned", "400");
			map.put("reason", e.getMessage());
			util.sendExceptionEmail(e);
		}
		return util.getJsonResult(map);
	}
}
