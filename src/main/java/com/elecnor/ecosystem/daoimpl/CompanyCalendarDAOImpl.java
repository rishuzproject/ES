package com.elecnor.ecosystem.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.CompanyCalendar;
import com.elecnor.ecosystem.dao.CompanyCalendarDAO;

@Repository
public class CompanyCalendarDAOImpl implements CompanyCalendarDAO {

	@Autowired
	SessionFactory sessionFactory;

	/*@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ArrayList<CompanyCalendar> getAllholidayDetails(long signUpDomainId)
			throws Exception {
		ArrayList<CompanyCalendar> allHolidayDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from HolidayCalendar where STATUS = 'ACTIVE' and DOMAIN_ID =:signUpDomainId ORDER BY MONTH(FROM_DATE),DAYOFMONTH(FROM_DATE),TO_DATE");
			query.setParameter("signUpDomainId", signUpDomainId);
			allHolidayDetails = (ArrayList<CompanyCalendar>) query
					.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allHolidayDetails;
	}*/

	@Override
	@Transactional
	public CompanyCalendar saveCalendarEvent(CompanyCalendar companyCalendarEventBean) throws Exception {
		CompanyCalendar result = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			result = (CompanyCalendar)session.merge(companyCalendarEventBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public CompanyCalendar updateCalendarEvent(CompanyCalendar companyCalendarEventBean) throws Exception {
		CompanyCalendar result = null;
		try {
			Query query = sessionFactory .getCurrentSession()
									.createQuery("update CompanyCalendar set "
									+ "EVENT_TYPE =:eventType, EVENT_COLOR_CODE =:eventColorCode, EVENT_TITLE = :eventTitle, "
									+ "EVENT_DESCRIPTION = :eventDesc, FROM_DATE =:fromDate, TO_DATE =:toDate,"
									+ "UPDATED_BY =:updatedBy, UPDATED_DATE =:updatedDate where EVENT_ID =:eventId");
			
			query.setParameter("eventType", companyCalendarEventBean.getEventType());
			query.setParameter("eventColorCode", companyCalendarEventBean.getEventColorCode());
			query.setParameter("eventTitle", companyCalendarEventBean.getEventTitle());
			query.setParameter("eventDesc", companyCalendarEventBean.getEventDescription());
			query.setParameter("fromDate", companyCalendarEventBean.getFromDate());
			query.setParameter("toDate", companyCalendarEventBean.getToDate());
			query.setParameter("updatedBy", companyCalendarEventBean.getUpdatedBy());
			query.setParameter("updatedDate", companyCalendarEventBean.getUpdatedDate());
			query.setParameter("eventId", companyCalendarEventBean.getEventId());
			
			query.executeUpdate();
			result = companyCalendarEventBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public String deleteCalendarEvent(Long eventId) throws Exception {
		String result = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			CompanyCalendar companyCalendarBean = new CompanyCalendar(eventId);
			
			session.delete(companyCalendarBean);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompanyCalendar> getEventListForDateRange(Date fromDate,
			Date toDate, Long domainId) throws Exception {
		// TODO Auto-generated method stub
		Session session;
		Query query;
		try {
			session = sessionFactory.getCurrentSession();
			query = session.createQuery("from CompanyCalendar where ((FROM_DATE BETWEEN :fromDate AND :toDate) "
					+ "OR (TO_DATE BETWEEN :fromDate_1 AND :toDate_1)) and DOMAIN_ID =:domainId");
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("fromDate_1", fromDate);
			query.setParameter("toDate_1", toDate);
			query.setParameter("domainId", domainId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return (List<CompanyCalendar>) query.list();
	}

	@Override
	@Transactional
	public Integer getHolidayCountInDateRange(Date fromDate, Date toDate, Long domainId) throws Exception {
		// TODO Auto-generated method stub
		
		Session session;
		Query query;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer holidayCount = 0;
		try {
			session = sessionFactory.getCurrentSession();
			query = session.createSQLQuery("SELECT SUM(" 
					+"CASE "
					+ "WHEN (:fromDate BETWEEN FROM_DATE AND TO_DATE) AND (:toDate BETWEEN FROM_DATE AND TO_DATE)"
						+ " THEN CAST(:toDate AS DATE) - CAST(:fromDate AS DATE) + 1 "
					+ "WHEN (:fromDate BETWEEN FROM_DATE AND TO_DATE) AND :toDate > TO_DATE"
						+ " THEN TO_DATE - CAST(:fromDate AS DATE) + 1 "
					+ "WHEN (:toDate BETWEEN FROM_DATE AND TO_DATE) AND :fromDate < FROM_DATE"
						+ " THEN CAST(:toDate AS DATE) - FROM_DATE + 1 "
					+ "WHEN :fromDate < FROM_DATE AND :toDate > TO_DATE"
						+ " THEN TO_DATE - FROM_DATE + 1 "
					+ "END ) HOLIDAY "
					+ "FROM COMPANY_CALENDAR "
					+ "WHERE"
						+ "("
						+ "(FROM_DATE BETWEEN :fromDate and :toDate)"
							+ "OR"
						+ "(TO_DATE BETWEEN :fromDate and :toDate)"
						+ ") "
						+ "AND DOMAIN_ID = :domainId");
			query.setParameter("fromDate", sdf.format(fromDate));
			query.setParameter("toDate", sdf.format(toDate));
			query.setParameter("domainId", domainId);
			
			System.out.println("======== > "+query.uniqueResult());
			
			System.out.println("======== >>>>>>>>> " + query.list().get(0));
			
			if(query.uniqueResult()!=null)
				holidayCount = ((Double) query.uniqueResult()).intValue();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return holidayCount;
	}

	/*@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<CompanyCalendar> getAllholidayDetails(int year,
			long signUpDomainId) throws Exception {
		ArrayList<CompanyCalendar> allHolidayDetails = null;
		try {
			String sql = "from HolidayCalendar where STATUS = 'ACTIVE' AND YEAR= "
					+ year
					+ " AND DOMAIN_ID = "
					+ signUpDomainId
					+ " OR IS_HOLIDAY_APPLICABLE_FOR_NEXT_YEAR =true AND STATUS = 'ACTIVE' AND DOMAIN_ID = "
					+ signUpDomainId
					+ " ORDER BY MONTH(FROM_DATE),DAYOFMONTH(FROM_DATE),TO_DATE";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			allHolidayDetails = (ArrayList<CompanyCalendar>) query
					.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return allHolidayDetails;
	}*/

	/*@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<CompanyCalendar> getAllHolidayDetailsFromYearByDomain(
			Integer year, Long signUpDomainId) throws Exception {
		ArrayList<CompanyCalendar> allHolidayDetailsFromYear = null;
		try {
			String sql = "from CompanyCalendar where STATUS = 'ACTIVE' AND YEAR>= "
					+ year
					+ " AND DOMAIN_ID = "
					+ signUpDomainId
					+ " OR IS_HOLIDAY_APPLICABLE_FOR_NEXT_YEAR =true AND STATUS = 'ACTIVE' AND DOMAIN_ID = "
					+ signUpDomainId
					+ " ORDER BY MONTH(FROM_DATE),DAYOFMONTH(FROM_DATE),TO_DATE";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			allHolidayDetailsFromYear = (ArrayList<CompanyCalendar>) query
					.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return allHolidayDetailsFromYear;
	}*/

}
