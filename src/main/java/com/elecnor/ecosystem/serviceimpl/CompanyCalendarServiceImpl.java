package com.elecnor.ecosystem.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.CompanyCalendar;
import com.elecnor.ecosystem.bean.UserDetail;
import com.elecnor.ecosystem.dao.CompanyCalendarDAO;
import com.elecnor.ecosystem.service.CompanyCalendarService;


/**
 * @author Vaibhav
 *
 */
@Service
public class CompanyCalendarServiceImpl implements CompanyCalendarService{
	
	@Autowired
	CompanyCalendarDAO companyCalendarDAO;

	/**
	 * This Service will Save / Update the Calendar Event based on EventId
	 */
	@Override
	public CompanyCalendar saveCalendarEvent(CompanyCalendar companyCalendarEventBean, UserDetail userDetails)
			throws Exception {
		CompanyCalendar result = null;
		try {
			companyCalendarEventBean.setDomainDetail(userDetails.getDomainDetail());
			if(companyCalendarEventBean.getEventId() == null){
				companyCalendarEventBean.setSubmittedDate(new Date());
				companyCalendarEventBean.setSubmittedBy(userDetails);
				result = companyCalendarDAO.saveCalendarEvent(companyCalendarEventBean);
			} else {
				companyCalendarEventBean.setUpdatedDate(new Date());
				companyCalendarEventBean.setUpdatedBy(userDetails);
				result = companyCalendarDAO.updateCalendarEvent(companyCalendarEventBean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String deleteCalendarEvent(Long eventId) throws Exception{
		// TODO Auto-generated method stub
		return companyCalendarDAO.deleteCalendarEvent(eventId);
	}

	@Override
	public List<CompanyCalendar> getEventListForDateRange(Date fromDate, Date toDate, Long domainId) throws Exception {
		// TODO Auto-generated method stub
		return companyCalendarDAO.getEventListForDateRange(fromDate, toDate, domainId);
	}

	@Override
	public Integer getHolidayCountInDateRange(Date fromDate, Date toDate,
			Long domainId) throws Exception {
		// TODO Auto-generated method stub
		return companyCalendarDAO.getHolidayCountInDateRange(fromDate, toDate, domainId);
	}

}
