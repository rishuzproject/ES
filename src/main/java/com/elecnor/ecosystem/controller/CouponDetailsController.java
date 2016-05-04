package com.elecnor.ecosystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.CouponDetails;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.CouponDetailsDAO;
import com.elecnor.ecosystem.helper.CouponDetailsHelper;
import com.elecnor.ecosystem.service.CouponDetailsService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class CouponDetailsController {
	
	@Autowired
	CouponDetailsDAO couponDetailsDAO;
	@Autowired
	CouponDetailsService couponDetailsService;

	/*@RequestMapping("/couponDetails")
	public String getCouponDetailPage() {
		return "couponDetails";
	}

	@ModelAttribute(value = "/couponForm")
	public CouponDetails generateCoupon() {
		return new CouponDetails();
	}*/
	
	@RequestMapping(value = "/saveOrUpdateCouponForm", method = RequestMethod.POST)
	public @ResponseBody
	String setSaveOrUpdateCoupon(
			@ModelAttribute("couponForm") CouponDetails couponDetails,
			@RequestParam("typeOfAction") String actionTemp,
			HttpSession session, HttpServletRequest request) {
		System.out.println("controller");
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			String result = couponDetailsService.setSaveOrUpdateCoupon(couponDetails, userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/getCouponDetail", method = RequestMethod.POST)
	@ResponseBody
	public String getAllCouponDetails() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		try {
			ArrayList<CouponDetails> allCouponList = couponDetailsDAO.getAllCouponDetails();
			map.put("allCouponList", allCouponList);
		} catch (Exception e) {
			map = util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}

	@RequestMapping(value = "/couponCodeDeleteAction", method = RequestMethod.POST)
	@ResponseBody
	public String couponDelete(HttpSession session,HttpServletRequest request) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		Utility util = new Utility();
		CouponDetailsHelper couponDetailsHelper = new CouponDetailsHelper();
		try {
			long couponId = couponDetailsHelper.setCouponIdToDelete(request);
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			String result = couponDetailsDAO.setCouponDelete(couponId,userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			resultMap = util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

}
