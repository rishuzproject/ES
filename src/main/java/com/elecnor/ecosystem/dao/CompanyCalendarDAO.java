package com.elecnor.ecosystem.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.elecnor.ecosystem.bean.CompanyCalendar;

public interface CompanyCalendarDAO {

	/*public ArrayList<CompanyCalendar> getAllholidayDetails(long signUpDomainId) throws Exception;*/

	public CompanyCalendar saveCalendarEvent(CompanyCalendar companyCalendarEventBean) throws Exception;

	public CompanyCalendar updateCalendarEvent(CompanyCalendar companyCalendarEventBean) throws Exception;

	public String deleteCalendarEvent(Long eventId) throws Exception;
	
	public List<CompanyCalendar> getEventListForDateRange(Date fromDate, Date toDate, Long domainId) throws Exception;
	
	public Integer getHolidayCountInDateRange(Date fromDate, Date toDate, Long domainId) throws Exception;

	/*public ArrayList<CompanyCalendar> getAllholidayDetails(int year,
			long signUpDomainId) throws Exception;*/
	
	/*public ArrayList<CompanyCalendar> getAllHolidayDetailsFromYearByDomain(Integer year, Long signUpDomainId) throws Exception;*/

}
