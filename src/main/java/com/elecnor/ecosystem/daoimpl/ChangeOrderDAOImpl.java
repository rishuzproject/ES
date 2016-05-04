package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.RfcLog;
import com.elecnor.ecosystem.bean.RfcLogTracking;
import com.elecnor.ecosystem.bean.RfcSubCostTypeBean;
import com.elecnor.ecosystem.bean.RfcTakeoffSheet;
import com.elecnor.ecosystem.dao.ChangeOrderDAO;

@Repository
public class ChangeOrderDAOImpl implements ChangeOrderDAO {
	@Autowired
	private SessionFactory sessionFactory;
	static Logger logger = Logger.getLogger(ChangeOrderDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<RfcLog> getAllRfcLogList(int projId) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getAllRfcLogList(int projId) method ---- \n");
		Session ses = null;
		ArrayList<RfcLog> rfcLogList = null;
		try {
			rfcLogList = new ArrayList<RfcLog>();
			ses = sessionFactory.getCurrentSession();
			rfcLogList = (ArrayList<RfcLog>) ses.createQuery("from RfcLog where JOB_ID = :projId AND DELETED=false").setParameter("projId", projId).list();
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting Change Order's List---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return rfcLogList;
	}

	@Override
	@Transactional
	public RfcLog insertOrUpdateRfcLogList(RfcLog rfcLog) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : insertOrUpdateRfcLogList(RfcLog rfcLog) method ---- \n");
		Session ses = null;
		RfcLog obj = null;
		try {
			ses = sessionFactory.getCurrentSession();
			obj = (RfcLog) ses.merge(rfcLog);
			if (rfcLog.getRfcMapping() != null && rfcLog.getRfcMapping().length != 0) {
				for (String map : rfcLog.getRfcMapping()) {
					try {
						deleteRfcLogList(Integer.parseInt(map), true, obj.getsNo());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting inserting or updating Change Order Log List ---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return obj;
	}

	@Override
	@Transactional
	public String deleteRestoreRfcLogList(int sNo, boolean status) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : deleteRfcLogList(int sNo, boolean status) method ---- \n");
		String ex = null;
		Session ses = null;
		try {
			ses = sessionFactory.getCurrentSession();
			RfcLog log = (RfcLog) ses.get(RfcLog.class, sNo);
			log.setDeleted(status);
			ses.update(log);
			ArrayList<RfcLog> rfcLogList = getAllRfcLogList(log.getJobDetail().getJobId().intValue());
			for (RfcLog rfc : rfcLogList) {
				if (rfc.getsNo() == sNo) {
					rfc.setDeleted(status);
					break;
				}
			}
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while Deleting Change Order ---- \n" + e.getCause());
			ex = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return ex;
	}

	@Override
	@Transactional
	public String deleteRfcLogList(int sNo, boolean status, int mappingId) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : deleteRfcLogList(int sNo, boolean status, int mappingId) method ---- \n");
		String ex = null;
		Session ses = null;
		try {
			ses = sessionFactory.getCurrentSession();
			RfcLog log = (RfcLog) ses.get(RfcLog.class, sNo);
			log.setDeleted(status);
			log.setRfcMappingDB(mappingId + "");
			ses.update(log);
			ArrayList<RfcLog> rfcLogList = getAllRfcLogList(log.getJobDetail().getJobId().intValue());
			for (RfcLog rfc : rfcLogList) {
				if (rfc.getsNo() == sNo) {
					rfc.setDeleted(status);
					rfc.setRfcMappingDB(mappingId + "");
					break;
				}
			}
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while Deleting Change Order based on RFC Mapping Id---- \n" + e.getCause());
			ex = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return ex;
	}

	@Override
	@Transactional
	public void insertRfcTracking(RfcLogTracking rfcBeanTracking) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : insertRfcTracking(RfcLogTracking rfcBeanTracking) method ---- \n");
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.merge(rfcBeanTracking);
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while inserting History (Tracking) for Change Order---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RfcLogTracking> getRfcTrackingDetails(int rfcLogId) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getRfcTrackingDetails(int rfcLogId) method ---- \n");
		Session session = null;
		List<RfcLogTracking> result = null;
		try {
			session = sessionFactory.getCurrentSession();
			result = session.createCriteria(RfcLogTracking.class).add(Restrictions.eq("rfcLog.sNo", rfcLogId)).list();
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting History details for Change Order ---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	@Transactional
	public RfcLog getRfcDetails(int sNo) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getRfcDetails(int sNo) method ---- \n");
		Session session = null;
		RfcLog rfcBean = null;
		try {
			session = sessionFactory.getCurrentSession();
			rfcBean = (RfcLog) (session.createCriteria(RfcLog.class).add(Restrictions.eq("sNo", sNo)).list().get(0));
		} catch (IndexOutOfBoundsException e) {
			logger.info(" ---- Exception occurred while getting Change Order Details based on S_NO ---- \n" + e.getCause());
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting Change Order Details based on S_NO ---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return rfcBean;
	}

	@Override
	@Transactional
	public String getLastOrinalReferenceNumberBasedOnType(String hqlQuery) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getLastOrinalReferenceNumberBasedOnType(String hqlQuery) method ---- \n");
		Session session = null;
		String lastOriginalReferenceNumber = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hqlQuery).setMaxResults(1);
			lastOriginalReferenceNumber = (String) query.uniqueResult();
		} catch (Exception exp) {
			logger.info(" ---- Exception occurred while getting Last Origination Reference # Based on Type ---- \n" + exp.getCause());
			exp.printStackTrace();
			throw exp;
		}
		return lastOriginalReferenceNumber;
	}

	@Override
	@Transactional
	public int reOpenApprovedRfc(int rfcNumber) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : reOpenApprovedRfc(int rfcNumber) method ---- \n");
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("update RfcLog set rfcUserResponseStatus='Send For Customer Approval' , rfcStatus='Pending' WHERE sNo=:rfcNumber").setParameter("rfcNumber", rfcNumber);
			int result = query.executeUpdate();
			return result;
		} catch (Exception exp) {
			logger.info(" ---- Exception occurred while Reopening an APPROVED Change Order ---- \n" + exp.getCause());
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	@Transactional
	public List<RfcSubCostTypeBean> getSubcostList(int sNo) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getSubcostList(int sNo) method ---- \n");
		List<RfcSubCostTypeBean> subCostList = new ArrayList<RfcSubCostTypeBean>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT RFC_LOG_SUB_COST_ID,ACTIVITY_ID,ACTIVITY_DESCRIPTION," + "QUOTED_PRICE,ACTIVITY_NUMBER,COST_TYPE,QUANTITY FROM RFC_COST_DETAILS "
					+ "WHERE  S_NO=:rfcSNO");
			query.setParameter("rfcSNO", sNo);
			@SuppressWarnings("unchecked")
			Iterator<Object[]> list = query.list().iterator();

			while (list.hasNext()) {
				Object[] tuple = list.next();
				RfcSubCostTypeBean bean = new RfcSubCostTypeBean();
				bean.setRfcLogSubCostId((Integer) tuple[0]);
				bean.setActivityId((Integer) tuple[1]);
				bean.setActivityDescription((String) tuple[2]);
				bean.setQuotePrice((Double) tuple[3]);
				bean.setActivityNumber((Integer) tuple[4]);
				bean.setCostType((String) tuple[5]);
				bean.setQuantity((Integer) tuple[6]);
				subCostList.add(bean);
			}
		} catch (Exception exp) {
			logger.info(" ---- Exception occurred while getting Sub Cost List ---- \n" + exp.getCause());
			exp.printStackTrace();
			throw exp;
		}
		return subCostList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<RfcLog> getAllRfcLogs() throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getAllRfcLogs() method ---- \n");
		Session ses = null;
		ArrayList<RfcLog> allRfcLogList = null;
		try {
			allRfcLogList = new ArrayList<RfcLog>();
			ses = sessionFactory.getCurrentSession();
			allRfcLogList = (ArrayList<RfcLog>) ses.createQuery("from RfcLog where DELETED=false").list();
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting All the change orders from Database ---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return allRfcLogList;
	}

