package com.elecnor.ecosystem.service;

import java.util.Date;
import java.util.List;

import com.elecnor.ecosystem.bean.CompanyCalendar;
import com.elecnor.ecosystem.bean.UserDetail;

/**
 * @author Vaibhav
 *
 */
public interface CompanyCalendarService {

	public CompanyCalendar saveCalendarEvent(CompanyCalendar companyCalendarEventBean,UserDetail userDetails) throws Exception;
	
	public String deleteCalendarEvent(Long eventId) throws Exception;
	
	public List<CompanyCalendar> getEventListForDateRange(Date fromDate , Date toDate , Long domainId) throws Exception;
	
	public Integer getHolidayCountInDateRange(Date fromDate, Date toDate, Long domainId) throws Exception;

}
