package com.elecnor.ecosystem.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.ProjectType;
import com.elecnor.ecosystem.dao.TypesOfProjectDAO;

@Repository
public class TypeProjectDAOimpl implements TypesOfProjectDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private ArrayList<ProjectType> allProjectTypes;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<ProjectType> getAllProjectTypes(long signUpDomainId) throws Exception {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from ProjectType where DOMAIN_ID=:signUpDomainId AND STATUS='ACTIVE'");
			query.setParameter("signUpDomainId", signUpDomainId);
			allProjectTypes = (ArrayList<ProjectType>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return allProjectTypes;
	}

	@Override
	@Transactional
	public String addProjectTypeRecord(Object projType) throws Exception {
		String ex = null;
		Session ses = null;
		try {
			ses = (Session) sessionFactory.getCurrentSession();
			ses.merge(projType);
		} catch (Exception e) {
			ex = e.getMessage();
			throw e;
		}
		return ex;
	}

	@Override
	@Transactional
	public String updateProjectTypeRecord(ProjectType projTypeBean) throws Exception {
		String ex = null;
		try {
			System.out.println("Enter the update section" + projTypeBean.getProjectTypeId() + "------"
					+ projTypeBean.getProjectTypeName());
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update ProjectType set PROJECT_TYPE_NAME=:projTypeName,UPDATED_BY=:updatedBy,UPDATED_DATE=:updatedDate where PROJECT_TYPE_ID=:projTypeId");
			query.setParameter("projTypeId", projTypeBean.getProjectTypeId());
			query.setParameter("projTypeName", projTypeBean.getProjectTypeName());
			query.setParameter("updatedBy", projTypeBean.getUpdatedBy());
			query.setParameter("updatedDate", projTypeBean.getUpdatedDate());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return ex;
	}

	@Override
	@Transactional
	public String deleteProjectTypeRecord(long projTypeId) throws Exception {
		String ex = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"update ProjectType set STATUS='INACTIVE' where PROJECT_TYPE_ID=:projTypeId");
			query.setParameter("projTypeId", projTypeId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return ex;
	}

	@Override
	@Transactional
	public ProjectType getProjectById(long projTypeId) throws Exception {
		// TODO Auto-generated method stub
		ProjectType projType = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from ProjectType  where PROJECT_TYPE_ID=:projTypeId");
			query.setParameter("projTypeId", projTypeId);
			projType = (ProjectType) query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return projType;
	}

	@Override
	@Transactional
	public List<ProjectType> getProjectTypeListByDomainId(Long domainId)
			throws Exception {
		// TODO Auto-generated method stub
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("select new ProjectType(projectTypeId , projectTypeName)" +
					"from ProjectType where DOMAIN_ID=:domainId AND STATUS='ACTIVE'");
			query.setParameter("domainId", domainId);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return query.list();
	}

}
