package com.elecnor.ecosystem.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.JobUploadDocument;
import com.elecnor.ecosystem.dao.JobUploadDocumentDAO;

@Repository
public class JobUploadDocumentDAOImpl implements JobUploadDocumentDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public JobUploadDocument saveDocumentIds(JobUploadDocument jobUploadDocument) {
		JobUploadDocument info = null;
		try {
			info = (JobUploadDocument) sessionFactory.getCurrentSession().merge(jobUploadDocument);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<JobUploadDocument> getUploadDocumentIds(Integer id) {
		List<JobUploadDocument> jobDocList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from JobUploadDocument where JOB_ID =:id");
			query.setParameter("id", id);

			jobDocList = (List<JobUploadDocument>) query.list();
		} catch (Exception reason) {

		}
		return jobDocList;
	}

	@Override
	@Transactional
	public Integer updateDocumentIds(JobUploadDocument jobUploadDocument) {
		int result = 0;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("update JobUploadDocument set APPLICATION_UPLOAD_FILE_ID =:fileIds where JOB_UPLOAD_DOC_ID =:id");
			query.setParameter("fileIds", jobUploadDocument.getAppUploadFileId());
			query.setParameter("id", jobUploadDocument.getId());
			result = query.executeUpdate();
		} catch (Exception reason) {
			reason.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JobUploadDocument getJobUploadDocument(String fieldName, Integer id) {

		JobUploadDocument jobUploadInfo = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from JobUploadDocument where ASSOCIATED_FIELD =:fieldName and JOB_ID =:id");
			query.setParameter("fieldName", fieldName);
			query.setParameter("id", id);

			List<JobUploadDocument> info = (List<JobUploadDocument>) query.list();

			if (info.size() > 0) {
				jobUploadInfo = info.get(0);
			}
		} catch (Exception reason) {
			reason.printStackTrace();
		}
		return jobUploadInfo;
	}

	@Override
	public Integer deleteDocumentId(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
