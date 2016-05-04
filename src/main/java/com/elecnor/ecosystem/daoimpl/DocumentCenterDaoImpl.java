package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DocumentCenter;
import com.elecnor.ecosystem.dao.DocumentCenterDao;

@Repository
public class DocumentCenterDaoImpl implements DocumentCenterDao {

	@Autowired
	SessionFactory sessionFactory;

	//method for save document details
	@Override
	@Transactional
	public String setDocumentDetailsSave(DocumentCenter documentCenterBean) throws Exception {
		String result = null;
		try {
			Session hybSes = sessionFactory.getCurrentSession();
			hybSes.merge(documentCenterBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	//method for get all documents details
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<DocumentCenter> getDocumentsList(Long domainId) throws Exception {
		ArrayList<DocumentCenter> documentDetailList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from DocumentCenter where DOMAIN_ID = :domainId");
			query.setParameter("domainId", domainId);
			documentDetailList = (ArrayList<DocumentCenter>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return documentDetailList;
	}

	//method for delete document details
	@Override
	@Transactional
	public String deleteDocumentById(long documentId) throws Exception {
		String result = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			DocumentCenter documentCenterBean = (DocumentCenter) session.get(DocumentCenter.class, documentId);
			session.delete(documentCenterBean);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	//method for downloading document details
	@Override
	@Transactional
	public DocumentCenter setDocumetDowload(long descId) throws Exception {
		DocumentCenter documetDetails = null;
		try{
			Session hybSes = sessionFactory.getCurrentSession();
			documetDetails = (DocumentCenter)hybSes.get(DocumentCenter.class, descId);
		}catch(HibernateException e){
			e.printStackTrace();
			throw e;
		}
		return documetDetails;
	}

}
