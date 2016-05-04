package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.JobDetail;
import com.elecnor.ecosystem.dao.JobsDetailDAOForDownstreamApp;
import com.elecnor.ecosystem.util.ConstantUtil;

@Repository
public class JobsDetailDAOImplForDownstreamApp implements
		JobsDetailDAOForDownstreamApp {
	
	@Autowired
	SessionFactory sessionFactory;

	static Logger logger = Logger.getLogger(JobsDetailDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<JobDetail> getJobDetails(Long domainId) throws Exception {
		ArrayList<JobDetail> jobList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("from JobDetail where DOMAIN_ID =:domainId and STATUS =:status and JOB_NUMBER != NULL");
			query.setParameter("domainId", domainId);
			query.setParameter("status", "ACTIVE");
			jobList = (ArrayList<JobDetail>) query.list();

		} catch (Exception e) {
			logger.info(" ---- Exception occurred while getting List for all Jobs---- \n"
					+ e.getCause());
			e.printStackTrace();
			throw e;
		}
		return jobList;
	}

	@Override
	@Transactional
	public JobDetail getJobById(long jobId) {
		// TODO Auto-generated method stub
		JobDetail jobDetail = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from JobDetail  where JOB_ID=:jobId");
			query.setParameter("jobId", jobId);
			jobDetail = (JobDetail) query.list().get(0);

		} catch (HibernateException e) {
			jobDetail = null;
			e.printStackTrace();
		}
		return jobDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<String> getJobNamesByDomainId(long domainId)
			throws Exception {
		ArrayList<String> jobNames = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("select jobName from JobDetail where DOMAIN_ID=:domainId and STATUS=:status and JOB_NUMBER != NULL");
			query.setParameter("domainId", domainId);
			query.setParameter("status", ConstantUtil.ACTIVE);
			jobNames = (ArrayList<String>) query.list();
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return jobNames;
	}

}
