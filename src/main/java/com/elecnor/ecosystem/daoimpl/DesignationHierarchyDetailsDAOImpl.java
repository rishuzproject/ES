package com.elecnor.ecosystem.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.ecosystem.bean.DesignationHierarchyDetails;
import com.elecnor.ecosystem.dao.DesignationHierarchyDetailsDAO;

@Repository
public class DesignationHierarchyDetailsDAOImpl implements DesignationHierarchyDetailsDAO{

	@Autowired
//	@Qualifier("masterDBSessionFactory")
	private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	@Override
//	@Transactional("masterDB")
	@Transactional
	public List<DesignationHierarchyDetails> getParentDesignationsForGivenId(int designationId) {
		List<DesignationHierarchyDetails> desigHierarchy = null;
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("from DesignationHierarchyDetails where DESIGNATION_ID =:designationId"
					+ " AND TYPE ='PARENT'");
			   query.setParameter("designationId", designationId);
			   desigHierarchy = query.list();

		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		return desigHierarchy;
	}

	@Override
//	@Transactional("masterDB")
	@Transactional
	public String addDesignationHierarchy(DesignationHierarchyDetails designationHierarchyToBeAdded) {
		// TODO Auto-generated method stub
		String status = "";
		try {
			Session session = (Session) sessionFactory.getCurrentSession();
			session.save(designationHierarchyToBeAdded);
		} catch (HibernateException e) {
			status = e.getMessage();
			e.printStackTrace();
		}
		return status;
	}

	
	@Override
//	@Transactional("masterDB")
	@Transactional
	public String deleteDesignationHierarchyDetails(int designationId)throws Exception{
		String status="success";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery("delete from DesignationHierarchyDetails where associatedDesignation =:designationId or associatedParentDesignation =:designationId");
	    query.setParameter("designationId", designationId);
		query.executeUpdate();
		}catch(Exception e){
			status=e.getMessage();
			e.printStackTrace();
		}
		return status;
	}
	
}
