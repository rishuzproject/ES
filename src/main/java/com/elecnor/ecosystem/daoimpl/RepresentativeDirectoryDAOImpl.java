package com.elecnor.ecosystem.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elecnor.ecosystem.bean.RepresentativeDirectory;
import com.elecnor.ecosystem.dao.RepresentativeDirectoryDAO;

@Repository
public class RepresentativeDirectoryDAOImpl implements RepresentativeDirectoryDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<RepresentativeDirectory> getRepresentative(Integer id,Class<?> className) {
		if(className.getName().equalsIgnoreCase("CustomerDirectory")){
			
		}else if(className.getName().equalsIgnoreCase("CustomerDirectory")){
			
		}
		Query query = sessionFactory.getCurrentSession().createQuery("from RepresentativeDirectory where "+className.getName());
		return null;
	}

	@Override
	public RepresentativeDirectory saveOrUpdateRepresentative(RepresentativeDirectory representative) {
		RepresentativeDirectory result;
		try {
			Session session = sessionFactory.getCurrentSession();
			result = (RepresentativeDirectory) session.merge(representative);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Integer deleteRepresentative(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