	@Override
	@Transactional
	public void addRfcTakeSheet(RfcTakeoffSheet rfcTakeoffSheet) {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : addRfcTakeSheet(RfcTakeoffSheet rfcTakeoffSheet) method ---- \n");
		Session ses = null;
		try {
			ses = sessionFactory.getCurrentSession();
			ses.merge(rfcTakeoffSheet);
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while adding Take Off Sheet for a Change Order ---- \n" + e.getCause());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<RfcTakeoffSheet> getAllTakeSheetsList(Long jobId) {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getAllTakeSheetsList() method ---- \n");
		Query ses = null;
		ArrayList<RfcTakeoffSheet> rfcTakeoffSheet = null;
		try {
			rfcTakeoffSheet = new ArrayList<RfcTakeoffSheet>();
			ses = sessionFactory.getCurrentSession().createQuery("from RfcTakeoffSheet where JOB_ID=:jobId");
			ses.setParameter("jobId", jobId);
			rfcTakeoffSheet=(ArrayList<RfcTakeoffSheet>) ses.list();
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting All Take Off Sheet's list ---- \n" + e.getCause());
			e.printStackTrace();
		}
		return rfcTakeoffSheet;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<RfcLog> getAllActiveRfcLogListForProject(int projId) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getAllActiveRfcLogListForProject(int projId) method ---- \n");
		Session ses = null;
		ArrayList<RfcLog> rfcLogList = null;
		try {
			rfcLogList = new ArrayList<RfcLog>();
			ses = sessionFactory.getCurrentSession();
			rfcLogList = (ArrayList<RfcLog>) ses.createQuery("from RfcLog where JOB_ID=:projId and DELETED=false").setParameter("projId", projId).list();
		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting Active Change Order's List form Database ---- \n" + e.getCause());
			e.printStackTrace();
			throw e;
		}
		return rfcLogList;
	}

	// This method is same as getAllRfcLogList(int projId). It is only used for
	// Change Order Expose.
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RfcLog> getChangeOrderListByJobId(String jobIdStr) throws Exception {
		logger.info(" ---- Inside class ChangeOrderDAOImpl : getChangeOrderListByJobId(String jobIdStr) method ---- \n");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RfcLog where JOB_ID = :jobId AND DELETED=false");
		query.setParameter("jobId", jobIdStr);
		return (List<RfcLog>) query.list();
	}
}
