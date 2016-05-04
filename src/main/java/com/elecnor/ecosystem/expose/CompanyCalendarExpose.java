package com.elecnor.ecosystem.expose;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.ecosystem.service.CompanyCalendarService;
import com.elecnor.ecosystem.util.Utility;


@Controller
public class CompanyCalendarExpose {

	@Autowired
	CompanyCalendarService campanyCalendarService;
	
	@Autowired
	Utility util;

	/*@RequestMapping(value = "/excludeInterceptor/getHolidayListByDomain")
	public @ResponseBody String getHolidayListByDomain(
			HttpServletRequest request) {
		Utility util = new Utility();
		Long domainId = Long.parseLong(request.getParameter("id"));
		int year = Integer.parseInt(request.getParameter("year"));
		try {
			return util.getJsonResult(holidayCalendarDAO
					.getAllHolidayDetailsFromYearByDomain(year, domainId));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}*/
	
	@RequestMapping(value = "/excludeInterceptor/getHolidayListByDomain")
	public @ResponseBody String getHolidayListByDomain(@RequestParam("startDate") String startDateStr , @RequestParam("leadTime") int leadTime,
			@RequestParam("domainId") Long domainId, HttpServletRequest request) {
		
		Date startDate , endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		try {
			startDate = util.stringToDate(startDateStr, "MM-dd-yyyy");
			endDate = getNextDateExcludingWeekends(startDate, leadTime);
			int holidayCount = campanyCalendarService.getHolidayCountInDateRange(startDate, endDate, domainId);
			
			while(holidayCount > 0){
				startDate = endDate;
				endDate = getNextDateExcludingWeekends(endDate, holidayCount);
				System.out.println("===== >> "+startDate + "======= >>> "+endDate);
				holidayCount = campanyCalendarService.getHolidayCountInDateRange(startDate, endDate, domainId);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sdf.format(endDate);
	}
	
	/**
	 * This methos 
	 * @param startDate
	 * @param noOfDays
	 * @return
	 */
	public Date getNextDateExcludingWeekends(Date startDate , int noOfDays){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		for(int counter = 0; counter <= noOfDays;) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
	        //here sat and sun are added
	        //but at the end it goes to the correct week day.
	        //because i is only increased if it is week day
			// DAY_OF_WEEK 1 -- Sunday , 7 -- Saturday.
	        if(calendar.get(Calendar.DAY_OF_WEEK) >= 2 && calendar.get(Calendar.DAY_OF_WEEK) <=6) {
	        	System.out.println("=== >>> "+calendar.getTime());
	        	counter++;
	        }
		}
		
		return calendar.getTime();
	}
}
