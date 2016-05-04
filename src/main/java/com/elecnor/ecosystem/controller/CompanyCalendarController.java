package com.elecnor.ecosystem.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.bean.CompanyCalendar;
import com.elecnor.ecosystem.bean.DomainDetail;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.service.CompanyCalendarService;
import com.elecnor.ecosystem.util.Utility;

/**
 * 
 * @author Vaibhav
 * @Vaibhav
 * Removing Navigation API to Navigation Controller. 
 */

@Controller
public class CompanyCalendarController {

	@Autowired
	CompanyCalendarService holidayCalendarService;
	
	@Autowired
	Utility util;

	/*@RequestMapping("/holiday")
	public String getHolidayDetailPage() {
		return "holidayCalendar";
	}

	@RequestMapping("/holidayCalendarForm")
	public HolidayCalendar generateHoliday() {
		return new HolidayCalendar();
	}*/
	
	/**
	 * API to save or Update the calendar event.
	 * @param companyCalendarEventBean
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveCalendarEvent", method = RequestMethod.POST)
	@ResponseBody
	public String saveCalendarEvent(@RequestBody CompanyCalendar companyCalendarEventBean, HttpSession session, HttpServletRequest request) {
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			CompanyCalendar result = holidayCalendarService.saveCalendarEvent(companyCalendarEventBean, userDetails);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}
	
	/**
	 * API to delete the calendar event.
	 * @param eventId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteCalendarEvent", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCalendarEvent(@RequestParam("eventId") Long eventId, HttpSession session) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			String result = holidayCalendarService.deleteCalendarEvent(eventId);
			resultMap = util.responseBuilder(result);
		} catch (Exception e) {
			util.handleException(resultMap, e);
		}
		return util.getJsonResult(resultMap);
	}

	/**
	 * API to get the calendar event between given date range.
	 * @param fromDate
	 * @param toDate
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getEventListForDateRange", method = RequestMethod.GET)
	@ResponseBody
	public String getEventListForDateRange(@RequestParam("fromDate") String fromDateString, 
			@RequestParam("toDate") String toDateString, HttpSession session) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		try {
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			long domainId = domainDetail.getDomainId();
			Date fromDate = formatter.parse(fromDateString);
			Date toDate = formatter.parse(toDateString);
			List<CompanyCalendar> calendarEventList = holidayCalendarService.getEventListForDateRange(fromDate , toDate , domainId);
			map.put("calendarEventList", calendarEventList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}


	/**
	 * Method for fetch holiday based on year
	 * @param session
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/getHolidayDetailsBasedOnYear", method = RequestMethod.POST)
	@ResponseBody
	public String getHolidayDetailsBasedOnYear(HttpSession session,HttpServletRequest request) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Utility util = new Utility();
		CompanyCalenderHelper holidayCalendarHelper = new CompanyCalenderHelper();
		try {
			int year = holidayCalendarHelper.getYearFromRequest(request);
			UserDetail userDetails = (UserDetail) session.getAttribute("selectedUser");
			DomainDetail domainDetail = userDetails.getDomainDetail();
			long signUpDomainId = domainDetail.getDomainId();
			ArrayList<CompanyCalendar> allHolidayList = holidayCalendarDAO.getAllholidayDetails(year, signUpDomainId);
			map.put("allHolidayList", allHolidayList);
		} catch (Exception e) {
			util.handleException(map, e);
		}
		return util.getJsonResult(map);
	}*/
}
